<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>拉米招聘</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script src="<%=path%>/js/jquery-easyui-1.2.6/jquery-1.7.2.min.js" charset="UTF-8" type="text/javascript"></script>
	<script src="<%=path%>/js/jquery.cookie.js" charset="UTF-8" type="text/javascript"></script>
	<link id="easyuiTheme" href="<%=path%>/js/jquery-easyui-1.2.6/themes/default/easyui.css" rel="stylesheet" type="text/css" media="screen">
	<script src="<%=path%>/js/changeEasyuiTheme.js" charset="UTF-8" type="text/javascript"></script>
	<link href="<%=path%>/js/jquery-easyui-1.2.6/themes/icon.css" rel="stylesheet" type="text/css" media="screen">
	<script src="<%=path%>/js/jquery-easyui-1.2.6/jquery.easyui.min.js" charset="UTF-8" type="text/javascript"></script>
	<script src="<%=path%>/js/jquery-easyui-1.2.6/locale/easyui-lang-zh_CN.js" charset="UTF-8" type="text/javascript"></script>
	<link href="<%=path%>/css/baseCss.css" rel="stylesheet" type="text/css" media="screen">
	<script src="<%=path%>/js/syUtils.js" charset="UTF-8" type="text/javascript"></script>
	<link href="<%=path%>/css/common.css" rel="stylesheet" type="text/css" media="screen">
	<script language="JavaScript"> 
		if (window != top) {
			top.location.href = "<%=path%>/loginController/index.do"; 
		}
	</script>
	<script type="text/javascript">
		$(function () {
			$("#login").click(function (){
				login();
			});
			
			/**
			 *监听回车键
			 */
			document.onkeydown = function(event) {　　
				if (event.keyCode == 13) //回车键的键值为13
				　　　login();　
			};
// 			$(document).keydown(function (event) {
// 			});
			
		});
		
		/**
		 *登录
		 */
		function login(){
			var userName = $("#userName").val();
			var password = $("#password").val();
			var code = $("#code").val();
			$.ajax( {  
    		       url:'<%=path%>/loginController/login.do',// 跳转到 action  
    		       data:{  
    		    	   userName : userName,
    		    	   password : password,
    		    	   code : code
    		       },  
    		      type:'post',  
    		      cache:false,  
    		      dataType:'json',  
    		      success:function(data) { 
    		    	  if(data.success){
    		    		  location.href="<%=path%>/loginController/main.do";
    		    	  }
    		       },  
    		      error : function() {  
    		            alert("异常！");  
    		       }  
    		  });
		}
		/**
		 * 验证码
		 */
		function changeImg(){
			var time=new Date().getTime();
	    	var url = "<%=path%>/validatecode.jpg?time="+time;
	    	$("#validateImg").attr("src",url);
		}
	</script>
  </head>
  
  <body>
		<!--   登录 -->
    	账号：<input type="text" value="xmkj" name="userName" id="userName"/>  
		密码：<input type="password" value="1" name="passWord" id="password"/>  
		验证码：<input type="text" name="code" id="code"/><img src="<%=path%>/validatecode.jpg" id="validateImg" alt=""  onClick="javascript:changeImg();"/> 
		</br><input type="button" id="login" value="登     录"/>  
  </body>
</html>
