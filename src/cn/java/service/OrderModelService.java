package cn.java.service;

import cn.java.model.Emp;
import cn.java.model.OrderModel;
import cn.java.query.OrderModelQuery;

public interface OrderModelService extends BaseService<OrderModel, OrderModelQuery> {
	//���涩��
	public void saveOrder(OrderModel order);
	//�ύ��˶���
	public void updateAduitOrder(Emp emp,OrderModel order,String note);
	
	//�޸Ķ���
	public void updateOrder(OrderModel order);
	
	//ָ���˺�״̬���޸�
	public void updateAssginOrder(OrderModel order);
}
