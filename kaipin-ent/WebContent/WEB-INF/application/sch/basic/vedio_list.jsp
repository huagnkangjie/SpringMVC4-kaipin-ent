<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- header-start -->
<%@ include file="/WEB-INF/application/main/header_ent.jsp"%>
<!-- header-end -->
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
	<!-- 视频播放 -->
	<link rel="stylesheet" type="text/css" href="${STATIC_SCH }/js/vedio/video-js.css"/>
	<script type="text/javascript" src="${STATIC_SCH }/js/vedio/video.js" ></script>
	<!-- 系统 -->
	<script type="text/javascript" src="${STATIC_SCH }/js/home/home.js"></script>
	<script type="text/javascript" src="${STATIC_SCH }/js/home/histroyVedio.js"></script>
	
	<script type="text/javascript">
		$(function(){
			xjhList(1,1);
			
			/* 加载更多宣讲会 */
			$("#moreXjh").click(function(){
				var page = $("#meetingPage").val();
				xjhList(page,"more");
			});
			
			$("#send-preachBtn").show();//发布信息按钮
		});
	</script>
	
		<!-- 1正在上传0未上传 -->
		<input type="hidden" id="pushHistoryIng" value="0">
		
		<!--宣讲会和校招岗位 start-->
		<div class="tab-conference-panel">
			<!--tab-title start-->
			<input type="hidden" id="xjhCountValue"/>
			<div class="tab-title" id="tab-title-change">
				
					<h5 class="bd-none tab-active" id="xjhCount" id="xjhCount">视频 0 个</h5>
				
			</div>
			<!--tab-title end-->
			
			<!--会议详细详细 开始-->
			<div class="confer-full-info" id="confer-full-info">
				
				<!--宣讲会  开始-->
				<div class="confer-detalis-cons preach-cons">
					
				<!--空会议 开始-->
				<div class="confer-null-panel" id="noMeet">
					<div class="dancer-png-show"></div>
					<div class="single-tips">暂无信息</div>
					<a href="javascript:void(0)" class="right-off-upload" id="upload-confers">立即发布</a>
				</div>
				<!--空会议 结束-->
				
				<!--详细内容会议开始-->
				<div style="display: none;" id="meetList">
					<div id="meetCotent">
					
					</div>
					<div class="upload-addMore" style="display:none;" id="moreXjh" >
						<a href="javascript:void(0)" class="more">加载更多</a>
					</div>
				</div>
				<!--详细内容会议结束-->
				
				<input type="hidden" value="1" id="meetingPage"/>
				
				</div>
				<!--宣讲会  结束-->
				
			</div>
			<!--会议详细详细 结束-->
			
		</div>
		<!--宣讲会和校招岗位 end-->
		
		
		<!--编辑以往视频 开始-->
			<div id="historyXjhEdit" class="preacp-and-oldvideo" style="display: none;" >
				<a href="javascript:void(0)" class="close-xx" id="close-meetingss"></a>
				<div class="edit-tlt">编辑视频</div>
				<div class="meeting-detaiils" id="change-meeting-detaiils">
					<div class="send-preacp-meet meetings  send-old-videos" style="display: block;" >
						<form method="post" name="historyEidtForm" id="historyEidtForm" action="<%=path%>/entMeetingNoticeController/add.do">
							<div class="spm-cons-details">
								<div class="spm-img-info spm-videos-info">
									<div>
										<span class="spm-tips fl">上传视频 </span>
										<div class="help-lists fl">
											<a href="javascript:void(0)"  class="help-icon"></a>
											
											<div class="help-details">
												<span class="inverted-triangle"></span>
												<p>支持视频格式:mp4</p>
											</div>
										</div>
										<i class="small-tips">视频不能超过2G</i>
									</div>
									<div class="spm-videos">
										<a href="javascript:void(0)" id="upHistoryVedioTrrigerEdit">选择文件</a>
									</div>
									<input type="hidden" id="upHistoryVedioUrlEdit" name="xjhAnnexId"/>
								</div>
								
								<input type="text" style="display:none;" value="3" name="type" />
								<input type="text" style="display:none;" name="xjhId" id="historyXjhId"/>
								
								
									<div class="spm-cons-details">
								<div class="spm-img-info">
									<span class="spm-tips">设置封面图<i>图片大小限制1 MB以内</i></span>
									<div class="img-see" id="historyImgEidt">
										<a href="javascript:void(0)" class="updata-img-btn">
											<img src="${STATIC_SCH }/images/list-img.jpg" width="80" height="80"/>
										</a>
										<a href="javascript:void(0)" class="delete-imgCover"></a>
									</div>
									<div class="imgCover upCoverImg-btn fl" style="border:1px solid #E8E8E8;" id="upHistoryImgEditTrigger">
										<a href="javascript:void(0)" class="upCoverImgBtn"></a>
									</div>
									<div id="upHistoryImgEdit" style="display:none;"></div>
									<input type="hidden" name="coverImagePath" id="historyImgUrlEidt" />
									<div class="clear"></div>
								</div>
							
								<div class="clear"></div>
							</div>
							
								
								
								
								<div class="spm-style-set">
									<span class="spm-tips">视频名称</span>
									<input type="text" id="history-subject-edit" name="subject" class="spm-input  mgr-null">
								</div>
								<div class="clear"></div>
							</div>
							<div class="spm-style-set remark">
								<span class="spm-tips">视频介绍</span>
								<textarea id="history-memo-edit" name="memo"></textarea>
							</div>
							<div class="spm-cons-details">
								<div class="spm-style-set">
									<span class="spm-tips">拍摄时间</span>
									<input type="hidden" name="stratTimeStr" id="history-startTimeEdit"/>
									<ul id="start_time_history_edit" class="mod_select">
											<li>
												<div class="select_box">
													<span class="select_txt startYear">2015年</span><span class="selet_open"></span>
													<div class="option">
														<a href="javascript:void(0)">2015年</a>
														<a href="javascript:void(0)">2016年</a>
														<a href="javascript:void(0)">2017年</a>
														<a href="javascript:void(0)">2018年</a>
														<a href="javascript:void(0)">2019年</a>
														<a href="javascript:void(0)">2020年</a>
														<a href="javascript:void(0)">2021年</a>
														<a href="javascript:void(0)">2022年</a>
														<a href="javascript:void(0)">2023年</a>
														<a href="javascript:void(0)">2024年</a>
														<a href="javascript:void(0)">2025年</a>
														<a href="javascript:void(0)">2026年</a>
														<a href="javascript:void(0)">2027年</a>
														<a href="javascript:void(0)">2028年</a>
														<a href="javascript:void(0)">2029年</a>
														<a href="javascript:void(0)">2030年</a>
													</div>
												</div>
											</li>
											<li>
												<div class="select_box">
													<span class="select_txt select-other startMouth">11月</span><span class="selet_open"></span>
													<div class="option option-Wfixed" >
														<a href="javascript:void(0)">1月</a>
														<a href="javascript:void(0)">2月</a>
														<a href="javascript:void(0)">3月</a>
														<a href="javascript:void(0)">4月</a>
														<a href="javascript:void(0)">5月</a>
														<a href="javascript:void(0)">6月</a>
														<a href="javascript:void(0)">7月</a>
														<a href="javascript:void(0)">8月</a>
														<a href="javascript:void(0)">9月</a>
														<a href="javascript:void(0)">10月</a>
														<a href="javascript:void(0)">11月</a>
														<a href="javascript:void(0)">12月</a>
													</div>
												</div>
											</li>
											
											<li>
												<div class="select_box">
													<span class="select_txt select-other startData">30日</span><span class="selet_open"></span>
													<div class="option option-Wfixed">
														<a href="javascript:void(0)">1日</a>
														<a href="javascript:void(0)">2日</a>
														<a href="javascript:void(0)">3日</a>
														<a href="javascript:void(0)">4日</a>
														<a href="javascript:void(0)">5日</a>
														<a href="javascript:void(0)">6日</a>
														<a href="javascript:void(0)">7日</a>
														<a href="javascript:void(0)">8日</a>
														<a href="javascript:void(0)">9日</a>
														<a href="javascript:void(0)">10日</a>
														<a href="javascript:void(0)">11日</a>
														<a href="javascript:void(0)">12日</a>
														<a href="javascript:void(0)">13日</a>
														<a href="javascript:void(0)">14日</a>
														<a href="javascript:void(0)">15日</a>
														<a href="javascript:void(0)">16日</a>
														<a href="javascript:void(0)">17日</a>
														<a href="javascript:void(0)">18日</a>
														<a href="javascript:void(0)">19日</a>
														<a href="javascript:void(0)">20日</a>
														<a href="javascript:void(0)">21日</a>
														<a href="javascript:void(0)">22日</a>
														<a href="javascript:void(0)">23日</a>
														<a href="javascript:void(0)">24日</a>
														<a href="javascript:void(0)">25日</a>
														<a href="javascript:void(0)">26日</a>
														<a href="javascript:void(0)">27日</a>
														<a href="javascript:void(0)">28日</a>
														<a href="javascript:void(0)">29日</a>
														<a href="javascript:void(0)">30日</a>
														<a href="javascript:void(0)">31日</a>
													</div>
												</div>
											</li>
											<li>
												<div class="select_box">
													<span class="select_txt select-other startHour">12时</span><span class="selet_open"></span>
													<div class="option option-Wfixed">
														<a href="javascript:void(0)">1时</a>
														<a href="javascript:void(0)">2时</a>
														<a href="javascript:void(0)">3时</a>
														<a href="javascript:void(0)">4时</a>
														<a href="javascript:void(0)">5时</a>
														<a href="javascript:void(0)">6时</a>
														<a href="javascript:void(0)">7时</a>
														<a href="javascript:void(0)">8时</a>
														<a href="javascript:void(0)">9时</a>
														<a href="javascript:void(0)">10时</a>
														<a href="javascript:void(0)">11时</a>
														<a href="javascript:void(0)">12时</a>
														<a href="javascript:void(0)">13时</a>
														<a href="javascript:void(0)">14时</a>
														<a href="javascript:void(0)">15时</a>
														<a href="javascript:void(0)">16时</a>
														<a href="javascript:void(0)">17时</a>
														<a href="javascript:void(0)">18时</a>
														<a href="javascript:void(0)">19时</a>
														<a href="javascript:void(0)">20时</a>
														<a href="javascript:void(0)">21时</a>
														<a href="javascript:void(0)">22时</a>
														<a href="javascript:void(0)">23时</a>
														<a href="javascript:void(0)">24时</a>
													</div>
												</div>
											</li>
											<li>
												<div class="select_box">
													<span class="select_txt select-other startMinutes">11分</span><span class="selet_open"></span>
													<div class="option option-Wfixed">
														<a href="javascript:void(0)">0分</a>
														<a href="javascript:void(0)">1分</a>
														<a href="javascript:void(0)">2分</a>
														<a href="javascript:void(0)">3分</a>
														<a href="javascript:void(0)">4分</a>
														<a href="javascript:void(0)">5分</a>
														<a href="javascript:void(0)">6分</a>
														<a href="javascript:void(0)">7分</a>
														<a href="javascript:void(0)">8分</a>
														<a href="javascript:void(0)">9分</a>
														<a href="javascript:void(0)">10分</a>
														<a href="javascript:void(0)">11分</a>
														<a href="javascript:void(0)">12分</a>
														<a href="javascript:void(0)">13分</a>
														<a href="javascript:void(0)">14分</a>
														<a href="javascript:void(0)">15分</a>
														<a href="javascript:void(0)">16分</a>
														<a href="javascript:void(0)">17分</a>
														<a href="javascript:void(0)">18分</a>
														<a href="javascript:void(0)">19分</a>
														<a href="javascript:void(0)">20分</a>
														<a href="javascript:void(0)">21分</a>
														<a href="javascript:void(0)">22分</a>
														<a href="javascript:void(0)">23分</a>
														<a href="javascript:void(0)">24分</a>
														<a href="javascript:void(0)">25分</a>
														<a href="javascript:void(0)">26分</a>
														<a href="javascript:void(0)">27分</a>
														<a href="javascript:void(0)">28分</a>
														<a href="javascript:void(0)">29分</a>
														<a href="javascript:void(0)">30分</a>
														<a href="javascript:void(0)">31分</a>
														<a href="javascript:void(0)">32分</a>
														<a href="javascript:void(0)">33分</a>
														<a href="javascript:void(0)">34分</a>
														<a href="javascript:void(0)">35分</a>
														<a href="javascript:void(0)">36分</a>
														<a href="javascript:void(0)">37分</a>
														<a href="javascript:void(0)">38分</a>
														<a href="javascript:void(0)">39分</a>
														<a href="javascript:void(0)">40分</a>
														<a href="javascript:void(0)">41分</a>
														<a href="javascript:void(0)">42分</a>
														<a href="javascript:void(0)">43分</a>
														<a href="javascript:void(0)">44分</a>
														<a href="javascript:void(0)">45分</a>
														<a href="javascript:void(0)">46分</a>
														<a href="javascript:void(0)">47分</a>
														<a href="javascript:void(0)">48分</a>
														<a href="javascript:void(0)">49分</a>
														<a href="javascript:void(0)">50分</a>
														<a href="javascript:void(0)">51分</a>
														<a href="javascript:void(0)">52分</a>
														<a href="javascript:void(0)">53分</a>
														<a href="javascript:void(0)">54分</a>
														<a href="javascript:void(0)">55分</a>
														<a href="javascript:void(0)">56分</a>
														<a href="javascript:void(0)">57分</a>
														<a href="javascript:void(0)">58分</a>
														<a href="javascript:void(0)">59分</a>
													</div>
												</div>
											</li>
										</ul>
								</div>
								<div class="clear"></div>
							</div>
							
						
							
							
							<div class="submit-box">
								<a href="javascript:void(0)" onclick="historyVedioEditSave();" id="historyVedioEdit">发布 </a>
							</div>
						</form>	
	
						<!-- 上传ing -->
						<div id="upHistoryVedioEdit"></div>	
						
						
						<!-- 上传成功 开始 -->
						<div class="uploadVedioSuccess" id="uploadVedioSuccessEdit">
							<div class="uvs-tile fl">
