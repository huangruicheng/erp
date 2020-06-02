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
			<form id="roleForm" action="${path }/ajax_role_add" method="post">
  			<div style="border:1px solid #cecece;">
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
				    <!-- </tr>
				    <tr  bgcolor="#FFFFFF">
				      <td colspan="4">&nbsp;</td>
				    </tr>
				    <tr  bgcolor="#FFFFFF">
				      <td width="18%" height="30" align="center">资源名称</td>
				      <td width="82%" colspan="3">
				      	<input type="checkbox" id="all">全选&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				      	<input type="checkbox" id="reverse">反选
				      </td>
				    </tr>
				    <tr  bgcolor="#FFFFFF">
				      <td width="18%" height="30" align="center">&nbsp;</td>
				      <td width="82%" colspan="3">
				      	<input type="checkbox"/>员工列表
				      	<input type="checkbox"/>添加/修改员工
				      	<input type="checkbox"/>删除员工
				      	<input type="checkbox"/>提交编辑员工
				      </td>
				    </tr>
				     <tr  bgcolor="#FFFFFF">
				      <td width="18%" height="30" align="center">菜单名称</td>
				      <td width="82%" colspan="3">
				      	<input type="checkbox" id="all">全选&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				      	<input type="checkbox" id="reverse">反选
				      </td>
				    </tr>
				    <tr  bgcolor="#FFFFFF">
				      <td width="18%" height="30" align="center">&nbsp;</td>
				      <td width="82%" colspan="3">
				      	<input type="checkbox"/>基础维护
				      	<input type="checkbox"/>部门维护
				      	<input type="checkbox"/>员工维护
				      	<input type="checkbox"/>.....
				      </td>
				    </tr> -->
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
					    	<a href="javascript:document.forms[0].submit()"><img src="${path}/images/order_tuo.gif" border="0" /></a>
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
