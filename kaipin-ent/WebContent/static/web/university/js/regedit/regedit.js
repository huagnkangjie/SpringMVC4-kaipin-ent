	/**
	 * 注册
	 */
	jQuery(function($){  
		
		//***********************选择用户类型 开始*******************************
		$("#slect-student").click(function(){
			$("#regeditType").val("10");
		});
		$("#slect-company").click(function(){
			$("#regeditType").val("11");
		});
		$("#slect-Colleges").click(function(){
			$("#regeditType").val("12");
		});
		
		$("#nextDo").click(function(){
			var type = $("#regeditType").val();
			var userId = $("#userId").val();
			if(type == '0'){
				alert("请选择注册类型");
				return;
			}
			//选择类型
			chooseRole(type, userId);
		});
		
		//选择用户类型
		function chooseRole(type, userId){
			$("#nextDo").hide();
			$.ajax({
				cache : false,
				type : "POST",
				url : r_path + '/regedit/chooseRole',
				data : {
					type : type,
					userId : userId
				},
				async : true,
				beforeSend:function(request){
					$("#nextDo-ing").show();
				},
				error : function(request) {
					alert("网络异常，请稍后再试！");
				},
				complete : function(data) {
					var categoryId = $("#regeditType").val();
					var dataStr = data.responseText;
					if(dataStr != ''){
						var datas = eval('('+dataStr+')');
						if(datas.success){
							location.href= r_path + "/regedit/init?oper=regedit_open&categoryId="+categoryId+"&id="+userId;
						}
					}
				}
			});
		}
		//***********************选择用户类型 结束*******************************

		//***********************数据校验 失焦  开始*******************************
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
		
		//验证邮箱  重新获取
		$("#emailAgain").blur(function (){
			flag = false;
			var email = $("#emailAgain").val();
			var emailAgainOld = $("#emailAgainOld").val();
			if(email == emailAgainOld){
				$("#emailAgainTip").hide();
			}else{
				emailValidata();
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
		
		//验证邀请码
		$("#invitationParentCode").blur(function (){
			flag = false;
			if(checkValLength("invitationParentCode")){
				inviteCode();
			}
		});
		
		//******************************************************
		//验证企业名称
		$("#entName").blur(function (){
			flag = false;
			if(checkValLength2("entName")){
				entNamesValidata();
			}
		});
		//验证企业简称
		$("#entSimpleName").blur(function (){
			flag = false;
			if(checkValLength2("entSimpleName")){
				entSimpleNameValidata();
			}
		});

		//验证用户名称 姓
		$("#userSurname").blur(function (){
			flag = false;
			if(checkValLength2("userSurname")){
				userSurnameNameValidata();
			}
		});
		//验证用户名称 名
		$("#userName").blur(function (){
			flag = false;
			if(checkValLength2("userName")){
				userNameValidata();
			}
		});
		//验证  城市
		$("#cityList").blur(function (){
			flag = false;
			if(checkValLength2("cityList")){
				cityValidata();
			}
		});
		//验证  所在地区
		$("#proviceList").blur(function (){
			flag = false;
			if(checkValLength2("proviceList")){
				proviceValidata();
			}
		});
		//专业名称
		$("#majorName").blur(function (){
			flag = false;
			if(checkValLength2("majorName")){
				majorNameValidata();
			}
		});
		
		
		//******************************************************

		//***********************数据校验 失焦  结束*******************************

		//***********************数据校验   账号注册 开始*******************************
		//验证邀请码
		function inviteCode(){
			var inviteCode = $("#invitationParentCode").val();
			if(inviteCode.length ==6 || inviteCode.length ==0){
				$("#invitationParentCode-erro").removeClass("error-txts");
				$("#invitationParentCodeTip").hide();
				//判读邀请码是否存在
				if(inviteCode.length ==6){
					 if(validataInviteCode(inviteCode)){
						 $("#invitationParentCode-erro").removeClass("error-txts");
						 $("#invitationParentCodeTip").hide();
						 return true;
					 }else{
						 $("#invitationParentCode-erro").addClass("error-txts");
						 $("#invitationParentCodeTip").show();
						 $("#invitationParentCodeTip").html("请输入6位有效邀请码");
						 return false;
					 }
				}else{
					return true;	
				}
			}else if(inviteCode.length != 6 ){
				$("#invitationParentCode-erro").addClass("error-txts");
				$("#invitationParentCodeTip").show();
				$("#invitationParentCodeTip").html("请输入6位有效邀请码");
				return false
			}
			
		}
		
		//邀请码唯一性验证
		function validataInviteCode(inviteCode){
			var flags = false
			$.ajax({
				cache : false,
				type : "POST",
				url : r_path + '/regedit/validataInviteCode',
				data : {
					inviteCode : inviteCode
				},
				async : false,
				error : function(request) {
					alert("网络异常，请稍后再试！");
				},
				success : function(data) {
					flags = data.success;
				}
			});
			return flags;
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
		function emailValidata(email){
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
						//var datas = eval('(' + data + ')');
						if(data == null || data == ''){
							return;
						}
						if (data.success) {
							$("#email-erro").addClass("error-txts");
							$("#emailTip").show();
							$("#emailTip").html("该邮箱已经被注册");
							email_val = false;
						} else {
							$("#email-erro").removeClass("error-txts");
							$("#emailTip").hide();
							email_val = true;
						}
					}
				});
			}else{
				$("#email-erro").addClass("error-txts");
				$("#emailTip").show();
				$("#emailTip").html("邮箱格式不对");
				email_val = false;
			}
			return email_val;
		}
		 

		/*手机号码验证*/
		function validatemobile(){
			var mobile = $("#phone").val();
	        if(mobile.length!=11)
	        {
	        	$("#phone-erro").addClass("error-txts");
				$("#phoneTip").show();
				$("#phoneTip").html("请输入11位有效手机号码");
	            return false;
	        }
	        
	        var myreg = /^1\d{10}$/;
	        if(!myreg.test(mobile))
	        {
	        	$("#phone-erro").addClass("error-txts");
				$("#phoneTip").show();
				$("#phoneTip").html("请输入11位有效手机号码");
	            return false;
	        }else{
	        	//先验证手机号是否被注册
				//再验证手机验证码
				if(doValidataPhone(mobile)){
					$("#phone-erro").removeClass("error-txts");
					$("#phoneTip").hide();
					return true;
				}else{
					$("#phone-erro").addClass("error-txts");
					$("#phoneTip").show();
					$("#phoneTip").html("该手机号已注册");
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
				url : r_path + '/regedit/validataPhone',
				data : {
					phone : mobile
				},
				success : function(data) {
					if(data == null || data == ''){
						return;
					}
					//var datas = eval('('+ data +')');
					if(data.success){
						flagPhone = true;
					}
				}
			});
			return flagPhone;
		}
		
		/*手机验证码校验*/
		function phoneCodeValidate(){
			var phoneCode = $("#phoneCode").val();
			var length = $.trim(phoneCode).length;
			if(length == 4){
				//先验证手机号是否 
				if(checkPhoneCode(phoneCode)){
					$("#phoneCode-erro").removeClass("error-txts");
					$("#phoneCodeTip").hide();
					return true;
				}else{
					$("#phoneCode-erro").addClass("error-txts");
					$("#phoneCodeTip").show();
					$("#phoneCodeTip").html("请输入有效手机验证码");
					return false;
				}
				
			}else{
				$("#phoneCode-erro").addClass("error-txts");
				$("#phoneCodeTip").show();
				$("#phoneCodeTip").html("请输入有效手机验证码");
				return false;
			}
		}
		
		/*手机验证码校验*/
		function checkPhoneCode(phoneCode){
			var checkFlag = false;
			var phone = $("#phone").val();
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
					//var datas = eval('(' + data + ')');
					if(data == null || data == ''){
						return;
					}
					if(data.obj == '200'){
						checkFlag = true;
					}
				}
			});
			return checkFlag;
		}

		/**
		 * 获取手机验证码
		 */
		
		var countdown=60;
		$("#getPhoneCode").off().click(function(){
			if(validatemobile()){
				$("#phoneCode-erro").removeClass("error-txts");
				$("#phoneCodeTip").hide();
				
				$("#phoneCodeTip").hide();
				var phone = $("#phone").val();
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
		    	$("#getPhoneCodeIng").html("重新发送(" + countdown + "秒)");
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
					//var datas = eval('('+ data +')');
					if(data == null || data == ''){
						return;
					}
					if(data.success){
						if(data.obj != '200'){
							alert(data.msg);
						}
					}
				}
			});
		}

		/* 密码1 */
		function pw1Validata(){
			var pw1 = $("#password").val();
			if(pw1.length != 0){
				if (pw1 != '' && pw1.length >= 6 && pw1.length <= 32) {
					$("#password-erro").removeClass("error-txts");
					$("#passwordTip").hide();
					return true;
					// 唯一性验证
				} else if (pw1.length != '' && (pw1.length < 6 || pw1.length > 32)) {
					$("#password-erro").addClass("error-txts");
					$("#passwordTip").show();
					$("#passwordTip").html("密码长度6-32位");
					return false;
				} 
			}else{
				$("#password-erro").addClass("error-txts");
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
				$("#password2-erro").removeClass("error-txts");
				$("#password2Tip").hide();
				return true;
				// 唯一性验证
			} else if (pw1.length < 6) {
				$("#password2-erro").addClass("error-txts");
				$("#password2Tip").show();
				$("#password2Tip").html("密码长度6-32位");
				return false;
			} else {
				$("#password2-erro").addClass("error-txts");
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
						//var datas = eval('(' + data + ')');
						if(data == null || data == ''){
							return;
						}
						if (data.success) {
							$("#valiCode-erro").removeClass("error-txts");
							$("#valiCodeTip").hide();
							valiCode_val = true;
						} else {
							$("#valiCode-erro").addClass("error-txts");
							$("#valiCodeTip").show();
							$("#valiCodeTip").html("验证码不正确");
							valiCode_val =  false;	
						}
					}
				});
			}else{
				$("#valiCode-erro").addClass("error-txts");
				$("#valiCodeTip").show();
				$("#valiCodeTip").html("验证码不正确");
				valiCode_val = false;	
			}
			return valiCode_val;
		}
		//***********************数据校验   账号注册 结束*******************************

		//***********************数据校验   ent 开始******************************
		/*企业全称*/
		function entNamesValidata(){
			var entName = $("#entName").val();
			if($.trim(entName).length == 0 || $.trim(entName).length < 4) {
				$("#entName-erro").addClass("ipt-txt-Err");
				$("#entNameTip").show();
				$("#entNameTip").html("请输入正确的名称");
				return false;
			}else{
				$("#entName-erro").removeClass("ipt-txt-Err");
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
				$("#entSimpleName-erro").addClass("ipt-txt-Err");
				$("#entSimpleNameTip").show();
				$("#entSimpleNameTip").html("请输入正确的简称");
				return false;
			}else if(lengthEntName != 0 && (length >= lengthEntName)){
				$("#entSimpleName-erro").addClass("ipt-txt-Err");
				$("#entSimpleNameTip").show();
				$("#entSimpleNameTip").html("请输入正确的简称");
				return false;
			}else{
				$("#entSimpleName-erro").removeClass("ipt-txt-Err");
				$("#entSimpleNameTip").hide();
				return true;
			}
		}
		/*验证用户名  姓*/
		function userSurnameNameValidata(){
			var userName = $("#userSurname").val();
			if($.trim(userName).length == 0) {
				$("#userSurname-erro").addClass("ipt-txt-Err");
				$("#userSurnameTip").show();
				$("#userSurnameTip").html("请输入姓");
				userName_val = false;
				return userName_val;
			}else{
				if($.trim(userName).length > 20){
					$("#userSurname-erro").addClass("ipt-txt-Err");
					$("#userSurnameTip").show();
					$("#userSurnameTip").html("请输入正确的姓");
					userName_val = false;
				}else{
					$("#userSurname-erro").removeClass("ipt-txt-Err");
					$("#userSurnameTip").hide();
					userName_val = true;
				}
				return userName_val;
			}
		}
		/*验证用户名   名*/
		function userNameValidata(){
			var userName = $("#userName").val();
			if($.trim(userName).length == 0) {
				$("#userName-erro").addClass("ipt-txt-Err");
				$("#userNameTip").show();
				$("#userNameTip").html("请输入名");
				userName_val = false;
				return userName_val;
			}else{
				if($.trim(userName).length > 20){
					$("#userName-erro").addClass("ipt-txt-Err");
					$("#userNameTip").show();
					$("#userNameTip").html("请输入正确的名字");
					userName_val = false;
				}else{
					$("#userName-erro").removeClass("ipt-txt-Err");
					$("#userNameTip").hide();
					userName_val = true;
				}
				
				return userName_val;
			}
		}
		
		//城市数值校验
		function cityValidata(){
			var val = $("#cityList").val();
			if($.trim(val).length == 0) {
				$("#cityVal-erro").addClass("ipt-txt-Err");
				$("#cityValTip").show();
				$("#cityValTip").html("请选择城市");
				return false;
			}else{
				$("#cityVal-erro").removeClass("ipt-txt-Err");
				$("#cityValTip").hide();
				return true;
			}
		}
		//省会数值校验
		function proviceValidata(){
			var val = $("#proviceList").val();
			if($.trim(val).length == 0) {
				$("#proviceVal-erro").addClass("ipt-txt-Err");
				$("#proviceValTip").show();
				$("#proviceValTip").html("请选择所在地区");
				return false;
			}else{
				$("#proviceVal-erro").removeClass("ipt-txt-Err");
				$("#proviceValTip").hide();
				return true;
			}
		}
		//***********************数据校验   ent 结束*******************************

		//***********************数据校验   sch 开始*******************************
		//***********************数据校验   sch 结束*******************************

		//***********************数据校验   stu 开始*******************************
		
		/*专业*/
		function majorNameValidata(){
			var entName = $("#majorName").val();
			if($.trim(entName).length == 0 || $.trim(entName).length < 2) {
				$("#majorName-erro").addClass("ipt-txt-Err");
				$("#majorNameTip").show();
				$("#majorNameTip").html("请输入正确的名称");
				return false;
			}else{
				$("#majorName-erro").removeClass("ipt-txt-Err");
				$("#majorNameTip").hide();
				userName_val = true;
				return true;
			}
		}
		
		//***********************数据校验   stu 结束*******************************


		//***********************数据提交  开始*******************************

		$("#saveBtn").off().click(function(){
			var select = $("#show_icon").val();
			if(select == '1'){
				$("#selectedTip").hide();
//				if(entNamesValidata()){//企业全称
//					if(entSimpleNameValidata()){//企业简称
//						if(userNameValidata()){//验证用户名
							if(validatemobile()){//验证手机
								if(emailValidata()){//邮箱
									if(pw1Validata()){
										if(pw2Validata()){
											if(valiCodeValidata()){//验证码
												if(inviteCode()){//邀请码
													if(oper == 0){
//														if(phoneCodeValidate()){//手机验证码
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
						 								 						//document.regeditForm.reset();
						 								 						var datas = eval('('+dataStr+')');
						 								 						var oper ;
						 								 						var userId;
						 								 						var companyId;
						 								 						var email = "";
						 								 						if(datas.success){
						 								 							userId = datas.obj.userId;
						 								 							location.href =r_path + "/regedit/init?oper=regedit_role&id=" + userId;
						 								 						}else{
						 								 							//oper = 1;
						 								 							alert("注册失败，请重新注册！");
						 								 							$("#submit-reg").hide();
							 														$("#saveBtn").show();
						 								 						}
						 														//location.href =r_path + "/regedit/certificate.do?target=certificate&oper="+oper+"&userId="+userId+"&companyId="+companyId;
						 								 						
						 								 					}
						 								 				});
																    }, 200);
														}else if(oper == 1){
															alert("亲，点慢点，俺正在努力");
														}
													}
//												}
											}
										}
//									}
//								}
//							}
						}
					}
				}
			}else{
				$("#selectedTip").show();
				$("#selectedTip").html("请阅读条款");
			}
		});

	//***********************数据提交  ent*******************************
		/*企业基本信息*/
		$("#ent-submit-btn").off().click(function(){
			if(entNamesValidata()){
				if(entSimpleNameValidata()){
					if(userSurnameNameValidata()){
						if(userNameValidata()){
							if(proviceValidata()){
								if(cityValidata()){
									$("#cityVal-erro").removeClass("ipt-txt-Err");
									$("#ent-submit-btn").hide();
									$.ajax({
						 					cache : false,
						 					type : "POST",
											url : r_path + '/regedit/regeditBasic',
						 					data : $('#regetEntForm').serialize(),// 你的formid
						 					async : true,
						 					error : function(request) {
						 						alert("网络异常，请稍后再试！");
						 					},
						 					beforeSend : function(request) {
						 						$("#ent-submit-btn-ing").show();
						 					},
						 					complete: function(data) { 
						 						var dataStr = data.responseText;
						 						var datas = eval('('+dataStr+')');
						 						var companyId = "";
						 						var userId = "";
						 						if(datas.success){
						 							companyId = datas.obj.orgId;
						 							userId = datas.obj.userId;
						 							location.href =r_path + "/certificate/certificateIndex?oper=11&orgId=" + companyId + "&userId=" + userId;
						 						}else{
						 							//oper = 1;
						 							alert("注册失败，请重新注册！");
						 						}
						 						
						 					}
						 				});
								}
							}
						}
					}
				}
			}
			
		});
		
		/*学生基本信息*/
		function checkSchoolCode(){
			var v = $("#schoolCode").val();
			var schoolId = $("#schoolId").val();
			var flags = false;
			if($.trim(v).length == 0){
				$(".schoolNameErro").addClass("ipt-txt-Err");
				$(".schoolNameErroTip").html("请输入正确的名称");
				$(".schoolNameErroTip").show();
			}else{
				//验证唯一性
				if(checkSchool(schoolId)){
					flags =true;
					$(".schoolNameErro").removeClass("ipt-txt-Err");
					$(".schoolNameErroTip").hide();
				}else{
					flags =false;
					$(".schoolNameErro").addClass("ipt-txt-Err");
					$(".schoolNameErroTip").html("该学校已经被注册");
					$(".schoolNameErroTip").show();
				}
				
			}
			return flags;
			
		}
		
		function checkSchool(schoolId){
			var flag = false;
			$.ajax({
				cache : false,
				type : "POST",
				url : r_path + '/regedit/schoolValidata',
				data : {
					schoolId : schoolId
				},
				async : false,
				error : function(request) {
					alert("网络异常，请稍后再试！");
				},
				success: function(data) { 
					if(data.success){
						flag = true;
					}
				}
			});
			return flag;
		}
		
		function checkMajorCode(){
			var v = $("#majorCode").val();
			var flags = false;
			if($.trim(v).length == 0){
				$("#majorName-erro").addClass("ipt-txt-Err");
				$("#majorNameTip").html("请输入正确的名称");
				$("#majorNameTip").show();
			}else{
				flags =true;
			}
			return flags;
		}
		
		$("#stu-submit-btn").off().click(function(){
			if(userSurnameNameValidata()){
				if(userNameValidata()){
					if(entNamesValidata() && checkSchoolCode()){
						if(majorNameValidata()){
								if(checkMajorCode()){
									if(proviceValidata()){
										if(cityValidata()){
											$("#cityVal-erro").removeClass("ipt-txt-Err");
											$("#stu-submit-btn").hide();
											$.ajax({
								 					cache : false,
								 					type : "POST",
													url : r_path + '/regedit/regeditBasic',
								 					data : $('#regetStuForm').serialize(),// 你的formid
								 					async : true,
								 					error : function(request) {
								 						alert("网络异常，请稍后再试！");
								 					},
								 					beforeSend : function(request) {
								 						$("#stu-submit-btn-ing").show();
								 					},
								 					complete: function(data) { 
								 						var dataStr = data.responseText;
								 						var datas = eval('('+dataStr+')');
								 						var companyId = "";
								 						var userId = "";
								 						if(datas.success){
								 							companyId = datas.obj.orgId;
								 							userId = datas.obj.userId;
								 							location.href =r_path + "/certificate/certificateIndex?oper=10&orgId=10&userId=" + userId;
								 						}else{
								 							//oper = 1;
								 							alert("注册失败，请重新注册！");
								 						}
								 						
								 					}
								 				});
									}
								}
							}
						}
					}
				}
			}
			
		});
		

		/*学校基本信息*/
		$("#sch-submit-btn").off().click(function(){
			if(entNamesValidata() && checkSchoolCode()){
				if(entSimpleNameValidata()){
					if(userSurnameNameValidata()){
						if(userSurnameNameValidata()){
							if(userNameValidata()){
								if(proviceValidata()){
									if(cityValidata()){
										$("#cityVal-erro").removeClass("ipt-txt-Err");
										$("#sch-submit-btn").hide();
										$.ajax({
							 					cache : false,
							 					type : "POST",
												url : r_path + '/regedit/regeditBasic',
							 					data : $('#regetSchForm').serialize(),// 你的formid
							 					async : true,
							 					error : function(request) {
							 						alert("网络异常，请稍后再试！");
							 					},
							 					beforeSend : function(request) {
							 						$("#sch-submit-btn-ing").show();
							 					},
							 					complete: function(data) { 
							 						var dataStr = data.responseText;
							 						var datas = eval('('+dataStr+')');
							 						var companyId = "";
							 						var userId = "";
							 						var schoolId = "";
							 						if(datas.success){
							 							companyId = datas.obj.orgId;
							 							userId = datas.obj.userId;
							 							schoolId = datas.obj.schoolId;
							 							location.href =r_path + "/certificate/certificateIndex?oper=12&orgId="+schoolId+"&userId=" + userId;
							 						}else{
							 							//oper = 1;
							 							alert("注册失败，请重新注册！");
							 						}
							 						
							 					}
							 				});
									}
								}
							}
						}
					}
				}
			}
			
		});
		
		
		//***********************数据提交  结束*******************************


		/*移除css*/
		 function removeCss(obj){
			 $("#" + obj).removeClass("error-txts");
		 }
		 /*添加css*/
		 function addCss(obj){
			 $("#" + obj).addClass("error-txts");
		 }
		 
		 /*判断提交值的长度*/
		function checkValLength(objId){
			var obj = $("#" + objId).val();
			//setTimeout(function(objId){
				if($.trim(obj).length == 0) {
					$("#" + objId + "-erro").removeClass("error-txts");
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
		
		/*判断提交值的长度*/
		function checkValLength2(objId){
			var obj = $("#" + objId).val();
			//setTimeout(function(objId){
			if($.trim(obj).length == 0) {
				$("#" + objId + "-erro").removeClass("ipt-txt-Err");
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
		
		
	});
	
	
	
	/* 切换验证码 */
	function changeImg(){
		var time=new Date().getTime();
    	var url = r_path + "/validatecode.jpg?time="+time;
    	$("#validateImg").attr("src",url);
    	$("#valiCode").val("");
	}
	
