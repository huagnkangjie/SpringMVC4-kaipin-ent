<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
  
  	<%@ include file="/WEB-INF/pages/ent/regedit/comment/regHeader.jsp" %>
  	
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
					<span class="tips-txt">-创建您的开频账户</span>
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
					<dd style="color:#6ab540;">选择身份</dd>
				</dl>
				<dl class="sheet2">
					<dt>
						<span class="ll-bg ll-bg-one">2</span>
					</dt>
					<dd style="color:#d8d8d8;">提交账号信息</dd>
				</dl>
				<dl class="sheet3">
					<dt>
						<span class="ll-bg ll-bg-one">3</span>
					</dt>
					<dd>提交认证</dd>
				</dl>
				<div class="line line1"></div>
				<div class="line line2"></div>
			</div>
			
			<div class="content-box">
				
				<div class="all-sheet identity-selection" style="height:auto; padding-bottom:40px;">
					<div class="sel-box">
						<form>
							<!-- <dl class="sb-dist">
								<dt>
									<p class="tlt">我是在校学生&应届生求职者</p>
								</dt>
								<dd>
									<div class="intro">
										观看企业在线上直播的校招视频宣讲会创建简历投递心仪职位参加笔试，参加视频面试，通过收企业发来的职位录用通知书方可直接入职企业
									</div>
								</dd>
							</dl> -->
							<dl>
								<dt>
									<p class="tlt">我是校招企业</p>
									
								</dt>
								<dd>
									<div class="intro">
										企业发布校招视频宣讲会直播预告，直播宣讲会筛选收到的简历，邀请用户在线笔试和视频面试发起笔试，发起视频面试给笔试面试通过的求职者发职位录用通知书等待求职者入职
									</div>
								</dd>
							</dl>
							
							<!-- <input type="checkbox" id="check-student" class="check-mark check-inp"/> -->
							<input type="checkbox" id="check-company" class="check-mark check-inp"/>
							<input type="hidden" id="regeditType" value="ent"/>
<!-- 							<input type="hidden" id="regeditType" value="0"/> -->
							<div id="lable-select">
								<!-- <label for="check-student" id="slect-student" class="check-mark check-no slect-stu"></label> -->
								<label for="check-company" id="slect-company" class="check-mark check-no slect-com" style="top:54px;"></label>
							</div>
						</form>
						
						<div class="next-step">
							<a href="javascript:void(0);" class="btn" id="nextDo">下一步</a>
						</div>
					</div>
				</div>
			</div>
			
		</div>
		<!--注册流程 end-->
		
		<!-- footer_reg start-->
		<%@ include file="/WEB-INF/pages/ent/regedit/comment/regFooter.jsp" %>
		<!-- footer_reg end-->
		
		<script type="text/javascript" src="<%=path%>/js/jquery-1.11.1.min.js"></script>
		<script type="text/javascript" src="<%=path%>/js/base.js"></script>
		<script type="text/javascript" src="<%=path%>/js/regedit/regedit.js"></script>
		<script type="text/javascript" src="<%=path%>/js/regedit/fiexdFoot.js"></script>
		<script type="text/javascript">
			$(function(){
				
				$("#lable-select").find("label").click(function(){
					$(this).addClass("bg-img").siblings().removeClass("bg-img");
				});
				
				$("#regeditType").val("ent");
				
			})
			
			
		</script>
	</body>
</html>
