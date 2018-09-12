
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>

 
<%

 //out.print( request.getSession().getId());
 
response.reset();
Cookie[] cookies = request.getCookies();
if (cookies != null && cookies.length > 0) {
	for (Cookie c : cookies) {
		out.println(c.getName()+":"+c.getValue()+"\n");
	}
}


%>
</body>
</html>