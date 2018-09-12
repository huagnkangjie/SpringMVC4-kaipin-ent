<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<%-- 	<script type="text/javascript" src="<%=path%>/js/jquery1.4.2.js"></script> --%>


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
	

	<script type="text/javascript">
		var datagrid ;
		var addForm;
		$(function () {
// 			jQuery.ajax( {  
// 		        type : 'GET',  
// 		        contentType : 'application/json',  
// 		        url : 'user/list',  
// 		        dataType : 'json',  
// 		        success : function(data) {  
// 		          if (data && data.success == "true") {  
// 		            $('#info').html("共" + data.total + "条数据。<br/>");  
// 		            $.each(data.data, function(i, item) {  
// 		              $('#info').append(  
// 		                  "编号：" + item.id + "，姓名：" + item.username  
// 		                      + "，年龄：" + item.age);  
// 		            });  
// 		          }  
// 		        },  
// 		        error : function() {  
// 		          alert("error")  
// 		        }  
// 		      });  


				datagrid =	$('#grid').datagrid({
							title : '',
							url : '<%=path%>/areaController/getAll.do',
							pagination : true,
							fitColumns: true,
							showRefresh : false,
							pageSize : 5,
							pageList : [5, 10, 20, 30, 40, 50, 60, 70, 80, 90, 100 ],
							fit : true,
							nowrap : false,
							border : false,			
							idField : 'id',
							fitColumns : true,	
							pagination: true,
					columns : [ [ {
						title : 'id',
						field : 'id',
						width : $(this).width() * 0.1,
						checkbox : true
					},{
						title : '城市id',
						width :$(this).width() * 0.3,
						field : 'cityId'
					},{
						title : '城市名称',
						width :$(this).width() * 0.2,
						field : 'name'
					}] ],
					toolbar : '#toolbar',
					onBeforeLoad : function(param) {
// 						parent.$.messager.progress({
// 							text : '数据加载中....'
// 						});
					},
					onLoadSuccess : function(data) {
// 						$('.iconImg').attr('src', sy.pixel_0);
// 						parent.$.messager.progress('close');
					}
				});
				
				addForm = $('#addForm').form();
				
				$("#ajaxSubmit").click(function (){
					$.ajax({                
						cache: true,                
						type: "POST",                
						url:  '<%=path%>/areaController/addAreaAjax.do',                
						data:$('#addForm').serialize(),// 你的formid                
						async: false,                
						error: function(request) {                    
							alert("Connection error");                
						},                
						success: function(data) {
							addForm.form('clear');
							datagrid.datagrid('unselectAll');
							datagrid.datagrid('reload');
							}            
						});
				});

		      $("#submit").click(function() {  
<%-- 		    	  $.post("<%=path%>/areaController/add.do",{id : "123"},function(data){ --%>
// 		  			alert(data[0].name);
// 		  		},"json");
		    	  
		    	  $.ajax( {  
	    		       url:'<%=path%>/areaController/add.do',// 跳转到 action  
	    		       data:{  
	    		    	   id : '123'
	    		       },  
	    		      type:'post',  
	    		      cache:false,  
	    		      dataType:'json',  
	    		      success:function(data) {  
	    		    	  alert(data[0].name);
	    		       },  
	    		      error : function() {  
	    		            // view("异常！");  
	    		            alert("异常！");  
	    		       }  
	    		  });
		    	  
		      });  
		      $("#getCityName").click(function() {  
<%-- 		    	  $.post("<%=path%>/areaController/add.do",{id : "123"},function(data){ --%>
// 		  			alert(data[0].name);
// 		  		},"json");
		    	  
		    	  $.ajax( {  
	    		       url:'<%=path%>/areaController/test.do',// 跳转到 action  
	    		       data:{  
	    		    	   id : '123'
	    		       },  
	    		      type:'post',  
	    		      cache:false,  
	    		      dataType:'json',  
	    		      success:function(data) { 
	    		    	  alert(data.name);
	    		       },  
	    		      error : function() {  
	    		            // view("异常！");  
	    		            alert("异常！");  
	    		       }  
	    		  });
		    	  
		    	  
		      }); 
		      
		      $("#getList").click(function (){
		    	  $.ajax( {  
	    		       url:'<%=path%>/areaController/list.do',// 跳转到 action  
	    		       data:{  
	    		    	   id : '123'
	    		       },  
	    		      cache:false,  
	    		      dataType:'json',  
	    		      success:function(data) { 
	    		    	  var html = "";
	    		    	  $("#cityList").empty();
	    		    	  $.each(data,function(id,item){
	    		    		  html = html +  "<div>"+item.name+"--------<a href='#' onclick='deleteData("+item.id+")'>删除</a></div></br>";
	    					});
	    		    	  $("#cityList").append(html);
	    		       },  
	    		      error : function() {  
	    		            alert("异常！");  
	    		       }  
	    		  });
		      });
		      
		      $("#logout").click(function () {
					location.href="<%=path%>/loginController/logout.do"
				});
		      
		      $("#fileLoadJDT").click(function () {
					location.href="<%=path%>/areaController/flieLoadPage.do"
				});
		      $("#fileLoadJDT2").click(function () {
					location.href="<%=path%>/areaController/flieLoadPage2.do"
				});
		      
		      
		});
		
		function deleteData (obj) {
			$.ajax( {  
 		       url:'<%=path%>/areaController/del.do',// 跳转到 action  
 		       data:{  
 		    	   id : obj
 		       },  
 		      cache:false,  
 		      dataType:'json',  
 		      success:function(data) { 
 		    	  alert("重新获取list");
 		       },  
 		      error : function() {  
 		            alert("异常！");  
 		       }  
 		  });
		}
		
		function jsonBtn () {
			$.ajax( {  
 		       url:'<%=path%>/areaController/jsonBtn.do',// 跳转到 action  
 		      cache:false,  
 		      dataType:'json',  
 		      success:function(data) { 
 		    	 var datas = eavl('('+data+')');
 		    	 alert(datas.obj.msg);
 		       },  
 		      error : function() {  
 		            alert("异常！");  
 		       }  
 		  });
		}
		
		
		
		function changeImg(){
			var time=new Date().getTime();
	    	var url = "<%=path%>/validatecode.jpg?time="+time;
	    	$("#validateImg").attr("src",url);
		}
	</script>
	
	<!--文件上传 -->
	<script type="text/javascript">  
	    i = 1;  
	    j = 1;  
	    $(document).ready(function(){  
	          
	        $("#btn_add1").click(function(){  
	            document.getElementById("newUpload1").innerHTML+='<div id="div_'+i+'"><input  name="file" type="file"  /><input type="button" value="删除"  onclick="del_1('+i+')"/></div>';  
	              i = i + 1;  
	        });  
	          
	        $("#btn_add2").click(function(){  
	            document.getElementById("newUpload2").innerHTML+='<div id="div_'+j+'"><input  name="file_'+j+'" type="file"  /><input type="button" value="删除"  onclick="del_2('+j+')"/></div>';  
	              j = j + 1;  
	        });  
	    });  
	  
	    function del_1(o){  
	     document.getElementById("newUpload1").removeChild(document.getElementById("div_"+o));  
	    }  
	      
	    function del_2(o){  
	         document.getElementById("newUpload2").removeChild(document.getElementById("div_"+o));  
	    }  
	  
	</script>
  </head>
  
  <body>
  	<input type="button" id="logout" value="注销"/></br>
  	<input type="button" id="fileLoadJDT" value="进度条上传demo1"/></br>
<!--   	<input type="button" id="fileLoadJDT2" value="进度条上传demo2"/></br> -->
	<!-- 文件上传 -->
	<h1>springMVC字节流输入上传文件</h1>   
    <form name="userForm1" action="<%=path%>/areaController/upload.do" enctype="multipart/form-data" method="post">  
        <div id="newUpload1">  
            <input type="file" name="file">  
        </div>  
          
        <input type="button" id="btn_add1" value="增加一行" >  
        <input type="submit" value="上传" >  
    </form>   
    <br>  
    <br>  
    <hr align="left" width="60%" color="#FF0000" size="3">  
    <br>  
    <br>  
     <h1>springMVC包装类上传文件</h1>   
    <form name="userForm2" action="<%=path%>/areaController/upload2.do" enctype="multipart/form-data" method="post"">  
        <div id="newUpload2">  
            <input type="file" name="file">  
        </div>  
        <input type="button" id="btn_add2" value="增加一行" >  
        <input type="submit" value="上传" >  
    </form>
	<!-- 文件上传结束 -->
	</br>
	</br>
	</br>
	<input type="button" id="JsonBtn" onclick="jsonBtn();" value="Json格式数据拆入"/>
	</br>
	</br>
	</br>
	文件下载：<a href="<%=path%>/areaController/downLoad.do">下载图片</a>
	
	</br>
	
    <form action="/sm/areaController/addArea.do" method="post" id="addForm" name="addForm">
    	<table>
    		<tr>
    			<td>id</td>
    			<td><input type="text" name="id"/></td>
    		</tr>
    		<tr>
    			<td>cityId</td>
    			<td><input type="text" name="cityId"/></td>
    		</tr>
    		<tr>
    			<td>name</td>
    			<td><input type="text" name="name"/></td>
    		</tr>
    		<tr>
    			<td><input type="submit" value="提交"/></td>
    		</tr>
    		<tr>
    			<td><input type="button" id="ajaxSubmit" value="ajax提交"/></td>
    		</tr>
    	</table>
    	
    	<div id="info"></div>  
    	
		<!--   登录 -->
    	账号：<input type="text" name="userName"/>  
			密码：<input type="text" name="passWord"/>  
			验证码：<input type="text" name="code"/><img src="<%=path%>/validatecode.jpg" id="validateImg" alt=""  onClick="javascript:changeImg();"/> 
		<form action="/sm/areaController/add.do" method="post" >  
			 
		<input type="button" value="登录" id="submit"/>  
		
		<input type="button" value="提交" id="submit"/>  
		<input type="button" value="获取城市名称" id="getCityName"/>  
		</form>  
    	<input type="button" value="获取list" id="getList"/></br>
    	<div id="cityList"></div></br>
    </form>
    
    <div region="center" id="agencyTabs"  style="width:800px;height:300px;padding:10px;border:red;">  
		 	<div id="toolbar" class="datagrid-toolbar" style="height: auto;>
			 	<fieldset id="search-tab" style="position:relative;">
					<legend>筛选</legend>
						<table class="tableForm">
							<tr>
								<th>名称</th>
								<td>
									<input id="menuName" name="menuName" style="width: 150px;"/>
								</td>
								<td>
									<a href="javascript:void(0);" class="easyui-linkbutton"iconCls="icon-search" plain="true"onclick="searchFunction();">查询</a>
									<a class="easyui-linkbutton" iconCls="icon-cancle" plain="true" onclick="clearFun();" href="javascript:void(0);" >清空</a>
								</td>
							</tr>
						</table>
								
				</fieldset>
					<div style="height: 30px;background:#f4f4f4;line-height:30px;" >
						<a fid="1427337134308" class="easyui-linkbutton" iconCls="icon-add" onclick="append();" plain="true" href="javascript:void(0);">增加</a> 
						<a fid="1427337134309" class="easyui-linkbutton" iconCls="icon-edit" onclick="edit();" plain="true" href="javascript:void(0);">修改</a> 
						<a fid="1427337134310" class="easyui-linkbutton" iconCls="icon-remove" onclick="delData();" plain="true" href="javascript:void(0);">删除</a> 
					</div>
		</div>
		<table id="grid"></table>
	</div>
    
  </body>
</html>
