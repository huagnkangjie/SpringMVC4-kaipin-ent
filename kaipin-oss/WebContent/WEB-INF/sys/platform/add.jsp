<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<%@ include file="/WEB-INF/sys/header.jsp"%>
    <link rel="stylesheet" href="${BASE_PATH}/public/js/bootstrap/bootstrap.css"/>
    <link rel="stylesheet" href="${BASE_PATH}/public/js/bootstrap/bootstrapValidator.css"/>

    <script type="text/javascript" src="${BASE_PATH}/public/js/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="${BASE_PATH}/public/js/bootstrap/bootstrap.min.js"></script>
    <script type="text/javascript" src="${BASE_PATH}/public/js/bootstrap/bootstrapValidator.js"></script>
</head>
<body>
	<div class="wrap js-check-wrap">
		<ul class="nav nav-tabs">
			<li ><a href="${BASE_PATH }/platform/module/list?parentId=${parentId}">管理</a></li>
			<li class="active"><a href="javascript:;" target="_self">添加</a></li>
		</ul>
		<form action="${BASE_PATH}/platform/module/add" method="post">
			<div style="width:500px;height:200px;margin:0 auto;margin-top:40px;">
				<input type="hidden" name="parentId" value="${parentId }"/>
				<input type="hidden" name="priority" value="999"/>
				<div class="form-group">
	                <label>名称</label>
	                <input type="text" class="form-control" name="name" style="height: 30px;"/>
	            </div>
	            <div class="form-group">
	                <label>英文名</label>
	                <input type="text" class="form-control" name="sn" style="height: 30px;"/>
	            </div>
	            <div class="form-group">
	                <label>URL</label>
	                <input type="text" class="form-control" name="url" style="height: 30px;"/>
	            </div>
	            <div class="form-group">
	                <label>描述</label>
	                <input type="text" class="form-control" name="description" style="height: 30px;"/>
	            </div>
	            <div class="form-group">
	                <label>是否子集</label>
	                <select style="width:100px;" name="icon">
	                	<option value="">是</option>
	                	<option value="list">否</option>
	                </select>说明：是-->为子集，否-->该菜单还有子集
	            </div>
<!-- 	            <div class="form-group"> -->
<!-- 	                <button type="submit" name="submit" class="btn btn-primary">提交</button> -->
<!-- 	            </div> -->
				<input type="submit" value="提交" class="btn btn-primary"/>
			</div>
            
        </form>
        </div>
</body>

<script type="text/javascript">
	$(function () {
       $('form').bootstrapValidator({
　　　　　　　　message: 'This value is not valid',
            　feedbackIcons: {
                　　　　　　   valid: 'glyphicon glyphicon-ok',
                　　　　　　　　invalid: 'glyphicon glyphicon-remove',
                　　　　　　　　validating: 'glyphicon glyphicon-refresh'
            　　　　　　　　   },
            fields: {
            	name: {
                    message: '',
                    validators: {
                        notEmpty: {
                            message: '名称不能为空'
                        },
                        callback: {
         							message: '名称不能为空',
         							callback: function (value, validator) {
         								var res = true;
         								if(value == 'test'){
         									res = false;
         								}
         								return res;
         							}
         						}
                    }
                },
                sn: {
                    validators: {
                        notEmpty: {
                            message: '英文名不能为空'
                        }
                    }
                },
                url: {
                    validators: {
                        notEmpty: {
                            message: 'URL不能为空'
                        }
                    }
                },
                description: {
                    validators: {
                        notEmpty: {
                            message: '描述不能为空'
                        }
                    }
                }
            }
        });
    });
	</script>
</html>