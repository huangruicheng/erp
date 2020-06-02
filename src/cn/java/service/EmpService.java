package cn.java.service;

import java.util.List;

import cn.java.model.Emp;
import cn.java.model.Role;
import cn.java.query.EmpQuery;

public interface EmpService extends BaseService<Emp, EmpQuery>{
	//通过用户名获得用户
	public Emp getEmpByUname(String username);
	//更新用户
	public void updateEmp(Emp emp);
	//根据用户id获取到role
	public List<Role> getStateRoles(Integer empId);
	//根据用户id和角色id更新角色
	public void updateEmpRole(Integer empId, String roleIds);
	//根据username和password查找用户
	public Emp getEmpByUnameAndPWord(String username, String password);
}
