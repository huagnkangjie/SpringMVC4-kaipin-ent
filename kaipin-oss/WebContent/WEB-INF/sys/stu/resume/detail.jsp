<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/sys/header.jsp"%>
<link rel="stylesheet" href="${BASE_PATH}/public/assets/css/basic.css" />
<script src="${BASE_PATH}/public/assets/js/rotate/CJL.0.1.min.js"></script>
<script src="${BASE_PATH}/public/assets/js/rotate/ImageTrans.js"></script>
</head>
<body>
	
	
	
	<!-- 简历详情  开始 -->
	
		<!--resume-details-->
		<section class="resume-details mgTop30">
			<div class="rd-container">
				<!--rd-cons-basicInfo start-->
				<div class="rd-cons-basicInfo fl" id="rd-cons-basicInfo">
					<div class="header-pic" id="headUrlDiv">
						<a href="javascript:void(0)" id="headUrl" style="background-image:url('${BASE_PATH }/public/assets/images/moren_guanzhu.jpg')"></a>
					</div>
					<div class="person-singleInfo">
						<p class="user-name">${RESUME_INFO.surname }${RESUME_INFO.missSurname }</p>
						<p class="user-school">${schoolNames }</p>
						<p class="user-special">${majorName }</p>
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
								<c:if test="${RESUME_INFO.sexCode == 1 }">女</c:if>
								<c:if test="${RESUME_INFO.sexCode == 2 }">男</c:if>
							</p>
							<p><span>身高：</span>
								<c:if test="${RESUME_INFO.height != null && RESUME_INFO.height != '' }">
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
										<c:if test="${is_grad == 1}">未毕业</c:if>
										<c:if test="${is_grad == 0}">已毕业</c:if>
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
		
	<!-- 简历详情  结束 -->
	
	<script src="${BASE_PATH}/public/js/common.js"></script>
	<script src="${BASE_PATH}/public/assets/js/page.js"></script>
	
	<script type="text/javascript">	
	
		var userId ;
		var headUrl;
		$(function(){
			
			userId = '${userId}';
			headUrl = '${userHeadUrl}';
			debugger;
			if(headUrl != null && headUrl != ''){
				$("#headUrlDiv").empty();
				$("#headUrlDiv").html("<a href='javascript:void(0)' id='headUrl' style='background-image:url("+headUrl+")'></a>");
			}
			
			//获取详细职位列表
			$("#positionTagList").on("click",".selectPosition",function(){
				var positionName = $(this).data("tag");
				var locationCode = $(this).data("code");
				
				$("#position-lists").empty();
				
				$("#positionCount").html(0);//清空职位总数
				
				getPositionList(/*意向职位*/positionName, /*意向城市代码*/locationCode);
				
			});
			
			//复选框点击
			$("#position-lists").on("click","input",function(){
				var pid = $(this).data("pid");
				var positionCount = $("#positionCount").html();
				positionCount = parseInt(positionCount);
				if($(this).is(':checked')) {
					positionCount = positionCount + 1;
				}else{
					positionCount = positionCount - 1;
				}
				$("#positionCount").html(positionCount);
			});
			
			//推送职位提交按钮
			$("#submitPositionPush").click(function(){
				var positionCount = $("#positionCount").html();
				var pids = [];
				if(positionCount == '0'){
					alert("请选择职位");
					return;
				}else{
					var len = $("#position-lists").find("li").length;
					var lis = $("#position-lists").find("li");
					for(var i = 0; i < len; i++){
						if(lis.eq(i).find("input").is(':checked')){
							var pid = lis.eq(i).find("input").data("pid");
							pids.push(pid);
						}
					}
					//提交推送职位
					sendPushPositions(pids, userId);
				}
			});
			
			//发送推荐职位
			function sendPushPositions(pids, userId){
				$.ajax({                
					cache: false,    
					async: false, 
					type: "POST",                
					url:  '${BASE_PATH}/management/stu/main/sendPushPositions',                
					data:{
						pids : pids.join(","),
						userId : userId
					},  
					beforeSend : function(request){
						$("#submitPositionPushIng").show();
						$("#submitPositionPush").hide();
					},
					error: function(request) { 
						alert("网络错误");
					},                
					complete: function(data) {
						var dataStr = data.responseText;
						var datas = eval('('+dataStr+')');
						if(datas.success){
							alert("推送成功");
						}else{
							alert("推送失败");
						}
						$("#submitPositionPushIng").hide();
						$("#submitPositionPush").show();
					}           
				});
			}
			
			//获取职位列表
			function getPositionList(positionName, locationCode){
				$.ajax({                
						cache: false,    
						async: false, 
						type: "POST",                
						url:  '${BASE_PATH}/management/stu/main/positionList',                
						data:{
							positionName : positionName,
							locationCode : locationCode
						},  
						beforeSend : function(request){
						},
						error: function(request) { 
							alert("网络错误");
						},                
						success: function(data) {
							if(data.obj != null && data.obj != ''){
								var datas = eval('('+data.obj+')');
								var len = datas.data.length;
								if(len > 0){
									var name = "";
									var id = "";
									var city = "";
									var html = "";
									$("#position-lists-panle").show();
									for(var i = 0; i < len; i++){
										name = datas.data[i].name;
										id = datas.data[i].id;
										city = datas.data[i].city;
										html = html + " <li>"+
										"					<span class='info'>"+name+"</span>"+
										"					<span class='info'>"+city+"</span>"+
										"					<span class='info'><input type='checkbox' name='chosePosition' data-pid='"+id+"'/></span>"+
										"				</li>";
									}
									$("#position-lists").append(html);
								}
							}
						}           
					});
			}
		});
	</script>
</body>
</html>