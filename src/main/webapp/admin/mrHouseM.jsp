<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	$(function() {
		$('#admin_mrHouseM_datagrid').datagrid({
							url : '${pageContext.request.contextPath}/mrHouseAction!datagrid.action',
							fit : true,
							//fitColumns:true,
							border : false,
							pagination : true,
							idField : 'id',
							pageSize : 10,
							pageList : [ 10, 20, 30, 40, 50 ],
							//翻页条放的位置默认下方
							//pagePosition:'both',
							sortName : 'mrHouseName',
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
								field : 'mrHouseName',
								title : '菇房名称',
								width : 150,
								sortable : true
							} ] ],
							columns : [ [{
								field : 'collectorIds',
								title : '采集设备ID',
								width : 150,
								hidden : true
							}, {
								field : 'collectorNames',
								title : '采集设备',
								width : 150
							},{
								field : 'monitorIds',
								title : '监控设备ID',
								width : 150,
								hidden : true
							}, {
								field : 'monitorNames',
								title : '监控设备',
								width : 150
							}] ],
							toolbar : [ {
								text : '增加',
								iconCls : 'icon-add',
								handler : function() {
									append_mrHouseFun();
								}
							}, '-', {
								text : '刪除',
								iconCls : 'icon-remove',
								handler : function() {
									remove_mrHouseFun();
								}
							//}, '-', {
							//	text : '编辑',
							//	iconCls : 'icon-edit',
							//	handler : function() {
									//edit_mrHouseFun();
							//	}
							} ]
						});
	});

	function edit_mrHouseFun() {
		var rows = $('#admin_mrHouseM_datagrid').datagrid('getChecked');
		//console.info(rows.length);
		//alert('2');
		if (rows.length == 1) {
			var d = $('<div/>')
					.dialog(
							{
								height : 200,
								width : 500,
								href : '${pageContext.request.contextPath}/admin/userMEdit.jsp',
								title : '编辑用户',
								modal : true,
								buttons : [ {
									text : '修改',
									handler : function() {
										$('#admin_userMEdit_form')
												.form(
														'submit',
														{
															url : '${pageContext.request.contextPath}/userAction!edit.action',
															success : function(
																	r) {
																var obj = jQuery
																		.parseJSON(r);
																if (obj.success) {
																	d
																			.dialog('close');
																	$(
																			'#admin_mrHouseM_datagrid')
																			.datagrid(
																					'updateRow',
																					{
																						index : $(
																								'#admin_mrHouseM_datagrid')
																								.datagrid(
																										'getRowIndex',
																										rows[0].id),
																						row : obj.obj
																					});
																	$(
																			'#admin_mrHouseM_datagrid')
																			.datagrid(
																					'uncheckAll')
																			.datagrid(
																					'unselectAll')
																			.datagrid(
																					'clearSelections');
																	//$('#admin_mrHouseM_datagrid').datagrid('reload');
																}
																$.messager
																		.show({
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
									$('#admin_userMEdit_form').form('load',
											rows[0]);
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
		//$('#admin_mrHouseM_datagrid').datagrid('load', {
		//	name : $('#admin_mrHouse_layout input[name=name]').val()
		//});
		//通过表单向后台传值
		//console.info(serializeObject($('#admin_mrHouseM_searchForm')));
		$('#admin_mrHouseM_datagrid').datagrid('load',
				serializeObject($('#admin_mrHouseM_searchForm')));
	}
	function append_mrHouseFun() {
		//$('#admin_userM_addForm input').val('');
		$('#admin_mrHouseM_datagrid').datagrid('uncheckAll').datagrid(
				'unselectAll').datagrid('clearSelections');
		$('<div></div>').dialog ({
							id : 'addMrHouseDialog',
							href : '${pageContext.request.contextPath}/admin/mrHouseAdd.jsp',
							width : 300,
							height : 370,
							modal : true,
							title : '添加菇房',
							buttons : [ {
								text : '增加',
								iconCls : 'icon-add',
								handler : function() {
									//var d = $(this).closest('.window-body');
									$('#admin_mrHouseAdd_addForm')
											.form(
													'submit',
													{
														url : '${pageContext.request.contextPath}/mrHouseAction!add.action',
														success : function(
																result) {
															try {
																var r = $.parseJSON(result);
																if (r.success) {
																	$('#admin_mrHouseM_datagrid')
																			.datagrid(
																					'insertRow',
																					{
																						index : 0,
																						row : r.obj
																					});
																	$('#addMrHouseDialog').dialog('destroy');
																	//d.dialog('destroy');
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
								$('#addMrHouseDialog').dialog('destroy');
							}
						});
	}
	function remove_mrHouseFun() {
		var rows = $('#admin_mrHouseM_datagrid').datagrid('getChecked');
		//console.info(rows);
		var ids = [];
		var names = [];
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
												url : '${pageContext.request.contextPath}/mrHouseAction!remove.action',
												data : {
													ids : ids.join(','),
													names : names.join(',')
												},
												dataType : 'json',
												success : function(r) {
													$(
															'#admin_mrHouseM_datagrid')
															.datagrid('load');
													$(
															'#admin_mrHouseM_datagrid')
															.datagrid(
																	'uncheckAll')
															.datagrid(
																	'unselectAll')
															.datagrid(
																	'clearSelections');
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

<div id="admin_mrHouse_layout" class="easyui-layout"
	data-options="fit:true,border:false">
	<div data-options="region:'north',border:false,title:'查询'"
		style="height:55px;">
		<form id="admin_mrHouseM_searchForm">
			菇房名称(包含)：<input name="name" /> <a href="#" class="easyui-linkbutton"
				data-options="iconCls:'icon-search',plain:true"
				onclick="searchFun();">查询</a> <a href="#" class="easyui-linkbutton"
				data-options="iconCls:'icon-search',plain:true"
				onclick="clearFun();">查询所有</a>
		</form>
	</div>
	<div data-options="region:'center',border:false">
		<table id="admin_mrHouseM_datagrid">
		</table>
	</div>
</div>



