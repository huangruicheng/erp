package cn.java.dao.impl;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.java.dao.DepDao;
import cn.java.model.Dep;
import cn.java.query.DepQuery;

public class DepDaoImpl extends BaseDaoImpl<Dep, DepQuery> implements DepDao {

	@Override
	public String createHql(DepQuery q) {
		String hql = "from Dep t where 1 = 1";
		hql = hql + createHqlCondition(q);
		return hql;
	}

	@Override
	public String createHqlCount(DepQuery q) {
		String hql = "select count(depId) from Dep t where 1 = 1";
		hql = hql + createHqlCondition(q);
		return hql;
	}
	
	
	@Override
	public String createHqlCondition(DepQuery q) {
		String hql = "";
		if(StringUtils.isNotBlank(q.getName())){
			hql = hql + " and t.name like :name";
		}
		if(StringUtils.isNotBlank(q.getTel())){
			hql = hql + " and t.tel like :tel";
		}
		return hql;
	}

	

}
