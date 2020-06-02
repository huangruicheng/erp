package cn.java.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.java.model.Dep;
import cn.java.model.Emp;
import cn.java.model.OrderDetail;
import cn.java.model.OrderModel;
import cn.java.model.Product;
import cn.java.model.Supplier;
import cn.java.query.OrderModelQuery;
import cn.java.service.DepService;
import cn.java.service.EmpService;
import cn.java.service.OrderDetailService;
import cn.java.service.OrderModelService;
import cn.java.service.ProductService;
import cn.java.service.SupplierService;
import cn.java.utils.ERPConstants;
import cn.java.utils.Page;

import com.opensymphony.xwork2.ActionContext;

public class TranOrderModelAction extends BaseAction {
	
	
	private OrderModelQuery query = new OrderModelQuery();
	
	private OrderModel order = new OrderModel();
	
	private OrderModelService orderModelService;
	
	private DepService depService;
	
	private SupplierService supplierService;
	
	private EmpService empService;
	
	

	public EmpService getEmpService() {
		return empService;
	}

	public void setEmpService(EmpService empService) {
		this.empService = empService;
	}

	public SupplierService getSupplierService() {
		return supplierService;
	}

	public void setSupplierService(SupplierService supplierService) {
		this.supplierService = supplierService;
	}

	public DepService getDepService() {
		return depService;
	}

	public void setDepService(DepService depService) {
		this.depService = depService;
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
	
	public OrderModelService getOrderModelService() {
		return orderModelService;
	}

	public void setOrderModelService(OrderModelService orderModelService) {
		this.orderModelService = orderModelService;
	}
	/**
	 * ��ҳ��ת
	 * @return
	 */
	public String tranOrderModel_taskList(){
		ActionContext context = ActionContext.getContext();
		//��ѯ�����й�Ӧ��
		List<Supplier> suppliers = supplierService.list();
		context.put("suppliers", suppliers);
		Integer pageNo = query.getPageNo();
		if(pageNo == null ){
			query.setPageNo(1);
		}
		Page page = orderModelService.queryObjByCondition(query, exclude);
		context.put("page", page);
		return SUCCESS;
	}
	
	/**
	 * ����ָ��
	 * @return
	 */
	public String tranOrderModel_taskDetail(){
		order = orderModelService.getObj(order.getOrderId());
		Dep dep = depService.getObj(2);
		ActionContext context = ActionContext.getContext();
		context.put("dep", dep);
		return SUCCESS;
	}
	/**
	 * ȷ���������ɹ���
	 * @return
	 */
	public String tranOrderModel_taskDetailBuying(){
		order = orderModelService.getObj(order.getOrderId());
		Dep dep = depService.getObj(2);
		ActionContext context = ActionContext.getContext();
		context.put("dep", dep);
		return SUCCESS;
	}
	/**
	 * �����ѯ
	 * @return
	 */
	public String tranOrderModel_tasks(){
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session2 = context.getSession();
		Emp emp = (Emp) session2.get("user");
		query.setCompleter(emp.getEmpId());
		//��ѯ�����й�Ӧ��
		List<Supplier> suppliers = supplierService.list();
		context.put("suppliers", suppliers);
		Integer pageNo = query.getPageNo();
		if(pageNo == null ){
			query.setPageNo(1);
		}
		Page page = orderModelService.queryObjByCondition(query, exclude);
		context.put("page", page);
		return SUCCESS;
	}
	
	
	/**
	 * ����ָ��
	 * @throws IOException
	 */
	public void ajax_tranOrderModel_assginOrder() throws IOException{
		OrderModel order1 = orderModelService.getObj(order.getOrderId());
		//����ָ�ɵ�����
		Emp emp = empService.getObj(order.getCompleter());
		order1.setOrderCompleter(emp);
		//�޸�����Ϊ���䵥
		order1.setOrderType(new Integer(ERPConstants.ORDER_TYPE_TRANS));
		//�޸�״̬Ϊ���ɹ�
		order1.setOrderState(new Integer(ERPConstants.ORDER_TYPE_TRANS_BUY));
		orderModelService.update(order1);
		//orderModelService.updateAssginOrder(order);
		response.getWriter().write("success");
	}
	/**
	 * ���вɹ���
	 * @throws IOException
	 */
	public void ajax_tranOrderModel_getOrderDetail() throws IOException{
		OrderModel order1 = orderModelService.getObj(order.getOrderId());
		//�޸�״̬Ϊ�ɹ���3
		order1.setOrderState(new Integer(ERPConstants.ORDER_TYPE_TRANS_BUYING));
		orderModelService.update(order1);
		response.getWriter().write("success");
	}
	
	/**
	 * ��ɲɹ�
	 * @throws IOException
	 */
	public void ajax_tranOrderModel_finishTranOrder() throws IOException{
		OrderModel order1 = orderModelService.getObj(order.getOrderId());
		//�޸�����Ϊ��ⵥ
		order1.setOrderType(new Integer(ERPConstants.ORDER_TYPE_INSTOCK));
		//�޸�״̬Ϊ���
		order1.setOrderState(new Integer(ERPConstants.ORDER_TYPE_INSTOCK_WAIT));
		orderModelService.update(order1);
		response.getWriter().write("success");
	}
	/**
	 * ���
	 * @return
	 */
	public String tranOrderModel_inList(){
		ActionContext context = ActionContext.getContext();
		Integer pageNo = query.getPageNo();
		if(pageNo == null ){
			query.setPageNo(1);
		}
		Page page = orderModelService.queryObjByCondition(query, exclude);
		context.put("page", page);
		return "store_success";
	}
	
	/**
	 * �����ϸ
	 * @return
	 */
	public String tranOrderModel_inDetail(){
		order = orderModelService.getObj(query.getOrderId());
		return "store_inDetail";
	}
	
}
