(function(){
	var height = $("#position-attribute").parents(".prev-details").height();
	var topmain = $("#position-attribute").offset().top;
	var left = $("#position-attribute").offset().left;
	if(height>658){
		$(window).resize(function(){
			left = $("#position-attribute").offset().left;
		});
		
		$(window).scroll(function () {
	        if ($(window).scrollTop() > (topmain)) {
	            $("#position-attribute").addClass("fixedTop");
	            $("#position-attribute").css("left",left);
	          }else {
	           $("#position-attribute").removeClass("fixedTop");
	         }
	    });
	}
	
})();