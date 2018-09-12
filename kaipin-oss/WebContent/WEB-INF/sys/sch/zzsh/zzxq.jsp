<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/sys/header.jsp"%>
<link rel="stylesheet" href="${BASE_PATH}/public/assets/css/feekback.css" />
<script src="${BASE_PATH}/public/assets/js/rotate/CJL.0.1.min.js"></script>
<script src="${BASE_PATH}/public/assets/js/rotate/ImageTrans.js"></script>
</head>
<body>

	<div class="wrap js-check-wrap">
		<div style="position:fixed;right: 0;width: 150px;">
			<button class="btn btn-primary btn-small js-ajax-pass"
					type="submit" data-action="/admin_post/listorders.html" id="submit">通过</button>
			<button class="btn btn-primary btn-small" style="display:none;" id="submit-ing"
						type="button">通过...</button>	
			<button class="btn btn-primary btn-small js-ajax-no"
			 		type="submit" data-action="/admin_post/listorders.html">不通过</button>
		</div>
		企业名称：${entName }		
		</br></br>
		<input id="idLeft" type="button" value="向左旋转" />
		<input id="idRight" type="button" value="向右旋转" />
<!-- 		<input id="idVertical" type="button" value="垂直翻转" /> -->
<!-- 		<input id="idHorizontal" type="button" value="水平翻转" /> -->
		<input id="idReset" type="button" value="重置" />
