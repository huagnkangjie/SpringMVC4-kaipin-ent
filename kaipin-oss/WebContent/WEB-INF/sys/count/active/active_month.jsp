<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/sys/header.jsp"%>
 <script src="${BASE_PATH}/public/assets/js/count/count.js"></script>
<style>
.home_info li em {
	float: left;
	width: 120px;
	font-style: normal;
}

li {
	list-style: none;
}
</style>
</head>
<body>
	
	<div id="monthActiveCount" class="indexCount" style="margin-top: 15px;width:100%"></div>
	<script src="${CONTEXT_PATH}/public/js/common.js"></script>
	<script src="${CONTEXT_PATH}/public/js/highcharts/highcharts.js"></script>
	<script src="${CONTEXT_PATH}/public/js/highcharts/highcharts-3d.js"></script>
	<script type="text/javascript">
		$(function(){
			getMonthActiveCount();
		});
	
	</script>

</body>
</html>