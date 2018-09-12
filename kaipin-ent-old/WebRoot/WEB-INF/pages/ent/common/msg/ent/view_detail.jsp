<!--  header-start -->
		<%@ include file="/WEB-INF/pages/main/header.jsp"%>
<!--  header-end -->
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

  	
		<!--消息详情中心 开始-->
		<div class="message-cons-center">
			<div class="msg-details-info">
				<div class="msg-title-info">
					<p class="msg-tlt fl">${view.title }</p>
<!-- 					<p class="msg-date fr">215年11月30日    <span>17:30</span></p> -->
					<p class="msg-date fr">${time }</p>
				</div>
				<div class="msg-contents-part">
					${view.content }
				</div>
			</div>
		</div>
		<!--footer-->
		<!-- 		 footer-start -->
		<%@ include file="/WEB-INF/pages/main/footer.jsp"%>
		<!-- 		 footer-end -->
  </body>
</html>
