<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


	<!--企业认证 start-->
	<div class="business-attestation" style="display: none;">
		<span>请完成企业资质认证，认证资质会提升企业的公信力和公司信息与职位信息曝光率。</span>
		<a href="javascript:void(0)" class="att-btn" title="立即认证">立即认证</a>
		<a href="javascript:void(0)" class="close-att" id="close-att" title="关闭"></a>
	</div>
	<!--企业认证 end-->
	
	<!--企业基本信息 start-->
	<div class="basicInfos">
		<div class="bscPeanel">
			<a href="${BASE_PATH }/home">
				<div class="company-logo fl">
					<img id="company-logo" src="${STATIC_SCH }/images/fang_img.jpg" width="108" height="108" alt="logo">
				</div>
			</a>
			<div class="simple-infos fl">
				<h5 class="eu-title">
					<strong class="short-name">${linkInfo.schoolShortName }</strong><span class="full-name">[${info.schoolName }]</span>
					<a href="javascript:void(0)" class="certi no-certi" style="display:none;" id="zzrz0" onclick="certificateTipPage(99);" title="未认证">未认证</a>
					<a href="javascript:void(0)" class="certi ing-certi" style="display:none;" id="zzrz3" onclick="certificateTipPage(3);" title="审核中">审核中</a>
					<a href="javascript:void(0)" class="certi already-certi" style="display:none;" id="zzrz2" onclick="certificateTipPage(3);" title="已认证">√ 已认证</a>
					<a href="javascript:void(0)" class="certi already-certi" style="display:none;" id="zzrz1" onclick="certificateTipPage(3);" title="未通过">× 未通过</a>
					
				</h5>
				<p class="others" id="schoolFeatureName">院校类型</p>
				<p class="others" id="studentCountName">院校学生人数</p>
				<a href="${BASE_PATH }/basic/init" class="edit-means" id="editBasic" style="display:none;">编辑资料</a>
				<a href="javascript:void(0);" class="edit-means" id="uploadLogo" style="display:none;">编辑头像</a>
			</div>
			
			<div class="edit-window-panel fl">
			<a href="javascript:void(0)" class="sy-edit-btns edit-enterprise" id="edit-enterprise" data-open="open" style="display:none;">编辑</a>
			<div class="ewp-edit-company" style="display:none;">
				<span class="inverted-triangle"></span>
				<div class="ewp-info">
					<form id="formBasicLogoInfo" name="formBasicLogoInfo" action="/basicConctroller/edit.do">
						
						<input type="hidden" name="oper" value="basic_logo"/>
						
						<div class="name" style="display:none;">
							<span class="ewp-tips">公司全称</span>
							<input type="text" id="entName" name="entName">
						</div>
						<div class="simple-name-industry">
							<div class="name fl">
								<span class="ewp-tips">学校简称</span>
								<input type="text" class="ip-w260" id="schoolShortName" name="schoolShortName" value="${linkInfo.schoolShortName }" maxlength="20">
								<span class="ewp-tips">在校学生人数</span>
								<input type="text" class="ip-w260" id="studentCount" name="studentCount" value="${linkInfo.studentCount }" maxlength="8">
								<span class="ewp-tips">历届校友人数</span>
								<input type="text" class="ip-w260" id="studentTotal" name="studentTotal" value="${linkInfo.studentTotal }" maxlength="8">
							</div></div>
							

							<div class="simple-name-industry">
							<div class="name  fl">
								<span class="ewp-tips">院校类型</span>
								<select name="schoolFeatureId" id="schoolFeatureId" value="${linkInfo.schoolFeatureId }" class="down-list">
										<option value="0">请选择</option>
										<option value="10000">211院校</option>
										<option value="10001">985院校</option>
										<option value="10002">211院校/985院校</option>
										<option value="10003">重本</option>
										<option value="10004">二本</option>
										<option value="10005">三本</option>
										<option value="10006">专科</option>
										<option value="10007">高职</option>
									
								</select>
							</div>
						</div>
						
<!-- 									<div class="simple-name-industry"> -->
<!-- 										<div class="name fl"> -->
<!-- 											<span class="ewp-tips">员工数量</span> -->
<!-- 											<input type="text" class="ip-w260" id="numbers" name="peopleNumber" /> -->
<!-- 										</div> -->
<!-- 									</div> -->
						
						
						<div class="sure-or-cancel">
							<a href="javascript:void(0)" id="saveBasicLogoInfo" class="opear-btn suer-btn go-l">确定</a>
							<a href="javascript:void(0)" id="cancleBasicLogoInfo" class="opear-btn cancle-btn">取消</a>
						</div>
					</form>		
				</div>
				
			</div>
		</div>
			<div class="attention-infos fr">
				<a href="${BASE_PATH }/followfans/init?oper=follow">
					<dl>
						<dt id="floowCount">0</dt>
						<dd>关注</dd>
					</dl>
				</a>
				<a href="${BASE_PATH }/followfans/init?oper=fans" class="bdRight">
					<dl>
						<dt id="fansCount">0</dt>
						<dd>粉丝</dd>
					</dl>
				</a>
			</div>
		</div>
	</div>
	<!--企业基本信息 end-->
	

	<script type="text/javascript">
		
		var headUrl;
		var checkStatus;
		var orgName;
		var schoolFeatureId;
		var schoolFeature;
		var studentCountName;
		
		$(function(){
			headUrl = '${headUrl}';
			checkStatus = '${checkStatus}';
			orgName = '${linkInfo.schoolShortName }';
			schoolFeatureId = '${linkInfo.schoolFeatureId }';
			schoolFeature = '${schoolFeature }';
			studentCountName = '${studentCountName }';
			
			if(headUrl != '0'){
				$("#company-logo").attr("src",headUrl);
				$("#company-logo-head").css("background-image","url("+headUrl+")");
				$("#hd-logo").attr("src",headUrl);
			}else{
				$("#editLogo").show();
				headUrl = r_path + "/static/web/university/images/default-hdPic.jpg";
			}
		
			//院校特色名称
			if(schoolFeatureId == '0' || schoolFeatureId == ''){
				$("#schoolFeatureName").html("院校类型");
				$("#schoolFeatureName").show();
			}else{
				$("#schoolFeatureName").html(schoolFeature)
			}
			
			$("#schoolFeatureId").val(schoolFeatureId);
			
			if(studentCountName != '0'){//院校特色名称
				$("#studentCountName").html(studentCountName)
			}
			
			$("#zzrz" + checkStatus).show();
			
		});
		
	</script>