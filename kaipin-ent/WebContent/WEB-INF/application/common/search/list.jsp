<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!-- header-start -->
<%@ include file="/WEB-INF/application/main/header_ent.jsp"%>
<!-- header-end -->
	<!-- 视频播放 -->
	<link rel="stylesheet" type="text/css" href="${STATIC_SCH }/js/vedio/video-js.css"/>
	<script type="text/javascript" src="${STATIC_SCH }/js/vedio/video.js" ></script>

<script type="text/javascript">

	var result;
	var searchType="";
	var searchTxt="";
	
	$(function(){
		result = '${result}';
		searchType = '${searchType}';
		searchTxt = '${searchTxt}';
		$("#selection-"+searchType).addClass("selection");
		$("#search-text").val(searchTxt);
		
		if(result != '0' && result != 'error'){
			eachSearchResult(/*查询返回结果*/result, /*查查类型*/searchType);
		}
	});
</script>

	
	<div class="container">
		<div class="search-reslut">
			<div class="search-count">
				<div class="search-switch">
					<a id="selection-stu" data-tag="stu" href="javascript:void(0);">会员</a>
					<a id="selection-position" data-tag="position" href="javascript:void(0);">职位</a>
					<a id="selection-ent" data-tag="ent" href="javascript:void(0);">企业</a>
					<a id="selection-sch" data-tag="sch" href="javascript:void(0);">高校</a>
					<a id="selection-live" data-tag="live" href="javascript:void(0);">视频</a>
				</div>搜索
				<span>“${searchTxt }”</span>的相关结果（共<span id="searchCount">0</span>个结果）
			</div>
			<!-- 遍历信息开始 -->
			<div id="search-result-list">
				<%-- <div class="search-row">
					<div class="search-head-img-panle">
						<img class="search-head-img" alt="" src="${STATIC_SCH }/images/moren_guanzhu.jpg">
					</div>
					<div class="search-basic-info-panle">
						<div>
							<span class="user-name"><span class="search-bright">习大大</span></span>
							<span class="user-keyword">产品运营、完美主义者</span>
						</div>
						<div class="detail">
							<span class="basic-info weak">目前就职 <span class="basic-info-detail">按时打<span class="search-bright">发打发杀死对方</span>是打发啊</span></span>
							<span class="basic-info weak">曾经就职 <span class="basic-info-detail">按时打发<span class="search-bright">打发杀死对方</span>是打发啊</span></span>
							<span class="basic-info">求职意向 <span class="basic-info-detail"><span class="search-bright">按时打发</span>打发杀死对方是打发啊</span></span>
						</div>
					</div>
					<div class="search-detai-btn-panle">
						<div class="send-meeting-btn fr">
							<a href="javascript:void(0)" class="detail-btn search-btn">查看详情</a>
						</div>
					</div>
				</div> --%>
			</div>
			<!-- 遍历信息结束 -->
			<input type="hidden" id="moreSearchIng" value="0"/>
			<div class="upload-addMore" id="moreSearch" style="display:none;" >
				<a href="javascript:void(0)" class="more">数据加载中
					<img src="${STATIC_SCH }/images/loading.gif">
				</a>
			</div>
		</div>
	</div>
	
		<input type="hidden" id="searchMoreType" value="${searchType }"/>
		<input type="hidden" id="searchPage" value="1"/>
		<input type="hidden" id="searchPageSize" value="5"/>
	
		
		<!--视频播放 开始-->
		<div id="video-contents">
			<div class="vd-title">
				<p id="vedioName">宣讲会预告/视频</p>
				<a href="javascript:void(0)" class="close-xx" id="close-video" title="关闭"></a>
			</div>
			<div class="vd-panelBox">
			 <video id="example_video_1" class="video-js vjs-default-skin" controls preload="none" width="855" height="556"
			      poster=""
			      data-setup="{}">
			    <source src="http://img1.kaipin.tv:81/Qy3/vedio/dae7bcc3-199a-496a-ae0d-80143b179d09.mp4" type='video/mp4' />
			    <p class="vjs-no-js">To view this video please enable JavaScript, and consider upgrading to a web browser that 
				    <a href="http://videojs.com/html5-video-support/" target="_blank">supports HTML5 video
				    </a>
			    </p>
			 </video>
			</div>
		</div>
		<!--视频播放 结束-->
		
		<!-- header-start -->
		<%@ include file="/WEB-INF/application/main/footer.jsp"%>
		<!-- header-end -->
		
		<script type="text/javascript">
			  //图片查询中正对浏览器主页面滚动事件处理(瀑布流)。只能使用window方式绑定，使用document方式不起作用
			  $(window).on('scroll',function(){
			    if(scrollTop() + windowHeight() >= (documentHeight() - 50/*滚动响应区域高度取50px*/)){
			      waterallowData();
			    }
			  });
			
			  function waterallowData(){
				  var operIng = $("#moreSearchIng").val();
			    	if(operIng == '0'){
			    		$("#moreSearchIng").val(1);
			    		$("#moreSearch").show();
			    		console.log(">>>>>> 开始查询");
			    		var page = $("#searchPage").val();
						var pageSize = $("#searchPageSize").val();
						var searchType = $("#searchMoreType").val();
						var searchTxt = $("#search-text").val();
						setTimeout(function(){
							search(/*搜索类型*/searchType, /*搜索内容*/searchTxt, page , pageSize);
						},1500);
						
			    	}else{
			    		console.log(">>>>>> 查询中，请稍等");
			    	}
			  }
  	</script>