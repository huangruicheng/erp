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
	 * 角色添加
	 * @return
	 */
	public String role_input(){
		
		return SUCCESS;
	}
	/**
	 * 权限分配
	 * @return
	 */
	public String role_listPerm(){
		//查询到role
		Role role = roleService.getObj(query.getRoleId());
		//获得到用户下的菜单
		Set<Menu> menus = role.getMenus();
		//获取到系统菜单
		Menu menu = menuService.getObj(1);
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		createTreeDate(menu,list,menus);
		//将集合list转换为JSON对象
		JSONArray ja = new JSONArray().fromObject(list);
		ActionContext context = ActionContext.getContext();
		context.put("zNodes", ja);
		return SUCCESS;
	}
	/**
	 * 组装数据，树形数据，采用递归
	 * @param menu 所有菜单
	 * @param list 
	 * @param roleMenus 角色已有的菜单
	 */
	public void createTreeDate(Menu menu, List<Map<String,Object>> list,Set<Menu> roleMenus){
		Map<String,Object> map = new HashMap<String,Object>();
		if(menu != null){
			Integer id = menu.getMenuId();
			Integer pId = menu.getParentMenuId();
			String name = menu.getName();
			//如果id为1，是系统菜单，不需要展示出来
			if(id.intValue() != 1){
				map.put("id", id);
				map.put("pId", pId);
				map.put("name", name);
				//如果已经有菜单，需要把它展示出来
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
	 * 权限分配
	 * @throws IOException 
	 */
	public void ajax_role_grantrole() throws IOException{
		roleService.updateGranRole(query.getRoleId(), permIds);
		response.getWriter().write("success");
	}
	
	/**
	 * 角色添加
	 * @throws IOException
	 */
	public void ajax_role_add() throws IOException{
	
		roleService.save(role);
		response.getWriter().write("success");
	}
	/**
	 * 修改角色
	 * @return
	 */
	public String role_update(){
		role = roleService.getObj(role.getRoleId());
		return SUCCESS;
	}
	
	/**
	 * 删除角色
	 * @return
	 */
	public String role_delete(){
		roleService.delete(role);
		return LIST;
	}
	

	/**
	 * 部门修改
	 */
	public void ajax_role_update() throws IOException{
		roleService.update(role);
		response.getWriter().write("success");
	}

	
	
	
}
