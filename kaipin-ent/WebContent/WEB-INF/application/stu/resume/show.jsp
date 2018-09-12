<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!-- header-start -->
<%@ include file="/WEB-INF/application/main/header_ent.jsp"%>
<!-- header-end -->
	<script type="text/javascript" src="${STATIC_SCH }/js/home/home.js"></script>
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
	<script type="text/javascript">
		var userId;
		$(function(){
			userId = '${userId}';
			getFollowAndFans(userId);//获取关注和粉丝的统计数
			getPushList("","init");//获取推荐关注
			getFollowList(1, 8, 'fans', userId);//获取最近关注的粉丝
			var flag = isFollowFans(userId);
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
					insertFollowInResume(userId, "10");
				});
			}	
			function bindDelFollow(){
				$("#attentionBtn-cancle").on("click",function(){
					delFollowInResume(userId, "10");
				});
			}	
		});
	</script>
	
	
	<!--resume-details-->
		<section class="resume-details mgTop30">
			<div class="rd-container">
			
				<!--简历 start-->
				<div class="rd-cons-basicInfo fl" id="rd-cons-basicInfo">
					<div class="header-pic">
					
						<c:if test="${userHeadUrl == '0' }">
							<img src="${STATIC_SCH }/images/moren_guanzhu.jpg">
						</c:if>
						<c:if test="${userHeadUrl != '0' }">
							<img src="${userHeadUrl }">
						</c:if>
						<!--<a href="javascript:void(0)" id="headUrl" style="background-image:url('${STATIC_SCH }/images/moren_guanzhu.jpg')"></a>-->
