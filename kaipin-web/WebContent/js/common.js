	//###############################################
	//##########                    #################
	//##########          公共组件                 #################
	//##########                    #################
	//###############################################

	/**
	 * 导航栏
	 */
	 //简历管理
	jQuery(function($){  
	    // 备份jquery的ajax方法    
	    var _ajax=$.ajax;  
	    // 重写ajax方法，先判断登录在执行success函数   
	    $.ajax=function(opt){  
	        var _success = opt && opt.success || function(a, b){};  
	        var _opt = $.extend(opt, {  
	            success:function(data, textStatus){  
	                // 如果后台将请求重定向到了登录页，则data里面存放的就是登录页的源码，这里需要找到data是登录页的证据(标记)  
//	                if(data.indexOf('weinianjie') != -1) {  
//	                    window.location.href= Globals.ctx + "/login.action";  
//	                    return;  
//	                }  
//	            	alert(data);
	            	if(data == 'timeout'){
	            		alert("登录超时，请重新登录！");
	            		location.href=r_path+"/loginController/index.do";
	            		return;
	            	}
	                _success(data, textStatus);    
	            }    
	        });  
	        _ajax(_opt);  
	    };  
	});
	
	$(function(){
		
		
		/* 查询基本信息 和 配置文件 */
		$("#account-set-btn").click(function(){
			$.ajax({                
				cache: false,                
				type: "POST",                
				url:  r_path + '/basicConctroller/getUserInfoAndConfig.do',                
				async: false, 
				data :{
					oper : 0
				},
				error: function(request) {                    
				},                
				success: function(data) {
					var datas = eval('('+data+')');
					var sex = datas.obj.sex;
					$("#mod-entName").val(datas.obj.entName);
					$("#mod-entName-tip").html(datas.obj.entName);
					$("#mod-email-tip").html(datas.obj.email);
					if(sex != null){
						if(sex == 1){
							$("#user-sex-select").html("女");
						}else if(sex == 2){
							$("#user-sex-select").html("男");
						}else if(sex == 0){
							$("#user-sex-select").html("未知");
						}
					}
					
						for(var key in datas.obj){
							var value = datas.obj[key];
						$("#user-"+key).val(value);
					}
						
				}            
			});
		});
		/* 个人中心性别选择 */
		$(".userinfo a").click(function(){
			var data = $(this).data("tag");
			$("#user-sex").val(data);
		});
		
		
		
	});
	
	/**
	 * 判读字符串是否为空
	 * @param obj
	 * @returns {Boolean}
	 */
	function isNotEmptys(obj){
		if (obj== undefined){
			return false;
		}
		if (obj != '') { 
			return true;
		}else{
			return false;
		}
	}
	 /**
	  * 判断只字符串是否为空
	  */
	function isNotEmpty(val){
		return $.trim(val).length!=0;
	}
	/**
	 * 弹出框
	 * @param obj
	 */
	function alertDiv(obj){
		alert(obj);
	}
	
	/**
	 * 切换导航栏背景颜色
	 * @param obj
	 */
	function changeMulu(obj){
		$("#lm-navList").find("li").eq(obj).addClass("nav-active").siblings().removeClass("nav-active");
		$("#muluId").val(obj);
	}
	
	/**
	 * 检查资质认证
	 */
	function checkCertificate(){
		$.ajax({
			cache : false,
			async : true,
			type : 'POST',
			url : r_path + '/basicConctroller/checkCertificate.do',
			success : function (data){
				var datas =  eval('(' + data + ')'); 
				if(!datas.success){
					$("#no-ertificate").show();
					$("#no-ertificate1").show();
					$("#certificate-tip").show();
				}else{
					if(datas.obj == '0'){
						$("#ing-ertificate").show();
						$("#ing-ertificate1").show();
					}else if(datas.obj == '1'){
						$("#nopass-ertificate").show();
						$("#nopass-ertificate1").show();
					}else if(datas.obj == '2'){
						$("#yes-ertificate").show();
						$("#yes-ertificate1").show();
					}
				}
			}
		});
	}
	
	
	//简历管理
	 function resumePage(){
		location.href=r_path+"/resume/init.do";
	 }
	 //职位管理
	 function potionPage(){
		 location.href=r_path+"/position/init.do";
	 }
	 
	 /**
	  * 导航栏下面的标签页切换
	  */
	 //简历未阅读
	 function resumeNoPage(){
		 location.href=r_path+"/resume/init.do";
	 }
	 //简历已经阅读
	 function resumeYesPage(){
		 location.href=r_path+"/resumeYesController/init.do";
	 }
	 //刷选通过
	 function resumePassPage(){
		 location.href=r_path+"/resumePassOrNoController/initPass.do";
	 }
	 //刷选不通过
	 function resumePassNoPage(){
		 location.href=r_path+"/resumePassOrNoController/initNOPass.do";
	 }
	 //全部
	 function resumeAllPage(){
		 location.href=r_path+"/resumeAllController/init.do";
	 }
	 
	 /*统计*/
	 /*function counts(){
		 alert(1);
		$.ajax({
			 cache : false,
			 async : false,
			 url : r_path + '/resume/counts.do',
			 data : {
				 
			 },
			 success : function(data){
				var datas = eval('('+data+')');
				var zero = datas.obj.zero;
				var one = datas.obj.one;
				var two = datas.obj.two;
				var three = datas.obj.three;
				var total = datas.obj.total;
				$("#zero").html(zero);
				$("#one").html(one);
				$("#two").html(two);
				$("#three").html(three);
				$("#total").html(total);
			 }
		 });
	 }*/
	 
	 /**
	  * 获取项目根路劲
	  * @returns
	  */
	//js获取项目根路径，如： http://localhost:8083/uimcardprj
	 function getRootPath(){
	     //获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
	     var curWwwPath=window.document.location.href;
	     //获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
	     var pathName=window.document.location.pathname;
	     var pos=curWwwPath.indexOf(pathName);
	     //获取主机地址，如： http://localhost:8083
	     var localhostPaht=curWwwPath.substring(0,pos);
	     //获取带"/"的项目名，如：/uimcardprj
	     var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
	     return(localhostPaht+projectName);
	 }
	 
	 /**获取时间段*/
	//js函数代码：字符串转换为时间戳
	function getDateTimeStamp(dateStr) {
		return Date.parse(dateStr.replace(/-/gi, "/"));
	}
	//JavaScript函数：
	var minute = 1000 * 60;
	var hour = minute * 60;
	var day = hour * 24;
	var halfamonth = day * 15;
	var month = day * 30;
	
	function getDateDiff(dateTimeStamp) {
		var now = new Date().getTime();
		var diffValue = now - dateTimeStamp;
		if (diffValue < 0) {
			//若日期不符则弹出窗口告之
			//alert("结束日期不能小于开始日期！");
		}
		var monthC = diffValue / month;
		var weekC = diffValue / (7 * day);
		var dayC = diffValue / day;
		var hourC = diffValue / hour;
		var minC = diffValue / minute;
		if (monthC >= 1) {
			result = parseInt(monthC) + "个月前";
		} else if (weekC >= 1) {
			result = parseInt(weekC) + "周前";
		} else if (dayC >= 1) {
			result = parseInt(dayC) + "天前";
		} else if (hourC >= 1) {
			result = parseInt(hourC) + "个小时前";
		} else if (minC >= 1) {
			result = parseInt(minC) + "分钟前";
		} else
			result = "刚刚";
		return result;
	}
	
	/*根据时间戳获取时间*/
	function getTimeByMillis(Millis){
		var date = new Date(Millis);
		Y = date.getFullYear() + '-';
		M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
		D = date.getDate() < 10 ? '0'+date.getDate() + ' ' : date.getDate()+' ' ;
//		h = date.getHours() + ':';
//		m = date.getMinutes() + ':';
//		s = date.getSeconds(); 
		//alert(Y+M+D+h+m+s); //呀麻碟

		var h = parseInt(date.getHours());
		var m = parseInt(date.getMinutes());
		var s = parseInt(date.getSeconds());
		h = h >= 10? h: "0"+h;
		m = m >= 10? m: "0"+m;
		s = s >= 10? s: "0"+s;
		
		h = h + ':';
		m = m + ':';
		return Y+M+D+h+m+s;
	}
	
	/**
	 * 比较日期大小  只涉及年月日
	 * @param a
	 * @param b
	 * @returns {Boolean}
	 */
	function compaerTime(a,b)
	{
		a = a.substring(0,10);
		b = b.substring(0,10);
		var arr=a.split("-");
		var starttime=new Date(arr[0],arr[1],arr[2]);
		var starttimes=starttime.getTime();
	
		var arrs=b.split("-");
		var lktime=new Date(arrs[0],arrs[1],arrs[2]);
		var lktimes=lktime.getTime();
	
		if(starttimes>=lktimes)
		{
			return false;
		}
		else
		return true;

	}
	//判断日期，时间大小 
	function compareTimes(startDate, endDate) {
		if (startDate.length > 0 && endDate.length > 0) {
			var startDateTemp = startDate.split(" ");
			var endDateTemp = endDate.split(" ");
	
			var arrStartDate = startDateTemp[0].split("-");
			var arrEndDate = endDateTemp[0].split("-");
	
			var arrStartTime = startDateTemp[1].split(":");
			var arrEndTime = endDateTemp[1].split(":");
	
			var allStartDate = new Date(arrStartDate[0], arrStartDate[1],
					arrStartDate[2], arrStartTime[0], arrStartTime[1],
					arrStartTime[2]);
			var allEndDate = new Date(arrEndDate[0], arrEndDate[1], arrEndDate[2],
					arrEndTime[0], arrEndTime[1], arrEndTime[2]);
	
			if (allStartDate.getTime() >= allEndDate.getTime()) {
				//alert("startTime不能大于endTime，不能通过");
				return false;
			} else {
				//alert("startTime小于endTime，所以通过了");
				return true;
			}
		}
	} 
	/* 比较时间大小 */
	function compareTo(beginTime,endTime){  
// var beginTime = "2009-09-21 00:00:02";
//        var endTime    = "2009-09-21 00:00:01";  
        var beginTimes = beginTime.substring(0,10).split('-');  
        var endTimes   =  endTime.substring(0,10).split('-'); 
        beginTime = beginTimes[1]+'-'+beginTimes[2]+'-'+beginTimes[0]+' '+beginTime.substring(10,19);  
        endTime    = endTimes[1]+'-'+endTimes[2]+'-'+endTimes[0]+' '+endTime.substring(10,19);  
        var a =(Date.parse(endTime)-Date.parse(beginTime))/3600/1000;  
//        if(a<0){  
//            alert("endTime小!");  
//        }else if (a>0){  
//            alert("endTime大!");  
//        }else if (a==0){  
//            alert("时间相等!");  
//        }  
        if(a > 0){
        	return true;
        }else{
        	return false;
        }
     }  
	
	/*function getList(parentId){
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
	}*/
	
	function formatDate(now)   {     
        var  year = now.getFullYear();     
        var  month=parseInt(now.getMonth()+1);     
        var  date=parseInt(now.getDate());     
        var  hour=parseInt(now.getHours());     
        var  minute=parseInt(now.getMinutes());     
        var  second=parseInt(now.getSeconds()); 
        
        month =  month > 10 ? month: "0"+month; 
        date = date > 10 ? date: "0"+date; 
        hour =  hour > 10 ? hour: "0"+hour; 
        minute = minute > 10 ? minute: "0"+minute; 
        second = second > 10 ? second: "0"+second; 
        return   year+"-"+month+"-"+date+" "+hour+":"+minute+":"+second;     
    }
	
	/**
	 * 公共--获取下拉列表
	 */
	function commonList(tName, parentCode){
		var datas ;
		$.ajax({                
			cache: false,                
			type: "POST",                
			url:  r_path + '/commCode/getCodeList',   
			data : {
				parentCode : parentCode,
				tableName : tName
				
			},
			async: false,                
			error: function(request) {                    
			},                
			success: function(data) {
				datas = data;
			}            
		});
		return datas;
	}
	
	/*删除数组指定下表元素*/
	Array.prototype.remove=function(obj){  
        for(var i =0;i <this.length;i++){  
            var temp = this[i];  
            if(!isNaN(obj)){  
                temp=i;  
            }  
            if(temp == obj){  
                for(var j = i;j <this.length;j++){  
                    this[j]=this[j+1];  
                }  
                this.length = this.length-1;  
            }     
        }  
    }
	
	/*数组去重*/
	function repeteArray(/*数组*/array){
		var hash = {},
        len = array.length,
        result = [];
	    for (var i = 0; i < len; i++) {
	        if (!hash[array[i]]) {
	            hash[array[i]] = true;
	            result.push(array[i]);
	        }
	    }
	    return result;
	}
	
	/*判断图片是否加载完毕*/
	function loadImg(src,callback){
		var img = new Image();
		img.src = src;	
		 //FF，chrome浏览器的判断方式
		 if(img.complete){//判断图片是否下载完毕
			if(callback)callback.call(img,true);	
		  }else{
			//ie浏览器
			img.onload = function(){
				if(callback)callback.call(img,true);
			};
			//图片加载失败
			img.onerror = function(){
				if(callback)callback.call(img,false);
			};
		}
		 
	};
	
	String.prototype.startWith=function(s){
		  if(s==null||s==""||this.length==0||s.length>this.length)
		   return false;
		  if(this.substr(0,s.length)==s)
		     return true;
		  else
		     return false;
		  return true;
		 }
	
	
	/* 
	功能：保存cookies函数  
	参数：name，cookie名字；value，值 
	*/  
	function SetCookie(name,value){  
	    var Days = 30*12;   //cookie 将被保存一年  
	    var exp  = new Date();  //获得当前时间  
	    exp.setTime(exp.getTime() + Days*24*60*60*1000);  //换成毫秒  
	    document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString();  
	}   
	/* 
	功能：获取cookies函数  
	参数：name，cookie名字 
	*/  
	function getCookie(name){  
	    var arr = document.cookie.match(new RegExp("(^| )"+name+"=([^;]*)(;|$)"));  
	    if(arr != null){  
	     return unescape(arr[2]);   
	    }else{  
	     return null;  
	    }  
	}  
	
	/* 
	功能：删除cookies函数  
	参数：name，cookie名字 
	*/  
	  
	function delCookie(name){  
	    var exp = new Date();  //当前时间  
	    exp.setTime(exp.getTime() - 1);  
	    var cval=getCookie(name);  
	    if(cval!=null) document.cookie= name + "="+cval+";expires="+exp.toGMTString();  
	}
