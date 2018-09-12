<!--  header-start -->
<%@ include file="/WEB-INF/pages/main/header.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--  header-end -->
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%-- 	<script src="<%=path%>/js/jquery-easyui-1.2.6/jquery-1.7.2.min.js" charset="UTF-8" type="text/javascript"></script> --%>
<%-- 	<script src="<%=path%>/js/jquery.cookie.js" charset="UTF-8" type="text/javascript"></script> --%>
<%-- 	<link id="easyuiTheme" href="<%=path%>/js/jquery-easyui-1.2.6/themes/default/easyui.css" rel="stylesheet" type="text/css" media="screen"> --%>
<%-- 	<link href="<%=path%>/js/jquery-easyui-1.2.6/themes/icon.css" rel="stylesheet" type="text/css" media="screen"> --%>
<%-- 	<script src="<%=path%>/js/jquery-easyui-1.2.6/jquery.easyui.min.js" charset="UTF-8" type="text/javascript"></script> --%>
<%-- 	<script src="<%=path%>/js/jquery-easyui-1.2.6/locale/easyui-lang-zh_CN.js" charset="UTF-8" type="text/javascript"></script> --%>
<%-- 	<link href="<%=path%>/css/common.css" rel="stylesheet" type="text/css" media="screen"> --%>
<%-- 	<link href="<%=path%>/css/dialog.css" rel="stylesheet" type="text/css"> --%>
	<!-- 系统 -->
	<script type="text/javascript" src="<%=path%>/js/position.js?v.<%=System.currentTimeMillis()%>" ></script>
	<script type="text/javascript" src="<%=path%>/js/position/positionMajor.js?v.<%=System.currentTimeMillis()%>" ></script>
	<!-- validator -->
	<script type="text/javascript" src="<%=path%>/js/formatJs.js" ></script>
	<script type="text/javascript" src="<%=path%>/js/formValidator.js" ></script>
	<script type="text/javascript" src="<%=path%>/js/formValidatorRegex.js" ></script>
	<!-- editor-->
	<script type="text/javascript" charset="utf-8" src="<%=path%>/js/editor/ueditor.config.js"></script>
	<script type="text/javascript" charset="utf-8" src="<%=path%>/js/editor/ueditor.all.js"> </script>
	<script type="text/javascript" charset="utf-8" src="<%=path%>/js/editor/lang/zh-cn/zh-cn.js"></script>
	
	<script type="text/javascript">
		var salaryType = '${positionInfo.salaryType}';
		$(function(){
			if(salaryType == "0"){
				$("#salaryStart").attr("disabled","disabled");
				$("#salaryEnd").attr("disabled","disabled");
				$("#salaryStart").val("");
				$("#salaryEnd").val("");
				$("#salaryName").html("面议");
			}else if(salaryType == "1"){
				$("#salaryName").html("月薪");
			}else if(salaryType == "2"){
				$("#salaryName").html("年薪");
			}
		});
		
		function back(){
			//window.history.back();
			 location.href=r_path+"/position/init.do";
		}
	</script>
	<style type="text/css">
		.edui-container .edui-toolbar{display: none;}
		.edui-container{box-shadow:none;border:0;}
	</style>
		
		
	<!--发布职位中心 开始-->
		<div class="post-office-center">
			<div class="post-office">
				
				<!--send-office-tlt start-->
				<div class="send-office-tlt">
					<h5>发布职位</h5>
					<div class="send-Of-btn fr">
							<a href="javascript:void(0)" onclick="submit();" id="submitBtn" class="send">发布</a>
							<a href="javascript:void(0)" style="display:none;" id="submitBtnIng" class="send">发布...</a>
							<a href="javascript:void(0)" onclick="back();" class="preview">取消</a>
							<input type="hidden" value="0" id="opering"/><!--默认0提交3  -->	
					</div>
				</div>			
				<!--send-office-tlt end-->
				
				<!--send-offer-forms start-->
				<div class="send-offer-forms">
					<form id="form1" name="form1" method="post" action="<%=path%>/position/edit.do">
						<input type="id" name="id" style="display:none;" value="${positionInfo.id }"/>
						<!--必填项 开始-->
						<div class="required-forms">
							<div class="friendly-tips">
								<p class="ft-cons">必填（带<span class="heart">*</span>的内容为必填项）</p>
							</div>
							
							<div class="req-fm-lists">
								<ul id="req-select-mark">
									<li>
										<div class="clear"></div>
										<span class="rf-tips">职位名称<i class="req-heart">*</i></span>
										<input type="text" class="send-input" value="${positionInfo.positionName }" id="positionName" name="positionName" />
									</li>
									<li>
										<div class="clear"></div>
										<span class="rf-tips">所属行业<i class="req-heart">*</i></span>
										<div class="select_box" data-flag="true" id="industryTypeSelect">
											<span class="select_txt" >${industryTypeName }</span>
											<span class="selet_open"></span>
											<input type="text" class="hide-input hide" value="${positionInfo.industryCode }" id="industryTypeEd" name="industryCode">
											<div class="option" id="industryTypeList">
