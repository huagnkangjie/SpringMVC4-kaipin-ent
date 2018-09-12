/*****************************/
/**** 用于header页面的公用js******/
/**** 主要是查询          *******/

$(function(){
	
});

	/*立即认证提示页面*/
	function certificateTipPage(oper){
		var userId = $("#userId").val();
		var companyId = $("#companyId").val();
		if(oper != '99'){
			location.href =r_path + "/regedit/certificate.do?target=certificate_show&userId="+userId+"&companyId="+companyId+"&checkType="+oper;
		}else{
			location.href =r_path + "/regedit/certificate.do?userId="+userId+"&companyId="+companyId;
		}
	}


	/*人才库搜到简历统计*/
	function getResumeCount(){
		$.ajax({
			 cache : false,
			 async : false,
			 url : r_path + '/resume/counts.do',
			 data : {
				 
			 },
			 success : function(data){
				var datas = eval('('+data+')');
				var zero = datas.obj.zero;
				var one = datas.obj.one;
				var two = datas.obj.two;
				var three = datas.obj.three;
				var total = datas.obj.total;
				$("#zero").html(zero);
				$("#one").html(one);
				$("#two").html(two);
				$("#three").html(three);
				$("#total").html(total);
				$("#resumeDatas").html("人才库（"+ zero +"）")
			 }
		 });
	}
