<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
 	<%@ include file="/WEB-INF/pages/ent/regedit/comment/regHeader.jsp" %>
	<script type="text/javascript">
		var r_path='<%=basePath%>';
	</script>
	
	
	
  </head>
  
  <body>
		<!--header start-->
		<div class="lm-header-container">
			<div class="header">
				<h1>
					<a href="javascript:void(0)" class="logo backIndexLogin"></a>
					<span class="tips-txt">-创建您的开频账户</span>
				</h1>
			</div>
		</div>
		<!--header end-->
		
		<!--注册流程 start-->
		<div class="register-process">
			
			<div class="flowsheet">
				<dl class="sheet1">
					<dt>
						<span class="ll-bg ll-bg-three">1</span>
					</dt>
					<dd>选择身份</dd>
				</dl>
				<dl class="sheet2">
					<dt>
						<span class="ll-bg ll-bg-two">2</span>
					</dt>
					<dd>提交账号信息</dd>
				</dl>
				<dl class="sheet3">
					<dt>
						<span class="ll-bg ll-bg-one">3</span>
					</dt>
					<dd>提交认证</dd>
				</dl>
				<div class="line line1"></div>
				<div class="line line2"></div>
			</div>
			
			<div class="content-box">
				<div class="all-sheet register-box">
					
					<!--提交账号信息 start-->
					<div class="rgb-panel">
						<form id="regeditForm" name="regeditForm" action="<%=path%>/loginController/register.do" method="post">
							<input type="hidden" name="oper" value="ent"/>
							
							<div class="rgb-infos">
								<input type="text" class="input" id="entName" name="entName" maxlength="25" placeholder="填写企业全称，请对照公司营业执照企业全称"/>
								<span class="error-info" id="entNameTip"></span>
							</div>
								
							<div class="rgb-infos">
								<input type="text" class="input" id="entSimpleName" name="entSimpleName" maxlength="25"  placeholder="填写企业简称，例如：腾讯、百度、阿里巴巴、富士康"/>
								<span class="error-info" id="entSimpleNameTip"></span>
							</div>
							
							<div class="rgb-infos">
								<input type="text" class="input" id="userName" name="userName" placeholder="输入招聘负责人姓名" maxlength="10"/>
								<span class="error-info" id="userNameTip" ></span>
							</div>
							<div class="rgb-infos">
								<input type="text" class="input" id="phone" name="phone" placeholder="输入招聘负责人手机号码" maxlength="11"/>
								<span class="error-info" id="phoneTip"></span>
							</div>
							<div class="rgb-infos relative">
								<input type="text" class="input" id="phoneCode" placeholder="输入验证码" maxlength="4"/>
								<span class="error-info"></span>
<!-- 								<input type="button" id="btn" value="免费获取验证码" onclick="settime(this)" /> -->
								<a href="javascript:void(0)" class="btn-blue getBtn"  id="getPhoneCode">获取验证码</a>
								<a href="javascript:void(0)" class="btn-blue getBtn" id="getPhoneCodeIng" style="display:none;"></a>
								<span class="error-info" id="phoneCodeTip"></span>
							</div>
							<div class="rgb-infos">
								<input type="text" class="input" id="email" name="email" placeholder="name@example.com" />
								<span class="error-info" id="emailTip"></span>
							</div>
							
							<div class="rgb-infos controlPassword">
								<input type="password" class="input" id="password" name="password" onpaste="return false"  placeholder="输入密码" />
								<input type="text" class="input placeholder" placeholder="输入密码" />
								<span class="error-info" id="passwordTip"></span>
							</div>

							<div class="rgb-infos controlPassword">
								<input type="password" class="input" id="password2" onpaste="return false" placeholder="再输入一次密码" />
								<input type="text"  class="input placeholder" id="psd1" placeholder="再输入一次密码" />
								<span class="error-info" id="password2Tip"></span>
							</div>
							<div class="rgb-infos relative">
								<input type="text" class="input" id="valiCode" name="valiCode"   placeholder="输入图中的字母或数字验证码，不区分大小写" />
								<span class="error-info"></span>
								<a href="javascript:void(0)" onClick="javascript:changeImg();" class="change-ewm">换一张</a>
