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
		
		<div class="sessionRegister">
			
			<!--邮箱激活-->
			<div class="submit-reg-infos">
				<div class="sri-contents">
					<div class="email-active qr-code">
						<h5 class="title">关注开频校招公众帐号</h5>
						<p class="small-tlt">用微信扫描二维码</p>
						<div class="kaipin-ewm">
							<img src="<%=path %>/images/kaipin-EWM.jpg" width="148" height="148" alt="二维码" title="扫描二维码，关注开频校招" />
						</div>
						<p class="small-tlt case-tips">关注后，可以收到精彩阅读内容推荐，以及开频校招的最新动态信息</p>
						<div class="skip-btnBox">
							<a href="javascript:void(0)" title="跳过" id="next1" onclick="next();">跳过</a>
							<a href="javascript:void(0)" title="跳过" id="next2" style="display:none;">跳过
								<img src="<%=path%>/images/loading.gif"/>
							</a>
						</div>
					</div>
				</div>
			</div>
			
			
		</div>
		<!-- footer_reg start-->
		<%@ include file="/WEB-INF/pages/regedit/comment/regFooter.jsp" %>
		<!-- footer_reg end-->
		
		<script type="text/javascript">
		
			var userId;
			$(function(){
				userId = '${userId}';
			});
			
			function aginEmail(){
				location.href="<%=path%>/regedit/init?oper=aginMail&userId="+userId;
			}
			
			/* 推荐关注 */
			function next(){
				$.ajax({
					cache : false,
					async : true,
					dataType: 'json',  
			        crossDomain: true,
					type : 'GET',
					jsonp: "callback",//服务端用于接收callback调用的function名的参数  
			        jsonpCallback:"callback",//callback的function名称  
					url : r_sso_url + '/web/auth/rCookie',
					data : {
						uType : '11',
						uid: userId
					},
					beforeSend:function(){
						$("#next1").hide();
						$("#next2").show();
					},
					xhrFields: {
	                      withCredentials: true
	              },
					complete : function(data) {
						var datastr = data.responseText;
						var datas = eval('('+datastr+')');
						var redirect_uri = datas.redirect_uri;
						var uid = datas.uid;
// 						location.href=datas.redirect_uri + "/home";
						var redirect_uri = datas.redirect_uri;
						var url = "";
						if(redirect_uri.startWith("http://u.kaipin.")){
							url = r_university_url + "/home?userId=" + uid;
						}else if(redirect_uri.startWith("http://c.kaipin.")){
							url = r_company_url + "/home";
							
						}
						location.href=url;
					}
				});
			}
				
			String.prototype.startWith=function(s){
				  if(s==null||s==""||this.length==0||s.length>this.length)
				   return false;
				  if(this.substr(0,s.length)==s)
				     return true;
				  else
				     return false;
				  return true;
				 }
		</script>
	</body>
</html>