<!-- 												<a href="javascript:void(0)">IT|通信|电子|互联网</a> -->
												<c:forEach items="${industryTypeList }" var="m">
													<a href="javascript:void(0)" data-tag="${m.industry_code }">${m.industry_name }</a>
												</c:forEach>
											</div>
										</div>
									</li>
									<li>
									<div class="clear"></div>
										<span class="rf-tips">学历<i class="req-heart">*</i></span>
										<div class="select_box" data-flag="true" id="eduSelect">
											<span class="select_txt">${eduName }</span>
											<span class="selet_open"></span>
											<input type="text" class="hide-input hide" value="${positionInfo.educationCode }" id="educationEd" name="educationCode">
											<div class="option" id="educationList">
<!-- 												<a href="javascript:void(0)">不限</a> -->
												<c:forEach items="${eduList }" var="m">
													<a href="javascript:void(0)"  data-tag="${m.education_code }">${m.education_name }</a>
												</c:forEach>
											</div>
										</div>
									</li>
									<li class="linkage-select">
										<div class="clear"></div>
										<span class="rf-tips">岗位职能<i class="req-heart">*</i></span>
										<div class="select_box" data-flag="true" id="jobTpyeSelect1">
											<span class="select_txt">${jobType1Name }</span>
											<span class="selet_open"></span>
											<div class="option" id="jobTypeList1">
<!-- 												<a href="javascript:void(0)">XXXX</a> -->
												<c:forEach items="${jobTypeList }" var="m">
													<a href="javascript:void(0)"  data-tag="${m.job_type_code }">${m.job_type_name }</a>
												</c:forEach>
											</div>
										</div>
									</li>
									
									<li class="linkage-select">
										<div class="clear"></div>
										<span class="rf-tips"></span>
										<div class="select_box" data-flag="true" id="jobTpyeSelect2">
											<span class="select_txt" id="jobTypeList2Select">${jobType2Name }</span>
											<span class="selet_open"></span>
											<input type="text" class="hide-input hide" value="${positionInfo.jobTypeCode }" id="jobType" name="jobTypeCode">
											<div class="option" id="jobTypeList2">
<!-- 												<a href="javascript:void(0)">XXXX</a> -->
											</div>
										</div>
									</li>
									
									<li>
											<div class="clear"></div>
<!-- 										<span class="rf-tips">有限期<i class="req-heart">*</i></span> -->
<%-- 										<input type="text" class="send-input" value="${positionInfo.endTime }" id="endTime" name="endTime"/> --%>
										<span class="rf-tips">有效期</span>
										<div class="select_box" data-flag="true" id="endTimeList">
											<span class="select_txt">${showMouths }</span>
											<span class="selet_open"></span>
											<input type="text" class="hide-input hide" value="${mouths }" id="endTime" name="endTime">
											<div class="option endtime" >
												<a href="javascript:void(0)" data-tag="1">1个月</a>
												<a href="javascript:void(0)" data-tag="2">2个月</a>
												<a href="javascript:void(0)" data-tag="3">3个月</a>
												<a href="javascript:void(0)" data-tag="4">4个月</a>
												<a href="javascript:void(0)" data-tag="5">5个月</a>
												<a href="javascript:void(0)" data-tag="6">6个月</a>
												<a href="javascript:void(0)" data-tag="7">7个月</a>
												<a href="javascript:void(0)" data-tag="8">8个月</a>
												<a href="javascript:void(0)" data-tag="9">9个月</a>
												<a href="javascript:void(0)" data-tag="10">10个月</a>
												<a href="javascript:void(0)" data-tag="11">11个月</a>
												<a href="javascript:void(0)" data-tag="12">12个月</a>
												<a href="javascript:void(0)" data-tag="0">长期有效</a>
											</div>
										</div>
									</li>
									<li>
										<div class="clear"></div>
										<span class="rf-tips">年龄要求<i class="req-heart">*</i></span>
