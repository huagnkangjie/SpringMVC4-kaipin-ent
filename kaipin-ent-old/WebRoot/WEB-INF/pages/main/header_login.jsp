<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    <title>开频校招_校园招聘官方网_校园招聘网_2016校园招聘_校园招聘会_校招宣讲会_线上双选会_视频面试_兼职_实习</title>
	<meta  content="开频,校招,校园招聘,应届生求职,找工作,大学生求职,招聘网,人才网,宣讲会视频,在线双选会,视频面试,kaipin,校招企业,应届生求职,暑期兼职,寒假兼职,兼职,实习,大一,大二,大三,大四,研究生,网申系统" name="Keywords"/>
	<meta content="开频校招通过大数据分析来对接校招职位与大学生简历数据，为大学生和企业提供免费校招服务,是学生、企业、学校的连接器,是中国领先的一站式校园垂直招聘社区平台,宣讲会直播点播,在选笔试,视频面试,收发Offer, 大学生直接入职,打破时空限制实现无缝链接,让校园招聘变得很简单" name="Description"/>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="-1">    
	<script type="text/javascript">
		var r_path='<%=basePath%>';
	</script>
	<!-- 系统 -->
	<link rel="stylesheet" href="<%=path%>/css/basic.css" />
	<link rel="shortcut icon" href="<%=path%>/favicon.ico" type="image/x-icon" />
	<script type="text/javascript" src="<%=path%>/js/jquery-1.11.1.min.js" ></script>
<%-- 	<script src="<%=path%>/js/jquery-easyui-1.2.6/jquery-1.7.2.min.js" charset="UTF-8" type="text/javascript"></script> --%>
	<script type="text/javascript">
		
	</script>
	
  </head>
  <body>
  		<!--header start-->
		<div class="header">
			<div class="head">
				<div class="hd-top-search fl">
					<a href="<%=path%>/loginController/index.do" class="lm-logo fl backIndexLogin"></a>
				</div>
				<div class="login-registe-btns fr">
					<a class="signup_link login-button" href="<%=path%>/loginController/index.do" id="login-button">企业登录</a>
					<a class="signup_link signup-button" href="<%=path%>/regedit/init.do" id="regedit">企业注册</a>
				</div>
				<div class="clear"></div>
			</div>
		</div>
		<!--header end-->
		
	</body>
		
		
		