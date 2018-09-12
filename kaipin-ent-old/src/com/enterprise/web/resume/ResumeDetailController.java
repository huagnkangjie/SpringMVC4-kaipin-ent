package com.enterprise.web.resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jws.WebParam.Mode;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.Constant;
import com.common.constants.Resume;
import com.common.pojo.Json;
import com.enterprise.model.PositionInfo;
import com.enterprise.model.UserEducationBackground;
import com.enterprise.model.UserInterview;
import com.enterprise.model.UserProfessionalBackground;
import com.enterprise.model.UserResume;
import com.enterprise.model.UserResumeRelation;
import com.enterprise.model.common.EntUser;
import com.enterprise.model.common.UserPersonal;
import com.enterprise.model.position.PositionDelivery;
import com.enterprise.model.resume.ResumeInfo;
import com.enterprise.service.common.IBaseCodeService;
import com.enterprise.service.common.IBaseService;
import com.enterprise.service.common.IUserPersonalService;
import com.enterprise.service.position.IPDeliveryInterviewService;
import com.enterprise.service.position.IPDeliveryService;
import com.enterprise.service.position.IPInterviewService;
import com.enterprise.service.position.IPostionService;
import com.enterprise.service.resume.IResumeInfoService;
import com.enterprise.service.resume.IResumeService;
import com.enterprise.service.resume.IUserEducationBackgroundService;
import com.enterprise.service.resume.IUserInterviewService;
import com.enterprise.service.resume.IUserProfessionalBackgroundService;
import com.enterprise.service.resume.IUserResumeRelationService;
import com.util.CookieUtil;
import com.util.LogUtil;
import com.util.PropUtil;
import com.util.StringUtil;
import com.util.TimeUtil;
import com.util.UuidUtil;

/**
 * 简历的详情
 * @author Mr-H
 *
 */
@Controller
@RequestMapping("resumeDetail")
public class ResumeDetailController {

	Logger logger = Logger.getLogger(ResumeDetailController.class.getName());
	
	@Autowired
	private IResumeService iResumeService;//简历
	@Autowired
	private IUserEducationBackgroundService eduservice;//教育背景
	@Autowired
	private IUserProfessionalBackgroundService proservice;//教育背景
	@Autowired
	private IUserResumeRelationService relationService;//关系表
	@Autowired
	private IUserPersonalService personService;
	@Autowired
	private IPostionService positonService;//职位
	@Autowired
	private IPDeliveryService pDeliveryService;//简历投递关系表
	@Autowired
	private IPDeliveryInterviewService pdInterviewService;//面试邀请  笔试  offer 中间表
	@Autowired
	private IPInterviewService pInterviewService;//面试邀请表
	@Autowired
	private IResumeInfoService resumeInfoService;//简历信息
	@Autowired
	private IBaseCodeService baseService;//基础code
	@Autowired
	private IBaseService baseServices;//基础
	
