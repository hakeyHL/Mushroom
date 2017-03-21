<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'default.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

	<link rel="stylesheet" type="text/css" href="<%=path%>/css/common.css">
	

</head>

<body>
	
		<div class="banner">标题</div>
		<div class="show">提示内容</div>
		<div class="maindiv">
			<div class="monitor">监控</div>
			<div class="showandcontrol" > 
				<div class="monitorcontrol">监控控制</div>
				<div class="collectordata">采集数据</div>
				<div class="eqcontrol">设备控制</div>
			</div>
		</div>
		<div class="stat">统计数据</div>
		<div class="bottom">版本</div>


</body>
</html>
