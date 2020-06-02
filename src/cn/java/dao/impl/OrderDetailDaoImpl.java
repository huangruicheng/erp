package cn.java.dao.impl;

import java.sql.SQLException;

import org.apache.commons.lang.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import cn.java.dao.OrderDetailDao;
import cn.java.model.OrderDetail;
import cn.java.model.OrderModel;
import cn.java.query.OrderDetailQuery;

public class OrderDetailDaoImpl extends BaseDaoImpl<OrderDetail, OrderDetailQuery> implements OrderDetailDao {

	@Override
	public String createHql(OrderDetailQuery equery) {
		String hql = "from OrderDetail t where 1=1 ";
		
		return hql;
	}

	@Override
	public String createHqlCount(OrderDetailQuery q) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String createHqlCondition(OrderDetailQuery q) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteDetailByOrderId(final Integer orderId) {
		final String hql = "delete from OrderDetail t where t.orderId = :orderId";
		this.getHibernateTemplate().execute(new HibernateCallback<Object>() {

			@Override
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				Query query = session.createQuery(hql);
				query.setParameter("orderId", orderId+"");
				query.executeUpdate();
				return null;
			}
		});
		
	}
	

}