	/**
	 * 简历详情
	 * @param request
	 * @param resumeId 简历id
	 * @param id 简历投递关系表
	 * @param times 第几次看简历 第一次，更改状态，其余查看，不需要更改状态
	 * 			             参数为1 代表第一次看
	 * @return
	 */
	@RequestMapping(value="/init")  
	public String init(HttpServletRequest request,Model model,
			String resumeId,String relationId,String postionId,String status,String userId, String oper) { 
		try {
			if(StringUtil.isNotEmpty(resumeId)){
				String cookie_companyId = CookieUtil.getCookieInfoByKey(request, Constant.USER_ORG_ID);
				
				PositionDelivery delivery = pDeliveryService.selectByPrimaryKey(relationId);
					
				if(delivery != null){
					if(delivery.getIsLook() == 0){
						//写日志
						String content = "已查看";
						Map<String,Object> map = new HashMap<String,Object>();
						map.put("position_delivery_id", relationId);
						map.put("create_time", TimeUtil.currentTimeMillis());
						map.put("content", content);
						map.put("status", Constant.VALUE_ONE);
						map.put("type", Constant.VALUE_ONE);
						map.put("object_id", "");
						int ii = iResumeService.insertViewLog(map);
						
						//修改简历已读状体
						delivery.setIsLook(Byte.valueOf(Constant.VALUE_ONE));
						pDeliveryService.updateByPrimaryKeySelective(delivery);//修改查看状态
					}	
				}
				
				ResumeInfo resume = resumeInfoService.selectByPrimaryKey(resumeId);//获取简历对象
				
				//地区
				String area = resume.getLocationCode();
				if(StringUtil.isNotEmpty(area)){
					String name = getName("comm_location", "location_name", "location_code", area);
					resume.setLocationCode(name);
				}
				
				
				
				//获取教育背景
				List<Map<String,Object>> eduList = getCommList(Resume.RES_EDU,resumeId);
				if(eduList.size() > 0){
					Object school = eduList.get(0).get("school");
					Object zhuanye = eduList.get(0).get("major");
					Object xueli = eduList.get(0).get("education_code");
					Object is_grad = eduList.get(0).get("is_grad");
					Object startTimeO = eduList.get(0).get("start_time");
					Object endTimeO = eduList.get(0).get("end_time");
					String schoolName = "";
					if(school != null){
						String sql1 = "select a.school_name name from school_info a where a.school_code = '"+school+"'";
						schoolName = getEduName(sql1);
					}
					String zhuanyeName = "";
					if(zhuanye != null){
						String sql2 = "select a.major_name name from comm_major a where a.major_code = '"+zhuanye+"'";
						zhuanyeName = getEduName(sql2);
					}
					String xueliName = "";
					if(xueli != null){
						String sql3 = "select a.education_name name from comm_education a where a.education_code = '"+xueli+"'";
						xueliName = getEduName(sql3);
					}
					//开始时间
					String startTime = "";
					if(startTimeO != null){
						startTime = String.valueOf(startTimeO);
						if(StringUtil.isNotEmpty(startTime)){
							String s[] = startTime.split("-");
							startTime = s[0] + "年" + s[1] + "月	- " ;
						}
					}
					//结束时间
					String endTime = "";
					if(endTimeO != null){
						endTime = String.valueOf(endTimeO);
						if(StringUtil.isNotEmpty(endTime)){
							String s[] = endTime.split("-");
							endTime = s[0] + "年" + s[1] + "月" ;
						}
					}
					
					model.addAttribute("time", startTime + endTime);
					model.addAttribute("schoolName", schoolName);
					model.addAttribute("zhuanyeName", zhuanyeName);
					model.addAttribute("xueliName", xueliName);
					model.addAttribute("is_grad", is_grad);
				}
				//职业背景
				
				List<Map<String,Object>> proList = getCommList(Resume.RES_PROFESS,resumeId);
				if(proList.size() > 0){
					request.setAttribute("proList", proList);
				}
				
				
				HashMap<String,Object> mapPrames = new HashMap<String, Object>();
				mapPrames.put(Resume.RESUME_ID, resumeId);
				
				//语言能力
				
				List<Map<String,Object>> languagelist = getCommList(Resume.RES_LANGIAGE,"comm_language", "language_name", "language_code", resumeId);
				if(languagelist.size() > 0){
					request.setAttribute("languagelist", languagelist);
				}
				
				
				//感兴趣的工作地方
				List<Map<String,Object>> likeWorkAreaList = getCommList(Resume.RES_LIke_JOB_AREA,"comm_location","location_name","location_code", resumeId);
				if(likeWorkAreaList.size() > 0){
					request.setAttribute("likeWorkAreaList", likeWorkAreaList);
				}
				
				//感兴趣的区域
//				String interestZone = resume.getInterestZone();
//				if(StringUtil.isNotEmpty(interestZone)){
//					List<String> interestZonelist = getList(interestZone);
//					request.setAttribute("interestZonelist", interestZonelist);
//				}
				
				//喜欢的工作类型1
//				List<Map<String,Object>> likeWorkTypeList = getCommList(Resume.RES_LIke_JOB_TYPE,resumeId);
//				if(likeWorkTypeList.size() > 0){
//					request.setAttribute("likeWorkTypeaList", likeWorkTypeList);
//				}
				
				//感兴趣的工作领域
				List<Map<String,Object>> likeWorkLingYu = getCommList(Resume.RES_LIKE_LINGYU,"comm_industry","industry_name","industry_code", resumeId);
				if(likeWorkLingYu.size() > 0){
					request.setAttribute("likeWorkLingYu", likeWorkLingYu);
				}
				
				//聘用类型
				List<Map<String,Object>> hireTypeList = getCommList(Resume.RES_HIRE_TYPE,resumeId);
				if(hireTypeList.size() > 0){
					String colVal = String.valueOf(hireTypeList.get(0).get("employment_type_code"));
					String name = getName(Resume.COMM_HIRE_TYPE, Resume.COMM_HIRE_TYPE_COL, Resume.RES_HIRE_TYPE_COL, colVal);
					request.setAttribute("hireType", name);
				}
				
				//意向工作职位
				List<Map<String,Object>> positionList = getCommList(Resume.LIKE_JOB_POSITION,resumeId);
				if(positionList.size() > 0){
					request.setAttribute("positionList", positionList);
				}
				
				//关键字
				String keyWords = resume.getKeywords();
				List<Map<String,Object>> keyWordsList = new ArrayList<Map<String,Object>>();
				if(StringUtil.isNotEmpty(keyWords)){
					String s[] = keyWords.split(",");
					for (int i = 0; i < s.length; i++) {
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("keyword", s[i]);
						keyWordsList.add(map);
					}
					request.setAttribute("keyWordsList", keyWordsList);
				}
				
				//获取用户头像基本信息
				Map<String,Object> stuMap = new HashMap<String,Object>();
				String userHeadUrl = "";
				String uId = "";
				if(delivery != null){
					uId = delivery.getStuUserId();
				}else{
					uId = userId;
				}
				if(StringUtil.isNotEmpty(uId)){
					stuMap.put("sql", "select head_url from stu_user where id = '"+ uId +"'");
					List<Map<String,Object>> stuList = baseService.getBaseList(stuMap);
					
					if(stuList.size() > 0){
						if(stuList.get(0) != null) {
							Object headUrl = stuList.get(0).get("head_url");
							PropUtil pro = new PropUtil();
							String path = pro.getValue(Constant.STU_HEAD_URL);
							if(headUrl != null){
								userHeadUrl = String.valueOf(headUrl);
								if(!userHeadUrl.contains("http://")){
									userHeadUrl = path + userHeadUrl;
								}
							}
						}
						
					}
				}
				model.addAttribute("userHeadUrl", userHeadUrl);
				//简历对象
				request.setAttribute(Constant.RESUME_INFO, resume);
				//关系id
				request.setAttribute("relationId", relationId);
				
				if(StringUtil.isEmpty(oper)){
					oper = "in";
				}
				request.setAttribute("oper", oper);
				if(delivery != null){
					//职位id
					request.setAttribute("positionId", delivery.getPositionId());
					//企业id
					request.setAttribute("companyId", delivery.getCompanyId());
					//简历id
					request.setAttribute("resumeId", delivery.getResumeId());
					//职位id
					request.setAttribute("postionId", delivery.getPositionId());
					//用户id
					request.setAttribute("userId", delivery.getStuUserId());
					//职位名称
					PositionInfo positionInfo = positonService.selectByPrimaryKey(delivery.getPositionId());
					request.setAttribute("positionName", positionInfo.getPositionName());
//					request.getSession().setAttribute(Constant.USER, user);
				}
				
			}
			return "/ent/resume/resume_detail";
		} catch (Exception e) {
			logger.info(LogUtil.getTrace(e));;
			e.printStackTrace();
			return null;
		}
		  
	}
	
