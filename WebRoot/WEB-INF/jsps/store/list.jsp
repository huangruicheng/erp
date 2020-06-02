<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../taglibs.jsp"%>
<script type="text/javascript">
	$(function() {
		$("#query").click(function() {
			$("[name='pageNum']").val(1);
			$("#pageNo").val(1);
			$("form:first").submit();
		});
		$("#addStoreButton").click(function(){
			var diag = new Dialog();
			diag.Width = 850;
			diag.Height = 200;
			diag.ShowButtonRow=true;
			diag.Title = "添加仓库";
			diag.URL = "${path}/store_input";
			diag.OKEvent = function(){
				var win = diag.innerFrame.contentWindow;
				//调用提交表单的方法获得返回值
				var result = win.submitForm();
				//如果成功页面要刷新
				if(result == "success"){
					diag.close();
					window.location.href = "${path }/store_list";
				}
			};
			diag.show();
			
		});
	});
	function showMsg(msg,uuid){
		top.$('context-msg').style.display = "block";
		top.$('context-msg-text').innerHTML=msg;
		top.$('hid-action').value="actionName";
		top.lock.show();
	}
	//修改sotre信息
	function updateStore(storeId){
		var diag = new Dialog();
		diag.Width = 850;
		diag.Height = 200;
		diag.ShowButtonRow=true;
		diag.Title = "修改仓库";
		diag.URL = "${path}/store_update?store.storeId="+storeId;
		diag.OKEvent = function(){
			var win = diag.innerFrame.contentWindow;
			//调用提交表单的方法获得返回值
			var result = win.submitForm();
			//如果成功页面要刷新
			if(result == "success"){
				diag.close();
				window.location.href = "${path }/store_list";
			}
		};
		diag.show();
	}
	
	//删除
	function deleteStore(storeId){
		Dialog.confirm('确认要删除吗?',function(){
			window.location.href = "${path}/store_delete?store.storeId="+storeId;
		});
	}
</script>
<div class="content-right">
	<div class="content-r-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">仓库管理</span>
		</div>
	</div>
	<div class="content-text">
		<form action="${path }/store_list" method="post"> 
			<div class="square-o-top">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					style="font-size:14px; font-weight:bold; font-family:"黑体";">
					<tr align="right">
						<td align="right" width="12%"></td>
						<td align="right" width="13%"></td>
						<td align="right" width="9%">仓库名称: </td>
						<td align="right" width="13%"><s:textfield name="query.name" type="text" size="18"></s:textfield></td>
						<td align="right" width="9%">仓库地址: </td>
						<td align="right" width="13%"><s:textfield name="query.address" type="text" size="18"></s:textfield></td>
						<td align="right" width="10%"><a id="query"><img src="${path}/images/can_b_01.gif" border="0" /> </a></td>
						<td align="right" width="10%"><a id="addStoreButton"  href="javascript:void(0);"><img src="${path}/images/can_b_02.gif" border="0" /> </a></td>
					</tr>
					
					<%-- <tr>
						<td width="68" height="30">&nbsp;</td>
						<td width="123">&nbsp;</td>
						<td width="62">角色名称</td>
						<td width="142"><s:textfield name="query.name" type="text" size="18" ></s:textfield>
						<td width="60">角色编码</td>
						<td width="149"><s:textfield name="query.code" type="text" size="18"></s:textfield>
						<td width="70"><a id="query"> <img src="${path}/images/can_b_01.gif" border="0" /> </a></td>
						<td width="70" style="padding-bottom: 4px"><a id="addRoleButton" href="javascript:void(0);"><img src="${path}/images/can_b_02.gif" border="0" /></a></td>
					</tr> --%>
				</table>
			</div>
			<!--"square-o-top"end-->
			<div class="square-order">
				<table width="100%" border="1" cellpadding="0" cellspacing="0">
					<tr align="center"
						style="background:url(${path}/images/table_bg.gif) repeat-x;">
						<td width="20%" height="30">仓库名称</td>
						<!-- <td width="10%">仓库管理员</td> -->
						<td width="44%">仓库地址</td>
						<!-- <td width="20%">总容积量</td> -->
						<!-- <td width="10%">当前容积量</td> -->
						<td width="16%">操作</td>
					</tr>
					<s:iterator value="#page.list" var="store">
						<tr align="center" bgcolor="#FFFFFF">
							<td width="13%" height="30"><s:property value="#store.name"/></td>
							<!-- <td>张三</td> -->
							<td align="center">&nbsp;<s:property value="#store.address"/></td>
							<%-- <td align="center"><s:property value="#store.stockman"/>&nbsp;米<sup>3</sup>&nbsp;</td> --%>
							<!-- <td align="right">20&nbsp;米<sup>3</sup>&nbsp;</td> -->
							<td>
								<img src="${path}/images/icon_3.gif" /> 
								<span style="line-height:12px; text-align:center;"> 
									<a href="javascript:void(0)" class="xiu" onclick="updateStore(<s:property value="#store.storeId"/>)">修改</a>
								</span> 
								<img src="${path}/images/icon_04.gif" /> 
								<span style="line-height:12px; text-align:center;"> 
									<a href="javascript:void(0)" class="xiu" onclick="deleteStore(<s:property value="#store.storeId"/>)">删除</a>
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
