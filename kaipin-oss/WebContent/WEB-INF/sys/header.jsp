
<%@ include file="/WEB-INF/sys/include.inc.jsp"%>
<!doctype html>
<html>
<head>
	<meta charset="utf-8">
	<!-- Set render engine for 360 browser -->
	<meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- HTML5 shim for IE8 support of HTML5 elements -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <![endif]-->

	<link href="${BASE_PATH}/public/simpleboot/themes/bluesky/theme.min.css" rel="stylesheet">
    <link href="${BASE_PATH}/public/simpleboot/css/simplebootadmin.css" rel="stylesheet">
    <link href="${BASE_PATH}/public/js/artDialog/skins/default.css" rel="stylesheet" />
    <link href="${BASE_PATH}/public/simpleboot/font-awesome/4.4.0/css/font-awesome.min.css"  rel="stylesheet" type="text/css">
  	<link rel="shortcut icon" href="${BASE_PATH}/public/images/favicon.ico" type="image/x-icon" />
    <style>
		.length_3{width: 180px;}
		form .input-order{margin-bottom: 0px;padding:3px;width:40px;}
		.table-actions{margin-top: 5px; margin-bottom: 5px;padding:0px;}
		.table-list{margin-bottom: 0px;}
	</style>
	<!--[if IE 7]>
	<link rel="stylesheet" href="__PUBLIC__/simpleboot/font-awesome/4.4.0/css/font-awesome-ie7.min.css">
	<![endif]-->
<script type="text/javascript">
//全局
var GV = {
    DIMAUB: "${CONTEXT_PATH}",
    JS_ROOT: "/public/js/",
    TOKEN: ""
};
var r_path = '${BASE_PATH}';
</script>
<!-- Le javascript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="${BASE_PATH}/public/js/jquery.js"></script>
    <script src="${BASE_PATH}/public/js/wind.js"></script>
    <script src="${BASE_PATH}/public/js/wind.js"></script>
    <script src="${BASE_PATH}/public/simpleboot/bootstrap/js/bootstrap.min.js"></script>
    <c:if test="${APP_DEBUG == true }">
 
	<style>
		#think_page_trace_open{
			z-index:9999;
		}
	</style>
</c:if>