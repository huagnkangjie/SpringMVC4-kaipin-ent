//前台页面方法
$(document).ready(function(){
		
	/*省 区、县下拉框*/
	$(".select-adress-selectDown").find(".down-list").off().on("change",".down-list",function(){
		var index = $(this).parents(".down-list").index();
		var valTitle = $(this).find("option:selected").text();
		var value = $(this).val();
		var parents = $(this).parents(".sa-allSty");
		
		 var $opt = $(this).find('option');
         $opt.each(function(i) {
             if ($opt[i].selected == true) {
                 txt = $opt[i].innerHTML;
             }
         })
         parents.find('span').html(txt);
         
		 switch(index){
			case 0 : city(value); break;
			case 1 : area(value); break;
		 } 
			
			/*var parentsLab =  $(this).parents(".sa-allSty").find("span");
			parentsLab.html(valTitle);*/
			
	});
	
	//发布以往的视频
	$("#historyVedio").click(function(){
		var startTime = $("#start_time_history");
		var st = returnTimer(startTime,"start");
		$("#history-startTime").val(st);
		var history_subject = $("#history-subject").val();
		var historyImgUrl = $("#historyImgUrl").val();
		var history_memo = $("#history-memo").val();
		var upHistoryVedioUrl = $("#upHistoryVedioUrl").val();
		if(history_subject.length == 0){
			alert("请填写视频名称");
			return;
		}else if(history_subject.length > 30){
			alert("视频名称字数不能超过30个汉字");
			return;
		}
		if(historyImgUrl.length == 0){
			alert("请上传视频封面图");
			return;
		}
		if(history_memo.length > 2000){
			alert("视频介绍字数超过2000");
			return;
		}
		if(upHistoryVedioUrl.length == 0){
			alert("请上传视频");
			return;
		}
		//以往视频保存
		$.ajax({                
			cache: true,                
			type: "POST",                
			url:  r_path + '/entMeetingNoticeController/add.do',                
			data:$('#historyForm').serialize(),// 你的formid                
			async: false,                
			error: function(request) {                    
			},                
			success: function(data) {
				document.historyForm.reset();
				$("#tzui-loading-overlay").hide();
				$(".preacp-and-oldvideo").hide();
				$("#meetingPage").val("1");
				$("#meetCotent").empty();
//				xjhList(1,1);
				location.href=r_path + "/home";
			}            
		});
		
	});
	
	
//	获取时间
	function returnTimer($id,$mark){
		var startYear = parseInt($id.find("."+$mark+"Year").html());
		var startMouth = parseInt($id.find("."+$mark+"Mouth").html());
		var startData = parseInt($id.find("."+$mark+"Data").html());
		var startHour = parseInt($id.find("."+$mark+"Hour").html());
		var startMinutes = parseInt($id.find("."+$mark+"Minutes").html());
		
		startMouth = startMouth>=10? startMouth: "0"+startMouth;
		startData = startData>=10? startData: "0"+startData;
		startHour = startHour>=10? startHour: "0"+startHour;
		startMinutes = startMinutes>=10? startMinutes: "0"+startMinutes;
		var stratTime = startYear + "-" + startMouth + "-"+startData+" "+startHour+":"+startMinutes+":00";
		return stratTime;
		
	}
	
	//宣讲会历史维护编辑关闭按钮
	$("#close-meetingss").on("click",function(){
		$("#tzui-loading-overlay").hide();
		$("#historyXjhEdit").hide();
	});
	
	//关注list
	$("#followCountList").click(function(){
		window.location.href= r_path + "/followEntController/followListPage"
	});
	
	
});