<!-- 								<img src="images/yanZma.jpg" class="yzmImg" width="73" height="30" alt="验证码"> -->
								<img src="<%=path%>/validatecode.jpg" width="73" height="30" id="validateImg" onClick="javascript:changeImg();" class="yzmImg" >
							</div>
							<span class="error-info" id="valiCodeTip"></span>
							
							<div class="rgb-infos favor">
								 <a href="javascript:void(0)" class="mwui-switch-btn" id="mwui-switch-btn">
							   		<span class=""></span>
							   		<input type="hidden" name="show_icon" id="show_icon" value="0" />
							  	 </a> 
							  	<p class="favor-tips nosel-color"><span id="favor-tips">我已阅读并同意</span><a target="_blank" href="<%=path %>/inc/rule.html">《开频校招服务条款》</a></p>
							  	<p class="loading">已有账号？<a href="javascript:void(0)" class="backIndexLogin">登录</a></p>
							  	<span class="clear"></span>
								<span class="error-info" id="selectedTip">请阅读条款</span>
						   </div>
							
							<div class="rgb-infos submit-btn">
								<a href="javascript:void(0);" class="btn-blue sub-btn" id="saveBtn">提交</a>
								<a href="javascript:void(0);" class="btn-blue sub-btn"  id="submit-reg" style="display:none"><img src="<%=path%>/images/loading.gif" style="margin-right:5px;"/>提交中...
								</a>
							</div>
						</form>
					</div>
					<!--提交账号信息 end-->
				</div>
			</div>
		</div>
		<!--注册流程 end-->
		
		<!-- footer_reg start-->
		<%@ include file="/WEB-INF/pages/ent/regedit/comment/regFooter.jsp" %>
		<!-- footer_reg end-->
		
		
		
		<script type="text/javascript" src="<%=path%>/js/jquery-1.11.1.min.js?v.<%=System.currentTimeMillis()%>" ></script>
		
		<!-- 	Validator -->
		<script type="text/javascript" src="<%=path%>/js/formatJs.js" ></script>
		<script type="text/javascript" src="<%=path%>/js/formValidator.js" ></script>
		<script type="text/javascript" src="<%=path%>/js/formValidatorRegex.js" ></script>
		
		
		<script type="text/javascript" src="<%=path%>/js/base.js?v.<%=System.currentTimeMillis()%>"></script>
		<script type="text/javascript" src="<%=path%>/js/regedit/regedit.js?v.<%=System.currentTimeMillis()%>"></script>
		<script type="text/javascript" src="<%=path%>/js/regedit/fiexdFoot.js"></script>
		
		<script type="text/javascript">
			$(function(){
				
				$("#show_icon").val("0");
				
				//切换选中
				$("#lable-select").find("label").click(function(){
					$(this).addClass("bg-img").siblings().removeClass("bg-img");
				});
				
				//开关
				$("#favor-tips").click(function(){
					$('.mwui-switch-btn').trigger("click");
				});
				
	           $('.mwui-switch-btn').on("click", function(){ 
	                var btn = $(this).find("span");
	                btn.toggleClass('off'); 
	                if(btn.attr("class") == 'off') { 
	                	$("#selectedTip").hide();
	                    $(this).find("input").val("1");
	                    var path = r_path + "/images/off.png";
	                    $("#mwui-switch-btn").css("background-image","url(" + path +")");
	                } else { 
	                    $(this).find("input").val("0");
	                    var path = r_path + "/images/no.png";
	                    $("#mwui-switch-btn").css("background-image","url(" + path +")");
	                }  
	                return false;
		        });
				
				
				 
			})
			
			
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
		        	
		        	if(Math.floor(Sys.ie)<10){
		        		$(".placeholder").css("display","block");
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
	        			    
	        			    
	        			    var control = $(".placeholder");
	        				 var $this = "";
	        				 control.each(function(){
	        				 	$this = $(this); 
	        				 	var $first = $this.parent().find("input:first");
	        				 	$this.on("click",function(){
	        				 		$(this).hide();
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
