package cn.java.dao;

import cn.java.model.ProductType;
import cn.java.model.Supplier;
import cn.java.query.SupplierQuery;

public interface SupplierDao extends BaseDao<Supplier, SupplierQuery> {
	//��ѯ��Ӧ���Ƿ����
	public Supplier getSupplierByName(Supplier supplier);
	

}
