package cn.java.query;

import cn.java.model.Dep;

public class DepQuery extends Dep {

		//��ǰҳ��
		private Integer pageNo;
		//��ʼ�к�
		private Integer startNum;
		

		public Integer getPageNo() {
			return pageNo;
		}

		public void setPageNo(Integer pageNo) {
			this.pageNo = pageNo;
		}
	
}
