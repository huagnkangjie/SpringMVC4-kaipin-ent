<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    <title>2016校园招聘_开频校招_免费校园招聘网_线上招聘会_线上宣讲会_线上双选会_视频面试</title>
     <meta  content="开频,校招,校园招聘网,应届生求职,找工作,大学生求职,人才网,宣讲会视频,在线双选会,视频面试,kaipin,应届生求职,暑期兼职,寒假兼职,兼职,实习,大一,大二,大三,大四,研究生" name="Keywords"/>
     <meta content="开频校招通过大数据分析，把校招职位与大学生简历数据匹配，为大学生和企业提供免费校招服务,是学生、企业、学校的连接器,是中国领先的一站式校园垂直招聘社区平台,宣讲会直播点播,在选笔试,视频面试,收发Offer, 大学生直接入职,打破时空限制实现无缝链接,让校园招聘变得很简单" name="Description"/>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="-1">   
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<meta name="renderer" content="webkit"> 
	<script type="text/javascript">
		var r_path='<%=basePath%>';
	</script>
	<!-- 系统 -->
	<link rel="stylesheet" href="<%=path%>/css/basic.css?v.<%=System.currentTimeMillis()%>" />
	<link rel="shortcut icon" href="<%=path%>/favicon.ico" type="image/x-icon" />
	<script type="text/javascript" src="<%=path%>/js/jquery-1.11.1.min.js?v.<%=System.currentTimeMillis()%>" ></script>
<%-- 	<script src="<%=path%>/js/jquery-easyui-1.2.6/jquery-1.7.2.min.js" charset="UTF-8" type="text/javascript"></script> --%>
	<script type="text/javascript" src="<%=path%>/js/constants.js?v.<%=System.currentTimeMillis()%>" ></script>
	<script type="text/javascript" src="<%=path%>/js/common.js?v.<%=System.currentTimeMillis()%>" ></script>
	<script type="text/javascript" src="<%=path%>/js/basic.js?v.<%=System.currentTimeMillis()%>"></script>
	<script type="text/javascript" src="<%=path%>/js/regedit/regedit.js?v.<%=System.currentTimeMillis()%>"></script>
	<script type="text/javascript" src="<%=path%>/js/comet/comet4j.js?v.<%=System.currentTimeMillis()%>"></script>
	<script type="text/javascript" src="<%=path%>/js/header.js?v.<%=System.currentTimeMillis()%>"></script>
	
	<script type="text/javascript">
		$(function (){
			
			
			showLogo();//显示头像
			
			var entName = '${sessionScope.user.entName }';
			if(entName != null || entName != ""){
				var length = entName.length;
				if(length > 10){
					entName = entName.substring(0,10);
					$("#header_ent_all_name").html(entName + "...");
				}else{
					$("#header_ent_all_name").html(entName);
				}
			}
			/* 加载头像 */
			var logoImg = '${sessionScope.logo }';
			if(logoImg != undefined){
				if(logoImg != 'null' && logoImg != ''){
					$("#header-logo").css("background-image","url("+logoImg+")");
					$("#hd-logo").html("<img  src='"+logoImg+"' width='48' height='48'>");
				}
			}
			
			$("#push-position").click(function (){
				location.href="<%=path%>/position/pulishPage.do";
			});
			
			/* 全局搜索 */
			$("#search").click(function (){
				var v = $("#search-value").val();
				if(v.length > 0){
					document.formSearch.submit();
				}
			});
			
// 			msg();
			//runMsg();
// 			init();
			/* 加载职位数 */
			getResumeCount();
		});
		
		/* 消息通知中心 */
		function runMsg(){
			
// 			var time = 1000*60*5;//5分钟
			var time = 1000*5;
			setInterval(msg,time);
		}
		function msg(){
			$.ajax({                
				cache: false,    
				async: false, 
				type: "POST",                
				url:  '<%=path%>/msgController/msg.do',                
				error: function(request) {                    
				},                
				success: function(data) {
					var datas = eval('('+data+')');
					var count = 0;
// 					var meetCount = datas.obj[0].meetCount;
					var viewCount = datas.obj[0].viewCount;
					count = viewCount;
					if(count > 0){
						$("#count").show();
						$("#count").html(count);
						$("#viewCount").html(viewCount);
// 						$("#meetCount").html(meetCount);
					}
				}            
			});
		}
		
		/*个人配置页面*/
		function config(){
			location.href="<%=path%>/configController/init.do";
		}
		/* 退出 */
		function layout(){
			$("#tzui-loading-overlay—layout").show();
			var redirect_uri = r_web_url + "/login";
			parent.window.location.href= r_sso_url + "/web/auth/logout?redirect_uri=" + redirect_uri;
		}
		/* 职位发布 */
		function pushPosition(){
			location.href="<%=path%>/position/pulishPage.do";
		}
		
		
		
		/* 初始化消息通道 */
		function init(){
	         var number1 = document.getElementById('number1');
	         var number2 = document.getElementById('number2');
	         // 建立连接，conn 即web.xml中 CometServlet的<url-pattern>
	         var url = r_path + "/conn.do";
	         JS.Engine.start(url);
	         // 监听后台某个频道
	         JS.Engine.on(
	                { 
	                 msgInterView : function(data){
	                	 var datas = eval('('+data+')');
	                	 getMsg(datas);
	                 }
	             }
	            );
	 	}
		
		/* 收到广播后获取消息 */
		function getMsg(datas){
			var self_companyId = $("#self_companyId").val();
			var self_uId = $("#self_uId").val();
			var companyId = datas.company_id;
			var uIds = datas.user_ids;
			if(datas.type == '2'){
				if(self_companyId == companyId){
					if(uIds.length > 0){
						for (var i= 0;  i< uIds.length; i++) {
							if(self_uId == uIds[i]){
								var msg_type = datas.msg_type;
								if(msg_type == '4'){
									setTimeout(function(){
										msg();
									}, 1000);
								}
								break;
							}
						}
					}
				}
			}else if(datas.type == '1'){
				//TODO
				//公共消息
			}
		}
		
	</script>
	
  </head>
  <body>
