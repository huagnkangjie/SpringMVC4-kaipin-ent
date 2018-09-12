<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>

<!DOCTYPE html>
<html>
<head>
<title>开频校招温馨提示：您的浏览器需要更新才能访问哦^_^</title>
<meta charset="utf-8"/>
	<style>
		body { text-align:center; padding-top:30px; }
		img { border:none; }
		h2,h3,h4 { color:#666; font-family:arial; }
		strong { border-bottom:1px dotted #930; font-weight:normal; padding-bottom:2px; color:#930; }
		p { padding:50px 0; letter-spacing:20px; }
		h4 { font-weight:normal; }
	</style>
</head>

<body>
	<h2>开频校招温馨提示：<strong>您的浏览器版本过低，请升级到IE10以上浏览器才能正常访问哦 ^_^</strong></h2>
	<h3>推荐使用 <strong>Chrome</strong>、<strong>Safari</strong>、<strong>firefox</strong>、<strong>opera</strong> 浏览器访问~</h3>
	<p>
		<a href="https://www.baidu.com/s?ie=utf-8&f=8&rsv_bp=1&rsv_idx=1&tn=baidu&wd=%E8%B0%B7%E6%AD%8C%E6%B5%8F%E8%A7%88%E5%99%A8%E4%B8%8B%E8%BD%BD&oq=%E8%B0%B7%E6%AD%8C%E6%B5%8F%E8%A7%88%E5%99%A8&rsv_pq=d0a3d2f40012c10b&rsv_t=9476SyvaK4Yf3fWb%2F6fhmQVvnBAaxIn4ZluGhSb3UdH8yzQkVRBH9Ii8FwA&rsv_enter=1&inputT=663593&rsv_sug3=25&rsv_sug1=14&bs=%E8%B0%B7%E6%AD%8C%E6%B5%8F%E8%A7%88%E5%99%A8" target="_blank">
		<img src="<%=path%>/images/chrome.png" alt="chrome" title="chrome" /></a>
		<a href="http://www.apple.com/safari" target="_blank"><img src="<%=path%>/images/safari.png" alt="safari" title="safari"/></a>
		<a href="http://www.firefox.com/" target="_blank"><img src="<%=path%>/images/firefox.png" alt="firefox" title="firefox"/></a>
		<a href="http://www.opera.com/zh-cn" target="_blank"><img src="<%=path%>/images/opera.png" alt="opera" title="opera"/></a>
	</p>
	<h4>Please update to a modern browser</h4>
</body>
</html>