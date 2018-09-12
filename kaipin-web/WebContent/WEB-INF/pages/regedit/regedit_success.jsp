<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
 <%@ include file="/WEB-INF/pages/regedit/comment/regHeader.jsp" %>
	<script type="text/javascript">
		var r_path='<%=basePath%>';
	</script>
	<script type="text/javascript">
		var success ;
		$(function(){
			success = '${success }';
			$("#email").html(success);
		});
		
		/* 返回登录 */
		function backIndex(){
			window.location.href="<%=path%>/loginController/index.do";
		}
	</script>
  </head>
  
  <body>
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
		
  
		<!--注册成功-->
		<div class="registered-success" style="display:block;">
			<p class="success-title"><span class="su-icon"></span>注册成功</p>
			<p class="success-tips">激活验证邮件已发送到注册邮箱<span id="email" style="color:#74C426;font-size:16px;"></span>，请登录该邮箱进行验证</p>
			<a href="javascript:void(0)" onclick="backIndex();" class="back-top-main">返回</a>
		</div>
  </body>
</html>
