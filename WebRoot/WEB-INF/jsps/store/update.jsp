<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../taglibs.jsp"%>
<script type="text/javascript">
	$(function() {
		$("#commit").click(function() {
			$("form:first").submit();
		});
	});
	
	function submitForm(){
		//成功标识
		var result = "";
		$("#storeForm").ajaxSubmit({
				async:false,
				dataType:"text",
				success:function(responseText){
					//alert(222)
					//如果后台添加emp正确返回success
					result = responseText;
				}
		})
		return result;
	}
</script>
<div class="content-right">
	<div class="content-r-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">仓库管理</span>
		</div>
	</div>
	<div class="content-text">
		<div class="square-order">
			<form id="storeForm" action="${path }/ajax_store_update" method="post">
  			<div style="border:1px solid #cecece;">
  			<input  type="hidden" name="store.storeId" value="<s:property value="store.storeId"/>">
				<table width="100%"  border="0" cellpadding="0" cellspacing="0">
				  <tr bgcolor="#FFFFFF">
				    <td>&nbsp;</td>
				  </tr>
				</table>
				<table width="100%"  border="0" cellpadding="0" cellspacing="0">
				    <tr  bgcolor="#FFFFFF">
				      <td width="10%" height="30" align="center">仓库名称</td>
				      <td width="20%">
				      	<s:textfield name="store.name" type="text" size="25" ></s:textfield>
				      </td>
				      <td width="10%" align="center">仓库地址</td>
				      <td width="20%">
				      	<s:textfield name="store.address" type="text" size="25" ></s:textfield>
				      </td>
				      
				    </tr>
				  
				</table>
				<table width="100%"  border="0" cellpadding="0" cellspacing="0">
				  <tr bgcolor="#FFFFFF">
				    <td>&nbsp;</td>
				  </tr>
				</table>
			</div>
			
			</form>
		</div><!--"square-order"end-->
	</div><!--"content-text"end-->
	<div class="content-bbg"><img src="${path}/images/content_bbg.jpg" /></div>
</div>
