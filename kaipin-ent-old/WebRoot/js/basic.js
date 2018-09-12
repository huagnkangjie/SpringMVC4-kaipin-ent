

(function(){
	DropSlideNav($("#message-details"),".message-details");
	var time = "";
	DropSlideNav($("#personal-setting"),".personal-setting");
	function DropSlideNav($parent,$childs){
		$parent.hover(function(){
			time=setTimeout(function(){
			 	$parent.find($childs).stop(true,true).slideDown("fast");
			 },400);
		},function(){
			clearTimeout(time);//清除计时器
			time=setTimeout(function(){
				$parent.find($childs).stop(true,true).slideUp("fast");	
			},10);//鼠标移除元素区域子元素消失
		});
	}
	
	
	//修改背景
	$("#change-background-img").find(".edit-bgImg").click(function(){
		$("#change-background-img").find(".set-bg-btns").fadeIn();
	});
	$("#change-background-img").find(".close-bg-btns").click(function(){
		$("#change-background-img").find(".set-bg-btns").fadeOut();
	});
	
	$("#touch-change").on("click",function(){
		//判断是否有网址
		$.ajax({
			cache : false,
			async : false,
			type : 'POST',
			url : r_path + '/basicConctroller/getWebsite.do',
			success : function (data){
				if(isNotEmpty(data)){
					var datas = eval('('+data+')');
					if(datas.success){
						var url = datas.obj;
						url = url.replace("https://","")
						url = url.replace("http://","")
						window.open ("http://"+url); 
					}else{
						$("#change-background-img .edit-bgImg").trigger("click");
					}
				}
				
			}
		});
	});
	
//	$("#select-bgImg-file").click(function(){
//		$("#change-background-img").find(".set-bg-btns").hide();
//		$("#save-and-cancel").show();
//	});
	
	$("#save-and-cancel").find(".cancle").click(function(){
		$("#save-and-cancel").hide();
	});
	
	//company-title-details 公司简称
	
	
	//profile-full-details 企业简介
	/*var editCompanyInfo = $("#edit-company-info");
	var panelWinDefaulte = $(".panel-win-defaulte");
	var $cancleCompanyInfo = $("#profile-full-details").find(".cancle-btn");*/
	
	//编辑企业
	
	//企业简介
	/*editWinShow(editCompanyInfo,panelWinDefaulte);
	editWinHide($cancleCompanyInfo,editCompanyInfo,panelWinDefaulte);*/
	/*
	 *  方法名：editWinShow
	 * 	参    数：$obj----》编辑按钮
	 * 		 $panel---》打开的窗口
	 */
	
	
	/*
	 *  方法名：editWinHide
	 * 	参    数：$obj----》取消按钮
	 * 		 $flag ---编辑按钮
	 * 		 $panel---》打开的窗口对象
	 */
	/*function editWinHide($obj,$flag,$panel){
		$obj.click(function(){
			$flag.data("open","open");
			$panel.hide();
		});
	}*/
	
	
	
	/*展开收起*/
	$("#spread-outs").click(function(){
		var flag = $(this).data("flag");
		debugger;
		if(flag=="open"){
			$("#detail33").hide();
			$("#detail3").show();
			$(this).html("-收起").data("flag","close");
			$("#profile-full-details").find(".simple-introduction").hide();
			$("#profile-full-details").find(".details-introduction").show();
		}else{
			$("#detail3").hide();
			$("#detail33").show();
			$(this).html("+展开").data("flag","open");
			$("#profile-full-details").find(".simple-introduction").show();
			$("#profile-full-details").find(".details-introduction").hide();
		}
	});

	
	//宣讲会和校招岗位切换
	$("#tab-title-change").find("li").click(function(){
		$(this).addClass("tab-active").siblings().removeClass("tab-active");
		var index = $(this).index();
		$("#confer-full-info").find(".confer-detalis-cons").eq(index).show().siblings().hide();
		if(index == 1){
			getPostion();
		}
	});
	//	获取首页职位列表
	function getPostion(){
		$.ajax({                
			cache: true,    
			async: true, 
			type: "POST",                
			url:  r_path + '/position/datagridIndex.do',                
			data:{
				page : 1,
				rows : 10
			},              
			error: function(request) {                    
			},                
			success: function(data) {
				var datas = eval('('+data+')');
				if(datas.rows.length > 0){
					$("#noPosition").hide();
					$("#positions").show();
					$("#school-hover").empty();
					var html = "";
					for(var i = 0; i < datas.rows.length; i++){
						if(i%2==0){
							html = html + "<tr>"
						}else{
							html = html + "<tr class='bgColor' onclick='positionDetail(this);'>"
						}
						var times = getTimeByMillis(datas.rows[i].create_time);
						html = html +
										"<td title='"+datas.rows[i].position_name+"'><div class='maxW'><a href='javascript:void(0);' class='positiondetail' data-tag='"+datas.rows[i].id+"'>"+datas.rows[i].position_name+"</div></a>" +
										"<input type='hidden' value='"+datas.rows[i].id+"'/></td>"+
										"<td title='"+datas.rows[i].jobType+"'>"+datas.rows[i].jobType+"</td>"+
										"<td title='"+datas.rows[i].docname+"'>"+datas.rows[i].docname+"</td>"+
										"<td title='"+datas.rows[i].create_time+"'>"+times+"</td>"+
									"</tr>";
						
					}
					$("#school-hover").append(html);
					$("#school-hover").find("tr").click(function(){
						var id = $(this).find("input[type='hidden']").val();
						positionDetail(id);
					}) ;
				}
			}            
		});
	}
	
	function positionDetail(obj){
		location.href= r_path + "/position/detail.do?postionId="+obj;
	}

	//个人设置显示关闭
	mainPanelShwo($("#account-set-btn"),$("#personal-set-panel"));
	mainPanelHide($("#close-accountSet"),$("#personal-set-panel"));

	//修改密码
	$("#update-passwordBtn").click(function(){
		$("#updata-password-panel").show();
		$("#tip-error").hide();
		$("#personal-set-panel").hide();
	});
	
	//邮箱配置取消
	$("#cancle-mail").click(function(){
		$("#bind-firm-eamil").hide();
		$("#personal-set-panel").show();
	});
	
	//邮箱配置提交
	$("#config-mail-btn").click(function(){
		var id = $("#config-configId").val();
		var mailUsername = $("#config-from").val();
		var mailPassword = $("#config-pw").val();
		var mailHost = $("#config-host").val();
		var mailPort = $("#config-port").val();
		if(mailUsername.length == 0){
			alert("请填写邮箱");
			return;
		}
		if(mailPassword.length == 0){
			alert("请填写邮箱密码");
			return;
		}
		if(mailHost.length == 0){
			alert("请填写邮箱服务器");
			return;
		}
		if(mailPort.length == 0){
			alert("请填写邮箱服务器端口");
			return;
		}
		$("#config-mail-btn").hide();
		$("#config-mail-btn-ing").css("display","inline-block");
		var timers = "";
		clearTimeout(timers);
		timers = setTimeout(function(){
			$.ajax({
				cache : false,
				async : true,
				type : 'POST',
				url : r_path + '/basicConctroller/config.do',
				data : {
					id : id,
					mailUsername : mailUsername,
					mailPassword : mailPassword,
					mailHost : mailHost,
					mailPort : mailPort,
					oper : 'mail'
				},
				success : function (data){
					var datas = eval('('+data+')');
					if(datas.success){
						$("#bind-success-tip").show();
						var timer = "";
						clearTimeout(timer);
						timer = setTimeout(function(){
							$("#bind-success-tip").hide();
						},1000);
					}else{
						$("#bind-fail-tip").show();
						var timer = "";
						clearTimeout(timer);
						timer = setTimeout(function(){
							$("#bind-fail-tip").hide();
						},1000);
						
					}
					$("#config-mail-btn").show();
					$("#config-mail-btn-ing").hide();
				}
			});
		},500);
	})
	
	/*下拉*/
	showDownlists($("#expected-start-time .select_box"),$("#basic-endTimer-select .select_box").find(".option"));
	showDownlists($("#basic-endTimer-select .select_box"),$("#expected-start-time .select_box").find(".option"));
	showDownlists($("#start_time_history_edit .select_box"),"");
	showDownlists($("#start_time_history .select_box"),"");//以往视频发布
	showDownlists($("#start_time_xjhEditStart .select_box"),"");//维护宣讲会开始时间
	showDownlists($("#end_time_xjhEditEnd .select_box"),"");//维护宣讲会结束时间
	showDownlists($("#exam-select-mark .select_box"),"")
	
	
	function showDownlists($obj,$closeObj){
		if($closeObj==""){
			$obj.click(function(event){
				event.stopPropagation();
				var $this = $(this);
				$this.find(".option").toggle();
				$this.parent().siblings().find(".option").hide();
			});
		}else{
			$obj.click(function(event){
				event.stopPropagation();
				var $this = $(this);
				$this.find(".option").toggle();
				$closeObj.hide();
				$this.parent().siblings().find(".option").hide();
			});
		}
		
	};
	
	$("#select-sex").find(".select_box").click(function(event){
			event.stopPropagation();
			var $this = $(this);
			$this.find(".option").toggle();
	});
	
//	selectHide($(document),"click");
//	selectHide($(document),"scroll");
	
	/*赋值给文本框*/
	getValue($("#expected-start-time .option a"));
	getValue($("#basic-endTimer-select .option a"));
	getValue($("#select-sex .option a"));
	getValue($("#start_time_history_edit .option a"));
	getValue($("#start_time_history .option a"));//维护宣讲会开始时间
	getValue($("#start_time_xjhEditStart .option a"));//维护宣讲会开始时间
	getValue($("#end_time_xjhEditEnd .option a"));//维护宣讲会结束时间
	getValue($("#exam-select-mark .option a"));//添加笔试题考试
	
	function getValue($obj){
		$obj.click(function(){
			var $this = $(this);
			var value=$this.text();
			$this.parent().siblings(".select_txt").text(value);
		});
	}
	
	function selectHide(obj,$fun){
		obj.on($fun,function(event){
			var eo=$(event.target);
			if($(".select_box").is(":visible") && eo.attr("class")!="option" && !eo.parent(".option").length){
				$('.option').hide();
			}
		});
	}



	//切换会议
	$("#change-meetings").find("li").click(function(){
		var index = $(this).index();
		$(this).addClass("bgColor-active").siblings().removeClass("bgColor-active");
		$("#change-meeting-detaiils").find(".meetings").eq(index).show().siblings().hide();
	});
	
	//发布宣讲会
	mainPanelShwo($("#send-preachBtn"),$("#change-meetings").parents(".preacp-and-oldvideo"));
	mainPanelHide($("#close-meetings"),$("#change-meetings").parents(".preacp-and-oldvideo"));
	
	/*
	*   编辑宣讲会
	*/
	
//	mainPanelShwo($("#upload-confers"),$("#edit-preacp-contents"));
//	mainPanelShwo($("#rightOfedit-comInfo"),$("#edit-company-details"));
//	mainPanelHide($("#close-company-details"),$("#edit-company-details"));
	mainPanelShwo($("#confer-full-info").find("li .pre-edit"),$("#edit-preacp-contents"));
	mainPanelHide($("#close-preacp-contents"),$("#edit-preacp-contents"));
	function mainPanelShwo($obj,$showObj){
		$obj.click(function(){
//			$("#pushAllXjh").show();
			$("#xhjImg").hide();
			$("#xjhAnnexId").val("");
			$("#historyFormReset").trigger("click");
			$("#xjhFormReset").trigger("click");
			//$("#xjhForm").reset()
			//document.xjhForm.reset();
			$("#uploadVedioSuccess").hide();//上传成功
			$("#upHistoryVedioTrriger").show();//选择文件
			$("#upHistoryVedioUrl").val("");//选择文件
			$("#tzui-loading-overlay").show();
			//以往的宣讲会视频表单 
			$("#historyImg").hide();
			//$("#historyForm").reset()
			//document.historyForm.reset();
			$showObj.show();
		});
	}
	
	function mainPanelHide($obj,$showObj){
		$obj.click(function(){
			$("#tzui-loading-overlay").hide();
			$showObj.hide();
		});
	}
	
	$("#upload-confers").click(function(){
		$("#send-preachBtn").trigger("click");
	});

})();


