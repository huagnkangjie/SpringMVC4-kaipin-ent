<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/sys/header.jsp"%>
</head>
<body>

	<div class="wrap js-check-wrap">
		<ul class="nav nav-tabs">
			<li class="active"><a href="javascript:;">管理</a></li>
			<!-- <li><a href="add" target="_self">添加</a></li> -->
		</ul>

		<form class="well form-search"  name="form-search" method="post"  action="${BASE_PATH }/count/delivery/position/list">
			<%-- 激活状态： <select class="select_2" name="isCheckMail" value="${userInfo.isCheckMail }" id="isCheckMail">
				<option value=''>全部</option>
				<option value='1'>已激活</option>
				<option value='0'>未激活</option>
			</select> &nbsp;&nbsp; 注册时间： <input type="text" name="startTime"
				class="js-date" value="${startTime }" style="width: 80px;" autocomplete="off">-
			<input type="text" class="js-date" name="endTime" value="${endTime }"
				style="width: 80px;" autocomplete="off"> &nbsp; &nbsp;  --%>
			企业名称：
			<input type="text" name="entName" value="${entName }" style="width: 200px;" value=""
				placeholder="请输入关键字..."> 
			学生名称：
			<input type="text" name="userName" value="${userName }" style="width: 200px;" value=""
				placeholder="请输入关键字..."> 
				<input type="submit" 	class="btn btn-primary" value="搜索" /> 
				
				<input type="hidden" 	id="pageNo"  name="pageNo"  /> 
				
			<!-- 	<input type="hidden" name="pageSize"  id="pageSize"
		 /> -->
		 
		 
		</form>

		<form class="js-ajax-form" action="" method="post" id="company_info_list_form">

			<div class="table-actions" style="display: none;">
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

						<th>学生名字</th>
						<th>简历名称</th>
						<th>联系电话</th>
						<th>企业名称</th>
						<th>联系电话</th>
						<th>投递职位</th>
						<th>投递时间</th>
						<th>状态</th>
						<th>处理时间</th>

					</tr>
				</thead>
				<tbody class="kaipin-table">
					<c:forEach var="m"  items="${page.elements }" varStatus="index">
						<tr>
							<td></td>
							<td>
								<a href="#" class="showResume" data-tag="${m.stu_user_id }" data-name="${m.surname }${m.miss_surname }">${m.surname }${m.miss_surname }</a>
							</td>
							<td>${m.resume_name }</td>
							<td>${m.stu_phone }</td>
							<td>
								${m.ent_name }
<%-- 								<a href="#" onclick="showEnt();" id="showEnt" data-tag="${m.company_id }" data-name="${m.ent_name }">${m.ent_name }</a> --%>
							</td>
							<td>${m.company_phone }</td>
							<td>${m.position_name }</td>
							<td>${m.create_time }</td>
							<td>${m.content }</td>
							<td>${m.handle_time }</td>
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
		$(function(){
			$(".showResume").click(function(){
				var id = $(this).data("tag");
				var entName = $(this).data("name");
				var openUrl = r_path + '/stu/resume/detail?userId=' + id;//弹出窗口的url
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
		});
		
		function showEnt(){
			var id = $("#showEnt").data("tag");
			var entName = $("#showEnt").data("name");
			var openUrl = r_path + '/management/company/main/detail?companyId=' + id;//弹出窗口的url
			var iWidth=800; //弹出窗口的宽度;
			var iHeight=600; //弹出窗口的高度;
			var iTop = (window.screen.availHeight-30-iHeight)/2; //获得窗口的垂直位置;
			var iLeft = (window.screen.availWidth-10-iWidth)/2; //获得窗口的水平位置;
			OpenWindow = window.open(openUrl,"1","height="+iHeight+", width="+iWidth+", top="+iTop+", left="+iLeft);
			//window.open('http://www.baidu.com','','width=600,height=500,left=10, top=10,toolbar=no, status=no, menubar=no, resizable=yes, scrollbars=yes');return false;
			setTimeout(function(){
				OpenWindow.document.title = entName;
			},500);
		}
	
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