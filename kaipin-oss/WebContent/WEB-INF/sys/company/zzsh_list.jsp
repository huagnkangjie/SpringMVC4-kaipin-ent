<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/sys/header.jsp"%>
</head>
<body>

	<div class="wrap js-check-wrap">

		<form class="well form-search"  name="form-search" method="post"  action="${BASE_PATH }/management/company/main/zzlist">
			分类： <select class="select_2" name="verifyStatus" id="verifyStatus" >
				<option value=''>全部</option>
				<option value='0'>未审核</option>
				<option value='2'>审核已通过</option>
				<option value='1'>审核不通过</option>
			</select> &nbsp;&nbsp; 注册时间： <input type="text" name="startTime"
				class="js-date" value="${startTime }" style="width: 80px;" autocomplete="off">-
			<input type="text" class="js-date" name="endTime" value="${endTime }"
				style="width: 80px;" autocomplete="off"> &nbsp; &nbsp; 企业名称：
			<input type="text" name="entName" style="width: 200px;" value="${companyInfo.entName }"
				placeholder="请输入关键字..."> 
				<input type="submit" 	class="btn btn-primary" value="搜索" /> 
				
				<input type="hidden" 	id="pageNo"  name="pageNo"  /> 
				
			<!-- 	<input type="hidden" name="pageSize"  id="pageSize"
		 /> -->
		 
		 
		</form>

		<form class="js-ajax-form" action="" method="post">

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

						<th>企名</th>
						<th>地区</th>
						<th width="50">联人</th>
						<th width="50">电话</th>
						<th width="50">提次</th>
						<th width="50">状态</th>
						<th>操作</th>

					</tr>
				</thead>
				<tbody class="kaipin-table">
					<c:forEach var="m"  items="${page.elements }" varStatus="index">
						<tr data-tag="${m.companyInfo.entName}">
							<td><input type="checkbox" class="js-check"
								data-yid="js-check-y" data-xid="js-check-x" name="ids[]" value=""
								title=""></td>
							<td>
								 ${m.companyInfo.entName}
								 <c:choose>
									   <c:when test="${m.companyInfo.entSimpleName != ''}"> <span style="color: #39C;">${m.companyInfo.entSimpleName}</span>
									   </c:when>
									   <c:otherwise><span style="color: #39C"><span style="color:red">未填</span></span>
									   </c:otherwise>
								</c:choose>
							</td>
							<td data-location="${m.companyInfo.officeArea }">加载中...</td>
							<td width="50">${m.userInfo.surname }${m.userInfo.missSurname }</td>
							<td width="50">${m.localUser.phone }</td>
							<td width="50">${m.documentCount }</td>
							<td width="80" align="center">
								<c:if test="${m.companyInfo.verifyStatus == 0}">
									未审核
								</c:if>
								<c:if test="${m.companyInfo.verifyStatus == 1}">
									<span style="color:red;">不通过</span>
								</c:if>
								<c:if test="${m.companyInfo.verifyStatus == 2}">
									<span style="color:#CFCFCF;">已通过</span>
								</c:if>
							</td>
		
							<td>
								<a href="#" data-tag="${m.companyInfo.id}" data-name="${m.companyInfo.entName}" class="show-zz">查看</a> 
<%-- 								<a target="_blank" href="${BASE_PATH}/management/company/main/zzxq?companyId=${m.companyInfo.id}">查看</a> |  --%>
								<!-- <a href="#" class="js-ajax-delete">通过</a> | 
								<a href="#" class="js-ajax-delete">不通过</a> -->
							</td>
						</tr>
						<c:if test="${index.index == (fn:length(page.elements) -1)}">
							<!-- table 加载完毕重新渲染页面 -->
							<script type="text/javascript">
	 							$(function(){ 
	 								//查看按钮
	 								$(".show-zz").click(function(){
	 									var id = $(this).data("tag");
	 									var entName = $(this).data("name");
	 									var openUrl = '${BASE_PATH}/management/company/main/zzxq?companyId=' + id;//弹出窗口的url
	 									var iWidth=800; //弹出窗口的宽度;
	 									var iHeight=600; //弹出窗口的高度;
	 									var iTop = (window.screen.availHeight-30-iHeight)/2; //获得窗口的垂直位置;
	 									var iLeft = (window.screen.availWidth-10-iWidth)/2; //获得窗口的水平位置;
	 									OpenWindow = window.open(openUrl,"1","height="+iHeight+", width="+iWidth+", top="+iTop+", left="+iLeft);
	 									//window.open('http://www.baidu.com','','width=600,height=500,left=10, top=10,toolbar=no, status=no, menubar=no, resizable=yes, scrollbars=yes');return false;
	 									setTimeout(function(){
	 										OpenWindow.document.title = entName;
	 									},500);
	 									
	 								});
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
		var verifyStatus;
		$(function() {
			verifyStatus = '${verifyStatus}';
			$("#verifyStatus").val(verifyStatus);

		});
		
	</script>

</body>
</html>