package cn.java.service.impl;

import cn.java.dao.SupplierDao;
import cn.java.model.Supplier;
import cn.java.query.SupplierQuery;
import cn.java.service.SupplierService;

public class SupplierServiceImpl extends BaseServiceImpl<Supplier, SupplierQuery> implements SupplierService {

	
	private SupplierDao supplierDao;
	
	public void setSupplierDao(SupplierDao supplierDao) {
		this.supplierDao = supplierDao;
		this.baseDao = supplierDao;
	}

	@Override
	public Supplier getSupplierByName(Supplier supplier) {
		return supplierDao.getSupplierByName(supplier);
	}
	
	

}
