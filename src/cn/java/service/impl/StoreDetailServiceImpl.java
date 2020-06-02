package cn.java.service.impl;

import cn.java.dao.StoreDetailDao;
import cn.java.model.StoreDetail;
import cn.java.query.StoreDetailQuery;
import cn.java.service.StoreDetailService;

public class StoreDetailServiceImpl extends BaseServiceImpl<StoreDetail, StoreDetailQuery> implements StoreDetailService {

	
	private StoreDetailDao storeDetailDao;
	
	public void setStoreDetailDao(StoreDetailDao storeDetailDao) {
		this.storeDetailDao = storeDetailDao;
		this.baseDao = storeDetailDao;
	}
	
	

}
