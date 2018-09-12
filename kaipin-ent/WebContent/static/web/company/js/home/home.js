
	/*获取最近收到的简历列表*/
	function getResumeList(){
		$.ajax({                
			cache: false,                
			type: "POST",                
			url:  r_path + '/getResume',                
			async: true,                
			error: function(request) {                    
			},                
			success: function(data) {
				var datas = eval('('+data+')');
				var positionName = "";
				var resumeName = "";
				var surname ;
				var miss_surname ;
				var name = "";
				var major = "";
				var head_url = "";
				
				var relationId = "";
				var createTime = "";
				var id = "";
				if(datas.success){
					var html = "<div class='nep-lists'>";
					for(var i = 0; i < datas.obj.list.length; i++){
						resumeName = datas.obj.list[i].resume_name;
						surname = datas.obj.list[i].surname;
						miss_surname = datas.obj.list[i].miss_surname;
						relationId = datas.obj.list[i].relationId;
						id = datas.obj.list[i].id;
						if(surname != null && miss_surname!= null && surname != "" && miss_surname != ""){
							name = surname + miss_surname;
						}
						major = datas.obj.list[i].major_name;
						create_time = datas.obj.list[i].create_time;
						var createTimeStr = getTimeByMillis(create_time);
						var showTime = getDateDiff(getDateTimeStamp(createTimeStr));
						
						head_url = datas.obj.list[i].head_url;
						if(head_url == null || head_url == ''){
							head_url = r_path + "/static/web/university/images/default-hdPic.jpg";
						}
						
						var path = r_path + "/resume/detail?relationId="+relationId;
						
						html = html +   "<li>"+
										"	<div class='col-left'>"+
										"	    <a href='"+path+"'>" +
										"			<img src='"+head_url+"' width='35' height='35'>" +
										"		</a>"+
										"	</div>"+
										"	<div class='col-right'>"+
										"		<h2>" +
										"			<a href='"+path+"'>"
														+name +
										"			</a>" +
										"			<span class='timer'>"+showTime+"</span>"+
										"		</h2>"+
										"		<h3>"+major+"</h3>"+
										"	</div>"+
										"</li>";
					}
					
					if(datas.obj.list.length > 0){
						$("#resumeList5Div").show();
//						$("#resumeDatas").html("人才库（"+datas.obj.counts+"）");//收到简历总数
					}
					$("#resumeList5").empty();
					$("#resumeList5").append(html);
					//$("#resumeCounts").html("人才库（"+datas.obj.counts+"）");
					
					$(".show-resume").click(function(){
						var resumeId = $(this).data("tag");
						var relationId = $(this).data("gx");
						location.href= r_path + "/resume/detail?status=0&resumeId="+resumeId+"&relationId="+relationId;
					});
					$(".indexResume").click(function(){
						location.href=r_path+"/resume";
					});
				}
			}            
		});
	}
	
	
	/*人才库搜到简历统计*/
	function getResumeCount(){
		$.ajax({
			 cache : false,
			 async : true,
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
				$("#resumeDatas").html("人才库（"+ zero +"）");
				$("#resumeCounts").html(total);
			 }
		 });
	}