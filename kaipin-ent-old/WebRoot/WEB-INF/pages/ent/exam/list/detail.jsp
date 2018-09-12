<!--  header-start -->
<%@ include file="/WEB-INF/pages/main/header.jsp"%>
<!--  header-end -->
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

	<!-- 系统 -->
	<link href="<%=path%>/css/dialog.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="<%=path%>/js/dialog3.0.js" ></script>

	<script type="text/javascript">
// 		$(function(){
// 			$.ajax({                
// 				cache: false,    
// 				async: false, 
// 				type: "POST",                
<%-- 				url:  '<%=path%>/entMeetingNoticeController/del.do',                 --%>
// 				data:{
// 					meetingId : encodeURI(newContents)
// 				},              
// 				error: function(request) {                    
// 				},                
// 				success: function(data) {
					
// 				}            
// 			});
// 		});
	</script>
	
  		<!--笔试题 开始-->
		<div class="writer-exam">
			
			<!--we-l-tlt start-->
				<div class="we-l-tlt">
					<div class="wt-panel">
						<div class="we-result fl">
							<input type="hidden" value="${inviteDetial.positionId }" id="positionId"/>
							<input type="hidden" value="${inviteDetial.position_name }" id="positionName"/>
							<h4 class="re-title">${userName }的${inviteDetial.position_name }笔试结果</h4>
							<p class="re-timer"><span>总用时：${inviteDetial.second }秒</span>提交答案时间：${inviteDetial.create_time }</p>
						</div>
						<div class="we-manage fr">
								<input type="hidden" id="inviteId" value="${inviteId }"/>
								<a href="javascript:void(0)" class="mg-nopass" onclick="passOrNo(7);"> 不通过笔试 </a>
								<a href="javascript:void(0)" class="mg-btn" onclick="passOrNo(8);"> 通过笔试 </a>
						</div>
					</div>
				</div>			
			<!--we-l-tlt end-->
				
			<div class="we-lists">
				<div class="we-queste">
					<div class="weq-title">
						<a href="javascript:void(0)" class="weq-change weq_pre" id="weq_req_pre"><i></i></a>
						<!--标题列表 开始-->
						<div class="weq-t-lists">
							<ul id="WEQ-lists-req" class="WEQ-lists-title">
								<li class="pages_item current">
									<a href="javascript:void(0)">第1题</a>
								</li>
								<c:forEach var="m" items="${answerList }" varStatus="status">
									<li class="pages_item">
										<a href="javascript:void(0)">第${status.index + 2 }题</a>
									</li>
								</c:forEach>
							</ul>
						</div>
						<!--标题列表 结束-->
						<a href="javascript:void(0)" class="weq-change weq_next" id="weq_req_next"><i></i></a>
					</div>
					
					<div class="weq-detalis  weq-question">
						
						<!--result-requests-lists start-->
						<div class="result-requests-lists" id="result-requests-lists">
							<div class="wrl-page pageCurrent">
								<div class="pg-tlt">
									<span class="pg-tips">题目：</span>
									<div class="tlt">${listOne.question }</div>
								</div>
<!-- 								<div class="pg-timer"> -->
<!-- 									<span class="pg-tips">用时：</span> -->
<!-- 									2分20秒 -->
<!-- 								</div> -->
								<div class="pg-answer">
									<span class="pg-tips">答案：</span>
									<div class="answer">
										${listOne.ianswer }
									</div>
								</div>
							</div>
							
							<c:forEach var="m" items="${answerList }">
								<div class="wrl-page ">
								<div class="pg-tlt">
									<span class="pg-tips">题目：</span>
									<div class="tlt">${m.question }</div>
								</div>
<!-- 								<div class="pg-timer"> -->
<!-- 									<span class="pg-tips">用时：</span> -->
<!-- 									2分20秒 -->
<!-- 								</div> -->
								<div class="pg-answer">
									<span class="pg-tips">答案：</span>
									<div class="answer">
										${m.ianswer }
									</div>
								</div>
							</div>
							</c:forEach>
							
							
						</div>
						<!--result-requests-lists end-->
						<!--下一题 开始-->
<!-- 						<div class="next-questions"> -->
<!-- 							<a href="javascript:void(0)" class="goNext">下一题</a> -->
<!-- 						</div> -->
						<!--下一题 结束-->
					</div>
				</div>
				
				
			</div>
		</div>
		<!--笔试题 结束-->
		<!-- 		 footer-start -->
		<%@ include file="/WEB-INF/pages/main/footer.jsp"%>
		<!-- 		 footer-end -->
		
		<script type="text/javascript" src="<%=path%>/js/exam/exam.js?v.<%=System.currentTimeMillis()%>"></script>
		
		<script type="text/javascript">
			$(function(){
				
				//笔试题切换添加
				var addNum = 1;
				var animateNum = 0;
				var oldPageNum = $("#WEQ-lists-req").find("li").length;
				if(oldPageNum>8){
					$("#weq_req_pre").show();
					$("#weq_req_next").show();
				}else{
					$("#weq_req_pre").hide();
					$("#weq_req_next").hide();
				}
					
					
				//切换
				$("#WEQ-lists-req").on("click","li",function(){
					var $index = $(this).index();
					$(this).addClass("current").siblings().removeClass("current");
					$("#result-requests-lists").find(".wrl-page").eq($index).addClass("pageCurrent").siblings().removeClass("pageCurrent");
				});
				
				
				//下一个
				$("#weq_req_next").on("click",function(){
					var len = $("#WEQ-lists-req").find("li").length;
					animateNum++;
					if(animateNum>len-8){
						animateNum=len-8;
					}
					$("#WEQ-lists-req").animate({"left":-(animateNum*95)},200);
				});
				
				//上一个
				$("#weq_req_pre").on("click",function(){
					animateNum--;
					if(animateNum<=0){
						animateNum=0;
					}
					$("#WEQ-lists-req").animate({"left":-(animateNum*95)},200);
				});
				
			});
		</script>
  </body>
</html>
