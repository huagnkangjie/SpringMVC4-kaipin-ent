<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/sys/header.jsp"%>
</head>
<body>
	<div id="loading">
	</div>
	<div class="wrap js-check-wrap">

		<form class="well form-search"  name="form-search" method="post"  action="#">
			激活状态： <select class="select_2" name="isCheckMail" value="${userInfo.isCheckMail }" id="isCheckMail">
				<option value=''>全部</option>
				<option value='1'>已激活</option>
				<option value='0'>未激活</option>
			</select> &nbsp;&nbsp; 注册时间： <input type="text" name="startTime"
				class="js-date" value="${startTime }" style="width: 80px;" autocomplete="off">-
			<input type="text" class="js-date" name="endTime" value="${endTime }"
				style="width: 80px;" autocomplete="off"> &nbsp; &nbsp; 企业名称：
			<input type="text" name="entName" value="${companyInfo.entName }" style="width: 200px;" value=""
				placeholder="请输入关键字..."> 
				<input type="submit" 	class="btn btn-primary" value="搜索" /> 
				
				<input type="hidden" 	id="pageNo"  name="pageNo"  /> 
		 
		</form>

		<form class="js-ajax-form" action="" method="post" id="position_info_list_form">

			<div class="table-actions">
				<button class="btn btn-primary btn-small js-ajax-submit"
					type="submit" data-action="/admin_post/listorders.html">排序</button>

				<button class="btn btn-primary btn-small js-ajax-submit"
					type="submit" data-action="/admin_post/check/check/1.html"
					data-subcheck="true">审核</button>
				<button class="btn btn-primary btn-small js-ajax-submit"
					type="submit" data-action="/admin_post/check/uncheck/1.html"
					data-subcheck="true">取消审核</button>
				<button class="btn btn-primary btn-small js-ajax-submit"
					type="submit" data-action="/admin_post/top/top/1.html"
					data-subcheck="true">置顶</button>
				<button class="btn btn-primary btn-small js-ajax-submit"
					type="submit" data-action="/admin_post/top/untop/1.html"
					data-subcheck="true">取消置顶</button>
				<button class="btn btn-primary btn-small js-ajax-submit"
					type="submit" data-action="/admin_post/recommend/recommend/1.html"
					data-subcheck="true">推荐</button>
				<button class="btn btn-primary btn-small js-ajax-submit"
					type="submit"
					data-action="/admin_post/recommend/unrecommend/1.html"
					data-subcheck="true">取消推荐</button>
				<button class="btn btn-primary btn-small js-ajax-submit"
					type="submit" data-action="/admin_post/delete.html"
					data-subcheck="true" data-msg="你确定删除吗？">删除</button>

				<button class="btn btn-primary btn-small js-articles-move"
					type="button">批量移动</button>
			</div>



			<table class="table table-hover table-bordered table-list">
				<thead>
					<tr>
						<th width="15"><label><input type="checkbox"
								class="js-check-all" data-direction="x"
								data-checklist="js-check-x"></label></th>

						<th width="210">职位名称</th>
						<th>地区</th>
						<th width="100">招聘人数</th>
						<th width="100">发布日期</th>
						<th >状态</th>
						<th>操作</th>

					</tr>
				</thead>
				<tbody class="kaipin-table">
					<c:forEach var="m"  items="${page.elements }" varStatus="index">
						<tr>
						<td><input type="checkbox" class="js-check"
							data-yid="js-check-y" data-xid="js-check-x" name="ids[]" value=""
							title=""></td>
						<td>
							 ${m.positionName}
						</td>
						<td data-location="${m.locationCode }">加载中...</td>
						<td width="50">${m.numbers }</td>
						<td width="150">${m.createTimes }</td>
						<td width="50">
							<c:if test="${m.isGuoQi == 0}">
								招聘中
							</c:if>
							<c:if test="${m.isGuoQi == 1}">
								<span style="color:red;">已过期</span>
							</c:if>
						</td>
						<td>
							<a href="${BASE_PATH}/management/position/detail?positionId=${m.id}&companyId=${companyId}">查看</a> 
						</td>
					</tr>
					
					
					<c:if test="${index.index == (fn:length(page.elements) -1)}">
								<!-- table 加载完毕重新渲染页面 -->
								<script type="text/javascript">
		 							$(function(){ 
		 								//区域代码
		 								var locationArray = $(".kaipin-table").find("tr");
		 								for(var i = 0; i < locationArray.length; i++){
		 									var $td = locationArray.eq(i).find("td").eq(2);
		 									var location = $td.data("location");
		 									if(isNotEmpty(location)){
		 										var name = getLocationName(location);
		 										$td.html(name);
		 									}else{
		 										$td.html("");
		 									}
		 								}
		 								
		 							}); 
		 							
		 							//获取区域名称
		 							function getLocationName(code){
		 								var name ="";
		 								$.ajax({                
		 									cache: false,    
		 									async: false, 
		 									type: "POST",                
		 									url:  '${BASE_PATH}/common/getLocationName',                
		 									data:{
		 										code : code
		 									},  
		 									beforeSend : function(request){
		 									},
		 									error: function(request) { 
		 										alert("网络错误");
		 									},                
		 									success: function(data) {
		 										name = data.obj;
		 									}           
		 								});
		 								
		 								return name;
		 							}
		 						</script> 
							</c:if>
					</c:forEach>
				</tbody>
			</table>
		</form>

		<%@ include file="/WEB-INF/sys/page.jsp"%>

	</div>

	<script src="${BASE_PATH}/public/js/common.js"></script>
	<script src="${BASE_PATH}/public/assets/js/company/company.js"></script>
	<script>
		function refersh_window() {
			var refersh_time = getCookie('refersh_time');
			if (refersh_time == 1) {
				window.location = "";
			}
		}
		setInterval(function() {
			refersh_window();
		}, 2000);
		$(function() {
			setCookie("refersh_time", 0);

		});
		
		//设置分页记录数
		$(function() {
			setCookie("_cookie_page_size", 8);

		});
		
		//设置参数
		var isCheckMail;
		$(function() {
			isCheckMail = '${isCheckMail}';
			$("#isCheckMail").val(isCheckMail);

		});
		
	</script>

</body>
</html>