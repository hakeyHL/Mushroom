<%@ page language="java" pageEncoding="UTF-8"%>
<form id="admin_userAdd_addForm" method="post">
		<table>
			<tr>
				<!--  <th>编号：</th>
				<td><input name="id" readonly="readonly" /></td>-->
				<th>用户名称：</th>
				<td><input name="name" class="easyui-validatebox"
					data-options="required:true, missingMessage:'请填写用戶名称！'" /></td>

				<th>密码：</th>
				<td><input name="pwd" type="password"
					class="easyui-validatebox"
					data-options="required:true, missingMessage:'请填写密码！'" /></td>
			</tr>
			<tr>
				<th>真实姓名：</th>
				<td><input name="truename" /></td>

				<th>电话：</th>
				<td><input name="tel" /></td>
			</tr>
			<tr>
				<th>用户类型：</th>
				<td><select class="easyui-combobox" name="usertype" data-options="required:true">
						<option value="管理员">管理员</option>
						<option value="员工" selected>员工</option>
						<!--  <input name="usertype" />-->
				</select></td>
			</tr>
			<!--  <tr>
				<th>创建时间：</th>
				<td><input name="createdatetime" readonly="readonly" /></td>
				<th>最后修改时间：</th>
				<td><input name="modifydatetime" readonly="readonly" /></td>
			</tr>-->
		</table>
	</form>
