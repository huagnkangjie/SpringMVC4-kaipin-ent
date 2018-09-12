function getPositionListDataStr(/*行数*/rows){
	var dataStr = "";
	$.ajax({                
		cache: true,    
		async: false, 
		type: "POST",                
		url:  r_path + '/position/datagridIndex',                
		data:{
			page : 1,
			rows : rows
		},              
		error: function(request) {                    
		},                
		success: function(data) {
		},
		complete: function(data){
			dataStr = data.responseText;
		}
	});
	return dataStr;
}

function eachPositionList(/*职位数组*/data,/*查询类型*/type,/*工作地区*/workArea,/*公司简称*/simpelEntName){
	debugger;
	if(isNotEmpty(data)){
		var datas = eval('('+data+')')
		if(datas.rows.length == 0 || datas.rows.length == 1){
			$("#company-info-div").removeClass("bd-bottom");
		}else{
			$("#company-postion-list").show()
			var li = "";
			var positionName = "";
			var showTime = "";
			var url = "";
			var id = "";
			var operLen = "0"; 
			for(var i = 0; i < datas.rows.length; i ++){
				id = datas.rows[i].id;
				if(type == "xiangqing"){
					var v = $("#each-positionId").val();
					if(id == v){
						operLen = "1";
						continue;
					}
				}
				if(i == 3 && operLen == "0"){
					break;
				}
				
				positionName = datas.rows[i].position_name;
				showTime = getDateDiff(datas.rows[i].create_time);
				url = r_path + "/position/detail?postionId=" + id;
				li = li +   "<li>"+
								"<div class='pi-logo'><a href='"+url+"'><img src='"+logo+"' width='70' height='70'/></a></div>"+
								"<div class='pi-txt'><a href='"+url+"' class=''>"+positionName+"</a></div>"+
								"<p class='short-name'>"+simpelEntName+"</p>"+
								"<p class='adress'>"+workArea+"</p>"+
								"<span class='send-time'>"+showTime+"</span>"+
							"</li>";
			}
			$("#company-postion-list-ul").empty();
			$("#company-postion-list-ul").append(li);
		}
	}
}