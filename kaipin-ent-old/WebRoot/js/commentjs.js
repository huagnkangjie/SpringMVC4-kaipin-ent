
	(function(){
		
		//添加斑马线 
		$(".tdbg-color").find("tr:odd").addClass("tr-color");
		$("#pnl-show-lists").find("dl:even").addClass("hoverBgColor");
		
		//全选
		var selectSingle ;
		$("#select-all").on("click",function(){
			$(".tdbg-color").find("input[type='checkbox']").prop("checked",this.checked);
			selectSingle=$(".tdbg-color").find("input[type='checkbox']:checked").length;
			if(this.checked){
				$(".totalBtn").find("a").addClass("blue-btn");
			}else{
				$(".totalBtn").find("a").removeClass("blue-btn");
			}
		});
		var $singleCkeckbox = $(".tdbg-color").find("input[type='checkbox']");
		$singleCkeckbox.click(function(){
			var len = $(".tdbg-color").find("input[type='checkbox']:checked").length;
			if(selectSingle!=len){
				$("#select-all").prop("checked",false);
			}else{
				$("#select-all").prop("checked",true);
			}
			if(len<1){
				$(".totalBtn").find("a").removeClass("blue-btn");
			}
			if(len>1){
				$(".totalBtn").find("a").addClass("blue-btn");
			}
		});
	})();
			
	(function(){/*搜索下拉框显示*/
		var _height = $(window).height()-60;
		$("#iFrame1").css("height",_height);
		
		var coverLayer = $("#cover-layer");
		//搜索列表
		$("#hdNav-panelLists").find(".search-btn").click(function(){
			coverLayer.show();
			$("#hdNav-panelLists").fadeOut("fast",function(){
				$("#search-lists").fadeIn("fast",function(){
					$("#search-lists").find(".sl-position").focus();
					$("#search-lists").find(".sl-contents").addClass("sl-contents-li");
					$("#search-lists").find(".sl-contents").slideToggle("fast");
				});
			});
		});
		
		//遮盖层事件
		coverLayer.on("click",function(){
			$("#search-lists").find(".sl-close").trigger("click");
		});
		
		//获取焦点显示
		$("#hdNav-panelLists").find(".nav-search input").focus(function(){
			$("#hdNav-panelLists").find(".search-btn").trigger("click");
		});
		//关闭下拉搜索框
		$("#search-lists").find(".sl-close").click(function(){
			$("#search-lists").find(".sl-contents").slideToggle("fast",function(){
				$("#search-lists").find(".sl-contents").removeClass("sl-contents-li");
				$("#search-lists").fadeOut("fast",function(){
					$("#hdNav-panelLists").fadeIn();
					coverLayer.hide();
				});
			});
		});
		
		$('input:text').each(function(){  
			var txt = $(this).val();  
			$(this).focus(function(){  
				if(txt === $(this).val()) 
					$(this).val("");  
			}).blur(function(){  
				if($(this).val() == "") 
					$(this).val(txt);  
			});  
		})
		
	})();
				
	(function(){
		/*条件筛选*/
			$(".select_box").click(function(event){
				event.stopPropagation();
				$(this).find(".option").toggle();
				$(this).parents("li").toggleClass("active");
				$(this).parent().siblings().find(".option").hide();
				$(this).parents("li").siblings().removeClass("active");
			});
			
			selectHide($(document),"click");
			selectHide($(document),"scroll");
			
			
			/*赋值给文本框*/
			$("#basic-info-select .option").on("click","a",function(){
				var value=$(this).text();
				var data_tag = $(this).data("tag");
				var title = $(this).parents(".option").find(".all").text();
				$(this).parents(".option").siblings(".select_txt").text(title+" : "+value);
				if(data_tag != 'undefined'){
					if(data_tag != '-1'){
						$(this).parents(".option").siblings(".val").val(data_tag);
					}else{
						$(this).parents(".option").siblings(".val").val("");
					}
				}
				$("#select_value").val(value);
				
//				test($(this).parents(".select_box").find(".select_txt"),$(this));
				
			});
			
			
//			function test(obj,child){
//				obj.on("DOMNodeInserted",function(){
//					/*var txt = child.data("tag");
//					return txt;*/
//				});
//				obj.trigger('DOMNodeInserted');
//			}
		
			function selectHide(obj,$fun){
				obj.on($fun,function(event){
					var eo=$(event.target);
					if($(".select_box").is(":visible") && eo.attr("class")!="option" && !eo.parent(".option").length){
						$('.option').hide();
						$(".select_box").parents("li").removeClass("active");
					}
				});
			}
			
		
		//详细列表筛选
		var conditionList = $("#condition-details-lists");
		var $aHover = conditionList.find(".condition-adress-header");
		//var $aHover = conditionList.find(".condition-adress-header a").not(".btn-more");
		var $bHover =  conditionList.find(".condition-position-header");
//		var $bHover =  conditionList.find(".condition-position-header a").not(".btn-more")
		var $adressCondition = conditionList.find(".adress-conditions a");
		var $positionConditions = conditionList.find(".position-conditions a");
		
		
		
		/*显示隐藏*/
		$("#condition-details-lists").find(".btn-more").click(function(){
			$(this).parents(".positionAll-style").find(".others").slideToggle();
			$(this).find(".icon").toggleClass("iconT");
		});
		
		conditionShow($aHover,$adressCondition);
		otherCondition($adressCondition,$aHover);
		conditionShow($bHover,$positionConditions);
		otherCondition($positionConditions,$bHover);
		function conditionShow($obj,$siblings){//加载显示页操作
			$obj.on("click",'a.clickActive',function(){
//				 alert(2);
				$(this).addClass("active").siblings().removeClass("active");
				$siblings.removeClass("active");
			})
		}
		
		function otherCondition($obj,$next){//点击后加载显示页操作
			$obj.click(function(){
				$(this).addClass("active").siblings().removeClass("active");
				$next.removeClass("active");
			})
		}
		
	})();
	
	
	(function(){
		/*登录注册*/
		var businessLoginBtn = $("#business-login");//登录
		var $overlay = $("#tzui-loading-overlay");//遮盖层
		var wdBusinessLogin = $(".wd-businessLogin");//登录注册框
		var businessRegister = $("#business-register");
		var close = $("#wd-businessLogin").find(".close-btn");//登录注册关闭
		var findPassword = $("#find-password");//找回密码
		var findPasswordBtn = $("#wd-businessLogin").find(".forget-password");//忘记密码按钮
		var passwordClose = findPassword.find(".close-btn");//找回密码关闭
		var seedSuccess = $("#seedSuccess");
		//登录
		businessLoginBtn.click(function(){
			$overlay.show();
			$("#wd-businessLogin").show();
		})
		
		//注册
		$("#business-register").click(function(){
			$overlay.show();
			$("#quickFlip").css("top",-415);
			$("#wd-businessLogin").css({"height":523,"marginTop":-(523/2)}).show();
		});
		
		//关闭登录注册
		close.click(function(){
			closePanel(true)
		});
		
		function closePanel(flog,show){
			$("#wd-businessLogin").hide(function(){
				if(flog){
						$overlay.hide();	
					}
				if(show=="show" && flog == false){
					findPassword.show();
				}
				$("#wd-businessLogin").css({"height":"415px","marginTop":-(415/2)});
				$("#quickFlip").css({"top":0});
			});
		}
		
		
		//切换登录注册
		$("#fast-zhuce").click(function(){
			var $box = $("#wd-businessLogin");
			var height = $(this).parents(".blackPanel").height()+52;
			var zhuceHeight = $("#quickFlip").find(".backshow").height()+50;
			$box.css({"height":zhuceHeight});
			$("#wd-businessLogin").animate({"marginTop":-(zhuceHeight/2)},500);
			$("#quickFlip").animate({"top":-height},500);
		});
		
		$("#goto-loading").click(function(){
			var height = $("#wd-businessLogin").find(".blackPanel").height()+50;
			var zhuceHeight = $("#quickFlip").find(".backshow").height()+50;
			$("#wd-businessLogin").css({"height":height});
			$("#wd-businessLogin").animate({"marginTop":-(height/2)},500);
			$("#quickFlip").animate({"top":0},500);
		});
		
		//忘记密码
		findPasswordBtn.click(function(){
			closePanel(false,"show");
		});
		
		passwordClose.click(function(){
			findPassword.hide(function(){
				$overlay.hide();
			});
		})
		
		//返回登录
		findPassword.find(".return-loging").click(function(){
			findPassword.hide(function(){
				$("#wd-businessLogin").show();
			});
		});
		
		//发送成功提示框
		findPassword.find(".formSubmit").click(function(){
			findPassword.hide();
			seedSuccess.show();
		});
		//发送成功提示框关闭
		seedSuccess.find(".close-btn").click(function(){
			seedSuccessClose();
		});
		seedSuccess.find(".formSubmit").click(function(){
			seedSuccessClose();
		});
		function seedSuccessClose(){
			seedSuccess.hide();
			$overlay.hide();
		}
	})();
	
	(function(){
		var companyImg = $("#companyImg");//背景
		var changeBgBtn = companyImg.find(".btn-changeBg");
		var editCpName = $("#edit-cp-name");//编辑公司名称
		var editCpNameParent = $("#edit-cp-name").parents(".company-icon-name");
		var companyIntrodution = $("#companyIntrodution") /*编辑公司简介*/
		var editBtnCpInfo = $("#edit-btn-cpInfo");
		
		showOrHide(editCpNameParent,editCpName);
		showOrHide(companyImg,changeBgBtn);
		showOrHide(companyIntrodution,editBtnCpInfo);
		function showOrHide(obj,$childs){
			obj.hover(function(){
				$childs.show();
			},function(){
				$childs.fadeOut();
			});
		};
	
		/*编辑公司名称*/
		editCpName.on("click",function(){
			$(this).hide();
			var $name = $(this).prev();
			var stxt = $name.text();
			$name.html("<input type='text' class='itemvalue' value='"+stxt+"' >");
			var $itemvalue = $name.find(".itemvalue");
			$itemvalue.css({"color":"#666666","width":"296px","height":"34px","lineHeight":"34px","fontSize":"28px"});
			$itemvalue.select();
			$itemvalue.blur(function(){//失去焦点进行保存
				var value = $(this).val();
				$name.html(value);
			});
		});
		
		
		/*简介展开收缩*/
		var openDownUp = $("#open-down-up");
		var cpInfoDetails = $("#cpInfo-details");
		var cpInfoDetailsAll = $("#cpInfo-detailsAll");
		var isOpen ="";
		openDownUp.click(function(){
			$(this).toggleClass("open-up-down");
			isOpen = openDownUp.data("open");
			if(isOpen == "open"){
				cpInfoDetails.css("display","none");
				cpInfoDetailsAll.css("display","block");
				openDownUp.data("open","close");
				$(this).attr("title","收起");
			}else{
				cpInfoDetails.css("display","block");
				cpInfoDetailsAll.css("display","none");
				openDownUp.data("open","open");
				$(this).attr("title","展开");
			}
		});


		/*双选会tips显示*/
		var ulLi = $("#vc-details-list .lists-details").find("li");
		var editsingleImg = ulLi.find(".opear-img .edit-img-btn");
		var opearImg,$tips="";
		
		
		
		$("#vc-details-list .lists-details").on("mouseover","li",function(event){
			event.stopPropagation();
			opearImg = $(this).find(".opear-img");
			opearImg.stop(true,true).fadeIn();
		});
		$("#vc-details-list .lists-details").on("mouseout","li",function(){
			opearImg = $(this).find(".opear-img");
			opearImg.stop(true,true).fadeOut();
		});
		
		
		
		/*切换会议*/		
		$("#video-select").find("li").on("click",function(){
        	var index = $(this).index();
        	$(this).addClass("vc-active").siblings().removeClass("vc-active");
        	var $listsDetails = $("#vc-details-list").find(".lists-details");
        	$listsDetails.eq(index).fadeIn().siblings().hide();
        });
        
        /*删除信息*/
//		$("#vc-details-list .lists-details").on("click",".delete-img-btn",function(){
//			$.fn.fandialog({
//				title:"删除信息",
//				contents:"确定要删除该条信息吗？",
//				click:function(ok){
//					if(ok){
//						alert("你点击了确认!");
//					}else{
//						alert("你点击了取消!");
//					}
//				}
//			});
//		});
	})();
	
	(function(){
		/*上传宣讲会视频*/		
		var overlay = $("#tzui-edit-overlay");//遮盖层
		var voide = $("#upload-panel-voide");//上传视频panel
		var seminar = $("#send-panel-seminar");//发布会议panel
		var close1 = voide.find(".close-panelBtn");//关闭视频panel
		var close2 = seminar.find(".close-panelBtn");//关闭会议panel
		var title1 = voide.find(".up-title h3");
		var title2 = seminar.find(".up-title h3");
		var videosInfoBtn = $("#videos-info");//show视频panel按钮
		var seminarInfoBtn = $("#seminar-info");//show会议panel按钮
		var removeAllInfo = voide.find("input[type='reset']");
		var removeAllInfo1 = seminar.find("input[type='reset']");
		var sendShuangxuanBtn = $("#send-Shuangxuan");//发布双选会按钮
		var uploadShuangxuanBtn = $("#upload-Shuangxuan");//上传双选会按钮
		var opear1 = $("#opear1");
		var opear2 = $("#opear2");
		$("#xhjImg").hide();
		showPanel(seminarInfoBtn,seminar,title2,"发布宣讲会",opear1,"发布宣讲会");
		showPanel(videosInfoBtn,voide,title1,"上传宣讲会视频",opear2,"上传宣讲会视频");
		showPanel(sendShuangxuanBtn,seminar,title2,"发布双选会",opear1,"发布双选会");
		showPanel(uploadShuangxuanBtn,voide,title1,"上传双选会视频",opear2,"上传双选会视频");
		closePanel(close1,voide,removeAllInfo);
		closePanel(close2,seminar,removeAllInfo1);
		
		function showPanel(obj,panel,objTle,tlt,opear,mark){
			obj.click(function(){
				$("#xhjImg").empty();
				$("#xhjImg").hide();
				var windowH = $(window).height();
				var panelH = panel.height();
				panel.css("top",(windowH-panelH)/2);
				overlay.fadeIn("fast");
				objTle.html(tlt);
				panel.show();
				opear.val(mark);
			});
		}
		function closePanel($obj,panel,removeAllInfo){
			$obj.click(function(){
				overlay.fadeOut("fast");
				panel.hide();
				removeAllInfo.trigger("click");
			});
		};
		
	})();
	
	(function(){
		/*发送offer*/
		$("#send-offer-dialog").find(".close-sendOffer").click(function(){
			$("#send-offer-dialog").hide();	
			$("#send-offer-dialog-overlay").hide();
		});
		
		
		/*邀请面试*/
		var ivPosition = $("#iv-position");
		var ivWay = $("#iv-way");
		divselectCons(ivPosition,ivPosition.find(".titleActive"),ivPosition.find(".select-details"),ivWay.find(".select-details"));
		divselectCons(ivWay,ivWay.find(".titleActive"),ivWay.find(".select-details"),ivPosition.find(".select-details"),ivWay.find(".input-val"));
		
		/*div-select框*/
		function divselectCons(divselectid,$title,$select,$close,$input){
			var titleActive = divselectid.find($title);
			divselectid.on("click",function(event){
				event.stopPropagation();
				var selectDetails = $select;
				$close.hide();
				if(selectDetails.css("display")=="none"){
					selectDetails.slideDown(100);
				}else{
					selectDetails.slideUp("fast");
				}
			});
			$select.find("a").click(function(){
				var $txt = 	$(this).html();
				var data = $(this).data("title");
				titleActive.html($txt);
				$input.val(data);
			});
			
			$(document).click(function(){
				divselectid.find($select).hide();
			});
		}
		
		/*字数限制*/
		var dataCount = $("#remarksNum").data("count");
		$(document).keyup(function(){
			sizeOfCount();
		});
		$(document).keydown(function(){
			sizeOfCount();
		});
		function sizeOfCount(){
			var txt = $("#remarksNum").val();
			if(txt != undefined){
				txt = txt.trim();
				var len = $("#remarksNum").val().length;
				if(len<=dataCount){
					$("#limit-number").find("span").html(len);
				}else{
					var newText = txt.substring(0,dataCount);
	            	var text = $("#remarksNum").val(newText);
	            }
			}
		}
		/*关闭*/
		closePanelMain($("#interview-panel").find(".close-itrView"),$("#interview-panel"),$("#interview-panel-overlay"));
	})();

	/*
	 * 关闭弹窗的总方法
	 * $closeBtn：关闭按钮
	 * $parentPanel：父容器
	 * $overly：遮盖层
	 */
	function closePanelMain($closeBtn,$parentPanel,$overly){
		$closeBtn.on("click",function(){
			$overly.hide();
			$parentPanel.hide();
		});
	}
	

