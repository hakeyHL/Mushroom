<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	$(function() {
		$('#user_reg_regForm').form({
			url : '${pageContext.request.contextPath}/userAction!reg.action',
			success : function(r) {
				var obj = jQuery.parseJSON(r);
				if (obj.success) {
					$('#user_reg_regDialog').dialog('close');
				}
				$.messager.show({
					title : '提示',
					msg : obj.msg
				});
			}
		});
		$('#user_reg_regForm input').bind('keyup', function(event) {/* 增加回车提交功能 */
			if (event.keyCode == '13') {
				$('#user_reg_regForm').submit();
			}
		});
	});
</script>
<!-- 注册框 -->
<div id="user_reg_regDialog" class="easyui-dialog"
	data-options="title:'注册',modal:true,closed:true,buttons:[{
				text:'注册',
				iconCls:'icon-ok',
				handler:function(){
					$('#user_reg_regForm').submit();
				}
			}]">
	<form id="user_reg_regForm" method="post">
		<table>
			<tr>
				<th>登录名：</th>
				<td><input name="name" class="easyui-validatebox"
					data-options="required:true, missingMessage:'请填写登录名称！'" /></td>
			</tr>
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
					data-options="required:true, missingMessage:'请填写确认密码！',validType:'eqPwd[\'#user_reg_regForm input[name=pwd]\']' " /></td>
			</tr>
		</table>
	</form>
</div>