<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
 	<%@ include file="/WEB-INF/pages/regedit/comment/regHeader.jsp" %>
	<script type="text/javascript">
		var r_path='<%=basePath%>';
	</script>
	
	
	
  </head>
  
  <body>
		<!--header start-->
		<div class="lm-header-container">
			<div class="header">
				<h1>
					<a href="javascript:void(0)" class="logo backIndexLogin"></a>
					<span class="tips-txt"> - 创建您的开频账户</span>
				</h1>
			</div>
		</div>
		<!--header end-->
		
		<!--注册流程 start-->
		<div class="sessionRegister">
			<div class="flowsheet">
				<dl class="sheet1">
					<dt>
						<span class="ll-bg ll-bg-three">1</span>
					</dt>
					<dd class="lq-color-one">创建账号</dd>
				</dl>
				<dl class="sheet2">
					<dt>
						<span class="ll-bg ll-bg-three">2</span>
					</dt>
					<dd class="lq-color-one">选择身份</dd>
				</dl>
				<dl class="sheet3">
					<dt>
						<span class="ll-bg ll-bg-two">3</span>
					</dt>
					<dd class="lq-color-two">填基本信息</dd>
				</dl>
				<dl class="sheet4">
					<dt>
						<span class="ll-bg ll-bg-one">4</span>
					</dt>
					<dd class="lq-color-three">提交认证</dd>
				</dl>
				<div class="line line1 line-color-sure""></div>
				<div class="line line2 line-color-sure""></div>
				<div class="line line3"></div>
			</div>
			<div class="submit-reg-infos">
				<div class="sri-contents">
					<form action="<%=path %>/regedit/regeditBasic" method="post" id="regetStuForm">
						<div class="basic-informations">
							<div class="infos-lists">
								<p class="tle-tips"> 姓名</p>
								<div class="input-infos  contact-txt">
									<div class="ipt-txt" id="userName-erro">
										<input type="" name="missSurname" id="userName" value="" placeholder="名字，如：云"/>
									</div>
									<span class="error-info" id="userNameTip">输入错误</span>
								</div>
								<div class="input-infos  contact-txt contact-txt-surname">
									<div class="ipt-txt" id="userSurname-erro">
										<input type="" name="surname" id="userSurname" value="" placeholder="姓氏，如：马"/>
									</div>
									<span class="error-info" id="userSurnameTip">输入错误</span>
								</div>
								<span class="clear"></span>
							</div>
							
							<div class="infos-lists lenovo">
								<p class="tle-tips">学校名称</p>
								<div class="input-infos">
									<div class="ipt-txt schoolNameErro" id="entName-erro">
										<input type="" name="schoolName" id="entName" value="" placeholder="填写毕业学校全称，如：北京大学"/>
										<input type="hidden" name="schoolCode" id="schoolCode" value="" placeholder="填写毕业学校名称"/>
									</div>
									<span class="error-info schoolNameErroTip" id="entNameTip">输入错误</span>
								</div>
								<div class="lenovoBox" id="lenovoBox" style="display: none;">
									<ul>
									</ul>
								</div>
								<span class="clear"></span>
							</div>
							
							<div class="infos-lists lenovo">
								<p class="tle-tips">专业名称</p>
								<div class="input-infos ">
									<div class="ipt-txt" id="majorName-erro">
										<input type="" name="majorName" id="majorName" value="" placeholder="填写所读专业全称，如：计算机科学与技术"/>
										<input type="hidden" name="majorCode" id="majorCode" value="" placeholder="填写所读专业全称，如：计算机科学与技术"/>
									</div>
									<div class="lenovenBox"></div>
									<span class="error-info" id="majorNameTip">输入错误</span>
								</div>
								<div class="lenovoBox" id="majorBox" >
									<ul>
									</ul>
								</div>
								<span class="clear"></span>
							</div>
							
							
							<div class="infos-lists the-areaOf-selectDown">
								<p class="tle-tips">最高学历</p>
								
								<div class="input-infos " >
									<div class="input-infos adress-select-txt"  style="float: left;">
										<div class="ipt-txt sel" id="educationCode-erro">
											<span >请选择最高学历</span>
										</div>
										<select class="selectDown" name="educationCode" id="educationCode">
											<option value="0">请选择最高学历</option>
											<option value="13000001">初中</option>
											<option value="13000002">中技</option>
											<option value="13000003">高中</option>
											<option value="13000004">中专</option>
											<option value="13000005">大专</option>
											<option value="13000006">本科</option>
											<option value="13000007">硕士</option>
											<option value="13000008">MBA</option>
											<option value="13000009">EMBA</option>
											<option value="13000010">博士</option>
											<option value="13000011">其他</option>
										</select>
										<span class="error-info" >输入错误</span>
									</div>
									<span class="error-info" id="educationCodeTip">输入错误</span>
								</div>
								<span class="clear"></span>
							</div>
							
							
							<div class="infos-lists the-areaOf-selectDown">
								<p class="tle-tips"> 常住地址</p>
								<div class="input-infos adress-select-txt">
									<div class="ipt-txt sel">
										<span id="countryListSelect">请选择区县</span>
									</div>
									<select class="selectDown" id="countryList" onchange="setLoactionCode();">
										<option value="-1">请选择区县</option>
									</select>
									<span class="error-info" >输入错误</span>
								</div>
								<div class="input-infos adress-select-txt city-margin">
									<div class="ipt-txt sel" id="cityVal-erro">
										<span id="cityListSelect">请选择城市</span>
										<input type="hidden" id="cityVal"/>
									</div>
									<select class="selectDown" id="cityList" onchange="getCountryList();">
										<option value="">请选择城市</option>
									</select>
									<span class="error-info" >输入错误</span>
								</div>
								<div class="input-infos  adress-select-txt">
									<div class="ipt-txt sel" id="proviceVal-erro">
										<span>所在地区</span>
										<input type="hidden" id="proviceVal"/>
									</div>
									<select class="selectDown" id="proviceList" onchange="getCityList();">
										<option value="">所在地区</option>
										<c:forEach var="m" items="${proviceList }" >
											<option value="${m.location_code }">${m.location_name }</option>
										</c:forEach>
									</select>
									<span class="error-info" >输入错误</span>
								</div>
								<span class="clear"></span>
							</div>
						</div>
						
						<input type="hidden" name="userId" value="${localUser.id}"/>
						<input type="hidden" name="oper" value="10"/>
						
						<div class="submit-box nosel-color">
							<a href="javascript:void(0)" class="reset-btn" onclick="chooseRole();">返回</a>
							<a href="javascript:void(0)" class="sub-btn" id="stu-submit-btn">提交</a>
							<a href="javascript:void(0)" class="sub-btn" id="stu-submit-btn-ing" style="display:none;">
							提交中...<img src="<%=path%>/images/loading.gif" style="margin-left:5px;"/>
							</a>
						</div>
						
						<input type="hidden" id="locationCode" name="locationCode"/>
					</form>
				</div>
			</div>
		</div>
		<!--注册流程 end-->
		
		
		<!-- footer_reg start-->
		<%@ include file="/WEB-INF/pages/regedit/comment/regFooter.jsp" %>
		<!-- footer_reg end-->
		
		
		
		<script type="text/javascript" src="<%=path%>/js/jquery-1.11.1.min.js?v.<%=System.currentTimeMillis()%>" ></script>
		
		<!-- 	Validator -->
		<script type="text/javascript" src="<%=path%>/js/formatJs.js" ></script>
		<script type="text/javascript" src="<%=path%>/js/formValidator.js" ></script>
		<script type="text/javascript" src="<%=path%>/js/formValidatorRegex.js" ></script>
		<script type="text/javascript" src="<%=path%>/js/common.js" ></script>
		
		
		<script type="text/javascript" src="<%=path%>/js/base.js?v.<%=System.currentTimeMillis()%>"></script>
		<script type="text/javascript" src="<%=path%>/js/regedit/regedit.js?v.<%=System.currentTimeMillis()%>"></script>
		<script type="text/javascript" src="<%=path%>/js/regedit/fiexdFoot.js"></script>
		
		<script>
			var userId;
			$(function(){
				userId = '${localUser.id}';
				
				$("#lenovoBox").hide();
				$("#majorBox").hide();
				
				$(".the-areaOf-selectDown").find('.adress-select-txt').off().on("change",'select.selectDown',function(){
					 var $opt = $(this).find('option');
				         $opt.each(function(i) {
				             if ($opt[i].selected == true) {
				                 txt = $opt[i].innerHTML;
				             }
				         })
				         $(this).parents('.adress-select-txt').find('.ipt-txt span').html(txt);
				});
				
				/* 学校名称联想搜索 */
				$("#entName").keyup(function(){
				  var schoolName = $("#entName").val();
				  if($.trim(schoolName).length > 0){
					  $.ajax({                
							cache: false,    
							async: true, 
							type: "POST",                
							url:  '<%=path%>/commCode/getSchList',                
							data:{
								param : schoolName
							},
							error: function(request) { 
							},
							beforeSend : function(request){
							},
							complete: function(data) { 
								var dataStr = data.responseText;
								var datas = eval('('+dataStr+')');
								if(datas.success){
									$("#lenovoBox").empty();
									$("#lenovoBox").show();
									var html = "<ul>";
									var schoolName = "";
									var schoolCode = "";
									for(var i = 0; i < datas.obj.length; i++){
										schoolName = datas.obj[i].school_name;
										schoolCode = datas.obj[i].school_code;
										html = html + "<li data-tag='"+schoolCode+"'>"+schoolName+"</li>"
									}
									html = html + "</ul>";
									$("#lenovoBox").append(html);
									
									//加载点击事件
									$("#lenovoBox").off().on("click",'li',function(){
										var schoolName = $(this).text();
										var schoolCode = $(this).data("tag");
										$("#schoolCode").val(schoolCode);
										$("#entName").val(schoolName);
										$("#lenovoBox").hide();
										
										//样式控制
										$("#entName-erro").removeClass("ipt-txt-Err");
										$("#entNameTip").hide();
									});
								}
							}
						});
				  }else{
					  $("#schoolCode").val("");
					  $("#lenovoBox").hide();
					  //样式控制
					  $("#entName-erro").removeClass("ipt-txt-Err");
					  $("#entNameTip").hide();
				  }
				});
				
				/* 所学专业 */
				$("#majorName").keyup(function(){
					  var schoolName = $("#majorName").val();
					  if($.trim(schoolName).length > 0){
						  $.ajax({                
								cache: false,    
								async: true, 
								type: "POST",                
								url:  '<%=path%>/commCode/getMajorList',                
								data:{
									param : schoolName,
									oper : 'name'
								},
								error: function(request) { 
								},
								beforeSend : function(request){
								},
								complete: function(data) { 
									var dataStr = data.responseText;
									var datas = eval('('+dataStr+')');
									if(datas.success){
										$("#majorBox").empty();
										$("#majorBox").show();
										var html = "<ul>";
										var schoolName = "";
										var schoolCode = "";
										for(var i = 0; i < datas.obj.length; i++){
											schoolName = datas.obj[i].major_name;
											schoolCode = datas.obj[i].major_code;
											html = html + "<li data-tag='"+schoolCode+"'>"+schoolName+"</li>"
										}
										html = html + "</ul>";
										$("#majorBox").append(html);
										
										//加载点击事件
										$("#majorBox").off().on("click",'li',function(){
											var schoolName = $(this).text();
											var schoolCode = $(this).data("tag");
											$("#majorCode").val(schoolCode);
											$("#majorName").val(schoolName);
											$("#majorBox").hide();
											
											 //样式控制
											  $("#majorName-erro").removeClass("ipt-txt-Err");
											  $("#majorNameTip").hide();
										});
									}
								}
							});
					  }else{
						  $("#majorCode").val("");
						  $("#majorBox").hide();
						  
						//样式控制
						  $("#majorName-erro").removeClass("ipt-txt-Err");
						  $("#majorNameTip").hide();
					  }
					});
			});
			
			//选择省份
			function getCityList(){
				$("#countryList").empty();
				$("#countryListSelect").html("请选择区县");
				$("#locationCode").val("");
				var provice = $("#proviceList").val();
					if(provice != ''){
						if(provice == '563' || provice == '562' || provice == '561'){
							$("#cityList").empty();
							$("#cityListSelect").html("请选择城市");
							$("#locationCode").val(provice);
						}
						var data = commonList('location', provice);
						if(data != null && data != ''){
							if(data.success){
								$("#cityList").empty();
								$("#cityVal").val("");
								$("#proviceVal-erro").removeClass("ipt-txt-Err");
								$("#cityListSelect").html("请选择城市");
								var html_inde = "<option value=''>请选择城市</option>";
								var html = "";
								for(var i = 0;i < data.obj.length;i++){
									var code = data.obj[i].location_code;
									var name = data.obj[i].location_name;
									html = html + "<option value='"+code+"'>"+name+"</option>"
								}
								$("#cityList").append(html_inde + html);
								$("#cityList").val("");
							}
						}
					}
			}
			
			//选择区县
			function getCountryList(){
				$("#countryList").empty();
				$("#countryListSelect").html("请选择区县");
				var city = $("#cityList").val();
					if(city != ''){
						$("#locationCode").val(city);
						var data = commonList('location', city);
						 if(data != null && data != '' && data.obj.length != 1){
							if(data.success){
								$("#countryList").empty();
								$("#countryList-erro").removeClass("ipt-txt-Err");
								$("#countryListSelect").html("请选择区县");
								var html_inde = "<option value='-1'>请选择区县</option>";
								var html = "";
								for(var i = 1;i < data.obj.length;i++){
									var code = data.obj[i].location_code;
									var name = data.obj[i].location_name;
									html = html + "<option value='"+code+"'>"+name+"</option>"
								}
								$("#countryList").append(html_inde + html);
								$("#countryList").val("");
							}
						} 
					}
			}
			
			//给区域复制
			function setLoactionCode(){
				var locationCode = $("#countryList").val();
				if(locationCode != '-1'){
					$("#locationCode").val(locationCode);
				}
			}
			
			function chooseRole(){
				location.href =r_path + "/regedit/init?oper=regedit_role&id=" + userId;
			}
			
		</script>
		
		
	</body>
</html>
