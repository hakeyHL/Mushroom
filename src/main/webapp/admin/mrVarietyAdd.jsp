<%@ page language="java" pageEncoding="UTF-8"%>
<form id="admin_mrVarietyAdd_addForm" method="post">
		<table>
			<tr>
				<!--  <th>编号：</th>
				<td><input name="id" readonly="readonly" /></td>-->
				<th>食用菌品种名称：</th>
				<td><input name="mushroomname" class="easyui-validatebox"
					data-options="required:true, missingMessage:'请填写食用菌名称！'" /></td>
			</tr>
			<tr>
				<th>栽培时长（天）：</th>
				<td><input name="culduration" 
					class="easyui-validatebox"
					data-options="required:true,validType:['Number','length[1,2]'], missingMessage:'请填写栽培时长！'" /></td>
			</tr>
		</table>
</form>
