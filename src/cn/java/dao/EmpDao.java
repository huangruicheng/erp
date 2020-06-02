package cn.java.dao;

import java.util.List;

import cn.java.model.Emp;
import cn.java.query.EmpQuery;

public interface EmpDao extends BaseDao<Emp, EmpQuery>{
	
	//�����û�����ѯ�û�
	public Emp getEmpByUname(String username);
	//�����û�������������û�
	public Emp getEmpByUnameAndPWord(String username, String password);

}
