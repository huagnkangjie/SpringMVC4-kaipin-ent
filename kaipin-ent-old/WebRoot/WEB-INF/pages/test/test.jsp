<!--  header-start -->
<%@ include file="/WEB-INF/pages/main/header.jsp"%>
<!--  header-end -->
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

	<script type="text/javascript">
      $(function(){
    	 $("#sendPhoneMsg").click(function(){
   			 $.ajax({                
   					cache: false,    
   					async: false, 
   					type: "POST",                
   					url:  r_path + '/registerController/sendPhoneMsg.do',
   					contentType: "application/json; charset=utf-8",
   					data:{
   						phone : '15283771727'
   					},              
   					error: function(request) {                    
   					},                
   					success: function(data) {
   						alert(data);
   					}            
   			});
    	 }); 
    	 
    	 $("#transDataUser").click(function(){
   			 $.ajax({                
   					cache: false,    
   					async: false, 
   					type: "POST",                
   					url:  r_path + '/test/dataUser.do',
   					contentType: "application/json; charset=utf-8",
   					data:{
   						phone : '15283771727'
   					},              
   					error: function(request) {                    
   					},                
   					success: function(data) {
   					
   					}            
   			});
    	 }); 
    	 
    	 $("#transDataPosition").click(function(){
   			 $.ajax({                
   					cache: false,    
   					async: false, 
   					type: "POST",                
   					url:  r_path + '/test/dataPosition.do',
   					contentType: "application/json; charset=utf-8",
   					data:{
   						phone : '15283771727'
   					},              
   					error: function(request) {                    
   					},                
   					success: function(data) {
   					
   					}            
   			});
    	 }); 
    	 
    	 $("#transLiveInfo").click(function(){
   			 $.ajax({                
   					cache: false,    
   					async: false, 
   					type: "POST",                
   					url:  r_path + '/test/liveInfo.do',
   					contentType: "application/json; charset=utf-8",
   					data:{
   						phone : '15283771727'
   					},              
   					error: function(request) {                    
   					},                
   					success: function(data) {
   					
   					}            
   			});
    	 }); 
    	 
    	 
    	 
   		 var sub = false;
   		 $("#repeat").click(function(){
   		     if(sub === true){
   		    	 alert("重复提交")
   		         return;
   		     }
   		     sub = true;
   		     $.ajax({                
   					cache: false,    
   					async: false, 
   					type: "POST",                
   					url:  r_path + '/test/repeat.do',
   					contentType: "application/json; charset=utf-8",
   					data:{
   						phone : '15283771727'
   					},              
   					error: function(request) {                    
   					},                
   					success: function(data) {
   						alert(data);
   						sub = false;
   					}            
   			});
   		 });
   		 
   		/* 获取项目路径 */
   		$("#getWebPath").click(function(){
  			 $.ajax({                
  					cache: false,    
  					async: false, 
  					type: "POST",                
  					url:  r_path + '/test/getWebPath.do',
  					contentType: "application/json; charset=utf-8",
  					data:{
  						phone : '15283771727'
  					},              
  					error: function(request) {                    
  					},                
  					success: function(data) {
  					alert(data);
  					}            
  			});
   	 }); 
   		/* 事物回滚 */
   		$("#rollback").click(function(){
  			 $.ajax({                
  					cache: false,    
  					async: false, 
  					type: "POST",                
  					url:  r_path + '/test/rollback',
  					contentType: "application/json; charset=utf-8",
  					data:{
  						phone : '15283771727'
  					},              
  					error: function(request) {                    
  					},                
  					success: function(data) {
  					alert(data);
  					}            
  			});
   	 	}); 
   		
   		/* 获取缓存 */
   		$("#getCache").click(function(){
  			 $.ajax({                
  					cache: false,    
  					async: false, 
  					type: "POST",                
  					url:  r_path + '/annexProcess/processClear',
  					contentType: "application/json; charset=utf-8",
  					data:{
  						phone : '15283771727'
  					},              
  					error: function(request) {                    
  					},                
  					success: function(data) {
  					}            
  			});
   	 	}); 
      });
     </script>
  </head>
  <body>
  	</br></br></br></br>
  	<input type="text" /><button id="sendPhoneMsg">获取短信验证码</button></br></br></br></br>
  	<a href="https://webapi.sms.mob.com/sms/sendmsg?appkey=e87a7d8f209f&phone=15283771727&zone=86" >短信test</a></br></br>
<!--   	<p>迁移用户数据：            <a href="javascript:void(0)" id="transDataUser">迁移用户数据</a></p></br></br> -->
<!--   	<p>迁移职位数据：            <a href="javascript:void(0)" id="transDataPosition">迁移职位数据</a></p></br></br> -->
<!--   	<p>迁移宣讲会数据：            <a href="javascript:void(0)" id="transLiveInfo">迁移宣讲会数据</a></p></br></br> -->
  	<p>jquery 重复提交：            <a href="javascript:void(0)" id="repeat">jquery 重复提交</a></p></br></br>
  	<p>获取工程路径：            <a href="javascript:void(0)" id="getWebPath">获取工程路径</a></p></br></br>
  	<p>事物回滚：            <a href="javascript:void(0)" id="rollback">事物回滚</a></p></br></br>
  	<p>获取缓存：            <a href="javascript:void(0)" id="getCache">获取缓存</a></p></br></br>
  	
  	
  	</body>
</html>