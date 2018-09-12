/**
 * 
 * 统计辅助js
 * 
 */

$(function(){
});

	function getCount(v){
		if(v == 'timeToTime'){
			$("#conditions").show();
		}else{
			if(v == 'nearMonth'){
				location.href= r_path + "/management/main"
			}else{
				$("#conditions").hide();
				
				switch (v) {
				case "week": getWeekCount(); break;
				case "month": getMonthCount(); break;
				case "quarter": getQuarterCount(); break;
				case "year": getYearCount(); break;
				case "monthActive": getMonthActiveCount(); break;

				default:
					break;
				}
				
			}
			
		}
	}
	
	/*线形图*/
	function setLine(/*统计图id*/picId, /*x轴*/xArray, /*y轴 学生*/yStuArray, /*y轴 企业*/yEntArray, /*y轴*/ySchArray, /*统计图名称*/title, /*y轴名称*/ yTitle){
		if(yTitle == null || yTitle == ''){
			yTitle = "注册数量";
		}
		$('#' + picId ).highcharts({
	        chart: {
	            type: 'spline'
	        },
	        credits: { enabled:false },
	        title: {
	            text: title
	        },
	        xAxis: {
	        	labels: {
	                rotation: -45
	            },
	            categories: xArray
	        },
	        yAxis: {
	            title: {
	                text: yTitle
	            },
	            allowDecimals: false,
	            labels: {
	                formatter: function() {
	                    return this.value 
	                }
	            }
	        },
	        tooltip: {
	            crosshairs: true,
	            shared: true
	        },
	        plotOptions: {
	            spline: {
	                marker: {
	                    radius: 2,
	                    lineColor: '#000',
	                    lineWidth: 1
	                }
	            }
	        },
	        series: [{
	            name: '企业用户',
	            marker: {
	                symbol: 'square'
	            },
	            data: yEntArray

	        },{
	            name: '学生用户',
	            marker: {
	                symbol: 'square'
	            },
	            data: yStuArray

	        },{
	            name: '学校用户',
	            marker: {
	                symbol: 'square'
	            },
	            data: ySchArray

	        }]
	    });
	}
	
	/*柱状图*/
	function setBar(/*统计图id*/picId, /*x轴*/xArray, /*y轴 学生*/yStuArray, /*y轴 企业*/yEntArray, /*y轴*/ySchArray, /*统计图名称*/title){
		var chart = new Highcharts.Chart({
	        chart: {
	            renderTo: picId,
	            type: 'column',
	            margin: 75,
	            options3d: {
	                enabled: true,
	                alpha: 15,
	                beta: 15,
	                depth: 50,
	                viewDistance: 25
	            }
	        },
	        title: {
	            text: title
	        },
	        credits: { enabled:false },
	        plotOptions: {
	            column: {
	                depth: 25
	            }
	        },
	        xAxis: {
	        	labels: {
	                rotation: -45
	            },
	            categories: xArray
	        },
	        yAxis: {
	            title: {
	                text: '注册数量'
	            },
	            allowDecimals: false,
	            labels: {
	                formatter: function() {
	                    return this.value 
	                }
	            }
	        },
	        series: [{
	        	name: '学生用户',
	            data: yStuArray
	        },
	        {
	        	name: '企业用户',
	            data: yEntArray
	        },
	        {
	        	name: '学校用户',
	            data: ySchArray
	        }]
	    });
	    
	}
	
	/*饼状图*/
	function setPie(/*统计图id*/picId, /*x轴*/xArray, /*y轴 学生*/yStuArray, /*y轴 企业*/yEntArray, /*y轴*/ySchArray, /*统计图名称*/title){
		
	}
	
	/*周*/
	function getWeekCount(){
		$.ajax({                
			cache: true,    
			async: true, 
			type: "POST",                
			url:  r_path + '/index/count/week',                
			data:{
			},  
			beforeSend : function(request){
			},
			error: function(request) { 
				alert("网络错误");
			},                
			complete: function(data) {
				var dataStr = data.responseText;
				var datas = eval('('+dataStr+')');
				
				var xArray = datas.obj.xValue.split(",");
				
				var yEntArray = getArray(datas.obj.y_ent_value);
				var ySchArray = getArray(datas.obj.y_sch_value);
				var yStuArray = getArray(datas.obj.y_stu_value);
				
				$(".indexCount").hide();
				setLine("weekCount", xArray, yStuArray, yEntArray, ySchArray, "2016年各周注册统计");
			    $("#weekCount").show();
				
			}           
		});
	}
	
	/*月*/
	function getMonthCount(){
		$.ajax({                
			cache: true,    
			async: true, 
			type: "POST",                
			url:  r_path + '/index/count/month',                
			data:{
			},  
			beforeSend : function(request){
			},
			error: function(request) { 
				alert("网络错误");
			},                
			complete: function(data) {
				var dataStr = data.responseText;
				var datas = eval('('+dataStr+')');
				
				var xArray = datas.obj.xValue.split(",");
				
				var yEntArray = getArray(datas.obj.y_ent_value);
				var ySchArray = getArray(datas.obj.y_sch_value);
				var yStuArray = getArray(datas.obj.y_stu_value);
				
				$(".indexCount").hide();
				setBar("monthCount", xArray, yStuArray, yEntArray, ySchArray, "2016年各月注册统计");
				
				$("#monthCount").show();
				
			}           
		});
	}
	
	/*季度*/
	function getQuarterCount(){
		$.ajax({                
			cache: true,    
			async: true, 
			type: "POST",                
			url:  r_path + '/index/count/quarter',                
			data:{
			},  
			beforeSend : function(request){
			},
			error: function(request) { 
				alert("网络错误");
			},                
			complete: function(data) {
				var dataStr = data.responseText;
				var datas = eval('('+dataStr+')');
				
				var xArray = datas.obj.xValue.split(",");
				
				var yEntArray = getArray(datas.obj.y_ent_value);
				var ySchArray = getArray(datas.obj.y_sch_value);
				var yStuArray = getArray(datas.obj.y_stu_value);
				
				$(".indexCount").hide();
				setBar("quarterCount", xArray, yStuArray, yEntArray, ySchArray, "2016年各季度注册统计");
				
				$("#quarterCount").show();
				
			}           
		});
	}
	
	/*年*/
	function getYearCount(){
		$.ajax({                
			cache: true,    
			async: true, 
			type: "POST",                
			url:  r_path + '/index/count/year',                
			data:{
			},  
			beforeSend : function(request){
			},
			error: function(request) { 
				alert("网络错误");
			},                
			complete: function(data) {
				var dataStr = data.responseText;
				var datas = eval('('+dataStr+')');
				
				var xArray = datas.obj.xValue.split(",");
				
				var yEntArray = getArray(datas.obj.y_ent_value);
				var ySchArray = getArray(datas.obj.y_sch_value);
				var yStuArray = getArray(datas.obj.y_stu_value);
				
				$(".indexCount").hide();
				setBar("yearCount", xArray, yStuArray, yEntArray, ySchArray, "年度统计");
				
				$("#yearCount").show();
				
			}           
		});
	}
	
	/*时间段*/
	function getTimeToTimeCount(){
		var startTime = $("#startTime").val();
		var endTime = $("#endTime").val();
		if(startTime == '' || endTime == ''){
			alert("请选择时间");
			return;
		}
		$.ajax({                
			cache: true,    
			async: true, 
			type: "POST",                
			url:  r_path + '/index/count/timeToTime',                
			data:{
				startTime : startTime,
				endTime : endTime
			},  
			beforeSend : function(request){
			},
			error: function(request) { 
				alert("网络错误");
			},                
			complete: function(data) {
				var dataStr = data.responseText;
				var datas = eval('('+dataStr+')');
				
				var xArray = datas.obj.xValue.split(",");
				
				var yEntArray = getArray(datas.obj.y_ent_value);
				var ySchArray = getArray(datas.obj.y_sch_value);
				var yStuArray = getArray(datas.obj.y_stu_value);
				
				$(".indexCount").hide();
				setLine("timeToTimeCount", xArray, yStuArray, yEntArray, ySchArray, startTime +"至"+ endTime +" 统计");
				
				$("#timeToTimeCount").show();
				
			}           
		});
	}
	
	/**
	 * 月活跃用户统计
	 */
	function getMonthActiveCount(){
		$.ajax({                
			cache: true,    
			async: true, 
			type: "POST",                
			url:  r_path + '/monthActive/monthActive',                
			data:{
			},  
			beforeSend : function(request){
			},
			error: function(request) { 
				alert("网络错误");
			},                
			complete: function(data) {
				var dataStr = data.responseText;
				var datas = eval('('+dataStr+')');
				
				var xArray = datas.obj.xValue.split(",");
				
				var yEntArray = getArray(datas.obj.y_ent_value);
				var ySchArray = getArray(datas.obj.y_sch_value);
				var yStuArray = getArray(datas.obj.y_stu_value);
				
				$(".indexCount").hide();
				setLine("monthActiveCount", xArray, yStuArray, yEntArray, ySchArray, "最近一个月活跃量统计", "用户登录数");
				
				$("#monthActiveCount").show();
				
			}           
		});
	}
	
	
	function getArray(str){
		var array = [];
		var obj = str.split(",");
		for(var i = 0; i < obj.length; i++){
			array.push(parseInt(obj[i]));
		}
		return array;
	}