/* 宣讲会list */
function xjhList(page,oper){
	$.ajax({
			 cache : false,
			 async : false,
			 url : r_path + '/entMeetingNoticeController/meetingList.do',
			 data : {
				page : page
			 },
			 success : function(data){
				var datas = eval('('+data+')');
				if(datas.success){
					$("#noMeet").hide();
					var html = "<ul id='meetingLists'>";
					var counts = datas.obj.counts;
					var count = parseInt(counts);
					var pages = parseInt($("#meetingPage").val());
					if((count - (pages * 3)) >= 0){
						$("#moreXjh").show();
					}else{
						$("#moreXjh").hide();
					}
					$("#xjhCount").html("直播点播（"+datas.obj.counts+"）");
					$("#xjhCountValue").val(datas.obj.counts);
					for(var i = 0; i < datas.obj.list.length; i++){
						var name = datas.obj.list[i].subject;
						var detail = datas.obj.list[i].memo;
						var detail2 = datas.obj.list[i].memo;
						if(detail != null && detail != ''){
						if(detail.length > 29){
							detail = detail.substring(0,29) + "...";
						}
					}
						var createTime = datas.obj.list[i].create_time;
						//var createTimeStr = formatDate(new   Date(createTime));
						var createTimeStr = getTimeByMillis(createTime);
						var showTime = getDateDiff(getDateTimeStamp(createTimeStr));
						var startTime = datas.obj.list[i].strat_time;
						var startTimeStr = startTime.substring(0,startTime.length-3);
//							startTimeStr = startTimeStr.substring(0,startTimeStr.length-3);
//							var startTimeStr = formatDate(new   Date(startTime));
//							startTimeStr = startTimeStr.substring(0,startTimeStr.length-3);
						var path = datas.obj.list[i].cover_image_path;
						var vedioPath = datas.obj.list[i].video_url;
						var sfgq = datas.obj.list[i].sfgq;
						var time = "开始时间";
						var vedioHtml = " ";
						if(path == undefined) {
							path == "";
						}
						var type = datas.obj.list[i].type;
						var tag = "";
						if(vedioPath != null && vedioPath != ''){
							if(type == "3"){
								time = "拍摄时间";
								tag = "点播";
							}
							vedioHtml = "<a href='javascript:void(0)' data-tags='"+name+"' class='play_video' data-src='"+vedioPath+"'>"+
											"<img src='" + r_path + "/static/web/university/images/play.png' width='45' height='45' title='播放'/>"+
										"</a>";
						}else{
							if(type == "1"){
								tag = "预告";
								if(sfgq != 1){
									tag = sfgq;
								}
							}else if(type == "2"){
								var status= datas.obj.list[i].status;
								if(status == "1"){
									tag = "直播中";
								}else{
									tag = "已直播";
								}
							}
						}
						html = html + "<li id='"+datas.obj.list[i].id+"'>"+
										"<div class='pc-lists'>"+
										"<div class='pc-title'><span>"+name+"</span></div>"+
										"<div class='pc-timer'><span>"+time+"</span><i>"+startTimeStr+"</i></div>"+
										"<div class='pc-video'>"+
											"<a href='javascript:void(0)' class='thum_video' title='"+name+"'>"+
												"<img src='"+path+"' alt=''/>"+
												"<span class='play_state'>"+tag+"</span>"+
											"</a>"+ vedioHtml +
										"</div>"+
										"<div class='pc-numInfo'>"+
											"<a href='javascript:void(0)' title='"+detail2+"'>"+detail+"</a>"+
										"</div>"+
										"<div class='pc-sendTime'>"+showTime+"</div>"+
									"</div>"+
									"<div class='eidt-preach'>"+
										"<a href='javascript:void(0)' data-tag='"+datas.obj.list[i].id+"' class='preach-btn pre-edit edit-xjh'><span class='pre-icon'></span>编辑</a>"+
										"<a href='javascript:void(0)' data-tags='"+datas.obj.list[i].id+"' class='preach-btn pre-delete del-xjh'><span class='pre-icon'></span>删除</a>"+
									"</div>"+
								"</li>";
					}
					html = html + "</ul>";
					
					if(page == 1){
						$("#meetCotent").empty();
					}
					//页面加1
				    var v = $("#meetingPage").val();
					var page = parseInt(v) + 1;
					$("#meetingPage").val(page);
					
					
					$("#meetCotent").append(html);
					$("#meetCotent").show();
					$("#meetList").show();
					
					/* 宣讲会编辑 */
					$(".edit-xjh").click(function(){
						var s = $(this);
						xjhDetail($(this));
					});
					/* 宣讲会删除 */
					$(".del-xjh").click(function(){
						 var id = $(this).data("tags"); 
						$.fn.fandialog({
							title:"删除信息",
							contents:"确定要删除该条信息吗？",
							click:function(ok){
								if(ok){
									 xjhDel(id); 
								}
							}
						});
					});
					//视频播放
					$("#meetingLists li").find(".play_video").click(function(){
						var $src = $(this).data("src");
						var vedioName =  $(this).data("tags");
						$("#vedioName").html(vedioName);
						if($src==undefined || $src ==""){
							alert("暂无播放视频");
						}else{
							$("#example_video_1").find("#example_video_1_html5_api").attr("src",$src);
							$("#example_video_1").find("source").attr("src",$src);
							$("#tzui-loading-overlay").show();
							$("#video-contents").show();	
							var myPlayer = videojs('example_video_1');
						    videojs("example_video_1").ready(function(){
						        var myPlayer = this;
						        myPlayer.play();
						    });
						}
					});
					
					$("#close-video").click(function(){
						$("#video-contents").hide();
						$("#tzui-loading-overlay").hide();
						var myPlayer = videojs('example_video_1');
					    videojs("example_video_1").ready(function(){
					        var myPlayer = this;
					        myPlayer.pause();
					    });
					});
				}else{
					if(oper != 'more'){
						$("#noMeet").show();
					}
				}
				 
			 }
		 });
}

