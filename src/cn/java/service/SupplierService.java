package cn.java.service;

import cn.java.model.Supplier;
import cn.java.query.SupplierQuery;

public interface SupplierService extends BaseService<Supplier, SupplierQuery> {
	
	//查询供应商是否存在
	public Supplier getSupplierByName(Supplier supplier);

}
