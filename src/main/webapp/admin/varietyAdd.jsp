<%@ page language="java" pageEncoding="UTF-8"%>
<script type="text/javascript">
$(function() {
		$('#admin_varietyAdd_combogrid_mrVariety').combogrid({
			multiple : false,
			required:true,
			nowrap : false,
			url : '${pageContext.request.contextPath}/mrVarietyAction!combogrid.action',
			panelWidth : 300,
			panelHeight : 200,
			idField : 'id',
			textField : 'mushroomname',
			pagination : true,
			fitColumns : true,
			rownumbers : true,
			editable : true,
			mode : 'remote',
			delay : 500,
			pageSize : 5,
			pageList : [ 5, 10 ],
			columns : [ [ {
				field : 'id',
				title : '编号',
				width : 100,
				hidden : true
			}, {
				field : 'mushroomname',
				title : '食用菌品种名称',
				width : 100
			}]]
		});
	});
</script>
<form id="admin_varietyAdd_addForm" method="post">
		<table>
			<tr>
				<!--  <th>编号：</th>
				<td><input name="id" readonly="readonly" /></td>-->
				<th>食用菌品种名称：</th>
				<td><input id="admin_varietyAdd_combogrid_mrVariety" name="mrVarietyIds" style="width: 140px;" /></td>
				<th>栽培时间点（第几天）：</th>
				<td><input name="culdays" class="easyui-validatebox" data-options="required:true,validType:'Number', missingMessage:'此输入项为必输项！'" /></td>
			</tr>
			
			<tr>
				<th>最高温度：</th>
				<td><input name="maxtemp" class="easyui-validatebox" data-options="required:true,validType:'Number', missingMessage:'此输入项为必输项！'"/></td>

				<th>最低温度：</th>
				<td><input name="mintemp" class="easyui-validatebox" data-options="required:true,validType:'Number', missingMessage:'此输入项为必输项！'"/></td>
			</tr>
			<tr>
				<th>最高湿度：</th>
				<td><input name="maxhumi" class="easyui-validatebox" data-options="required:true,validType:'Number', missingMessage:'此输入项为必输项！'"/></td>

				<th>最低湿度：</th>
				<td><input name="minhumi" class="easyui-validatebox" data-options="required:true,validType:'Number', missingMessage:'此输入项为必输项！'"/></td>
			</tr>
			<tr>
				<th>最高二氧化碳浓度：</th>
				<td><input name="maxco2" class="easyui-validatebox" data-options="required:true,validType:'Number', missingMessage:'此输入项为必输项！'"/></td>
				<th>最低二氧化碳浓度：</th>
				<td><input name="minco2" class="easyui-validatebox" data-options="required:true,validType:'Number', missingMessage:'此输入项为必输项！'"/></td>
			</tr>
			<tr>
				<th>补光时长 （小时）：</th>
				<td><input name="ligthtime" class="easyui-validatebox" data-options="required:true,validType:'Number', missingMessage:'此输入项为必输项！'"/></td>
			</tr>
		</table>
	</form>
