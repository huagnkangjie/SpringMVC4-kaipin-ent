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
		 var postionName;
		 var postionId;
		 $(function (){
			 postionId = '${POSTION_ID }';
			 datagrid =	$('#grid').datagrid({
				title : '',
				url : '<%=path%>/resume/datagridNoReadList.do',
				queryParams : {
					postionId : postionId,
					status : '3'
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
					checkbox : true
				}, {
					title : '简历名称',
					width : $(this).width() * 0.2,
					align : 'center',
					resizable : false,
					field : 'resume_name',
					formatter : function (value,row,i){
						var surName = row.surname;
						var sissName = row.miss_surname;
						var r_name = row.resume_name;
						if(surName != undefined && sissName != undefined){
							return r_name + " - " + surName + sissName;
						}else{
							return r_name;
						}
					}	
				}, {
					title : '学历',
					width : $(this).width() * 0.09,
					align : 'center',
					resizable : false,
					field : 'major'
				},/*  {
					title : '工作经验',
					width : $(this).width() * 0.09,
					align : 'center',
					field : 'work_experience',
					formatter : function (value,row,i){
						var st = row.st;
						var et = row.et;
						if(st != undefined && et != undefined){
							var date1 = Date.parse(st);
							var date2 = Date.parse(et);
							var date3 = date2-date1;
							var days=Math.floor(date3/(24*3600*1000*30));
							if(days == '0'){
								return Math.floor(date3/(24*3600*1000)) + "天";
							}
							return days + "月";
						}else{
							return "--";
						}
					}
				}, */ {
					title : '性别',
					width : $(this).width() * 0.05,
					align : 'center',
					resizable : false,
					field : 'sex_code',
					formatter : function(value,row,i){
						if(value != null && value != ''){
							if(value == "2"){
								return "男";
							}else if(value == "1"){
								return "女";
							}else if(value == "0"){
								return "未知";
							}
						}else{
							return "--";
						}
					}
				},/*  {
					title : '工资要求',
					width : $(this).width() * 0.06,
					align : 'center',
					resizable : false,
					field : 'salary'
				},  */{
					title : '操作',
					width : $(this).width() * 0.395,
					field : '1',
					align:'center',
					resizable : false,
					formatter:function(value,row,i){
						return  "<a href='javascript:void(0);' onclick='passOrNo(2,"+i+")' class='btn-set color-pass'>通过</a>" + 
								"<a href='javascript:void(0);' onclick='detail("+i+");' class='btn-set color-operat'>查看</a>";
					}
				} ] ],
				toolbar : '#toolbar',
				onBeforeLoad : function(param) {
					//				parent.$.messager.progress({
					//					text : '数据加载中....'
					//				});
				},
				onLoadSuccess : function(data) {
					$("#sum").empty();
					var length = data.total;
					if(length == 0){
						$("#sum").append("<a href='javascript:void(0)'>"+postionName+"<span>0</span></a>");
					}else{
						postionName = data.rows[0].postion_name;
						$("#sum").append("<a href='javascript:void(0)'>"+data.rows[0].postion_name+"<span>"+data.total+"</span></a>");
					}
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
		 
		 /* 条件查询 */
		 	function search(eduVal, workExpVal, cityVal){
		 		datagrid.datagrid('load', {
		 			education : eduVal,
		 			workExperience : workExpVal,
		 			workArea : cityVal,
		 			status : '3',
		 			postionId : postionId
				});
		 	}
		 
		 /**
		 *通过
		 */
		 function passOrNo(oper,index){
			 var row = $("#grid").datagrid("getRows")[index];
			 var resumeName = row.resume_name;
			 var ids = row.id;//投递简历关系表主键
			 if(oper == 2){
				 $.fn.fandialog({
					title:"提示信息",
					contents:"是否通过简历【"+resumeName+"】",
					click:function(ok){
						doPassOrNo(ids,oper);
					}
				});
			 }
		 }
		 function doPassOrNo(ids,oper){
			 $.ajax({                
				cache: false, 
				async: false, 
				type: "POST",                
				url:  '<%=path%>/resumePassOrNoController/passOrNo.do',                
				data:{
					ids : ids,
					oper : oper
				},                
				success: function(data) {
					var data = JSON.parse(data);
					if(data.success){
						datagrid.datagrid('reload');
					}
				}            
			});
		 }
		 /**
		 *简历详情
		 */
		 function detail(index){
			 datagrid.datagrid('unselectAll');
			 var row = $("#grid").datagrid("getRows")[index];
			 var resumeName = row.resume_name;//简历名字
			 var resumeId = row.resume_id;//简历id
			 location.href="<%=path%>/resumeDetail/init.do?resumeId="+resumeId;
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
					contents:"请选择至少一份简历！",
					click:function(ok){
					}
				});
			 }else{
				//TODO 
			 }
		 }
	</script>

		
		<!--单个职位名称 开始-->
			
			<div class="singlePoistion-name">
				<div class="spn-box width1180" id="sum">
				</div>
			</div>
			
		<!--单个职位名称 结束-->
		
		
		<!--operat-center start-->
		<div class="operat-center">
			<div class="oc-ifream">
				<div class="condition-iframeDetails" style="display:none;">
					<!--条件选择 start-->
					<div class="basic-info-select" >
				<dl>
					<dd>筛选条件</dd>
					<dt>
						<ul id="basic-info-select" class="mod_select">
							<li>
								<div class="select_box">
									<span class="select_txt">学历</span><span class="selet_open"></span>
									<input type="hidden" class="val"  id="eduListVal"/>
									<div class="option w130">
										<a href="javascript:void(0)" style="display:none;" class="all">学历</a>
										<div id="eduList">
											<a href="javascript:void(0)">应届生</a>
											<a href="javascript:void(0)">大专</a>
											<a href="javascript:void(0)">本科</a>
											<a href="javascript:void(0)">研究生</a>
											<a href="javascript:void(0)">博士及以上</a>
										</div>
									</div>
								</div>
							</li>
							<li>
								<div class="select_box">
									<span class="select_txt">工作经验年限</span><span class="selet_open"></span>
									<input type="hidden" class="val" id="workExpListVal"/>
									<div class="option w144">
										<a href="javascript:void(0)" style="display:none;" class="all">工作经验年限</a>
										<div id="workExpList">
											<a href="javascript:void(0)">1年以下</a>
											<a href="javascript:void(0)">1-3年</a>
											<a href="javascript:void(0)">4-6年</a>
											<a href="javascript:void(0)">7-10年</a>
											<a href="javascript:void(0)">10年以上</a>
										</div>
									</div>
								</div>
							</li>
							
							<li>
								<div class="select_box">
									<span class="select_txt">工作意向地区</span><span class="selet_open"></span>
									<div class="option w144">
										<a href="javascript:void(0)" style="display:none;" class="all">工作意向地区</a>
										<div id="proviceList">
										</div>
									</div>
								</div>
							</li>
							
							<li>
								<div class="select_box">
									<span class="select_txt" id="cityName">户口地区</span><span class="selet_open"></span>
									<input type="hidden" class="val" id="cityListVal"/>
									<div class="option w116">
										<a href="javascript:void(0)" style="display:none;" class="all">户口地区</a>
										<div id="cityList">
										</div>
									</div>
								</div>
							</li>
							
