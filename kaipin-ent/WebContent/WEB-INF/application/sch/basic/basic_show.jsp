<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- header-start -->
<%@ include file="/WEB-INF/application/main/header_ent.jsp"%>
<!-- header-end -->
	<link href="${STATIC_SCH }/css/basic.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${STATIC_SCH }/js/home/home.js"></script>
	<script type="text/javascript" src="${STATIC_SCH }/js/basic/basicV1.js"></script>
	<script type="text/javascript" src="${STATIC_SCH }/js/basic/basic.js"></script>
	<!-- 截图插件 -->
	<link href="${STATIC_SCH }/js/imgareaselect/css/imgareaselect-default.css" rel="stylesheet" type="text/css" />
    <script src="${STATIC_SCH }/js/imgareaselect/jquery.imgareaselect.pack.js" type="text/javascript"></script>
	<!-- uploadify -->
	<link href="${STATIC_SCH }/css/newPosi.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css" href="${STATIC_SCH }/js/uploadify/Huploadify.css"/>
	<script type="text/javascript"  src="${STATIC_SCH }/js/uploadify/jquery.Huploadify.js"></script>
	<!-- 图片裁剪 -->
	<link href="${STATIC_SCH }/js/image/css/cropper.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${STATIC_SCH }/js/image/bootstrap.min.js?v.<%=System.currentTimeMillis()%>"></script>
	<script type="text/javascript" src="${STATIC_SCH }/js/image/cropper.js?v.<%=System.currentTimeMillis()%>"></script>
	<script type="text/javascript" src="${STATIC_SCH }/js/image/imgcut.js?v.<%=System.currentTimeMillis()%>"></script>
	<script type="text/javascript" src="${STATIC_SCH }/js/image/logo.js?v.<%=System.currentTimeMillis()%>"></script>
		
	<script type="text/javascript" >
	
		var bg;
		var companyId;
		
		$(function(){
			bg = '${bg}';
			companyId = '${companyId}';
			
			$("#basic_show_page_org_id").val(companyId);
			if(bg != '0'){
				$("#change-background-img").css({"background-image":"url("+bg+")","filter":"progid:DXImageTransform.Microsoft.AlphaImageLoader(src='"+bg+"',sizingMethod='scale'\9"});
				$("#bg_url").val(bg);
			}
			
			getFollowAndFans(companyId);//获取关注和粉丝的统计数
			getFeeds(1, "getFeedByOrgId", companyId);//获取消息流
			getPushList("","init");//获取推荐关注
			getFollowList(1, 8, 'fans', companyId);//获取最近关注的粉丝
			getFollowListByClass(1, 8, 'follow', 10);
			getFollowListByClass(1, 8, 'follow', 11);
			getFollowListByClass(1, 8, 'follow', 12);
			
			var cookie_own = '${cookie_own}';
			if(cookie_own != companyId){
				var flag = isFollowFans(companyId);
				if(flag){
					$("#attentionBtn").hide();
					$("#attentionBtn-cancle").show();
				}else{
					$("#attentionBtn").show();
					$("#attentionBtn-cancle").hide();
				}
			}
			
			bindDelFollow();
			bindAddFollow();
			function bindAddFollow(){
				$("#attentionBtn").on("click",function(){
					insertFollowInResume(companyId, "12");
				});
			}	
			function bindDelFollow(){
				$("#attentionBtn-cancle").on("click",function(){
					delFollowInResume(companyId, "12");
				});
			}
		});
	</script>
		
		<div class="container">
			<div class="mainBoxPeanel">
				<input type="hidden" id="basic_show_page_org_id"/>
				<!--企业基本信息 start-->
				<div class="basicInfos">
					<div class="bscPeanel">
						<div class="company-logo fl">
							<a href="${BASE_PATH }/feeds/targetOrg?orgId=${companyId}">
								<img id="company-logo" src="${STATIC_SCH }/images/fang_img.jpg" width="108" height="108" alt="logo">
							</a>
						</div>
						<div class="simple-infos fl">
							<h5 class="eu-title">
								<strong class="short-name">${linkInfo.schoolShortName }</strong>
								<span class="full-name">[${info.schoolName }]</span>
								<c:if test="${verify_status == 2 }">
									<a href="${BASE_PATH }/certificate/certificate-university-show?org_id=${ companyId}">
										<span class="rzIcon" >已认证</span>
									</a>
								</c:if>
								
							</h5>
							<p class="others" id="schoolFeatureName">院校类型</p>
							<p class="others" id="studentCountName">院校学生人数</p>
							<a href="${BASE_PATH }/basic/init" class="edit-means" id="editBasic" style="display:none;">编辑资料</a>
						</div>
						
						<div class="edit-window-panel fl">
						<div class="ewp-edit-company" style="display:none;">
							<span class="inverted-triangle"></span>
							
						</div>
					</div>
						<div class="attention-infos fr">
							<a href="${BASE_PATH }/followfans/init?oper=follow&org_id=${companyId}">
								<dl>
									<dt id="floowCount">0</dt>
									<dd>关注</dd>
								</dl>
							</a>
							<a href="${BASE_PATH }/followfans/init?oper=fans&org_id=${companyId}" class="bdRight">
								<dl>
									<dt id="fansCount">0</dt>
									<dd>粉丝</dd>
								</dl>
							</a>
							
							<a class="attention <!--alreadyattention-->" href="javascript:void(0);" id="attentionBtn" style="display:none;">+ 关注</a>
							<a class="attention <!--alreadyattention-->" href="javascript:void(0);" id="attentionBtn-cancle" style="display:none;">- 取消关注</a>
						</div>
					</div>
				</div>
				<!--企业基本信息 end-->
				
				<!--详细内容-->
				<div class="col-main">
					
					<!--左边-->
					<div class="leftContent fl">
						<!--企业形象 start-->
						<div class="enterprise">
							<c:if test="${linkInfo.schoolBg != null && linkInfo.schoolBg != ''}">
								<!--轮播图 -->
								<div class="enterBanner" id="enterBannerShow">
									<div id="change-background-img">
										
										<ul>
											<li style="display: block;">
												
												<a href="javascript:void(0)" id="touch-change">
													<%-- <c:if test="${linkInfo.schoolBg == null ||  linkInfo.schoolBg == ''}">
														<img src="${STATIC_SCH }/images/banner_01.jpg" width="646" height="213">
													</c:if> --%>
														<img src="${linkInfo.schoolBg }" width="646" height="213">
												</a>
											</li>
										</ul>
										
										<div class="collecBtn" style="left: 297.5px; display:none;" >
											<span class="active"></span>
											<span class=""></span>
											<span class=""></span>
										</div>
									</div>
									
	                        		<!-- 背景原来 url -->
									<input type="hidden" id="bg_url" value="/static/web/university/images/default-bgImg.jpg" style="width:800px;"/>
									<!-- 背景预览 url -->
									<input type="hidden" id="bg_preview" />
	                                
								</div>
							</c:if>
							<!--企业简介等信息 -->
							<div class="enter-introInfos">
								<div class="eiiPanel">
									
									<!--简介-->
									<div class="firm-introduce">
										<p>
											${detail }
										</p>
									</div>
									
									<!--简介基本信息-->
									<div class="fi-otherInfos">
										<div class="oi-field">
											<p class="field-attent">主要的教学专业、学科或学院</p>
											<p class="field-info">
												<c:if test="${linkInfo.direction != null || linkInfo.direction != ''}">
													${linkInfo.direction}
												</c:if>
												<c:if test="${linkInfo.direction == null || linkInfo.direction == ''}">
													如：哲学、化工、计算机等，请编辑
												</c:if>
											</p>
										</div>
										<div class="oi-lists">
											<dl>
												<dt>学校网站</dt>
												<dd>
													<c:if test="${website != null || website != ''}">
														<a target="_blank" href="http://${website}">${website}</a>
													</c:if>
													<c:if test="${website == null || website == ''}">
														如:四川大学网站地址，请编辑
													</c:if>
												</dd>
											</dl>
											<dl>
												<dt>学校类型</dt>
												<dd>
													<c:if test="${schoolFeature != '0' }">
														${schoolFeature }
													</c:if>
													<c:if test="${schoolFeature == '' || schoolFeature == '0' }">
														如：211院校，请编辑
													</c:if>
												</dd>
											</dl>
											<dl>
												<dt>在校学生人数</dt>
												<dd>
													<c:if test="${studentCount != null && studentCount != '' }">
														${studentCount }
													</c:if>
													<c:if test="${studentCount == null || studentCount == '' }">
														如：5万人，请编辑
													</c:if>
												</dd>
											</dl>
											<dl>
												<dt>学校校区地址</dt>
												<dd>
													<c:if test="${linkInfo.address != null || linkInfo.address != ''}">
														${linkInfo.address}
													</c:if>
													<c:if test="${linkInfo.address == null || linkInfo.address == ''}">
														如：四川成都武侯区118号，请编辑
													</c:if> 
												</dd>
											</dl>
											<dl>
												<dt>学校所属类别</dt>
												<dd>
													<c:if test="${schoolClass != '0' }">
														${schoolClass }
													</c:if>
													<c:if test="${schoolClass == '0' }">
														如：综合，请编辑
													</c:if>
												</dd>
											</dl>
											<dl>
												<dt>学校成立年份</dt>
												<dd>
													<c:if test="${linkInfo.birthYear != null || linkInfo.birthYear != ''}">
														${linkInfo.birthYear}
													</c:if>
													<c:if test="${linkInfo.birthYear == null || linkInfo.birthYear == ''}">
														如：1969年，请编辑
													</c:if> 
												</dd>
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
							
							<!--推荐列表-->
							<div class="slideSameStyle recommend-lists" id="pushListDiv" style="display:none;">
								<div class="slideTitle">推荐</div>
								<div class="recDetails">
									<ul id="pushList" class="pushList">
										
									</ul>
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
							
							
						</div>
						
						<!-- ad-start -->
						<%@ include file="/WEB-INF/application/common/ad/ad.jsp"%>
						<!-- ad-end -->

					</div>
					<div class="clear"></div>
				</div>
			</div>
		</div>
		
		<!--遮盖层-->
		<div class="tzui-loading-overlay"></div>
		
		<!--编辑企业封面图&宣传视频（最多可添加5张）   编辑封面1-->
		<div class="editCovers" id="editCovers" style="display:none;">
			<div class="coverTlt-tips">
				编辑企业封面图&宣传视频（最多可添加5张）
				<a href="javascript:void(0)" class="icon deleteIcon"></a>
			</div>
			<div class="cover-cons">
				<div class="uploadBtncoll">
					<span class="icon upIcon"></span>
					<a href="javascript:void(0)" class="addImgBtn">添加图片</a>
				</div>
			</div>
		</div>
		
		
		<div class="editCovers uploadFiles-lists" id="editCovers2" style="display:none;">
			<div class="coverTlt-tips">
				编辑企业封面图&宣传视频（最多可添加5张）
				<a href="javascript:void(0)" class="icon deleteIcon"></a>
			</div>
			<div class="cover-cons FilesListsCon">
				<div class="uploadBtncoll">
					<a href="javascript:void(0)" class="addImgBtn">添加图片</a>
				</div>
				
				<div class="listsImgInfos">
					<ul class="imgOp-showLists">
						<li>
							<div class="picImgBox">
								<img src="${STATIC_SCH }/images/banner_01.jpg" width="262" height="86">
								
								<div class="operateBtn">
									<span class="opcCover"></span>
									<div class="OBCons">
										<a href="javascript:void(0)" class="icon northIcon" title=""></a>
										<a href="javascript:void(0)" class="icon southIcon" title=""></a>
										<a href="javascript:void(0)" class="icon cancelIcon" title="删除"></a>
									</div>
								</div>
							</div>
							<div class="changeCurrentCons">
								<a href="javascript:void(0)" class="changeImgBtn">更换图片</a>
								<p class="changeStyle otherChanges">
									<a href="javascript:void(0)" title="跳转链接">+跳转链接</a>
									<a href="javascript:void(0)" title="链接视频">+链接视频</a>
								</p>
							</div>
						</li>
						
						<li>
							<div class="picImgBox">
								<img width="262" height="86">
								<div class="operateBtn">
									<span class="opcCover"></span>
									<div class="OBCons">
										<a href="javascript:void(0)" class="icon northIcon" title=""></a>
										<a href="javascript:void(0)" class="icon southIcon" title=""></a>
										<a href="javascript:void(0)" class="icon cancelIcon" title="删除"></a>
									</div>
								</div>
							</div>
							<div class="changeCurrentCons">
								<a href="javascript:void(0)" class="changeImgBtn">更换图片</a>
								<!--<p class="changeStyle otherChanges">
									<a href="javascript:void(0)" title="跳转链接">+跳转链接</a>
									<a href="javascript:void(0)" title="链接视频">+链接视频</a>
								</p>-->
								<p class="changeStyle editOrdel">
									<a href="javascript:void(0)" class="links" title="跳转链接">
										<span class="icon gtIcon"></span>
										跳转链接
									</a>
									<a href="javasciprt:void(0)" class="opBtnSty dels">删除</a>
									<a href="javasciprt:void(0)" class="opBtnSty edits">编辑</a>
								</p>
							</div>
						</li>
					</ul>
					<ul class="progressBarLists">
						<li>
							<span class="picTleName tmui-ellipsis">封面图3.jpg</span>
							<div class="progress-bar">
								<div class="pb-stateW"></div>
							</div>
							<span class="percentNum">50%</span>
							<span class="icon xxDelIcon" class="删除"></span>
						</li>
						<li>
							<span class="picTleName tmui-ellipsis">封面图3.jpg</span>
							<div class="progress-bar">
								<div class="pb-stateW"></div>
							</div>
							<span class="percentNum">50%</span>
							<span class="icon xxDelIcon" class="删除"></span>
						</li>
						
					</ul>
				</div>
				<div class="previewOrSave">
					<a href="javascript:void(0)" class="previewsBtn" class="预览">预览</a>
					<a href="javascript:void(0)" class="savesBtn" class="保存">保存</a>
				</div>
			</div>
		</div>
		
		<script type="text/javascript" src="${STATIC_SCH }/js/bannerShow.js"></script>
		<script type="text/javascript" src="${STATIC_SCH }/js/Drags.js"></script>
		
		<script type="text/javascript">
			$(function(){
				
				//初始化拖拽
				$("#editCovers").tmDrags({
					isDrag : false
				});
				
				$("#editCovers2").tmDrags({
					isDrag : true
				});
			});
		</script>
		
		
		<!-- header-start -->
		<%@ include file="/WEB-INF/application/main/footer.jsp"%>
		<!-- header-end -->
		
		<script type="text/javascript" src="${STATIC_SCH }/js/videoPlay.js"></script>
		<script type="text/javascript" src="${STATIC_SCH }/js/thumbUp.js"></script>
		<script type="text/javascript" src="${STATIC_SCH }/js/comments.js"></script>
		
		<script type="text/javascript">
		
		var schHeadUrl;
		var checkStatus;
		var schName;
		var schoolFeatureId;
		var schoolFeature;
		var studentCountName;
		
		$(function(){
			schHeadUrl = '${headUrl}';
			checkStatus = '${checkStatus}';
			schName = '${linkInfo.schoolShortName }';
			schoolFeatureId = '${linkInfo.schoolFeatureId }';
			schoolFeature = '${schoolFeature }';
			studentCountName = '${studentCountName }';
			
			if(schHeadUrl != '0'){
				$("#company-logo").attr("src",schHeadUrl);
				//$("#company-logo-head").css("background-image","url("+headUrl+")");
				//$("#hd-logo").attr("src",headUrl);
			}
		
			//院校特色名称
			if(schoolFeatureId == '0' || schoolFeatureId == ''){
				$("#schoolFeatureName").html("院校类型");
				$("#schoolFeatureName").show();
			}else{
				$("#schoolFeatureName").html(schoolFeature)
			}
			
			$("#schoolFeatureId").val(schoolFeatureId);
			
			if(studentCountName != '0'){//院校特色名称
				$("#studentCountName").html(studentCountName)
			}
			
			$("#zzrz" + checkStatus).show();
			
			
		});
		
	</script>
		
		<script type="text/javascript">
			$(function(){
				
				$("#cancle-btn-two").click(function(){
					$("#panel-win-defaulte").hide();
					$("#edit-company-info").data("open","open");
				});
				
				//company-title-details 公司简称
				var editEnterprise= $("#edit-enterprise");
				var ewpEditCompany = $(".ewp-edit-company");
				var $cancleBtn = $("#edit-enterprise").parents(".edit-window-panel").find(".cancle-btn");
				
				//profile-full-details 企业简介
				var editCompanyInfo = $("#edit-company-info");
				var panelWinDefaulte = $("#panel-win-defaulte");
				var $cancleCompanyInfo = $("#profile-full-details").find(".cancle-btn");
				
				//编辑企业
				editWinShow(editEnterprise,ewpEditCompany);
				editWinHide($cancleBtn,editEnterprise,ewpEditCompany);
				//企业简介
				editWinShow(editCompanyInfo,panelWinDefaulte);
				editWinHide($cancleCompanyInfo,editCompanyInfo,panelWinDefaulte);
				/*
				 *  方法名：editWinShow
				 * 	参    数：$obj----》编辑按钮
				 * 		 $panel---》打开的窗口
				 */
				function editWinShow($obj,$panel){
					$obj.click(function(){
						var open = $obj.data("open");
						if(open=="open"){
							$obj.data("open","close");
							$panel.show();	
						}else{
							$obj.data("open","open");
							$panel.hide();	
						}
					});
				}
				
				/*
				 *  方法名：editWinHide
				 * 	参    数：$obj----》取消按钮
				 * 		 $flag ---编辑按钮
				 * 		 $panel---》打开的窗口对象
				 */
				function editWinHide($obj,$flag,$panel){
					$obj.click(function(){
						$flag.data("open","open");
						$panel.hide();
					});
				}
				
				
 			});
		</script>
		
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
  		</script>

		
	</body>
	
</html>

