<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
 <%@ include file="/WEB-INF/pages/regedit/comment/regHeader.jsp" %>
	<script type="text/javascript">
		var r_path='<%=basePath%>';
	</script>
  </head>
  
  <body>
		<div class="lm-header-container">
				<div class="header">
					<h1><a href="javascript:void(0)" class="logo backIndexLogin"></a></h1>
				</div>
			</div>

			<div class="registered-success bounceIn" style="display: block;">
				<p class="success-title"><span class="su-icon"></span>验证成功</p>
				<p class="success-tips">该账号已通过验证</p>
				<a href="javascript:void(0)" class="back-top-main login-im backIndexLogin">立即登录</a>
			</div>
  </body>
</html>
