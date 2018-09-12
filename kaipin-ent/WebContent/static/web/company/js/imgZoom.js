var imgbox = (function(){
		function lightImgbox(src){
			loadImg(src,function(ok){
				if(ok){
					var imgDom = this;
					//创建图片的相框 css3动画效果
					var imgboxDom = $("<div id='imgbox'></div>");
					var overDom = $("<div id='imgoverlay'></div>");
					$("body").append(imgboxDom);
					$("body").append(overDom);
					
					//遮罩层绑定点击事件
					overDom.on("click",function(){
						imgboxDom.fadeOut();
						overDom.fadeOut();
						var timer = setTimeout(function(){
							imgboxDom.remove();
							overDom.remove();
							clearTimeout(timer);
						},500);
					});
					
					
					
					//窗口的resize事件的时候
					var xbit = 900;
					var ybit = 500;
					//改变窗口之前的宽度和高度
					var ww = $(window).width();
					var wh = $(window).height();
					
					$(window).resize(function(){
						//获取等比例缩放以后的宽度和高度去计算 500 * 500
						var json = resizeImg(imgDom,xbit,ybit); 
						//获取缩小的比例值，乘以图片的宽度和高度 	
						var xb = ($(window).width() / ww) ;
						var yb = ($(window).height() / wh);
						//获取元素的
						var scale = Math.min(xb,yb,1);
						var xper =  scale * json.width;
						var yper = scale * json.height;
						xper = (xper>=xbit?xbit:xper);
						yper = (yper>ybit?ybit:yper);
						//定位相框
						tz_position(imgboxDom,xper,yper);	
						//将图片放入相框中
						imgboxDom.find("img").css({"width":xper,"height":yper});
						
					});

					//等比例缩放图片的地址
					var json = resizeImg(imgDom,900,500);
					//定位相框
					tz_position(imgboxDom,json.width,json.height);	
					//将图片放入相框中
					var newImg = $("<img  src='"+imgDom.src+"' width='"+json.width+"' height='"+json.height+"' class='animated fadeIn'/>") 
					imgboxDom.append(newImg);
				}else{
					//this.src = "图片加载失败的地址"	;
					alert("图片加载失败...");
					//loading("remove");
				}
			});
		};
	
		//判断一个图片是否加载完毕
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


		//层居中的小算法
		function tz_position(dom,w,h){
			//获取窗口的宽度和高度
			var ww = $(window).width();
			var wh = $(window).height();
			var left = (ww - w) / 2;
			var top = (wh - h)/2;
			dom.css({"left":left,"top":top,"width":w,"height":h});
		};

		//图片等比例 
		function resizeImg(img,iwidth,iheight){ 
			//图片对象
			var image= img;  
			//计算后的图片宽度和高度的对象
			var boxWH = {};
			//如果图片的宽度和高度都大于0，代表是一张图片
			if(image.width>0 && image.height>0){
				//图片宽度
	     		boxWH.width=image.width;
				//图片的高度
	     		boxWH.height=image.height;	    
				//宽度固定，高度缩放
				if(boxWH.width>iwidth){    
					boxWH.height = (boxWH.height*iwidth)/boxWH.width;  
					boxWH.width = iwidth;//宽度不变		 
				}
				//高度固定，宽度缩放
				if(boxWH.height>iheight){    
					boxWH.width = (boxWH.width*iheight)/boxWH.height;;   
					boxWH.height = iheight;	             	 
				}    	           
			}   
			return boxWH;
		};
		

		return {
			show:lightImgbox
		};
				
})();