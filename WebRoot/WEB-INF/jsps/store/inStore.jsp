<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../taglibs.jsp" %>
<script type="text/javascript">
	function inStoreTure(productId,orderDetailId){
		var storeId = $("#storeId").val();
		var productNum = $("#productNum").val();
		//alert(productNum)
		var result = "";
		$.ajax({
			url:"${path}/ajax_store_instoreTure",
			type:"post",
			data:{
				"query.storeId":storeId,
				"productNum":productNum,
				"productId":productId,
				"orderDetailId":orderDetailId
			},
			async:false,
			dataType:"text",
			success:function(responseText){
				result = responseText;
			}
		})
		return result;
	}

</script>
<div class="content-right">
	<div class="content-r-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">入库</span>
		</div>
	</div>
	<div class="content-text">
			<div class="square-o-top">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					style="font-size:14px; font-weight:bold; font-family:"黑体";">
					<tr>
						<td width="6%">仓库:&nbsp;&nbsp;</td>
						<td  width="44%">
							<s:select id="storeId" list="sList" listKey="storeId" listValue="name"></s:select>
						</td>
						<td height="30" width="6%">数量:&nbsp;&nbsp;</td>
						<td class="order_show_msg" width="44%">
							<input id="productNum" type="text" >
						</td>
					</tr>
					
				</table>
			</div>
			
</div>
