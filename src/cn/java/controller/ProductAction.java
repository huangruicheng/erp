package cn.java.controller;

import java.io.IOException;
import java.util.List;

import cn.java.model.Product;
import cn.java.model.ProductType;
import cn.java.model.Supplier;
import cn.java.query.ProductQuery;
import cn.java.service.ProductService;
import cn.java.service.ProductTypeService;
import cn.java.service.SupplierService;
import cn.java.utils.JSONUtils;
import cn.java.utils.Page;

import com.opensymphony.xwork2.ActionContext;

public class ProductAction extends BaseAction {
	
	
	private ProductQuery query = new ProductQuery();
	
	private Product product = new Product();
	
	private SupplierService supplierService;
	
	
	
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public SupplierService getSupplierService() {
		return supplierService;
	}

	public void setSupplierService(SupplierService supplierService) {
		this.supplierService = supplierService;
	}

	public ProductQuery getQuery() {
		return query;
	}

	public void setQuery(ProductQuery query) {
		this.query = query;
	}

	private ProductService productService;
	
	private ProductTypeService productTypeService;
	
	
	public ProductTypeService getProductTypeService() {
		return productTypeService;
	}

	public void setProductTypeService(ProductTypeService productTypeService) {
		this.productTypeService = productTypeService;
	}

	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}


	public String product_list(){
		
		ActionContext context = ActionContext.getContext();
		List<Supplier> suppliers = supplierService.list();
		Integer pageNo = query.getPageNo();
		if(pageNo == null ){
			query.setPageNo(1);
		}
		
		Page page = productService.queryObjByCondition(query, exclude);
		
		context.put("page", page);
		context.put("suppliers",suppliers);
		
		return SUCCESS;
	}
	
	public String product_input(){
		ActionContext context = ActionContext.getContext();
		List<Supplier> suppliers = supplierService.list();
		List<ProductType> productTypes = productTypeService.list();
		context.put("suppliers",suppliers);
		context.put("productTypes", productTypes);
		return SUCCESS;
	}
	
	public String product_update(){
		ActionContext context = ActionContext.getContext();
		//查询出所有供应商
		List<Supplier> suppliers = supplierService.list();
		context.put("suppliers", suppliers);
		product = productService.getObj(product.getProductId());
		return SUCCESS;
	}
	//删除
	public String product_delete(){
		productService.delete(product);
		return LIST;
	}
		
	/*
	 * ajax区
	 */
	//添加
	public void ajax_product_add() throws IOException{
		productService.save(product);
		response.getWriter().write("success");
	}
	
	//修改
	public void ajax_product_update() throws IOException{
		//product = productService.getObj(product.getProductId());
		productService.update(product);
		response.getWriter().write("success");
	}
	//查询到的商品，传到前台
	public void ajax_product_getProductObj() throws IOException{
		Product pro = productService.getObj(query.getProductId());
		JSONUtils.printJSONObj(response, pro, new String[]{"productType"});
	}
	
}
