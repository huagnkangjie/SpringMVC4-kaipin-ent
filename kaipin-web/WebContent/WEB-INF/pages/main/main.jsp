<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
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
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
	<!-- 系统 -->
	<script type="text/javascript" src="<%=path%>/js/jquery-1.11.1.min.js" ></script>
	<script type="text/javascript">
		function layout(){
			parent.window.location.href="<%=path%>/loginController/logout.do";
		}
	</script>
  </head>
 <body style="overflow: hidden;">
		<!--header start-->
		<div class="header noSelect">
			<div class="nav">
				<div class="usr-lm-logo fl"><a href="javascript:void(0)" class="ul-logo"></a></div>
				<!--navList start-->
				<div class="hdNav-panelLists fl" id="hdNav-panelLists">
					<div class="lm-navList fl">
						<ul>
							<li class="nav-active">
								<a href="javascript:void(0)" onclick="getData('basicConctroller/init.do')">企业首页</a>
							</li>
							<li class="message-remind">
								<a href="javascript:void(0)" onclick="getData('resume/init.do')">简历管理</a>
								<span class="mg-remind"></span>
							</li>
							<li><a href="javascript:void(0)" onclick="getData('position/init.do')">职位管理</a></li>
							<li><a href="javascript:void(0)" onclick="getData('areaController/init.do')">测试</a></li>
						</ul>
						<div class="clear"></div>
					</div>
					
					<div class="nav-search fr">
						<form>
							<input type="text" placeholder="输入简历关键字" />
							<a href="javascript:void(0)" class="search-btn"></a>
						</form>
					</div>
				</div>
				
				<div class="company-loging fr">
					<a href="javascript:void(0)" class="company-icon fl" style="background: url(images/company-logo.png);"></a>
					<p class="fl"><span class="businessName" title="${sessionScope.user.entName }">${sessionScope.user.entName }</span>|<a href="javascript:void(0)" onclick="layout();" class="layout">退出</a></p>
				</div>
				
				<div class="search-lists" id="search-lists">
					<div class="sl-tlt">
						<span class="icon"></span>
						<input type="text" class="sl-position" placeholder="搜索：职位、简历"/>
						<a href="javascript:void(0)" class="sl-close"></a>
					</div>
					<div class="sl-contents">
						<p class="tisp"><span>快速链接</span></p>
						<ul class="">
							<li><a href="javascript:void(0)"><span>Java开发工程师</span></a></li>
							<li><a href="javascript:void(0)"><span>Ios工程师</span></a></li>
							<li><a href="javascript:void(0)"><span>Android开发工程师</span></a></li>
							<li><a href="javascript:void(0)"><span>前端开发工程师</span></a></li>
						</ul>
					</div>
				</div>				
			</div>
		</div>
		<!--header end-->
		
		<div class="ifreamBox">
			<iframe id="iFrame1" name="iFrame1" width="100%"  frameborder="0" src="<%=path%>/basicConctroller/init.do"></iframe>
		</div>
		
		<div id='cover-layer' style='width:100%;height:100%; display: none; background-color:#141414;position:fixed;top:0;left:0;z-index:9999;filter:alpha(opacity=58);-moz-opacity:0.58;-khtml-opacity:0.58;opacity:0.58;'></div>
		
		<script type="text/javascript" src="<%=path%>/js/commentjs.js" ></script>
		<script type="text/javascript">
			$(function(){
				
			});
			function getData(ifm){
				document.getElementById("iFrame1").src = <%=path%>/+ifm;
			}
		</script>
			
	</body>
</html>
