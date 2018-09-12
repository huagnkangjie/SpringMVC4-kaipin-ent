<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!-- header-start -->
<%@ include file="/WEB-INF/application/main/header_ent.jsp"%>
<!-- header-end -->
	<script type="text/javascript" src="${STATIC_SCH }/js/home/home.js"></script>
	<!-- 截图插件 -->
	<link href="${STATIC_SCH }/js/imgareaselect/css/imgareaselect-default.css" rel="stylesheet" type="text/css" />
    <script src="${STATIC_SCH }/js/imgareaselect/jquery.imgareaselect.pack.js" type="text/javascript"></script>
	<!-- uploadify -->
	<link href="${STATIC_SCH }/css/newPosi.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css" href="${STATIC_SCH }/js/uploadify/Huploadify.css"/>
	<script type="text/javascript"  src="${STATIC_SCH }/js/uploadify/jquery.Huploadify.js"></script>
	<!-- 图片裁剪 -->
	<link href="${STATIC_SCH }/js/image/css/cropper.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${STATIC_SCH }/js/image/bootstrap.min.js?v.<%=System.currentTimeMillis()%>"></script>
	<script type="text/javascript" src="${STATIC_SCH }/js/image/cropper.js?v.<%=System.currentTimeMillis()%>"></script>
	
	<!-- dalog-->
	<link href="${STATIC_ENT }/css/dialog.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="${STATIC_ENT }/js/dialog3.0.js" ></script>
	
	<!-- editor-->
	<script type="text/javascript" charset="utf-8" src="${STATIC_ENT }/js/editor/ueditor.config.js"></script>
	<script type="text/javascript" charset="utf-8" src="${STATIC_ENT }/js/editor/ueditor.all.js"> </script>
	<script type="text/javascript" charset="utf-8" src="${STATIC_ENT }/js/editor/lang/zh-cn/zh-cn.js"></script>
	
	<!-- 时间 -->
	<link rel="stylesheet" href="${STATIC_ENT }/css/jquery.datetimepicker.css" />
	<script type="text/javascript" src="${STATIC_ENT }/js/jquery.datetimepicker.js"></script>
	
	<script type="text/javascript">
		var userId;
		$(function(){
			userId = '${userId}';
			getFollowAndFans(userId);//获取关注和粉丝的统计数
			getPushList("","init");//获取推荐关注
			var flag = isFollowFans(userId);
			if(flag){
				$("#attentionBtn").hide();
				$("#attentionBtn-cancle").show();
			}else{
				$("#attentionBtn").show();
				$("#attentionBtn-cancle").hide();
			}
			bindDelFollow();
			bindAddFollow();
			function bindAddFollow(){
				$("#attentionBtn").on("click",function(){
					insertFollowInResume(userId, "10");
				});
			}	
			function bindDelFollow(){
				$("#attentionBtn-cancle").on("click",function(){
					delFollowInResume(userId, "10");
				});
			}	
		});
	</script>
	<script type="text/javascript">
		
		var relationId;
		var positionId;
		
		var postion;
		var companyId; 
		var times = '1';
		var face = '1';
		var status;
		var type;
		var oper;
		
		var headUrl = "";
		$(function (){
			relationId = '${relationId }';
			positionId = '${positionId }';
			userHeadUrl = '${userHeadUrl}';
			if(userHeadUrl != ''){
				$("#headUrl").css("background-image","url("+userHeadUrl+")");
			}
			oper = '${oper}';
			if(oper != 'index'){
				init();
				if(status == 0 || status == 1 || status == 2 || status == 3 || status == 5 || status == 6
						|| status == 7){
					$("#invite-person").show();
				}
				
			}
		});
		
		function init(){
			getStatus(relationId);
			getFaceTimes(relationId);
			getBtns();
		}
		
		function getStatus(relationId){
	 		$.ajax({
				 cache :false,
				 async :false,
				 url : '<%=path%>/company/resume/log',
				 data : {
					 relationId : relationId,
					 oper : 'show'
				 },
				 success : function(data){
					 var datas = eval('('+data+')');
					 if(datas.success){
						 status = datas.obj.showStatus;
						 type = datas.obj.showType;
					 }
				 }
			 });
		}
		
		function getFaceTimes(relationId){
	 		$.ajax({
				 cache :false,
				 async :false,
				 url : '<%=path%>/company/resume/getFaceTimes',
				 data : {
					 relationId : relationId
				 },
				 success : function(data){
					 var n;
					 var m;
					 var datas = eval('('+data+')');
					 if(datas.obj.length > 0){
						 n = datas.obj[0].count_n;
						 m = datas.obj[0].count_m;
						 if(n != null && n != ''){
							times = n; 
						 }
						 if(m != null && m != ''){
							face = m; 
						 }
					 }
				 }
			 });
		}
		
		
		 function getBtns(){
			var htmlReq = "";
			var htmlAcc = "";
			var htmlOffer = "";
			var htmlExam = "";
			var htmlPassOrNo = "";
			
			if(type == 1){
				if(status == 1 || status == 0 ){
					htmlReq = "<a href='javascript:void(0);' onclick='passOrNo(2)' class='pass-written'>通过</a></br>" + 
			        		  "<a href='javascript:void(0);' onclick='passOrNo(3)' class='refuse-written'>不通过</a></br>";
				}else if(status == 2){
					htmlExam = "<a href='javascript:void(0);' onclick='reqExam()' class='written-test'>邀请笔试</a>";
					htmlReq = "<a href='javascript:void(0);' onclick='opers(4)' class='written-test'>第"+times+"次邀请"+face+"面</a></br>";
				}else if(status == 3){
					htmlReq = "<a href='javascript:void(0);' onclick='opers(2)' class='pass-written'>通过</a></br>";
				}
				/* else if(status == 4){
					htmlReq = "<a href='javascript:void(0);' onclick='oper(5)' class='table-opreat-btn blue-btn'>接受第"+times+"邀请"+face+"面</a></br>" +
					"<a href='javascript:void(0);' onclick='oper(6)' class='table-opreat-btn blue-btn'>拒绝第"+times+"邀请"+face+"面</a></br>";
				} */
				else if(status == 5){
					htmlPassOrNo = "<a href='javascript:void(0);' onclick='opers(7)' class='pass-written'>通过"+face+"面</a></br>" + 
									"<a href='javascript:void(0);' onclick='opers(8)' class='refuse-written'>不通过"+face+"面</a></br>";
				}else if(status == 6){
					htmlReq = "<a href='javascript:void(0);' onclick='opers(4)' class='written-test'>第"+(times+1)+"邀请"+face+"面</a></br>";
				}else if(status == 7){
					htmlOffer = "<a href='javascript:void(0);' onclick='offer()' class='pass-written'>发送offer</a></br>";
					htmlReq = "<a href='javascript:void(0);' onclick='opers(4)' class='written-test'>第1次邀请"+(face + 1)+"面</a></br>";
				}
			}else if(type == 2){
				htmlReq = "<a href='javascript:void(0);' onclick='opers(4)' class='written-test'>第"+times+"次邀请"+face+"面</a></br>";
			}
			$("#btns").empty();
			$("#btns").append(htmlOffer + htmlReq + htmlExam + htmlPassOrNo);
				 
		 }
		//简历未阅读
		 function resumeNoPage(){
			 location.href="<%=path%>/company/resume"
		 }
		//简历筛选通过不通过
		 function passOrNo(oper){
			 $.ajax({                
					cache: false, 
					async: false, 
					type: "POST",                
					url:  '<%=path%>/company/resumePassOrNo/passOrNo',                
					data:{
						ids : relationId,
						oper : oper
					},                
					success: function(data) {
						getStatus(relationId);
					    getBtns();
					}            
				});
		 }
		
		 /* 简历操作 */
		 function opers(oper){
			 if(oper == 4){
				 req();
			 }else{
				 $.ajax({
		 			 cache : false,
		 			 async : false,
		 			 contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
					 url : '<%=path%>/company/resume/oper',
		 			 data : {
		 				 id : relationId,
		 				 status : oper
		 			 },
		 			 success : function(data){
		 				init();
		 			 }
		 		 });
			 }
		 }
		 /*邀请面试*/
		 function req(){
			 $("#iv-position").html(postion);
			 $("#interview-panel-overlay").show();
			 $("#interview-panel").show();
			 $("#iv-timer").val("");
			 $("#remarksNum").val("");
		 }
		 function closeDailog(){
			 $("#interview-panel-overlay").hide();
			 $("#interview-panel").hide();
		 }
		 //邀请面试
		 function reqBtn(){
			 var faceTime = $("#iv-timer").val();
			 if(faceTime.length == 0){
				 alert("请填写面试时间 ");
				 $("#reqBtn").show();
				 $("#reqBtnSubmit").hide();
				 return;
			 }
			 var faceType = $("#faceType").val();
			 var memo = $("#remarksNum").val();
			 $("#reqBtn").hide();
			 $("#reqBtnSubmit").show();
			 setTimeout(function () {
				 $.ajax({
					 cache : false,
					 async : false,
					 url : '<%=path%>/company/resume/oper',
					 type : 'POST',
					 contentType: "application/x-www-form-urlencoded; charset=UTF-8",
					 data : {
						 id : relationId,
						 status : '4',
						 faceTime : faceTime,
						 memo : memo
					 },
					 success : function(data){
						 $("#reqBtn").show();
						 $("#reqBtnSubmit").hide();
						 $("#interview-panel-overlay").hide();
						 $("#interview-panel").hide();
						 init();
					 }
				 });
			 },200);
		 }
		 
		 /*邀请笔试
	     判断是否存在笔试题 */
	 function reqExam(){
		 $.ajax({
			 cache : false,
			 async : false,
			 url : '<%=path%>/company/examManager/checkExamPaper',
			 type : 'POST',
			 contentType: "application/x-www-form-urlencoded; charset=UTF-8",
			 data : {
				 positionId : positionId
			 },
			 success : function(data){
				 var datas = eval('('+data+')');
				 if(datas.success){
					 $.fn.fandialog({
						title:"提示信息",
						contents:"请创建试卷",
						click:function(ok){
							if(ok){
								window.location.href="<%=path%>/company/examManager/init"
							}
						}
					});
				 }else{
					 var status = datas.obj;
					 if(status == '1'){
						 doReqExam();
					 }else if(status == '0'){
						 alert("该试卷已被禁用");
					 }
				 }
			 }
		 });
	 }
	
	function doReqExam(){
		$.ajax({
			 cache : false,
			 async : false,
			 url : '<%=path%>/company/examManager/reqExam',
			 type : 'POST',
			 contentType: "application/x-www-form-urlencoded; charset=UTF-8",
			 data : {
				 positionId : positionId,
				 resumePositionId : relationId
			 },
			 success : function(data){
				 getStatus(relationId);
			     getBtns();
			 }
		 });
	}
		 
	</script>
	
	
	<!--resume-details-->
		<section class="resume-details mgTop30">
			<div class="rd-container" style="margin-top: 88px;">
			
				<!--简历 start-->
				<div class="rd-cons-basicInfo fl" id="rd-cons-basicInfo">
					<div class="header-pic">
					
						<c:if test="${userHeadUrl == '0' }">
							<img src="${STATIC_SCH }/images/moren_guanzhu.jpg">
						</c:if>
						<c:if test="${userHeadUrl != '0' }">
							<img src="${userHeadUrl }">
						</c:if>
						<!--<a href="javascript:void(0)" id="headUrl" style="background-image:url('${STATIC_SCH }/images/moren_guanzhu.jpg')"></a>-->
