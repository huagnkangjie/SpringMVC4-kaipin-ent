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
	<!-- 系统 -->
	<script type="text/javascript" src="<%=path%>/js/jquery-1.11.1.min.js" ></script>
	
  </head>
  <body>
		<!--header start-->
		<div class="header noSelect">
			<div class="nav">
				<div class="usr-lm-logo fl">
					<a href="javascript:void(0)" class="ul-logo fl"></a>
				</div>
				<div class="business-enter fl ">
					<a href="detail-ifream.html" class="business-enter-btn"> | 企业入口</a>
				</div>
				<div class="business-login fr">
					<a href="javascript:void(0)" id="business-login">登录</a> | <a href="javascript:void(0)" id="business-register">注册</a>
				</div>
			</div>
		</div>
		<!--header end-->
		
		<div class="entrance-cons">
			<div class="ec-pic">
				<img src="images/sy.jpg" width="1180"/>
			</div>
		</div>
		
		
		
		<div class="tzui-loading-overlay overlay" id="tzui-loading-overlay"></div>
		<!--登录注册 开始-->
		<div class="wd-businessLogin" id="wd-businessLogin" style="overflow: hidden;display: none;">
			<div class="quickFlip" id="quickFlip" style="position:absolute;top:0;left:0;">
				<!--登录开始-->
		        <div class="blackPanel">
		        	<div class="cancle-close">
		        		<a href="javascript:void(0)" class="close-btn fr"></a>
		        	</div>
		        	<div class="loging-input">
		        		<h3 class="">会员登录</h3>
		        		<div class="emil-tips">
		        			<span>邮箱登录</span>
		        			<span class="message" style="display: inline-block;">邮箱格式不正确</span>
		        		</div>
		        		<div class="loging-form">
		        			<form>
		        				<p><input type="text" class="formInput email" placeholder="邮箱"/></p>
		        				<p><input type="password" class="formInput password" placeholder="密码"/></p>
		        				<p><input type="button" class="formSubmit" value="登录"></p>
		        				<p class="register">
		        					<a href="javascript:void(0)" class="forget-password">忘记密码？</a>
		        					<span>还没有账号　<a href="javascript:void(0)" id="fast-zhuce">立即注册？</a></span>
		        				</p>
		        			</form>
		        		</div>
		        	</div>
		        </div>
		        <!--登录结束-->
		    
		    	<!--注册开始-->
		        	<div class="backshow">
			        	<div class="cancle-close">
			        		<a href="javascript:void(0)" class="close-btn fr"></a>
			        	</div>
			        	<div class="loging-input">
			        		<h3>立即注册</h3>
			        		<div class="emil-tips">
			        			<span>邮箱注册</span>
			        			<span class="message">邮箱格式不正确</span>
			        		</div>
			        		<div class="loging-form">
			        			<form>
			        				<p><input type="text" class="formInput email" placeholder="邮箱"/></p>
			        				<p><input type="text" class="formInput" placeholder="企业名称"/></p>
			        				<p><input type="password" class="formInput password" placeholder="密码"/></p>
			        				<p><input type="password" class="formInput password" placeholder="确认密码"/></p>
			        				<p><input type="button" class="formSubmit" value="注册"></p>
			        				<p class="register">
			        					<a href="javascript:void(0)" class="quickFlipCta" id="goto-loading">返回登录</a>
			        				</p>
			        			</form>
			        		</div>
			        	</div>
			        </div>
		        <!--注册结束-->
    		</div>
		</div>
		
		<!--登录注册 结束-->
		
		
		
		<!--seedSuccess start-->
		<div class="seed-success" id="seedSuccess" id="seed-success">
			<div class="cancle-close">
        		<a href="javascript:void(0)" class="close-btn fr"></a>
        	</div>
        	<div class="success-tips">
        		<h3>发送成功</h3>
        		<p>找回密码的连接已发送至你的邮箱，请登录该邮箱并激活连接。</p>
        		<p><input type="button" class="formSubmit" value="确定"></p>
        	</div>
		</div>
		<!--seedSuccess end--> 
		
		
		<!--找回密码 开始-->
		<div class="find-password" id="find-password">
			<div class="cancle-close">
        		<a href="javascript:void(0)" class="close-btn fr"></a>
        	</div>
			<div class="fp-tips">
        		<h3>找回密码</h3>
        		<p>通过邮箱重置密码</p>
        		<form>
        			<p><input type="text" class="formInput email" placeholder="请输入注册的邮箱"/></p>
        			<p><input type="button" class="formSubmit" value="确定"></p>
        			<p><a href="javascript:void(0)" class="return-loging">返回登录</a></p>
        		</form>
        	</div>
		</div>
		<!--找回密码 end-->
		
		
		<!--重置密码开始-->
		<div class="reset-password" >
			<div class="cancle-close">
        		<a href="javascript:void(0)" class="close-btn fr"></a>
        	</div>
			<div class="fp-tips">
				<h3>重置密码</h3>
        		<form>
        			<p><input type="password" class="formInput password" placeholder="密码"/></p>
			        <p><input type="password" class="formInput password" placeholder="确认密码"/></p>
			        <p><input type="button" class="formSubmit" value="确定"></p>
        		</form>
        	</div>
		</div>
		<!--重置密码 end-->
		
		
		<!--footer start-->
		<div class="footer">
			<div class="foot">
				<ul>
					<li><a href="javascript:void(0)">关于我们 </a>|</li>
					<li><a href="javascript:void(0)">意见反馈 </a>|</li>
					<li><a href="javascript:void(0)">帮助中心 </a>|</li>
				</ul>
				<p>Copyright © 2015 LaMi Inc. 保留所有权利    京公安网安备 11010500896 京ICP备10214630</p>
			</div>
		</div>
		<!--footer end-->
		<script type="text/javascript" src="<%=path %>/js/commentjs.js" ></script>
		
		<script>
			$(function(){
				
			});
		</script>
	</body>  
</html>
