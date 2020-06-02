package cn.java.service.impl;

import cn.java.dao.OrderDetailDao;
import cn.java.model.OrderDetail;
import cn.java.query.OrderDetailQuery;
import cn.java.service.OrderDetailService;

public class OrderDetailServiceImpl extends BaseServiceImpl<OrderDetail, OrderDetailQuery> implements OrderDetailService {

	
	private OrderDetailDao orderDetailDao;
	
	public void setOrderDetailDao(OrderDetailDao orderDetailDao) {
		this.orderDetailDao = orderDetailDao;
		this.baseDao = orderDetailDao;
	}
	
	

}
