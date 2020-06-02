package cn.java.dao;

import cn.java.model.OrderDetail;
import cn.java.model.OrderModel;
import cn.java.query.OrderDetailQuery;

public interface OrderDetailDao extends BaseDao<OrderDetail, OrderDetailQuery> {
	
	//根据orderId删除订单明细
	public void deleteDetailByOrderId(Integer orderId);

}
