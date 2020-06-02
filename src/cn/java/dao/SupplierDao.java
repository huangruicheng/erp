package cn.java.dao;

import cn.java.model.ProductType;
import cn.java.model.Supplier;
import cn.java.query.SupplierQuery;

public interface SupplierDao extends BaseDao<Supplier, SupplierQuery> {
	//查询供应商是否存在
	public Supplier getSupplierByName(Supplier supplier);
	

}
