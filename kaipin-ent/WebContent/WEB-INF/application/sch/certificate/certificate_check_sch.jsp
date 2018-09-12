<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
 	<%@ include file="/WEB-INF/application/sch/certificate/common/regHeader.jsp" %>
	<script type="text/javascript">
		var r_path='<%=basePath%>';
	</script>
	
	<script type="text/javascript" src="${STATIC_COM }/js/jquery-1.11.1.min.js"></script>
	
	<!-- uploadify -->
	<link rel="stylesheet" type="text/css" href="${STATIC_SCH }/js/uploadify/Huploadify.css"/>
	<script type="text/javascript" src="${STATIC_SCH }/js/uploadify/jquery.Huploadify.js"></script>
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
											<c:if test="${m.document_type != null &&  m.document_type != '' && m.document_type == 'licence_card'}">
												<dl>
													<dt>教育许可证</dt>
													<dd>
														<div class="eaxm-img">
															<img src="${m.document_path}" onclick="imgbox.show(this.src)" width="78px" height="78px"/>
														</div>
													</dd>
												</dl>
											</c:if>
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
											<c:if test="${m.document_type != null &&  m.document_type != '' && m.document_type == 'employee_card'}">
												<dl>
													<dt>工作证</dt>
													<dd>
														<div class="eaxm-img">
															<img src="${m.document_path}" onclick="imgbox.show(this.src)" width="78px" height="78px"/>
														</div>
													</dd>
												</dl>
											</c:if>
											<c:if test="${m.document_type != null &&  m.document_type != '' && m.document_type == 'business_card'}">
												<dl>
													<dt>名片</dt>
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
											<dd>
												<c:if test="${link.verifyStatus == '1' }">
													<c:forEach var="m" items="${feedbackList }">
														<span style="color:red;">【${m.title }】</span>${m.content }</br>
													</c:forEach>
													<a href="${BASE_PATH }/certificate/certificate?target=certificate" class="anew-sub" style="margin-top:10px;">重新提交</a>
												</c:if>
												
											</dd>
										</dl>
									</div>
									<div class="clear"></div>
								</div>
								
								<div class="return-back">
									<a href="javascript:void(0)" class="back-btn" onclick="back();">返回</a>
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
		<!--注册流程 end-->
		
		<!-- footer_reg start-->
		<%@ include file="/WEB-INF/application/main/footer.jsp" %>
		<!-- footer_reg end-->
		
		
		
		
		<script type="text/javascript" src="${STATIC_SCH }/js/regedit/regedit.js?v.<%=System.currentTimeMillis()%>"></script>
		<script type="text/javascript" src="${STATIC_SCH }/js/imgZoom.js"></script>
		<script type="text/javascript" src="${STATIC_SCH }/js/regedit/fiexdFoot.js"></script>
		
		<script type="text/javascript">
			function back(){
				location.href="<%=path%>/home";
			}
		</script>
  </body>
</html>
