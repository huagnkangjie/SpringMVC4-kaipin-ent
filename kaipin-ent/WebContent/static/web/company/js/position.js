$(function(){
	
	/*点击职能一级加载第二级*/
	$("#jobTypeList1 a").click(function(){
		$("#jobTpyeSelect1").removeClass("error-input");
		var code = $(this).data("tag");
		$("#jobType").val("");
		$("#jobTypeList2Select").html("请选择");
		var tName = " comm_job_type ";
		$("#jobTypeList2").empty();
		var params = " parent_code = '"+ code +"'";
		var jobType2 = commonList(tName,params);
		getJobType2(jobType2);
	});
	
	
	//加载职能2级
	function getJobType2(jobType2){
		var datas = eval('('+jobType2+')');
		$("#jobType").val("");
		if(datas.obj.length > 0){
			$("#jobTypeList2").empty();
			var html = "";
			for(var i = 0; i < datas.obj.length; i++){
				html = html + "<a class='jobtpye2-val' data-tag='"+datas.obj[i].job_type_code+"' href='javascript:void(0)'>"+datas.obj[i].job_type_name+"</a>";
			}
			$("#jobTypeList2").append(html);
			
			//加载点击事件
			$(".jobtpye2-val").click(function(){
				$("#jobTpyeSelect2").removeClass("error-input");
				var code = $(this).data("tag");
				$("#jobType").val(code);
			});
		}
	}
	
	/*点击省加载市区  发布*/
	$("#workAreaLists a").click(function(){
		$("#workArea1").removeClass("error-input");
		var code = $(this).data("tag");
		$("#workAreaProvice").val(code);
		$("#cityList").empty();
		$("#workArea").val("");
		$("#cityListVal").val("");
		$("#cityName").html("(市)");
		var tName = " comm_location ";
		$("#jobTypeList2").empty();
		var params = " parent_code = '"+ code +"' or location_code = '"+ code +"'";
		var citys = commonList(tName,params);
		cityListsPulish(citys);
	});
	
	
	/*点击省加载市区  筛选*/
	$("#workAreaListsSelect a").click(function(){
		$("#workArea1").removeClass("error-input");
		var code = $(this).data("tag");
		$("#workAreaProvice").val(code);
		$("#cityList").empty();
		$("#workArea").val("");
		$("#cityListVal").val("");
		$("#cityName").html("(市)");
		var tName = " comm_location ";
		$("#jobTypeList2").empty();
		var params = " parent_code = '"+ code +"' or location_code = '"+ code +"'";
		var citys = commonList(tName,params);
		cityLists(citys);
	});
	
	/**
	 * 获取市区 职位发布
	 */
	function cityListsPulish(citys){
		var datas = eval('('+citys+')');
		$("#workArea").val("");
		if(datas.obj.length > 0){
			$("#cityList").empty();
			var html = "";
			for(var i = 0; i < datas.obj.length; i++){
				var name = datas.obj[i].location_name;
				var code = datas.obj[i].location_code;
				html = html + "<a href='javascript:void(0)' class='change' data-tag='"+code+"' >"+ name +"</a>";
			}
			$("#cityList").append(html);
			
			//加载点击事件
			$(".change").click(function(){
				$("#workArea2").removeClass("error-input");
				var code = $(this).data("tag");
				$("#workArea").val(code);
			});
		}
	}
	/**
	 * 获取市区 条件查询
	 */
	function cityLists(citys){
		var datas = eval('('+citys+')');
		$("#workArea").val("");
		if(datas.obj.length > 0){
			$("#cityList").empty();
			var html = "";
			for(var i = 1; i < datas.obj.length; i++){
				var name = datas.obj[i].location_name;
				var code = datas.obj[i].location_code;
				html = html + "<a href='javascript:void(0)' class='change' data-tag='"+code+"' >"+ name +"</a>";
			}
			$("#cityList").append(html);
			
			//加载点击事件
			$(".change").click(function(){
				$("#workArea2").removeClass("error-input");
				var code = $(this).data("tag");
				$("#workArea").val(code);
			});
		}
	}
	
	/*所属行业点击去掉红色边框*/
	$("#industryTypeList a").click(function(){
		$("#industryTypeSelect").removeClass("error-input");
	});
	
	$("#educationList a").click(function(){
		$("#eduSelect").removeClass("error-input");
	});
	
	$("#industryTypeList a").click(function(){
		$("#industryTypeSelect").removeClass("error-input");
	});
	
	$("#industryTypeList a").click(function(){
		$("#industryTypeSelect").removeClass("error-input");
	});
	
	/*******************************************************************************/
	/**数据验证*/
	$.formValidator.initConfig({formID:"form1",theme:"DefaultEx",wideWord:true,
        onError:function(msg){},
        onSuccess:function(){return true;},
        ajaxPrompt : '有数据正在进行验证，请稍等...'});
	
	 /*职位*/
	 $("#positionName").formValidator({onCorrect:" ",onShowText:""})
	 	.functionValidator({fun:function(val,obj){
			if($.trim(val).length > 0){
				if(val.length > 18){
					$("#positionName").val(val.substring(0,18));
				}
				removeCss("positionName");
				return true;
			}else{
				addCss("positionName");
				return false;
			}
		}
	 });
	 
	 /*所属行业*/
	 $("#industryType").formValidator({onCorrect:" ",onShowText:""})
	 	.functionValidator({fun:function(val,obj){
	 		if(val.length > 0){
	 			removeCss("industryTypeSelect");
	 			return true;
	 		}else{
	 			addCss("industryTypeSelect");
	 			return false;
		 }
	 	}
	 });
	 
	 /*学历*/
	 $("#education").formValidator({onCorrect:" ",onShowText:""})
	 .functionValidator({fun:function(val,obj){
		 if(val.length > 0){
			 removeCss("eduSelect");
			 return true;
		 }else{
			 addCss("eduSelect");
			 return false;
		 }
	 }
	 });
	 
	 /*岗位职能*/
	 $("#jobType").formValidator({onCorrect:" ",onShowText:""})
		 .functionValidator({fun:function(val,obj){
			 if(val.length > 0){
				 removeCss("jobTpyeSelect1");
				 removeCss("jobTpyeSelect2");
				 return true;
			 }else{
				 addCss("jobTpyeSelect1");
				 addCss("jobTpyeSelect2");
				 return false;
			 }
		 }
	 });
	 
	 /*有效期*/
	 $("#endTime").formValidator({onCorrect:" ",onShowText:""})
	 	.functionValidator({fun:function(val,obj){
			if(val.length > 0){
				if(val.length > 30){
					$("#endTime").val(val.substring(0,30));
				}
				removeCss("endTimeList");
				return true;
			}else{
				addCss("endTimeList");
				return false;
			}
		}
	 });
	 
	 $(".endtime a").click(function (){
		 removeCss("endTimeList");
	 });
	 
	 /*年龄要求--开始*/
	 $("#ageStart").formValidator({onCorrect:" ",onShowText:""})
		 .functionValidator({fun:function(val,obj){
			 if(val.length > 0){
				 var str = /^[1-9]\d*$/;
				 if(str.test(val)){
					 if(val.length > 2){
						 $("#ageStart").val(val.substring(0,2));
					 }
					 removeCss("ageStart");
					 return true;
				 }else{
					 addCss("ageStart");
					 return false;
				 }
			 }else{
				 addCss("ageStart");
				 return false;
			 }
		 }
	 });
	 /*年龄要求--结束*/
	 $("#ageEnd").formValidator({onCorrect:" ",onShowText:""})
		 .functionValidator({fun:function(val,obj){
			 var ageEnd = 0;
			 var ageStart = 0;
			 var ageStartStr = "";
			 var ageEndStr = "";
			 if(val.length > 0){
				 var str = /^[1-9]\d*$/;
				 if(str.test(val)){
					 if(val.length > 2){
						ageEnd = parseInt(val.substring(0,2));
						$("#ageEnd").val(val.substring(0,2));
					 }else{
						ageEnd = parseInt(val);
					 }
					ageStartStr = $("#ageStart").val();
					if(ageStartStr.length > 0){
						ageStart = parseInt(ageStartStr);
					}
					if(ageEnd > ageStart){
						removeCss("ageEnd");
						return true;
					}else{
						addCss("ageEnd");
						return false;
					}
				 }else{
					 addCss("ageEnd");
					 return false;
				 }
			 }else{
				 addCss("ageEnd");
				 return false;
			 }
		 }
	 });
	 
	 /*性别要求*/
	 $("#sexs").formValidator({onCorrect:" ",onShowText:""})
		 .functionValidator({fun:function(val,obj){
			 if(val.length > 0){
				 removeCss("sexSelect");
				 return true;
			 }else{
				 addCss("sexSelect");
				 return false;
			 }
		 }
	 });
	 $(".sex a").click(function(){
		 var val = $(this).data("tag");
		 $("#sexs").val(val);
		 removeCss("sexSelect"); 
	 });
	 
	 /*工作区域*/
	 $("#workArea").formValidator({onCorrect:" ",onShowText:""})
		 .functionValidator({fun:function(val,obj){
			 if(val.length > 0){
				 removeCss("workArea1");
				 removeCss("workArea2");
				 return true;
			 }else{
				 addCss("workArea1");
				 addCss("workArea2");
				 return false;
			 }
		 }
	 });
	 
	 /*汇报对象*/
	 $("#reporting").formValidator({onCorrect:" ",onShowText:""})
		 .functionValidator({fun:function(val,obj){
			 if(val.length > 0){
				 if(val.length > 16){
					 $("#reporting").val(val.substring(0,16));
					 alert("汇报对象字数超过16个");
				 }
			 }
				 
		 }
	 });
	 
	 /*所属部门*/
	 $("#department").formValidator({onCorrect:" ",onShowText:""})
		 .functionValidator({fun:function(val,obj){
			 if(val.length > 0){
				 if(val.length > 16){
					 $("#department").val(val.substring(0,16));
					 alert("所属部门字数超过16个");
				 }
			 }
			 
		 }
	 });
	 
	 /*专业要求*/
	 $("#professionalRequirements").formValidator({onCorrect:" ",onShowText:""})
		 .functionValidator({fun:function(val,obj){
			 if(val.length > 0){
				 if(val.length > 16){
					 $("#professionalRequirements").val(val.substring(0,16));
					 alert("专业要求字数超过16个");
				 }
			 }
			 
		 }
	 });
	 
	 
	 /*年假福利*/
	 $("#yearHoliday").formValidator({onCorrect:" ",onShowText:""})
	 .functionValidator({fun:function(val,obj){
		 if(val.length > 0){
			 if(val.length > 10){
				 $("#yearHoliday").val(val.substring(0,10));
				 alert("年假福利字数超过10个");
			 }
		 }
		 
	 }
	 });
	 
	 
	 /*月薪构成*/
	 $("#salaryForms").formValidator({onCorrect:" ",onShowText:""})
	 .functionValidator({fun:function(val,obj){
		 if(val.length > 0){
			 if(val.length > 16){
				 $("#salaryForms").val(val.substring(0,16));
				 alert("月薪构成字数超过16个");
			 }
		 }
		 
	 }
	 });
	 
	 /*社保福利*/
	 $("#socialSecurity").formValidator({onCorrect:" ",onShowText:""})
	 .functionValidator({fun:function(val,obj){
		 if(val.length > 0){
			 if(val.length > 16){
				 $("#socialSecurity").val(val.substring(0,16));
				 alert("社保福利字数超过16个");
			 }
		 }
		 
	 }
	 });
	 
	 /*居住福利*/
	 $("#live").formValidator({onCorrect:" ",onShowText:""})
	 .functionValidator({fun:function(val,obj){
		 if(val.length > 0){
			 if(val.length > 16){
				 $("#live").val(val.substring(0,16));
				 alert("居住福利字数超过16个");
			 }
		 }
		 
	 }
	 });
	 
	 /*通讯交通*/
	 $("#callTraffic").formValidator({onCorrect:" ",onShowText:""})
	 .functionValidator({fun:function(val,obj){
		 if(val.length > 0){
			 if(val.length > 16){
				 $("#callTraffic").val(val.substring(0,16));
				 alert("通讯交通字数超过16个");
			 }
		 }
		 
	 }
	 });
	 
//	 /*工作职责*/
//	 $("#job-respons").formValidator({onCorrect:" ",onShowText:""})
//		 .functionValidator({fun:function(val,obj){
//			 if(val.length > 0){
//				 removeCss("job-respons-select");
//				 return true;
//			 }else{
//				 addCss("job-respons-select");
//				 return false;
//			 }
//		 }
//	 });
//	 
//	 /*任职要求*/
//	 $("#job-required").formValidator({onCorrect:" ",onShowText:""})
//		 .functionValidator({fun:function(val,obj){
//			 if(val.length > 0){
//				 removeCss("job-required-select");
//				 return true;
//			 }else{
//				 addCss("job-required-select");
//				 return false;
//			 }
//		 }
//	 });
	 
	 /**
	  * 薪资样式控制
	  */
	 $("#salaryTypeSelect a").click(function(){
		 var type = $(this).data("tag");
		 if(type == "0"){
			 $("#salaryStart").val("");
			 $("#salaryEnd").val("");
			 $("#salaryStart").attr("disabled","disabled");
			 $("#salaryEnd").attr("disabled","disabled");
			 $("#salarySubmitType").val("0");
			 removeCss("salaryStart");
			 removeCss("salaryEnd");
		 }else{
			 $("#salarySubmitType").val(type);
			 $("#salaryStart").val("");
			 $("#salaryEnd").val("");
			 $("#salaryStart").removeAttr("disabled");
			 $("#salaryEnd").removeAttr("disabled");
			 removeCss("salaryStart");
			 removeCss("salaryEnd");
		 }
	 });
	 
	/* 薪资起*/
	 $("#salaryStart").formValidator({onCorrect:" ",onShowText:""})
		 .functionValidator({fun:function(val,obj){
			 var type =  $("#salarySubmitType").val();
			 if(type == "1" || type == "2"){
				 if(val.length > 0){
						var ss = $("#salaryStart").val();
						var se = $("#salaryEnd").val();
						var str = /^[1-9]\d*$/;
						if(str.test(ss)){
							if(se.length > 0){
								var start = parseInt(ss);
								var end = parseInt(se);
								if(start >= end){
									$("#salaryStart").val("");
									addCss("salaryStart");
									return false;
								}else{
									removeCss("salaryStart");
									return true;
								}
							}else{
								if(ss.length > 10){
									 $("#salaryStart").val(ss.substring(0,9));
									 removeCss("salaryStart");
									 return true;
								 }else{
									 $("#salaryStart").val(ss);
									 removeCss("salaryStart");
									 return true;
								 }
							}
						}else{
							$("#salaryStart").val("");
							addCss("salaryStart");
							return false;
							
						}
					 }else{
						 $("#salaryStart").val("");
						 addCss("salaryStart");
						 return false;
					 } 
			 }
		 }
	 });
	 /* 薪资止*/
	 $("#salaryEnd").formValidator({onCorrect:" ",onShowText:""})
		 .functionValidator({fun:function(val,obj){
			 var type =  $("#salarySubmitType").val();
			 if(type == "1" || type == "2"){
				 if(val.length > 0){
						var ss = $("#salaryStart").val();
						var se = $("#salaryEnd").val();
						var str = /^[1-9]\d*$/;
						if(str.test(se)){
							if(ss.length > 0){
								var start = parseInt(ss);
								var end = parseInt(se);
								if(start >= end){
									$("#salaryEnd").val("");
									addCss("salaryEnd");
									return false;
								}else{
									removeCss("salaryEnd");
									return true;
								}
							}else{
								if(se.length > 10){
									 $("#salaryEnd").val(ss.substring(0,9));
									 removeCss("salaryEnd");
									 return true;
								 }else{
									 $("#salaryEnd").val(ss);
									 removeCss("salaryEnd");
									 return true;
								 }
							}
						}else{
							$("#salaryEnd").val("");
							addCss("salaryEnd");
							return false;
							
						}
					 }else{
						 $("#salaryEnd").val("");
						 addCss("salaryEnd");
						 return false;
					 }
			 }
		 }
	 });
	 
	 
	 
	 /* 招聘人数*/
	 $("#numbers").keyup(function(){
		 var v = $("#numbers").val();
		 var str = /^[1-9]\d*$/;
		 if(str.test(v)){
			 if(v.length > 10){
				 $("#numbers").val(v.substring(0,9));
			 }else{
				 $("#numbers").val(v);
			 }
		 }else{
			 $("#numbers").val("");
		 }
		 
	 });
	 
	 /* 下属人数*/
	 $("#departmentNumbers").keyup(function(){
		 var v = $("#departmentNumbers").val();
		 var str = /^[1-9]\d*$/;
		 if(str.test(v)){
			 if(v.length > 10){
				 $("#departmentNumbers").val(v.substring(0,9));
			 }else{
				 $("#departmentNumbers").val(v);
			 }
		 }else{
			 $("#departmentNumbers").val("");
		 }
		 
	 });
	 
	 /*获取年薪*/
	 $(".salary-year a").click(function(){
		 var val = $(this).data("tag");
		 $("#salaryYear").val(val);
	 });
	 
	 /* 企业规模*/
	 $("#numbers").keyup(function(){
		 var v = $("#numbers").val();
		 var str = /^[1-9]\d*$/;
		 if(str.test(v)){
			 if(v.length > 10){
				 $("#numbers").val(v.substring(0,9));
			 }else{
				 $("#numbers").val(v);
			 }
		 }else{
			 $("#numbers").val("");
		 }
		 
	 });
	 
	 /*移除css*/
	 function removeCss(obj){
		 $("#" + obj).removeClass("error-input");
	 }
	 /*添加css*/
	 function addCss(obj){
		 $("#" + obj).addClass("error-input");
	 }
	
});



