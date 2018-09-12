<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>

<!DOCTYPE HTML>
<html>
<head>
<title>${TITLE }</title>
 <meta content="${KEYWORDS }" name="Keywords"/>
 <meta content="${CONTENT }" name="Description"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="<%=path%>/css/nameUnknown.css"/>
<title>重发邮件</title>
<style>

.Absolute-Center {
  margin: auto;
 
  top: 0; left: 0; bottom: 0; right: 0;
  height:100px;
  line-height:100px;
  font-size:20px;
  font-family: "Microsoft YaHei","微软雅黑","Arial","黑体","宋体";
}
</style>
<script type="text/javascript" src="<%=path%>/js/jquery-1.11.1.min.js?v.<%=System.currentTimeMillis()%>" ></script>
<script type="text/javascript" src="<%=path%>/js/base.js?v.<%=System.currentTimeMillis()%>" ></script>
<script type="text/javascript" src="<%=path%>/js/regedit/regedit.js?v.<%=System.currentTimeMillis()%>" ></script>
<link rel="shortcut icon" href="<%=path%>/favicon.ico" type="image/x-icon" />
<script type="text/javascript">
		var r_path='<%=basePath%>';
</script>
<script type="text/javascript">
	var oper = 0;
	var userId;
	$(function(){
		userId = '${userId}';
		
		$("#emailAgain").blur(function(){
			var email = $("#emailAgain").val();
			var emailAgainOld = $("#emailAgainOld").val();
			validataVal(email, emailAgainOld);
		});
		
		function validataVal(email, emailAgainOld){
			if(email == emailAgainOld){
				$("#emailAgainTip").hide();
				return true;
			}else{
				var flag = emailValidata();
				return flag;
			}
		}
		
		$("#act_send_mail").click(function(){
			var email = $("#emailAgain").val();
			var emailAgainOld = $("#emailAgainOld").val();
			var flag = validataVal(email, emailAgainOld);
			debugger;
			if(flag){
				$.ajax({
					cache : false,
					type : "POST",
					url : r_path + '/regedit/reSendMail',
					data : {
						uId : userId,
						email : email
					},
					async : true,
					error : function(request) {
						alert("网络异常，请稍后再试！");
					},
					beforeSend:function(request){
						$("#act_send_mail").hide();
						$("#act_send_mail_ing").show();
					},
					complete: function(data) { 
						$("#act_send_mail").show();
						$("#act_send_mail_ing").hide();
						oper = 0;
						var dataStr = data.responseText;
						var datas = eval('('+dataStr+')');
						if(datas.success){
							alert(datas.msg);
							location.href =r_path + "/regedit/init?oper=check_mail&userId="+userId;
						}else{
							alert(datas.msg);
						}
						
					}
				});
			}
		});
		
		//邮箱格式验证
		function emailCheck(value) {
			var pattern = /^[0-9A-Za-z0-9]+([-_.][0-9A-Za-zd0-9]+)*@([^_]+[0-9a-zA-Za-zd_]+[-.])+[A-Za-zd]{2,5}$/;
			//^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([^_|-|.]+[a-z0-9A-Z_]+(-[a-z0-9A-Z])?[^_|-|.]+\\.)+[a-zA-Z]{2,}$
//			var pattern = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/;
			if (!pattern.test(value)) {
				return false;
			}
			return true;
		}
		/* 验证邮箱唯一性 */
		function emailValidata(email){
			var email = $("#emailAgain").val();
			var email_val = false;
			if (emailCheck(email)) {
				// 唯一性验证
				$.ajax({
					cache : false,
					async : false,
					type : 'POST',
					url : r_path + '/regedit/emailValidata.do',
					data : {
						email : email
					},
					success : function(data) {
						//var datas = eval('(' + data + ')');
						if(data == null || data == ''){
							return;
						}
						if (data.success) {
							$("#emailAgainTip").show();
							$("#emailAgainTip").html("该邮箱已经被注册");
							email_val = false;
						} else {
							$("#emailAgainTip").hide();
							email_val = true;
						}
					}
				});
			}else{
				$("#emailAgainTip").show();
				$("#emailAgainTip").html("邮箱格式不对");
				email_val = false;
			}
			return email_val;
		}
		
		
		
	});
	
</script>
</head>

<body>
	<input type="hidden" id="uId" value="${uId }"/>

		<!--header start-->
		<div class="lm-header-container">
			<div class="header">
				<h1>
					<a href="javascript:void(0)" class="logo backIndexLogin"></a>
					<span class="tips-txt"> - 创建您的开频账户</span>
				</h1>
			</div>
		</div>
		<!--header end-->
		
		<div class="sessionRegister">
			
			<div class="submit-reg-infos">
				<div class="sri-contents">
					<div class="email-active qr-code">
						<h5 class="title">再次发送激活邮件</h5>
					</div>
					
					<div class="email-infos sendEmailsbox">
						<div class="ipt-txt" id="email-erro">
							<input type="text" name="email" id="emailAgain" value="${localUser.email }"  placeholder="输入你的注册邮箱"/>
							<input type="hidden"  id="emailAgainOld" value="${localUser.email }"  placeholder="输入你的注册邮箱"/>
							
						</div>
						<a href="javascript:void(0)" class="continue-btn send-eamils" id="act_send_mail">发送</a>
						<a href="javascript:void(0)" class="continue-btn send-eamils" style="display:none;" id="act_send_mail_ing">
							发送<img src="<%=path %>/images/loading.gif"/>
						</a>
						<div class="clear"></div>
					</div>
					
					<div class="eamils-tips">
						<span class="error-info" id="emailAgainTip" style="display:none;color: red;">上放大法的</span>
						<p>请确认填写的邮箱地址是否正确</p>
						<p>提示：@符号后面不能跟www.</p>
						<p>正常格式：name@example.com</p>
					</div>
				</div>
			</div>
			
			
			
		</div>
		
				
<!-- 				<div class="the-emails"> -->
<!-- 					<span class="reg-eamils-tips"></span> -->
<!-- 					<a href="javascript:void(0)" class="find-pwSubmit" onclick="backPw();" id="find-pwSubmit">确定</a> -->
<!-- 					<a href="javascript:void(0)" class="find-pwSubmit" style="display:none;" id="find-pwSubmit2">提交中</a> -->
<!-- 					<a  href="javascript:window.opener=null;window.open('','_self');window.close();" class="find-pwSubmit" style="background-color: #ccc;margin-left:10px;color:#666;">取消</a> -->
<!-- 				</div> -->
		
	</body>
</html>
