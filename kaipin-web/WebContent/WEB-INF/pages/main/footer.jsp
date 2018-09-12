<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!--footer start-->
<!-- 		<div class="footer"> -->
<!-- 			<div class="foot"> -->
<!--  				<ul> --> 
<!--  					<li><a href="javascript:void(0)">关于我们 </a></li>| --> 
<!--  					<li><a href="javascript:void(0)">意见反馈 </a></li>| -->
<!--  					<li><a href="javascript:void(0)">帮助中心 </a></li> --> 
<!--  				</ul> --> 
<!-- 				<p>Copyright © 2016 LaMi Inc. 保留所有权利   &copy; 开频   渝ICP备15011713号-2</p> -->
<!-- 			</div> -->
<!-- 		</div> -->
		<!-- <div class="footer">
			<div class="foot">
				<div class="ft-infos">
					<a href="<%=path%>/loginController/about">关于我们</a>  
					<span class="rule">|</span>
					<a href="<%=path%>/loginController/help">帮助中心</a>  <span class="rule">|</span>  意见反馈QQ群：185930548  &copy;  2016开频　渝ICP备15011713号-2
				</div>
			</div>
		</div> -->
		
		
		<div class="footer">
			<div class="foot">
				<p class="ft-l">© 2016开频 渝ICP备15011713号-2</p>
				<p class="ft-r">
				<%-- <a href="<%=path%>/v/about">关于我们</a> 
					<span class="rule">|</span>
					<a href="<%=path%>/v/help">帮助中心</a>
					<span class="rule">|</span> --%>
					<span>意见反馈QQ群：185930548</span>
				</p>
			</div>
		</div>
		<!--footer end-->
		<script type="text/javascript" src="<%=path%>/js/basic.js?v.<%=System.currentTimeMillis()%>" ></script>
		<script type="text/javascript" src="<%=path%>/js/basicInfo.js?v.<%=System.currentTimeMillis()%>" ></script>
		<script>
				var _hmt = _hmt || [];
				(function() {
				  var hm = document.createElement("script");
				  hm.src = "//hm.baidu.com/hm.js?e23eaa09e1f5c317b738833cf7ceb385";
				  var s = document.getElementsByTagName("script")[0]; 
				  s.parentNode.insertBefore(hm, s);
				})();
		</script>


<script type="text/javascript">
		    $(function () {
		    	  var Sys = {};
			        var ua = navigator.userAgent.toLowerCase();
			        var s;
			        (s = ua.match(/rv:([\d.]+)\) like gecko/)) ? Sys.ie = s[1] :
			        (s = ua.match(/msie ([\d.]+)/)) ? Sys.ie = s[1] :
			        (s = ua.match(/firefox\/([\d.]+)/)) ? Sys.firefox = s[1] :
			        (s = ua.match(/chrome\/([\d.]+)/)) ? Sys.chrome = s[1] :
			        (s = ua.match(/opera.([\d.]+)/)) ? Sys.opera = s[1] :
			        (s = ua.match(/version\/([\d.]+).*safari/)) ? Sys.safari = s[1] : 0;
			        if (Sys.ie){
			        	if(Math.floor(Sys.ie)<10){
			        		alert("你的IE浏览器版本太低，有些功能可能无法使用，请注意升级！");
			        	}
			        }
		    });
</script>
