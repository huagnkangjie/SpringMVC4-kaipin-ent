<!--  header-start -->
<%@ include file="/WEB-INF/pages/main/header.jsp"%>
<!--  header-end -->
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
	
	<!-- 系统 -->
	<link href="<%=path%>/css/dialog.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="<%=path%>/js/dialog3.0.js" ></script>

	

	<script type="text/javascript">
		var positionName;
		$(function(){
			positionName = '${positionName }';
			$("#positionName").html(positionName);
		});
		
		function edit(){
			$("#save").hide();
			$("#saveing").show();
			var length = $("#WE-pages").find(".page").length;
			for(var i = 0; i < length ;i++){
				$val  = $("#WE-pages .page").eq(i).find(".txt").val();
				if($val.trim().length == 0){
					alert("第" + (i+1) +"题题目为空");
					$("#save").show();
					$("#saveing").hide();
					return;
				} 
			}
			setTimeout(submits(),50);
		}
		function submits(){
			document.formExamEdit.submit();
		}
	</script>
	
  		<!--笔试题 开始-->
		<div class="writer-exam">
			
			<!--we-l-tlt start-->
				<div class="we-l-tlt">
					<div class="wt-panel">
						<h5><span id="positionName"></span>笔试题管理</h5>
						<div class="we-manage fr">
								<a href="javascript:void(0)" onclick="edit();" id="save" class="mg-btn"> 提交 </a>
								<a href="javascript:void(0)"  id="saveing" style="display:none;" class="mg-btn"> 提交中 </a>
						</div>
					</div>
				</div>			
			<!--we-l-tlt end-->
				
			<div class="we-lists">
				<div class="we-queste" >
					
					<div class="weq-title">
						<a href="javascript:void(0)" class="weq-change weq_pre" id="weq_pre"><i></i></a>
						<div class="weq-t-lists">
								<ul id="WEQ-lists-title" class="WEQ-lists-title">
									<li class="pages_item current" data-current="open">
										<a href="javascript:void(0)">第1题</a>
										<span class="page-remove"></span>
									</li>
									<c:forEach var="m" items="${questionList }" varStatus="status">
										<li class="pages_item" data-current="open">
											<a href="javascript:void(0)">第${status.index + 2 }题</a>
											<span class="page-remove"></span>
										</li>
									</c:forEach>
								</ul>
						</div>
						<a href="javascript:void(0)" class="weq-change weq_next" id="weq_next"><i></i></a>
						<a href="javascript:void(0)" class="weq-change weq_add" id="weq_addPage"><i></i></a>
					</div>
					
					<input type="hidden" id="question-length" value="${questionLength }"/>
					<form name="formExamEdit" id="formExamEdit" method="post" action="<%=path %>/examManager/edit.do">
						<input type="hidden" name="paperId" id="paperId" value="${paperId }"/>
						<input type="hidden" name="editFlag" id="editFlag" value="${editFlag }"/>
						<input type="hidden" name="dbId" id="dbId" value="${dbId }"/>
						<div class="weq-detalis">
							<div class="weqd-cons">
								<div class="page-lists" id="WE-pages">
									<c:forEach var="m1" items="${questionListOne }" varStatus="status1">
										<div class="page pageCurrent">
											<div class="wp-contents">
												<span class="caption">题目</span>
												<input type="hidden" name="examList[0].id" value="${m1.id }"/>
												<textarea class="txt" id="question0" name="examList[0].question">${m1.question }</textarea>
											</div>
											<div class="wp-suer-cancle">
<!-- 												<input type="button" class="wp-btn wp-sure" value="确认"/> -->
<!-- 												<input type="button" class="wp-btn wp-cancle" value="取消"/> -->
											</div>
										</div>
									</c:forEach>
									<c:forEach var="m" items="${questionList }" varStatus="statusQuestion">
										<div class="page">
											<div class="wp-contents">
												<span class="caption">题目</span>
												<input type="hidden" name="examList[${statusQuestion.index + 1 }].id" value="${m.id }"/>
												<textarea class="txt" id="question${statusQuestion.index + 1 }" name="examList[${statusQuestion.index + 1 }].question">${m.question }</textarea>
											</div>
											<div class="wp-suer-cancle">
<!-- 												<input type="button" class="wp-btn wp-sure" value="确认"/> -->
<!-- 												<input type="button" class="wp-btn wp-cancle" value="取消"/> -->
											</div>
										</div>
									</c:forEach>
								</div>
							</div>
						</div>
					</form>
				</div>
				
				
			</div>
		</div>
		<!--笔试题 结束-->
		
		<!-- 		 footer-start -->
		<%@ include file="/WEB-INF/pages/main/footer.jsp"%>
		<!-- 		 footer-end -->
		<script type="text/javascript" src="<%=path%>/js/exam/exam.js?v.<%=System.currentTimeMillis()%>"></script>
  </body>
</html>
