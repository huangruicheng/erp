<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../taglibs.jsp" %>
<script type="text/javascript">
	function assginOrder(){
		var empId = $("#assignEmp").val();
		var orderId = $("#orderId").val();
		//alert(empId)
		//alert(orderId)
		var result = "";
		$.ajax({
			url:"${path}/ajax_tranOrderModel_assginOrder",
			type:"post",
			data:{
				"order.orderId":orderId,
				"order.completer":empId
			},
			async:false,
			dataType:"text",
			success:function(responseText){
				result = responseText;
				//alert(result)
			}
		})
		return result;
	}

</script>

<div class="content-right">
	<input type="hidden" id="orderId" value="<s:property value="order.orderId"/>">
	<div class="content-r-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">进货订单明细</span>
		</div>
	</div>
	<div class="content-text">
			<div class="square-o-top">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					style="font-size:14px; font-weight:bold; font-family:"黑体";">
					<tr>
						<td height="30">企业名称:</td>
						<td class="order_show_msg"><s:property value="order.supplier.name"/></td>
						<td height="30">订单类别:</td>
						<td class="order_show_msg">
							<e:orderTypetext orderType="${order.orderType }"/>
						</td>
						<td>提货方式:</td>
						<td class="order_show_msg">
						<s:property value="order.supplier.needs == 1?'送货':'自提'"/>
						</td>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;订单编号:</td>
						<td class="order_show_msg" colspan="2"><s:property value="order.orderNum"/></td>
					</tr>
					<tr>
						<td>联&nbsp;系&nbsp;人:</td>
						<td class="order_show_msg"><s:property value="order.supplier.contact"/></td>
						<td>联系方式:</td>
						<td class="order_show_msg"><s:property value="order.supplier.tel"/></td>
						<td>商品总量:</td>
						<td class="order_show_msg"><s:property value="order.totalNum"/></td>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;地&nbsp;&nbsp;&nbsp;&nbsp;址:</td>
						<td class="order_show_msg"><s:property value="order.supplier.address"/></td>
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
						<td width="20%">购买数量</td>
						
					</tr>
					<s:iterator value="order.ods" var="orderDetail">
						<tr align="center" bgcolor="#FFFFFF">
						<td height="30"><s:property value="#orderDetail.product.productType.name"/></td>
						<td><s:property value="#orderDetail.product.name"/></td>
						<td><s:property value="#orderDetail.detailNum"/></td>
						</tr>
					</s:iterator>
					<tr align="right">
							<td height="30" width="20%" colspan="2">指派人&nbsp;&nbsp;</td>
							<td width="20%"><s:select id="assignEmp" list="#dep.emps" listKey="empId" listValue="name"></s:select></td>
						</tr>
					<br/>
				</table>
			</div>
	</div>
	<div class="content-bbg"></div>
</div>
