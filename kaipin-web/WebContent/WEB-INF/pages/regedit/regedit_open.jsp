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
		
		
		
		<div class="startService">
			<div class="st-panel">
				<div class="sp-title">
					<img src="<%=path %>/images/ser-pic.png" width="161" height="43" />
				</div>
				<c:if test="${localUser.categoryId == '10'}">
					<h5 class="tips-tlt">抓住全国优质职位机会</h5>
					<h5 class="tips-tlt free">一年免费</h5>
					<p class="intros">
						提供500强企业在内的数100万个优质校招职位，为学生异地求职节约时间与金钱成本，提供个性化的求职服务。还能偶遇校友建立联系，现在开始积累最初的职场人脉。
					</p>
					<div class="start-btns">
					<a href="<%=path %>/regedit/init?oper=regedit_basic_stu&id=${localUser.id}" class="operService">开始一年免费求职服务</a>
				</div>
				</c:if>
				
				<c:if test="${localUser.categoryId == '12'}">
					<h5 class="tips-tlt">提高学校就业率</h5>
					<h5 class="tips-tlt free">一年免费</h5>
					<p class="intros">
						与众多校招企业建立联系，实时同步校招需求给本校学生，实时同步人才到企业端，达到无缝连接企业和本校学生，极大的提升人才与企业的需求对接，提高学校品牌宣传。
					</p>
					<div class="start-btns">
					<a href="<%=path %>/regedit/init?oper=regedit_basic_sch&id=${localUser.id}" class="operService">开始一年免费推介服务</a>
				</div>
				</c:if>
				
				<c:if test="${localUser.categoryId == '11'}">
					<h5 class="tips-tlt">获得全国高校人才资源</h5>
					<h5 class="tips-tlt free">一年免费</h5>
					<p class="intros">
						提供100强高校在内的数100万个人才匹配服务，为企业异地校招节约时间与金钱成本，提供个性化的校招服务。还能偶遇HR同行建立联系，现在开始为企业注入新鲜血液。
					</p>
					<div class="start-btns">
					<a href="<%=path %>/regedit/init?oper=regedit_basic_ent&id=${localUser.id}" class="operService">开始一年免费校招服务</a>
				</c:if>
				
			</div>
		</div>
		
		
		<!-- footer_reg start-->
		<%@ include file="/WEB-INF/pages/regedit/comment/regFooter.jsp" %>
		<!-- footer_reg end-->
		
		<script>
			$(function(){
				
				
				var height = $(window).height();
				var docu =  $(document).height();
				console.log(height);
				if(height == docu){
					$("#footer_reg").addClass("fiexFoot");
				}else{
					$("#footer_reg").remove("fiexFoot");
				}
				
			});
		</script>
		
	</body>
</html>
