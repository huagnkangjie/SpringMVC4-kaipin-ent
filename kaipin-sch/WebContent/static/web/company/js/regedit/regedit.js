	/**
	 * 注册
	 */
	jQuery(function($){  
		//********************注册功能*******************************
		$("#slect-student").click(function(){
			$("#regeditType").val("stu");
		});
		$("#slect-company").click(function(){
			$("#regeditType").val("ent");
		});
		
		$("#nextDo").click(function(){
			var type = $("#regeditType").val();
			if(type == '0'){
				alert("请选择注册类型");
				return;
			}
			if(type == 'stu'){
				location.href= r_path + "/regedit/init.do?oper=stu";
			}else if(type == 'ent'){
				location.href= r_path + "/regedit/init.do?oper=ent";
			}
		});
		
		//***********************数据校验*******************************
		var selected = false;
		var entName_val = false;
		var userName_val = false;
		var email_val = false;
		var valiCode_val = false;
		var oper = 0;
		var flag = false;
		
		//验证邮箱
		$("#email").blur(function (){
			flag = false;
			var email = $("#email").val();
			if(checkValLength("email")){
				emailValidata();
			}
		});
		//验证用户名称
		$("#userName").blur(function (){
			flag = false;
			if(checkValLength("userName")){
				userNameValidata();
			}
		});
		//验证企业名称
		$("#entName").blur(function (){
			flag = false;
			if(checkValLength("entName")){
				entNamesValidata();
			}
		});
		//验证企业简称
		$("#entSimpleName").blur(function (){
			flag = false;
			if(checkValLength("entSimpleName")){
				entSimpleNameValidata();
			}
		});
		//密码
		$("#password").blur(function (){
			flag = false;
			if(checkValLength("password")){
				pw1Validata();
			}
		});
		$("#password2").blur(function (){
			flag = false;
			if(checkValLength("password2")){
				pw2Validata();
			}
		});
		//验证码
		$("#valiCode").blur(function (){
			flag = false;
			if(checkValLength("valiCode")){
				valiCodeValidata();
			}
		});
		//验证手机号码
		$("#phone").blur(function (){
			flag = false;
			if(checkValLength("phone")){
				validatemobile();
			}
		});
		
		
		/*企业全称*/
		function entNamesValidata(){
			var entName = $("#entName").val();
			if($.trim(entName).length == 0 || $.trim(entName).length < 5) {
				$("#entName").addClass("error-input");
				$("#entNameTip").show();
				$("#entNameTip").html("请输入正确的企业名称");
				return false;
			}else{
				$("#entName").removeClass("error-input");
				$("#entNameTip").hide();
				userName_val = true;
				return true;
			}
		}
		/*企业简称*/
		function entSimpleNameValidata(){
			var entSimpleName = $("#entSimpleName").val();
			var entName = $("#entName").val();
			var length = $.trim(entSimpleName).length;
			var lengthEntName = $.trim(entName).length;
			if(length == 0 || length > 9 || (entSimpleName == entName)) {
				$("#entSimpleName").addClass("error-input");
				$("#entSimpleNameTip").show();
				$("#entSimpleNameTip").html("请输入正确的企业简称");
				return false;
			}else if(lengthEntName != 0 && (length >= lengthEntName)){
				$("#entSimpleName").addClass("error-input");
				$("#entSimpleNameTip").show();
				$("#entSimpleNameTip").html("请输入正确的企业简称");
				return false;
			}else{
				$("#entSimpleName").removeClass("error-input");
				$("#entSimpleNameTip").hide();
				return true;
			}
		}
		/*验证用户名*/
		function userNameValidata(){
			var userName = $("#userName").val();
			if($.trim(userName).length == 0) {
				$("#userName").addClass("error-input");
				$("#userNameTip").show();
				$("#userNameTip").html("请输入姓名");
				userName_val = false;
				return userName_val;
			}else{
				$("#userName").removeClass("error-input");
				$("#userNameTip").hide();
				userName_val = true;
				return userName_val;
			}
		}
		
		//邮箱格式验证
		function emailCheck(value) {
			var pattern = /^[0-9A-Za-z0-9]+([-_.][0-9A-Za-zd0-9]+)*@([^_]+[0-9a-zA-Za-zd_]+[-.])+[A-Za-zd]{2,5}$/;
			//^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([^_|-|.]+[a-z0-9A-Z_]+(-[a-z0-9A-Z])?[^_|-|.]+\\.)+[a-zA-Z]{2,}$
//			var pattern = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/;
			if (!pattern.test(value)) {
				return false;
			}
			return true;
		}
		/* 验证邮箱唯一性 */
		function emailValidata(){
			var email = $("#email").val();
			if (emailCheck(email)) {
				// 唯一性验证
				$.ajax({
					cache : false,
					async : false,
					type : 'POST',
					url : r_path + '/regedit/emailValidata.do',
					data : {
						email : email
					},
					success : function(data) {
						var datas = eval('(' + data + ')');
						if (datas.success) {
							$("#email").addClass("error-input");
							$("#emailTip").show();
							$("#emailTip").html("该邮箱已经被注册");
							email_val = false;
						} else {
							$("#email").removeClass("error-input");
							$("#emailTip").hide();
							email_val = true;
						}
					}
				});
			}else{
				$("#email").addClass("error-input");
				$("#emailTip").show();
				$("#emailTip").html("邮箱格式不对");
				email_val = false;
			}
			return email_val;
		}
		
		/*手机号码验证*/
		function validatemobile(){
			var mobile = $("#mod-phone").val();
	        if(mobile.length!=11)
	        {
	        	$("#mod-phone").addClass("error-input");
				$("#mod-phone-tip").show();
				$("#mod-phone-tip").html("请输入11位有效手机号码");
	            return false;
	        }
	        
	        var myreg = /^1\d{10}$/;
	        if(!myreg.test(mobile))
	        {
	        	$("#mod-phone").addClass("error-input");
				$("#mod-phone-tip").show();
				$("#mod-phone-tip").html("请输入11位有效手机号码");
	            return false;
	        }else{
	        	//先验证手机号是否被注册
				//再验证手机验证码
				if(doValidataPhone(mobile)){
					$("#mod-phone").removeClass("error-input");
					$("#mod-phone-tip").hide();
					return true;
				}else{
					$("#mod-phone").addClass("error-input");
					$("#mod-phone-tip").show();
					$("#mod-phone-tip").html("该手机号已注册");
		            return false;
				}
	        }
		}
		
		/*手机号唯一性验证*/
		function doValidataPhone(mobile){
			var flagPhone = false;
			$.ajax({
				cache : false,
				async : false,
				type : 'POST',
				url : r_path + '/regedit/validataPhone.do',
				data : {
					phone : mobile
				},
				success : function(data) {
					var datas = eval('('+ data +')');
					if(datas.success){
						flagPhone = true;
					}
				}
			});
			return flagPhone;
		}
		
		/*手机验证码校验*/
		function phoneCodeValidate(){
			var phoneCode = $("#mod-phone-code").val();
			var length = $.trim(phoneCode).length;
			if(length == 4){
				//先验证手机号是否 
				if(checkPhoneCode(phoneCode)){
					$("#mod-phone-code").removeClass("error-input");
					$("#mod-phone-code-tip").hide();
					return true;
				}else{
					$("#mod-phone-code").addClass("error-input");
					$("#mod-phone-code-tip").show();
					$("#mod-phone-code-tip").html("请输入有效手机验证码");
					return false;
				}
				
			}else{
				$("#mod-phone-code").addClass("error-input");
				$("#mod-phone-code-tip").show();
				$("#mod-phone-code-tip").html("请输入有效手机验证码");
				return false;
			}
		}
		
		/*手机验证码校验*/
		function checkPhoneCode(phoneCode){
			var checkFlag = false;
			var phone = $("#mod-phone").val();
			$.ajax({
				cache : false,
				async : false,
				type : 'POST',
				url : r_path + '/regedit/sendPhoneMsg.do',
				data : {
					code : phoneCode,
					phone : phone
				},
				success : function(data) {
					var datas = eval('(' + data + ')');
					if(datas.obj == '200'){
						checkFlag = true;
					}
				}
			});
			return checkFlag;
		}
		
		
		/* 密码1 */
		function pw1Validata(){
			var pw1 = $("#password").val();
			if(pw1.length != 0){
				if (pw1 != '' && pw1.length >= 6 && pw1.length <= 32) {
					$("#password").removeClass("error-input");
					$("#passwordTip").hide();
					return true;
					// 唯一性验证
				} else if (pw1.length != '' && (pw1.length < 6 || pw1.length > 32)) {
					$("#password").addClass("error-input");
					$("#passwordTip").show();
					$("#passwordTip").html("密码长度6-32位");
					return false;
				} 
			}else{
				$("#password").addClass("error-input");
				$("#passwordTip").show();
				$("#passwordTip").html("密码长度6-32位");
				return false;
			}
		}
		
		/* 密码2 */
		function pw2Validata(){
			var pw1 = $("#password").val();
			var pw2 = $("#password2").val();
			if (pw1 == pw2 && pw1.length >= 6 && pw1 != '' && pw1.length <=32) {
				$("#password2").removeClass("error-input");
				$("#password2Tip").hide();
				return true;
				// 唯一性验证
			} else if (pw1.length < 6) {
				$("#password2").addClass("error-input");
				$("#password2Tip").show();
				$("#password2Tip").html("密码长度6-32位");
				return false;
			} else {
				$("#password2").addClass("error-input");
				$("#password2Tip").show();
				$("#password2Tip").html("两次输入密码不相同");
				return false;
			}
		}
		
		/* 验证码验证 */
		function valiCodeValidata(){
			var valiCode = $("#valiCode").val();
			if(valiCode.length == 4){
				$.ajax({
					cache : false,
					async : true,
					type : 'POST',
					url : r_path + '/regedit/valiCodeValidata.do',
					data : {
						valiCode : valiCode
					},
					success : function(data) {
						var datas = eval('(' + data + ')');
						if (datas.success) {
							$("#valiCode").removeClass("error-input");
							$("#valiCodeTip").hide();
							valiCode_val = true;
						} else {
							$("#valiCode").addClass("error-input");
							$("#valiCodeTip").show();
							$("#valiCodeTip").html("验证码不正确");
							valiCode_val =  false;	
						}
					}
				});
			}else{
				$("#valiCode").addClass("error-input");
				$("#valiCodeTip").show();
				$("#valiCodeTip").html("验证码不正确");
				valiCode_val = false;	
			}
			return valiCode_val;
		}
		
		//***********************ent 注册提交*******************************
		
		$("#saveBtn").off().click(function(){
			var select = $("#show_icon").val();
			if(select == '1'){
				$("#selectedTip").hide();
				if(entNamesValidata()){//企业全称
					if(entSimpleNameValidata()){//企业简称
						if(userNameValidata()){//验证用户名
							if(validatemobile()){//验证手机
								if(emailValidata()){//邮箱
									if(pw1Validata()){
										if(pw2Validata()){
											if(valiCodeValidata()){//验证码
												if(oper == 0){
													if(phoneCodeValidate()){//手机验证码
														$("#submit-reg").show();
														$("#saveBtn").hide();
														oper = 1;
														var timeer = "";
														clearTimeout(timeer);
														timeer = setTimeout(function () {
																	$.ajax({
					 								 					cache : false,
					 								 					type : "POST",
					 													url : r_path + '/regedit/register.do',
					 								 					data : $('#regeditForm').serialize(),// 你的formid
					 								 					async : true,
					 								 					error : function(request) {
					 								 						alert("网络异常，请稍后再试！");
					 								 					},
					 								 					success : function(data) {
					 								 						
					 								 					},
					 								 					complete: function(data) { 
					 								 						var dataStr = data.responseText;
					 								 						$("#submit-reg").hide();
					 														$("#saveBtn").show();
					 								 						document.regeditForm.reset();
					 								 						var datas = eval('('+dataStr+')');
					 								 						var oper ;
					 								 						var userId;
					 								 						var companyId;
					 								 						var email = "";
					 								 						if(datas.success){
					 								 							email = datas.obj.email;
					 								 							userId = datas.obj.userId;
					 								 							companyId = datas.obj.companyId;
					 								 						}else{
					 								 							oper = 1;
					 								 						}
					 														//location.href =r_path + "/regedit/certificate.do?target=certificate&oper="+oper+"&userId="+userId+"&companyId="+companyId;
					 								 						location.href =r_path + "/regedit/regeditSuc.do?oper="+email;
					 								 					}
					 								 				});
															    }, 200);
													}else if(oper == 1){
														alert("亲，点慢点，俺正在努力");
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}else{
				$("#selectedTip").show();
				$("#selectedTip").html("请阅读条款");
			}
		});
		
		
		/*移除css*/
		 function removeCss(obj){
			 $("#" + obj).removeClass("error-input");
		 }
		 /*添加css*/
		 function addCss(obj){
			 $("#" + obj).addClass("error-input");
		 }
		 
		 /*判断提交值的长度*/
		function checkValLength(objId){
			var obj = $("#" + objId).val();
			//setTimeout(function(objId){
				if($.trim(obj).length == 0) {
					$("#" + objId).removeClass("error-input");
					$("#" + objId + "Tip").hide();
					flag = false;
					//return flag;
				}else{
					flag = true;
					//return flag;
				}
			//},20);
			return flag;
		}
		
		/**
		 * 获取手机验证码
		 */
		
		var countdown=60;
		$("#getPhoneCode").off().click(function(){
			if(validatemobile()){
				$("#phoneCodeTip").hide();
				var phone = $("#mod-phone").val();
				$("#getPhoneCodeIng").hide();
		    	$("#getPhoneCode").show();
				getPhoneCode(phone);
				
				settime();
				
			}
		});
		function settime() { 
			if (countdown == 0) { 
		    	$("#getPhoneCode").html("获取验证码");
		    	$("#getPhoneCodeIng").hide();
		    	$("#getPhoneCode").show();
		        countdown = 59; 
		        return;
		    } else { 
		    	$("#getPhoneCodeIng").css("background-color","#CCC");
		    	$("#getPhoneCodeIng").html("重新发送" + countdown + "秒");
		    	$("#getPhoneCode").hide()
		    	$("#getPhoneCodeIng").show();
		        countdown--; 
		    } 
			setTimeout(function() { 
			    settime() }
			    ,1000)
		}
		
		/*后台验证手机验证码*/
		function getPhoneCode(phone){
			$.ajax({
				cache : false,
				type : "POST",
				url : r_path + '/regedit/sendPhoneMsg.do',
				data : {
					phone : phone
				},
				async : true,
				error : function(request) {
					alert("网络异常，请稍后再试！");
				},
				success : function(data) {
					var datas = eval('('+ data +')');
					if(datas.success){
						if(datas.obj != '200'){
							alert(datas.msg);
						}
					}
				}
			});
		}
		
		
		
	});
	
	
	
	/* 切换验证码 */
	function changeImg(){
		var time=new Date().getTime();
    	var url = r_path + "/validatecode.jpg?time="+time;
    	$("#validateImg").attr("src",url);
    	$("#valiCode").val("");
	}
	
