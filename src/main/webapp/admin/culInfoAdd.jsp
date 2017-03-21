<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<script type="text/javascript">
	$(function() {
		$('#admin_culInfoAdd_combogrid_mrHouse').combogrid({
			multiple : false,
			required:true,
			nowrap : false,
			url : '${pageContext.request.contextPath}/mrHouseAction!combogrid.action',
			panelWidth : 450,
			panelHeight : 200,
			idField : 'id',
			textField : 'mrHouseName',
			pagination : true,
			fitColumns : true,
			rownumbers : true,
			editable : false,
			mode : 'remote',
			delay : 500,
			pageSize : 5,
			pageList : [ 5, 10 ],
			columns : [ [ {
				field : 'id',
				title : '编号',
				width : 150,
				hidden : true
			}, {
				field : 'mrHouseName',
				title : '菇房名称',
				width : 150
			}] ]
		});
		$('#admin_culInfoAdd_combogrid_mrVariety').combogrid({
			multiple : false,
			editable:false,
			required:true,
			nowrap : false,
			url : '${pageContext.request.contextPath}/mrVarietyAction!combogrid.action',
			panelWidth : 450,
			panelHeight : 200,
			idField : 'id',
			textField : 'mushroomname',
			pagination : true,
			fitColumns : true,
			rownumbers : true,
			editable : false,
			mode : 'remote',
			delay : 500,
			pageSize : 5,
			pageList : [ 5, 10 ],
			columns : [ [ {
				field : 'id',
				title : '编号',
				width : 150,
				hidden : true
			}, {
				field : 'mushroomname',
				title : '食用菌品种名称',
				width : 150
			}, {
				title : '栽培时长',
				field : 'culduration',
				width : 150
			}] ]
		});
	});
</script>
<form id="admin_culinfoAdd_addForm" method="post">
		<table>
			<tr>
				<th>菇房名称：</th>
			<td><input id="admin_culInfoAdd_combogrid_mrHouse" name="mrHouseIds" style="width: 150px;" />
			</tr>
			<tr>
				<th>食用菌品种名称：</th>
			<td><input id="admin_culInfoAdd_combogrid_mrVariety" name="mrvarietyIds" style="width: 150px;" />
			</tr>
			<tr>
				<th>负责人：</th>
				<td><input name="chargeman" class="easyui-validatebox"
					data-options="required:true, missingMessage:'该项为必填项！'" /></td>
			</tr>
			<tr>
				<th>栽培开始时间：</th>
				<td><input name="culstarttime" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="easyui-validatebox"
					data-options="required:true, missingMessage:'该项为必填项！'"/></td>
			</tr>
		</table>
	</form>