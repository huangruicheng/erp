package cn.java.query;

import cn.java.model.Store;

public class StoreQuery extends Store{
	
	private Integer pageNo;
	
	private Integer startNum;
	
	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getStartNum() {
		return startNum;
	}

	public void setStartNum(Integer startNum) {
		this.startNum = startNum;
	}
	
	

}