/* 宣讲会详情 */
function xjhDetail(obj){
	var id = obj.data("tag");
	$.ajax({                
		cache: false,    
		async: false, 
		type: "POST",                
		url:  r_path +  '/entMeetingNoticeController/edit.do',                
		data:{
			meetingId : id
		},              
		error: function(request) {                    
		},                
		success: function(data) {
			var datas = eval('('+data+')');
			if(datas.success){
				if(datas.obj.type == "1"){
					openXjhYG(datas);
				}else if(datas.obj.type == "3"){
					openXjhDb(datas);
				}else if(datas.obj.type == "2"){
					alert("直播数据不能维护");
				}
			}
		}            
	});
}

/*打开宣讲会   预告   编辑框*/
function openXjhYG(datas){
	$("#tzui-loading-overlay").show();
	$("#edit-preacp-contents").show();
	$("#xjhIdEdit").val(datas.obj.id);
	$("#subject-edit").val(datas.obj.subject.trim());
	$("#detailXjhEdit").val(datas.obj.memo);
	var startTimeId = $("#start_time_xjhEditStart");
		evaluation(datas.obj.strat_time,startTimeId,"start");
	if(datas.obj.type == "1"){
		var endTimeId = $("#end_time_xjhEditEnd");
		evaluation(datas.obj.end_time,endTimeId,"end");
	}
	if(datas.obj.cover_image_path != undefined){
		$("#xjhAnnexIdEdit").val(datas.obj.cover_image_path);
		$("#xhjImgEdit").show();
		$("#xhjImgEdit").empty();
		var html = "<a href='javascript:void(0)'><img src='"+ datas.obj.cover_image_path +"' width='78' height='78' /></a>"+
					"<a href='javascript:void(0)' onclick='delXjhImg();' class='delete-imgCover'></a>";
		$("#xhjImgEdit").append(html);
	}
}

/*宣讲会编辑保存*/
function xjhEditSave(){
	var subject = $("#subject-edit").val();
	var memo = $("#detailXjhEdit").val();
	var url = $("#xjhAnnexIdEdit").val();
	//时间
	var startTime = $("#start_time_xjhEditStart");
	var endTime = $("#end_time_xjhEditEnd");
	var st = returnTimer(startTime,"start");
	var et = returnTimer(endTime,"end");
	if(compareTimes(st,et)){
		$("#xjhEditStratTimeStr").val(st);
		$("#xjhEditEndTimeStr").val(et);
	}else{
		alert("结束时间必须大于开始时间");
		return;
	}
	if(subject.length == 0){
		alert("请填写名称");
		return;
	}else if(subject.length > 30){
		alert("名称字数不能超过30个汉字");
		return;
	}
	if(url.length == 0){
		alert("请上传封面图");
		return;
	}
	if(memo.length > 2000){
		alert("介绍字数不能超过2000个汉字");
		return;
	}
	$.ajax({                
		cache: true,                
		type: "POST",                
		url:  r_path + '/entMeetingNoticeController/add.do',                
		data:$('#xjhEidtForm').serialize(),// 你的formid                
		async: false,                
		error: function(request) {                    
		},                
		success: function(data) {
			document.xjhEidtForm.reset();
			$("#tzui-loading-overlay").hide();
			$("#edit-preacp-contents").hide();
			$("#meetingPage").val("1");
			$("#meetCotent").empty();
			xjhList(1,1);
		}            
	});
}

