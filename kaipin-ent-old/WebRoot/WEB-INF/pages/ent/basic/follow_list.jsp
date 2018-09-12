<!--  header-start -->
<%@ include file="/WEB-INF/pages/main/header.jsp"%>
<!--  header-end -->
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


	<script type="text/javascript">
		$(function(){
			var rows = '12';
			getFollowLists(rows,'init',1);
			var followCounts = getFollowCount();
			$("#followCount").html(followCounts);
			
			$("#moreXjh").click(function(){
				var opering = $("#opering").val();
				if(opering == '0'){
					$("#opering").val("1");
					var page = $("#followlistsPage").val();
					$("#moreXjh").hide();
					$("#moreXjhIng").show();
					var timer = setTimeout(getFollowLists(rows,'more',page),200);
					
				}
				
			});
		});
	</script>
	
	<script>
		window.onscroll=function(){
		    var a = document.documentElement.scrollTop==0? document.body.clientHeight : document.documentElement.clientHeight;
		    var b = document.documentElement.scrollTop==0? document.body.scrollTop : document.documentElement.scrollTop;
		    var c = document.documentElement.scrollTop==0? document.body.scrollHeight : document.documentElement.scrollHeight;
		    if(a+b >= c){
		    	$("#moreXjh").trigger("click");
		    }
		}
	</script>
	<%-- <script type="text/javascript" src="<%=path%>/js/blocksit.min.js""></script> --%>
	<!--fansContainer start-->
		<div class="fansContainer">
			<div class="fans-box">
				<div class="fans-num">
					<h5>粉丝<span id="followCount">0</span>人</h5>
				</div>
				<div class="fans-lists">
					<div id="followlists">
					</div>
				</div>
				<div class="upload-addMore" style="">
					<input type="hidden" value="0" id="opering"/>
					<input type="hidden" value="2" id="followlistsPage"/>
					<a href="javascript:void(0)" id="moreXjh" class="more">加载更多</a>
					<a href="javascript:void(0)" id="moreXjhIng" class="more" style="display:none;">加载中...<img src="<%=path%>/images/loading.gif" style="margin-right:5px;"/></a>
				</div>
			</div>
		</div>
		<!--fansContainer end-->
		<!-- 		 footer-start -->
		<%@ include file="/WEB-INF/pages/main/footer.jsp"%>
		<!-- 		 footer-end -->
  </body>
</html>
