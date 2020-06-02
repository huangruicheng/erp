package cn.java.dao.impl;

import org.apache.commons.lang.StringUtils;

import cn.java.dao.StoreDetailDao;
import cn.java.model.StoreDetail;
import cn.java.query.StoreDetailQuery;

public class StoreDetailDaoImpl extends BaseDaoImpl<StoreDetail, StoreDetailQuery> implements StoreDetailDao {

	@Override
	public String createHql(StoreDetailQuery equery) {
		String hql = "from StoreDetail t where 1=1 ";
		
		return hql;
	}

	@Override
	public String createHqlCount(StoreDetailQuery q) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String createHqlCondition(StoreDetailQuery q) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
