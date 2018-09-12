

	$(function(){
		$(".search-switch").on("click","a",function(){
			$(this).parents(".search-switch").find("a").removeClass("selection");
			$(this).addClass("selection");
			var page = "1";
			var pageSize = $("#searchPageSize").val();
			var searchType = $(this).data("tag");
			var searchTxt = $("#search-text").val();
			$("#search-result-list").empty();
			$("#searchPage").val(page);
			$("#searchCount").html(0);
			$("#searchMoreType").val(searchType);
			search(/*搜索类型*/searchType, /*搜索内容*/searchTxt, page , pageSize);
			
		});
	});


	/*搜索*/
	function search(/*搜索类型*/searchType, /*搜索内容*/searchTxt, page , pageSize){
		debugger;
		$("#moreSearch").show();
		$.ajax({                
			cache: false,    
			async: false, 
			type: "POST",                
			url:  r_path + '/search/result',                
			data:{
				searchType : searchType,
				searchTxt : searchTxt,
				page : page,
				pageSize : pageSize
			},              
			error: function(request) {                    
			},                
			success: function(data){
				var datas = eval('('+data+')');
				var data = datas.obj;
				if(data != null && data != ''){
					data = eval('('+data+')');
				}
				
				var searchCount = $("#searchCount").html();
				
				var size = data.data.length;
				searchCount = parseInt(searchCount) + size;
				$("#searchCount").html(searchCount);
				
				switch(searchType)
				{
					case "sch":  renderingSch(data); break;
					case "ent":  renderingEnt(data); break;
					case "stu":  renderingStu(data); break;
					case "position":  renderingPosition(data); break;
					case "live":  renderingLive(data); break;
					
				}
				$("#moreSearchIng").val(0);
				$("#moreSearch").hide();
			}
		});
	}
	
	/*遍历查询结果*/
	function eachSearchResult(/*查询返回结果*/result, /*查询类型*/searchType){
		var data = eval('('+result+')');
		
		switch(searchType)
		{
			case "sch":  renderingSch(data); break;
			case "ent":  renderingEnt(data); break;
			case "stu":  renderingStu(data); break;
			case "position":  renderingPosition(data); break;
			case "live":  renderingLive(data); break;
			
		}
	}
	
	/*渲染	学校*/
	function renderingSch(data){
		var html = "";
		var school_id = "";
		var school_name = "";
		var school_feature_id = "";//院校特色
		var school_class_id = "";//院校类型
		var direction = "";//研究领域
		var logo = "";
		var length = data.data.length;
		if(length > 0){
			$("#searchCount").html(data.pagination.total);
			setPage();//设置页面
			for(var i = 0; i < length; i++){
				school_name = data.data[i].school_name;
				school_feature_id = data.data[i].school_feature_id;
				school_class_id = data.data[i].school_class_id;
				direction = subStringStr(data.data[i].direction,40);
				logo = getLogo(data.data[i].school_logo);
				school_id = data.data[i].id;
				
				var schoolClassHtml = "";
				if(school_class_id.length > 0){
					schoolClassHtml = "<span class='basic-info weak'>院校类型 <span class='basic-info-detail'>"+school_class_id+"</span></span>";
				}
				var directionHtml = "";
				if(direction.length > 0){
					directionHtml = "<span class='basic-info'>研究领域 <span class='basic-info-detail'><span class='search-bright'>"+direction+"</span></span></span>";
				}
				
				
				html = html +   "<div class='search-row'>"+
				"					<div class='search-head-img-panle'>" +
				"						<a target='_blank' href='"+r_path+"/feeds/targetOrg?orgId="+school_id+"'>"+
				"							<img class='search-head-img' alt='' src='"+logo+"'>" +
				"						</a>"+
				"					</div>"+
				"					<div class='search-basic-info-panle'>"+
				"						<div>"+
				"							<span class='user-name'><span class='search-bright'>" +
				"							<a target='_blank' href='"+r_path+"/feeds/targetOrg?orgId="+school_id+"'>" +
				"								"+school_name+"</span></span>" +
				"							</a>"+
				"							<span class='user-keyword'>"+school_feature_id+"</span>"+
				"						</div>"+
				"						<div class='detail'>"+
				"							"+schoolClassHtml+
				"							"+directionHtml+
				"						</div>"+
				"					</div>"+
				"					<div class='search-detai-btn-panle'>"+
				"						<div class='send-meeting-btn fr'>"+
				"							<a target='_blank' href='"+r_path+"/feeds/targetOrg?orgId="+school_id+"' class='detail-btn search-btn'>查看详情</a>"+
				"						</div>"+
				"					</div>"+
				"				</div>";
			}
		}
			
		$("#search-result-list").append(html);	
	}
	
	
	/*渲染	企业*/
	function renderingEnt(data){
		
		var html = "";
		var company_id = "";
		var ent_name = "";
		var location = "";//区域
		var positionCount = "";//公司在招聘职位数
		var business_domain = "";//专注领域
		var logo = "";
		var length = data.data.length;
		if(length > 0){
			$("#searchCount").html(data.pagination.total);
			setPage();//设置页面
			for(var i = 0; i < length; i++){
				company_id = data.data[i].id;
				ent_name = data.data[i].name;
				location = data.data[i].city;
				positionCount = data.data[i].positionCount;
				business_domain = subStringStr(data.data[i].business_domain,40);
				logo = getLogo(data.data[i].logo);
				
				var bussinessHtml = "";
				if(business_domain.length != '无' && business_domain.length > 0){
					bussinessHtml = "<span class='basic-info'>专注领域 <span class='basic-info-detail'><span class='search-bright'>"+business_domain+"</span></span></span>";
				}
				 
				html = html +   "<div class='search-row'>"+
				"					<div class='search-head-img-panle'>" +
				"						<a target='_blank' href='"+r_path+"/feeds/targetOrg?orgId="+company_id+"'>"+
				"							<img class='search-head-img' alt='' src='"+logo+"'>" +
				"						</a>"+
				"					</div>"+
				"					<div class='search-basic-info-panle'>"+
				"						<div>"+
				"							<span class='user-name'><span class='search-bright'>" +
				"								<a target='_blank' href='"+r_path+"/feeds/targetOrg?orgId="+company_id+"'>" +
				"									"+ent_name+"</span></span>" +
				"								</a>"+
				"							<span class='user-keyword'>"+location+"</span>"+
				"						</div>"+
				"						<div class='detail'>"+
				"							" +bussinessHtml+
				"							<span class='basic-info'>正在招聘的职位 <span class='basic-info-detail'><span class='search-bright'>"+positionCount+"</span></span></span>"+
				"						</div>"+
				"					</div>"+
				"					<div class='search-detai-btn-panle'>"+
				"						<div class='send-meeting-btn fr'>"+
				"							<a target='_blank' href='"+r_path+"/feeds/targetOrg?orgId="+company_id+"' class='detail-btn search-btn'>查看详情</a>"+
				"						</div>"+
				"					</div>"+
				"				</div>";
			}
		}
			
		$("#search-result-list").append(html);	
	}
	
	/*渲染	学生*/
	function renderingStu(data){
		
		var html = "";
		var id = "";
		var name = "";
		var school_code = "";//学校名称
		var major_code = "";//专业
		var location_code = "";//地区
		var logo = "";
		var length = data.data.length;
		if(length > 0){
			$("#searchCount").html(data.pagination.total);
			setPage();//设置页面
			for(var i = 0; i < length; i++){
				id = data.data[i].id;
				name = data.data[i].name;
				school_code = data.data[i].school_code;
				major_code = data.data[i].major_code;
				location_code = data.data[i].location_code;
				logo = getLogo(data.data[i].head_url);
				
				
				html = html +   "<div class='search-row'>"+
				"					<div class='search-head-img-panle'>" +
				"						<a target='_blank' href='"+r_path+"/feeds/targetOrg?orgId="+id+"'>"+
				"							<img class='search-head-img' alt='' src='"+logo+"'>" +
				"						</a>"+
				"					</div>"+
				"					<div class='search-basic-info-panle'>"+
				"						<div>"+
				"							<span class='user-name'><span class='search-bright'>" +
				"								<a target='_blank' href='"+r_path+"/feeds/targetOrg?orgId="+id+"'>" +
				"									"+name+"</span></span>" +
				"								</a>"+
				"							<span class='user-keyword'>"+school_code+"</span>"+
				"						</div>"+
				"						<div class='detail'>"+
				"							<span class='basic-info'>就读专业 <span class='basic-info-detail'><span class='search-bright'>"+major_code+"</span></span></span>"+
				"							<span class='basic-info'>所在地区 <span class='basic-info-detail'><span class='search-bright'>"+location_code+"</span></span></span>"+
				"						</div>"+
				"					</div>"+
				"					<div class='search-detai-btn-panle'>"+
				"						<div class='send-meeting-btn fr'>"+
				"							<a target='_blank' href='"+r_path+"/feeds/targetOrg?orgId="+id+"' class='detail-btn search-btn'>查看详情</a>"+
				"						</div>"+
				"					</div>"+
				"				</div>";
			}
		}
			
		$("#search-result-list").append(html);	
	}
	
	
	/*渲染	职位*/
	function renderingPosition(data){
		
		var html = "";
		var id = "";
		var company_id = "";
		var name = "";
		var company_name = "";//企业名称
		var positionCount = "";//招聘职位数
		var location_code = "";//地区
		var logo = "";
		var length = data.data.length;
		if(length > 0){
			$("#searchCount").html(data.pagination.total);
			setPage();//设置页面
			for(var i = 0; i < length; i++){
				id = data.data[i].id;
				name = data.data[i].name;
				company_id = data.data[i].company_id;
				company_name = data.data[i].company_name;
				positionCount = data.data[i].positionCount;
				location_code = data.data[i].city;
				logo = getLogo(data.data[i].logo);
				
				
				html = html +   "<div class='search-row'>"+
				"					<div class='search-head-img-panle'>" +
				"						<a target='_blank' href='"+r_path+"/feeds/targetOrg?orgId="+company_id+"'>"+
				"							<img class='search-head-img' alt='' src='"+logo+"'>" +
				"						</a>"+
				"					</div>"+
				"					<div class='search-basic-info-panle'>"+
				"						<div>"+
				"							<span class='user-name'><span class='search-bright'>" +
				"								<a target='_blank' href='"+r_path+"/position/detail?positionId="+id+"&org_id="+company_id+"'>" +
				"									"+name+"</span></span>" +
				"								</a>"+
				"							<span class='user-keyword'>" +
				"								<a target='_blank' href='"+r_path+"/feeds/targetOrg?orgId="+company_id+"'>" +
				"									"+company_name+"</span>" +
				"								</a>"+
				"						</div>"+
				"						<div class='detail'>"+
				"							<span class='basic-info'>工作地区 <span class='basic-info-detail'><span class='search-bright'>"+location_code+"</span></span></span>"+
				"							<span class='basic-info'>正在招聘职位 <span class='basic-info-detail'><span class='search-bright'>"+positionCount+"</span></span></span>"+
				"						</div>"+
				"					</div>"+
				"					<div class='search-detai-btn-panle'>"+
				"						<div class='send-meeting-btn fr'>"+
				"							<a target='_blank' href='"+r_path+"/position/detail?positionId="+id+"&org_id="+company_id+"' class='detail-btn search-btn'>查看详情</a>"+
				"						</div>"+
				"					</div>"+
				"				</div>";
			}
		}
			
		$("#search-result-list").append(html);	
	}
	
	
	/*渲染	视频*/
	function renderingLive(data){
		
		var html = "";
		var id = "";
		var name = "";
		var cover_image_path = "";//封面
		var memo = "";//备注
		var statr_time="";//上传时间
		var length = data.data.length;
		var vedio_url = "";
		var orgId = "";
		if(length > 0){
			$("#searchCount").html(data.pagination.total);
			setPage();//设置页面
			for(var i = 0; i < length; i++){
				id = data.data[i].id;
				name = data.data[i].name;
				cover_image_path = data.data[i].cover_image_path;
				memo = subStringStr(data.data[i].memo, 40);
				statr_time = data.data[i].start_time;
				vedio_url = data.data[i].vedio_url;
				orgId = data.data[i].company_id;
				
				
				html = html +   "<div class='search-row'>"+
				"					<div class='search-head-img-panle'>" +
				"						<a target='_blank' href='"+r_path+"/feeds/targetOrg?orgId="+orgId+"'>"+
				"							<img class='search-head-img' alt='' src='"+cover_image_path+"'>" +
				"						</a>"+
				"					</div>"+
				"					<div class='search-basic-info-panle'>"+
				"						<div>"+
				"							<span class='user-name'><span class='search-bright video-player'>" +
				"								<a href='javascript:void(0);' data-tags='"+name+"' data-src='"+vedio_url+"' class='detail-btn search-btn'>" +
				"									"+name+"</span></span>" +
				"								</a>"+
				"						</div>"+
				"						<div class='detail'>"+
				"							<span class='basic-info'>上传时间 <span class='basic-info-detail'><span class='search-bright'>"+statr_time+"</span></span></span>"+
				"							<span class='basic-info'>视频详情 <span class='basic-info-detail'><span class='search-bright'>"+memo+"</span></span></span>"+
				"						</div>"+
				"					</div>"+
				"					<div class='search-detai-btn-panle video-player'>"+
				"						<div class='send-meeting-btn fr'>"+
				"							<a href='javascript:void(0);' data-tags='"+name+"' data-src='"+vedio_url+"' class='detail-btn search-btn'>查看详情</a>"+
				"						</div>"+
				"					</div>"+
				"				</div>";
			}
		}
		
			
		$("#search-result-list").append(html);	
		
		
		//视频播放
		$(".video-player").find(".detail-btn").click(function(){
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
	}

	
	function setPage(){
		var page = $("#searchPage").val();
		page = parseInt(page) + 1;
		$("#searchPage").val(page);
	}
	
	function subStringStr(obj,index){
		if(obj.length >0 && obj.length > index){
			obj = obj.substring(0,index) + "...";
		}
		return obj;
	}
	