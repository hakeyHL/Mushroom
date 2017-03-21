<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	//$('#admin_userM_datagrid').treegrid('reload');
	$('#admin_userM_datagrid').datagrid({
		url : '${pageContext.request.contextPath}/userAction!datagrid.action',
		fit : true,
		//fitColumns:true,
		border : false,
		pagination : true,
		idField : 'id',
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50 ],
		//翻页条放的位置默认下方
		//pagePosition:'both',
		sortName : 'name',
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
			field : 'name',
			title : '用户名称',
			width : 150,
			sortable : true
		} ] ],
		columns : [ [ {
			field : 'pwd',
			title : '密码',
			width : 150,
			hidden : true,
			//悬停提示看不全的内容
			formatter : function(value, row, index) {
				return '**********';
				//return '<span title="'+value+'">' + value + '</span>';
			}
		}, {
			field : 'truename',
			title : '姓名',
			width : 150,
			sortable : true
		}, {
			field : 'usertype',
			title : '用户类型',
			width : 150,
			sortable : true
		}, {
			field : 'tel',
			title : '电话',
			width : 150
		}, {
			field : 'createdatetime',
			title : '创建时间',
			width : 150,
			sortable : true
		}, {
			field : 'modifydatetime',
			title : '最后修改时间',
			width : 150,
			sortable : true
		} ] ],
		toolbar : [ {
			text : '增加',
			iconCls : 'icon-add',
			handler : function() {
				append_userFun();
			}
		}, '-', {
			text : '刪除',
			iconCls : 'icon-remove',
			handler : function() {
				remove_userFun();
			}
		}, '-', {
			text : '编辑',
			iconCls : 'icon-edit',
			handler : function() {
				edit_userFun();
			}
		} , '-', {
			text : '重置密码',
			iconCls : 'icon-lock',
			handler : function() {
				//editFun();
				changePwdFun();
			}
		}]
		/*, '-', {
			text : '清空选择',
			iconCls : 'icon-reload',
			handler : function() {
				//editFun();
				
				$('#admin_userM_datagrid').datagrid('uncheckAll').datagrid('unselectAll').datagrid('clearSelections');
				$('#admin_userM_datagrid').datagrid('reload');
			}
		}*/ 
		

	});
	function changePwdFun(){
		var rows = $('#admin_userM_datagrid').datagrid('getChecked');
		//console.info(rows);
		//alert('2');
		if (rows.length == 1) {
			var d = $('<div/>').dialog({
						height : 160,
						width : 250,
						href : '${pageContext.request.contextPath}/admin/changePwd.jsp',
						title : '重置密码',
						modal : true,
						buttons : [ {
							text : '重置',
							handler : function() {
								$('#admin_changePwd_form')
								.form(
										'submit',
										{
											url : '${pageContext.request.contextPath}/userAction!modifyPwd.action',
											success : function(
													r) {
												var obj = jQuery
														.parseJSON(r);
												if (obj.success) {
													d.dialog('close');
													$('#admin_userM_datagrid')
															.datagrid(
																	'updateRow',
																	{
																		index : $(
																				'#admin_userM_datagrid')
																				.datagrid(
																						'getRowIndex',
																						rows[0].id),
																		row : obj.obj
																	});
													$('#admin_userM_datagrid').datagrid('uncheckAll').datagrid('unselectAll').datagrid('clearSelections');
													//$('#admin_userM_datagrid').datagrid('reload');
												}
												$.messager.show({
															title : '提示',
															msg : obj.msg
														});
											}
										});
							}
						} ],
						onClose : function() {
							$(this).dialog('destroy');
						},
						onLoad : function() {
							$('#admin_changePwd_form input[name=id]').val(rows[0].id);
							/*$('#admin_changePwd_form').form('load',{
								id : id
							});*/
						}
					});
		}else{
			$.messager.show({
				title : '提示',
				msg : '请勾选（单条）重置数据！'
			});
		}
	}
	function edit_userFun() {
		var rows = $('#admin_userM_datagrid').datagrid('getChecked');
		//console.info(rows.length);
		//alert('2');
		if (rows.length == 1) {
			var d = $('<div/>').dialog({
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
																	d.dialog('close');
																	$('#admin_userM_datagrid')
																			.datagrid(
																					'updateRow',
																					{
																						index : $(
																								'#admin_userM_datagrid')
																								.datagrid(
																										'getRowIndex',
																										rows[0].id),
																						row : obj.obj
																					});
																	$('#admin_userM_datagrid').datagrid('uncheckAll').datagrid('unselectAll').datagrid('clearSelections');
																	//$('#admin_userM_datagrid').datagrid('reload');
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
	function searchFun() {
		//$('#admin_userM_datagrid').datagrid('load', {
		//	name : $('#admin_userM_layout input[name=name]').val()
		//});
		//通过表单向后台传值
		//console.info(serializeObject($('#admin_userM_searchForm')));
		$('#admin_userM_datagrid').datagrid('load',
				serializeObject($('#admin_userM_searchForm')));
	}
	function clearFun() {
		$('#admin_userM_layout input[name=name]').val('');
		$('#admin_userM_datagrid').datagrid('load', {});
	}
	function append_userFun() {
		//$('#admin_userM_addForm input').val('');
		$('#admin_userM_datagrid').datagrid('uncheckAll').datagrid('unselectAll').datagrid('clearSelections');
		var d =$('<div/>').dialog({
			href : '${pageContext.request.contextPath}/admin/userAdd.jsp',
			width : 420,
			height : 200,
			modal : true,
			title : '添加用户',
			buttons : [ {
				text : '增加',
				iconCls : 'icon-add',
				handler : function() {
					//var d = $(this).closest('.window-body');
					$('#admin_userAdd_addForm').form('submit', {
						url : '${pageContext.request.contextPath}/userAction!add.action',
						success : function(result) {
							try {
								var r = $.parseJSON(result);
								if (r.success) {
									d.dialog('close');
									$('#admin_userM_datagrid').datagrid('insertRow', {
										index : 0,
										row : r.obj
									});
									//d.dialog('destroy');
								}
								$.messager.show({
									title : '提示',
									msg : r.msg
								});
							} catch (e) {
								$.messager.alert('提示', result);
							}
						}
					});
				}
			} ],
			onClose : function() {
				$(this).dialog('destroy');
			}
		});
	}
	function remove_userFun() {
		var rows = $('#admin_userM_datagrid').datagrid('getChecked');
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
												url : '${pageContext.request.contextPath}/userAction!remove.action',
												data : {
													ids : ids.join(','),
													names:names.join(',')
												},
												dataType : 'json',
												success : function(r) {
													$('#admin_userM_datagrid')
															.datagrid('load');
													$('#admin_userM_datagrid').datagrid('uncheckAll').datagrid('unselectAll').datagrid('clearSelections');
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

<div id="admin_userM_layout" class="easyui-layout"
	data-options="fit:true,border:false">
	<div data-options="region:'north',border:false,title:'查询'"
		style="height:55px;">
		<form id="admin_userM_searchForm">
			用户名称(包含)：<input name="name" /> <a href="#" class="easyui-linkbutton"
				data-options="iconCls:'icon-search',plain:true"
				onclick="searchFun();">查询</a> <a href="#" class="easyui-linkbutton"
				data-options="iconCls:'icon-search',plain:true"
				onclick="clearFun();">查询所有</a>
		</form>
	</div>
	<div data-options="region:'center',border:false">
		<table id="admin_userM_datagrid">
		</table>
	</div>
</div>

