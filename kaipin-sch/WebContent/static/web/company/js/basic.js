$(function(){
	
	/*修改个人中心*/
	$("#ent-save-user-info").click(function(){
		var entName = $("#mod-entName").val();
		debugger;
		if(entName.length == 0){
			alert("请填写企业名称");
			return;
		}
		
		var userName = $("#user-userName").val();
		var userSurName = $("#user-userSurname").val();
		var sex = $("#user-sex").val();
		var position = $("#user-position").val();
		
		$.ajax({
			cache : false,
			type : "POST",
			url : r_path + '/entbasic/edit',
			data : {
				entName : entName,
				surname : userSurName,
				sex : sex,
				position : position,
				missSurname : userName,
				oper : 'user-info'
			},
			async : false,
			error : function(request) {
				alert("网络异常，请稍后再试！");
			},
			success : function(data) {
				var datas = eval('('+ data +')');
				if(datas.success){
					alert("修改成功");
					$("#ent_all_name").html(datas.msg)
					$("#personal-set-panel").hide();
					$("#tzui-loading-overlay").hide();
				}
			}
		});
		
	});
	
	/*保存基本信息 logo部分*/
	$("#saveBasicLogoInfoEnt").click(function(){
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
			async: true, 
			type: "POST",                
			url:  r_path + '/entbasic/edit',                
			data: $('#formBasicLogoInfo').serialize(),              
			error: function(request) { 
			},
			beforeSend : function(request){
			},
			complete: function(data) { 
				location.href=r_path + "/company/basic"
			}
		});
	});
	
	/*基本信息-主要部分*/
	$("#save-two-ent").click(function(){
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
		
		var locationCode = $("#locationCodeSelect").val();
		if(locationCode.length == 0){
			alert("请选择所在地区");
			return;
		}
		$.ajax({                
			cache: true,                
			type: "POST",                
			url:  r_path + '/entbasic/edit',                
			data:$('#form3').serialize(),// 你的formid                
			async: true,                
			error: function(request) {                    
			},                
			complete: function(data) {
				var dataStr = data.responseText;
				var datas = eval('('+dataStr+')');
				location.href= r_path + "/company/basic";
			}            
		});
	});
	
	/*修改背景*/
	$("#bg-save-ent").click(function(){
		var bgUrl = $("#bg_preview").val();
		$.ajax({
			cache : false,
			async : false,
			type : 'POST',
			url : r_path + '/entbasic/edit',
			data : {
				bgUrl : bgUrl,
				oper : 'bg'
			},
			success : function (data){
				var datas =eval('('+data+')');
				var path = datas.obj;
				$("#save-and-cancel").hide();
				$("#bg_url").val(path);
				$("#bg_preview").val("");
			}
		});
	});
});
