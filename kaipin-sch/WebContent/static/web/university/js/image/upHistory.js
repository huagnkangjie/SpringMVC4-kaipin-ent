	var upHistoryImg;
	var upHistoryImgEdit;
	var upHistoryVedio;
	var upHistoryVedioEdit;
	
	$(function (){

		//宣讲会历史封面上传 新增
		$("#uploadhistoryImgUrlTrigger").click(function(){
			var oper = $("#pushHistoryIng").val();
			if(oper == 1){
				alert("视频正在上传中...");
				return;
			}
			
			showDialog();
  			//裁剪类型
  			$("#operTypeCut").val("historyImgAdd");
  			
  			//显示主体框
  			$("#pushAllXjh").hide();
  			
  			//显示正方形 影藏长方形
  			$("#showZFX").show();
  			$("#showCFX").hide();
  			
  			//预览区 大图
  			$("#logo-preview-pic").hide();
  			$("#others-preview-pic").show();
  			
  			//显示裁剪框
  			$("#tzui-loading-overlay").show();
			$("#tailoring-dialog").show();
			
		});
		
		//宣讲会历史封面上传 	维护
		$("#upHistoryImgEditTrigger").click(function(){
			var oper = $("#pushHistoryIng").val();
			if(oper == 1){
				alert("视频正在上传中...");
				return;
			}
			
			showDialog();
			
			//裁剪类型
			$("#operTypeCut").val("historyImgEdit");
			//显示主体框
			$("#historyXjhEdit").hide();
			//显示正方形 影藏长方形
  			$("#showZFX").show();
  			$("#showCFX").hide();
  			
  			//预览区 大图
  			$("#logo-preview-pic").hide();
  			$("#others-preview-pic").show();
			
			//显示裁剪框
			$("#tzui-loading-overlay").show();
			$("#tailoring-dialog").show();
			
		});
		
		function showDialog(){
			$("#selectCropperBox").show();
			$("#preview-show-preview").hide();
			$("#defaultshow-bg").show();
			$("#cropperBox").hide();
			$("#cropperBox169").hide();
			$("#fileNameCut").val("");
			$("#getData").show();
  			$("#getDataIng").hide();
  			$("#loading-spinner").hide();
		}
		
		var orgId = $("#ogrId").val();
		
		upHistoryImg = $('#upHistoryImg').Huploadify({
			auto:false,
			fileTypeExts:'*.*',
			multi:false,
			auto:true,
			formData:{key:123456,key2:'vvvv'},
			fileSizeLimit:2048,
			removeCompleted:false,
			removeTimeout: 50,//上传完成后进度条的消失时间
			showUploadedPercent:true,
			showUploadedSize:true,
			removeTimeout:9999999,
			uploader: r_path + '/annex/uploadImg?orgId=' + orgId,
			onUploadStart:function(file){
				$(".showDiv").hide();
			},
			onInit:function(obj){
			},
			onUploadComplete:function(file,data,response){
				////cancle();
				var datas = data.split(",");
				var path = datas[0];
				$("#historyImgUrl").val(datas[0]);
				var html = "<a href='javascript:void(0)'><img src='"+path+"' width='78' height='78' /></a>"+
							"<a href='javascript:void(0)' onclick='delXjhImg();' class='delete-imgCover'></a>";
				$("#historyImg").show();
				$("#historyImg").empty();
				$("#historyImg").append(html);
				$(".delete-imgCover").click(function(){
					$("#historyImg").hide();
					$("#historyImgUrl").val("");
				});
				
			}
		});
		
		upHistoryImgEdit = $('#upHistoryImgEdit').Huploadify({
			auto:false,
			fileTypeExts:'*.*',
			multi:false,
			auto:true,
			formData:{key:123456,key2:'vvvv'},
			fileSizeLimit:2048,
			removeCompleted:false,
			removeTimeout: 50,//上传完成后进度条的消失时间
			showUploadedPercent:true,
			showUploadedSize:true,
			removeTimeout:9999999,
			uploader: r_path + '/annex/uploadImgCut?orgId=' + orgId,
			onUploadStart:function(file){
				$(".showDiv").hide();
				$("#loading-spinner").show();
				var path1 = r_path + "/static/web/images/100.jpg";
				var path2 = r_path + "/static/web/images/fang_img.jpg";
				$("#image169").cropper('replace', path2);
				$("#image").cropper('replace', path1);
				$("#defaultBgCl").hide();
				$("#defaultBgCl169").hide();
			},
			onInit:function(obj){
			},
			onUploadComplete:function(file,data,response){
				//cancle();
				
				var datas = data.split(",");
				var path = datas[0];
				$("#fileNameCut").val(datas[1]);
				
				$("#selectCropperBox").hide();
				$("#image").attr("src",path);
				$("#loading-spinner").hide();
				$("#image").cropper('replace', path);
				$("#defaultshow-bg").hide();
				$("#preview-show-preview").show();
				$("#selectAgin").show();
				
				loadImg(path,function(ok){
					if(ok){
						var operTypeCut = $("#operTypeCut").val();
						if(operTypeCut == 'bg'){
							$("#defaultBgCl").hide();
							$("#defaultBgCl169").show();
							$("#cropperBox169").show();
							$("#others-show").hide();
							$("#image169").cropper('replace', path);
						}else{
							$("#defaultBgCl").show();
							$("#defaultBgCl169").hide();
							$("#cropperBox").show();
							if(operTypeCut == 'logo'){
								$("#others-show").show();
							}else{
								$("#others-show").hide();
							}
							var timer = setTimeout(function(){
								$("#image").cropper('replace', path);
							},200);
							
						}
						
					}else{
						alert("图片加载失败，重新上传");
					}
				});
				
			}
		});
		
		var orgId = $("#ogrId").val();
		
		upHistoryVedio = $('#upHistoryVedio').Huploadify({
			auto:false,
			fileTypeExts:'*.mp4;*.MP4',
			multi:false,
			auto:true,
			formData:{key:123456,key2:'vvvv'},
			fileSizeLimit:1024*1024*2,
			removeCompleted:false,
			removeTimeout: 50,//上传完成后进度条的消失时间
			showUploadedPercent:true,
			showUploadedSize:true,
			removeTimeout:2999999,
			uploader: r_path + '/annex/uploadVideo?orgId=' + orgId,
			onUploadStart:function(file){
				$(".showDiv").show();
				$("#upHistoryVedio").show();
				$("#uploadVedioSuccess").hide();
				$("#upHistoryVedioTrriger").hide();
				$("#pushHistoryIng").val(1);
			},
			onInit:function(obj){
				$("#upHistoryVedio").find('.uploadify-button').hide();
			}
			,onCancel:function(file){
				$("#upHistoryVedio").hide();
				cancleVedio();
			},
			onUploadComplete:function(file,data,response){
				cancleVedio();
				$("#upHistoryVedio").hide();
				$("#pushHistoryIng").val(0);
				var datas = data.split(",");
				var path = datas[1];
				var fileName = datas[2];
				$("#fileName").html(fileName);
				$("#fileName").attr("title",fileName);
				$("#uploadVedioSuccess").show();
				$("#upHistoryVedioTrriger").hide();
				$("#upHistoryVedioUrl").val(path);
			},onCancel:function(file){
				$("#upHistoryVedio").hide();
				cancleVedio();
			},onUploadProcess:function(file,data){
				alert("上传过程中"+data);
			}
			
		});
		
		$("#upLoadAgain").click(function(){
			$("#upHistoryVedio a").trigger("click");
			$("#upHistoryVedio").find('.uploadify-button').hide();
		});
		
		
		upHistoryVedioEdit = $('#upHistoryVedioEdit').Huploadify({
			auto:false,
			fileTypeExts:'*.mp4;*.MP4',
			multi:false,
			auto:true,
			formData:{key:123456,key2:'vvvv'},
			fileSizeLimit:1024*1024*2,
			removeCompleted:false,
			removeTimeout: 50,//上传完成后进度条的消失时间
			showUploadedPercent:true,
			showUploadedSize:true,
			removeTimeout:2999999,
			uploader: r_path + '/annex/uploadVideo.do?orgId=' + orgId,
			onUploadStart:function(file){
				$(".showDiv").show();
				$("#upHistoryVedioEdit").show();
				$("#uploadVedioSuccessEdit").hide();
				$("#pushHistoryIng").val(1);
			},
			onInit:function(obj){
				$("#upHistoryVedioEdit").find('.uploadify-button').hide();
			},onCancel:function(file){
				$("#upHistoryVedioEdit").hide();
			},
			onUploadComplete:function(file,data,response){
				//cancleVedioEdit();
				$("#upHistoryVedioEdit").hide();
				$("#pushHistoryIng").val(0);
				var datas = data.split(",");
				var path = datas[1];
				var fileName = datas[2];
				$("#fileNameEdit").html(fileName);
				$("#fileNameEdit").attr("title",fileName);
				$("#uploadVedioSuccessEdit").show();
				$("#upHistoryVedioUrlEdit").val(path);
			}
		});
		
		$("#upHistoryVedioTrriger").click(function(){
			var v = $("#historyImgUrl").val();
//			if(v.length == 0){
//				alert("请上传封面");
//			}else{
				$("#upHistoryVedio a").trigger("click");
				$("#upHistoryVedio").find('.uploadify-button').hide();
//			}
		});
		//编辑
		$("#upHistoryVedioTrrigerEdit").click(function(){
			var v = $("#historyImgUrlEidt").val();
			if(v.length == 0){
				alert("请上传封面");
			}else{
				$("#upHistoryVedioEdit a").trigger("click");
				$("#upHistoryVedioEdit").find('.uploadify-button').hide();
			}
		});
		//编辑
		$("#upLoadAgainEdit").click(function(){
			var v = $("#historyImgUrlEidt").val();
			if(v.length == 0){
				alert("请上传封面");
			}else{
				$("#upHistoryVedioEdit a").trigger("click");
				$("#upHistoryVedioEdit").find('.uploadify-button').hide();
			}
		});
		
	});
	function cancle(){
		upHistoryImg.cancel('*');
	}
	function cancleVedio(){
		$("#upHistoryVedio").hide();
		upHistoryVedio.cancel('*');
	}
	function cancleVedioEdit(){
		$("#upHistoryVedioEdit").hide();
		upHistoryVedio.cancel('*');
	}
	
	function uploadVedio(){
		upHistoryVedio.upload('*');
	}