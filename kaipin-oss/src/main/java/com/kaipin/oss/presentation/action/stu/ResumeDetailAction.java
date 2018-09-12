package com.kaipin.oss.presentation.action.stu;

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

import com.kaipin.oss.constant.Constant;
import com.kaipin.oss.constant.ConstantTables;
import com.kaipin.oss.constant.Resume;
import com.kaipin.oss.model.stu.StuUser;
import com.kaipin.oss.model.stu.resume.ResumeInfo;
import com.kaipin.oss.service.common.IBaseCodeService;
import com.kaipin.oss.service.stu.IResumeInfoService;
import com.kaipin.oss.service.stu.IStuUserService;
import com.kaipin.oss.service.stu.IUserLocalauthService;
import com.kaipin.oss.util.PropUtil;
import com.kaipin.oss.util.StringUtil;


/**
 * 学生简历
 * @author Mr-H
 *
 */
@RequestMapping("/stu/resume")
@Controller
public class ResumeDetailAction{

	
	@Autowired
	private IUserLocalauthService localUserService;
	@Autowired
	private IBaseCodeService baseCodeService;
	@Autowired
	private IResumeInfoService resumeInfoService;
	@Autowired
	private IStuUserService stuUserService;
	
	/**
	 * 其他平台查看简历详情
	 * @return
	 */
	@RequestMapping("/detail")
	public String detail(HttpServletRequest request, Model model, 
			String userId){
		try {
			ResumeInfo resume = resumeInfoService.selectByUid(userId);
			if(resume != null){
				getDetail(request, model, userId, resume.getId());
			}
			return "/stu/resume/detail";
		} catch (Exception e) {
			e.printStackTrace();
			return "/stu/resume/detail";
		}
	}
	
	public void getDetail(HttpServletRequest request, Model model, String userId, String resumeId){
		
		String uId = userId;
		
		ResumeInfo resume = resumeInfoService.selectByPrimaryKey(resumeId);//获取简历对象
		StuUser  stuUser = stuUserService.selectByPrimaryKey(resume.getStuUserId());
		//地区
		String area = resume.getLocationCode();
		if(StringUtil.isNotEmpty(area)){
			String name = baseCodeService.getAreaAllNameByCode(area);
			resume.setLocationCode(name);
		}
		
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
					Resume.COMM_HIRE_TYPE, Resume.COMM_HIRE_TYPE_COL, 
					Resume.RES_HIRE_TYPE_COL, colVal);
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
			stuMap.put("sql", "select head_url from stu_user where id = '"+ uId +"'");
			List<Map<String,Object>> stuList = baseCodeService.getBaseList(stuMap);
			
			if(stuList.size() > 0){
				if(stuList.get(0) != null) {
					Object headUrl = stuList.get(0).get("head_url");
					String path = Constant.HTTP_STU_HEAD_URL;
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
}
