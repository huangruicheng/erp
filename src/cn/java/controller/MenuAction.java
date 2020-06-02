package cn.java.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cn.java.model.Menu;
import cn.java.query.MenuQuery;
import cn.java.service.MenuService;
import cn.java.utils.Page;

import com.opensymphony.xwork2.ActionContext;

public class MenuAction extends BaseAction {
	
	
	private MenuQuery query = new MenuQuery();
	
	private Menu menu = new Menu();
	
	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public MenuQuery getQuery() {
		return query;
	}

	public void setQuery(MenuQuery query) {
		this.query = query;
	}

	private MenuService menuService;

	
	public MenuService getMenuService() {
		return menuService;
	}

	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}


	public String menu_list(){
		
		ActionContext context = ActionContext.getContext();
		List<Menu> mList = menuService.list();
		List<Menu> lists = new ArrayList<Menu>();
		for(Menu m : mList){
			if(m.getMenuId() == 1){
				lists.add(m);
			}else if(m.getMenuId() == 2){
				lists.add(m);
			}else if(m.getMenuId() == 3){
				lists.add(m);
			}else if(m.getMenuId() == 4){
				lists.add(m);
			}else if(m.getMenuId() == 5){
				lists.add(m);
			}else if(m.getMenuId() == 6){
				lists.add(m);
			}else if(m.getMenuId() == 7){
				lists.add(m);
			}else if(m.getMenuId() == 8){
				lists.add(m);
			}
		}
		context.put("mList", lists);
		
		Integer pageNo = query.getPageNo();
		if(pageNo == null ){
			query.setPageNo(1);
		}
		
		Page page = menuService.queryObjByCondition(query, exclude);
		
		context.put("page", page);
		
		
		return SUCCESS;
	}
	
	public String menu_input(){
		ActionContext context = ActionContext.getContext();
		List<Menu> mList = menuService.list();
		List<Menu> lists = new ArrayList<Menu>();
		for(Menu m : mList){
			if(m.getMenuId() == 1){
				lists.add(m);
			}else if(m.getMenuId() == 2){
				lists.add(m);
			}else if(m.getMenuId() == 3){
				lists.add(m);
			}else if(m.getMenuId() == 4){
				lists.add(m);
			}else if(m.getMenuId() == 5){
				lists.add(m);
			}else if(m.getMenuId() == 6){
				lists.add(m);
			}else if(m.getMenuId() == 7){
				lists.add(m);
			}else if(m.getMenuId() == 8){
				lists.add(m);
			}
		}
		context.put("mList", lists);
		
		return SUCCESS;
	}
	
	
	/**
	 * 资源添加
	 * @throws IOException
	 */
	public void ajax_menu_add() throws IOException{
		menu.setParentMenuId(menu.getMenuId());
		menuService.save(menu);
		response.getWriter().write("success");
	}
	
	/**
	 * 资源修改
	 * @return
	 */
	public String menu_update(){
		ActionContext context = ActionContext.getContext();
		List<Menu> mList = menuService.list();
		List<Menu> lists = new ArrayList<Menu>();
		for(Menu m : mList){
			if(m.getMenuId() == 1){
				lists.add(m);
			}else if(m.getMenuId() == 2){
				lists.add(m);
			}else if(m.getMenuId() == 3){
				lists.add(m);
			}else if(m.getMenuId() == 4){
				lists.add(m);
			}else if(m.getMenuId() == 5){
				lists.add(m);
			}else if(m.getMenuId() == 6){
				lists.add(m);
			}else if(m.getMenuId() == 7){
				lists.add(m);
			}else if(m.getMenuId() == 8){
				lists.add(m);
			}
		}
		context.put("mList", lists);
		menu.setParentMenuId(menu.getMenuId());
		menu = menuService.getObj(menu.getMenuId());
		return SUCCESS;
	}

	/**
	 * 资源修改
	 */
	public void ajax_menu_update() throws IOException{
		menuService.update(menu);
		//System.out.println("0000000000000000000000");
		response.getWriter().write("success");
	}
	
	/**
	 * 删除资源
	 * @return
	 */
	public String menu_delete(){
		menuService.delete(menu);
		return LIST;
	}
	

	
}
