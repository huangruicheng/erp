<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../taglibs.jsp" %>
<script type="text/javascript">
	$(function() {
		$("#query").click(function() {
			$("form:first").submit();
		});
		
		$("#addProductButten").click(function(){
			var diag = new Dialog();
			diag.Width = 850;
			diag.Height = 400;
			diag.ShowButtonRow=true;
			diag.Title = "添加商品";
			diag.URL = "${path}/product_input";
			diag.OKEvent = function(){
				var win = diag.innerFrame.contentWindow;
				//调用提交表单的方法获得返回值
				var result = win.submitForm();
				//如果成功页面要刷新
				if(result == "success"){
					diag.close();
					window.location.href = "${path }/product_list";
				}
			};
			diag.show();
		});
	});
	
	
	//修改商品类别信息
	function updateProduct(productId){
		var diag = new Dialog();
		diag.Width = 850;
		diag.Height = 400;
		diag.ShowButtonRow=true;
		diag.Title = "修改商品信息";
		diag.URL = "${path}/product_update?product.productId="+productId;
		diag.OKEvent = function(){
			var win = diag.innerFrame.contentWindow;
			//调用提交表单的方法获得返回值
			var result = win.submitForm();
			//如果成功页面要刷新
			if(result == "success"){
				diag.close();
				window.location.href = "${path }/product_list";
			}
		};
		diag.show();
	}
	
	//删除
	function deleteProduct(productId){
		Dialog.confirm('确认要删除吗?',function(){
			window.location.href = "${path}/product_delete?product.productId="+productId;
		});
	}
	
</script>
<div class="content-right">
	<div class="content-r-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">商品管理</span>
		</div>
	</div>
	<div class="content-text">
		<form action="${path }/product_list" method="post"> 
			<div class="square-o-top">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					style="font-size:14px; font-weight:bold; font-family:"黑体";">
					<tr>
						<td>供应商:</td>
						<td>
						<!-- 	<select style="width:113px">
								<option value="-1">----请-选-择----</option>
								<option value="1">康师傅</option>
								<option value="2">七匹狼</option>
							</select> -->
							<s:select list="#suppliers" name="query.supplierId" cssStyle="width: 113px" headerKey="" headerValue="----请-选-择----" listKey="supplierId" listValue="name"></s:select>
						</td>
						<td height="30">商&nbsp;品&nbsp;名</td>
						<td><s:textfield name="query.name" type="text" size="14"></s:textfield></td>
						<td>生产厂家</td>
						<td><s:textfield name="query.producer" type="text" size="14"></s:textfield></td>
						<td>单&nbsp;&nbsp;&nbsp;&nbsp;位</td>
						<td><s:textfield name="query.unit" type="text" size="14"></s:textfield></td>
						<td width="70"><a id="addProductButten" href="javascript:void(0)"><img src="${path}/images/can_b_02.gif" border="0" /> </a></td>
					</tr>
					<tr>
						<td height="30">进货价格</td>
						<td><s:textfield name="query.minInPrice" type="text" size="14"></s:textfield></td>
						<td>到</td>
						<td><s:textfield name="query.maxInPrice" type="text" size="14"></s:textfield></td>
						<td height="30">销售价格</td>
						<td><s:textfield name="query.minOutPrice" type="text" size="14"></s:textfield></td>
						<td>到</td>
						<td><s:textfield name="query.maxOutPrice" type="text" size="14"></s:textfield></td>
						<td><a id="query"> <img src="${path}/images/can_b_01.gif" border="0" /> </a></td>
					</tr>
				</table>
			</div>
			<!--"square-o-top"end-->
			<div class="square-order">
				<table width="100%" border="1" cellpadding="0" cellspacing="0">
					<tr align="center"
						style="background:url(${path}/images/table_bg.gif) repeat-x;">
						<td width="22%" height="30">供应商</td>
						<td width="10%">商品名</td>
						<td width="10%">生产厂家</td>
						<td width="10%">产地</td>
						<td width="10%">进货价格</td>
						<td width="10%">销售价格</td>
						<td width="10%">单位</td>
						<td width="16%">操作</td>
					</tr>
					<s:iterator value="#page.list" var="product">
						<tr align="center" bgcolor="#FFFFFF">
								<td width="13%" height="30"><s:property value="#product.productType.supplier.name"/></td>
								<td><s:property value="#product.name"/></td>
								<td><s:property value="#product.producer"/></td>
								<td><s:property value="#product.origin"/></td>
								<td align="right"><s:property value="#product.inPrice"/>&nbsp;元&nbsp;</td>
								<td align="right"><s:property value="#product.outPrice"/>&nbsp;元&nbsp;</td>
								<td><s:property value="#product.unit"/></td>
								<td>
									<img src="${path}/images/icon_3.gif" /> 
									<span style="line-height:12px; text-align:center;"> 
										<a href="javascript:void(0)" class="xiu" onclick="updateProduct(<s:property value="#product.productId"/>)">修改</a> 
									</span> 
									<img src="${path}/images/icon_04.gif" /> 
									<span style="line-height:12px; text-align:center;"> 
										<a href="javascript:void(0)" class="xiu" onclick="deleteProduct(<s:property value="#product.productId"/>)">删除</a>
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
