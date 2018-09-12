/*
 * @version : I don’t konw !
 * @author ：      The who ？！
 * @update ：     Every day !!
 * @fn : 评论！
 * 
 */


(function($){
	$.fn.tmComments = function(options){
		var This = this;
		This.each(function(){
			var _this = $(this);
			var opts = $.extend({},$.fn.tmComments.methods,$.fn.tmComments.defalutes,options);
			opts.init(_this);
			
		});
	};
	
	$.fn.tmComments.methods = {
		//初始化函数
		init : function(obj){
			var $this = this;
			var dataStyle = $this._sendCommentsPanel(obj).data("style");
			if(dataStyle == "block"){
				//alert(1);
				$this._sendMessages(obj);//获取焦点
				$this._replyComments(obj);//回复
			}else{
				obj.on("click",'.commentsBtn',function(){
					$this._showSendComments(obj);
					$this._sendMessages(obj);//获取焦点
				});
			}
			//点击评论按钮
			obj.on("click",'.commentsBtn',function(){
				$this._showSendComments(obj);
				$this._sendMessages(obj);//获取焦点
			});
			
		},
		
		_sendCommentsPanel : function(obj){
			return obj.find(".comments-erea");
		},
		
		//评论框显示
		_showSendComments : function(obj){
			this._sendCommentsPanel(obj).show();
		},
		
		//评论框获取焦点
		_sendMessages : function(obj){
			var $this = this;
			var saysPanel = obj.find('textarea.addComments');
			var defaluteTxt = saysPanel.val();
			var commentsLsPanel = obj.find('.comments-erea');
			//评论框获取焦点
			saysPanel.on("focus",function(){
				//alert("哎吆，窝草,评论框获取焦点咯！");
				$this._checkValueFocus(saysPanel,defaluteTxt);//评论框获取焦点检测值
				$this._setCommentsPanelStyle(saysPanel);//设置评论框的属性
				
				$this._enterSendWords(commentsLsPanel,saysPanel);//按回车发送
			});
			
			//评论框失去焦点
			saysPanel.on("blur",function(){
				//alert("失去焦点");
				if(saysPanel.val().trim() != ""){
					$(this).parents(".comments-erea").find(".Surlimitpro2").hide();
					return false;
				}else{
					$this._checkValueBlur(saysPanel,defaluteTxt);
				}
				$this._restoreCommentsPanelStyle(saysPanel);////还愿评论框的属性
				
			});
			
		},
		
		//评论框获取焦点检测值
		_checkValueFocus : function(saysPanel,defaluteTxt){
			saysPanel.each(function(){
				var This = $(this);
				if(This.val() == defaluteTxt){
					This.val("");
				}
			});
		},
		
		//评论框失去焦点检测值 2级回复
		_checkValueBlur : function(saysPanel,defaluteTxt){
			saysPanel.each(function(){
				var This = $(this);
				if(This.val().trim() == ""){
					This.val(defaluteTxt);
				}
			});
			$(document).off("keydown");
		},
		
		//配置评论框的属性值
		_setCommentsPanelStyle : function(saysPanel){
			saysPanel.addClass("sayFocus");
			saysPanel.parents('.say-someBox').stop(true,true).animate({height:'80px'},300);
		},
		
		//还原评论框的默认属性值
		_restoreCommentsPanelStyle : function(saysPanel){
//			saysPanel.removeClass("sayFocus");
			saysPanel.parents('.say-someBox').stop(true,true).animate({height:'36px'},300);
		},
		
		//回车键发表评论（可在此配置ajax等）
		_enterSendWords : function(commentsEreaobj,saysPanel){
			var $this =this;
			$(document).off().on('keydown',function(ev){
				var ev = ev || window.event;
				var txt = saysPanel; 
				if(ev.keyCode == 13){
					if(txt.val().trim() == ""){
						return false;
					}else{
						//动态id
						var f_id = txt.parents(".videoComment-panel").data("tag");
						//var appendSends = $this._sendCommentsTemplate(txt.val());
						
						var lens = $.trim(txt.val()).length;
						
						if(lens > 500){
							commentsEreaobj.find(".Surlimitpro2").show();
							var count = parseInt(lens)-500;
							commentsEreaobj.find(".oneLevelComent").html(count);
							return;
						}else{
							commentsEreaobj.find(".Surlimitpro2").hide();
						}
						
						//添加评论
						$this._addComment(f_id,txt,commentsEreaobj,$this);
						
//						commentsEreaobj.append(appendSends);
//						txt.val("");
//						txt.blur();
//						
//						$this._replyComments(appendSends);
						
					}
				}
			});
		},
		
		//回复评论（可在此配置ajax等）
		_replyComments : function(obj){
			
			var $this = this;
			obj.on("click",".reply-1",function(){
				var _this = $(this);
				$(this).parents(".commentsLsPanel").siblings().find(".reply-panel").hide();
				var  replyBox = $(this).parents(".commentsLsPanel").find(".comments-lists .reply-panel");
				replyBox.show();
				var replyInput = replyBox.find('.replyText'),//回复框
					defaluteReplyTxt= replyInput.val(),//回复框默认值 
					sendBtn = replyBox.find(".sendBtn");//发送按钮
					
				replyInput.on("focus",function(){
					$this._checkValueFocus(replyInput,defaluteReplyTxt);
					$this._setCommentsPanelStyle(replyInput);//配置评论框的属性值
				});
				
				replyInput.on("blur",function(){
					if(replyInput.val().trim() != ""){
						$(this).parents(".comments-erea").find(".Surlimitpro2_level2").hide();
						return false;
					}else{
						$this._checkValueBlur(replyInput,defaluteReplyTxt);
					}
					$this._restoreCommentsPanelStyle(replyInput);//配置评论框的属性值
				});
				
				$this._sendReplyComments(sendBtn,replyInput);
				
				var id = _this.data("ids");
				var to_uid = _this.data("touid");
				var f_id = _this.data("fid");
				
				_this.parents(".commentsLsPanel").find(".idsVal").val(id);
				_this.parents(".commentsLsPanel").find(".touidVal").val(to_uid);
				_this.parents(".commentsLsPanel").find(".fidVal").val(f_id);
				
			});
			
			obj.on("click",'.reply-2',function(){
				var $this = $(this);
				var id = $this.data("ids");
				var to_uid = $this.data("touid");
				var f_id = $this.data("fid");
				$this.parents(".commentsLsPanel").find(".idsVal").val(id);
				$this.parents(".commentsLsPanel").find(".touidVal").val(to_uid);
				$this.parents(".commentsLsPanel").find(".fidVal").val(f_id);
			});
			
			
		},
		
		//回复二级评论，此处可配置ajax
		_sendReplyComments : function(sendBtn,oPInput){
			
			var $this = this;
			sendBtn.each(function(){
					var _this = $(this);
					var oPanel = _this.parents('.comments-lists').find("ul.reply-lists");
					_this.off().on("click",function(){
						var sendReplayTxt = oPInput.val();
						if(sendReplayTxt == "" || sendReplayTxt == "回复.."){
							return false;
						}else{
							//无限下级
//							var id =  _this.parents(".commentsLsPanel").find(".idsVal").val();
//							var touid =  _this.parents(".commentsLsPanel").find(".touidVal").val();
//							var f_id =  _this.parents(".commentsLsPanel").find(".fidVal").val();
							//只有一个2级
							var $reply_1 = _this.parents(".commentsLsPanel").find(".reply-1");
							var id = $reply_1.data("ids");//一级评论的id
							var touid = $reply_1.data("touid");//一级评论的用户id
							var f_id = $reply_1.data("fid");//一级评论的动态id
							
							var sendReplayTxtlen = sendReplayTxt.trim().length;
							
							var lens = parseInt($.trim(sendReplayTxt).length);
							
							if(lens > 500){
								oPInput.parents(".comments-erea").find(".Surlimitpro2_level2").show();
								oPInput.parents(".comments-erea").find(".twoLevelComent").html(lens - 500);
								return;
							}else{
								oPInput.parents(".comments-erea").find(".Surlimitpro2_level2").hide();
							}
							
							$this._addCommentLevelTwo(oPanel,sendReplayTxt,oPInput,_this,$this,id,touid,f_id,sendReplayTxt);
							
//							oPanel.append($this._replyCommentTemplates(sendReplayTxt));
//							oPInput.val('回复..');
//							_this.parents('.reply-panel').hide();
//							$this._restoreCommentsPanelStyle(oPInput);//还原评论框的默认属性值
						}
					});
			});
			
		},
		
		//发表评论模板文件
		_sendCommentsTemplate :function(commentWords, data){
			
			var id = data.id;//评论的详细id
			var to_uid = data.uid;//评论用户的id
			var f_id = data.feedId;//动态id
			
			var tempfirstSend = $("<div class='sendComments commentsSame commentsLsPanel commentLeveOnePanel'>"+
									"<div class='card-pic'>" +
									"	<a target='_blank' href='"+r_path+"/feeds/targetOrg?orgId="+r_org_id+"'>"+
									"		<img src='"+headUrl+"' width='34' height='34'>" +
									"	</a>"+
									"</div>"+
									"<div class='comments-content'>"+
									"	<div class='cons'>"+
									"		<a target='_blank' href='"+r_path+"/feeds/targetOrg?orgId="+r_org_id+"' class='nameCard'>" +
									"			"+orgName+" "+
									"			<span class='icon'></span>"+
									"		</a>"+
									"		"+commentWords+""+
									"	</div>"+
									"	<div class='comments-tools'>"+
									"		<span class='timer'>刚刚&nbsp;&nbsp;·&nbsp;&nbsp;</span>"+
									"		<a href='javascript:void(0)' title='赞' style='display:none;'>赞&nbsp;&nbsp;·&nbsp;&nbsp;</a>"+
									"		<a href='javascript:void(0)' title='回复' class='reply-1' data-ids='"+id+"' data-touid='"+to_uid+"' data-fid='"+f_id+"'>回复&nbsp;&nbsp;·&nbsp;&nbsp;</a>"+
									"		<a href='javascript:void(0)' title='删除' class='delCommentLevelOne' data-ids='"+id+"' data-touid='"+to_uid+"' data-fid='"+f_id+"'>删除</a>"+
									"		<a href='javascript:void(0)' style='display:none;' class='icon-z' data-up='0' onclick='thumbUp.show(this)'>"+
									"			<span class='icon nuZan'></span>"+
									"			<span class='count'>0</span>&nbsp;&nbsp;·&nbsp;&nbsp;"+
									"		</a>"+
									
									"	</div>"+
									"</div>"+
									"<div class='comments-lists'>"+
									"<ul class='reply-lists'></ul>"+
									"			<div class='commentsSame reply-panel' style='display:none'>"+
									"				<div class='card-pic'>"+
									"					<img src='"+headUrl+"' width='34' height='34'>"+
									"				</div>"+
									"				<div class='textinput'>"+
									"					<div class='say-someBox'>"+
									"						<input type='hidden' class='idsVal'/>"+
									"						<input type='hidden' class='touidVal'/>"+
									"						<input type='hidden' class='fidVal'/>"+
									"						<textarea class='say replyText'>回复..</textarea> "+
									"					</div>"+
									"				</div> "+
									"               <a href='javascript:void(0)' style='clear:both;float:right;' class='sendBtn'>发表</a>  "+
									"			</div>"+
									"</div>"+
								"</div>");
			return tempfirstSend;
		},
		
		//回复评论模板文件
		_replyCommentTemplates :function(hfWords, data, name){
			
			var id = data.id;//评论的详细id
			var to_uid = data.uid;//评论用户的id
			var f_id = data.feedId;//动态id
			
			var replyCosns = $("<li class='commentLevelTwoPanel'>"+
									"	<div class='card-pic'>" +
									"		<a target='_blank' href='"+r_path+"/feeds/targetOrg?orgId="+r_org_id+"'>"+
									"			<img src='"+headUrl+"' width='34' height='34'>" +
									"		</a>"+
									"	</div>"+
									"	<div class='replay-cons'>"+
									"		<div class='comments-content '>"+
									"			<div class='cons'>"+
									"				<a target='_blank' href='"+r_path+"/feeds/targetOrg?orgId="+r_org_id+"' class='nameCard'>"+orgName+"</a>"+
									"				回复了"+
									"				<a target='_blank' href='"+r_path+"/feeds/targetOrg?orgId="+to_uid+"' class='nameCard'>"+name+"</a>"+
									""+hfWords+""+
									"			</div>"+
									"			<!--评论工具条-->"+
									"			<div class='comments-tools'>"+
									"				<span class='timer'>刚刚&nbsp;&nbsp;·&nbsp;&nbsp;</span>"+
									"				<a href='javascript:void(0)' title='赞' style='display:none;'>赞&nbsp;&nbsp;·&nbsp;&nbsp;</a>"+
									"				<a href='javascript:void(0)' title='回复' class='reply-1 reply-2' data-ids='"+id+"' data-touid='"+to_uid+"' data-fid='"+f_id+"'>回复&nbsp;&nbsp;·&nbsp;&nbsp;</a>"+
									"				<a href='javascript:void(0)' title='删除' class='delCommentLevelTwo' data-ids='"+id+"' data-touid='"+to_uid+"' data-fid='"+f_id+"'>删除</a>"+
									"				<a href='javascript:void(0)' class='icon-z' style='display:none;'  data-up='0' onclick='thumbUp.show(this)'>"+
									"					<span class='icon nuZan'></span>"+
									"					<span class='count'>0</span>&nbsp;&nbsp;·&nbsp;&nbsp;"+
									"               </a>"+
									
									"			</div>"+
									"		</div>"+
									"	</div>"+
									"</li>");
									
			return replyCosns;
		},
		
		//添加一级评论
		_addComment : function (f_id,text,commentsEreaobj,$this){
			var txt = text.val();
			$.ajax({                
				cache: false,    
				async: true, 
				type: "POST",                
				url:  r_path + '/feeds/addComment',                
				data:{
					feedId : f_id,
					content : txt,
					oper : 'pl'
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
					if(datas.success){
						var appendSends = $this._sendCommentsTemplate(txt,datas.obj.comment);
						commentsEreaobj.append(appendSends);
						text.val("");
						text.blur();
						$this._replyComments(appendSends);
					}
					
				}
			});
		},
		
		//添加二级评论  回复
		_addCommentLevelTwo : function (oPanel,sendReplayTxt,oPInput,_this,$this,id,touid,f_id,sendReplayTxt){
			$.ajax({                
				cache: false,    
				async: true, 
				type: "POST",                
				url:  r_path + '/feeds/addComment',                
				data:{
					feedId : f_id,
					content : sendReplayTxt,
					toCommentId : id,
					toUid : touid,
					oper : 'hf'
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
					if(datas.success){
						oPanel.append($this._replyCommentTemplates(sendReplayTxt,datas.obj.comment,datas.obj.name));
						oPInput.val('回复..');
						_this.parents('.reply-panel').hide();
						$this._restoreCommentsPanelStyle(oPInput);//还原评论框的默认属性值
					}
					
				}
			});
		},
		
	};
	
	$.fn.tmComments.defalutes = function(){//默认属性，以及回调函数可以在此配置，
		
	};
	
	String.prototype.trim = function(){  
        return this.replace(/(^\s*)|(\s*$)/g, "");  
    }  
})(jQuery);