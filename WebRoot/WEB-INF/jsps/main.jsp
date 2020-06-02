<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
 <link rel="stylesheet" href="${path }/js/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>ERP进销存系统-系统主页</title>

<SCRIPT type="text/javascript">
	$(document).ready(function(){
		var setting = {
				data: {
					simpleData: {
						enable: true
					}
				}
			};

			var zNodes =${zNodes};
		
		$.fn.zTree.init($("#treeDemo"), setting, zNodes);
	});
	
</SCRIPT>
</head>
<body>
	<div class="container">
		<div class="head">
			<div class="head-left">
				<span style="font-weight:bold; color:#1f4906">欢迎您-</span><br />
				<span style="color:#4a940d " >
					<s:property value="#session.user.name"/>
				</span>
			</div>
			<div class="head-right" style="margin-bottom: 5px">
				<table width="100%" border="0" cellpadding="0" cellspacing="0" >
					<tr>
						<td width="40%">
						</td>
						<td width="10%">
							<a href="${path}/emp_logOut"  >
								<input type="button" style="background:#E6F5DD ; font-size:14px;color: #4a940d " value="退出"/>
							</a>
						</td>
						
					</tr>
				</table>
			</div>
		</div>
		<!--"head"end-->

		<div class="content">
			<div class="left">
				<div style="margin-left:2px;margin-top: 4px;margin-bottom: -6px ">
					<img src="${path}/images/left-top.gif" width="163" height="25" />
				</div>
				<div class="left-bottom">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<div class="zTreeDemoBackground left" style="height: 550px">
							<ul id="treeDemo" class="ztree" style="height: 500px"></ul>
						</div>
					</table>
				</div>
				<!--"left-bottom"end-->
			</div>
			<!--"left"end-->

			<iframe id="frame-contect" src="${path }/erp_context"
				style="width:848px;float:right;height:530px" scrolling="no"
				name="main" frameborder="0"></iframe>
			<!--"content-right"end-->
		</div>
		<!--"content"end-->
		<div class="footer">
			<div style="margin-top:5px;">
				<table width="98%" border="0" cellpadding="0" cellspacing="0"
					align="center">
					<tr>
						<td width="82%"><img src="${path}/images/icon_1.gif" />&nbsp; <a
							class="lanyo" href="http://www.zsc.edu.cn/">电子科技大学中山学院</a></td>
					</tr>
				</table>
			</div>

		</div>
		<!--"footer"end-->
	</div>
	
</body>
</html>
