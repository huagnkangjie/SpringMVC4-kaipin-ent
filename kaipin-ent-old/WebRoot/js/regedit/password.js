/*
 * 
 *	作者：891779683@qq.com
 * 	描述：解决低版本浏览器（IE）密码框不兼容（password） placeholder问题
 * 
 */
(function(){
	var placeholderOfPsws = $('.placeholderOfPsw');
	function isPlaceHolder(){
	    return "placeholder" in document.createElement("input");
	}
	if(!isPlaceHolder()){
		placeholderOfPsws.find(".placeholders").css("display","block");
		exPlaceholder();
	}
 
	function exPlaceholder(){
	 	var $this = "";
		 placeholderOfPsws.find(".placeholders").each(function(){
		 	$this = $(this); 
		 	var $first = $this.parent().find("input[type = 'password']");
		 	if(!isPlaceHolder()){
				$first.css("display","none");
				placeholderOfPsws.find(".placeholders").css("display","block");
			}
		 	
		 	$this.on("click",function(){
		 		$(this).hide();
		 		$first.css("display","block");
		 		$first.focus();
		 	});
		 	
		 	$first.on("blur",function(){
		 		String.prototype.trim=function(){return this.replace(/(^\s*)|(\s*$)/g,"");}
		 		var val = $(this).val();
		 		if(val.trim() == "" || val.length <= 0){
		 			var next = $(this).next("input").show();
		 			$(this).hide();
		 		}
		 	});
		});
	}
})();
