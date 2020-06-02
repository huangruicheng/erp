package cn.java.service.impl;

import java.sql.Timestamp;
import java.util.Date;

import cn.java.dao.ConsoleLogDao;
import cn.java.dao.OrderDetailDao;
import cn.java.dao.OrderModelDao;
import cn.java.model.ConsoleLog;
import cn.java.model.Emp;
import cn.java.model.OrderModel;
import cn.java.query.OrderModelQuery;
import cn.java.service.OrderModelService;
import cn.java.utils.ERPConstants;

public class OrderModelServiceImpl extends BaseServiceImpl<OrderModel, OrderModelQuery> implements OrderModelService {

	
	private OrderModelDao orderModelDao;
	
	private ConsoleLogDao consoleLogDao;
	
	private OrderDetailDao orderDetailDao;
	
	
	public void setOrderDetailDao(OrderDetailDao orderDetailDao) {
		this.orderDetailDao = orderDetailDao;
	}

	public void setConsoleLogDao(ConsoleLogDao consoleLogDao) {
		this.consoleLogDao = consoleLogDao;
	}

	public void setOrderModelDao(OrderModelDao orderModelDao) {
		this.orderModelDao = orderModelDao;
		this.baseDao = orderModelDao;
	}

	@Override
	public void saveOrder(OrderModel order) {
		orderModelDao.save(order);
		
	}

	@Override
	public void updateAduitOrder(Emp emp, OrderModel order, String note) {
		OrderModel order1 = orderModelDao.getObj(order.getOrderId());
		//设置订单状态
		order1.setOrderState(order.getOrderState());
		//设置审核时间
		order1.setCheckTime(new Date());
		//设置审核人
		order1.setOrderChecker(emp);
		//创建操作日志
		ConsoleLog consoleLog = new ConsoleLog();
		consoleLog.setNote(note);
		consoleLog.setTableName("order_model");
		consoleLog.setOptType("审核订单");
		consoleLog.setOptTime(new Timestamp(new Date().getTime()));
		consoleLog.setEmp(emp);
		consoleLog.setEntityId(order.getOrderId());
		consoleLogDao.save(consoleLog);
	}

	@Override
	public void updateOrder(OrderModel order) {
		orderDetailDao.deleteDetailByOrderId(order.getOrderId());
		orderModelDao.update(order);
	}

	@Override
	public void updateAssginOrder(OrderModel order) {
		OrderModel order1 = orderModelDao.getObj(order.getOrderId());
		//设置指派单的人
		order1.setCompleter(order.getCompleter());
		//修改类型为运输单
		order1.setOrderType(new Integer(ERPConstants.ORDER_TYPE_TRANS));
		//修改状态为待采购
		order1.setOrderState(new Integer(ERPConstants.ORDER_TYPE_TRANS_BUY));
		orderModelDao.update(order1);
	}
	
	

}
