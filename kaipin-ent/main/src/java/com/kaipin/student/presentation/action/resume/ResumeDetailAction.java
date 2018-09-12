package com.kaipin.student.presentation.action.resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kaipin.common.constants.Constant;
import com.kaipin.common.constants.ConstantTables;
import com.kaipin.common.constants.Resume;
import com.kaipin.common.entity.Json;
import com.kaipin.common.entity.User;
import com.kaipin.common.presentation.action.BaseAction;
import com.kaipin.common.service.common.IBaseCodeService;
import com.kaipin.common.util.PropUtil;
import com.kaipin.common.util.StringUtil;
import com.kaipin.common.util.TimeUtil;
import com.kaipin.enterprise.model.position.PositionDelivery;
import com.kaipin.enterprise.model.position.PositionInfo;
import com.kaipin.enterprise.service.position.IPositionService;
import com.kaipin.student.model.resume.ResumeInfo;
import com.kaipin.student.model.user.StuUser;
import com.kaipin.student.service.resume.IResumeInfoService;
import com.kaipin.student.service.resume.IResumeService;
import com.kaipin.student.service.user.IStuUserService;
import com.kaipin.enterprise.service.position.IPDeliveryService;
import com.kaipin.university.model.user.UserLocalauth;
import com.kaipin.university.service.user.IUserLocalauthService;

/**
 * 学生简历
 * @author Mr-H
 *
 */
@RequestMapping("/resume")
@Controller
public class ResumeDetailAction extends BaseAction{

	
	@Autowired
	private IUserLocalauthService localUserService;
	@Autowired
	private IBaseCodeService baseCodeService;
	@Autowired
	private IPositionService positionService;
	@Autowired
	private IPDeliveryService pDeliveryService;
	@Autowired
	private IResumeInfoService resumeInfoService;
	@Autowired
	private IResumeService iResumeService;//简历
	@Autowired
	private IStuUserService stuUserService;
	
	
	/**
	 * 其他平台查看简历详情
	 * @return
	 */
	@RequestMapping("/show")
	public String show(HttpServletRequest request, Model model, 
			User user){
		try {
			String orgId = super.getOrgId(request, user);
			ResumeInfo resume = resumeInfoService.selectByUid(orgId);
			if(resume != null){
				getDetail(request, model, user, resume.getId());
			}else{
				this.setSysAttr(model, "个人简历", null, null);
			}
			return "/stu/resume/show";
		} catch (Exception e) {
			e.printStackTrace();
			return ERRO;
		}
	}
	
