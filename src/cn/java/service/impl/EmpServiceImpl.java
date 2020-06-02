package cn.java.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import cn.java.dao.EmpDao;
import cn.java.dao.RoleDao;
import cn.java.model.Emp;
import cn.java.model.Role;
import cn.java.query.EmpQuery;
import cn.java.service.EmpService;


public class EmpServiceImpl extends BaseServiceImpl<Emp, EmpQuery> implements EmpService {

	
	private EmpDao empDao;
	
	private RoleDao roleDao;
	
	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}
	
	
	public void setEmpDao(EmpDao empDao) {
		this.empDao = empDao;
		this.baseDao = empDao;
	}


	@Override
	public Emp getEmpByUname(String username) {
		return empDao.getEmpByUname(username);
	}


	@Override
	public void updateEmp(Emp emp) {
		Emp emp1 = empDao.getObj(emp.getEmpId());
		emp.setPassword(emp1.getPassword());
		try {
			//将emp对象的所有属性赋值给emp1对象
			BeanUtils.copyProperties(emp1, emp);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		empDao.update(emp1);
	}


	@Override
	public List<Role> getStateRoles(Integer empId) {
		//查询到所以的角色
		List<Role> list = roleDao.list();
		//根据empId获取到emp
		Emp emp = empDao.getObj(empId);
		//获取到用户的所有角色
		Set<Role> roles = emp.getRoles();
		for(Role r : list){
			for(Role er : roles){
				//如果用户有相应的角色，将会匹配
				if(r.getRoleId().intValue() == er.getRoleId().intValue()){
					r.setSelect("yes");
				}
			}
		}
		return list;
	}


	@Override
	public void updateEmpRole(Integer empId, String roleIds) {
		//根据empId获取到emp
		Emp emp = empDao.getObj(empId);
		Set<Role> roles = emp.getRoles();
		//将用户分配到的角色清除
		roles.clear();
		if(StringUtils.isNotBlank(roleIds)){
			String[] ids = roleIds.split(",");
			for(String roleId : ids){
				//根据角色id获取到新的角色
				Role role = roleDao.getObj(new Integer(roleId));
				roles.add(role);
			}
		}
		
	}


	@Override
	public Emp getEmpByUnameAndPWord(String username, String password) {
		return empDao.getEmpByUnameAndPWord(username, password);
	}

	
}
