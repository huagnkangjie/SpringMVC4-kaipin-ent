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
	<link rel="stylesheet" href="<%=path%>/css/jquery.mCustomScrollbar.css" />
	<script type="text/javascript" src="<%=path%>/js/dialog3.0.js" ></script>
	<!-- 时间 -->
	<link rel="stylesheet" href="<%=path%>/css/jquery.datetimepicker.css" />
	<script type="text/javascript" src="<%=path%>/js/jquery.datetimepicker.js"></script>
	<script type="text/javascript" src="<%=path%>/js/editjs.js" ></script>
	<!-- editor-->
	<script type="text/javascript" charset="utf-8" src="<%=path%>/js/editor/ueditor.config.js"></script>
	<script type="text/javascript" charset="utf-8" src="<%=path%>/js/editor/ueditor.all.js"> </script>
	<script type="text/javascript" charset="utf-8" src="<%=path%>/js/editor/lang/zh-cn/zh-cn.js"></script>
	
	<!-- scrollBar-->
	<script type="text/javascript" src="<%=path%>/js/scrollBar/jquery-ui-1.10.4.min.js"></script>
	<script type="text/javascript" src="<%=path%>/js/scrollBar/jquery.mousewheel.min.js"></script>
	<script type="text/javascript" src="<%=path%>/js/scrollBar/jquery.mCustomScrollbar.min.js"></script>
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
				status : '99'
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
			},/*  {
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
				formatter : function (value,row,i){
					var relationId = row.id;
					var status = show(relationId);
					return "<p calss='state-lists' onmouseover='log(event,this,"+i+")'>"+status+"</p>";
				}
			}, {
				title : '操作',
				width : $(this).width() * 0.395,
				field : '1',
				align:'center',
				resizable : false,
				formatter:function(value,row,i){
					var view_id = row.interview_id;
					var exam_id = row.exam_invite_id;
					var offer_id = row.offer_id; 
					
					var times = row.times;
					var face = row.face;
					
					var accept_status = row.accept_status;
					var pass_status = row.pass_status;
					var req_status = row.req_status;
					
					var htmlReq = "";
					var htmlAcc = "";
					var htmlOffer = "";
					var htmlExam = "";
					var htmlPassOrNo = "";
					var htmlPassOrNoSX = "";
					
					var status = row.current_status;
					
					var isLook = row.is_look;
					var isPass = row.is_pass;
					debugger;
					if(isLook == '0'){
						
					}else{
						if(isPass == '2'){
							if(view_id == null || view_id == ''){
								htmlReq = "<a href='javascript:void(0);' onclick='oper(4,"+i+")' class='btn-set color-operat'>第1次邀请1面</a>";
								if(exam_id == null || exam_id == ''){
									htmlExam = "<a href='javascript:void(0);' onclick='reqExam("+i+")' class='btn-set color-operat'>邀请笔试</a>";
								}
							}else{
								/* if(status == '4'){
									htmlAcc = "<a href='javascript:void(0);' onclick='oper(5,"+i+")' class='table-opreat-btn blue-btn'>接受第"+times+"次邀请"+face+"面</a>" +
			 						"<a href='javascript:void(0);' onclick='oper(6,"+i+")' class='table-opreat-btn blue-btn'>拒绝第"+times+"次邀请"+face+"面</a>";
								} */
								if(status == '5'){
									if(pass_status == null || pass_status == ''){
										htmlPassOrNo = "<a href='javascript:void(0);' onclick='oper(7,"+i+")' class='btn-set color-pass'>通过"+face+"面</a>" + 
										"<a href='javascript:void(0);' onclick='oper(8,"+i+")' class='btn-set color-fail'>不通过"+face+"面</a>";
									}
								}
								if(status == '6'){
									htmlReq = "<a href='javascript:void(0);' onclick='oper(4,"+i+")' class='btn-set color-operat'>第"+(times+1)+"次邀请"+face+"面</a>";
								}
								if(status == '7'){
									if(offer_id == null || offer_id == ''){
										htmlReq = "<a href='javascript:void(0);' onclick='oper(4,"+i+")' class='btn-set color-operat'>第1次邀请"+(face + 1)+"面</a>";
										htmlOffer = "<a href='javascript:void(0);' onclick='offer("+i+")' class='btn-set color-pass'>发送offer</a>";
									}
								}
								if(status == '8' || status == '9'){
									htmlAcc = ""; htmlReq = ""; htmlExam = ""; htmlPassOrNo = ""; htmlOffer ="";
								}
							}
						}else if(isPass == '0'){
							htmlPassOrNoSX = "<a href='javascript:void(0);' onclick='passOrNo(2,"+i+")' class='btn-set color-pass'>通过</a>" + 
					        		"<a href='javascript:void(0);' onclick='passOrNo(3,"+i+")' class='btn-set color-fail'>不通过</a>";
						}else if(isPass == '3'){
							htmlPassOrNoSX = "<a href='javascript:void(0);' onclick='passOrNo(2,"+i+")' class='btn-set color-pass'>通过</a>";
						}
					}
					
					var htmlEnd = "<a href='javascript:void(0);' onclick='detail(1,"+i+");' class='btn-set color-operat'>查看</a>";
					return htmlPassOrNoSX + htmlAcc + htmlReq + htmlExam + htmlPassOrNo + htmlOffer + htmlEnd;
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
					if(postionName == 'undefined'){
						postionName == "职位";
					}
					$("#iv-position").empty();
					$("#iv-position").append(postionName);
					$("#postion").append(postionName);
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
		 
		 /* 影藏log日志 */
		 $("#state-of-details").mouseover(function(event){
			 event.stopPropagation();
		 });
		 $("body").mouseover(function(event){
			 $("#state-of-details").hide();
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
	 			status : 'all',
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
	 
	 /*日志*/
	 function log(event,e,index){
		 event.stopPropagation();
		 var row = $("#grid").datagrid("getRows")[index];
		 $.ajax({
			 cache :false,
			 async :false,
			 url : '<%=path%>/resume/log.do',
			 data : {
				 relationId : row.id,
				 oper : 'log'
			 },
			 success : function(data){
				 var datas = eval('('+data+')');
				 if(datas.success){
					 if(datas.obj.length > 0){
						 var html = "";
						 for(var i = 0; i < datas.obj.length; i++){
							 var time = getTimeByMillis(datas.obj[i].create_time);
							 html = html + "<p><span>"+datas.obj[i].content+"</span><br><em>"+time+"</em></p>";
						 }
						 $("#state-cons").empty();
						 $("#state-cons").append(html);
					 }
				 }
			 }
		 });
		 $("#state-of-details").show();
		 var x = 0,y = 0;
		 x = e.offsetLeft;   
		 y = e.offsetTop;  
		 var height = $("#state-of-details").height();
		 var width = $("#state-of-details").width();
		 var w_y = $(window).height();
	     while(e=e.offsetParent)
	     {
	       x   +=   e.offsetLeft;  
	       y   +=   e.offsetTop;
	     }
	     if((w_y - y) > height){
	    	 $("#state-of-details").css({
					"top": y, 
					"left":x - width + 20
				});
	    	 $("#state-of-details").find(".state-icon").css("top",1);
	    	 
	     }else{
	     	 $("#state-of-details").css({
	 		    	"top":y - height,
	 		    	"left":x - width + 20
	 		    }); 
	     	$("#state-of-details").find(".state-icon").css("top",194);
	     }
	 }
	 
	 /*******/
	 /*oper 邀请  通过 不通过  */
	 function oper(oper,index){
		 $("#state-cons").mCustomScrollbar();
		 var row = $("#grid").datagrid("getRows")[index];
		 $("#rowIndex").val(index);
		 $("#oper").val(oper);
//		 var data = JSON.stringify(row);
		 if(oper == 4){
			 req();
		 }else{
			 $.ajax({
	 			 cache : false,
	 			 async : false,
	 			 contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
				 url : '<%=path%>/resume/oper.do',
	 			 data : {
	 				 id : row.id,
	 				 status : oper
	 			 },
	 			 success : function(data){
	 				 datagrid.datagrid('reload');
	 			 }
	 		 });
		 }
	 }
	 
// 	 /*显示日志*/
// 	 function log(index){
// 		 var row = $("#grid").datagrid("getRows")[index];
// 		 $.ajax({
// 			 cache :false,
// 			 async :false,
<%-- 			 url : '<%=path%>/resume/log.do', --%>
// 			 data : {
// 				 resumeId : row.resume_id
// 			 },
// 			 success : function(data){
// 				 var datas = eval('('+data+')');
// 				 if(datas.success){
// 					 if(datas.obj.length > 0){
// 						 var html = "";
// 						 for(var i = 0; i < datas.obj.length; i++){
// 							 html = html + datas.obj[i].create_time + "  :  " + datas.obj[i].detail + "</br>";
// 						 }
// 	 					 $("#log").empty();
// 	 					 $("#log").append(html);
// 					 }
// 				 }
// 			 }
// 		 });
// 	 }
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
	 function detail(times,index){
		 datagrid.datagrid('unselectAll');
		 var row = $("#grid").datagrid("getRows")[index];
		 var resumeName = row.resume_name;//简历名字
		 var resumeId = row.resume_id;//简历id
		 var relationId = row.id;
		 var userId = row.user_id;
		 var status = row.status;
		 location.href="<%=path%>/resumeDetail/init.do?status="+status+"&resumeId="+resumeId+"&relationId="+relationId+"&userId="+userId;
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
	 /*邀请面试*/
	 function req(){
		 $("#interview-panel-overlay").show();
		 $("#interview-panel").show();
		 $("#iv-timer").val("");
		 $("#remarksNum").val("");
	 }
	 function reqBtn(){
		 var index = $("#rowIndex").val();
		 var oper = $("#oper").val();
		 $("#reqBtn").hide();
		 $("#reqBtnSubmit").show();
		 setTimeout(function () {
			 reqAjax(oper,index);
		 },200);
	 }
	 function reqAjax(oper,index){
		 var row = $("#grid").datagrid("getRows")[index];
		 var faceTime = $("#iv-timer").val();
		 if(faceTime.length == 0){
			 alert("请填写面试时间 ");
			 $("#reqBtn").show();
			 $("#reqBtnSubmit").hide();
			 return;
		 }
		 var faceType = $("#faceType").val();
		 var memo = $("#remarksNum").val();
		 $.ajax({
			 cache : false,
			 async : false,
			 url : '<%=path%>/resume/oper.do',
			 type : 'POST',
			 contentType: "application/x-www-form-urlencoded; charset=UTF-8",
			 data : {
				 id : row.id,
				 status : oper,
				 faceTime : faceTime,
				 memo : memo
			 },
			 success : function(data){
				 $("#reqBtn").show();
				 $("#reqBtnSubmit").hide();
				 $("#interview-panel-overlay").hide();
				 $("#interview-panel").hide();
				 datagrid.datagrid('reload');
			 }
		 });
	 }
	 
	 /*邀请笔试
     判断是否存在笔试题 */
 function reqExam(index){
	 var row = $("#grid").datagrid("getRows")[index];
	 $.ajax({
		 cache : false,
		 async : false,
		 url : '<%=path%>/examManager/checkExamPaper.do',
		 type : 'POST',
		 contentType: "application/x-www-form-urlencoded; charset=UTF-8",
		 data : {
			 positionId : row.position_id
		 },
		 success : function(data){
			 var datas = eval('('+data+')');
			 if(datas.success){
				 $.fn.fandialog({
					title:"提示信息",
					contents:"请创建试卷",
					click:function(ok){
						if(ok){
							window.location.href="<%=path%>/examManager/init.do"
						}
					}
				});
			 }else{
				 var status = datas.obj;
				 if(status == '1'){
					 doReqExam(index);
					 datagrid.datagrid('reload');
				 }else if(status == '0'){
					 alert("该试卷已被禁用");
				 }
			 }
		 }
	 });
 }

function doReqExam(index){
	var row = $("#grid").datagrid("getRows")[index];
	$.ajax({
		 cache : false,
		 async : false,
		 url : '<%=path%>/examManager/reqExam.do',
		 type : 'POST',
		 contentType: "application/x-www-form-urlencoded; charset=UTF-8",
		 data : {
			 positionId : row.position_id,
			 resumePositionId : row.id
		 },
		 success : function(data){
			 datagrid.datagrid('reload');
		 }
	 });
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
<!-- 					<div class="op-r-pass"> -->
<!-- 						<div class="fl passBtn"> -->
<!-- 							<input type="checkbox" class="checkbox" onclick="selectAll(this);" name="" id="select-all" value="" /> -->
<!-- 							<label for="select-all" id="forSelect-all">全选</label> -->
<!-- 						</div> -->
<!-- 						<div class="fl totalBtn"> -->
<!-- 							<a href="javascript:void(0)" onclick="passAll();" class="pass table-btn">批量通过</a> -->
<!-- 						</div> -->
<!-- 					</div> -->
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

						<!--笔试面试状态查看列表 开始-->
						<div class="state-of-details" id="state-of-details" style="height:200px">
							<span class="state-icon" style="top:1px;"></span>
							<div class="state-cons mCustomScrollbar" id="state-cons">
<!-- 								<p><span>投递1111111111111简历投递s</span><br><em>2015年10月26日18:38:41</em></p> -->
<!-- 								<p><span>卡时间的</span><br><em>2015年10月26日18:38:41</em></p> -->
<!-- 								<p><span>阿萨德擦拭的才是</span><br><em>2015年10月26日18:38:41</em></p> -->
<!-- 								<p><span>asdcasd</span><br><em>2015年10月26日18:38:41</em></p> -->
							</div>
						</div>
						<!--笔试面试状态查看列表 结束-->

						<table id="grid" data-options="fit:true"></table>
					</div>
				</div>
			</div>
		</div>		
		<!--operat-center end-->
		
		
		<!--邀请面试  开始-->
		<form method="POST" action="<%=path%>/resumePassOrNoController/reqView.do" id="reqViewForm" name="reqViewForm">
			<div class="tzui-loading-overlay" id="interview-panel-overlay" style="display: none;"></div>
			<div class="interview-panel radius" id="interview-panel" style="display: none;">
				<div class="iv-title">
					<h3 id="reqName">邀请面试</h3>
					<a href="javascript:void(0)" class="close-itrView closeAllStyle"></a>
				</div>
				<!--影藏数据-->
				<input type="hidden" id="rowIndex"/>
				<input type="hidden" id="oper"/>
				<div class="iv-cons-lists">
					<div class="iv-select">
						<span class="name-tips fl">职位</span>
						<div class="iv-select-cons iv-select-posi fl" id="iv-position">
							面试职位
							<input type="hidden" name="postion" id="postion"/>
						</div>
					</div>
					
					<div class="iv-select">
						<span class="name-tips fl">时间</span>
						<div class="iv-timer fl"><input type="text" id="iv-timer" name="faceTime" readonly="readonly"></div>
					</div>
					
<!-- 					<div class="iv-select"> -->
<!-- 						<span class="name-tips fl">方式</span> -->
<!-- 						<div class="iv-select-cons fl" id="iv-way"> -->
<!-- 							<input type="hidden" name="faceType" id="faceType" value="0" class="input-val"/> -->
<!-- 							<span class="titleActive">网络面试</span> -->
<!-- 							<span class="selet_sj sanjiao"></span> -->
<!-- 							<div class="select-details"> -->
<!-- 								<a href="javascript:void(0)" data-title="0">网络面试</a> -->
	<!-- 							<a href="javascript:void(0)">网络笔试</a> -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 					</div> -->
					
					<div class="iv-select iv-remark">
						<span class="name-tips fl">备注</span>
						<textarea class="remarks" id="remarksNum" name="memo" data-count="50" ></textarea>
						<span class="limit-number" id="limit-number"><span>0</span>/50</span>
					</div>
				</div>
				<div class="iv-sureBtn">
					<a href="javascript:void(0)" id="reqBtn" onclick="reqBtn();" class="allSureBtnstyle" >确定</a>
					<a href="javascript:void(0)" id="reqBtnSubmit" style="display:none;background:#ececec;color:#c4c4c4;" class="allSureBtnstyle" >提交中</a>
				</div>
			</div>
		</form>
		<!--邀请面试  结束-->
		
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
						<input type="text" id="offerTheam" value="2015校园招聘录用通知书"/>
					</div>
					<div class="offer-editPanel">
						<div class="cons-details" id="send-offerCons" style="font:12px/22px '微软雅黑','microsoft yahei';color:#565656;"></div>
					</div>			
				</div>
				<div class="offer-button">
						<a href="javascript:void(0)" class="allSureBtnstyle offer-suer" id="offer-suer">确定</a>
						<a href="javascript:void(0)" style="display:none;background:#ececec;color:#c4c4c4;" class="allSureBtnstyle offer-suer" id="offer-suer-submit">发送中</a>
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
			/**发送offer*/
			var name = "XX先生/女士";
			var sendOfferCons;
			
			sendOfferCons = textareaEditor(sendOfferCons,"send-offerCons");//发送offer
			function textareaEditor(objNmae,obj){
				objNmae = UE.getEditor(obj,{
				 	toolbars:[],/*工具栏设置*/
				 	wordCount:false,/*字数统计*/
			    	contextMenu:[],/*屏蔽右键菜单*/
			    	elementPathEnabled:false,/*标签结构*/
			    	autoHeightEnabled:false,/*自动升高*/
			    	initialFrameWidth:540,/*宽度*/
			    	initialFrameHeight:240,/*高度*/
			    });
			    return objNmae;
			}
			
			function offer(index){
				var row = $("#grid").datagrid("getRows")[index];
				$.ajax({
					 cache : false,
					 async : false,
					 url : '<%=path%>/resumeOfferController/checkOffer.do',
					 type : 'POST',
					 data : {
						 relationId : row.id
					 },
					 success : function(data){
						 var datas = eval('('+data+')');
						 if(datas.success){
							    name = row.surname + row.miss_surname;
								$("#send-offer-dialog-overlay").show();
							 	$("#send-offer-dialog").show();
							 	
							 	var contents = "<p>"+name+" :</p>"+
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
									
								sendOfferCons.setContent(contents);
								
								$("#offer-canle").click(function(){
									$("#send-offer-dialog").hide();	
									$("#send-offer-dialog-overlay").hide();
								});
								
								$("#send-offer-dialog").find(".close-sendOffer").click(function(){
									$("#send-offer-dialog").hide();	
									$("#send-offer-dialog-overlay").hide();
								});
								$("#offer-suer").click(function () {
									var offerContent = sendOfferCons.getContent();
									var offerTitle = $("#offerTheam").val();
									if(offerContent.length == 0){
										alert("请填写邮件内容");
										return;
									}
									if(offerTitle.length == 0){
										alert("请填写邮件标题");
										return;
									}
									var row_offer = $("#grid").datagrid("getRows")[index];
									$("#offer-suer").hide();
									$("#offer-suer-submit").show();
									setTimeout(function(){
										$.ajax({
											 cache : false,
											 async : false,
											 url : '<%=path%>/resumeOfferController/sendOffer.do',
											 type : 'POST',
											 contentType: "application/x-www-form-urlencoded; charset=UTF-8",
											 data : {
												 raltionId : row_offer.id,
												 companyId : row_offer.company_id,
												 resumeId : row_offer.resume_id,
												 postionId : row_offer.postion_id,
												 userId : row_offer.user_id,
												 offerContent : offerContent,
												 offerTitle : offerTitle,
												 email : row_offer.email
											 },
											 success : function(data){
												 var datas = eval('('+data+')');
												 $("#send-offer-dialog").hide();	
												 $("#send-offer-dialog-overlay").hide();
												 $("#offer-suer").show();
												 $("#offer-suer-submit").hide();
												 debugger;
												 if(datas.success){
													 var tip = datas.obj.tip_push + "<br>" + datas.obj.tip_mail;
													 $.fn.fandialog({
															title:"提示信息",
															contents:tip,
															click:function(ok){
															}
														});
													 $("#grid").datagrid('reload');
												 }else{
													 $.fn.fandialog({
															title:"提示信息",
															contents:tip,
															click:function(ok){
															}
														});
													 $("#grid").datagrid('reload');
												 }
											 },error:function(){
												 alert("发送失败");
												 $("#offer-suer").show();
												 $("#offer-suer-submit").hide();
											 }
										 });
									},200);
									$("#offer-suer").off("click");
								});
						 }else{
							 $.fn.fandialog({
								title:"提示信息",
								contents:"该学生未设置邮箱，不能发送offer",
								click:function(ok){
								}
							});
						 }
					 }
				 });
			}
		</script>
		<script type="text/javascript" src="<%=path%>/js/common-search.js" ></script>
	</body>
</html>
