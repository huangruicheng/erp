package cn.java.dao.impl;

import org.apache.commons.lang.StringUtils;

import cn.java.dao.ConsoleLogDao;
import cn.java.model.ConsoleLog;
import cn.java.query.ConsoleLogQuery;

public class ConsoleLogDaoImpl extends BaseDaoImpl<ConsoleLog, ConsoleLogQuery> implements ConsoleLogDao {

	@Override
	public String createHql(ConsoleLogQuery equery) {
		String hql = "from ConsoleLog t where 1=1 ";
		hql = hql + createHqlCondition(equery) ;
		return hql;
	}

	@Override
	public String createHqlCount(ConsoleLogQuery q) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String createHqlCondition(ConsoleLogQuery q) {
		String hql = " and entityId = :entityId and t.tableName like :tableName and t.optType like :optType";
		return hql;
	}
	

}
