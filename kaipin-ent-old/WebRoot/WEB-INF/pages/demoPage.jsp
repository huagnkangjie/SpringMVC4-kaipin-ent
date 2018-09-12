<!--  header-start -->
<%@ include file="/WEB-INF/pages/main/header.jsp"%>
<!--  header-end -->
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

	<script src="<%=path%>/js/jquery-easyui-1.2.6/jquery-1.7.2.min.js" charset="UTF-8" type="text/javascript"></script>
	<script src="<%=path%>/js/jquery.cookie.js" charset="UTF-8" type="text/javascript"></script>
	<link id="easyuiTheme" href="<%=path%>/js/jquery-easyui-1.2.6/themes/default/easyui.css" rel="stylesheet" type="text/css" media="screen">
	<script src="<%=path%>/js/changeEasyuiTheme.js" charset="UTF-8" type="text/javascript"></script>
	<link href="<%=path%>/js/jquery-easyui-1.2.6/themes/icon.css" rel="stylesheet" type="text/css" media="screen">
	<script src="<%=path%>/js/jquery-easyui-1.2.6/jquery.easyui.min.js" charset="UTF-8" type="text/javascript"></script>
	<script src="<%=path%>/js/jquery-easyui-1.2.6/locale/easyui-lang-zh_CN.js" charset="UTF-8" type="text/javascript"></script>
	<link href="<%=path%>/css/baseCss.css" rel="stylesheet" type="text/css" media="screen">
	<script src="<%=path%>/js/syUtils.js" charset="UTF-8" type="text/javascript"></script>
	<link href="<%=path%>/css/common.css" rel="stylesheet" type="text/css" media="screen">

	<script type="text/javascript">
		$(function(){
			$.ajax({                
				cache: false,    
				async: false, 
				type: "POST",                
				url:  '<%=path%>/entMeetingNoticeController/del.do',                
				data:{
					meetingId : encodeURI(newContents)
				},              
				error: function(request) { 
					alert("网络错误");
				},
				beforeSend : function(request){
				},
				success: function(data) {
					
				},
				complete: function(data) { 
					var dataStr = data.responseText;
				}
			});
		});
	</script>
	
		<!-- 		 footer-start -->
		<%@ include file="/WEB-INF/pages/main/footer.jsp"%>
		<!-- 		 footer-end -->
  </body>
</html>
