package cn.java.service.impl;

import java.util.Set;

import org.apache.commons.lang.StringUtils;

import cn.java.dao.MenuDao;
import cn.java.dao.RoleDao;
import cn.java.model.Menu;
import cn.java.model.Role;
import cn.java.query.RoleQuery;
import cn.java.service.RoleService;
import freemarker.template.utility.StringUtil;

public class RoleServiceImpl extends BaseServiceImpl<Role, RoleQuery> implements RoleService {

	
	private RoleDao roleDao;
	
	private MenuDao menuDao;
	
	public void setMenuDao(MenuDao menuDao) {
		this.menuDao = menuDao;
	}
	
	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
		this.baseDao = roleDao;
	}

	@Override
	public void updateGranRole(Integer orderId, String permIds) {
		//查询到role
		Role orle = roleDao.getObj(orderId);
		//获取到对象的所有菜单
		Set<Menu> menus = orle.getMenus();
		//清除已经有的菜单
		menus.clear();
		if(StringUtils.isNotBlank(permIds)){
			String[] menuIds = permIds.split(",");
			for(String menuId : menuIds){
				//获得新的菜单
				Menu menu = menuDao.getObj(new Integer(menuId));
				//将新的菜单添加到菜单集合中
				menus.add(menu);
			}
		}
		
	}
	
	

}
