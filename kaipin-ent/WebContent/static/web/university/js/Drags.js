/*
 * @version : ...
 * @author ：      ...
 * @update ：     Every day !!
 * @fn : 拖拽 上传企业封面弹窗(可继续扩展)
 * 
 */

(function($){
	$.fn.tmDrags = function(options){
		var This = this;
		var opts = $.extend({},$.fn.tmDrags.methods,$.fn.tmDrags.defalutes,options);
		opts.inits(This,opts);
	}
	
	$.fn.tmDrags.methods = {
		inits:function(obj,opts){
			this.getCenter(obj);
			this.resize(obj);
			this.dragMoves(obj,opts);
		},
		
		dragMoves : function(obj,opts){
			var _this = this;
			var editTlt = obj.children("div").eq(0);
			opts.isDrag == true ? editTlt.on("mouseover",function(){
				$(this).addClass("hover");
			}) : editTlt.on("mouseout",function(){
				$(this).removeClass("hover");
			});
			
			if(opts.isDrag){
				editTlt.on("mousedown",function(ev){
					var ev = ev || window.event;
					var oldX = ev.clientX;
					var oldY = ev.clientY;
					var _left = obj.offset().left;
					var _top = obj.offset().top;
					var width = obj.width();
					var height = obj.height();
					var scrollLeft = $(window).scrollLeft();
					var scrollTop = $(window).scrollTop();
					var dottedPanel = $("<div></div>");
					dottedPanel.css({
						"width":width,
						"height":height,
						"position":"fixed",
						"left":_left-scrollLeft-2,
						"top":_top-scrollTop-2,
						"cursor":"move",
						"zIndex":"12",
						"border":"2px dashed #ccc"
					});
					$("body").append(dottedPanel);
					
					var maxLeft = $(window).width()-width;
					var maxTop = $(window).height()-height;
					var isFlag = true;
					
					$(document).on("mousemove",function(ev){
						if(isFlag){
							var e = ev || window.event;
							var newX = e.clientX;
							var newY = e.clientY;
							var newLeft = newX - oldX + _left - scrollLeft; 
							var newTop = newY - oldY + _top - scrollTop;
							if(newLeft<=0)newLeft=0;
							if(newTop<=0)newTop=0;
							if(newLeft>maxLeft)newLeft = maxLeft;
							if(newTop>maxTop)newTop = maxTop;
							dottedPanel.css({"left":newLeft,"top":newTop});	
						}
					});
					$(document).on("mouseup",function(){
						var resulteLeft = dottedPanel.offset().left;
						var resulteTop = dottedPanel.offset().top;
						if(resulteLeft == _left-scrollLeft-2 && resulteTop == _top-scrollTop-2)dottedPanel.remove();
						else if(resulteLeft == 0 && resulteTop == 0)return false;
						else{
							if(resulteLeft <= 0)resulteLeft = 0;
							if(resulteTop <= 0)resulteTop = 0;
							var scrollLeft = $(window).scrollLeft();
							var scrollTop = $(window).scrollTop();
							obj.animate({"left":resulteLeft-scrollLeft+2,"top":resulteTop-scrollTop+2},300,function(){
								dottedPanel.remove();
							});
						}
						isFlag = false;
						return false;
					});
					
				});
			}
		},
		
		resize:function(obj){
			var _this = this;
			$(window).on("resize",function(){
				_this.getCenter(obj);
			})
		},
		
		getCenter:function(obj){
			var height = obj.height();
			var width = obj.width();
			var winW = $(window).width();
			var winH = $(window).height();
			var left = (winW - width)/2;
			var top =  (winH - height)/2;
			obj.animate({"left":left,"top":top},400);
		}
	};
	
	//可配置的信息 
	$.fn.tmDrags.defalutes = {
		isDrag : true //是否可以拖拽，默认true
	};
		
})(jQuery);
