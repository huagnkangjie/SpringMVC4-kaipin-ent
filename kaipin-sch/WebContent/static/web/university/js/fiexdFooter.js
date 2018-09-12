/*
 * 
 * 	fn ：解决注册类底部是否固定问题
 * 
 */
(function(){
	var height = $(window).height();
	var docu =  $(document).height();
	fiexedBootom(height,docu);
	$(window).resize(function(){
		var height1 = $(window).height();
		var docu1 =  $(document).height();
		fiexedBootom(height1,docu1);
	});
	function fiexedBootom(h1,h2){
		if(h2 <= h1){
			$("#footer_reg").addClass("fiexFoot");
		}else{
			$("#footer_reg").removeClass("fiexFoot");
		}
	}
})();
