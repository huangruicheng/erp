package cn.java.service;

import cn.java.model.Emp;
import cn.java.model.ProductType;
import cn.java.query.ProductTypeQuery;

public interface ProductTypeService extends BaseService<ProductType, ProductTypeQuery> {
	
	//����supplierId��name��ѯ��productType
	public ProductType getProductTypeBySName(ProductType pt);
	
}
