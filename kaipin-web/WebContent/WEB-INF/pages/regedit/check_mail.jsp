<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
  
  	<%@ include file="/WEB-INF/pages/regedit/comment/regHeader.jsp" %>
  	
	<script type="text/javascript">
		var r_path='<%=basePath%>';
	</script>
	</head>
	<body>
		<!--header start-->
		<div class="lm-header-container">
			<div class="header">
				<h1>
					<a href="javascript:void(0)" class="logo"></a>
					<span class="tips-txt"> - 创建您的开频账户</span>
				</h1>
			</div>
		</div>
		<!--header end-->
		<!--regedit start-->
		<div class="sessionRegister">
			<!--邮箱激活-->
			<div class="submit-reg-infos">
				<div class="sri-contents">
					<form action="" method="">
						<div class="email-active">
							<h5 class="title">请完成邮箱验证</h5>
							
							<div class="email-verification">
								<p>激活邮件已发送到注册邮箱，一般激活邮件会在1-3分钟内发送完毕，请注意查收</p>
								<p>邮件并在48小时内点击邮件中的激活链接，完成注册。</p>
								<p class="questions">遇到问题？如果长时间没有收到激活邮件，请在60秒后  <a href="javascript:void(0)" onclick="aginEmail();" class="sendAngin" title="再发一封激活邮件">再发一封激活邮件</a></p>
							</div>	
							<input type="hidden" id="oper" value="${localUser.categoryId }"/>
							<div class="submit-box nosel-color sendEMBtn">
								<a href="javascript:void(0)" class="sub-btn" onclick="pushFollow();" id="pushFollow">继续</a>
								<a href="javascript:void(0)" class="sub-btn" style="display:none;" id="pushFollowIng">
									继续<img src="<%=path%>/images/loading.gif" >
								</a>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
		<!--regedit end-->
			
		<!-- footer_reg start-->
		<%@ include file="/WEB-INF/pages/regedit/comment/regFooter.jsp" %>
		<!-- footer_reg end-->
		
		<script type="text/javascript">
		
			var userId;
			var orgId;
			var userType ;
			$(function(){
				userId = '${userId}';
				orgId = '${orgId}';
				userType = '${localUser.categoryId }';
			});
			
			function aginEmail(){
				location.href="<%=path%>/regedit/init?oper=aginMail&userId="+userId;
			}
			
			/* 推荐关注 */
			function pushFollow(){
// 				$("#pushFollow").hide();
// 				$("#pushFollowIng").show();
				location.href="<%=path%>/rfollow/pushFollowPage?oper="+userType+"&userId="+userId;
<%-- 				location.href="<%=path%>/regedit/init?oper=qr_code&userId="+userId; --%>
			}
				
		</script>
	</body>
</html>
