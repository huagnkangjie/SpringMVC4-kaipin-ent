/*
 *   	功能---弹出层
 * 	  	作者:饭
 * 		版本：3.0（成熟版）
 * 		时间：15.08.24
 */
(function($){
	
	$.fn.fandialog = function(options){
		//this.each(function(){
			//$(this).click(function(){
				var opts = $.extend({},$.fn.fandialog.methods,$.fn.fandialog.defalutes,options);
				var $dialog = opts.templates(opts);
				opts.event(opts,$dialog);
			//});
		//});
	}
	
	/*方法设置*/
	$.fn.fandialog.methods = {
		templates:function(opts){
			var $dialog = $(".tm_alert");
			if($dialog.html()==undefined){
				$dialog = $(
				"		<div class='tm_alert'>"+
				"			<div class='tm_title'>"+
				"				<h2 id='tm_title'>"+opts.title+"</h2>"+
				"				<a href='javascript:void(0)' class='tm_close'></a>"+
				"			</div>"+
				"			<div class='tm_contents'>"+
				"			<p class='tips-title'>"+opts.contents+"</p>"+
				"			</div>"+
				"			<div class='tm-btns'>"+
				"				<a href='javascript:void(0)' class='tm_sure'>确认</a>"+
				"				<a href='javascript:void(0)' class='tm_cancel'>取消</a>"+
				"			</div>"+
				"		</div>"
				);
				
				$("body").append($dialog);
				$("body").append("<div id='tm_layer'></div>");
				var $layer = $("#tm_layer");
				$layer.width($(window).width());
				$layer.height($(window).height());
				$layer.css({"position":"fixed","top":"0px","background":"#000000","left":"0px","zIndex":"999","opacity":"0.5","filter":"alpha(opacity:50)"});
				
			}else{
				$dialog.find("#tm_title").html(opts.title);
				$dialog.find(".tm_contents").html(opts.contents);
			}
			return $dialog;
		},
		
		event:function(opts,$dialog){
			var $this = this;
			$this._getCenter($dialog);/*初始化元素居中*/
			$this._beginDrag($dialog);/*初始化元素拖动*/
			$this._close($dialog,$this);/*关闭*/
			$this._sure(opts,$dialog,$this);/*确认*/
			$this._cancel(opts,$dialog,$this);/*取消*/
			/*随窗口变化而变化*/
			$(window).resize(function(){
				$this._getCenter($dialog);
				var $layer = $("#tm_layer");
				$layer.width($(window).width());
				$layer.height($(window).height());
			});
		},
		
		_beginDrag:function($dialog){/*开始拖拽*/
			var $tm_title = $dialog.find(".tm_title");
			$tm_title.mousedown(function(e){
				var oldX = e.clientX;
				var oldY = e.clientY;
				var left = $dialog.offset().left;
				var top = $dialog.offset().top;
				var height = $dialog.height();
				var width = $dialog.width();
				var scrollTop = $(window).scrollTop();
				var maxLeft = $(window).width() - width;
				var maxTop = $(window).height() - height;
				tm_forbiddenSelect();
				var isFlag = true;
				$(document).mousemove(function(e){
					if(isFlag){
						var newX = e.clientX;
						var newY = e.clientY;
						var newLeft = newX - oldX + left;
						var newTop = newY - oldY + top-scrollTop;
						if(newLeft<=0)newLeft=0;
						if(newTop<=0)newTop = 0;
						if(newLeft>maxLeft)newLeft=maxLeft;
						if(newTop>maxTop)newTop=maxTop;
						$dialog.css({"position":"fixed","zIndex":"10000","left":newLeft,"top":newTop});
					}
				}).mouseup(function(){
					isFlag = false;
					tm_autoSelect();
				});
			});
		},
		
		/*设置层居中*/
		_getCenter:function($dialog){
			var height = $dialog.height();
			var width = $dialog.width();
			var winW = $(window).width();
			var winH = $(window).height();
			var left = (winW - width)/2;
			var top =  (winH - height)/2;
			$dialog.css({"position":"fixed","zIndex":"10000","left":left,"top":top});
		},
		_close:function($dialog,$this){
			var $tm_close = $dialog.find(".tm_close");
			$tm_close.click(function(){
				$this._allClose($dialog);				
			});
		},
		_sure:function(opts,$dialog,$this){
			var $sure = $dialog.find(".tm_sure");
			$sure.click(function(){
				
				if(opts.click){
					opts.click(true);
				}
				$this._allClose($dialog);
			});
		},
		_cancel:function(opts,$dialog,$this){
			var $cancel = $dialog.find(".tm_cancel");
			$cancel.click(function(){
				if(opts.click){
					opts.click(false);
				}
				$this._allClose($dialog);
			});
		},
		_allClose:function($dialog){
				setTimeout(function(){
					$dialog.remove();
				},10);
				setTimeout(function(){
					var $layer = $("#tm_layer");
					$layer.fadeOut("slow",function(){
						$layer.remove();
					});
				},16);
		}
	};
	
	$.fn.fandialog.defalutes = {
		title:"提示信息",
		contents:"您是否要删除该老师？",
		teacherName:"张亮",
		job:"历史老师"

	};
	
	
	/**
		 * 禁止窗体选中
		 */
		function tm_forbiddenSelect() {
			$(document).on("selectstart", function() {
				return false;
			});
			document.onselectstart = new Function("event.returnValue=false;");
			$("*").css({
				"-moz-user-select" : "none"
			});
		}
		
		
		/* 窗体允许选中 */
		function tm_autoSelect() {
			$(document).on("selectstart", function() {
				return true;
			});
			document.onselectstart = new Function("event.returnValue=true;");
			$("*").css({
				"-moz-user-select" : ""
			});
		}
		
	
		
		
	
	
})(jQuery);



