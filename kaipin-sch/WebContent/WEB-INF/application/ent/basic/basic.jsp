<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- header-start -->
<%@ include file="/WEB-INF/application/main/header_ent.jsp"%>
<!-- header-end -->
	<link href="${STATIC_SCH }/css/basic.css?v.<%=System.currentTimeMillis()%>" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${STATIC_SCH }/js/home/home.js?v.<%=System.currentTimeMillis()%>"></script>
	<script type="text/javascript" src="${STATIC_SCH }/js/basic/basicV1.js?v.<%=System.currentTimeMillis()%>"></script>
	<script type="text/javascript" src="${STATIC_SCH }/js/basic/basic.js?v.<%=System.currentTimeMillis()%>"></script>
	<!-- 截图插件 -->
	<link href="${STATIC_SCH }/js/imgareaselect/css/imgareaselect-default.css?v.<%=System.currentTimeMillis()%>" rel="stylesheet" type="text/css" />
    <script src="${STATIC_SCH }/js/imgareaselect/jquery.imgareaselect.pack.js?v.<%=System.currentTimeMillis()%>" type="text/javascript"></script>
	<!-- uploadify -->
	<link rel="stylesheet" type="text/css" href="${STATIC_SCH }/js/uploadify/Huploadify.css?v.<%=System.currentTimeMillis()%>"/>
	<script type="text/javascript"  src="${STATIC_SCH }/js/uploadify/jquery.Huploadify.js?v.<%=System.currentTimeMillis()%>"></script>
	<!-- 图片裁剪 -->
	<link href="${STATIC_SCH }/js/image/css/cropper.css?v.<%=System.currentTimeMillis()%>" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${STATIC_SCH }/js/image/bootstrap.min.js?v.<%=System.currentTimeMillis()%>"></script>
	<script type="text/javascript" src="${STATIC_SCH }/js/image/cropper.js?v.<%=System.currentTimeMillis()%>"></script>
	<script type="text/javascript" src="${STATIC_SCH }/js/image/imgcut.js?v.<%=System.currentTimeMillis()%>"></script>
	<script type="text/javascript" src="${STATIC_SCH }/js/image/logo.js?v.<%=System.currentTimeMillis()%>"></script>
		
	<script type="text/javascript" >
	
		var bg;
		$(function(){
			
			bg = '${bg}';
			if(bg != '0'){
				$("#change-background-img").css({"background-image":"url("+bg+")","filter":"progid:DXImageTransform.Microsoft.AlphaImageLoader(src='"+bg+"',sizingMethod='scale'\9"});
				$("#bg_url").val(bg);
			}
			
			getEntUserSelfLogo();
			
			$("#edit-enterprise").show();
			$("#uploadLogoEnt").show();//修改头像按钮
			
			getFollowAndFans();//获取关注和粉丝的统计数
			getPushList("","init");//获取推荐关注
			getFollowList(1, 8, 'fans');//获取最近关注的粉丝
			getFollowListByClass(1, 8, 'follow', 10);
			getFollowListByClass(1, 8, 'follow', 11);
			getFollowListByClass(1, 8, 'follow', 12);
			getResumeList();//获取最新收到的简历
		});
	</script>
		
		
		<div class="container">
			<div class="mainBoxPeanel">
				
				<!--企业基本信息  企业认证 start-->
					<%@ include file="/WEB-INF/application/ent/basic/basic_logo.jsp"%>
				<!--企业基本信息  企业认证 end-->
				
				<input type="hidden" id="currentUserType" value="ent"/>
				
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
						
						<!--企业形象 start-->
						<div class="enterprise">
							<!--轮播图 -->
							<div class="enterBanner" id="enterBannerShow">
								<div style="display:none;">
									<ul>
										<li style="display: block;">
											<a href="javascript:void(0)">
												<img src="${STATIC_SCH }/images/attent.jpg" width="646" height="213">
											</a>
										</li>
										<li style="display: none;">
											<a href="javascript:void(0)">
												<img src="${STATIC_SCH }/images/img3.jpg" width="646" height="213">
											</a>
										</li>
										<li style="display: none;">
											<a href="javascript:void(0)">
												<img src="${STATIC_SCH }/images/bg.jpg" width="646" height="213">
											</a>
										</li>
									</ul>
									
									<div class="collecBtn" style="left: 297.5px;">
										<span class="active"></span>
										<span class=""></span>
										<span class=""></span>
									</div>
								</div>
                                
                            	<!--编辑图片弹框-->
                           		<div class="change-background-img" id="change-background-img" >
                           		
                           			<a href="javascript:void(0)" id="touch-change" style="width:100%;height:100%;display:block;"></a>
                           		
                            		<a href="javascript:void(0)" class="sy-edit-btns edit-bgImg">编辑</a>
                            		<div class="save-and-cancel" id="save-and-cancel">
										<a href="javascript:void(0)" class="sy-edit-btns press-btn save" id="bg-save-ent">保存</a>
										<a href="javascript:void(0)" class="sy-edit-btns press-btn cancle" id="bg-cancle">取消</a>
									</div>
                        
                        			<div class="set-bg-btns" style="display:;" id="choseImg">
										<a href="javascript:void(0)" class="selet-close close-bg-btns"></a>
										<a href="javascript:void(0)" class="select-bgImg" id="select-bgImg-file">选择图片</a>
											<span class="select-tips">图片大小限制1M内，推荐尺寸850×230像素</span>
									</div>
                       		 	</div>
                        		<!--编辑图片弹框 end-->
                        		
                        		<!-- 背景原来 url -->
								<input type="hidden" id="bg_url" value="/static/web/images/default-bgImg.jpg" style="width:800px;"/>
								<!-- 背景预览 url -->
								<input type="hidden" id="bg_preview" />
                                
							</div>
							
							<!--企业简介等信息 -->
							<div class="enter-introInfos">
							
							
							<!--编辑企业简介 开始-->
							
                            <div class="modify-company-info panel-win-defaulte" id="panel-win-defaulte">
								<span class="inverted-triangle"></span>
								<div class="modify-lists">
									<form class="" method="post" id="form3" name="form3">
										<input type="hidden" name="oper" value="info">
										<div class="modify-cons">
											<span class="md-tips">企业简介</span>
											<textarea class="txt" id="detail" name="detail" value="${infos.detail }"></textarea>
										</div>
										<div class="modify-cons">
											<span class="md-tips">公司网址</span>
												<input type="text" class="md-input mr-15" id="website" name="website" value="${website }">
										</div>
										
										<div class="modify-cons">
											<span class="md-tips">成立年份</span>
												<input type="text" class="md-input mr-15" id="regeditDate" name="regeditDate" value="${infos.regeditDate }">
										</div>
										
											
											<div class="modify-cons">
												<span class="md-tips">公司类型</span>
												<select class="mr-15" id="companyTypeCode" name="companyTypeCode" value="${companyType }">
													<option value="">请选择</option>
													<option value="-1">全部</option>
													<option value="14000002">国企</option>
													<option value="14000003">外商独资</option>
													<option value="14000004">代表处</option>
													<option value="14000005">合资</option>
													<option value="14000006">民营</option>
													<option value="14000007">股份制企业</option>
													<option value="14000008">上市公司</option>
													<option value="14000009">国家机关</option>
													<option value="14000010">事业单位</option>
													<option value="14000011">其它</option>
												</select>
										</div>
										
										<div class="modify-cons">
											<span class="md-tips">所在地区</span>
											<div class="">
												
												<select class="mr-15 mr-add" id="provinceSelect" onchange="">
													<option value="">请选择地区</option>
													<c:forEach var="m" items="${provinceList }">
														<option value="${m.location_code }">${m.location_name }</option>
													</c:forEach>
												</select>
												<select class="mr-15 mr-add" id="citySelect" onchange="">
													<option value="">请选择城市</option>
													<c:forEach var="m" items="${cityList }">
														<option value="${m.location_code }">${m.location_name }</option>
													</c:forEach>
												</select>
												<select class="mr-15 mr-add" id="countySelect" onchange="" name="">
													<option value="">请选择区县</option>
													<c:forEach var="m" items="${countyList }">
														<option value="${m.location_code }">${m.location_name }</option>
													</c:forEach>
												</select>
												
												<input type="hidden" name="officeArea" id="locationCodeSelect" value="${infos.officeArea }"/>
											</div>
										</div>
										<div class="modify-cons">
											
												<span class="md-tips">公司规模</span>
												<input type="text" class="md-input mr-15" id="peopleNumber" name="peopleNumber" maxlength="10" value="${infos.peopleNumber }" >
										
										</div>
										<div class="modify-cons">
											
												<span class="md-tips">公司总部</span>
												<input type="text" class="md-input mr-15" id="officeAddress" name="officeAddress" maxlength="10" >
										
										</div>
							
										<div class="sure-or-cancel">
											<a href="javascript:void(0)" id="save-two-ent" class="opear-btn suer-btn go-l">确定</a>
											<a href="javascript:void(0)" id="cancle-btn-two" class="opear-btn cancle-btn">取消</a>
										</div>
									</form>
								</div>
							</div>
							<!--编辑企业简介 结束-->
									
							
								<!--基本信息展示 开始-->
								<div class="eiiPanel">
									<a href="javascript:void(0)" class="sy-edit-btns edit-company-info" data-open="open" id="edit-company-info">编辑</a>
									<!--简介-->
									<div class="firm-introduce">
										<p>
											${detail }
										</p>
									</div>
									
									<!--简介基本信息-->
									<div class="fi-otherInfos">
										<div class="oi-field">
											<p class="field-attent">企业简介</p>
											<p class="field-info">
												<c:if test="${infos.detail != null || infos.detail != ''}">
													${infos.detail}
												</c:if>
												<c:if test="${infos.detail == null || infos.detail == ''}">
													如：公司成立于...
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
														如:www.XXX.com，请编辑
													</c:if>
												</dd>
											</dl>
											<dl>
												<dt>成立年份</dt>
												<dd>
													<c:if test="${infos.regeditDate != null || infos.regeditDate != '' }">
														${infos.regeditDate }
													</c:if>
													<c:if test="${infos.regeditDate == null || infos.regeditDate == '' }">
														如：1969年，请编辑
													</c:if>
												</dd>
											</dl>
											<dl>
												<dt>公司类型</dt>
												<dd>
													<c:if test="${companyType != null && companyType != '' }">
														${companyType }
													</c:if>
													<c:if test="${companyType == null || industryType == '' }">
														如：国企/私企，请编辑
													</c:if>
												</dd>
											</dl>
											<dl>
												<dt>公司规模</dt>
												<dd>
													<c:if test="${infos.peopleNumber != null || infos.peopleNumber != ''}">
														${infos.peopleNumber}人
													</c:if>
													<c:if test="${infos.peopleNumber == null || infos.peopleNumber == ''}">
														如：XX人，请编辑
													</c:if> 
												</dd>
											</dl>
											<dl>
												<dt>公司总部</dt>
												<dd>
													<c:if test="${infos.officeAddress != null || infos.officeAddress != '' }">
														${infos.officeAddress }
													</c:if>
													<c:if test="${infos.officeAddress == null || infos.officeAddress == '' }">
														如：四川成都武侯区118号，请编辑
													</c:if>
												</dd>
											</dl>
											<dl>
												<dt>所在地区</dt>
												<dd>
													<c:if test="${officeArea != null || officeArea != ''}">
														${officeArea}
													</c:if>
													<c:if test="${officeArea == null || officeArea == ''}">
														如：四川 成都 锦江区，请编辑
													</c:if> 
												</dd>
											</dl>
										</div>
									</div>
								</div>
								
								<!--基本信息展示 结束-->
								
								<!--收起展开 start-->
								<div class="spread-out" id="spread-out" style="display: none;">
									<a href="javascript:void(0)" id="spread-outs"  data-flag="close">+收起</a>
								</div>
								<!--收起展开 end-->
							</div>
						</div>
						<!--企业形象 end-->
						
						
						
						
						
						<!--我关注的内容 start-->
						
						<div class="perAttention-cons" id="follow-content">
							<div class="perA-peanel" >
								<h3 class="perC-tlt">我关注的内容</h3>
								
								<!--企业-->
								<div class="sameAttentlists" id="11FollowCountDiv">
									<h5 class="tlt">企业(<span id="11FollowCount" class="followCount">0</span>)</h5>
									<div class="lists-details" id="11FollowList">
										
									</div>
								</div>
								
								<!--用户-->
								<div class="sameAttentlists" id="10FollowCountDiv">
									<h5 class="tlt">用户(<span id="10FollowCount" class="followCount">0</span>)</h5>
									<div class="lists-details" id="10FollowList">
									
									
									</div>
								</div>
								
								<!--学校-->
								<div class="sameAttentlists" id="12FollowCountDiv">
									<h5 class="tlt">学校(<span id="12FollowCount" class="followCount">0</span>)</h5>
									<div class="lists-details" id="12FollowList">
									
									
									</div>
								</div>
							</div>
						</div>
						
						<!--我关注的内容 end-->
						
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
							<!--新收到简历列表-->
							<div class="slideSameStyle resume-lists" id="resumeList5Div">
								<div class="slideTitle">新收到的简历</div>
								<div class="resumeDetails">
									<ul id="resumeList5">
										
									</ul>
								</div>
								<div class="totalResumes">
									<a href="${BASE_PATH }/company/resume" class="alls" title="查看更多在招职位">共收到<span id="resumeCounts"></span>份简历 <span class="icon"></span></a>
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

		
	</body>
	
</html>

