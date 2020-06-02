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
		//��ѯ��role
		Role orle = roleDao.getObj(orderId);
		//��ȡ����������в˵�
		Set<Menu> menus = orle.getMenus();
		//����Ѿ��еĲ˵�
		menus.clear();
		if(StringUtils.isNotBlank(permIds)){
			String[] menuIds = permIds.split(",");
			for(String menuId : menuIds){
				//����µĲ˵�
				Menu menu = menuDao.getObj(new Integer(menuId));
				//���µĲ˵���ӵ��˵�������
				menus.add(menu);
			}
		}
		
	}
	
	

}
