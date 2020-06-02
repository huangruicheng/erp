package cn.java.service;

import java.util.List;

import cn.java.utils.Page;

public interface BaseService<T, Q> {
	
	public void save(T t);
	
	public void update(T t);
	
	public T getObj(Integer id);
	
	public void delete(Integer id);
	
	public void delete(T t);
	
	public Page queryObjByCondition(Q q, List<String> exclude);
	
	//²éÑ¯ËùÓÐ
	public List<T> list();
	public List<T> queryObjByConditionNoPage(final Q q, final List<String> exclude );
}
