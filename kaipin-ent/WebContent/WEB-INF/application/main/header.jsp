<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<%
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "No-cache");
	response.setDateHeader("Expires", -1);
	response.setHeader("Cache-Control", "No-store");
%>
<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">
<title>${sys_title }</title>
<meta content="${sys_keywords }" name="Keywords" />
<meta content="${sys_description }" name="Description" />
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="-1">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<link rel="shortcut icon" href="${BASE_PATH }/favicon.ico"
	type="image/x-icon" />
<meta name="renderer" content="webkit">
<script type="text/javascript">
	var r_path='<%=basePath%>';
	var r_org_id = "";
	var r_uid = "";
</script>
<link rel="stylesheet" href="${STATIC_SCH }/css/main.css" />
<!-- 弹窗 -->
	<link rel="stylesheet" href="${STATIC_SCH }/css/dialog.css" />
<link rel="stylesheet"
	href="${STATIC_SCH }/css/basic.css" />
<script type="text/javascript"
	src="${STATIC_COM }/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript"
	src="${STATIC_SCH }/js/constants.js"></script>
<script type="text/javascript"
	src="${STATIC_SCH }/js/common.js"></script>
<script type="text/javascript"
	src="${STATIC_SCH }/js/header/header.js"></script>
<script type="text/javascript"
	src="${STATIC_SCH }/js/search/search.js"></script>
<script type="text/javascript"
	src="${STATIC_ENT }/js/home/home.js"></script>

</head>
<body>
	<div class="header">
		<!--head start-->
		<div class="head">
			<div class="hdMainPeal">
				<!--seo-->
				<h1 class="seo-title">开频校招</h1>
				<a href="${BASE_PATH }/home" class="lm-logo fl" title="开频"><spna class="logo-kp">开频</spna></a>

				<div class="searchForm fl">
					<div class="search-text fl">
						<input type="text" class="txt-input" id="search-text"
							maxlength="15" placeholder="搜索职位、会员、企业、高校等更多内容">
					</div>
					<a href="javascript:void(0)" class="searchBtn fl" id="searchBtn" title="搜索"></a>

					<!--全文搜索-->
					<%@ include file="/WEB-INF/application/main/search.jsp"%>
					<!--全文搜索-->
				</div>



				<div class="company-info fr">

					<!--消息中心 start-->
					<div class="message-center fl" id="message-details" style="display:none;">
						<a href="javascript:void(0)" class="timely-information"> <span
							class="info-number" id="count" style="display: none;">1</span>
						</a>
						<div class="message-details">
							<h4 class="info-notice">消息通知</h4>
							<div class="info-lists">
								<dl>
									<dt>
										<a href="javascript:void(0)" class="mg-notice-tlt"> <span
											class="tlt-info">宣讲会通知</span> <span class="info-timer"
											id="meetCount">0</span>
										</a>
									</dt>
								</dl>
								<dl>
									<dt>
										<a href="javascript:void(0)" id="entViewList"
											class="mg-notice-tlt"> <span class="tlt-info">面试通知</span>
											<span class="info-timer" id="viewCount">0</span>
										</a>
									</dt>
								</dl>
								<dl>
									<dt>
										<a href="javascript:void(0)" id="entViewList"
											class="mg-notice-tlt"> <span class="tlt-info">邀请通知</span>
											<span class="info-timer" id="viewCount">0</span>
										</a>
									</dt>
								</dl>

								<!-- 								<div class="read-more"> -->
								<!-- 									<a href="javascript:void(0)" class="">查看更多</a> -->
								<!-- 								</div> -->
							</div>
						</div>
					</div>
					<!--消息中心 end-->

					<!--设置中心 start-->
					<div class="setCenter fl">
						<a href="${BASE_PATH }/home">
							<span class="personLogo" id="company-logo-head"
							style="background-image:url(${STATIC_SCH }/images/headLogo.png)"></span>
						</a>
						
						<div class="message-details">
							<h4 class="info-notice">管理</h4>
							<div class="info-lists">
								<ul>
									<li><span class="setting-icon set"></span> <a
										href="javascript:void(0)" id="account-set-btn">设置</a></li>
									<li><span class="setting-icon layout"></span> <a
										href="javascript:void(0)" onclick="layout();"
										class="xt-layout">退出</a></li>
								</ul>
							</div>
						</div>
					</div>
					<!--设置中心 end-->
				</div>

				<div class="clear"></div>
			</div>
		</div>
		<!--head end-->
		<!--鼠标向下滚动改变导航条样式-->
		
