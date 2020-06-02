package cn.java.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.java.model.Emp;
import cn.java.model.OrderModel;
import cn.java.model.Store;
import cn.java.model.StoreDetail;
import cn.java.query.StoreQuery;
import cn.java.service.OrderModelService;
import cn.java.service.StoreService;
import cn.java.utils.Page;

import com.opensymphony.xwork2.ActionContext;

public class StoreAction extends BaseAction {
	
	
	private StoreQuery query = new StoreQuery();
	
	private Store store = new Store();
	
	
	private Integer productId;
	
	private Integer productNum;
	
	private Integer orderDetailId;
	
	

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getProductNum() {
		return productNum;
	}

	public void setProductNum(Integer productNum) {
		this.productNum = productNum;
	}

	public Integer getOrderDetailId() {
		return orderDetailId;
	}

	public void setOrderDetailId(Integer orderDetailId) {
		this.orderDetailId = orderDetailId;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public StoreQuery getQuery() {
		return query;
	}

	public void setQuery(StoreQuery query) {
		this.query = query;
	}

	private StoreService storeService;
	
	
	public StoreService getStoreService() {
		return storeService;
	}

	public void setStoreService(StoreService storeService) {
		this.storeService = storeService;
	}

	/**
	 * ²Ö¿âÁÐ±í²éÑ¯
	 * @return
	 */
	public String store_slist(){
		
		ActionContext context = ActionContext.getContext();
		
		Integer pageNo = query.getPageNo();
		if(pageNo == null ){
			query.setPageNo(1);
		}
		
		Page page = storeService.queryObjByCondition(query, exclude);
		
		context.put("page", page);
		
		
		return SUCCESS;
	}
	/**
	 * ²Ö¿â
	 * @return
	 */
	public String store_list(){
		
		ActionContext context = ActionContext.getContext();
		
		Integer pageNo = query.getPageNo();
		if(pageNo == null ){
			query.setPageNo(1);
		}
		
		Page page = storeService.queryObjByCondition(query, exclude);
		
		context.put("page", page);
		
		
		return SUCCESS;
	}
	
	public String store_inList(){
		return SUCCESS;
	}
	
	public String store_inStore(){
		ActionContext context = ActionContext.getContext();
		List<Store> sList = storeService.list();
		context.put("sList", sList);
		return SUCCESS;
	}
	/**
	 * Èë¿â
	 * @throws IOException 
	 */
	public void ajax_store_instoreTure() throws IOException{
		storeService.updateInStore(productId, productNum, orderDetailId, query.getStoreId());
		response.getWriter().write("success");
	}
	
	
	/**
	 * ²Ö¿âÌí¼Ó
	 * @return
	 */
	public String store_input(){
		return SUCCESS;
	}
	/**
	 * ²Ö¿âÌí¼Ó
	 * @return
	 */
	public String store_update(){
		store = storeService.getObj(store.getStoreId());
		return SUCCESS;
	}
	
	/**
	 * ²Ö¿âÉ¾³ý
	 * @return
	 */
	public String store_delete(){
		store = storeService.getObj(store.getStoreId());
		storeService.delete(store);
		return LIST;
	}
	
	/**
	 * ²Ö¿âÃ÷Ï¸
	 * @return
	 */
	public String store_storeDetail(){
		ActionContext context = ActionContext.getContext();
		Store store = storeService.getObj(query.getStoreId());
		Set<StoreDetail> details = store.getStoreDetails();
		context.put("details", details);
		
		return SUCCESS;
	}
	
	/**
	 * Ìí¼Ó
	 * @throws IOException
	 */
	public void ajax_store_add() throws IOException{
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session2 = context.getSession();
		Emp emp = (Emp) session2.get("user");
		store.setStoreAdmin(emp);
		storeService.save(store);
		
		response.getWriter().write("success");
	}
	/**
	 * ÐÞ¸Ä
	 * @throws IOException
	 */
	public void ajax_store_update() throws IOException{
		
		storeService.update(store);
		response.getWriter().write("success");
	}
	
}
