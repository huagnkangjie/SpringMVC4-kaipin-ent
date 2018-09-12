<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
  
  	<%@ include file="/WEB-INF/pages/regedit/comment/regHeader.jsp" %>
  	
	<script type="text/javascript">
		var r_path='<%=basePath%>';
		var userId;
		var companyId;
	
		var times = 3;
		$(function(){
			userId = '${userId }';
			runGo(times);
		});
		
		function runGo(times){
			setInterval(function(){
				$("#goTimes").html(times);
				times--;
				if(times == 0){
					location.href="<%=path%>/rfollow/pushFollowPage?userId=" + userId;
				}
			},1000);
		}
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
				<div class="sri-contents" style="height:300px;">
					<form action="" method="">
						<div class="email-active"  style="text-align:center;">
							<h5 class="title" style="padding-top:140px;">邮箱激活成功！感谢您确认邮箱${email }</h5>
							</br>
							<a href="javascript:void(0);"><span id="goTimes">3</span>秒后自动跳转</a>
							<!-- <div class="otherEmailInfos">
								<p class="tips-tlt">用户姓名，欢迎使用开频校招！</p>
								<p class="tips-small-tlt">找找你邮箱通讯录中谁已加入开频校招</p>
							</div>
							<div class="email-infos">
								<div class="ipt-txt">
									<input type="text" name="" id="" value="" placeholder="devale@163.com"/>
								</div>
								<div class="ipt-txt placeholderOfPsw">
									<input type="password" name="" id="" value="" placeholder="密码"/>
									<input type="text" class="placeholders" placeholder="密码">
								</div>
								<a href="javascript:void(0)" class="continue-btn">继续</a>
								<a href="javascript:void(0)" class="skip-btn" title="跳过">跳过</a>
								<div class="clear"></div>
							</div>
							<div class="details-tips">
								<p>导入通讯录后，这些信息将用于向您推荐相关联系人，帮助您构建更丰富的开频校招人脉。 
									<a href="javascript:void(0)" class="konw-more"> 详细了解？
										<span class="smail-tips">
											导入通讯录后，联系人的详细信息将上传到开频校招服务器。这些信息将用于向您推荐相关联系人，帮助您构建更丰富的开频校招人脉，以及隐私政策中提及的其他用途。如您需要输入密码，开频校招仅暂时保留密码，用于验证帐号。未经您允许，开频校招不会向任何人发送邮件。
										</span>
									</a>
								</p>
							</div>
							
							<div class="whereThey">
								<p class="wh-tlt">他们会不会也在这里？</p>
								<ul class="ul">
									<li>
										<a href="javascript:void(0)">室友</a>
									</li>
									<li>
										<a href="javascript:void(0)">母校</a>
									</li>
									<li>
										<a href="javascript:void(0)">老东家</a>
									</li>
									<li>
										<a href="javascript:void(0)">校友</a>
									</li>
									<li>
										<a href="javascript:void(0)">同事</a>
									</li>
									<li>
										<a href="javascript:void(0)">联系人</a>
									</li>
									<li class="noLine-h">
										<a href="javascript:void(0)">合作<br>伙伴</a>
									</li>
									<li>
										<a href="javascript:void(0)">朋友</a>
									</li>
									<li class="noLine-h">
										<a href="javascript:void(0)">大学<br>同学</a>
									</li>
									<li class="noLine-h">
										<a href="javascript:void(0)">高中<br>同学</a>
									</li>
									<li class="noLine-h">
										<a href="javascript:void(0)">初中<br>同学</a>
									</li>
								</ul>
							</div> -->
							
						</div>
					</form>
				</div>
			</div>
			
			
		</div>
		<!--regedit end-->
			
		<!-- footer_reg start-->
		<%@ include file="/WEB-INF/pages/regedit/comment/regFooter.jsp" %>
		<!-- footer_reg end-->
		
	</body>
</html>
