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
		//���ö���״̬
		order1.setOrderState(order.getOrderState());
		//�������ʱ��
		order1.setCheckTime(new Date());
		//���������
		order1.setOrderChecker(emp);
		//����������־
		ConsoleLog consoleLog = new ConsoleLog();
		consoleLog.setNote(note);
		consoleLog.setTableName("order_model");
		consoleLog.setOptType("��˶���");
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
		//����ָ�ɵ�����
		order1.setCompleter(order.getCompleter());
		//�޸�����Ϊ���䵥
		order1.setOrderType(new Integer(ERPConstants.ORDER_TYPE_TRANS));
		//�޸�״̬Ϊ���ɹ�
		order1.setOrderState(new Integer(ERPConstants.ORDER_TYPE_TRANS_BUY));
		orderModelDao.update(order1);
	}
	
	

}
