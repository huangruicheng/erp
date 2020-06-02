package cn.java.controller;

import java.io.IOException;
import java.util.Set;



import cn.java.model.ProductType;
import cn.java.model.Supplier;
import cn.java.query.SupplierQuery;
import cn.java.service.SupplierService;
import cn.java.utils.JSONUtils;
import cn.java.utils.Page;

import com.opensymphony.xwork2.ActionContext;

public class SupplierAction extends BaseAction {
	
	
	private SupplierQuery query = new SupplierQuery();
	
	private Supplier supplier = new Supplier();
	
	
	
	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public SupplierQuery getQuery() {
		return query;
	}

	public void setQuery(SupplierQuery query) {
		this.query = query;
	}

	private SupplierService supplierService;
	
	
	
	
	
	public SupplierService getSupplierService() {
		return supplierService;
	}

	public void setSupplierService(SupplierService supplierService) {
		this.supplierService = supplierService;
	}


	public String supplier_list(){
		
		ActionContext context = ActionContext.getContext();
		
		Integer pageNo = query.getPageNo();
		if(pageNo == null ){
			query.setPageNo(1);
		}
		
		Page page = supplierService.queryObjByCondition(query, exclude);
		
		context.put("page", page);
		
		
		return SUCCESS;
	}
	
	public String supplier_input(){
		
		return SUCCESS;
	}
	
	public String supplier_update(){
		supplier = supplierService.getObj(supplier.getSupplierId());
		return SUCCESS;
	}
	
	public String supplier_delete(){
		supplierService.delete(supplier);
		return LIST;
	}
	
	
	//添加
	public void ajax_supplier_add() throws IOException{
		supplierService.save(supplier);
		response.getWriter().write("success");
	}
	
	//供应商是否重复校验
	public void ajax_supplier_validSname() throws IOException{
		String result = "yes";
		Supplier supplier1 = supplierService.getSupplierByName(supplier);
		if(supplier1 != null){
			result = "no";
		}
		response.getWriter().write(result);
	}
	
	//修改
	public void ajax_supplier_update() throws IOException{
		supplierService.update(supplier);
		response.getWriter().write("success");
	}
	//查询供应商下的商品
	public void ajax_supplier_getProductType(){
		Supplier supplier1 = supplierService.getObj(supplier.getSupplierId());
		Set<ProductType> pts = supplier1.getPts();
		JSONUtils.printJSON(response, pts, new String[] {"supplier","products"});
	}
	
	
}