/*打开宣讲会  点播  编辑框*/
function openXjhDb(datas){
	$("#upHistoryVedioTrrigerEdit").hide();
	$("#tzui-loading-overlay").show();
	$("#historyXjhEdit").show();
	$("#historyXjhId").val(datas.obj.id);
	$("#history-subject-edit").val(datas.obj.subject.trim());
	$("#history-memo-edit").val(datas.obj.memo);
	var video_url = datas.obj.video_url;
	$("#upHistoryVedioUrlEdit").val(video_url);
	var fileName = video_url.substring(video_url.length-6,video_url.length);
	$("#fileNameEidt").html(fileName);
	$("#fileNameEidt").attr("title",fileName);
	$("#uploadVedioSuccessEdit").show();
	var startTimeId = $("#start_time_history_edit");
		evaluation(datas.obj.strat_time,startTimeId,"start");
	if(datas.obj.cover_image_path != undefined){
		$("#historyImgUrlEidt").val(datas.obj.cover_image_path);
		$("#historyImgEidt").show();
		$("#historyImgEidt").empty();
		var html = "<a href='javascript:void(0)'><img src='"+ datas.obj.cover_image_path +"' width='78' height='78' /></a>"+
					"<a href='javascript:void(0)' onclick='delXjhHisImg();' class='delete-imgCover'></a>";
		$("#historyImgEidt").append(html);
	}
}
/*删除历史宣讲会封面图片*/
function delXjhHisImg(){
	$("#historyImgEidt").empty();
	$("#historyImgUrlEidt").val("");
	$("#historyImgEidt").hide();
}
/*编辑保存  点播*/
function historyVedioEditSave(){
	var subject = $("#history-subject-edit").val();
	var memo = $("#history-memo-edit").val();
	var urlVedio = $("#upHistoryVedioUrlEdit").val();
	var urlImg = $("#historyImgUrlEidt").val();
	//时间
	var startTime = $("#start_time_history_edit");
	var st = returnTimer(startTime,"start");
	$("#history-startTimeEdit").val(st);
	if(subject.length == 0){
		alert("请填写视频名称");
		return;
	}else if(subject.length > 30){
		alert("视频名称字数不能超过30个汉字");
		return;
	}
	if(urlVedio.length == 0){
		alert("请上传视频");
		return;
	}
	if(urlImg.length == 0){
		alert("请上传封面图");
		return;
	}
	if(memo.length > 2000){
		alert("视频介绍字数不能超过2000个汉字");
		return;
	}
	//以往视频保存
	$.ajax({                
		cache: true,                
		type: "POST",                
		url:  r_path + '/entMeetingNoticeController/add.do',                
		data:$('#historyEidtForm').serialize(),// 你的formid                
		async: false,                
		error: function(request) {                    
		},                
		success: function(data) {
			document.historyEidtForm.reset();
			$("#tzui-loading-overlay").hide();
			$("#historyXjhEdit").hide();
			$("#meetingPage").val("1");
			$("#meetCotent").empty();
			xjhList(1,1);
		}            
	});
}
/* 宣讲会   删除 */
function xjhDel(id){
	$.ajax({                
		cache: false,    
		async: false, 
		type: "POST",                
		url:  r_path + '/entMeetingNoticeController/del.do',                
		data:{
			meetingId : id
		},              
		error: function(request) {                    
		},                
		success: function(data) {
			var datas = eval('('+data+')');
			if(datas.success){
				$("#xjhCount").html("直播点播（"+datas.obj+"）");
			}else{
				$("#xjhCount").html("直播点播（0）");
			}
			
			var xjhCountValue = $("#xjhCountValue").val();
			var v = parseInt(xjhCountValue) - 1;
			$("#xjhCountValue").val(v);
			$("#meetingPage").val("1");
			if(v == 0){
				$("#noMeet").show();
			}
			$("#meetCotent").empty();
			xjhList(1,1);
			
			//$("#"+id).remove();
		}            
	});
}

//统计职位数量
function getCountPosition(oper){
	 $.ajax({
			cache : false,
			async : false,
			type : 'POST',
			url : r_path + '/postionCount/countPostionByEndtime.do',
			data : {
				oper : oper
			},
			success : function (data){
				var datas =  eval('(' + data + ')'); 
				if(datas.success){
					$("#zwCount").html("校招岗位（"+datas.obj.zero+"）");
				}
			}
		});
}

/* 时间格式化  2009-02-20 13:28:29 -- > 2009年02月30日。。。。。。*/
function evaluation(time,$id,$mark){
	var $timearr=time.replace(" ",":").replace(/\:/g,"-").split("-");
	$id.find("."+$mark+"Year").html($timearr[0]+"年");
	$id.find("."+$mark+"Mouth").html($timearr[1]+"月");
	$id.find("."+$mark+"Data").html($timearr[2]+"日");
	$id.find("."+$mark+"Hour").html($timearr[3]+"时");
	$id.find("."+$mark+"Minutes").html($timearr[4]+"分");
}

