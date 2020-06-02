<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../taglibs.jsp" %>
<script type="text/javascript">
	$(function() {
		$("#commit").click(function() {
			$("form:first").submit();
		});
	});
	
	function submitForm(){
		//成功标识
		var result = "";
		$("#depForm").ajaxSubmit({
				async:false,
				dataType:"text",
				success:function(responseText){
					//alert(222)
					//如果后台添加emp正确返回success
					result = responseText;
					//alert(result);
				}
		})
		return result;
	}
</script>
	<div class="content-right">
	<div class="content-r-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">部门管理</span>
		</div>
	</div>
	<div class="content-text">
		<div class="square-order">
			<form id="depForm" action="${path }/ajax_dep_update" method="post">
  			<div style="border:1px solid #cecece;">
  				<input  type="hidden" name="dep.depId" value="<s:property value="dep.depId"/>">
				<table width="100%"  border="0" cellpadding="0" cellspacing="0">
				  <tr bgcolor="#FFFFFF">
				    <td>&nbsp;</td>
				  </tr>
				</table>
				<table width="100%"  border="0" cellpadding="0" cellspacing="0">
				    <tr  bgcolor="#FFFFFF">
				      <td width="18%" height="30" align="center">部门名称</td>
				      <td width="32%">
				      <s:textfield name="dep.name" type="text" size="25"></s:textfield>
				      </td>
				      <td width="18%" align="center">电话</td>
				      <td width="32%">
				      	<s:textfield name="dep.tel" type="text" size="25"></s:textfield>
				      </td>
				    </tr>
				    <tr  bgcolor="#FFFFFF">
				      <td colspan="4">&nbsp;</td>
				    </tr>
				</table>
				
			</div>
			<%-- <div class="order-botton">
				<div style="margin:1px auto auto 1px;">
					<table width="100%"  border="0" cellpadding="0" cellspacing="0">
					  <tr>
					    <td>
					    	<a href="javascript:void(0)" id="commit"><img src="${path}/images/order_tuo.gif" border="0" /></a>
					    </td>
					    <td>&nbsp;</td>
					    <td><a href="#"><img src="${path}/images/order_tuo.gif" border="0" /></a></td>
					    <td>&nbsp;</td>
					    <td><a href="#"><img src="${path}/images/order_tuo.gif" border="0" /></a></td>
					  </tr>
					</table>
				</div>
			</div> --%>
			</form>
		</div><!--"square-order"end-->
	</div><!--"content-text"end-->
	<div class="content-bbg"><img src="${path}/images/content_bbg.jpg" /></div>
</div>
