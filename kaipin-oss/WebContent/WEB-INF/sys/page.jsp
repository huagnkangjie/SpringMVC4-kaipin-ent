<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<script src="${BASE_PATH}/public/assets/js/page.js"></script>
 

<div id="generalPages-template">
	<input type="hidden" value="${BASE_PATH }" id="BASE_PATH">
	<div class="pagination" style="float: right;">
		
		<!-- - <select class="form-control pagesizeOfRefObject"
			id="pagesizeOfrefObject"
			style="float: left; width: 108px; padding: 0 12px; height: 30px;">
			<option value="5" selected="selected">每页 5 条</option>
			<option value="10">每页 10 条</option>
			<option value="15">每页 15 条</option>
			<option value="20" >每页 20 条</option>
			<option value="30">每页 30 条</option>
			<option value="50">每页 50 条</option>
		</select> -->
		<ul class="pagination" id="pagesUlOfrefObject"
			style="float: left; margin-top: 0px;" refObject="refObject"
			currentPage="${page.pageNo}}">
			<li><a href="javascript:void(0)" class="firstPage">|&lt;</a></li>
			<li><a href="javascript:void(0)" class="prevPage">&lt;</a></li>
			<li><a
				style="padding-top: 0px; padding-bottom: 0px; height: 28px;">当前页
					<input type="text" id="toPageOfrefObject"
					style="width: 30px; height: 15px; position: relative; top: 2px;text-align:center;"
					value="${page.pageNo}"    class="toPageOfRefObject" /> 共 <span
					id="totalPagesOfrefObject">${page.lastPageNo}</span> 页
			</a></li>
			<li><a href="javascript:void(0)"  class="nextPage">&gt;</a></li>
			<li><a href="javascript:void(0)" class="lastPage">&gt;|</a></li>
			<li><a href="javascript:void(0)" class="refreshCurrentPage">刷新</a></li>
			<li><a>目前显示 <span id="currentItemsIndexOfrefObject">${fn:length(page.elements)}</span>
					，共 <span id="totalItemsOfrefObject">${page.totalCount}</span> 条记录
			</a></li>
		</ul>
	</div>
</div>




