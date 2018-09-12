<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<script type="text/javascript">
window.onload=function()
{
        var oBox=document.getElementById('search-result');        
        oBox.onclick=function(ev)
        {
                var ev = ev || window.event;
                ev.cancelBubble=true;        
        };
        document.onclick=function()
        {
        	var v_searchBtn = $("#v_searchBtn").val();
        	
        	if(v_searchBtn != 'open'){
        		 oBox.style.display='none';
        	}else{
        		oBox.style.display='block';
        	}
               
        };
}
$(function(){
	/* 全文检索 */
	$("#search-text").keyup(function(){
		showSearch();
	});
	
	$("#search-result").on("click", "li" ,function(){
		/*搜索类型*/
		var searchType = $(this).data("tag");
		 /*搜索内容*/
		var searchTxt = $("#search-text").val();
		var url = r_path + "/search/list?page=1&pageSize=5&searchType="+searchType+"&searchTxt="+searchTxt;
		$("#searchTriggerA").attr("href",url);
		$("#searchTrigger").trigger("click");
	});
	
	/*搜索按钮*/
	$("#searchBtn").click(function() {
		$("#v_searchBtn").val("open");
		showSearch();
		//判断当前状态，如果是搜索按钮则显示，500毫秒后清除
		setTimeout(function(){
			$("#v_searchBtn").val("");
		}, 500);
	});
	
	/*展示需要搜索的内容*/
	function showSearch(){
		var searchTxt = $("#search-text").val();
		if($.trim(searchTxt).length > 0){
			$(".search-tip").html(searchTxt);
			$("#search-result").show();
		}else{
			$("#search-result").hide();
		}
	}
	
});
</script>
	<input type="hidden" id="v_searchBtn">
	<div id="search-result" class="search" onblur="this.style.display='none';">
		<ul>
			<li class="a" data-tag="stu" style="background-image: url('${STATIC_SCH }/images/search_input_list_bg.png')">带有   <span class="search-tip"></span> 关键字的会员</li>
			<li class="b" data-tag="position" style="background-image: url('${STATIC_SCH }/images/search_input_list_bg.png')">带有 <span class="search-tip"></span> 关键字的职位</li>
			<li class="c" data-tag="ent" style="background-image: url('${STATIC_SCH }/images/search_input_list_bg.png')">带有 <span class="search-tip"></span> 关键字的企业</li>
			<li class="d" data-tag="sch" style="background-image: url('${STATIC_SCH }/images/search_input_list_bg.png')">带有 <span class="search-tip"></span> 关键字的学校</li>
			<li class="e" data-tag="live" style="background-image: url('${STATIC_SCH }/images/search_input_list_bg.png')">带有 <span class="search-tip"></span> 关键字的视频</li>
		</ul>
		<a  href="#" id="searchTriggerA" style="display:none;"><span id="searchTrigger">搜索结果点解跳转触发器</span></a>
	</div>
	