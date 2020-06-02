<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../taglibs.jsp" %>
<link rel="stylesheet" href="${path }/js/ztree/css/demo.css" type="text/css">
<link rel="stylesheet" href="${path }/js/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript">
	var tree = "";
	$(function(){
		var setting = {
				check: {
					enable: true
				},
				data: {
					simpleData: {
						enable: true
					}
				},
			};
			var zNodes = ${zNodes};
			tree = $.fn.zTree.init($("#treeDemo"), setting, zNodes);
	});
	function getPerms(roleId){
		//获取到勾选的对象
		var nodes = tree.getCheckedNodes(true);
		//获取到勾选到对象的id
		var permIds = "";
		for(var i = 0;i < nodes.length; i++){
			permIds = permIds + nodes[i].id + ",";
		}
		var result = getGranRole(roleId,permIds);
		return result;
	}
	function getGranRole(roleId,permIds){
		var result = "";
		$.ajax({
			url:"${path}/ajax_role_grantrole",
			type:"post",
			data:{
				"query.roleId":roleId,
				"permIds":permIds
			},
			async:false,
			dataType:"text",
			success:function(responseText){
				result = responseText;
				//alert(result)
			}
		})
		return result;
		//alert(result)
	}
	
</script>
<div>
	<div class="">
		<div class="zTreeDemoBackground left" style="height: 450px">
			<ul id="treeDemo" class="ztree"></ul>
		</div>
	</div>
	
</div>
