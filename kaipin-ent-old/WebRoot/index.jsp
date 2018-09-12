<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<% String url = request.getRequestURL().toString();
	if(null!=url&&url.startsWith("http://kaipin.tv")) 
	response.sendRedirect("http://www.kaipin.tv");
%>
<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
   	<title></title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<meta name="renderer" content="webkit">
	<link rel="shortcut icon" href="<%=path%>/favicon.ico" type="image/x-icon" />
	<script type="text/javascript" src="<%=path%>/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript">
		var r_path='<%=basePath%>';
		$(function(){
			window.location.href= r_path + ""
		});
		
	</script>
	
	</head>
	<body>
		
	</body>
</html>

