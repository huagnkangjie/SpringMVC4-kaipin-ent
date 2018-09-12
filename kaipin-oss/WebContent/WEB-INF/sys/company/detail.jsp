<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<link rel="stylesheet" href="${BASE_PATH}/public/assets/css/basic.css" />
	<script src="${BASE_PATH}/public/js/jquery.js"></script>
	<script src="${BASE_PATH}/public/assets/js/index.js"></script>
	<script src="${BASE_PATH}/public/assets/js/common.js"></script>
	<script type="text/javascript">
		var logo = "";
		$(function (){
			logo = '${basicInfo.logo }';
			if(logo != 'undefined' || logo != null || logo != ''){
				$(".cp-tilte").css("background-image","url("+logo+")");
			}
		});
	</script>
</head>
<body>

	<!--back-manage start-->
		<div class="back-manage">
			<!--bm-company-details start-->
			<div class="bm-company-details">
				<div class="cp-tilte"></div>
				<div class="cp-infos">
					<div class="cp-simple-title">
						<h3 class="tt-name">${basicInfo.entSimpleName }<span>（全称：${basicInfo.entName }）</span></h3>
						<p class="tt-number">
							<c:if test="${basicInfo.peopleNumber != null || basicInfo.peopleNumber != ''}">
								已有员工${basicInfo.peopleNumber }人
							</c:if>
						
						</p>
<!-- 						<div class="evaluate-company"> -->
<!-- 							<span class="ec-title" id="evaluate-title">企业评级A</span> -->
<!-- 							<a href="javascript:void(0)" class="edit-evalBtn" id="edit-evalBtn">编辑</a> -->
<!-- 							<div class="evalu-lists" id="evalu-lists"> -->
<!-- 								<ul> -->
<!-- 									<li><a href="javascript:void(0)">企业评级<span>A级</span></a></li> -->
<!-- 									<li><a href="javascript:void(0)">企业评级<span>B级</span></a></li> -->
<!-- 									<li><a href="javascript:void(0)">企业评级<span>C级</span></a></li> -->
<!-- 									<li><a href="javascript:void(0)">企业评级<span>D级</span></a></li> -->
<!-- 									<li class="bd-none"><a href="javascript:void(0)">企业评级<span>E级</span></a></li> -->
<!-- 								</ul> -->
<!-- 							</div> -->
<!-- 						</div> -->
					</div>
					<div class="details-lists">
						<dl>
							<dt>${xjhYg }场</dt>
							<dd>宣讲会预告</dd>
						</dl>
						<dl>
							<dt>${xjhZb }场</dt>
							<dd>宣讲会直播</dd>
						</dl>
						<dl data-tag="resume">
							<dt>${xjhDb }场</dt>
							<dd>宣讲会点播</dd>
						</dl>
						
						<dl id="xxxxx" data-tag="position">
							<dt>
								<c:if test="${positionCount != 0 }">