<%-- 										<input type="text" class="send-input" value="${positionInfo.age }" id="age" name="age"/> --%>
										<div class="ages">
											<input type="text" id="ageStart" name="ageStart" value="${positionInfo.ageStart }" class="age-input">	
											<span class="unit">岁</span>
										</div>
										<div class="line">一</div>
										<div class="ages">
											<input type="text" id="ageEnd" name="ageEnd" value="${positionInfo.ageEnd }" class="age-input">	
											<span class="unit">岁</span>
										</div>
									</li>
									
									<li>
										<div class="clear"></div>
										<span class="rf-tips">性别要求<i class="req-heart">*</i></span>
										<div class="select_box" data-flag="true" id="sexSelect">
											<span class="select_txt">${sexName }</span>
											<span class="selet_open"></span>
											<input type="text" class="hide-input hide"  value="${positionInfo.sexCode }" id="sexs" name="sexCode">
											<div class="option sex">
												<a data-tag="0" href="javascript:void(0)">不限</a>
												<a data-tag='2' href="javascript:void(0)">男</a>
												<a data-tag='1' href="javascript:void(0)">女</a>
											</div>
										</div>
									</li>
									
									
									
									<li class="linkage-select">
										<div class="clear"></div>
										<span class="rf-tips">工作地区<i class="req-heart">*</i></span>
										<div class="select_box" data-flag="true" id="workArea1">
											<span class="select_txt">${workArea1Name }</span>
											<span class="selet_open"></span>
											<input type="text" class="hide-input hide" id="" name="">
											<div class="option" style="display: none;" id="workAreaLists">
<!-- 												<a href="javascript:void(0)">XXXX</a> -->
												<c:forEach items="${workAreaList }" var="m">
													<a href="javascript:void(0)"  data-tag="${m.location_code }">${m.location_name }</a>
												</c:forEach>
											</div>
										</div>
									</li>
									
									<li class="linkage-select">
										<div class="clear"></div>
										<span class="rf-tips"></span>
										<div class="select_box" data-flag="true" id="workArea2">
											<span class="select_txt" id="workAreaName">${workArea2Name }</span>
											<span class="selet_open"></span>
											<input type="text" class="hide-input hide" value="${positionInfo.locationCode }"  id="workArea" name="locationCode">
											<div  class="option" id="cityList">
