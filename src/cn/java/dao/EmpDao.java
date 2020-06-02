package cn.java.dao;

import java.util.List;

import cn.java.model.Emp;
import cn.java.query.EmpQuery;

public interface EmpDao extends BaseDao<Emp, EmpQuery>{
	
	//根据用户名查询用户
	public Emp getEmpByUname(String username);
	//根据用户名和密码查找用户
	public Emp getEmpByUnameAndPWord(String username, String password);

}
