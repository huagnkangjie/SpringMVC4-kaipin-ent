<!--  header-start -->
<%@ include file="/WEB-INF/pages/main/header.jsp"%>
<!--  header-end -->

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
	<link rel="stylesheet" href="<%=path%>/css/newPosi.css" />
	<script src="<%=path%>/js/jquery-easyui-1.2.6/jquery-1.7.2.min.js" charset="UTF-8" type="text/javascript"></script>
	<script src="<%=path%>/js/jquery.cookie.js" charset="UTF-8" type="text/javascript"></script>
	<link id="easyuiTheme" href="<%=path%>/js/jquery-easyui-1.2.6/themes/default/easyui.css" rel="stylesheet" type="text/css" media="screen">
	<link href="<%=path%>/js/jquery-easyui-1.2.6/themes/icon.css" rel="stylesheet" type="text/css" media="screen">
	<script src="<%=path%>/js/jquery-easyui-1.2.6/jquery.easyui.min.js" charset="UTF-8" type="text/javascript"></script>
	<script src="<%=path%>/js/jquery-easyui-1.2.6/locale/easyui-lang-zh_CN.js" charset="UTF-8" type="text/javascript"></script>
	<link href="<%=path%>/css/common.css" rel="stylesheet" type="text/css" media="screen">
	<link href="<%=path%>/css/dialog.css" rel="stylesheet" type="text/css">
	<!-- 系统 -->
	<script type="text/javascript" src="<%=path%>/js/dialog3.0.js" ></script>
	<script type="text/javascript" src="<%=path%>/js/formatJs.js" ></script>
	<script type="text/javascript" src="<%=path%>/js/formValidator.js" ></script>
	<script type="text/javascript" src="<%=path%>/js/formValidatorRegex.js" ></script>
	<script type="text/javascript" src="<%=path%>/js/position/positionList.js" ></script>
	
	<script type="text/javascript">
	
		var positionId;
		$(function (){
			changeMulu("2");
			positionId = '${positionId }';
		});
		
		var logo ;
		$(function (){
			logo = '${logo}';
			if(logo == undefined || logo == null || logo == 'null' || logo == ''){
				logo = r_path + "/images/fang_img.jpg";
			}
			$("#companyLogo").empty();
			$("#company-info-logo").empty();
			$("#companyLogo").append('<img  src="'+logo+'" width="100" height="100" alt="logo">');
			$("#company-info-logo").append('<img  src="'+logo+'" width="70" height="70" alt="logo">');
		});
		
		/* 发布 */
		function pulish(){
			if(positionJsonStr !=null && positionJsonStr.length > 0){
				$.ajax({
					cache : true,
					async : true,
					type : 'POST',
					url : '<%=path%>/position/pulishOnPreview.do',
					data : {
						postionStr : encodeURI(positionJsonStr)
					},
					success : function(data){
						location.href="<%=path%>/position/init.do";
					}
				});
			}
		}
		
		function edit(){
			 location.href="<%=path%>/position/editPage.do?postionId="+positionId;
		}
	</script>
	<style type="text/css">
		.edui-container .edui-toolbar{display: none;}
		.edui-container{box-shadow:none;}
	</style>
  		
		
		<!--position-container start-->
		<div class="position-container">
			<div class="pc-content">
				<!--sendPosiPrev start-->
				<div class="sendPosiPrev">
					<h5>${positionInfo.positionName }详情</h5>
					<div class="nd-more">
						<a href="javascript:;" class="Btnstyle sendBtn" onclick="edit();">编辑</a>
						<a href="javascript:window.history.back();" class="Btnstyle editBtn" >返回</a>
					</div>
				</div>
				<!--sendPosiPrev end-->
				
				<div class="sp-contents">
					<div class="nc-left fl">
						<div class="ncl-contents">
							<!--nc-left start-->
							<div class="ncl-title">
								<div class="ncl-box">
									<div class="logo" id="companyLogo">
										<img  src="<%=path%>/images/fang_img.jpg" width="100" height="100" alt="logo">
									</div>
									<div class="ncl-del">
										<h3><a href="javascript:;">${positionInfo.positionName }</a></h3>
										<span class="time">发布时间：${createTime }（有效期：${positionInfo.endTime }）</span><br/>
										<span class="from">工作地区：${positionInfo.locationCode }</span><br/>
										<span class="go"><a href="javascript:;" style="display:none;">前往公司网站投递</a></span><br/>
										<a href="javascript:;" class="deliver" style="display:none;">投递简历</a>
										<a href="javascript:;" class="deliver" style="display:none;">实习申请</a>
									</div>
									<div class="nc-share" >
										<a href="javascript:;" class="share shear" id="share"><i class="sr-share"></i>分享</a>
										<span class="line"></span>
										<a href="javascript:;" style="display:none;" class="share collect" onclick="javascript:addFavorite2()" id="collect"><i class="cl-collect"></i>收藏</a>
										<div class="share-space" id="share-space">
											
												<div class="bdsharebuttonbox" data-tag="share_1">
													<input type="hidden" id="shareUrl" value="${shareUrl}"/>
													<a class="bds_qzone" data-cmd="qzone" href="#" data-id=6356238572082961202 title="分享到朋友圈">朋友圈</a>
													<a class="bds_weixin" data-cmd="weixin" href="#" data-id='6356238572082961203' title="分享到微信">微信</a>
													<a class="bds_sqq" data-cmd="sqq" href="#" data-id='6356238572082961204' title="分享给QQ好友">QQ好友</a>
													<a class="bds_mail" data-cmd="mail" href="#" data-id='6356238572082961250'  title="用电子邮件分享">电子邮件</a>
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
												    </script>
												
										</div>
									</div>
									<div class="clear"></div>
								</div>
							</div>
							<!--ncl-title end-->
							
							<!--company-position-details start-->
							<div class="company-position-details">
								<div class="pd-left fl">
									<div class="nt-introduce">
										<div class="nt-infisrt">
											<h4>岗位职责</h4>
											${positionInfo.positionResponsibility }
										</div>
										<div class="nt-infisrt">
											<h4>任职要求</h4>
											${positionInfo.positionRequirements }
										</div>
										<c:if test="${positionInfo.positionDetail != ''}">
											<div class="nt-infisrt">
												<h4>职位摘要</h4>
												${positionInfo.positionDetail }
											</div>
										</c:if>
										<c:if test="${positionInfo.otherInfo != ''}">
											<div class="nt-infisrt">
												<h4>其它要求</h4>
												${positionInfo.otherInfo }
											</div>
										</c:if>
									</div>
								</div>
								
								<div class="pd-right fr">
									<div class="iead">
										<h5>行业</h5>
										<span>${positionInfo.industryCode }</span>
									</div>
									<div class="iead">
										<h5>学历</h5>
										<span>${positionInfo.educationCode }</span>
									</div>
									<div class="iead">
										<h5>职能</h5>
										<span>${positionInfo.jobTypeCode }</span>
									</div>
									<c:if test="${positionInfo.superior != ''}">
										<div class="iead">
											<h5>汇报对象</h5>
											<span>${positionInfo.superior }</span>
										</div>
									</c:if>
									<div class="iead">
										<h5>年龄</h5>
										<span>${positionInfo.ageStart } - ${positionInfo.ageEnd }岁</span>
									</div>
									<div class="iead">
										<h5>性别</h5>
										<span>${positionInfo.sexCode }</span>
									</div>
									<div class="iead">
										<h5>薪水</h5>
										<span class="money">${salaryTypeVal }</span>
									</div>
									<c:if test="${positionInfo.workExperienceCode != ''}">
										<div class="iead">
											<h5>经验</h5>
											<span>${positionInfo.workExperienceCode }</span>
										</div>
									</c:if>
									<c:if test="${positionInfo.numbers != ''}">
										<div class="iead">
											<h5>招聘人数</h5>
											<span>${positionInfo.numbers}人</span>
										</div>
									</c:if>
									<c:if test="${positionInfo.department != ''}">
										<div class="iead">
											<h5>所属部门</h5>
											<span>${positionInfo.department }</span>
										</div>
									</c:if>
									<c:if test="${positionInfo.majorRequest != ''}">
										<div class="iead">
											<h5>专业要求</h5>
											<span>${positionInfo.majorRequest }</span>
										</div>
									</c:if>
									<c:if test="${departmentNumbers != ''}">
										<div class="iead">
											<h5>下属人数</h5>
											<span>${departmentNumbers }</span>
										</div>
									</c:if>
								</div>
								
								<div class="clear"></div>
								
							</div>
							<!--company-position-details end-->
						
						</div>
						<!--ncl-title start-->
						
						<!--firm-introduce start-->
						<div class="firm-introduce clear">
							<!--fi-dettails start-->
							<div class="fi-dettails">
								<div class="fid-box bd-bottom" id="company-info-div">
									<div class="fid-title">
										<p class="ncy-img fl" id="company-info-logo">
											<img  src="<%=path%>/images/fang_img.jpg" width="70" height="70" alt="logo">
										</p>
										<p class="ncy-cyname fl">
											<a href="javascript:;" class="tt">${basic.entName }</a>
											<a href="javascript:;" class="add Btnstyle" style="display:none;">添加关注</a>
										</p>
									</div>
									
									<div class="abstract">
										${basic.detail }
									</div>
									
									<div class="fi-otherInfos">
										<div class="oi-field">
											<p class="field-attent">关注领域</p>
											<p class="field-info">${basic.businessDomain }</p>
										</div>
										<div class="oi-lists">
											<dl>
												<dt>公司网站</dt>
												<dd>${basic.website }</dd>
											</dl>
											<dl>
												<dt>所属行业</dt>
												<dd>${Industry }</dd>
											</dl>
											<dl>
												<dt>公司类型</dt>
												<dd>${companyType }</dd>
											</dl>
											<dl>
												<dt>公司总部</dt>
												<dd>${basic.officeAddress }</dd>
											</dl>
											<dl>
												<dt>公司规模</dt>
												<dd>${basic.peopleNumber }人</dd>
											</dl>
											<dl>
												<dt>公司区域</dt>
												<dd>${companyArea }</dd>
											</dl>
										</div>
									</div>
								</div>
							</div>
							<!--fi-dettails end-->
							
							<div class="other-position-lists" id="company-postion-list" style="display:none;">
								<div class="fid-box">
									<div class="opl-tt">
										<p class="fl">${basic.entSimpleName }的其他职位</p>
										<a href="<%=path %>/position/init" class="fr">${basic.entName }的全部职位</a>
									</div>
									<div class="pol-infos">
										<ul id="company-postion-list-ul">
											<li>
												<div class="pi-logo"><a href="javascript:;"></a></div>
												<div class="pi-txt"><a href="javascript:;" class="">快车事业部高级数据都</a></div>
												<p class="short-name">公司简称	</p>
												<p class="adress">上海</p>
												<span class="send-time">27天前发布</span>
											</li>
										</ul>
									</div>
								</div>
							</div>
						</div>
						<!--firm-introduce end-->
						
						
						<!--想死职位 start-->
						<div class="similar-position" style="display:none;">
							<div class="simp-box">
								<div class="sp-tlt">
									<p class="fl">相似职位</p>
									<a href="javascript:void(0)" class="fr">查看全部相似职位</a>
								</div>
								
								<div class="sp-listsInfo">
									<ul>
										<li>
											<div class="pi-logo"><a href="javascript:;"></a></div>
											<div class="pi-txt"><a href="javascript:;" class="">快车事业部高级数据都</a></div>
											<p class="short-name">公司简称	</p>
											<p class="adress">上海</p>
											<span class="send-time">27天前发布</span>
										</li>
										<li>
											<div class="pi-logo"><a href="javascript:;"></a></div>
											<div class="pi-txt"><a href="javascript:;" class="">快车事业部高级数据都</a></div>
											<p class="short-name">公司简称	</p>
											<p class="adress">上海</p>
											<span class="send-time">27天前发布</span>
										</li>
										<li>
											<div class="pi-logo"><a href="javascript:;"></a></div>
											<div class="pi-txt"><a href="javascript:;" class="">快车事业部高级数据都</a></div>
											<p class="short-name">公司简称	</p>
											<p class="adress">上海</p>
											<span class="send-time">27天前发布</span>
										</li>
										<li>
											<div class="pi-logo"><a href="javascript:;"></a></div>
											<div class="pi-txt"><a href="javascript:;" class="">快车事业部高级数据都</a></div>
											<p class="short-name">公司简称	</p>
											<p class="adress">上海</p>
											<span class="send-time">27天前发布</span>
										</li>
										<li>
											<div class="pi-logo"><a href="javascript:;"></a></div>
											<div class="pi-txt"><a href="javascript:;" class="">快车事业部高级数据都</a></div>
											<p class="short-name">公司简称	</p>
											<p class="adress">上海</p>
											<span class="send-time">27天前发布</span>
										</li>
										<li>
											<div class="pi-logo"><a href="javascript:;"></a></div>
											<div class="pi-txt"><a href="javascript:;" class="">快车事业部高级数据都</a></div>
											<p class="short-name">公司简称	</p>
											<p class="adress">上海</p>
											<span class="send-time">27天前发布</span>
										</li>
									</ul>
								</div>
							</div>
						</div>
						<!--想死职位 end-->
						
						<!--感兴趣的职位 start-->
						<div class="similar-position intersting-position" style="display:none;">
							<div class="simp-box">
								<div class="sp-tlt">
									<p class="fl">你可能感兴趣的职位</p>
									<a href="javascript:void(0)" class="fr">查看更多感兴趣职位</a>
								</div>
								
								<div class="sp-listsInfo">
									<ul>
										<li>
											<div class="pi-logo"><a href="javascript:;"></a></div>
											<div class="pi-txt"><a href="javascript:;" class="">快车事业部高级数据都</a></div>
											<p class="short-name">公司简称	</p>
											<p class="adress">上海</p>
											<span class="send-time">27天前发布</span>
										</li>
										<li>
											<div class="pi-logo"><a href="javascript:;"></a></div>
											<div class="pi-txt"><a href="javascript:;" class="">快车事业部高级数据都</a></div>
											<p class="short-name">公司简称	</p>
											<p class="adress">上海</p>
											<span class="send-time">27天前发布</span>
										</li>
										<li>
											<div class="pi-logo"><a href="javascript:;"></a></div>
											<div class="pi-txt"><a href="javascript:;" class="">快车事业部高级数据都</a></div>
											<p class="short-name">公司简称	</p>
											<p class="adress">上海</p>
											<span class="send-time">27天前发布</span>
										</li>
										
									</ul>
								</div>
							</div>
						</div>
						<!--感兴趣的职位 end-->
						
						<!--foot-ad start-->
						<div class="foot-ad" style="display:none;">
							<div class="simp-box">
								<a href="javascript:void(0)">
									<img src="<%=path%>/images/img4.jpg" width="100%" height="100%"  />
								</a>
							</div>
						</div>
						<!--foot-ad start-->
						
					</div>
					<!--nc-left start-->
					
					<!--nc-left start-->
					<div class="nc-right addvert-board fr">
						<input type="hidden" id="each-positionId" value="${positionId }"/>
						<img src="<%=path%>/images/img22.jpg" width="314" height="394">
						<a href="javascript:;" style="display:none;"><img src="<%=path%>/images/img3.jpg" width="314" height="226"></a>
					</div>
					<!--nc-left start-->
				</div>
			</div>
		</div>
		<!--position-container end-->
		
		<script type="text/javascript" src="<%=path%>/js/position/positionDetails.js?v.<%=System.currentTimeMillis()%>"></script>
		<!-- 		 footer-start -->
		<%@ include file="/WEB-INF/pages/main/footer.jsp"%>
		<!-- 		 footer-end -->
		
		<script type="text/javascript">
		
			$(function(){
				var logo = '${logo}';
				if(logo == undefined && logo != 'null' && logo != ''){
					logo = r_path + "/images/fang_img.jpg";
				}
				
				var workArea = '${workArea}';
				var simpelEntName = '${basic.entSimpleName}';
				var positionId = '${positionId}';
				
				//获取该企业的职位数据  json格式
				var data = getPositionListDataStr(4);
				
				//遍历该json 数据渲染页面
				eachPositionList(data,"xiangqing",workArea,simpelEntName);
				
				$("#share").on("click",function(){
					$("#share-space").stop(true,true).slideToggle('fast');
					$("#share-space a").on("click",function(){
						$("#share-space").hide();
					});
				});
			});
		</script>
	</body>
</html>
