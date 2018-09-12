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
	
	</head>
	<body>
		<%out.print(123);
			Cookie c[] = request.getCookies();
			for(Cookie cc : c){
				out.println(cc.getName() + "    :    " + cc.getValue()+ "\n\r");
			}
		%>
	</body>
	
</html>

