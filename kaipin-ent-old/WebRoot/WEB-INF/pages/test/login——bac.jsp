<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>拉米招聘</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
	<script type="text/javascript" src="<%=path%>/js/jquery-1.11.1.min.js" ></script>
	<script src="<%=path%>/js/jquery-easyui-1.2.6/jquery-1.7.2.min.js" charset="UTF-8" type="text/javascript"></script>
	<script type="text/javascript" src="<%=path%>/js/common.js" ></script>
	<script type="text/javascript" src="<%=path%>/js/login.js" ></script>
	<script language="JavaScript"> 
		if (window != top) {
			top.location.href = "<%=path%>/loginController/index.do"; 
		}
	</script>
	<script type="text/javascript">
	
		var email_val = "false";
		var entName_val = false;
		$(function () {
			$("#login").click(function (){
				login();
			});
			$("#tip1").hide();
			$("#tip2").hide();
			/**
			 *监听回车键
			 */
			document.onkeydown = function(event) {　　
				if (event.keyCode == 13) //回车键的键值为13
				　　　login();　
			};
			/***********************************************/
			//验证邮箱
			$("#email").blur(function (){
				emailValidata();
			});
			//验证企业名称
			$("#entName").blur(function (){
				entNameValidata();
			});
			//密码
			$("#pw1").blur(function (){
				pw1Validata();
			});
			$("#pw2").blur(function (){
				pw2Validata();
			});
			//登录 邮箱
			$("#lg_email").blur(function (){
				lg_emailValidata();
			});
		});
		
		/**
		 * 验证码
		 */
		function changeImg(){
			var time=new Date().getTime();
	    	var url = "<%=path%>/validatecode.jpg?time="+time;
	    	$("#validateImg").attr("src",url);
		}
	</script>

  </head>
  
  <body>
		<!--header start-->
		<div class="header noSelect">
			<div class="nav">
				<div class="usr-lm-logo fl">
					<a href="javascript:void(0)" class="ul-logo fl"></a>
				</div>
<!-- 				<div class="business-enter fl "> -->
<!-- 					<a href="detail-ifream.html" class="business-enter-btn"> | 企业入口</a> -->
<!-- 				</div> -->
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
		        			<span class="message" id="tip1" style="display: inline-block;"></span>
		        		</div>
		        		<div class="loging-form">
		        			<form>
		        				<p><input type="text" id="lg_email" value="xmkj@qq.com" class="formInput email" placeholder="邮箱"/></p>
		        				<p><input type="password" id="lg_pw" value="1" class="formInput password" placeholder="密码"/></p>
		        				<p><input type="button" onclick="login();" class="formSubmit" value="登录"></p>
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
			        			<span class="message" id="tip2" style="display: inl ine-block;"></span>
			        		</div>
			        		<div class="loging-form">
			        			<input type="hidden" value="false" id="zcValue"/>
			        			<form id="form2" name="form2" action="<%=path%>/loginController/register.do" method="post">
			        				<p><input type="text"  name="email" id="email" class="formInput email" maxlength="36" placeholder="邮箱"/></p>
			        				<p><input type="text" name="entName" id="entName" class="formInput" placeholder="企业名称"/></p>
			        				<p><input type="password" name="password" id="pw1"  class="formInput password" placeholder="密码"/></p>
			        				<p><input type="password" class="formInput password" id="pw2" placeholder="确认密码"/></p>
			        				<p><input type="button" onclick="register();" class="formSubmit" value="注册"></p>
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
        			<p><input type="button"  class="formSubmit" value="确定"></p>
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
		
		<!--test include  start-->
<%-- 		<%@ include file="inc/include1.jsp"%> --%>
		<!--test include  end-->
		<script type="text/javascript" src="<%=path %>/js/commentjs.js" ></script>
		<a href="<%=path%>/registerController/init.do">注册</a>
	</body>
</html>
