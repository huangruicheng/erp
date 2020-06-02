package cn.java.controller;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import cn.java.model.Product;
import cn.java.model.ProductType;
import cn.java.model.Supplier;
import cn.java.query.ProductTypeQuery;
import cn.java.service.ProductTypeService;
import cn.java.service.SupplierService;
import cn.java.utils.JSONUtils;
import cn.java.utils.Page;

import com.opensymphony.xwork2.ActionContext;

public class ProductTypeAction extends BaseAction {
	
	
	private ProductTypeQuery query = new ProductTypeQuery();
	
	private ProductType productType = new ProductType();
	
	
	
	public ProductType getProductType() {
		return productType;
	}

	public void setProductType(ProductType productType) {
		this.productType = productType;
	}

	public ProductTypeQuery getQuery() {
		return query;
	}

	public void setQuery(ProductTypeQuery query) {
		this.query = query;
	}

	private ProductTypeService productTypeService;
	
	
	private SupplierService supplierService;
	
	
	
	
	public SupplierService getSupplierService() {
		return supplierService;
	}

	public void setSupplierService(SupplierService supplierService) {
		this.supplierService = supplierService;
	}

	public ProductTypeService getProductTypeService() {
		return productTypeService;
	}

	public void setProductTypeService(ProductTypeService productTypeService) {
		this.productTypeService = productTypeService;
	}


	public String productType_list(){

		ActionContext context = ActionContext.getContext();
		
		//��ѯ�����й�Ӧ��
		List<Supplier> suppliers = supplierService.list();
		
		Integer pageNo = query.getPageNo();
		if(pageNo == null ){
			query.setPageNo(1);
		}
		
		Page page = productTypeService.queryObjByCondition(query, exclude);
		
		context.put("page", page);
		context.put("suppliers", suppliers);
		
		
		return SUCCESS;

	}
	
	public String productType_input(){
		ActionContext context = ActionContext.getContext();
		
		//��ѯ�����еĹ�Ӧ��
		List<Supplier> suppliers = supplierService.list();
		context.put("suppliers", suppliers);
		return SUCCESS;
	}
	
	public String productType_update(){
		ActionContext context = ActionContext.getContext();
		//��ѯ�����й�Ӧ��
		List<Supplier> suppliers = supplierService.list();
		context.put("suppliers", suppliers);
		productType = productTypeService.getObj(productType.getProductTypeId());
		return SUCCESS;
	}
	//ɾ��
	public String productType_delete(){
		productTypeService.delete(productType);
		return LIST;
	}
		
	/*
	 * ajax��
	 */
	//���
	public void ajax_productType_add() throws IOException{
		productTypeService.save(productType);
		response.getWriter().write("success");
	}
	//��Ʒ�Ƿ��ظ�У��
	public void ajax_productType_validSname() throws IOException{
		String result = "yes";
		ProductType pn = productTypeService.getProductTypeBySName(productType);
		if(pn != null){
			result = "no";
		}
		response.getWriter().write(result);
	}
	//�޸�
	public void ajax_productType_update() throws IOException{
		productTypeService.update(productType);
		response.getWriter().write("success");
	}
	
	//��ѯ��Ʒ
	public void ajax_productType_getProducts(){
		ProductType productType = productTypeService.getObj(query.getProductTypeId());
		Set<Product> products = productType.getProducts();
		JSONUtils.printJSON(response, products, new String[] {"productType"});
	}
	
	
}