<!-- <input type="text" id="msgId"/> -->
  	<input type="hidden" id="self_companyId" value="${sessionScope.user.companyId }"/>
  	<input type="hidden" id="self_uId" value="${sessionScope.user.id }"/>
  <!--header start-->
		<div class="header">
			<div class="head">
				<div class="hd-top-search fl">
					<a href="<%=path %>/basicConctroller/init.do" class="lm-logo fl"></a>
					<h3>
						<form method="post" id="formSearch" name="formSearch" action="<%=path%>/globalSearchController/search.do">
							<input type="hidden" name="oper" value="1"/>
							<input type="text" name="datas" id="search-value" class="search-input fl" placeholder="搜索收到的简历" />
							<a href="javascript:void(0)" id="search" class="search-btn fl"></a>
						</form>
					</h3>
				</div>
				
				<div class="company-info fr">
					
					<!--消息中心 start-->
					<div class="message-center fl" id="message-details">
						<a href="javascript:void(0)" class="timely-information">
							<span class="info-number" style="display:none;" id="count">1</span>	
						</a>
						<div class="message-details">
							<h4 class="info-notice">消息通知</h4>
							<div class="info-lists">
								<dl>
									<dt>
										<a href="javascript:void(0)" class="mg-notice-tlt">
											<span class="tlt-info">宣讲会通知</span>
											<span class="info-timer" id="meetCount">0</span>
										</a>
									</dt>
<!-- 									<dd><a href="javascript:void(0)" class="mg-company-tips">小米公司宣讲会</a></dd> -->
								</dl>
								<dl>
									<dt>
										<a href="<%=path %>/msgController/getMsgEntViewList.do" id="entViewList" class="mg-notice-tlt">
											<span class="tlt-info">面试通知</span>
											<span class="info-timer" id="viewCount">0</span>
										</a>
									</dt>