<!-- 		<input id="idSrc" type="text" value="2.jpg" /> -->
<!-- 		<input id="idLoad" type="button" value="换图" /> -->
		</br></br>
		营业执照：</br>
		<div id="idContainer" style="width:530px;height:540px;border:#1E90FF 1px solid;"> 
			<img id="businessLicensePath" src="" width="530" height="540"/>
		</div>
		</br></br>
		<c:if test="${idCardPath != ''}">
			身份证：</br></br>
			<img id="idCardPath" src="" width="530" height="540"/></br></br>
		</c:if>
		<c:if test="${employeeCard != ''}">
			工作证：</br></br>
			<img id="employeeCard" src="" width="530" height="540"/></br></br>
		</c:if>
		<c:if test="${businessCard != ''}">
			名片：</br></br>
			<img id="businessCard" src="" width="530" height="540"/></br></br>
		</c:if>
		
		
		
	</div>
	
	
	<div id="tzui-loading-overlay" class="tzui-loading-overlay" style="display: none;"></div>
		
		<div id="audit-noPass-noSel" class="audit-noPass noSel" style="display: none;">
			<div class="audit-tlt">
				不通过审核反馈
			</div>
			
			<div class="item-lists" id="item-lists">
				<h3 class="itm-tips">不符合项目</h3>
				<c:forEach items="${configList }" var="m">
					<div class="reason" id="reason_${m.id }">
						<input type="hidden" value="${m.id }"/>
						<span class="check-icon check-right" data-tag="${m.id }"></span>
						<div class="rs-infos">
							<p class="tlt-info check-right">${m.title }</p>
							<p class="txt"></p>
						</div>
					</div>
				</c:forEach>
				
				<div class="submit-panel">
					<a href="javascript:void(0)" class="sure res-sure">确定</a>
					<a href="javascript:void(0)" class="cancle res-cancle" id="res-cancle">取消</a>
				</div>
			</div>
			
			<div class="tzui-loading-overlayWrite" id="tzui-loading-overlayWrite" style="display: none;"></div>
			<form action="${BASE_PATH }/management/sch/main/zzsh" method="post" id="resForm" name="resForm">
				<input type="hidden" name="schoolId" value="${schoolId }"/>
				<input type="hidden" name="schoolName" value="${entName }"/>
				<input type="hidden" name="oper" value="1" id="oper"/>
				<input type="hidden" name="configIds" value="" id="configIds"/>
				<c:forEach items="${templeteList }" var="m" varStatus="index">
					<div class="result-reason" id="rs_${m.configId }">
						<div class="reason-lists">
							<h3 class="itm-tips">不符合项目</h3>
							<div class="rl-infos">
								<c:forEach items="${m.templeteList}" var="v" varStatus="i">
									<dl>
										<dt><span class="check-icon check-right" data-tag="${m.configId }"></span></dt>
										<dd>${v.content }</dd>
									</dl>
								</c:forEach>
								
								<textarea class="other-reason other-color">请输入其他原因</textarea>
								<input type="hidden" class="result-val" name="templeteList[${index.index }].content"/>
								<input type="hidden" value="${m.configId }" name="templeteList[${index.index }].configId"/>
								<input type="hidden" class="getSelectIndex"/>
								<div class="submit-panel sub-reason-result">
									<a href="javascript:void(0)" class="sure" id="submit-no">确定</a>
									<a href="javascript:void(0)" class="sure" style="display: none;" id="submit-no-ing">确定...</a>
									<a href="javascript:void(0)" class="cancle">取消</a>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>
			</form>
	
	<script src="${BASE_PATH}/public/js/common.js"></script>
	<script src="${BASE_PATH}/public/assets/js/page.js"></script>
	
	
	<script type="text/javascript">
		var businessLicensePath;
		var idCardPath;
		var employeeCard;
		var businessCard;
		$(function(){
			
			businessLicensePath = '${businessLicensePath}';
			idCardPath = '${idCardPath}';
			employeeCard = '${employeeCard}';
			businessCard = '${businessCard}';
// 			$("#businessLicensePath").attr("src",businessLicensePath);
			$("#idCardPath").attr("src",idCardPath);
			$("#employeeCard").attr("src",employeeCard);
			$("#businessCard").attr("src",businessCard);
			
			$(".js-ajax-pass").click(function(){
				$("#oper").val("2");
				operOrNo(2,"submit");
			});
			
			$(".js-ajax-no").click(function(){
				//operOrNo(1,"submit-no");
				$("#oper").val("1");
				$("#tzui-loading-overlay").show();
				$("#audit-noPass-noSel").show();
			});
			
			
			var container = $$("idContainer"), src = businessLicensePath,
			options = {
				onPreLoad: function(){ container.style.backgroundImage = "url('loading.gif')"; },
				onLoad: function(){ container.style.backgroundImage = ""; }
			},
			it = new ImageTrans( container, options );
			it.load(src);
// 			//垂直翻转
// 			$$("idVertical").onclick = function(){ it.vertical(); }
// 			//水平翻转
// 			$$("idHorizontal").onclick = function(){ it.horizontal(); }
			//左旋转
			$$("idLeft").onclick = function(){ it.left(); }
			//右旋转
			$$("idRight").onclick = function(){ it.right(); }
// 			//重置
			$$("idReset").onclick = function(){ it.reset(); }
// 			//换图
// 			$$("idLoad").onclick = function(){ it.load( $$("idSrc").value ); }
			
			
			//通过或者不通过
			function operOrNo(obj,submitType){
				$.ajax({                
					cache: false,    
					async: true, 
					type: "POST",                
					url:  '${BASE_PATH}/management/sch/main/zzsh',                
					data : $('#resForm').serialize(),// 你的formid  
					beforeSend : function(request){
						$("#"+submitType).hide();
						$("#"+submitType+"-ing").show();
						setTimeout(function(){},300)
					},
					error: function(request) { 
						alert("网络错误");
					},                
					success: function(data) {
						
					},
					complete: function(data) { 
						var dataStr = data.responseText;
						$("#"+submitType).show();
						$("#"+submitType+"-ing").hide();
						if(dataStr == '') return;
						var datas = eval('('+dataStr+')');
						console.log(datas);
						if(datas.success){
							alert("审核成功");
							window.opener.location.reload(); 
							window.opener=null;
							window.open('','_self');
							window.close();
							
						}else{
							alert("审核失败");
						}
					}           
				});
			}
			
			$("#res-cancle").click(function(){
				$("#tzui-loading-overlay").hide();
				$("#audit-noPass-noSel").hide();
			});
			
			$(".res-sure").click(function(){
			
				var items = $("#item-lists").find("span");
				var input = $("#item-lists").find("input");
				var flag = false;
				for(var i = 0; i < items.length; i++){
					var v = items[i].className
					if(v.length > 22){
						flag = true;
						break;
					}
				}
				var ids = [];
				for(var i = 0; i < input.length; i++){
					var v = items[i].className
					if(v.length > 22){
						ids.push(input[i].value);
					}
				}
				$("#configIds").val(ids.join(','))
				if(flag){
					operOrNo(1,"submit-no");
				}else{
					alert("请选择不通过的理由");
				}
			});
			
			
			function popupReason(id){
				var $reasonId = $("#reason_"+id);
				var $rsPanel = $("#rs_"+id);
				var resultText = "";
				bindBigSelect();
				//选中大类并显示对应的该小类信息
				function bindBigSelect(){ 
					$reasonId.find(".check-right").off().on("click",function(){
						//var id = $reasonId.find(".check-icon").data("tag");
						$reasonId.find(".check-icon").addClass("check");
						$("#tzui-loading-overlayWrite").show();
						$rsPanel.show();
						bindSmallSelect();
						textareaVal();
						bindSmBtnSure();
						cancleCurrent();
					});
				}
				
				//小类
				function bindSmallSelect(){
					$rsPanel.find("dl").off().on("click",function(){
						var $this = $(this);
						var index = $this.index();
						$this.toggleClass("cur");
						$this.find("dt > .check-icon").toggleClass("check");
					});
				}
				
				//去掉textarea提示文字
				function textareaVal(){
					var $textarea = $rsPanel.find(".other-reason");
						var txt = $textarea.val();
						$textarea.off().focus(function(){
							if(txt === $(this).val()) {
								$(this).val("");  
								$(this).removeClass("other-color");
							}
						}).blur(function(){  
							if($(this).val() == "") {
								$(this).val(txt);  
								$(this).addClass("other-color");
							}
						}); 
				}
				
				//小类中的确定按钮
				function bindSmBtnSure(){
					var resultval = "";
					var mm = "";
					$rsPanel.find(".sub-reason-result .sure").off().on("click",function(){
						resultval = "";
						mm = "";
						$rsPanel.find(".reason-lists dl").each(function(){
							var cur = $(this).attr("class");
							if(cur == "cur"){
								resultval =  resultval +  $(this).find("dd").html() + ";";
								mm = mm + $(this).index()+",";
							}
						});
						if(!($rsPanel.find(".other-reason").val()=="请输入其他原因" || $rsPanel.find(".other-reason").val() == "")){
							resultval += $rsPanel.find(".other-reason").val() + ";";
						}
						$rsPanel.find('.getSelectIndex').val(mm.substring(0,mm.length-1));
						$rsPanel.find(".result-val").val(resultval);
						getTheVal();
						
						
					});	
				}
				
				//取消小分类选择
				function cancleCurrent(){
					$rsPanel.find(".sub-reason-result .cancle").off().on("click",function(){
						getTheVal();
					});
				}
				
				//判断getTheVal后是否取消选中的分类
				function getTheVal(){
					var currVal =  $rsPanel.find(".result-val").val();
					if(currVal == ""){
						$rsPanel.find("dl").each(function(){
							$(this).attr("class","");
							$(this).find("dt .check-icon").removeClass("check");
						});
						$rsPanel.find(".other-reason").addClass("other-color");
						$rsPanel.find(".other-reason").val("请输入其他原因");
						$reasonId.find(".check-icon").removeClass("check");
						closeSmPanle();
					}else{
						var arr = $rsPanel.find('.getSelectIndex').val().split(',');
						for(var i=0;i<arr.length;i++){
							$rsPanel.find("dl").eq(arr[i]).addClass("cur");
							$rsPanel.find("dl").eq(arr[i]).each(function(){
								$(this).find("dt .check-icon").addClass("check");
							})
						}
						closeSmPanle();
					}
					$("#tzui-loading-overlayWrite").hide();
				}
				
				//关闭小分类
				function closeSmPanle(){
					$rsPanel.hide();
				}
			}
			
			var configIds = '${configIds}';
			var ids = configIds.split(",");
			for(var i = 0; i < ids.length; i++){
				popupReason(ids[i]);
			}
			
		});
	</script>
</body>
</html>