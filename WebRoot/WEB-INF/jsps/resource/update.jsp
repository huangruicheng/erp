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
		$("#resourceForm").ajaxSubmit({
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
			<span class="page_title">资源管理</span>
		</div>
	</div>
	<div class="content-text">
		<div class="square-order">
			<form id="resourceForm" action="${path }/ajax_resource_update" method="post">
  			<div style="border:1px solid #cecece;">
  			<input  type="hidden" name="resource.resourceId" value="<s:property value="resource.resourceId"/>">
				<table width="100%"  border="0" cellpadding="0" cellspacing="0">
				  <tr bgcolor="#FFFFFF">
				    <td>&nbsp;</td>
				  </tr>
				</table>
				<table width="100%"  border="0" cellpadding="0" cellspacing="0">
				    <tr  bgcolor="#FFFFFF">
				      <td width="18%" height="30" align="center">资源名称</td>
				      <td width="82%" colspan="3">
				      	<s:textfield name="resource.name"  type="text" size="77" ></s:textfield>
				      </td>
				    </tr>
				    <tr bgcolor="#FFFFFF">
					  <td colspan="4">&nbsp;</td>
					</tr>
				    <tr  bgcolor="#FFFFFF">
				      <td width="18%" height="30" align="center">资源值</td>
				      <td width="82%" colspan="3">
				      <s:textfield name="resource.url"  type="text" size="77" ></s:textfield>
				      </td>
				    </tr>
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