<!-- 						<img src="http://kaipin.com:8083:82/12/2016/images/info/0/a2fdef36-db4e-4c25-aa89-b482b2217f06.jpg"> -->
					</div>
					<div class="per-infoLists">
					
								<h5>
									<span>${RESUME_INFO.surname }${RESUME_INFO.missSurname }</span>
									<a href="javascript:void(0)" class="certi no-certi" title="未认证">未认证</a>
									<a href="javascript:void(0)" class="certi ing-certi" title="审核中">审核中</a>
									<a href="javascript:void(0)"  style="display:none;" class="certi already-certi" title="已认证">已认证</a>
								</h5>
								<div class="info-per-lists">
									
									<c:if test="${keyWordsList != null && keyWordsList == ''}">
										<p class="signature">
											<span>个性签名：</span> 
											<c:forEach items="${keyWordsList }" var="m" varStatus="status">
													
												<c:if test="${fn:length(keyWordsList)-1 == status.index}">
													<c:out value="${m.keyword}" />  
												</c:if>
												<c:if test="${fn:length(keyWordsList)-1 != status.index}">
													<c:out value="${m.keyword}" /> 、 
												</c:if>
											</c:forEach>
										</p>
									</c:if>
									
									<p class="per-otherInfo per-adress">${area } 
										<c:if test="${likeWorkLingYu != null && likeWorkLingYu != '' }">
											| 兴趣领域：
											<c:forEach items="${likeWorkLingYu }" var="m"  varStatus="status">
												
												<c:if test="${fn:length(likeWorkLingYu)-1 == status.index}">
													<c:out value="${m.name}" />  
												</c:if>
												<c:if test="${fn:length(likeWorkLingYu)-1 != status.index}">
													<c:out value="${m.name}" /> 、 
												</c:if>
											</c:forEach>
										</c:if> 
									</p>
									<p class="per-otherInfo per-school">${schoolNames }-${majorName }
											<c:if test="${is_grad == 0}">（未毕业）</c:if>
											<c:if test="${is_grad == 1}">（已毕业）</c:if>
											<c:if test="${is_grad == 2}">（已结业）</c:if>
											<c:if test="${is_grad == 3}">（休学中）</c:if></p>
									<p class="per-otherInfo language">
										<c:forEach items="${languagelist }" var="languagelistVal" varStatus="status"> 
											<c:if test="${fn:length(languagelist)-1 == status.index}">
												<c:out value="${languagelistVal.name}" /> 
											</c:if>
											<c:if test="${fn:length(languagelist)-1 != status.index}">
												<c:out value="${languagelistVal.name}" />、 
											</c:if>
										</c:forEach>
									</p>
								</div>
								<div class="operating clear" style="display: none;">
									<div class="btn-add">
										<a href="javascript:void(0)" class="add-ability" title="加入人才库"><span>+</span>加入人才库</a>
										<a href="javascript:void(0)" class="see-log" title="查看求职日志">求职日志</a>
									</div>
									<div class="btn-interview">
										<a href="javascript:void(0)" class="written-test">邀请笔试</a>
										<a href="javascript:void(0)" class="refuse-written">拒绝笔试</a>
										<a href="javascript:void(0)" class="written-test">再邀笔试</a>
										<a href="javascript:void(0)" class="pass-written">通过笔试</a>
										<a href="javascript:void(0)" class="written-test">邀请一面</a>
										<a href="javascript:void(0)" class="pass-written">发offer</a>
									</div>
								</div>
							</div>
							<div class="attention-infos fr">
							<a class="right-sideline" href="${BASE_PATH }/followfans/init?oper=follow&org_id=${userId}">
								<span class="n" id="floowCount">0</span>
									<span>关注</span>
							</a>
							<a href="${BASE_PATH }/followfans/init?oper=fans&org_id=${userId}" class="bdRight">
								<span class="n" id="fansCount">0</span>
									<span>粉丝</span>
							</a>
							<a class="attention <!--alreadyattention-->" href="javascript:void(0);" id="attentionBtn" >+ 关注</a>
							<a class="attention <!--alreadyattention-->" href="javascript:void(0);" id="attentionBtn-cancle" >- 关注</a>
						<div class="nc-share">
										<a href="javascript:;" class="share shear" id="share" title="分享"><i class="sr-share"></i></a>
										<span style="display:none;" class="line"></span>
										<a href="javascript:;" style="display:none;" class="share collect" onclick="javascript:addFavorite2()" id="collect"><i class="cl-collect"></i>收藏</a>
										<div class="share-space" id="share-space" style="display: none;">
											
												<div class="bdsharebuttonbox bdshare-button-style0-16" data-tag="share_1" data-bd-bind="1471484457902">
													<input type="hidden" id="shareUrl" value="/resume/share/${userId }">
													<a class="bds_qzone" data-cmd="qzone" href="#" data-id="6356238572082961202" title="分享到朋友圈">朋友圈</a>
													<a class="bds_weixin" data-cmd="weixin" href="#" data-id="6356238572082961203" title="分享到微信">微信</a>
													<a class="bds_sqq" data-cmd="sqq" href="#" data-id="6356238572082961204" title="分享给QQ好友">QQ好友</a>
													<a class="bds_mail" data-cmd="mail" href="#" data-id="6356238572082961250" title="用电子邮件分享">电子邮件</a>
												</div>
												
												<script type="text/javascript">
												        //全局变量，动态的文章ID
												        var ShareId = "";
												        //绑定所有分享按钮所在A标签的鼠标移入事件，从而获取动态ID
												        $(function () {
												            $(".bdsharebuttonbox a").mouseover(function () {
												                ShareId = $(this).attr("data-id");
												            });
												        });
												
												        /* 
												        * 动态设置百度分享URL的函数,具体参数
												        * cmd为分享目标id,此id指的是插件中分析按钮的ID
												        *，我们自己的文章ID要通过全局变量获取
												        * config为当前设置，返回值为更新后的设置。
												        */
												        function SetShareUrl(cmd, config) {        
												        	var shareUrl = $("#shareUrl").val();
												            if (ShareId) {
												                config.bdUrl = r_path + shareUrl;   
												            }
												            return config;
												        }
												
												        //插件的配置部分，注意要记得设置onBeforeClick事件，主要用于获取动态的文章ID
												         window._bd_share_config = {
													            "common": {
													                onBeforeClick:SetShareUrl,
													                "bdSnsKey":{},
													                "bdText":"",
													                "bdMini":"2",
													                "bdMiniList":false,
													                "bdPic":"",
													                "bdStyle":"0",
													                "bdSize":"12"
													            }, "share": {}
													        };
												        //插件的JS加载部分
												        with (document) 0[(getElementsByTagName('head')[0] || body).appendChild(createElement('script')).src = 'http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='+ ~(-new Date() / 36e5)];
												      //切换分享框显示与隐藏
														$(function(){
															$("#share").on("mouseover",function(){
																$("#share-space").stop(true,true).slideToggle('fast');
																$("#share-space a").on("click",function(){
																	$("#share-space").hide();});
															    		$("body").click( function(){
																	$("#share-space").hide();});
															    		//鼠标已出框隐藏框
															    		//$("#share-space").onmouseout( function(){
																		//$("#share-space").hide();
																	    //});
															});
														});
										
														
												        </script>
												
										</div>
									</div>
						</div>
						</div>
				<!--rd-cons-left-basicInfo end-->		
							<!--个人简历详细信息 start-->
					<div class="person-other-infos">
					<div class="stuconleft">
						<div class="poi-box">
							<!--教育背景 start-->
							<div class="pb poi-education">
								<h5 class="tlt">教育背景</h5>
								<p>
										${time}  
										${schoolName}  
										${zhuanyeName}  
										${xueliName}  
										
											<c:if test="${is_grad == 0}">未毕业</c:if>
											<c:if test="${is_grad == 1}">已毕业</c:if>
											<c:if test="${is_grad == 2}">已结业</c:if>
											<c:if test="${is_grad == 3}">休学中</c:if>
										
								</p>
							</div>
							<!--教育背景 end-->
							
							<!--职业背景 start-->
							<c:if test="${proList != null && proList != '' }">
								<div class="pb poi-position">
									<h5 class="tlt">职业背景</h5>
									
									<c:forEach items="${proList }" var="proListVal">
										<p> 
											${proListVal.start_time}
												<c:if test="${proListVal.end_time != ''}">
													- ${proListVal.end_time}
												</c:if>
											 |
											${proListVal.position} |
											${proListVal.employer} 
										</p>
									</c:forEach>
								</div>
							</c:if>
							<!--职业背景 end-->
							
							<!--求职意向（全职） start-->
							<c:if test="${ positionList != null && positionList != '' && likeWorkAreaList != null && likeWorkAreaList != '' && likeWorkLingYu != null && likeWorkLingYu != ''}">
								<div class="pb poi-position">
									<h5 class="tlt">求职意向
										<c:if test="${hireType != null && hireType != ''}">
											(${hireType })
										</c:if>
									</h5>
									<c:if test="${positionList != null && positionList != '' }">
										<p>求职意向职位:   
											<c:forEach items="${positionList }" var="m" varStatus="status">
												<c:if test="${fn:length(positionList)-1 == status.index}">
													<c:out value="${m.position_tag}" />  
												</c:if>
												<c:if test="${fn:length(positionList)-1 != status.index}">
													<c:out value="${m.position_tag}" /> 、 
												</c:if>
											</c:forEach>
										</p>
									</c:if>
									
									<c:if test="${likeWorkAreaList != null && likeWorkAreaList != '' }">
										<p>意向工作地区:   
											<c:forEach items="${likeWorkAreaList }" var="m" varStatus="status">
												<c:if test="${fn:length(likeWorkAreaList)-1 == status.index}">
													<c:out value="${m.name}" />  
												</c:if>
												<c:if test="${fn:length(likeWorkAreaList)-1 != status.index}">
													<c:out value="${m.name}" /> 、 
												</c:if>
											</c:forEach>
										</p>
									</c:if>
									
									<c:if test="${likeWorkLingYu != null && likeWorkLingYu != '' }">
										<p>意向工作领域:   
											<c:forEach items="${likeWorkLingYu }" var="m"  varStatus="status">
													
													<c:if test="${fn:length(likeWorkLingYu)-1 == status.index}">
														<c:out value="${m.name}" />  
													</c:if>
													<c:if test="${fn:length(likeWorkLingYu)-1 != status.index}">
														<c:out value="${m.name}" /> 、 
													</c:if>
												</c:forEach>
										</p>
									</c:if>
									
								</div>
							</c:if>
							<!--求职意向（全职） end-->
							
							<!--个人关键词 start-->
							<c:if test="${keyWordsList != null && keyWordsList != '' }">
								<div class="pb poi-apply">
									<h5 class="tlt">个人关键词</h5>
									<p>
										<c:forEach items="${keyWordsList }" var="m" varStatus="status">
											<c:if test="${fn:length(keyWordsList)-1 == status.index}">
												<c:out value="${m.keyword}" />  
											</c:if>
											<c:if test="${fn:length(keyWordsList)-1 != status.index}">
												<c:out value="${m.keyword}" /> 、 
											</c:if>
										</c:forEach>
									</p>
								</div>
							</c:if>
							<!--个人关键词 end-->
							
							<!--语言能力 start-->
							<c:if test="${languagelist != null && languagelist != '' }">
								<div class="pb poi-language">
									<h5 class="tlt">语言能力</h5>
									<p>
										<c:forEach items="${languagelist }" var="languagelistVal" varStatus="status"> 
											<c:if test="${fn:length(languagelist)-1 == status.index}">
												<c:out value="${languagelistVal.name}" /> 
											</c:if>
											<c:if test="${fn:length(languagelist)-1 != status.index}">
												<c:out value="${languagelistVal.name}" />、 
											</c:if>
										</c:forEach>
									</p>
								</div>
							</c:if>
							<!--语言能力 end-->
							
							<!--求职信 start-->
							<c:if test="${RESUME_INFO.coverLetter != null && RESUME_INFO.coverLetter != ''}">
								<div class="pb poi-coverLetter">
									<h5 class="tlt">求职信</h5>
									<div class="the-leter">
										<p>${RESUME_INFO.coverLetter }</p>
									</div>
								</div>
							</c:if>
							<!--求职信 end-->
							
						</div>
						<!--简历 end-->
						<!--foot-ad start-->
						<div class="foot-ad">
							<div class="simp-box">
								<a href="http://www.ef.com.cn/" target="_blank">
									<img src="${STATIC_SCH }/images/img4.jpg" width="100%" height="100%"  />
								</a>
							</div>
						</div>
						<!--foot-ad start-->	
						</div>					
				<!--nc-left start-->
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
				<!--nc-left start-->
					</div>
					<!--个人简历详细信息 start-->
					

					<div class="invite-person" id="invite-person" style="display:none;">
						<!-- 左边的按钮 -->
						<p id="btns">
						</p>
						
					</div>
				
				<div class="clear"></div>
			</div>
		</section>
		
		
		<!--邀请面试  开始-->
		<form method="POST" action="<%=path%>/resumePassOrNoController/reqView.do" id="reqViewForm" name="reqViewForm">
			<div class="tzui-loading-overlay" id="interview-panel-overlay" style="display: none;"></div>
			<div class="interview-panel radius" id="interview-panel" style="display: none;">
				<div class="iv-title">
					<h3 id="reqName">邀请面试</h3>
					<a href="javascript:void(0)" onclick="closeDailog();"  class="close-itrView closeAllStyle"></a>
				</div>
				<!--影藏数据-->
				<input type="hidden" id="rowIndex"/>
				<input type="hidden" id="oper"/>
				<div class="iv-cons-lists">
					<div class="iv-select">
						<span class="name-tips fl">职位</span>
						<div class="iv-select-cons iv-select-posi fl" id="iv-position">
							${positionName }
							<input type="hidden" name="postion" id="postion"/>
						</div>
					</div>
					
					<div class="iv-select">
						<span class="name-tips fl">时间</span>
						<div class="iv-timer fl"><input type="text" id="iv-timer" name="faceTime" readonly="readonly"></div>
					</div>
					
