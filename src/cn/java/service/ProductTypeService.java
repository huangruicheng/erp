package cn.java.service;

import cn.java.model.Emp;
import cn.java.model.ProductType;
import cn.java.query.ProductTypeQuery;

public interface ProductTypeService extends BaseService<ProductType, ProductTypeQuery> {
	
	//根据supplierId和name查询到productType
	public ProductType getProductTypeBySName(ProductType pt);
	
}
