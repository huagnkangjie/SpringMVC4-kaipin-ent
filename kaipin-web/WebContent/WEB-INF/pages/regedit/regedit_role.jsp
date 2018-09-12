<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
  
  	<%@ include file="/WEB-INF/pages/regedit/comment/regHeader.jsp" %>
  	
	<script type="text/javascript">
		var r_path='<%=basePath%>';
	</script>
	</head>
	<body>
		<!--header start-->
		<div class="lm-header-container">
			<div class="header">
				<h1>
					<a href="<%=path %>/init" class="logo"></a>
					<span class="tips-txt"> - 创建您的开频账户</span>
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
					<dd class="lq-color-one">创建账号</dd>
				</dl>
				<dl class="sheet2">
					<dt>
						<span class="ll-bg ll-bg-two">2</span>
					</dt>
					<dd class="lq-color-two">选择身份</dd>
				</dl>
				<dl class="sheet3">
					<dt>
						<span class="ll-bg ll-bg-one">3</span>
					</dt>
					<dd class="lq-color-three">填基本信息</dd>
				</dl>
				<dl class="sheet4">
					<dt>
						<span class="ll-bg ll-bg-one">4</span>
					</dt>
					<dd class="lq-color-three">提交认证</dd>
				</dl>
				<div class="line line1 line-color-sure"></div>
				<div class="line line2"></div>
				<div class="line line3"></div>
			</div>
			
			<div class="content-box">
				<div class="all-sheet identity-selection">
					<div class="sel-box">
						<form>
							<dl class="sb-dist">
								<dt>
									<p class="tlt">求职用户（中高等院校学生和毕业不久的学生）</p>
								</dt>
								<dd>
									<div class="intro">
										观看企业视频宣讲提前了解职场，在线笔试和视频面试，降低时间及交通成本，随时随地参与校招，与企业校友互动。
									</div>
								</dd>
							</dl>
							
							<dl class="sb-dist">
								<dt>
									<p class="tlt">企业用户</p>
									
								</dt>
								<dd>
									<div class="intro">
										精准推荐潜在对口的人才，与学生群体、高校、企业建立联系，企业获得全国高校人才资源。
									</div>
								</dd>
							</dl>
							
							<dl>
								<dt>
									<p class="tlt">高校用户</p>
								</dt>
								<dd>
									<div class="intro">
										高效同步企业校招信息，与企业和学生建立联系，提高学校就业率。
									</div>
								</dd>
							</dl>
							
							
						<!--	<input type="checkbox" id="check-student" class="check-mark check-inp"/>
							<input type="checkbox" id="check-company" class="check-mark check-inp"/>-->
							
							<div id="lable-select">
								<label for="check-student" id="slect-student" data-type="1" class="check-mark  check-no slect-stu"></label>
								<label for="check-company" id="slect-company" data-type="2" class="check-mark check-no slect-com"></label>
								<label for="check-colleges" id="slect-Colleges" data-type="3" class="check-mark check-no slect-coll"></label>
							</div>
							<input type="hidden" id="regeditType" value="0"/>
							<input type="hidden" id="userId" value="${userId }"/>
						</form>
						
						<div class="next-step">
							<a  href="javascript:void(0);" class="btn" id="nextDo">下一步</a>
							<a  href="javascript:void(0);" class="btn" id="nextDo-ing" style="display:none;">
								提交中...<img src="<%=path%>/images/loading.gif" style="margin-left:5px;"/>
							</a>
						</div>
					</div>
				</div>
				
			</div>
		</div>
		<!--注册流程 end-->
		
		<script>
			$(function(){
				
				
				var height = $(window).height();
				var docu =  $(document).height();
				
				if(height == docu){
					$("#footer").addClass("fiexFoot");
				}else{
					$("#footer").remove("fiexFoot");
				}
				
			});
		</script>
		
		<!-- footer_reg start-->
		<%@ include file="/WEB-INF/pages/regedit/comment/regFooter.jsp" %>
		<!-- footer_reg end-->
		
		<script type="text/javascript" src="<%=basePath%>/js/base.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/regedit/regedit.js?v.<%=System.currentTimeMillis()%>"></script>
		<script type="text/javascript" src="<%=basePath%>/js/regedit/fiexdFoot.js"></script>
		<script type="text/javascript">
			$(function(){
				
				$("#lable-select").find("label").click(function(){
					$(this).addClass("bg-img").siblings().removeClass("bg-img");
				});
			});
			
			
		</script>
	</body>
</html>
