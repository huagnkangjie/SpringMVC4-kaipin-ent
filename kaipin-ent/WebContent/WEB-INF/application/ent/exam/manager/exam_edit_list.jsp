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
	<link href="${STATIC_ENT }/css/animate.css" rel="stylesheet" type="text/css">
	<style type="text/css">
		.datagrid-header-inner{width:1178px;border-left:1px solid #e8e8e8;border-top:0;}
		.datagrid-body{background:#fff;width: 1178px !important;border:1px solid #e8e8e8;border-top:0;overflow: hidden;}
	</style>
	<!-- 系统 -->
	<script type="text/javascript" src="${STATIC_ENT }/js/dialog3.0.js" ></script>
	<script type="text/javascript" src="${STATIC_ENT }/js/exam/exam.js?v.<%=System.currentTimeMillis()%>" ></script>
	
	<script type="text/javascript">
		
		 var datagrid ;
		 var postionName;
		 var postionId;
		 $(function (){
			 postionId = '${POSTION_ID }';
			 datagrid =	$('#grid').datagrid({
				title : '',
				url : '<%=path%>/examCount/datagrid.do',
				queryParams:{
					status : 0
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
					title : '状态',
					width : $(this).width() * 0.2,
					align : 'center',
					resizable : false,
					field : 'status',
					formatter : function(value,row,i){
						if(isNotEmptys(value)){
							if(value == '0'){
								return "已停用";
							}else if(value == '1'){
								return "启用中";
							}
						}else {
							return "已停用";
						}
					}
				}, {
					title : '操作',
					width : $(this).width() * 0.395,
					field : '1',
					align:'center',
					resizable : false,
					formatter:function(value,row,i){
						var status = row.status;
						var html = "";
						if(status != null || status != ''){
							if(status == '0'){
								html = "<a href='javascript:void(0);' onclick='oper(1,"+i+")' class='btn-set color-pass'>启用</a>";
							}else if(status == '1'){
								html = "<a href='javascript:void(0);' onclick='oper(0,"+i+")' class='btn-set color-operat'>停用</a>";
							}
						}else {
							html = "";
						}
						return  "<a href='javascript:void(0);' onclick='editPage(0,"+i+")' class='btn-set color-operat'>编辑</a>" +
								html + 
								"<a href='javascript:void(0);' onclick='editPage(1,"+i+");' class='btn-set color-operat'>查看</a>";
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
			 
		});
		 
		 /**
		 *简历详情
		 */
		 function detail(index){
			 datagrid.datagrid('unselectAll');
// 			 var row = $("#grid").datagrid("getRows")[index];
// 			 var resumeName = row.resume_name;//简历名字
// 			 var resumeId = row.resume_id;//简历id
			 location.href="<%=path%>/examList/init";
		 }
		 
		/*  编辑试题 */
		 function editPage(oper,index){
			 var row = $("#grid").datagrid("getRows")[index];
			 var paperId = row.paper_id;
			 var status = row.status;
			 if(status == '0' && oper != '1'){
				 $.fn.fandialog({
						title:"提示信息",
						contents: "该试卷已停用，编辑后会自动转换成启用，是否继续？",
						click:function(ok){
							if(ok){
								location.href="<%=path%>/examManager/editPage?paperId="+paperId+"&oper="+oper;
							}
						}
					});
			 }else{
				 location.href="<%=path%>/examManager/editPage?paperId="+paperId+"&oper="+oper;
			 }
		 }
		 
		 /* 启用或者停用 */
		 function oper(oper,index){
			 var row = $("#grid").datagrid("getRows")[index];
			 var paperId = row.paper_id;
			 var tipName = "";
			 if(oper == '0'){
				 tipName = "该试卷已启用，确定停用？";
			 }else if(oper == '1'){
				 tipName = "该试卷已停用，确定启用？";
			 }
			 $.fn.fandialog({
					title:"提示信息",
					contents: tipName,
					click:function(ok){
						if(ok){
							$.ajax({
								cache : false,
								async : false,
								type : 'POST',
								url : '<%=path%>/examManager/upOrDown.do',
								data : {
									oper : oper,
									paperId : paperId
								},
								success : function (data){
									var datas =  eval('(' + data + ')'); 
									if(datas.success){
										datagrid.datagrid('reload');
										datagrid.datagrid('unselectAll');
									}else{
										alert("系统错误")
									}
								}
							});
						}
					}
				});
		 }
		 
	</script>
		<div class="writer-exam">
			<div class="weq-lists">
		
				<!--we-l-tlt start-->
				<div class="we-l-tlt">
					<h5>笔试题管理</h5>
					<div class="we-manage fr">
							<a href="javascript:void(0)" class="mg-btn" id="writer-manager">+添加笔试题</a>
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
		
		<!--遮盖层-->
		<div class="tzui-loading-overlay" id="weitten-examOverlay"></div>
		
		<!-- 添加试题弹出框 开始 -->
		<div class="weitten-exam-mg" id="weitten-exam-mg">
			<div class="mg-panel noSel">
				<span class="exam-tlt-tips">笔试职位</span>
				<div class="exam-cons-lists" >
					<ul id="exam-select-mark">
						<li>
							<div class="select_box">
								<span class="select_txt" id="positionSelected">请选择</span>
								<input type="hidden" id="positionSelectedId"/>
								<span class="selet_open"></span>
								<div class="option" id="positionList">
									<a href="javascript:void(0)">互联网电子商务</a>
								</div>
							</div>
						</li>
					</ul>
				</div>
				<div class="clear"></div>
			</div>
			<!-- 添加试题弹出框 结束 -->
			
			<div class="op-btns">
				<a href="javascript:void(0)" class="op-sure" id="addPage">添加笔试题</a>
				<a href="javascript:void(0)" class="op-cancle">取消</a>
			</div>
		</div>
		
		<!-- 		 footer-start -->
		<%@ include file="/WEB-INF/application/main/footer.jsp"%>
		<!-- 		 footer-end -->
		
		
	</body>
</html>
