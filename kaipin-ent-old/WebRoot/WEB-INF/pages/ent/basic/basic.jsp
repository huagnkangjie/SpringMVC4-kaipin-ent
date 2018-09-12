<!-- header-start -->
<%@ include file="/WEB-INF/pages/main/header.jsp"%>
<!-- header-end -->

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	<!-- 弹窗 -->
	<link rel="stylesheet" href="<%=path%>/css/dialog.css" />
	<!-- uploadify -->
	<link rel="stylesheet" type="text/css" href="<%=path%>/js/uploadify/Huploadify.css"/>
<%-- 	<script type="text/javascript" src="<%=path%>/js/uploadify/jquery.js"></script> --%>
	<script type="text/javascript" src="<%=path%>/js/uploadify/jquery.Huploadify.js"></script>
	<!-- 系统 -->
	<link rel="stylesheet" type="text/css" href="<%=path%>/js/image/css/cropper.css"/>
	<script type="text/javascript" src="<%=path%>/js/image/bootstrap.min.js"></script>
	<!-- 图片裁剪 -->
	<script type="text/javascript" src="<%=path%>/js/image/cropper.js?v.<%=System.currentTimeMillis()%>"></script>
	<script type="text/javascript" src="<%=path%>/js/image/imgcut.js?v.<%=System.currentTimeMillis()%>"></script>
	<!--上传logo-->
	<script type="text/javascript" src="<%=path%>/js/image/logo.js?v.<%=System.currentTimeMillis()%>"></script>
	<!--上传宣讲会封面-->
	<script type="text/javascript" src="<%=path%>/js/image/xjhImg.js?v.<%=System.currentTimeMillis()%>"></script>
	<!--上传宣讲会历史 封面和视频-->
	<script type="text/javascript" src="<%=path%>/js/image/upHistory.js?v.<%=System.currentTimeMillis()%>"></script>
	<!-- 视频播放 -->
	<link rel="stylesheet" type="text/css" href="<%=path%>/js/vedio/video-js.css"/>
	<script type="text/javascript" src="<%=path%>/js/vedio/video.js" ></script>
	<script type="text/javascript">
		    videojs.options.flash.swf = "<%=path%>/js/vedio/video-js.swf";
	</script>
	
	<!-- Validator -->
	<script type="text/javascript" src="<%=path%>/js/formatJs.js" ></script>
	<script type="text/javascript" src="<%=path%>/js/formValidator.js" ></script>
	<script type="text/javascript" src="<%=path%>/js/formValidatorRegex.js" ></script>
	<script type="text/javascript" src="<%=path%>/js/basicValidator.js"></script>
	<!-- 截图插件 -->
	<link href="<%=path%>/js/imgareaselect/css/imgareaselect-default.css" rel="stylesheet" type="text/css" />
    <script src="<%=path%>/js/imgareaselect/jquery.imgareaselect.pack.js" type="text/javascript"></script>
	<script type="text/javascript">
	
		var area1;
		var area1Code;
		var area2;
		var area2Code;
		
		$(function (){
			
			area1 = '${area1}';
			area1Code = '${area1Code}';
			area2 = '${area2}';
			area2Code = '${area2Code}';
			
			$("#officeArea").val(area1Code);
			
			$("#send-preachBtn").show();
			
			var bg = '${bg }';
			
			if(bg != undefined){
				if(bg != null && bg != ''){
					$("#change-background-img").css({"background-image":"url("+bg+")","filter":"progid:DXImageTransform.Microsoft.AlphaImageLoader(src='"+bg+"',sizingMethod='scale'\9"});
					$("#bg_url").val(bg);
				}	
			}
			
			//*********************************************
			//初始化查询方法
			//checkCertificate();//检查资质认证
			editPage();
			xjhList(1,1);
			getResumeList();
			getFollowList(8);
			var followCounts = getFollowCount();
			getCountPosition(1);
			checkCertificate();
			$("#followCount").html(followCounts);
			//*********************************************
			
			/* 编辑企业名称  */
			var editEnterprise= $("#edit-enterprise");
			var ewpEditCompany = $(".ewp-edit-company");
			var $cancleBtn = $("#edit-enterprise").parents(".edit-window-panel").find(".cancle-btn");
			
			editWinShow(editEnterprise,ewpEditCompany,1);
			editWinHide($cancleBtn,editEnterprise,ewpEditCompany);
			
			/* 编辑企业简介 */
			var editCompanyInfo = $("#edit-company-info");
			var panelWinDefaulte = $("#panel-win-defaulte");
			var $cancleCompanyInfo = $("#profile-full-details").find(".cancle-btn");
			
			editWinShow(editCompanyInfo,panelWinDefaulte,2);
			editWinHide($cancleCompanyInfo,editCompanyInfo,panelWinDefaulte);
			
			/* 公用显示  */
			function editWinShow($obj,$panel,no){
				
				$obj.click(function(){
					var open = $obj.data("open");
					if(open=="open"){
						switch(no){
							case 1 : editPage(); break;
							case 2 : deitEntDetail(); break;
						}  
						$obj.data("open","close");
						$panel.show();	
					}else{
						$obj.data("open","open");
						$panel.hide();	
					}
				});
			}
			/* 公用关闭按钮 */
			function editWinHide($obj,$flag,$panel){
				$obj.click(function(){
					$flag.data("open","open");
					$panel.hide();
				});
			}
			
			/* 公共保存关闭更改状态 */
			function saveClose($flag,$panel){
				$flag.data("open","open");
				$panel.hide();
			}
			
			
			/* 打开第一个编辑页面  */
			function editPage(){
				$.ajax({                
					cache: true,                
					type: "POST",                
					url:  '<%=path%>/basicConctroller/detail.do', 
					async: true,                
					error: function(request) {                    
					},                
					success: function(data) {
						var datas = eval('('+data+')');
						
						$("#entName").val(datas.obj.entName);
						$("#ent_all_name").html(datas.obj.entName);
						$("#personalEntName").html(datas.obj.entName);
						$("#industryType").val(datas.obj.industryCode);
						$("#numbers").val(datas.obj.peopleNumber);
						$("#entSimpleName").val(datas.obj.entSimpleName);
						var $txt1 = datas.obj.industryCode;
						if($txt1==null || $txt1==""){
							$("#ent_type").css("display","none");
						}else{
							$("#ent_type").css("display","block");
							//$("#ent_type").html(datas.obj.industryCode);
						}
						$("#ent_name").html(datas.obj.entSimpleName);
						
						var menbers = datas.obj.peopleNumber;
						if(menbers != null && menbers != ''){
							$("#ent_menbers").html("已有"+menbers+"员工");
						}
						
						
						if(datas.obj.detail != null && datas.obj.detail != ''){
							var detail = datas.obj.detail;
							if(detail != null && detail != ''){
								if(detail.length > 280){
									$("#detail33").html(detail.substring(0,280)+"...");
								}else{
									$("#detail33").html(detail);
								}
							}  
							$("#detail3").html(detail);
							$("#detail3").hide();
							$("#followArea3").html(datas.obj.businessDomain);
							$("#website3").html(datas.obj.website);
 							$("#entType3").html(datas.obj.companyTypeCode);
							$("#officeAddress3").html(datas.obj.officeAddress);
							$("#regeditTime3").html(datas.obj.regeditDate);
							var menbers = datas.obj.peopleNumber;
							if(menbers != null && menbers != ''){
								menbers = menbers+"人";
							}
							$("#peopleNumber3").html(menbers);
							$("#noCotent").hide();
							$("#profile-full-details").show();
							$("#spread-out").show();
						}
					}            
				});
			}
			/* 企业名称保存 */
			$("#saveOne").click(function (){
				var entNameStr = $("#entName").val();
				if(entNameStr.length == 0){
					alert("请填写企业名称");
					return;
				}else if(entNameStr.length > 25){
					alert("企业名称太长");
					return;
				}
				$.ajax({                
					cache: true,                
					type: "POST",                
					url:  '<%=path%>/basicConctroller/edit.do',                
					data:$('#form1').serialize(),// 你的formid                
					async: true,                
					error: function(request) {                    
					},                
					success: function(data) {
						var datas = eval('('+data+')');
						var entNameStr = datas.obj.entName;
						if(entNameStr != null || entNameStr != ""){
							var length = entNameStr.length;
							$("#personalEntName").html(entNameStr );
							if(isNotEmpty(entNameStr)){
								if(entNameStr.length > 20){
									entNameStr = entNameStr.substring(0, 20) + "...";
								}
							}
							$("#entNameHistroyAdd").html(entNameStr );
							$("#entNameXjhAdd").html(entNameStr );
							$("#entNameHistroyEdit").html(entNameStr );
							$("#entNameXjhEdit").html(entNameStr );
							if(length > 10){
								//$("#header_ent_all_name").attr("title",entNameStr);
								entNameStr = entNameStr.substring(0,10) + "...";
								//$("#header_ent_all_name").html(entNameStr );
							}else{
								//$("#header_ent_all_name").attr("title",entNameStr);
								//$("#header_ent_all_name").html(entNameStr );
							}
						}
						$(".ewp-edit-company").hide();
						$("#ent_all_name").html(datas.obj.entName);
						$("#ent_name").html(datas.obj.entSimpleName);
						var $txt1 = datas.obj.industryCode;
						if($txt1==null || $txt1==""){
							$("#ent_type").css("display","none");
						}else{
							var text = $("#industryType").find("option:selected").text();
							$("#ent_type").css("display","block");
// 							$("#ent_type").html(datas.obj.industryCode);
 							$("#ent_type").html(text);
						}
						var menbers = datas.obj.peopleNumber;
						if(menbers != null && menbers != ''){
							$("#ent_menbers").html("已有"+menbers+"员工");
						}else{
							$("#ent_menbers").html("已有0员工");
						}
						saveClose(editEnterprise,ewpEditCompany);
					}            
				});
			});
			
			/* 第一次打开编辑企业简介 */
			$("#rightOfedit-comInfo").click(function (){
				$("#edit-company-details").show();
				$("#tzui-loading-overlay").show();
			});
			$("#close-company-details").click(function (){
				$("#edit-company-details").hide();
				$("#tzui-loading-overlay").hide();
			});
			$("#edit-first").click(function (){
				$("#edit-company-details").hide();
				$("#tzui-loading-overlay").hide();
			});
			/* 企业简介保存 */
			$("#save-first").click(function (){
				var detail = $("#entdetail").val();
				if(detail.trim().length == 0){
					alert("请填写企业简介");
					return;
				}else{
					if(detail.length > 2500){
						alert("企业简介字数超过2500");
						return;
					}
				}
				var followArea2 = $("#followArea").val();
				if(followArea2.trim().length == 0){
					alert("请填写关注领域");
					return;
				}else{
					if(followArea2.length > 100){
						alert("关注领域字数超过100");
						return;
					}
				}
				var companyType = $("#entType").val();
				if(companyType.length == 0){
						alert("请选择企业性质");
						return;
				}
				var website2 = $("#website").val();
				if(website2.length > 50){
						alert("网站长度不能超过50个字符");
						return;
				}
				var regeditTime2 = $("#regeditTime").val();
				if(regeditTime2.length > 10){
						alert("注册时间不能超过10个字符");
						return;
				}
				var officeAddress2 = $("#officeAddress").val();
				if(officeAddress2.length > 100){
						alert("地址不能超过100个字符");
						return;
				}
				var companyArea = $("#sa-city").val();
				if(!isNotEmpty(companyArea)){
					alert("请选择公司所在地区");
					return;
				}
				var peopleNuber = $("#peopleNumber").val();
				if(!isNotEmpty(peopleNuber)){
					alert("请填写公司规模");
					return;
				}
				var text = $("#entType").find("option:selected").text();
				$.ajax({                
					cache: true,                
					type: "POST",                
					url:  '<%=path%>/basicConctroller/edit.do',                
					data:$('#form2').serialize(),// 你的formid                
					async: true,                
					error: function(request) {                    
					},                
					success: function(data) {
						$("#edit-company-details").hide();
						$("#tzui-loading-overlay").hide();
						$("#noCotent").hide();
						$("#profile-full-details").show();
						var datas = eval('('+data+')');
						if(datas.success){
							var detail = datas.obj.detail;
							if(detail != null && detail != ''){
								
								if(detail.length > 280){
									$("#detail33").html(detail.substring(0,280)+"...");
								}else{
									$("#detail33").html(detail);
								}
							}
							$("#detail3").html(datas.obj.detail);
							$("#detail3").hide();
							$("#website3").html(datas.obj.website);
							$("#entType3").html(text);
							$("#followArea3").html(datas.obj.businessDomain);
							$("#officeAddress3").html(datas.obj.officeAddress);
							$("#regeditTime3").html(datas.obj.regeditDate);
							
							var menbers = datas.obj.peopleNumber;
							if(menbers != null && menbers != ''){
								$("#ent_menbers").html("已有"+menbers+"员工");
							}else{
								$("#ent_menbers").html("已有0员工");
							}
							$("#peopleNumber3").html(menbers);
							
							//公司地区
							var text1 = $("#officeArea").find("option:selected").text();
							var text2 = $("#sa-city").find("option:selected").text();
							var text3 = text1 + " " + text2;
							$("#officeArea3").html(text3);
							
							$("#spread-out").show();
							document.form2.reset();
						}
					}            
				});
				$("#edit-company-details").hide();
				$("#tzui-loading-overlay").hide();
			});
			
			/* 打开企业简介 第二次 编辑页面 */
			function deitEntDetail(){
				$.ajax({                
					cache: true,                
					type: "POST",                
					url:  '<%=path%>/basicConctroller/detail.do',  
					data : {
						opers : 'detailEdit'
					},
					async: true,                
					error: function(request) {                    
					},                
					success: function(data) {
						if(data == ''){
							return;
						}
						var datas = eval('('+data+')');
						$("#detail2").html(datas.obj.detail);
						$("#followArea2").html(datas.obj.businessDomain);
						$("#website2").val(datas.obj.website);
						$("#entType2").val(datas.obj.companyTypeCode);
						$("#entType").val(datas.obj.companyTypeCode);
						$("#officeAddress2").val(datas.obj.officeAddress);
						$("#regeditTime2").val(datas.obj.regeditDate);
						$("#peopleNumber2").val(datas.obj.peopleNumber);
						var codes = datas.msg;
						if(isNotEmpty(codes)){
							var d = eval('('+codes+')');
							$("#officeArea2").val(d.pro);
							var citys = d.cityList;
							var html = "";
							for(var i = 0; i < citys.length; i++){
								name = citys[i].location_name;
								code = citys[i].location_code;
								html = html + "<option value='"+code+"'>"+name+"</option>"
							}
							$("#sa-city2").empty();
							$("#sa-city2").append(html);
							$("#sa-city2").val(d.cityCode);
						}
					}            
				});
			}
			
			/* 企业基本信息保存 */
			$("#save-two").click(function (){
				var detail2 = $("#detail2").val();
				if(detail2.trim().length == 0){
					alert("请填写企业简介");
					return;
				}else{
					if(detail2.length > 2500){
						alert("企业简介字数超过2500");
						return;
					}
				}
				var followArea2 = $("#followArea2").val();
				if(followArea2.trim().length == 0){
					alert("请填写关注领域");
					return;
				}else{
					if(followArea2.length > 100){
						alert("关注领域字数超过100");
						return;
					}
				}
				var companyType = $("#entType").val();
				if(companyType.length == 0){
						alert("请选择企业性质");
						return;
				}
				var website2 = $("#website2").val();
				if(website2.length > 50){
						alert("网站长度不能超过50个字符");
						return;
				}
				var regeditTime2 = $("#regeditTime2").val();
				if(regeditTime2.length > 10){
						alert("注册时间不能超过10个字符");
						return;
				}
				var officeAddress2 = $("#officeAddress2").val();
				if(officeAddress2.length > 100){
						alert("地址不能超过100个字符");
						return;
				}
				var companyArea = $("#sa-city2").val();
				if(!isNotEmpty(companyArea)){
					alert("请选择公司所在地区");
					return;
				}
				var peopleNuber = $("#peopleNumber2").val();
				if(!isNotEmpty(peopleNuber)){
					alert("请填写公司规模");
					return;
				}
				$.ajax({                
					cache: true,                
					type: "POST",                
					url:  '<%=path%>/basicConctroller/edit.do',                
					data:$('#form3').serialize(),// 你的formid                
					async: false,                
					error: function(request) {                    
					},                
					success: function(data) {
						var datas = eval('('+data+')');
						var detail = datas.obj.detail;
						if(detail != null && detail != ''){
							
							if(detail.length > 280){
								$("#detail33").html(detail.substring(0,280)+"...");
							}else{
								$("#detail33").html(detail);
							}
						}
						$("#detail3").html(datas.obj.detail);
						$("#followArea3").html(datas.obj.businessDomain);
						$("#website3").html(datas.obj.website);
						var text = $("#entType2").find("option:selected").text();
						//$("#entType3").val(datas.obj.companyTypeCode);
						$("#entType3").html(text);
						$("#officeAddress3").html(datas.obj.officeAddress);
						$("#regeditTime3").html(datas.obj.regeditDate);
						$("#noCotent").hide();
						$("#profile-full-details").show();
						
						var menbers = datas.obj.peopleNumber;
						if(menbers != null && menbers != ''){
							$("#ent_menbers").html("已有"+menbers+"员工");
						}else{
							$("#ent_menbers").html("已有0员工");
						}
						$("#peopleNumber3").html(menbers);
						
						//公司地区
						var text1 = $("#officeArea2").find("option:selected").text();
						var text2 = $("#sa-city2").find("option:selected").text();
						var text3 = text1 + " " + text2;
						$("#officeArea3").html(text3);
						
						saveClose(editCompanyInfo,panelWinDefaulte);
					}            
				});
			});
			
			/* 时间格式化  2009-02-20 13:28:29 -- > 2009年02月30日。。。。。。*/
			function evaluation(time,$id,$mark){
				var $timearr=time.replace(" ",":").replace(/\:/g,"-").split("-");
				$id.find("."+$mark+"Year").html($timearr[0]+"年");
				$id.find("."+$mark+"Mouth").html($timearr[1]+"月");
				$id.find("."+$mark+"Data").html($timearr[2]+"日");
				$id.find("."+$mark+"Hour").html($timearr[3]+"时");
				$id.find("."+$mark+"Minutes").html($timearr[4]+"分");
			}
			
			/* 获取最新的5个简历 */
			function getResumeList(){
				$.ajax({                
					cache: false,                
					type: "POST",                
					url:  '<%=path%>/basicConctroller/getResume.do',                
					async: true,                
					error: function(request) {                    
					},                
					success: function(data) {
						var datas = eval('('+data+')');
						var positionName = "";
						var resumeName = "";
						var surname ;
						var miss_surname ;
						var name = "";
						if(datas.success){
							var html = "<div class='nep-lists'>";
							for(var i = 0; i < datas.obj.list.length; i++){
								resumeName = datas.obj.list[i].resume_name;
								surname = datas.obj.list[i].surname;
								miss_surname = datas.obj.list[i].miss_surname;
								if(surname != null && miss_surname!= null && surname != "" && miss_surname != ""){
									name = surname + miss_surname;
								}
								html = html + "<dl><a class='show-resume' data-gx='"+datas.obj.list[i].relationId+"' data-tag='"+datas.obj.list[i].id+"' href='javascript:void(0)'><dt>"+resumeName+"</dt><dd>"+name+"</dd></a></dl>";
							}
							html = html + "</div>";
							var htmlCount = "<div class='totle-resume-num'>" +
											"<a href='javascript:void(0)' class='indexResume'>共收到"+datas.obj.counts+"份简历</a>" + 
											"</div> ";
							html = html + htmlCount;
							$("#noResume").hide();
							$("#resume").empty();
							$("#resume").show();
							$("#resume").append(html);
							
							$(".show-resume").click(function(){
								var resumeId = $(this).data("tag");
								var relationId = $(this).data("gx");
								location.href="<%=path%>/resumeDetail/init.do?status=0&resumeId="+resumeId+"&relationId="+relationId;
							});
							$(".indexResume").click(function(){
								location.href=r_path+"/resume/init.do";
							});
						}
					}            
				});
			}
			
			/* 保存宣讲会  */
			$("#xjh").click(function (){
				var themeName = $("#subject").val();
				if(themeName.length == 0){
					alert("请填写名称！");
					return;
				}else if(themeName.length > 30){
					alert("名称字数不能超过30个汉字");
					return;
				}
				var xjhAnnexId = $("#xjhAnnexId").val();
				if(xjhAnnexId.length == 0){
					alert("请上传封面图片！");
					return;
				}
				var detailXjh = $("#detailXjh").val();
				if(detailXjh.length > 2000){
					alert("介绍字数不能超过2000个汉字");
					return;
				}
				
				var startTime = $("#start_time_1");
				var endTime = $("#end_time_1");
				var st = returnTimer(startTime,"start");
				var et = returnTimer(endTime,"end");
				if(compareTimes(st,et)){
					$("#stratTime").val(st);
					$("#endTime").val(et);
				}else{
					alert("结束时间必须大于开始时间");
					return;
				}
				
				$.ajax({                
					cache: true,                
					type: "POST",                
					url:  r_path + '/entMeetingNoticeController/add.do',                
					data:$('#xjhForm').serialize(),// 你的formid                
					async: false,                
					error: function(request) {                    
					},                
					success: function(data) {
						document.xjhForm.reset();
						$("#tzui-loading-overlay").hide();
						$(".preacp-and-oldvideo").hide();
						$("#meetCotent").empty();
						$("#meetingPage").val("1");
						$("#xjhId").val("");
						xjhList(1,1);
					}            
				});
			});
			//	获取时间
			function returnTimer($id,$mark){
				var startYear = parseInt($id.find("."+$mark+"Year").html());
				var startMouth = parseInt($id.find("."+$mark+"Mouth").html());
				var startData = parseInt($id.find("."+$mark+"Data").html());
				var startHour = parseInt($id.find("."+$mark+"Hour").html());
				var startMinutes = parseInt($id.find("."+$mark+"Minutes").html());
				
				startMouth = startMouth>=10? startMouth: "0"+startMouth;
				startData = startData>=10? startData: "0"+startData;
				startHour = startHour>=10? startHour: "0"+startHour;
				startMinutes = startMinutes>=10? startMinutes: "0"+startMinutes;
				var stratTime = startYear + "-" + startMouth + "-"+startData+" "+startHour+":"+startMinutes+":00";
				return stratTime;
				
			}
			
			/* 保存编辑 个人信息 */
			$("#saveUserInfo").click(function(){
				$.ajax({                
					cache: true,                
					type: "POST",                
					url:  '<%=path%>/basicConctroller/config.do',                
					data:$('#userForm').serialize(),// 你的formid                
					async: false,                
					error: function(request) {                    
					},                
					success: function(data) {
						$("#tzui-loading-overlay").hide();
						$("#personal-set-panel").hide();
					}            
				});
			});
			
			
			/* 加载更多宣讲会 */
			$("#moreXjh").click(function(){
				var page = $("#meetingPage").val();
				xjhList(page,"more");
			});
			$("#right-pulish").click(function(){
				location.href = r_path + "/position/pulishPage.do";
			});

		});
	</script>
	
		<input type="hidden" value="1" id="meetingPage"/>
		
		<!--container start-->
		<div class="container">
		
		
		<!--企业认证 开始-->			
		<div class="business-attestation" style="display:none;" id="certificate-tip">
			<input type="hidden" id="userId" value="${cookie_uid }"/>
			<input type="hidden" id="companyId" value="${cookie_companyId }"/>
			<span>请完成企业资质认证，资质认证通过后能提升企业的公信力，提升企业和企业发布的职位被推荐的几率。</span>
			<a href="javascript:void(0)" class="att-btn" title="立即认证" onclick="certificateTipPage(99);">立即认证</a>
			<a href="javascript:void(0)" class="close-att" id="close-att" onclick="closeAtt();" title="关闭"></a>
		</div>
		<!--企业认证 结束-->
		
		<!-- logo上传按钮 -->
		<div style="display:none;" id="uploadLogoTriger"></div>
		<input type="hidden" name="logoId" id="logoId"/>
		<div id="logoImg"></div>
		
			<!--company-title-details start-->
			<div class="company-title-details">
				<div class="logo-bg"></div>
				<div class="get-bgimg"  style="display:none;background-image:url(<%=path%>/images/attent.jpg);" id="uploadLogo">
					<a href="javascript:void(0)" class="update-headerPic">
						<span class="camera-icon"></span>
						<span class="">修改形象Logo</span>
					</a>
				</div>
				<div class="attention-num" id="followCountList">
					<a href="javascript:void(0)" title="">粉丝 <span id="followCount">0</span> 人</a></span>
				</div>
				<a href="javascript:void(0)" id="add-logo" class="add-heaer-pic">
					<span class="camera-icon"></span>
					<span class="">添加形象Logo</span>
				</a>
				
				<div class="enterprise-username">
					<div class="simple-info fl">
						<h3 class="eu-title">
							<span class="short-tlt" id="ent_name">XX科技</span>
							<a href="javascript:void(0)" class="certi no-certi" id="no-ertificate" onclick="certificateTipPage(99);" title="未认证">未认证</a>
							<a href="javascript:void(0)" class="certi ing-certi" id="ing-ertificate" onclick="certificateTipPage(0);" title="审核中">审核中</a>
							<a href="javascript:void(0)" class="certi already-certi" id="yes-ertificate" onclick="certificateTipPage(2);" title="已认证">已认证</a>
							<a href="javascript:void(0)" class="certi already-certi" id="nopass-ertificate" onclick="certificateTipPage(1);" title="审核不通过">审核不通过</a>
							（全称：<span id="ent_all_name"></span>）