<!-- 												<a href="javascript:void(0)">XXXX</a> -->
											</div>
										</div>
									</li>
									
									<li class="txt-requied">
										<div class="clear"></div>
										<span class="rf-tips">岗位职责<i class="req-heart">*</i></span>
										<div class="job-listings" id="job-respons-select">
											<textarea id="job-respons" value='${positionInfo.positionResponsibility }' name="positionResponsibility" style="font:12px/12px '微软雅黑', 'microsoft yahei';word-break: break-all;"></textarea>
										</div>
									</li>
									<li class="txt-requied">
										<div class="clear"></div>
										<span class="rf-tips">任职要求<i class="req-heart">*</i></span>
										<div class="job-listings" id="job-required-select">
											<textarea id="job-required" value='${positionInfo.positionRequirements }' name="positionRequirements" style="font:12px/12px '微软雅黑', 'microsoft yahei';word-break: break-all;"></textarea>
										</div>
									</li>
									
									<div class="clear"></div>
								</ul>
							</div>
						</div>
						<!--必填项 结束-->
						
						<!--选填项 开始-->
						<div class="required-forms">
							<div class="friendly-tips">
								<p class="ft-cons">选填（让求职者更好的了解该职位）</p>
							</div>
							
							<div class="req-fm-lists ptional-fields">
								<ul id="req-off-select">
									<li class="txt-requied">
										<div class="clear"></div>
										<span class="rf-tips">职位摘要</span>
										<div class="job-listings" >
											<textarea id="job-summary" name="positionDetail" style="font:12px/12px '微软雅黑', 'microsoft yahei';word-break: break-all;"></textarea>
										</div>
									</li>
									<li class="txt-requied">
										<div class="clear"></div>
										<span class="rf-tips">其它要求</span>
										<div class="job-listings">
											<textarea id="other-required" name="otherInfo" class="other-required" style="font:12px/12px '微软雅黑', 'microsoft yahei';word-break: break-all;"></textarea>
										</div>
									</li>
									
									<li class="salary-req">
										<div class="clear"></div>
										<span class="rf-tips">薪水</span>
										<div class="select_box" data-flag="true">
											<span class="select_txt" id="salaryName">面议</span>
											<span class="selet_open"></span>
											<input type="hidden" id="salarySubmitType" value="0"/>
											<input type="text" value="${positionInfo.salaryType }" id="salaryType" name="salaryType" class="hide-input hide">
											<div class="option salary" id="salaryTypeSelect">
												<a href="javascript:void(0)" data-tag="0">面议</a>
												<a href="javascript:void(0)" data-tag="1">月薪</a>
												<a href="javascript:void(0)" data-tag="2">年薪</a>
											</div>
										</div>
										
										<div class="salary-range mrg-l">
											<input type="text" value="${positionInfo.salaryStart }" id="salaryStart" name="salaryStart" class="salary-input"/>
											<span class="unit">元</span>
										</div>
										<div class="line">一</div>
										<div class="salary-range">
											<input type="text" value="${positionInfo.salaryEnd }" id="salaryEnd" name="salaryEnd" class="salary-input"/>
											<span class="unit">元</span>
										</div>
									</li>
									
									<li>
										<div class="clear"></div>
										<span class="rf-tips">工作经验<i class="req-heart">*</i></span>
										<div class="select_box" data-flag="true" id="">
											<span class="select_txt">${workExpName }</span>
											<span class="selet_open"></span>
											<input type="text" class="hide-input hide" id=workExperience name="workExperienceCode" value="${positionInfo.workExperienceCode }">
<!-- 											<div class="option" id="educationList"> -->
<!-- 												<a href="javascript:void(0)">不限</a> -->
<!-- 											</div> -->
											<div class="option" id="educationList">
<!-- 												<a href="javascript:void(0)" data-tag="-1">不限</a> -->
												<c:forEach items="${expList }" var="m">
													<a href="javascript:void(0)"  data-tag="${m.work_experience_code }">${m.work_experience_name }</a>
												</c:forEach>
											</div>
										</div>
									</li>
									
<!-- 									<li> -->
<!-- 										<div class="clear"></div> -->
<!-- 										<span class="rf-tips">工作经验</span> -->
<!-- 										<div class="select_box" data-flag="true"> -->
<%-- 											<span class="select_txt">${workExpName }</span> --%>
<!-- 											<span class="selet_open"></span> -->
<%-- 											<input type="text" id="workExperienceEd" class="hide-input hide" value="${positionInfo.workExperience }" name="workExperience"  /> --%>
<!-- 											<div class="option" id="workExpList"> -->
<!-- 												<a href="javascript:void(0)">不限</a> -->
<!-- 												<a href="javascript:void(0)">1年及以上</a> -->
<!-- 												<a href="javascript:void(0)">2年及以上</a> -->
<!-- 												<a href="javascript:void(0)">3年及以上</a> -->
<!-- 												<a href="javascript:void(0)">4年及以上</a> -->
<!-- 												<a href="javascript:void(0)">5年及以上</a> -->
<!-- 												<a href="javascript:void(0)">5-8年</a> -->
<!-- 												<a href="javascript:void(0)">8-10年</a> -->
<!-- 												<a href="javascript:void(0)">10年及以上</a> -->
<!-- 											</div> -->
<!-- 										</div> -->
<!-- 									</li> -->


									<li>
										<div class="clear"></div>
										<span class="rf-tips">招聘人数</span>
										<div class="person-num">
											<input type="text" value="${positionInfo.numbers }" id="numbers" name="numbers" class="send-input"/>
											<span class="unit">人</span>
										</div>
									</li>
									<li>
										<div class="clear"></div>
										<span class="rf-tips">汇报对象</span>
										<input type="text" value="${positionInfo.superior }" id="reporting" name="superior" class="send-input"/>
									</li>
									<li>
										<div class="clear"></div>
										<span class="rf-tips">所属部门</span>
										<input type="text" value="${positionInfo.department }" id="department" name="department" class="send-input"/>
									</li>
									
									<li>
										<span class="rf-tips">专业要求</span>
