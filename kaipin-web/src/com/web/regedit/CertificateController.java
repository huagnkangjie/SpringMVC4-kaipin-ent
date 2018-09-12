package com.web.regedit;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.constants.Constant;
import com.common.constants.ConstantTables;
import com.common.pojo.Json;
import com.model.company.CompanyInfo;
import com.model.company.CompanyUserInfo;
import com.model.sch.SchoolInfoLink;
import com.model.sch.SchoolUserInfo;
import com.model.stu.StuUser;
import com.model.user.UserLocalauth;
import com.service.common.ICommonCodeService;
import com.service.company.ICompanyInfoService;
import com.service.company.ICompanyUserInfoService;
import com.service.company.IEntBaseUserService;
import com.service.sch.ISchoolBaseUserService;
import com.service.sch.ISchoolInfoLinkService;
import com.service.sch.ISchoolUserInfoService;
import com.service.stu.IStuBaseUserService;
import com.service.stu.IStuUserService;
import com.service.user.IUserLocalauthService;
import com.service.user.IUserService;
import com.util.LogUtil;
import com.util.StringUtil;
import com.util.TimeUtil;
/**
 * 资质认证
 * @author Mr-H
 *
 */
@Controller
@RequestMapping("/certificate")
public class CertificateController {
	
	Logger log = Logger.getLogger(CertificateController.class.getName());
	
	@Autowired
	private IEntBaseUserService entBaseService;
	@Autowired
	private ICompanyInfoService companyInfoService;
	@Autowired
	private ICompanyUserInfoService companyUserInfoService;
	@Autowired
	private IUserService userService;
	@Autowired
	private IUserLocalauthService localUserService;//用户登录表
	@Autowired
	private IStuUserService stuUserService;
	@Autowired
	private IStuBaseUserService stuBaseService;
	@Autowired
	private ISchoolBaseUserService schBaseService;
	@Autowired
	private ISchoolUserInfoService schUserInfoService;
	@Autowired
	private ICommonCodeService commonCodeService;
	@Autowired
	private ISchoolInfoLinkService linkService;
	
