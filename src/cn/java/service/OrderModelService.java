package cn.java.service;

import cn.java.model.Emp;
import cn.java.model.OrderModel;
import cn.java.query.OrderModelQuery;

public interface OrderModelService extends BaseService<OrderModel, OrderModelQuery> {
	//保存订单
	public void saveOrder(OrderModel order);
	//提交审核订单
	public void updateAduitOrder(Emp emp,OrderModel order,String note);
	
	//修改订单
	public void updateOrder(OrderModel order);
	
	//指派人后，状态得修改
	public void updateAssginOrder(OrderModel order);
}
