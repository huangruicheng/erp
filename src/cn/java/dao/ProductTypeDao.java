package cn.java.dao;

import cn.java.model.ProductType;
import cn.java.query.ProductTypeQuery;

public interface ProductTypeDao extends BaseDao<ProductType, ProductTypeQuery> {
	
	//����supplierId��name��ѯ��productType
	public ProductType getProductTypeBySName(ProductType pt);
	

}
