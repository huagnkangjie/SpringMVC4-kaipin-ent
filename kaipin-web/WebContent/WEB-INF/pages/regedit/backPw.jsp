<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    <title>${TITLE }</title>
    <meta  content="${KEYWORDS }" name="Keywords"/>
    <meta content="${CONTENT }" name="Description"/>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
	<link rel="stylesheet" type="text/css" href="<%=path%>/css/register.css"/>
	<link rel="shortcut icon" href="<%=path%>/favicon.ico" type="image/x-icon" />
	<script type="text/javascript">
		var r_path='<%=basePath%>';
	</script>
	<script type="text/javascript" src="<%=path%>/js/jquery-1.11.1.min.js" ></script>
	<script type="text/javascript">
	</script>
  </head>
  
  <body>
  
  
		<div class="lm-header-container">
			<div class="header">
				<h1><a href="javascript:void(0)" class="logo backIndexLogin"></a></h1>
			</div>
		</div>
		
		
		<div class="forget-password-page">
			<div class="pw-tips"><span>找回密码</span></div>
			<form method="post" id="">
				<div class="the-emails">
					<span class="reg-eamils-tips">注册邮箱</span>
					<input type="text" class="eamils-input" id="email" name="email"/>
					<span class="clear"></span>
					<span class="error-info" id="emailTip">
					</span>
				</div>
				
				<div class="the-emails">
					<span class="reg-eamils-tips"></span>
					<a href="javascript:void(0)" class="find-pwSubmit" onclick="backPw();" id="find-pwSubmit">确定</a>
					<a href="javascript:void(0)" class="find-pwSubmit" style="display:none;" id="find-pwSubmit2">提交中</a>
					<a  href="javascript:window.opener=null;window.open('','_self');window.close();" class="find-pwSubmit" style="background-color: #ccc;margin-left:10px;color:#666;">取消</a>
				</div>
			</form>
		</div>
		
		
		
		
		
		<!--注册成功-->
		<div class="registered-success">
			<p class="success-title"><span class="su-icon"></span>发送成功</p>
			<p class="success-tips">找回密码邮件已发送到注册邮箱<span id="emails" style="color:#74C426;font-size:16px;"></span>，请登录该邮箱进行修改密码</p>
<!-- 			<a href="javascript:void(0)" class="back-top-main" id="backBtn">返回</a> -->
		</div>

		<script type="text/javascript">
			$(function(){
				$("#email").blur(function (){
					emailValidata();
				});
// 				$("#find-pwSubmit").click(function(){
// 					$(".registered-success").show();
// 				});
				$("#backBtn").click(function(){
					window.history.back();
				});
				$("#reset-val").click(function(){
					window.history.back();
				});
				
				document.onkeydown = function(event) {　　
					if (event.keyCode == 13) //回车键的键值为13
						event.preventDefault();
				};
			})
			/* 验证邮箱 */
			function emailValidata(){
				var email = $("#email").val();
				if($.trim(email).length != 0){
					if (emailCheck(email)) {
						$("#email").removeClass("error-input");
						$("#emailTip").hide();
						return true;
					}else{
						$("#email").addClass("error-input");
						$("#emailTip").show();
						$("#emailTip").html("邮箱格式不对");
						return false;
					}
				}else{
					$("#email").addClass("error-input");
					$("#emailTip").show();
					$("#emailTip").html("请填写邮箱");
				}
			}
			function emailCheck(value) {
				var pattern = /^[0-9A-Za-z0-9]+([-_.][0-9A-Za-zd0-9]+)*@([^_]+[0-9a-zA-Za-zd_]+[-.])+[A-Za-zd]{2,5}$/;
				if (!pattern.test(value)) {
					return false;
				}
				return true;
			}
			
			function backPw(){
				if(emailValidata()){
					var email = $("#email").val();
					$("#find-pwSubmit").hide();
					$("#find-pwSubmit2").show();
					setTimeout(function () {
						$.ajax({
							cache : false,
							async : false,
							type : 'POST',
							url : r_path + '/regedit/backPw',
							data : {
								email : email
							},
							success : function(data) {
								var datas = data;
								if (datas.success) {
									$("#emails").html(datas.obj);
									$(".registered-success").show();
								} else {
									$(".registered-success").hide();
									$("#find-pwSubmit").show();
									$("#find-pwSubmit2").hide();
									$("#emailTip").css({"display":"inline-block","marginBottom":"20px"});
									$("#emailTip").html("提示：O(∩_∩)O~，我们的攻城师说该邮箱未</br>被注册过。请核实邮箱地址");
								}
							}
						});
					}, 500);
				}
			}
		</script>
  </body>
</html>
