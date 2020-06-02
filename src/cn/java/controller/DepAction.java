package cn.java.controller;

import java.io.IOException;
import java.util.List;

import cn.java.model.Dep;
import cn.java.query.DepQuery;
import cn.java.query.EmpQuery;
import cn.java.service.DepService;
import cn.java.service.EmpService;
import cn.java.utils.MD5Utils;
import cn.java.utils.Page;

import com.opensymphony.xwork2.ActionContext;

public class DepAction extends BaseAction {
	

	private DepQuery query = new DepQuery();
	
	private Dep dep = new Dep();

	public Dep getDep() {
		return dep;
	}

	public void setDep(Dep dep) {
		this.dep = dep;
	}

	public DepQuery getQuery() {
		return query;
	}

	public void setQuery(DepQuery query) {
		this.query = query;
	}

	private DepService depService;
	
	public DepService getDepService() {
		return depService;
	}

	public void setDepService(DepService depService) {
		this.depService = depService;
	}

	
	public String dep_list(){
		ActionContext context = ActionContext.getContext();
		List<Dep> list = depService.list();
		context.put("dList", list);
		Integer pageNo = query.getPageNo();
		if(pageNo == null){
			query.setPageNo(1);
		}
		Page page = depService.queryObjByCondition(query, exclude);
		context.put("page", page);
		return SUCCESS;
	}
	/**
	 * �������
	 * @return
	 */
	public String dep_input(){
		
		return SUCCESS;
	}
	
	/**
	 * �������
	 * @throws IOException
	 */
	public void ajax_dep_add() throws IOException{
	
		depService.save(dep);
		response.getWriter().write("success");
	}
	/**
	 * �û��޸�
	 * @return
	 */
	public String dep_update(){
		dep = depService.getObj(dep.getDepId());
		return SUCCESS;
	}
	
	/**
	 * ����ɾ��
	 * @return
	 */
	public String dep_delete(){
		depService.delete(dep);
		return LIST;
	}
	

	/**
	 * �����޸�
	 */
	public void ajax_dep_update() throws IOException{
		depService.updateDep(dep);
		//System.out.println("0000000000000000000000");
		response.getWriter().write("success");
	}

	
	
}
