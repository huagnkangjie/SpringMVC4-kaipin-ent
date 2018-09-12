<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- header-start -->
<%@ include file="/WEB-INF/application/main/header_ent.jsp"%>
<!-- header-end -->
	<link href="${STATIC_SCH }/css/basic.css" rel="stylesheet" type="text/css" />
	<link href="${STATIC_SCH }/css/newPosi.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${STATIC_SCH }/js/home/home.js"></script>
	<script type="text/javascript" src="${STATIC_SCH }/js/basic/basicV1.js"></script>
	<script type="text/javascript" src="${STATIC_SCH }/js/basic/basic.js"></script>
	<script type="text/javascript" src="${STATIC_ENT }/js/position/positionList.js"></script>
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
	

	<script type="text/javascript">
		
	</script>
	
		<!--校招岗位  开始-->
		<div class="confer-detalis-cons" >
		<p class="pagename">在招职位</p>
			<!--详细校招列表 开始-->
			<div class="school-position">

					<div class="school-hover" id="school-hover">
					</div>
			</div>
			<!--详细校招列表 结束-->
		</div>
		<!--校招岗位  结束-->
  	
		<!-- header-start -->
		<%@ include file="/WEB-INF/application/main/footer.jsp"%>
		<!-- header-end -->
		
		<script type="text/javascript">
			var companyId ;
		
			$(function(){
				companyId = '${companyId}';
				getPostion(companyId);
			});
			//		获取首页职位列表
			function getPostion(companyId){
				$.ajax({                
					cache: true,    
					async: true, 
					type: "POST",                
					url:  r_path + '/position/datagridIndex?org_id='+companyId,                
					data:{
						page : 1,
						rows : 100
					},              
					error: function(request) {                    
					},                
					success: function(data) {
						var datas = eval('('+data+')');
						if(datas.rows.length > 0){
							$("#noPosition").hide();
							$("#positions").show();
							$("#school-hover").empty();
							var html = "";
							for(var i = 0; i < datas.rows.length; i++){
								if(i%2==0){
									html = html + ""
								}else{
									html = html + "<div class='joblist'"
								}
								var times = getTimeByMillis(datas.rows[i].create_time);
								html = html +
												"<div class='joblist'><p><a href='javascript:void(0);' class='jobname' data-tag='"+datas.rows[i].id+"'>"+datas.rows[i].position_name+"</a></p>" +
												"<input type='hidden' value='"+datas.rows[i].id+"'/>"+
												"<p title='"+datas.rows[i].jobType+"'>"+datas.rows[i].jobType+"</p>"+
												"<p title='"+datas.rows[i].docname+"'>"+datas.rows[i].docname+"</p>"+
												"<p title='"+datas.rows[i].create_time+"'>"+times+"</p>"+
											"</div>";
								
							}
							$("#school-hover").append(html);
							$("#school-hover").find(".jobname").click(function(){
// 								var id = $(this).find("input[type='hidden']").val();
								var id = $(this).data("tag");
								positionDetail(id);
							}) ; 
						}
					}            
				});
			}
			function positionDetail(obj){
				location.href= r_path + "/position/detail?positionId="+obj+"&org_id="+companyId;
			}
		</script>
  </body>
</html>
