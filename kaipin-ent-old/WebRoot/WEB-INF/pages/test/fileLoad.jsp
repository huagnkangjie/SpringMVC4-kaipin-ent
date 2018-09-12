<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>文件上传</title>
 	<link rel="stylesheet" type="text/css" href="<%=path%>/js/uploadify/Huploadify.css"/>
 	<script type="text/javascript">
		var r_path='<%=basePath%>';
	</script>
	<script type="text/javascript" src="<%=path%>/js/uploadify/jquery.js"></script>
	<script type="text/javascript" src="<%=path%>/js/uploadify/jquery.Huploadify.js"></script>
  <script >
  $(function(){
		var up = $('#upload').Huploadify({
			auto:false,
			fileTypeExts:'*.*',
			multi:true,
			formData:{key:123456,key2:'vvvv'},
			fileSizeLimit:99999999999,
			removeCompleted:false,
			removeTimeout: 50,//上传完成后进度条的消失时间
			showUploadedPercent:true,
			showUploadedSize:true,
			removeTimeout:9999999,
			uploader:'<%=path%>/annexController/uploadImgCut.do',
			onUploadStart:function(file){
				
			},
			onInit:function(obj){
				console.log('初始化');
				console.log(obj);
			},
			onUploadComplete:function(file,data,response){
				var datas = data.split(",");
				var path = datas[0];
				$("#showPic").empty();
				$("#fileName").val(datas[1]);
				//alert("文件名	 "+datas[1]);
				$("#showPic").append("<img src='"+path+"'/>");
				cancle();
			},
			onCancel:function(file){
				console.log(file.name+'删除成功');
			},
			onClearQueue:function(queueItemCount){
				console.log('有'+queueItemCount+'个文件被删除了');
			},
			onDestroy:function(){
				console.log('destroyed!');
			},
			onSelect:function(file){
				console.log(file.name+'加入上传队列');
			},
			onQueueComplete:function(queueData){
				console.log('队列中的文件全部上传完成',queueData);
			}
		});
		
		function cancle(){
			up.cancel('*');
		}

		$('#btn2').click(function(){
			up.upload('*');
		});
		$('#btn3').click(function(){
			up.cancel('*');
		});
		$('#btn4').click(function(){
			//up.disable();
			up.Huploadify('disable');
		});
		$('#btn5').click(function(){
			up.ennable();
		});
		$('#btn6').click(function(){
			up.destroy();
		});
		
		/* 删除指定文件 */
		$('#deleteFile').click(function(){
			$.ajax({                
					cache: false,    
					async: false, 
					type: "POST",                
					url:  r_path + '/annexController/deleteFile.do',
					data:{
						fileName : '1.jpg'
					},              
					error: function(request) {    
						alert("网络异常");
					},                
					success: function(data) {
						alert(data);
					}            
			});
		});
		
		/* 图片裁剪 */
		$('#ingCut').click(function(){
			var datas = "{\"x\":20,\"y\":20,\"w\":50,\"h\":70}";
			var fileName = $("#fileName").val();
			$.ajax({                
					cache: false,    
					async: false, 
					type: "POST",                
					url:  r_path + '/annexController/imgCut.do',
					data:{
						datas : datas,
						fileName : fileName
					},              
					error: function(request) {    
						alert("网络异常");
					},                
					success: function(data) {
						var datas = eval('('+data+')');
						var path = datas.obj;
						$("#showCutPic").empty();
						$("#showCutPic").append("<img src='"+path+"'/>");
					}            
			});
		});
		
		
	});
     </script>
  </head>
  <body>
   <div id="upload"></div>
	<button id="btn2">upload</button>
	<button id="btn3">cancel</button>
	<button id="btn4">disable</button>
	<button id="btn5">ennable</button>
	<button id="btn6">destroy</button>
	</br>
	</br>
	</br>
	删除指定文件： <a href="javascript:void(0);" id="ingCut">裁剪</a>
	</br>
	</br>
	</br>
	</br>
	</br>
	</br>
	</br>
	<input type="text" id="fileName"/>
	</br>
	<div id="showPic" style="border:1px red solid;width: 360px;height: 270px;text-align: center;"></div>
	<div id="showCutPic"></div>
  </body>
</html>