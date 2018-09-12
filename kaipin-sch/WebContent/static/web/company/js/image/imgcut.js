
$(function(){
	//关闭按钮
	$("#close-img-cut").click(function(){
		$("#fileNameCut").val("");
		$("#tzui-loading-overlay").hide();
		$("#tailoring-dialog").hide();
		//裁剪类型
		var operTypeCut = $("#operTypeCut").val();
		if(isNotEmpty(operTypeCut)){
			$("#tzui-loading-overlay").show();
			if(operTypeCut == 'xjhadd'){//宣讲会	新增
				$("#pushAllXjh").show();
			}else if(operTypeCut == 'historyImgAdd'){//历史	新增
				$("#pushAllXjh").show();	
			}else if(operTypeCut == 'xjhEdit'){//宣讲会	维护
				$("#edit-preacp-contents").show();
			}else if(operTypeCut == 'historyImgEdit'){//历史	维护
				$("#historyXjhEdit").show();
			}else if(operTypeCut == 'bg' || operTypeCut == 'logo'){//背景  logo
				$("#tzui-loading-overlay").hide();
			}
		}
	});
	//取消按钮
	$("#cancle-img-cut").click(function(){
		$("#close-img-cut").trigger("click");
	});
	
	//触发选择文件
	$("#uploadLogoTrigger").click(function(){
		$("#file_upload_1-button").trigger("click");
	});
	
	//重新选择文件
	$("#selectAgin").click(function(){
		$("#uploadLogoTrigger").trigger("click");
	});
	$("#selectAginBg").click(function(){
		$("#uploadLogoTrigger").trigger("click");
	});
	
	$('#image').cropper({
  		/*画布的比例*/
  		aspectRatio: 1 / 1,
  		autoCropArea : 0.8,
  		/*设置预览区*/
  		preview: $(".preview"),
  		/*是否设置黑色0.5黑色遮罩*/
  		modal: true,
  		/*在剪裁框上方显示虚线*/
  		guides:true,
  		/*在剪裁框上方显示中心标识（剪裁框中间的白色的小加号）*/
  		center:true,
  		/*在剪裁框上面显示白色的模态框（高亮剪裁框）*/
  		highlight:true,
  		/*显示容器的网格背景（容器中，图片未占用区域显示的透明背景）*/
  		background:true,
  		/*允许移动图片*/
  		movable:false,
  		
  		/*不允许缩放图片*/
  		scalable:false,
  		zoomable:true,
  		
  		/*容器最小高度和最小宽度（图片包裹器)*/
  		minContainerWidth:360,
  		minContainerHeight:270,
  		
  		/*剪裁框最小高度和最小高度*/
  		minCropBoxWidth:10,
  		minCropBoxHeight:10,
  	});
	
	$('#image169').cropper({
		/*画布的比例*/
		aspectRatio: 4,
		autoCropArea : 0.8,
		/*设置预览区*/
		preview: $(".preview1"),
		/*是否设置黑色0.5黑色遮罩*/
		modal: true,
		/*在剪裁框上方显示虚线*/
		guides:true,
		/*在剪裁框上方显示中心标识（剪裁框中间的白色的小加号）*/
		center:true,
		/*在剪裁框上面显示白色的模态框（高亮剪裁框）*/
		highlight:true,
		/*显示容器的网格背景（容器中，图片未占用区域显示的透明背景）*/
		background:true,
		/*允许移动图片*/
		movable:false,
		
		/*不允许缩放图片*/
		scalable:false,
		zoomable:true,
		
		/*容器最小高度和最小宽度（图片包裹器)*/
		minContainerWidth:360,
		minContainerHeight:270,
		
		/*剪裁框最小高度和最小高度*/
		minCropBoxWidth:10,
		minCropBoxHeight:10
	});
		  	
  	
  	//获取最终剪裁结果
  	$("#getData").click(function(){
  		var fileName = $("#fileNameCut").val();
  		if(!isNotEmpty(fileName)){
  			$("#getData").show();
  			$("#getDataIng").hide();
  			alert("请上传图片");
  			$("#loading-spinner").hide();
  			return;
  		}
  		var operTypeCut = $("#operTypeCut").val();
  		if(!isNotEmpty(operTypeCut)){
  			$("#getData").show();
  			$("#getDataIng").hide();
  			alert("请刷新页面后再试");
  			$("#loading-spinner").hide();
  			return;
  		}
  		
  		$("#loading-spinner").show();
  		$("#getData").hide();
  		$("#getDataIng").show();
  		var dataCut = $('#pe-imgContainer > img').cropper('getData');
  		var datasCut = JSON.stringify(dataCut);
  		
  		 /*根据不同的情况对应的裁剪方法*/
  		switch(operTypeCut){
			case 'logo' : cutLogo(datasCut); break;
			case 'xjhadd' : cutHjhAdd(datasCut); break;
			case 'historyImgAdd' : cutHistoryImgAdd(datasCut); break;
			case 'xjhEdit' : cutXjhEdit(datasCut); break;
			case 'historyImgEdit' : cutHistoryImgEdit(datasCut); break;
			case 'bg' : cutBg(); break;
		}  
  	});
  	
  	/*裁剪logo*/
  	function cutLogo(datasCut){
  		setTimeout(function(){
  			var data = cutImg(datasCut);
  			var time = 1000;
  			var timer = "";
  			timer = setInterval(function(){
				var cutData = $("#cutData").val();
				if(cutData.length > 0){
					var datas = eval('('+cutData+')');
					clearInterval(timer);
					var path = datas.obj;
					if(datas.obj == null || datas.obj == ''){
						alert("裁剪失败，请重新裁剪");
						return;
					}
					uploadLogoImg(datas.obj);
				}
			},time);
  			clearVal();
  		},1000);
  	}
  	
  	/*裁剪bg*/
  	function cutBg(){
  		var datCutBg169 = $('#image169').cropper('getData');
  		var datasCut = JSON.stringify(datCutBg169);
  		var dataCut = $('#image169').cropper('getData');
  		var datasCut = JSON.stringify(dataCut);
  		setTimeout(function(){
  			var data = cutImg(datasCut);
  			var time = 1000;
  			var timer = "";
  			timer = setInterval(function(){
				var cutData = $("#cutData").val();
				if(cutData.length > 0){
					clearInterval(timer);
					$("#loading-spinner").show();
		  			var datas = eval('('+cutData+')');
		  			if(datas.success){
		  				//上传头像
		  				//显示裁剪框
		  	  			$("#tzui-loading-overlay").hide();
		  				$("#tailoring-dialog").hide();
		  				var bgUrl = datas.obj;
						$("#bg_preview").val(bgUrl);
						$("#change-background-img").css("background-image","url("+bgUrl+")");
						$("#change-background-img").find(".set-bg-btns").hide();
						$("#save-and-cancel").show();
		  			}
				}
			},time);
  			clearVal();
  		},1000);
  	}
  	
  	/*裁剪宣讲会新增图片*/
  	function cutHjhAdd(datasCut){
  		setTimeout(function(){
  			var data = cutImg(datasCut);
  			$("#loading-spinner").show();
  			var time = 1000;
  			var timer = "";
  			timer = setInterval(function(){
				var cutData = $("#cutData").val();
				if(cutData.length > 0){
					var datas = eval('('+cutData+')');
					clearInterval(timer);
		  			
		  			$("#xhjImg").empty();
					$("#xhjImg").show();
					var path = datas.obj;
					$("#xjhAnnexId").val(path);
					var html = "<a href='javascript:void(0)'><img src='"+path+"' width='78' height='78' /></a>"+
								"<a href='javascript:void(0)' onclick='delXjhImg();' class='delete-imgCover'></a>";
					$("#xhjImg").append(html);
					//关闭裁剪框 显示宣讲会
					$("#tailoring-dialog").hide();
					$("#pushAllXjh").show();
				}
			},time);
  			clearVal();
  		},1000);
  	}
  	
  	/*裁剪宣讲会编辑图片*/
  	function cutXjhEdit(datasCut){
  		setTimeout(function(){
  			var data = cutImg(datasCut);
  			$("#loading-spinner").show();
  			var time = 1000;
  			var timer = "";
  			timer = setInterval(function(){
				var cutData = $("#cutData").val();
				if(cutData.length > 0){
					var datas = eval('('+cutData+')');
					clearInterval(timer);
		  			
		  			$("#xhjImgEdit").empty();
					$("#xhjImgEdit").show();
					var path = datas.obj;
					$("#xjhAnnexIdEdit").val(path);
					var html = "<a href='javascript:void(0)'><img src='"+path+"' width='78' height='78' /></a>"+
								"<a href='javascript:void(0)' onclick='delXjhImg();' class='delete-imgCover'></a>";
					$("#xhjImgEdit").append(html);
					//关闭裁剪框 显示宣讲会
					$("#tailoring-dialog").hide();
					$("#edit-preacp-contents").show();
				}
			},time);
  			clearVal();
  		},1000);
  	}
  	
  	/*宣讲会历史封面图片	新增*/
  	function cutHistoryImgAdd(datasCut){
  		setTimeout(function(){
  			var data = cutImg(datasCut);
  			$("#loading-spinner").show();
  			
  			var time = 1000;
  			var timer = "";
  			timer = setInterval(function(){
				var cutData = $("#cutData").val();
				if(cutData.length > 0){
					var datas = eval('('+cutData+')');
					clearInterval(timer);
		  			
					var path = datas.obj;
					$("#historyImgUrl").val(path);
					var html = "<a href='javascript:void(0)'><img src='"+path+"' width='78' height='78' /></a>"+
								"<a href='javascript:void(0)' onclick='delXjhImg();' class='delete-imgCover'></a>";
					$("#historyImg").show();
					$("#historyImg").empty();
					$("#historyImg").append(html);
					$(".delete-imgCover").click(function(){
						$("#historyImg").hide();
						$("#historyImgUrl").val("");
					});
					//关闭裁剪框 显示宣讲会
					$("#tailoring-dialog").hide();
					$("#pushAllXjh").show();
				}
			},time);
  			clearVal();
  		},1000);
  	}
  	
  	/*宣讲会历史封面图片	维护*/
  	function cutHistoryImgEdit(datasCut){
  		setTimeout(function(){
  			var data = cutImg(datasCut);
  			$("#loading-spinner").show();
  			var time = 1000;
  			var timer = "";
  			timer = setInterval(function(){
				var cutData = $("#cutData").val();
				if(cutData.length > 0){
					var datas = eval('('+cutData+')');
					clearInterval(timer);
					var path = datas.obj;
					$("#historyImgUrlEidt").val(path);
					var html = "<a href='javascript:void(0)'><img src='"+path+"' width='78' height='78' /></a>"+
								"<a href='javascript:void(0)' class='delete-imgCover'></a>";
					$("#historyImgEidt").show();
					$("#historyImgEidt").empty();
					$("#historyImgEidt").append(html);
					$(".delete-imgCover").click(function(){
						$("#historyImg").hide();
						$("#historyImgUrlEidt").val("");
					});
					//关闭裁剪框 显示宣讲会
					$("#tailoring-dialog").hide();
					$("#historyXjhEdit").show();
				}
			},time);
  			clearVal();
  		},1000);
  	}
  	
  	function uploadLogoImg(data){
  		var path = data
  		$.ajax({
			cache : false,
			async : false,
			type : 'POST',
			url : r_path + '/basicConctroller/edit.do',
			data : {
				annexId : path,
				oper : 'logo'
			},
			success : function (data){
				var datas =eval('('+data+')');
				if(datas.success){
					var path = datas.obj.logo;
					if(!isNotEmpty(path)){
						path = r_path + "/x.jpg";
					}
					$("#add-logo").hide();
					$("#uploadLogo").css("background-image","url("+path+")");
					$("#header-logo").css("background-image","url("+path+")");
					$("#hd-logo").html("<img  src='"+path+"' width='48' height='48'>");
					$("#uploadLogo").show();
					$("#close-img-cut").trigger("click");
				}else{
					alert("图片保存错误");
					$("#getDataIng").hide();
		  			$("#loading-spinner").hide();
		  			$("#getData").show();
				}
				
			}
		});
  	}
  	
  	/*图片裁剪*/
  	function cutImg(datas){
  		var fileName = $("#fileNameCut").val();
  		var dataStr = "";
  		$.ajax({                
			cache: false,    
			async: true, 
			type: "POST",    
			timeout: 120000,
			url:  r_path + '/annexController/imgCut.do',                
			data:{
				datas : datas,
				fileName : fileName,
				cutType : 'info'
			},              
			error: function(request) {
				$("#getData").show();
				$("#getDataIng").hide();
				alert("网络错误");
			},
			beforeSend: function () {
				$("#loading-spinner").show();
		    },
			success: function(data) {
				/*var datas = eval('('+data+')');
				if(datas.success){
					dataStr = data;
				}else{
					dataStr = "";
					alert("图片保存错误");
					$("#getDataIng").hide();
		  			$("#loading-spinner").hide();
		  			$("#getData").show();
				}*/
			},
			complete: function(data) { 
				dataStr = data.responseText;
				$("#cutData").val(dataStr);
			}            
		});
  		return dataStr;
  	}
  	//清除所有页面参数
  	function clearVal(){
  		$("#fileNameCut").val("");
  		$("#operTypeCut").val("");
  		$("#cutData").val("");
  	}
});