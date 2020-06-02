package cn.java.utils;

import java.util.List;

public class Page {
	
	/**
	 * ҳ��
	 */
	private int pageNo = 1;
	
	/**
	 * ÿҳ�ļ�¼��
	 */
	private int pageSize = 5;
	
	/**
	 * ָ���Ĳ�ѯ�����µ��ܼ�¼�������������Ҫ�Ӻ�̨���ݿ��ѯ����
	 */
	private int totalCount = 0;
	
	/**
	 * ָ����ѯ������ ����ҳ��
	 */
	private int totalPage = 1;
	
	/**
	 * ��ʼ�к�startNum = (pageNo - 1)*pageSize
	 */
	private int startNum = 0;
	
	
	/**
	 * ���������ͨ�����ݿ�����ѯ
	 */
	private List<?> list;


	public int getPageNo() {
		return pageNo;
	}


	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}


	public int getPageSize() {
		return pageSize;
	}


	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}


	public int getTotalCount() {
		return totalCount;
	}


	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}


	/**
	 * pageSize		totalCount		totalPage
	 * 10				0				1
	 * 10				100				10
	 * 10				55				6
	 * 
	 * @return
	 */
	public int getTotalPage() {
		totalPage = totalCount/pageSize;
		if(totalCount == 0 || totalCount%pageSize != 0){
			totalPage++;
		}
		
		return totalPage;
	}


	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}


	/**
	 * ����ҳ���ÿҳ��¼�������㿪ʼ�к�
	 * @return
	 */
	public int getStartNum() {
		return (pageNo - 1)*pageSize;
	}


	public void setStartNum(int startNum) {
		this.startNum = startNum;
	}


	public List<?> getList() {
		return list;
	}


	public void setList(List<?> list) {
		this.list = list;
	}
	
	
	

}
