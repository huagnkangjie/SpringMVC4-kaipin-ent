<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
 <%@ include file="/WEB-INF/pages/ent/regedit/comment/regHeader.jsp" %>
	<script type="text/javascript">
		var r_path='<%=basePath%>';
	</script>
	<script type="text/javascript" src="<%=path%>/js/jquery-1.11.1.min.js" ></script>

	<script type="text/javascript">
	
	
		var userId;
		var companyId;
	
		$(function(){
			userId = '${userId }';
			companyId = '${companyId }';
		});
		
		/* 返回登录 */
		function certificate(){
			//location.href =r_path + "/regedit/certificate.do?target=certificate&userId="+userId+"&companyId="+companyId;
			location.href =r_path + "/regedit/certificate.do?userId="+userId+"&companyId="+companyId;
		}
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
				<a href="javascript:void(0)" class="back-top-main login-im" onclick="certificate();">去完成资质认证</a>
			</div>
  </body>
</html>
