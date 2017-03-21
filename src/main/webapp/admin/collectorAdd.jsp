<%@ page language="java" pageEncoding="UTF-8"%>
<form id="admin_collectorAdd_addForm" method="post">
		<table>
			<tr>
				<!--  <th>编号：</th>
				<td><input name="id" readonly="readonly" /></td>-->
				<th>采集设备名称：</th>
				<td><input name="collectorName" class="easyui-validatebox"
					data-options="required:true, missingMessage:'请填写采集设备名称！'" /></td>
			</tr>
			<tr>
				<th>采集设备IP：</th>
				<td><input name="collectorIp" type="text"
					class="easyui-validatebox"
					data-options="required:true, missingMessage:'请填写采集设备IP！'" /></td>
			</tr>
		</table>
	</form>
