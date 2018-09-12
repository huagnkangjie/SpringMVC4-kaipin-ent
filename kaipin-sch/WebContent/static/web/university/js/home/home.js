/*
 *
 *主页调用js
 
*/

(function($){
	 
})(jQuery);

	/*获取信息流*/
	function getFeeds(/*页码*/page, /*查询类型*/oper, /*组织id*/org_id){
		setTimeout(function(){
			$.ajax({                
				cache: false,    
				async: true, 
				type: "POST",  
				timeout : 1000 * 15,
				url:  r_path + '/feeds/getFeeds',                
				data:{
					page : page,
					oper : oper,
					org_id : org_id
				},              
				error: function(request) { 
				},
				beforeSend : function(request){
					if(page != 1){
						$("#feedMoreInit").hide();
						$("#feedMoreIng").show();	
					}
					$("#feedMore").hide();
					$("#feedMoreIngVal").val("1");
				},
				success: function(data) {
				},
				complete: function(data) { 
					var dataStr = data.responseText;
					if(dataStr == '') return ;
					var datas = eval('('+dataStr+')');
					var f_type;
					var dataFeed;
					$("#feedMoreIngVal").val("0");//状态0表示可查询
					for(var i = 0; i < datas.obj.feed.length; i++){
						dataFeed = datas.obj.feed[i];
						f_type = datas.obj.feed[i].f_act_type;
						if(f_type == '120' || f_type == '150'){//视频
							renderingVideo(dataFeed);
						}else if(f_type == '130'){//职位
							renderingPosition(dataFeed);
						}else if(f_type == '100'){//宣讲会
							renderingMeeting(dataFeed);
						}
					}
					var page = parseInt($("#feedPage").val());
					var count = parseInt(datas.obj.count);
					$("#feedMoreInit").hide();
					$("#feedMoreIng").hide();
					if((page * 20) < count){
						$("#feedMore").show();
					}else{
						$("#feedMore").hide();
					}
					
					
				}
			});
		},1000);
	}
	
	$(function(){
		
		/*加载更多*/ 
		$("#feedMore").click(function(){
			var page = parseInt($("#feedPage").val()) + 1;
			$("#feedPage").val(page)
			var operIng = $("#feedMoreIngVal").val();
			if(operIng == '0'){//状态0表示可查询
				$("#feedMoreIngVal").val("1");
				getFeeds(page);
			}
			
		});
		
		/*加载更多  资料页面 查询id公司的所有动态*/ 
		$("#feedMoreBasic").click(function(){
			var page = parseInt($("#feedPage").val()) + 1;
			$("#feedPage").val(page)
			var operIng = $("#feedMoreIngVal").val();
			if(operIng == 0){//状态0表示可查询
				$("#feedMoreIngVal").val("1");
				var companyId = $("#companyIdBasic").val();
				getFeeds(page, "getFeedByOrgId", companyId);
			}
			
		});
		
		//换一个推荐
		$("#pushList").on("click",".changePush",function(){
			var parents = $(this).parents("ul");
			var length = parents.find("li").length;
			var index = $(this).parents("li").index();
			var type = $(this).data("type");
			var id = $(this).data("id");
			var ids = "";
			var $ul = $(this).parents("ul");
			var ulLength = $ul.find("li").length;
			for(var i = 0; i < ulLength; i++){
				ids = ids + "," + $ul.find("li").eq(i).find(".changePush").data("id");
			}
			var $li =  $ul.find("li").eq(index);
			
			ids = ids.substring(1, ids.length);
			getPushListChange(id,ids, type, $li);
		});
		
		
		//关注
		$("#pushList").on("click",".follow",function(){
			var index = $(this).parents("li").index();
			var type = $(this).parents(".col-right").find(".changePush").data("type");
			var $ul = $(this).parents("ul");
			var orgId = $ul.find("li").eq(index).find(".changePush").data("id");
			var $li =  $ul.find("li").eq(index);
			$(this).removeClass("follow");
			var $_this = $(this);
			insertFollow(orgId, type, $_this);
			setTimeout(function(){
				//自动换下一个
				var id = orgId;
				var ulLength = $ul.find("li").length;
				var ids = "";
				for(var i = 0; i < ulLength; i++){
					ids = ids + "," + $ul.find("li").eq(i).find(".changePush").data("id");
				}
				
				ids = ids.substring(1, ids.length);
				getPushListChange(id,ids, type, $li);
				
			},1000);
		});
		
		
		//点击组织名称跳转
		$("#messages").on("click",".orgNameTarget",function(){
			$(this).parents(".hdPic-tipsInfo").find(".orgNameTargetA").trigger("click");
		});
		
		
		//隐藏资质认证提示
		$("#close-att").click(function(){
			$("#certificate-tip").hide();
		});
		
		
		//初始化评论删除	 一级
		$("#messages").on("click",".delCommentLevelOne",function(){
			var commentId = $(this).data("ids");
			var feedId = $(this).parents(".videoComment-panel").data("tag");
			var $_this =  $(this);
			$.fn.fandialog({
				title:"删除信息",
				contents:"确定要删除该条信息吗？",
				click:function(ok){
					if(ok){
						$_this.parents(".commentLeveOnePanel").hide();
						delComment(commentId, feedId, $_this);
						
					}
				}
			});
			
		});
		
		//初始化评论删除 	二级
		$("#messages").on("click",".delCommentLevelTwo",function(){
			var commentId = $(this).data("ids");
			var feedId = $(this).parents(".videoComment-panel").data("tag");
			var $_this =  $(this);
			$.fn.fandialog({
				title:"删除信息",
				contents:"确定要删除该条信息吗？",
				click:function(ok){
					if(ok){
						$_this.parents(".commentLevelTwoPanel").hide();
						delComment(commentId, feedId, $_this);
					}
				}
			});
		});
		
		
	});
	
	/*渲染职位*/
	function renderingPosition(data){
		var html = "";
		var logo = data.f_logo;
		var org_name = data.org_name;
		var create_time = data.create_time;
		var createTimeStr = getTimeByMillis(create_time);
		var showTime = getDateDiff(getDateTimeStamp(createTimeStr));
		var f_title =data.f_title;
		var f_describe =data.f_describe;
		var f_url =data.f_url;
		var f_img =data.f_img;
		var f_url =data.f_url;
		var digg_count =data.digg_count;
		var sub_count =data.sub_count;
		var comment_count =data.comment_count;
		var f_resource_id = data.f_resource_id;//职位id
		var f_org_id = data.f_org_id;//动态产生的组织id
		
		var id = data.f_id;
		if(logo == '0'){
			logo = r_path + "/static/web/university/images/default-hdPic.jpg";
		}
		$('#messages').append("<!--正在招聘列表 start-->"+
				"						<div class='lookingFor-position'>"+
				"							<div class='default-penel positionInfos'>"+
				"								<div class='defaultPic fl'>" +
				"									<a target='_blank' href='"+r_path+"/feeds/targetOrg?orgId="+f_org_id+"'>"+
				"										<img src='"+logo+"' width='50' height='50' alt='logo'>" +
				"									</a>"+
				"								</div>"+
				"								"+
				"								<div class='hdPic-tipsInfo fl'>"+
				"<h4><span class='orgNameTarget' style='cursor:pointer;'>"+org_name+"</span><span class='date'>"+showTime+"</span></h4>"+
				"<a target='_blank' href='"+r_path+"/feeds/targetOrg?orgId="+f_org_id+"' style='display:none;'>"+
				"	<span class='orgNameTargetA' >点击组织跳转</span>"+
				"</a>"+
				"<p>正在招聘：<a class='addBtn' target='_blank' href='"+r_path+"/position/detail?positionId="+f_resource_id+"&org_id="+f_org_id+"'>"
				+f_title+"</a></p>"+
				"<a target='_blank' href='"+r_path+"/position/plist?org_id="+f_org_id+"&org_name="+org_name+"' class='addBtn' title='查看更多在招职位'>查看更多在招职位 <span class='icon'></span></a>"+
				"								</div>"+
				"								<div class='clear'></div>"+
				"							</div>"+
				"						</div>"+
				"						<!--正在招聘列表 end-->");
	}
	
	function renderingMeeting(data){
		var html = "";
		var logo = data.f_logo;
		var org_name = data.org_name;
		var create_time = data.create_time;
		var createTimeStr = getTimeByMillis(create_time);
		var showTime = getDateDiff(getDateTimeStamp(createTimeStr));
		var f_title =data.f_title;
		var f_describe =data.f_describe;
		var f_url =data.f_url;
		var f_img =data.f_img;
		var f_url =data.f_url;
		var digg_count =data.digg_count;
		var sub_count =data.sub_count;
		var comment_count =data.comment_count;
		var is_digg =data.is_digg;
		var id = data.f_id;
		var f_org_id = data.f_org_id;//动态产生的组织id
		var f_start_time = data.f_start_time;//开始时间
		
		var commentLeveOneHtml = "";
		var commentAreaHtml = "<div class='comments-erea' data-style = 'block' style='display:none;'>";
		
		if(comment_count > 0){
			var comments = getComments(id);
			var length = data.comment_list.length;
			var dataCommentList = data.comment_list;
			commentAreaHtml = "<div class='comments-erea' data-style = 'block' style='display:block;'>";
			if(length > 0){
				for(var i = 0;i < length; i++){
					commentLeveOneHtml = commentLeveOneHtml + commentTemplateFirst(dataCommentList[i]);
				}
			}
		}
		
		if(logo == '0'){
			logo = r_path + "/static/web/university/images/default-hdPic.jpg";
		}
		
		var yiZanHtml = "";
		var weiZanHtml = "";
		if(is_digg == '已赞'){
			yiZanHtml = "style='display:block;'";
			weiZanHtml = "style='display:none;'";
		}else{
			weiZanHtml = "style='display:block;'";
			yiZanHtml = "style='display:none;'";
		}
		
		$('#messages').append("<!--视频区 start-->"+
				"						<div class='videoComment-panel' data-tag='"+id+"'>"+
				"							"+
				"							<!--视频info1-->"+
				"							<div class='videos-live'>"+
				"								<!--企业基本信息-->"+
				"								<div class='live-title'>"+
				"									<div class='default-penel'>"+
				"										<div class='defaultPic fl'>" +
				"											<a target='_blank' href='"+r_path+"/feeds/targetOrg?orgId="+f_org_id+"'>"+
				"												<img src='"+logo+"' width='50' height='50' alt='logo'>" +
						"									</a>"+
				"										</div>"+
				"										"+
				"										<div class='hdPic-tipsInfo fl'>"+
				"											<h4><span class='orgNameTarget' style='cursor:pointer;'>"+org_name+"</span><span class='date'>"+showTime+"</span></h4>"+
				"												<a target='_blank' href='"+r_path+"/feeds/targetOrg?orgId="+f_org_id+"' style='display:none;'>"+
				"													<span class='orgNameTargetA' >点击组织跳转</span>"+
				"												</a>"+
				"											<p>"+f_title+"</p>"+
				"											<p>开始时间："+f_start_time+"</p>"+
				"										</div>"+
				"										<div class='clear'></div>"+
				"									</div>"+
				"								</div>"+
				"								"+
				"								<!--视频简介-->"+
				"								<div class='live-Introduce'>"+
				"									<a href='javascript:void(0)' class='intro-tlt' title='"+f_title+"'>#"+f_title+"#</a>"+
				"									"+f_describe+""+
				"								</div>"+
				"								"+
				"								<!--视频播放区-->"+
				"								<div class='live-paenl' id='"+id+"'>"+
				"									<img src='"+f_img+"'>"+
				"								</div>"+
				"								"+
				"								<!--操作工具条-->"+
				"								<div class='tool-lists'>"+
				"									<div class='tools-info'>"+
				"										<p class='support yizan' "+yiZanHtml+">"+
				"											<span class='icon'></span>"+
				"										</p>"+
				"										<p class='support weizan' "+weiZanHtml+">"+
				"											<span class='icon2'></span>"+

				"										</p>"+
				"										<span class='number'>"+digg_count+"</span>"+
				"										<ul class='allInfos-list'>"+
				"											<li style='display:none;'>"+digg_count+"次分享</li>"+
				"											<li style='display:none;'>"+sub_count+"次订阅</li>"+
				"											<li class='commentCount'>"+comment_count+"条评论</li>"+
				"										</ul>"+
				"									</div>"+
				"									<div class='operate-tools'>"+
				"										<div class='tool-l fl'>"+
				"											<ul class='datatag' data-tag='"+id+"'>"+
				"												<li class='nuzan32'>"+
				"													<a href='javascript:void(0)'>"+
				"														<span class='icon tools-up'></span>"+
				"														<span class='fn-tips zanWords'>"+is_digg+"</span>"+
				"													</a>"+
				"												</li>"+
				"												<li style='display:none;'>"+
				"													<a href='javascript:void(0)'>"+
				"														<span class='icon icon-share'></span>"+
				"														<span class='fn-tips'>分享</span>"+
				"													</a>"+
				"												</li>"+
				"												<li style='display:none;'>"+
				"													<a href='javascript:void(0)'>"+
				"														<span class='icon icon-dy'></span>"+
				"														<span class='fn-tips'>订阅</span>"+
				"													</a>"+
				"												</li>"+
				"												<li>"+
				"													<a href='javascript:void(0)' class='commentsBtn'>"+
				"														<span class='icon icon-comment'></span>"+
				"														<span class='fn-tips'>评论</span>"+
				"													</a>"+
				"												</li>"+
				"												<li>"+
				"													<a href='javascript:void(0)' class='commentsBtn'>"+
				"														<span class='icon-comment'></span>"+
				"														<span class='fn-tips'></span>"+
				"													</a>"+
				"												</li>"+
				"											</ul>"+
				"										</div>"+
				"										<div class='tool-r fr'>"+
				"											<a href='javascript:void(0)' class='delete' title='删除' style='display:none;'>删除</a>"+
				"										</div>"+
				"										<div class='clear'></div>"+
				"									</div>"+
				"								</div>"+
				"								"+
				"								<!--评论区-->"+
													commentAreaHtml+
				"									<!--发表评论-->"+
				"									<div class='sendComments commentsSame'>"+
				"										<div class='card-pic'>"+
				"											<img src='"+headUrl+"' width='34' height='34'>"+
				"										</div>"+
				"										<div class='textinput'>"+
				"											<div class='say-someBox'>"+
				"												<textarea class='say addComments'>添加评论</textarea>"+
				"											</div>"+
				"               							<span id='Surlimitpro2' class='Surlimitpro2' style='display:none;'>评论内容为500字内，你已超出<span class='oneLevelComent'></span>字</span>"+
				"											<p class='tips'>按Enter键发布</p>"+
				"										</div>"+
				"									</div>"+
				"									"+
				"									<!--评论列表区-->"+
				"									"+
				"									<!--评论内容列表（直接循环此处内容块）-->"+
														commentLeveOneHtml + 
				"								</div>"+
				"							</div>"+
				
				"						<!--视频区 start-->");
		
//		//绑定点赞事件
		$(".nuzan32").off().on("click","a",function(){
			var f_id = $(this).parents(".datatag").data("tag");
			var $count = $(this).parents(".tool-lists").find(".number");
			var $zanWords = $(this).parents(".tool-lists").find(".zanWords");
			nuzan(f_id, $count, $zanWords);
		});
		
		
		getParents(id).tmComments();
		getComments(id);
	}
	
	/*渲染视频区*/
	function renderingVideo(data){
		
		var html = "";
		var logo = data.f_logo;
		var org_name = data.org_name;
		var create_time = data.create_time;
		var createTimeStr = getTimeByMillis(create_time);
		var showTime = getDateDiff(getDateTimeStamp(createTimeStr));
		var f_title =data.f_title;
		var f_describe =data.f_describe;
		var f_url =data.f_url;
		var f_img =data.f_img;
		var f_url =data.f_url;
		var digg_count =data.digg_count;
		var sub_count =data.sub_count;
		var comment_count =data.comment_count;
		var is_digg =data.is_digg;
		var id = data.f_id;
		var f_org_id = data.f_org_id;//动态产生的组织id
		var resource_id = data.resource_id;// 动态产生的资源第
		
		var commentLeveOneHtml = "";
		var commentAreaHtml = "<div class='comments-erea' data-style = 'block' style='display:none;'>";
		
		if(comment_count > 0){
			var comments = getComments(id);
			var length = data.comment_list.length;
			var dataCommentList = data.comment_list;
			commentAreaHtml = "<div class='comments-erea' data-style = 'block' style='display:block;'>";
			if(length > 0){
				for(var i = 0;i < length; i++){
					commentLeveOneHtml = commentLeveOneHtml + commentTemplateFirst(dataCommentList[i]);
				}
			}
		}
		
		//判断是否是登录组织所产生的动态
		var delHtml = "";
		if(r_org_id == f_org_id){
			delHtml = "<a href='javascript:void(0)' data-tag='"+id+"' data-rid='"+resource_id+"' class='delVideo' >"+
			"			 <span class='icon-comment'></span>"+
			"			 <span class='fn-tips'>删除</span>"+
			"		  </a>";
			
		}
		
		if(logo == '0'){
			logo = r_path + "/static/web/university/images/default-hdPic.jpg";
		}
		var yiZanHtml = "";
		var weiZanHtml = "";
		if(is_digg == '已赞'){
			yiZanHtml = "style='display:block;'";
			weiZanHtml = "style='display:none;'";
		}else{
			weiZanHtml = "style='display:block;'";
			yiZanHtml = "style='display:none;'";
		}
		$('#messages').append("<!--视频区 start-->"+
				"						<div class='videoComment-panel' data-tag='"+id+"'>"+
				"							"+
				"							<!--视频info1-->"+
				"							<div class='videos-live'>"+
				"								<!--企业基本信息-->"+
				"								<div class='live-title'>"+
				"									<div class='default-penel'>"+
				"										<div class='defaultPic fl'>"+
				"											<a target='_blank' href='"+r_path+"/feeds/targetOrg?orgId="+f_org_id+"'>"+		
				"												<img src='"+logo+"' width='50' height='50' alt='logo'>" +
				"											</a>"+
				"										</div>"+
				"										"+
				"										<div class='hdPic-tipsInfo fl'>"+
				"											<h4><span class='orgNameTarget' style='cursor:pointer;'>"+org_name+"</span><span class='date'>"+showTime+"</span></h4>"+
				"											<a target='_blank' href='"+r_path+"/feeds/targetOrg?orgId="+f_org_id+"' style='display:none;'>"+
				"												<span class='orgNameTargetA' >点击组织跳转</span>"+
				"											</a>"+
				"											<p>"+f_title+"</p>"+
				"										</div>"+
				"										<div class='clear'></div>"+
				"									</div>"+
				"								</div>"+
				"								"+
				"								<!--视频简介-->"+
				"								<div class='live-Introduce'>"+
				"									<a href='javascript:void(0)' class='intro-tlt' title='"+f_title+"'>#"+f_title+"#</a>"+
				"									"+f_describe+""+
				"								</div>"+
				"								"+
				"								<!--视频播放区-->"+
				"								<div class='live-paenl' id='"+id+"'>"+
				"									<video onended='playOver();' src='"+f_url+"' width='100%' height='100%' class='videoPlayer'  poster='"+f_img+"'>"+
				"										your browser does not support the video tag"+
				"									</video>"+
				"									<div class='errors'>"+
				"										<img src='"+r_path+"/static/web/university/images/error.jpg' width='100%' height='100%'/>"+
				"									</div>"+
				"									<div class='default-pauseBtn coverLayer'>"+
				"										<a href='javascript:void(0)' class='pauseIcon ctrollerBtn'  title='播放'></a>"+
				"									</div>"+
				"									"+
				"									<div class='live-bar'>"+
				"										<a href='javascript:void(0)' class='lv-btn lv-play'></a>"+
				"										<span class='time currentTime'>00:00</span>"+
				"										<div class='progress-bar'>"+
				"											<div class='timerSliderBar'></div>"+
				"										</div>"+
				"										<span class='time totalTime'>00:00</span>"+
				"										<div class='volume'></div>"+
				"										<span class='fullPage fullPageBtn' title='全屏'></span>"+
				"										<span class='fullPage exitFullPageBtn' style='display: none;' title='退出全屏'></span>"+
				"									</div>"+
				"								</div>"+
				"								"+
				"								<!--操作工具条-->"+
				"								<div class='tool-lists'>"+
				"									<div class='tools-info'>"+
				"										<p class='support yizan' "+yiZanHtml+">"+
				"											<span class='icon'></span>"+
				"										</p>"+
				"										<p class='support weizan' "+weiZanHtml+">"+
				"											<span class='icon2'></span>"+
				"										</p>"+
				"										<span class='number'>"+digg_count+"</span>"+
				"										<ul class='allInfos-list'>"+
				"											<li style='display:none;'>"+digg_count+"次分享</li>"+
				"											<li style='display:none;'>"+sub_count+"次订阅</li>"+
				"											<li class='commentCount'>"+comment_count+"条评论</li>"+
				"										</ul>"+
				"									</div>"+
				"									<div class='operate-tools'>"+
				"										<div class='tool-l fl'>"+
				"											<ul class='datatag' data-tag='"+id+"'>"+
				"												<li class='nuzan32'>"+
				"													<a href='javascript:void(0)'>"+
				"														<span class='icon tools-up'></span>"+
				"														<span class='fn-tips zanWords'>"+is_digg+"</span>"+
				"													</a>"+
				"												</li>"+
				"												<li style='display:none;'>"+
				"													<a href='javascript:void(0)'>"+
				"														<span class='icon icon-share'></span>"+
				"														<span class='fn-tips'>分享</span>"+
				"													</a>"+
				"												</li>"+
				"												<li style='display:none;'>"+
				"													<a href='javascript:void(0)'>"+
				"														<span class='icon icon-dy'></span>"+
				"														<span class='fn-tips'>订阅</span>"+
				"													</a>"+
				"												</li>"+
				"												<li>"+
				"													<a href='javascript:void(0)' class='commentsBtn'>"+
				"														<span class='icon icon-comment'></span>"+
				"														<span class='fn-tips'>评论</span>"+
				"													</a>"+
				"												</li>"+
				"												<li>"+
																	delHtml+
				"												</li>"+
				"											</ul>"+
				"										</div>"+
				"										<div class='tool-r fr'>"+
				"											<a href='javascript:void(0)' class='delete' title='删除' style='display:none;'>删除</a>"+
				"										</div>"+
				"										<div class='clear'></div>"+
				"									</div>"+
				"								</div>"+
				"								"+
				"								<!--评论区-->"+
													commentAreaHtml+
				"									<!--发表评论-->"+
				"									<div class='sendComments commentsSame'>"+
				"										<div class='card-pic'>"+
				"											<img src='"+headUrl+"' width='34' height='34'>"+
				"										</div>"+
				"										<div class='textinput'>"+
				"											<div class='say-someBox'>"+
				"												<textarea class='say addComments'>添加评论</textarea>"+
				"											</div>"+
				"               							<span id='Surlimitpro2' class='Surlimitpro2' style='display:none;'>评论内容为500字内，你已超出<span class='oneLevelComent'></span>字</span>"+
				"											<p class='tips'>按Enter键发布</p>"+
				"										</div>"+
				"									</div>"+
				"									"+
				"									<!--评论列表区-->"+
				"									"+
				"									<!--评论内容列表（直接循环此处内容块）-->"+
														commentLeveOneHtml + 
				"								</div>"+
				"							</div>"+
				
				"						<!--视频区 start-->");
		
		
		setTimeout(function(){
			var v1 = new VideoPlay();
		    v1.init(id);
		},500);
		
		
		
//		//绑定点赞事件
		$(".nuzan32").off().on("click","a",function(){
			var f_id = $(this).parents(".datatag").data("tag");
			var $count = $(this).parents(".tool-lists").find(".number");
			var $zanWords = $(this).parents(".tool-lists").find(".zanWords");
			nuzan(f_id, $count, $zanWords);
		});
		
		/*删除视频*/
		$(".delVideo").off().on("click",function(){
			var $_this = $(this);
			var f_id = $(this).data("tag");
			var resource_id = $(this).data("rid");
			$.fn.fandialog({
				title:"删除信息",
				contents:"确定要删除该条信息吗？",
				click:function(ok){
					if(ok){
						delFeed(f_id, resource_id, $_this, "video");
						
					}
				}
			});
		});
		
		
		getParents(id).tmComments();
		getComments(id);
	}
	
	//删除动态
	function delFeed(f_id, resource_id, $_this, type){
		$.ajax({                
			cache: true,    
			async: false, 
			type: "POST",                
			url:  r_path + '/feeds/delFeed',                
			data:{
				feedId : f_id,
				resourceId : resource_id,
				type : type
			},              
			error: function(request) { 
			},
			beforeSend : function(request){
			},
			success: function(data) {
				
			},
			complete: function(data) { 
				$_this.parents(".videoComment-panel").hide();
			}
		});
	}
	
	//删除评论
	function delComment(commentId, feedId, $_this){
		$.ajax({                
			cache: true,    
			async: false, 
			type: "POST",                
			url:  r_path + '/feeds/delFeedComment',                
			data:{
				commentId : commentId,
				feedId : feedId
				
			},              
			error: function(request) { 
			},
			beforeSend : function(request){
			},
			success: function(data) {
				
			},
			complete: function(data) { 
				var dataStr = data.responseText;
				var datas = eval('('+dataStr+')');
				var count = datas.obj;
				$_this.parents(".videoComment-panel").find(".commentCount").html(count+"条评论");
			}
		});
	}
	
	//播放完毕后事件
	function playOver(){
		
	}
	
	//获取第一级评论模板
	function commentTemplateFirst(data){
		
		var commentWords = data.commentLevelOne.content;
		var commentLevelTwo = data.comment_list;
		var id = data.commentLevelOne.id;//评论的详细id
		var to_uid = data.commentLevelOne.uid;//评论用户的id
		var f_id = data.commentLevelOne.feed_id;//动态id
		var createTimeStr = getTimeByMillis(data.commentLevelOne.create_time);
		var showTime = getDateDiff(getDateTimeStamp(createTimeStr));
		
		var delHtml = "";
		if(r_uid == to_uid){
			delHtml = "<a href='javascript:void(0)' title='删除' class='delCommentLevelOne' data-ids='"+id+"' data-touid='"+to_uid+"' data-fid='"+f_id+"'>删除</a>";
		}
		
		var logo = data.logo;
		var name = data.name;
		var orgId = data.orgId;
		
		if(logo == '0'){
			logo = r_path + "/static/web/university/images/default-hdPic.jpg";
		}
		
		var template1 = "<div class='sendComments commentsSame commentsLsPanel commentLeveOnePanel'>"+
						"	<div class='card-pic'>	" +
						"		<a target='_blank' href='"+r_path+"/feeds/targetOrg?orgId="+orgId+"'>"+
						"			<img src='"+logo+"' width='34' height='34'>" +
						"		</a>"+
						"	</div>"+
						"	<div class='comments-content'>	"+
						"		<div class='cons'>		"+
						"			<a target='_blank' href='"+r_path+"/feeds/targetOrg?orgId="+orgId+"' class='nameCard'>"+name+" 			"+
						"				<span class='icon'></span>			"+
						"			</a>"+
						"				"+commentWords+""+
						"		</div>	"+
						"		<div class='comments-tools'>		"+
						"			<span class='timer'>"+showTime+"&nbsp;&nbsp;·&nbsp;&nbsp;</span>	"+
						"			<a href='javascript:void(0)' style='display:none;' title='赞'>赞&nbsp;&nbsp;·&nbsp;&nbsp;</a>		"+
						"			<a href='javascript:void(0)' title='回复' class='reply-1' data-ids='"+id+"' data-touid='"+to_uid+"' data-fid='"+f_id+"'>回复&nbsp;&nbsp;&nbsp;&nbsp;</a>		"+
										delHtml +
						"			<a href='javascript:void(0)' style='display:none;' class='icon-z' data-up='0' onclick='thumbUp.show(this)'>			"+
						"			<span class='icon nuZan'></span>			"+
						"			<span class='count'>0</span>&nbsp;&nbsp;·&nbsp;&nbsp;		</a>		"+
						
						"		</div>"+
						"	</div>"+
						"	<!--二级回复列表-->"+
						"	<div class='comments-lists'>"+
						"		<ul class='reply-lists'>";
		var template2 = "";
		
		var template3 = "		</ul>"+
						"		<!--回复框-->"+
						"		<div class='commentsSame reply-panel' style='display:none'>				"+
						"			<div class='card-pic'>					"+
						"					<img src='"+headUrl+"' width='34' height='34'>				"+
						"			</div>				"+
						"			<div class='textinput'>					"+
						"				<div class='say-someBox'>						"+
						"					<input type='hidden' class='idsVal'/>"+
						"					<input type='hidden' class='touidVal'/>"+
						"					<input type='hidden' class='fidVal'/>"+
						"					<textarea class='say replyText'>回复..</textarea>"+
						"				</div>"+
						"			</div>               "+
						"               <span id='Surlimitpro' class='Surlimitpro2_level2' style='display:none;'>评论内容为500字内，你已超出<span class='twoLevelComent'></span>字</span>"+
						"			<a href='javascript:void(0)' style='clear:both;float:right;' class='sendBtn'>发表</a>  			"+
						"		</div>"+
						"	</div>"+
						"</div>";
		
		
		if(commentLevelTwo.length > 0){
			for(var i = 0; i  < commentLevelTwo.length; i ++){
				template2 = template2 +  commentTemplateSecond(commentLevelTwo[i],name,orgId);
			}
		}
		
		
		return template1 + template2 + template3;
	}
	
	
	
	//获取第二级评论模板
	function commentTemplateSecond(data,levelOneName,levelOneOrgId){
		var hfWords = data.commentLevelTwo.content;
		var id = data.commentLevelTwo.id;//评论的详细id
		var to_uid = data.commentLevelTwo.uid;//评论用户的id
		var f_id = data.commentLevelTwo.feed_id;//动态id
		
		var createTimeStr = getTimeByMillis(data.commentLevelTwo.create_time);
		var showTime = getDateDiff(getDateTimeStamp(createTimeStr));
		 	
		var logo = data.logo;
		var name = data.name;
		var orgId = data.orgId;
		
		if(logo == '0'){
			logo = r_path + "/static/web/university/images/default-hdPic.jpg";
		}
		
		var delHtml = "";
		
		if(r_uid == to_uid){
			delHtml = "	<a href='javascript:void(0)' title='删除' class='delCommentLevelTwo' data-ids='"+id+"' data-touid='"+to_uid+"' data-fid='"+f_id+"'>删除</a>";
		}
		
		var template =  "<li class='commentLevelTwoPanel'>"+
						"	<div class='card-pic'>" +
						"			<a target='_blank' href='"+r_path+"/feeds/targetOrg?orgId="+orgId+"'>"+
						"				<img src='"+logo+"' width='34' height='34'>" +
						"			</a>"+
						"		</div>"+
						"		<div class='replay-cons'>"+
						"			<div class='comments-content '>"+
						"				<div class='cons'>"+
						"					<a target='_blank' href='"+r_path+"/feeds/targetOrg?orgId="+orgId+"' class='nameCard'>"+name+"</a>"+
						"					回复了"+
						"					<a target='_blank' href='"+r_path+"/feeds/targetOrg?orgId="+levelOneOrgId+"' class='nameCard'>"+levelOneName+"</a>"+
						"					"+
						"					"+hfWords+""+
						"					"+
						"				</div>"+
						"				<!--评论工具条-->"+
						"				<div class='comments-tools'>"+
						"					<span class='timer'>"+showTime+"&nbsp;&nbsp;·&nbsp;&nbsp;</span>"+
						"					<a href='javascript:void(0)' style='display:none;' title='赞'>赞&nbsp;&nbsp;·&nbsp;&nbsp;</a>"+
						"					<a href='javascript:void(0)' title='回复' class='reply-1 reply-2' data-ids='"+id+"' data-touid='"+to_uid+"' data-fid='"+f_id+"'>回复&nbsp;&nbsp;&nbsp;&nbsp;</a>"+
												delHtml +
						"					<a href='javascript:void(0)' style='display:none;' class='icon-z'  data-up='0' onclick='thumbUp.show(this)'>"+
						"						<span class='icon nuZan'></span>"+
						"						<span class='count'>0</span>&nbsp;&nbsp;·&nbsp;&nbsp;"+
						"	               </a>"+
						
						"				</div>"+
						"			</div>"+
						"		</div>"+
						"	</li>";
		return template;
	}
	
	function getParents(id){
		return $("#"+id).parents(".videos-live");
	}
	
	function getComments(obj){
		return false;
		var f_id;
		
		
		getParents(obj).off().on("click",'.commentsBtn',function(){
			f_id = $(this).data("tag");
			alert(f_id);
			getParents(obj).find('.comments-erea').show();
		});
		
		getParents(obj).find('textarea.say').each(function(){
			var oTxt = $(this).val();
			var This = $(this);
			$(this).off().on('focus',function(){
				if(oTxt === $(this).val()){
					$(this).val("");
				}
				$(this).addClass("sayFocus");
				$(this).parents('.say-someBox').animate({height:80},200);
				
				
				$(document).on('keydown',function(ev){
					//alert("哎呦我擦");
					var ev = ev || window.event;
					
					if(ev.keyCode == 13){
						var commentWords = This.val().trim();
						if(This.val() == "")
							return false;
						else{
							
							if(commentWords != ""){
								alert(f_id);
								var penals = $("<div class='sendComments commentsSame commentsLsPanel'>"+
				"<div class='card-pic'>"+
				"	<img src='"+r_path+"/static/web/university/images/default-hdPic.jpg' width='34' height='34'>"+
				"</div>"+
				"<div class='comments-content'>"+
				"	<div class='cons'>"+
				"		<a href='javascript:void(0)' class='nameCard'>"+
				"			<span class='icon'></span>"+
				"			</a>"+
				""+commentWords+""+
				"	</div>"+
				"	<div class='comments-tools'>"+
				"		<span class='timer'>1小时前</span>"+
				"		<a href='javascript:void(0)' title='赞'>赞&nbsp;&nbsp;·&nbsp;&nbsp;</a>"+
				"		<a href='javascript:void(0)' title='回复' class='reply-1'>回复&nbsp;&nbsp;&nbsp;&nbsp;</a>"+
				"		<a href='javascript:void(0)' title='删除' class='reply-1'>删除</a>"+
				"		<a href='javascript:void(0)' class='icon-z' data-up='0' onclick='thumbUp.show(this)'>"+
				"			<span class='icon nuZan'></span>"+
				"			<span class='count'>0</span>&nbsp;&nbsp;·&nbsp;&nbsp;"+
				"		</a>"+
				
				"	</div>"+
				"</div>"+
				"<div class='comments-lists'>"+
				"<ul class='reply-lists'></ul>"+
				"			<div class='commentsSame reply-panel' style='display:none'>"+
				"				<div class='card-pic'>"+
				"					<img src='"+r_path+"/static/web/university/images/default-hdPic.jpg' width='34' height='34'>"+
				"				</div>"+
				"				<div class='textinput'>"+
				"					<div class='say-someBox'>"+
				"						<textarea class='say hf'>回复..</textarea> "+
				"					</div>"+
				"				</div> "+
				"               <span id='Surlimitpro' style='display:none'>评论内容为500字内，你已超出XXX字</span>"+
				"               <a href='javascript:void(0)' id='send' style='clear:both;float:right;' class='sendBtn'>发表</a>  "+
				"			</div>"+
				"</div>"+
			"</div>");
								
							
								getParents(obj).find('.comments-erea').append(penals);
								This.val("");
								This.blur();
								
								penals.find(".reply-1").on('click',function(){
$(this).parents(".commentsSame").find(".comments-lists .reply-panel").show();

$(this).parents(".commentsSame").find(".comments-lists .reply-panel").on('focus',".hf",function(){
	
	var _This = $(this);
	
	if($(this).val().trim() == "回复.."){
		$(this).val("");
		$(this).addClass("sayFocus");
		$(this).parents('.say-someBox').animate({height:80},200);
	}
	
	
	$(this).parents(".commentsSame").find(".comments-lists .reply-panel").off().on('click',".sendBtn",function(){
		//alert("来打我啊！");
		
		var hfWords = _This.val().trim();
		if(hfWords != ""){
			
			var tempjh = $("<li>"+
						"	<div class='card-pic'>"+
						"		<img src='"+r_path+"/static/web/university/images/default-hdPic.jpg' width='34' height='34'>"+
						"	</div>"+
						"	<div class='replay-cons'>"+
						"		<div class='comments-content '>"+
						"			<div class='cons'>"+
						"				<a href='javascript:void(0)' class='nameCard'>山东大学</a>"+
						"				回复了"+
						"				<a href='javascript:void(0)' class='nameCard'>京版</a>"+
						""+hfWords+""+
						"			</div>"+
						"			<!--评论工具条-->"+
						"			<div class='comments-tools'>"+
						"				<span class='timer'>1小时前&nbsp;&nbsp;·&nbsp;&nbsp;</span>"+
						"				<a href='javascript:void(0)' title='赞'>赞&nbsp;&nbsp;·&nbsp;&nbsp;</a>"+
						"				<a href='javascript:void(0)' title='回复' class='reply-2'>回复&nbsp;&nbsp;·&nbsp;&nbsp;</a>"+
						"				<a href='javascript:void(0)' title='删除' class='reply-2'>删除</a>"+
						"				<a href='javascript:void(0)' class='icon-z'  data-up='0' onclick='thumbUp.show(this)'>"+
						"					<span class='icon nuZan'></span>"+
						"					<span class='count'>0</span>&nbsp;&nbsp;·&nbsp;&nbsp;"+
						"               </a>"+
						
						"			</div>"+
						"		</div>"+
						"	</div>"+
						"</li>")
			
			$(this).parents(".comments-lists").find("ul.reply-lists").append(tempjh);
			_This.val("");
			_This.blur();
			_This.val("回复..");
			_This.removeClass("sayFocus");
			_This.parents('.say-someBox').animate({height:36},200,function(){
				$(this).parents('.reply-panel').hide();
			});
			
			$(this).parents(".comments-lists").find("ul.reply-lists").find("li").on('click','.reply-2',function(){
				var mmm = penals.find(".reply-1").parents(".commentsSame").find(".comments-lists .reply-panel");
				mmm.show();
				mmm.find('.hf').focus();
				if(mmm.find('.hf').val().trim() == "回复.."){
					mmm.find('.hf').val("");
					mmm.find('.hf').addClass("sayFocus");
					mmm.find('.hf').parents('.say-someBox').animate({height:80},200);
				}
				
			});
			
		}
		
		
	});
	
	
	
});


							$(this).parents(".commentsSame").find(".comments-lists .reply-panel").on('blur',".hf",function(){
								if($(this).val().trim() == ""){
									
								}
								
							});

								});
							}
						}
					}
					
				});
			});
			
			
			
			$(this).on('blur',function(){
				if($(this).val().trim() == ""){ 
					$(this).val(oTxt);  
					$(this).removeClass("sayFocus");
					$(this).parents('.say-someBox').animate({height:36},200);
					$(document).off();
				}
			});
			
		});
	}
	
	/*获取关注数 粉丝*/
	function getFollowAndFans(/*组织id*/org_id){
		$.ajax({                
			cache: true,    
			async: false, 
			type: "POST",                
			url:  r_path + '/home/getFollowAndFans',                
			data:{
				org_id : org_id
			},              
			error: function(request) { 
			},
			beforeSend : function(request){
			},
			success: function(data) {
				
			},
			complete: function(data) { 
				var dataStr = data.responseText;
				var datas = eval('('+dataStr+')');
				$("#floowCount").html(datas.obj.floowCount);
				$("#fansCount").html(datas.obj.fansCount);
			}
		});
	}
	
	/*资质认证页面*/
	function certificateTipPage(oper){
		if(oper != '99'){
			location.href =r_path + "/certificate/checkPageSch";
		}else{
			//进行认证的页面
			location.href =r_path + "/certificate/certificate";
		}
	}
	/*资质认证页面*/
	function certificateTipPageEnt(oper){
		if(oper == '99'){
			location.href =r_path + "/certificate/certificate-company";
		}else if(oper == '2' || oper == '1'){
			location.href =r_path + "/certificate/checkPageEnt";
		}else{
			//进行认证的页面
			location.href =r_path + "/certificate/certificate-company";
		}
	}
	
	function nuzan(f_id, $count, $zanWords){
		$.ajax({                
			cache: true,    
			async: true, 
			type: "POST",                
			url:  r_path + '/feeds/addZan',                
			data:{
				f_id : f_id
			},              
			error: function(request) { 
			},
			beforeSend : function(request){
			},
			success: function(data) {
				
			},
			complete: function(data) { 
				var dataStr = data.responseText;
				var datas = eval('('+dataStr+')');
				var count = parseInt($count.html());
				if(datas.success){
					var status = datas.obj;
					var $parents = $zanWords.parents(".videoComment-panel");
					debugger;
					if(status == 'add'){
						$count.html(count + 1);
						$zanWords.html("已赞");
						$parents.find(".yizan").show();
						$parents.find(".weizan").hide();
					}else if(status == 'del'){
						$count.html(count - 1);
						$zanWords.html("赞");
						$parents.find(".yizan").hide();
						$parents.find(".weizan").show();
						
						
					}
					
				}
			}
		});
	} 
	
	//获取粉丝或者关注着列表
	function getFollowList(page, pageSize, type, companyId){
		$.ajax({                
			cache: true,    
			async: true, 
			type: "POST",                
			url:  r_path + '/followfans/getFollowList',                
			data:{
				page : page,
				pageSize : pageSize,
				type : type,
				org_id : companyId
			},              
			error: function(request) { 
			},
			beforeSend : function(request){
			},
			complete: function(data) { 
				var dataStr = data.responseText;
				var datas = eval('('+dataStr+')');
				if(datas.success){
					data = datas;
					var html = "";
					var name = "";
					var orgId = "";
					var logog = "";
					for(var i = 0; i < data.obj.person.length; i++){
						name = data.obj.person[i].name;
						orgId = data.obj.person[i].orgId;
						logo = data.obj.person[i].logo;
						if(logo == '0'){
							logo = r_path + "/static/web/university/images/default-hdPic.jpg";
						}
						html += "   <li>"+
								"		<a href='"+r_path+"/feeds/targetOrg?orgId="+orgId+"' class='cardPic'>"+
								"			<img src='"+ logo +"' width='58' height='58'>"+
								"		</a>"+
								"		<a href='"+r_path+"/feeds/targetOrg?orgId="+orgId+"' class='nameShow' title='"+name+"'>"+name+"</a>"+
								"	</li>"
					}
					
					$('#fansList').empty();
					$('#fansList').append(html);
					$('#fansListDiv').show();
				}
			}
		});
	} 
	//获取粉丝或者关注着列表  页面
	function getFollowListPage(page, pageSize, type, org_id, toUserType){
		$.ajax({                
			cache: true,    
			async: true, 
			type: "POST",                
			url:  r_path + '/followfans/getFollowList',                
			data:{
				page : page,
				pageSize : pageSize,
				type : type,
				org_id : org_id,
				toUserType : toUserType
			},              
			error: function(request) { 
			},
			beforeSend : function(request){
				$("#"+type+"ListMore").hide();
				$("#"+type+"ListMoreIng").show();
			},
			complete: function(data) { 
				var dataStr = data.responseText;
				var datas = eval('('+dataStr+')');
				
				if(datas.success && datas.obj.person.length > 0){
					
					data = datas.obj.person;
					var count = datas.obj.count;
					var name = "";
					var orgId = "";
					var rowTwo = "";
					var rowThree = "";
					var userType = "";
					var logo = "";
					var html = "";
					
					for(var i = 0; i < data.length; i++){
						name = data[i].name;
						orgId = data[i].orgId;
						rowTwo = data[i].rowTwo;
						rowThree = data[i].rowThree;
						logo = data[i].logo;
						userType = data[i].userType;
						if(logo == '0'){
							logo = r_path + "/static/web/university/images/default-hdPic.jpg";
						}
						var addOrdelHtml = "";
						if(org_id != orgId && orgId != r_org_id){
							var flag = isFollowFans(orgId);
							if(flag){
								addOrdelHtml = "<div class='fans-role-attention' title='添加关注' style='display: none;'></div>"+
								   "<div class='fans-role-attention-no' title='取消关注' style='display: block;'></div>";
							}else{
								addOrdelHtml = "<div class='fans-role-attention' title='添加关注' style='display: block;'></div>"+
								   "<div class='fans-role-attention-no' title='取消关注' style='display: none;'></div>";
								
							}
						}
						
						
						html = html +  "<div class='grid' data-tag='"+orgId+"' data-utype='"+userType+"'>"+
											addOrdelHtml	+
											"<div class='picture'>"+
												"<a href='"+r_path+"/feeds/targetOrg?orgId="+orgId+"' class='showinfos' data-tag=''><img src='"+logo+"' width='180' height='180' alt='"+name+"' title=''></a>"+
											"</div>"+
											"<div class='pic-info'>"+
												"<a href='"+r_path+"/feeds/targetOrg?orgId="+orgId+"' class='title showinfos' data-tag=''>"+name+"</a>"+
												"<p class='other-txt'>"+rowTwo+"</p>"+
												"<p class='other-txt'>"+rowThree+"</p>"+
											"</div>"+
											
										"</div>";
						
					}
					
					
					$("#"+type+"PageCount").html(count);
					//$("#"+type+"PageList").empty();
					$("#"+type+"PageList").append(html);
					
					
					//绑定事件
					//添加关注
					$(".fans-role-attention").click(function(){
						var orgId = $(this).parents(".grid").data("tag");
						var uType = $(this).parents(".grid").data("utype");
						if(orgId != '' && uType != ''){
							var flag = addFollow(orgId, uType);
							if(flag){
//								var count = $("#followPageCount").html();
//								count = parseInt(count) + 1;
//								$("#followPageCount").html(count);
								$(this).hide();
								$(this).parents(".grid").find(".fans-role-attention-no").show();
							}
						}
					});
					//取消关注
					$(".fans-role-attention-no").click(function(){
						var orgId = $(this).parents(".grid").data("tag");
						var uType = $(this).parents(".grid").data("utype");
						if(orgId != '' && uType != ''){
							var flag = delFollow(orgId, uType);
							if(flag){
//								var count = $("#followPageCount").html();
//								count = parseInt(count) - 1;
//								$("#followPageCount").html(count);
								$(this).hide();
								$(this).parents(".grid").find(".fans-role-attention").show();
							}
						}
						
					});
				}
				//分页查询
				$("#"+type+"ListIngVal").val("0");
				var page = $("#"+type+"ListPage").val();
				page = parseInt(page) + 1;
				$("#"+type+"ListPage").val(page);
				
				$("#"+type+"ListMore").hide();
				$("#"+type+"ListMoreIng").hide();
				
			}
		});
	} 
	
	//获取推荐关注列表
	function getPushList(ids, type){
		$.ajax({                
			cache: true,    
			async: true, 
			type: "POST",                
			url:  r_path + '/push/getPushList',                
			data:{
				ids : ids,
				type : type
			},              
			error: function(request) { 
			},
			beforeSend : function(request){
			},
			complete: function(data) { 
				var dataStr = data.responseText;
				var datas = eval('('+dataStr+')');
				var html = "";
				var entIds = "";
				var schIds = "";
				var stuIds = "";
				if(datas.obj.length > 0){
					for(var i = 0; i < datas.obj.length; i++){
						var data = datas.obj[i];
						for(var j = 0;j < data.length;j++){
							$("#pushListDiv").show();
							var logo = data[j].logo;
							var name = data[j].name;
							var orgId = data[j].orgId;
							var rowTwo = data[j].rowTwo;
							var rowThree = data[j].rowThree;
							var dataType = data[j].dataType;
							if(logo == '0'){
								logo = r_path + "/static/web/university/images/default-hdPic.jpg";
							}
							html = html + 
									"	 <li>"+
									"		<div class='col-left'>" +
									"			<a href='"+r_path+"/feeds/targetOrg?orgId="+orgId+"'>"+
									"				<img src='"+logo+"' width='60' height='60'/>" +
									"			<a>"+
									"		</div>"+
									"		<div class='col-center'>"+
									"			<h2 class='pushTargetOrg' data-tag='"+orgId+"' style='cursor:pointer;'>"+name+"</h2>"+
									"			<h3>"+rowTwo+"</h3>"+
									"			<h4>"+rowThree+"</h4>"+
									"		</div>"+
									"		<div class='col-right'>"+
									"			<a href='javascript:void(0)' class='pay-attention follow' title='关注'>关注</a>"+
									"			<a href='javascript:void(0)' class='change-other' title='换一个'>"+
									"			<span class='icon'></span><span class='changePush' data-type='"+dataType+"' data-id='"+orgId+"'>换一个</span>"+
									"			</a>"+
									"		</div>"+
									"	</li>";
						}
					}
					
					$("#pushList").empty();
					$("#pushList").append(html);
					
					$("#pushList").on("click",".pushTargetOrg",function(){
						var orgId = $(this).data("tag");
						$("#targetOrgTriggerA").attr("href",r_path + "/feeds/targetOrg?orgId=" + orgId);
						$("#targetOrgTrigger").trigger("click");
					});
				}
			}
		});
	} 
	
	
	//更改推荐列表
	function getPushListChange(id, ids, type, $li){
		$.ajax({                
			cache: true,    
			async: true, 
			type: "POST",                
			url:  r_path + '/push/getPushList',                
			data:{
				id : id,
				ids : ids,
				type : type
			},              
			error: function(request) { 
			},
			beforeSend : function(request){
			},
			complete: function(data) { 
				var dataStr = data.responseText;
				var datas = eval('('+dataStr+')');
				var html = "";
				var entIds = "";
				var schIds = "";
				var stuIds = "";
				if(datas.obj.length > 0){
					for(var i = 0; i < datas.obj.length; i++){
						var data = datas.obj[i];
						if(data.length > 0){
							for(var j = 0;j < data.length;j++){
								var logo = data[j].logo;
								var name = data[j].name;
								var orgId = data[j].orgId;
								var rowTwo = data[j].rowTwo;
								var rowThree = data[j].rowThree;
								var dataType = data[j].dataType;
								if(logo == '0'){
									logo = r_path + "/static/web/university/images/default-hdPic.jpg";
								}
								html = html + 
									"		<div class='col-left'>"+
									"		<a href='"+r_path+"/feeds/targetOrg?orgId="+orgId+"'>"+
									"			<img src='"+logo+"' width='60' height='60'/>"+
									"		</a>"+
									"		</div>"+
									"		<div class='col-center'>"+
									"			<h2 class='pushTargetOrg' data-tag='"+orgId+"' style='cursor:pointer;'>"+name+"</h2>"+
									"			<h3>"+rowTwo+"</h3>"+
									"			<h4>"+rowThree+"</h4>"+
									"		</div>"+
									"		<div class='col-right'>"+
									"			<a href='javascript:void(0)' class='pay-attention follow' title='关注'>关注</a>"+
									"			<a href='javascript:void(0)' class='change-other' title='换一个'>"+
									"			<span class='icon'></span><span class='changePush' data-type='"+dataType+"' data-id='"+orgId+"'>换一个</span>"+
									"			</a>"+
									"		</div>";
							}
							
						}
					}
					
					if(html.length == 0){
						$li.remove();
					}else{
						$li.empty();
						$li.append(html);
						
						$li.on("click",".pushTargetOrg",function(){
							var orgId = $(this).data("tag");
							$("#targetOrgTriggerA").attr("href",r_path + "/feeds/targetOrg?orgId=" + orgId);
							$("#targetOrgTrigger").trigger("click");
						});
					}
					
					var len = $("#pushList").find("li").length;
					if((datas.obj[0].length + datas.obj[1].length + datas.obj[2].length) == 0 && len == 0){
						$("#pushListDiv").hide();
					}
				}
				
			}
		});
	} 
	
	function insertFollow(orgId, type, $_this){
		$.ajax({                
			cache: true,    
			async: true, 
			type: "POST",                
			url:  r_path + '/followfans/addPush',                
			data:{
				toId : orgId,
				toUserTypes : type
			},              
			error: function(request) { 
			},
			beforeSend : function(request){
			},
			complete: function(data) { 
				$_this.html("已关注");
			}
		});
	}
	
	/*判读是否关注当前用户*/
	function isFollowFans(/*被阅览的用户id*/org_id){
		var flag = false;
		$.ajax({                
			cache: false,    
			async: false, 
			type: "POST",                
			url:  r_path + '/comm/followfans/isFollowFans',                
			data:{
				org_id : org_id
			},              
			error: function(request) { 
			},
			beforeSend : function(request){
			},
			success: function(data) {
				if(data == null || data == '') return;
				var datas = eval('('+data+')');
				if(datas.success){
					flag = true;
				}
			}
		});
		return flag;
	}
	
	/*添加关注  简历里面*/
	function insertFollowInResume(orgId, type){
		$.ajax({                
			cache: true,    
			async: true, 
			type: "POST",                
			url:  r_path + '/followfans/addPush',                
			data:{
				toId : orgId,
				toUserTypes : type
			},              
			error: function(request) { 
			},
			beforeSend : function(request){
			},
			complete: function(data) {
				var dataStr = data.responseText;
				var datas = eval('('+dataStr+')');
				if(datas.success){
					var fansCount = $("#fansCount").html();
					fansCount = parseInt(fansCount) + 1;
					$("#fansCount").html(fansCount);
					$("#attentionBtn").hide();
					$("#attentionBtn-cancle").show();
				}
			}
		});
	}
	/*取消关注  简历里面*/
	function delFollowInResume(orgId, type){
		$.ajax({                
			cache: true,    
			async: true, 
			type: "POST",                
			url:  r_path + '/followfans/delPush',                
			data:{
				toId : orgId,
				toUserTypes : type
			},              
			error: function(request) { 
			},
			beforeSend : function(request){
			},
			complete: function(data) {
				var dataStr = data.responseText;
				var datas = eval('('+dataStr+')');
				if(datas.success){
					var fansCount = $("#fansCount").html();
					fansCount = parseInt(fansCount) - 1;
					$("#fansCount").html(fansCount);
					$("#attentionBtn").show();
					$("#attentionBtn-cancle").hide();
				}
			}
		});
	}
	
	/*添加关注*/
	function addFollow(orgId, type){
		var flag = false;
		$.ajax({                
			cache: false,    
			async: false, 
			type: "POST",                
			url:  r_path + '/followfans/addPush',                
			data:{
				toId : orgId,
				toUserTypes : type
			},              
			error: function(request) { 
			},
			beforeSend : function(request){
			},
			success: function(data) {
				var datas = eval('('+data+')');
				if(datas.success){
					flag = true;
				}
			}
		});
		return flag;
	}
	/*取消关注*/
	function delFollow(orgId, type){
		var flag = false;
		$.ajax({                
			cache: false,    
			async: false, 
			type: "POST",                
			url:  r_path + '/followfans/delPush',                
			data:{
				toId : orgId,
				toUserTypes : type
			},              
			error: function(request) { 
			},
			beforeSend : function(request){
			},
			success: function(data) {
				var datas = eval('('+data+')');
				if(datas.success){
					flag = true;
				}
			}
		});
		return flag;
	}
	
	/*检查资质认证*/
	function checkCertificate(){
		$.ajax({                
			cache: false,    
			async: true, 
			type: "POST",                
			url:  r_path + '/followfans/delPush',                
			data:{
				toId : orgId,
				toUserTypes : type
			},              
			error: function(request) { 
			},
			beforeSend : function(request){
			},
			success: function(data) {
				var datas = eval('('+data+')');
				if(datas.success){
					flag = true;
				}
			}
		});
	}
