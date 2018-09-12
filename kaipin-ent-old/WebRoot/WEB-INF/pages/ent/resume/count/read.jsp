 <!--  header-start -->
		<%@ include file="/WEB-INF/pages/main/header.jsp"%>
<!--  header-end -->

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
	<script src="<%=path%>/js/jquery-easyui-1.2.6/jquery-1.7.2.min.js" charset="UTF-8" type="text/javascript"></script>
	<script src="<%=path%>/js/jquery.cookie.js" charset="UTF-8" type="text/javascript"></script>
	<link id="easyuiTheme" href="<%=path%>/js/jquery-easyui-1.2.6/themes/default/easyui.css" rel="stylesheet" type="text/css" media="screen">
	<link href="<%=path%>/js/jquery-easyui-1.2.6/themes/icon.css" rel="stylesheet" type="text/css" media="screen">
	<script src="<%=path%>/js/jquery-easyui-1.2.6/jquery.easyui.min.js" charset="UTF-8" type="text/javascript"></script>
	<script src="<%=path%>/js/jquery-easyui-1.2.6/locale/easyui-lang-zh_CN.js" charset="UTF-8" type="text/javascript"></script>
	<link href="<%=path%>/css/common.css" rel="stylesheet" type="text/css" media="screen">
	<style type="text/css">
		.datagrid-header-inner{width:1178px;border-left:1px solid #e8e8e8;border-top:0;}
		.datagrid-body{background:#fff;width: 1178px !important;border:1px solid #e8e8e8;border-top:0;overflow: hidden;}
	</style>
	<!-- 系统 -->
	
	<script type="text/javascript">
		
		 var datagrid ;
		 $(function (){
			 datagrid =	$('#grid').datagrid({
				title : '',
				url : '<%=path%>/resume/datagridGoutList.do?status=1',
				pagination : true,
				fitColumns : true,
				showRefresh : false,
				pageSize : 20,
				pageList : [ 10, 15, 20 ],
				fit : true,
				nowrap : false,
				border : false,
				idField : 'id',
				fitColumns : true,
				pagination : true,
				singleSelect:true,
				columns : [ [ {
					title : 'id',
					field : 'id',
					checkbox : true
				}, {
					title : '职位名称',
					width : $(this).width() * 0.1,
					align : 'center',
					resizable : false,
					field : 'position_name'
				}, {
					title : '收到的简历',
					width : $(this).width() * 0.1,
					align : 'center',
					resizable : false,
					field : 'counts'
				}, {
					title : '操作',
					width : $(this).width() * 0.22,
					align : 'center',
					resizable : false,
					field : '1',
					formatter:function(value,row,i){
							return "<a href='javascript:void(0)' onclick='test("+i+");'  class='table-opreat-btn see-btn'>查看</a>";
					}
				} ] ],
				toolbar : '#toolbar',
				onBeforeLoad : function(param) {
					//				parent.$.messager.progress({
					//					text : '数据加载中....'
					//				});
				},
				onLoadSuccess : function(data) {
					//				$('.iconImg').attr('src', sy.pixel_0);
					//				parent.$.messager.progress('close');
				},
				rowStyler:function(index,row){
					var bgClass = "background-color:#F9F9F9;";
					if(index%2==0){
						bgClass = "background-color:#FFF;";
					}
					return bgClass;
				}
			});
			 
			 //条件筛选----城市
			 $(".condition-adress-header").on("click","a",function(){
				var city_code = $(this).data("tag");
				$("#city_code").val(city_code);
				var jobType_code = $("#jobType_code").val();
				$("#jobType_code").val(jobType_code);
				
				serach(city_code,jobType_code);
			 });
			 //条件筛选----城市
			 $(".condition-position-header").on("click","a",function(){
				 var city_code = $("#city_code").val();
				$("#city_code").val(city_code);
				var jobType_code = $(this).data("tag");
				$("#jobType_code").val(jobType_code);
				serach(city_code,jobType_code);
			 });
			 
			 /*统计*/
// 			 getResumeCount();
		});
		 
		 function serach(city_code,jobType_code){
			 datagrid.datagrid('load', {
					city : city_code,
					jobType : jobType_code
				});
		 }
		 
		 function test(index){
			 var row = $("#grid").datagrid("getRows")[index];
			 var postionId = row.postion_id;
			 location.href="<%=path%>/resumeYesController/yesReadPage.do?postionId="+postionId;
		 }
	</script>
  
		
		<!--  数据 -->
		<input type="hidden" id="city_code"/>
		<input type="hidden" id="jobType_code"/>
		
		<!--condition-of-selection start-->
		<div class="condition-of-selection">
			<div class="cos-box" id="cos-box">
				
				<!--cos-condition start-->
				<div class="cos-condition">
					<ul>
						<li >
							<a href="javascript:void(0)" onclick="resumeNoPage();">
								<span class="cos-name">未读</span>
								<span class="cos-num" id="zero">0
								</span>
							</a>
						</li>
						<li class="selected">
							<a href="javascript:void(0)" onclick="resumeYesPage();">
								<span class="cos-name">已读</span>
								<span class="cos-num" id="one">0
								</span>
							</a>
						</li>
						<li>
							<a href="javascript:void(0)" onclick="resumePassPage();">
								<span class="cos-name">筛选通过</span>
								<span class="cos-num" id="two">0
								</span>
							</a>
						</li>
						<li>
							<a href="javascript:void(0)" onclick="resumePassNoPage();">
								<span class="cos-name">筛选未通过</span>
								<span class="cos-num" id="three">0
								</span>
							</a>
						</li>
						<li>
							<a href="javascript:void(0)" onclick="resumeAllPage();">
								<span class="cos-name">全部</span>
								<span class="cos-num" id="total">0
								</span>
							</a>
						</li>
					</ul>
					<div class="clear"></div>
				</div>
				<!--cos-condition end-->
				
				<!--筛选 开始-->
				<div class="filterBox" id="condition-details-lists">
					
					<!--工作地点 开始-->
					<div class="positionAll-style" id="cityList" style="display:none;">
							<ul class="condition-adress-header">
								<li id="city1">
								</li>
								<li class="moreA">
								<a class="btn-more btn-more-adress zIndex100" href="javascript:;">更多 <i class="icon"></i></a>
								</li>
								<div class="clear"></div>
							</ul>	
							<div class="others adress-conditions">
								<ul>
									<li id="city2">
									</li>
									<div class="clear"></div>
								</ul>
						</div>	
					</div>
					<!--工作地点 结束-->
					
					<!--职位名称 开始-->
					<div class="positionAll-style" id="jobType" style="display:none;">
							<ul class="condition-position-header">
								<li id="jobType1">
								</li>
								<li class="moreA">
									<a class="btn-more btn-more-adress zIndex100" href="javascript:;">更多 <i class="icon"></i></a>
								</li>
								<div class="clear"></div>
							</ul>			
							<div class="others position-conditions">
								<ul>
									<li id="jobType2">
									</li>
									<div class="clear"></div>
								</ul>
							</div>
					</div>
					<!--职位名称 结束-->
					
				</div>
				<!--筛选 结束-->
				
				
				<div class="batch-select">
					<div class="table-lists" id="table-lists" style="width:100%;height:750px;border:red;">
						<table id="grid"></table>
					</div>
				</div>
			</div>
		</div>		
		<!--operat-center end-->
		

		<!-- 		 header-start -->
		<%@ include file="/WEB-INF/pages/main/footer.jsp"%>
		<!-- 		 header-end -->
		
		<script type="text/javascript" src="<%=path%>/js/commentjs.js" ></script>
	</body>
</html>
