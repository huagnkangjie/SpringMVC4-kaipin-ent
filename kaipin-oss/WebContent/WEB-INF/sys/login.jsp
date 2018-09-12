<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ include file="/WEB-INF/sys/include.inc.jsp"%>
<!doctype html>
<html>
<head>
<title>登陆</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<meta http-equiv="X-UA-Compatible" content="chrome=1,IE=edge" />
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta name="robots" content="noindex,nofollow">
<link href="${BASE_PATH}/public/assets/css/admin_login.css"
	rel="stylesheet" />
<link rel="shortcut icon" href="${BASE_PATH}/public/favicon.ico" type="image/x-icon" />
<style>
#login_btn_wraper {
	text-align: center;
}

#login_btn_wraper .tips_success {
	color: #fff;
}

#login_btn_wraper .tips_error {
	color: #DFC05D;
}

#login_btn_wraper button:focus {
	outline: none;
}
</style>
<script>
	if (window.parent !== window.self) {
		document.write = '';
		window.parent.location.href = window.self.location.href;
		setTimeout(function() {
			document.body.innerHTML = '';
		}, 0);
	}
</script>
</head>
<body>
	<div class="wrap">
		<h1>
			<a href="">开频管理中心</a>
		</h1>
		<form method="post" name="login" action="${BASE_PATH}/login"
			autoComplete="off">
			<div class="login">
				<ul>
					<li><input class="input" id="js-admin-name" required
						name="username" type="text" placeholder="用户名" title="用户名" /></li>
					<li><input class="input" id="admin_pwd" type="password"
						required name="password" placeholder="密码" title="密码" /></li>
					
					<li><input class="input" type="text" name="captcha" required
						placeholder="请输入验证码" /></li>
                        <li class="verifycode-wrapper"><img class="verify_img"
						src="${BASE_PATH}/captcha.png"
						onclick="this.src='${BASE_PATH}/captcha.png?time='+Math.random();"
						style="cursor: pointer;" title="点击获取" width="" height='' /></li>
				</ul>
				<div id="login_btn_wraper">
					<button type="submit" name="submit" class="btn js-ajax-submit"
						data-loadingmsg="正在加载...">登录</button>

					<span class="tips_error"> 
					 <c:choose>
							<c:when
								test="${error=='org.apache.shiro.authc.IncorrectCredentialsException'}">

								<kaipin:message code="error.invalidPassword" />


							</c:when>
							<c:when
								test="${error=='org.apache.shiro.authc.UnknownAccountException'}">

								<kaipin:message code="error.usernameNotExist" />

							</c:when>
							<c:when
								test="${error=='com.kaipin.oss.exception.security.CaptchaErrorException'}">

								<kaipin:message code="error.invalidCaptcha" />
							</c:when>
							
							<c:when
								test="${error=='com.kaipin.oss.exception.security.DisabledException'}">

								<kaipin:message code="error.userDisabled" />

							</c:when>
							
							<c:otherwise></c:otherwise>
						</c:choose>


					</span>
				</div>

			</div>
		</form>

	</div>
	<script>
		var GV = {
			DIMAUB : "",
			JS_ROOT : "${CONTEXT_PATH}/public/js/",//js版本号
			TOKEN : '' //token ajax全局
		};
	</script>
	<script type="text/javascript" src="${CONTEXT_PATH}/public/js/wind.js"></script>
	<script type="text/javascript"
		src="${CONTEXT_PATH}/public/js/jquery.js"></script>
	<script type="text/javascript"
		src="${CONTEXT_PATH}/public/js/common.js"></script>
	<script>
		;
		(function() {

			document.getElementById('js-admin-name').focus();
		})();
	</script>
</body>
</html>