<!-- 							<li> -->
<!-- 								<div class="select_box"> -->
<!-- 									<span class="select_txt">所学专业</span><span class="selet_open"></span> -->
<!-- 									<div class="option w172"> -->
<!-- 										<a href="javascript:void(0)" class="all">所学专业</a> -->
<!-- 										<a href="javascript:void(0)">计算机互联网专业</a> -->
<!-- 										<a href="javascript:void(0)">计算机互联网专业</a> -->
<!-- 										<a href="javascript:void(0)">计算机互联网专业</a> -->
<!-- 										<a href="javascript:void(0)">计算机互联网专业</a> -->
<!-- 									</div> -->
<!-- 								</div> -->
<!-- 							</li> -->
						</ul>
					</dt>
				</dl>
			</div>
					<!--条件选择 end-->
				</div>
				<!--operat-center end-->
				<div class="batch-select">
					<div class="op-r-pass">
						<div class="fl passBtn">
							<input type="checkbox" class="checkbox" onclick="selectAll(this);" name="" id="select-all" value="" />
							<label for="select-all" id="forSelect-all">全选</label>
						</div>
						<div class="fl totalBtn">
							<a href="javascript:void(0)" onclick="passAll();" class="pass table-btn">批量通过</a>
						</div>
					</div>
					<div class="table-lists" id="table-lists" style="width:100%;height:750px;border:red;">
<!-- 						<div id="toolbar" class="datagrid-toolbar" style="height: auto;display: none;"> -->
<!-- 						 	<fieldset id="search-tab" style="position:relative;"> -->
<!-- 									<table class="tableForm"> -->
<!-- 										<tr> -->
<!-- 											<td> -->
<!-- 												<button>批量通过</button> -->
<!-- 											</td> -->
<!-- 										</tr> -->
<!-- 									</table> -->
<!-- 							</fieldset> -->
<!-- 						</div> -->
						<table id="grid" data-options="fit:true"></table>
					</div>
				</div>
			</div>
		</div>		
		<!--operat-center end-->
		

		<!-- 		 header-start -->
		<%@ include file="/WEB-INF/pages/main/footer.jsp"%>
		<!-- 		 header-end -->
		
		<script type="text/javascript" src="<%=path%>/js/commentjs.js" ></script>
		<script type="text/javascript" src="<%=path%>/js/common-search.js" ></script>
	</body>
</html>