<%-- 								<img src="<%=path%>/static/web/university/images/list-img.jpg" width="70" height="70" alt=""> --%>
								<a href="javascript:" class="uvs-bt">
									<span class="uvs-icon"></span>
								</a>
							</div>
							
							<div class="uvs-infos fl">
								<p class="info-title">
									<span class="name">文件名:</span>
									<span class="tlt" title="文件名" id="fileNameEidt"></span>
									<a href="javascript:" class="again" title="重新上传" id="upLoadAgainEdit">重新上传</a>
								</p>
								<p class="info-tips">文件已审核通过</p>
							</div>
						</div>
						<!-- 上传成功 结束 -->
						
						
						<!-- 上传失败 开始 -->
						<div class="uploadVedioSuccess uploadVediofail" id="uploadVediofailEdit">
							<div class="uvs-tile fl">
								<a href="javascript:" class="uvs-bt">
									<span class="uvs-icon"></span>
								</a>
							</div>
							
							<div class="uvs-infos fl">
								<p class="info-title">
									<span class="name">文件名:</span>
									<span class="tlt" title="xx222xxx.mp4">xx222xxx.mp4</span>
									<a href="javascript:" class="again" title="重新上传">重新上传</a>
								</p>
								<p class="info-tips">文件上传失败，请重新上传</p>
							</div>
						</div>
						<!-- 上传失败 结束-->				
					</div>
				</div>
			</div>
		<!--编辑以往视频 结束-->
		
		
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
		
		<!-- 发布宣讲会  和  发布以往的视频 开始 -->
		<%@ include file="/WEB-INF/application/basic/push_info.jsp"%>
		<!-- 发布宣讲会  和  发布以往的视频 结束 -->
		
		<!-- header-start -->
		<%@ include file="/WEB-INF/application/main/footer.jsp"%>
		<!-- header-end -->
		
		<script type="text/javascript" src="${STATIC_SCH }/js/videoPlay.js"></script>
		<script type="text/javascript" src="${STATIC_SCH }/js/thumbUp.js"></script>
		<script type="text/javascript" src="${STATIC_SCH }/js/comments.js"></script>
		

		
	</body>
	
</html>

