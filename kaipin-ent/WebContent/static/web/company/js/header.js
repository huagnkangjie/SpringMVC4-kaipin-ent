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


