package cn.java.service.impl;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

import cn.java.dao.ProductTypeDao;
import cn.java.model.Emp;
import cn.java.model.ProductType;
import cn.java.query.ProductTypeQuery;
import cn.java.service.ProductTypeService;

public class ProductTypeServiceImpl extends BaseServiceImpl<ProductType, ProductTypeQuery> implements ProductTypeService {

	
	private ProductTypeDao productTypeDao;
	
	public void setProductTypeDao(ProductTypeDao productTypeDao) {
		this.productTypeDao = productTypeDao;
		this.baseDao = productTypeDao;
	}

	@Override
	public ProductType getProductTypeBySName(ProductType pt) {
		return productTypeDao.getProductTypeBySName(pt);
	}

	
	
	

}
