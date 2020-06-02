package cn.java.dao;

import cn.java.model.OrderDetail;
import cn.java.model.OrderModel;
import cn.java.query.OrderDetailQuery;

public interface OrderDetailDao extends BaseDao<OrderDetail, OrderDetailQuery> {
	
	//����orderIdɾ��������ϸ
	public void deleteDetailByOrderId(Integer orderId);

}
