<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../taglibs.jsp" %>

<script type="text/javascript">
	$(function() {
		$("#query").click(function() {
			$("#pageNo").val(1);
			$("form:first").submit();
		});
		
		$("#addDepButton").click(function(){
			var diag = new Dialog();
			diag.Width = 850;
			diag.Height = 200;
			diag.ShowButtonRow=true;
			diag.Title = "添加部门";
			diag.URL = "${path}/dep_input";
			diag.OKEvent = function(){
				var win = diag.innerFrame.contentWindow;
				//调用提交表单的方法获得返回值
				var result = win.submitForm();
				//如果成功页面要刷新
				if(result == "success"){
					diag.close();
					window.location.href = "${path }/dep_list";
				}
			};
			diag.show();
			
		});
	});
	
	//修改emp信息
	function updateDep(depId){
		var diag = new Dialog();
		diag.Width = 850;
		diag.Height = 400;
		diag.ShowButtonRow=true;
		diag.Title = "修改部门";
		diag.URL = "${path}/dep_update?dep.depId="+depId;
		diag.OKEvent = function(){
			var win = diag.innerFrame.contentWindow;
			//调用提交表单的方法获得返回值
			var result = win.submitForm();
			//如果成功页面要刷新
			if(result == "success"){
				diag.close();
				window.location.href = "${path }/dep_list";
			}
		};
		diag.show();
	}
	
	//删除
	function deleteDep(depId){
		Dialog.confirm('确认要删除吗?',function(){
			window.location.href = "${path}/dep_delete?dep.depId="+depId;
		});
	}
	
</script>
<div class="content-right">
	<div class="content-r-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">部门管理</span>
		</div>
	</div>
	<div class="content-text">
		<form action="${path }/dep_list" method="post">
			<div class="square-o-top">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					style="font-size:14px; font-weight:bold; font-family:"黑体";">
					<tr>
						<td width="68" height="30">&nbsp;&nbsp;&nbsp;</td>
						<td width="123">&nbsp;</td>
						<td width="62">部门名称:</td>
						<td width="142"><s:textfield name="query.name" type="text" size="18"></s:textfield>
						</td>
						<td width="60">电话:</td>
						<td width="149"><s:textfield name="query.tel" type="text" size="18"></s:textfield></td>
						<td width="70"><a id="query"> <img src="${path}/images/can_b_01.gif" border="0" /> </a></td>
						<td width="70"><a id="addDepButton"  href="javascript:void(0);"><img src="${path}/images/can_b_02.gif" border="0" /> </a></td>
					</tr>
				</table>
			</div>
			<!--"square-o-top"end-->
			<div class="square-order">
				<!-- <center>
					<span style="font-size:20px;color:#96D34A;font-weight:bold">没有查找到满足条件的数据！</span>
				</center> -->
				<table width="100%" border="1" cellpadding="0" cellspacing="0">
					<tr align="center"
						style="background:url(${path}/images/table_bg.gif) repeat-x;">
						<td width="13%" height="30">编号</td>
						<td width="13%">部门名称</td>
						<td width="16%">电话</td>
						<td width="16%">操作</td>
					</tr>
					
					<s:iterator value="#page.list" var="dep">
					<tr align="center" bgcolor="#FFFFFF">
						<td width="13%" height="30"><s:property value="#dep.depId"/></td>
						<td><s:property value="#dep.name"/></td>
						<td><s:property value="#dep.tel"/></td>
						<td>
							<img src="${path}/images/icon_3.gif" /> 
							<span style="line-height:12px; text-align:center;"> 
								<a href="javascript:void(0)" class="xiu" onclick="updateDep(<s:property value="#dep.depId"/>)">修改</a>
							</span> 
							<img src="${path}/images/icon_04.gif" /> 
							<span style="line-height:12px; text-align:center;"> 
								<a href="javascript:void(0)" class="xiu" onclick="deleteDep(<s:property value="#dep.depId"/>)">删除</a>
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