<%-- 							（全称：<span id="ent_all_name">${entName }</span>） --%>
						</h3>
						<span class="other-info" id="ent_type">${enthy }</span>
						<span class="other-info" id="ent_menbers">已有0员工</span>
					</div>
					<div class="edit-window-panel fl">
						<a href="javascript:void(0)" class="sy-edit-btns edit-enterprise" id="edit-enterprise" data-open="open">编辑</a>
						<div class="ewp-edit-company">
							<span class="inverted-triangle"></span>
							<div class="ewp-info">
								<form id="form1" name="form1" action="<%=path%>/basicConctroller/edit.do">
									<div class="name">
										<span class="ewp-tips">公司全称</span>
										<input type="text" id="entName" name="entName"/>
									</div>
									<div class="simple-name-industry">
										<div class="name fl">
											<span class="ewp-tips">公司简称</span>
											<input type="text" class="ip-w260" id="entSimpleName" name="entSimpleName" />
										</div>
										
<!-- 										<div class="name fr"> -->
<!-- 											<span class="ewp-tips">所属行业</span> -->
<!-- 											<input type="text" class="ip-w260" id="industryType" name="industryCode" /> -->
<!-- 										</div> -->
										
										<div class="name fr">
											<span class="ewp-tips">所属行业</span>
											<select name="industryCode" id="industryType" class="down-list">
												<c:forEach items="${industryTypeList }" var="m1"> 
													<option value="${m1.industry_code }">${m1.industry_name }</option>
												</c:forEach>
											</select>
										</div>
									</div>
									
