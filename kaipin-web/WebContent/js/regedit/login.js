			

			function login(){
				if(emailValidata()){
					var s = pw1Validata();
					if(pw1Validata()){
						$("#loginBtn").hide();
						$("#loginBtn2").show();
						if(oper == 0){
							oper = 1;
							setTimeout(function(){
								var userName = $("#email").val();
								var password = $("#password").val();
								var timeer = new Date().getTime();
								sss = $.ajax({
									cache : false,
									async : true,
									dataType: 'json',  
							        crossDomain: true,
									type : 'GET',
									jsonp: "callback",//服务端用于接收callback调用的function名的参数  
							        jsonpCallback:"callback",//callback的function名称  
									url : r_sso_url + '/web/auth/login?temp=' + timeer,
									data : {
										username : $.trim(userName),
										password : password
									},
									xhrFields: {
					                      withCredentials: true
					              },
									complete : function(data) {
										var datastr = data.responseText;
										var datas = eval('('+datastr+')');
										var code = datas.code ;
										var msg = datas.message;
										var uType = datas.u_type;
										debugger;
										if(code == '0'){
											//location.href=datas.redirect_uri;
											msg += "，跳转中...";
											var redirect_uri = datas.redirect_uri;
											var url = "";
											if(redirect_uri.startWith("http://u.kaipin.")){
												url =  r_university_url + "/home";
											}else if(redirect_uri.startWith("http://c.kaipin.")){
												url = r_company_url + "/home";
												
											}
											
											jump(url);
											
										}else if(code == '100'){//未选择角色
// 											var userName = $("#email").val();
// 											var userIds = chooseRole(userName);
											var userIds = datas.uid;
											var url =r_path + "/regedit/init?oper=regedit_role&id=" + userIds;
											jump(url);
										}else if(code == '511'){
											var url =r_path + "/regedit/init?oper=regedit_basic_ent&id=" + datas.uid;
											jump(url);
										}else if(code == '512'){
											var url =r_path + "/regedit/init?oper=regedit_basic_sch&id=" + datas.uid;
											jump(url);
										}else if(code == '550'){//邮件未验证
											var orgIds = datas.group_id;
											var url = "";
											if(orgIds == '-1'){//基本信息未填写
												url = r_path + "/regedit/init?oper=regedit_basic_"+uType+"&id=" + datas.uid;
											}else{
												var redirect_uri = datas.redirect_uri;
												if(redirect_uri == '0'){
													url = r_path + "/regedit/init?oper=certificate_"+uType+"&userId="+datas.uid+"&orgId="+orgIds;
												}else if(redirect_uri == '1'){
													url = r_path + "/regedit/init?oper=check_mail&userId="+datas.uid;
												}
												
											}
											jump(url);
										}else if(code == '560'){
											var orgIds = datas.group_id;
											debugger;
											url = r_path + "/regedit/init?oper=certificate_"+uType+"&userId="+datas.uid+"&orgId="+orgIds;
											jump(url);
										}else if(code == '999'){//学生端暂时未提供
											var url = r_path + "/regedit/stu-qr-code";
											jump(url);
										}
										
										$("#tip").find(".tips").html(msg);
										$("#loginBtn").show();
										$("#loginBtn2").hide();
										$("#tip").show();
										oper = 0;
									}
								});
							},300);
						}
						
					}
				}
			}
			
			function jump(url){
				setTimeout(function(){
					location.href = url;
				},500);
			}
			
			function chooseRole(userName){  
				var userIds = "";
				$.ajax({
					cache : false,
					async : false,
					url : r_path + '/regedit/getLocalUserByEmailOrPhone',
					data : {
						userName : userName
					},
					success : function(data) {
						if(data.success){
							userIds = data.obj[0].id;
						}
					}
				}); 
				return userIds;
			}
			
			String.prototype.startWith=function(s){
			  if(s==null||s==""||this.length==0||s.length>this.length)
			   return false;
			  if(this.substr(0,s.length)==s)
			     return true;
			  else
			     return false;
			  return true;
			 }