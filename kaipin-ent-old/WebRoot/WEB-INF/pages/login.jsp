<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
    <title>2016校园招聘_开频校招_免费校园招聘网_线上招聘会_线上宣讲会_线上双选会_视频面试</title>
     <meta  content="开频,校招,校园招聘网,应届生求职,找工作,大学生求职,人才网,宣讲会视频,在线双选会,视频面试,kaipin,应届生求职,暑期兼职,寒假兼职,兼职,实习,大一,大二,大三,大四,研究生" name="Keywords"/>
     <meta content="开频校招通过大数据分析，把校招职位与大学生简历数据匹配，为大学生和企业提供免费校招服务,是学生、企业、学校的连接器,是中国领先的一站式校园垂直招聘社区平台,宣讲会直播点播,在选笔试,视频面试,收发Offer, 大学生直接入职,打破时空限制实现无缝链接,让校园招聘变得很简单" name="Description"/>
    
	<meta http-equiv="pragma" content="no-cache">
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
		
			var status_oper ;
			$(function(){
				status_oper = '${status_oper}';
				if(status_oper == '1'){
					window.location.href="<%=path%>/basicConctroller/init.do";
				}
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
				$("#email").focus(function(){
					tabNum = 2;
				});
				$(window).keydown(function(event){
					var evev = event || window.event;　
					if(evev.keyCode == 9){
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
		
		
		
		<script type="text/javascript">
			var oper = 0;
			$(function (){
				//验证邮箱
				$("#email").blur(function (){
					emailValidata();
				});
				//密码
				$("#password").blur(function (){
					pw1Validata();
				});
				
				document.onkeydown = function(event) {　
					var evev = event || window.event;　
					if (evev.keyCode == 13) {
						　login();　
					}
					　　
				};
			});
			/* 密码1 */
			function pw1Validata(){
				var pw1 = $("#password").val();
				if(pw1.length != 0){
					if (pw1 != '' && pw1.length >= 6 && pw1.length <= 32) {
						$("#tip").hide();
						return true;
						// 唯一性验证
					} else if (pw1.length != '' && (pw1.length < 6 || pw1.length > 32)) {
						$("#tip").find(".tips").empty();
						$("#tip").show();
						$("#tip").find(".tips").html("密码长度6-32位");
						return false;
					} 
				}else{
					$("#tip").find(".tips").empty();
					$("#tip").show();
					$("#tip").find(".tips").html("密码长度6-32位");
					return false;
				}
			}
			/* 验证邮箱 */
			function emailValidata(){
				return true;
// 				var email = $("#email").val();
// 				if (emailCheck(email)) {
// 					$("#tip").empty();
// 					$("#tip").hide();
// 					return true;
// 				}else{
// 					$("#tip").empty();
// 					$("#tip").show();
// 					$("#tip").html("邮箱格式不对");
// 					return false;
// 				}
			}
			function emailCheck(value) {
				var pattern = /^[0-9A-Za-z0-9]+([-_.][0-9A-Za-zd0-9]+)*@([^_]+[0-9a-zA-Za-zd_]+[-.])+[A-Za-zd]{2,5}$/;
				if (!pattern.test(value)) {
					return false;
				}
				return true;
			}
			
			function login(){
				if(emailValidata()){
					if(pw1Validata()){
						$("#loginBtn").hide();
						$("#loginBtn2").show();
						if(oper == 0){
							oper = 1;
							setTimeout(function(){
								var userName = $("#email").val();
								var password = $("#password").val();
								$.ajax({
									cache : false,
									async : false,
									type : 'POST',
									url : r_path + '/loginController/login',
									data : {
										userName : $.trim(userName),
										password : password
									},
									success : function(data) {
										var datas = eval('(' + data + ')');
										if (datas.success) {
											location.href = r_path + "/basicConctroller/init";
										} else {
											oper = 0;
											var obj = datas.obj;
											var msg = datas.msg;
											if(obj == '0'){
												$("#tip").find(".tips").html(msg);
												location.href = r_path + "/regedit/againMailPage?userName="+userName;
											}else if(obj == '1'){
												$("#tip").find(".tips").html(msg);
											}else{
												$("#tip").find(".tips").html(msg);
											}
											$("#loginBtn").show();
											$("#loginBtn2").hide();
											$("#tip").show();
										}
									}
								});
							},300);
						}
						
					}
				}
			}
		</script>
	</head>
	<body>
		
		<!--lm-header-container start-->
		<div class="lm-header-container">
			<div class="lm-header">
				<h1 class="header-logo"><a href="http://kaipin.tv"></a></h1>
				<div class="login-registe-btns">
					<a class="signup_link login-button" href="<%=path%>/loginController/index.do" id="login-button">企业登录</a>
					<a class="signup_link signup-button" href="<%=path%>/regedit/init.do" id="regedit">企业注册</a>
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
				
				<!--part-one-contents end-->
				<div class="mouse-hover">
					<a href="javascript:void(0)" class="mouse-show"></a>
					<span class="mouse-show slideDown"></span>
				</div>
				
				<!--login start-->
				<div class="recruit-login flipInX" id="recruit-login">
					<form id="form1" name="form1" action="<%=path%>/loginController/login.do" method="post">
						<p class="login-userEmail"><input type="text" class="emailInput" value="${userName }" name="userName" id="email" placeholder="手机号/电子邮件" /></p>
						<p class="login-userPassword" id="control">
							<input type="password" class="pswInput" value="${passWord }" name="password" id="password"  placeholder="密码"/>
							<input type="text" class="passwordIpt placeholder" placeholder="密码">
						</p>
						<p class="tips-message" id="tip" style="display:none;">
							<span class="mg-bg"></span>
							<span class="tips">用户名或密码错误</span>
						</p>
						<p class="login-sumit">
							<input type="button" class="submit-btn" onclick="login();" id="loginBtn" value="登录"/>
							<input type="button" class="submit-btn"  id="loginBtn2" style="display:none;" value="登录..."/>
						</p>
						<p class="forget-password"> <a href="<%=path%>/regedit/init.do?oper=backPw" target="_block">忘记密码?</a></p>
					</form>
<!-- 					<a href="javasscript:void(0)" style- class="recruit-close"></a> -->
				</div>
				<!--login end-->
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
				<div class="recruitment-desc animated fadeInDown">
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
					<a href="<%=path%>/v/about">关于我们</a>  
					<span class="rule">|</span>  
					<a href="<%=path%>/v/help">帮助中心</a>  <span class="rule">|</span>  意见反馈QQ群：185930548  <span class="rule">|</span>   &copy; 2015开频校招  渝ICP备15011713号-2
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
		
		
		<script type="text/javascript">
		    $(function () {
		        var Sys = {};
		        var ua = navigator.userAgent.toLowerCase();
		        var s;
		        (s = ua.match(/rv:([\d.]+)\) like gecko/)) ? Sys.ie = s[1] :
		        (s = ua.match(/msie ([\d.]+)/)) ? Sys.ie = s[1] :
		        (s = ua.match(/firefox\/([\d.]+)/)) ? Sys.firefox = s[1] :
		        (s = ua.match(/chrome\/([\d.]+)/)) ? Sys.chrome = s[1] :
		        (s = ua.match(/opera.([\d.]+)/)) ? Sys.opera = s[1] :
		        (s = ua.match(/version\/([\d.]+).*safari/)) ? Sys.safari = s[1] : 0;
		        
		        if (Sys.ie){
		        	var $ie = Math.floor(Sys.ie);
		        	if($ie<=9){
		        		function change(obj, alt, val) {
		    		        obj.hide();
		    		        alt.show();
		    		        alt.val(val);
		    		    }
		        		
		        		function placeHolder() {
		        			//holder获取所有文本框返回jquery对象，然后使用.each()方法对其进行遍历
		        			
		        			    var holder = $("input[type = 'text']").not("[class = 'text']"),
		        			        texts = $(".text"),
		        			        passwords = $(".password"),
		        			        note = passwords.attr("placeholder");
		        			    change(passwords, texts, note);
		        			    texts.on("focus",function(){
		        			        change(texts, passwords, "");
		        			        passwords.focus();  
		        			    });
		        			    passwords.on("blur",function(){
		        			        if($(this).val() == ""){
		        			            change(passwords, texts, note);
		        			        }
		        			    });
		        			    holder.each(function () {
		        			        var that = $(this);
		        			        var note = that.attr("placeholder")
		        			        that.val(note);
		        			        that.on({
		        			            "focus": function () {  
		        			                if (that.val() == note) {
		        			                    that.val("");
		        			                }
		        			            },
		        			            "blur": function () {
		        			                if (that.val() == "") {
		        			                    that.val(note);
		        			                }
		        			            }
		        			        });
		        			    });
		        			};
	        			    //能力检测
	        			    function isPlaceHolder(){
	        			        return "placeholder" in document.createElement("input");
	        			    }
	        			    if(!isPlaceHolder()){
	        			        placeHolder();
	        			    }
	        			    
	        			    var $placeholder = $("#control").find(".placeholder");
	        				 var $this = "";
	        				 $placeholder.css("display","block");
	        				 $placeholder.each(function(){
	        				 	$this = $(this); 
	        					var $first = $this.parent().find("input:first");
	        				 	$this.on("click",function(){
	        				 		$this.hide();
	        				 		$first.focus();
	        				 	});
	        				 	
	        				 	$first.on("blur",function(){
	        				 		String.prototype.trim=function(){return this.replace(/(^\s*)|(\s*$)/g,"");}
	        				 		var val = $(this).val();
	        				 		if(val.trim() == ""){
	        				 			var next = $(this).next("input").show();
	        				 		}
	        				 		
	        				 		/*if(val.length <= 0 || val == ""){
	        				 			var next = $(this).next("input").show();
	        				 		}*/
	        				 	});
	        				 });
		        	}
		        }
		    });
		</script>
		
	</body>
</html>
