<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<link rel="stylesheet" href="${BASE_PATH}/public/assets/css/basic.css" />
	<script src="${BASE_PATH}/public/js/jquery.js"></script>
	<script type="text/javascript">
		var logo = "";
		$(function (){
			
		});
	</script>
</head>
<body>

	<!--job-preview start-->
		<div class="job-preview">
			
			<!--prev-operate start-->
			<div class="prev-operate">
				<h5>职位详情</h5>
			</div>
			<!--prev-operate end-->
			
			<!--prev-details start-->
			<div class="prev-details">
				
				<!--position-info 职位信息  开始-->
				<div class="position-info">
					
					<div class="job-tltInfo">
						<h4>${positionInfo.positionName }</h4>
						<p>更新日期：${createTime }(有效期:${positionInfo.endTime }）<span>工作地区：${positionInfo.locationCode }</span></p>
					</div>
					
					<div class="requires-info">
						
						<div class="tatol-infoSty">
							<div class="tinfo-tlt">岗位职责</div>
							<div class="tinfo-cons tinfo-lists">
								${positionInfo.positionResponsibility }
							</div>
						</div>
						
						<div class="tatol-infoSty">
							<div class="tinfo-tlt">任职要求</div>
							<div class="tinfo-cons tinfo-lists">
								${positionInfo.positionRequirements }
							</div>
						</div>
						
						<c:if test="${positionInfo.positionDetail != ''}">
							<div class="tatol-infoSty">
								<div class="tinfo-tlt">职位摘要</div>
								<div class="tinfo-cons">
									${positionInfo.positionDetail }
								</div>
							</div>
						</c:if>
	
						<c:if test="${positionInfo.otherInfo != ''}">
							<div class="tatol-infoSty">
								<div class="tinfo-tlt">其它要求</div>
								<div class="tinfo-cons">
									${positionInfo.otherInfo }
								</div>
							</div>
						</c:if>					
						
						<c:if test="${positionInfo.companyIntroduction != ''}">
							<div class="tatol-infoSty">
								<div class="tinfo-tlt">公司简介</div>
								<div class="tinfo-cons">
									 ${positionInfo.companyIntroduction }
								</div>
							</div>
						</c:if>
						
					</div>
					
				</div>
				<!--position-info 职位信息  结束-->
				
				<!--position-attribute 职位属性 开始-->
				<div class="position-attribute" id="position-attribute">
					<div class="attr-info-lists">
						<div class="attr-tlt"></div>
						<div class="attr-details">
							<div class="tinfo-tlt">
								职位属性
							</div>
							
							<div class="other-info">
								<p><span>行业：</span>${positionInfo.industryCode }</p>
								<p><span>学历：</span>${positionInfo.educationCode }</p>
								<p><span>职能：</span>${positionInfo.jobTypeCode }</p>
								<c:if test="${positionInfo.superior != ''}">
									<p><span>汇报对象：</span>${positionInfo.superior }</p>
								</c:if>
								
								<p><span>年龄：</span>${positionInfo.ageStart } - ${positionInfo.ageEnd }岁</p>
								<p><span>性别：</span>${positionInfo.sexCode }</p>
								<p><span>薪水：</span>${salaryTypeVal }</p>
								
								<c:if test="${positionInfo.workExperienceCode != ''}">
									<p><span>经验：</span>${positionInfo.workExperienceCode }</p>
								</c:if>
								
								<c:if test="${positionInfo.numbers != ''}">
									<p><span>招聘人数：</span>
										${positionInfo.numbers}人
									</p>
								</c:if>
								
								<c:if test="${positionInfo.department != ''}">
									<p><span>所属部门：</span>${positionInfo.department }</p>
								</c:if>
								
								<c:if test="${positionInfo.majorRequest != ''}">
									<p><span>专业要求：</span>${positionInfo.majorRequest }</p>
								</c:if>
								
								<c:if test="${departmentNumbers != ''}">
									<p><span>下属人数：</span>${departmentNumbers }</p>
								</c:if>
								
								<c:if test="${positionInfo.salaryYear != ''}">
									<p><span>年薪酬发放：</span>${positionInfo.salaryYear }</p>
								</c:if>
								
								<c:if test="${positionInfo.yearHoliday != ''}">
									<p><span>年假福利：</span>${positionInfo.yearHoliday }</p>
								</c:if>
								
								<c:if test="${positionInfo.salaryForms != ''}">
									<p><span>月薪构成：</span>${positionInfo.salaryForms }</p>
								</c:if>
								
								<c:if test="${positionInfo.socialSecurity != ''}">
									<p><span>社保福利：</span>${positionInfo.socialSecurity }</p>
								</c:if>
								
								<c:if test="${positionInfo.live != ''}">
									<p><span>居住福利：</span>${positionInfo.live }</p>
								</c:if>
								
								<c:if test="${positionInfo.callTraffic != ''}">
									<p><span>通讯交通费：</span>${positionInfo.callTraffic }</p>
								</c:if>
								
								<c:if test="${companyType != ''}">
									<p><span>企业性质：</span>${companyType }</p>
								</c:if>
								
								<c:if test="${peopleNumbers != ''}">
									<p><span>企业规模：</span>
										${peopleNumbers }人
									</p>
								</c:if>
								
							</div>
						</div>
					</div>
				</div>
				<!--position-attribute 职位属性 结束-->
				
			</div>
			<!--prev-details end-->
		</div>
		<!--job-preview end-->

	</body>
</html>