	/**
	 * 页面初始化
	 * @return
	 */
	@RequestMapping({"/init"})
	public String init(HttpServletRequest request, String oper,
			String userId, String orgId, UserLocalauth user, Model model){
		try {
			if(StringUtil.isEmpty(userId) && user != null){
				userId = user.getId();
			}
			model.addAttribute("userId", userId);//用户id
			model.addAttribute("orgId", orgId);//企业  学习  学生   基本信息id
			model.addAttribute("localUser", user);
			
			return "/regedit/regedit";
		} catch (Exception e) {
			log.info(LogUtil.getTrace(e));
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 资质认证初始化页面
	 * @return
	 */
	@RequestMapping({"/certificateIndex"})
	public String certificateIndex(HttpServletRequest request, String oper, String orgId, Model model, String userId){
		try {
			String orgName = "";
			String page = "regedit";//返回的页面
			if(StringUtil.isNotEmpty(orgId) && StringUtil.isNotEmpty(oper)){
				if(oper.equals(Constant.USER_TYPE_ENT)){
					CompanyInfo info = companyInfoService.selectByPrimaryKey(orgId);
					orgName = "企业名称";
					if(info != null) {
						orgName = info.getEntName();
					}
					page = "certificate_index_ent";
				}else if(oper.equals(Constant.USER_TYPE_STU)){
					StuUser stuUser = stuUserService.selectByPrimaryKey(userId);
					orgName = "学生用户名称";
					if(stuUser != null){
						orgName = stuUser.getSurname() + stuUser.getMissSurname();
					}
					
					
					page = "certificate_index_stu";
				}else if(oper.equals(Constant.USER_TYPE_SCH)){
					orgName = "高校用户名称";
					List<Map<String,Object>> list = 
							commonCodeService.getListByCol(ConstantTables.COMM_SCHOOL, ConstantTables.SCHOOL_COL_ID, orgId);
					if(list.size() > 0){
						orgName = String.valueOf(list.get(0).get("school_name"));
					}
					page = "certificate_index_sch";
				}
			}
			model.addAttribute("orgName", orgName);
			model.addAttribute("orgId", orgId);
			model.addAttribute("userId", userId);
			model.addAttribute("uploadAgin", "regedit");//用于判断是新增还是审核不通过重新上传的
			return "/regedit/" + page;
		} catch (Exception e) {
			log.info(LogUtil.getTrace(e));
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 企业资质审核	企业
	 * @param request
	 * @param yyzz
	 * @param sfz
	 * @param gzz
	 * @param mp
	 * @return
	 */
	@RequestMapping(value=("/certificateEnt"))
	@ResponseBody
	public Json certificateEnt(HttpServletRequest request, String yyzz, String sfz,
			String gzz, String mp, String uploadAgin, String userId, String orgId){ 
		Json json = new Json();
		try {
			long time = TimeUtil.currentTimeMillis();
			if(StringUtil.isEmpty(userId)) return json;
			boolean flag1 = false,flag2 = false,flag3 = false,flag4 = false;
			if(StringUtil.isEmpty(uploadAgin) || uploadAgin.equals("regedit")){
				//删除该账户下的资质文件
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("ent_user_id", userId);
				entBaseService.deleteDoc(map);
				if(StringUtil.isNotEmpty(yyzz)){//营业执照
					flag1 = insertEntDoc(userId, time, yyzz, "business_license");
				}
				if(StringUtil.isNotEmpty(sfz)){//身份证
					flag2 = insertEntDoc(userId, time, sfz, "id_card");
				}
				if(StringUtil.isNotEmpty(gzz)){//工作证
					flag3 = insertEntDoc(userId, time, gzz, "employee_card");
				}
				if(StringUtil.isNotEmpty(mp)){//名片
					flag4 = insertEntDoc(userId, time, mp, "business_card");
				}
				if(flag1 || flag2 || flag3 || flag4){
					json.setSuccess(true);
				}
			}
			return json;
		} catch (Exception e) {
			log.info(LogUtil.getTrace(e));
			e.printStackTrace();
			return json;
		}
	}
	
	public boolean insertEntDoc(String userId, long time, String path, String type){
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("ent_user_id", userId);
			map.put("last_updated_time", time);
			map.put("create_time", time);
			map.put("document_path", path);
			map.put("document_type", type);
			entBaseService.insertDoc(map);
			return true;
		} catch (Exception e) {
			log.info(LogUtil.getTrace(e));
			e.printStackTrace();
			return false;
			
		}
	}
	
	/**
	 * 资质认证  学生
	 * @return
	 */
	@RequestMapping(value=("/certificateStu"))
	@ResponseBody
	public Json certificateStu(HttpServletRequest request, String sfz, String byz,
			String xsz, String uploadAgin, String userId, String orgId){ 
		Json json = new Json();
		try {
			long time = TimeUtil.currentTimeMillis();
			if(StringUtil.isEmpty(userId)) return json;
			boolean flag1 = false,flag2 = false,flag3 = false;
			if(StringUtil.isEmpty(uploadAgin) || uploadAgin.equals("regedit")){
				//删除该账户下的资质文件
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("stu_user_id", userId);
				stuBaseService.deleteDoc(map);
				if(StringUtil.isNotEmpty(sfz)){//身份证
					flag1 = insertStuDoc(userId, time, sfz, "id_card");
				}
				if(StringUtil.isNotEmpty(byz)){//毕业证
					flag2 = insertStuDoc(userId, time, byz, "graduation_card");
				}
				if(StringUtil.isNotEmpty(xsz)){//学生证
					flag3 = insertStuDoc(userId, time, xsz, "student_card");
				}
				if(flag1 || flag2 || flag3 ){
					json.setSuccess(true);
				}
			}
			return json;
		} catch (Exception e) {
			log.info(LogUtil.getTrace(e));
			e.printStackTrace();
			return json;
		}
	}
	
	public boolean insertStuDoc(String userId, long time, String path, String type){
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("stu_user_id", userId);
			map.put("last_updated_time", time);
			map.put("create_time", time);
			map.put("document_path", path);
			map.put("document_type", type);
			stuBaseService.insertDoc(map);
			return true;
		} catch (Exception e) {
			log.info(LogUtil.getTrace(e));
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 资质认证  学校
	 * @return
	 */
	@RequestMapping(value=("/certificateSch"))
	@ResponseBody
	public Json certificateSch(HttpServletRequest request, String sfz, String jyxkz,
			String gzz, String mp, String uploadAgin, String userId, String orgId){ 
		Json json = new Json();
		try {
			long time = TimeUtil.currentTimeMillis();
			if(StringUtil.isEmpty(userId)) return json;
			boolean flag1 = false,flag2 = false,flag3 = false,flag4 = false;
			if(StringUtil.isEmpty(uploadAgin) || uploadAgin.equals("regedit")){
				//删除该账户下的资质文件
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("sch_user_id", userId);
				schBaseService.deleteDoc(map);
				if(StringUtil.isNotEmpty(jyxkz)){//教育许可证
					flag2 = insertSchDoc(userId, time, jyxkz, "licence_card");
				}
				if(StringUtil.isNotEmpty(sfz)){//身份证
					flag1 = insertSchDoc(userId, time, sfz, "id_card");
				}
				if(StringUtil.isNotEmpty(gzz)){//工作证
					flag3 = insertSchDoc(userId, time, gzz, "employee_card");
				}
				if(StringUtil.isNotEmpty(mp)){//名片
					flag4 = insertSchDoc(userId, time, mp, "business_card");
				}
				if(flag1 || flag2 || flag3 || flag4){
					json.setSuccess(true);
				}
			}
			return json;
		} catch (Exception e) {
			log.info(LogUtil.getTrace(e));
			e.printStackTrace();
			return json;
		}
	}
	
	public boolean insertSchDoc(String userId, long time, String path, String type){
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("sch_user_id", userId);
			map.put("last_updated_time", time);
			map.put("create_time", time);
			map.put("document_path", path);
			map.put("document_type", type);
			schBaseService.insertDoc(map);
			return true;
		} catch (Exception e) {
			log.info(LogUtil.getTrace(e));
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 资质审核详情页面
	 * @param request
	 * @param usrtId
	 * @param orgId
	 * @param model
	 * @return
	 */
	@RequestMapping(value=("/checkPageEnt"))
	public String checkPage(HttpServletRequest request, String userId,
			String orgId, Model model, String uploadAgin){
		try {
			CompanyInfo info = new CompanyInfo();
			UserLocalauth user = new UserLocalauth();
			CompanyUserInfo userInfo = new CompanyUserInfo();
			if(StringUtil.isNotEmpty(userId)){
				user = localUserService.selectByPrimaryKey(userId);
				userInfo = companyUserInfoService.selectByPrimaryKey(userId);
			}
			if(StringUtil.isNotEmpty(orgId)){
				info = companyInfoService.selectByPrimaryKey(orgId);
			}
			
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("ent_user_id", userId);
			List<Map<String,Object>> listDoc = entBaseService.selectDoc(map);
			model.addAttribute("info", info);	
			model.addAttribute("userInfo", userInfo);
			model.addAttribute("user", user);
			String checkRuslt = "";
			if(StringUtil.isEmpty(uploadAgin) || uploadAgin.equals("regedit")){
				checkRuslt = "未审核";
			}
			
			String locationCode = info.getOfficeArea();
			String location = "无";
			if(StringUtil.isNotEmpty(locationCode)){
				location = commonCodeService.getLocationNameByCode(locationCode);
			}
			
			model.addAttribute("location", location);
			
			model.addAttribute("uploadAgin", uploadAgin);
			model.addAttribute("checkRuslt", checkRuslt);
			model.addAttribute("listDoc", listDoc);
			
			
			model.addAttribute("userId", userId);
			model.addAttribute("orgId", orgId);
			
			return "/regedit/certificate_check_ent";
		} catch (Exception e) {
			log.info(LogUtil.getTrace(e));
			e.printStackTrace();
			return null;
			
		}
	}
	
	/**
	 * 资质审核详情页面 学生
	 * @param request
	 * @param usrtId
	 * @param orgId
	 * @param model
	 * @return
	 */
	@RequestMapping(value=("/checkPageStu"))
	public String checkPageStu(HttpServletRequest request, String userId,
			String orgId, Model model, String uploadAgin){
		try {
			UserLocalauth user = new UserLocalauth();
			user = localUserService.selectByPrimaryKey(userId);
			
			StuUser stuUser = stuUserService.selectByPrimaryKey(userId);
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("stu_user_id", userId);
			List<Map<String,Object>> listDoc = stuBaseService.selectDocList(map);
			model.addAttribute("user", user);
			model.addAttribute("userInfo", stuUser);
			String checkRuslt = "";
			if(StringUtil.isEmpty(uploadAgin) || uploadAgin.equals("regedit")){
				checkRuslt = "未审核";
			}
			
			String locationCode = stuUser.getLocationCode();
			String location = "无";
			if(StringUtil.isNotEmpty(locationCode)){
				location = commonCodeService.getLocationNameByCode(locationCode);
			}
			
			model.addAttribute("location", location);
			
			model.addAttribute("uploadAgin", uploadAgin);
			model.addAttribute("checkRuslt", checkRuslt);
			model.addAttribute("listDoc", listDoc);
			
			
			model.addAttribute("userId", userId);
			model.addAttribute("orgId", orgId);
			
			return "/regedit/certificate_check_stu";
		} catch (Exception e) {
			log.info(LogUtil.getTrace(e));
			e.printStackTrace();
			return null;
			
		}
	}
	
	/**
	 * 资质审核详情页面	 学校
	 * @param request
	 * @param usrtId
	 * @param orgId
	 * @param model
	 * @return
	 */
	@RequestMapping(value=("/checkPageSch"))
	public String checkPageSch(HttpServletRequest request, String userId,
			String orgId, Model model, String uploadAgin){
		try {
			UserLocalauth user = new UserLocalauth();
			user = localUserService.selectByPrimaryKey(userId);
			
			SchoolUserInfo schUser = schUserInfoService.selectByPrimaryKey(userId);
			SchoolInfoLink link = linkService.selectByPrimaryKey(orgId);
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("sch_user_id", userId);
			List<Map<String,Object>> listDoc = schBaseService.selectDocList(map);
			model.addAttribute("user", user);
			model.addAttribute("userInfo", schUser);
			String checkRuslt = "";
			if(StringUtil.isEmpty(uploadAgin) || uploadAgin.equals("regedit")){
				checkRuslt = "未审核";
			}
			
			String location = "无";
			if(link != null){
				String locationCode = link.getLocationCode();
				
				if(StringUtil.isNotEmpty(locationCode)){
					location = commonCodeService.getLocationNameByCode(locationCode);
				}
			}
			
			model.addAttribute("location", location);
			
			model.addAttribute("uploadAgin", uploadAgin);
			model.addAttribute("checkRuslt", checkRuslt);
			model.addAttribute("listDoc", listDoc);
			
			
			model.addAttribute("userId", userId);
			model.addAttribute("orgId", orgId);
			
			return "/regedit/certificate_check_sch";
		} catch (Exception e) {
			log.info(LogUtil.getTrace(e));
			e.printStackTrace();
			return null;
			
		}
	}
}
