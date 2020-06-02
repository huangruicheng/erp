package cn.java.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import net.sf.json.JSONArray;
import cn.java.model.Menu;
import cn.java.model.Role;
import cn.java.query.RoleQuery;
import cn.java.service.MenuService;
import cn.java.service.RoleService;
import cn.java.utils.Page;

import com.opensymphony.xwork2.ActionContext;

public class RoleAction extends BaseAction {
	
	
	private RoleQuery query = new RoleQuery();
	
	private Role role = new Role();
	
	private String permIds;
	
	
	public String getPermIds() {
		return permIds;
	}

	public void setPermIds(String permIds) {
		this.permIds = permIds;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public RoleQuery getQuery() {
		return query;
	}

	public void setQuery(RoleQuery query) {
		this.query = query;
	}

	private RoleService roleService;
	
	private MenuService menuService;
	
	
	
	public MenuService getMenuService() {
		return menuService;
	}

	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}

	public RoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}


	public String role_list(){
		
		ActionContext context = ActionContext.getContext();
		
		Integer pageNo = query.getPageNo();
		if(pageNo == null ){
			query.setPageNo(1);
		}
		
		Page page = roleService.queryObjByCondition(query, exclude);
		
		context.put("page", page);
		
		
		return SUCCESS;
	}
	
	/**
	 * ��ɫ���
	 * @return
	 */
	public String role_input(){
		
		return SUCCESS;
	}
	/**
	 * Ȩ�޷���
	 * @return
	 */
	public String role_listPerm(){
		//��ѯ��role
		Role role = roleService.getObj(query.getRoleId());
		//��õ��û��µĲ˵�
		Set<Menu> menus = role.getMenus();
		//��ȡ��ϵͳ�˵�
		Menu menu = menuService.getObj(1);
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		createTreeDate(menu,list,menus);
		//������listת��ΪJSON����
		JSONArray ja = new JSONArray().fromObject(list);
		ActionContext context = ActionContext.getContext();
		context.put("zNodes", ja);
		return SUCCESS;
	}
	/**
	 * ��װ���ݣ��������ݣ����õݹ�
	 * @param menu ���в˵�
	 * @param list 
	 * @param roleMenus ��ɫ���еĲ˵�
	 */
	public void createTreeDate(Menu menu, List<Map<String,Object>> list,Set<Menu> roleMenus){
		Map<String,Object> map = new HashMap<String,Object>();
		if(menu != null){
			Integer id = menu.getMenuId();
			Integer pId = menu.getParentMenuId();
			String name = menu.getName();
			//���idΪ1����ϵͳ�˵�������Ҫչʾ����
			if(id.intValue() != 1){
				map.put("id", id);
				map.put("pId", pId);
				map.put("name", name);
				//����Ѿ��в˵�����Ҫ����չʾ����
				if(roleMenus != null){
					for(Menu m : roleMenus){
						if(menu.getMenuId().intValue() == m.getMenuId().intValue()){
							map.put("checked", true);
							map.put("open", true);
							break;
						}
					}
				}
				list.add(map);
			}
			Set<Menu> menus = menu.getMenus();
			if(menus != null & menus.size() > 0){
				for(Menu m : menus){
					createTreeDate(m,list,roleMenus);
				}
			}
		}
	}

	/**
	 * Ȩ�޷���
	 * @throws IOException 
	 */
	public void ajax_role_grantrole() throws IOException{
		roleService.updateGranRole(query.getRoleId(), permIds);
		response.getWriter().write("success");
	}
	
	/**
	 * ��ɫ���
	 * @throws IOException
	 */
	public void ajax_role_add() throws IOException{
	
		roleService.save(role);
		response.getWriter().write("success");
	}
	/**
	 * �޸Ľ�ɫ
	 * @return
	 */
	public String role_update(){
		role = roleService.getObj(role.getRoleId());
		return SUCCESS;
	}
	
	/**
	 * ɾ����ɫ
	 * @return
	 */
	public String role_delete(){
		roleService.delete(role);
		return LIST;
	}
	

	/**
	 * �����޸�
	 */
	public void ajax_role_update() throws IOException{
		roleService.update(role);
		response.getWriter().write("success");
	}

	
	
	
}
