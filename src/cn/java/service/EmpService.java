package cn.java.service;

import java.util.List;

import cn.java.model.Emp;
import cn.java.model.Role;
import cn.java.query.EmpQuery;

public interface EmpService extends BaseService<Emp, EmpQuery>{
	//ͨ���û�������û�
	public Emp getEmpByUname(String username);
	//�����û�
	public void updateEmp(Emp emp);
	//�����û�id��ȡ��role
	public List<Role> getStateRoles(Integer empId);
	//�����û�id�ͽ�ɫid���½�ɫ
	public void updateEmpRole(Integer empId, String roleIds);
	//����username��password�����û�
	public Emp getEmpByUnameAndPWord(String username, String password);
}