<script type="text/javascript">

			var UP = 'up',
				DOWN = 'down',
				direction = null,
				lastY = lastY || document.body.scrollTop;
			lastY = lastY || Math.max(document.documentElement.scrollTop, lastY);
	
				$(window).scroll(function(){ setTimeout( function() { 
					var currentY = window.pageYOffset;

					currentY = currentY || document.body.scrollTop;
					currentY = currentY || Math.max(document.documentElement.scrollTop, currentY);

					if(0 > lastY) {
						lastY = 0;}
					if(currentY > lastY && direction !== DOWN) {					
						direction = DOWN;						
						//隐藏导航
						
                  
						document.getElementById("hNav").className ="headNav2";
						//document.getElementById('hNav').style.top="11px";
						//document.getElementById('hNav').style.position="fixed";
						//document.getElementById('hNav').style.zIndex="1";
						//document.getElementById('hNav').style.width="100%";
						//document.getElementById('hNav').style.-webkit-animation-delay="50ms";
						//document.getElementById('hNav').style.-moz-animation-delay="50ms";
						//document.getElementById('hNav').style.-o-animation-delay="50ms";
						//document.getElementById('hNav').style.animation-delay="50ms";
						//document.getElementById('hNav').style.-moz-animation-delay="50ms";
			
					
					
						
						
						//to do
                       		 					 
					} else if(lastY > currentY && direction !== UP) {					
							direction = UP;						
							//显示导航
					
							
				          
							document.getElementById('hNav').className = "headNav"; 
							//document.getElementById('hNav').style.top="40px";
						    
							//to do	 
					}  

					lastY = currentY;										
				}, 300); }
			);	
			$(".header").mouseenter(function(){ setTimeout( function(){
				document.getElementById('hNav').className = "headNav";
				//document.getElementById('hNav').style.top="11px".slideDown(fast);
				//document.getElementById('hNav').style.position="fixed";
				
			}, 150); });
			
			
		
			
			//$(".header").mouseleave(function(){ setTimeout( function()				
			//{
			//	document.getElementById("hNav").className ="headNav2";
				//document.getElementById('hNav').style.top="40px";
			//}, 300); });
		</script>

		<!--headNav start-->
		<%@ include file="/WEB-INF/application/main/menu.jsp"%>
		<!--headNav end-->
	</div>


	<!--遮盖层-->
	<div id="tzui-loading-overlay" class="tzui-loading-overlay"></div>
	<!--遮盖层 退出-->
	<div id="tzui-loading-overlay—layout" class="tzui-loading-overlay"
		style="text-align: center;">
		<img src="${STATIC_SCH }/images/5-121204193R0-50.gif"
			style="margin-top: 280px;">
	</div>
	<a href="#" id="targetOrgTriggerA"
		style="display: none;"><span id="targetOrgTrigger">组织机构跳转</span></a>


	<!--个人设置  开始-->
	<div class="personal-set" id="personal-set-panel">
		<a href="javascript:void(0)" class="close-xx" id="close-accountSet"></a>
		<div class="ps-info-cos">
			<div class="header-info">

				<div class="header-name">
					<p>
						<a href="javascript:void(0)" title="" class="hd-logo" id="person-hd-logo">
						<img id="hd-logo" src="${STATIC_SCH }/images/moren.jpg"></a>
					</p>
					<p class="tlt">
						<spam id="mod-entName-tip">${info.schoolName }</spam>
						<a href="javascript:void(0)" class="certi no-certi"
							style="display: none;" id="no-ertificate1"
							onclick="certificateTipPage(99);" title="未认证">未认证</a> <a
							href="javascript:void(0)" class="certi ing-certi"
							style="display: none;" id="ing-ertificate1"
							onclick="certificateTipPage(0);" title="审核中">审核中</a> <a
							href="javascript:void(0)" class="certi already-certi"
							style="display: none;" id="yes-ertificate1"
							onclick="certificateTipPage(2);" title="已认证">已认证</a> <a
							href="javascript:void(0)" class="certi already-certi"
							style="display: none;" id="nopass-ertificate1"
							onclick="certificateTipPage(1);" title="审核不通过">审核未通过</a>
					</p>
					<p>
						<span class="eamil" id="mod-email-tip">${baseUserInfo.email }</span>
					</p>
					<p>
						<a href="javascript:void(0)" class="update-pswd"
							id="update-passwordBtn">修改密码</a>
					</p>
				</div>
			</div>

			<div class="personal-forms">
				<form method="post">
					<div class="pf-totalStyle">
						<span class="pf-tips">手机</span> <input type="text"
							class="pf-input" id="user-phone" name="phone" disabled="disabled" />
						<input type="button" class="change-account" value="修改账号"
							style="display: none;" /> <a href="javascript:void(0)"
							class="update-pswd" id="bind-phone">更改</a>
					</div>


					<div class="pf-totalStyle">
						<span class="pf-tips">姓</span> <input type="text"
							id="user-surname" name="surname" class="pf-input" />
					</div>

					<div class="pf-totalStyle">
						<span class="pf-tips">名</span> <input type="text"
							id="user-missSurname" name="missSurname" class="pf-input" />
					</div>
					<div class="clear"></div>

					<div class="pf-otherStyle">
						<div class="pf-totalStyle">
							<span class="pf-tips">性别</span> <select name="sex" id="user-sex"
								class="down-list">
								<option value="1">女</option>
								<option value="2">男</option>
							</select>
						</div>
						<div class="clear"></div>
					</div>

					<div class="pf-otherStyle" style="display: none;">
						<div class="pf-totalStyle">
							<span class="pf-tips">学校名称</span> <input type="text"
								class="pf-input firm" id="mod-entName" name="mod-entName"
								value="${mod_entName }" />
						</div>
						<div class="clear"></div>
					</div>


					<div class="pf-otherStyle">
						<div class="pf-totalStyle">
							<span class="pf-tips">职务</span> <input type="text"
								class="pf-input" id="user-position" name="position" />
						</div>
						<!-- <div class="pf-totalStyle">
								<span class="pf-tips">手机号</span>
								<input type="text" id="user-position" name="position" class="pf-input mgr-null"/>
							</div> -->
						<div class="clear"></div>
					</div>


					<!-- 						<div class="pf-otherStyle"> -->
					<!-- 							<div class="pf-totalStyle"> -->
					<!-- 								<span class="pf-tips">手机号</span> -->
					<!-- 								<input type="text" id="user-phone" name="phone" disabled="disabled" class="pf-input"/> -->
					<!-- 							</div> -->
					<!-- 							<div class="clear"></div> -->
					<!-- 						</div> -->
					<div class="pf-otherStyle" style="display: none;">
						<p class="bind-eamils">
							<a href="javascript:void(0)" id="bing-eamils-info">绑定企业邮箱</a>（用于发录取邮件）
						</p>
					</div>
					<div class="submit-box">
						<a href="javascript:void(0)" id="save-user-info">完成</a>
					</div>
				</form>
			</div>
		</div>

	</div>
	<!--个人设置  结束-->

	<!--修改密码 开始-->
	<div class="updata-password" id="updata-password-panel">
		<a href="javascript:void(0)" class="close-xx"
			id="close-updata-password"></a>
		<div class="psw-title">修改密码</div>
		<form method="post" id="modifyPwForm" name="modifyPwForm">
			<div class="error-info" id="tip-error" style="display: block;">密码错误</div>
			<div class="psw-toatl-pattern">
				<span class="psw-tips">当前密码</span> <input type="password"
					class="up-input" id="pw-now" name="pwNow" />
			</div>
			<div class="psw-toatl-pattern">
				<span class="psw-tips">修改密码</span> <input type="password"
					class="up-input" id="pw" name="pw" onpaste="return false" />
			</div>
			<div class="psw-toatl-pattern">
				<span class="psw-tips">确认密码</span> <input type="password"
					class="up-input" id="pw-sure" name="pwSure" onpaste="return false" />
			</div>
			<div class="psw-toatl-pattern">
				<span class="psw-tips"></span> <input type="button"
					class="sure-submit" id="sure-submit" value="确定" /> <input
					type="button" class="cancel-submit" id="cancel-submit" value="取消" />
			</div>
		</form>
	</div>

	<!--修改密码  结束-->

	<!--修改密码成功提示 开始-->
	<div class="updata-password-success" id="updata-password-success">
		<a href="javascript:void(0)" class="close-xx" id="close-succ-tips"></a>
		<div class="upd-ps-true">
			<span class="true-icon"></span> <span class="true-tips">密码修改成功</span>
			<a href="javascript:void(0)" class="sure-btn" id="sure-modifyPw-btn">确定</a>
		</div>
	</div>
	<!--修改密码成功提示 结束-->


	<!--绑定企业邮箱 start-->
	<div class="bind-firm-eamil noSel" id="bind-firm-eamil">
		<a href="javascript:void(0)" class="close-xx" id="close-bindEamil"></a>
		<input type=hidden id="config-configId" name="configId" />
		<div class="ps-tilte">绑定企业邮箱</div>
		<div class="bd-infos">
			<form method="post" id="configForm" id="configForm">
				<div class="bd-input">
					<span class="tips fl">企业对公邮箱</span>
					<div class="input-cons fl">
						<input type="text" id="config-from" name="mailUsername" class="em">
						<p class="small-tips">绑定该邮箱用于向求职者发送录取邮件，密码会加密，开频校招看不到您的邮箱密码，请放心填写。</p>
					</div>
				</div>

				<div class="bd-input">
					<span class="tips fl">邮箱密码</span>
					<div class="input-cons fl">
						<input type="password" id="config-pw" name="mailPassword"
							class="em">
					</div>
				</div>


				<div class="bd-input">
					<span class="tips fl">SMTP服务器</span>
					<div class="input-cons fl smtp-web">
						<input type="text" id="config-host" name="mailHost" class="em">
					</div>
					<div class="smtp-num fr">
						<span>端口</span> <input type="text" id="config-port"
							name="mailPort" class="input-smtp" value="465">
					</div>
					<div class="other-tips">
						<p class="exam">
							例：不会填写邮箱服务器？点击获得<a href="<%=path%>/loginController/help.do"
								class="help">帮助</a>
						</p>
						<p>腾讯企业邮箱smtp.exmail.qq.com</p>
						<p><p>腾讯邮箱smtp.qq.com</p>
							<p>新浪邮箱smtp.sina.com</p>
							<p>126邮箱smtp.126.com</p>
							<p>163邮箱smtp.163.com</p>
							<p>如果是您自己公司搭建的邮件服务器</p>
							<p>一般是smtp.XXXX.XXX如果设置失败</p>
							<p>就请联系您所在公司的管理员</p>
						</div>
					</div>
					
					<div class="bd-input">
						<span class="tips fl"></span>
						<div class="input-cons fz fr">
							<a href="javascript:void(0)" class="btn sure-btn"
							id="config-mail-btn">确定</a>
							<a href="javascript:void(0)" class="btn sure-btn"
							style="display:none;" id="config-mail-btn-ing">
								提交中...<img
							src="${STATIC_SCH }/images/loading.gif" width="16"
							height="16" style="margin-left:5px" />
							</a>
							<a href="javascript:void(0)" class="btn off-btn" id="cancle-mail">取消</a>
						</div>
					</div>
				</form>
			</div>
			
			<div class="bind-succsee" style="display: none;"
			id="bind-success-tip">
				<span class="right"></span>
				<p class="tips">绑定成功</p>
			</div>
			
			<div class="bind-succsee bind-fails" id="bind-fail-tip"
			style="display: none;">
				<span class="right"></span>
				<p class="tips">绑定失败</p>
			</div>
			
		</div>
		<!--绑定企业邮箱 end-->
		
		
		<!--重新绑定手机号 开始-->
		<div class="change-ph-num" id="change-ph-num">
			<a href="javascript:void(0)" class="close-xx" id="close-changehpNum"></a>
			<div class="cpn-cons noSelect">
				<form method="post">
					<div class="ph-input">
						<p class="send-input mgb fl">
						<input type="text" id="mod-phone" class="txt" maxlength="11"
							placeholder="请输入新手机号作为账号">
					</p>
						<p class="send-angin mgb fr">
							<a href="javascript:void(0)" class="send" id="getPhoneCode">获取验证码</a>  
							<a href="javascript:void(0)" class="send" id="getPhoneCodeIng"
							style="display:none;">50S后重发</a>
					</p>
						<p class="tips" style="display:none;" id="mod-phone-tip">请输入有效的手机号码</p>
					</div>
					<div class="ph-input">
						<p class="send-input mgb fl">
						<input type="text" id="mod-phone-code" class="txt"
							placeholder="请输入验证码">
					</p>
						<p class="send-angin mgb fr">
						<a href="javascript:void(0)" class="sure-ph" id="mod-phone-btn">确定</a>
					</p>
						<p class="tips" style="display:none;" id="mod-phone-code-tip">验证码错误</p>
					</div>
				</form>
			</div>
		</div>
		
		<!--重新绑定手机号 结束-->
		
		
		
		<!--图片裁剪-->
		<div class="tailoring-dialog" style="display:none;"
		id="tailoring-dialog">
			<div class="dialog-title">
				<h3 class="title">编辑图片</h3>
				<a href="javascript:;" class="close-dt" id="close-img-cut">X</a>
			</div>
			<div class="bea-tip">
				<span>展现出你的最佳情况</span>
			</div>
			<div class="dialog-content">
				<!--图片展示区域 start-->
				<div class="tailoring-area">
					
					<!--图片选择-->
					<div class="help-infos" id="selectCropperBox">
						<h5>选择照片</h5>
						<p>支持 JPG或 PNG 文件 (不超过 1 MB)。</p>
