package cn.java.dao.impl;

import org.apache.commons.lang.StringUtils;

import cn.java.dao.ResourceDao;
import cn.java.model.Resource;
import cn.java.query.ResourceQuery;

public class ResourceDaoImpl extends BaseDaoImpl<Resource, ResourceQuery> implements ResourceDao {

	@Override
	public String createHql(ResourceQuery equery) {
		String hql = "from Resource t where 1=1 ";
		hql = hql + createHqlCondition(equery) + " order by t.resourceId desc";;
		return hql;
	}

	@Override
	public String createHqlCount(ResourceQuery q) {
		String hql = "select count(resourceId) from Resource t where 1 = 1";
		hql = hql + createHqlCondition(q);
		return hql;
	}

	@Override
	public String createHqlCondition(ResourceQuery q) {
		String hql = "";
		if(StringUtils.isNotBlank(q.getName())){
			hql = hql + " and t.name like :name";
		}
		if(StringUtils.isNotBlank(q.getUrl())){
			hql = hql + " and t.url like :url";
		}
		if(q.getResourceId() != null){
			hql = hql + " and t.resourceId = :resourceId";
		}
		
		return hql;
	}
	

}
