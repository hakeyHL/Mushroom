<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	$('#admin_monitorM_datagrid').datagrid({
		url : '${pageContext.request.contextPath}/monitorAction!datagrid.action',
		fit : true,
		//fitColumns:true,
		border : false,
		pagination : true,
		idField : 'id',
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50 ],
		//翻页条放的位置默认下方
		//pagePosition:'both',
		sortName : 'monitorName',
		sortOrder : 'asc',
		checkOnSelect : false,
		selectOnCheck : false,
		//显示序号
		//rownumbers:true,
		frozenColumns : [ [ {
			field : 'id',
			title : '编号',
			width : 150,
			//hidden : true
			checkbox : true
		}, {
			field : 'monitorName',
			title : '监控设备名称',
			width : 150,
			sortable : true
		} ] ],
		columns : [ [ {
			field : 'monitorIp',
			title : '监控设备IP',
			width : 150,
		}] ],
		toolbar : [ {
			text : '增加',
			iconCls : 'icon-add',
			handler : function() {
				append_monitorFun();
			}
		}, '-', {
			text : '刪除',
			iconCls : 'icon-remove',
			handler : function() {
				remove_monitorFun();
			}
		} ]
		

	});
	
	function edit_monitorFun() {
		var rows = $('#admin_monitorM_datagrid').datagrid('getChecked');
		//console.info(rows.length);
		//alert('2');
		if (rows.length == 1) {
			var d = $('<div/>').dialog({
								height : 200,
								width : 500,
								href : '${pageContext.request.contextPath}/admin/monitorEdit.jsp',
								title : '编辑用户',
								modal : true,
								buttons : [ {
									text : '修改',
									handler : function() {
										$('#admin_monitorEdit_form')
												.form(
														'submit',
														{
															url : '${pageContext.request.contextPath}/monitorAction!edit.action',
															success : function(
																	r) {
																var obj = jQuery
																		.parseJSON(r);
																if (obj.success) {
																	d.dialog('close');
																	$('#admin_monitorM_datagrid')
																			.datagrid(
																					'updateRow',
																					{
																						index : $(
																								'#admin_monitorM_datagrid')
																								.datagrid(
																										'getRowIndex',
																										rows[0].id),
																						row : obj.obj
																					});
																	$('#admin_monitorM_datagrid').datagrid('uncheckAll').datagrid('unselectAll').datagrid('clearSelections');
																	//$('#admin_collectorM_datagrid').datagrid('reload');
																}
																$.messager.show({
																			title : '提示',
																			msg : obj.msg
																		});
															}
														});
										//console.info(rows.length);
									}
								} ],
								onClose : function() {
									//alert('2');
									//console.info(rows);
									$(this).dialog('destroy');
								},
								onLoad : function() {
									//alert('3');
									//console.info(rows);
									//$('#admin_userMEdit_form input[name=id]').val(rows[0].id);
									$('#admin_monitorEdit_form').form('load',rows[0]);
								}
							});
		} else {
			$.messager.show({
				title : '提示',
				msg : '请勾选（单条）编辑数据！'
			});
		}
	}
	function searchFun() {
		//$('#admin_collectorM_datagrid').datagrid('load', {
		//	name : $('#admin_userM_layout input[name=name]').val()
		//});
		//通过表单向后台传值
		//console.info(serializeObject($('#admin_userM_searchForm')));
		$('#admin_monitorM_datagrid').datagrid('load',
				serializeObject($('#admin_monitorM_searchForm')));
	}
	function append_monitorFun() {
		$('#admin_monitorM_datagrid').datagrid('uncheckAll').datagrid(
		'unselectAll').datagrid('clearSelections');
			$('<div></div>').dialog ({
					id : 'addMonitorDialog',
					href : '${pageContext.request.contextPath}/admin/monitorAdd.jsp',
					width : 270,
					height : 150,
					modal : true,
					title : '添加监控设备',
					buttons : [ {
						text : '增加',
						iconCls : 'icon-add',
						handler : function() {
							//var d = $(this).closest('.window-body');
							$('#admin_monitorAdd_addForm')
									.form(
											'submit',
											{
												url : '${pageContext.request.contextPath}/monitorAction!add.action',
												success : function(
														result) {
													try {
														var r = $.parseJSON(result);
														if (r.success) {
															$('#admin_monitorM_datagrid')
																	.datagrid(
																			'insertRow',
																			{
																				index : 0,
																				row : r.obj
																			});
															$('#addMonitorDialog').dialog('destroy');
														}
														$.messager
																.show({
																	title : '提示',
																	msg : r.msg
																});
													} catch (e) {
														$.messager
																.alert(
																		'提示',
																		result);
													}
												}
											});
						}
					} ],
					onClose : function() {
						$(this).dialog('destory');
					}
				});
	}
	function remove_monitorFun() {
		var rows = $('#admin_monitorM_datagrid').datagrid('getChecked');
		//console.info(rows);
		var ids = [];
		var names=[];
		if (rows.length > 0) {
			$.messager
					.confirm(
							'确认',
							'您是否要删除当前选中的数据？',
							function(r) {
								if (r) {
									for (var i = 0; i < rows.length; i++) {
										ids.push(rows[i].id);
										names.push(rows[i].name);
									}
									$
											.ajax({
												url : '${pageContext.request.contextPath}/monitorAction!remove.action',
												data : {
													ids : ids.join(','),
													names:names.join(',')
												},
												dataType : 'json',
												success : function(r) {
													$('#admin_monitorM_datagrid')
															.datagrid('load');
													$('#admin_monitorM_datagrid').datagrid('uncheckAll').datagrid('unselectAll').datagrid('clearSelections');
													$.messager.show({
														title : '提示',
														msg : r.msg
													});
												}
											});
								}
							});
		} else {
			$.messager.show({
				title : '提示',
				msg : '请勾选要删除的数据！'
			});
		}
	}
