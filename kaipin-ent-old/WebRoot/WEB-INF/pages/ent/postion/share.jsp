<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<% String url = request.getRequestURL().toString();
	if(null!=url&&url.startsWith("http://kaipin.tv")) 
	response.sendRedirect("http://www.kaipin.tv");
%>
<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
   	<title>${title }</title>
    <meta  content="开频,校招,校园招聘网,应届生求职,找工作,大学生求职,人才网,宣讲会视频,在线双选会,视频面试,kaipin,应届生求职,暑期兼职,寒假兼职,兼职,实习,大一,大二,大三,大四,研究生" name="Keywords"/>
    <meta content="开频校招通过大数据分析，把校招职位与大学生简历数据匹配，为大学生和企业提供免费校招服务,是学生、企业、学校的连接器,是中国领先的一站式校园垂直招聘社区平台,宣讲会直播点播,在选笔试,视频面试,收发Offer, 大学生直接入职,打破时空限制实现无缝链接,让校园招聘变得很简单" name="Description"/>
    
	<meta name='viewport' content='width=device-width, initial-scale=1.0, maximum-scale=1.0'/>
	<meta content="yes" name="apple-mobile-web-app-capable">
	<meta content="black" name="apple-mobile-web-app-status-bar-style">
	<meta content="telephone=no" name="format-detection">
	<meta content="email=no" name="format-detection">
	<link rel="stylesheet" href="<%=path %>/css/h5.css" />
	<link rel="shortcut icon" href="<%=path%>/favicon.ico" type="image/x-icon" />
	<script type="text/javascript">
		var r_path='<%=basePath%>';
	</script>
	<script type="text/javascript" src="<%=path%>/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript">
		var logo;
		$(function(){
			logo = '${logo}';
			if(logo != 'logo'){
				$("#logo_ent").attr("src",logo)
			}
		});
	</script>
	
	</head>
	<body>
	<!--header-cons start-->
		<div class="header-cons">
			<div class="hd-infos">
				<div class="position-name">
					<h3>${positionInfo.positionName }</h3>
					<p>发布时期：${createTime }（有效期：${positionInfo.endTime }）</p>
					<p>工作地区：${positionInfo.locationCode }</p>
				</div>
				<div class="company-name">
				
					<div class="logos fl">
						<img id="logo_ent" src="<%=path %>/images/default_ent.png"/>
					</div>
					<div class="infos fl">
						<h3>${basic.entName }</h3>
						<p>${companyArea }</p>
						<p>${Industry }
							<c:if test="${peopleNumbers != '' && Industry != '' && Industry != null}">
								/
							</c:if>
							<c:if test="${peopleNumbers != ''}">
								${peopleNumbers }人
							</c:if>
						</p>
					</div>
					<div class="clear"></div>
				</div>
			</div>
		</div>
		<!--header-cons end-->
		
		<!--position-wage start-->
		<div class="position-wage">
			<ul class="wage-cons">
				<li>
					<span class="tips-info">薪资</span>
					<span class="salary">${salaryTypeVal }</span>
				</li>
				<li>
					<span class="tips-info">人数</span>
					<span class="other">${positionInfo.numbers }人</span>
				</li>
				<li>
					<span class="tips-info">学历</span>
					<span class="other">${positionInfo.educationCode }</span>
				</li>
				<c:if test="${positionInfo.workExperienceCode != ''}">
					<li>
						<span class="tips-info">经验</span>
						<span class="other">${positionInfo.workExperienceCode }</span>
					</li>
				</c:if>
				<li>
					<span class="tips-info">年龄</span>
					<span class="other">${positionInfo.ageStart } - ${positionInfo.ageEnd }岁</span>
				</li>
			</ul>
		</div>
		<!--position-wage end-->
		
		<!--position-duty start-->
		<div class="position-duty">
			<h3>岗位职责</h3>
			<div class="duty-lists">
				${positionInfo.positionResponsibility }
			</div>
		</div>
		<!--position-duty end-->
		
		<!--other-infos start-->
		<div class="other-infos">
			<h3>其他信息</h3>
			<div class="lists">
				<ul>
					<c:if test="${positionInfo.superior != ''}">
						<li>
							<span class="tips">汇报对象：</span>
							<span>${positionInfo.superior }</span>
						</li>
					</c:if>
					
					<c:if test="${departmentNumbers != ''}">
						<li>
							<span class="tips">下属人数：</span>
							<span>${departmentNumbers }</span>
						</li>
					</c:if>
					
					<c:if test="${positionInfo.industryCode != ''}">
						<li>
							<span class="tips">所属行业：</span>
							<span>${positionInfo.industryCode }</span>
						</li>
					</c:if>
					<c:if test="${positionInfo.department != ''}">
						<li>
							<span class="tips">所属部门：</span>
							<span>${positionInfo.department }</span>
						</li>
					</c:if>
					<c:if test="${companyType != ''}">
						<li>
							<span class="tips">企业性质：</span>
							<span>${companyType }</span>
						</li>
					</c:if>
					<c:if test="${peopleNumbers != ''}">
						<li>
							<span class="tips">企业规模：</span>
							<span>${peopleNumbers }人</span>
						</li>
					</c:if>
				</ul>
			</div>
		</div>
		<!--other-infos end-->
		
		<!--other-position start-->
		<div class="other-position" style="display:none;">
			<div class="other-pt-tlt">
				<span class="blue-line"></span>
				<span class="pt-title">您可能感兴趣的职位</span>
			</div>
			
			<div class="other-pt-lists">
				<ul>
					<li>
						<a href="javascript:void()">
							<div class="job-title">
								<span class="tlt fl">测试工程师</span>
								<span class="time fr">1小时前</span>
								<span class="clear"></span>
							</div>
							<div class="job-where">
								<span class="adress fl">小米科技有限公司</span>
								<span class="city fr">重庆、广州</span>
								<span class="clear"></span>
							</div>
						</a>
					</li>
					
					<li>
						<a href="javascript:void()">
							<div class="job-title">
								<span class="tlt fl">测试工程师</span>
								<span class="time fr">1小时前</span>
								<span class="clear"></span>
							</div>
							<div class="job-where">
								<span class="adress fl">小米科技有限公司</span>
								<span class="city fr">重庆、广州</span>
								<span class="clear"></span>
							</div>
						</a>
					</li>
					<li>
						<a href="javascript:void()">
							<div class="job-title">
								<span class="tlt fl">测试工程师</span>
								<span class="time fr">1小时前</span>
								<span class="clear"></span>
							</div>
							<div class="job-where">
								<span class="adress fl">小米科技有限公司</span>
								<span class="city fr">重庆、广州</span>
								<span class="clear"></span>
							</div>
						</a>
					</li>
					<li>
						<a href="javascript:void()">
							<div class="job-title">
								<span class="tlt fl">测试工程师</span>
								<span class="time fr">1小时前</span>
								<span class="clear"></span>
							</div>
							<div class="job-where">
								<span class="adress fl">小米科技有限公司</span>
								<span class="city fr">重庆、广州</span>
								<span class="clear"></span>
							</div>
						</a>
					</li>
					<li>
						<a href="javascript:void()">
							<div class="job-title">
								<span class="tlt fl">测试工程师</span>
								<span class="time fr">1小时前</span>
								<span class="clear"></span>
							</div>
							<div class="job-where">
								<span class="adress fl">小米科技有限公司</span>
								<span class="city fr">重庆、广州</span>
								<span class="clear"></span>
							</div>
						</a>
					</li>
				</ul>
			</div>
		</div>
		<!--other-position end-->
		
		<!--down-load start-->
		<div class="down-load">
			<div class="down-logo fl">
				<img src="<%=path %>/images/down-logo.png">
			</div>
			<div class="introduce fl">
				<p class="t1">开频校招</p>
				<p class="t2">毕业生求职神器</p>
				<p class="t3"></p>
			</div>
			<div class="downLoad-btn fr">
				<a href="http://www.kaipin.tv/xssm/index.html" class="btn-down">下载</a>
			</div>
			<div class="clear"></div>
		</div>
		<!--down-load end-->
		
		<!--footer start-->
		<div class="footer">
			<div class="foot-con fl" id="share-or-collect">
				<dl>
					<dt><img src="<%=path %>/images/share.png"></dt>
					<dd>分享</dd>
				</dl>
				<dl>
					<dt><img src="<%=path %>/images/collect.png" onclick="xz();"></dt>
					<dd>收藏</dd>
				</dl>
				<div class="clear"></div>
			</div>
			<div class="foot-con ft-send fl" onclick="xz();">
				投递简历
			</div>
			<div class="clear"></div>
		</div>
		<!--footer end-->
		
		<div class="layout" id="layout">
			<div class="layout-pan"></div>
			<img src="<%=path %>/images/share-arr.png" class="arrows">
			<div class="I-know">
				<a href="javascipt:;" id="know">知道了</a>
			</div>
		</div>
		
		<script type="text/javascript" src="<%=path %>/js/font.js" ></script>
		<script type="text/javascript">
				document.body.addEventListener('touchstart', function () {});
				var layout = document.getElementById("layout");
				var know = document.getElementById("know");
				var shareOrCollect = document.getElementById("share-or-collect");
				var html = document.documentElement;
				var windowWidth = html.clientWidth; 
				var windowHeight = html.clientHeight;
				layout.style.width = windowWidth + "px";
				layout.style.height = windowHeight + "px";
				shareOrCollect.onclick = function(){
					layout.style.display = "block";
				};
				know.onclick = function(){
					layout.style.display = "none";
				};
				
				function xz(){
					window.location.href="http://www.kaipin.tv/xssm/index.html";
				}
		</script>
	</body>
</html>

