<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- header-start -->
<%@ include file="/WEB-INF/application/main/header.jsp"%>
<!-- header-end -->
	<script type="text/javascript" src="${STATIC_SCH }/js/home/home.js?v.<%=System.currentTimeMillis()%>"></script>
	<!-- 截图插件 -->
	<link href="${STATIC_SCH }/js/imgareaselect/css/imgareaselect-default.css?v.<%=System.currentTimeMillis()%>" rel="stylesheet" type="text/css" />
    <script src="${STATIC_SCH }/js/imgareaselect/jquery.imgareaselect.pack.js?v.<%=System.currentTimeMillis()%>" type="text/javascript"></script>
	<!-- uploadify -->
	<link href="${STATIC_SCH }/css/newPosi.css?v.<%=System.currentTimeMillis()%>" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css" href="${STATIC_SCH }/js/uploadify/Huploadify.css?v.<%=System.currentTimeMillis()%>"/>
	<script type="text/javascript"  src="${STATIC_SCH }/js/uploadify/jquery.Huploadify.js?v.<%=System.currentTimeMillis()%>"></script>
	<!-- 图片裁剪 -->
	<link href="${STATIC_SCH }/js/image/css/cropper.css?v.<%=System.currentTimeMillis()%>" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${STATIC_SCH }/js/image/bootstrap.min.js?v.<%=System.currentTimeMillis()%>"></script>
	<script type="text/javascript" src="${STATIC_SCH }/js/image/cropper.js?v.<%=System.currentTimeMillis()%>"></script>
		<script type="text/javascript">
			var headUrl;
			var orgName;
			var companyId;
			var showLogo;
			
			$(function(){
				orgName = '${orgName}';
				headUrl = '${headUrl}';
				getUserSelfLogo();//获取自己的头像
				companyId = '${companyId}';
				$("#basic_show_page_org_id").val(companyId);
				showLogo = '${logo}';
				if(headUrl != '0'){
					$("#company-logo").attr("src",headUrl);
					$("#company-logo-head").css("background-image","url("+headUrl+")");
					$("#hd-logo").attr("src",headUrl);
				}
				if(showLogo != '0'){
					$("#entShowLogo").attr("src",showLogo);
				}
				
// 				$("#editBasic").show();
				
				getFollowAndFans(companyId);//获取关注和粉丝的统计数
				getFeeds(1, "getFeedByOrgId", companyId);//获取消息流
				getPushList("","init");//获取推荐关注
// 				getFollowList(1, 8, 'fans');//获取最近关注的粉丝

				var flag = isFollowFans(companyId);
				if(flag){
					$("#attentionBtn").hide();
					$("#attentionBtn-cancle").show();
				}else{
					$("#attentionBtn").show();
					$("#attentionBtn-cancle").hide();
				}
				bindDelFollow();
				bindAddFollow();
				function bindAddFollow(){
					$("#attentionBtn").on("click",function(){
						insertFollowInResume(companyId, "11");
					});
				}	
				function bindDelFollow(){
					$("#attentionBtn-cancle").on("click",function(){
						delFollowInResume(companyId, "11");
					});
				}
				
			});
		</script>
		
		<div class="container">
			<div class="mainBoxPeanel">
				<input type="hidden" id="basic_show_page_org_id">
				
				<!--企业基本信息 start-->
				<div class="basicInfos">
					<div class="bscPeanel">
						<div class="company-logo fl">
							<a href="${BASE_PATH }/show?org_id=${companyId}">
								<img id="entShowLogo" src="${STATIC_SCH }/images/default-hdPic.jpg" width="108" height="108" alt="logo">
							</a>
						</div>
						<div class="simple-infos fl">
							<h5 class="eu-title">
								<strong class="short-name">${simpleEntName }</strong>
								<span class="full-name">[${infos.entName }]</span>
								<c:if test="${verify_status == 2 }">
									<a href="${BASE_PATH }/certificate/certificate-company-show?org_id=${ companyId}">
										<span class="rzIcon" >已认证</span>
									</a>
								</c:if>
							</h5>
							<p class="others">${industryType }</p>
							<p class="others">
								<c:if test="${peopleCount != '0' }">
									已有${peopleCount }员工
								</c:if>
								<c:if test="${peopleCount == '0' }">
									已有0员工
								</c:if>
							</p>
							<!--<a href="javascript:void(0)" class="edit-means">编辑资料</a>-->
							<a href="javascript:void(0)" class="send-letter" style="display:none;">私信沟通</a>
						</div>
						<div class="attention-infos fr">
							<a target="_blank" href="${BASE_PATH }/followfans/init?oper=follow&org_id=${companyId}">
								<dl>
									<dt id="floowCount">0</dt>
									<dd>关注</dd>
								</dl>
							</a>
							<a target="_blank" href="${BASE_PATH }/followfans/init?oper=fans&org_id=${companyId}" class="bdRight">
								<dl>
									<dt id="fansCount">0</dt>
									<dd>粉丝</dd>
								</dl>
							</a>
							<a class="attention <!--alreadyattention-->" href="javascript:void(0);" id="attentionBtn" style="display:none;">+ 关注</a>
							<a class="attention <!--alreadyattention-->" href="javascript:void(0);" id="attentionBtn-cancle" style="display:none;">- 关注</a>
						</div>
					</div>
				</div>
				<!--企业基本信息 end-->
				
				<!--邀请关注某某企业、学生、学校名称 start-->
				<div class="basicInfos inviteInfoList" style="display:none;">
					<h4 class="tlt">邀请关注某某企业、学生、学校名称 
						<span class="icon deleteIcon" title="删除"></span>
					</h4>
					<div class="invitePanel">
						<ul id="inviteLists-show">
							<li>
								<div class="headerPicAndUser">
									<img  src="${STATIC_SCH }/images/default-hdPic.jpg" width="138" height="138" alt="headerPic"/>
									<div class="userName">
										<span class="name">用户名</span>
									</div>
								</div>
								<div class="inviteBtn">
									<a href="javascript:void(0)">
										<span class="icon userIcon"></span>
										邀请
									</a>
								</div>
							</li>
						</ul>
					</div>
				</div>
				<!--邀请关注某某企业、学生、学校名称 end-->
				
				
				
				<!--详细内容-->
				<div class="col-main">
					
					<!--左边-->
					<div class="leftContent fl">
						
						
						
						<!--企业形象 start-->
						<div class="enterprise">
							
							<!--轮播图 -->
							<c:if test="${infos.bgUrl != null && infos.bgUrl != '' }">
								<div class="enterBanner" id="enterBannerShow">
									<ul id="change-background-img">
										<li style="display: block;">
											<a href="javascript:void(0)">
												<%-- <c:if test="${infos.bgUrl == null || infos.bgUrl == '' }">
													<img src="${STATIC_SCH }/images/banner_01.jpg" width="646" height="213"/>
												</c:if> --%>
												
													<img src="${infos.bgUrl }" width="646" height="213"/>
											</a>
										</li>
									</ul>
									
									<div class="collecBtn" style="display: none;">
										<span class="active"></span>
										<span></span>
										<span></span>
									</div>
								</div>
							</c:if>
							<!--企业简介等信息 -->
							<div class="enter-introInfos">
								
								<div class="eiiPanel">
									
									<!--简介-->
									<div class="firm-introduce">
										<p>
										${infos.detail }
										</p>
									</div>
									
									<!--简介基本信息-->
									<div class="fi-otherInfos">
										<div class="oi-field">
											<p class="field-attent">关注领域</p>
											<p class="field-info">${infos.businessDomain }</p>
										</div>
										<div class="oi-lists">
											<dl>
												<dt>公司网站</dt>
												<dd>
													<a target="_blank" href="http://${website }">${website }</a>
												</dd>
											</dl>
											<dl>
												<dt>成立年份</dt>
												<dd>${infos.regeditDate }</dd>
											</dl>
											<dl>
												<dt>公司类型</dt>
												<dd>${companyType }</dd>
											</dl>
											<dl>
												<dt>公司总部</dt>
												<dd>${infos.officeAddress }</dd>
											</dl>
											<dl>
												<dt>公司规模</dt>
												<dd>${infos.peopleNumber }</dd>
											</dl>
											<dl>
												<dt>所在地区</dt>
												<dd>${officeArea }</dd>
											</dl>
										</div>
									</div>
								</div>
								
								
								<!--收起展开 start-->
								<div class="spread-out" id="spread-out" style="display: none;">
									<a href="javascript:void(0)" id="spread-outs"  data-flag="close">+收起</a>
								</div>
								<!--收起展开 end-->
							</div>
						</div>
						<!--企业形象 end-->
						
						
						<!--消息流start-->
						<div id="messages">
						
						</div>
						<!--消息流end-->
						
						
						<input type="hidden" value="1" id="feedPage"/>
						<input type="hidden" value="0" id="feedMoreIngVal"/>
						<input type="hidden" value="${companyId }" id="companyIdBasic"/>
						<div class="readMore noSel" title="初始化" id="feedMoreInit" style="display:block;">
							加载中...<img src="${STATIC_SCH }/images/loading.gif">
						</div>
						<div class="readMore noSel" title="点击查看更多" id="feedMoreIng">
							加载中...<img src="${STATIC_SCH }/images/loading.gif">
						</div>
						<div class="readMore noSel" title="点击查看更多" id="feedMoreBasic">更多</div>
