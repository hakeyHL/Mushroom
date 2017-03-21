<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div>
	<form id="admin_changePwd_form" method="post">
		<table>
			
			<tr>
				<th>密码：</th>
				<td><input name="pwd" type="password"
					class="easyui-validatebox"
					data-options="required:true, missingMessage:'请填写密码！'" /></td>
			</tr>
			<tr>
				<th>确认密码：</th>
				<td><input name="rePwd" type="password"
					class="easyui-validatebox"
					data-options="required:true, missingMessage:'请填写确认密码！',validType:'eqPwd[\'#admin_changePwd_form input[name=pwd]\']' " /></td>
			</tr>
			<tr style="visibility:hidden">
				<th>编号</th>
				<td><input name="id" readonly="readonly" /></td>
			<tr>
		</table>
	</form>
</div>


