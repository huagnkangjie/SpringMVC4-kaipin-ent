<!--  header-start -->
		<%@ include file="/WEB-INF/pages/main/header.jsp"%>
<!--  header-end -->
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


	<script type="text/javascript">
		
	</script>
	
	<!--消息详情中心 开始-->
		<div class="message-cons-center">
			<div class="mcc-defalut-container">
				<div class="mcc-title">面试消息<span class="record">共${counts }条</span></div>
				<div class="mcc-details-lists">
					<ul>
						<c:forEach var="m" items="${msgEntViewList }">
							<li onclick="test();" class="read-already">
							<a href="<%=path %>/msgController/viewDetai.do?viewId=${m.id}">
								<i class="envelope-icon"></i>
								<span class="envelope-title">
									${m.title }
									<c:if test="${m.status == 0 }">(未读)</c:if>
									<c:if test="${m.status == 1 }">(已读)</c:if>
								</span>
								<span class="envelope-timer">
									${m.createTime }
								</span>
							</a>
						</li>
						</c:forEach>
					</ul>
				</div>
			</div>
		</div>
		<!--消息详情中心 开始-->
  	
		<!--footer-->
		<!-- 		 footer-start -->
		<%@ include file="/WEB-INF/pages/main/footer.jsp"%>
		<!-- 		 footer-end -->
  </body>
</html>