</script>

<div id="admin_monitorM_layout" class="easyui-layout"
	data-options="fit:true,border:false">
	<!-- <div data-options="region:'north',border:false,title:'查询'"
		style="height:55px;">
		<form id="admin_monitorM_searchForm">
			监控设备名称(包含)：<input name="name" /> <a href="#" class="easyui-linkbutton"
				data-options="iconCls:'icon-search',plain:true"
				onclick="searchFun();">查询</a> <a href="#" class="easyui-linkbutton"
				data-options="iconCls:'icon-search',plain:true"
				onclick="clearFun();">查询所有</a>
		</form>
	</div> -->
	<div data-options="region:'center',border:false">
		<table id="admin_monitorM_datagrid">
		</table>
	</div>
</div>
<!--  
<div id="admin_monitorM_addCollectorDialog" class="easyui-dialog"
	style="width:500px;height:200px;"
	data-options="closed:true,modal:true,title:'添加用戶',buttons:[{
				text:'增加',
				iconCls:'icon-add',
				handler:function(){
					$('#admin_monitorM_addForm').form('submit',{
						url:'${pageContext.request.contextPath}/monitorAction!add.action',
						success : function(r) {
						var obj = jQuery.parseJSON(r);
						if (obj.success) {
							$('#admin_monitorM_datagrid').datagrid('insertRow',{
								index:0,
								row:obj.obj
							});
							$('#admin_monitorM_addCollectorDialog').dialog('close');
						}
						$.messager.show({
							title : '提示',
							msg : obj.msg
						});
					}});
				}
			}]">
	<form id="admin_monitorM_addForm" method="post">
		<table>
			<tr>
				<!--  <th>编号：</th>
				<td><input name="id" readonly="readonly" /></td>-->
				<th>采集设备名称：</th>
				<td><input name="monitorName" class="easyui-validatebox"
					data-options="required:true, missingMessage:'请填写采集设备名称！'" /></td>

				<th>采集设备IP：</th>
				<td><input name="monitorIp" type="text"
					class="easyui-validatebox"
					data-options="required:true, missingMessage:'请填写采集设备IP！'" /></td>
			</tr>
		</table>
	</form>
</div>
-->


