package cn.java.service;

import cn.java.model.Role;
import cn.java.query.RoleQuery;

public interface RoleService extends BaseService<Role, RoleQuery> {
	
	public void updateGranRole(Integer orderId,String permIds);

}
