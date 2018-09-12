/*
 * @version : ...
 * @author ：      ...
 * @update ：     Every day !!
 * @fn : 轮播图（未配置封装）；
 */

(function(){
	var oParentBanner = $("#enterBannerShow")
	var num = 0;
	var nowTime = 0;
	var time = 3000;//时间种子
	var oLi = oParentBanner.find("ul li");
	var oSpan = oParentBanner.find('.collecBtn span');
	var oSpanW =oParentBanner.find('.collecBtn');
	oSpanW.css("left",((oLi.eq(0).width()-oSpanW.width())/2));
	oLi.eq(0).fadeIn();
	var timer = null;
	oSpanW.on("mouseover","span",function(){
		clearInterval(timer);
		var _index = $(this).index();
		num = _index;
		mainPlay(num);
	});
	
	oSpanW.on("mouseout","span",function(){
		autoPlay();
	});
	autoPlay();
	function autoPlay(){
		timer = setInterval(function(){
			num++;
			if(num > oSpan.length-1){
				num = 0;
			}
			mainPlay(num);
		},time);
	};
	
	function mainPlay(index){
		oSpan.eq(index).addClass("active").siblings().removeClass("active");
		oLi.eq(index).fadeIn().siblings().fadeOut();
	}
})();