(function($){
	
	$.fn.fandialog = function(options){
		//this.each(function(){
			//$(this).click(function(){
				var opts = $.extend({},$.fn.fandialog.methods,$.fn.fandialog.defalutes,options);
				var $dialog = opts.templates(opts);
				opts.event(opts,$dialog);
			//});
		//});
	};
	
	/*方法设置*/
	$.fn.fandialog.methods = {
		templates:function(opts){
			var $dialog = $(".tm_alert");
			if($dialog.html()==undefined){
				$dialog = $(
				"		<div class='tm_alert'>"+
				"			<div class='tm_title'>"+
				"				<h2 id='tm_title'>"+opts.title+"</h2>"+
				"				<a href='javascript:void(0)' class='tm_close'></a>"+
				"			</div>"+
				"			<div class='tm_contents'>"+
				"			<p class='tips-title'>"+opts.contents+"</p>"+
				"			</div>"+
				"			<div class='tm-btns'>"+
				"				<a href='javascript:void(0)' class='tm_sure'>确认</a>"+
				"				<a href='javascript:void(0)' class='tm_cancel'>取消</a>"+
				"			</div>"+
				"		</div>"
				);
				
				$("body").append($dialog);
				$("body").append("<div id='tm_layer'></div>");
				var $layer = $("#tm_layer");
				$layer.width($(window).width());
				$layer.height($(window).height());
				$layer.css({"position":"fixed","top":"0px","background":"#000000","left":"0px","zIndex":"999","opacity":"0.5","filter":"alpha(opacity:50)"});
				
			}else{
				$dialog.find("#tm_title").html(opts.title);
				$dialog.find(".tm_contents").html(opts.contents);
			}
			return $dialog;
		},
		
		event:function(opts,$dialog){
			var $this = this;
			$this._getCenter($dialog);/*初始化元素居中*/
			$this._beginDrag($dialog);/*初始化元素拖动*/
			$this._close($dialog,$this);/*关闭*/
			$this._sure(opts,$dialog,$this);/*确认*/
			$this._cancel(opts,$dialog,$this);/*取消*/
			/*随窗口变化而变化*/
			$(window).resize(function(){
				$this._getCenter($dialog);
				var $layer = $("#tm_layer");
				$layer.width($(window).width());
				$layer.height($(window).height());
			});
		},
		
		_beginDrag:function($dialog){/*开始拖拽*/
			var $tm_title = $dialog.find(".tm_title");
			$tm_title.mousedown(function(e){
				var oldX = e.clientX;
				var oldY = e.clientY;
				var left = $dialog.offset().left;
				var top = $dialog.offset().top;
				var height = $dialog.height();
				var width = $dialog.width();
				var scrollTop = $(window).scrollTop();
				var maxLeft = $(window).width() - width;
				var maxTop = $(window).height() - height;
				tm_forbiddenSelect();
				var isFlag = true;
				$(document).mousemove(function(e){
					if(isFlag){
						var newX = e.clientX;
						var newY = e.clientY;
						var newLeft = newX - oldX + left;
						var newTop = newY - oldY + top-scrollTop;
						if(newLeft<=0)newLeft=0;
						if(newTop<=0)newTop = 0;
						if(newLeft>maxLeft)newLeft=maxLeft;
						if(newTop>maxTop)newTop=maxTop;
						$dialog.css({"position":"fixed","zIndex":"10000","left":newLeft,"top":newTop});
					}
				}).mouseup(function(){
					isFlag = false;
					tm_autoSelect();
				});
			});
		},
		
		/*设置层居中*/
		_getCenter:function($dialog){
			var height = $dialog.height();
			var width = $dialog.width();
			var winW = $(window).width();
			var winH = $(window).height();
			var left = (winW - width)/2;
			var top =  (winH - height)/2;
			$dialog.css({"position":"fixed","zIndex":"10000","left":left,"top":top});
		},
		_close:function($dialog,$this){
			var $tm_close = $dialog.find(".tm_close");
			$tm_close.click(function(){
				$this._allClose($dialog);				
			});
		},
		_sure:function(opts,$dialog,$this){
			var $sure = $dialog.find(".tm_sure");
			$sure.click(function(){
				if(opts.click){
					opts.click(true);
				}
				$this._allClose($dialog);
			});
		},
		_cancel:function(opts,$dialog,$this){
			var $cancel = $dialog.find(".tm_cancel");
			$cancel.click(function(){
				if(opts.click){
					opts.click(false);
				}
				$this._allClose($dialog);
			});
		},
		_allClose:function($dialog){
				setTimeout(function(){
					$dialog.remove();
				},10);
				setTimeout(function(){
					var $layer = $("#tm_layer");
					$layer.fadeOut("slow",function(){
						$layer.remove();
					});
				},16);
		}
	};
	
	$.fn.fandialog.defalutes = {
		title:"提示信息",
		contents:"您是否要删除该老师？",
		teacherName:"张亮",
		job:"历史老师"

	};
	
	
	/**
		 * 禁止窗体选中
		 */
		function tm_forbiddenSelect() {
			$(document).on("selectstart", function() {
				return false;
			});
			document.onselectstart = new Function("event.returnValue=false;");
			$("*").css({
				"-moz-user-select" : "none"
			});
		}
		
		
		/* 窗体允许选中 */
		function tm_autoSelect() {
			$(document).on("selectstart", function() {
				return true;
			});
			document.onselectstart = new Function("event.returnValue=true;");
			$("*").css({
				"-moz-user-select" : ""
			});
		}
		
		$(".option a").click(function(){
			var value = $(this).data("tag");
			$("#sex").val(value);
		});
		
		
		/*密码修改校验*/
		
		/*当前密码验证*/
		$("#pw-now").blur(function(){
			 pwNowValidata();
		});
		
		function pwNowValidata(){
			var pwNow = $("#pw-now").val();
			var flag = false;
			if(pwNow.length != 0){
				if(pwNow.length >= 6 && pwNow.length <= 32){
					if(pwNow.trim() != ''){
						$.ajax({
							cache : false,
							async : false,
							type : 'POST',
							url : r_path + '/basicConctroller/modiyPw.do',
							data : {
								pw : pwNow,
								oper : '0'
							},
							success : function (data){
								var datas = eval('('+data+')');
								if(datas.success){
									$("#tip-error").hide();
									flag = true;
								}else{
									$("#tip-error").html("密码不正确");
									$("#tip-error").show();
									flag = false;
								}
							}
						});
					}
				}else{
					$("#tip-error").html("密码格式不对");
					$("#tip-error").show();
				}
			}
			return flag;
		}
		
		/*修改密码校验*/
		$("#pw").blur(function(){
			pwValidata();
		});
		function pwValidata(){
			var pw = $("#pw").val();
			var length = pw.length;
			var flag = false;
			if(length != 0){
				if(length < 6 || length > 32 ){
					$("#tip-error").html("密码长度6-32");
					$("#tip-error").show();
					flag = false;
				}else{
					flag = true;
					$("#tip-error").hide();
				}
			}
			return flag;
		}
		
		/*确认密码校验*/
		$("#pw-sure").blur(function(){
			pwSureValidata();
		});
		function pwSureValidata(){
			var pw = $("#pw").val();
			var pwSure = $("#pw-sure").val();
			var length = pwSure.length;
			var flag = false;
			if(length != 0){
				if(length < 6 || length > 32 ){
					$("#tip-error").html("密码长度6-32");
					$("#tip-error").show();
					flag = false;
				}else{
					if(pw == pwSure){
						flag = true;
						$("#tip-error").hide();
					}else{
						flag = false;
						$("#tip-error").show();
						$("#tip-error").html("两次密码不同");
					}
				}
			}
			return flag;
		}
		
		$("#cancel-submit").click(function(){
			$("#updata-password-panel").hide();
			$("#personal-set-panel").show();
		});
		
		/*修改密码提交*/
		$("#sure-submit").on("click",function(){
			if(pwNowValidata()){
				if(pwValidata()){
					if(pwSureValidata()){
						var pwNow = $("#pw").val();
						$.ajax({
							cache : false,
							async : false,
							type : 'POST',
							url : r_path + '/basicConctroller/modiyPw.do',
							data : {
								pw : pwNow,
								oper : '1'
							},
							success : function (data){
								var datas = eval('('+data+')');
								if(datas.success){
									$("#updata-password-panel").hide();
				 					$("#updata-password-success").show();
									$("#tip-error").hide();
								}
							}
						});
					}
				}
			}
		});
		
		/*修改密码跳转登录*/
		$("#sure-modifyPw-btn").click(function(){
			window.location.href= r_web_url + "/login";
//			window.location.href=r_path + "/loginController/index.do";
		});
		
		
		/*修改手机号*/
		//验证手机号码
		$("#mod-phone").blur(function (){
			validatemobile();
		});
		
		
		/*手机号码验证*/
		function validatemobile(){
			var mobile = $("#mod-phone").val();
			if(mobile.length == 0){
				$("#mod-phone-tip").hide();
				return;
			}
	        if(mobile.length!=11)
	        {
				$("#mod-phone-tip").show();
				$("#mod-phone-tip").html("请输入11位有效手机号码");
	            return false;
	        }
	        
	        var myreg = /^1\d{10}$/;
	        if(!myreg.test(mobile))
	        {
	        	$("#mod-phone-tip").show();
	        	$("#mod-phone-tip").html("请输入11位有效手机号码");
	            return false;
	        }else{
	        	//先验证手机号是否被注册
				//再验证手机验证码
				if(doValidataPhone(mobile)){
					$("#mod-phone-tip").hide();
					return true;
				}else{
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
		
		/**
		 * 获取手机验证码
		 */
		
		var countdown=60;
		$("#getPhoneCode").off().click(function(){
			if(validatemobile()){
				$("#mod-phone-tip").hide();
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
		    	$("#getPhoneCodeIng").html(countdown + "s 后重发 ");
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
				async : false,
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
		
		$("#mod-phone-code").blur(function(){
			validatemobile();
		});
		/*手机验证码校验*/
		function phoneCodeValidate(){
			var phoneCode = $("#mod-phone-code").val();
			var length = $.trim(phoneCode).length;
			if(length == 0){
				$("#mod-phone-code-tip").hide();
			}else{
				if(length == 4){
					//先验证手机号是否 
					$("#mod-phone-code-tip").hide();
					if(checkPhoneCode(phoneCode)){
						$("#mod-phone-code-tip").hide();
						return true;
					}else{
						$("#mod-phone-code-tip").show();
						$("#mod-phone-code-tip").html("请输入有效手机验证码");
						return false;
					}
					
				}else{
					$("#mod-phone-code-tip").show();
					$("#mod-phone-code-tip").html("请输入有效手机验证码");
					return false;
				}
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
		
		
		/*修改手机提交*/
		$("#mod-phone-btn").click(function(){
			var phone = $("#mod-phone").val();
			debugger;
			if(validatemobile()){
				if(phoneCodeValidate()){
					$.ajax({
						cache : false,
						type : "POST",
						url : r_path + '/basicConctroller/config.do',
						data : {
							phone : phone,
							oper : 'user-phone'
						},
						async : false,
						error : function(request) {
							alert("网络异常，请稍后再试！");
						},
						success : function(data) {
							var datas = eval('('+ data +')');
							if(datas.success){
								alert("修改成功");
								$("#mod-phone").val("");
								$("#mod-phone-code").val("");
								$("#change-ph-num").hide();
								$("#tzui-loading-overlay").hide();
							}
						}
					});
				}
			}
		});
		
		
		/*修改个人中心*/
		$("#save-user-info").click(function(){
			var entName = $("#mod-entName").val();
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
				url : r_path + '/basicConctroller/config.do',
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
		
		/**********************************************************/
		/*数据验证*/
		/*企业人数*/
		$("#peopleNumber2").keyup(function(){
			var v = $("#peopleNumber2").val();
			var str = /^[1-9]\d*$/;
			if(str.test(v)){
				$("#peopleNumber2").val(v);
			}else{
				$("#peopleNumber2").val("");
			}
			
		});
		$("#peopleNumber").keyup(function(){
			var v = $("#peopleNumber").val();
			var str = /^[1-9]\d*$/;
			if(str.test(v)){
				$("#peopleNumber").val(v);
			}else{
				$("#peopleNumber").val("");
			}
			
		});
		/*企业端口*/
		$("#config-port").keyup(function(){
			var v = $("#config-port").val();
			var str = /^[1-9]\d*$/;
			if(str.test(v)){
				$("#config-port").val(v);
			}else{
				$("#config-port").val("");
			}
			
		});
		/*企业名字长度*/
		$("#entSimpleName").keyup(function(){
			var v = $("#entSimpleName").val();
			if(v.length > 0){
				if(v.length > 8){
					if(v.trim() == 0){
						$("#entSimpleName").val("");
					}else{
						v = v.substring(0,8);
						$("#entSimpleName").val(v);
					}
					
				}
			}
		});
})(jQuery);
