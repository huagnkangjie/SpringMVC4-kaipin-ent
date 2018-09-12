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
		$(function(){
			
			bg = '${bg}';
			if(bg != '0'){
				$("#change-background-img").css({"background-image":"url("+bg+")","filter":"progid:DXImageTransform.Microsoft.AlphaImageLoader(src='"+bg+"',sizingMethod='scale'\9"});
				$("#bg_url").val(bg);
			}
			
			$("#edit-enterprise").show();
			$("#uploadLogo").show();//修改头像按钮
			
			getFollowAndFans();//获取关注和粉丝的统计数
			getPushList("","init");//获取推荐关注
			getFollowList(1, 8, 'fans');//获取最近关注的粉丝
			getFollowListByClass(1, 8, 'follow', 10);
			getFollowListByClass(1, 8, 'follow', 11);
			getFollowListByClass(1, 8, 'follow', 12);
		});
	</script>
		
		
		<div class="container">
			<div class="mainBoxPeanel">
				
				<!--企业基本信息  企业认证 start-->
					<%@ include file="/WEB-INF/application/sch/basic/basic_logo.jsp"%>
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
										<a href="javascript:void(0)" class="sy-edit-btns press-btn save" id="bg-save">保存</a>
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
								<input type="hidden" id="bg_url" value="/static/web/university/images/default-bgImg.jpg" style="width:800px;"/>
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
											<span class="md-tips">学校简介</span>
											<textarea class="txt" id="detail" name="detail"></textarea>
										</div>
										<div class="modify-cons">
											<span class="md-tips">主要的教学专业、学科或学院</span>
											<textarea class="txt" id="direction" name="direction" maxlength="100"></textarea>
										</div>
										
										<div class="modify-cons">
											<span class="md-tips">学校网站</span>
												<input type="text" class="md-input mr-15" id="website" name="website">
										</div>
										
											
											<div class="modify-cons">
												<span class="md-tips">在校生人数</span>
												<input type="text" class="md-input mr-15" id="studentCountEdit" name="studentCount" maxlength="8">
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
												
												<input type="hidden" name="locationCode" id="locationCodeSelect" value="${locationCode }"/>
											</div>
										</div>
										
										<div class="modify-cons">
												<span class="md-tips">学校地址</span>
												<input type="text" class="md-input mr-15" id="address" name="address" maxlength="100">
											</div>
											<div class="modify-cons">
											
												<span class="md-tips">成立年份</span>
												<input type="text" class="md-input mr-15" id="birthYear" name="birthYear" maxlength="10" >
										
										</div>
										<div class="modify-cons">	
												<span class="md-tips">学校类型</span>
												
 													<!--<input type="text" class="md-input" id="entType2" name="industryCode">-->
													<select id="schoolFeatureIdEdit" name="schoolFeatureId" class="mr-15">
															<option value="0">请选择</option>
															<option value="10000">211院校</option>
															<option value="10001">985院校</option>
															<option value="10002">211院校/985院校</option>
															<option value="10003">重本</option>
															<option value="10004">二本</option>
															<option value="10005">三本</option>
															<option value="10006">专科</option>
															<option value="10007">高职</option>
													</select>
												
												</div>
										<div class="modify-cons">	
												<span class="md-tips">学校所属类别</span>
												
													<select class="mr-15" id="schoolClassId" name="schoolClassId">
														<option value="0">请选择</option>
														<option value="10000">综合院校 </option>
														<option value="10001">工科院校</option>
														<option value="10002">农业院校</option>
														<option value="10003">林业院校</option>
														<option value="10004">医药院校</option>
														<option value="10005">师范院校</option>
														<option value="10006">语言院校</option>
														<option value="10007">财经院校</option>
														<option value="10008">政法院校</option>
														<option value="10009">体育院校</option>
														<option value="10010">艺术院校</option>
														<option value="10011">民族院校</option>
														<option value="10012">军事院校</option>
													</select>
											</div>
							
										<div class="sure-or-cancel">
											<a href="javascript:void(0)" id="save-two" class="opear-btn suer-btn go-l">确定</a>
											<a href="javascript:void(0)" id="cancle-btn-two" class="opear-btn cancle-btn">取消</a>
										</div>
									</form>
								</div>
							</div>
							<!--编辑企业简介 结束-->
									
							
								
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
												<dt>在校生人数</dt>
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
												<dt>学校地址</dt>
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
												<dt>所属类别</dt>
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
												<dt>成立年份</dt>
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

