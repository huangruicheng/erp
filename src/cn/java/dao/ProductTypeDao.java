package cn.java.dao;

import cn.java.model.ProductType;
import cn.java.query.ProductTypeQuery;

public interface ProductTypeDao extends BaseDao<ProductType, ProductTypeQuery> {
	
	//根据supplierId和name查询到productType
	public ProductType getProductTypeBySName(ProductType pt);
	

}
