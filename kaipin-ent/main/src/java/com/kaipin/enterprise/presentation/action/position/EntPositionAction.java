package com.kaipin.enterprise.presentation.action.position;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.deser.Deserializers.Base;
import com.kaipin.common.constants.AppSearchConstant;
import com.kaipin.common.constants.Constant;
import com.kaipin.common.constants.ConstantTables;
import com.kaipin.common.constants.FeedStatus;
import com.kaipin.common.entity.DataGridJson;
import com.kaipin.common.entity.Json;
import com.kaipin.common.entity.Page;
import com.kaipin.common.entity.StautsBean;
import com.kaipin.common.entity.User;
import com.kaipin.common.presentation.action.BaseAction;
import com.kaipin.common.service.common.IBaseCodeService;
import com.kaipin.common.util.CookieUtil;
import com.kaipin.common.util.HttpPostUtil;
import com.kaipin.common.util.JsonUtil;
import com.kaipin.common.util.LogUtil;
import com.kaipin.common.util.LuceneUtil;
import com.kaipin.common.util.PropUtil;
import com.kaipin.common.util.StringUtil;
import com.kaipin.common.util.TimeUtil;
import com.kaipin.common.util.UuidUtil;
import com.kaipin.enterprise.model.position.PositionInfo;
import com.kaipin.enterprise.model.user.CompanyInfo;
import com.kaipin.enterprise.service.position.IPositionService;
import com.kaipin.enterprise.service.user.ICompanyInfoService;
import com.kaipin.university.model.feed.Feed;
import com.kaipin.university.service.feed.IFeedService;

import net.sf.json.JSONObject;


/**
 * 企业--职位
 * @author Mr-H
 *
 */
@RequestMapping("/position")
@Controller
public class EntPositionAction extends BaseAction{
	
	@Autowired
	private IPositionService postionservice;
	@Autowired
	private IBaseCodeService baseCodeService;
	@Autowired
	private ICompanyInfoService comInfoService;
	@Autowired
	private IFeedService feedService;
	
	/**
	 * 初始化职位管理
	 * @return
	 */
	@RequestMapping(value="")  
	public String init(HttpServletRequest request, Model model, User user) {
		
		initList(request,  model);
		String orgName = this.getOrgName(request, user);
		this.setSysAttr(model, orgName, null, null);
		
		return "/ent/position/position";  
	}
	
	/**
	 * 初始化所有的list
	 */
	public void initList(HttpServletRequest request, Model model){
		//所属行业
		model.addAttribute("industryTypeList", getCommList(ConstantTables.COMM_INDUSTRY,Constant.VALUE_ONE));
		//学历
		model.addAttribute("eduList", getCommList(ConstantTables.COMM_EDU,""));
		//职能的第一级
		model.addAttribute("jobTypeList", getCommList(ConstantTables.COMM_JOB_TYPE,Constant.VALUE_ONE));
		//工作地区第一级
		model.addAttribute("workAreaList", getCommList(ConstantTables.COMM_LOCATION," parent_code = '489' or location_code = '489' "));
		//工作经验
		model.addAttribute("expList", getCommList(ConstantTables.COMM_WORK_EXPE,Constant.VALUE_ONE));
	}
	
	/**
	 * 初始化
	 * 发布职位页面
	 * @return
	 */
	@RequestMapping(value="/pulishPage")  
	public String pulishPage(HttpServletRequest request, Model model, User user) {  
		try {
			initList(request,  model);
			
			String orgName = this.getOrgName(request, user);
			this.setSysAttr(model, orgName, null, null);
			
			return "/ent/position/pulishPage";  
		} catch (Exception e) {
			return null;
		}
		
	}
	
