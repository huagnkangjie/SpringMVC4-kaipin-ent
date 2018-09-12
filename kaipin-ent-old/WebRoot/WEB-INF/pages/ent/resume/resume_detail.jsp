<!--  header-start -->
		<%@ include file="/WEB-INF/pages/main/header.jsp"%>
<!--  header-end -->

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
	<!-- dalog-->
	<link href="<%=path%>/css/dialog.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="<%=path%>/js/dialog3.0.js" ></script>
	
	<!-- editor-->
	<script type="text/javascript" charset="utf-8" src="<%=path%>/js/editor/ueditor.config.js"></script>
	<script type="text/javascript" charset="utf-8" src="<%=path%>/js/editor/ueditor.all.js"> </script>
	<script type="text/javascript" charset="utf-8" src="<%=path%>/js/editor/lang/zh-cn/zh-cn.js"></script>
	
	<!-- 时间 -->
	<link rel="stylesheet" href="<%=path%>/css/jquery.datetimepicker.css" />
	<script type="text/javascript" src="<%=path%>/js/jquery.datetimepicker.js"></script>
	
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
				 url : '<%=path%>/resume/log.do',
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
				 url : '<%=path%>/resumeDetail/getFaceTimes.do',
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
					htmlReq = "<a href='javascript:void(0);' onclick='passOrNo(2)' class='btn-set color-pass'>通过</a></br>" + 
			        		  "<a href='javascript:void(0);' onclick='passOrNo(3)' class='btn-set color-fail'>不通过</a></br>";
				}else if(status == 2){
					htmlExam = "<a href='javascript:void(0);' onclick='reqExam()' class='btn-set color-operat'>邀请笔试</a>";
					htmlReq = "<a href='javascript:void(0);' onclick='opers(4)' class='btn-set color-operat'>第"+times+"次邀请"+face+"面</a></br>";
				}else if(status == 3){
					htmlReq = "<a href='javascript:void(0);' onclick='opers(2)' class='btn-set color-pass'>通过</a></br>";
				}
				/* else if(status == 4){
					htmlReq = "<a href='javascript:void(0);' onclick='oper(5)' class='table-opreat-btn blue-btn'>接受第"+times+"邀请"+face+"面</a></br>" +
					"<a href='javascript:void(0);' onclick='oper(6)' class='table-opreat-btn blue-btn'>拒绝第"+times+"邀请"+face+"面</a></br>";
				} */
				else if(status == 5){
					htmlPassOrNo = "<a href='javascript:void(0);' onclick='opers(7)' class='btn-set color-pass'>通过"+face+"面</a></br>" + 
									"<a href='javascript:void(0);' onclick='opers(8)' class='btn-set color-fail'>不通过"+face+"面</a></br>";
				}else if(status == 6){
					htmlReq = "<a href='javascript:void(0);' onclick='opers(4)' class='btn-set color-operat'>第"+(times+1)+"邀请"+face+"面</a></br>";
				}else if(status == 7){
					htmlOffer = "<a href='javascript:void(0);' onclick='offer()' class='btn-set color-pass'>发送offer</a></br>";
					htmlReq = "<a href='javascript:void(0);' onclick='opers(4)' class='btn-set color-operat'>第1次邀请"+(face + 1)+"面</a></br>";
				}
			}else if(type == 2){
				htmlReq = "<a href='javascript:void(0);' onclick='opers(4)' class='btn-set color-operat'>第"+times+"次邀请"+face+"面</a></br>";
			}
			$("#btns").empty();
			$("#btns").append(htmlOffer + htmlReq + htmlExam + htmlPassOrNo);
				 
		 }
		//简历未阅读
		 function resumeNoPage(){
			 location.href="<%=path%>/resume/init.do"
		 }
		//简历筛选通过不通过
		 function passOrNo(oper){
			 $.ajax({                
					cache: false, 
					async: false, 
					type: "POST",                
					url:  '<%=path%>/resumePassOrNoController/passOrNo.do',                
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
					 url : '<%=path%>/resume/oper.do',
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
					 url : '<%=path%>/resume/oper.do',
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
			 url : '<%=path%>/examManager/checkExamPaper.do',
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
								window.location.href="<%=path%>/examManager/init.do"
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
			 url : '<%=path%>/examManager/reqExam.do',
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
			<div class="rd-container">
				<!--rd-cons-basicInfo start-->
				<div class="rd-cons-basicInfo fl" id="rd-cons-basicInfo">
					<div class="header-pic">
						<a href="javascript:void(0)" id="headUrl" style="background-image:url('<%=path %>/images/moren_guanzhu.jpg')"></a>
					</div>
					<div class="person-singleInfo">
						<p class="user-name">${RESUME_INFO.surname }${RESUME_INFO.missSurname }</p>
						<p class="user-school">${schoolName }</p>
						<p class="user-special">${zhuanyeName }</p>
					</div>
					<div class="invite-person" id="invite-person" style="display:none;">
						<!-- 左边的按钮 -->
						<p id="btns">
						</p>
						
					</div>
				</div>
				<!--rd-cons-left-basicInfo end-->
				
				<!--rd-cons-detailsInfostart-->
				<div class="rd-cons-detailsInfo fr">
					
					<dl>
						<dt>联系信息</dt>
						<dd>
							<p><span>姓氏：</span>${RESUME_INFO.surname }</p>
							<p><span>名字：</span>${RESUME_INFO.missSurname }</p>
							<p><span>性别：</span>
								<c:if test="${RESUME_INFO.sexCode == 0 }">未知</c:if>
								<c:if test="${RESUME_INFO.sexCode == 1 }">女</c:if>
								<c:if test="${RESUME_INFO.sexCode == 2 }">男</c:if>
							</p>
							<p><span>身高：</span>
								<c:if test="${RESUME_INFO.height != null || RESUME_INFO.height != '' }">
									${RESUME_INFO.height}cm
								</c:if>
								
							</p>
							<p><span>生日：</span>${RESUME_INFO.birthDate }</p>
							<p><span>邮件：</span>${RESUME_INFO.email }</p>
							<p><span>电话：</span>${RESUME_INFO.phone }</p>
							<p><span>地区：</span>${RESUME_INFO.locationCode }</p>
							<p><span>街道：</span>${RESUME_INFO.liveAddress }</p>
							<p><span>邮编：</span>${RESUME_INFO.postCode }</p>
							
						</dd>
					</dl>
					
					<dl>
						<dt>教育背景</dt>
						<dd>
							<p>
									<span class="hasIcon">${time}</span> 
									<span class="fontFm">${schoolName}</span> 
									<span class="fontFm">${zhuanyeName}</span> 
									<span class="fontFm">${xueliName}</span> 
									<span class="fontFm">
										<c:if test="${is_grad == 0}">未毕业</c:if>
										<c:if test="${is_grad == 1}">已毕业</c:if>
										<c:if test="${is_grad == 2}">已结业</c:if>
										<c:if test="${is_grad == 3}">休学中</c:if>
									</span> 
							</p>
						</dd>
					</dl>
					
					<style>
						
					</style>
					
					<dl>
						<dt>职业背景</dt>
						<dd>
							<c:forEach items="${proList }" var="proListVal">
								<p> 
									<span class="hasIcon">${proListVal.start_time}
										<c:if test="${proListVal.end_time != ''}">
											- ${proListVal.end_time}
										</c:if>
									</span> |
									<span class="hasIcon">${proListVal.position}</span> |
									<span class="fontFm">${proListVal.employer}</span> 
								</p>
							</c:forEach>
							
						</dd>
					</dl>
					
<!-- 					<dl> -->
<!-- 						<dt>兴趣方向</dt> -->
<!-- 						<dd> -->
<!-- 							<p> <span class="hasIcon">广告</span> -->
<!-- 								<span class="hasIcon">视觉传达</span> -->
<!-- 								<span class="hasIcon">移动互联网</span> -->
<!-- 							</p> -->
<!-- 						</dd> -->
<!-- 					</dl> -->
					
					<dl>
						<dt>求职信</dt>
						<dd>
							<div style="margin-left:20px;">${RESUME_INFO.coverLetter }</div>
						</dd>
					</dl>
					
					<dl>
						<dt>个人关键词</dt>
						<dd>
							<p>
 								<c:forEach items="${keyWordsList }" var="m"> 
										<span  class="hasIcon"><c:out value="${m.keyword}" /></span> 
								</c:forEach>
							</p>
						</dd>
					</dl>
					
					<dl>
						<dt>语言能力</dt>
						<dd>
							<p>
								<c:forEach items="${languagelist }" var="languagelistVal"> 
									
										<span  class="hasIcon"><c:out value="${languagelistVal.name}" /></span> 
									
								</c:forEach>
							</p>
						</dd>
					</dl>
					
					<dl>
						<dt>意向职位</dt>
						<dd>
							<p>
								<c:forEach items="${positionList }" var="m"> 
									<span  class="hasIcon"><c:out value="${m.position_tag}" /></span> 
								</c:forEach>
							</p>
						</dd>
					</dl>
					<dl>
						<dt>意向工作地区</dt>
						<dd>
							<p>
								<c:forEach items="${likeWorkAreaList }" var="m"> 
									<span  class="hasIcon"><c:out value="${m.name}" /></span> 
							</c:forEach>
							</p>
						</dd>
					</dl>
					
					<dl>
						<dt>意向工作领域</dt>
						<dd>
							<p>
								<c:forEach items="${likeWorkLingYu }" var="m"> 
									<span  class="hasIcon"><c:out value="${m.name}" /></span> 
								</c:forEach>
							</p>
						</dd>
					</dl>
					
					<dl>
						<dt>聘用类型</dt>
						<dd>
							<p>
								<span  class="hasIcon">${hireType }</span> 
							</p>
						</dd>
					</dl>
				</div>
				<!--rd-cons-detailsInfostart-->
				<div class="clear"></div>
			</div>
		</section>
		
		
		<!--邀请面试  开始-->
		<form method="POST" action="<%=path%>/resumePassOrNoController/reqView.do" id="reqViewForm" name="reqViewForm">
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
		
		
		
		<!-- 		 header-start -->
		<%@ include file="/WEB-INF/pages/main/footer.jsp"%>
		<!-- 		 header-end -->
		
		
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
					 url : '<%=path%>/resumeOfferController/checkOffer.do',
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
											 url : '<%=path%>/resumeOfferController/sendOffer.do',
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
