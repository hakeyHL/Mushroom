<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	$(function() {
		$('#user_login_logForm').form({
			url : '${pageContext.request.contextPath}/userAction!login.action',
			success : function(r) {
				var obj = jQuery.parseJSON(r);
				if (obj.success) {
					$('#user_login_loginDialog').dialog('close');
				}
				$.messager.show({
					title : '提示',
					msg : obj.msg
				});
			}
		});
		$('#user_login_logForm input').bind('keyup', function(event) {/* 增加回车提交功能 */
			if (event.keyCode == '13') {
				$('#user_login_logForm').submit();
			}
		});
		//让ie与其他浏览器一样获得焦点
		window.setTimeout(function(){
			$('#user_login_logForm input[name=name]').focus();
		},0);
	});
</script>
<!-- 登录框 -->
	<div id="user_login_loginDialog" class="easyui-dialog" data-options="title:'登录',modal:true,closable:false,buttons:[{
				text:'登录',
				iconCls:'icon-ok',
				handler:function(){
					$('#user_login_logForm').submit();
				}
			},{
				text:'取消',
				iconCls:'icon-cancel',
				handler:function(){ 
					$('#user_login_logForm input').val('');
				}
			},{
				text:'注册',
				iconCls:'icon-add',
				handler:function(){ 
					$('#user_reg_regDialog input').val('');
					$('#user_reg_regDialog').dialog('open');
				}
			}]">
		<form id="user_login_logForm" method="post">
			<table>
				<tr>
					<th>登录名：</th>
					<td><input name="name" class="easyui-validatebox" data-options="required:true, missingMessage:'请填写登录名称！'"/></td>
				</tr>	
				<tr>
					<th>密码：</th>
					<td><input name="pwd" type="password" class="easyui-validatebox" data-options="required:true, missingMessage:'请填写登录密码！'"/></td>
				</tr>	
			</table>
		</form>	
	</div>