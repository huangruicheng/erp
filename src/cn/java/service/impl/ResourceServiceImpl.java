package cn.java.service.impl;

import cn.java.dao.ResourceDao;
import cn.java.model.Resource;
import cn.java.query.ResourceQuery;
import cn.java.service.ResourceService;

public class ResourceServiceImpl extends BaseServiceImpl<Resource, ResourceQuery> implements ResourceService {

	
	private ResourceDao resourceDao;
	
	public void setResourceDao(ResourceDao resourceDao) {
		this.resourceDao = resourceDao;
		this.baseDao = resourceDao;
	}
	
	

}
