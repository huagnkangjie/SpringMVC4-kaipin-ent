<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
 	<%@ include file="/WEB-INF/pages/regedit/comment/regHeader.jsp" %>
	<script type="text/javascript">
		var r_path='<%=basePath%>';
	</script>
	
	<script type="text/javascript" src="<%=path%>/js/jquery-1.11.1.min.js"></script>
	<!-- 系统 -->
	<link href="<%=path%>/css/dialog.css" rel="stylesheet" type="text/css" >
	<script type="text/javascript" src="<%=path%>/js/dialog3.0.js" ></script>
	<script type="text/javascript" src="<%=path%>/js/basic.js" ></script>
	
	<!-- uploadify -->
	<link rel="stylesheet" type="text/css" href="<%=path%>/js/uploadify/Huploadify.css"/>
	<script type="text/javascript" src="<%=path%>/js/uploadify/jquery.js"></script>
	<script type="text/javascript" src="<%=path%>/js/uploadify/jquery.Huploadify.js"></script>
	<script type="text/javascript">
		var fail ;
		var orgId;
		var userId;
		$(function(){
			fail = '${fail}';
			orgId = '${orgId}';
			userId = '${userId}';
			
			if(fail == 'fail'){
				alert("保存失败，请勿重复提交");
			}
			
			
			
			$("#uploadAgin").click(function(){
				window.location.href="<%=path%>/regedit/certificate.do?target=certificate&oper=uploadAgin";
			});
			
		});
		
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
		<div class="sessionRegister">
			<div class="flowsheet">
				<dl class="sheet1">
					<dt>
						<span class="ll-bg ll-bg-three">1</span>
					</dt>
					<dd class="lq-color-one">创建账号</dd>
				</dl>
				<dl class="sheet2">
					<dt>
						<span class="ll-bg ll-bg-three">2</span>
					</dt>
					<dd class="lq-color-one">选择身份</dd>
				</dl>
				<dl class="sheet3">
					<dt>
						<span class="ll-bg ll-bg-three">3</span>
					</dt>
					<dd class="lq-color-one">填基本信息</dd>
				</dl>
				<dl class="sheet4">
					<dt>
						<span class="ll-bg ll-bg-two">4</span>
					</dt>
					<dd class="lq-color-two">提交认证</dd>
				</dl>
				<div class="line line1 line-color-sure"></div>
				<div class="line line2 line-color-sure"></div>
				<div class="line line3 line-color-sure"></div>
			</div>
			
			<div class="submit-reg-infos">
				<div class="sri-contents">
					<form action="" method="">
						<div class="basic-informations borderNo">
							<div class="ident">
								<div class="it-title">
									<ul>
										<li>个人信息</li>
										<li>账户信息</li>
										<li class="mg0">状态</li>
									</ul>
								</div>
								
								<div class="info-details">
									
									<div class="firstColumn fl">
										<dl>
											<dt>姓名</dt>
											<dd>${userInfo.surname}${userInfo.missSurname }</dd>
										</dl>
										<dl>
											<dt>手机号</dt>
											<dd>${user.phone}</dd>
										</dl>
										<dl>
											<dt>邮箱</dt>
											<dd>${user.email}</dd>
										</dl>
										<dl>
											<dt>地区</dt>
											<dd>${location}</dd>
										</dl>
									</div>
									
									<div class="firstColumn fl">
										<c:forEach	var="m" items="${listDoc }">
											<c:if test="${m.document_type != null &&  m.document_type != '' && m.document_type == 'id_card'}">
												<dl>
													<dt>身份证</dt>
													<dd>
														<div class="eaxm-img">
															<img src="${m.document_path}" onclick="imgbox.show(this.src)" width="78px" height="78px"/>
														</div>
													</dd>
												</dl>
											</c:if>
											<c:if test="${m.document_type != null &&  m.document_type != '' && m.document_type == 'student_card'}">
												<dl>
													<dt>学生证</dt>
													<dd>
														<div class="eaxm-img">
															<img src="${m.document_path}" onclick="imgbox.show(this.src)" width="78px" height="78px"/>
														</div>
													</dd>
												</dl>
											</c:if>
											<c:if test="${m.document_type != null &&  m.document_type != '' && m.document_type == 'graduation_card'}">
												<dl>
													<dt>毕业证</dt>
													<dd>
														<div class="eaxm-img">
															<img src="${m.document_path}" onclick="imgbox.show(this.src)" width="78px" height="78px"/>
														</div>
													</dd>
												</dl>
											</c:if>
										</c:forEach>
									</div>
									<div class="firstColumn fl">
										<dl class="mg0 mark no-pass">
											<dt><span>${checkRuslt }</span></dt>
											<dd style="display:none;">糊，请重新拍摄上传
												<a href="javascript:void(0)" class="anew-sub">重新提交</a>
											</dd>
										</dl>
									</div>
									<div class="clear"></div>
								</div>
								
								<div class="return-back">
									<c:if test="${uploadAgin == 'regedit' }">
										<a href="javascript:void(0)" class="back-btn" onclick="sendEmal();" id="sendEmail">继续</a>
										<a href="javascript:void(0)" class="back-btn" id="sendEmailIng" style="display:none;">继续
											<img src="<%=path %>/images/loading.gif"/>
										</a>
										<script type="text/javascript">
											function sendEmal(){
												$("#sendEmail").hide();
												$("#sendEmailIng").show();
												$.ajax({                
													cache: false,    
													async: true, 
													type: "POST",                
													url:  '<%=path%>/regedit/sendEmail',                
													data:{
														userId : userId
													},              
													error: function(request) { 
													},
													beforeSend : function(request){
														$("#sendEmail").hide();
														$("#sendEmailIng").show();
													},
													success: function(data) {
														
													},
													complete: function(data) { 
														var dataStr = data.responseText;
														var datas = eval('('+dataStr+')');
														var userId= datas.obj.userId;
														location.href='<%=path%>/regedit/init?oper=check_mail&userId='+userId;
													}
												});
												
											}
										</script>
									</c:if>
									<c:if test="${uploadAgin == 'uploadAgin' }">
										<a href="javascript:void(0)" class="back-btn" onclick="back();">返回</a>
									</c:if>
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
		<!--注册流程 end-->
		
		<!-- footer_reg start-->
		<%@ include file="/WEB-INF/pages/regedit/comment/regFooter.jsp" %>
		<!-- footer_reg end-->
		
		
		
		
		<script type="text/javascript" src="<%=path%>/js/base.js?v.<%=System.currentTimeMillis()%>"></script>
		<script type="text/javascript" src="<%=path%>/js/regedit/regedit.js?v.<%=System.currentTimeMillis()%>"></script>
		<script type="text/javascript" src="<%=path%>/js/imgZoom.js"></script>
		<script type="text/javascript" src="<%=path%>/js/regedit/fiexdFoot.js"></script>
  </body>
</html>
