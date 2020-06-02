<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../taglibs.jsp" %>
<script type="text/javascript">
	$(function() {
		$("#supplierId").change(function(){
			var supplierId = $(this).val();
			$.ajax({
				url:"${path}/ajax_supplier_getProductType",
				type:"post",
				data:{
					"supplier.supplierId":supplierId
				},
				async:false,
				dataType:"text",
				success:function(responseText){
					$("#productType").empty();
					$("#productType").append("<option value=''>----请-选-择----</option>");
					var jsonArr = $.parseJSON(responseText);
					for(var i = 0; i < jsonArr.length; i++){
						$("#productType").append("<option value='"+jsonArr[i].productTypeId+"'>"+jsonArr[i].name+"</option>")
					}
					
				}
			})
		})
	});
	
	function submitForm(){
		//成功标识
		var result = "";
		$("#productForm").ajaxSubmit({
			async:false,
			dataType:"text",
			success:function(responseText){
				//alert(222)
				//如果后台添加emp正确返回success
				result = responseText;
				//alert(result)
			}
	
		})
		return result;
	}
</script>
<div class="content-right">
	<div class="content-r-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">商品管理</span>
		</div>
	</div>
	<div class="content-text">
		<div class="square-order">
			<form id="productForm" action="${path }/ajax_product_add" method="post">
  			<div style="border:1px solid #cecece;">
				<table width="100%"  border="0" cellpadding="0" cellspacing="0">
				  <tr bgcolor="#FFFFFF">
				    <td>&nbsp;</td>
				  </tr>
				</table>
				<table width="100%"  border="0" cellpadding="0" cellspacing="0">
				    <tr  bgcolor="#FFFFFF">
				      <td width="18%" height="30" align="center">供&nbsp;应&nbsp;商</td>
				      <td width="32%">
				      	<!-- 	<select style="width:190px">
								<option value="-1">----请-选-择----</option>
								<option value="1">七匹狼</option>
								<option value="2">康师傅</option>
							</select> -->
							<s:select id="supplierId" style="width:190px" list="#suppliers" name="product.productType.supplier.supplierId" cssClass="kuan" headerKey="" headerValue="----请-选-择----" listKey="supplierId" listValue="name"></s:select>
				      </td>
				      <td width="18%"align="center">商品类别</td>
				      <td width="32%">
				      		<select id="productType" name="product.productType.productTypeId" style="width:190px">
								<option value="">----请-选-择----</option>
								
							</select>
							
					  </td>
				    </tr>
				    <tr bgcolor="#FFFFFF">
					  <td colspan="4">&nbsp;</td>
					</tr>
				    <tr  bgcolor="#FFFFFF">
				      <td align="center">商品名称</td>
				      <td>
				      	<s:textfield name="product.name" type="text" size="25"></s:textfield>
				      </td>
				      <td  align="center">产&nbsp;&nbsp;&nbsp;&nbsp;地</td>
				      <td >
				      	<s:textfield name="product.origin" type="text" size="25"></s:textfield>
				      </td>
				    </tr>
				     <tr bgcolor="#FFFFFF">
					  <td colspan="4">&nbsp;</td>
					</tr>
				    <tr  bgcolor="#FFFFFF">
				      <td height="30" align="center">生产厂家</td>
				      <td>
				      	<s:textfield name="product.producer" type="text" size="25"></s:textfield>
				      <td align="center">单&nbsp;&nbsp;&nbsp;&nbsp;位</td>
				      <td>
				      	<s:textfield name="product.unit" type="text" size="25"></s:textfield>
					  </td>
				     </tr>
				    <tr bgcolor="#FFFFFF">
					  <td colspan="4">&nbsp;</td>
					</tr>
				    <tr  bgcolor="#FFFFFF">
				      <td height="30" align="center">进货单价</td>
				      <td>
				      	<s:textfield name="product.inPrice" type="text" size="25"></s:textfield>
					  </td>
				      <td align="center">销售单价</td>
				      <td>
				      	<s:textfield name="product.outPrice" type="text" size="25"></s:textfield>
					  </td>
				    </tr>
				    <tr bgcolor="#FFFFFF">
					  <td colspan="4">&nbsp;</td>
					</tr>
				    <!-- <tr  bgcolor="#FFFFFF">
				      <td height="30" align="center">体&nbsp;&nbsp;&nbsp;&nbsp;积</td>
				      <td>
				      	<input type="text" size="25"/>
					  </td>
				      <td align="center">&nbsp;</td>
				      <td>&nbsp;</td>
				    </tr> -->
				     <tr bgcolor="#FFFFFF">
					  <td colspan="4">&nbsp;</td>
					</tr>
				</table>
			</div>
			
			</form>
		</div><!--"square-order"end-->
	</div><!--"content-text"end-->
	<div class="content-bbg"><img src="${path}/images/content_bbg.jpg" /></div>
</div>
