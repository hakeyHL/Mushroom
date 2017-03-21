<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	$('#admin_mrVarietyM_datagrid').datagrid({
		url : '${pageContext.request.contextPath}/mrVarietyAction!datagrid.action',
		fit : true,
		//fitColumns:true,
		border : false,
		pagination : true,
		idField : 'id',
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50 ],
		//翻页条放的位置默认下方
		//pagePosition:'both',
		sortName : 'mushroomname',
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
			field : 'mushroomname',
			title : '食用菌品种名称',
			width : 150,
			sortable : true
		},{
			field : 'culduration',
			title : '栽培时长（天）',
			width : 150,
		}] ],
		toolbar : [ {
			text : '增加',
			iconCls : 'icon-add',
			handler : function() {
				append_mrVarietyFun();
			}
		}, '-', {
			text : '刪除',
			iconCls : 'icon-remove',
			handler : function() {
				remove_mrVarietyFun();
			}
		}
		//, '-', {
		//	text : '编辑',
		//	iconCls : 'icon-edit',
		//	handler : function() {
		//		editFun();
		//	}
		//} 
		]
		

	});
	
	function edit_mrVarietyFun() {
		var rows = $('#admin_collectorM_datagrid').datagrid('getChecked');
		//console.info(rows.length);
		//alert('2');
		if (rows.length == 1) {
			var d = $('<div/>').dialog({
								height : 200,
								width : 500,
								href : '${pageContext.request.contextPath}/admin/collectMEdit.jsp',
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
																	d.dialog('close');
																	$('#admin_collectorM_datagrid')
																			.datagrid(
																					'updateRow',
																					{
																						index : $(
																								'#admin_collectorM_datagrid')
																								.datagrid(
																										'getRowIndex',
																										rows[0].id),
																						row : obj.obj
																					});
																	$('#admin_collectorM_datagrid').datagrid('uncheckAll').datagrid('unselectAll').datagrid('clearSelections');
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
									$('#admin_userMEdit_form').form('load',rows[0]);
								}
							});
		} else {
			$.messager.show({
				title : '提示',
				msg : '请勾选（单条）编辑数据！'
			});
		}
	}
	function append_mrVarietyFun() {
		$('#admin_mrVarietyM_datagrid').datagrid('uncheckAll').datagrid(
		'unselectAll').datagrid('clearSelections');
			$('<div></div>').dialog ({
					id : 'addMrVarietyDialog',
					href : '${pageContext.request.contextPath}/admin/mrVarietyAdd.jsp',
					width : 270,
					height : 150,
					modal : true,
					title : '添加食用菌品种',
					buttons : [ {
						text : '增加',
						iconCls : 'icon-add',
						handler : function() {
							//var d = $(this).closest('.window-body');
							$('#admin_mrVarietyAdd_addForm')
									.form(
											'submit',
											{
												url : '${pageContext.request.contextPath}/mrVarietyAction!add.action',
												success : function(
														result) {
													try {
														var r = $.parseJSON(result);
														if (r.success) {
															$('#admin_mrVarietyM_datagrid')
																	.datagrid(
																			'insertRow',
																			{
																				index : 0,
																				row : r.obj
																			});
															$('#addMrVarietyDialog').dialog('destroy');
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
						$('#addMrVarietyDialog').dialog('destory');
					}
				});
	}
	function remove_mrVarietyFun() {
		var rows = $('#admin_mrVarietyM_datagrid').datagrid('getChecked');
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
												url : '${pageContext.request.contextPath}/mrVarietyAction!remove.action',
												data : {
													ids : ids.join(','),
													names:names.join(',')
												},
												dataType : 'json',
												success : function(r) {
													$('#admin_mrVarietyM_datagrid')
															.datagrid('load');
													$('#admin_mrVarietyM_datagrid').datagrid('uncheckAll').datagrid('unselectAll').datagrid('clearSelections');
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

<div id="admin_mrVarietyM_layout" class="easyui-layout"
	data-options="fit:true,border:false">
	<div data-options="region:'center',border:false">
		<table id="admin_mrVarietyM_datagrid">
		</table>
	</div>
</div>