	/**
	 * 查看学生简历详情
	 * @return
	 */
	@RequestMapping("/detail")
	public String detail(HttpServletRequest request,Model model,
			String resumeId,String relationId,String postionId,String status,String userId, String oper, 
			User user){
		try {
			PositionDelivery delivery = pDeliveryService.selectByPrimaryKey(relationId);
			ResumeInfo resume = null;
			
			if(delivery != null){
				
				resume = resumeInfoService.selectByUid(delivery.getStuUserId());
				
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
			
			
			
			if(resume != null){
				getDetail(request, model, user, resume.getId());
			}else{
				this.setSysAttr(model, "个人简历", null, null);
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
				List<Map<String,Object>> stuList = baseCodeService.getBaseList(stuMap);
				
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
				PositionInfo positionInfo = positionService.selectByPrimaryKey(delivery.getPositionId());
				request.setAttribute("positionName", positionInfo.getPositionName());
//				request.getSession().setAttribute(Constant.USER, user);
			}
			
			return "/stu/resume/resume_detail";
		} catch (Exception e) {
			e.printStackTrace();
			return ERRO;
		}
	}
	
	/**
	 * 分享查看
	 * @return
	 */
	@RequestMapping("/share/{id}")
	public String share(HttpServletRequest request, Model model, 
			User user, @PathVariable String id){
		try {
			String orgId = id;
			ResumeInfo resume = resumeInfoService.selectByUid(orgId);
			if(resume != null){
				getDetail(request, model, user, resume.getId());
			}
			model.addAttribute("userId", id);
			return "/stu/resume/detail_share";
		} catch (Exception e) {
			e.printStackTrace();
			return ERRO;
		}
	}
	
	@SuppressWarnings("unused")
	public void getDetail(HttpServletRequest request, Model model, 
			User user, String resumeId){
		
		String uId = super.getUid(request, user);
		
		ResumeInfo resume = resumeInfoService.selectByPrimaryKey(resumeId);//获取简历对象
		StuUser  stuUser = stuUserService.selectByPrimaryKey(resume.getStuUserId());
		//地区
		String area = stuUser.getLocationCode();
		if(StringUtil.isNotEmpty(area)){
			area = baseCodeService.getAreaAllNameByCode(area);
			resume.setLocationCode(area);
		}
		model.addAttribute("area", area);
		
		//专业名称
		String major = stuUser.getMajorCode();
		if(StringUtil.isNotEmpty(major)){
			major = baseCodeService.getNameByCode(ConstantTables.COMM_MAJOR, 
					ConstantTables.MAJOR_COL_CODE, major, ConstantTables.MAJOR_COL_NAME);
		}
		model.addAttribute("majorName", major);
		//学校名称
		String schoolNames = stuUser.getSchoolCode();
		if(StringUtil.isNotEmpty(schoolNames)){
			schoolNames = baseCodeService.getNameByCode(ConstantTables.COMM_SCHOOL, 
					ConstantTables.SCHOOL_COL_CODE, schoolNames,
					ConstantTables.SCHOOL_COL_NAME);
		}
		model.addAttribute("schoolNames", schoolNames);
		
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
				schoolName = baseCodeService.getNameByCode(
						ConstantTables.COMM_SCHOOL, ConstantTables.SCHOOL_COL_CODE, school+"", 
						ConstantTables.SCHOOL_COL_NAME);
			}
			String zhuanyeName = "";
			if(zhuanye != null){
				zhuanyeName = baseCodeService.getNameByCode(
						ConstantTables.COMM_MAJOR, ConstantTables.MAJOR_COL_CODE, zhuanye+"", 
						ConstantTables.MAJOR_COL_NAME);
			}
			String xueliName = "";
			if(xueli != null){
				xueliName = baseCodeService.getNameByCode(
						ConstantTables.COMM_EDU, ConstantTables.EDU_COL_CODE, xueli+"", 
						ConstantTables.EDU_COL_NAME);
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
		
		List<Map<String,Object>> languagelist = 
				getCommList(Resume.RES_LANGIAGE,"comm_language", 
						"language_name", "language_code", resumeId);
		if(languagelist.size() > 0){
			request.setAttribute("languagelist", languagelist);
		}
		
		
		//感兴趣的工作地方
		List<Map<String,Object>> likeWorkAreaList = 
				getCommList(Resume.RES_LIke_JOB_AREA,"comm_location",
						"location_name","location_code", resumeId);
		if(likeWorkAreaList.size() > 0){
			request.setAttribute("likeWorkAreaList", likeWorkAreaList);
		}
		
		//感兴趣的区域
//		String interestZone = resume.getInterestZone();
//		if(StringUtil.isNotEmpty(interestZone)){
//			List<String> interestZonelist = getList(interestZone);
//			request.setAttribute("interestZonelist", interestZonelist);
//		}
		
		//喜欢的工作类型1
//		List<Map<String,Object>> likeWorkTypeList = getCommList(Resume.RES_LIke_JOB_TYPE,resumeId);
//		if(likeWorkTypeList.size() > 0){
//			request.setAttribute("likeWorkTypeaList", likeWorkTypeList);
//		}
		
		//感兴趣的工作领域
		List<Map<String,Object>> likeWorkLingYu = 
				getCommList(Resume.RES_LIKE_LINGYU,"comm_industry",
						"industry_name","industry_code", resumeId);
		if(likeWorkLingYu.size() > 0){
			request.setAttribute("likeWorkLingYu", likeWorkLingYu);
		}
		
		//聘用类型
		List<Map<String,Object>> hireTypeList = getCommList(Resume.RES_HIRE_TYPE,resumeId);
		if(hireTypeList.size() > 0){
			String colVal = String.valueOf(hireTypeList.get(0).get("employment_type_code"));
			String name = baseCodeService.getNameByCode(
					Resume.COMM_HIRE_TYPE, Resume.RES_HIRE_TYPE_COL, 
					colVal,Resume.COMM_HIRE_TYPE_COL);
			
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
		if(StringUtil.isNotEmpty(uId)){
			stuMap.put("sql", "select head_url from stu_user where id = '"+ user.getOrg_id() +"'");
			List<Map<String,Object>> stuList = baseCodeService.getBaseList(stuMap);
			
			if(stuList.size() > 0){
				if(stuList.get(0) != null) {
					Object headUrl = stuList.get(0).get("head_url");
					PropUtil pro = new PropUtil();
					String path = pro.getValue(Constant.STU_HEAD_URL);
					if(headUrl != null){
						userHeadUrl = String.valueOf(headUrl);
						if(!userHeadUrl.contains("http://") && StringUtil.isNotEmpty(userHeadUrl)){
							userHeadUrl = path + userHeadUrl;
						}
					}
				}
				
			}
		}
		model.addAttribute("userHeadUrl", StringUtil.getLogo(userHeadUrl));
		//简历对象
		request.setAttribute(Constant.RESUME_INFO, resume);
		request.setAttribute("userId", user.getOrg_id());
		
		String title = "";
		if(resume == null){
			title = "个人简历";
		}else{
			String name = StringUtil.getResumeName(resume.getSurname(), resume.getMissSurname());
			if(StringUtil.isEmpty(name)){
				title = "个人简历";
			}
			title = name;
		}
		
		this.setSysAttr(model, title, null, null);
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
		List<Map<String,Object>> list = baseCodeService.getBaseList(map);
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
		List<Map<String,Object>> list = baseCodeService.getBaseList(map);
		return list;
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
			StringBuffer sql = new StringBuffer();
			sql.append(" select * from resume_info where 1=1 and stu_user_id = '" + uId + "'");
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("sql", sql.toString());
			List<Map<String,Object>> list = baseCodeService.getBaseList(map);
			if(list.size() > 0){
				json.setObj(list.get(0).get("id"));
				json.setSuccess(true);
			}
			return json;
		} catch (Exception e) {
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
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 获取教育对应名称
	 * @param sql
	 * @return
	 */
	public String getEduName(String sql){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("sql", sql);
		List<Map<String,Object>> list = baseCodeService.getBaseList(map);
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
		List<Map<String,Object>> list = baseCodeService.getBaseList(map);
		String name = "";
		if(list.size() > 0){
			name = String.valueOf(list.get(0).get("name"));
		}
		return name;
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
	
}
