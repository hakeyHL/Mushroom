<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<script type="text/javascript">
	$(function() {
		$('#admin_mtHouseAdd_combogrid_collector').combogrid({
			multiple : true,
			required:true,
			nowrap : false,
			url : '${pageContext.request.contextPath}/collectorAction!combogrid.action',
			panelWidth : 450,
			panelHeight : 200,
			idField : 'id',
			textField : 'collectorName',
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
				field : 'collectorName',
				title : '采集设备名称',
				width : 150
			}, {
				title : '采集设备Ip',
				field : 'collectorIp',
				width : 300,
			}] ]
		});
		$('#admin_mtHouseAdd_combogrid_monitor').combogrid({
			multiple : true,
			required:true,
			nowrap : false,
			url : '${pageContext.request.contextPath}/monitorAction!combogrid.action',
			panelWidth : 450,
			panelHeight : 200,
			idField : 'id',
			textField : 'monitorName',
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
				field : 'monitorName',
				title : '监控设备名称',
				width : 150
			}, {
				title : '监控设备Ip',
				field : 'monitorIp',
				width : 300,
			}] ]
		});
	});
</script>
<form id="admin_mrHouseAdd_addForm" method="post">
		<table>
			<tr>
				<!--  <th>编号：</th>
				<td><input name="id" readonly="readonly" /></td>-->
				<th>菇房名称：</th>
				<td><input name="mrHouseName" class="easyui-validatebox"
					data-options="required:true, missingMessage:'请填写监控设备名称！'" /></td>
			</tr>
			<tr>
				<th>采集设备：</th>
			<td><input id="admin_mtHouseAdd_combogrid_collector" name="collectorIds" style="width: 200px;" />
			</tr>
			<tr>
				<th>监控设备：</th>
			<td><input id="admin_mtHouseAdd_combogrid_monitor" name="monitorIds" style="width: 200px;" />
			</tr>
		</table>
	</form>