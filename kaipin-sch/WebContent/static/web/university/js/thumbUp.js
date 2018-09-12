/*
 * @version : ...
 * @author ：      ...
 * @update ：     Every day !!
 * @fn : 点赞、
 * 
 */
var thumbUp = (function(){
		
		/*
		 *   一个账号点一个赞？一个IP点一个赞  ？游客也点赞 ？...还是可以取消，目前木有取消。。。？！！！
		 * 		
		 * 		额额额额，，。。。egg疼 ！！！！
		 * 			。。。
		 * 		未完待续！！！
		 * 
		 * 		（额，看完请删除 ！）
		 */
				
	function upAddOne(obj){
		//var left = This.position().left;
		//var top = This.position().top;
		
		//条件在这写。。。或者ajax
		/*if(){
			
		}*/
		var Ospan = $("<span>1</span>");
		var This = $(obj);
		var upNum = parseInt(This.data('up'));
		var countNum = This.find('.count');
		This.append(Ospan);
		upNum ++;
		This.data('up',upNum);
		countNum.html(upNum);
		Ospan.css({
			width : "20px",
			height : "20px",
			fontSize: "12px",
			fontWeight:"bold",
			lineHeight : '20px',
			borderRadius : "50%",
			textAlign :"center",
			background:"red",
			color : "#fff",
			position : 'absolute',
			left : 20,
			top: -1
		}).stop(true,true).animate({top:-20,"opacity":0},500,function(){
			Ospan.remove();
		});
	}
	
	return {
		show:upAddOne
	};
	
})();