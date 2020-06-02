package cn.java.service;

import java.util.List;

import cn.java.model.Dep;
import cn.java.model.Emp;
import cn.java.query.DepQuery;

public interface DepService extends BaseService<Dep, DepQuery> {
	
	public void updateDep(Dep dep);
}
