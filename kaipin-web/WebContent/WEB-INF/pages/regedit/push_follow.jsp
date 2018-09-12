<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
  
  	<%@ include file="/WEB-INF/pages/regedit/comment/regHeader.jsp" %>
  	
	<script type="text/javascript">
		var r_path='<%=basePath%>';
	</script>
	<script type="text/javascript">
		var count;
		var userId;
		var orgId;
		$(function(){
			count = '${count}';
			userId = '${userId}';
			orgId = '${orgId}';
			if(count == 0){
				location.href="<%=path%>/regedit/init?oper=qr_code&userId="+userId;
			}else{
				$("#countPush").html(count);
				$("#countPushIng").html(count);
			}
		});
	</script>
	</head>
	<body>
		<!--header start-->
		<div class="lm-header-container">
			<div class="header">
				<h1>
					<a href="javascript:void(0)" class="logo"></a>
					<span class="tips-txt"> - 创建您的开频账户</span>
				</h1>
			</div>
		</div>
		<!--header end-->
		<!--推荐关注-->
		<div class="recommend-attention">
			<div class="rcm-att-title">
				<h3>推荐关注</h3>
				<p>他们都有可能是你以后的客户或合作伙伴，积累人脉越早越好。</p>
			</div>
			
			<div class="rcm-cons" id="recommend-cons">
				<ul>
				 	<c:forEach var="m" items="${list }">
				 		<li data-tag="${m.userId }" data-utype="${m.userType }">
							<div class="head-pic">
								<c:if test="${m.url == '0' }">
									<img src="<%=path %>/images/moren_fans.jpg"  width="180" height="180" alt=""/>
								</c:if>
								<c:if test="${m.url != '0' }">
									<img src="${m.url }"  width="180" height="180" alt=""/>
								</c:if>
							</div>
							<div class="basic-infos">
							<span class="isApprove" title="已认证"></span>
								<p class="name">${m.name }
									
								</p>
								<p class="state">${m.row2 }</p>
								<p class="address">${m.area }</p>
							</div>
							<div class="attention add-attention" title="添加关注"></div>
							<div class="attention cancel-attention" title="取消关注"></div>
						</li>
				 	</c:forEach>
				</ul>
			</div>
			
		</div>
		
		<div class="attention-all">
			<div class="att-btns">
				<a href="javascript:void(0)" class="skip-btns skip" title="跳过" onclick="next();"> 跳过</a>
				<a href="javascript:void(0)" class="skip-btns addAll" id="add-attentionAll" title="关注全部"> + 共<span id="countPush">15</span>个对象，关注全部</a>
				<a href="javascript:void(0)" class="skip-btns addAll" id="add-attentionAll-Ing" style="display:none;" title="关注全部"> + 共<span id="countPushIng">15</span>个对象，关注全部
					<img alt="" src="<%=path%>/images/loading.gif"/>
				</a>
			</div>
		</div>
		
		<!-- footer_reg start-->
		<%@ include file="/WEB-INF/pages/regedit/comment/regFooter.jsp" %>
		<!-- footer_reg end-->
		
		<script type="text/javascript">
		
			var userId;
			var orgId;
			$(function(){
				userId = '${userId}';
				orgId = '${orgId}';
			});
			
			/* 下一步 */
			function next(){
				location.href="<%=path%>/regedit/init?oper=qr_code&userId="+userId;
				
			}
			
			//guanzhu
			attentionFn("add-attention","cancel-attention");
			attentionFn("cancel-attention","add-attention");
			
			//关注单个
			function attentionFn(obj1,obj2){
				$("#recommend-cons").find("li").on("click","."+obj1,function(){
					var fromId = $(this).parents("li").data("tag");
					var fromUserType = $(this).parents("li").data("utype");
					if(obj1 == 'add-attention'){
						doPushAdd(orgId, fromId, fromUserType);
					}else if(obj1 == 'cancel-attention'){
						doPushDel(orgId, fromId, fromUserType);
					}
					$(this).hide();
					var index = $(this).parents("li").index();
					$("#recommend-cons li").eq(index).find("."+obj2).show();
				});
			}
			
			//关注所有
			$("#add-attentionAll").click(function(){
				var length = $("#recommend-cons li").length;
				var $obj =  $("#recommend-cons li");
				var ids = "";
				var utypes = "";
				for(var i = 0;i < length;i++){
					ids = ids + "," +$obj.eq(i).data("tag");
					utypes = utypes + "," +$obj.eq(i).data("utype");
				}
				
				ids = ids.substring(1,ids.length);
				utypes = utypes.substring(1,utypes.length);
				
				$("#add-attentionAll").hide();
				$("#add-attentionAll-Ing").show();
				setTimeout(function(){
					doPushAdd(orgId, ids, utypes);
				},200)
				
				setTimeout(function(){
					$("#recommend-cons li").find(".add-attention").hide();
					$("#recommend-cons li").find(".cancel-attention").show();
					$("#add-attentionAll").show();
					$("#add-attentionAll-Ing").hide();
					
					location.href="<%=path%>/regedit/init?oper=qr_code&userId="+userId;
				},1000)
				
				
			});
			
			function doPushAdd(fromId,toId, fromUserType){
				$.ajax({                
					cache: false,    
					async: true, 
					type: "POST",                
					url:  '<%=path%>/rfollow/addPush',                
					data:{
						fromId : fromId,
						toId : toId,
						userId : userId,
						fromUserType : fromUserType
					},
					error: function(request) { 
					},
					beforeSend : function(request){
						
					},
					success: function(data) {
						
					},
					complete: function(data) { 
						var dataStr = data.responseText;
					}
				});
			}
			function doPushDel(fromId,toId){
				$.ajax({                
					cache: false,    
					async: true, 
					type: "POST",                
					url:  '<%=path%>/rfollow/delPush',                
					data:{
						fromId : fromId,
						toId : toId
					},
					error: function(request) { 
					},
					beforeSend : function(request){
						
					},
					success: function(data) {
						
					},
					complete: function(data) { 
						var dataStr = data.responseText;
					}
				});
			}
		</script>
	</body>
</html>