<!-- 						<img src="http://kaipin.com:8083:82/12/2016/images/info/0/a2fdef36-db4e-4c25-aa89-b482b2217f06.jpg"> -->
					</div>
					<div class="per-infoLists">
					
								<h5>
									<span>${RESUME_INFO.surname }${RESUME_INFO.missSurname }</span>
									<a href="javascript:void(0)" class="certi no-certi" title="未认证">未认证</a>
									<a href="javascript:void(0)" class="certi ing-certi" title="审核中">审核中</a>
									<a href="javascript:void(0)"  style="display:none;" class="certi already-certi" title="已认证">已认证</a>
								</h5>
								<div class="info-per-lists">
									
									<c:if test="${keyWordsList != null && keyWordsList == ''}">
										<p class="signature">
											<span>个性签名：</span> 
											<c:forEach items="${keyWordsList }" var="m" varStatus="status">
													
												<c:if test="${fn:length(keyWordsList)-1 == status.index}">
													<c:out value="${m.keyword}" />  
												</c:if>
												<c:if test="${fn:length(keyWordsList)-1 != status.index}">
													<c:out value="${m.keyword}" /> 、 
												</c:if>
											</c:forEach>
										</p>
									</c:if>
									
									<p class="per-otherInfo per-adress">${area } 
										<c:if test="${likeWorkLingYu != null && likeWorkLingYu != '' }">
											| 兴趣领域：
											<c:forEach items="${likeWorkLingYu }" var="m"  varStatus="status">
												
												<c:if test="${fn:length(likeWorkLingYu)-1 == status.index}">
													<c:out value="${m.name}" />  
												</c:if>
												<c:if test="${fn:length(likeWorkLingYu)-1 != status.index}">
													<c:out value="${m.name}" /> 、 
												</c:if>
											</c:forEach>
										</c:if> 
									</p>
									<p class="per-otherInfo per-school">${schoolNames }-${majorName }
											<c:if test="${is_grad == 0}">（未毕业）</c:if>
											<c:if test="${is_grad == 1}">（已毕业）</c:if>
											<c:if test="${is_grad == 2}">（已结业）</c:if>
											<c:if test="${is_grad == 3}">（休学中）</c:if></p>
									<p class="per-otherInfo language">
										<c:forEach items="${languagelist }" var="languagelistVal" varStatus="status"> 
											<c:if test="${fn:length(languagelist)-1 == status.index}">
												<c:out value="${languagelistVal.name}" /> 
											</c:if>
											<c:if test="${fn:length(languagelist)-1 != status.index}">
												<c:out value="${languagelistVal.name}" />、 
											</c:if>
										</c:forEach>
									</p>
								</div>
								<div class="operating clear">
									<div class="btn-add" style="display: none;">
										<a href="javascript:void(0)" class="add-ability" title="加入人才库"><span>+</span>加入人才库</a>
										<a href="javascript:void(0)" class="see-log" title="查看求职日志">求职日志</a>
									</div>
									<div class="btn-interview" id="btns">
										<!-- <a href="javascript:void(0)" class="written-test">邀请笔试</a>
										<a href="javascript:void(0)" class="refuse-written">拒绝笔试</a>
										<a href="javascript:void(0)" class="written-test">再邀笔试</a>
										<a href="javascript:void(0)" class="pass-written">通过笔试</a>
										<a href="javascript:void(0)" class="written-test">邀请一面</a>
										<a href="javascript:void(0)" class="pass-written">发offer</a> -->
									</div>
								</div>
							</div>
							<div class="attention-infos fr">
							<a class="right-sideline" href="${BASE_PATH }/followfans/init?oper=follow&org_id=${userId}">
								<span class="n" id="floowCount">0</span>
									<span>关注</span>
							</a>
							<a href="${BASE_PATH }/followfans/init?oper=fans&org_id=${userId}" class="bdRight">
								<span class="n" id="fansCount">0</span>
									<span>粉丝</span>
							</a>
							<a class="attention <!--alreadyattention-->" href="javascript:void(0);" id="attentionBtn" >+ 关注</a>
							<a class="attention <!--alreadyattention-->" href="javascript:void(0);" id="attentionBtn-cancle" >- 取消关注</a>
						<div class="nc-share">
										<a href="javascript:;" class="share shear" id="share" title="分享"><i class="sr-share"></i></a>
										<span style="display:none;" class="line"></span>
										<a href="javascript:;" style="display:none;" class="share collect" onclick="javascript:addFavorite2()" id="collect"><i class="cl-collect"></i>收藏</a>
										<div class="share-space" id="share-space" style="display: none;">
											
												<div class="bdsharebuttonbox bdshare-button-style0-16" data-tag="share_1" data-bd-bind="1471484457902">
													<input type="hidden" id="shareUrl" value="/resume/share/${userId }">
													<a class="bds_qzone" data-cmd="qzone" href="#" data-id="6356238572082961202" title="分享到朋友圈">朋友圈</a>
													<a class="bds_weixin" data-cmd="weixin" href="#" data-id="6356238572082961203" title="分享到微信">微信</a>
													<a class="bds_sqq" data-cmd="sqq" href="#" data-id="6356238572082961204" title="分享给QQ好友">QQ好友</a>
													<a class="bds_mail" data-cmd="mail" href="#" data-id="6356238572082961250" title="用电子邮件分享">电子邮件</a>
												</div>
												
												<script type="text/javascript">
												        //全局变量，动态的文章ID
												        var ShareId = "";
												        //绑定所有分享按钮所在A标签的鼠标移入事件，从而获取动态ID
												        $(function () {
												            $(".bdsharebuttonbox a").mouseover(function () {
												                ShareId = $(this).attr("data-id");
												            });
												        });
												
												        /* 
												        * 动态设置百度分享URL的函数,具体参数
												        * cmd为分享目标id,此id指的是插件中分析按钮的ID
												        *，我们自己的文章ID要通过全局变量获取
												        * config为当前设置，返回值为更新后的设置。
												        */
												        function SetShareUrl(cmd, config) {        
												        	var shareUrl = $("#shareUrl").val();
												            if (ShareId) {
												                config.bdUrl = r_path + shareUrl;   
												            }
												            return config;
												        }
												
												        //插件的配置部分，注意要记得设置onBeforeClick事件，主要用于获取动态的文章ID
												         window._bd_share_config = {
													            "common": {
													                onBeforeClick:SetShareUrl,
													                "bdSnsKey":{},
													                "bdText":"",
													                "bdMini":"2",
													                "bdMiniList":false,
													                "bdPic":"",
													                "bdStyle":"0",
													                "bdSize":"12"
													            }, "share": {}
													        };
												        //插件的JS加载部分
												        with (document) 0[(getElementsByTagName('head')[0] || body).appendChild(createElement('script')).src = 'http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='+ ~(-new Date() / 36e5)];
												      //切换分享框显示与隐藏
														$(function(){
															$("#share").on("mouseover",function(){
																$("#share-space").stop(true,true).slideToggle('fast');
																$("#share-space a").on("click",function(){
																	$("#share-space").hide();});
															    		$("body").click( function(){
																	$("#share-space").hide();});
															    		//鼠标已出框隐藏框
															    		//$("#share-space").onmouseout( function(){
																		//$("#share-space").hide();
																	    //});
															});
														});
										
														
												        </script>
												
										</div>
									</div>
						</div>
						</div>
				<!--rd-cons-left-basicInfo end-->		
							<!--个人简历详细信息 start-->
					<div class="person-other-infos">
					<div class="stuconleft">
						<div class="poi-box">
							<!--教育背景 start-->
							<div class="pb poi-education">
								<h5 class="tlt">教育背景</h5>
								<p>
										${time}  
										${schoolName}  
										${zhuanyeName}  
										${xueliName}  
										
											<c:if test="${is_grad == 0}">未毕业</c:if>
											<c:if test="${is_grad == 1}">已毕业</c:if>
											<c:if test="${is_grad == 2}">已结业</c:if>
											<c:if test="${is_grad == 3}">休学中</c:if>
										
								</p>
							</div>
							<!--教育背景 end-->
							
							<!--职业背景 start-->
							<c:if test="${proList != null && proList != '' }">
								<div class="pb poi-position">
									<h5 class="tlt">职业背景</h5>
									
									<c:forEach items="${proList }" var="proListVal">
										<p> 
											${proListVal.start_time}
												<c:if test="${proListVal.end_time != ''}">
													- ${proListVal.end_time}
												</c:if>
											 |
											${proListVal.position} |
											${proListVal.employer} 
										</p>
									</c:forEach>
								</div>
							</c:if>
							<!--职业背景 end-->
							
							<!--求职意向（全职） start-->
							<c:if test="${ positionList != null && positionList != '' && likeWorkAreaList != null && likeWorkAreaList != '' && likeWorkLingYu != null && likeWorkLingYu != ''}">
								<div class="pb poi-position">
									<h5 class="tlt">求职意向
										<c:if test="${hireType != null && hireType != ''}">
											(${hireType })
										</c:if>
									</h5>
									<c:if test="${positionList != null && positionList != '' }">
										<p>求职意向职位:   
											<c:forEach items="${positionList }" var="m" varStatus="status">
												<c:if test="${fn:length(positionList)-1 == status.index}">
													<c:out value="${m.position_tag}" />  
												</c:if>
												<c:if test="${fn:length(positionList)-1 != status.index}">
													<c:out value="${m.position_tag}" /> 、 
												</c:if>
											</c:forEach>
										</p>
									</c:if>
									
									<c:if test="${likeWorkAreaList != null && likeWorkAreaList != '' }">
										<p>意向工作地区:   
											<c:forEach items="${likeWorkAreaList }" var="m" varStatus="status">
												<c:if test="${fn:length(likeWorkAreaList)-1 == status.index}">
													<c:out value="${m.name}" />  
												</c:if>
												<c:if test="${fn:length(likeWorkAreaList)-1 != status.index}">
													<c:out value="${m.name}" /> 、 
												</c:if>
											</c:forEach>
										</p>
									</c:if>
									
									<c:if test="${likeWorkLingYu != null && likeWorkLingYu != '' }">
										<p>意向工作领域:   
											<c:forEach items="${likeWorkLingYu }" var="m"  varStatus="status">
													
													<c:if test="${fn:length(likeWorkLingYu)-1 == status.index}">
														<c:out value="${m.name}" />  
													</c:if>
													<c:if test="${fn:length(likeWorkLingYu)-1 != status.index}">
														<c:out value="${m.name}" /> 、 
													</c:if>
												</c:forEach>
										</p>
									</c:if>
									
								</div>
							</c:if>
							<!--求职意向（全职） end-->
							
							<!--个人关键词 start-->
							<c:if test="${keyWordsList != null && keyWordsList != '' }">
								<div class="pb poi-apply">
									<h5 class="tlt">个人关键词</h5>
									<p>
										<c:forEach items="${keyWordsList }" var="m" varStatus="status">
											<c:if test="${fn:length(keyWordsList)-1 == status.index}">
												<c:out value="${m.keyword}" />  
											</c:if>
											<c:if test="${fn:length(keyWordsList)-1 != status.index}">
												<c:out value="${m.keyword}" /> 、 
											</c:if>
										</c:forEach>
									</p>
								</div>
							</c:if>
							<!--个人关键词 end-->
							
							<!--语言能力 start-->
							<c:if test="${languagelist != null && languagelist != '' }">
								<div class="pb poi-language">
									<h5 class="tlt">语言能力</h5>
									<p>
										<c:forEach items="${languagelist }" var="languagelistVal" varStatus="status"> 
											<c:if test="${fn:length(languagelist)-1 == status.index}">
												<c:out value="${languagelistVal.name}" /> 
											</c:if>
											<c:if test="${fn:length(languagelist)-1 != status.index}">
												<c:out value="${languagelistVal.name}" />、 
											</c:if>
										</c:forEach>
									</p>
								</div>
							</c:if>
							<!--语言能力 end-->
							
							<!--求职信 start-->
							<c:if test="${RESUME_INFO.coverLetter != null && RESUME_INFO.coverLetter != ''}">
								<div class="pb poi-coverLetter">
									<h5 class="tlt">求职信</h5>
									<div class="the-leter">
										<p>${RESUME_INFO.coverLetter }</p>
									</div>
								</div>
							</c:if>
							<!--求职信 end-->
							
						</div>
						<!--简历 end-->
						<!--foot-ad start-->
						<div class="foot-ad">
							<div class="simp-box">
								<a href="http://www.ef.com.cn/" target="_blank">
									<img src="${STATIC_SCH }/images/img4.jpg" width="100%" height="100%"  />
								</a>
							</div>
						</div>
						<!--foot-ad start-->	
						</div>					
				<!--nc-left start-->
				<!--右边-->
					<div class="rightContent fr">
						<div class="slideShow">
							
							<!--推荐列表-->
							<div class="slideSameStyle recommend-lists" id="pushListDiv" style="display:none;">
								<div class="slideTitle">推荐</div>
								<div class="recDetails">
									<ul id="pushList" class="pushList">
										
									</ul>
								</div>
							</div>
							
							<!--新加关注-->
							<div class="slideSameStyle newAttention" style="display:none;" id="fansListDiv">
								<div class="slideTitle">新加关注</div>
								<div class="attentionDetails">
									<ul id="fansList">
										
									</ul>
								</div>
							</div>
						
							<!--广告位1-->
							<div class="slideSameStyle advertise" style="display:none;">
								<div class="adDetails"></div>
							</div>
							
							
						</div>
						
						<!-- ad-start -->
						<%@ include file="/WEB-INF/application/common/ad/ad.jsp"%>
						<!-- ad-end -->
						
						
					</div>
				<!--nc-left start-->
					</div>
					<!--个人简历详细信息 start-->
					

					<div class="invite-person" id="invite-person" style="display:none;">
						<!-- 左边的按钮 -->
						<p id="btns">
						</p>
						
					</div>
				
				<div class="clear"></div>
			</div>
		</section>
		
		
		<!--邀请面试  开始-->
		<form method="POST" action="<%=path%>/company/resumePassOrNo/reqView" id="reqViewForm" name="reqViewForm">
			<div class="tzui-loading-overlay" id="interview-panel-overlay" style="display: none;"></div>
			<div class="interview-panel radius" id="interview-panel" style="display: none;">
				<div class="iv-title">
					<h3 id="reqName">邀请面试</h3>
					<a href="javascript:void(0)" onclick="closeDailog();"  class="close-itrView closeAllStyle"></a>
				</div>
				<!--影藏数据-->
				<input type="hidden" id="rowIndex"/>
				<input type="hidden" id="oper"/>
				<div class="iv-cons-lists">
					<div class="iv-select">
						<span class="name-tips fl">职位</span>
						<div class="iv-select-cons iv-select-posi fl" id="iv-position">
							${positionName }
							<input type="hidden" name="postion" id="postion"/>
						</div>
					</div>
					
					<div class="iv-select">
						<span class="name-tips fl">时间</span>
						<div class="iv-timer fl"><input type="text" id="iv-timer" name="faceTime" readonly="readonly"></div>
					</div>
					
