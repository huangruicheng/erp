package cn.java.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import cn.java.utils.ERPConstants;

/**
 * �Զ����ǩ�� ���ݶ�������
 * @author Administrator
 *
 */
public class ERPOrderTypeTag extends TagSupport {
	
	//���ڽ��ն�������
	private String orderType;

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	@Override
	public int doStartTag() throws JspException {
		//���JSPWriter������jspҳ��д�ı�
		JspWriter out = pageContext.getOut();
		String text = "";
		switch(orderType){
			case ERPConstants.ORDER_TYPE_BUY:
				text = ERPConstants.ORDER_TYPE_BUY_TEXT;
				break;
			case ERPConstants.ORDER_TYPE_TRANS:
				text = ERPConstants.ORDER_TYPE_TRANS_TEXT;
				break;
			case ERPConstants.ORDER_TYPE_INSTOCK:
				text = ERPConstants.ORDER_TYPE_INSTOCK_TEXT;
				break;
			case ERPConstants.ORDER_TYPE_SALES:
				text = ERPConstants.ORDER_TYPE_SALES_TEXT;
				break;
		}
		try {
			out.write(text);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return super.doStartTag();
	}
	
	
	
	
	

}
