	var upLogo ;
	var upBg;
	
	$(function (){
		
		//背景图修改触犯按钮
		$("#select-bgImg-file").click(function(){
			$("#selectCropperBox").show();
			$("#preview-show-preview").hide();
			$("#defaultshow-bg").show();
			$("#cropperBox").hide();
			$("#cropperBox169").hide();
			$("#fileNameCut").val("");
			$("#getData").show();
  			$("#getDataIng").hide();
  			$("#loading-spinner").hide();
  			
  			//裁剪类型
  			var currentUserType = $("#currentUserType").val();
  			var operTypeCut = "bg";
  			$("#operTypeCut").val(operTypeCut);
  			
  			$("").attr("src", r_path + "/static/web/university/images/fang_img.jpg");
  			
  			//显示正方形 影藏长方形
  			$("#showZFX").hide();
  			$("#showCFX").show();
  			
  			//显示裁剪框
  			$("#tzui-loading-overlay").show();
			$("#tailoring-dialog").show();
			
		});
		
		var orgId = $("#ogrId").val();
		
		upLogo = $('#uploadLogoTriger').Huploadify({
			auto:false,
			fileTypeExts:'*.jpg;*.png;*.jpeg',
			multi:false,
			auto:true,
			formData:{key:123456,key2:'info'},
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
				var path1 = r_path + "/static/web/university/images/100.jpg";
				var path2 = r_path + "/static/web/university/images/fang_img.jpg";
				$("#image169").cropper('replace', path2);
				$("#image").cropper('replace', path1);
				$("#defaultBgCl").hide();
				$("#defaultBgCl169").hide();
			},
			onInit:function(obj){
			},
			onSelect:function(file){
			},
			onUploadComplete:function(file,data,response){
				cancle();
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
		
	
		//加载更换背景的按钮样式
		$("#uploadLogoEnt").click(function(){
			//裁剪type 
  			$("#operTypeCut").val("logo-ent");
  			//弹出选择文件框  用于图片裁剪	   修改
			showDailog();
			
		});
		
		$("#add-logo-btn-ent").click(function(){
			//裁剪type 
  			$("#operTypeCut").val("logo-ent");
			//弹出选择文件框  用于图片裁剪 	    新增
			showDailog();
			
		});
		
		//加载更换背景的按钮样式---企业
		$("#entUploadLogo").click(function(){
			//裁剪type 
			$("#operTypeCut").val("entlogo");
			//弹出选择文件框  用于图片裁剪	   修改
			showDailog();
			
		});
		$("#ent-add-logo-btn").click(function(){
			//裁剪type 
			$("#operTypeCut").val("entlogo");
			//弹出选择文件框  用于图片裁剪 	    新增
			showDailog();
			
		});
		
		function showDailog(){
			$("#selectCropperBox").show();
			$("#preview-show-preview").hide();
			$("#defaultshow-bg").show();
			$("#cropperBox").hide();
			$("#cropperBox169").hide();
			$("#fileNameCut").val("");
			$("#getData").show();
  			$("#getDataIng").hide();
  			$("#loading-spinner").hide();
  			
  			
  			//显示正方形 影藏长方形
  			$("#showZFX").show();
  			$("#showCFX").hide();
  			
  			//预览区 大图
  			$("#logo-preview-pic").show();
  			$("#others-preview-pic").hide();
			
  			//显示裁剪框
			$("#tzui-loading-overlay").show();
			$("#tailoring-dialog").show();
		}
		
		
		
		/* 背景图片触发事件 */
//		$("#select-bgImg-file").click(function(){
//			$("#uploadBg a").trigger("click");
//		});
		/* 保存背景图片 */
		$("#bg-save").click(function (){
			bgUrl = $("#bg_preview").val();
			$.ajax({
				cache : false,
				async : false,
				type : 'POST',
				url : r_path + '/sch/edit',
				data : {
					schoolBg : bgUrl,
					oper : 'bg'
				},
				success : function (data){
					var datas =eval('('+data+')');
					var path = datas.obj;
					$("#save-and-cancel").hide();
					$("#bg_url").val(path);
					$("#bg_preview").val("");
				}
			});
		});
		/* 背景取消 */
		$("#bg-cancle").click(function (){
			var oldUrl = $("#bg_url").val();
			if(oldUrl == '/static/web/images/default-bgImg.jpg'){
				oldUrl = r_path + oldUrl;
			}
			$("#change-background-img").css("background-image","url("+oldUrl+")");
		});
		
	});
	function cancle(){
		upLogo.cancel('*');
	}
	//删除宣讲会附件
	function delXjhImg(){
// 						  		$("#xjhAnnexId").val("");
// 						  		$("#xhjImg").empty();
// 						  		$("#xhjImg").hide();
	}
	