/*
 *   名称：loading插件
 * 	  作者：饭饭
 * 	  版本：3.0
 * 	  日期：15.08.11
 * 
 */

(function($){
	$.fn.tmLoading = function(options){
		//this.each(function(){
		//	$(this).click(function(){
				var opts = $.extend({},$.fn.tmLoading.defalutes,$.fn.tmLoading.methods,options) 
				/*var tips = $(this).data("tips");
				if(tips==undefined){
					tips = opts.content;
				}else if(tips != undefined){
					opts.content = tips;	
				}*/
				var  $loading = opts.templates(opts);
				opts.events($loading,opts);
			//});
		//});
	}
	
	/*方法设置*/
	$.fn.tmLoading.methods = {
		templates:function(opts){
			var $loading = $("#tm-loading");
			if($loading.html()==undefined){
				$loading = $(
					"<div id='tm-loading' style='z-index:9999999999'>"+
					"<img src='images/loading.gif' width='16px' height='16px' style='float:left;margin-top:16px'>"+
					"<span class='content'>"+
					opts.content+
					"</span>"+
					"</div>"
				);
				$("body").append($loading);	
			}else{
				$loading.find(".content").html(opts.content);
			}
			$loading.width(opts.width);
			$loading.height(opts.height);
			$loading.css({"background":opts.background,
						  "lineHeight":"45px",
						  "padding":"0 10px",
						  "textAlign":"center",
						  "fontSize":"14px",
						  "color":"#fff",
						  "fontWeight":"bold"
			});
			return $loading;
		},
		
		events:function($loading,opts){
			var $this = this;
			var timer = null;
			$this._position($loading);		
			
			$loading.click(function(){/*点击关闭*/
				$(this).slideUp("slow",function(){
					$(this).remove();
				});
			});
			
			if(opts.time>0){/*根据时间关闭*/
				clearTimeout(timer);
				timer = setTimeout(function(){
					$loading.slideUp("slow",function(){
						$(this).remove();
					});
				},opts.time * 500);
			}
			
			/*根据窗口变化，元素自动居中*/
			$(window).resize(function(){
				$this._position($loading);	
			})
			
		},
		_position:function($loading){/*设置元素居中*/
			var windowWidth = $(window).width();
			var windowHeight = $(window).height();
			var dialogWidth = $loading.width();
			var dialogHeight = $loading.height();
			var left = (windowWidth-dialogWidth)/2;
			var top =  (windowHeight-dialogHeight)/2;
			$loading.css({"position":"absolute","zIndex":"10001",left:left,top:top});
		}
	};
			
	/*默认值设置*/
	$.fn.tmLoading.defalutes = {
		content:"请输入内容...",/*//提示内容*/
		time:0,/*时间*/
		background:"#000",/*背景色*/
		width:"160px",/*宽度*/
		height:"45px"/*高度*/
	};
	

})(jQuery)


		function showLoading(content){
 	 		setTimeout(function(){
						$.fn.tmLoading({
							content:content,
							background:"green"
						});
					},300);
 	 	}

