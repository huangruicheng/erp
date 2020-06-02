package cn.java.dao.impl;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
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

import cn.java.dao.BaseDao;
import cn.java.model.Dep;
import cn.java.query.DepQuery;

public abstract class BaseDaoImpl<T, Q> extends HibernateDaoSupport implements BaseDao<T, Q> {

	@Override
	public void save(T t) {
		this.getHibernateTemplate().save(t);
	}

	@Override
	public void update(T t) {
		this.getHibernateTemplate().update(t);
	}

	@Override
	public T getObj(Integer id) {
		Class<?> class1 = getGenericClass();
		return  (T) this.getHibernateTemplate().get(class1, id);
	}

	@Override
	public void delete(Integer id) {
		T obj = getObj(id);
		this.getHibernateTemplate().delete(obj);
	}

	@Override
	public List<T> queryObjByCondition(final Q q, final List<String> exclude ) {
		@SuppressWarnings("unchecked")
		List<T> tList = this.getHibernateTemplate().executeFind(new HibernateCallback<List<T>>() {

			/**
			 * Session��spring�����Ĵ���session�������Զ��Ŀ������ύ����͹ر�session
			 */
			@Override
			public List<T> doInHibernate(Session session)
					throws HibernateException, SQLException {
				String hql = createHql(q);
				//������ѯ����
				Query query = session.createQuery(hql);
				setDynamicParam(query, q, exclude);
				//��ò�ѯ����������
				Class<? extends Object> class1 = q.getClass();
				Object object = null;
				try {
					//����������������startNum
					Field startNumField = class1.getDeclaredField("startNum");
					//�����ƻ�˽������
					startNumField.setAccessible(true);
					//���˽�����Ե�ֵ
					object = startNumField.get(q);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				List<T> list = (List<T>)query.setFirstResult((Integer)object).setMaxResults(5).list();
				
				return list;
			}
			
		});
		
		return tList;
	}
	
	public Long queryObjByConditionCount(final Q q, final List<String> exclude){
		
		Long totalCount = this.getHibernateTemplate().execute(new HibernateCallback<Long>() {

			@Override
			public Long doInHibernate(Session session) throws HibernateException,
					SQLException {
				
				String hql = createHqlCount(q);
				//������ѯ����
				Query query = session.createQuery(hql);
				setDynamicParam(query, q, exclude);
				Long count = (Long) query.uniqueResult();
				return count;
			}
			
			
		});
		
		return totalCount;
		
		
	}
	
	public Class<?> getGenericClass(){
		//��÷��͵ĸ���
		Type genericSuperclass = this.getClass().getGenericSuperclass();
		//�ѷ��͵ĸ�����ǿ��ת��ParameterizedType
		ParameterizedType pt = (ParameterizedType) genericSuperclass;
		//����ParameterizedType��õ�ǰ������з��͵�����
		Type[] actualTypeArguments = pt.getActualTypeArguments();
		//���T�ľ�����
		Class<?> class1 = (Class<?>) actualTypeArguments[0];
		return class1;
	}

	@Override
	public void delete(T t) {
		this.getHibernateTemplate().delete(t);
	}
	
	/**
	 * ����hql
	 * @param q
	 * @return
	 */
	public abstract String createHql(Q q);
	
	
	public abstract String createHqlCount(Q q);
	
	
	public abstract String createHqlCondition(Q q);
	
	
	/**
	 * ��̬���ò���
	 * @param query
	 * @param q
	 * exclude:�ų�ĳЩ����
	 */
	public void setDynamicParam(Query query, Q q, List<String> exclude){
		Class<? extends Object> class1 = q.getClass();
		
		//���������ѯ�����г���ѯ�������������
		Field[] fields = class1.getDeclaredFields();
		Field[] superFields = class1.getSuperclass().getDeclaredFields();
		List<Field> list1 = Arrays.asList(fields);
		List<Field> list2 = Arrays.asList(superFields);
		//����һ����ļ��ϣ�����洢��ѯ��������Զ��������������Զ���
		List<Field> fList = new ArrayList<Field>();
		fList.addAll(list1);
		fList.addAll(list2);
		
		//��������
		for(Field f : fList){
			//������Ե�����
			String fieldName = f.getName();
			if(exclude != null && exclude.contains(fieldName)){
				continue;
			}
			Object val = null;
			try {
				f.setAccessible(true);
				//������Ե�ֵ
				val = f.get(q);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
			if(val != null){
				if(val.getClass() == String.class){
					if(StringUtils.isNotBlank(val.toString())){
						query.setParameter(fieldName, "%"+val+"%");
					}
					
				}else{
					query.setParameter(fieldName, val);
				}
			}
			
		}
		
	}

	
	public List<T> list(){
		Class<?> class1 = getGenericClass();
		String hql = "from "+class1.getName();
		return this.getHibernateTemplate().find(hql);
		
	}
	
	@Override
	public List<T> queryObjByConditionNoPage(final Q q, final List<String> exclude ) {
		@SuppressWarnings("unchecked")
		List<T> tList = this.getHibernateTemplate().executeFind(new HibernateCallback<List<T>>() {

			/**
			 * Session��spring�����Ĵ���session�������Զ��Ŀ������ύ����͹ر�session
			 */
			@Override
			public List<T> doInHibernate(Session session)
					throws HibernateException, SQLException {
				String hql = createHql(q);
				//������ѯ����
				Query query = session.createQuery(hql);
				setDynamicParam(query, q, exclude);
				
				List<T> list = (List<T>)query.list();
				
				return list;
			}
			
		});
		
		return tList;
	}
	
}
