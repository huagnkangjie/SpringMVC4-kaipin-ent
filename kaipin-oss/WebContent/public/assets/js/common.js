$(function(){
	
	//obj:当前的对象
	function OpenNewWindow(obj){
		
		obj.on('click',function(){
			
		});
	}
	
	
	
	
	/*$("#xxxxx").on('click',function(){
		 //  alert(1);
		  // var val = $(this).html();
		  var $position = $(this).data('tag');
		  var isTrue = true;
		  var $oLi = $("#task-content-inner",window.parent.document).find('li');
	  	  var width1 = 118;
		   var len = $("#task-content-inner,#task-content", window.parent.document).find('li').length;
		   $("#task-content-inner,#task-content", window.parent.document).css("width",width1*(len+1));
		   var num=0;
		   var index = 0;
		   for(var i=0;i<$oLi.length;i++){
			   if($oLi.eq(i).hasClass($position)){
				   num++;
				   index = i;
				   break;
			   }
		   }
		   
		   if(num>=1){
			   isTrue = false; 
		   }
		   //http://localhost:8081/kaipin-oss/management/position/list?companyId=39f9d638-9346-4eac-914f-36b4e9410689
		  var url = '${BASE_PATH}/management/position/list?companyId=${companyId}';
		   if(isTrue){
			   $("#task-content-inner", window.parent.document).append('<li class="macro-component-tabitem '+$position+'" app-id="'+len+'" data-flag="open" app-url="http://localhost:8081/kaipin-oss/management/company/main/zzlist" app-name="企业资质审核1"><span class="macro-tabs-item-text" title="企业资质审核1">企业资质审核1</span><a class="macro-component-tabclose" href="javascript:void(0)" title="点击关闭标签"><span></span><b class="macro-component-tabclose-icon">×</b></a></li>');
			   $("#content",window.parent.document).append('<iframe src="'+url+'" style="width: 100%; height: 100%;" frameborder="0" id="appiframe-'+(len)+'" class="appiframe"></iframe>');
		   }else{
			   $("#content",window.parent.document).find('iframe').eq(index).remove();
			   $("#content",window.parent.document).append('<iframe src="'+url+'" style="width: 100%; height: 100%;" frameborder="0" id="appiframe-'+(len)+'" class="appiframe"></iframe>');
		   }
		   len = $("#task-content-inner,#task-content", window.parent.document).find('li').length;
		   
		   
		   if(len>=7){
			   $("#breadcrumbs",window.parent.document).find(".task-changebt").css('display',"inline");
			   $("#task-content", window.parent.document).css('width',920);
			   if(len==8){
				   $("#task-content-inner", window.parent.document).css({'width':width1*len,"marginLeft":-24});
			   }else{
				   $("#task-content-inner", window.parent.document).css({'width':width1*len,"marginLeft":-((len-8)*width1+24)});
			   }
		   }
		   $("#task-content-inner,#task-content", window.parent.document).find('li').eq(len-1).addClass('current').siblings().removeClass('current');
		   $("#content",window.parent.document).find('iframe').eq(len-1).show().siblings().hide();
		  
		   
	   });*/
	
	
	
});