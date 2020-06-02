package cn.java.dao.impl;

import org.apache.commons.lang.StringUtils;

import cn.java.dao.MenuDao;
import cn.java.model.Menu;
import cn.java.query.MenuQuery;

public class MenuDaoImpl extends BaseDaoImpl<Menu, MenuQuery> implements MenuDao {

	@Override
	public String createHql(MenuQuery equery) {
		String hql = "from Menu t where 1=1 ";
		hql = hql + createHqlCondition(equery);
		return hql;
	}

	@Override
	public String createHqlCount(MenuQuery q) {
		String hql = "select count(menuId) from Menu t where 1=1 ";
		hql = hql + createHqlCondition(q);
		return hql;
	}

	@Override
	public String createHqlCondition(MenuQuery q) {
		String hql = "";
		if(StringUtils.isNotBlank(q.getName())){
			hql = hql + " and t.name like :name";
		}
		if(StringUtils.isNotBlank(q.getUrl())){
			hql = hql + " and t.url like :url";
		}
		if(q.getMenuId() != null){
			hql = hql + " and t.menuId = :menuId";
		}
		if(q.getParentMenuId()!= null){
			hql = hql + " and t.parentMenuId = :parentMenuId";
		}
		return hql;
	}
	

}