<!-- 									<div class="simple-name-industry"> -->
<!-- 										<div class="name fl"> -->
<!-- 											<span class="ewp-tips">员工数量</span> -->
<!-- 											<input type="text" class="ip-w260" id="numbers" name="peopleNumber" /> -->
<!-- 										</div> -->
<!-- 									</div> -->
									
									
									<div class="sure-or-cancel">
										<a href="javascript:void(0)" id="saveOne" class="opear-btn suer-btn go-l">确定</a>
										<a href="javascript:void(0)" class="opear-btn cancle-btn">取消</a>
									</div>
								</form>		
							</div>
							
						</div>
					</div>
				</div>
			</div>
			<!--company-title-details end-->
			
			<!--content-panel start-->
			<div class="content-panel">
				<!--con-container-left start-->
				<div class="con-container-left fl">
					
					<!--修改背景 start-->
					<div class="change-background-img" id="change-background-img">
						<a href="javascript:void(0)" id="touch-change"></a>
						
					
						<a href="javascript:void(0)" class="sy-edit-btns edit-bgImg">编辑</a>
						<div class="save-and-cancel" id="save-and-cancel">
							<a href="javascript:void(0)" class="sy-edit-btns press-btn save" id="bg-save">保存</a>
							<a href="javascript:void(0)" class="sy-edit-btns press-btn cancle" id="bg-cancle">取消</a>
						</div>
						<div class="set-bg-btns">
							<a href="javascript:void(0)" class="selet-close close-bg-btns"></a>
							<a href="javascript:void(0)" class="select-bgImg" id="select-bgImg-file">选择图片</a>
							<span class="select-tips">图片大小限制1 MB内，推荐尺寸850×230像素</span>
						</div>
					</div>
					<!--修改背景 end-->
					<div style="display:none;" id="uploadBg"></div>
					<!-- 背景原来 url -->
					<input type="hidden" id="bg_url" value="/images/default-bgImg.jpg" style="width:800px;"/>
					<!-- 背景预览 url -->
					<input type="hidden" id="bg_preview" style="width:800px;"/>
					
					
					<!--企业简介  展示 start -->
					<div class="company-profile">
						<!--企业简介无内容 开始-->
						<div class="has-null-con-panel" id="noCotent" >
							<span class="no-cons-tips">
								编辑企业详细信息
							</span>
							<a href="javascript:void(0)" class="right-off-upload" id="rightOfedit-comInfo">立即编辑</a>
						</div>
						<!--企业简介无内容 end-->
						
						<!--企业简介详情 start-->
						<div class="profile-full-details" id="profile-full-details" style="display:none;">
<!-- 							<h3 class="pro-title">企业简介</h3> -->
							
							<!--简单信息 开始-->
							<div class="simple-introduction">
								<p id="detail33"></p>
							</div>
							<!--简单信息 结束-->

							<!--详细信息 开始-->
							<div class="details-introduction" id="detail" >
								<div class="info-introduce-lists">
									<p id="detail3"></p>
								</div>
								<div class="other-info-introduce">
									<div class="intro-field">
										<p class="field-attent">关注领域</p>
										<p class="field-info" id="followArea3">例：互联网、光能发电、核能研究</p>
									</div>
									<div class="other-field-lists">
										<dl>
											<dt>公司网站</dt>
											<dd id="website3">www.lami.com</dd>
										</dl>
										<dl>
											<dt>成立年份</dt>
											<dd id="regeditTime3"></dd>
										</dl>
										<dl class="mg-null">
											<dt>公司类型</dt>
											<dd id="entType3">
												<select style="display:none;" id="entType33" name="" class="down-list">
													<c:forEach items="${companyTypeList }" var="m4"> 
														<option value="${m4.company_type_code }">${m4.company_type_name }</option>
													</c:forEach>
												</select>
											</dd>
										</dl>
										<dl>
											<dt>公司规模</dt>
											<dd id="peopleNumber3">33人</dd>
										</dl>
										<dl>
											<dt>公司总部</dt>
											<dd id="officeAddress3"></dd>
										</dl>
										<dl class="mg-null">
											<dt>公司所在地区</dt>
											<dd id="officeArea3">${officeArea }</dd>
										</dl>
									</div>
								</div>
							</div>
							<!--详细信息 结束-->
							
							
							<!--第二次 编辑企业简介 开始-->
							<a href="javascript:void(0)" class="sy-edit-btns edit-company-info" data-open="open" id="edit-company-info">编辑</a>
							<div class="modify-company-info panel-win-defaulte" id="panel-win-defaulte">
								<span class="inverted-triangle"></span>
								<div class="modify-lists">
									<form class="" method="post" id="form3" name="form3">
										<div class="modify-cons">
											<span class="md-tips">公司简介</span>
											<textarea class="txt" id="detail2" name="detail"></textarea>
										</div>
										<div class="modify-cons">
											<span class="md-tips">关注领域</span>
											<textarea class="txt" id="followArea2" name="businessDomain"></textarea>
										</div>
										
										<div class="modify-cons">
											<dl>
												<dt><span class="md-tips">公司网址</span></dt>
												<dd><input type="text" class="md-input mr-15" id="website2" name="website"></dd>
											</dl>
											<dl>
												<dt><span class="md-tips">公司类型</span></dt>
												<dd>
<!-- 													<input type="text" class="md-input" id="entType2" name="industryCode"> -->
													<select id="entType2" name="companyTypeCode" class="down-list">
														<c:forEach items="${companyTypeList }" var="m3"> 
															<option value="${m3.company_type_code }">${m3.company_type_name }</option>
														</c:forEach>
													</select>
												</dd>
												
											</dl>
											<dl>
												<dt><span class="md-tips">公司总部</span></dt>
												<dd><input type="text" class="md-input mr-15" id="officeAddress2" name="officeAddress"></dd>
											</dl>
											<dl>
												<dt><span class="md-tips">成立年份</span></dt>
												<dd><input type="text" class="md-input" id="regeditTime2" name="regeditDate"></dd>
											</dl>
											<dl>
												<dt><span class="md-tips">请选择公司所在地区</span></dt>
												<dd>
													<select class="down-list change-pro" onchange="changePro(this);" id="officeArea2">
														<option value="0">请选择</option>
														<c:forEach items="${proviceList }" var="m">
															<option value="${m.location_code }">${m.location_name }</option>
														</c:forEach>
													</select>
												</dd>
											</dl>
											
											<dl style="margin-left: 16px;">
												<dt><span class="md-tips" style="height:19px;"></span></dt>
												<dd>
													<select class="down-list" id="sa-city2" name="officeArea">
														<option value="">请选择</option>
													</select>
												</dd>
											</dl>
											
											
											
											<dl style="clear:both;">
												<dt><span class="md-tips">公司规模</span></dt>
												<dd><input type="text" class="md-input mr-15" id="peopleNumber2" maxlength="10" name="peopleNumber"></dd>
											</dl>
										</div>
										<div class="sure-or-cancel">
											<a href="javascript:void(0)" id="save-two" class="opear-btn suer-btn go-l">确定</a>
											<a href="javascript:void(0)" class="opear-btn cancle-btn">取消</a>
										</div>
									</form>
								</div>
							</div>
							<!--编辑企业简介 结束-->
						</div>
						<!--企业简介详情 end-->
						
						
						<!--收起展开 start-->
						<div class="spread-out" id="spread-out" style="display: none;">
							<a href="javascript:void(0)" id="spread-outs"  data-flag="close">+收起</a>
						</div>
						<!--收起展开 end-->
					</div>
					<!--企业简介 end-->
					
					<!--第一次 编辑企业消息 开始-->
					<div class="modify-company-info panel-win-defaulte edit-company-details" id="edit-company-details">
						<a href="javascript:void(0)" class="close-xx" id="close-company-details"></a>
						<div class="ecd-title">编辑企业信息</div>
						<div class="modify-lists">
							<form class="" method="post" name="form2" id="form2">
								<div class="modify-cons">
									<span class="md-tips">公司简介</span>
									<textarea class="txt" id="entdetail" name="detail"></textarea>
								</div>
								<div class="modify-cons">
									<span class="md-tips">关注领域</span>
									<textarea class="txt" id="followArea" name="businessDomain"></textarea>
								</div>
								
								<div class="modify-cons">
									<dl>
										<dt><span class="md-tips">公司网址</span></dt>
										<dd><input type="text" class="md-input mr-15" id="website" name="website"></dd>
									</dl>
									<dl>
										<dt><span class="md-tips">公司类型</span></dt>
										<dd>
			<!-- 								<input type="text" class="md-input" id="entType" name="industryCode"> -->
											<select id="entType" name="companyTypeCode" class="down-list">
												<c:forEach items="${companyTypeList }" var="m2"> 
													<option value="${m2.company_type_code }">${m2.company_type_name }</option>
												</c:forEach>
											</select>
										</dd>
									</dl>
									<dl>
										<dt><span class="md-tips">公司总部</span></dt>
										<dd><input type="text" class="md-input mr-15" id="officeAddress" name="officeAddress"></dd>
									</dl>
									<dl>
										<dt><span class="md-tips">成立年份</span></dt>
										<dd><input type="text" class="md-input" id="regeditTime" name="regeditDate"></dd>
									</dl>
									<dl  class="select-adress select-adress-selectDown">
										<dt><span class="md-tips">请选择公司所在地区</span></dt>
										<dd>
											<select class="down-list" onchange="changePro(this);" value="${area1Code }" id="officeArea">
												<option value="0">请选择</option>
												<c:forEach items="${proviceList }" var="m">
													<option value="${m.location_code }">${m.location_name }</option>
												</c:forEach>
											</select>
										</dd>
									</dl>
									
									
									<dl style="margin-left: 16px;">
										<dt><span class="md-tips" style="height:19px;"></span></dt>
										<dd>
											<select class="down-list" id="sa-city"  name="officeArea">
												<option value="${area2Code }" >${area2 }</option>
											</select>
										</dd>
									</dl>
									
									<dl style="clear:both;">
										<dt><span class="md-tips">公司规模</span></dt>
										<dd><input type="text" class="md-input mr-15" name="peopleNumber" maxlength="10" id="peopleNumber"></dd>
									</dl>
								</div>
								<div class="sure-or-cancel">
									<a href="javascript:void(0)" id="save-first" class="opear-btn suer-btn go-l">确定</a>
									<a href="javascript:void(0)" id="edit-first" class="opear-btn cancle-btn">取消</a>
								</div>
							</form>
						</div>
					</div>
					<!--编辑企业消息 结束-->
					
					
					
					<!--宣讲会和校招岗位 start-->
					<div class="tab-conference-panel">
						<!--tab-title start-->
						<input type="hidden" id="xjhCountValue"/>
						<div class="tab-title" id="tab-title-change">
							<ul>
								<li class="bd-none tab-active"><a href="javascript:void(0)" id="xjhCount">直播点播（0）</a></li>
								<li><a href="javascript:void(0)" id="zwCount">校招岗位（0）</a></li>
							</ul>
						</div>
						<!--tab-title end-->
						
						
						
						<!--会议详细详细 开始-->
						<div class="confer-full-info" id="confer-full-info">
							
							
							<!--宣讲会  开始-->
							<div class="confer-detalis-cons preach-cons">
								
							<!--空会议 开始-->
							<div class="confer-null-panel" id="noMeet">
								<div class="dancer-png-show"></div>
								<div class="single-tips">暂无信息</div>
								<a href="javascript:void(0)" class="right-off-upload" id="upload-confers">立即发布</a>
							</div>
							<!--空会议 结束-->
							
							<!--详细内容会议开始-->
							<div style="display: none;" id="meetList">
								<div id="meetCotent">
								</div>
								<div class="upload-addMore" style="">
									<a href="javascript:void(0)" style="display:none;" id="moreXjh" class="more">加载更多</a>
								</div>
							</div>
							<!--详细内容会议结束-->
							</div>
							<!--宣讲会  结束-->
							
							<!--校招岗位  开始-->
							<div class="confer-detalis-cons" style="display: none;">
								
								<!--空校招 开始-->
								<div class="confer-null-panel" id="noPosition" >
									<div class="dancer-png-show"></div>
									<div class="single-tips">暂无信息</div>
									<a href="<%=path%>/position/pulishPage.do" class="right-off-upload" id="right-pulish">立即发布</a>
								</div>
								<!--空校招 结束-->
								
								<!--详细校招列表 开始-->
								<div class="school-position" id="positions" style="display: none;">
									<table cellpadding="0" cellspacing="0">
										<thead>
											<tr>
												<td>职位名称</td>
												<td>职能</td>
												<td>工作地区</td>
												<td>发布时间</td>
											</tr>
										</thead>
										<tbody class="school-hover" id="school-hover">
											<tr>
												<td>测试工程师</td>
												<td>开发</td>
												<td>北京，上海</td>
												<td>2015-11-11</td>
											</tr>
										</tbody>
									</table>
								</div>
								<!--详细校招列表 结束-->
							</div>
							<!--校招岗位  结束-->
							
						</div>
						<!--会议详细详细 结束-->
						
					</div>
					<!--宣讲会和校招岗位 end-->
					
				</div>
				<!--con-container-left end-->
				
				<!--con-timerInfo-right start-->
				<div class="con-timerInfo-right fr">
					
					<!--新收到的简历 start-->
					<div class="new-resume">
						<div class="resume-tips-title">
							<span>
								新收到的简历
							</span>
						</div>
						
						<!--无简历显示 开始-->
						<div class="resume-of-null" id="noResume">
							<div class="earth-png">
								<img src="<%=path%>/images/earth.png" />
							</div>
							<p>暂无信息</p>
						</div>
						<!--无简历显示  结束-->
						
						<!--新收简历显示 开始-->
						
						<div class="new-resume-position" id="resume" style="display: none;">
							<div class="nep-lists">
								<dl>
									<a href="javascript:void(0)">
										<dt>架构师</dt>
										<dd>李丽</dd>
									</a>
								</dl>
							</div>