<!-- 									<dd><a href="javascript:void(0)" class="mg-company-tips">小米公司宣讲会</a></dd> -->
								</dl>
								
<!-- 								<div class="read-more"> -->
<!-- 									<a href="javascript:void(0)" class="">查看更多</a> -->
<!-- 								</div> -->
							</div>
						</div>
					</div>
					<!--消息中心 end-->
					
					<!--设置中心 start-->
					<div class="setting-center fl" id="personal-setting">
						<a href="javascript:void(0)" class="enterprise-name">
							<span class="ep-logo" title="" id="header-logo" style="background-image:url(<%=path%>/images/headLogo.png)"></span>
<!-- 							<span id="header_ent_all_name1" title=""></span> -->
<%-- 							<span id="header_ent_all_name" title="${sessionScope.user.entName }"></span> --%>
						</a>
						
						<div class="personal-setting">
							<h4 class="info-notice">个人中心</h4>
							<div class="ps-settints-to-show" >
								<ul>
<!-- 									<li> -->
<!-- 										<span class="setting-icon center"></span> -->
<!-- 										<a href="javascript:void(0)" class="">个人中心</a> -->
<!-- 									</li> -->
									<li>
										<span class="setting-icon set"></span>
										<a href="javascript:void(0)" id="account-set-btn">设置</a>
									</li>
									<li>
										<span class="setting-icon layout"></span>
										<a href="javascript:void(0)" onclick="layout();" class="xt-layout">退出</a>
									</li>
								</ul>
							</div>
						</div>
					</div>
					<!--设置中心 end-->
				</div>
				<div class="clear"></div>
			</div>
			
			<div class="operation-nav">
				<div class="nav-lists">
					<ul class="lists-details fl">
						<li style="padding-left:1px;"><a href="<%=path%>/basicConctroller/init.do">首页</a></li>
						<li><a href="<%=path%>/resume/init.do" id="resumeDatas">人才库</a></li>
						<li><a href="<%=path%>/position/init.do">职位库</a></li>
						<li><a href="<%=path%>/examCount/init.do">笔试管理</a></li>
					</ul>
					<div class="send-meeting-btn fr">
						<a href="javascript:void(0)" id="send-preachBtn" style="display:none;">发布信息</a>
						<a href="javascript:void(0)" onclick="pushPosition();" id="push-position">发布职位</a>
					</div>
				</div>
			</div>
			
		</div>
		
		
		<!--遮盖层-->
		<div id="tzui-loading-overlay" class="tzui-loading-overlay"></div>
		<!--遮盖层 退出-->
		<div id="tzui-loading-overlay—layout" class="tzui-loading-overlay" style="text-align: center;">
			<img src="<%=path%>/images/5-121204193R0-50.gif" style="margin-top: 280px;">
		</div>
		
		<!--个人设置  开始-->
		<div class="personal-set" id="personal-set-panel">
			<a href="javascript:void(0)" class="close-xx" id="close-accountSet"></a>
			<div class="ps-tilte">设置</div>
			<div class="ps-info-cos">
				<div class="header-info">
					<a href="javascript:void(0)" title="" class="hd-logo" id="hd-logo"><img  src="<%=path%>/images/moren.jpg" width="48" height="48"></a>
					<div class="header-name">
						<p class="tlt"><spam id="mod-entName-tip">XXXX</spam>
							<a href="javascript:void(0)" class="certi no-certi" style="display:none;" id="no-ertificate1" onclick="certificateTipPage(99);" title="未认证">未认证</a>
							<a href="javascript:void(0)" class="certi ing-certi" style="display:none;" id="ing-ertificate1" onclick="certificateTipPage(0);" title="审核中">审核中</a>
							<a href="javascript:void(0)" class="certi already-certi" style="display:none;" id="yes-ertificate1" onclick="certificateTipPage(2);" title="已认证">已认证</a>
							<a href="javascript:void(0)" class="certi already-certi" style="display:none;" id="nopass-ertificate1" onclick="certificateTipPage(1);" title="审核不通过">审核不通过</a>	
						</p>
						<p>
							<span class="eamil" id="mod-email-tip">5856776@qq.com</span>
							<a href="javascript:void(0)" class="update-pswd" id="update-passwordBtn">修改账号密码</a>
						</p>
					</div>
				</div>
				
				<div class="personal-forms">
					<form method="post">
						<div class="pf-totalStyle">
							<span class="pf-tips">手机</span>
							<input type="text" class="pf-input" id="user-phone" name="phone" disabled="disabled"/>
							<input type="button" class="change-account" value="修改账号" style="display: none;"/>
							<a href="javascript:void(0)" class="update-pswd" id="bind-phone">重新绑定手机号</a>
						</div>
						
						<div class="pf-otherStyle">
							<div class="pf-totalStyle">
								<span class="pf-tips">姓</span>
								<input type="text" id="user-userSurname" name="surname" class="pf-input"/>
							</div>
							
							<div class="pf-totalStyle" style="float:right">
								<span class="pf-tips">名</span>
								<input type="text" id="user-userName" name="missSurname" class="pf-input" style="width:250px;"/>
							</div>
							<div class="clear"></div>
						</div>
						
						<div class="pf-otherStyle">
							<div class="pf-totalStyle">
								<span class="pf-tips">性别</span>
								<select name="sex" id="user-sex" class="down-list">
										<option value="1">女</option>
										<option value="2">男</option>
								</select>
							</div>
							<div class="clear"></div>
						</div>

						<div class="pf-otherStyle">
							<div class="pf-totalStyle">
								<span class="pf-tips">企业名称</span>
								<input type="text" class="pf-input firm" id="mod-entName" name="mod-entName" value="${mod_entName }"/>
							</div>
							<div class="clear"></div>
						</div>
						
						
						<div class="pf-otherStyle">
							<div class="pf-totalStyle">
								<span class="pf-tips">职务</span>
								<input type="text" class="pf-input" id="user-position" name="position"/>
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
						<div class="pf-otherStyle" style="display:none;"> 
							<p class="bind-eamils"><a href="javascript:void(0)" id="bing-eamils-info">绑定企业邮箱</a>（用于发录取邮件）</p>
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
			<a href="javascript:void(0)" class="close-xx" id="close-updata-password"></a>
			<div class="psw-title">修改密码</div>
			<form method="post" id="modifyPwForm" name="modifyPwForm">
				<div class="error-info" id="tip-error" style="display:block;">密码错误</div>
				<div class="psw-toatl-pattern">
					<span class="psw-tips">当前密码</span>
					<input type="password" class="up-input" id="pw-now" name="pwNow"/>
				</div>
				<div class="psw-toatl-pattern">
					<span class="psw-tips">修改密码</span>
					<input type="password" class="up-input" id="pw" name="pw" onpaste="return false"/>
				</div>
				<div class="psw-toatl-pattern">
					<span class="psw-tips">确认密码</span>
					<input type="password" class="up-input" id="pw-sure" name="pwSure" onpaste="return false"/>
				</div>
				<div class="psw-toatl-pattern">
					<span class="psw-tips"></span>
					<input type="button" class="sure-submit" id="sure-submit" value="确定"/>
					<input type="button" class="cancel-submit" id="cancel-submit" value="取消"/>
				</div>
			</form>
		</div>
		
		<!--修改密码  结束-->
		
		<!--修改密码成功提示 开始-->
		<div class="updata-password-success" id="updata-password-success">
			<a href="javascript:void(0)" class="close-xx" id="close-succ-tips"></a>
			<div class="upd-ps-true">
				<span class="true-icon"></span>
				<span class="true-tips">密码修改成功</span>
				<a href="javascript:void(0)" class="sure-btn" id="sure-modifyPw-btn">确定</a>
			</div>
		</div>
		<!--修改密码成功提示 结束-->
		
		
		<!--绑定企业邮箱 start-->
		<div class="bind-firm-eamil noSel" id="bind-firm-eamil">
			<a href="javascript:void(0)" class="close-xx" id="close-bindEamil"></a>
			<input type=hidden id="config-configId" name="configId"/>
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
							<input type="password" id="config-pw" name="mailPassword" class="em">
						</div>
					</div>
					
					
					<div class="bd-input">
						<span class="tips fl">SMTP服务器</span>
						<div class="input-cons fl smtp-web">
							<input type="text" id="config-host" name="mailHost" class="em">
						</div>						
						<div class="smtp-num fr">
							<span>端口</span>
							<input type="text" id="config-port" name="mailPort" class="input-smtp" value="465">
						</div>
						<div class="other-tips">
							<p class="exam">例：不会填写邮箱服务器？点击获得<a href="<%=path%>/loginController/help.do" class="help">帮助</a></p>
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
							<a href="javascript:void(0)" class="btn sure-btn" id="config-mail-btn">确定</a>
							<a href="javascript:void(0)" class="btn sure-btn" style="display:none;" id="config-mail-btn-ing">
								提交中...<img src="<%=path %>/images/loading.gif" width="16" height="16" style="margin-left:5px"/>
							</a>
							<a href="javascript:void(0)" class="btn off-btn" id="cancle-mail">取消</a>
						</div>
					</div>
				</form>
			</div>
			
			<div class="bind-succsee" style="display: none;" id="bind-success-tip">
				<span class="right"></span>
				<p class="tips">绑定成功</p>
			</div>
			
			<div class="bind-succsee bind-fails" id="bind-fail-tip" style="display: none;">
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
						<p class="send-input mgb fl"><input type="text" id="mod-phone" class="txt" maxlength="11" placeholder="请输入新手机号作为账号"></p>
						<p class="send-angin mgb fr">
							<a href="javascript:void(0)" class="send" id="getPhoneCode">获取验证码</a>  
							<a href="javascript:void(0)" class="send" id="getPhoneCodeIng" style="display:none;">50S后重发</a></p>
						<p class="tips" style="display:none;" id="mod-phone-tip">请输入有效的手机号码</p>
					</div>
					<div class="ph-input">
						<p class="send-input mgb fl"><input type="text" id="mod-phone-code" class="txt" placeholder="请输入验证码"></p>
						<p class="send-angin mgb fr"><a href="javascript:void(0)" class="sure-ph" id="mod-phone-btn">确定</a></p>
						<p class="tips" style="display:none;" id="mod-phone-code-tip">验证码错误</p>
					</div>
				</form>
			</div>
		</div>
		
		<!--重新绑定手机号 结束-->
		
		
		<script type="text/javascript">
			$(function(){
				
				/***************************新添加内容开始*************************/
				
				
				//修改密码成功提示
// 				$("#sure-submit").click(function(){
// 					$("#updata-password-panel").hide();
// 					$("#updata-password-success").show();
// 				});
				
				//重新绑定手机
				$("#bind-phone").click(function(){
					$("#personal-set-panel").hide();
					$("#change-ph-num").show();
					$("#mod-phone").val("");
					$("#mod-phone-tip").hide();
					$("#mod-phone-code-tip").hide();
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
						async: false,                
						error: function(request) {                    
						},                
						success: function(data) {
							var datas = eval('('+data+')');
							if(datas.obj != null){
								$("#config-configId").val(datas.obj.id);
								$("#config-from").val(datas.obj.mail_username);
								$("#config-pw").val(datas.obj.mail_password);
								$("#config-host").val(datas.obj.mail_host);
								$("#config-port").val(datas.obj.mail_port);
							}
						}            
					});
				});
				
				
				closeMian("close-changehpNum","change-ph-num");
				closeMian("close-succ-tips","updata-password-success");
				closeMian("close-bindEamil","bind-firm-eamil");
				closeMian("close-updata-password","updata-password-panel");
				
				function closeMian(close,obj){
					$("#"+close).on("click",function(){
						$("#"+obj).hide();
						$("#tzui-loading-overlay").fadeOut();
					});
				}
				
				
				/***************************新添加内容结束*************************/
				
				
			});
		</script>
		
		