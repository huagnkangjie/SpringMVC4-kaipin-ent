/**
 * 登录 注册
 */

/**
 * 登录
 */
function login(){
	if(lg_emailValidata()){
		userValidata();
	}
}

/**
 * 注册
 */
function register() {
	if (emailValidata()) {
		if (entNameValidata()) {
			if (pw1Validata()) {
				if (pw2Validata()) {
					$.ajax({
						cache : true,
						type : "POST",
						url : r_path
								+ '/registerController/register.do',
						data : $('#form2').serialize(),// 你的formid
						async : false,
						error : function(request) {
							alert("网络异常，请稍后再试！");
						},
						success : function(data) {
							location.href = r_path
									+ "/loginController/main.do";
						}
					});
				}
			}
		}
	}
}
/**
 * 登录邮箱地址验证
 * @returns {Boolean}
 */
function lg_emailValidata(){
	var lg_email_v = $("#lg_email").val();
	if(emailCheck(lg_email_v)){
		$("#tip1").hide();
		return true;
	}else{
		$("#tip1").show();
		$("#tip1").html("邮箱格式不正确");
		return false;
	}
	
}
/**
 * 登录用户 账号密码验证
 */
function userValidata(){
	var userName = $("#lg_email").val();
	var password = $("#lg_pw").val();
	$.ajax({
		cache : false,
		async : false,
		type : 'POST',
		url : r_path + '/loginController/login.do',
		data : {
			userName : userName,
			password : password
		},
		success : function(data) {
			var datas = eval('(' + data + ')');
			if (datas.success) {
				location.href = r_path + "/loginController/main.do";
			} else {
				$("#tip1").show();
				$("#tip1").html("请输入正确的邮箱和密码");
			}
		}
	});
}

/* 邮箱验证 */
function emailValidata() {
	var email = $("#email").val();
	if (emailCheck(email)) {
		$("#tip2").hide();
		// 唯一性验证
		$.ajax({
			cache : false,
			async : false,
			type : 'POST',
			url : r_path + '/registerController/emailValidata.do',
			data : {
				email : email
			},
			success : function(data) {
				var datas = eval('(' + data + ')');
				if (datas.success) {
					$("#tip2").show();
					$("#tip2").html("该邮箱已经注册");
					email_val = "false"
				} else {
					$("#tip2").hide();
					email_val = "true";
				}
			}
		});
		if (email_val == "true") {
			return true;
		} else if (email_val == "false") {
			return false;
		}
	} else {
		$("#tip2").show();
		$("#tip2").html("邮箱格式不对");
		return false;
	}
}
function emailCheck(value) {
	var pattern = /^([\.a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/;
	if (!pattern.test(value)) {
		return false;
	}
	return true;
}
/* 企业名称 */
function entNameValidata() {
	var entName = $("#entName").val();
	if (entName == '' || entName.length == 0) {
		$("#tip2").show();
		$("#tip2").html("企业名称不能为空");
		return false;
	}else if(entName.length > 32){
		$("#tip2").show();
		$("#tip2").html("企业名称不能超过32位");
		return false;
	}else {
		$.ajax({
			cache : false,
			async : false,
			type : 'POST',
			url : r_path + '/registerController/entNameValidata.do',
			data : {
				entName : entName
			},
			success : function(data) {
				var datas = eval('(' + data + ')');
				if (datas.success) {
					$("#tip2").show();
					$("#tip2").html("该企业已经注册");
					entName_val = false;
				} else {
					$("#tip2").hide();
					entName_val = true;
				}
			}
		});
		if (entName_val == true) {
			return true;
		} else if (entName_val == false) {
			return false;
		}
	}
}
/* 密码验证 */
function pw1Validata() {
	var pw1 = $("#pw1").val();
	if (pw1 != '' && pw1.length >= 6 && pw1.length <= 32) {
		$("#tip2").hide();
		return true;
		// 唯一性验证
	} else if (pw1 != '' && (pw1.length < 6 || pw1.length > 32)) {
		$("#tip2").show();
		$("#tip2").html("密码长度6 - 32 位");
		return false;
	}
}
function pw2Validata() {
	var pw1 = $("#pw1").val();
	var pw2 = $("#pw2").val();
	if (pw1 == pw2 && pw1.length >= 6 && pw1 != '') {
		$("#tip2").hide();
		return true;
		// 唯一性验证
	} else if (pw1.length < 6) {
		$("#tip2").show();
		$("#tip2").html("密码长度6 - 32位");
		return false;
	} else {
		$("#tip2").show();
		$("#tip2").html("两次输入密码不相同");
		return false;
	}
}