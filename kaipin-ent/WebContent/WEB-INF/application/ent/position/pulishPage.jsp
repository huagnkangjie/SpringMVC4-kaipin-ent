<!--  header-start -->
<%@ include file="/WEB-INF/application/main/header_ent.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--  header-end -->

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%-- <script src="${STATIC_ENT }/js/jquery-easyui-1.2.6/jquery-1.7.2.min.js" charset="UTF-8" type="text/javascript"></script> --%>
<%-- 	<script src="${STATIC_ENT }/js/jquery.cookie.js" charset="UTF-8" type="text/javascript"></script> --%>
<%-- 	<link id="easyuiTheme" href="${STATIC_ENT }/js/jquery-easyui-1.2.6/themes/default/easyui.css" rel="stylesheet" type="text/css" media="screen"> --%>
<%-- 	<link href="${STATIC_ENT }/js/jquery-easyui-1.2.6/themes/icon.css" rel="stylesheet" type="text/css" media="screen"> --%>
<%-- 	<script src="${STATIC_ENT }/js/jquery-easyui-1.2.6/jquery.easyui.min.js" charset="UTF-8" type="text/javascript"></script> --%>
<%-- 	<script src="${STATIC_ENT }/js/jquery-easyui-1.2.6/locale/easyui-lang-zh_CN.js" charset="UTF-8" type="text/javascript"></script> --%>
<%-- 	<link href="${STATIC_ENT }/css/common.css" rel="stylesheet" type="text/css" media="screen"> --%>
<%-- 	<link href="${STATIC_ENT }/css/dialog.css" rel="stylesheet" type="text/css"> --%>
	<!-- 系统 -->
	<script type="text/javascript" src="${STATIC_ENT }/js/position/position.js?v.<%=System.currentTimeMillis()%>" ></script>
	<script type="text/javascript" src="${STATIC_ENT }/js/position/positionMajor.js?v.<%=System.currentTimeMillis()%>" ></script>
	<!-- 	Validator -->
	<script type="text/javascript" src="${STATIC_ENT }/js/formatJs.js" ></script>
	<script type="text/javascript" src="${STATIC_ENT }/js/formValidator.js" ></script>
	<script type="text/javascript" src="${STATIC_ENT }/js/formValidatorRegex.js" ></script>
	

	<!-- editor-->
	<script type="text/javascript" charset="utf-8" src="${STATIC_ENT }/js/editor/ueditor.config.js"></script>
	<script type="text/javascript" charset="utf-8" src="${STATIC_ENT }/js/editor/ueditor.all.js"> </script>
	<script type="text/javascript" charset="utf-8" src="${STATIC_ENT }/js/editor/lang/zh-cn/zh-cn.js"></script>
	
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
							<a href="javascript:void(0)" onclick="submit(0);" id="submitBtn" class="send">发布</a>
							<a href="javascript:void(0)" style="display:none;" id="submitBtnIng" class="send">发布...</a>
							<a href="javascript:void(0)" onclick="submit(1);" class="preview">预览</a>
							<input type="hidden" value="0" id="opering"/><!--默认0提交3  -->					
					</div>
				</div>			
				<!--send-office-tlt end-->
				
				<!--send-offer-forms start-->
				<div class="send-offer-forms">
					<form id="form1" target="blank" name="form1" method="post" action="<%=path%>/position/pulish.do">
					
						<!--必填项 开始-->
						<div class="required-forms">
							<div class="friendly-tips">
								<p class="ft-cons">必填（带<span class="heart">*</span>的内容为必填项）</p>
							</div>
							
							<div class="req-fm-lists">
								<ul id="req-select-mark">
									<li>
										<span class="rf-tips">职位名称<i class="req-heart">*</i></span>
										<input type="text" class="send-input" id="positionName" name="positionName" />
									</li>
									<li>
										<div class="clear"></div>
										<span class="rf-tips">所属行业<i class="req-heart">*</i></span>
										<div class="select_box" data-flag="true" id="industryTypeSelect">
											<span class="select_txt" >请选择</span>
											<span class="selet_open"></span>
											<input type="text" class="hide-input hide" id="industryType" name="industryCode">
<!-- 											<div class="option" id="industryTypeList"> -->
<!-- 												<a href="javascript:void(0)">IT|通信|电子|互联网</a> -->
<!-- 											</div> -->
											<div class="option" id="industryTypeList">
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
											<span class="select_txt">请选择</span>
											<span class="selet_open"></span>
											<input type="text" class="hide-input hide" id="education" name="educationCode">
<!-- 											<div class="option" id="educationList"> -->
<!-- 												<a href="javascript:void(0)">不限</a> -->
<!-- 											</div> -->
											<div class="option" id="educationList">
<!-- 												<a href="javascript:void(0)" data-tag="-1">不限</a> -->
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
											<span class="select_txt">请选择</span>
											<span class="selet_open"></span>
