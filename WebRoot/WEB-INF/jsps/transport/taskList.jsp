<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../taglibs.jsp" %>
<script type="text/javascript">
	$(function() {
		$("#query").click(function() {
			$("form:first").submit();
		});
	});
	function assign(orderId){
		var diag = new Dialog();
		diag.Width = 850;
		diag.Height = 400;
		diag.ShowButtonRow=true;
		diag.Title = "任务指派";
		diag.URL = "${path}/tranOrderModel_taskDetail?order.orderId="+orderId;
		diag.OKEvent = function(){
			var win = diag.innerFrame.contentWindow;
			//调用提交表单的方法获得返回值
			var result = win.assginOrder();
			//如果成功页面要刷新
			if(result == "success"){
				diag.close();
				window.location.href = "${path }/tranOrderModel_taskList?query.orderType=1&query.orderState=2";
			}
		};
		diag.show();
	}
</script>
<div class="content-right">
	<div class="content-r-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">商品运输管理</span>
		</div>
	</div>
	<div class="content-text">
		<form action="${path }/tranOrderModel_taskList?query.orderType=1&query.orderState=2" method="post"> 
			<div class="square-o-top">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					style="font-size:14px; font-weight:bold; font-family:"黑体";">
					<tr>
						<td>发货方式:</td>
						<td>
							<s:select list="#{'1':'送货','2':'自提' }" name="query.needs" cssStyle="width:115px" headerKey="" headerValue="----请-选-择----"></s:select>
						</td>
						<td>制单人:</td>
						<td>
						<s:textfield name="query.createrName" type="text" size="10"/>
						</td>
						<td>供&nbsp;应&nbsp;商:</td>
						<td>
							<!-- <select style="width:115px">
								<option value="-1">----请-选-择----</option>
								<option value="1">七匹狼</option>
								<option value="0">康师傅</option>
							</select> -->
							<s:select list="#suppliers" name="query.supplierId" cssStyle="width: 115px" headerKey="" headerValue="----请-选-择----" listKey="supplierId" listValue="name"></s:select>
						</td>
						<td>审核人:</td>
						<td>
						<s:textfield name="query.checkName" type="text" size="10"/>
						</td>
						<td>&nbsp;</td>
						<td><a href="javascript:void(0)" id="query"> 
							<img src="${path}/images/can_b_01.gif" border="0" /> </a>
						</td>
					</tr>
					
				</table>
			</div>
			<!--"square-o-top"end-->
			<div class="square-order">
				<table width="100%" border="1" cellpadding="0" cellspacing="0">
					<tr align="center"
						style="background:url(${path}/images/table_bg.gif) repeat-x;">
						<td width="10%" height="30">订单类别</td>
						<td width="13%">下单时间</td>
						<td width="13%">制单人</td>
						<td width="13%">审核时间</td>
						<td width="13%">审核人</td>
						<td width="20%">供应商</td>
						<td width="8%">发货方式</td>
						<td width="10%">跟单人</td>
					</tr>
					<s:iterator value="#page.list" var="order">
						<tr align="center" bgcolor="#FFFFFF">
							<td height="30">
							<c:set var="orderType" value="${order.orderType }"></c:set>
							<e:orderTypetext orderType="${orderType }"/>
							</td>
							<td><s:property value="#order.createTime"/></td>
							<td><s:property value="#order.orderCreater.name"/></td>
							<td><s:property value="#order.checkTime"/></td>
							<td><s:property value="#order.orderChecker.name"/></td>
							<td><s:property value="#order.supplier.name"/></td>
							<td><s:property value="#order.supplier.needs == 1?'送货' : '自提'"/></td>
							<td>
									<img src="${path}/images/icon_3.gif" /> 
									<span style="line-height:12px; text-align:center;"> 
										<a id="assignId" href="javascript:void(0)" class="xiu" onclick="assign(<s:property value="#order.orderId"/>)">任务指派
										</a> 
									</span>
							</td>
						</tr>
					</s:iterator>
						
						
						
				</table>
				<%@ include file="../tools/paging.jsp" %>
			</div>
		</form>
	</div>
	<div class="content-bbg"></div>
</div>
