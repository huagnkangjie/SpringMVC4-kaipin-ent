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
	<!-- 系统 -->
	<link href="<%=path%>/css/dialog.css" rel="stylesheet" type="text/css" >
	<script type="text/javascript" src="<%=path%>/js/dialog3.0.js" ></script>
	
	<style type="text/css">
		.datagrid-header-inner{width:1178px;border-left:1px solid #e8e8e8;border-top:0;}
		.datagrid-body{background:#fff;width: 1178px !important;border:1px solid #e8e8e8;border-top:0;overflow: hidden;}
	</style>
	
	<script type="text/javascript">
		
		 var datagrid ;
		 $(function (){
			 datagrid =	$('#grid').datagrid({
				url : '<%=path%>/basic/datagrid.do',
				pagination : true,
				fitColumns : true,
				showRefresh : false,
				pageSize : 20,
				pageList : [ 20, 50, 100 ],
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
					checkbox : true
				}, {
					title : '企业名称',
					width : $(this).width() * 0.1,
					align : 'center',
					resizable : false,
					field : 'entName'
				}, {
					title : '所属行业',
					width : $(this).width() * 0.1,
					align : 'center',
					resizable : false,
					field : 'industryType'
				}, {
					title : '企业人数',
					width : $(this).width() * 0.1,
					align : 'center',
					field : 'numbers'
				}, {
					title : '注册日期',
					width : $(this).width() * 0.1,
					align : 'center',
					resizable : false,
					field : 'createTime'
				}, {
					title : '操作',
					width : $(this).width() * 0.2,
					field : '1',
					align:'center',
					resizable : false,
					formatter:function(value,row,i){
						return  "<a href='javascript:void(0);' onclick='del("+i+")' class='table-opreat-btn send-offer see-btn'>删除</a>" +
								"<a href='javascript:void(0);' onclick='edit("+i+")' class='table-opreat-btn blue-btn see-btn'>编辑</a>" + 
								"<a href='javascript:void(0);' onclick='detail("+i+");' class='table-opreat-btn see-btn'>查看</a>";
					}
				} ] ],
				toolbar : '#toolbar',
				onBeforeLoad : function(param) {
					//				parent.$.messager.progress({
					//					text : '数据加载中....'
					//				});
				},
				onLoadSuccess : function(data) {
					$('#grid').css("height","800");
				}
			});
			 
			 /* 点击顶部切换选项卡 */
			 $(".dt-infoBox li").click(function (){
				 $(this).addClass("dtr-active").siblings().removeClass("dtr-active");
				 status = $(this).find("a").data("tag");
				 $("#status").val(status);
				 search();
			 });
			 
			 /* 条件查询 */
			 $(".select_box").on("click","a",function(){
				 search();
			 });
			 
			 
		});
		 
		 /* 条件查询 */
	 	function search(){
	 		 var eduVal = $("#eduListVal").val();
			 var workExpVal = $("#workExpListVal").val();
			 var cityVal = $("#cityListVal").val();
			 var status = $("#status").val();
	 		 datagrid.datagrid('load', {
	 			education : eduVal,
	 			workExperience : workExpVal,
	 			workArea : cityVal,
	 			status : status
			});
	 	}
		 
		 /*统计招聘的职位   正在  暂停 过期*/
		 function count(oper){
			 $.ajax({
					cache : false,
					async : false,
					type : 'POST',
					url : '<%=path%>/postionCountController/countPostionByEndtime.do',
					data : {
						oper : oper
					},
					success : function (data){
						var datas =  eval('(' + data + ')'); 
						if(datas.success){
							$("#count0").html(datas.obj.zero);
							$("#count1").html(datas.obj.one);
							$("#count2").html(datas.obj.two);
						}
					}
				});
		 }
		 
		 /**发布职位页面*/
		 function pulish(){
			 location.href="<%=path%>/position/pulishPage.do";
		 }
		 /**编辑*/
		 function edit(index){
			 datagrid.datagrid('unselectAll');
			 var row = $("#grid").datagrid("getRows")[index];
			 location.href="<%=path%>/position/editPage.do?postionId="+row.id;
		 }
		 /**
		 *删除
		 */
		 function del(index){
			 datagrid.datagrid('unselectAll');
			 var row = $("#grid").datagrid("getRows")[index];
			 var positonName = row.positionName;
			 $.fn.fandialog({
				title:"提示信息",
				contents:"是否删除职位【"+positonName+"】",
				click:function(ok){
					if(ok){
						$.ajax({
							cache : false,
							async : false,
							type : 'POST',
							url : '<%=path%>/position/del.do',
							data : {
								ids : row.id
							},
							success : function (data){
								count(1);
								datagrid.datagrid('reload');
							}
						});
					}
				}
			});

		 }
		 /**
		 *职位详情
		 */
		 function detail(index){
			 datagrid.datagrid('unselectAll');
			 var row = $("#grid").datagrid("getRows")[index];
			 var id = row.id;//简历id
			 location.href="<%=path%>/position/detail.do?postionId="+id;
		 }
		 
		 /**
		 *全选
		 */
		 function selectAll(obj){
			 var choose = obj.checked;
			 if(choose == true){
				 datagrid.datagrid('selectAll');
			 }else{
				 datagrid.datagrid('unselectAll');
			 }
		 }
		 /**批量通过*/
		 function passAll(){
			 var rows = datagrid.datagrid('getSelections');
			 if(rows.length == 0){
				 $.fn.fandialog({
					title:"提示信息",
					contents:"请选择职位！",
					click:function(ok){
					}
				});
			 }else{
				 $.fn.fandialog({
						title:"提示信息",
						contents:"是否删除职位！",
						click:function(ok){
							var ids = [];
							for ( var i = 0; i < rows.length; i++) {
								ids.push(rows[i].id);
							}
							$.ajax({
								cache : false,
								async : false,
								type : 'POST',
								url : '<%=path%>/position/del.do',
								data : {
									ids : ids.join(',')
								},
								success : function (data){
									count(1);
									datagrid.datagrid('reload');
								}
							});
						}
					});
			 }
		 }
	</script>
  
		<div class="error-page" id="error-page">
			<div class="container">
				<div class="error-cons">
					<span>你访问的页面暂时不存在</span>
				</div>
			</div>
		</div>		
		
		

		<!-- 		 header-start -->
		<%@ include file="/WEB-INF/pages/main/footer.jsp"%>
		<!-- 		 header-end -->
		
		<script type="text/javascript" src="<%=path%>/js/commentjs.js" ></script>
	</body>
</html>