<!-- 							<div class="totle-resume-num"> -->
<!-- 								<a href="javascript:void(0)">共收到3322份简历</a> -->
<!-- 							</div> -->
						</div>
						
						<!--新收简历显示 结束-->
						
					</div>
					<!--新收到的简历 end-->
					
					<!--新加关注 start-->
					<div class="new-attention">
						<div class="attention-tips-title">
							<span>
								新加关注
							</span>
						</div>
						
						<!--无关注内容 开始-->
						<div class="attention-of-null" id="noFollow">
							<div class="radish-png">
								<img src="<%=path%>/images/luobu.png" />
							</div>
							<p>暂无信息</p>
						</div>
						<!--无关注内容 结束-->
						
						<!--新关注任务列表  开始-->
						
						<div class="attention-info-lists" id="follow" style="display: none;">
						</div>
						
						<!--新关注任务列表  结束-->
						
						
					</div>
					<!--新加关注 end-->
					
				</div>
				<!--con-timerInfo-right end-->
			<div class="clear"></div>
			</div>
			<!--content-panel end-->
		</div>
		<!--container end-->

		<!--发布宣讲会 和 发布以往视频 开始-->
			<div class="preacp-and-oldvideo" id="pushAllXjh">
				<a href="javascript:void(0)" class="close-xx" id="close-meetings"></a>
				<ul class="change-panel" id="change-meetings">
					<li class="bd-null bgColor-active"><a href="javascript:void(0)">发布直播预告</a></li>
					<li><a href="javascript:void(0)">发布点播视频</a></li>
				</ul>

				<div class="meeting-detaiils" id="change-meeting-detaiils">
					<!--发布宣讲会 开始-->
					<div class="send-preacp-meet meetings" >
						<form method="post" id="xjhForm" name="xjhForm">
							<input type="reset" id="xjhFormReset" style="display:none;"/>
							<input style="display:none;" id="xjhId" name="xjhId"/>
							<input type="hidden" id="stratTime" name="stratTimeStr"/>
							<input type="hidden" id="endTime" name="endTimeStr"/>
							<div class="spm-cons-details">
								<div class="spm-style-set">
									<span class="spm-tips" >公司名称</span>
									<span id="entNameXjhAdd">${entName }</span>
								</div>
								<div class="spm-style-set">
									<span class="spm-tips">直播预告名称</span><span id="themeNameTips"><span>
									<input type="text" class="spm-input mgr-null" id="subject" name="subject">
								</div>
							</div>
							<input type="text" style="display:none;" value="1" name="type" />
							<div class="spm-cons-details">
								<div class="spm-style-set" id="start_time_1">
									<span class="spm-tips">预计开始时间</span>
									<ul id="expected-start-time" class="mod_select">
											<li>
												<div class="select_box">
													<span class="select_txt startYear">2015年</span><span class="selet_open"></span>
													<div class="option year">
														<a href="javascript:void(0)">2015年</a>
														<a href="javascript:void(0)">2016年</a>
														<a href="javascript:void(0)">2017年</a>
														<a href="javascript:void(0)">2018年</a>
														<a href="javascript:void(0)">2019年</a>
														<a href="javascript:void(0)">2020年</a>
													</div>
												</div>
											</li>
											<li>
												<div class="select_box">
													<span class="select_txt select-other startMouth">11月</span><span class="selet_open"></span>
													<div class="option option-Wfixed" >
														<a href="javascript:void(0)">1月</a>
														<a href="javascript:void(0)">2月</a>
														<a href="javascript:void(0)">3月</a>
														<a href="javascript:void(0)">4月</a>
														<a href="javascript:void(0)">5月</a>
														<a href="javascript:void(0)">6月</a>
														<a href="javascript:void(0)">7月</a>
														<a href="javascript:void(0)">8月</a>
														<a href="javascript:void(0)">9月</a>
														<a href="javascript:void(0)">10月</a>
														<a href="javascript:void(0)">11月</a>
														<a href="javascript:void(0)">12月</a>
													</div>
												</div>
											</li>
											
											<li>
												<div class="select_box">
													<span class="select_txt select-other startData">30日</span><span class="selet_open"></span>
													<div class="option option-Wfixed">
														<a href="javascript:void(0)">1日</a>
														<a href="javascript:void(0)">2日</a>
														<a href="javascript:void(0)">3日</a>
														<a href="javascript:void(0)">4日</a>
														<a href="javascript:void(0)">5日</a>
														<a href="javascript:void(0)">6日</a>
														<a href="javascript:void(0)">7日</a>
														<a href="javascript:void(0)">8日</a>
														<a href="javascript:void(0)">9日</a>
														<a href="javascript:void(0)">10日</a>
														<a href="javascript:void(0)">11日</a>
														<a href="javascript:void(0)">12日</a>
														<a href="javascript:void(0)">13日</a>
														<a href="javascript:void(0)">14日</a>
														<a href="javascript:void(0)">15日</a>
														<a href="javascript:void(0)">16日</a>
														<a href="javascript:void(0)">17日</a>
														<a href="javascript:void(0)">18日</a>
														<a href="javascript:void(0)">19日</a>
														<a href="javascript:void(0)">20日</a>
														<a href="javascript:void(0)">21日</a>
														<a href="javascript:void(0)">22日</a>
														<a href="javascript:void(0)">23日</a>
														<a href="javascript:void(0)">24日</a>
														<a href="javascript:void(0)">25日</a>
														<a href="javascript:void(0)">26日</a>
														<a href="javascript:void(0)">27日</a>
														<a href="javascript:void(0)">28日</a>
														<a href="javascript:void(0)">29日</a>
														<a href="javascript:void(0)">30日</a>
														<a href="javascript:void(0)">31日</a>
													</div>
												</div>
											</li>
											<li>
												<div class="select_box">
													<span class="select_txt select-other startHour">12时</span><span class="selet_open"></span>
													<div class="option option-Wfixed">
														<a href="javascript:void(0)">1时</a>
														<a href="javascript:void(0)">2时</a>
														<a href="javascript:void(0)">3时</a>
														<a href="javascript:void(0)">4时</a>
														<a href="javascript:void(0)">5时</a>
														<a href="javascript:void(0)">6时</a>
														<a href="javascript:void(0)">7时</a>
														<a href="javascript:void(0)">8时</a>
														<a href="javascript:void(0)">9时</a>
														<a href="javascript:void(0)">10时</a>
														<a href="javascript:void(0)">11时</a>
														<a href="javascript:void(0)">12时</a>
														<a href="javascript:void(0)">13时</a>
														<a href="javascript:void(0)">14时</a>
														<a href="javascript:void(0)">15时</a>
														<a href="javascript:void(0)">16时</a>
														<a href="javascript:void(0)">17时</a>
														<a href="javascript:void(0)">18时</a>
														<a href="javascript:void(0)">19时</a>
														<a href="javascript:void(0)">20时</a>
														<a href="javascript:void(0)">21时</a>
														<a href="javascript:void(0)">22时</a>
														<a href="javascript:void(0)">23时</a>
														<a href="javascript:void(0)">24时</a>
													</div>
												</div>
											</li>
											<li>
												<div class="select_box">
													<span class="select_txt select-other startMinutes">11分</span><span class="selet_open"></span>
													<div class="option option-Wfixed">
														<a href="javascript:void(0)">0分</a>
														<a href="javascript:void(0)">1分</a>
														<a href="javascript:void(0)">2分</a>
														<a href="javascript:void(0)">3分</a>
														<a href="javascript:void(0)">4分</a>
														<a href="javascript:void(0)">5分</a>
														<a href="javascript:void(0)">6分</a>
														<a href="javascript:void(0)">7分</a>
														<a href="javascript:void(0)">8分</a>
														<a href="javascript:void(0)">9分</a>
														<a href="javascript:void(0)">10分</a>
														<a href="javascript:void(0)">11分</a>
														<a href="javascript:void(0)">12分</a>
														<a href="javascript:void(0)">13分</a>
														<a href="javascript:void(0)">14分</a>
														<a href="javascript:void(0)">15分</a>
														<a href="javascript:void(0)">16分</a>
														<a href="javascript:void(0)">17分</a>
														<a href="javascript:void(0)">18分</a>
														<a href="javascript:void(0)">19分</a>
														<a href="javascript:void(0)">20分</a>
														<a href="javascript:void(0)">21分</a>
														<a href="javascript:void(0)">22分</a>
														<a href="javascript:void(0)">23分</a>
														<a href="javascript:void(0)">24分</a>
														<a href="javascript:void(0)">25分</a>
														<a href="javascript:void(0)">26分</a>
														<a href="javascript:void(0)">27分</a>
														<a href="javascript:void(0)">28分</a>
														<a href="javascript:void(0)">29分</a>
														<a href="javascript:void(0)">30分</a>
														<a href="javascript:void(0)">31分</a>
														<a href="javascript:void(0)">32分</a>
														<a href="javascript:void(0)">33分</a>
														<a href="javascript:void(0)">34分</a>
														<a href="javascript:void(0)">35分</a>
														<a href="javascript:void(0)">36分</a>
														<a href="javascript:void(0)">37分</a>
														<a href="javascript:void(0)">38分</a>
														<a href="javascript:void(0)">39分</a>
														<a href="javascript:void(0)">40分</a>
														<a href="javascript:void(0)">41分</a>
														<a href="javascript:void(0)">42分</a>
														<a href="javascript:void(0)">43分</a>
														<a href="javascript:void(0)">44分</a>
														<a href="javascript:void(0)">45分</a>
														<a href="javascript:void(0)">46分</a>
														<a href="javascript:void(0)">47分</a>
														<a href="javascript:void(0)">48分</a>
														<a href="javascript:void(0)">49分</a>
														<a href="javascript:void(0)">50分</a>
														<a href="javascript:void(0)">51分</a>
														<a href="javascript:void(0)">52分</a>
														<a href="javascript:void(0)">53分</a>
														<a href="javascript:void(0)">54分</a>
														<a href="javascript:void(0)">55分</a>
														<a href="javascript:void(0)">56分</a>
														<a href="javascript:void(0)">57分</a>
														<a href="javascript:void(0)">58分</a>
														<a href="javascript:void(0)">59分</a>
													</div>
												</div>
											</li>
										</ul>
								</div>
							</div>
							<div class="clear"></div>
							
							<div class="spm-cons-details">
								<div class="spm-style-set" id="end_time_1">
									<span class="spm-tips">预计结束时间</span>
									<ul id="basic-endTimer-select" class="mod_select end-timer">
											<li>
												<div class="select_box">
													<span class="select_txt endYear">2015年</span><span class="selet_open"></span>
													<div class="option">
														<a href="javascript:void(0)">2015年</a>
														<a href="javascript:void(0)">2016年</a>
														<a href="javascript:void(0)">2017年</a>
														<a href="javascript:void(0)">2018年</a>
														<a href="javascript:void(0)">2019年</a>
														<a href="javascript:void(0)">2020年</a>
													</div>
												</div>
											</li>
											<li>
												<div class="select_box">
													<span class="select_txt select-other endMouth">11月</span><span class="selet_open"></span>
													<div class="option option-Wfixed">
														<a href="javascript:void(0)">1月</a>
														<a href="javascript:void(0)">2月</a>
														<a href="javascript:void(0)">3月</a>
														<a href="javascript:void(0)">4月</a>
														<a href="javascript:void(0)">5月</a>
														<a href="javascript:void(0)">6月</a>
														<a href="javascript:void(0)">7月</a>
														<a href="javascript:void(0)">8月</a>
														<a href="javascript:void(0)">9月</a>
														<a href="javascript:void(0)">10月</a>
														<a href="javascript:void(0)">11月</a>
														<a href="javascript:void(0)">12月</a>
													</div>
												</div>
											</li>
											
											<li>
												<div class="select_box">
													<span class="select_txt select-other endData">30日</span><span class="selet_open"></span>
													<div class="option option-Wfixed">
														<a href="javascript:void(0)">1日</a>
														<a href="javascript:void(0)">2日</a>
														<a href="javascript:void(0)">3日</a>
														<a href="javascript:void(0)">4日</a>
														<a href="javascript:void(0)">5日</a>
														<a href="javascript:void(0)">6日</a>
														<a href="javascript:void(0)">7日</a>
														<a href="javascript:void(0)">8日</a>
														<a href="javascript:void(0)">9日</a>
														<a href="javascript:void(0)">10日</a>
														<a href="javascript:void(0)">11日</a>
														<a href="javascript:void(0)">12日</a>
														<a href="javascript:void(0)">13日</a>
														<a href="javascript:void(0)">14日</a>
														<a href="javascript:void(0)">15日</a>
														<a href="javascript:void(0)">16日</a>
														<a href="javascript:void(0)">17日</a>
														<a href="javascript:void(0)">18日</a>
														<a href="javascript:void(0)">19日</a>
														<a href="javascript:void(0)">20日</a>
														<a href="javascript:void(0)">21日</a>
														<a href="javascript:void(0)">22日</a>
														<a href="javascript:void(0)">23日</a>
														<a href="javascript:void(0)">24日</a>
														<a href="javascript:void(0)">25日</a>
														<a href="javascript:void(0)">26日</a>
														<a href="javascript:void(0)">27日</a>
														<a href="javascript:void(0)">28日</a>
														<a href="javascript:void(0)">29日</a>
														<a href="javascript:void(0)">30日</a>
														<a href="javascript:void(0)">31日</a>
													</div>
												</div>
											</li>
											<li>
												<div class="select_box">
													<span class="select_txt select-other endHour">12时</span><span class="selet_open"></span>
													<div class="option option-Wfixed">
														<a href="javascript:void(0)">1时</a>
														<a href="javascript:void(0)">2时</a>
														<a href="javascript:void(0)">3时</a>
														<a href="javascript:void(0)">4时</a>
														<a href="javascript:void(0)">5时</a>
														<a href="javascript:void(0)">6时</a>
														<a href="javascript:void(0)">7时</a>
														<a href="javascript:void(0)">8时</a>
														<a href="javascript:void(0)">9时</a>
														<a href="javascript:void(0)">10时</a>
														<a href="javascript:void(0)">11时</a>
														<a href="javascript:void(0)">12时</a>
														<a href="javascript:void(0)">13时</a>
														<a href="javascript:void(0)">14时</a>
														<a href="javascript:void(0)">15时</a>
														<a href="javascript:void(0)">16时</a>
														<a href="javascript:void(0)">17时</a>
														<a href="javascript:void(0)">18时</a>
														<a href="javascript:void(0)">19时</a>
														<a href="javascript:void(0)">20时</a>
														<a href="javascript:void(0)">21时</a>
														<a href="javascript:void(0)">22时</a>
														<a href="javascript:void(0)">23时</a>
														<a href="javascript:void(0)">24时</a>
													</div>
												</div>
											</li>
											<li>
												<div class="select_box">
													<span class="select_txt select-other endMinutes">11分</span><span class="selet_open"></span>
													<div class="option option-Wfixed">
														<a href="javascript:void(0)">0分</a>
														<a href="javascript:void(0)">1分</a>
														<a href="javascript:void(0)">2分</a>
														<a href="javascript:void(0)">3分</a>
														<a href="javascript:void(0)">4分</a>
														<a href="javascript:void(0)">5分</a>
														<a href="javascript:void(0)">6分</a>
														<a href="javascript:void(0)">7分</a>
														<a href="javascript:void(0)">8分</a>
														<a href="javascript:void(0)">9分</a>
														<a href="javascript:void(0)">10分</a>
														<a href="javascript:void(0)">11分</a>
														<a href="javascript:void(0)">12分</a>
														<a href="javascript:void(0)">13分</a>
														<a href="javascript:void(0)">14分</a>
														<a href="javascript:void(0)">15分</a>
														<a href="javascript:void(0)">16分</a>
														<a href="javascript:void(0)">17分</a>
														<a href="javascript:void(0)">18分</a>
														<a href="javascript:void(0)">19分</a>
														<a href="javascript:void(0)">20分</a>
														<a href="javascript:void(0)">21分</a>
														<a href="javascript:void(0)">22分</a>
														<a href="javascript:void(0)">23分</a>
														<a href="javascript:void(0)">24分</a>
														<a href="javascript:void(0)">25分</a>
														<a href="javascript:void(0)">26分</a>
														<a href="javascript:void(0)">27分</a>
														<a href="javascript:void(0)">28分</a>
														<a href="javascript:void(0)">29分</a>
														<a href="javascript:void(0)">30分</a>
														<a href="javascript:void(0)">31分</a>
														<a href="javascript:void(0)">32分</a>
														<a href="javascript:void(0)">33分</a>
														<a href="javascript:void(0)">34分</a>
														<a href="javascript:void(0)">35分</a>
														<a href="javascript:void(0)">36分</a>
														<a href="javascript:void(0)">37分</a>
														<a href="javascript:void(0)">38分</a>
														<a href="javascript:void(0)">39分</a>
														<a href="javascript:void(0)">40分</a>
														<a href="javascript:void(0)">41分</a>
														<a href="javascript:void(0)">42分</a>
														<a href="javascript:void(0)">43分</a>
														<a href="javascript:void(0)">44分</a>
														<a href="javascript:void(0)">45分</a>
														<a href="javascript:void(0)">46分</a>
														<a href="javascript:void(0)">47分</a>
														<a href="javascript:void(0)">48分</a>
														<a href="javascript:void(0)">49分</a>
														<a href="javascript:void(0)">50分</a>
														<a href="javascript:void(0)">51分</a>
														<a href="javascript:void(0)">52分</a>
														<a href="javascript:void(0)">53分</a>
														<a href="javascript:void(0)">54分</a>
														<a href="javascript:void(0)">55分</a>
														<a href="javascript:void(0)">56分</a>
														<a href="javascript:void(0)">57分</a>
														<a href="javascript:void(0)">58分</a>
														<a href="javascript:void(0)">59分</a>
													</div>
												</div>
											</li>
										</ul>
								</div>
							</div>
							
						<!-- 上传宣讲会图片 -->
						<script type="text/javascript">
							
						</script>
							
							<div class="spm-style-set frontCover-img clear">
								<span class="spm-tips">设置封面图
									<i>图片大小限制1 MB以内</i>
								</span>
								<div class="upload-img">
									<div class="imgCover fl" id="xhjImg" style="display:none;">
										<a href="javascript:void(0)" id=""><img src="" width="78" height="78"></a>
										<a href="javascript:void(0)" class="delete-imgCover"></a>
									</div>
									<div class="imgCover upCoverImg-btn fl" id="uploadImgTrigger">
										<a href="javascript:void(0)" class="upCoverImgBtn"></a>
									</div>
									<div id="uploadImg" style="display:none;"></div>
										<input type="hidden" name="coverImagePath"  id="xjhAnnexId"/>
									<div class="clear"></div>
									<div class="clear"></div>
								</div>
							</div>
							
							<div class="spm-style-set remark">
								<span class="spm-tips">视频直播介绍</span>
								<textarea id="detailXjh" name="memo" placeholder="例如：本次会议宣讲会嘉宾有XXX先生、XXX先生，将会重点介绍今年的校招岗位需求"></textarea>
							</div>
							
							<div class="submit-box">
								<a href="javascript:void(0)" id="xjh">发布 </a>
							</div>
							
						</form>						
					</div>
					<!--发布宣讲会 结束-->
					
					<!--发布以往视频  开始-->
					<div class="send-preacp-meet meetings  send-old-videos" style="display: none;" >
						<form method="post" name="historyForm" id="historyForm" action="<%=path%>/entMeetingNoticeController/add.do">
							<input type="reset" value="重置" style="display:none;" id="historyFormReset"/>
							<div class="spm-cons-details">
								<div class="spm-style-set">
									<span class="spm-tips">公司名称</span>
									<span id="entNameHistroyAdd">${entName }</span>