<!-- 										<input type="text" id="professionalRequirements" name="majorRequest" class="send-input"/> -->
										<div class="fb-input-box person-num" id="professionalRequirements">
											<span style="padding-left:10px;">${majroLenght }</span>
										</div>
										<input type=hidden value="${positionInfo.majorRequest }" readonly="readonly" id="selectAllCodes" name="majorRequest"/>
									</li>
									<li>
										<span class="rf-tips">下属人数</span>
										<div class="person-num">
											<input type="text" value="${positionInfo.departmentNumbers }" id="departmentNumbers" name="departmentNumbers" class="send-input"/>
<!-- 											<input type="text" id="departmentNumbers" name="departmentNumbers" class="send-input"/> -->
											<span class="unit">人</span>
										</div>
									</li>
									<li>
										<div class="clear"></div>
										<span class="rf-tips"> 年薪酬发放</span>
										<div class="select_box" data-flag = "true">
											<span class="select_txt">${salarYearName }</span>
											<span class="selet_open"></span>
											<input type="text" id="salaryYear" value="${positionInfo.salaryYear }" name="salaryYear"  style="display:none;"/>
											<div class="option salary-year">
												<a href="javascript:void(0)" data-tag="0">无</a>
												<a href="javascript:void(0)" data-tag="13">13个月</a>
												<a href="javascript:void(0)" data-tag="14">14个月</a>
												<a href="javascript:void(0)" data-tag="15">15个月</a>
												<a href="javascript:void(0)" data-tag="16">16个月</a>
												<a href="javascript:void(0)" data-tag="17">17个月</a>
												<a href="javascript:void(0)" data-tag="18">18个月</a>
												<a href="javascript:void(0)" data-tag="19">19个月</a>
												<a href="javascript:void(0)" data-tag="20">20个月</a>
												<a href="javascript:void(0)" data-tag="21">21个月</a>
												<a href="javascript:void(0)" data-tag="22">22个月</a>
												<a href="javascript:void(0)" data-tag="23">23个月</a>
												<a href="javascript:void(0)" data-tag="24">24个月以上</a>
											</div>
										</div>
									</li>
									
									<li>
										<span class="rf-tips">年假福利</span>
										<input type="text" value="${positionInfo.yearHoliday }" id="yearHoliday" name="yearHoliday" class="send-input"/>
									</li>
									
									<li>
										<span class="rf-tips">月薪构成</span>
										<input type="text" value="${positionInfo.salaryForms }" id="salaryForms" name="salaryForms" class="send-input"/>
									</li>
									
									<li>
										<span class="rf-tips">社保福利</span>
										<input type="text" value="${positionInfo.socialSecurity }" id="socialSecurity" name="socialSecurity" class="send-input"/>
									</li>
									
									<li>
										<span class="rf-tips">居住福利</span>
										<input type="text" value="${positionInfo.live }" id="live" name="live" class="send-input"/>
									</li>
									
									<li>
										<span class="rf-tips">通讯交通费</span>
										<input type="text" value="${positionInfo.callTraffic }" id="callTraffic" name="callTraffic" class="send-input"/>
									</li>
									
<!-- 									<li> -->
<!-- 										<span class="rf-tips">企业性质</span> -->
<!-- 										<input type="text" id="" name="" class="send-input"/> -->
<!-- 									</li> -->
									
<!-- 									<li> -->
<!-- 										<span class="rf-tips">企业规模</span> -->
<!-- 										<div class="ages"> -->
<!-- 											<input type="text" id="" name="" class="age-input">	 -->
<!-- 											<span class="unit">人</span> -->
<!-- 										</div> -->
<!-- 										<div class="line">一</div> -->
<!-- 										<div class="ages"> -->
<!-- 											<input type="text" id="" name="" class="age-input">	 -->
<!-- 											<span class="unit">人</span> -->
<!-- 										</div> -->
<!-- 									</li> -->
									
									<li class="txt-requied position-summary">
										<span class="rf-tips">公司简介</span>
										<div class="job-listings">
											<textarea id="company-detail" name="companyIntroduction" style="font:12px/12px '微软雅黑', 'microsoft yahei';word-break: break-all;" rows="" cols=""></textarea>
										</div>
										
									</li>
									<div class="clear"></div>
								</ul>
							</div>
						</div>
						<!--选填项 结束-->
					
						<!-- 默认初始化状态为预览 -->
						<input type="hidden" name="status" value="0" id="status"/> 
					
						<!--操作按钮 组 开始-->
