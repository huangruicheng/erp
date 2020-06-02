package cn.java.model;

import java.util.Date;
import java.util.Set;

/**
 * OrderModel entity. @author MyEclipse Persistence Tools
 */

public class OrderModel implements java.io.Serializable {

	// Fields

	private Integer orderId;
	private String orderNum;
	private Integer creater;
	private Date createTime;
	private Integer checker;
	private Date checkTime;
	private Integer completer;
	private Date endTime;
	private Integer orderType;
	private Integer orderState;
	private Integer totalNum;
	private Double totalPrice;
	private Integer supplierId;
	
	//多对一  一个员工可以有多个订单
	private Emp orderCreater;
	//多对一  一个供应商可以有多个订单
	private Supplier supplier;
	//多对一  一个审核人可以审核很多订单
	private Emp orderChecker;
	//多对一  一个员工可以有很多个派单
	private Emp orderCompleter;
	//一对多
	private Set<OrderDetail> ods;
	
	// Constructors
	

	public Set<OrderDetail> getOds() {
		return ods;
	}

	public Emp getOrderCompleter() {
		return orderCompleter;
	}

	public void setOrderCompleter(Emp orderCompleter) {
		this.orderCompleter = orderCompleter;
	}

	public Emp getOrderChecker() {
		return orderChecker;
	}

	public void setOrderChecker(Emp orderChecker) {
		this.orderChecker = orderChecker;
	}

	public void setOds(Set<OrderDetail> ods) {
		this.ods = ods;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public Emp getOrderCreater() {
		return orderCreater;
	}

	public void setOrderCreater(Emp orderCreater) {
		this.orderCreater = orderCreater;
	}

	/** default constructor */
	public OrderModel() {
	}

	/** full constructor */
	public OrderModel(String orderNum, Integer creater, Date createTime,
			Integer checker, Date checkTime, Integer completer, Date endTime,
			Integer orderType, Integer orderState, Integer totalNum,
			Double totalPrice, Integer supplierId) {
		this.orderNum = orderNum;
		this.creater = creater;
		this.createTime = createTime;
		this.checker = checker;
		this.checkTime = checkTime;
		this.completer = completer;
		this.endTime = endTime;
		this.orderType = orderType;
		this.orderState = orderState;
		this.totalNum = totalNum;
		this.totalPrice = totalPrice;
		this.supplierId = supplierId;
	}

	// Property accessors

	public Integer getOrderId() {
		return this.orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getOrderNum() {
		return this.orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public Integer getCreater() {
		return this.creater;
	}

	public void setCreater(Integer creater) {
		this.creater = creater;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getChecker() {
		return this.checker;
	}

	public void setChecker(Integer checker) {
		this.checker = checker;
	}

	public Date getCheckTime() {
		return this.checkTime;
	}

	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}

	public Integer getCompleter() {
		return this.completer;
	}

	public void setCompleter(Integer completer) {
		this.completer = completer;
	}

	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Integer getOrderType() {
		return this.orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

	public Integer getOrderState() {
		return this.orderState;
	}

	public void setOrderState(Integer orderState) {
		this.orderState = orderState;
	}

	public Integer getTotalNum() {
		return this.totalNum;
	}

	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}

	public Double getTotalPrice() {
		return this.totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Integer getSupplierId() {
		return this.supplierId;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}

}