<%-- 									<input type="text" readonly="readonly" value="${entName }" class="spm-input"> --%>
								</div>
								<input type="text" style="display:none;" value="3" name="type" />
								<div class="spm-style-set">
									<span class="spm-tips">视频名称</span>
									<input type="text" id="history-subject" name="subject" class="spm-input  mgr-null">
								</div>
								
							</div>
							<div class="clear"></div>
							<div class="spm-cons-details">
								<div class="spm-style-set">
									<span class="spm-tips">拍摄时间</span>
									<input type="text" style="display:none;" name="stratTimeStr" id="history-startTime"/>
									<ul id="start_time_history" class="mod_select">
											<li>
												<div class="select_box">
													<span class="select_txt startYear">2015年</span><span class="selet_open"></span>
													<div class="option">
														<a href="javascript:void(0)">2015年</a>
														<a href="javascript:void(0)">2016年</a>
														<a href="javascript:void(0)">2017年</a>
														<a href="javascript:void(0)">2018年</a>
														<a href="javascript:void(0)">2019年</a>
														<a href="javascript:void(0)">2020年</a>
														<a href="javascript:void(0)">2021年</a>
														<a href="javascript:void(0)">2022年</a>
														<a href="javascript:void(0)">2023年</a>
														<a href="javascript:void(0)">2024年</a>
														<a href="javascript:void(0)">2025年</a>
														<a href="javascript:void(0)">2026年</a>
														<a href="javascript:void(0)">2027年</a>
														<a href="javascript:void(0)">2028年</a>
														<a href="javascript:void(0)">2029年</a>
														<a href="javascript:void(0)">2030年</a>
													</div>
												</div>
											</li>
											<li>
												<div class="select_box">
													<span class="select_txt select-other startMouth">11月</span><span class="selet_open"></span>
													<div class="option option-Wfixed" >
														<a href="javascript:void(0)">1月</a>
														<a href="javascript:void(0)">2月</a>
														<a href="javascript:void(0)">3月</a>
														<a href="javascript:void(0)">4月</a>
														<a href="javascript:void(0)">5月</a>
														<a href="javascript:void(0)">6月</a>
														<a href="javascript:void(0)">7月</a>
														<a href="javascript:void(0)">8月</a>
														<a href="javascript:void(0)">9月</a>
														<a href="javascript:void(0)">10月</a>
														<a href="javascript:void(0)">11月</a>
														<a href="javascript:void(0)">12月</a>
													</div>
												</div>
											</li>
											
											<li>
												<div class="select_box">
													<span class="select_txt select-other startData">30日</span><span class="selet_open"></span>
													<div class="option option-Wfixed">
														<a href="javascript:void(0)">1日</a>
														<a href="javascript:void(0)">2日</a>
														<a href="javascript:void(0)">3日</a>
														<a href="javascript:void(0)">4日</a>
														<a href="javascript:void(0)">5日</a>
														<a href="javascript:void(0)">6日</a>
														<a href="javascript:void(0)">7日</a>
														<a href="javascript:void(0)">8日</a>
														<a href="javascript:void(0)">9日</a>
														<a href="javascript:void(0)">10日</a>
														<a href="javascript:void(0)">11日</a>
														<a href="javascript:void(0)">12日</a>
														<a href="javascript:void(0)">13日</a>
														<a href="javascript:void(0)">14日</a>
														<a href="javascript:void(0)">15日</a>
														<a href="javascript:void(0)">16日</a>
														<a href="javascript:void(0)">17日</a>
														<a href="javascript:void(0)">18日</a>
														<a href="javascript:void(0)">19日</a>
														<a href="javascript:void(0)">20日</a>
														<a href="javascript:void(0)">21日</a>
														<a href="javascript:void(0)">22日</a>
														<a href="javascript:void(0)">23日</a>
														<a href="javascript:void(0)">24日</a>
														<a href="javascript:void(0)">25日</a>
														<a href="javascript:void(0)">26日</a>
														<a href="javascript:void(0)">27日</a>
														<a href="javascript:void(0)">28日</a>
														<a href="javascript:void(0)">29日</a>
														<a href="javascript:void(0)">30日</a>
														<a href="javascript:void(0)">31日</a>
													</div>
												</div>
											</li>
											<li>
												<div class="select_box">
													<span class="select_txt select-other startHour">12时</span><span class="selet_open"></span>
													<div class="option option-Wfixed">
														<a href="javascript:void(0)">1时</a>
														<a href="javascript:void(0)">2时</a>
														<a href="javascript:void(0)">3时</a>
														<a href="javascript:void(0)">4时</a>
														<a href="javascript:void(0)">5时</a>
														<a href="javascript:void(0)">6时</a>
														<a href="javascript:void(0)">7时</a>
														<a href="javascript:void(0)">8时</a>
														<a href="javascript:void(0)">9时</a>
														<a href="javascript:void(0)">10时</a>
														<a href="javascript:void(0)">11时</a>
														<a href="javascript:void(0)">12时</a>
														<a href="javascript:void(0)">13时</a>
														<a href="javascript:void(0)">14时</a>
														<a href="javascript:void(0)">15时</a>
														<a href="javascript:void(0)">16时</a>
														<a href="javascript:void(0)">17时</a>
														<a href="javascript:void(0)">18时</a>
														<a href="javascript:void(0)">19时</a>
														<a href="javascript:void(0)">20时</a>
														<a href="javascript:void(0)">21时</a>
														<a href="javascript:void(0)">22时</a>
														<a href="javascript:void(0)">23时</a>
														<a href="javascript:void(0)">24时</a>
													</div>
												</div>
											</li>
											<li>
												<div class="select_box">
													<span class="select_txt select-other startMinutes">11分</span><span class="selet_open"></span>
													<div class="option option-Wfixed">
														<a href="javascript:void(0)">0分</a>
														<a href="javascript:void(0)">1分</a>
														<a href="javascript:void(0)">2分</a>
														<a href="javascript:void(0)">3分</a>
														<a href="javascript:void(0)">4分</a>
														<a href="javascript:void(0)">5分</a>
														<a href="javascript:void(0)">6分</a>
														<a href="javascript:void(0)">7分</a>
														<a href="javascript:void(0)">8分</a>
														<a href="javascript:void(0)">9分</a>
														<a href="javascript:void(0)">10分</a>
														<a href="javascript:void(0)">11分</a>
														<a href="javascript:void(0)">12分</a>
														<a href="javascript:void(0)">13分</a>
														<a href="javascript:void(0)">14分</a>
														<a href="javascript:void(0)">15分</a>
														<a href="javascript:void(0)">16分</a>
														<a href="javascript:void(0)">17分</a>
														<a href="javascript:void(0)">18分</a>
														<a href="javascript:void(0)">19分</a>
														<a href="javascript:void(0)">20分</a>
														<a href="javascript:void(0)">21分</a>
														<a href="javascript:void(0)">22分</a>
														<a href="javascript:void(0)">23分</a>
														<a href="javascript:void(0)">24分</a>
														<a href="javascript:void(0)">25分</a>
														<a href="javascript:void(0)">26分</a>
														<a href="javascript:void(0)">27分</a>
														<a href="javascript:void(0)">28分</a>
														<a href="javascript:void(0)">29分</a>
														<a href="javascript:void(0)">30分</a>
														<a href="javascript:void(0)">31分</a>
														<a href="javascript:void(0)">32分</a>
														<a href="javascript:void(0)">33分</a>
														<a href="javascript:void(0)">34分</a>
														<a href="javascript:void(0)">35分</a>
														<a href="javascript:void(0)">36分</a>
														<a href="javascript:void(0)">37分</a>
														<a href="javascript:void(0)">38分</a>
														<a href="javascript:void(0)">39分</a>
														<a href="javascript:void(0)">40分</a>
														<a href="javascript:void(0)">41分</a>
														<a href="javascript:void(0)">42分</a>
														<a href="javascript:void(0)">43分</a>
														<a href="javascript:void(0)">44分</a>
														<a href="javascript:void(0)">45分</a>
														<a href="javascript:void(0)">46分</a>
														<a href="javascript:void(0)">47分</a>
														<a href="javascript:void(0)">48分</a>
														<a href="javascript:void(0)">49分</a>
														<a href="javascript:void(0)">50分</a>
														<a href="javascript:void(0)">51分</a>
														<a href="javascript:void(0)">52分</a>
														<a href="javascript:void(0)">53分</a>
														<a href="javascript:void(0)">54分</a>
														<a href="javascript:void(0)">55分</a>
														<a href="javascript:void(0)">56分</a>
														<a href="javascript:void(0)">57分</a>
														<a href="javascript:void(0)">58分</a>
														<a href="javascript:void(0)">59分</a>
													</div>
												</div>
											</li>
										</ul>
								</div>
								<div class="clear"></div>
							</div>
							
							<!-- 上传历史宣讲会封面图片 -->
							
							
							<div class="spm-cons-details">
								<div class="spm-img-info">
									<span class="spm-tips">设置封面图<i>图片大小限制1 MB以内</i></span>
									<div class="img-see" id="historyImg">
										<a href="javascript:void(0)" class="updata-img-btn">
											<img src="<%=path%>/images/list-img.jpg" width="80" height="80"/>
										</a>
										<a href="javascript:void(0)" class="delete-imgCover"></a>
									</div>
									<div class="upload-img">
										<div class="imgCover upCoverImg-btn fl" style="border:1px solid #E8E8E8;" id="uploadhistoryImgUrlTrigger">
											<a href="javascript:void(0)" class="upCoverImgBtn"></a>
										</div>
									</div>
									
									<div id="upHistoryImg" style="display:none;"></div>
									<input name="coverImagePath" style="display:none;" id="historyImgUrl" />
									<div class="clear"></div>
								</div>
								<div class="spm-img-info spm-videos-info">
									<div>
										<span class="spm-tips fl">上传视频 </span>
										<div class="help-lists fl">
											<a href="javascript:void(0)"  class="help-icon"></a>
											
											<div class="help-details">
												<span class="inverted-triangle"></span>
												<p>支持视频格式:mp4</p>
											</div>
										</div>
										<i class="small-tips">视频不能超过2G</i>
									</div>
									<div class="spm-videos">
										<a href="javascript:void(0)" id="upHistoryVedioTrriger">选择文件</a>
									</div>
									<input type="hidden" id="upHistoryVedioUrl" name="xjhAnnexId"/>
								</div>
								<div class="clear"></div>
							</div>
							
							<div class="spm-style-set remark">
								<span class="spm-tips">视频介绍</span>
								<textarea id="history-memo" name="memo"></textarea>
							</div>
							
							<div class="submit-box">
								<a href="javascript:void(0)" id="historyVedio">发布 </a>
							</div>
						</form>	
						
						<!-- 上传ing -->
						<div id="upHistoryVedio"></div>	
						
						<!-- 上传成功 开始 -->
						<div class="uploadVedioSuccess" id="uploadVedioSuccess">
							<div class="uvs-tile fl">
								<a href="javascript:" class="uvs-bt">
									<span class="uvs-icon"></span>
								</a>
							</div>
							
							<div class="uvs-infos fl">
								<p class="info-title">
									<span class="name">文件名:</span>
									<span class="tlt" title="文件名" id="fileName">文件名</span>
									<a href="javascript:" class="again" title="重新上传" id="upLoadAgain">重新上传</a>
								</p>
								<p class="info-tips">文件上传成功</p>
							</div>
						</div>
						<!-- 上传成功 结束 -->
						
						
						<!-- 上传失败 开始 -->
						<div class="uploadVedioSuccess uploadVediofail" id="uploadVediofail">
							<div class="uvs-tile fl">
								<a href="javascript:" class="uvs-bt">
									<span class="uvs-icon"></span>
								</a>
							</div>
							
							<div class="uvs-infos fl">
								<p class="info-title">
									<span class="name">文件名:</span>
									<span class="tlt" title="xx222xxx.mp4">xx222xxx.mp4</span>
									<a href="javascript:" class="again" title="重新上传">重新上传</a>
								</p>
								<p class="info-tips">文件上传失败，请重新上传</p>
							</div>
						</div>
						<!-- 上传失败 结束-->
					</div>
					<!--发布以往视频 结束-->
				</div>
			</div>
		<!--发布宣讲会 和 发布以往视频 结束-->
			
		<!--编辑宣讲会内容 开始-->
		<div class="send-preacp-meet edit-preacp-contents" id="edit-preacp-contents">
			<a href="javascript:void(0)" class="close-xx" id="close-preacp-contents"></a>
			<div class="edit-tlt">
				直播预告
			</div>
			<form method="post" name="xjhEidtForm" id="xjhEidtForm"> 
				<input type="hidden" id="xjhIdEdit" name="xjhId"/>
				<div class="spm-cons-details">
					<div class="spm-style-set">
						<span class="spm-tips">公司名称</span>
						<span id="entNameXjhEdit">${entName }</span>
