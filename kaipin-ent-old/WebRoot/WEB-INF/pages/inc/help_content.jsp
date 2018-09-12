<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

		<div class="contentBox">
			
			<div class="con-at">
				<!--con-left-tips start-->
				<div class="con-left-tips about-details" id="timer-lines-info" >
					<h5>帮助中心</h5>
					<div class="timer-lines" >
						<div class="single-line"></div>
						<div class="tlt-lists" id="timer-lines">
							<ul>
								<li class="active">
									<span class="pointer"></span>
									<a href="javascript:void(0)" onclick="document.getElementById('about-us').scrollIntoView();"  class="text">开频校招产品使用帮助</a>
								</li>
								<li>
									<span class="pointer"></span>
									<a href="javascript:void(0)" onclick="document.getElementById('about-intro').scrollIntoView();" class="text">如何注册</a>
								</li>
								<li>
									<span class="pointer"></span>
									<a href="javascript:void(0)" onclick="document.getElementById('about-event').scrollIntoView();" class="text">如何完善企业信息</a>
								</li>
								<li>
									<span class="pointer"></span>
									<a href="javascript:void(0)" onclick="document.getElementById('about-fun').scrollIntoView();" class="text">如何发职位</a>
								</li>
								<li>
									<span class="pointer"></span>
									<a href="javascript:void(0)" onclick="document.getElementById('bind-eamil').scrollIntoView();" class="text">如何绑定企业邮箱</a>
								</li>
							</ul>
						</div>
					</div>
				</div>
				<!--con-left-tips start-->
				
				<div class="con-right-details">
					
					<div class="rd-panel">
						
						<div class="rd-head">
						<a href="javascript:void(0)" id="about-us" name="about-us"></a>
							<h5>使用前的准备工作 - 推荐浏览器</h5>
							<img src="<%=path %>/images/help.jpg" />
						</div>
						
						<div class="reg-introduce" >
						<a href="javascript:void(0)" id="about-intro" name="about-intro"></a>
							<h5>如何注册</h5>
							<dl>
								<dt>1.浏览 www.kaipin.tv 网址、点击网站右上角注册按钮</dt>
								<dd><img src="<%=path %>/images/hp01.jpg" width="433" height="263"></dd>
							</dl>
							<dl class="mg0">
								<dt>2.根据提示信息填写内容，<span>注：企业全称一旦填写将无法更改</span></dt>
								<dd><img src="<%=path %>/images/hp02.jpg" width="433" height="263"></dd>
							</dl>
							<dl>
								<dt>3.登录你的注册邮箱并点击激活账号链接</dt>
								<dd><img src="<%=path %>/images/hp03.jpg" width="433" height="263"></dd>
							</dl>
							<dl class="mg0">
								<dt>4.激活成功，你就可以登录了</dt>
								<dd><img src="<%=path %>/images/hp04.jpg" width="433" height="263"></dd>
							</dl>
							<div class="clear"></div>
						</div>
						
						<div class="reg-introduce">
						<a href="javascript:void(0)" id="about-event" name="about-event"></a>
							<h5>如何完善企业信息</h5>
							<dl>
								<dt>1.输入邮箱和密码登录，注意区分大小写</dt>
								<dd><img src="<%=path %>/images/hp05.jpg" width="433" height="263"></dd>
							</dl>
							<dl class="mg0">
								<dt>2.登陆后上传企业logo，企业logo应为四周留白的正方形，如图</dt>
								<dd><img src="<%=path %>/images/hp06.jpg" width="433" height="263"></dd>
							</dl>
							<dl>
								<dt>3.鼠标移到编辑位置并根据提示填写相关内容</dt>
								<dd><img src="<%=path %>/images/hp07.jpg" width="433" height="263"></dd>
							</dl>
							<dl class="mg0">
								<dt>4.激活成功，你就可以登录了</dt>
								<dd><img src="<%=path %>/images/hp08.jpg" width="433" height="263"></dd>
							</dl>
							<dl>
								<dt>5.为了更好的展示企业，你可以完善企业信息</dt>
								<dd><img src="<%=path %>/images/hp09.jpg" width="433" height="263"></dd>
							</dl>
							<div class="clear"></div>
						</div>
						
						
						<div class="reg-introduce">
						<a href="javascript:void(0)" id="about-fun" name="about-fun" ></a>
							<h5>如何发布职位</h5>
							<dl>
								<dt>1.浏览 www.kaipin.tv 网址、点击网站右上角注册按钮</dt>
								<dd><img src="<%=path %>/images/hp10.jpg" width="433" height="263"></dd>
							</dl>
							<dl class="mg0">
								<dt>2.根据提示填写职位属性，带*内容为必填</dt>
								<dd><img src="<%=path %>/images/hp11.jpg" width="433" height="263"></dd>
							</dl>
							<dl>
								<dt>3.发布完成后，可在职位管理中查看、编辑、删除你发布的职位</dt>
								<dd><img src="<%=path %>/images/hp12.jpg" width="433" height="263"></dd>
							</dl>
							<div class="clear"></div>
						</div>
						
						
						
						<div class="reg-introduce" id="bind-eamil">
							<h5>如何绑定企业邮箱</h5>
							<dl>
								<dt>1.从导航栏点击账号设置进入设置面板</dt>
								<dd><img src="<%=path %>/images/hp13.jpg" width="433" height="263"></dd>
							</dl>
							<dl class="mg0">
								<dt>2、轻点“绑定企业邮箱”打开绑定企业邮箱面板</dt>
								<dd><img src="<%=path %>/images/hp14.jpg" width="433" height="263"></dd>
							</dl>
							<dl>
								<dt>3、根据提示填写（端口号默认为25，特殊情况才需要修改）</dt>
								<dd><img src="<%=path %>/images/hp15.jpg" width="433" height="263"></dd>
							</dl>
							<dl class="mg0">
								<dt>4、绑定成功后就可以发送offer邮件了。如果绑定失败请检查填写信息</dt>
								<dd><img src="<%=path %>/images/hp16.jpg" width="433" height="263"></dd>
							</dl>
							<div class="clear"></div>
						</div>
						
						
						<div class="reg-introduce" id="position">
							<h5>列举几个SMTP服务器地址查看方式</h5>
							<dl>
								<dt>126邮箱</dt>
								<dd><img src="<%=path %>/images/hp17.jpg" width="433" height="263"></dd>
							</dl>
							<dl class="mg0">
								<dt>163邮箱</dt>
								<dd><img src="<%=path %>/images/hp18.jpg" width="433" height="263"></dd>
							</dl>
							<div class="clear"></div>
						</div>
						
						
					</div>
				</div>
				<div class="clear"></div>
			</div>
		</div>
		
		<script type="text/javascript">
			$(function(){
				$("#timer-lines").find("li a").click(function(){
					$(this).parent().addClass("active").siblings().removeClass("active");
				});
				var topmain = $("#timer-lines-info").offset().top;
				var sTop = 0;
				$(window).scroll(function () {
					console.log($(window).scrollTop());
	                if ($(window).scrollTop() > (topmain)) {
                        $("#timer-lines-info").addClass("fixedTop");
	                  }else {
                       $("#timer-lines-info").removeClass("fixedTop");
                     }
	                sTop = $(window).scrollTop(); 
	                if(sTop<309){
	                	$("#timer-lines").find("li").eq(0).addClass("active").siblings().removeClass("active");
	                }
	                if(sTop>=309 && sTop <977){
	                	$("#timer-lines").find("li").eq(1).addClass("active").siblings().removeClass("active");
	                }
	                if(sTop>=977 && sTop < 1957){
	                	$("#timer-lines").find("li").eq(2).addClass("active").siblings().removeClass("active");
	                }
	                 if(sTop>=1957 && sTop < 2625){
	                	$("#timer-lines").find("li").eq(3).addClass("active").siblings().removeClass("active");
	                }
	                 if(sTop>=2625){
		                	$("#timer-lines").find("li").eq(4).addClass("active").siblings().removeClass("active");
		             }
	            });
			});
		</script>