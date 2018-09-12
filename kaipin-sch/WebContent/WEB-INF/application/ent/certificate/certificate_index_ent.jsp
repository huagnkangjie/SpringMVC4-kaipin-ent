<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
 	<%@ include file="/WEB-INF/application/certificate/common/regHeader.jsp" %>
	<script type="text/javascript">
		var r_path='<%=basePath%>';
	</script>
	

	<script type="text/javascript">
	
		var userId;
		var companyId;
		
		$(function(){
			userId = '${userId }';
			companyId = '${orgId }';
		});
		
		/* 返回登录 */
		function backIndex(){
			window.location.href="<%=path%>/loginController/index.do";
		}
		
		/* 跳转资质验证提交页面 */
		function certificate(){
			window.location.href= r_path + "/certificate/certificate-company?target=certificate";
		}
	</script>
  </head>
  
  <body>
		<!--header start-->
		<div class="lm-header-container">
			<div class="approve-header header">
				<h1>
					<a href="javascript:void(0)" class="logo" style="background-image: url('${STATIC_SCH }/images/reg-logs.png');"></a>
				</h1>
			</div>
		</div>
		<!--header end-->
		
		<div class="approve-center">
			<div class="ac-info">
				<div class="tips-title">
					企业资质认证
				</div>
				
				<div class="info-details">
					<div class="dt-panel">
						<div class="com-title">
							<div class="firm-info">
<!-- 								<img src="images/lm-l.png" class="logo" height="55" width="55px" alt="logo"/> -->
								<div class="news-name">
									<p>${orgName }</p>
									<a href="javascript:void(0)" class="certi no-certi" title="未认证">未认证</a>
									<!--<a href="javascript:void(0)" class="certi ing-certi" title="审核中">审核中</a>
									<a href="javascript:void(0)" class="certi already-certi" title="已认证">已认证</a>-->
								</div>
								<a href="javascript:void(0)" class="att-btn" onclick="certificate();" title="立即认证">立即认证</a>
							</div>
							
						</div>
					</div>
					
					
					<div class="info-lists">
							<p class="headline">介绍</p>
							<ul>
								<li>
									• 为了营造一个健康诚信的招聘平台，为求职者提供证实有效的优质职位，开频校招将对入驻企业进行资质审核认证。
								</li>
								<li>
									• 认证通过后，求职者将看到企业已认证标志，提升企业公信力
								</li>
								<li>
									• 认证通过后企业和企业发布的职位会有更多的推荐机会。
								</li>
								<li>
									• 认证通过后还能优先体验开频校招后续提供的增值服务。
								</li>
								<li>
									• 认证通过的企业免费使用开频校招提供的除增值服务意外的全部功能服务。
								</li>
<!-- 								<li> -->
<!-- 									<span class="color-r">• 审核服务费用：10元</span> -->
<!-- 								</li> -->
							</ul>
							
							<p class="headline">申明</p>
							<ul>
								<li>
									• 开频校招将会对用户提供的认证资料进行加密操作
								</li>
<!-- 								<li> -->
<!-- 									• 确保只能会被审核资质的工作人员阅览，严格按照<span class="color-b">开频校招员工流程规范</span>操作。 -->
<!-- 								</li> -->
								<li>
									• 审核周期为1-3个工作日，一般情况上午提交资料，下午就能审核通过。特殊情况除外。
								</li>
<!-- 								<li> -->
<!-- 									• 收取的10元审核服务费为开频校招提供认证审核服务的成本。 -->
<!-- 								</li> -->
							</ul>
						</div>
					
				</div>
				
			</div>
		</div>
		
		<!--footer start-->
		<%@ include file="/WEB-INF/application/main/footer.jsp"%>
		<!--footer end-->
		
		
<%-- 		<script type="text/javascript" src="<%=path%>/js/base.js?v.<%=System.currentTimeMillis()%>"></script> --%>
		<script type="text/javascript" src="${STATIC_SCH }/js/regedit/regedit.js?v.<%=System.currentTimeMillis()%>"></script>

	</body>
</html>