<%-- 									<a href="javascript:openapp('${BASE_PATH}/management/position/list?companyId=1','14Company','职位信息',true);">${positionCount }个</a> --%>
									<a href="#" >${positionCount }个</a>
									<script>
									   $("#xxxxx").on('click',function(){
										 //  alert(1);
										  // var val = $(this).html();
										  var $position = $(this).data('tag');
										  var isTrue = true;
										  var $oLi = $("#task-content-inner",window.parent.document).find('li');
									  	  var width1 = 118;
										   var len = $("#task-content-inner,#task-content", window.parent.document).find('li').length;
										   $("#task-content-inner,#task-content", window.parent.document).css("width",width1*(len+1));
										   var num=0;
										   var index = 0;
										   for(var i=0;i<$oLi.length;i++){
											   if($oLi.eq(i).hasClass($position)){
												   num++;
												   index = i;
												   break;
											   }
										   }
										   
										   if(num>=1){
											   isTrue = false; 
										   }
										   //http://localhost:8081/kaipin-oss/management/position/list?companyId=39f9d638-9346-4eac-914f-36b4e9410689
										  var url = '${BASE_PATH}/management/position/list?companyId=${companyId}';
										   if(isTrue){
											   $("#task-content-inner", window.parent.document).append('<li class="macro-component-tabitem '+$position+'" app-id="'+len+'" data-flag="open" app-url="http://localhost:8081/kaipin-oss/management/company/main/zzlist" app-name="职位信息"><span class="macro-tabs-item-text" title="职位信息">职位信息</span><a class="macro-component-tabclose" href="javascript:void(0)" title="点击关闭标签"><span></span><b class="macro-component-tabclose-icon">×</b></a></li>');
											   $("#content",window.parent.document).append('<iframe src="'+url+'" style="width: 100%; height: 100%;" frameborder="0" id="appiframe-'+(len)+'" class="appiframe"></iframe>');
										   }else{
											   $("#content",window.parent.document).find('iframe').eq(index).remove();
											   $("#content",window.parent.document).append('<iframe src="'+url+'" style="width: 100%; height: 100%;" frameborder="0" id="appiframe-'+(len)+'" class="appiframe"></iframe>');
										   }
										   len = $("#task-content-inner,#task-content", window.parent.document).find('li').length;
										   
										   
										   if(len>=7){
											   $("#breadcrumbs",window.parent.document).find(".task-changebt").css('display',"inline");
											   $("#task-content", window.parent.document).css('width',920);
											   if(len==8){
												   $("#task-content-inner", window.parent.document).css({'width':width1*len,"marginLeft":-24});
											   }else{
												   $("#task-content-inner", window.parent.document).css({'width':width1*len,"marginLeft":-((len-8)*width1+24)});
											   }
										   }
										   $("#task-content-inner,#task-content", window.parent.document).find('li').eq(len-1).addClass('current').siblings().removeClass('current');
										   $("#content",window.parent.document).find('iframe').eq(len-1).show().siblings().hide();
										  
										   
									   });
									</script>
								</c:if>
								<c:if test="${positionCount == 0 }">
									${positionCount }个
								</c:if>
								
							
							</dt>
							<dd>职位</dd>
						</dl>
						<dl>
							<dt>${resumeCount }份</dt>
							<dd>收到简历</dd>
						</dl>
<!-- 						<dl> -->
<%-- 							<dt>${xjhDb }次</dt> --%>
<!-- 							<dd>面试</dd> -->
<!-- 						</dl> -->
						<dl>
							<dt>${offer }份</dt>
							<dd>发offer</dd>
						</dl>
						<dl>
							<dt>${followCount }人</dt>
							<dd>关注</dd>
						</dl>
						
<!-- 						<div class="state-company" id="state-company"> -->
<!-- 							<div class="sc-title">企业状态：<span>正常</span></div> -->
<!-- 							<a href="javascript:void(0)" data-state="open" class="change-state btn-set color-pass" id="change-state">拉黑</a> -->
<!-- 						</div> -->
					</div>
				</div>
			</div>
			<!--bm-company-details end-->
			
			<!--company-details-center start-->
			<div class="company-details-center">
			
				<div class="brief-infos">
					<h5 class="bi-tlt">企业简介</h5>
					<div class="bi-detailsInfo">
						${basicInfo.detail }
					</div>
				</div>
				
				
				
				<div class="brief-attribute">
					<dl>
						<dt>公司类型</dt>
						<dd>${basicInfo.companyTypeCode }</dd>
					</dl>
					<dl>
						<dt>成立年份</dt>
						<dd>${basicInfo.regeditDate }</dd>
					</dl>
					<dl>
						<dt>关注领域</dt>
						<dd>${basicInfo.businessDomain }</dd>
					</dl>
					<dl>
						<dt>公司网址</dt>
						<dd>${basicInfo.website }</dd>
					</dl>
					
					<dl>
						<dt>联系人</dt>
						<dd>${user.surname }${user.missSurname }</dd>
					</dl>
					
					<dl>
						<dt>联系电话</dt>
						<dd>${user.phone }</dd>
					</dl>
					
					<dl>
						<dt>公司地址</dt>
						<dd>${basicInfo.officeAddress }</dd>
					</dl>
					
				</div>
			
			
			</div>
			<!--company-details-center end-->
			
			
			
		</div>
		<!--back-manage start-->

<!-- 	<div class="wrap js-check-wrap"> -->
<!-- 		详情 -->

<!-- 	</div> -->

</body>
</html>