	/**
	 * 获取左边的按钮的面试次数
	 * @param relationId
	 * @return
	 */
	@RequestMapping("/getFaceTimes")
	@ResponseBody
	public Json getFaceTimes(String relationId){
		Json json = new Json();
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("relationId", relationId);
			List<Map<String,Object>> list = iResumeService.getFaceTimes(map);
			json.setObj(list);
			return json;
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 参数    parms1,parms2,parms3
	 * 割据成一个一个的字符串封装成list<map>返回
	 * @param str
	 * @return
	 */
	public List<String> getList(String str){
		try {
			if(StringUtil.isNotEmpty(str)){
				String strArray[] = str.split(",");
				List<String> list = new ArrayList<String>();
				for (int i = 0; i < strArray.length; i++) {
					list.add(strArray[i]);
				}
				return list;
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	
	/**
	 * 获取list
	 * @param tableName
	 * @param resumeId
	 * @return
	 */
	public List<Map<String,Object>> getCommList(String tableName,String commTable, String commBacCol, String col, String resumeId){
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT comm."+commBacCol+" name,a.* FROM "+tableName+" a,"+commTable+" comm WHERE 1 = 1 ");
		sql.append(" AND a.resume_id = '"+resumeId+"' ");
		sql.append(" AND comm."+col+" = a."+col+" ");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("sql", sql.toString());
		List<Map<String,Object>> list = baseService.getBaseList(map);
		return list;
	}
	
	
	/**
	 * 获取list
	 * @param tableName
	 * @param resumeId
	 * @return
	 */
	public List<Map<String,Object>> getCommList(String tableName, String resumeId){
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from " + tableName + " where 1=1 and resume_id = '" + resumeId + "'");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("sql", sql.toString());
		List<Map<String,Object>> list = baseService.getBaseList(map);
		return list;
	}
	
	/**
	 * 获取字段名称
	 * @param tableName
	 * @param bacCol
	 * @param col
	 * @param colVal
	 * @return
	 */
	public String getName(String tableName, String bacCol, String col, String colVal){
		StringBuffer sql = new StringBuffer();
		sql.append(" select "+bacCol+" name from "+tableName+" where "+col+" = '"+colVal+"'");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("sql", sql.toString());
		List<Map<String,Object>> list = baseService.getBaseList(map);
		String name = "";
		if(list.size() > 0){
			name = String.valueOf(list.get(0).get("name"));
		}
		return name;
	}
	
	/**
	 * 获取教育对应名称
	 * @param sql
	 * @return
	 */
	public String getEduName(String sql){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("sql", sql);
		List<Map<String,Object>> list = baseService.getBaseList(map);
		String name = "";
		if(list.size() > 0){
			Object nameObj = list.get(0).get("name");
			if(nameObj != null){
				name = String.valueOf(nameObj);
			}
		}
		return name;
	}
	
	/**
	 * 判断该用户是否有简历
	 * @param relationId
	 * @return
	 */
	@RequestMapping("/checkResume")
	@ResponseBody
	public Json checkResume(String uId){
		Json json = new Json();
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("stu_user_id", uId);
			List<Map<String,Object>> list = baseServices.getList("resume_info", map, "");
			if(list.size() > 0){
				json.setObj(list.get(0).get("id"));
				json.setSuccess(true);
			}
			return json;
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
			return null;
		}
	}
}
