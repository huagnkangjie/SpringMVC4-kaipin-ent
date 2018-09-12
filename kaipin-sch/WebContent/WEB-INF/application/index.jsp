<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- header-start -->
<%@ include file="/WEB-INF/application/main/header.jsp"%>
<!-- header-end -->
	<!-- 弹窗 -->
	<link rel="stylesheet" href="${STATIC_SCH }/css/dialog.css" />
	<script type="text/javascript" src="${STATIC_SCH }/js/dialog3.0.js" ></script>
	<script type="text/javascript" src="${STATIC_SCH }/js/home/home.js"></script>
	<script type="text/javascript" src="${STATIC_SCH }/js/home/histroyVedio.js"></script>
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
	<script type="text/javascript" src="${STATIC_SCH }/js/image/imgcut.js?v.<%=System.currentTimeMillis()%>"></script>
	<script type="text/javascript" src="${STATIC_SCH }/js/image/logo.js?v.<%=System.currentTimeMillis()%>"></script>
		<script type="text/javascript">
		
			$(function(){
				$("#editBasic").show();
				
				$("#send-preachBtn").show();//发布就业指导视频按钮
				
				
				getFollowAndFans();//获取关注和粉丝的统计数
				getFeeds(1, "", "");//获取消息流
				getPushList("","init");//获取推荐关注
				getFollowList(1, 8, 'fans');//获取最近关注的粉丝
				//checkCertificate();//检查资质认证
				var checkStatus = '${checkStatus}';
				if(checkStatus == '0'){
					$("#certificate-tip").show();
				}
			});
		</script>
		
		
		
		
		<div class="container">
			<!--认证 开始-->			
			<div class="business-attestation" style="display:none;"  id="certificate-tip">
				<input type="hidden" id="userId" value="${cookie_uid }"/>
				<input type="hidden" id="companyId" value="${cookie_companyId }"/>
				<span>请完成高校资质认证，资质认证通过后能提升高校的公信力，提升高校被推荐的几率。</span>
				<a href="javascript:void(0)" class="att-btn" title="立即认证" onclick="certificateTipPage(99);">立即认证</a>
				<a href="javascript:void(0)" class="close-att" id="close-att" onclick="closeAtt();" title="关闭"></a>
			</div>
			<!--认证 结束-->
			
			
			<div class="mainBoxPeanel">
				
				<!--企业基本信息  企业认证 start-->
					<%@ include file="/WEB-INF/application/basic/basic_logo.jsp"%>
				<!--企业基本信息  企业认证 end-->
				
				<!--详细内容-->
				<div class="col-main">
					
					<!--左边-->
					<div class="leftContent fl">
						<!--添加头像 start-->
						<div class="add-headerPic" style="display:none;" id="editLogo">
							<!--添加显示 start-->
							<div class="addHP-peanel default-penel">
								<div class="defaultPic fl">
									<img src="${STATIC_SCH }/images/default-hdPic.jpg" width="50" height="50">
								</div>
								
								<div class="hdPic-tipsInfo fl">
									<h4>添加图像</h4>
									<p>塑造一个良好形象，在求职和交友互动交流过程中都是至关重要的。</p>
									<a href="javascript:void(0)" class="addBtn" title="立即添加" id="add-logo-btn">立即添加 <span class="icon"></span></a>
									<div style="display:none;" id="uploadLogoTriger"></div>
								</div>
								<div class="clear"></div>
							</div>
						</div>
						<!--添加头像 end-->
						
						<!--消息流start-->
						<div id="messages">
						
						</div>
						<!--消息流end-->
						
						
						<input type="hidden" value="1" id="feedPage"/>
						<input type="hidden" value="0" id="feedMoreIngVal"/>
						<div class="readMore noSel" title="初始化" id="feedMoreInit" style="display:block;">
							加载中...<img src="${STATIC_SCH }/images/loading.gif">
						</div>
						<div class="readMore noSel" title="点击查看更多" id="feedMoreIng">
							加载中...<img src="${STATIC_SCH }/images/loading.gif">
						</div>
						<div class="readMore noSel" title="点击查看更多" id="feedMore">更多</div>
					</div>
					
					<!--右边-->
					<div class="rightContent fr">
						<div class="slideShow">
							
							<!--推荐列表-->
							<div class="slideSameStyle recommend-lists" id="pushListDiv" style="display:none;">
								<div class="slideTitle">推荐</div>
								<div class="recDetails">
									<ul id="pushList" class="pushList">
										
									</ul>
								</div>
								
							</div>
							
							<!--新收到简历列表-->
							<div class="slideSameStyle resume-lists" style="display:none;">
								<div class="slideTitle">新收到的简历</div>
								<div class="resumeDetails">
									<ul>
										<li>
											<div class="col-left">
												<img src="${STATIC_SCH }/images/company.jpg" width="35" height="35">
											</div>
											<div class="col-right">
												<h2>
													方曼/北京大学
													<span class="timer">1小时前</span>
												</h2>
												<h3>人力资源业务伙伴</h3>
											</div>
										</li>
										<li>
											<div class="col-left">
												<img src="${STATIC_SCH }/images/company.jpg" width="35" height="35">
											</div>
											<div class="col-right">
												<h2>
													方曼/北京大学
													<span class="timer">1小时前</span>
												</h2>
												<h3>人力资源业务伙伴</h3>
											</div>
										</li>
										<li>
											<div class="col-left">
												<img src="${STATIC_SCH }/images/company.jpg" width="35" height="35">
											</div>
											<div class="col-right">
												<h2>
													方曼/北京大学
													<span class="timer">1小时前</span>
												</h2>
												<h3>人力资源业务伙伴</h3>
											</div>
										</li>
										<li>
											<div class="col-left">
												<img src="${STATIC_SCH }/images/company.jpg" width="35" height="35">
											</div>
											<div class="col-right">
												<h2>
													方曼/北京大学
													<span class="timer">1小时前</span>
												</h2>
												<h3>人力资源业务伙伴</h3>
											</div>
										</li>
									</ul>
								</div>
								<div class="totalResumes">
									<a href="javascript:void(0)" class="alls" title="查看更多在招职位">共收到简历233份<span class="icon"></span></a>
								</div>
							</div>
							
							<!--新加关注-->
							<div class="slideSameStyle newAttention" style="display:none;" id="fansListDiv">
								<div class="slideTitle">新加关注</div>
								<div class="attentionDetails">
									<ul id="fansList">
										
									</ul>
								</div>
							</div>
						
							<!--广告位1-->
							<div class="slideSameStyle advertise" style="display:none;">
								<div class="adDetails"></div>
							</div>
							
							<!--你的粉丝还看过-->
							<div class="slideSameStyle yourFans" style="display:none;">
								<div class="slideTitle">你的粉丝还看过</div>
								<div class="fansLists">
									<ul>
										<li></li>
										<li></li>
										<li></li>
										<li></li>
										<li></li>
										<li></li>
										<li></li>
										<li></li>
										<li></li>
									</ul>
								</div>
							</div>
						</div>
						
						<!-- ad-start -->
						<%@ include file="/WEB-INF/application/common/ad/ad.jsp"%>
						<!-- ad-end -->
						
						
					</div>
					<div class="clear"></div>
				</div>
			</div>
		</div>
		
	
		<!-- 发布宣讲会  和  发布以往的视频 开始 -->
		<%@ include file="/WEB-INF/application/basic/push_info.jsp"%>
		<!-- 发布宣讲会  和  发布以往的视频 结束 -->
		
		
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
			  var operIng = $("#feedMoreIngVal").val();
		    	if(operIng == '0'){
		    		console.log(">>>>>> 开始查询");
		    		$("#feedMore").trigger("click");
		    	}else{
		    		console.log(">>>>>> 查询中，请稍等");
		    	}
		  }
  	</script> 
		

		
	</body>
	
</html>

