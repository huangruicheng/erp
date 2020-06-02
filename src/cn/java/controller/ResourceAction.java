package cn.java.controller;

import java.io.IOException;
import java.util.List;

import cn.java.model.Resource;
import cn.java.query.ResourceQuery;
import cn.java.service.ResourceService;
import cn.java.utils.Page;

import com.opensymphony.xwork2.ActionContext;

public class ResourceAction extends BaseAction {
	
	
	private ResourceQuery query = new ResourceQuery();
	
	private Resource resource = new Resource();
	
	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	public ResourceQuery getQuery() {
		return query;
	}

	public void setQuery(ResourceQuery query) {
		this.query = query;
	}

	private ResourceService resourceService;
	
	
	public ResourceService getResourceService() {
		return resourceService;
	}

	public void setResourceService(ResourceService resourceService) {
		this.resourceService = resourceService;
	}


	public String resource_list(){
		
		ActionContext context = ActionContext.getContext();
		
		Integer pageNo = query.getPageNo();
		if(pageNo == null ){
			query.setPageNo(1);
		}
		
		Page page = resourceService.queryObjByCondition(query, exclude);
		
		context.put("page", page);
		
		
		return SUCCESS;
	}
	/**
	 * 添加跳转
	 * @return
	 */
	public String resource_input(){
		return SUCCESS;
	}
	/**
	 * 资源添加
	 * @throws IOException
	 */
	public void ajax_resource_add() throws IOException{
	
		resourceService.save(resource);
		response.getWriter().write("success");
	}
	
	/**
	 * 部门修改
	 * @return
	 */
	public String resource_update(){
		resource = resourceService.getObj(resource.getResourceId());
		return SUCCESS;
	}

	/**
	 * 部门修改
	 */
	public void ajax_resource_update() throws IOException{
		resourceService.update(resource);
		//System.out.println("0000000000000000000000");
		response.getWriter().write("success");
	}
	
	/**
	 * 部门删除
	 * @return
	 */
	public String resource_delete(){
		resourceService.delete(resource);
		return LIST;
	}
	


	
	
	
}
