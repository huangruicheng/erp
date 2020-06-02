package cn.java.service.impl;

import java.util.Set;

import cn.java.dao.OrderDetailDao;
import cn.java.dao.OrderModelDao;
import cn.java.dao.ProductDao;
import cn.java.dao.StoreDao;
import cn.java.model.OrderDetail;
import cn.java.model.OrderModel;
import cn.java.model.Product;
import cn.java.model.Store;
import cn.java.model.StoreDetail;
import cn.java.query.StoreQuery;
import cn.java.service.StoreService;
import cn.java.utils.ERPConstants;

public class StoreServiceImpl extends BaseServiceImpl<Store, StoreQuery> implements StoreService {

	
	private StoreDao storeDao;
	
	private OrderDetailDao orderDetailDao;
	
	private ProductDao productDao;
	
	private OrderModelDao orderModelDao;
	
	public void setOrderModelDao(OrderModelDao orderModelDao) {
		this.orderModelDao = orderModelDao;
	}
	
	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}
	
	public void setOrderDetailDao(OrderDetailDao orderDetailDao) {
		this.orderDetailDao = orderDetailDao;
	}
	
	public void setStoreDao(StoreDao storeDao) {
		this.storeDao = storeDao;
		this.baseDao = storeDao;
	}

	@Override
	public void updateInStore(Integer productId, Integer productNum,
			Integer orderDetailId, Integer storeId) {
		//��ȡ�ֿ�
		Store store = storeDao.getObj(storeId);	
		//��ȡ������ϸ
		OrderDetail orderDetail = orderDetailDao.getObj(orderDetailId);
		//��ȡ�ֿ��µ���ϸ
		Set<StoreDetail> storeDetails = store.getStoreDetails();
		boolean isExist = false;
		for(StoreDetail sd : storeDetails){
			if(sd.getProduct().getProductId().intValue() == productId){
				//�޸Ĳֿ���Ʒ������
				sd.setNum(sd.getNum() + productNum);
				//�޸Ķ�����ϸ����Ʒ����	
				orderDetail.setSurplus(orderDetail.getSurplus() - productNum);
				isExist = true;
				break;
			}
		}
		if(!isExist){
			StoreDetail storeDetail = new StoreDetail();
			storeDetail.setNum(productNum);
			//�޸Ķ�����ϸ����Ʒ����	
			orderDetail.setSurplus(orderDetail.getSurplus() - productNum);
			storeDetail.setStoreId(storeId);
			//��ȡ��Ʒ
			Product product = productDao.getObj(productId);
			storeDetail.setProduct(product);
			//���µĲֿ���ϸ���뵽�ֿ�
			storeDetails.add(storeDetail);
		}
		//���ݶ�����ϸ��ȡ������
		String orderId = orderDetail.getOrderId();
		OrderModel order = orderModelDao.getObj(new Integer(orderId));
		//��ȡ�������µĶ�����ϸ
		Set<OrderDetail> ods = order.getOds();
		boolean isFinish = true;
		for(OrderDetail detail : ods){
			if(detail.getSurplus() != 0){
				//�޸�״̬Ϊ�����
				order.setOrderState(new Integer(ERPConstants.ORDER_TYPE_INSTOCK_INING));
				isFinish = false;
			}
		}
		if(isFinish){
			//�޸�״̬Ϊ�����
			order.setOrderState(new Integer(ERPConstants.ORDER_TYPE_INSTOCK_FINISH));
		}
	}
	
	

}
