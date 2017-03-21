<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
<title>食用菌智能测控系统管理系统</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!-- my97日期控件 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/jslib/My97DatePickerBeta4.8b2/My97DatePicker/WdatePicker.js" charset="utf-8"></script>
<script type="text/javascript"
	src="jslib/jquery-easyui-1.4.3/jquery.min.js"></script>
<script type="text/javascript"
	src="jslib/jquery-easyui-1.4.3/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="jslib/jquery-easyui-1.4.3/locale/easyui-lang-zh_CN.js"></script>
<link type="text/css" rel="stylesheet"
	href="jslib/jquery-easyui-1.4.3/themes/default/easyui.css" />
<link type="text/css" rel="stylesheet"
	href="jslib/jquery-easyui-1.4.3/themes/icon.css" />
<script type="text/javascript" src="jslib/zxhUtil.js"></script>
<script type="text/javascript">
	$(function() {

	});
</script>
</head>
<body class="easyui-layout">
	<div data-options="region:'north'" style="height:60px;"></div>
	<div data-options="region:'south'" style="height:40px;"></div>
	<div data-options="region:'west',title:''" style="width:200px;">
		<jsp:include page="layout/west.jsp" />
	</div>
	<!--  /*<div data-options="region:'east',title:'east',split:true"
		style="width:200px;"></div>*/-->
	<div data-options="region:'center',title:'操作区'"
		style="overflow:hidden">
		<jsp:include page="layout/center.jsp" />
	</div>

</body>
</html>
