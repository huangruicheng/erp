package cn.java.service;

import cn.java.model.Supplier;
import cn.java.query.SupplierQuery;

public interface SupplierService extends BaseService<Supplier, SupplierQuery> {
	
	//��ѯ��Ӧ���Ƿ����
	public Supplier getSupplierByName(Supplier supplier);

}
