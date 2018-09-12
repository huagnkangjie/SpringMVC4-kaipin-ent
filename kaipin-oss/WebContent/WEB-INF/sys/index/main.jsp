<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/sys/header.jsp"%>
 <script src="${BASE_PATH}/public/assets/js/count/count.js"></script>
<style>
.home_info li em {
	float: left;
	width: 120px;
	font-style: normal;
}

li {
	list-style: none;
}
</style>
</head>
<body>
	<%-- <div class="wrap">
		<div id="home_toptip"></div>
		<h4 class="well">系统通知</h4>
		<div class="home_info">
			<ul id="thinkcmf_notices">
				<li><img
					src="${CONTEXT_PATH}/public/assets/images/loading.gif"
					style="vertical-align: middle;" /><span
					style="display: inline-block; vertical-align: middle;">加载中...</span></li>
			</ul>
		</div>
		<h4 class="well">系统信息</h4>
		<div class="home_info">
			<ul>
				<li><em>操作系统</em> <span>Linux</span></li>
				<li><em>运行环境</em> <span>nginx/1.8.1</span></li>
				<li><em>PHP运行方式</em> <span>fpm-fcgi</span></li>
				<li><em>MYSQL版本</em> <span>5.7.11</span></li>
				 
				<li><em>上传附件限制</em> <span>2M</span></li>
				<li><em>执行时间限制</em> <span>30s</span></li>
				<li><em>剩余空间</em> <span>13353.95M</span></li>
			</ul>
		</div>
		<h4 class="well">发起团队</h4>
		<div class="home_info" id="home_devteam">
			<ul>
				<li><em>ThinkCMF</em> <a href="http://www.thinkcmf.com"
					target="_blank"> </a></li>
				<li><em>团队成员</em> <span>Dean,Sam,Tuolaji,Smile,Codefans,睡不醒的猪,Jack,日本那只猫</span></li>
				<li><em>联系邮箱</em> <span>cmf@simplewind.net</span></li>
			</ul>
		</div>
		<h4 class="well">贡献者</h4>
		<div class="">
			<ul class="inline" style="margin-left: 25px;">
				<li>Kin Ho</li>
				<li><a href="#" target="_blank">Powerless</a></li>
				<li>Jess</li>
				<li>木兰情</li>
				<li><a href="#" target="_blank">Labulaka</a></li>
				<li><a href="#" target="_blank">WelKinVan</a></li>
				<li><a href="#"
					target="_blank">Jeson</a></li>
				<li>Yim</li>
				<li><a href="#" target="_blank">Jamlee</a></li>
				<li><a>香香咸蛋黄</a></li>
				<li><a>小夏</a></li>
				<li><a href="#" target="_blank">小凯</a></li>
				<li><a href="#" target="_blank">Co</a></li>
			</ul>
		</div>
	</div> --%>
	
	<div id="loading" style="margin:0 auto;"></div>
	</br>
	
	<div style="margin:0 auto;text-align: center;">
		<table >
			<tr>
				<td style=" margin-left:30px;float:left"><h5 style=" margin:0; font-size:18px">${companyCount }</h5>企业总数</td>
                <td style=" margin-left:30px;float:left"><h5 style=" margin:0; font-size:18px">${studentCount }</h5>学生总数</td>
                <td style=" margin-left:30px;float:left"><h5 style=" margin:0; font-size:18px">${schoolCount }</h5>学校总数</td>
				<td style=" margin-left:30px;float:left"><h5 style=" margin:0; font-size:18px">${postionCount }</h5>职位总数</td>
				<td style=" margin-left:30px;float:left"><h5 style=" margin:0; font-size:18px">${resumeCount }</h5>简历总数</td>
				<td style=" margin-left:30px;float:left"><h5 style=" margin:0; font-size:18px">${positionDeliveryCount }</h5>已投职位数</td>
				<td style=" margin-left:30px;float:left"><h5 style=" margin:0; font-size:18px">${resumeDeliveryCount }</h5>已投简历数</td>
			</tr>
			<tr>
				<td colspan="6" align="right">
					
				</td>
			</tr>
		</table>
		<span></span>
		<div style="float:right;text-align: right;height: 25px;">
			<form class="well form-search"  name="form-search" method="post"  action="${BASE_PATH }/management/company/main/list">
				<span id="conditions" style="display: none;">注册时间： <input type="text" name="startTime"
						class="js-date" id="startTime" value="${startTime }" style="width: 80px;" autocomplete="off">-
					<input type="text" id="endTime" class="js-date" name="endTime" value="${endTime }"
						style="width: 80px;" autocomplete="off"> &nbsp; &nbsp; 
						<input type="button" 	class="btn btn-primary" value="统计" onclick="getTimeToTimeCount();" /> 
						
						<input type="hidden" 	id="pageNo"  name="pageNo"  /> 
				</span>
			 	<select id="selectDimension" onchange="getCount(this.options[this.options.selectedIndex].value);">
					<option value="nearMonth">最近一个月</option>
					<!-- <option value="monthActive">月活跃量</option> -->
					<option value="week">周</option>
					<option value="month">月</option>
					<option value="quarter">季度</option>
					<option value="year">年</option>
					<option value="timeToTime">时间段</option>
				</select>
			 
			</form>
			
		</div>
		<span style="margin-left:20px;"></span>
		<span style="margin-left:20px;"></span>
		<span style="margin-left:20px;"></span>
		<span style="margin-left:20px;"></span>
		<span style="margin-left:20px;"></span>
	</div>
	</br>
	<div id="nearMonthCount" class="indexCount" style="margin-top: 15px;width:100%"></div>
	<div id="weekCount" class="indexCount" style="margin-top: 15px;width:100%"></div>
	<div id="monthCount" class="indexCount" style="margin-top: 15px;width:100%"></div>
	<div id="quarterCount" class="indexCount" style="margin-top: 15px;width:100%"></div>
	<div id="yearCount" class="indexCount" style="margin-top: 15px;width:100%"></div>
	<div id="timeToTimeCount" class="indexCount" style="margin-top: 15px;width:100%"></div>
	<div id="monthActiveCount" class="indexCount" style="margin-top: 15px;width:100%"></div>
	<script src="${CONTEXT_PATH}/public/js/common.js"></script>
	<script src="${CONTEXT_PATH}/public/js/highcharts/highcharts.js"></script>
	<script src="${CONTEXT_PATH}/public/js/highcharts/highcharts-3d.js"></script>
	<script type="text/javascript">
	var xStr ;
	var yStr ;
	var yStuStr ;
	var ySchStr ;
	var xArray ;
	var yArrayObj ;
	var yStuArrayObj ;
	var ySchArrayObj ;
	var yEntArray = [];
	var yStuArray = [];
	var ySchArray = [];
	$(function () {
		xStr = '${xArray}';
		yStr = '${yArray}';
		yStuStr = '${yStuArray}';
		ySchStr = '${ySchArray}';
		xArray = xStr.split(",");
		yArrayObj = yStr.split(",");
		yStuArrayObj = yStuStr.split(",");
		ySchArrayObj = ySchStr.split(",");
		for(var i = 0; i < yArrayObj.length; i++){
			yEntArray.push(parseInt(yArrayObj[i]));
		}
		for(var i = 0; i < yStuArrayObj.length; i++){
			yStuArray.push(parseInt(yStuArrayObj[i]));
		}
		for(var i = 0; i < ySchArrayObj.length; i++){
			ySchArray.push(parseInt(ySchArrayObj[i]));
		}
		
		setLine("nearMonthCount", xArray, yStuArray, yEntArray, ySchArray, "平台用户注册统计（最近一个月）");
	});
	
	
	</script>

</body>
</html>