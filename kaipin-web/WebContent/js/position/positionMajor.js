
/**
 * 职位专业筛选
 */
$(function(){
	
		var selectCodeArray = []; //小类选择器
		var closeArray = [];//取消小类的选择器
		var selectAllCodes ;//初始化所有小类选择器
		var selectAllCodeArray;//所有小类选择器
		var totalSelectEnd = 0;
		var smallSortLen = 0;//选中小分类的个数
		var totalLen = 0;//总共的选择长度
		var kindIndex;
		
		init();
		
		//初始化所有小类选择器
		function init(){
			selectAllCodes = $("#selectAllCodes").val();
			if(isNotEmpty(selectAllCodes)){
				selectAllCodeArray = selectAllCodes.split(",");
			}else{
				selectAllCodeArray = [];
			}
		}
		//显示要选择的图层--大类
		$("#professionalRequirements").click(function(){
			init();
			$("#send-position-overlay").show();
			$("#requirements-select").show();
			var dataStr = getMajorClass("1","");
			var datas = eval('('+dataStr+')');
			var html = "";
			var select_html = "";
			if(datas.success){
				var code = "";
				var name = "";
				for (var i = 0; i < datas.obj.length; i++) {
					code = datas.obj[i].major_code;
					name = datas.obj[i].major_name;
					
					if(selectAllCodeArray.length > 0){
						var classs = "";
						for(var j=0; j < selectAllCodeArray.length; j++){
							var code_class_select = selectAllCodeArray[j].substring(0,6);
							var code_class = code.substring(0,6);
							if(code_class_select == code_class){
								classs = "sort-profession-select";
								var str = getCodeName(selectAllCodeArray[j]);
								if(isNotEmpty(str)){
									var select_data = eval('('+str+')');
									var select_name = select_data.obj[0].major_name;
									var select_code = select_data.obj[0].major_code;
									select_html += '<li data-code='+select_code+' data-index='+i+' data-sort=sort_of_'+i+'>'+
												   '	<span>'+select_name+'</span>'+
												   '	<a href="javascript:;" class="close-select" title="删除"></a>'+
										     	   '</li>';
								}
							}
						}
						
					}
					//大类拼接html
					html = html + "<li class='"+classs+"' data-sort = 'sort_of_"+i+"' data-index='"+i+"' data-tag='"+code+"' data-name='"+name+"'>"+
										"<a href='javascript:;'>"+
											"<span class='name-info'>"+name+"</span> "+
											"<span class='arrows'></span>"+
										"</a>"+
										"<input type='hidden' class='hideTxt' value='0' readonly='readonly'/>"+
								  "</li>";
					
				}
				
				//选择大分类
				var resultOfSelectLen = 0;
				$("#sort-profession-select").on("click","li",function(){
					
					kindIndex  = $(this).index();//获取当前的大类的index
					
					resultOfSelectLen = getResultlen();
					var selectThis = $(this);
					var classCode = selectThis.data("tag");
					var index = selectThis.data("index");
					var className = selectThis.data("name");
					var sortCate = "";
					if(resultOfSelectLen>5){
						getTips();
						return;
					}else{
						selectThis.addClass("sort-profession-select");
						sortCate = selectThis.data("sort");
						
						var data = getMajorClass("2", classCode);
						getMajorSmallClass(data, index, className);//显示该大类下面的小类
						
						$("#transparent-layer").show();
						$("#fication-details").show();
					}
				});
				
				
				$("#sort-profession-select").empty();
				$("#sort-profession-select").append(html);
				
				
				$("#result-of-select").empty();
				$("#result-of-select").append(select_html);
				
				var v_code ;
				for(var i = 0; i < datas.obj.length; i++){
					v_code = datas.obj[i].major_code.substring(0,6);//大类
					for(var j = 0; j < selectAllCodeArray.length; j++){
						var v_select_code = selectAllCodeArray[j].substring(0,6);
						if(v_code == v_select_code){
							//取出当前大类的统计input值 +1，并返回赋值
							var  hideTxt = parseInt($("#sort-profession-select li").eq(i).find("input.hideTxt").val());
							hideTxt +=1;
							$("#sort-profession-select li").eq(i).find("input.hideTxt").val(hideTxt);
							
						}
					}
				}
				
			}
		});
		
		/*展示小分类*/
		function getMajorSmallClass(data, index, className){
			if(isNotEmpty(data)){
				$("#className").html(className);
				var datas = eval('('+data+')');
				var html = "";
				var code = "";
				var name = "";
				for(var i = 0; i < datas.obj.length; i++){
					code = datas.obj[i].major_code;
					name = datas.obj[i].major_name;
					var selectLi = "";
					if(selectAllCodeArray.length > 0){
						for(var j = 0; j < selectAllCodeArray.length; j++){
							var s = selectAllCodeArray[j];
							if(s == code){
								selectLi = "<li data-flag = 'open'  class='sort-profession-select' data-index='"+index+"' data-code='"+code+"' data-sort = 'sort_of_"+index+"'>"
								break;
							}else{
								selectLi = "<li data-flag = 'close'  data-code='"+code+"' data-index='"+index+"' data-sort = 'sort_of_"+index+"'>"
								continue;
							}
						}
					}else{
						selectLi = "<li data-flag = 'close' data-code='"+code+"' data-index='"+index+"' data-sort = 'sort_of_"+index+"'>";
					}
					html = html + selectLi +
									 "<a href='javascript:;'>" +
										"<span class='name-info'>"+name+"</span> " +
										"<span class='right'></span>" +
									 "</a>" +
								  "</li>";
				}
				
				closeArray = [];
				$("#major-small-class").empty();
				$("#major-small-class").append(html);
			}
		}
		
		//关闭
		$("#colseSelectMajor").click(function(){
			
			$("#send-position-overlay").hide();
			$("#requirements-select").hide();
		});
		//关闭
		$("#cancleSelectMajor").click(function(){
			$("#colseSelectMajor").trigger("click");
		});
		
		
		//已选择好的分类
		function getResultlen(){
			return $("#result-of-select").find("li").length;
		}
		
		
		
		$("#fication-details").off().on("click","li",function(){
			var infoThis = $(this);
			var code = infoThis.data("code");
			infoThis.toggleClass("sort-profession-select");
			var flag = infoThis.data("flag");
			if(flag == "close"){
				flag = infoThis.data("flag","open");//选中
				if(closeArray.length > 0){
					for(var i = 0; i < closeArray.length; i++){
						if(closeArray[i] == code){
							closeArray.remove(i);
						}
					}
				}
			}else{
				flag = infoThis.data("flag","close");//取消选中
				var closeCode = infoThis.data("code");
				closeArray.push(closeCode);
			}
			
			
			
		});
		
		
		
		/*
		 * 选择小分类--小类确定按钮
		 */
		$("#fication-details").find(".subBth-sure").click(function(){
			smallSortLen = 0;//选中小分类的个数
			totalLen = 0;//总共的选择长度
			
			var smallAllLen = $("#fication-details").find("li").length;//小分类的总长度
			resultOfSelectLen = getResultlen();
			var intVal = 0;
			var nowCode = 0;
			kindIndex  = $("#major-small-class").find("li").eq(0).data("index");//获取当前的大类的index
			
			//重新初始化选中数组
			selectCodeArray = [];
			
			//获取选中小分类的个数
			for(var i=0;i<smallAllLen;i++){
				if($("#fication-details").find("li").eq(i).data("flag") == "open"){
					var selectCode = $("#fication-details").find("li").eq(i).data("code");
					selectCodeArray.push(selectCode);
					smallSortLen ++;
				}
			}

			//去掉大类选中效果
			if(smallSortLen==0){
				$("#sort-profession-select").find("li").eq(kindIndex).removeClass("sort-profession-select");
			}
			
			/*
			 *  获取除当前本身以外的所选小类的总和
			 */
			for(var l = 0;l<$("#sort-profession-select").find("li").length;l++){
				if(l != kindIndex){
					intVal += parseInt($("#sort-profession-select li").eq(l).find("input").val());
				}
			}
			var total = smallSortLen + intVal;
			if(total > 5){
				getTips();
				return;
			}
			//为大类赋值
			$("#sort-profession-select li").eq(kindIndex).find("input").val(smallSortLen);
			
			//获取当前小类---去重
            selectCodeArray = repeteArray(selectCodeArray);
			
            //加上当前本身及以外的所选小类的总和
			totalLen = total;
			
			//如果选择的小分类的长度大于5
			if(totalLen > 5){
				getTips();
				return;
			}else{
				var innTxt = "";
				var dataSort = $("#fication-details .kind-of-pro-lists").find("li").data("sort");
				
				for (var i=0;i<smallAllLen;i++){
					if($("#fication-details .kind-of-pro-lists").find("li").eq(i).data("flag") == "open"){
						var val = $("#fication-details .kind-of-pro-lists").find("li").eq(i).text();
						var $code = $("#fication-details .kind-of-pro-lists").find("li").eq(i).data("code");
						var flags = true;
						for(var n = 0; n < selectAllCodeArray.length; n++){
							if(selectAllCodeArray[n] == $code){//判断已经选择好的分类是否包含该小类
								flags = false;
								break;
							}
						}
						
						//取消小分类时，删除已经选择好的分类
						/*for(var m = 0;m<closeArray.length;m++){
							for(var t = 0; t < selectAllCodeArray.length; t++){
								if(closeArray[m]==selectAllCodeArray[t]){
									var $resultLi = $("#result-of-select").find("li"); 
									for(var y=0;y<$resultLi.length;y++){
										var $rm_index = $resultLi.eq(y).data("index");
										var $rm_code = $resultLi.eq(y).data("code");
										if($rm_code == closeArray[m]){
											$resultLi.eq(y).remove();
										}
									}
								}
							}
						}*/
						//如果已经选择好的分类未包含该小类，则添加该小类
						if(flags){
							var  selectSort= $("#fication-details .kind-of-pro-lists").find("li").eq(i).data("sort");
							innTxt += '<li data-code='+$code+' data-index='+kindIndex+' data-sort='+selectSort+'>'+
									  '	<span>'+val+'</span>'+
									  '	<a href="javascript:;" class="close-select" title="删除"></a>'+
							     	  '</li>';
							};
						}
				}
				
				for(var i = 0; i < selectAllCodeArray.length; i++){
					for(var j = 0; j < closeArray.length; j++){
						if(selectAllCodeArray[i] == closeArray[j]){
							
							for(var k = 0;k < getResultlen();k++){
								var vv  = $("#result-of-select").find("li").eq(k).data("code");
								if(vv == selectAllCodeArray[i]){
									$("#result-of-select").find("li").eq(k).remove();
								}
							}
							
							for(var k = 0;k<resultOfSelectLen;k++){
								var delectCode = $("#result-of-select").find("li").eq(k).data("code");
								var txt =selectAllCodeArray[i];
								if( delectCode==txt ){
									var delectIndex = $("#result-of-select").find("li").eq(k).index;
									$("#result-of-select").find("li").eq(delectIndex).remove();
								}
							}
							
						}
					}
				}
				
				
				if(totalLen<=5){
					//alert(totalLen);
					//return;
					$("#fication-details").hide();
					$("#transparent-layer").hide();
					$("#result-of-select").append(innTxt);	
				}else{
					getTips();
					return;
				}
			}
			
			
			//把选择好的code放到全局里面去
			for(var i = 0; i < selectCodeArray.length; i++){
				selectAllCodeArray.push(selectCodeArray[i]);
			}
			selectAllCodeArray = repeteArray(selectAllCodeArray);
			//alert("全局所有的code == " + selectAllCodeArray);
			
			/**
			 * 获取最后选中的小类id集合
			 */
			for(var m = 0;m<closeArray.length;m++){
				for(var t = 0; t < selectAllCodeArray.length; t++){
					if(closeArray[m]==selectAllCodeArray[t]){
						selectAllCodeArray.remove(t);
					}
				}
			}
			
		});
		
		
		//删除已选分类
		
		$("#result-of-select").on("click","li a.close-select",function(){
			
			
			var patrnts = $(this).parents("li");
			var li_index = patrnts.index();
			selectCodeArray.remove(li_index);
			var parentsSort = patrnts.data("sort");
			var numSortnum = 0;
			var sortArry = [];
			var thisDlIndex = patrnts.data("index");
			
			
			var codes = patrnts.data("code");
			for(var i =0;i<selectAllCodeArray.length;i++){
				if(codes == selectAllCodeArray[i]){
					selectAllCodeArray.remove(i);
				}
			}
			
			patrnts.remove();
			var v = $("#sort-profession-select li").eq(thisDlIndex).find("input").val();
			$("#sort-profession-select li").eq(thisDlIndex).find("input").val(v-1);
			var resultLens = $("#result-of-select").find("li").length;
			for(var i=0;i<resultLens;i++){
				sortArry.push($("#result-of-select").find("li").eq(i).data("sort"));
			};
			
			
			for(var i=0;i<sortArry.length;i++){
				
				if(parentsSort == sortArry[i] ){
					numSortnum ++;
				}
			}
			
			var parentsortLen = $("#sort-profession-select").find("li").length;
			for(var i=0;i<parentsortLen;i++){
				if($("#sort-profession-select").find("li").eq(i).data("sort") == parentsSort){
					if(numSortnum<=0){
						$("#sort-profession-select").find("li").eq(i).removeClass("sort-profession-select");
					}
				}
			}
		});


		//取消选择分类
		$("#fication-details").find(".subBth-cancle").on("click",function(){
			
			var newIndex = $("#major-small-class").find("li").eq(0).data("index");
			var newNum = 0;
			var cancleSelectArray = [];
			for(var i=0;i<$("#major-small-class").find("li").length;i++){
				cancleSelectArray.push($("#major-small-class").find("li").eq(i).data("code"));
			}
			for(var i =0;i<cancleSelectArray.length;i++){
				for(var j =0;j<selectAllCodeArray.length;j++){
					if(selectAllCodeArray[j] == cancleSelectArray[i]){
						newNum++;
					} 
				}
			}
			if( newNum == 0){
				$("#sort-profession-select").find("li").eq(kindIndex).removeClass("sort-profession-select");
			}
			
			$("#fication-details").hide();
			$("#transparent-layer").hide();
		});
		
		
		//选择分类完成
		$("#requirements-select").find(".subBth-sure").click(function(){
			var endLen  = $("#result-of-select").find("li").length;
			$("#professionalRequirements span").html("已选择"+endLen+"个专业");
			$("#requirements-select").hide();
			$("#send-position-overlay").fadeOut();
			var ids = selectAllCodeArray.join(",");
			$("#selectAllCodes").val(ids);
		});
		
		
		function getTips(){
			$("#tips-of-select").show();
			setTimeout(function(){
				$("#tips-of-select").hide();
			},1000);
		}
		
		/*获取专业大类*/
		function getMajorClass(type, code){
			var dataStr = "";
			$.ajax({                
				cache: false,    
				async: false, 
				type: "POST",                
				url:  r_path + '/position/getMajorClass.do',                
				data:{
					type : type,
					code : code
				},              
				error: function(request) {                    
				},                
				success: function(data) {
					if(data == ''){
						return;
					}
					dataStr = data;
				}            
			});
			return dataStr;
		}
		
		/**
		 * 获取编码名称
		 */
		function getCodeName(code){
			var tName = "comm_major";
			var params = "  major_code = '"+code+"' ";
			var datass = "";
			$.ajax({                
				cache: false,    
				async: false, 
				type: "POST",                
				url:  r_path + '/baseCode/getList.do',
				data:{
					tName : tName,
					params : params
				},              
				error: function(request) {                    
				},                
				success: function(data) {
					datass = data;
				}            
			});
			return datass;
		}
		
});