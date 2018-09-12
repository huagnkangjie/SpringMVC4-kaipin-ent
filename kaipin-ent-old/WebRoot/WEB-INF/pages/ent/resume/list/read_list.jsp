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
	<!-- 系统 -->
	<script type="text/javascript" src="<%=path%>/js/dialog3.0.js" ></script>
	<!-- editor-->
	<script type="text/javascript" charset="utf-8" src="<%=path%>/js/editor/ueditor.config.js"></script>
	<script type="text/javascript" charset="utf-8" src="<%=path%>/js/editor/ueditor.all.js"> </script>
	<script type="text/javascript" charset="utf-8" src="<%=path%>/js/editor/lang/zh-cn/zh-cn.js"></script>

	<style type="text/css">
		.datagrid-header-inner{width:1178px;border-left:1px solid #e8e8e8;border-top:0;}
		.datagrid-body{background:#fff;width: 1178px !important;border:1px solid #e8e8e8;border-top:0;overflow: hidden;}
	</style>
	
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
					status : '1'
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
				}, /* {
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
				},  */{
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
				}, /* {
					title : '工资要求',
					width : $(this).width() * 0.06,
					align : 'center',
					resizable : false,
					field : 'salary'
				},  */{
					title : '简历状态',
					width : $(this).width() * 0.1,
					align : 'center',
					resizable : false,
					field : 'status',
					formatter:function(value,row,i){
						var relationId = row.id;
						var status = show(relationId);
						return status;
					}
				}, {
					title : '操作',
					width : $(this).width() * 0.395,
					field : '1',
					align:'center',
					resizable : false,
					formatter:function(value,row,i){
						var is_pass = row.is_pass;
						var passHtml = "";
						var noPassHtml = "";
						var detailHtml = "";
						if(is_pass == 0){
							passHtml = "<a href='javascript:void(0);' onclick='passOrNo(2,"+i+")' class='btn-set color-pass'>通过</a>" + 
					        		   "<a href='javascript:void(0);' onclick='passOrNo(3,"+i+")' class='btn-set color-fail'>不通过</a>";
						}else if(is_pass == 2){
							noPassHtml = "<a href='javascript:void(0);' onclick='passOrNo(3,"+i+")' class='btn-set color-fail'>不通过</a>";
						}else if(is_pass == 3){
							passHtml = "<a href='javascript:void(0);' onclick='passOrNo(2,"+i+")' class='btn-set color-pass'>通过</a>";
						}
						detailHtml = "<a href='javascript:void(0);' onclick='detail("+i+");' class='btn-set color-operat'>查看</a>";
						
						return  passHtml + noPassHtml + detailHtml;
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
		 			status : '1',
		 			postionId : postionId
				});
		 	}
		 
		 	/* 显示状态 */
		 	function show(relationId){
		 		var status = "";
		 		$.ajax({
					 cache :false,
					 async :false,
					 url : '<%=path%>/resume/log.do',
					 data : {
						 relationId : relationId,
						 oper : 'show'
					 },
					 success : function(data){
						 var datas = eval('('+data+')');
						 if(datas.success){
							 status = datas.obj.showMsg;
						 }
					 }
				 });
		 		return status;
		 	}
		 
		 /**
			 *通过
			 */
			 function passOrNo(oper,index){
				 datagrid.datagrid('unselectAll');
				 var row = $("#grid").datagrid("getRows")[index];
				 var resumeName = row.resume_name;
				 var ids = row.id;//投递简历关系表主键
				 var passOrNo ;
				 if(oper == '2'){
					 passOrNo = "通过【"+resumeName+"】筛选";
				 }else{
					 passOrNo = "不通过【"+resumeName+"】筛选";
				 }
				 $.fn.fandialog({
					title:"提示信息",
					contents:passOrNo,
					click:function(ok){
						if(ok){
							doPassOrNo(ids, oper);
						}
					}
				});
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
			 var relationId = row.id;
			 var userId = row.stu_user_id;
			 var status = row.is_look;
			 var postionId = row.position_id;
			 location.href="<%=path%>/resumeDetail/init.do?status=1&resumeId="+resumeId+"&relationId="+relationId+"&userId="+userId+"&postionId="+postionId;
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
				 var ids = [];
				 for ( var i = 0; i < rows.length; i++) {
						ids.push(rows[i].id);
					} 
				 doPassOrNo(ids.join(','),"2");
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
		
		<!--发送offer 开始-->
		<div class="tzui-loading-overlay" id="send-offer-dialog-overlay" style="display: none;"></div>
		
		<div class="send-offer-dialog" id="send-offer-dialog" style="display: none;">
			<form>
				<div class="offer-title">
					<h3>发放offer</h3>
					<a href="javascript:void(0)" class="close-sendOffer closeAllStyle"></a>
				</div>
				<div class="offer-cons">
					<div class="cons-title">
						<input type="text" value="2015校园招聘录用通知书-姓名"/>
					</div>
					<div class="cons-details" id="send-offerCons" style="font:12px/22px '微软雅黑','microsoft yahei';color:#565656;"></div>			
				</div>
				<div class="offer-button">
						<a href="javascript:void(0)" class="allSureBtnstyle offer-suer" id="offer-suer">确定</a>
						<a href="javascript:void(0)" class="allCloseBtnstyle" id="offer-canle">取消</a>
				</div>
			</form>
		</div>
		<!--发送offer 介绍-->

		<!-- 		 header-start -->
		<%@ include file="/WEB-INF/pages/main/footer.jsp"%>
		<!-- 		 header-end -->
		
		<script type="text/javascript" src="<%=path%>/js/commentjs.js" ></script>
		<script type="text/javascript">
			var contents = "<p> XX先生/女士：</p>"+
			"<p>非常荣幸的通知您，由于您出众的专业能力和优秀的综合素质，已经通过公司的面试考核，成为公司的一员，您将入职XX公司XX部门担任XX职位，我们对您加入XX大家庭表示热烈的欢迎。"+
			"</p>"+
				"<p>"+
			"一、请于XX年XX月XX日XX点XX分，到公司人力资源部办理报到手续，公司地址：XXXXXXX，人力资源部电话：XXXXXX，联系人：XXXX。  <br>"+
			"二、请您在办理入职手续时，提供以下资料：<br>"+
			"　1.居民身份证原件，外地户籍还需提供居住证原件；<br>"+  
			"　2.最高学历证书及学位证原件； <br>"+
			"　3.专业技术职称证书原件、职业资格证书原件、上岗证书原件；   <br>"+
			"　4.前一家公司离职证明原件；<br>"+
			"</p>"+
						"<span style='clear:both'></span><p style='float:right;margin-right:20px;'>X年X月X日</p>";
			
			var sendOfferCons = UE.getEditor("send-offerCons",{
						toolbars:[],/*工具栏设置*/
					 	wordCount:false,/*字数统计*/
				    	contextMenu:[],/*屏蔽右键菜单*/
				    	elementPathEnabled:false,/*标签结构*/
				    	autoHeightEnabled:false,/*自动升高*/
				    	initialFrameWidth:240,/*宽度*/
				    	initialFrameHeight:540/*高度*/
			        });
			sendOfferCons.setContent(contents);
			
			$("#send-offer-dialog").find(".close-sendOffer").click(function(){
				$("#send-offer-dialog").hide();	
				$("#send-offer-dialog-overlay").hide();
			});
		</script>
		<script type="text/javascript" src="<%=path%>/js/common-search.js" ></script>
	</body>
</html>