	/**
	 * 职位直接发布 
	 * 和 预览
	 * @param request
	 * @param function
	 * @param oper == preview说明是从预览界面进行发布
	 * @return
	 */
	@RequestMapping(value="/pulish")  
	public String pulish(HttpServletRequest request, PositionInfo function, Model model, String oper) {  
		try {
			String cookie_companyId = CookieUtil.getCookieInfoByKey(request, Constant.USER_ORG_ID);
			String cookie_uId = CookieUtil.getCookieInfoByKey(request, Constant.USER_UID);
			int status = function.getStatus();
			if(status == Constant.OPER_ONE){//预览
				//把实体转换成json字符串返回给页面
				model.addAttribute("positionJsonStr", JSONObject.fromObject(function).toString());
				//顶部时间
				Long time = TimeUtil.currentTimeMillis();
				String times = TimeUtil.getTimeByMillis(time);
				String mouth = times.substring(5, 7);
				mouth = Integer.valueOf(mouth) >= 10 ? mouth : mouth.substring(1, 2);
				times = times.substring(0, 4) + "年" + mouth + "月" + times.substring(8, 10) + "日";
				//function.setCreateTime(time);
				model.addAttribute("createTime", times);
				//工作地区
				String workArea = function.getLocationCode();
				String parentCode = "";
				if(StringUtil.isNotEmpty(workArea)){
					workArea = getWorkArea(workArea);
					
					function.setLocationCode(workArea);
				}
				//获取行业
				String industryType = function.getIndustryCode();
				if(StringUtil.isNotEmpty(industryType)){
					industryType = baseCodeService.getNameByCode(ConstantTables.COMM_INDUSTRY, 
							ConstantTables.INDUSTRY_COL_CODE, industryType, ConstantTables.INDUSTRY_COL_NAME);
					function.setIndustryCode(industryType);
				}
				//获取教育
				String edu = function.getEducationCode();
				if(StringUtil.isNotEmpty(edu)){
					edu = baseCodeService.getNameByCode(ConstantTables.COMM_EDU, 
							ConstantTables.EDU_COL_CODE, edu, ConstantTables.EDU_COL_NAME);
					function.setEducationCode(edu);
				}
				//职能
				String jobType = function.getJobTypeCode();
				if(StringUtil.isNotEmpty(jobType)){
					jobType = baseCodeService.getNameByCode(ConstantTables.COMM_JOB_TYPE, 
							ConstantTables.JOB_COL_CODE, jobType, ConstantTables.JOB_COL_NAME);
					function.setJobTypeCode(jobType);
				}
				
				//工作经验
				String workExp = function.getWorkExperienceCode();
				if(StringUtil.isNotEmpty(workExp)){
					workExp = baseCodeService.getNameByCode(ConstantTables.COMM_WORK_EXPE, 
							ConstantTables.EXP_COL_CODE, workExp, ConstantTables.EXP_COL_NAME);
					function.setWorkExperienceCode(workExp);
				}else{
					function.setWorkExperienceCode("");
				}
				
				//薪资
				int salaryType = function.getSalaryType();
				String salaryTypeVal = "面议";
				int salaryStart = 0;
				int salaryEnd = 0;
				
				if(function.getSalaryEnd() != null){
					salaryEnd = function.getSalaryEnd();
				}
				
				if(function.getSalaryStart() != null){
					salaryStart = function.getSalaryStart();
				}
				
				if(salaryType == Constant.OPER_ZERO){
					salaryTypeVal = "面议";
				}else if(salaryType == Constant.OPER_ONE){
					//if(StringUtil.isNotEmpty(salaryStart) && StringUtil.isNotEmpty(salaryEnd)){
						salaryTypeVal = "月薪    " + salaryStart + " - " + salaryEnd + " 元";
					//}else {
						//salaryTypeVal = "月薪";
					//}
				}else if(salaryType == Constant.OPER_TWO){
					//if(StringUtil.isNotEmpty(salaryStart) && StringUtil.isNotEmpty(salaryEnd)){
						salaryTypeVal = "年薪    " + salaryStart + " - " + salaryEnd + " 元";
					//}else {
					//	salaryTypeVal = "年薪";
					//}
				}
				model.addAttribute("salaryTypeVal", salaryTypeVal);
				
				//性别
				String sex = function.getSexCode();
				if(StringUtil.isNotEmpty(sex)){
					if(sex.equals(Constant.VALUE_ZERO)){
						sex = "不限";
					}else if(sex.equals(Constant.VALUE_ONE)){
						sex = "女";
					}else if(sex.equals(Constant.VALUE_TWO)){
						sex = "男";
					}else{
						sex = "不限";
					}
					model.addAttribute("sexVal", sex);
					function.setSexCode(sex);
				}
				
				//有效期
				String endTime = function.getEndTime();
				if(StringUtil.isNotEmpty(endTime)){
					if(endTime.equals("0")){
						endTime = "长期有效";
					}else{
						endTime = endTime + "个月";
					}
						
				}else{
					endTime = "长期有效";
				}
				function.setEndTime(endTime);
				
				//年薪福利
				String salaryYear = function.getSalaryYear();
				if(StringUtil.isNotEmpty(salaryYear)){
					if(salaryYear.equals(Constant.VALUE_ZERO)){
						salaryYear = "无";
					}else{
						if(salaryYear.equals("24")){
							salaryYear = salaryYear + "个月以上";
						}else{
							salaryYear = salaryYear + "个月";
						}
					}
				}else{
					salaryYear = "";
				}
				model.addAttribute("salaryYearVal", salaryYear);
				function.setSalaryYear(salaryYear);
				
				//专业要求
				String majroRequest = function.getMajorRequest();
				if(StringUtil.isNotEmpty(majroRequest)){
					String ids[] = majroRequest.split(",");
					String name = "";
					for (int i = 0; i < ids.length; i++) {
						name = name + " " +baseCodeService.getNameByCode(ConstantTables.COMM_MAJOR, 
							ConstantTables.MAJOR_COL_CODE, ids[i], ConstantTables.MAJOR_COL_NAME);
					}
					function.setMajorRequest(name);
				}
					
				//企业性质
				CompanyInfo info = comInfoService.selectByPrimaryKey(cookie_companyId);
				String companyType = info.getCompanyTypeCode();
				if(StringUtil.isEmpty(companyType)){
					companyType = "无";
				}else{
					companyType = baseCodeService.getNameByCode(ConstantTables.COMM_ENT_TYPE, 
							ConstantTables.ENT_TYPE_COL_CODE, companyType, ConstantTables.ENT_TYPE_COL_NAME);
				}
				//企业所属行业
				String companyIndustry = info.getIndustryCode();
				if(StringUtil.isNotEmpty(companyIndustry)){
					companyIndustry = baseCodeService.getNameByCode(ConstantTables.COMM_INDUSTRY, 
							ConstantTables.INDUSTRY_COL_CODE, companyIndustry, ConstantTables.INDUSTRY_COL_NAME);
				}else{
					companyIndustry = ""; 
				}
				//企业所在区域
				String companyArea = info.getOfficeArea();
				if(StringUtil.isNotEmpty(companyArea)){
					companyArea = getWorkArea(companyArea);
				}else{
					companyArea = "";
				}
				//下属人数
				Object o = function.getDepartmentNumbers();
				String departmentNumbers = "";
				if(o != null){
					departmentNumbers = String.valueOf(o);
				}
				model.addAttribute("departmentNumbers", departmentNumbers);
				
				//企业规模
				Object o2 = info.getPeopleNumber();
				String peopleNumber = "";
				if(o2 != null){
					peopleNumber = String.valueOf(o2);
				}
				model.addAttribute("peopleNumbers", peopleNumber);

				model.addAttribute("basic", info);
				model.addAttribute("companyType", companyType);
				model.addAttribute("companyIndustry", companyIndustry);
				model.addAttribute("workArea", workArea);
				model.addAttribute("companyArea", companyArea);
				
				model.addAttribute("positionInfo", function);
				model.addAttribute("logo", info.getLogo());
				
				return "/ent/position/previewPage";
			}else{
				//直接发布
				String positionId = UuidUtil.getUUID();
				function.setId(positionId);
				function.setCompanyId(cookie_companyId);
				function.setStatus(Byte.valueOf(Constant.VALUE_ZERO));
				long create_time = TimeUtil.currentTimeMillis();
				function.setCreateTime(create_time);
				function.setLastUpdatedTime(create_time);
				function.setCompanyName("");
				int endTime = Integer.valueOf(function.getEndTime());
				if(endTime == Constant.OPER_ZERO){
					function.setEndTime(TimeUtil.currentTimeMillisByTime("2050-12-30 00:00:00")+"");
				}else{
					String time = TimeUtil.getLaterMouthDate(endTime, TimeUtil.getDate()) + " 00:00:00";
					function.setEndTime(TimeUtil.currentTimeMillisByTime(time)+"");
				}
				postionservice.insertSelective(function);
				
				//全文检索 新建索引
				LuceneUtil.luceneOpt(positionId, Constant.VALUE_ONE, Constant.VALUE_ZERO);
				//创建消息流
				Feed feed = new Feed();
				feed.setId(UuidUtil.getUUID());
				feed.setResourceActType(Integer.valueOf(FeedStatus.FEED_ENT_POSITION));
				feed.setUid(cookie_uId);
				feed.setFeedType(Integer.valueOf(Constant.VALUE_ZERO));
				feed.setCreateUid(cookie_companyId);
				feed.setResourceTable(FeedStatus.T_FEED_POSITION);
				feed.setResourceId(positionId);
				feed.setCreateTime(TimeUtil.currentTimeMillis());
				createFeed(feed);
				
				request.setAttribute("add", "add");
				return "/ent/position/position";  
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	/**
	 * 预览后
	 * 发布
	 * @param postionStr
	 * @return
	 */
	@RequestMapping("/pulishOnPreview")
	@ResponseBody
	public Json pulishOnPreview(HttpServletRequest request,String postionStr){
		Json json = new Json();
		try {
			
			String cookie_companyId = CookieUtil.getCookieInfoByKey(request, Constant.USER_ORG_ID);
			String cookie_uId = CookieUtil.getCookieInfoByKey(request, Constant.USER_UID);
			
			postionStr = URLDecoder.decode(postionStr, "UTF-8");
			JSONObject jobj = JSONObject.fromObject(postionStr);   
			PositionInfo function = 
					(PositionInfo) JSONObject.toBean(jobj,PositionInfo.class); 
			String positionId = UuidUtil.getUUID();
			function.setId(positionId);
			function.setCompanyId(cookie_companyId);
			function.setStatus(Byte.valueOf(Constant.VALUE_ZERO));
			long create_time = TimeUtil.currentTimeMillis();
			function.setCreateTime(create_time);
			function.setLastUpdatedTime(create_time);
			function.setCompanyName("");
			int endTime = Integer.valueOf(function.getEndTime());
			if(endTime == Constant.OPER_ZERO){
				function.setEndTime(TimeUtil.currentTimeMillisByTime("2050-12-30 00:00:00")+"");
			}else{
				String time = TimeUtil.getLaterMouthDate(endTime, TimeUtil.getDate() + " 00:00:00");
				function.setEndTime(TimeUtil.currentTimeMillisByTime(time)+"");
			}
			
			int i = postionservice.insertSelective(function);
			if(i == 1){
				json.setSuccess(true);
				
				//全文检索 新建索引
				LuceneUtil.luceneOpt(positionId, Constant.VALUE_ONE, Constant.VALUE_ZERO);
				
				//创建消息流
				Feed feed = new Feed();
				feed.setId(UuidUtil.getUUID());
				feed.setFeedType(Integer.valueOf(FeedStatus.FEED_ENT_POSITION));
				feed.setUid(cookie_uId);
				feed.setCreateUid(cookie_companyId);
				feed.setResourceTable(FeedStatus.T_FEED_POSITION);
				feed.setResourceId(positionId);
				feed.setCreateTime(TimeUtil.currentTimeMillis());
				createFeed(feed);
				
			}else{
				json.setSuccess(false);
			}
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	/**
	 * 职位列表页面
	 * @return
	 */
	@RequestMapping("/plist")
	public String positionListPage(HttpServletRequest request, String postionId, String org_name, Model model, User user){
		try {
			String companyId = super.getOrgId(request, user);
			model.addAttribute("companyId", companyId);
		
			CompanyInfo info = comInfoService.selectByPrimaryKey(companyId);
			String orgName = "企业";
			if(info != null){
				orgName = info.getEntName();
			}
			
			
			this.setSysAttr(model, orgName + " - 职位列表", null, null);
			
			return "/ent/position/show_list";
		} catch (Exception e) {
			e.printStackTrace();
			return ERRO;
		}
	}
	
	/**
	 * 首页维护
	 * 
	 * 预览后维护
	 * @return
	 */
	@RequestMapping(value="/editPage")  
	public String editPage(HttpServletRequest request, String postionId, String postionStr, Model model) {  
		try {
			//初始化下拉列表
			initList(request,  model);
			
			PositionInfo function = new PositionInfo();
			
			if(StringUtil.isNotEmpty(postionId)){
				function =	postionservice.selectByPrimaryKey(postionId);
				//有效时间
			}
			
			if(StringUtil.isNotEmpty(postionStr)){
				postionStr = URLDecoder.decode(postionStr, "UTF-8");
				JSONObject jobj = JSONObject.fromObject(postionStr); 
				function = 
						(PositionInfo) JSONObject.toBean(jobj,PositionInfo.class); 
			}
				//工作地区
			String workArea = function.getLocationCode();
			String workAreaParentCode = "";
			String workArea1Name = "";
			String workArea2Name = "";
			if(StringUtil.isNotEmpty(workArea)){
				workAreaParentCode = baseCodeService.getNameByCode(ConstantTables.COMM_LOCATION, 
						ConstantTables.AREA_COL_CODE, workArea, ConstantTables.COL_PARENT_CODE);
				workArea2Name = baseCodeService.getNameByCode(ConstantTables.COMM_LOCATION, 
						ConstantTables.AREA_COL_CODE, workArea, ConstantTables.AREA_COL_NAME);
				if(workAreaParentCode.equals("489") || StringUtil.isEmpty(workAreaParentCode)){
					workArea1Name = baseCodeService.getNameByCode(ConstantTables.COMM_LOCATION, 
							ConstantTables.AREA_COL_CODE, workArea, ConstantTables.AREA_COL_NAME);
				}else{
					workArea1Name = baseCodeService.getNameByCode(ConstantTables.COMM_LOCATION, 
							ConstantTables.AREA_COL_CODE, workAreaParentCode, ConstantTables.AREA_COL_NAME);
				}
				
				model.addAttribute("workArea1Name", workArea1Name);
				model.addAttribute("workArea2Name", workArea2Name);
			}
				
//				if(StringUtil.isNotEmpty(workArea)){
//					workArea = codeService.getCityNameByCode(workArea);
//					model.addAttribute("workArea2Name", workArea);
//					if(workArea.equals("北京市") || workArea.equals("天津市") || workArea.equals("重庆市") || workArea.equals("上海市")){
//						model.addAttribute("workArea1Name",workArea);
//					}else{
//						Map<String,Object> map = codeService.getCityMapByCode(code);
//						String pId = String.valueOf(map.get("pk_defdoc1"));
//						Map<String,Object> mapProvice = codeService.getCityById(pId);
//						String provice = String.valueOf(mapProvice.get("docname"));
//						model.addAttribute("workArea1Name", provice);
//					}
//				}
			
			
				//获取行业
				String industryType = function.getIndustryCode();
				if(StringUtil.isNotEmpty(industryType)){
					industryType = baseCodeService.getNameByCode(ConstantTables.COMM_INDUSTRY, 
							ConstantTables.INDUSTRY_COL_CODE, industryType, ConstantTables.INDUSTRY_COL_NAME);
					model.addAttribute("industryTypeName", industryType);
				}
				//获取教育
				String edu = function.getEducationCode();
				if(StringUtil.isNotEmpty(edu)){
					edu = baseCodeService.getNameByCode(ConstantTables.COMM_EDU, 
							ConstantTables.EDU_COL_CODE, edu, ConstantTables.EDU_COL_NAME);
					model.addAttribute("eduName", edu);
				}
				
				//职能
				String jobType = function.getJobTypeCode();
				String jobTypeParentCode = "";
				String code1 = "";
				String code2 = "";
				if(StringUtil.isNotEmpty(jobType)){
					jobTypeParentCode = baseCodeService.getNameByCode(ConstantTables.COMM_JOB_TYPE, 
							ConstantTables.JOB_COL_CODE, jobType, ConstantTables.COL_PARENT_CODE);
					code2 = baseCodeService.getNameByCode(ConstantTables.COMM_JOB_TYPE, 
							ConstantTables.JOB_COL_CODE, jobTypeParentCode, ConstantTables.JOB_COL_NAME);
					code1 = baseCodeService.getNameByCode(ConstantTables.COMM_JOB_TYPE, 
							ConstantTables.JOB_COL_CODE, jobType, ConstantTables.JOB_COL_NAME);
					model.addAttribute("jobType2Name", code2);
					model.addAttribute("jobType1Name", code1);
				}
				
				//工作经验
				String workExp = function.getWorkExperienceCode();
				if(StringUtil.isNotEmpty(workExp)){
					workExp = baseCodeService.getNameByCode(ConstantTables.COMM_WORK_EXPE, 
							ConstantTables.EXP_COL_CODE, workExp, ConstantTables.EXP_COL_NAME);
					model.addAttribute("workExpName", workExp);
				}else{
					model.addAttribute("workExpName", "请选择");
				}
				
				//性别
				String sex = function.getSexCode();
				if(StringUtil.isNotEmpty(sex)){
					if(sex.equals(Constant.VALUE_ZERO)){
						sex = "不限";
					}else if(sex.equals(Constant.VALUE_ONE)){
						sex = "女";
					}else if(sex.equals(Constant.VALUE_TWO)){
						sex = "男";
					}else{
						sex = "不限";
					}
					model.addAttribute("sexName", sex);
				}
				
				//年薪福利
				String salarYear = function.getSalaryYear();
				String salarYearName = "";
				if(StringUtil.isNotEmpty(salarYear)){
					if(salarYear.equals(Constant.VALUE_ZERO)){
						salarYearName = "无";
					}else{
						if(salarYear.equals("24")){
							salarYearName = salarYear + "个月以上";
						}else{
							salarYearName = salarYear + "个月";
						}
					}
				}else{
					salarYearName = "请选择";
				}
				model.addAttribute("salarYearName", salarYearName);
				
				//专业要求
				String majroRequest = function.getMajorRequest();
				if(StringUtil.isNotEmpty(majroRequest)){
					String ids[] = majroRequest.split(",");
					model.addAttribute("majroLenght", "已选择"+ids.length+"个专业");
				}
				
				//有效时间
				if(StringUtil.isNotEmpty(postionStr)){
					String endTime = function.getEndTime();
					endTime = TimeUtil.getTimeByMillis(Long.valueOf(endTime))
							.substring(0, 10);
					if(StringUtil.isNotEmpty(endTime)){
						String showMouths = "";
						if(Integer.valueOf(endTime) > 12){
							showMouths = "长期有效";
							endTime = Constant.VALUE_ZERO;
						}else{
							showMouths = endTime + "个月";
						}
						model.addAttribute("mouths", endTime);
						model.addAttribute("showMouths", showMouths);
					} 
				}else{
					String endTime = function.getEndTime();
					endTime = TimeUtil.getTimeByMillis(Long.valueOf(endTime))
							.substring(0, 10);
					if(StringUtil.isNotEmpty(endTime)){
						String mouths = TimeUtil.getMouthsBetweenMouth(
								TimeUtil.getTimeByMillis(function.getLastUpdatedTime()), 
								endTime);
						if(Integer.valueOf(mouths) > 12){
							mouths = "0";
							endTime = "长期有效";
						}else{
							endTime = mouths + "个月";
						}
						model.addAttribute("mouths", mouths);
						model.addAttribute("showMouths", endTime);
					}
				}
				
				
				
			//返回对象给页面	
			request.setAttribute("positionInfo", function);
			
			return "/ent/position/editPage";  
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	/**
	 * 职位维护
	 * @param request
	 * @param function
	 * @return
	 */
	@RequestMapping(value="/edit")  
	public void edit(HttpServletRequest request,HttpServletResponse response, PositionInfo function) {  
		try {
			String cookie_companyId = CookieUtil.getCookieInfoByKey(request, Constant.USER_ORG_ID);
			String cookie_uId = CookieUtil.getCookieInfoByKey(request, Constant.USER_UID);
			
			String endTime = function.getEndTime();
			if(StringUtil.isNotEmpty(endTime)){
				if(endTime.equals(Constant.VALUE_ZERO)){
					endTime = TimeUtil.currentTimeMillisByTime("2050-12-30 00:00:00")+"";
				}else{
					endTime = TimeUtil.getLaterMouthDate(
							Integer.valueOf(endTime), 
							TimeUtil.getDate());
					endTime = TimeUtil.currentTimeMillisByTime(endTime + " 00:00:00")+"";
					
				}
				function.setEndTime(endTime);
			}
			String posistionId = function.getId();
			if(StringUtil.isNotEmpty(posistionId)){
				function.setLastUpdatedTime(TimeUtil.currentTimeMillis());
				postionservice.updateByPrimaryKeySelective(function);
				
				//全文检索 新建索引
				LuceneUtil.luceneOpt(posistionId, Constant.VALUE_ONE, Constant.VALUE_TWO);
				
			}else{
				
				function.setId(UuidUtil.getUUID());
				function.setCompanyId(cookie_companyId);
				//function.setCreateTime(TimeUtil.currentTimeMillis());
				function.setLastUpdatedTime(TimeUtil.currentTimeMillis());
				postionservice.insertSelective(function);
				
				//全文检索 新建索引
				LuceneUtil.luceneOpt(posistionId, Constant.VALUE_ONE, Constant.VALUE_ZERO);
			}
			
			//删除消息流
			feedService.delFeedByResourceId(posistionId);
			//创建消息流
			Feed feed = new Feed();
			feed.setId(UuidUtil.getUUID());
			feed.setResourceActType(Integer.valueOf(FeedStatus.FEED_ENT_POSITION));
			feed.setUid(cookie_uId);
			feed.setFeedType(Integer.valueOf(Constant.VALUE_ZERO));
			feed.setCreateUid(cookie_companyId);
			feed.setResourceTable(FeedStatus.T_FEED_POSITION);
			feed.setResourceId(posistionId);
			feed.setCreateTime(TimeUtil.currentTimeMillis());
			feedService.insertSelective(feed);
			
			String url = request.getContextPath();
			response.sendRedirect(url + "/position");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 职位删除判断
	 * @param request
	 * @param oper
	 * @param ids
	 * @return
	 */
	@RequestMapping(value="/delCheck")
	@ResponseBody
	public Json delCheck (HttpServletRequest request, String oper, String ids){
		try {
			Json json = new Json();
			Map<String, Object> map = new HashMap<String, Object>();
			String[] idA = ids.split(",");
			for(String id:idA) {
				StringBuffer sql1 = new StringBuffer();
				StringBuffer sql2 = new StringBuffer();
				sql1.append("select * from position_delivery where position_id = '"+id+"'");
				sql2.append("select * from exam_paper_position where position_id = '"+id+"'");
				map.put("sql",sql1.toString());
				List<Map<String,Object>> list1 = baseCodeService.getBaseList(map);
				map.put("sql",sql2.toString());
				List<Map<String,Object>> list2 = baseCodeService.getBaseList(map);
				if(list1.size() > 0 || list2.size() > 0){
					json.setSuccess(true);
					break;
				}
			}
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 删除职位
	 * @param request
	 * @param oper
	 * @param ids
	 * @return
	 */
	@RequestMapping(value="/del")
	@ResponseBody
	public Json del (HttpServletRequest request, String oper, String ids){
		try {
			Json json = new Json();
			PropUtil prop = new PropUtil(Constant.PRO_FILE_CONSTANTS);
			HashMap<String, Object> map = new HashMap<String, Object>();
			String[] idA = ids.split(",");
			for(String id:idA) {
				map.clear();
				map.put("status",Constant.VALUE_THREE);
				map.put("id", id);
				int status = postionservice.updateStatus(map);
				if(status == 1){
					json.setSuccess(true);
					//全文检索 新建索引
					LuceneUtil.luceneOpt(id, Constant.VALUE_ONE, Constant.VALUE_ONE);
					feedService.updateFeedByResourceId(id);
				}else{
					json.setSuccess(false);
				}
			}
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void getDetail(HttpServletRequest request, String postionId, Model model, String companyId){
		try {
			//String cookie_companyId = CookieUtil.getCookieInfoByKey(request, Constant.USER_ORG_ID);
			
			model.addAttribute("positionId", postionId);
			
			PositionInfo function =	postionservice.selectByPrimaryKey(postionId);
			
			//顶部时间
			Long timeUpdate = function.getLastUpdatedTime();
			Long time = null;
			if(timeUpdate == null){
				time = function.getCreateTime();
			}else{
				time = timeUpdate;
			}
			String times = TimeUtil.getTimeByMillis(time);
			String mouth = times.substring(5, 7);
			mouth = Integer.valueOf(mouth) >= 10 ? mouth : mouth.substring(1, 2);
			times = times.substring(0, 4) + "年" + mouth + "月" + times.substring(8, 10) + "日";
			//function.setCreateTime(time);
			model.addAttribute("createTime", times);
			//工作地区
			String workArea = function.getLocationCode();
			String parentCode = "";
			if(StringUtil.isNotEmpty(workArea)){
				workArea = baseCodeService.getAreaAllNameByCode(workArea);
				function.setLocationCode(workArea);
			}
			//获取行业
			String industryType = function.getIndustryCode();
			if(StringUtil.isNotEmpty(industryType)){
				industryType = baseCodeService.getNameByCode(ConstantTables.COMM_INDUSTRY, 
						ConstantTables.INDUSTRY_COL_CODE, industryType, ConstantTables.INDUSTRY_COL_NAME);
				function.setIndustryCode(industryType);
			}
			//获取教育
			String edu = function.getEducationCode();
			if(StringUtil.isNotEmpty(edu)){
				edu = baseCodeService.getNameByCode(ConstantTables.COMM_EDU, 
						ConstantTables.EDU_COL_CODE, edu, ConstantTables.EDU_COL_NAME);
				function.setEducationCode(edu);
			}
			//职能
			String jobType = function.getJobTypeCode();
			if(StringUtil.isNotEmpty(jobType)){
				jobType = baseCodeService.getNameByCode(ConstantTables.COMM_JOB_TYPE, 
						ConstantTables.JOB_COL_CODE, jobType, ConstantTables.JOB_COL_NAME);
				function.setJobTypeCode(jobType);
			}
			
			//工作经验
			String workExp = function.getWorkExperienceCode();
			if(StringUtil.isNotEmpty(workExp)){
				workExp = baseCodeService.getNameByCode(ConstantTables.COMM_WORK_EXPE, 
						ConstantTables.EXP_COL_CODE, workExp, ConstantTables.EXP_COL_NAME);
				function.setWorkExperienceCode(workExp);
			}else{
				function.setWorkExperienceCode("");
			}
			
			//薪资
			int salaryType = function.getSalaryType();
			String salaryTypeVal = "面议";
			int salaryStart = 0;
			int salaryEnd = 0;
			
			if(function.getSalaryEnd() != null){
				salaryEnd = function.getSalaryEnd();
			}
			
			if(function.getSalaryStart() != null){
				salaryStart = function.getSalaryStart();
			}
			
			if(salaryType == Constant.OPER_ZERO){
				salaryTypeVal = "面议";
			}else if(salaryType == Constant.OPER_ONE){
				//if(StringUtil.isNotEmpty(salaryStart) && StringUtil.isNotEmpty(salaryEnd)){
					salaryTypeVal = "月薪    " + salaryStart + " - " + salaryEnd + " 元";
				//}else {
					//salaryTypeVal = "月薪";
				//}
			}else if(salaryType == Constant.OPER_TWO){
				//if(StringUtil.isNotEmpty(salaryStart) && StringUtil.isNotEmpty(salaryEnd)){
					salaryTypeVal = "年薪    " + salaryStart + " - " + salaryEnd + " 元";
				//}else {
				//	salaryTypeVal = "年薪";
				//}
			}
			model.addAttribute("salaryTypeVal", salaryTypeVal);
			
			//性别
			String sex = function.getSexCode();
			if(StringUtil.isNotEmpty(sex)){
				if(sex.equals(Constant.VALUE_ZERO)){
					sex = "不限";
				}else if(sex.equals(Constant.VALUE_ONE)){
					sex = "女";
				}else if(sex.equals(Constant.VALUE_TWO)){
					sex = "男";
				}else{
					sex = "不限";
				}
				model.addAttribute("sexVal", sex);
				function.setSexCode(sex);
			}
			
			//有效期
			String endTime = function.getEndTime();
			if(StringUtil.isNotEmpty(endTime)){
				Long lastUpdateTime = function.getLastUpdatedTime();
				if(lastUpdateTime == null){
					lastUpdateTime = function.getCreateTime();
				}
				endTime = TimeUtil.getTimeByMillis(Long.valueOf(endTime))
						.substring(0, 10);
				String mouths = TimeUtil.getMouthsBetweenMouth(
						TimeUtil.getTimeByMillis(lastUpdateTime), 
						endTime);
				if(Integer.valueOf(mouths) > 12){
					endTime = "长期有效";
				}else{
					endTime = mouths + "个月";
				}
			}else{
				endTime = "长期有效";
			}
			function.setEndTime(endTime);
			
			//年薪福利
			String salaryYear = function.getSalaryYear();
			if(StringUtil.isNotEmpty(salaryYear)){
				if(salaryYear.equals(Constant.VALUE_ZERO)){
					salaryYear = "无";
				}else{
					if(salaryYear.equals("24")){
						salaryYear = salaryYear + "个月以上";
					}else{
						salaryYear = salaryYear + "个月";
					}
					
				}
			}else{
				salaryYear = "";
			}
			model.addAttribute("salaryYearVal", salaryYear);
			function.setSalaryYear(salaryYear);
			
			//专业要求
			String majroRequest = function.getMajorRequest();
			if(StringUtil.isNotEmpty(majroRequest)){
				String ids[] = majroRequest.split(",");
				String name = "";
				for (int i = 0; i < ids.length; i++) {
					name = name + " " +baseCodeService.getNameByCode(ConstantTables.COMM_MAJOR, 
						ConstantTables.MAJOR_COL_CODE, ids[i], ConstantTables.MAJOR_COL_NAME);
				}
				function.setMajorRequest(name);
			}
			
			//下属人数
			Object o = function.getDepartmentNumbers();
			String departmentNumbers = "";
			if(o != null){
				departmentNumbers = String.valueOf(o);
			}
			model.addAttribute("departmentNumbers", departmentNumbers);
			if(StringUtil.isNotEmpty(companyId)){
				companyId = companyId;
			}
			//企业性质
			CompanyInfo info = comInfoService.selectByPrimaryKey(companyId);
			String companyType = info.getCompanyTypeCode();
			if(StringUtil.isEmpty(companyType)){
				companyType = "无";
			}else{
				companyType = baseCodeService.getNameByCode(ConstantTables.COMM_ENT_TYPE, 
						ConstantTables.ENT_TYPE_COL_CODE, companyType, ConstantTables.ENT_TYPE_COL_NAME);
			}
			
			//企业规模
			Object o2 = info.getPeopleNumber();
			String peopleNumber = "";
			if(o2 != null){
				peopleNumber = String.valueOf(o2);
			}
			
			//公司所在地区
			String companyArea = "";
			if(StringUtil.isNotEmpty(info.getOfficeArea())){
				companyArea =  baseCodeService.getAreaAllNameByCode(info.getOfficeArea());
			}
			
			//公司所属行业
			String Industry = info.getIndustryCode();
			if(StringUtil.isNotEmpty(Industry)){
				Industry = baseCodeService.getNameByCode(ConstantTables.COMM_INDUSTRY, 
						ConstantTables.INDUSTRY_COL_CODE, Industry, ConstantTables.INDUSTRY_COL_NAME);
			}
			
			model.addAttribute("peopleNumbers", peopleNumber);
			String logo = info.getLogo();
			if(StringUtil.isEmpty(logo)){
				logo = "";
				info.setLogo(logo);
			}
			model.addAttribute("logo", logo);
			request.setAttribute("test", "testsss");
			
			model.addAttribute("basic", info);
			model.addAttribute("companyType", companyType);
			model.addAttribute("Industry", Industry);
			model.addAttribute("companyArea", companyArea);
			
			model.addAttribute("positionInfo", function);
			
			//分享的链接
			model.addAttribute("shareUrl", "/position/id/"+postionId+"/companyId/"+companyId);
			model.addAttribute("title", function.getPositionName() + " - " + "开频校招");
			
			
			this.setSysAttr(model, function.getPositionName() + " - " + info.getEntName(), null, null);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 查询该公司所有发布的职位
	 * 
	 * @return
	 */
	@RequestMapping(value="/datagrid")  
	@ResponseBody  
	public DataGridJson datagrid(Page page, HttpServletRequest request,String status,
							String educationCode, String workExperienceCode, 
							String cityVal1, String cityVal2) { 
		try {
			String cookie_companyId = CookieUtil.getCookieInfoByKey(request, Constant.USER_ORG_ID);
			DataGridJson json = new DataGridJson();
			HashMap<String, Object> map = new HashMap<String, Object>();
			if(StringUtil.isEmpty(status)){
				status = Constant.VALUE_ZERO;
			}
			map.put("status", status);
			map.put("today", TimeUtil.currentTimeMillis());
			//页面条件筛选条件
			if(StringUtil.isNotEmpty(educationCode)){
				if(educationCode.equals(Constant.VALUE_NAGETIVE)){
					educationCode = "";
				}
			}
			if(StringUtil.isNotEmpty(workExperienceCode)){
				if(workExperienceCode.equals(Constant.VALUE_NAGETIVE)){
					workExperienceCode = "";
				}
			}
			if(StringUtil.isNotEmpty(cityVal1)){
				if(cityVal1.equals("489")){
					cityVal1 = "";
				}
			}
			if(StringUtil.isNotEmpty(cityVal2)){
				if(cityVal2.equals("489")){
					cityVal2 = "";
				}
			}
			map.put("education", educationCode);
			map.put("workExperience", workExperienceCode);
			map.put("cityVal1", cityVal1);
			map.put("cityVal2", cityVal2);
			
			map.put("companyId", cookie_companyId);
			map.put("page_start", (page.getPage()-1) * page.getRows());
			map.put("page_size", page.getRows());
			
			List<PositionInfo> list = postionservice.selectAll(map);
			json.setRows(list);
			json.setTotal(Long.valueOf(postionservice.selectAllTotal(map)));
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	/**
	 * 首页职位列表
	 * 
	 * @return
	 */
	@RequestMapping(value="/datagridIndex")  
	@ResponseBody  
	public DataGridJson datagridIndex(Page page, HttpServletRequest request,String status,
			PositionInfo postion, User user) { 
		try {
			String companyId = super.getOrgId(request, user);
			DataGridJson json = new DataGridJson();
			HashMap<String, Object> map = new HashMap<String, Object>();
			if(StringUtil.isEmpty(status)){
				status = Constant.VALUE_ZERO;
			}
			map.put("status", status);
			map.put("today", TimeUtil.currentTimeMillis());
			
			map.put("companyId", companyId);
			map.put("page_start", (page.getPage()-1) * page.getRows());
			map.put("page_size", page.getRows());
			
			List<Map<String,Object>> list = postionservice.datagridIndex(map);
			List<Map<String,Object>> listVal = new ArrayList<Map<String,Object>>();
			if(list.size() > 0){
				for (int i = 0; i < list.size(); i++) {
					Map<String,Object> mapVal = new HashMap<String,Object>();
					mapVal.put("id", list.get(i).get("id"));
					mapVal.put("position_name", list.get(i).get("position_name"));
					mapVal.put("create_time", list.get(i).get("last_updated_time"));
					Object o = list.get(i).get("location_code");
					String workArea = "";
					if(o != null){
						workArea = baseCodeService.getNameByCode(ConstantTables.COMM_LOCATION, 
								ConstantTables.AREA_COL_CODE, String.valueOf(o), ConstantTables.AREA_COL_NAME);
					}
					Object oJobType = list.get(i).get("job_type_code");
					String jobType = "";
					if(oJobType != null){
						jobType = baseCodeService.getNameByCode(ConstantTables.COMM_JOB_TYPE, 
								ConstantTables.JOB_COL_CODE, String.valueOf(oJobType), ConstantTables.JOB_COL_NAME);
					}
					mapVal.put("jobType", jobType);
					mapVal.put("docname", workArea);
					listVal.add(mapVal);
				}
			}
			json.setRows(listVal);
			json.setTotal(Long.valueOf(postionservice.selectAllTotal(map)));
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	
	/**
	 * 根据职位查询该职位下的所有简历
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/datagridInit")  
	public String datagridInit(HttpServletRequest request) {  
		
		try {
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/ent/resume/count/no_read";
	}
	
	/**
	 * 职位 详情
	 * @param request
	 * @param function
	 * @return
	 */
	@RequestMapping(value="/detail")  
	public String detail(HttpServletRequest request, String postionId, String positionId, Model model, User user) {  
		try {
			String cookie_companyId = this.getOrgId(request, user);
			if(StringUtil.isNotEmpty(postionId) || StringUtil.isNotEmpty(positionId)){
				if(StringUtil.isEmpty(postionId) && StringUtil.isNotEmpty(positionId)){
					postionId = positionId;
				}
				getDetail(request, postionId, model, cookie_companyId);
			}
			model.addAttribute("companyId", cookie_companyId);
			return "/ent/position/detail";  
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	/**
	 * 查询对应的码表list
	 * @param tableName
	 * @param params
	 * @return
	 */
	public List<Map<String,Object>> getCommList(String tableName, String params){
		try {
			List<Map<String,Object>> list = null;
			Map<String,Object> map = new HashMap<String,Object>();
			if(StringUtil.isEmpty(params)){
				map.put("parameters", "1=1");
			}else if(params.equals(Constant.VALUE_ONE)){
				map.put("parameters", " parent_code = '' or  parent_code is NULL ");
			}else{
				map.put("parameters", params);
			}
			map.put("tableName", tableName);
		    list = baseCodeService.getCodeList(map);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public String getWorkArea(String workArea){
		String parentCode = "";
		if(workArea.equals("489")){
			workArea = "全国";
		}else{
			parentCode = baseCodeService.getNameByCode(ConstantTables.COMM_LOCATION, 
					ConstantTables.AREA_COL_CODE, workArea, ConstantTables.COL_PARENT_CODE);
			if(!parentCode.equals("489")){
				String workArea1 = baseCodeService.getNameByCode(ConstantTables.COMM_LOCATION, 
						ConstantTables.AREA_COL_CODE, parentCode, ConstantTables.AREA_COL_NAME);
				String workArea2 = baseCodeService.getNameByCode(ConstantTables.COMM_LOCATION, 
						ConstantTables.AREA_COL_CODE, workArea, ConstantTables.AREA_COL_NAME);
				workArea = workArea1 + " " + workArea2;
			}else{
				workArea = baseCodeService.getNameByCode(ConstantTables.COMM_LOCATION, 
						ConstantTables.AREA_COL_CODE, workArea, ConstantTables.AREA_COL_NAME);
			}
		}
		return workArea;
	}
	
	/**
	 * 获取专业列表
	 * @param request
	 * @param type  0大类	 1小类
	 * @param code
	 * @return
	 */
	@RequestMapping(value="/getMajorClass")
	@ResponseBody
	public Json getMajorClass (HttpServletRequest request, String type, String code){
		Json json = new Json();
		try {
			List<Map<String,Object>> list = null;
			Map<String,Object> map = new HashMap<String,Object>();
			if(type.equals(Constant.VALUE_ONE)){
				map.put("parameters", " parent_code = '' or  parent_code is NULL ");
			}else if(type.equals(Constant.VALUE_TWO)){
				map.put("parameters", " parent_code = '"+code+"'");
			}
			
			map.put("tableName", ConstantTables.COMM_MAJOR);
			list = baseCodeService.getCodeList(map);
			if(list.size() > 0){
				if(type.equals(Constant.VALUE_TWO) && !code.equals("18007200")){
					list.remove(list.size() -1);
				}
				json.setObj(list);
				json.setSuccess(true);
			}
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 创建索引
	 * @param url
	 * @param function
	 */
	public void createIndex(String url, PositionInfo function){
		try {
			
			Map<String, Object> mapLunece = new HashMap<String, Object>();
			String luneceTaskId = UuidUtil.getUUID();
			mapLunece.put("id", luneceTaskId);
			mapLunece.put("obj_id", function.getId());//资源id
			mapLunece.put("obj_type", 1);//对象类型(0-公司,1-职位,2-视频,3-学生,4-学校
			mapLunece.put("opt", 0);//操作类型(0-add,1-delete,2-update
			mapLunece.put("create_time", TimeUtil.currentTimeMillis()+"");//
			mapLunece.put("status", 0);//处理状态（0-未处理,1-已处理
			mapLunece.put("handle_time", TimeUtil.currentTimeMillis()+"");//处理时间
			
			PropUtil prop = new PropUtil(Constant.PRO_FILE_CONSTANTS);
			
			String result = HttpPostUtil.doPost(prop.getValue(AppSearchConstant.APP_SEARCH_URL)
					+ AppSearchConstant.SEARCH_TASK_URL, mapLunece);
			
			String code = JsonUtil.jsonToObj(result, StautsBean.class).getCode();
			
			if(StringUtil.isNotEmpty(code) && code.equals(Constant.VALUE_ZERO)){
				Map<String,Object> map = new HashMap<String,Object>();
				map.put(AppSearchConstant.APP_SEARCH_ID, function.getId());
				map.put(AppSearchConstant.POSITION_NAME, function.getPositionName());
				map.put(AppSearchConstant.LOCATION_CODE, function.getLocationCode());
				map.put(AppSearchConstant.INDUSTRY_CODE, function.getIndustryCode());
				map.put(AppSearchConstant.LAST_UPDATED_TIME, function.getLastUpdatedTime());
				
				String s = HttpPostUtil.doPost(prop.getValue(AppSearchConstant.APP_SEARCH_URL)
						+ url + luneceTaskId, map);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 创建消息流
	 * @param feed
	 */
	public void createFeed(Feed feed){
		try {
			feedService.insertSelective(feed);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
