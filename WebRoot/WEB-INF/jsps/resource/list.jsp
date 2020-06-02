<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../taglibs.jsp"%>
<script type="text/javascript">
	$(function() {
		$("#query").click(function() {
			$("[name='pageNum']").val(1);
			$("#pageNo").val(1);
			$("form:first").submit();
		});
		$("#addResourceButton").click(function(){
			var diag = new Dialog();
			diag.Width = 850;
			diag.Height = 200;
			diag.ShowButtonRow=true;
			diag.Title = "添加资源";
			diag.URL = "${path}/resource_input";
			diag.OKEvent = function(){
				var win = diag.innerFrame.contentWindow;
				//调用提交表单的方法获得返回值
				var result = win.submitForm();
				//如果成功页面要刷新
				if(result == "success"){
					diag.close();
					window.location.href = "${path }/resource_list";
				}
			};
			diag.show();
			
		});
	});
	function showMsg(msg,uuid){
		//top.document.getElementById("context-msg").style.display = "block";
		top.$('context-msg').style.display = "block";
		top.$('context-msg-text').innerHTML=msg;
		top.$('hid-action').value="actionName";
		top.lock.show();
	}
	//修改emp信息
	function updateResource(resourceId){
		var diag = new Dialog();
		diag.Width = 850;
		diag.Height = 200;
		diag.ShowButtonRow=true;
		diag.Title = "修改部门";
		diag.URL = "${path}/resource_update?resource.resourceId="+resourceId;
		diag.OKEvent = function(){
			var win = diag.innerFrame.contentWindow;
			//调用提交表单的方法获得返回值
			var result = win.submitForm();
			//如果成功页面要刷新
			if(result == "success"){
				diag.close();
				window.location.href = "${path }/resource_list";
			}
		};
		diag.show();
	}
	
	//删除
	function deleteResource(resourceId){
		Dialog.confirm('确认要删除吗?',function(){
			window.location.href = "${path}/resource_delete?resource.resourceId="+resourceId;
		});
	}
</script>
<div class="content-right">
	<div class="content-r-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">资源管理</span>
		</div>
	</div>
	<div class="content-text">
		<form action="${path }/resource_list" method="post">
			<div class="square-o-top">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					style="font-size:14px; font-weight:bold; font-family:"黑体";">
					<tr>
						<td width="481" height="30" align="right">资源名称&nbsp;</td>
						<td width="123"><s:textfield name="query.name" type="text" size="18"></s:textfield></td>
						<td width="70"><a id="query"> <img src="${path}/images/can_b_01.gif" border="0" /> </a></td>
						<td width="70" style="padding-bottom: 4px"><a id="addResourceButton" href="javascript:void(0)"><img src="${path}/images/can_b_02.gif" border="0" /></a></td>
					</tr>
				</table>
			</div>
			<!--"square-o-top"end-->
			<div class="square-order">
				<table width="100%" border="1" cellpadding="0" cellspacing="0">
					<tr align="center"
						style="background:url(${path}/images/table_bg.gif) repeat-x;">
						<td height="30">资源名称</td>
						<td>资源值</td>
						<td width="16%">操作</td>
					</tr>
					<s:iterator value="#page.list" var="resource">
						<tr align="center" bgcolor="#FFFFFF">
						<td width="13%" height="30"><s:property value="#resource.name"/> </td>
						<td align="left">&nbsp;<s:property value="#resource.url"/></td>
						<td>
							<img src="${path}/images/icon_3.gif" /> 
							<span style="line-height:12px; text-align:center;"> 
								<a href="javascript:void(0)"  class="xiu" onclick="updateResource(<s:property value="#resource.resourceId"/>)">修改</a>
							</span> 
							<img src="${path}/images/icon_04.gif" /> 
							<span style="line-height:12px; text-align:center;"> 
								<a href="javascript:void(0)" class="xiu" onclick="deleteResource(<s:property value="#resource.resourceId"/>)">删除</a>
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
