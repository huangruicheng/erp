package cn.java.query;

import cn.java.model.Dep;

public class DepQuery extends Dep {

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
	
}
