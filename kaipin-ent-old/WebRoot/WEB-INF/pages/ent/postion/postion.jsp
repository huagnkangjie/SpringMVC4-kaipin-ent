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
	<script type="text/javascript" src="<%=path%>/js/position.js?v.<%=System.currentTimeMillis()%>" ></script>
<%-- 	<script type="text/javascript" src="<%=path%>/js/common-search.js" ></script> --%>

	<!-- 	Validator -->
	<script type="text/javascript" src="<%=path%>/js/formatJs.js" ></script>
	<script type="text/javascript" src="<%=path%>/js/formValidator.js" ></script>
	<script type="text/javascript" src="<%=path%>/js/formValidatorRegex.js" ></script>
	
	<style type="text/css">
		.datagrid-header-inner{width:1178px;border-left:1px solid #e8e8e8;border-top:0;}
		.datagrid-body{background:#fff;width: 1178px !important;border:1px solid #e8e8e8;border-top:0;overflow: hidden;}
	</style>
	
	<script type="text/javascript">
		
		 var datagrid ;
		 var add;
		 var statusName = "招聘中";
		 $(function (){
			 add = '${add}';
			 if(add == 'add'){
				 window.location.href="<%=path%>/position/init.do";
			 }
			 datagrid =	$('#grid').datagrid({
				url : '<%=path%>/position/datagrid.do',
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
					title : '职位名称',
					width : $(this).width() * 0.1,
					align : 'center',
					resizable : false,
					field : 'positionName'
				}, {
					title : '学历',
					width : $(this).width() * 0.1,
					align : 'center',
					resizable : false,
					field : 'educationCode',
					formatter : function(value,row,i){
						if(value == '-1'){
							return "不限";
						}else  if(value == '13000007'){
							return "硕士";
						}else  if(value == '13000002'){
							return "中技";
						}else  if(value == '13000009'){
							return "EMBA";
						}else  if(value == '13000008'){
							return "MBA";
						}else  if(value == '13000005'){
							return "大专";
						}else  if(value == '13000004'){
							return "中专";
						}else  if(value == '13000006'){
							return "本科";
						}else  if(value == '13000010'){
							return "博士";
						}else  if(value == '13000001'){
							return "初中";
						}else  if(value == '13000011'){
							return "其他";
						}else  if(value == '13000003'){
							return "高中";
						}
					}
				}, {
					title : '招聘人数',
					width : $(this).width() * 0.1,
					align : 'center',
					field : 'numbers'
				}, {
					title : '发布日期',
					width : $(this).width() * 0.1,
					align : 'center',
					resizable : false,
					field : 'lastUpdatedTime',
					formatter : function (v,rec,i){
						var st = getTimeByMillis(rec.lastUpdatedTime);
						return st;
					}
				}, {
					title : '状态',
					width : $(this).width() * 0.1,
					align : 'center',
					resizable : false,
					field : 'endTime',
					formatter : function (value,row,i){
						return statusName;
					}
				}, {
					title : '操作',
					width : $(this).width() * 0.2,
					field : '1',
					align:'center',
					resizable : false,
					formatter:function(value,row,i){
						return  "<a href='javascript:void(0);' onclick='del("+i+")' class='btn-set color-fail'>删除</a>" +
								"<a href='javascript:void(0);' onclick='edit("+i+")' class='btn-set color-operat'>编辑</a>" + 
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
					
				},
				rowStyler:function(index,row){
					var bgClass = "background-color:#F9F9F9;";
					if(index%2==0){
						bgClass = "background-color:#FFF;";
					}
					return bgClass;
				}
			});
			 
			 /* 点击顶部切换选项卡 */
			 $(".dt-infoBox li").click(function (){
				 $(this).addClass("dtr-active").siblings().removeClass("dtr-active");
				 status = $(this).find("a").data("tag");
				 if(status == '0'){
					 statusName = "招聘中";
					 location.href="<%=path%>/position/init.do";
				 }else{
					 statusName = "已过期";
				 }
				 $("#status").val(status);
				 search();
			 });
			 
			 /* 条件查询 */
			 $(".select_box").on("click","a",function(){
				 
				 search();
			 });
			 
			count(1);
			 
		});
		 
		 /* 条件查询 */
	 	function search(){
	 		 var eduVal = $("#eduListVal").val();
			 var workExpVal = $("#workExpListVal").val();
			 var status = $("#status").val();
			 var cityVal1 = $("#workAreaProvice").val();
			 var cityVal2 = $("#cityListVal").val();
			 debugger;
	 		 datagrid.datagrid('load', {
	 			educationCode : eduVal,
	 			workExperienceCode : workExpVal,
	 			cityVal1 : cityVal1,
	 			cityVal2 : cityVal2,
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
			 if(delCheck(row.id)){
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
			 }else{
				 $.fn.fandialog({
						title:"提示信息",
						contents:"选择的职位有简历投递或者笔试题，不能删除",
						click:function(ok){
							
						}
					});
			 }
			 

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
				 var ids = [];
				 for ( var i = 0; i < rows.length; i++) {
					ids.push(rows[i].id);
				 }
				 var idStr = ids.join(',');
				 if(delCheck(idStr)){
					 $.fn.fandialog({
						title:"提示信息",
						contents:"是否删除职位！",
						click:function(ok){
							if(ok){
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
						}
					});
				 }else{
					 $.fn.fandialog({
						title:"提示信息",
						contents:"选择的职位有简历投递或者笔试题，不能删除",
						click:function(ok){
							
						}
					});
				 }
				 
			 }
		 }
		 
		 /* 删除校验 */
		 function delCheck(ids){
			 var delFlag = false;
			 $.ajax({
				cache : false,
				async : false,
				type : 'POST',
				url : '<%=path%>/position/delCheck.do',
				data : {
					ids : ids
				},
				success : function (data){
					var datas = eval('('+data+')');
					if(!datas.success){
						delFlag = true;
					}
				}
			});
			return delFlag;
		 }
		 
		 
	</script>
  
		<input type="hidden" id="status" value="0"/>
		
		<!--dateTime-recruitmentInfo start-->
		<div class="dateTime-recruitmentInfo">
			<div class="dt-infoBox">
				<ul>
					<li class="dtr-active"><a href="javascript:void(0)" data-tag="0">正在招聘的职位<span id="count0">0</span></a></li>	
<!-- 					<li><a href="javascript:void(0)" data-tag="1">暂停招聘<span id="count1">0</span></a></li>	 -->
					<li><a href="javascript:void(0)" data-tag="2">过期职位<span id="count2">0</span></a></li>	
				</ul>
<!-- 				<a href="javascript:void(0);" onclick="pulish();" class="release-recruitmentBtn fr">发布职位</a> -->
			</div>
		</div>		
		<!--dateTime-recruitmentInfo end-->
		
		<!--operat-center start-->
		<div class="operat-center">
			<div class="oc-ifream">
				<div class="condition-iframeDetails">
					<!--条件选择 start-->
					<div class="basic-info-select">
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
<!-- 											<a href="javascript:void(0)">应届生</a> -->
											<c:forEach items="${eduList }" var="m">
												<a href="javascript:void(0)"  data-tag="${m.education_code }">${m.education_name }</a>
											</c:forEach>
										</div>
									</div>
								</div>
							</li>
							<li>
								<div class="select_box">
									<span class="select_txt">工作经验年限</span><span class="selet_open"></span>
									<input type="hidden" class="val"  id="workExpListVal"/>
									<div class="option w144">
										<a href="javascript:void(0)" style="display:none;" class="all">工作经验年限</a>
										<div id="workExpList">
<!-- 											<a href="javascript:void(0)">1年以下</a> -->
											<c:forEach items="${expList }" var="m">
												<a href="javascript:void(0)"  data-tag="${m.work_experience_code }">${m.work_experience_name }</a>
											</c:forEach>
										</div>
									</div>
								</div>
							</li>
							
							<li>
								<div class="select_box">
									<span class="select_txt">工作意向地区（省）</span><span class="selet_open"></span>
									<input type="hidden" class="val"  id="workAreaProvice"/>
									<div class="option w144">
										<a href="javascript:void(0)" style="display:none;" class="all">工作意向地区</a>
										<div id="workAreaListsSelect">
											<c:forEach items="${workAreaList }" var="m">
													<a href="javascript:void(0)"  data-tag="${m.location_code }">${m.location_name }</a>
												</c:forEach>
										</div>
									</div>
								</div>
							</li>
							
							<li>
								<div class="select_box">
									<span class="select_txt" id="cityName">（市）</span><span class="selet_open"></span>
									<input type="hidden" class="val" id="cityListVal"/>
									<div class="option w116">
										<a href="javascript:void(0)" style="display:none;" class="all">市</a>
										<div  id="cityList" style="height: 200px;">
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
		
		
			<div class="oc-ifream">
				<div class="batch-select">
					<div class="op-r-pass">
						<div class="fl passBtn">
							<input type="checkbox" class="checkbox" onclick="selectAll(this);" name="" id="select-all" value="" />
							<label for="select-all" id="forSelect-all">全选</label>
						</div>
						<div class="fl totalBtn">
							<a href="javascript:void(0)" onclick="passAll();" class="pass table-btn">批量删除</a>
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
	</body>
</html>
