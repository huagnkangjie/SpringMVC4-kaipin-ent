
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<head>
   <base href="<%=basePath%>">
   	<title>${TITLE }</title>
    <meta  content="${KEYWORDS }" name="Keywords"/>
    <meta content="${CONTENT }" name="Description"/>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
	<link rel="shortcut icon" href="<%=path%>/favicon.ico" type="image/x-icon" />
	<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/nameUnknown.css"/>
	<script type="text/javascript" src="<%=basePath%>/js/jquery-1.11.1.min.js" ></script>
	<script type="text/javascript" src="<%=path%>/js/constants.js"></script> 
	<script type="text/javascript" src="<%=basePath%>/js/base.js" ></script>
