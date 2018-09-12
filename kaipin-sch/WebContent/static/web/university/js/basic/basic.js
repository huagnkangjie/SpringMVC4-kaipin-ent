	

	$(function(){
		
		//维护顶部 学校简称类信息
		$("#saveBasicLogoInfo").click(function(){
			var schoolShortName = $("#schoolShortName").val();
			if($.trim(schoolShortName).length == 0){
				alert("请填写学校简称");
				return;
			}
			$.ajax({                
				cache: true,    
				async: true, 
				type: "POST",                
				url:  r_path + '/basic/edit',                
				data: $('#formBasicLogoInfo').serialize(),              
				error: function(request) { 
				},
				beforeSend : function(request){
				},
				complete: function(data) { 
					location.href=r_path + "/basic/init"
				}
			});
		});
		
		/*查询详情*/
		$("#edit-company-info").click(function(){
			
			var currentUserType = $("#currentUserType").val();
			if(currentUserType == 'ent'){
				getEntDetai();
				return;
			}
			var open = $(this).data("open");
			if(open == 'open'){
				$.ajax({                
					cache: true,                
					type: "POST",                
					url:  r_path + '/basic/detail',                
					async: true,                
					error: function(request) {                    
					},                
					complete: function(data) {
						var datas = eval('('+data.responseText+')');
						
						for(var key in datas.obj.info){
							$("#" + key).val(datas.obj.info[key]);
						}
						$("#detail").val(datas.msg);
						
						$("#studentCountEdit").val(datas.obj.info.studentCount);
						$("#schoolFeatureIdEdit").val(datas.obj.info.schoolFeatureId);
						
						$("#provinceSelect").val(datas.obj.provinceCode);
						$("#citySelect").val(datas.obj.cityCode);
						$("#countySelect").val(datas.obj.countyCode);
					}            
				});
			}
		});
		
		function getEntDetai(){
			$.ajax({                
				cache: true,                
				type: "POST",                
				url:  r_path + '/entbasic/detail',                
				async: false,                
				error: function(request) {                    
				},                
				success: function(data) {
					var datas = eval('('+data+')');
					var obj = datas.obj.info;
					
					
					$("#detail").val(obj.detail);
					$("#website").val(obj.website);
					$("#regeditDate").val(obj.regeditDate);
					$("#companyTypeCode").val(obj.companyTypeCode);
					$("#peopleNumber").val(obj.peopleNumber);
					$("#officeAddress").val(obj.officeAddress);
					
					
					$("#provinceSelect").val(datas.obj.provinceCode);
					$("#citySelect").val(datas.obj.cityCode);
					$("#countySelect").val(datas.obj.countyCode);
					
				}            
			});
		}
		
		/* 企业基本信息保存 */
		$("#save-two").click(function (){
			var website = $("#website").val();
			
			if($.trim(website).length > 0){
				var websiteIndex = website.indexOf("@");
				var websiteIndex2 = website.indexOf("www.");
				if(websiteIndex != -1){
					alert("网址不能包含@字符");
					return;
				}
				if(websiteIndex2 == -1){
					alert("网址必须以www.开始");
					return;
				}
			}
			
			var locationCode = $("#provinceSelect").val();
			if(locationCode == '563' || locationCode == '562' || locationCode == '561'){
				$("#locationCodeSelect").val(locationCode);
			}else{
				locationCode = $("#locationCodeSelect").val();
				if(locationCode.length == 0){
					alert("请选择所在地区");
					return;
				}
			}
			
			$.ajax({                
				cache: true,                
				type: "POST",                
				url:  r_path + '/basic/edit',                
				data:$('#form3').serialize(),// 你的formid                
				async: true,                
				error: function(request) {                    
				},                
				complete: function(data) {
					var dataStr = data.responseText;
					var datas = eval('('+dataStr+')');
					location.href= r_path + "/basic/init";
				}            
			});
		});
		
		//初始化取消关注
		$("#follow-content").on("click",".delFollow", function(){
			var $_this = $(this);
			var orgId = $_this.parents("dl").data("orgid");
			var uType = $_this.parents("dl").data("uType");
			$_this.removeClass("delFollow");
			$_this.addClass("addFollow");
			var count = $_this.parents(".sameAttentlists").find(".followCount").text();
			var $_count = $_this.parents(".sameAttentlists").find(".followCount");
			
			delFollow(orgId, uType, $_this, count, $_count);
			
		});
		
		//初始化添加关注
		$("#follow-content").on("click",".addFollow", function(){
			var $_this = $(this);
			$_this.removeClass("addFollow");
			$_this.addClass("delFollow");
			
			insertFollow(orgId, uType, $_this);
		});
		
		//判断是否有网址--有网址直接跳转该网址
		$("#touch-change").on("click",function(){
			//判断是否有网址
			var org_id = $("#basic_show_page_org_id").val();
			$.ajax({
				cache : false,
				async : false,
				type : 'POST',
				url : r_path + '/basic/getWebsite',
				data:{
					org_id : org_id
				},
				success : function (data){
					if(isNotEmpty(data)){
						var datas = eval('('+data+')');
						if(datas.success){
							var url = datas.obj;
							url = url.replace("https://","");
							url = url.replace("http://","");
							window.open ("http://"+url); 
						}else{
							$("#choseImg").show();
						}
					}
					
				}
			});
		});
		
		
		//多级联动
		//点击省
		$("#provinceSelect").change(function(){
			var province = $("#provinceSelect").val();
			var type = "except";
			if(province == '563' || province == '562' || province == '561'){
				$("#locationCodeSelect").val(province);
				var html = "<option value=''>请选择城市</option>";
				var html2 = "<option value=''>请选择区县</option>";
				$("#citySelect").empty();
				$("#citySelect").append(html);
				
				$("#countySelect").empty();
				$("#countySelect").append(html2);
				return;
			}
			
			$.ajax({                
				cache: true,    
				async: true, 
				type: "POST",                
				url:  r_path + '/basecode/getlist',                
				data:{
					locationCode : province,
					type : type
				},              
				error: function(request) { 
				},
				beforeSend : function(request){
				},
				complete: function(data) { 
					var dataStr = data.responseText;
					var datas = eval('('+dataStr+')');
					
					$("#citySelect").val("");
					$("#countySelect").val("");
					$("#locationCodeSelect").val("");
					var html = "<option value=''>请选择城市</option>";
					var html2 = "<option value=''>请选择区县</option>";
					for(var i = 0; i < datas.obj.length; i++){
						html = html + "<option value='"+datas.obj[i].location_code+"'>"+datas.obj[i].location_name+"</option>";
					}
					$("#citySelect").empty();
					$("#citySelect").append(html);
					
					$("#countySelect").empty();
					$("#countySelect").append(html2);
				}
			});
		});
		
		//点击市区
		$("#citySelect").change(function(){
			var city = $("#citySelect").val();
			var type = "except";
			$.ajax({                
				cache: true,    
				async: true, 
				type: "POST",                
				url:  r_path + '/basecode/getlist',                
				data:{
					locationCode : city,
					type : type
				},              
				error: function(request) { 
				},
				beforeSend : function(request){
				},
				complete: function(data) { 
					var dataStr = data.responseText;
					var datas = eval('('+dataStr+')');
					
					$("#countySelect").val("");
					$("#locationCodeSelect").val(city);
					var html = "<option value=''>请选择区县</option>";
					for(var i = 0; i < datas.obj.length; i++){
						html = html + "<option value='"+datas.obj[i].location_code+"'>"+datas.obj[i].location_name+"</option>";
					}
					
					$("#countySelect").empty();
					$("#countySelect").append(html);
				}
			});
		});
		
		//选择区县
		$("#countySelect").change(function(){
			var code = $("#countySelect").val();
			$("#locationCodeSelect").val(code);
		});
		
	});
	
	//取消关注
	function delFollow(orgId, type, $_this, count, $_count){
		$.ajax({                
			cache: true,    
			async: true, 
			type: "POST",                
			url:  r_path + '/followfans/delPush',                
			data:{
				toId : orgId,
				toUserTypes : type
			},              
			error: function(request) { 
			},
			beforeSend : function(request){
			},
			complete: function(data) { 
				$_this.html("关注");
				$_this.parents("dl").hide();
				count = parseInt(count) - 1;
				$_count.html(count);
				
				getFollowListByClass(1, 8, 'follow', 10);
				getFollowListByClass(1, 8, 'follow', 11);
				getFollowListByClass(1, 8, 'follow', 12);
			}
		});
	}
	
	
	//添加关注
	function insertFollow(orgId, type, $_this){
		$.ajax({                
			cache: true,    
			async: true, 
			type: "POST",                
			url:  r_path + '/followfans/addPush',                
			data:{
				toId : orgId,
				toUserTypes : type
			},              
			error: function(request) { 
			},
			beforeSend : function(request){
			},
			complete: function(data) { 
				$_this.html("已关注");
			}
		});
	}
	
	
	//获取关注的企业 或者 学校 或者学生
	function getFollowListByClass(page, pageSize, type, toUserType){
		$.ajax({                
			cache: true,    
			async: true, 
			type: "POST",                
			url:  r_path + '/followfans/getFollowList',                
			data:{
				page : page,
				pageSize : pageSize,
				type : type,
				toUserType : toUserType
			},              
			error: function(request) { 
			},
			beforeSend : function(request){
			},
			complete: function(data) { 
				var dataStr = data.responseText;
				var datas = eval('('+dataStr+')');
				if(datas.success){
					data = datas;
					var html = "";
					var name = "";
					var orgId = "";
					var uTpye = "";
					var rowTwo = "";
					var rowThree = "";
					var count = datas.obj.count;
					var logo = "";
					var len = data.obj.person.length;
					len = len >= 8 ? (len - 1) : len;
					for(var i = 0; i < len; i++){
						name = data.obj.person[i].name;
						orgId = data.obj.person[i].orgId;
						rowTwo = data.obj.person[i].rowTwo;
						logo = data.obj.person[i].logo;
						uTpye = data.obj.person[i].userType;
						
						if(logo == '0'){
							logo = r_path + "/static/web/university/images/default-hdPic.jpg";
						}
						html += 
								"	<dl data-orgid='"+orgId+"' data-utype='"+uTpye+"'>"+
								"		<dt class='logos pushTargetOrg' style='cursor:pointer;'><image src='"+logo+"' width='60px' height='60px'></dt>"+
								"		<dd class='names'><p class='pushTargetOrg' style='cursor:pointer;'>"+name+"</p></dd>"+
								"		<dd class='industry'>"+rowTwo+"</dd>"+
								"		<dd class='attention'>"+
								"			<a href='javascript:void(0)' class='delFollow' title=''>"+
								"			取消关注"+
								"			</a>"+
								"		</dd>"+
								"	</dl>";
								
					}
					
					if(len == 7){
						html = html + 
								" <dl class='other-infos10'>"+
								"	<dt class='moreInfos'><a href='"+r_path+"/followfans/init?oper=follow&toUserType="+toUserType+"'><span></span></a></dt>"+
								"		<dd class='see-others'>"+
								"		<a href='"+r_path+"/followfans/init?oper=follow&toUserType="+toUserType+"'>更多关注 &gt; </a>"+
								"	</dd>"+
								" </dl>";
					}
					
					$("#"+toUserType+"FollowCount").html(count);
					$("#"+toUserType+"FollowList").empty();
					$("#"+toUserType+"FollowList").append(html);
					
					$("#"+toUserType+"FollowList").on("click",".pushTargetOrg",function(){
						var orgId = $(this).parents("dl").data("orgid");
						$("#targetOrgTriggerA").attr("href",r_path + "/feeds/targetOrg?orgId=" + orgId);
						$("#targetOrgTrigger").trigger("click");
					});
					
				}else{
					$("#"+toUserType+"FollowCountDiv").hide();
				}
				
				var stuCount = $("#10FollowCount").html();
				var entCount = $("#11FollowCount").html();
				var schCount = $("#12FollowCount").html();
				
				var total = parseInt(stuCount) + parseInt(entCount) + parseInt(schCount);
				if(total == 0){
					$("#follow-content").hide();
				}else{
					$("#follow-content").show();
				}
			}
		});
	} 