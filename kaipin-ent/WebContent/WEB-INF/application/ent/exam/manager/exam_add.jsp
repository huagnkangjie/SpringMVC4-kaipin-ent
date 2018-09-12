<!--  header-start -->
<%@ include file="/WEB-INF/application/main/header_ent.jsp"%>
<!--  header-end -->
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

	<!-- 系统 -->
	<link href="${STATIC_ENT }/css/dialog.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="${STATIC_ENT }/js/dialog3.0.js" ></script>

	<script type="text/javascript">
		
		function save(){
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
			document.formExam.submit();
		}
	</script>
	
  		<!--笔试题 开始-->
		<div class="writer-exam">
			
			<!--we-l-tlt start-->
				<div class="we-l-tlt">
					<div class="wt-panel">
						<h5>${positionName }添加笔试题</h5>
						<div class="we-manage fr">
								<a href="javascript:void(0)" onclick="save();" id="save" class="mg-btn"> 提交 </a>
								<a href="javascript:void(0)"  id="saveing" style="display:none;" class="mg-btn"> 提交中 </a>
						</div>
					</div>
				</div>			
			<!--we-l-tlt end-->
				
				
			<form name="formExam" id="formExam" method="post" action="<%=path %>/examManager/add.do">
			<input type="hidden" id="editFlag" value="0"/>
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
								</ul>
						</div>
						<a href="javascript:void(0)" class="weq-change weq_next" id="weq_next"><i></i></a>
						<a href="javascript:void(0)" class="weq-change weq_add" id="weq_addPage"><i></i></a>
					</div>
					
					
						<input type="hidden" name="positionId" id="positionId" value="${positionId }"/>
						<input type="hidden" id="question-length" value="1"/>
						<div class="weq-detalis" id="questions">
							<div class="weqd-cons">
								<div class="page-lists" id="WE-pages">
									<div class="page pageCurrent">
											<div class="wp-contents">
												<span class="caption">题目</span>
												<textarea id="question0" class="txt" name="examList[0].question"></textarea>
											</div>
<!-- 											<div class="wp-contents"> -->
<!-- 												<span class="caption">题目</span> -->
<!-- 												<textarea class="txt" name="area[1].subject"></textarea> -->
<!-- 											</div> -->
											<div class="wp-suer-cancle">
<!-- 												<input type="submit" class="wp-btn wp-sure" value="确认"/> -->
<!-- 												<input type="button" class="wp-btn wp-cancle" value="取消"/> -->
											</div>
									</div>
								</div>
							</div>
						</div>
					</form>
				</div>
				
				
			</div>
		</div>
		<!--笔试题 结束-->
		
		<!-- 		 footer-start -->
		<%@ include file="/WEB-INF/application/main/footer.jsp"%>
		<!-- 		 footer-end -->
		<script type="text/javascript" src="${STATIC_ENT }/js/exam/exam.js?v.<%=System.currentTimeMillis()%>"></script>
  </body>
</html>