<!-- 											<div class="option" id="jobTypeList1"> -->
<!-- 												<a href="javascript:void(0)">XXXX</a> -->
<!-- 											</div> -->
											<div class="option" id="jobTypeList1">
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
											<span class="select_txt" id="jobTypeList2Select">请选择</span>
											<span class="selet_open"></span>
											<input type="text" class="hide-input hide" id="jobType" name="jobTypeCode">
											<div class="option" id="jobTypeList2">
<!-- 												<a href="javascript:void(0)">XXXX</a> -->
											</div>
										</div>
									</li>
									
									<li>
										<div class="clear"></div>
										<span class="rf-tips">有效期<i class="req-heart">*</i></span>
										<div class="select_box" data-flag="true" id="endTimeList">
											<span class="select_txt">请选择</span>
											<span class="selet_open"></span>
											<input type="text" class="hide-input hide" id="endTime" name="endTime">
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
										<span class="rf-tips">年龄要求<i class="req-heart">*</i></span>
										<!-- <input type="text" class="send-input" id="age" name="age"/> -->
										
										<div class="ages">
											<input type="text" id="ageStart" name="ageStart" class="age-input">	
											<span class="unit">岁</span>
										</div>
										<div class="line">一</div>
										<div class="ages">
											<input type="text" id="ageEnd" name="ageEnd" class="age-input">	
											<span class="unit">岁</span>
										</div>

									</li>
									
									<li>
										<div class="clear"></div>
										<span class="rf-tips">性别要求<i class="req-heart">*</i></span>
										<div class="select_box" data-flag="true" id="sexSelect">
											<span class="select_txt">请选择</span>
											<span class="selet_open"></span>
											<input type="text" class="hide-input hide"  id="sexs" name="sexCode">
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
											<span class="select_txt">请选择</span>
											<span class="selet_open"></span>
											<input type="text" class="hide-input hide" id="" name="">
<!-- 											<div class="option" style="display: none;" id="proviceList"> -->
<!-- 												<a href="javascript:void(0)">XXXX</a> -->
<!-- 											</div> -->
											<div class="option" id="workAreaLists">
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
											<span class="select_txt" id="workAreaName">请选择</span>
											<span class="selet_open"></span>
											<input type="text" class="hide-input hide"  id="workArea" name="locationCode">
											<div  class="option" id="cityList">
											</div>
										</div>
									</li>
									
									<li class="txt-requied">
										<span class="rf-tips">岗位职责<i class="req-heart">*</i></span>
										<div class="job-listings" id="job-respons-select">
											<textarea id="job-respons" name="positionResponsibility" style="font:12px/12px '微软雅黑', 'microsoft yahei';word-break: break-all;"></textarea>
										</div>
									</li>
									<li class="txt-requied">
										<span class="rf-tips">任职要求<i class="req-heart">*</i></span>
										<div class="job-listings" id="job-required-select">
											<textarea id="job-required" name="positionRequirements" style="font:12px/12px '微软雅黑', 'microsoft yahei';word-break: break-all;"></textarea>
										</div>
									</li>
									
									<div class="clear"></div>
								</ul>
							</div>
						</div>
						<!--必填项 结束-->
						
						<!--选填项 开始-->
						<div class="required-forms select-forms">
							<div class="friendly-tips">
								<p class="ft-cons">选填（让求职者更好的了解该职位）</p>
							</div>
							
							<div class="req-fm-lists ptional-fields">
								<ul id="req-off-select">
									<li class="txt-requied">
										<span class="rf-tips">职位摘要</span>
										<div class="job-listings" >
											<textarea id="job-summary" name="positionDetail" style="font:12px/12px '微软雅黑', 'microsoft yahei';word-break: break-all;"></textarea>
										</div>
									</li>
									<li class="txt-requied">
										<span class="rf-tips">其它要求</span>
										<div class="job-listings">
											<textarea id="other-required" name="otherInfo" class="other-required" style="font:12px/12px '微软雅黑', 'microsoft yahei';word-break: break-all;"></textarea>
										</div>
									</li>
									
									<li class="salary-req">
										<div class="clear"></div>
										<span class="rf-tips">薪水</span>
										<div class="select_box" data-flag="true">
											<span class="select_txt">面议</span>
											<span class="selet_open"></span>
											<input type="hidden" id="salarySubmitType" value="0"/>
											<input type="text" id="salaryType" name="salaryType" value="0" class="hide-input hide">
											<div class="option salary" id="salaryTypeSelect">
												<a href="javascript:void(0)" data-tag="0">面议</a>
												<a href="javascript:void(0)" data-tag="1">月薪</a>
												<a href="javascript:void(0)" data-tag="2">年薪</a>
											</div>
										</div>
										<div class="salary-range mrg-l">
											<input type="text" id="salaryStart" name="salaryStart" disabled="disabled" class="salary-input"/>
											<span class="unit">元</span>
										</div>
										<div class="line">一</div>
										<div class="salary-range">
											<input type="text" id="salaryEnd" name="salaryEnd" disabled="disabled" class="salary-input"/>
											<span class="unit">元</span>
										</div>
									</li>
									
									
									<li>
										<div class="clear"></div>
										<span class="rf-tips">工作经验</span>
										<div class="select_box" data-flag="true" id="">
											<span class="select_txt">请选择</span>
											<span class="selet_open"></span>
											<input type="text" class="hide-input hide" id=workExperience name="workExperienceCode">
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
<!-- 											<span class="select_txt">请选择</span> -->
<!-- 											<span class="selet_open"></span> -->
<!-- 											<input type="text"  id="workExperience" name="workExperienceCode" /> -->
<!-- 											<div class="option" id="workExpList"> -->
<!-- 												<a href="javascript:void(0)">无</a> -->
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
										<span class="rf-tips">招聘人数</span>
										<div class="person-num">
											<input type="text" id="numbers" name="numbers" class="send-input"/>
											<span class="unit">人</span>
										</div>
									</li>
									<li>
										<span class="rf-tips">汇报对象</span>
										<input type="text"  id="reporting" name="superior" class="send-input"/>
									</li>
									<li>
										<span class="rf-tips">所属部门</span>
										<input type="text" id="department" name="department" class="send-input"/>
									</li>
									
									<li>
										<span class="rf-tips">专业要求</span>
