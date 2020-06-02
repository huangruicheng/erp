package cn.java.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.java.model.Emp;
import cn.java.model.OrderDetail;
import cn.java.model.OrderModel;
import cn.java.model.Product;
import cn.java.model.Supplier;
import cn.java.query.OrderModelQuery;
import cn.java.service.OrderDetailService;
import cn.java.service.OrderModelService;
import cn.java.service.ProductService;
import cn.java.service.SupplierService;
import cn.java.utils.ERPConstants;
import cn.java.utils.Page;

import com.opensymphony.xwork2.ActionContext;

public class OrderModelAction extends BaseAction {
	
	
	private OrderModelQuery query = new OrderModelQuery();
	
	private OrderModel order = new OrderModel();
	
	private SupplierService supplierService;
	
	private ProductService productService;
	
	private OrderDetailService orderDetailService;
	
	private String note;
	
	
	
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public OrderDetailService getOrderDetailService() {
		return orderDetailService;
	}

	public void setOrderDetailService(OrderDetailService orderDetailService) {
		this.orderDetailService = orderDetailService;
	}

	private Integer [] productTypeId;
	
	private Integer [] productId;
	
	private Integer [] detailNum;
	
	private Double [] detailPrice;
	
	
	
	
	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public Integer[] getProductTypeId() {
		return productTypeId;
	}

	public void setProductTypeId(Integer[] productTypeId) {
		this.productTypeId = productTypeId;
	}

	public Integer[] getProductId() {
		return productId;
	}

	public void setProductId(Integer[] productId) {
		this.productId = productId;
	}

	public Integer[] getDetailNum() {
		return detailNum;
	}

	public void setDetailNum(Integer[] detailNum) {
		this.detailNum = detailNum;
	}

	public Double[] getDetailPrice() {
		return detailPrice;
	}

	public void setDetailPrice(Double[] detailPrice) {
		this.detailPrice = detailPrice;
	}

	public SupplierService getSupplierService() {
		return supplierService;
	}

	public void setSupplierService(SupplierService supplierService) {
		this.supplierService = supplierService;
	}

	public OrderModel getOrder() {
		return order;
	}

	public void setOrder(OrderModel order) {
		this.order = order;
	}

	public OrderModelQuery getQuery() {
		return query;
	}

	public void setQuery(OrderModelQuery query) {
		this.query = query;
	}

	private OrderModelService orderModelService;
	
	
	
	
	
	public OrderModelService getOrderModelService() {
		return orderModelService;
	}

	public void setOrderModelService(OrderModelService orderModelService) {
		this.orderModelService = orderModelService;
	}


	public String orderModel_list(){
		
		ActionContext context = ActionContext.getContext();
		
		Integer pageNo = query.getPageNo();
		if(pageNo == null ){
			query.setPageNo(1);
		}
		
		Page page = orderModelService.queryObjByCondition(query, exclude);
		
		context.put("page", page);
		
		
		return SUCCESS;
	}
	public String orderModel_checklist(){
		
		ActionContext context = ActionContext.getContext();
		
		Integer pageNo = query.getPageNo();
		if(pageNo == null ){
			query.setPageNo(1);
		}
		
		Page page = orderModelService.queryObjByCondition(query, exclude);
		
		context.put("page", page);
		
		
		return SUCCESS;
	}
	
	public String orderModel_input(){
		ActionContext context = ActionContext.getContext();
		List<Supplier> suppliers = supplierService.list();
		context.put("suppliers", suppliers);
		return SUCCESS;
	}
	//查看订单详情
	public String orderModel_orderDetail(){
		order = orderModelService.getObj(query.getOrderId());
		return SUCCESS;
	}
	//审核跳转
	public String orderModel_aduitText(){
		
		return SUCCESS;
	}
	//修改订单
	public String orderModel_update(){
		ActionContext context = ActionContext.getContext();
		List<Supplier> suppliers = supplierService.list();
		context.put("suppliers", suppliers);
		order = orderModelService.getObj(order.getOrderId());
		return SUCCESS;
	}
	//提交订单
	public void ajax_orderModel_submitOrder() throws IOException{
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session2 = context.getSession();
		Emp emp = (Emp) session2.get("user");
		Supplier supplier = supplierService.getObj(order.getSupplierId());
		order.setSupplier(supplier);
		order.setOrderNum(new SimpleDateFormat("yyyyMMddHHssmmSSS").format(new Date()));
		order.setCreateTime(new Date());
		order.setOrderCreater(emp);
		order.setOrderType(new Integer(ERPConstants.ORDER_TYPE_BUY));
		order.setOrderState(new Integer(ERPConstants.ORDER_TYPE_BUY_AUDIT));
		Set<OrderDetail> ods = new HashSet<OrderDetail>();
		int totalNum = 0;
		double totalPrice = 0;
		for(int i = 0; i < productTypeId.length; i++ ){
			OrderDetail orderDetail = new OrderDetail();
			Product product = productService.getObj(productId[i]);
			orderDetail.setProduct(product);
			orderDetail.setDetailNum(detailNum[i]);
			orderDetail.setDetailPrice(detailPrice[i]);
			orderDetail.setSurplus(detailNum[i]);
			ods.add(orderDetail);
			totalNum = totalNum + detailNum[i];
			totalPrice = totalPrice + detailPrice[i]*detailNum[i];
		}
		order.setTotalNum(totalNum);
		order.setTotalPrice(totalPrice);
		order.setOds(ods);
		orderModelService.saveOrder(order);
		response.getWriter().write("success");
		
	}
	//修改订单ajax_orderModel_updateOrder
	public void ajax_orderModel_updateOrder() throws IOException{
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session2 = context.getSession();
		Emp emp = (Emp) session2.get("user");
		
		Supplier supplier = supplierService.getObj(order.getSupplier().getSupplierId());
		order.setSupplier(supplier);
		//order.setOrderNum(new SimpleDateFormat("yyyyMMddHHssmmSSS").format(new Date()));
		order.setCreateTime(new Date());
		order.setOrderCreater(emp);
		order.setOrderType(new Integer(ERPConstants.ORDER_TYPE_BUY));
		order.setOrderState(new Integer(ERPConstants.ORDER_TYPE_BUY_AUDIT));
		Set<OrderDetail> ods = new HashSet<OrderDetail>();
		int totalNum = 0;
		double totalPrice = 0;
		for(int i = 0; i < productTypeId.length; i++ ){
			OrderDetail orderDetail = new OrderDetail();
			Product product = productService.getObj(productId[i]);
			orderDetail.setProduct(product);
			orderDetail.setDetailNum(detailNum[i]);
			orderDetail.setDetailPrice(detailPrice[i]);
			orderDetail.setSurplus(detailNum[i]);
			ods.add(orderDetail);
			totalNum = totalNum + detailNum[i];
			totalPrice = totalPrice + detailPrice[i]*detailNum[i];
		}
		order.setTotalNum(totalNum);
		order.setTotalPrice(totalPrice);
		order.setOds(ods);
		
		orderModelService.updateOrder(order);
		response.getWriter().write("success");
		
	}
	//审核订单
	public void ajax_orderModel_aduitOrder() throws IOException{
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session2 = context.getSession();
		Emp emp = (Emp) session2.get("user");
		orderModelService.updateAduitOrder(emp, order, note);
		response.getWriter().write("success");
		
	}
	
	
}
