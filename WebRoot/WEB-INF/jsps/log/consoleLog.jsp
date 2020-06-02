<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../taglibs.jsp" %>
<script type="text/javascript">
	
</script>
<div class="content-right">

	<div class="content-text">
		<div class="square-order">
			<table id="order" width="100%" border="1" cellpadding="0" cellspacing="0">
				<tr align="center"
					style="background:url(${path}/images/table_bg.gif) repeat-x;">
					<td width="25%" height="30">审核人</td>
					<td width="25%">审核时间</td>
					<td width="50%">备注</td>
				</tr>
				<s:iterator value="#consoleLogList" var="log">
					<tr align="center" style="size: 30px" bgcolor="#FFFFFF"> 
						<td height="30"><s:property value="#log.emp.name"/></td>
						<td><s:property value="#log.optTime"/></td>
						<td><s:property value="#log.note"/></td>
					</tr>
				</s:iterator>
			</table>
			
		</div>
	</div>
</div>
