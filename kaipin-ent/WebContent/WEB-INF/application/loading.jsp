<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
   	<title></title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<meta name="renderer" content="webkit">
	<script type="text/javascript"
		src="${STATIC_SCH }/js/constants.js?v.<%=System.currentTimeMillis()%>"></script>
	<script type="text/javascript">
		function init(){
			location.href= r_web_url +  "/login";
		}	
	</script>
	</head>
	<body onload="init();">
	</body>
	
</html>

