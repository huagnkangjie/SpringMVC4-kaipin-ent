		<!--  header-start -->
		<%@ include file="/WEB-INF/application/main/header_ent.jsp"%>
		<!--  header-end -->

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
	<script src="${STATIC_ENT }/js/jquery-easyui-1.2.6/jquery-1.7.2.min.js" charset="UTF-8" type="text/javascript"></script>
	<script src="${STATIC_ENT }/js/jquery.cookie.js" charset="UTF-8" type="text/javascript"></script>
	<link id="easyuiTheme" href="${STATIC_ENT }/js/jquery-easyui-1.2.6/themes/default/easyui.css" rel="stylesheet" type="text/css" media="screen">
	<link href="${STATIC_ENT }/js/jquery-easyui-1.2.6/themes/icon.css" rel="stylesheet" type="text/css" media="screen">
	<script src="${STATIC_ENT }/js/jquery-easyui-1.2.6/jquery.easyui.min.js" charset="UTF-8" type="text/javascript"></script>
	<script src="${STATIC_ENT }/js/jquery-easyui-1.2.6/locale/easyui-lang-zh_CN.js" charset="UTF-8" type="text/javascript"></script>
	<link href="${STATIC_ENT }/css/common.css" rel="stylesheet" type="text/css" media="screen">
	<link href="${STATIC_ENT }/css/dialog.css" rel="stylesheet" type="text/css">
	<style type="text/css">
		.datagrid-header-inner{width:1178px;border-left:1px solid #e8e8e8;border-top:0;}
		.datagrid-body{background:#fff;width: 1178px !important;border:1px solid #e8e8e8;border-top:0;overflow: hidden;}
	</style>
	<!-- 系统 -->
	<script type="text/javascript" src="${STATIC_ENT }/js/dialog3.0.js" ></script>
	
	<script type="text/javascript">
		
		 var datagrid ;
		 var postionName;
		 var postionId;
		 $(function (){
			 datagrid =	$('#grid').datagrid({
				title : '',
				url : '<%=path%>/company/examCount/datagrid.do',
				queryParams:{
					status : 1
				},
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
				columns : [ [ {
					title : 'id',
					field : 'id',
					width : $(this).width() * 0.1,
					hidden : true,
					checkbox : true
				}, {
					title : '职位名称',
					width : $(this).width() * 0.2,
					align : 'center',
					resizable : false,
					field : 'position_name'
					
				}, {
					title : '题数',
					width : $(this).width() * 0.2,
					align : 'center',
					resizable : false,
					field : 'countsQuestion'
				},{
					title : '笔试人数',
					width : $(this).width() * 0.2,
					align : 'center',
					resizable : false,
					field : 'countsPeple'
				}, {
					title : '操作',
					width : $(this).width() * 0.395,
					field : '1',
					align:'center',
					resizable : false,
					formatter:function(value,row,i){
						return "<a href='javascript:void(0);' onclick='detail("+i+");' class='btn-set color-operat'>查看</a>";
					}
				} ] ],
				toolbar : '#toolbar',
				onBeforeLoad : function(param) {
					//				parent.$.messager.progress({
					//					text : '数据加载中....'
					//				});
				},
				onLoadSuccess : function(data) {
					
				},
				rowStyler:function(index,row){
					var bgClass = "background-color:#F9F9F9;";
					if(index%2==0){
						bgClass = "background-color:#FFF;";
					}
					return bgClass;
				}
			});
			 
			 /* 条件查询 */
			 $(".select_box").on("click","a",function(){
				 var eduVal = $("#eduListVal").val();
				 var workExpVal = $("#workExpListVal").val();
				 var cityVal = $("#cityListVal").val();
				 search(eduVal, workExpVal, cityVal);
			 });
		});
		 
		 /**
		 *查看当前职位下的所有参加笔试的人员
		 */
		 function detail(index){
			 var row = $("#grid").datagrid("getRows")[index];
			 var positionId = row.position_id;
			 var positionName = row.position_name;
			 datagrid.datagrid('unselectAll');
			 location.href="<%=path%>/company/examList/init?postionId="+positionId+"&positionName="+encodeURI(positionName) ;
		 }
		 
		 /* 笔试题管理 */
		 function examManager(){
			 location.href="<%=path%>/company/examManager/init";
		 }
		 
	</script>
		<div class="writer-exam">
			<div class="weq-lists">
		
				<!--we-l-tlt start-->
				<div class="we-l-tlt">
					<h5>笔试管理</h5>
					<div class="we-manage fr">
							<a href="javascript:void(0)" onclick="examManager();" class="mg-btn">笔试题管理</a>
					</div>
				</div>			
				<!--we-l-tlt end-->
				<!--operat-center start-->
				<div class="oc-ifream">
					<!--operat-center end-->
					<div class="batch-select">
						<div class="table-lists" id="table-lists" style="width:100%;height:750px;">
							<table id="grid" data-options="fit:true"></table>
						</div>
					</div>
				</div>
				<!--operat-center end-->
			</div>
		</div>
		

		<!-- 		 footer-start -->
		<%@ include file="/WEB-INF/application/main/footer.jsp"%>
		<!-- 		 footer-end -->
		
		<script type="text/javascript" src="${STATIC_ENT }/js/commentjs.js" ></script>
		<script type="text/javascript" src="${STATIC_ENT }/js/common-search.js" ></script>
		<script type="text/javascript">
			$(function(){
				$("#we-bgColor").find("tr:even").addClass("bgColor");
			});
		</script>
	</body>
</html>
