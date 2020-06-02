package cn.java.query;

import java.util.Date;

import cn.java.model.OrderModel;

public class OrderModelQuery extends OrderModel{
	
	private Integer pageNo;
	
	private Integer startNum;
	
	private String createrName;
	
	private String checkName;
	
	private String completerName;
	
	private Integer minTotalNum;
	
	private Integer maxTotalNum;
	
	private Integer needs;
	
	private Date minCreateTime;
	
	
	private Date maxCreateTime;
	
	private Double minTotalPrice;
	
	private Double maxTotalPrice;
	
	
	
	public String getCompleterName() {
		return completerName;
	}

	public void setCompleterName(String completerName) {
		this.completerName = completerName;
	}

	public String getCheckName() {
		return checkName;
	}

	public void setCheckName(String checkName) {
		this.checkName = checkName;
	}

	public Integer getNeeds() {
		return needs;
	}

	public void setNeeds(Integer needs) {
		this.needs = needs;
	}

	public String getCreaterName() {
		return createrName;
	}

	public void setCreaterName(String createrName) {
		this.createrName = createrName;
	}

	public Integer getMinTotalNum() {
		return minTotalNum;
	}

	public void setMinTotalNum(Integer minTotalNum) {
		this.minTotalNum = minTotalNum;
	}

	public Integer getMaxTotalNum() {
		return maxTotalNum;
	}

	public void setMaxTotalNum(Integer maxTotalNum) {
		this.maxTotalNum = maxTotalNum;
	}

	public Date getMinCreateTime() {
		return minCreateTime;
	}

	public void setMinCreateTime(Date minCreateTime) {
		this.minCreateTime = minCreateTime;
	}

	public Date getMaxCreateTime() {
		return maxCreateTime;
	}

	public void setMaxCreateTime(Date maxCreateTime) {
		this.maxCreateTime = maxCreateTime;
	}

	public Double getMinTotalPrice() {
		return minTotalPrice;
	}

	public void setMinTotalPrice(Double minTotalPrice) {
		this.minTotalPrice = minTotalPrice;
	}

	public Double getMaxTotalPrice() {
		return maxTotalPrice;
	}

	public void setMaxTotalPrice(Double maxTotalPrice) {
		this.maxTotalPrice = maxTotalPrice;
	}

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
