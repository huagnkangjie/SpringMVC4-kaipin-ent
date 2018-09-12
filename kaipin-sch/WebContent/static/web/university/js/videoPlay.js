/*
 * @version : ...
 * @author ：      ...
 * @update ：     Every day !!
 * @fn : 视频播放 (未完待续。...，暂未考虑低版本，若要兼容，请用flash播放器)
 */

(function(window, document){
	 var VideoPlay = function(){
    	this.ctrollerBtn = null;//播放按钮
    	this.lvPlay = null;//播放暂停按钮
    	this.video = null; //视频对象
    	this.liveBar = null;
    	this.totalTime = null;//总时间
    	this.currentTime = null;//当前的播放进度时间
    	this.timerSliderBar = null;//进度条
    	this.progressBar = null;//拖动进度条
    	this.allTime = 0;//视频的总长度
    	this.fullPageBtn = null;//全屏
    	this.volume = null;
    	this.exitFullPageBtn = null;//退出全屏
    	this.coverLayer = null;//遮盖层
    	this.volState = true;
    	this.errors = null;
    	this.src = null;
    };
    
    VideoPlay.prototype.init = function(oParent){
    	var This = this;
    	This.oParent = $("#"+oParent);
    	This.ctrollerBtn = This.oParent.find(".ctrollerBtn");
    	This.videoState = This.oParent.find('.videoPlayer'); 
    	This.errors = This.oParent.find('.errors');
    	This.video = This.oParent.find('.videoPlayer').get(0);
    	This.src = This.videoState.attr('src'); 
    	This.lvPlay = This.oParent.find('.lv-play');
    	This.totalTime =This.oParent.find(".totalTime");
    	This.currentTime = This.oParent.find(".currentTime");
    	This.timerSliderBar = This.oParent.find(".timerSliderBar");
    	This.progressBar = This.oParent.find(".progress-bar");
    	This.fullPageBtn = This.oParent.find(".fullPageBtn");
    	This.exitFullPageBtn = This.oParent.find(".exitFullPageBtn");
    	This.coverLayer = This.oParent.find(".coverLayer");
    	This.volume = This.oParent.find(".volume");
    	This.liveBar = This.oParent.find(".live-bar");
    	This.operateControls();
    	
    };
    
    VideoPlay.prototype.operateControls = function(){
    	
    	var This = this;
    	
    	This.ctrollerBtn.on('click',function(){
    		if(This.video.duration){
    			This.siblingsVideo();
				This.getPlay();
				This.errors.hide();
    		}else{
    			This.siblingsVideo();
    			This.errors.show();
    		}
    	});
    	
    	This.lvPlay.on('click',function(){
    		This.getPlay();
    	});
    	
    	This.videoState.on("click",function(){
    		This.getPlay();
    	});
    	
    	//视频播放进度
    	bindEvent(This.video,'timeupdate',function(){
    		 This.currentTime.html(formatSeconds(This.video.currentTime));
    		 This.allTime = parseInt(This.video.duration);
    		 This.timerSliderBar.css("width",(This.video.currentTime / This.video.duration * 100) + "%");
    	});
    	
    	This.videoState.on("ended",function(){
    		This.coverLayer.css("display","block");
    		This.lvPlay.css('backgroundImage',"url("+r_path+"/static/web/university/images/pause.png)");
    	});
    	
    	//进入全屏
    	This.fullPage();
    	//退出全屏
    	This.exitFullPage();
    	
    	//音量控制
    	This.setVolumes();
    	
    	//快进
    	This.fastForward();
    	
   };
   

    VideoPlay.prototype.getPlay = function(){
    	var This = this;
		var resultTotaltime = parseInt(This.video.duration);
		This.totalTime.html(formatSeconds(resultTotaltime));
		if (This.video.paused) {
			This.video.play(); 
		  	This.coverLayer.css('display',"none");
		  	This.lvPlay.css('backgroundImage',"url("+r_path+"/static/web/university/images/play.png)")
		  	This.liveBar.css('display',"block");
		}
		else{
			This.video.pause(); 
			This.coverLayer.css('display',"block");
		  	This.lvPlay.css('backgroundImage',"url("+r_path+"/static/web/university/images/pause.png)")
		}
    };
   
   	VideoPlay.prototype.fullPage = function(){
   		var This = this;
   		This.fullPageBtn.on("click",function(){
    		if (!This.video.fullscreenElement &&  !This.video.mozFullScreenElement && !This.video.webkitFullscreenElement && !This.video.msFullscreenElement ) {
			    if (This.video.requestFullscreen) {
			      This.oParent.get(0).requestFullscreen();
			    } else if (This.video.mozRequestFullScreen) {
			      This.oParent.get(0).mozRequestFullScreen();
			    } else if (This.video.webkitRequestFullscreen) {
			      This.oParent.get(0).webkitRequestFullscreen(Element.ALLOW_KEYBOARD_INPUT);
			    }else if(This.video.msRequestFullscreen){
			    	This.oParent.get(0).msRequestFullscreen();
			    }
			    This.oParent.css({width:'100%',height:'100%'});
			    This.fullPageBtn.hide();
			    This.exitFullPageBtn.show();
    			document.onkeyup = function(event){
    			 	 var e = event || window.event || arguments.callee.caller.arguments[0];
    			 	 if(e && e.keyCode == 27){
    			 	 	This.oParent.css({width:'604px',height:'340px'});
    					This.fullPageBtn.show();
			    		This.exitFullPageBtn.hide();
    			 	 }
    			 }
		    }else{
		    	alert("进入全屏失败。。。");
		    }
    	});
   	}
   	
   	VideoPlay.prototype.exitFullPage = function(){
   		var This = this;
   		This.exitFullPageBtn.on("click",function(){
    	 	var quit = document ||  $("doucment").get(0) || $('body').get(0);
		    if (quit.exitFullscreen) {
		        quit.exitFullscreen();
		    } else if (quit.mozCancelFullScreen) {
		        quit.mozCancelFullScreen();
		    } else if (quit.webkitCancelFullScreen) {
		        quit.webkitCancelFullScreen();
		    }else if (quit.msCancelFullScreen) {
		        quit.msCancelFullScreen();
		    }
		    This.oParent.css({width:'604px',height:'340px'});
			This.fullPageBtn.show();
    		This.exitFullPageBtn.hide();
    		document.onkeyup = null;
    	 	
    	 });
   	};
   
   
   	VideoPlay.prototype.setVolumes= function(){
   		var This = this;
	   	This.volume.on("click",function(){
			if(This.volState){
				This.video.volume = 0;
				This.volume.addClass("noVolume");
			}else{
				This.video.volume = 1;
				This.volume.removeClass("noVolume");
			}
			This.volState = !This.volState;
		});
   }
   	
    VideoPlay.prototype.fastForward = function(){
    	var This = this;
    	This.progressBar.on("click",function(ev){
			var ev = ev || window.event;
			var left =  This.progressBar.offset().left;
			var pageX = ev.pageX;
			var _width = pageX - left;
			This.video.currentTime = _width*This.allTime/400;
			This.timerSliderBar.css("width",_width/400*100 + "%");
		});
    };	
    	
	VideoPlay.prototype.siblingsVideo = function(){
		var This = this;
		var siblingsParents = This.ctrollerBtn.parents(".videos-live").siblings();
		if(siblingsParents.length != 0 ){
			siblingsParents.find(".videoPlayer").get(0).pause();
			siblingsParents.find(".videoPlayer").get(0).currentTime = 0;
			siblingsParents.find(".live-bar").css("display","none");
			siblingsParents.find(".coverLayer").css("display","block");
			siblingsParents.find(".timerSliderBar").css("width","0");	
		}else{
			return false;
		}
	}
   
    function bindEvent(ele, eventName, func){
        if(window.addEventListener){
            ele.addEventListener(eventName, func);
        }
        else{
            ele.attachEvent('on' + eventName, func);
        }
    }
   
    function formatSeconds(value) {
	    var theTime = parseInt(value);// 秒
	    var theTime1 = 0;// 分
	    var theTime2 = 0;// 小时
	    if(theTime > 60) {
	        theTime1 = parseInt(theTime/60);
	        theTime = parseInt(theTime%60);
	            if(theTime1 > 60) {
	            theTime2 = parseInt(theTime1/60);
	            theTime1 = parseInt(theTime1%60);
	            }
	    }
	    var h = parseInt(theTime2)>=10 ? parseInt(theTime2) : "0" + parseInt(theTime2);
	    var m = parseInt(theTime1)>=10 ? parseInt(theTime1) : "0"+parseInt(theTime1);
	    var s = parseInt(theTime)>=10? parseInt(theTime) : "0"+parseInt(theTime);
	    if(h>=1){
	    	return h+":"+m+":"+s;
	    }else{
	    	return m + ":"+s;
	    }
	}
    window.VideoPlay = VideoPlay;
})(this, document);