<!-- 						<input type="file" accept="image/jpeg, image/jpg, image/png, image/gif" name="file"> -->
<!-- 						<div class="spm-videos"> -->
<!-- 							<a href="javascript:void(0)" id="">选择文件</a> -->
<!-- 						</div> -->
						<button id="uploadLogoTrigger">选择文件</button>
					</div>
					
					
					<!--剪裁区域   1 : 1-->
					<div class="cropperBox" id="cropperBox" style="display: none;">
						<div class="help-infos">
							<h5>调整图像</h5>
							<p>拖拽蓝色方框，更改头像位置和尺寸 <a href="javascript:void(0);"
								id="selectAgin">重新选择</a>
						</p>
						</div>
						
						<div class="photo-edit-container" id="photo-edit-container">
							<div class="pe-imgContainer" id="pe-imgContainer">
								<img src="${STATIC_SCH }/images/100.jpg"
								id="image" />
							</div>
							
						</div>
						
					</div>
					<!--剪裁区域	16 : 9-->
					<div class="cropperBox" id="cropperBox169" style="display: none;">
						<div class="help-infos">
							<h5>调整图像</h5>
							<p>拖拽蓝色方框，更改头像位置和尺寸 <a href="javascript:void(0);"
								id="selectAginBg">重新选择</a>
						</p>
						</div>
						
						<div class="photo-edit-container">
							<div class="pe-imgContainer">
								<img src="${STATIC_SCH }/images/fang_img.jpg"
								id="image169" />
								<!--<div class="edit-corp-box" id="small-slider"></div>-->
							</div>
							
						</div>
						
					</div>
				</div>
				<!--图片展示区域 end-->
				
				<!--图片预览区域 start-->
				<div class="preview-area">
					<div class="help-infos">
						<h5>预览</h5>
						<p>网站图像显示</p>
					</div>

					<!--默认预览区（未加载图片显示）-->
					<div class="preview-show defaultshow-bg" id="defaultshow-bg"
					style="display: none;">
						<!--正方形 默认预览区（未加载图片显示）-->
						<div class=" preview-item" id="showZFX">
