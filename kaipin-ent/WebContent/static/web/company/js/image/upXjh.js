	var upImg ;
	var uploadImgEdit;
	
	$(function (){
		//宣讲会上传按钮	 新增
		$("#uploadImgTrigger").click(function(){
			//显示裁剪框里面的属性
			showDialog();
  			
  			//裁剪类型
  			$("#operTypeCut").val("xjhadd");
  			//显示正方形 影藏长方形
  			$("#showZFX").show();
  			$("#showCFX").hide();
  			
  			//预览区 大图
  			$("#logo-preview-pic").hide();
  			$("#others-preview-pic").show();
  			
  			//显示裁剪框
  			$("#tzui-loading-overlay").show();
			$("#tailoring-dialog").show();
			$("#pushAllXjh").hide();
			
		});
		
		//宣讲会	维护
		$("#uploadImgEditTrigger").click(function(){
			//显示裁剪框里面的属性
			showDialog();
  			//裁剪类型
  			$("#operTypeCut").val("xjhEdit");
  			//显示正方形 影藏长方形
  			$("#showZFX").show();
  			$("#showCFX").hide();
  			
  			//预览区 大图
  			$("#logo-preview-pic").hide();
  			$("#others-preview-pic").show();
  			
  			//显示裁剪框
  			$("#tzui-loading-overlay").show();
			$("#tailoring-dialog").show();
			$("#edit-preacp-contents").hide();
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
		
		upImg = $('#uploadImg').Huploadify({
			auto:false,
			fileTypeExts:'*.*',
			multi:false,
			auto:true,
			formData:{key:123456,key2:'vvvv'},
			fileSizeLimit:1024,
			removeCompleted:false,
			removeTimeout: 50,//上传完成后进度条的消失时间
			showUploadedPercent:true,
			showUploadedSize:true,
			removeTimeout:9999999,
			uploader: r_path + '/annexController/uploadImg.do',
			onUploadStart:function(file){
				$(".showDiv").hide();
			},
			onInit:function(obj){
			},
			onUploadComplete:function(file,data,response){
				cancle();
				$("#xhjImg").empty();
				$("#xhjImg").show();
				var datas = data.split(",");
				var path = datas[0];
				$("#xjhAnnexId").val(datas[0]);
				var html = "<a href='javascript:void(0)'><img src='"+path+"' width='78' height='78' /></a>"+
							"<a href='javascript:void(0)' onclick='delXjhImg();' class='delete-imgCover'></a>";
				$("#xhjImg").append(html);
			}
		});
		
		
		uploadImgEdit = $('#uploadImgEdit').Huploadify({
			auto:false,
			fileTypeExts:'*.*',
			multi:false,
			auto:true,
			formData:{key:123456,key2:'vvvv'},
			fileSizeLimit:1024,
			removeCompleted:false,
			removeTimeout: 50,//上传完成后进度条的消失时间
			showUploadedPercent:true,
			showUploadedSize:true,
			removeTimeout:9999999,
			uploader: r_path + '/annexController/uploadImg.do',
			onUploadStart:function(file){
				$(".showDiv").hide();
			},
			onInit:function(obj){
			},
			onUploadComplete:function(file,data,response){
				cancle();
				$("#xhjImgEdit").empty();
				$("#xhjImgEdit").show();
				var datas = data.split(",");
				var path = datas[0];
				$("#xjhAnnexIdEdit").val(datas[0]);
				var html = "<a href='javascript:void(0)'><img src='"+path+"' width='78' height='78' /></a>"+
							"<a href='javascript:void(0)' onclick='delXjhImg();' class='delete-imgCover'></a>";
				$("#xhjImgEdit").append(html);
			}
		});
		
		
		
	});
	
	function cancle(){
		upImg.cancel('*');
	}
	
	//删除宣讲会附件
  	function delXjhImg(){
  		$("#xjhAnnexId").val("");
  		$("#xhjImg").empty();
  		$("#xhjImg").hide();
  		$("#xjhAnnexIdEdit").val("");
  		$("#xhjImgEdit").empty();
  		$("#xhjImgEdit").hide();
	}
	
	