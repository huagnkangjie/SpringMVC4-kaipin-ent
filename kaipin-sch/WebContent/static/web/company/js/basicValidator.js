$(document).ready(function(){
//	$.formValidator.initConfig({formID:"form1",theme:"DefaultEx",wideWord:true,
//        onError:function(msg){},
//        onSuccess:function(){return true;},
//        ajaxPrompt : '有数据正在进行验证，请稍等...'});
//	 $("#themeName").formValidator({onCorrect:"",onShowText:""})
//	     .functionValidator({fun:function(val,obj){
//	          if(val != ''){
//	        	  if(val.length > 2){
//	        		  $("#themeName").addClass("error-input");
//	        		return false;
//	        	  }else{
//	        		  $("#themeName").removeClass("error-input");
//	        	  }
//	          }
//	         },onError:""
//	        	 
//	    });
	
	$("#themeName").keyup(function(){
		var val = $("#themeName").val();
		var length = val.length;
		if(val != ''){
			if(length > 32){
				val = val.substring(0,32);
				 $("#themeName").val(val);
			}
		}
	});
	
	$("#detailXjh").keyup(function(){
		var val = $("#detailXjh").val();
		var length = val.length;
		if(val != ''){
			if(length > 5000){
				val = val.substring(0,5000);
				 $("#detailXjh").val(val);
			}
		}
	});
});

