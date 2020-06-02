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
		//获取仓库
		Store store = storeDao.getObj(storeId);	
		//获取订单明细
		OrderDetail orderDetail = orderDetailDao.getObj(orderDetailId);
		//获取仓库下的明细
		Set<StoreDetail> storeDetails = store.getStoreDetails();
		boolean isExist = false;
		for(StoreDetail sd : storeDetails){
			if(sd.getProduct().getProductId().intValue() == productId){
				//修改仓库商品的数量
				sd.setNum(sd.getNum() + productNum);
				//修改订单明细的商品数量	
				orderDetail.setSurplus(orderDetail.getSurplus() - productNum);
				isExist = true;
				break;
			}
		}
		if(!isExist){
			StoreDetail storeDetail = new StoreDetail();
			storeDetail.setNum(productNum);
			//修改订单明细的商品数量	
			orderDetail.setSurplus(orderDetail.getSurplus() - productNum);
			storeDetail.setStoreId(storeId);
			//获取商品
			Product product = productDao.getObj(productId);
			storeDetail.setProduct(product);
			//将新的仓库明细加入到仓库
			storeDetails.add(storeDetail);
		}
		//根据订单明细获取到订单
		String orderId = orderDetail.getOrderId();
		OrderModel order = orderModelDao.getObj(new Integer(orderId));
		//获取到订单下的订单明细
		Set<OrderDetail> ods = order.getOds();
		boolean isFinish = true;
		for(OrderDetail detail : ods){
			if(detail.getSurplus() != 0){
				//修改状态为入库中
				order.setOrderState(new Integer(ERPConstants.ORDER_TYPE_INSTOCK_INING));
				isFinish = false;
			}
		}
		if(isFinish){
			//修改状态为已入库
			order.setOrderState(new Integer(ERPConstants.ORDER_TYPE_INSTOCK_FINISH));
		}
	}
	
	

}
