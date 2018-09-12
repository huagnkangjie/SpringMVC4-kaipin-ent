<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<% String url = request.getRequestURL().toString();
	if(null!=url&&url.startsWith("http://kaipin.tv")) 
	response.sendRedirect("http://www.kaipin.tv");
%>
<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
   	 <title>2016校园招聘_开频校招_免费校园招聘网_线上招聘会_线上宣讲会_线上双选会_视频面试</title>
     <meta  content="开频,校招,校园招聘网,应届生求职,找工作,大学生求职,人才网,宣讲会视频,在线双选会,视频面试,kaipin,应届生求职,暑期兼职,寒假兼职,兼职,实习,大一,大二,大三,大四,研究生" name="Keywords"/>
     <meta content="开频校招通过大数据分析，把校招职位与大学生简历数据匹配，为大学生和企业提供免费校招服务,是学生、企业、学校的连接器,是中国领先的一站式校园垂直招聘社区平台,宣讲会直播点播,在选笔试,视频面试,收发Offer, 大学生直接入职,打破时空限制实现无缝链接,让校园招聘变得很简单" name="Description"/>
    
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<meta name="renderer" content="webkit">
	<link rel="stylesheet" href="<%=path%>/css/index.css" />
	<link rel="shortcut icon" href="<%=path%>/favicon.ico" type="image/x-icon" />
	<script type="text/javascript">
		var r_path='<%=basePath%>';
	</script>
	<script type="text/javascript" src="<%=path%>/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="<%=path%>/js/jquery.fullPage.js"></script>
		<script type="text/javascript">
			$(function(){
				$('#dowebok').fullpage({
					scrollingSpeed:700,
					navigation:true,
					navigationPosition: 'right',
					afterLoad: function(anchorLink, index){
						if(index==4){
							$("#icon-collections").addClass("transShow");
						}
					},
					onLeave: function(index, direction){
						if(index==4){
							$("#icon-collections").removeClass("transShow");
						}
					}
				});
				
				var tabNum = 1;
				$(window).keydown(function(event){
					if(event.keyCode == 9){
						tabNum ++;
						if(tabNum%2==0){
							$("#recruit-login").find(".emailInput").focus();
						}else{
							$("#recruit-login").find(".pswInput").focus();
						}
						return false;
					}
				});
				
				/*显示*/
				closeBtnShow($("#recruit-login"),".recruit-close");
				closeBtnShow($("#recruit-registe"),".registe-close");
				function closeBtnShow($obj,$btn){
					$obj.mouseover(function(){
						$obj.find($btn).show();	
					}).mouseout(function(){
						$obj.find($btn).hide();
					});
				}
				
				/*关闭*/
				closeForm($("#recruit-login"),'.recruit-close');
				closeForm($("#recruit-registe"),'.registe-close');
				function closeForm($obj,$btn){
					$obj.find($btn).click(function(){
						$obj.hide();
					});
				}
				$("#login-button").click(function (){
					$("#recruit-login").show();
				});

				$("#regedit").click(function (){
					$("#recruit-registe").show();
				});
				
			});
		</script>
	</head>
	<body>
		
		<!--lm-header-container start-->
		<div class="lm-header-container">
			<div class="lm-header">
				<h1 class="header-logo"><a href="http://kaipin.tv"></a></h1>
				<div class="login-registe-btns">
					<a class="signup_link login-button" href="<%=path%>/login" id="login-button">登录</a>
					<a class="signup_link signup-button" href="<%=path%>/regedit" id="regedit">注册</a>
				</div>
			</div>
		</div>
		<!--lm-header-container end-->
		
		<!--dowebok start-->
		<div id="dowebok">
			<!--section1 start-->
			<div class="section section1">
				<div class="lm-cover-layer"></div>
				<!--part-one-contents start-->
				<div class="part-one-contents">
					<div class="lm-description">
						<div class="dzt-words animated fadeInUp">
							<p id="dzt-words">开频校招，开创全国一站式校招先河<br>一次在线宣讲覆盖全国高校</p>
						</div>
						
						 <div class="schoolRecruit-app-download" id="schoolRecruit-app-download">
							<ul>
								<li>
									<div class="lists-panel">
										<a href="javascript:void(0)" class="dl-title"><span class="icon icon-iPhone"></span><span class="sketch-words">iPhone</span></a>
										<div class="download-list" style="clear: both;">
											<a href="https://itunes.apple.com/cn/app/kai-pin-xiao-zhao/id1073527699?mt=8" class="bd-line">学生版</a>
											<a href="https://itunes.apple.com/cn/app/kai-pin-xiao-zhao-qi-ye/id1075852261?mt=8">企业版</a>
										</div>
									</div>
								</li>
								<li>
									<div class="lists-panel">
										<a href="javascript:void(0)" class="dl-title"><span class="icon icon-Android"></span><span class="sketch-words">Android</span></a>
										<div class="download-list">
											<a href="http://www.kaipin.tv/download/kaipintv_xs.apk" class="bd-line">学生版</a>
											<a href="http://www.kaipin.tv/download/kaipintv_qy.apk">企业版</a>
										</div>
									</div>
								</li>
								<li class="lm-erweima" >
									<div class="lists-panel">
										<a href="javascript:void(0)" class="dl-title"><span class="icon icon-erweima"></span> </a>
									</div>
									<div class="erm-down">
										<div class="list">
											<div class="erm-panle">
												<dl class="fl">
													<dt><img src="<%=path%>/images/stu-ewm.jpg" tlt="二维码扫描下载开频校招客户端" tilte="二维码扫描下载开频校招客户端"></dt>
													<dd>扫描下载客户端</dd>
												</dl>
												
												<!-- <div class="halving-line"></div> -->
											</div>
										</div>
									</div>
								</li>
								<div class="clear"></div>
							</ul>
						</div>  
					</div>
				</div>
				<!--part-one-contents end-->
				<div class="mouse-hover">
					<a href="javascript:void(0)" class="mouse-show"></a>
					<span class="mouse-show slideDown"></span>
				</div>
			</div>
			<!--section1 end-->
			
			
			
			<!--section2 start-->
			<div class="section section4">
				<div class="peacock-screem-exhibition">
					
					<div class="lm-exhibition-details">
						<!--开宣讲会 start-->
						<div class="exhibition-part1">
							<div class="ep-left-headerPic">
								<a href="javascript:void(0)"><img src="<%=path%>/images/live8.png"></a>
								<span class="lv-tips-tlt">开宣讲会</span>
							</div>
							<div class="ep-right-consShow">
								<div class="company-name-details">
									<div class="cnd-info">
										<span class="info-picLogo"></span>
										<div class="info-title">
											<h3>美国亚马逊</h3>
											<span>秋季宣讲会</span>
										</div>
									</div>
									<a href="javascript:void(0)" class="live-tips">直播</a>
								</div>
								
								<div class="live-videos">
									<ul>
										<li class="lv-icon-nhz"><a href="javascript:void(0)"><img src="<%=path%>/images/live1.png"></a></li>
										<li class="lv-icon-dxm"><a href="javascript:void(0)"><img src="<%=path%>/images/live2.png"></a></li>
										<li class="lv-icon-fed"><a href="javascript:void(0)"><img src="<%=path%>/images/live3.png"></a></li>
										<li class="lv-icon-ds"><a href="javascript:void(0)"><img src="<%=path%>/images/live4.png"></a></li>
										<li class="lv-icon-mmkc"><a href="javascript:void(0)"><img src="<%=path%>/images/live5.png"></a></li>
										<li class="lv-icon-zbf"><a href="javascript:void(0)"><img src="<%=path%>/images/live6.png"></a></li>
									</ul>
								</div>
							</div>
						</div>
						<!--开宣讲会 end-->
						<!--视频面试 start-->
						<div class="exhibition-part1 video-interview">
							<div class="ep-left-headerPic">
								<a href="javascript:void(0)"><img src="<%=path%>/images/live9.png"></a>
								<span class="lv-tips-tlt">视频面试</span>
							</div>
							<div class="ep-right-consShow interview-ing">
								<h3>亚马逊招聘经理</h3>
								<p><span>06:57</span></p>
							</div>
						</div>
						<!--视频面试 end-->
						
						<!--发送offer start-->
						<div class="exhibition-part1 send-offer-example">
							<div class="ep-left-headerPic">
								<a href="javascript:void(0)"><img src="<%=path%>/images/live10.png"></a>
								<span class="lv-tips-tlt">发送OFFER</span>
							</div>
							<div class="ep-right-consShow send-offer-dome">
								<p>发送OFFER</p>
							</div>
						</div>
						<!--发送offer end-->
					</div>
					
					<div class="explain-of-lmzp animated fadeInRight">
						<h3>开频校招，让校园招聘变得如此简单</h3>
						<p>在线发布/观看视频直播或点播宣讲会，在线笔试、视频面试、直至发放/接受offer，实时互动中一站式完成校招。</p>
					</div>
					
				</div>
			</div>
			<!--section2 end-->
			
			
			<!--section3 start-->
			<div class="section section3">
				<div class="recruitment-desc  animated fadeInDown">
					<p class="desc-title">开频校招，专为知名企业与大学生群体量身打造的职场盛宴</p>
					<p class="desc-con">这里聚集国内知名企业，荟萃各大高校优秀学生、轻松校招/求职，你会爱上这里。</p>
				</div>
				<div class="figure-panle-show">
					<img src="<%=path%>/images/figure-man.png" class="translate-figure-slide"/>
				</div>
			</div>
			<!--section3 end-->
			
			
			<!--section4 start-->
			<div class="section section2">
				<div class="icon-collections" id="icon-collections">
					<span class="icon icon-main"></span>
					<span class="icon icon-panel1"></span>
					<span class="icon icon-panel2"></span>
					<span class="icon icon-panel3"></span>
					<span class="icon icon-panel4"></span>
					<span class="icon icon-panel5"></span>
					<span class="icon icon-panel6"></span>
					<span class="icon icon-panel7"></span>
					<span class="icon icon-panel8"></span>
					<span class="icon icon-panel9"></span>
					<span class="icon icon-panel10"></span>
					<span class="icon icon-panel11"></span>
					<span class="icon icon-panel12"></span>
					<span class="icon icon-panel13"></span>
					<span class="icon icon-panel14"></span>
					<span class="icon icon-panel15"></span>
					<span class="icon icon-panel16"></span>
					<span class="icon icon-panel17"></span>
					<span class="icon icon-panel18"></span>
					<span class="icon icon-panel19"></span>
					<span class="icon icon-panel20"></span>
					<span class="icon icon-panel21"></span>
					<span class="icon icon-panel22"></span>
					<span class="icon icon-panel23"></span>
				</div>
				<div class="part-two-contents animated fadeInUp">
					<p class="bigWordsShow">开频校招，打破时空限制<br>实现无缝链接</p>
					<p class="smallWordsShow">我们让距离、时间都不是事儿，只有“愿不愿、想不想”。对滴，就是如此任性。<br>
让企业、学生共同撰写属于自己的未来。</p>
				</div>
			</div>
			<!--section4 end-->
			
			
		</div>
		<!--dowebok end-->
		
		
		<div class="footer">
			<div class="foot">
				<div class="ft-infos">
<%-- 					<a href="<%=path%>/v/about">关于我们</a>   --%>
<!-- 					<span class="rule">|</span>   -->
<%-- 					<a href="<%=path%>/v/help">帮助中心</a>   --%>
					<span class="rule">|</span>意见反馈QQ群：185930548<span class="rule">|</span>&copy; 2016开频
				</div>
			</div>
		</div>
		<script>
			$(function(){
				var height = $(window).height();
				$(".section").css("height",height);
				$(".fp-tableCell").css("height",height);
			});
		</script>
		
	</body>
</html>

