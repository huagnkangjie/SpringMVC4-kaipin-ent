<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/sys/header.jsp"%>
<link rel="stylesheet" href="${BASE_PATH}/public/assets/css/basic.css" />
<script src="${BASE_PATH}/public/assets/js/rotate/CJL.0.1.min.js"></script>
<script src="${BASE_PATH}/public/assets/js/rotate/ImageTrans.js"></script>
</head>
<body>
	
	<div id="tzui-loading-overlay" class="tzui-loading-overlay" style="display: none;"></div>
	
	<!-- 一对一服务 开始 -->
	<div class="one-to-one-panle">
		<div class="basic-info-panle">
			<div class="head-info">
				<c:if test="${logo == '0' }">
					<img width="100" height="100" src="${BASE_PATH}/public/assets/images/moren_guanzhu.jpg"/>
				</c:if>
				<c:if test="${logo != '0' }">	
					<img width="100" height="100" src="${logo }"/>
				</c:if>
			</div>
			<div class="basic-info">
				<span>${userName } <font class="vip">VIP</font></span>
				<span>历史推送次数: ${oneToOneCount }</span>
				<span>历史推送职位数: ${oneToOnePositionCount }</span>
			</div>
		</div>
		<div id="positionTagList">
			<ul>
				${positionListTip }
				<c:forEach items="${positionList }" var="m">
					<li>
						<span class="info">${m.position_tag }</span>
						<span class="info">${m.location_name }</span>
						<span class="info"><input type="radio" data-tag="${m.position_tag }" data-code="${m.location_code }" class="selectPosition" name="selectPosition"/></span>
					</li>
				</c:forEach>
			</ul>
		</div>
	</div>
	<div class="position-lists-panle" id="position-lists-panle" style="display: none;">
		<div class="position-lists" >
			<ul>
				<li>
					<span class="info">职位名称</span>
					<span class="info">工作地区</span>
					<span class="info">操作</span>
				</li>
			</ul>
			</br>
			
			<!-- 数据库查询职位 -->
			<ul id="position-lists">
				<li>
					<span class="info">求职意向</span>
					<span class="info">北京、广州</span>
					<span class="info"><input type="checkbox" data-pid="position1" name="chosePosition"/></span>
				</li>
    
    <li>
					<span class="info">求职意向</span>
					<span class="info">北京、广州</span>
					<span class="info"><input type="checkbox" data-pid="position2" name="chosePosition"/></span>
				</li>
    <li>
					<span class="info">求职意向</span>
					<span class="info">北京、广州</span>
					<span class="info"><input type="checkbox" data-pid="position3" name="chosePosition"/></span>
				</li>
    <li>
					<span class="info">求职意向</span>
					<span class="info">北京、广州</span>
					<span class="info"><input type="checkbox" data-pid="position4" name="chosePosition"/></span>
				</li>
    <li>
					<span class="info">求职意向</span>
					<span class="info">北京、广州</span>
					<span class="info"><input type="checkbox" data-pid="position5" name="chosePosition"/></span>
				</li>
    <li>
					<span class="info">求职意向</span>
					<span class="info">北京、广州</span>
					<span class="info"><input type="checkbox" data-pid="position6" name="chosePosition"/></span>
				</li>
    
			</ul>
			
		</div>
		<div class="position-btns">
			<button class="btn btn-primary btn-small js-ajax-pass"
					type="submit" data-action="/admin_post/listorders.html" id="submitPositionPush">推送，已选择职位（<span id="positionCount">0</span>）</button>
			<button class="btn btn-primary btn-small js-ajax-pass"
					type="submit" data-action="/admin_post/listorders.html" id="submitPositionPushIng" style="display:none;">推送中...</button>
<!-- 			<button class="btn btn-primary btn-small js-ajax-pass" -->
<!-- 					type="submit" data-action="/admin_post/listorders.html" id="submit">评价</button> -->
		</div>
	</div>
	
	<!-- 一对一服务 结束 -->
	
	<script src="${BASE_PATH}/public/js/common.js"></script>
	<script src="${BASE_PATH}/public/assets/js/page.js"></script>
	
	<script type="text/javascript">	
	
		var userId ;
		$(function(){
			
			userId = '${userId}';
			
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
							location.href= '${BASE_PATH }/management/stu/main/viplist'
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