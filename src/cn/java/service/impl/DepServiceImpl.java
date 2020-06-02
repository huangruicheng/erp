package cn.java.service.impl;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

import cn.java.dao.DepDao;
import cn.java.model.Dep;
import cn.java.model.Emp;
import cn.java.query.DepQuery;
import cn.java.service.DepService;

public class DepServiceImpl extends BaseServiceImpl<Dep,DepQuery> implements DepService {
	
	private DepDao depDao;
	
	public void setDepDao(DepDao depDao) {
		this.depDao = depDao;
		this.baseDao = depDao;
	}

	@Override
	public void updateDep(Dep dep) {
		Dep dep1 = depDao.getObj(dep.getDepId());
		try {
			//将dep对象的所有属性赋值给dep1对象
			BeanUtils.copyProperties(dep1, dep);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		depDao.update(dep1);
	}

	
}
