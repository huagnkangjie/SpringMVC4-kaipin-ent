$(document).ready(function(){ 
//    eduList();
//    workExpList();
//    proviceList();
	 
}); 

/**
 * 获取教育list
 */
function eduList(){
	$.ajax({                
		cache: true,    
		async: true, 
		type: "POST",                
		url:  r_path + '/commonListController/getList.do',                
		data:{
			parentId : encodeURI(5)
		},              
		error: function(request) {                    
		},                
		success: function(data) {
			if(data == '')return;
			var datas = eval('('+data+')');
			var html = "";
			if(datas.success){
				for(var i = 0; i < datas.obj.length; i++){
					html = html + "<a href='javascript:void(0)' data-tag='"+datas.obj[i].code+"' >"+datas.obj[i].name+"</a>";
				}
				$("#eduList").empty();
				$("#eduList").append(html);
			}
		}            
	});
}

/**
 * 工作经验List
 */
function workExpList(){
	$.ajax({                
		cache: true,    
		async: true, 
		type: "POST",                
		url:  r_path + '/commonListController/getList.do',                
		data:{
			parentId : encodeURI(6)
		},              
		error: function(request) {                    
		},                
		success: function(data) {
			if(data == '')return;
			var datas = eval('('+data+')');
			var html = "";
			if(datas.success){
				for(var i = 0; i < datas.obj.length; i++){
					html = html + "<a href='javascript:void(0)' data-tag='"+datas.obj[i].code+"' >"+datas.obj[i].name+"</a>";
				}
				$("#workExpList").empty();
				$("#workExpList").append(html);
			}
		}            
	});
}

/**
 * 获取省
 */
function proviceList(){
	$.ajax({                
		cache: true,    
		async: true, 
		type: "POST",                
		url:  r_path + '/commonListController/getAera.do',                
		data:{
			code : ''
		},              
		error: function(request) {                    
		},                
		success: function(data) {
			if(data == '')return;
			var datas = eval('('+data+')');
			var html = "<a href='javascript:void(0)' onclick='cityList(-1)' data-tag='-1' >不限</a>";
			if(datas.success){
				for(var i = 0; i < datas.obj.length; i++){
					var code = datas.obj[i].id;
					html = html + "<a href='javascript:void(0)' class='change' onclick='cityList("+code+")' data-tag='"+code+"' >"+datas.obj[i].name+"</a>";
				}
				$("#proviceList").empty();
				$("#cityList").empty();
				$("#proviceList").append(html);
				$(".change").click(function(){
					$("#cityName").html("市:");
					var obj = $(this).data("tag");
					$.ajax({                
						cache: true,    
						async: false, 
						type: "POST",                
						url:  r_path + '/commonListController/getAera.do',                
						data:{
							code : obj
						},              
						error: function(request) {                    
						},                
						success: function(data) {
							if(data == '')return;
							var datas = eval('('+data+')');
							var html = "<a href='javascript:void(0)' onclick='cityList(-1)' data-tag='-1' >不限</a>";
							if(datas.success){
								for(var i = 0; i < datas.obj.length; i++){
									var code = datas.obj[i].doccode;
									html = html + "<a href='javascript:void(0)' onclick='cityList("+code+")' data-tag='"+code+"' >"+datas.obj[i].name+"</a>";
								}
								$("#cityList").empty();
								$("#cityList").append(html);
							}
						}            
					});
				});
			}
		}            
	});
}

/**
 * 获取市区
 */
function cityList(obj){
	$.ajax({                
		cache: true,    
		async: false, 
		type: "POST",                
		url:  r_path + '/commonListController/getAera.do',                
		data:{
			code : obj
		},              
		error: function(request) {                    
		},                
		success: function(data) {
			if(data == '')return;
			var datas = eval('('+data+')');
			var html = "<a href='javascript:void(0)' onclick='cityList(-1)' data-tag='-1' >不限</a>";
			if(datas.success){
				for(var i = 0; i < datas.obj.length; i++){
					var code = datas.obj[i].doccode;
					html = html + "<a href='javascript:void(0)' onclick='cityList("+code+")' data-tag='"+code+"' >"+datas.obj[i].name+"</a>";
				}
				$("#cityList").empty();
				$("#cityList").append(html);
			}
		}            
	});
}
