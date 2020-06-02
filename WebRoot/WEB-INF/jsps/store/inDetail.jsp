<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../taglibs.jsp" %>
<script type="text/javascript">
	function inStoreProduct(productId,orderDetailId){
		
		var diag = new Dialog();
		diag.Width = 850;
		diag.Height = 200;
		diag.ShowButtonRow=true;
		diag.Title = "商品入库";
		diag.URL = "${path}/store_inStore";
		diag.OKEvent = function(){
			var win = diag.innerFrame.contentWindow;
			//调用提交表单的方法获得返回值
			var result = win.inStoreTure(productId,orderDetailId);
			//如果成功页面要刷新
			if(result == "success"){
				var orderId = $("#orderId").val();
				//alert(orderId);
				window.location.href = "${path}/tranOrderModel_inDetail?query.orderId=" + orderId;
				diag.close();
			}
		};
		diag.show();
	}
</script>

<div class="content-right">
	<div class="content-r-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">入库订单明细</span>
		</div>
	</div>
	<div class="content-text">
			<div class="square-o-top">
			<input id="orderId" type="hidden" value="<s:property value="order.orderId"/>">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					style="font-size:14px; font-weight:bold; font-family:"黑体";">
					<tr>
						<td width="10%">订 单 号:&nbsp;&nbsp;</td>
						<td class="order_show_msg" colspan="2" width="20%"><s:property value="order.orderNum"/></td>
						<td height="30" width="10%">供应商:&nbsp;&nbsp;</td>
						<td class="order_show_msg" width="20%"><s:property value="order.supplier.name"/></td>
						<td height="30" width="10%">商品总数量:&nbsp;&nbsp;</td>
						<td class="order_show_msg" width="10%"><s:property value="order.totalNum"/></td>
					</tr>
					
				</table>
			</div>
			<!--"square-o-top"end-->
			<div class="square-order">
				<center style="text-decoration:underline;font-size:16px; font-weight:bold; font-family:"黑体";">&nbsp;&nbsp;&nbsp;&nbsp;订&nbsp;&nbsp;单&nbsp;&nbsp;明&nbsp;&nbsp;细&nbsp;&nbsp;&nbsp;&nbsp;</center>
				<br/>
				<table width="100%" border="1" cellpadding="0" cellspacing="0">
					<tr align="center"
						style="background:url(${path}/images/table_bg.gif) repeat-x;">
						<td width="20%" height="30">商品类别</td>
						<td width="20%">商品名称</td>
						<td width="20%">已入库数量</td>
						<td width="20%">剩余数量</td>
						<td width="20%">操作</td>
					</tr>
					<s:iterator value="order.ods" var="orderDetail">
						<tr align="center" bgcolor="#FFFFFF">
						<td height="30"><s:property value="#orderDetail.product.productType.name"/></td>
						<td><s:property value="#orderDetail.product.name"/></td>
						<td><s:property value="#orderDetail.detailNum - #orderDetail.surplus"/></td>
						<td><s:property value="#orderDetail.surplus"/></td>
						<td>
							<s:if test="#orderDetail.surplus != 0">
								<input type="button" onclick="inStoreProduct(<s:property value="#orderDetail.product.productId"/>,<s:property value="#orderDetail.orderDetailId"/>)" value="入库">
							</s:if>
							<s:if test="#orderDetail.surplus == 0">
								已入库
							</s:if>
						</td>
						</tr>
					</s:iterator>
					
					
				</table>
				<br/>
				
			</div>
	</div>
	<div class="content-bbg"></div>
</div>
