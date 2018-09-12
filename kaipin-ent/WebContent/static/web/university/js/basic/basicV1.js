(function(){
	DropSlideNav($("#message-details"),".message-details");
	var time = "";
	DropSlideNav($("#personal-setting"),".personal-setting");
	function DropSlideNav($parent,$childs){
		$parent.hover(function(){
			time=setTimeout(function(){
			 	$parent.find($childs).stop(true,true).slideDown("fast");
			 },300);
		},function(){
			clearTimeout(time);//清除计时器
			time=setTimeout(function(){
				$parent.find($childs).stop(true,true).slideUp("fast");	
			},10);//鼠标移除元素区域子元素消失
		})
	}
	
	
	//修改背景
	$("#change-background-img").find(".edit-bgImg").click(function(){
		$("#change-background-img").find(".set-bg-btns").fadeIn();
	});
	$("#change-background-img").find(".close-bg-btns").click(function(){
		$("#change-background-img").find(".set-bg-btns").fadeOut();
	});
	
	$("#select-bgImg-file").click(function(){
		$("#change-background-img").find(".set-bg-btns").hide();
		$("#save-and-cancel").show();
	});
	
	$("#save-and-cancel").find(".cancle").click(function(){
		$("#save-and-cancel").hide();
	});
	
	//company-title-details 公司简称
	var editEnterprise= $("#edit-enterprise");
	var ewpEditCompany = $(".ewp-edit-company");
	var $cancleBtn = $("#edit-enterprise").parents(".edit-window-panel").find(".cancle-btn");
	
	//profile-full-details 企业简介
	var editCompanyInfo = $("#edit-company-info");
	var panelWinDefaulte = $("#panel-win-defaulte");
	var $cancleCompanyInfo = $("#profile-full-details").find(".cancle-btn");
	
	//编辑企业
	editWinShow(editEnterprise,ewpEditCompany);
	editWinHide($cancleBtn,editEnterprise,ewpEditCompany);
	//企业简介
	editWinShow(editCompanyInfo,panelWinDefaulte);
	editWinHide($cancleCompanyInfo,editCompanyInfo,panelWinDefaulte);
	/*
	 *  方法名：editWinShow
	 * 	参    数：$obj----》编辑按钮
	 * 		 $panel---》打开的窗口
	 */
	function editWinShow($obj,$panel){
		$obj.click(function(){
			var open = $obj.data("open");
			if(open=="open"){
				$obj.data("open","close");
				$panel.show();	
			}else{
				$obj.data("open","open");
				$panel.hide();	
			}
		});
	}
	
	/*
	 *  方法名：editWinHide
	 * 	参    数：$obj----》取消按钮
	 * 		 $flag ---编辑按钮
	 * 		 $panel---》打开的窗口对象
	 */
	function editWinHide($obj,$flag,$panel){
		$obj.click(function(){
			$flag.data("open","open");
			$panel.hide();
		});
	}
	
	
	
	/*展开收起*/
	$("#spread-out").click(function(){
		var flag = $(this).data("flag");
		if(flag=="open"){
			$(this).html("-收起").data("flag","close");
			$("#profile-full-details").find(".simple-introduction").hide();
			$("#profile-full-details").find(".details-introduction").show();
		}else{
			$(this).html("+展开").data("flag","open");
			$("#profile-full-details").find(".simple-introduction").show();
			$("#profile-full-details").find(".details-introduction").hide();
		}
	});

	
	//斑马线
	$("#school-hover").find("tr:odd").addClass("bgColor");
	
	//宣讲会和校招岗位切换
	$("#tab-title-change").find("li").click(function(){
		$(this).addClass("tab-active").siblings().removeClass("tab-active");
		var index = $(this).index();
		$("#confer-full-info").find(".confer-detalis-cons").eq(index).show().siblings().hide();
	});

	//个人设置显示关闭
	mainPanelShwo($("#account-set-btn"),$("#personal-set-panel"));
	mainPanelHide($("#close-accountSet"),$("#personal-set-panel"));

	//修改密码
	$("#update-passwordBtn").click(function(){
		$("#updata-password-panel").show();
		$("#personal-set-panel").hide();
	});
	//取消修改密码
	$("#close-updata-password").click(function(){
		$("#personal-set-panel").show();
		$("#updata-password-panel").hide();
	});
	
	
	showDownlists($("#basic-info-select .select_box"),$("#basic-endTimer-select .select_box").find(".option"));
	showDownlists($("#basic-endTimer-select .select_box"),$("#basic-info-select .select_box").find(".option"));
	showDownlists($("#exam-select-mark .select_box"),"");
	showDownlists($("#stu-select .select_box"),"");
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
	
	selectHide($(document),"click");
	selectHide($(document),"scroll");
	
	getValue($("#basic-info-select .option a"));
	getValue($("#basic-endTimer-select .option a"));
	getValue($("#select-sex .option a"));
	getValue($("#exam-select-mark .option a"));
	getValue($("#stu-select .option a"));
	/*赋值给文本框*/
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
	
	mainPanelShwo($("#upload-confers"),$("#edit-preacp-contents"));
	mainPanelShwo($("#rightOfedit-comInfo"),$("#edit-company-details"));
	mainPanelHide($("#close-company-details"),$("#edit-company-details"));
	mainPanelShwo($("#confer-full-info").find("li .pre-edit"),$("#edit-preacp-contents"));
	mainPanelHide($("#close-preacp-contents"),$("#edit-preacp-contents"));
	function mainPanelShwo($obj,$showObj){
		$obj.click(function(){
			$("#tzui-loading-overlay").show();
			$showObj.show();
		});
	}
	
	function mainPanelHide($obj,$showObj){
		$obj.click(function(){
			$("#tzui-loading-overlay").hide();
			$showObj.hide();
		});
	}
})();
