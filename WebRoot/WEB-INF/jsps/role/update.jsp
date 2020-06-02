<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../taglibs.jsp" %>
<script type="text/javascript">
	$(function() {
		$("#all").click(function() {
			$("[name=resources]:checkbox").attr("checked",$("#all").attr("checked")=="checked");
		});
		$("#reverse").click(function() {
			$("[name=resources]:checkbox").each(function () {
                $(this).attr("checked", !$(this).attr("checked"));
            });

		});
	});
	
	function submitForm(){
		//成功标识
		var result = "";
		$("#roleForm").ajaxSubmit({
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
			<span class="page_title">角色管理</span>
		</div>
	</div>
	<div class="content-text">
		<div class="square-order">
			<form id="roleForm" action="${path }/ajax_role_update" method="post">
  			<div style="border:1px solid #cecece;">
  			<input  type="hidden" name="role.roleId" value="<s:property value="role.roleId"/>">
  			<table width="100%"  border="0" cellpadding="0" cellspacing="0">
				  <tr bgcolor="#FFFFFF">
				    <td>&nbsp;</td>
				  </tr>
				</table>
				<table width="100%"  border="0" cellpadding="0" cellspacing="0">
				    <tr  bgcolor="#FFFFFF">
				      <td width="18%" height="30" align="center">角色名称</td>
				      <td width="32%">
				      	<s:textfield name="role.name" type="text" size="25"></s:textfield>
				      </td>
				      <td width="18%" align="center">角色编码</td>
				      <td width="32%">
				      	<s:textfield name="role.code" type="text" size="25"></s:textfield>
				      </td>
				 </tr>
				 </table>   
				 	<table width="100%"  border="0" cellpadding="0" cellspacing="0">
				  <tr bgcolor="#FFFFFF">
				    <td>&nbsp;</td>
				  </tr>
				</table>
			</form>
		</div><!--"square-order"end-->
	</div><!--"content-text"end-->
	<div class="content-bbg"><img src="${path}/images/content_bbg.jpg" /></div>
</div>
