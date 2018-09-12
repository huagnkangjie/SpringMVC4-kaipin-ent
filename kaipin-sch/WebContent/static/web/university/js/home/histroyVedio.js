	

	$(function(){
		
		//宣讲会历史维护编辑关闭按钮
		$("#close-meetingss").on("click",function(){
			$("#tzui-loading-overlay").hide();
			$("#historyXjhEdit").hide();
		});
		
		/* 保存宣讲会  */
		$("#xjh").click(function (){
			var themeName = $("#subject").val();
			if(themeName.length == 0){
				alert("请填写名称！");
				return;
			}else if(themeName.length > 30){
				alert("名称字数不能超过30个汉字");
				return;
			}
			
			var startTime = $("#start_time_1");
			var endTime = $("#end_time_1");
			var st = returnTimer(startTime,"start");
			var et = returnTimer(endTime,"end");
			if(compareTimes(st,et)){
				$("#stratTime").val(st);
				$("#endTime").val(et);
			}else{
				alert("结束时间必须大于开始时间");
				return;
			}
			
			var xjhAnnexId = $("#xjhAnnexId").val();
			if(xjhAnnexId.length == 0){
				alert("请上传封面图片！");
				return;
			}
			var detailXjh = $("#detailXjh").val();
			if(detailXjh.length > 2000){
				alert("介绍字数不能超过2000个汉字");
				return;
			}
			
			
			$.ajax({                
				cache: true,                
				type: "POST",                
				url:  r_path + '/vedio/add',                
				data:$('#xjhForm').serialize(),// 你的formid                
				async: false,                
				error: function(request) {                    
				},                
				success: function(data) {
					document.xjhForm.reset();
					$("#tzui-loading-overlay").hide();
					$(".preacp-and-oldvideo").hide();
					$("#meetCotent").empty();
					$("#meetingPage").val("1");
					$("#xjhId").val("");
					xjhList(1,1);
				}            
			});
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
			if(upHistoryVedioUrl.length == 0){
				alert("请上传视频");
				return;
			}
			if(historyImgUrl.length == 0){
				alert("请上传视频封面图");
				return;
			}
			if(history_subject.length == 0){
				alert("请填写视频名称");
				return;
			}else if(history_subject.length > 30){
				alert("视频名称字数不能超过30个汉字");
				return;
			}
			
			if(history_memo.length > 2000){
				alert("视频介绍字数超过2000");
				return;
			}
			
			//以往视频保存
			$.ajax({                
				cache: true,                
				type: "POST",                
				url:  r_path + '/vedio/add',                
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
//					xjhList(1,1);
					location.href=r_path + "/home";
				}            
			});
			
		});
		
		
		
	});
	
	
	/* 宣讲会list */
	function xjhList(page,oper){
		$.ajax({
				 cache : false,
				 async : false,
				 url : r_path + '/vedio/meetingList',
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
						$("#xjhCount").html("视频 "+datas.obj.counts+" 个");
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
//								startTimeStr = startTimeStr.substring(0,startTimeStr.length-3);
//								var startTimeStr = formatDate(new   Date(startTime));
//								startTimeStr = startTimeStr.substring(0,startTimeStr.length-3);
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
			url:  r_path +  '/vedio/edit',                
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
	
	/* 宣讲会   删除 */
	function xjhDel(id){
		$.ajax({                
			cache: false,    
			async: false, 
			type: "POST",                
			url:  r_path + '/vedio/del',                
			data:{
				meetingId : id
			},              
			error: function(request) {                    
			},                
			success: function(data) {
				var datas = eval('('+data+')');
				if(datas.success){
					$("#xjhCount").html("视频（"+datas.obj+"）");
				}else{
					$("#xjhCount").html("视频（0）");
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
			url:  r_path + '/vedio/add.do',                
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
	
	/*编辑 删除图片*/
	function delXjhHisImg(){
		$("#historyImgEidt").hide();
		$("#historyImgUrlEidt").val("");
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
			url:  r_path + '/vedio/add.do',                
			data:$('#historyEidtForm').serialize(),// 你的formid                
			async: false,                
			error: function(request) {                    
			},                
			success: function(data) {
				//document.historyEidtForm.reset();
				$("#tzui-loading-overlay").hide();
				$("#historyXjhEdit").hide();
				$("#meetingPage").val("1");
				$("#meetCotent").empty();
				xjhList(1,1);
			}            
		});
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
	
	/* 时间格式化  2009-02-20 13:28:29 -- > 2009年02月30日。。。。。。*/
	function evaluation(time,$id,$mark){
		var $timearr=time.replace(" ",":").replace(/\:/g,"-").split("-");
		$id.find("."+$mark+"Year").html($timearr[0]+"年");
		$id.find("."+$mark+"Mouth").html($timearr[1]+"月");
		$id.find("."+$mark+"Data").html($timearr[2]+"日");
		$id.find("."+$mark+"Hour").html($timearr[3]+"时");
		$id.find("."+$mark+"Minutes").html($timearr[4]+"分");
	}
		
		