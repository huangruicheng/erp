package cn.java.dao;

import java.util.List;

public interface BaseDao<T, Q> {
	
public void save(T t);
	
	public void update(T t);
	
	public T getObj(Integer id);
	
	public void delete(Integer id);
	
	public void delete(T t);
	
	/**
	 * ��ѯ��������ÿһҳ�ļ�¼
	 * @param q
	 * @return
	 */
	public List<T> queryObjByCondition(final Q q, final List<String> exclude );
	
	/**
	 * ��ѯָ�������µ��ܼ�¼��
	 * @param q
	 * @return
	 */
	public Long queryObjByConditionCount(final Q q, final List<String> exclude);
	/**
	 * ��ѯ����
	 * @return
	 */
	public List<T> list();

	/**
	 * ��ѯ�����������еļ�¼
	 * @param q
	 * @return
	 */
	public List<T> queryObjByConditionNoPage(final Q q, final List<String> exclude );
}