<%-- 						<input type="text" class="spm-input" value="${entName }"> --%>
					</div>
					<div class="spm-style-set">
						<span class="spm-tips">直播预告名称</span>
						<input type="text" class="spm-input mgr-null" name="subject" id="subject-edit">
					</div>
				</div>
				
				<div class="spm-cons-details">
					<div class="spm-style-set">
						<input type="hidden" id="xjhEditStratTimeStr" name="stratTimeStr"/>
						<span class="spm-tips">预计开始时间</span>
						<ul class="mod_select" id="start_time_xjhEditStart">
								<li>
									<div class="select_box">
										<span class="select_txt startYear">2015年</span><span class="selet_open"></span>
										<div class="option">
											<a href="javascript:void(0)">2015年</a>
											<a href="javascript:void(0)">2016年</a>
											<a href="javascript:void(0)">2017年</a>
											<a href="javascript:void(0)">2018年</a>
											<a href="javascript:void(0)">2019年</a>
											<a href="javascript:void(0)">2020年</a>
											<a href="javascript:void(0)">2021年</a>
											<a href="javascript:void(0)">2022年</a>
											<a href="javascript:void(0)">2023年</a>
											<a href="javascript:void(0)">2024年</a>
											<a href="javascript:void(0)">2025年</a>
											<a href="javascript:void(0)">2026年</a>
											<a href="javascript:void(0)">2027年</a>
											<a href="javascript:void(0)">2028年</a>
											<a href="javascript:void(0)">2029年</a>
											<a href="javascript:void(0)">2030年</a>
										</div>
									</div>
								</li>
								<li>
									<div class="select_box">
										<span class="select_txt select-other startMouth">11月</span><span class="selet_open"></span>
										<div class="option option-Wfixed" >
											<a href="javascript:void(0)">1月</a>
											<a href="javascript:void(0)">2月</a>
											<a href="javascript:void(0)">3月</a>
											<a href="javascript:void(0)">4月</a>
											<a href="javascript:void(0)">5月</a>
											<a href="javascript:void(0)">6月</a>
											<a href="javascript:void(0)">7月</a>
											<a href="javascript:void(0)">8月</a>
											<a href="javascript:void(0)">9月</a>
											<a href="javascript:void(0)">10月</a>
											<a href="javascript:void(0)">11月</a>
											<a href="javascript:void(0)">12月</a>
										</div>
									</div>
								</li>
								
								<li>
									<div class="select_box">
										<span class="select_txt select-other startData">30日</span><span class="selet_open"></span>
										<div class="option option-Wfixed">
											<a href="javascript:void(0)">1日</a>
											<a href="javascript:void(0)">2日</a>
											<a href="javascript:void(0)">3日</a>
											<a href="javascript:void(0)">4日</a>
											<a href="javascript:void(0)">5日</a>
											<a href="javascript:void(0)">6日</a>
											<a href="javascript:void(0)">7日</a>
											<a href="javascript:void(0)">8日</a>
											<a href="javascript:void(0)">9日</a>
											<a href="javascript:void(0)">10日</a>
											<a href="javascript:void(0)">11日</a>
											<a href="javascript:void(0)">12日</a>
											<a href="javascript:void(0)">13日</a>
											<a href="javascript:void(0)">14日</a>
											<a href="javascript:void(0)">15日</a>
											<a href="javascript:void(0)">16日</a>
											<a href="javascript:void(0)">17日</a>
											<a href="javascript:void(0)">18日</a>
											<a href="javascript:void(0)">19日</a>
											<a href="javascript:void(0)">20日</a>
											<a href="javascript:void(0)">21日</a>
											<a href="javascript:void(0)">22日</a>
											<a href="javascript:void(0)">23日</a>
											<a href="javascript:void(0)">24日</a>
											<a href="javascript:void(0)">25日</a>
											<a href="javascript:void(0)">26日</a>
											<a href="javascript:void(0)">27日</a>
											<a href="javascript:void(0)">28日</a>
											<a href="javascript:void(0)">29日</a>
											<a href="javascript:void(0)">30日</a>
											<a href="javascript:void(0)">31日</a>
										</div>
									</div>
								</li>
								<li>
									<div class="select_box">
										<span class="select_txt select-other startHour">12时</span><span class="selet_open"></span>
										<div class="option option-Wfixed">
											<a href="javascript:void(0)">1时</a>
											<a href="javascript:void(0)">2时</a>
											<a href="javascript:void(0)">3时</a>
											<a href="javascript:void(0)">4时</a>
											<a href="javascript:void(0)">5时</a>
											<a href="javascript:void(0)">6时</a>
											<a href="javascript:void(0)">7时</a>
											<a href="javascript:void(0)">8时</a>
											<a href="javascript:void(0)">9时</a>
											<a href="javascript:void(0)">10时</a>
											<a href="javascript:void(0)">11时</a>
											<a href="javascript:void(0)">12时</a>
											<a href="javascript:void(0)">13时</a>
											<a href="javascript:void(0)">14时</a>
											<a href="javascript:void(0)">15时</a>
											<a href="javascript:void(0)">16时</a>
											<a href="javascript:void(0)">17时</a>
											<a href="javascript:void(0)">18时</a>
											<a href="javascript:void(0)">19时</a>
											<a href="javascript:void(0)">20时</a>
											<a href="javascript:void(0)">21时</a>
											<a href="javascript:void(0)">22时</a>
											<a href="javascript:void(0)">23时</a>
											<a href="javascript:void(0)">24时</a>
										</div>
									</div>
								</li>
								<li>
									<div class="select_box">
										<span class="select_txt select-other startMinutes">11分</span><span class="selet_open"></span>
										<div class="option option-Wfixed">
											<a href="javascript:void(0)">0分</a>
											<a href="javascript:void(0)">1分</a>
											<a href="javascript:void(0)">2分</a>
											<a href="javascript:void(0)">3分</a>
											<a href="javascript:void(0)">4分</a>
											<a href="javascript:void(0)">5分</a>
											<a href="javascript:void(0)">6分</a>
											<a href="javascript:void(0)">7分</a>
											<a href="javascript:void(0)">8分</a>
											<a href="javascript:void(0)">9分</a>
											<a href="javascript:void(0)">10分</a>
											<a href="javascript:void(0)">11分</a>
											<a href="javascript:void(0)">12分</a>
											<a href="javascript:void(0)">13分</a>
											<a href="javascript:void(0)">14分</a>
											<a href="javascript:void(0)">15分</a>
											<a href="javascript:void(0)">16分</a>
											<a href="javascript:void(0)">17分</a>
											<a href="javascript:void(0)">18分</a>
											<a href="javascript:void(0)">19分</a>
											<a href="javascript:void(0)">20分</a>
											<a href="javascript:void(0)">21分</a>
											<a href="javascript:void(0)">22分</a>
											<a href="javascript:void(0)">23分</a>
											<a href="javascript:void(0)">24分</a>
											<a href="javascript:void(0)">25分</a>
											<a href="javascript:void(0)">26分</a>
											<a href="javascript:void(0)">27分</a>
											<a href="javascript:void(0)">28分</a>
											<a href="javascript:void(0)">29分</a>
											<a href="javascript:void(0)">30分</a>
											<a href="javascript:void(0)">31分</a>
											<a href="javascript:void(0)">32分</a>
											<a href="javascript:void(0)">33分</a>
											<a href="javascript:void(0)">34分</a>
											<a href="javascript:void(0)">35分</a>
											<a href="javascript:void(0)">36分</a>
											<a href="javascript:void(0)">37分</a>
											<a href="javascript:void(0)">38分</a>
											<a href="javascript:void(0)">39分</a>
											<a href="javascript:void(0)">40分</a>
											<a href="javascript:void(0)">41分</a>
											<a href="javascript:void(0)">42分</a>
											<a href="javascript:void(0)">43分</a>
											<a href="javascript:void(0)">44分</a>
											<a href="javascript:void(0)">45分</a>
											<a href="javascript:void(0)">46分</a>
											<a href="javascript:void(0)">47分</a>
											<a href="javascript:void(0)">48分</a>
											<a href="javascript:void(0)">49分</a>
											<a href="javascript:void(0)">50分</a>
											<a href="javascript:void(0)">51分</a>
											<a href="javascript:void(0)">52分</a>
											<a href="javascript:void(0)">53分</a>
											<a href="javascript:void(0)">54分</a>
											<a href="javascript:void(0)">55分</a>
											<a href="javascript:void(0)">56分</a>
											<a href="javascript:void(0)">57分</a>
											<a href="javascript:void(0)">58分</a>
											<a href="javascript:void(0)">59分</a>
										</div>
									</div>
								</li>
							</ul>
					</div>
				</div>
				
				
				<div class="spm-cons-details">
					<div class="spm-style-set">
						<span class="spm-tips">预计结束时间</span>
						<input type="hidden" id="xjhEditEndTimeStr" name="endTimeStr"/>
						<ul class="mod_select end-timer" id="end_time_xjhEditEnd">
								<li>
									<div class="select_box">
										<span class="select_txt endYear">2015年</span><span class="selet_open"></span>
										<div class="option">
											<a href="javascript:void(0)">2015年</a>
											<a href="javascript:void(0)">2016年</a>
											<a href="javascript:void(0)">2017年</a>
											<a href="javascript:void(0)">2018年</a>
											<a href="javascript:void(0)">2019年</a>
											<a href="javascript:void(0)">2020年</a>
											<a href="javascript:void(0)">2021年</a>
											<a href="javascript:void(0)">2022年</a>
											<a href="javascript:void(0)">2023年</a>
											<a href="javascript:void(0)">2024年</a>
											<a href="javascript:void(0)">2025年</a>
											<a href="javascript:void(0)">2026年</a>
											<a href="javascript:void(0)">2027年</a>
											<a href="javascript:void(0)">2028年</a>
											<a href="javascript:void(0)">2029年</a>
											<a href="javascript:void(0)">2030年</a>
										</div>
									</div>
								</li>
								<li>
									<div class="select_box">
										<span class="select_txt select-other endMouth">11月</span><span class="selet_open"></span>
										<div class="option option-Wfixed">
											<a href="javascript:void(0)">1月</a>
											<a href="javascript:void(0)">2月</a>
											<a href="javascript:void(0)">3月</a>
											<a href="javascript:void(0)">4月</a>
											<a href="javascript:void(0)">5月</a>
											<a href="javascript:void(0)">6月</a>
											<a href="javascript:void(0)">7月</a>
											<a href="javascript:void(0)">8月</a>
											<a href="javascript:void(0)">9月</a>
											<a href="javascript:void(0)">10月</a>
											<a href="javascript:void(0)">11月</a>
											<a href="javascript:void(0)">12月</a>
										</div>
									</div>
								</li>
								
								<li>
									<div class="select_box">
										<span class="select_txt select-other endData">30日</span><span class="selet_open"></span>
										<div class="option option-Wfixed">
											<a href="javascript:void(0)">1日</a>
											<a href="javascript:void(0)">2日</a>
											<a href="javascript:void(0)">3日</a>
											<a href="javascript:void(0)">4日</a>
											<a href="javascript:void(0)">5日</a>
											<a href="javascript:void(0)">6日</a>
											<a href="javascript:void(0)">7日</a>
											<a href="javascript:void(0)">8日</a>
											<a href="javascript:void(0)">9日</a>
											<a href="javascript:void(0)">10日</a>
											<a href="javascript:void(0)">11日</a>
											<a href="javascript:void(0)">12日</a>
											<a href="javascript:void(0)">13日</a>
											<a href="javascript:void(0)">14日</a>
											<a href="javascript:void(0)">15日</a>
											<a href="javascript:void(0)">16日</a>
											<a href="javascript:void(0)">17日</a>
											<a href="javascript:void(0)">18日</a>
											<a href="javascript:void(0)">19日</a>
											<a href="javascript:void(0)">20日</a>
											<a href="javascript:void(0)">21日</a>
											<a href="javascript:void(0)">22日</a>
											<a href="javascript:void(0)">23日</a>
											<a href="javascript:void(0)">24日</a>
											<a href="javascript:void(0)">25日</a>
											<a href="javascript:void(0)">26日</a>
											<a href="javascript:void(0)">27日</a>
											<a href="javascript:void(0)">28日</a>
											<a href="javascript:void(0)">29日</a>
											<a href="javascript:void(0)">30日</a>
											<a href="javascript:void(0)">31日</a>
										</div>
									</div>
								</li>
								<li>
									<div class="select_box">
										<span class="select_txt select-other endHour">12时</span><span class="selet_open"></span>
										<div class="option option-Wfixed">
											<a href="javascript:void(0)">1时</a>
											<a href="javascript:void(0)">2时</a>
											<a href="javascript:void(0)">3时</a>
											<a href="javascript:void(0)">4时</a>
											<a href="javascript:void(0)">5时</a>
											<a href="javascript:void(0)">6时</a>
											<a href="javascript:void(0)">7时</a>
											<a href="javascript:void(0)">8时</a>
											<a href="javascript:void(0)">9时</a>
											<a href="javascript:void(0)">10时</a>
											<a href="javascript:void(0)">11时</a>
											<a href="javascript:void(0)">12时</a>
											<a href="javascript:void(0)">13时</a>
											<a href="javascript:void(0)">14时</a>
											<a href="javascript:void(0)">15时</a>
											<a href="javascript:void(0)">16时</a>
											<a href="javascript:void(0)">17时</a>
											<a href="javascript:void(0)">18时</a>
											<a href="javascript:void(0)">19时</a>
											<a href="javascript:void(0)">20时</a>
											<a href="javascript:void(0)">21时</a>
											<a href="javascript:void(0)">22时</a>
											<a href="javascript:void(0)">23时</a>
											<a href="javascript:void(0)">24时</a>
										</div>
									</div>
								</li>
								<li>
									<div class="select_box">
										<span class="select_txt select-other endMinutes">11分</span><span class="selet_open"></span>
										<div class="option option-Wfixed">
											<a href="javascript:void(0)">0分</a>
											<a href="javascript:void(0)">1分</a>
											<a href="javascript:void(0)">2分</a>
											<a href="javascript:void(0)">3分</a>
											<a href="javascript:void(0)">4分</a>
											<a href="javascript:void(0)">5分</a>
											<a href="javascript:void(0)">6分</a>
											<a href="javascript:void(0)">7分</a>
											<a href="javascript:void(0)">8分</a>
											<a href="javascript:void(0)">9分</a>
											<a href="javascript:void(0)">10分</a>
											<a href="javascript:void(0)">11分</a>
											<a href="javascript:void(0)">12分</a>
											<a href="javascript:void(0)">13分</a>
											<a href="javascript:void(0)">14分</a>
											<a href="javascript:void(0)">15分</a>
											<a href="javascript:void(0)">16分</a>
											<a href="javascript:void(0)">17分</a>
											<a href="javascript:void(0)">18分</a>
											<a href="javascript:void(0)">19分</a>
											<a href="javascript:void(0)">20分</a>
											<a href="javascript:void(0)">21分</a>
											<a href="javascript:void(0)">22分</a>
											<a href="javascript:void(0)">23分</a>
											<a href="javascript:void(0)">24分</a>
											<a href="javascript:void(0)">25分</a>
											<a href="javascript:void(0)">26分</a>
											<a href="javascript:void(0)">27分</a>
											<a href="javascript:void(0)">28分</a>
											<a href="javascript:void(0)">29分</a>
											<a href="javascript:void(0)">30分</a>
											<a href="javascript:void(0)">31分</a>
											<a href="javascript:void(0)">32分</a>
											<a href="javascript:void(0)">33分</a>
											<a href="javascript:void(0)">34分</a>
											<a href="javascript:void(0)">35分</a>
											<a href="javascript:void(0)">36分</a>
											<a href="javascript:void(0)">37分</a>
											<a href="javascript:void(0)">38分</a>
											<a href="javascript:void(0)">39分</a>
											<a href="javascript:void(0)">40分</a>
											<a href="javascript:void(0)">41分</a>
											<a href="javascript:void(0)">42分</a>
											<a href="javascript:void(0)">43分</a>
											<a href="javascript:void(0)">44分</a>
											<a href="javascript:void(0)">45分</a>
											<a href="javascript:void(0)">46分</a>
											<a href="javascript:void(0)">47分</a>
											<a href="javascript:void(0)">48分</a>
											<a href="javascript:void(0)">49分</a>
											<a href="javascript:void(0)">50分</a>
											<a href="javascript:void(0)">51分</a>
											<a href="javascript:void(0)">52分</a>
											<a href="javascript:void(0)">53分</a>
											<a href="javascript:void(0)">54分</a>
											<a href="javascript:void(0)">55分</a>
											<a href="javascript:void(0)">56分</a>
											<a href="javascript:void(0)">57分</a>
											<a href="javascript:void(0)">58分</a>
											<a href="javascript:void(0)">59分</a>
										</div>
									</div>
								</li>
							</ul>
					</div>
				</div>
				
				<div class="spm-style-set frontCover-img clear">
					<span class="spm-tips">设置封面图
						<i>图片大小限制1 MB以内</i>
					</span>
					<div class="upload-img">
						<div class="imgCover fl" id="xhjImgEdit" style="display:none;">
							<a href="javascript:void(0)"><img src="<%=path%>/images/list-img.jpg" width="78" height="78"></a>
							<a href="javascript:void(0)" class="delete-imgCover"></a>
						</div> 
						<div class="imgCover upCoverImg-btn fl" id="uploadImgEditTrigger">
							<a href="javascript:void(0)" class="upCoverImgBtn"></a>
						</div>
						<div id="uploadImgEdit" style="display:none;"></div>
						<div class="clear"></div>
					</div>
				</div>
				<input type="hidden" id="xjhAnnexIdEdit" name="coverImagePath"/>
				<div class="spm-style-set remark">
					<span class="spm-tips">视频直播介绍</span>
					<textarea id="detailXjhEdit" name="memo"></textarea>
				</div>
				
				<div class="submit-box">
					<a href="javascript:void(0)" onclick="xjhEditSave();">发布 </a>
				</div>
			</form>						
		</div>
		<!--编辑宣讲会内容 结束-->
		
		<!--视频播放 开始-->
		<div id="video-contents">
			<div class="vd-title">
				<p id="vedioName">宣讲会预告/视频</p>
				<a href="javascript:void(0)" class="close-xx" id="close-video" title="关闭"></a>
			</div>
			<div class="vd-panelBox">
			 <video id="example_video_1" class="video-js vjs-default-skin" controls preload="none" width="855" height="556"
			      poster=""
			      data-setup="{}">
			    <source src="http://img1.kaipin.tv:81/Qy3/vedio/dae7bcc3-199a-496a-ae0d-80143b179d09.mp4" type='video/mp4' />
			    <p class="vjs-no-js">To view this video please enable JavaScript, and consider upgrading to a web browser that 
				    <a href="http://videojs.com/html5-video-support/" target="_blank">supports HTML5 video
				    </a>
			    </p>
			 </video>
			</div>
		</div>
		<!--视频播放 结束-->
		
		
		<!--编辑以往视频 开始-->
			<div id="historyXjhEdit" class="preacp-and-oldvideo" style="display: none;" >
				<a href="javascript:void(0)" class="close-xx" id="close-meetingss"></a>
				<div class="edit-tlt">
					编辑点播视频
				</div>
				<div class="meeting-detaiils" id="change-meeting-detaiils">
					<div class="send-preacp-meet meetings  send-old-videos" style="display: block;" >
						<form method="post" name="historyEidtForm" id="historyEidtForm" action="<%=path%>/entMeetingNoticeController/add.do">
							<div class="spm-cons-details">
								<div class="spm-style-set">
									<span class="spm-tips">公司名称</span>
									<span id="entNameHistroyEdit">${entName }</span>