<!-- 							<div id="logo-preview-pic" style="dispaly:none;"> -->
<%-- 								<img src="${STATIC_SCH }/images/100.jpg" width="130" height="130"/> --%>
<!-- 							</div> -->
							<div id="others-preview-pic" style="dispaly:none;">
								<img src="${STATIC_SCH }/images/fang_img.jpg"
								width="130" height="130" />
							</div>
							<%-- <div class="size100 defaultBgCl">
								<img src="${STATIC_SCH }/images/100.jpg" width="100" height="100"/>
							</div>
							
							<div class="other-size">
								<div class="size50 defaultBgCl">
									<img src="${STATIC_SCH }/images/50.jpg" width="50" height="50"/>
								</div>
								
								<div class="size30 defaultBgCl">
									<img src="${STATIC_SCH }/images/30.jpg" width="30" height="30"/>									
								</div>
								
								<div class="size50Roand defaultBgCl">
									<img src="${STATIC_SCH }/images/50.png" width="50" height="50"/>
								</div>
							</div> --%>
						</div>
						
						<!--长方形 默认预览区（未加载图片显示）-->
						<div id="showCFX"
						style="display:none;width:160px;height:40px;background:url(${STATIC_SCH }/images/bg_img.jpg);background-size:100% 100%;"></div>
					
					</div>
					
					<!--预览区（已经载图片完毕后显示（真正预览图片区））-->
					<div class="preview-show" id="preview-show-preview">
						<div class=" preview-item">
							<div class="size100 defaultBgCl" id="defaultBgCl"
							style="width:100px;height:100px;display:none;">
								<div class="preview"
								style="overflow: hidden;width:100px;height:100px;"></div>
								<!--<img src="images/picture.jpg" id="ferret"/>-->
							</div>
							
							<div class="size169 defaultBgCl" id="defaultBgCl169"
							style="display:none; width:160px;height:40px">
								<div class="preview1"
								style="overflow: hidden;width:160px;height:40px;"></div>
							</div>
							
							
							<div class="other-size" id="others-show" style="display:none;">
								<div class="size50 defaultBgCl">
									<div class="preview"
									style="overflow: hidden;width:50px;height:50px"></div>
									<!--<img src="images/picture.jpg" id="ferret"/>-->
								</div>
								
								<div class="size30 defaultBgCl">
									<div class="preview"
									style="overflow: hidden;width:30px;height:30px"></div>
								</div>
								
								<div class="size50Roand defaultBgCl">
									<div class="preview"
									style="overflow: hidden;width:50px;height:50px"></div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!--图片预览区域 end-->
			</div>
			
			<!--正在加载ing-->
			<div class="loading-spinner" id="loading-spinner">
				<img src="${STATIC_SCH }/images/5-121204193R0-50.gif" />
			</div>
			
			<!--button-->
			<div class="actionsbox">
				<input type="button" class="btn-save" value="确定" id="getData" />
				<input type="button" class="btn-cancel" value="确定..."
				id="getDataIng" style="display:none;" />
				<input type="button" class="btn-cancel" id="cancle-img-cut"
				value="取消" />
			</div>
		</div>
		
		<input type="hidden" id="fileNameCut" value="" />
		<input type="hidden" id="operTypeCut" value="" />
		<input type="hidden" id="cutData" value="" />
		<input type="hidden" id="orgId" value="${orgId }" />
		<input type="hidden" id="uid" value="${uid }" />
		<!--图片裁剪-->
		
		<script type="text/javascript">
			$(function(){
				
				getEntUserSelfLogo();//获取顶部小圆圈头像
				getResumeCount();//获取人才库收到简历总数
				
				//重新绑定手机
				$("#bind-phone").click(function(){
					$("#mod-phone").val("");
					$("#mod-phone-code").val("");
					
					$("#mod-phone-code-tip").hide();
					$("#mod-phone-tip").hide();
					
					$("#personal-set-panel").hide();
					$("#change-ph-num").show();
				});

				
				$("#cancel-submit").click(function(){
					$("#updata-password-panel").hide();
					$("#personal-set-panel").show();
				});
				
				//修改密码
				$("#update-passwordBtn").click(function(){
					$("#updata-password-panel").show();
					$("#tip-error").hide();
					$("#personal-set-panel").hide();
				});
				
				//绑定邮箱信息
				
				$("#bing-eamils-info").click(function(){
					$("#personal-set-panel").hide();
					$("#bind-firm-eamil").show();
					$.ajax({                
						cache: false,                
						type: "POST",                
						url:  '<%=path%>/basicConctroller/getUserInfoAndConfig.do',
												data : {
													oper : 1
												},
												async : false,
												error : function(request) {
												},
												success : function(data) {
													var datas = eval('(' + data
															+ ')');
													if (datas.obj != null) {
														$("#config-configId")
																.val(
																		datas.obj.id);
														$("#config-from")
																.val(
																		datas.obj.mail_username);
														$("#config-pw")
																.val(
																		datas.obj.mail_password);
														$("#config-host")
																.val(
																		datas.obj.mail_host);
														$("#config-port")
																.val(
																		datas.obj.mail_port);
													}
												}
											});
								});

				closeMian("close-changehpNum", "change-ph-num");
				closeMian("close-succ-tips", "updata-password-success");
				closeMian("close-bindEamil", "bind-firm-eamil");
				closeMian("close-updata-password", "updata-password-panel");

				function closeMian(close, obj) {
					$("#" + close).on("click", function() {
						$("#" + obj).hide();
						$("#personal-set-panel").show();
						//$("#tzui-loading-overlay").fadeOut();
					});
				}

			});
		</script>