<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/sys/header.jsp"%>
</head>
<body>

	<div class="wrap js-check-wrap">
		<ul class="nav nav-tabs">
			<li class="active"><a href="javascript:;">管理</a></li>
			<li><a href="${BASE_PATH }/platform/module/addpage?parentId=${parentId}" target="_self">添加</a></li>
		</ul>
		<!-- <button type="button" class="btn btn-primary" style="float:right;" id="backUp">返回上一级</button>
		<script type="text/javascript">
			$(function(){
				$("#backUp").click(function(){
					location.href="${BASE_PATH }/platform/module/list?parentId=${parentId}";
				});
			});
		</script> -->
		<form style="display:none;" class="well form-search"  name="form-search" method="post"  action="${BASE_PATH }/platform/module/list?parentId=${parentId}">
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

		<form  class="js-ajax-form" action="" method="post" id="company_info_list_form">

			<div style="display:none;" class="table-actions">
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

						<th>名称</th>
						<th>英文名</th>
						<th>描述</th>
						<th>URL</th>
						<th align="center">操作</th>

					</tr>
				</thead>
				<tbody class="kaipin-table">
					<c:forEach var="m"  items="${page.elements }" varStatus="index">
						<tr>
						<td data-companyid=""><input type="checkbox" class="js-check"
							data-yid="js-check-y" data-xid="js-check-x" name="ids[]" value=""
							title=""></td>
						
						<td>${m.name }</td>
						<td>${m.sn }</td>
						<td>${m.description }</td>
						<td>${m.url }</td>
						
	
						<td align="center">
							<a href="${BASE_PATH}/platform/module/list?parentId=${m.id}">子集管理</a> 
							| <a href="${BASE_PATH}/" class="js-ajax-delete" data-msg="删除此菜单会把该菜单的子集全部删掉！" data-form="company_info_list_form">删除</a>
							
						</td>
					</tr>
					
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