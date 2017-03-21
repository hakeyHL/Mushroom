<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div>
	<form id="admin_userMEdit_form" method="post">
		<table>
			<tr>
				<th>编号：</th>
				<td><input name="id" readonly="readonly" /></td>
				<th>用户名称：</th>
				<td><input name="name" class="easyui-validatebox"
					data-options="required:true, missingMessage:'请填写用戶名称！'" /></td>
			</tr>
			<tr>
				<th>密码：</th>
				<td><input name="pwd" type="text"
					class="easyui-validatebox"
					data-options="required:true, missingMessage:'请填写密码！'" /></td>
				<th>真实姓名：</th>
				<td><input name="truename" /></td>
			</tr>
			<tr>
				<th>电话：</th>
				<td><input name="tel" /></td>
				<th>用户类型：</th>
				<td><input name="usertype" /></td>
			</tr>
			<tr>
				<th>创建时间：</th>
				<td><input name="createdatetime" readonly="readonly" /></td>
				<th>最后修改时间：</th>
				<td><input name="modifydatetime" readonly="readonly" /></td>
			</tr>
		</table>
	</form>
</div>


