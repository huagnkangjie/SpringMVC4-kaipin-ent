<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


	<!--企业认证 start-->
	<div class="business-attestation" style="display: none;" id="certificate-right-now">
		<span>请完成企业资质认证，认证资质会提升企业的公信力和公司信息与职位信息曝光率。</span>
		<a href="${BASE_PATH }/certificate/certificate-company" class="att-btn" title="立即认证">立即认证</a>
		<a href="javascript:void(0)" class="close-att" id="close-att-s" title="关闭"></a>
	</div>
	<!--企业认证 end-->
	
	<!--企业基本信息 start-->
	<div class="basicInfos">
		<div class="bscPeanel">
			<a href="${BASE_PATH }/home">
				<div class="company-logo fl" id="company-logo-show">
					<img src="${STATIC_SCH }/images/fang_img.jpg" width="108" height="108" alt="logo">
				</div>
			</a>
			<div class="simple-infos fl">
				<h5 class="eu-title">
					<strong class="short-name">${simpleEntName}</strong><span class="full-name">[${infos.entName }]</span>
					<a href="javascript:void(0)" class="certi no-certi" style="display:none;" id="zzrz0" onclick="certificateTipPageEnt(99);" title="未认证">未认证</a>
					<a href="javascript:void(0)" class="certi ing-certi" style="display:none;" id="zzrz3" onclick="certificateTipPageEnt(3);" title="审核中">审核中</a>
					<a href="javascript:void(0)" class="certi already-certi" style="display:none;" id="zzrz2" onclick="certificateTipPageEnt(2);" title="已认证">√ 已认证</a>
					<a href="javascript:void(0)" class="certi already-certi" style="display:none;" id="zzrz1" onclick="certificateTipPageEnt(1);" title="未通过">× 未通过</a>
					
				</h5>
				<p class="others">${industryType }</p>
				<p class="others">
					<c:if test="${peopleCount != '0' }">
						已有${peopleCount }员工
					</c:if>
					<c:if test="${peopleCount == '0' }">
						已有0员工
					</c:if>
				</p>
				<a href="${BASE_PATH }/company/basic" class="edit-means" id="editBasic" style="display:none;">编辑资料</a>
				<a href="javascript:void(0);" class="edit-means" id=uploadLogoEnt style="display:none;">编辑头像</a>
			</div>
			
			<div class="edit-window-panel fl">
			<a href="javascript:void(0)" class="sy-edit-btns edit-enterprise" id="edit-enterprise" data-open="open" style="display:none;">编辑</a>
			<div class="ewp-edit-company" style="display:none;">
				<span class="inverted-triangle"></span>
				<div class="ewp-info">
					<form id="formBasicLogoInfo" name="formBasicLogoInfo" action="/basicConctroller/edit.do">
						
						<input type="hidden" name="oper" value="basic_logo"/>
						
						<div class="name">
							<span class="ewp-tips">企业全称</span>
							<input type="text" id="entName" name="entName" value="${infos.entName }">
						</div>
						<div class="simple-name-industry">
							<div class="name fl">
								<span class="ewp-tips">企业简称</span>
								<input type="text" class="ip-w260" id="entSimpleName" name="entSimpleName" value="${infos.entSimpleName }" maxlength="20">
<!-- 								<span class="ewp-tips">在校学生人数</span> -->
<%-- 								<input type="text" class="ip-w260" id="studentCount" name="studentCount" value="${linkInfo.studentCount }" maxlength="8"> --%>
<!-- 								<span class="ewp-tips">历届校友人数</span> -->
<%-- 								<input type="text" class="ip-w260" id="studentTotal" name="studentTotal" value="${linkInfo.studentTotal }" maxlength="8"> --%>
							</div></div>
							

							<div class="simple-name-industry">
							<div class="name  fl">
								<span class="ewp-tips">所属行业</span>
								<select name="industryCode" id="industryCode" value="${infos.industryCode }" class="down-list">
										<option value="">请选择</option>
										<option value="17000100">IT/通信/电子/互联网</option>
										<option value="17000200">金融业</option>
										<option value="17000300">房地产/建筑业</option>
										<option value="17000400">商业服务</option>
										<option value="17000500">贸易/批发/零售/租赁业/消费</option>
										<option value="17000600">文体教育/工艺美术</option>
										<option value="17000700">生产/加工/制造</option>
										<option value="17000800">交通/运输/物流/仓储</option>
										<option value="17000900">服务业</option>
										<option value="17001000">文化/传媒/娱乐/体育</option>
										<option value="17001100">能源/矿产/环保</option>
										<option value="17001200">政府/非盈利机构</option>
										<option value="17001300">农/林/牧/渔/其他</option>
									
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
							<a href="javascript:void(0)" id="saveBasicLogoInfoEnt" class="opear-btn suer-btn go-l">确定</a>
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
		var industryCode;
		
		$(function(){
			
			checkStatus = '${verify_status}';
			
			$("#zzrz" + checkStatus).show(); 
			
			industryCode = '${infos.industryCode}';
			$("#industryCode").val(industryCode);
		});
		
	</script>