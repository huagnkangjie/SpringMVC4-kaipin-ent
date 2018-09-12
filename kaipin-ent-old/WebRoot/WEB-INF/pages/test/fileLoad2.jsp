<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>文件上传</title>
	<link rel="stylesheet" type="text/css" href="<%=path%>/js/uploadify2/html5uploader.css"/>
	<script type="text/javascript" src="<%=path%>/js/uploadify2/jquery.js"></script>
	<script type="text/javascript" src="<%=path%>/js/uploadify2/jquery.html5uploader.js"></script>
  <script type="text/javascript">
$(function(){
	$('#upload').html5uploader({
		auto:false,
		multi:true,
		removeTimeout:9999999,
		url:'<%=path%>/annexController/uploadImg.do',
		onUploadStart:function(){
			alert('开始上传');
			},
		onInit:function(){
			alert('初始化');
			},
		onUploadComplete:function(){
			alert('上传完成');
			}
		});
	});
</script>
</head>

<body>
<div id="upload"></div>
</body>
</html>