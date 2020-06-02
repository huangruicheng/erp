package cn.java.service.impl;

import cn.java.dao.ProductDao;
import cn.java.model.Product;
import cn.java.query.ProductQuery;
import cn.java.service.ProductService;

public class ProductServiceImpl extends BaseServiceImpl<Product, ProductQuery> implements ProductService {

	
	private ProductDao productDao;
	
	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
		this.baseDao = productDao;
	}
	
	

}