<%-- 									<input type="text" readonly="readonly" value="${entName }" class="spm-input"> --%>
								</div>
								<input type="text" style="display:none;" value="3" name="type" />
								<input type="text" style="display:none;" name="xjhId" id="historyXjhId"/>
								<div class="spm-style-set">
									<span class="spm-tips">视频名称</span>
									<input type="text" id="history-subject-edit" name="subject" class="spm-input  mgr-null">
								</div>
								<div class="clear"></div>
							</div>
							
							<div class="spm-cons-details">
								<div class="spm-style-set">
									<span class="spm-tips">拍摄时间</span>
									<input type="hidden" name="stratTimeStr" id="history-startTimeEdit"/>
									<ul id="start_time_history_edit" class="mod_select">
											<li>
												<div class="select_box">
													<span class="select_txt startYear">2015年</span><span class="selet_open"></span>
													<div class="option">
														<a href="javascript:void(0)">2015年</a>
														<a href="javascript:void(0)">2016年</a>
														<a href="javascript:void(0)">2017年</a>
														<a href="javascript:void(0)">2018年</a>
														<a href="javascript:void(0)">2019年</a>
														<a href="javascript:void(0)">2020年</a>
														<a href="javascript:void(0)">2021年</a>
														<a href="javascript:void(0)">2022年</a>
														<a href="javascript:void(0)">2023年</a>
														<a href="javascript:void(0)">2024年</a>
														<a href="javascript:void(0)">2025年</a>
														<a href="javascript:void(0)">2026年</a>
														<a href="javascript:void(0)">2027年</a>
														<a href="javascript:void(0)">2028年</a>
														<a href="javascript:void(0)">2029年</a>
														<a href="javascript:void(0)">2030年</a>
													</div>
												</div>
											</li>
											<li>
												<div class="select_box">
													<span class="select_txt select-other startMouth">11月</span><span class="selet_open"></span>
													<div class="option option-Wfixed" >
														<a href="javascript:void(0)">1月</a>
														<a href="javascript:void(0)">2月</a>
														<a href="javascript:void(0)">3月</a>
														<a href="javascript:void(0)">4月</a>
														<a href="javascript:void(0)">5月</a>
														<a href="javascript:void(0)">6月</a>
														<a href="javascript:void(0)">7月</a>
														<a href="javascript:void(0)">8月</a>
														<a href="javascript:void(0)">9月</a>
														<a href="javascript:void(0)">10月</a>
														<a href="javascript:void(0)">11月</a>
														<a href="javascript:void(0)">12月</a>
													</div>
												</div>
											</li>
											
											<li>
												<div class="select_box">
													<span class="select_txt select-other startData">30日</span><span class="selet_open"></span>
													<div class="option option-Wfixed">
														<a href="javascript:void(0)">1日</a>
														<a href="javascript:void(0)">2日</a>
														<a href="javascript:void(0)">3日</a>
														<a href="javascript:void(0)">4日</a>
														<a href="javascript:void(0)">5日</a>
														<a href="javascript:void(0)">6日</a>
														<a href="javascript:void(0)">7日</a>
														<a href="javascript:void(0)">8日</a>
														<a href="javascript:void(0)">9日</a>
														<a href="javascript:void(0)">10日</a>
														<a href="javascript:void(0)">11日</a>
														<a href="javascript:void(0)">12日</a>
														<a href="javascript:void(0)">13日</a>
														<a href="javascript:void(0)">14日</a>
														<a href="javascript:void(0)">15日</a>
														<a href="javascript:void(0)">16日</a>
														<a href="javascript:void(0)">17日</a>
														<a href="javascript:void(0)">18日</a>
														<a href="javascript:void(0)">19日</a>
														<a href="javascript:void(0)">20日</a>
														<a href="javascript:void(0)">21日</a>
														<a href="javascript:void(0)">22日</a>
														<a href="javascript:void(0)">23日</a>
														<a href="javascript:void(0)">24日</a>
														<a href="javascript:void(0)">25日</a>
														<a href="javascript:void(0)">26日</a>
														<a href="javascript:void(0)">27日</a>
														<a href="javascript:void(0)">28日</a>
														<a href="javascript:void(0)">29日</a>
														<a href="javascript:void(0)">30日</a>
														<a href="javascript:void(0)">31日</a>
													</div>
												</div>
											</li>
											<li>
												<div class="select_box">
													<span class="select_txt select-other startHour">12时</span><span class="selet_open"></span>
													<div class="option option-Wfixed">
														<a href="javascript:void(0)">1时</a>
														<a href="javascript:void(0)">2时</a>
														<a href="javascript:void(0)">3时</a>
														<a href="javascript:void(0)">4时</a>
														<a href="javascript:void(0)">5时</a>
														<a href="javascript:void(0)">6时</a>
														<a href="javascript:void(0)">7时</a>
														<a href="javascript:void(0)">8时</a>
														<a href="javascript:void(0)">9时</a>
														<a href="javascript:void(0)">10时</a>
														<a href="javascript:void(0)">11时</a>
														<a href="javascript:void(0)">12时</a>
														<a href="javascript:void(0)">13时</a>
														<a href="javascript:void(0)">14时</a>
														<a href="javascript:void(0)">15时</a>
														<a href="javascript:void(0)">16时</a>
														<a href="javascript:void(0)">17时</a>
														<a href="javascript:void(0)">18时</a>
														<a href="javascript:void(0)">19时</a>
														<a href="javascript:void(0)">20时</a>
														<a href="javascript:void(0)">21时</a>
														<a href="javascript:void(0)">22时</a>
														<a href="javascript:void(0)">23时</a>
														<a href="javascript:void(0)">24时</a>
													</div>
												</div>
											</li>
											<li>
												<div class="select_box">
													<span class="select_txt select-other startMinutes">11分</span><span class="selet_open"></span>
													<div class="option option-Wfixed">
														<a href="javascript:void(0)">0分</a>
														<a href="javascript:void(0)">1分</a>
														<a href="javascript:void(0)">2分</a>
														<a href="javascript:void(0)">3分</a>
														<a href="javascript:void(0)">4分</a>
														<a href="javascript:void(0)">5分</a>
														<a href="javascript:void(0)">6分</a>
														<a href="javascript:void(0)">7分</a>
														<a href="javascript:void(0)">8分</a>
														<a href="javascript:void(0)">9分</a>
														<a href="javascript:void(0)">10分</a>
														<a href="javascript:void(0)">11分</a>
														<a href="javascript:void(0)">12分</a>
														<a href="javascript:void(0)">13分</a>
														<a href="javascript:void(0)">14分</a>
														<a href="javascript:void(0)">15分</a>
														<a href="javascript:void(0)">16分</a>
														<a href="javascript:void(0)">17分</a>
														<a href="javascript:void(0)">18分</a>
														<a href="javascript:void(0)">19分</a>
														<a href="javascript:void(0)">20分</a>
														<a href="javascript:void(0)">21分</a>
														<a href="javascript:void(0)">22分</a>
														<a href="javascript:void(0)">23分</a>
														<a href="javascript:void(0)">24分</a>
														<a href="javascript:void(0)">25分</a>
														<a href="javascript:void(0)">26分</a>
														<a href="javascript:void(0)">27分</a>
														<a href="javascript:void(0)">28分</a>
														<a href="javascript:void(0)">29分</a>
														<a href="javascript:void(0)">30分</a>
														<a href="javascript:void(0)">31分</a>
														<a href="javascript:void(0)">32分</a>
														<a href="javascript:void(0)">33分</a>
														<a href="javascript:void(0)">34分</a>
														<a href="javascript:void(0)">35分</a>
														<a href="javascript:void(0)">36分</a>
														<a href="javascript:void(0)">37分</a>
														<a href="javascript:void(0)">38分</a>
														<a href="javascript:void(0)">39分</a>
														<a href="javascript:void(0)">40分</a>
														<a href="javascript:void(0)">41分</a>
														<a href="javascript:void(0)">42分</a>
														<a href="javascript:void(0)">43分</a>
														<a href="javascript:void(0)">44分</a>
														<a href="javascript:void(0)">45分</a>
														<a href="javascript:void(0)">46分</a>
														<a href="javascript:void(0)">47分</a>
														<a href="javascript:void(0)">48分</a>
														<a href="javascript:void(0)">49分</a>
														<a href="javascript:void(0)">50分</a>
														<a href="javascript:void(0)">51分</a>
														<a href="javascript:void(0)">52分</a>
														<a href="javascript:void(0)">53分</a>
														<a href="javascript:void(0)">54分</a>
														<a href="javascript:void(0)">55分</a>
														<a href="javascript:void(0)">56分</a>
														<a href="javascript:void(0)">57分</a>
														<a href="javascript:void(0)">58分</a>
														<a href="javascript:void(0)">59分</a>
													</div>
												</div>
											</li>
										</ul>
								</div>
								<div class="clear"></div>
							</div>
							
							<div class="spm-cons-details">
								<div class="spm-img-info">
									<span class="spm-tips">设置封面图<i>图片大小限制1 MB以内</i></span>
									<div class="img-see" id="historyImgEidt">
										<a href="javascript:void(0)" class="updata-img-btn">
											<img src="<%=path%>/images/list-img.jpg" width="80" height="80"/>
										</a>
										<a href="javascript:void(0)" class="delete-imgCover"></a>
									</div>
									<div class="imgCover upCoverImg-btn fl" style="border:1px solid #E8E8E8;" id="upHistoryImgEditTrigger">
										<a href="javascript:void(0)" class="upCoverImgBtn"></a>
									</div>
									<div id="upHistoryImgEdit" style="display:none;"></div>
									<input type="hidden" name="coverImagePath" id="historyImgUrlEidt" />
									<div class="clear"></div>
								</div>
								<div class="spm-img-info spm-videos-info">
									<div>
										<span class="spm-tips fl">上传视频 </span>
										<div class="help-lists fl">
											<a href="javascript:void(0)"  class="help-icon"></a>
											
											<div class="help-details">
												<span class="inverted-triangle"></span>
												<p>支持视频格式:mp4</p>
											</div>
										</div>
										<i class="small-tips">视频不能超过2G</i>
									</div>
									<div class="spm-videos">
										<a href="javascript:void(0)" id="upHistoryVedioTrrigerEdit">选择文件</a>
									</div>
									<input type="hidden" id="upHistoryVedioUrlEdit" name="xjhAnnexId"/>
								</div>
								<div class="clear"></div>
							</div>
							
							<div class="spm-style-set remark">
								<span class="spm-tips">视频介绍</span>
								<textarea id="history-memo-edit" name="memo"></textarea>
							</div>
							
							<div class="submit-box">
								<a href="javascript:void(0)" onclick="historyVedioEditSave();" id="historyVedioEdit">发布 </a>
							</div>
						</form>	
	
						<!-- 上传ing -->
						<div id="upHistoryVedioEdit"></div>	
						
						
						<!-- 上传成功 开始 -->
						<div class="uploadVedioSuccess" id="uploadVedioSuccessEdit">
							<div class="uvs-tile fl">
