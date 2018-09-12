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
	<link href="<%=path%>/css/dialog.css" rel="stylesheet" type="text/css">
	<style type="text/css">
		.datagrid-header-inner{width:1178px;border-left:1px solid #e8e8e8;border-top:0;}
		.datagrid-body{background:#fff;width: 1178px !important;border:1px solid #e8e8e8;border-top:0;overflow: hidden;}
	</style>
	<!-- 系统 -->
	<script type="text/javascript" src="<%=path%>/js/dialog3.0.js" ></script>
	
	<script type="text/javascript">
		
		 var datagrid ;
		 var positionName;
		 var postionId;
		 var counts;
		 var countsPeple;
		 
		 $(function (){
			 postionId = '${POSTION_ID }';
			 counts = '${counts }';
			 countsPeple = '${countsPepleNo }';
			 positionName = '${positionName }';
			 $("#countsPeple").html(countsPeple);
			 $("#positionName").html(positionName);
			 datagrid =	$('#grid').datagrid({
				title : '',
				url : '<%=path%>/examList/datagrid.do',
				queryParams:{
					positionId : postionId
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
					title : '笔试人',
					width : $(this).width() * 0.1,
					align : 'center',
					resizable : false,
					field : '2',
					formatter : function (value,row,i){
						return row.surname + row.miss_surname;
					}
				}, {
					title : '笔试题数',
					width : $(this).width() * 0.1,
					align : 'center',
					resizable : false,
					field : 'degree',
					formatter : function(value, row, i){
						return counts;
					}
				},{
					title : '答题数',
					width : $(this).width() * 0.1,
					align : 'center',
					resizable : false,
					field : 'countsAnswer'
				},{
					title : '用时（秒）',
					width : $(this).width() * 0.1,
					align : 'center',
					resizable : false,
					field : 'second'
				},{
					title : '测试时间',
					width : $(this).width() * 0.12,
					align : 'center',
					resizable : false,
					field : 'create_time',
					formatter : function (v,rec,i){
						var st = getTimeByMillis(rec.create_time);
						return st;
					}
				},{
					title : '状态',
					width : $(this).width() * 0.1,
					align : 'center',
					resizable : false,
					field : 'result_type',
					formatter : function(value, row, i){
						var v = value.toString();
						var countsAnswer = row.countsAnswer;
						if(isNotEmptys(countsAnswer)){
							if(isNotEmptys(value.toString())){
								if(v == '0'){
									return "<a style='color:red;'>未阅卷</a>";
								}else if(v == '1'){
									return "已阅卷";
								}else if(v == '7'){
									return "不通过";
								}else if(v == '8'){
									return "已通过";
								}
							}	
						}else{
							return "未作答";
						}
						
					}
				}, {
					title : '操作',
					width : $(this).width() * 0.2,
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
		 *简历详情
		 */
		 function detail(index){
			 datagrid.datagrid('unselectAll');
			 var row = $("#grid").datagrid("getRows")[index];
			 var paperId = row.paperId;
			 var uId = row.stu_user_id;
			 var inviteId = row.inviteId;
			 var userName = row.surname + row.miss_surname;
			 if(isNotEmptys(paperId)){
				 location.href="<%=path%>/examList/detail.do?paperId="+paperId+"&uId="+uId+"&inviteId="+inviteId+"&userName=" + encodeURI(userName);
			 }else{
				 $.fn.fandialog({
						title:"提示信息",
						contents: "该试卷未进行作答",
						click:function(ok){
						}
					});
			 }
		 }
		 
		 /* 笔试题管理 */
		 function examManager(){
			 location.href="<%=path%>/examManager/init.do";
		 }
		 
	</script>
		<div class="writer-exam">
			<div class="weq-lists">
		
				<!--we-l-tlt start-->
				<div class="we-l-tlt">
					<h5><span id="positionName"></span>职位笔试结果<span id="countsPeple">0</span>条</h5>
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
		

		<!-- 		 header-start -->
		<%@ include file="/WEB-INF/pages/main/footer.jsp"%>
		<!-- 		 header-end -->
		
		<script type="text/javascript" src="<%=path%>/js/commentjs.js" ></script>
		<script type="text/javascript" src="<%=path%>/js/common-search.js" ></script>
		<script type="text/javascript">
			$(function(){
				$("#we-bgColor").find("tr:even").addClass("bgColor");
			});
		</script>
	</body>
</html>
