package cn.java.service;

import cn.java.model.Store;
import cn.java.query.StoreQuery;

public interface StoreService extends BaseService<Store, StoreQuery> {
	
	//Èë¿â
	public void updateInStore(Integer productId, Integer productNum, Integer orderDetailId,Integer storeId);

}
