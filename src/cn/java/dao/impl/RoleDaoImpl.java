package cn.java.dao.impl;

import org.apache.commons.lang.StringUtils;

import cn.java.dao.RoleDao;
import cn.java.model.Role;
import cn.java.query.RoleQuery;

public class RoleDaoImpl extends BaseDaoImpl<Role, RoleQuery> implements RoleDao {

	@Override
	public String createHql(RoleQuery equery) {
		String hql = "from Role t where 1=1 ";
		hql = hql + createHqlCondition(equery);
		return hql;
	}

	@Override
	public String createHqlCount(RoleQuery q) {
		String hql = "select count(roleId) from Role t where 1=1";
		hql = hql + createHqlCondition(q);
		return hql;
	}

	@Override
	public String createHqlCondition(RoleQuery q) {
		String hql = "";
		if(StringUtils.isNotBlank(q.getName())){
			hql = hql + " and t.name like :name";
		}
		if(StringUtils.isNotBlank(q.getCode())){
			hql = hql + " and t.code like :code";
		}
		if(q.getRoleId() != null){
			hql = hql + " and t.roleId = :roleId";
		}
		return hql;
	}
	

}
