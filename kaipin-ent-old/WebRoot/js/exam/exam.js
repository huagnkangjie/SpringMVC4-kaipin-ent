
/**
 * 创建笔试题
 */
$(function() {
	$("#we-bgColor").find("tr:even").addClass("bgColor");

	// 笔试题切换添加
	var status = 0
	var addNum = 1;
	var animateNum = 0;
	var oldPageNum = $("#WEQ-lists-title").find("li").length;
	if (oldPageNum > 8) {
		$("#weq_pre").show();
		$("#weq_next").show();
		$("#weq_addPage").css("left",8*95+20);
	} else {
		$("#weq_pre").hide();
		$("#weq_next").hide();
		$("#weq_addPage").css("left",oldPageNum*95);
	}

	// 切换
	$("#WEQ-lists-title").on("click","li",function() {
						var $index = $(this).index();
						var dataCurr = $(this).data("current", "open");
						for (var i = 0; i < $("#WEQ-lists-title").find("li").length; i++) {
							if (i != $index) {
								$("#WEQ-lists-title").find("li").eq(i).data("current", "colse");
							}
						}
						$(this).addClass("current").siblings().removeClass("current");
						//console.log("当前的索引值："+$index);
						$("#WE-pages").find(".page").eq($index).addClass("pageCurrent").siblings().removeClass("pageCurrent");
						//console.log("当前的page的长度："+$("#WE-pages").find(".page").length);
					});

	
	remove();
	// 添加笔试题
	$("#weq_addPage").on("click",function() {
						var editFlag = $("#editFlag").val();
						
						if(editFlag != '0'){
							$.fn.fandialog({
								title:"提示信息",
								contents: "该试卷有用户在使用，不能添加或者删除题目",
								click:function(ok){
								}
							});
							return;
						}
						oldPageNum = $("#WEQ-lists-title").find("li").length;
						oldPageNum++;
						addNum = oldPageNum;
						if (addNum > 8) {
							addNum = 8;
						}
						if (oldPageNum > 8) {
							status++;
							animateNum = status;
							$("#weq_pre").show();
							$("#weq_next").show();
							$("#weq_addPage").css("left", addNum * 95 + 20);
						} else {
							$("#weq_addPage").css("left", addNum * 95);
						}

						// 移除选中的样式
						$("#WEQ-lists-title").find("li").removeClass("current");
						$('#WE-pages').find(".page").removeClass("pageCurrent");
						// 添加的模板
						var tltLi = "<li class='pages_item current' data-current='open'>"
								+ "	<a href='javascript:void(0)'>第"
								+ oldPageNum
								+ "题</a>"
								+ "<span class='page-remove'></span>" + "</li>";
						var wePage = "<div class='page pageCurrent'>"
//									+ "		<form>"
									+ "			<div class='wp-contents'>"
									+ "				<span class='caption'>题目</span>"
									+ "				<textarea id='question"+ (oldPageNum - 1)+"' class='txt' name='examList[" + (oldPageNum-1) + "].question'></textarea>"
									+ "			</div>"
//									+ "			<div class='wp-suer-cancle'>"
//									+ "				<input type='button' class='wp-btn wp-sure' value='确认'/>"
//									+ "				<input type='button' class='wp-btn wp-cancle' value='取消'/>"
//									+ "			</div>" 
//									+ "		</form>" 
									+ "	</div>";
						// 把模板添加到相应位置
						$("#WEQ-lists-title").append(tltLi);
						$('#WE-pages').append(wePage);
						
						$(".wp-cancle").click(function(){
							$(this).parents(".page").find(".txt").val("");
						});
						
						// 获取添加后的新样式
						var newlen = $("#WEQ-lists-title").find("li").length;
						// 吧所有的开关全部关闭
						for (var i = 0; i < newlen - 1; i++) {
							$("#WEQ-lists-title").find("li").eq(i).data(
									"current", "close");
						}
						// 打开当前的最新的开关
						$("#WEQ-lists-title").find("li").eq(newlen).data(
								"current", "open");

						remove();
						
					});
	
	
	// 删除题方法
	function remove() {
		
		$("#WEQ-lists-title li").find(".page-remove").off(
				"click").on(
				"click",
				function(ev) {
					var editFlag = $("#editFlag").val();
					
					if(editFlag != '0'){
						$.fn.fandialog({
							title:"提示信息",
							contents: "该试卷有用户在使用，不能添加或者删除题目",
							click:function(ok){
							}
						});
						return;
					}
					if(oldPageNum == 1) {
						alert("至少一道题目");
						return;
					}
					oldPageNum--;
					var $parent = $(this).parents("li");
					var $prData = $parent.data("current");
					var index = $(this).parents("li").index();
					$(this).parents("li").remove();// 移除当前选中的标签
					$('#WE-pages').find(".page").eq(index)
							.remove();// 移除相对应的page页
					var len = $("#WEQ-lists-title").find(
							"li").length;
					if ($prData == "open") {
						$("#WEQ-lists-title").find("li")
								.eq(len - 1).addClass(
										"current");
						$("#WEQ-lists-title").find("li")
								.eq(len - 1).data(
										"current", "open");
						$('#WE-pages').find(".page").eq(
								len - 1).addClass(
								"pageCurrent");
					}

					// 重新初始化标题
					for (var i = 0; i <= len - 1; i++) {
						$("#WEQ-lists-title li").eq(i)
								.find("a")
								.html("第" + (i + 1) + "题");
					}

					if (len == 8) {
						status = 0;
						$("#weq_pre").hide();
						$("#weq_next").hide();
						$("#WEQ-lists-title").css("left",
								"0");
						$("#weq_addPage").css("left",
								len * 95);
					}
					if (len < 8) {
						status = 0;
						$("#weq_addPage").css("left",
								len * 95);
					}
					if (index >= 8) {
						animateNum--;
						$("#WEQ-lists-title").animate({
							"left" : -(animateNum * 95)
						}, 200);
					}
					
					var poLeft = $("#WEQ-lists-title").position().left;
					if(poLeft==0){
						return;
					}

					if (len > 8 && index <= 8 && index != 0) {
						animateNum--;
						$("#WEQ-lists-title").animate({
							"left" : -(animateNum * 95)
						}, 200);
					}
					ev.stopPropagation();
				});
	}

	// 下一个
	$("#weq_next").on("click", function() {
		var len = $("#WEQ-lists-title").find("li").length;
		animateNum++;
		if (animateNum > len - 8) {
			animateNum = len - 8;
		}
		$("#WEQ-lists-title").animate({
			"left" : -(animateNum * 95)
		}, 200);
	});

	// 上一个
	$("#weq_pre").on("click", function() {
		animateNum--;
		if (animateNum <= 0) {
			animateNum = 0;
		}
		$("#WEQ-lists-title").animate({
			"left" : -(animateNum * 95)
		}, 200);
	});
	
	
	$(".wp-cancle").click(function(){
		$(this).parents(".page").find(".txt").val("");
	});

});