<!-- 					<div class="iv-select"> -->
<!-- 						<span class="name-tips fl">方式</span> -->
<!-- 						<div class="iv-select-cons fl" id="iv-way"> -->
<!-- 							<input type="hidden" name="faceType" id="faceType" value="0" class="input-val"/> -->
<!-- 							<span class="titleActive">网络面试</span> -->
<!-- 							<span class="selet_sj sanjiao"></span> -->
<!-- 							<div class="select-details"> -->
<!-- 								<a href="javascript:void(0)" data-title="0">网络面试</a> -->
	<!-- 							<a href="javascript:void(0)">网络笔试</a> -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 					</div> -->
					
					<div class="iv-select iv-remark">
						<span class="name-tips fl">备注</span>
						<textarea class="remarks" id="remarksNum" name="memo" data-count="50" ></textarea>
						<span class="limit-number" id="limit-number"><span>0</span>/50</span>
					</div>
				</div>
				<div class="iv-sureBtn">
					<a href="javascript:void(0)" id="reqBtn" onclick="reqBtn();" class="allSureBtnstyle" >确定</a>
					<a href="javascript:void(0)" id="reqBtnSubmit" style="display:none;background:#ececec;color:#c4c4c4;" class="allSureBtnstyle" >提交中</a>
				</div>
			</div>
		</form>
		<!--邀请面试  结束-->
		
		<!--发送offer 开始-->
		<div class="tzui-loading-overlay" id="send-offer-dialog-overlay" style="display: none;"></div>
		
		<div class="send-offer-dialog" id="send-offer-dialog" style="display: none;">
			<form>
				<div class="offer-title">
					<h3>发放offer</h3>
					<a href="javascript:void(0)" class="close-sendOffer closeAllStyle"></a>
				</div>
				<div class="offer-cons">
					<div class="cons-title">
						<input type="text" id="offerTitle" value="2015校园招聘录用通知书"/>
					</div>
					<div class="offer-editPanel">
						<div class="cons-details" id="send-offerCons" style="font:12px/22px '微软雅黑','microsoft yahei';color:#565656;"></div>
					</div>			
				</div>
				<div class="offer-button">
						<a href="javascript:void(0)" class="allSureBtnstyle offer-suer" id="offer-suer">确定</a>
						<a href="javascript:void(0)" style="display:none;background:#ececec;color:#c4c4c4;" class="allSureBtnstyle offer-suer" id="offer-suer-submit">发送中</a>
						<a href="javascript:void(0)" class="allCloseBtnstyle" id="offer-canle">取消</a>
				</div>
			</form>
		</div>
		<!--发送offer 介绍-->
		
		
		<!-- header-start -->
		<%@ include file="/WEB-INF/application/main/footer.jsp"%>
		<!-- header-end -->
		
		<script type="text/javascript" src="${STATIC_SCH }/js/videoPlay.js"></script>
		<script type="text/javascript" src="${STATIC_SCH }/js/thumbUp.js"></script>
		<script type="text/javascript" src="${STATIC_SCH }/js/comments.js"></script>
		
		<script type="text/javascript">
			/**发送offer*/
			var name = "XX先生/女士";
			var nameXing = '${RESUME_INFO.surname }';
			var nameMing = '${RESUME_INFO.missSurname }';
			var email = '${RESUME_INFO.email }';
			var companyId;
			var resumeId;
			var postionId;
			var userId;
			
			$(function (){
				resumeId = '${resumeId}';
				postionId = '${postionId}';
				userId = '${userId}';
				companyId = '${companyId}';
			});
			
			var tag = 0;
			var sendOfferCons;
			
			sendOfferCons = textareaEditor(sendOfferCons,"send-offerCons");//发送offer
			function textareaEditor(objNmae,obj){
				objNmae = UE.getEditor(obj,{
				 	toolbars:[],/*工具栏设置*/
				 	wordCount:false,/*字数统计*/
			    	contextMenu:[],/*屏蔽右键菜单*/
			    	elementPathEnabled:false,/*标签结构*/
			    	autoHeightEnabled:false,/*自动升高*/
			    	initialFrameWidth:540,/*宽度*/
			    	initialFrameHeight:240,/*高度*/
			    });
			    return objNmae;
			}
			
			function offer(){
				$.ajax({
					 cache : false,
					 async : false,
					 url : '<%=path%>/company/resumeOffer/checkOffer.do',
					 type : 'POST',
					 data : {
						 relationId : relationId
					 },
					 success : function(data){
						 var datas = eval('('+data+')');
						 if(datas.success){
							    name = nameXing + nameMing;
								$("#send-offer-dialog-overlay").show();
							 	$("#send-offer-dialog").show();
							 	
							 	var contents = "<p>"+name+" :</p>"+
							 	"<p>非常荣幸的通知您，由于您出众的专业能力和优秀的综合素质，已经通过公司的面试考核，成为公司的一员，您将入职XX公司XX部门担任XX职位，我们对您加入XX大家庭表示热烈的欢迎。"+
								"</p>"+
									"<p>"+
								"一、请于XX年XX月XX日XX点XX分，到公司人力资源部办理报到手续，公司地址：XXXXXXX，人力资源部电话：XXXXXX，联系人：XXXX。  <br>"+
								"二、请您在办理入职手续时，提供以下资料：<br>"+
								"　1.居民身份证原件，外地户籍还需提供居住证原件；<br>"+  
								"　2.最高学历证书及学位证原件； <br>"+
								"　3.专业技术职称证书原件、职业资格证书原件、上岗证书原件；   <br>"+
								"　4.前一家公司离职证明原件；<br>"+
								"</p>"+
								"<span style='clear:both'></span><p style='float:right;margin-right:20px;'>X年X月X日</p>";
					
								
								sendOfferCons.setContent(contents);
								
								$("#offer-canle").click(function(){
									$("#send-offer-dialog").hide();	
									$("#send-offer-dialog-overlay").hide();
								});
								
								$("#send-offer-dialog").find(".close-sendOffer").click(function(){
									$("#send-offer-dialog").hide();	
									$("#send-offer-dialog-overlay").hide();
								});
								
								$("#offer-suer").click(function () {
									var offerCotent = sendOfferCons.getContent();
									var offerTitle = $("#offerTitle").val();
									$("#offer-suer").hide();
									$("#offer-suer-submit").show();
									setTimeout(function(){
										$.ajax({
											 cache : false,
											 async : false,
											 url : '<%=path%>/company/resumeOffer/sendOffer.do',
											 type : 'POST',
											 contentType: "application/x-www-form-urlencoded; charset=UTF-8",
											 data : {
												 raltionId : relationId,
												 companyId : companyId,
												 resumeId : resumeId,
												 postionId : postionId,
												 userId : userId,
												 offerContent : offerCotent,
												 offerTitle : offerTitle,
												 email : email
											 },
											 success : function(data){
												 $("#offer-suer").show();
												 $("#offer-suer-submit").hide();
												 var datas = eval('('+data+')');
												 $("#send-offer-dialog").hide();	
												 $("#send-offer-dialog-overlay").hide();
												 if(datas.success){
													 var tip = datas.obj.tip_push + "<br>" + datas.obj.tip_mail;
													 $.fn.fandialog({
															title:"提示信息",
															contents:tip,
															click:function(ok){
															}
														});
												 }else{
													 $.fn.fandialog({
															title:"提示信息",
															contents:datas.msg,
															click:function(ok){
															}
														});
												 }
												 init();
											 },error:function(){
												 init();
												 alert("发送失败");
												 $("#offer-suer").show();
												 $("#offer-suer-submit").hide();
											 }
										 });
									},200);
									
									$("#offer-suer").off("click");
								});
						 }else{
							 $.fn.fandialog({
								title:"提示信息",
								contents:"该学生未设置邮箱，不能发送offer",
								click:function(ok){
								}
							});
						 }
					 }
				 });
			}
		</script>
		
		
		<script type="text/javascript">
			
			$(function(){
				var topmain = $("#rd-cons-basicInfo").offset().top;
				$(window).scroll(function () {
	                if ($(window).scrollTop() > (topmain)) {
                        $("#rd-cons-basicInfo").addClass("fixedTop");
	                  }else {
	                       $("#rd-cons-basicInfo").removeClass("fixedTop");
	                     }
	            });
				
				/*面试时间*/
				$('#iv-timer').datetimepicker({
					lang:'ch'//格式化中文
				});
				$('#iv-timer').datetimepicker({
					value:'',
					step:1
				});


			})
			
			
		</script>
		

		
	</body>
	
</html>

		
