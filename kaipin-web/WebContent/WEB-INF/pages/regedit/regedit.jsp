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
					<a href="javascript:void(0)" class="logo backIndexLogin"></a>
					<span class="tips-txt"> - 创建您的开频账户</span>
				</h1>
			</div>
		</div>
		<!--header end-->
  
		<!--注册流程 start-->
		<div class="register-process">
			
			<div class="flowsheet">
				<dl class="sheet1">
					<dt>
						<span class="ll-bg ll-bg-two">1</span>
					</dt>
					<dd class="lq-color-two">创建账号</dd>
				</dl>
				<dl class="sheet2">
					<dt>
						<span class="ll-bg ll-bg-one">2</span>
					</dt>
					<dd class="lq-color-three">选择身份</dd>
				</dl>
				<dl class="sheet3">
					<dt>
						<span class="ll-bg ll-bg-one">3</span>
					</dt>
					<dd class="lq-color-three">填基本信息</dd>
				</dl>
				<dl class="sheet4">
					<dt>
						<span class="ll-bg ll-bg-one">4</span>
					</dt>
					<dd class="lq-color-three">提交认证</dd>
				</dl>
				<div class="line line1"></div>
				<div class="line line2"></div>
				<div class="line line3"></div>
			</div>
			
			
			<div class="content-box">
				<div class="reg-basic-infos">
					<div class="rbi-contents">
						<form id="regeditForm" name="regeditForm" action="<%=path%>/loginController/register.do" method="post">
							<div class="rgb-infos">
								<div class="ipt-txt" id="phone-erro">
									<input type="text" id="phone" name="phone" maxlength="11" class="input error-input " placeholder="输入联系人手机号码，忘记密码时用于找回密码">
								</div>
								<span class="error-info" id="phoneTip"></span>
							</div>
							
							<div class="rgb-infos">
								<div class="ipt-txt get-code" id="phoneCode-erro">
									<input type="text" id="phoneCode" placeholder="输入验证码" maxlength="4" class="input error-input " placeholder="输入验证码">
									<a href="javascript:void(0)" class="getCode-btn" id="getPhoneCode">获取验证码</a>
									<a href="javascript:void(0)" class="getCode-btn" id="getPhoneCodeIng" style="display:none;background:#ccc;">重新获取(50)</a>
								</div>
								<span class="error-info" id="phoneCodeTip"></span>
							</div>
							
							<div class="rgb-infos">
								<div class="ipt-txt" id="email-erro">
									<input type="text" id="email" name="email" class="input error-input " placeholder="输入邮箱">
								</div>
								<span class="error-info" id="emailTip"></span>
							</div>
							
							<div class="rgb-infos">
								<div class="ipt-txt placeholderOfPsw" id="password-erro">
									<input type="password" id="password" name="password" onpaste="return false" class="input error-input " placeholder="输入密码">
									<input type="text" class="input placeholders" onpaste="return false"  placeholder="输入密码" />
								</div>
								<span class="error-info" id="passwordTip"></span>
							</div>
							<div class="rgb-infos">
								<div class="ipt-txt placeholderOfPsw" id="password2-erro">
									<input type="password" id="password2" onpaste="return false" class="input error-input " placeholder="再输入一次密码">
									<input type="text" class="input placeholders" onpaste="return false"  placeholder="再输入一次密码" />
								</div>
								<span class="error-info" id="password2Tip"></span>
							</div>
							
							
							<div class="rgb-infos">
								<div class="ipt-txt get-code" id="valiCode-erro">
									<input type="text" class="input error-input " id="valiCode" name="valiCode" placeholder="输入图中的字母或数字验证码，不区分大小写">
									<a href="javascript:void(0)" class="change-ewm" onClick="javascript:changeImg();">换一张</a>
									<img src="<%=path%>/validatecode.jpg" id="validateImg" onClick="javascript:changeImg();" class="yzmImg" width="73" height="31" alt="验证码">
								</div>
								<span class="error-info" id="valiCodeTip"></span>
							</div>
							
							
							<div class="rgb-infos mgt30">
								<div class="ipt-txt" id="invitationParentCode-erro">
									<input type="text" id="invitationParentCode" name="invitationParentCode" class="input error-input " placeholder="请输入6位数邀请码，邀请码用来区分邀请者, 没有则可不填">
								</div>
								<span class="error-info" id="invitationParentCodeTip"></span>
							</div>
							
							<div class="rgb-infos mgt30">
								<a href="javascript:void(0)" class="submitBtns" id="saveBtn">提交</a>
								<a href="javascript:void(0)" class="submitBtns" id="submit-reg" style="display:none">
									提交中...<img src="<%=path%>/images/loading.gif" style="margin-left:5px;"/>
								</a>
							</div>
							
							<div class="rgb-infos favor">
								 <a href="javascript:void(0)" class="mwui-switch-btn" id="mwui-switch-btn">
							   		<span class="off"></span>
							   		<input type="hidden" name="show_icon" id="show_icon" value="0">
							  	 </a> 
							  	<p class="favor-tips nosel-color"><span id="favor-tips">我已阅读并同意</span><a target="_blank" href="<%=path %>/inc/rule.html">《开频校招服务条款》</a></p>
							  	<p class="loading">已有账号？<a href="<%=path %>/login">登录</a></p>
							  	<span class="error-info" id="selectedTip">请阅读条款</span>
						   </div>
						</form>
					</div>
					
				</div>
			</div>
			
			
		</div>
		<!--注册流程 end-->
		
		<!-- footer_reg start-->
		<%@ include file="/WEB-INF/pages/regedit/comment/regFooter.jsp" %>
		<!-- footer_reg end-->
		
		
		
		<script type="text/javascript" src="<%=path%>/js/jquery-1.11.1.min.js?v.<%=System.currentTimeMillis()%>" ></script>
		
		<!-- 	Validator -->
		<script type="text/javascript" src="<%=path%>/js/formatJs.js" ></script>
		<script type="text/javascript" src="<%=path%>/js/formValidator.js" ></script>
		<script type="text/javascript" src="<%=path%>/js/formValidatorRegex.js" ></script>
		
		
		<script type="text/javascript" src="<%=path%>/js/base.js?v.<%=System.currentTimeMillis()%>"></script>
		<script type="text/javascript" src="<%=path%>/js/regedit/regedit.js?v.<%=System.currentTimeMillis()%>"></script>
		<script type="text/javascript" src="<%=path%>/js/regedit/fiexdFoot.js"></script>
		<script type="text/javascript" src="<%=path%>/js/regedit/placeholder.js"></script>
		<script type="text/javascript" src="<%=path%>/js/regedit/password.js"></script>
		
		<script type="text/javascript">
			$(function(){
				
				$("#show_icon").val("0");
				
				//切换选中
				$("#lable-select").find("label").click(function(){
					$(this).addClass("bg-img").siblings().removeClass("bg-img");
				});
				
				//开关
				$("#favor-tips").click(function(){
					$('.mwui-switch-btn').trigger("click");
				});
				
	           $('.mwui-switch-btn').on("click", function(){ 
	                var btn = $(this).find("span");
	              	 btn.toggleClass('off'); 
	                var s =  btn.attr("class");
	                
	                if(btn.attr("class") == 'off') { //关闭
	                	$("#selectedTip").hide();
	                	$("#show_icon").val("0");
	                    var path = r_path + "/images/no.png";
	                    $("#mwui-switch-btn").css("background-image","url(" + path +")");
	                } else { //打开
	                	$("#show_icon").val("1");;
	                    var path = r_path + "/images/off.png";
	                    $("#mwui-switch-btn").css("background-image","url(" + path +")");
	                    $("#selectedTip").hide();
	                }  
	                return false;
		        });
				
				
				 
			})
			
			
		</script>
		
	</body>
</html>