//获取时间
function returnTimer($id,$mark){
	var startYear = parseInt($id.find("."+$mark+"Year").html());
	var startMouth = parseInt($id.find("."+$mark+"Mouth").html());
	var startData = parseInt($id.find("."+$mark+"Data").html());
	var startHour = parseInt($id.find("."+$mark+"Hour").html());
	var startMinutes = parseInt($id.find("."+$mark+"Minutes").html());
	
	startMouth = startMouth>=10? startMouth: "0"+startMouth;
	startData = startData>=10? startData: "0"+startData;
	startHour = startHour>=10? startHour: "0"+startHour;
	startMinutes = startMinutes>=10? startMinutes: "0"+startMinutes;
	var stratTime = startYear + "-" + startMouth + "-"+startData+" "+startHour+":"+startMinutes+":00";
	return stratTime;
	
}

	
	
	/*关闭认证提示*/
	function closeAtt(){
		$("#certificate-tip").hide();
	}
	
	
	/*更换省，并获取该省下的区县*/
	function changePro(obj){
		var code = $(obj).val();
		city(code);
	}
	
	/*加载市*/
	function city(val){
		/*4个直辖市 香港 台湾 澳门  还是选择自己*/
		/*北、上、重、天、香港、澳门、台湾*/
		$("#sa-city").empty();
		$("#sa-city-selected").html("请选择");
		$("#sa-city2").empty();
		$("#sa-city-selected2").html("请选择");
		/*if(val == '530' || val == '538' ||val == '551' ||val == '531' */
		if(val == '561' || val == '562' || val == '563'){
			var value = "";
			switch(val){
				case "561" : value = "香港"; break;
				case "562" : value = "澳门"; break;
				case "563" : value = "台湾省"; break;
			}
			$("#sa-city").append("<option value=''>请选择</option><option value='"+val+"'>"+value+"</option>");
			$("#sa-city2").append("<option value=''>请选择</option><option value='"+val+"'>"+value+"</option>");
		}else{
			var html = getHtml(val);
			$("#sa-city").append(html);
			$("#sa-city2").append(html);
		}
	}
	
	/*获取拼接的option*/
	function getHtml(code){
		var html = "";
		var tName = " comm_location ";
		var params = " parent_code = '"+ code +"' or location_code = '"+ code +"'";
		var citys = commonList(tName,params);
		if(isNotEmpty(citys)){
			var data = eval('('+citys+')');
			var name = "";
			var code = "";
			for(var i = 1; i < data.obj.length; i++){
				name = data.obj[i].location_name;
				code = data.obj[i].location_code;
				html = html + "<option value='"+code+"'>"+name+"</option>"
			}
		}
		html = "<option value=''>请选择</option>" + html;
		return html;
	}
	
	/* 获取最新的8个关注 */
	function getFollowList(num){
		$.ajax({                
			cache: true,                
			type: "POST",                
			url:  r_path + '/followEntController/getFollowList.do',   
			data: {
				num : num
			},
			async: true,                
			error: function(request) {                    
			},                
			success: function(data) {
				var datas = eval('('+data+')');
				 
				if(datas.success){
					var html = "";
					for(var i = 0; i < datas.obj.vList.length; i++){
						var headUrl = datas.obj.vList[i].head_url;
						var uId = datas.obj.vList[i].uId;
						var nicl_name = datas.obj.vList[i].nick_name
						if(!isNotEmpty(headUrl)){
							headUrl = r_path + "/static/web/university/images/moren_guanzhu.jpg";
						}
						html = html + "<dl class='showResumeDetail'>" +
										"<input type='hidden' value='"+uId+"'/>"+
										"<dt title='"+nicl_name+"'><a href='javascript:void(0)' ><img src='"+headUrl+"' width='58' height='58'></a></dt>"+
										"<dd title='"+nicl_name+"'><a href='javascript:void(0)' >"+nicl_name+"</a></dd>"+
									  "</dl>";
					}
					$("#noFollow").hide();
					$("#follow").empty();
					$("#follow").show();
					$("#follow").append(html);
					
					//增加点击事件
					$(".showResumeDetail").click(function(){
						var uId = $(this).find("input").val();
						if(isNotEmpty(uId)){
							showResumeDetail(uId);
						}
					});
					
				}
			}            
		});
	}
	
	//显示简历详情
	function showResumeDetail(uId){
		$.ajax({                
			cache: false,                
			type: "POST",                
			url:  r_path + '/resume/checkResume',   
			data:{
				uId : uId
			},
			async: false,               
			error: function(request) {                    
			},
			success: function(data) {
				var datas = eval('('+data+')');
				if(datas.success){
					var resumeId = datas.obj;
					var url = r_path + "/resume/detail?oper=index&resumeId="+resumeId+"&userId="+uId;
					//window.location.href= r_path + "/resumeDetail/init?oper=index&resumeId="+resumeId+"&userId="+uId;
					window.open(url,'1','width=1000,height=600,left=10, top=10,toolbar=no, status=no, menubar=no, resizable=yes, scrollbars=yes');
				}else{
					alert("暂未创建简历");
				}
			}            
		});
	}

	/* 获取所有的关注 */
	function getFollowLists(num,type,page){
		var rows = parseInt(num);
		var pages = parseInt(page);
		$.ajax({                
			cache: true,                
			type: "POST",                
			url:  r_path + '/followEntController/getFollowList.do',   
			data: {
				num : num,
				page : page
			},
			async: true,                
			error: function(request) {                    
			},
			beforeSend: function(){
				$("#moreXjh").hide();
				$("#moreXjhIng").show();
			},
			success: function(data) {
				
			},
			complete:function(data){
				$("#moreXjhIng").hide();
				
				var datas = eval('('+data.responseText+')');
				console.log(datas);
				if(datas.success){
					var html = "";
					var counts = parseInt(datas.obj.counts);
					var showFlag = (rows * pages) - counts;
					if(datas.obj.length == 0 || showFlag >= 0){
						$("#moreXjh").hide();
					}else{
						$("#moreXjh").show();
					}
					for(var i = 0; i < datas.obj.vList.length; i++){
						var headUrl = datas.obj.vList[i].head_url;
						var uId = datas.obj.vList[i].uId;
						if(!isNotEmpty(headUrl)){
							headUrl = r_path + "/images/moren_fans.jpg";
						}
						var uId = datas.obj.vList[i].uId;
						var name = datas.obj.vList[i].nick_name;
						var educationName = datas.obj.vList[i].educationName;
						var sf = "学生（" + educationName +"）";
						var school = datas.obj.vList[i].schoolName;
						var zhuanye = datas.obj.vList[i].zhuanyeName;
						var area = datas.obj.vList[i].areaName;
						html = html +  "<div class='grid' data-tag='"+uId+"'>"+
											"<div class='picture'>"+
												"<a href='javascript:void(0)' onclick='showResumeDetail('"+uId+"');' class='showinfos' data-tag='"+uId+"'><img src='"+headUrl+"' width='180' height='180' alt='"+name+"' title=''></a>"+
											"</div>"+
											"<div class='pic-info'>"+
												"<a  href='javascript:void(0)' class='title showinfos' data-tag='"+uId+"'>"+name+"</a>"+
												"<p class='other-txt'>"+sf+"</p>"+
												"<p class='other-txt'>"+school+"</p>"+
												"<p class='other-txt'>"+zhuanye+"</p>"+
												"<p class='other-txt'>"+area+"</p>"+
											"</div>"+
										"</div>";
					}
					if(type != 'more'){
						$("#followlists").empty();
					}else{
						//设置页数
						var page = $("#followlistsPage").val();
						page = parseInt(page);
						page = page + 1;
						$("#followlistsPage").val(page);
					}
					$("#followlists").append(html);
					/*$('#followlists').BlocksIt({
						numOfCol: 6,
						offsetX: 8,
						offsetY: 8,
						blockElement: '.grid'
					});*/
					$(".showinfos").off().on("click",function(){
						var uId = $(this).data("tag");
						showResumeDetail(uId);
					});
					
					//数据加载中 恢复默认
					$("#opering").val("0");
				}
			
			}            
		});
	}
	
	/* 获取企业关注数 */
	function getFollowCount(){
		var followCount;
		$.ajax({                
			cache: false,                
			type: "POST",                
			url:  r_path + '/followEntController/getFollowCounts.do',                
			async: false,                
			error: function(request) {                    
			},                
			success: function(data) {
				var datas = eval('('+data+')');
				if(datas.success){
					followCount = datas.obj;
				}
			}
		});
		return followCount;
	}
