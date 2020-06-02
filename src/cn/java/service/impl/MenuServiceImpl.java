package cn.java.service.impl;

import cn.java.dao.MenuDao;
import cn.java.model.Menu;
import cn.java.query.MenuQuery;
import cn.java.service.MenuService;

public class MenuServiceImpl extends BaseServiceImpl<Menu, MenuQuery> implements MenuService {

	
	private MenuDao menuDao;
	
	public void setMenuDao(MenuDao menuDao) {
		this.menuDao = menuDao;
		this.baseDao = menuDao;
	}
	
	

}
