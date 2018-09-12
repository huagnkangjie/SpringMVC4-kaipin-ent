
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
   	<title>${sys_title }</title>
    <meta  content="${sys_keywords }" name="Keywords"/>
    <meta content="${sys_description }" name="Description"/>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
	<link rel="shortcut icon" href="<%=path%>/favicon.ico" type="image/x-icon" />
	<link rel="stylesheet" type="text/css" href="${STATIC_SCH }/css/nameUnknown.css"/>
	<script type="text/javascript" src="${BASE_PATH }${_PUBLIC_ }/web/common/js/jquery-1.11.1.min.js" ></script>
