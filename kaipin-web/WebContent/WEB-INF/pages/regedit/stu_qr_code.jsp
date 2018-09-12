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
					<a href="http://www.kaipin.tv" class="logo"></a>
				
				</h1>
			</div>
		</div>
		<!--header end-->
		
		<div class="sessionRegister">
			
			<!--邮箱激活-->
			
			<div class="submit-reg-infos">
				<div class="sri-contents">
				
					<div class="email-active qr-code">
						<h5 class="title" style="font-size:38px;">欢迎使用开频</h5><br/>
						<p class="small-tlt" style="font-size:18px;">求职者请通过APP端使用，学生PC网站端正在开发中，敬请期待！</p><br/><br/>
						<p class="small-tlt"></p><p class="small-tlt"></p>
						<div class="kaipin-ewm">
							<img src="<%=path %>/images/kaipin-xssm.jpg" width="148" height="148" alt="二维码" title="扫描二维码，下载开频APP" />
						</div>
						<p class="small-tlt">扫描二维码，下载安装开频APP</p><br/><br/><br/>
					</div>
				</div>
			</div>
			
			
		</div>
		<!-- footer_reg start-->
		<%@ include file="/WEB-INF/pages/regedit/comment/regFooter.jsp" %>
		<!-- footer_reg end-->
		
	</body>
</html>
