/*
 * 
 *	作者：891779683@qq.com
 * 	描述：解决低版本浏览器（IE）输入框（input）不兼容placeholder问题
 * 
 */
(function(){
	function change(obj, alt, val) {
	    obj.hide();
	    alt.show();
	    alt.val(val);
	}
	function placeHolder() {
		//holder获取所有文本框返回jquery对象，然后使用.each()方法对其进行遍历
	    var holder = $("input[type = 'text']").not("[class = 'text']"),
	        texts = $(".text"),
	        passwords = $(".password"),
	        note = passwords.attr("placeholder");
	    change(passwords, texts, note);
	    texts.on("focus",function(){
	        change(texts, passwords, "");
	        passwords.focus();  
	    });
	    passwords.on("blur",function(){
	        if($(this).val() == ""){
	            change(passwords, texts, note);
	        }
	    });
	    holder.each(function () {
	        var that = $(this);
	        var note = that.attr("placeholder")
	        that.val(note);
	        that.on({
	            "focus": function () {  
	                if (that.val() == note) {
	                    that.val("");
	                }
	            },
	            "blur": function () {
	                if (that.val() == "") {
	                    that.val(note);
	                }
	            }
	        });
	    });
	};
	    //能力检测
	    function isPlaceHolder(){
	        return "placeholder" in document.createElement("input");
	    }
	    if(!isPlaceHolder()){
	        placeHolder();
	    }
 })();