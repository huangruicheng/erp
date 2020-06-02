package cn.java.query;

import java.util.Date;

import cn.java.model.Emp;

public class EmpQuery extends Emp{
	
	private Date startBirth;
	
	private Date endBirth;
	
	//当前页数
	private Integer pageNo;
	//开始行号
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

	public Date getStartBirth() {
		return startBirth;
	}

	public void setStartBirth(Date startBirth) {
		this.startBirth = startBirth;
	}

	public Date getEndBirth() {
		return endBirth;
	}

	public void setEndBirth(Date endBirth) {
		this.endBirth = endBirth;
	}
	
	

}