<!-- 						<div class="readMore noSel" title="点击查看更多" id="feedMore">更多</div> -->
						
						<!--广告-->
						<div class="foot-ad">
							<a href="http://www.ef.com.cn/" target="_blank">
								<img src="${STATIC_SCH }/images/img4.jpg" width="100%" height="100%">
							</a>
						</div>
					</div>
					
					<!--右边-->
					<div class="rightContent fr">
						<div class="slideShow">
							
							<!--工作机会-->
							<div class="slideSameStyle" style="display:none;">
								<div class="slideTitle">工作机会</div>
								<div class="slideSamePanel">
									<div class="left-conPic">
										<img src="${STATIC_SCH }/images/op.jpg" width="60" height="60" />
									</div>
									<div class="left-conDetail">
										<h3>对京版传媒感兴趣？</h3>
										<div class="tipsInfo">如果想加入我们，了解我们公司的方方面面，有助于你笔试面试和录取几率。</div>
										<div class="static-num">
											<p>本月已有182位校友邀请加入</p>
										</div>
										<a href="javascript:void(0)" class="konwDetails" title="详细了解">详细了解 <span class="icon"></span></a>
									</div>
								</div>
							</div>	
							
							<!--邀请校友-->
							<div class="slideSameStyle invite-join" style="display:none;">
								<div class="slideTitle">邀请加入</div>
								<div class="slideSamePanel">
									<div class="left-conPic">
										<img src="${STATIC_SCH }/images/op.jpg" width="60" height="60" />
									</div>
									<div class="left-conDetail">
										<h3>有校友邀请？</h3>
										<div class="tipsInfo">邀请你的校友加入开频校招，职场信息随时分享，随时随地与大家互动。</div>
										<div class="static-num">
											<p>本月已有182位校友邀请加入</p>
										</div>
										<a href="javascript:void(0)" class="schoolfellow" title="详细了解">邀请校友</a>
									</div>
								</div>
							</div>
							
							<!--推荐-->
							<div class="slideSameStyle recommend-lists" id="pushListDiv" style="display:none;">
								<div class="slideTitle recommendMoreBtn" >
									推荐
									<a href="javascript:void(0)" class="recommend-more" style="display:none;">
										更多
										<span class="icon"></span>
									</a>
								</div>
								<div class="recDetails">
									<ul id="pushList" class="pushList">
										
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
		
		<!-- footer-start -->
		<%@ include file="/WEB-INF/application/main/footer.jsp"%>
		<!-- footer-end -->
		
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
			    		$("#feedMoreBasic").trigger("click");
			    	}else{
			    		console.log(">>>>>> 查询中，请稍等");
			    	}
			  }
			  
			  $(function(){
				//点击图片加载网址
				//判断是否有网址--有网址直接跳转该网址
					$("#change-background-img").on("click",function(){
						//判断是否有网址
						var org_id = $("#basic_show_page_org_id").val();
						$.ajax({
							cache : false,
							async : false,
							type : 'POST',
							url : r_path + '/entbasic/getWebsite',
							data:{
								org_id : org_id
							},
							success : function (data){
								if(isNotEmpty(data)){
									var datas = eval('('+data+')');
									if(datas.success){
										var url = datas.obj;
										url = url.replace("https://","");
										url = url.replace("http://","");
										window.open ("http://"+url); 
									}
								}
								
							}
						});
					});
			  });
  		</script>
		
	
</html>

