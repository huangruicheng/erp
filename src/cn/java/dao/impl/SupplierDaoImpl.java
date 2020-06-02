package cn.java.dao.impl;

import java.sql.SQLException;

import org.apache.commons.lang.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import cn.java.dao.SupplierDao;
import cn.java.model.ProductType;
import cn.java.model.Supplier;
import cn.java.query.SupplierQuery;

public class SupplierDaoImpl extends BaseDaoImpl<Supplier, SupplierQuery> implements SupplierDao {

	@Override
	public String createHql(SupplierQuery equery) {
		String hql = "from Supplier t where 1=1 ";
		hql = hql + createHqlCondition(equery) +" order by t.supplierId desc";
		return hql;
	}

	@Override
	public String createHqlCount(SupplierQuery q) {
		String hql = "select count(t.supplierId) from Supplier t where 1=1 ";
		hql = hql + createHqlCondition(q);
		return hql;
	}

	@Override
	public String createHqlCondition(SupplierQuery q) {
		String hql = "";
		if(StringUtils.isNotBlank(q.getName())){
			hql = hql + " and t.name like :name";
		}
		if(StringUtils.isNotBlank(q.getContact())){
			hql = hql + " and t.contact like :contact";
		}
		if(q.getNeeds() != null){
			hql = hql + " and t.needs = :needs";
		}
		if(StringUtils.isNotBlank(q.getTel())){
			hql = hql + " and t.tel like :tel";
		}
		return hql;
	}

	@Override
	public Supplier getSupplierByName(final Supplier supplier) {
		final String hql = "from Supplier t where t.name = :name";
		Supplier supplier1 = this.getHibernateTemplate().execute(new HibernateCallback<Supplier>() {

			@Override
			public Supplier doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery(hql);
				query.setParameter("name", supplier.getName());
				return (Supplier) query.uniqueResult();
			}
			
			
		});
		
		return supplier1;
	}

}