/**
 * 笔试题维护
 */
$(function(){
	$("#we-bgColor").find("tr:even").addClass("bgColor");
	
	$("#writer-manager").click(function(){
		$.ajax({
			cache : false,
			async : false,
			type : 'POST',
			url : r_path + '/postionCountController/countPostionByEndtime.do',
			data : {
				oper : 1
			},
			success : function (data){
				var datas =  eval('(' + data + ')'); 
				if(datas.success){
					var counts = datas.obj.zero
					if(counts == '0'){
						$.fn.fandialog({
							title:"提示信息",
							contents: "请先发布职位",
							click:function(ok){
								if(ok){
									location.href=r_path + "/position/pulishPage.do";
								}
							}
						});
					}else{
						$("#positionSelected").html("请选择");
						$("#positionSelectedId").val("");
						getPositionList();
					}
				}
			}
		});
	});
	
	$("#weitten-exam-mg").find(".op-cancle").click(function(){
		$("#weitten-exam-mg").removeClass("flipInX").addClass("animated fadeOutUp");
		$("#weitten-examOverlay").fadeOut(function(){
			$("#weitten-exam-mg").hide();
			$("#weitten-exam-mg").removeClass("animated fadeOutUp");
		});
	});
	
	/*获取该企业的全部职位*/
	function getPositionList(){
		$.ajax({                
			cache: false,    
			async: false, 
			type: "POST",                
			url:  r_path + '/examManager/positionList.do',                
			data:{
				page : 1,
				rows : 50
			},              
			error: function(request) {                    
			},                
			success: function(data) {
				var datas = eval('('+data+')');
				
				if(datas.obj.length > 0){
					var positionName = "";
					var id = "";
					var html = "";
					for(var i = 0; i < datas.obj.length; i++){
						positionName = datas.obj[i].position_name;
						id = datas.obj[i].id;
						html = html + "<a href='javascript:void(0)' data-id='"+ id +"' data-tag='"+ positionName +"'>" + positionName + "</a>";
					}
					
					$("#positionList").empty();
					$("#positionList").append(html);
 					$("#weitten-examOverlay").fadeIn();
 					$("#weitten-exam-mg").addClass("animated flipInX").show();
 					
 					$("#positionList a").click(function(){
 						positionName = $(this).data("tag"); 
 						id = $(this).data("id");
 						getPositionInfo(positionName,id);
 					});
				}
			}            
		});
	}	
	
	/*获取职位相关信息*/
	function getPositionInfo(positionName,id){
		$("#positionSelected").html(positionName);
		$("#positionSelectedId").val(id);
		
	}
	
	/**
	 * 打开试卷添加页面
	 */
	$("#addPage").click(function(){
		var positionId = $("#positionSelectedId").val();
		if(positionId != ''){
			location.href = r_path + "/examManager/addPage.do?positionId=" + positionId;
		}else{
			$.fn.fandialog({
				title:"提示信息",
				contents: "请选择职位",
				click:function(ok){
				}
			});
		}
		
	});
	
});

/*笔试题通过或者不通过*/
function passOrNo(obj){
	var optionName = "通过该试卷";
	if(obj == '7'){
		optionName = "不通过该试卷";
	}
	
	var inviteId = $("#inviteId").val();
	
	var positionId = $("#positionId").val();
	var positionName = $("#positionName").val();
	
	$.fn.fandialog({
		title:"提示信息",
		contents: optionName,
		click:function(ok){
			if(ok){
				$.ajax({                
					cache: false,    
					async: false, 
					type: "POST",                
					url:  r_path + '/examList/passOrNo.do',                
					data:{
						oper : obj,
						inviteId : inviteId
					},              
					error: function(request) {                    
					},                
					success: function(data) {
						var datas = eval('('+data+')');
						if(datas.success){
							window.location.href=r_path + "/examList/init.do?postionId="+positionId+"&positionName=" + positionName;
						}else{
							alert("操作失败");
						}
					}            
				});
			}
		}
	});
}