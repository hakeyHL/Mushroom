<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	//var timer=null;
	$(function() {
		$('#admin_culinfo_datagrid').datagrid({
							url : '${pageContext.request.contextPath}/culinfoAction!datagrid.action',
							fit : true,
							border : false,
							pagination : true,
							idField : 'id',
							pageSize : 10,
							pageList : [ 10, 20, 30, 40, 50 ],
							sortName : 'culstarttime',
							sortOrder : 'asc',
							checkOnSelect : false,
							selectOnCheck : false,
							frozenColumns : [ [ {
								field : 'id',
								title : '编号',
								width : 150,
								//hidden : true
								checkbox : true
							}, {
								field : 'mrHouseIds',
								title : '生长条件ID',
								width : 150,
								hidden : true
							}, {
								field : 'mrHouseNames',
								title : '菇房名称',
								width : 150
							} ] ],
							columns : [ [{
								field : 'mrvarietyIds',
								title : '生长条件ID',
								width : 150,
								hidden : true
							}, {
								field : 'mrvarietyNames',
								title : '食用菌品种名称',
								width : 150
							},{
								field : 'chargeman',
								title : '负责人',
								width : 150
							}, {
								field : 'culstate',
								title : '栽培状态',
								width : 150
							},{
								field : 'culstarttime',
								title : '栽培开始时间',
								width : 150,
							}, {
								field : 'culendtime',
								title : '栽培结束时间',
								width : 150
							}] ],
							toolbar : [ {
								text : '增加',
								iconCls : 'icon-add',
								handler : function() {
									append_culinfoFun();
								}
							}, '-', {
								text : '刪除',
								iconCls : 'icon-remove',
								handler : function() {
									remove_culinfoFun();
								}
							}, '-', {
								text : '编辑',
								iconCls : 'icon-edit',
								handler : function() {
									end_culinfoFun();
								}
							} , '-', {
								text : '开始栽培',
								iconCls : 'icon-edit',
								handler : function() {
									start_culinfoFun();
								}
							}]
						});
	});
	function start_culinfoFun() {
		//$.ajax({
		//	url : '${pageContext.request.contextPath}/timerAction!timerStart.action'
		//});
		/*function show(){
			$.ajax({
				url : '${pageContext.request.contextPath}/timerAction!timerStart.action'
			});
			   //alert("ready");
			}*/
		//timer=setInterval(show,3000);
		//alert("2222222");
		$.ajax({
			url : '${pageContext.request.contextPath}/timerAction!timerStart.action',
			success : function(r) {
				var obj = jQuery
				.parseJSON(r);
				$.messager.show({
					title : '提示',
					msg : obj.msg
				});
			}
		});
		//$.ajax({
			//url : '${pageContext.request.contextPath}/timerAction!timerStart.action',
			//data : {
			//	ids : ids.join(','),
			//	states:states.join(',')
			//},
			
		//});
	}
	function end_culinfoFun() {
		//clearInterval(timer);
		//alert("2222222");
		$.ajax({
			url : '${pageContext.request.contextPath}/timerAction!timerStart.action',
			success : function(r) {
				var obj = jQuery
				.parseJSON(r);
				$.messager.show({
					title : '提示',
					msg : obj.msg
				});
			}
			
		});
	}
	function edit_culinfoFun() {
		var rows = $('#admin_culinfo_datagrid').datagrid('getChecked');
		//console.info(rows.length);
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
												.form('submit',
														{
															url : '${pageContext.request.contextPath}/userAction!edit.action',
															success : function(
																	r) {
																var obj = jQuery
																		.parseJSON(r);
																if (obj.success) {
																	d.dialog('close');
																	$('#admin_culinfo_datagrid')
																			.datagrid('updateRow',
																					{
																						index : $(
																								'#admin_culinfo_datagrid')
																								.datagrid(
																										'getRowIndex',
																										rows[0].id),
																						row : obj.obj
																					});
																	$('#admin_culinfo_datagrid')
																			.datagrid(
																					'uncheckAll')
																			.datagrid(
																					'unselectAll')
																			.datagrid(
																					'clearSelections');
																	//$('#admin_culinfo_datagrid').datagrid('reload');
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
		//$('#admin_culinfo_datagrid').datagrid('load', {
		//	name : $('#admin_variety_layout input[name=name]').val()
		//});
		//通过表单向后台传值
		//console.info(serializeObject($('#admin_variety_searchForm')));
		$('#admin_culinfo_datagrid').datagrid('load',
				serializeObject($('#admin_variety_searchForm')));
	}
	function append_culinfoFun() {
		$('#admin_culinfo_datagrid').datagrid('uncheckAll').datagrid(
				'unselectAll').datagrid('clearSelections');
		$('<div></div>').dialog ({
							id : 'addCulinfoDialog',
							href : '${pageContext.request.contextPath}/admin/culInfoAdd.jsp',
							width : 280,
							height : 200,
							modal : true,
							title : '添加食用菌栽培信息',
							buttons : [ {
								text : '增加',
								iconCls : 'icon-add',
								handler : function() {
									//var d = $(this).closest('.window-body');
									$('#admin_culinfoAdd_addForm')
											.form(
													'submit',
													{
														url : '${pageContext.request.contextPath}/culinfoAction!add.action',
														success : function(
																result) {
															try {
																var r = $.parseJSON(result);
																if (r.success) {
																	$('#admin_culinfo_datagrid')
																			.datagrid(
																					'insertRow',
																					{
																						index : 0,
																						row : r.obj
																					});
																	$('#addCulinfoDialog').dialog('destroy');
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
								$('#addCulinfoDialog').dialog('destroy');
							}
						});
	}
	function remove_culinfoFun() {
		var rows = $('#admin_culinfo_datagrid').datagrid('getChecked');
		//console.debug("ssssssss");
		//alert(rows[0].culstate);
		var ids = [];
		var states = [];
		if (rows.length > 0) {
			$.messager.confirm('确认','您是否要删除当前选中的数据？',
							function(r) {
								if (r) {
									for (var i = 0; i < rows.length; i++) {
										ids.push(rows[i].id);
										states.push(rows[i].culstate);
									}
									$.ajax({
												url : '${pageContext.request.contextPath}/culinfoAction!remove.action',
												data : {
													ids : ids.join(','),
													states:states.join(',')
												},
												dataType : 'json',
												success : function(r) {
													$('#admin_culinfo_datagrid').datagrid('load');
													$('#admin_culinfo_datagrid').datagrid(
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

<div id="admin_culinfo_layout" class="easyui-layout"
	data-options="fit:true,border:false">
	<div data-options="region:'center',border:false">
		<table id="admin_culinfo_datagrid">
		</table>
	</div>
</div>



