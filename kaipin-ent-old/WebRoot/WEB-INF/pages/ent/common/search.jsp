 <!-- 		 header-start -->
		<%@ include file="/WEB-INF/pages/main/header.jsp"%>
	<!-- 		 header-end -->
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
	<script type="text/javascript">
		var datas ;
		var counts ;
		var searchList;
		$(function(){
			datas = '${searchDatas }';
			counts = '${searchCounts }';
			searchList = '${searchList }';
			$("#search-data").html("“"+datas+"”");
			$("#counts").html("共"+counts+"条");
		});
	
	</script>
 
  	<!--搜素结果 开始-->
		<div class="message-cons-center">
			<div class="search-results">
				<div class="srt-title">
					<span id="search-data">“出纳”</span>搜索结果<span class="record" id="counts">共6条</span>
				</div>
				
				<div class="srt-details-lists">
					<ul>
						<c:forEach var="m" items="${searchList }">
							<li>
								<a href="<%=path%>/resumeDetail/init.do?status=1&resumeId=${m.id}&relationId=${m.relationId}">
									<span></span> ${m.position_name } - ${m.name } - ${m.surname }${m.miss_surname } - ${m.major_name }
								</a>
							</li>
						</c:forEach>
					</ul>
				</div>
			</div>
		</div>
		<!--搜素结果 结束-->
		
		<!-- 		 header-start -->
		<%@ include file="/WEB-INF/pages/main/footer.jsp"%>
		<!-- 		 header-end -->
  </body>
</html>
