<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../taglibs.jsp" %>
<script type="text/javascript">
function submitForm(){
	//成功标识
	var result = "";
	$("#supplierForm").ajaxSubmit({
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
			<span class="page_title">供应商管理</span>
		</div>
	</div>
	<div class="content-text">
		<div class="square-order">
			<form id="supplierForm" action="${path }/ajax_supplier_update" method="post">
  			<div style="border:1px solid #cecece;">
  				<input  type="hidden" name="supplier.supplierId" value="<s:property value="supplier.supplierId"/>">
				<table width="100%"  border="0" cellpadding="0" cellspacing="0">
				  <tr bgcolor="#FFFFFF">
				    <td>&nbsp;</td>
				  </tr>
				</table>
				<table width="100%"  border="0" cellpadding="0" cellspacing="0">
				    <tr  bgcolor="#FFFFFF">
				      <td width="18%" height="30" align="center">供应商名称</td>
				      <td width="82%" colspan="3">
				      	<s:textfield id="name" name="supplier.name"  type="text" size="82"></s:textfield>
				      </td>
				    </tr>
				    <tr  bgcolor="#FFFFFF">
				      <td colspan="4">&nbsp;</td>
				    </tr>
				    <tr  bgcolor="#FFFFFF">
				      <td width="18%" height="30" align="center">供应商地址</td>
				      <td width="82%" colspan="3">
				      	<s:textfield name="supplier.address"  type="text" size="82"></s:textfield>
				      </td>
				    </tr>
				    <tr  bgcolor="#FFFFFF">
				      <td colspan="4">&nbsp;</td>
				    </tr>
				    <tr  bgcolor="#FFFFFF">
				      <td width="18%" height="30" align="center">联系人</td>
				      <td width="32%">
				      	<s:textfield name="supplier.contact"  type="text" size="25"></s:textfield>
				      </td>
				      <td width="18%" align="center">电话</td>
				      <td width="32%">
				      	<s:textfield name="supplier.tel"  type="text" size="25"></s:textfield>
				      </td>
				    </tr>
				    <tr  bgcolor="#FFFFFF">
				      <td colspan="4">&nbsp;</td>
				    </tr>
				    <tr  bgcolor="#FFFFFF">
				      <td width="18%" height="30" align="center">送货方式</td>
				      <td width="32%">
				      	<!-- 	<select style="width:190px">
								<option value="-1">-----请-选-择-----</option>
								<option value="1">送货</option>
								<option value="2">自提</option>
							</select> -->
							<s:select list="#{'1':'送货','2':'自提' }" name="supplier.needs" style="width:190px" headerKey="" headerValue="-----请-选-择-----" ></s:select>
				      </td>
				      <td width="18%" align="center">&nbsp;</td>
				      <td width="32%">
				      	&nbsp;
				      </td>
				    </tr>
				    <tr  bgcolor="#FFFFFF">
				      <td colspan="4">&nbsp;</td>
				    </tr>
				</table>
				
			</div>
			</form>
		</div><!--"square-order"end-->
	</div><!--"content-text"end-->
	<div class="content-bbg"><img src="${path}/images/content_bbg.jpg" /></div>
</div>
