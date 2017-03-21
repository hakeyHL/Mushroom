<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="easyui-panel"
	data-options="title:'导航区',border:false,fit:true">
	<div class="easyui-accordion" data-options="fit:true,border:false">
		<div title="功能菜單" data-options="iconCls:'icon-save'">
			<ul class="easyui-tree"
				data-options="url:'${pageContext.request.contextPath}/menuAction!getAllTreeNode.action',
			   		parentField:'pid',
			   		lines:true,
			   		onClick:function(node){
			   			if(node.attributes.url){
				   			var url='${pageContext.request.contextPath}'+node.attributes.url;
				   			addTab({title:node.text,href:url,closable:true});
			   			}
			   		}"></ul>
		</div>
		<!--  <div title="Title2" data-options="iconCls:'icon-reload'"></div>-->
	</div>
</div>