<!-- 						<div class="prev-operate"> -->
<!-- 							<a href="javascript:void(0)" onclick="submit();" class="po-send">发布</a> -->
<!-- 							<a href="javascript:void(0)" onclick="back();" class="po-edit">取消</a> -->
<!-- 						</div> -->
						<!--操作按钮 组 结束-->
					
					</form>
				</div>
				<!--send-offer-forms end-->
				
			</div>
		</div>
		<!--发布职位中心 结束-->
		
		
		<div class="tzui-loading-overlay" id="send-position-overlay" style="display: none;"></div>
		<!--专业要求选择 start-->
		
		<!--纯色透明层-->
		<div class="transparent-layer" id="transparent-layer"></div>
		<div class="requirements-select" id="requirements-select">
			<a href="javascript:;" class="close-xx" id="colseSelectMajor" title="关闭"></a>
			<div class="req-panel">
				<div class="title">
					<h5>请选择该职位的专业要求</h5>
					<span>最多选择5项</span>
				</div>
				
				<div class="result-of-select">
					<span class="tips">已选择 : </span>
					<ul class="rs-value" id="result-of-select">
						<!--<li>1</li>
						<li>2</li>
						<li>3</li>
						<li>4</li>
						<li>5</li>-->
					</ul>
					<div class="clear"></div>
				</div>
				
				<div class="name-of-profession kind-profession">
					<ul id="sort-profession-select" >
						<li data-sort = "sort_of_16">
							<a href="javascript:;">
								<span class="name-info">专业名称类</span> 
								<span class="arrows"></span>
							</a>
						</li>
					</ul>
				</div>
				
				<!-- <div class="name-of-profession other-profession">
					<h5>其他专业</h5>
					<ul>
						<li>
							<a href="javascript:;">
								<span class="name-info">其他专业</span> 
								<span class="arrows"></span>
							</a>
						</li>
						<li>
							<a href="javascript:;">
								<span class="name-info">不限专业</span> 
								<span class="arrows"></span>
							</a>
						</li>
					</ul>
				</div>
				 -->
				<div class="submitBox">
					<a href="javascript:;" class="subBth subBth-sure">确定</a>
					<a href="javascript:;" class="subBth subBth-cancle" id="cancleSelectMajor">取消</a>
				</div>
			</div>
		</div>
		<!--专业要求选择 end-->
		
		
		
		<!--详细分类  start-->
			<div class="fication-details" id="fication-details" style="display: none;">
				<div class="info-lists-box">
					<div class="tlt-name">
						<h5 id="className">专业名称类</h5>
					</div>
					<div class="name-of-profession other-profession kind-of-pro-lists">
						<ul id="major-small-class">
							<li data-flag = "close" data-sort = "sort_of_1">
								<a href="javascript:;">
									<span class="name-info">专业名称类1</span> 
									<span class="right"></span>
								</a>
							</li>
						
						</ul>
					</div>
					
					<div class="submitBox">
						<a href="javascript:;" class="subBth subBth-sure">确定</a>
						<a href="javascript:;" class="subBth subBth-cancle">取消</a>
					</div>
				</div>
			</div>
			<!--详细分类  end-->
			
			<div class="tips-of-select" id="tips-of-select">
				<p>专业分类选择不能超过5个！</p>
			</div>
		
		
		
		<script type="text/javascript">
			var jobRespons;
			var jobRequired;
			
			$(function(){
				var flag = "";
				selectHide($(document),"click");
				selectHide($(document),"scroll");
				openSelect($("#req-select-mark"),$("#req-off-select"));
				openSelect($("#req-off-select"),$("#req-select-mark"));
				function openSelect(obj,$siblings){
					obj.find(".select_box").click(function(event){
							event.stopPropagation();
							var $this = $(this);
							flag = $this.data("flag")
							$this.find(".option").toggle();
							$this.parents("li").siblings().find(".select_box .option").hide();
							$siblings.find(".select_box .option").hide();;
					});
				}
				getValue($("#req-select-mark").find('.option'));
				getValue($("#req-off-select").find('.option'));
				function getValue($obj){
					$obj.on("click","a",function(){
						var $this = $(this);
						var value=$this.text();
						$this.parent().siblings(".select_txt").text(value);
						if(flag){
							var val = $this.data("tag");
							$this.parent().siblings(".hide-input").val(val);	
						}
					});
				}
				function selectHide(obj,$fun){
					obj.on($fun,function(event){
						var eo=$(event.target);
						if($(".select_box").is(":visible") && eo.attr("class")!="option" && !eo.parent(".option").length){
							$('.option').hide();
						}
					});
				}
				
				
				
				/*
				 *   初始化编辑器
				 */
			        
			    jobRespons =  textareaEditor(jobRespons,"job-respons");//岗位职责
			    jobRequired = textareaEditor(jobRequired,"job-required");//任职要求
			    var jobSummary = textareaEditor(jobSummary,"job-summary");//职位摘要
			    var otherRequired = textareaEditor(otherRequired,"other-required");//其他要求
			    var companyDetail = textareaEditor(companyDetail,"company-detail");//公司简介
			    
			    var jobResponsVal = '${positionInfo.positionResponsibility }';
			    var jobRequiredVal = '${positionInfo.positionRequirements }';
			    var jobSummaryVal = '${positionInfo.positionDetail }';
			    var otherRequiredVal = '${positionInfo.otherInfo }';
			    var companyDetailVal = '${positionInfo.companyIntroduction }';
			    
			   
			    setVals(jobRespons,jobResponsVal);
			    setVals(jobRequired,jobRequiredVal);
			    setVals(jobSummary,jobSummaryVal);
			    setVals(otherRequired,otherRequiredVal);
			    setVals(companyDetail,companyDetailVal);
			    
			    jobRespons.addListener('blur',function(){
			        var flag = jobRespons.hasContents();
			         if(flag){
			        	 $("#job-respons-select").removeClass("error-input");
			         }else{
			        	 $("#job-respons-select").addClass("error-input");
			         }
			    });
			    
			    jobRequired.addListener('blur',function(){
			        var flags = jobRequired.hasContents();
			         if(flags){
			        	 $("#job-required-select").removeClass("error-input");
			         }else{
			        	 $("#job-required-select").addClass("error-input");
			         }
			    });
			    
			    function setVals(obj,val){
			    	obj.addListener("ready", function () {
			    		obj.setContent(val);
					});
			    }

			    
			    function textareaEditor(objNmae,obj){
					objNmae = UE.getEditor(obj,{
					 	toolbars:[],/*工具栏设置*/
					 	wordCount:false,/*字数统计*/
				    	contextMenu:[],/*屏蔽右键菜单*/
				    	elementPathEnabled:false,/*标签结构*/
				    	autoHeightEnabled:false,/*自动升高*/
				    	initialFrameWidth:558,/*宽度*/
				    	initialFrameHeight:87/*高度*/
				    });
				    return objNmae;
				}
				
			});
			
			function submit(){
				var flag = jobRespons.hasContents();
			    if(flag){
		        	 $("#job-respons-select").removeClass("error-input");
		         }else{
		        	 $("#job-respons-select").addClass("error-input");
		         }
			    var flags = jobRequired.hasContents();
			    if(flags){
		        	 $("#job-required-select").removeClass("error-input");
		         }else{
		        	 $("#job-required-select").addClass("error-input");
		         }
			    var ss = $("#salaryStart").val();
				var se = $("#salaryEnd").val();
				if(ss.length > 0){
					if(se.length == 0){
						$("#salaryEnd").addClass("error-input");
						return;
					}
				}
				if($.formValidator.pageIsValid('1') && flag && flags){
					var opering = $("#opering").val();
					if(opering == '0'){
						$("#submitBtn").hide();
						$("#submitBtnIng").show();
						$("#opering").val("3");
						document.form1.submit();
					}
				}else{
					return;
				}
			}
		</script>
		
		
		<!-- 		 header-start -->
		<%@ include file="/WEB-INF/pages/main/footer.jsp"%>
		<!-- 		 header-end -->
		
		
	</body>
</html>