<!-- 										<input type="text" id="professionalRequirements" name="majorRequest" class="send-input"/> -->
										<div class="fb-input-box person-num" id="professionalRequirements">
											<span style="padding-left:10px;"></span>
										</div>
										<input type="hidden" value="" readonly="readonly" id="selectAllCodes" name="majorRequest"/>
									</li>
									<li>
										<span class="rf-tips">下属人数</span>
										<div class="person-num">
											<input type="text" id="departmentNumbers" name="departmentNumbers" class="send-input"/>
											<span class="unit">人</span>
										</div>
									</li>
									<li>
										<div class="clear"></div>
										<span class="rf-tips"> 年薪酬发放</span>
										<div class="select_box" data-flag = "true">
											<span class="select_txt">请选择</span>
											<span class="selet_open"></span>
											<input type="text" style="display:none;" id="salaryYear" name="salaryYear"  />
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
										<input type="text" id="yearHoliday" name="yearHoliday" class="send-input"/>
									</li>
									
									<li>
										<span class="rf-tips">月薪构成</span>
										<input type="text" id="salaryForms" name="salaryForms" class="send-input"/>
									</li>
									
									<li>
										<span class="rf-tips">社保福利</span>
										<input type="text" id="socialSecurity" name="socialSecurity" class="send-input"/>
									</li>
									
									<li>
										<span class="rf-tips">居住福利</span>
										<input type="text" id="live" name="live" class="send-input"/>
									</li>
									
									<li>
										<span class="rf-tips">通讯交通费</span>
										<input type="text" id="callTraffic" name="callTraffic" class="send-input"/>
									</li>
									
 								<!-- 	<li>
										<span class="rf-tips">企业性质</span>
										<input type="text" id="" name="" class="send-input"/>
									</li>
									
									<li>
										<span class="rf-tips">企业规模</span>
										<div class="ages">
											<input type="text" id="" name="" class="age-input">	
											<span class="unit">人</span>
										</div>
										<div class="line">一</div>
										<div class="ages">
											<input type="text" id="" name="" class="age-input">	
											<span class="unit">人</span>
										</div>
									</li> -->
									
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
<!-- 							<a href="javascript:void(0)" onclick="submit(0);" class="po-send">发布</a> -->
<!-- 							<a href="javascript:void(0)" onclick="submit(1);" class="po-edit">预览</a> -->
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
								<span class="name-info"></span> 
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
			    var companyDetail = textareaEditor(companyDetail,"company-detail");//其他要求
			    
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
			
			    
			function submit(oper){
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
					if(oper == 1){
						$("#status").val("1");
						$("#form1").attr("target","_blank");
						$("#form1").submit();
					}else{
						var opering = $("#opering").val();
						if(opering == '0'){
							$("#submitBtn").hide();
							$("#submitBtnIng").show();
							$("#opering").val("3");
							$("#status").val("0");
							$("#form1").removeAttr("target");
							document.form1.submit();
						}
						
					}
	 			}else{
					return;
				}
			}
			
		</script>
		
		
		<!-- 		 header-start -->
		<%@ include file="/WEB-INF/application/main/footer.jsp"%>
		<!-- 		 header-end -->
		
		
	</body>
</html>