<!-- 					<div class="iv-select"> -->
<!-- 						<span class="name-tips fl">方式</span> -->
<!-- 						<div class="iv-select-cons fl" id="iv-way"> -->
<!-- 							<input type="hidden" name="faceType" id="faceType" value="0" class="input-val"/> -->
<!-- 							<span class="titleActive">网络面试</span> -->
<!-- 							<span class="selet_sj sanjiao"></span> -->
<!-- 							<div class="select-details"> -->
<!-- 								<a href="javascript:void(0)" data-title="0">网络面试</a> -->
	<!-- 							<a href="javascript:void(0)">网络笔试</a> -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 					</div> -->
					
					<div class="iv-select iv-remark">
						<span class="name-tips fl">备注</span>
						<textarea class="remarks" id="remarksNum" name="memo" data-count="50" ></textarea>
						<span class="limit-number" id="limit-number"><span>0</span>/50</span>
					</div>
				</div>
				<div class="iv-sureBtn">
					<a href="javascript:void(0)" id="reqBtn" onclick="reqBtn();" class="allSureBtnstyle" >确定</a>
					<a href="javascript:void(0)" id="reqBtnSubmit" style="display:none;background:#ececec;color:#c4c4c4;" class="allSureBtnstyle" >提交中</a>
				</div>
			</div>
		</form>
		<!--邀请面试  结束-->
		
		<!--发送offer 开始-->
		<div class="tzui-loading-overlay" id="send-offer-dialog-overlay" style="display: none;"></div>
		
		<div class="send-offer-dialog" id="send-offer-dialog" style="display: none;">
			<form>
				<div class="offer-title">
					<h3>发放offer</h3>
					<a href="javascript:void(0)" class="close-sendOffer closeAllStyle"></a>
				</div>
				<div class="offer-cons">
					<div class="cons-title">
						<input type="text" id="offerTitle" value="2015校园招聘录用通知书"/>
					</div>
					<div class="offer-editPanel">
						<div class="cons-details" id="send-offerCons" style="font:12px/22px '微软雅黑','microsoft yahei';color:#565656;"></div>
					</div>			
				</div>
				<div class="offer-button">
						<a href="javascript:void(0)" class="allSureBtnstyle offer-suer" id="offer-suer">确定</a>
						<a href="javascript:void(0)" style="display:none;background:#ececec;color:#c4c4c4;" class="allSureBtnstyle offer-suer" id="offer-suer-submit">发送中</a>
						<a href="javascript:void(0)" class="allCloseBtnstyle" id="offer-canle">取消</a>
				</div>
			</form>
		</div>
		<!--发送offer 介绍-->
		
		
		<!-- header-start -->
		<%@ include file="/WEB-INF/application/main/footer.jsp"%>
		<!-- header-end -->
		
		<script type="text/javascript" src="${STATIC_SCH }/js/videoPlay.js"></script>
		<script type="text/javascript" src="${STATIC_SCH }/js/thumbUp.js"></script>
		<script type="text/javascript" src="${STATIC_SCH }/js/comments.js"></script>
		

		
	</body>
	
</html>

		
