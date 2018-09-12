<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/sys/header.jsp"%>
</head>
<body>

	<div class="wrap js-check-wrap">
		<ul class="nav nav-tabs">
			<li class="active"><a href="javascript:;">管理</a></li>
			<li><a href="add" target="_self">添加</a></li>
		</ul>

		<form class="well form-search"  name="form-search" method="post"  action="${BASE_PATH }/management/company/main/list">
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
				
			<!-- 	<input type="hidden" name="pageSize"  id="pageSize"
		 /> -->
		 
		 
		</form>

		<form class="js-ajax-form" action="" method="post" id="company_info_list_form">

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

						<th>企业简介</th>
						<th>认证</th>
						<th>邮箱</th>
						<th>联系电话</th>
						<th>联系人</th>
						<th>地区</th>
						<th>职位</th>
						<th>简历</th>
						<th>关注</th>
						<th>直播</th>
						<th>点播</th>
						<th>offer邮件</th>
						<th>评级</th>
						<th>注册时间</th>
						<th>状态</th>
						<th>操作</th>

					</tr>
				</thead>
				<tbody class="kaipin-table">
					<c:forEach var="m"  items="${page.elements }" varStatus="index">
						<tr  onclick="alert("${BASE_PATH}/management/company/main/detail?companyId=${m.companyInfo.id}&resumeCount=${m.resumeCount }&positionCount=${m.positionCount }&followCount=${m.followCount }&userId=${m.userInfo.id }")">
						<td data-companyid="${m.companyInfo.id }"><input type="checkbox" class="js-check"
							data-yid="js-check-y" data-xid="js-check-x" name="ids[]" value=""
							title=""></td>
						<td title="${m.companyInfo.entName}"> 
						${m.companyInfo.entName}
							 <c:choose>
								   <c:when test="${m.companyInfo.entSimpleName != ''}"> 
								    	<span title="${m.companyInfo.entName}"><span style="color:#39C;">${m.companyInfo.entSimpleName}</span></span>
								   </c:when>
								   <c:otherwise>
										<span style="color:red" title="${m.companyInfo.entName}"><span style="color: #39C;">---</span></span></span>
								   </c:otherwise>
							</c:choose>
						</td>
						<td>
							<c:choose>
								<c:when test="${m.companyInfo.verifyStatus == 0}">否</c:when>
								<c:when test="${m.companyInfo.verifyStatus == 1}">未</c:when>
								<c:when test="${m.companyInfo.verifyStatus == 2}">是</c:when>
							</c:choose>
						</td>
						<td>${m.localUser.email }</td>
						<td>${m.localUser.phone }</td>
						<td>${m.userInfo.surname }${m.userInfo.missSurname }</td>
						<td data-location="${m.companyInfo.officeArea }">载入...</td>
						<td>${m.positionCount }</td>
						<td>${m.resumeCount }</td>
						<td>${m.followCount }</td>
						<td></td>
						<td></td>
						<td></td>
						<td>评级</td>
						<td>${m.createTime }</td>
						<td align="center">
							<c:if test="${m.isActiveEmail == 1}">
								是
							</c:if>
							<c:if test="${m.isActiveEmail == 0}">
								<span style="color:red;">否</span>
							</c:if>
						</td>
	
						<td>
							<a href="${BASE_PATH}/management/company/main/detail?companyId=${m.companyInfo.id}&resumeCount=${m.resumeCount }&positionCount=${m.positionCount }&followCount=${m.followCount }&userId=${m.userInfo.id }">查看</a> 
							<!-- | <a href="#" class="js-ajax-delete">删除</a>  -->
							<c:if test="${m.companyInfo.enable == 1 }">
								| <a href="${BASE_PATH}/management/company/main/enable?companyId=${m.companyInfo.id}&oper=0" class="js-ajax-delete" data-msg="确定拉黑该用户？" data-form="company_info_list_form">拉黑</a>
							</c:if>
							<c:if test="${m.companyInfo.enable == 0 }">
								| <a href="${BASE_PATH}/management/company/main/enable?companyId=${m.companyInfo.id}&oper=1" class="js-ajax-delete" data-msg="确定取消拉黑该用户？" data-form="company_info_list_form">取消拉黑</a>
							</c:if>
							
						</td>
					</tr>
					
					
					<c:if test="${index.index == (fn:length(page.elements) -1)}">
								<!-- table 加载完毕重新渲染页面 -->
								<script type="text/javascript">
		 							$(function(){ 
		 								//区域代码
		 								var datasArray = $(".kaipin-table").find("tr");
		 								for(var i = 0; i < datasArray.length; i++){
		 									//区域代码
		 									var $td = datasArray.eq(i).find("td").eq(6);
		 									var location = $td.data("location");
		 									if(isNotEmpty(location)){
		 										var name = getLocationName(location);
		 										$td.html(name);
		 									}else{
		 										$td.html("");
		 									}
		 									
		 									//获取 直播、点播、预告统计数据
		 									var $td_id = datasArray.eq(i).find("td").eq(0);
		 									var $td_zb = datasArray.eq(i).find("td").eq(10);
		 									var $td_db = datasArray.eq(i).find("td").eq(11);
		 									var companyId = $td_id.data("companyid");
		 									getLiveCount($td_zb,$td_db,companyId);
		 									
		 									// 判断是否配置邮箱
		 									var $td_config = datasArray.eq(i).find("td").eq(12);
		 									checkConfig($td_config,companyId);
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
		 							
		 							//获取直播统计数据
		 							function getLiveCount($td_zb,$td_db,companyId){
		 								$.ajax({                
		 									cache: true,    
		 									async: true, 
		 									type: "POST",                
		 									url:  '${BASE_PATH}/management/company/main/livecounts',                
		 									data:{
		 									},  
		 									beforeSend : function(request){
		 									},
		 									error: function(request) { 
		 										alert("网络错误");
		 									},                
		 									complete: function(data) {
		 										var datas = data.responseText;
		 										var data = eval('('+datas+')');
		 										if(data.success){
		 											var zb = data.obj[1];
			 										var db = data.obj[2];
			 										if(zb != '0'){
			 											$td_zb.html(data.obj[1]);
			 										}
			 										if(db != '0'){
			 											$td_db.html(data.obj[2]);
			 										}
		 										}
		 										
		 									}           
		 								});
		 							}
		 							
		 							function checkConfig($td_config, companyId){
		 								$.ajax({                
		 									cache: true,    
		 									async: true, 
		 									type: "POST",                
		 									url:  '${BASE_PATH}/management/company/main/offerconfig',                
		 									data:{
		 									},  
		 									beforeSend : function(request){
		 									},
		 									error: function(request) { 
		 										alert("网络错误");
		 									},                
		 									complete: function(data) {
		 										var datas = data.responseText;
		 										var data = eval('('+datas+')');
		 										if(data.success){
		 											$td_config.html(data.obj);
		 										}
		 									}           
		 								});
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
			setCookie("_cookie_page_size", 20);

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