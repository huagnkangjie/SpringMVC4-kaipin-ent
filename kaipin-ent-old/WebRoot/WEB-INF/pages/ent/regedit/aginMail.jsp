<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>

<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="<%=path%>/css/NewRegister.css"/>
<title>重发邮件</title>
<style>

.Absolute-Center {
  margin: auto;
 
  top: 0; left: 0; bottom: 0; right: 0;
  height:100px;
  line-height:100px;
  font-size:20px;
  font-family: "Microsoft YaHei","微软雅黑","Arial","黑体","宋体";
}
</style>
<script type="text/javascript" src="<%=path%>/js/jquery-1.11.1.min.js?v.<%=System.currentTimeMillis()%>" ></script>
<script type="text/javascript" src="<%=path%>/js/base.js?v.<%=System.currentTimeMillis()%>" ></script>
<link rel="shortcut icon" href="<%=path%>/favicon.ico" type="image/x-icon" />
<script type="text/javascript">
		var r_path='<%=basePath%>';
</script>
<script type="text/javascript">
	var oper = 0;
	$(function(){
		$("#act_send_mail").click(function(){
			$("#act_send_mail").hide();
			$("#act_send_mail_ing").show();
			var uId = $("#uId").val();
			if(uId.lengt == 0){
				$("#act_send_mail").show();
				$("#act_send_mail_ing").hide();
				alert("邮件获取失败");
			}else{
				if(oper == 0){
					oper = 1;
					setTimeout(function(){
						$.ajax({
							cache : false,
							type : "POST",
							url : r_path + '/regedit/reSendMail.do',
							data : {
								uId : uId
							},
							async : true,
							error : function(request) {
								alert("网络异常，请稍后再试！");
							},
							success : function(data) {
// 								$("#act_send_mail").show();
// 								$("#act_send_mail_ing").hide();
// 								oper = 0;
// 								var datas = eval('('+data+')');
// 								alert(datas.msg);
// 								location.href =r_path + "/loginController/index.do"
							},
							complete: function(data) { 
								$("#act_send_mail").show();
								$("#act_send_mail_ing").hide();
								oper = 0;
								var dataStr = data.responseText;
								var datas = eval('('+dataStr+')');
								alert(datas.msg);
								location.href =r_path + "/loginController/index.do"
							}
						});
					},500);
				}else{
					alert("亲，正在努力发送中。。。");
				}
				
			}
		});
	});
	function getData(){
		
	}
</script>
</head>

<body>
	<input type="hidden" id="uId" value="${uId }"/>

		<!--header start-->
		<div class="lm-header-container">
			<div class="header">
				<h1>
					<a href="javascript:void(0)" class="logo backIndexLogin"></a>
					<span class="tips-txt">-创建您的开频账户</span>
				</h1>
			</div>
		</div>
		<!--header end-->
		
		<div class="registered-success activate" style="display: block;">
			<p class="tips">你的账号<span>${userName }</span><br>处于未激活状态</p>
			<p class="cons">请前往邮箱激活，如未收到激活邮件，请点击下面发送邮件，收到邮件后点击链接激活。</p>
			<a href="javascript:void(0)" class="back-top-main send-eamil" id="act_send_mail">发送邮件</a>
			<a href="javascript:void(0)" class="back-top-main send-eamil" style="display:none;" id="act_send_mail_ing">发送邮件<img src="<%=path %>/images/loading.gif"/></a>
			
		</div>
		
		
	</body>
</html>
