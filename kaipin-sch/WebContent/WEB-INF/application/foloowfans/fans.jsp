<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- header-start -->
<%@ include file="/WEB-INF/application/main/header.jsp"%>
<!-- header-end -->
	<script type="text/javascript" src="${STATIC_SCH }/js/home/home.js"></script>
	<!-- 截图插件 -->
	<link href="${STATIC_SCH }/js/imgareaselect/css/imgareaselect-default.css" rel="stylesheet" type="text/css" />
    <script src="${STATIC_SCH }/js/imgareaselect/jquery.imgareaselect.pack.js" type="text/javascript"></script>
	<!-- uploadify -->
	<link rel="stylesheet" type="text/css" href="${STATIC_SCH }/js/uploadify/Huploadify.css"/>
	<script type="text/javascript"  src="${STATIC_SCH }/js/uploadify/jquery.Huploadify.js"></script>
	<!-- 图片裁剪 -->
	<link href="${STATIC_SCH }/js/image/css/cropper.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${STATIC_SCH }/js/image/bootstrap.min.js?v.<%=System.currentTimeMillis()%>"></script>
	<script type="text/javascript" src="${STATIC_SCH }/js/image/cropper.js?v.<%=System.currentTimeMillis()%>"></script>
		
	<script type="text/javascript">
		var org_id;
		
		$(function(){
			org_id = '${org_id}';
			$("#orgIdInFollowPage").val(org_id);
			getFollowListPage(1, 20, 'fans', org_id, "");
		});
	</script>
		
		<!--fansContainer start-->
		<div class="fansContainer">
			<div class="fans-box">
				<div class="fans-num">
					<h5>粉丝<span id="fansPageCount">0</span>人</h5>
				</div>
				<div class="fans-lists">
					<div id="fansPageList">
					</div>
				</div>
				<div class="upload-addMore" style="">
					<input type="hidden" value="0" id="fansListIngVal"/>
					<input type="hidden" value="1" id="fansListPage"/>
					<input type="hidden" value="" id="toUserType"/>
					<input type="hidden" value="" id="orgIdInFollowPage"/>
					<a href="javascript:void(0)" id="fansListMore" style="display:none;" class="more">加载更多</a>
					<a href="javascript:void(0)" id="fansListMoreIng" class="more" style="display:none;">加载中
						<img src="${STATIC_SCH }/images/loading.gif" style="margin-right:5px;"/>
					</a>
				</div>
			</div>
		</div>
		<!--fansContainer end-->
		
		
		<!-- header-start -->
		<%@ include file="/WEB-INF/application/main/footer.jsp"%>
		<!-- header-end -->
		
		<script type="text/javascript" src="${STATIC_SCH }/js/videoPlay.js"></script>
		<script type="text/javascript" src="${STATIC_SCH }/js/thumbUp.js"></script>
		<script type="text/javascript" src="${STATIC_SCH }/js/comments.js"></script>
		
		<script type="text/javascript">
			  //图片查询中正对浏览器主页面滚动事件处理(瀑布流)。只能使用window方式绑定，使用document方式不起作用
			  $(window).on('scroll',function(){
			    if(scrollTop() + windowHeight() >= (documentHeight() - 50/*滚动响应区域高度取50px*/)){
			      waterallowData();
			    }
			  });
			
			  function waterallowData(){
				  var operIng = $("#fansListIngVal").val();
			    	if(operIng == '0'){
			    		$("#fansListIngVal").val("1");
			    		console.log(">>>>>> 开始查询");
			    		var toUserType = $("#toUserType").val();
						var org_id = $("#orgIdInFollowPage").val();
						var page = $("#fansListPage").val();
						debugger;
						getFollowListPage(page, 20, 'fans', org_id, toUserType);
			    	}else{
			    		console.log(">>>>>> 查询中，请稍等");
			    	}
			  }
	  	</script>
		

		
	</body>
	
</html>

