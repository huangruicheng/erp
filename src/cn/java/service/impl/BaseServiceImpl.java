package cn.java.service.impl;

import java.lang.reflect.Field;
import java.util.List;

import cn.java.dao.BaseDao;
import cn.java.service.BaseService;
import cn.java.utils.Page;

public class BaseServiceImpl<T, Q> implements BaseService<T, Q>{
	
BaseDao<T, Q> baseDao;
	
	@Override
	public void save(T t) {
		baseDao.save(t);
	}

	@Override
	public void update(T t) {
		baseDao.update(t);
	}

	@Override
	public T getObj(Integer id) {
		return (T) baseDao.getObj(id);
	}

	@Override
	public void delete(Integer id) {
		baseDao.delete(id);
	}

	@Override
	public void delete(T t) {
		baseDao.delete(t);
	}

	

	@Override
	public List<T> list() {
		return baseDao.list();
	}

	@Override
	public Page queryObjByCondition(Q q, List<String> exclude) {
		//����page����
		Page page = new Page();
		//��ò�ѯ�����е�pageNo
		Class<? extends Object> class1 = q.getClass();
		try {
			Field field = class1.getDeclaredField("pageNo");
			//���pageNo
			field.setAccessible(true);
			Integer pageNo = (Integer) field.get(q);
			//��pageNo���ø�page����
			page.setPageNo(pageNo);
			//���������ʼ�к�
			int startNum = page.getStartNum();
			//��q��ѯ��������startNum
			Field startNumField = class1.getDeclaredField("startNum");
			//���pageNo
			startNumField.setAccessible(true);
			startNumField.set(q, startNum);
			//��ѯ�����
			List<T> list = baseDao.queryObjByCondition(q, exclude);
			//�ѽ�������ø�page����
			page.setList(list);
			//��ѯ��ǰ�����µ��ܼ�¼��
			Long count = baseDao.queryObjByConditionCount(q, exclude);
			page.setTotalCount(new Integer(count+""));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return page;
	}
	
	public List<T> queryObjByConditionNoPage(final Q q, final List<String> exclude ){
		return baseDao.queryObjByConditionNoPage(q, exclude);
	}

}