<%-- 								<img src="<%=path%>/images/list-img.jpg" width="70" height="70" alt=""> --%>
								<a href="javascript:" class="uvs-bt">
									<span class="uvs-icon"></span>
								</a>
							</div>
							
							<div class="uvs-infos fl">
								<p class="info-title">
									<span class="name">文件名:</span>
									<span class="tlt" title="文件名" id="fileNameEidt"></span>
									<a href="javascript:" class="again" title="重新上传" id="upLoadAgainEdit">重新上传</a>
								</p>
								<p class="info-tips">文件已审核通过</p>
							</div>
						</div>
						<!-- 上传成功 结束 -->
						
						
						<!-- 上传失败 开始 -->
						<div class="uploadVedioSuccess uploadVediofail" id="uploadVediofailEdit">
							<div class="uvs-tile fl">
								<a href="javascript:" class="uvs-bt">
									<span class="uvs-icon"></span>
								</a>
							</div>
							
							<div class="uvs-infos fl">
								<p class="info-title">
									<span class="name">文件名:</span>
									<span class="tlt" title="xx222xxx.mp4">xx222xxx.mp4</span>
									<a href="javascript:" class="again" title="重新上传">重新上传</a>
								</p>
								<p class="info-tips">文件上传失败，请重新上传</p>
							</div>
						</div>
						<!-- 上传失败 结束-->				
					</div>
				</div>
			</div>
		<!--编辑以往视频 结束-->
		
		<!--图片裁剪-->
		<div class="tailoring-dialog" style="display:none;" id="tailoring-dialog">
			<div class="dialog-title">
				<h3 class="title">编辑图片</h3>
				<a href="javascript:;" class="close-dt" id="close-img-cut">X</a>
			</div>
			<div class="bea-tip">
				<span>展现出你的最佳情况</span>
			</div>
			<div class="dialog-content">
				<!--图片展示区域 start-->
				<div class="tailoring-area">
					
					<!--图片选择-->
					<div class="help-infos" id="selectCropperBox" >
						<h5>选择照片</h5>
						<p>支持 JPG或 PNG 文件 (不超过 1 MB)。</p>
<!-- 						<input type="file" accept="image/jpeg, image/jpg, image/png, image/gif" name="file"> -->
<!-- 						<div class="spm-videos"> -->
<!-- 							<a href="javascript:void(0)" id="">选择文件</a> -->
<!-- 						</div> -->
						<button id="uploadLogoTrigger">选择文件</button>
					</div>
					
					
					<!--剪裁区域   1 : 1-->
					<div class="cropperBox" id="cropperBox" style="display: none;">
						<div class="help-infos">
							<h5>调整图像</h5>
							<p>拖拽蓝色方框，更改头像位置和尺寸 <a href="javascript:void(0);" id="selectAgin">重新选择</a></p>
						</div>
						
						<div class="photo-edit-container" id="photo-edit-container">
							<div class="pe-imgContainer" id="pe-imgContainer">
								<img src="<%=path %>/images/100.jpg" id="image" />
								<!--<div class="edit-corp-box" id="small-slider"></div>-->
							</div>
							
						</div>
						
					</div>
					<!--剪裁区域	16 : 9-->
					<div class="cropperBox" id="cropperBox169" style="display: none;">
						<div class="help-infos">
							<h5>调整图像</h5>
							<p>拖拽蓝色方框，更改头像位置和尺寸 <a href="javascript:void(0);" id="selectAginBg">重新选择</a></p>
						</div>
						
						<div class="photo-edit-container">
							<div class="pe-imgContainer">
								<img src="<%=path %>/images/fang_img.jpg" id="image169" />
								<!--<div class="edit-corp-box" id="small-slider"></div>-->
							</div>
							
						</div>
						
					</div>
				</div>
				<!--图片展示区域 end-->
				
				<!--图片预览区域 start-->
				<div class="preview-area">
					<div class="help-infos">
						<h5>预览</h5>
						<p>网站图像显示</p>
					</div>

					<!--默认预览区（未加载图片显示）-->
					<div class="preview-show defaultshow-bg" id="defaultshow-bg" style="display: none;">
						<!--正方形 默认预览区（未加载图片显示）-->
						<div class=" preview-item" id="showZFX">
							<div id="logo-preview-pic" style="dispaly:none;">
								<img src="<%=path %>/images/100.jpg" width="130" height="130"/>
							</div>
							<div id="others-preview-pic" style="dispaly:none;">
								<img src="<%=path %>/images/fang_img.jpg" width="130" height="130"/>
							</div>
							<%-- <div class="size100 defaultBgCl">
								<img src="<%=path %>/images/100.jpg" width="100" height="100"/>
							</div>
							
							<div class="other-size">
								<div class="size50 defaultBgCl">
									<img src="<%=path %>/images/50.jpg" width="50" height="50"/>
								</div>
								
								<div class="size30 defaultBgCl">
									<img src="<%=path %>/images/30.jpg" width="30" height="30"/>									
								</div>
								
								<div class="size50Roand defaultBgCl">
									<img src="<%=path %>/images/50.png" width="50" height="50"/>
								</div>
							</div> --%>
						</div>
						
						<!--长方形 默认预览区（未加载图片显示）-->
						<div id="showCFX" style="display:none;width:160px;height:40px;background:url(<%=path %>/images/bg_img.jpg);background-size:100% 100%;"></div>
					
					</div>
					
					<!--预览区（已经载图片完毕后显示（真正预览图片区））-->
					<div class="preview-show"  id="preview-show-preview">
						<div class=" preview-item">
							<div class="size100 defaultBgCl" id="defaultBgCl" style="width:100px;height:100px;display:none;">
								<div class="preview" style="overflow: hidden;width:100px;height:100px;"></div>
								<!--<img src="images/picture.jpg" id="ferret"/>-->
							</div>
							
							<div class="size169 defaultBgCl" id="defaultBgCl169" style="display:none; width:160px;height:40px">
								<div class="preview1" style="overflow: hidden;width:160px;height:40px;"></div>
							</div>
							
							
							<div class="other-size" id="others-show" style="display:none;">
								<div class="size50 defaultBgCl">
									<div class="preview" style="overflow: hidden;width:50px;height:50px"></div>
									<!--<img src="images/picture.jpg" id="ferret"/>-->
								</div>
								
								<div class="size30 defaultBgCl">
									<div class="preview" style="overflow: hidden;width:30px;height:30px"></div>
								</div>
								
								<div class="size50Roand defaultBgCl">
									<div class="preview" style="overflow: hidden;width:50px;height:50px"></div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!--图片预览区域 end-->
			</div>
			
			<!--正在加载ing-->
			<div class="loading-spinner" id="loading-spinner">
				<img src="<%=path %>/images/5-121204193R0-50.gif"/>
			</div>
			
			<!--button-->
			<div class="actionsbox">
				<input type="button" class="btn-save" value="确定" id="getData"/>
				<input type="button" class="btn-cancel" value="确定..." id="getDataIng" style="display:none;"/>
				<input type="button" class="btn-cancel" id="cancle-img-cut" value="取消" />
			</div>
		</div>
		<!--图片裁剪-->
		<input type="hidden" id="fileNameCut" value=""/>
		<input type="hidden" id="operTypeCut" value=""/>
		<input type="hidden" id="cutData" value=""/>
		
		<!-- 		 footer-start -->
		<%@ include file="/WEB-INF/pages/main/footer.jsp"%>
		<!-- 		 footer-end -->
		
		
	</body>
</html>
