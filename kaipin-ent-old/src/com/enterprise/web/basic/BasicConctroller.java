package com.enterprise.web.basic;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.swing.plaf.BorderUIResource.TitledBorderUIResource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import com.common.Constant;
import com.common.ConstantTables;
import com.common.constants.AppSearchConstant;
import com.common.page.Page;
import com.common.pojo.Json;
import com.common.pojo.StautsBean;
import com.enterprise.model.EntBaseinfo;
import com.enterprise.model.SystemConfig;
import com.enterprise.model.common.EntUser;
import com.enterprise.model.user.CompanyInfo;
import com.enterprise.model.user.CompanyUserInfo;
import com.enterprise.model.user.UserLocalauth;
import com.enterprise.service.common.IBaseCodeService;
import com.enterprise.service.common.IBaseService;
import com.enterprise.service.login.IEntBaseinfoService;
import com.enterprise.service.login.IEntUserService;
import com.enterprise.service.resume.IResumeService;
import com.enterprise.service.resume.ISystemConfigService;
import com.enterprise.service.user.ICompanyInfoService;
import com.enterprise.service.user.ICompanyUserInfoService;
import com.enterprise.service.user.IEntBaseUserService;
import com.enterprise.service.user.IUserLocalauthService;
import com.util.CookieUtil;
import com.util.DESCoderUtil;
import com.util.HttpPostUtil;
import com.util.JsonUtil;
import com.util.LogUtil;
import com.util.MD5Util;
import com.util.ObjectUtil;
import com.util.PropUtil;
import com.util.SendHtmlMailUtil;
import com.util.StringUtil;
import com.util.TimeUtil;
import com.util.UuidUtil;

@Controller
@RequestMapping("basicConctroller")
public class BasicConctroller {
	
	Logger logger = Logger.getLogger(BasicConctroller.class.getName());
	
	@Autowired
	private IEntBaseinfoService entService;
	@Autowired
	private IResumeService resumeService;
	@Autowired
	private ISystemConfigService configService;
	@Autowired
	private IEntUserService userService;
	@Autowired
	private IEntBaseUserService baseUserService;
	@Autowired
	private ICompanyInfoService companyInfoService;
	@Autowired
	private ICompanyUserInfoService companyUserService;
	@Autowired
	private IBaseCodeService baseCodeService;
	@Autowired
	private IUserLocalauthService localUserService;
	
	public void initList(HttpServletRequest request, Model model){
		
		//所属行业
		model.addAttribute("industryTypeList", getCommList(ConstantTables.COMM_INDUSTRY,Constant.VALUE_ONE));
		//企业性质
		model.addAttribute("companyTypeList", getCommList(ConstantTables.COMM_ENT_TYPE,""));
		//获取省份
		model.addAttribute("proviceList", getCommList(ConstantTables.COMM_LOCATION,
				" parent_code = '489' "));
		
	}
	
	public List<Map<String,Object>> getCommList(String tableName, String params){
		Map<String,Object> map = new HashMap<String,Object>();
		if(StringUtil.isEmpty(params)){
			map.put("parameters", "1=1");
		}else if(params.equals(Constant.VALUE_ONE)){
			map.put("parameters", " parent_code = '' or  parent_code is NULL ");
		}else {
			map.put("parameters", params);
		}
		map.put("tableName", tableName);
		List<Map<String,Object>> list = baseCodeService.getCodeList(map);
		return list;
	}
	
	
	/**
	 * 初始化企业管理后台首页
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/init")
	public String init(HttpServletRequest request, Model model){
		try {
			initList(request,model);
			
			String cookie_companyId = CookieUtil.getCookieInfoByKey(request, Constant.USER_ORG_ID);
			String cookie_uId = CookieUtil.getCookieInfoByKey(request, Constant.USER_UID);
			
			CompanyInfo newUser = companyInfoService.selectByPrimaryKey(cookie_companyId);
			String detail = "";
			if(StringUtil.isNotEmpty(newUser.getDetail())){
				detail = StringUtil.replaceBlank(newUser.getDetail());
			}
			
			String industry = newUser.getIndustryCode();
			String enthy = "例：互联网/电子商务";
			if(StringUtil.isNotEmpty(industry)){
				enthy = baseCodeService.getNameByCode(ConstantTables.COMM_INDUSTRY, 
						ConstantTables.INDUSTRY_COL_CODE, industry, 
						ConstantTables.INDUSTRY_COL_NAME);
			}
			
			String officeArea = "";
			String officeAreaCode = newUser.getOfficeArea();
			String area2 = "";
			String area1 = "";
			String parentCode = "";
			if(StringUtil.isNotEmpty(officeAreaCode)){
				if(officeAreaCode.equals("561") 
						|| officeAreaCode.equals("562") 
						|| officeAreaCode.equals("563")){
					int code = Integer.valueOf(officeAreaCode);
					switch (code) {
					case 561:
						officeArea = "香港";
						break;
					case 562:
						officeArea = "澳门";
						break;
					case 563:
						officeArea = "台湾省";
						break;
					default:
						officeArea = "";
					}
				}else{
					area2 = baseCodeService.getNameByCode(ConstantTables.COMM_LOCATION, 
							ConstantTables.AREA_COL_CODE, officeAreaCode, ConstantTables.AREA_COL_NAME);
					parentCode = baseCodeService.getNameByCode(ConstantTables.COMM_LOCATION, 
							ConstantTables.AREA_COL_CODE, officeAreaCode, ConstantTables.COL_PARENT_CODE);
					area1 = baseCodeService.getNameByCode(ConstantTables.COMM_LOCATION, 
							ConstantTables.AREA_COL_CODE, parentCode, ConstantTables.AREA_COL_NAME);
					officeArea = area1 + " " + area2;
				}
			}
			 
			
			model.addAttribute("officeArea",officeArea);
			model.addAttribute("enthy",enthy);
			model.addAttribute("detail",detail);
			model.addAttribute("bg",newUser.getBgUrl());
			model.addAttribute("logo",newUser.getLogo());
			model.addAttribute("entName",newUser.getEntName());
			
			//查询企业工作地区
			model.addAttribute("area1",area1);
			model.addAttribute("area2",area2);
			model.addAttribute("area2Code",newUser.getOfficeArea());
			model.addAttribute("area1Code",parentCode);
			
			model.addAttribute("cookie_uid",cookie_uId);
			model.addAttribute("cookie_companyId",cookie_companyId);
			return "/ent/basic/basic";
		} catch (Exception e) {
			logger.info(LogUtil.getTrace(e));;
			return null;
		}
		
		
	}
	/**
	 * 维护
	 * @param request
	 * @param detail
	 * @return
	 */
	@RequestMapping(value="/edit")
	@ResponseBody
	public Json init(HttpServletRequest request,String detail, String oper, 
			String logo ,String annexId,CompanyInfo basicInfo){
		try {
			Json json = new Json();
			String cookie_companyId = CookieUtil.getCookieInfoByKey(request, Constant.USER_ORG_ID);
			CompanyInfo newUser = companyInfoService.selectByPrimaryKey(cookie_companyId);
			if(StringUtil.isNotEmpty(oper)){
				if(oper.equals("detail")){
					detail = URLDecoder.decode(detail, "UTF-8");
					newUser.setDetail(StringUtil.replaceBlank(detail));
				}else if(oper.equals("logo")){
					newUser.setLogo(annexId);
				}else if(oper.equals("bg")){//背景
					newUser.setBgUrl(annexId);
				}
			}else{
				String oldName = newUser.getEntName();
				String newName = basicInfo.getEntName();
				if(StringUtil.isNotEmpty(oldName) && StringUtil.isNotEmpty(newName)){
					if(!oldName.equals(newName)){
						basicInfo.setVerifyStatus(Byte.valueOf(Constant.VALUE_ZERO));
					}
				}
				ObjectUtil.copeField(basicInfo, newUser);
			}
			newUser.setLastUpdatedTime(TimeUtil.currentTimeMillis());
			int i = companyInfoService.updateByPrimaryKeySelective(newUser);
			
			//创建索引
			if((basicInfo.getVerifyStatus()+"").equals(Constant.VALUE_TWO)){
				createIndex(AppSearchConstant.APP_SEARCH_COMPANY_UPDATE, newUser);
			}
			
			
			if(i == 1){
//				request.getSession().setAttribute(Constant.USER, user);
				if(StringUtil.isNotEmpty(oper)){
					if(oper.equals("logo")){
						request.getSession().setAttribute(Constant.LOGO, annexId);
					}
				}
				json.setObj(newUser);
				json.setSuccess(true);
			}
			
			return json;
		} catch (Exception e) {
			logger.info(LogUtil.getTrace(e));;
			e.printStackTrace();
			return null;
		}
		
		
	}
	
	/**
	 * 创建索引
	 * @param url
	 * @param function
	 */
	public void createIndex(String url, CompanyInfo function){
		try {
			
			Map<String, Object> mapLunece = new HashMap<String, Object>();
			String luneceTaskId = UuidUtil.getUUID();
			mapLunece.put("id", luneceTaskId);
			mapLunece.put("obj_id", function.getId());//资源id
			mapLunece.put("obj_type", 0);//对象类型(0-公司,1-职位,2-视频,3-学生,4-学校
			mapLunece.put("opt", 2);//操作类型(0-add,1-delete,2-update
			mapLunece.put("create_time", TimeUtil.currentTimeMillis()+"");//
			mapLunece.put("status", 0);//处理状态（0-未处理,1-已处理
			mapLunece.put("handle_time", TimeUtil.currentTimeMillis()+"");//处理时间
			
			String result = HttpPostUtil.doPost(AppSearchConstant.APP_SEARCH_URL 
					+ AppSearchConstant.SEARCH_TASK_URL, mapLunece);
			
			String code = JsonUtil.jsonToObj(result, StautsBean.class).getCode();
			
			if(StringUtil.isNotEmpty(code) && code.equals(Constant.VALUE_ZERO)){
				Map<String,Object> map = new HashMap<String,Object>();
				map.put(AppSearchConstant.APP_SEARCH_ID, function.getId());
				map.put(AppSearchConstant.ENT_NAME, function.getEntName());
				map.put(AppSearchConstant.OFFICE_AREA, function.getOfficeArea());
				map.put(AppSearchConstant.INDUSTRY_CODE, function.getIndustryCode());
				map.put(AppSearchConstant.LAST_UPDATED_TIME, function.getLastUpdatedTime());
				
				String s = HttpPostUtil.doPost(AppSearchConstant.APP_SEARCH_URL 
						+ url +  luneceTaskId, map);
				System.out.println("ent 索引更新信息： " + s);
			}
			
		} catch (Exception e) {
			logger.info(LogUtil.getTrace(e));
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取详细信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/detail")
	@ResponseBody
	public Json detail(HttpServletRequest request, String opers){
		try {
			Json json = new Json();
			String cookie_companyId = CookieUtil.getCookieInfoByKey(request, Constant.USER_ORG_ID);
			//EntBaseinfo newUser = entService.selectByPrimaryKey(user.getCompanyId());
			CompanyInfo info = companyInfoService.selectByPrimaryKey(cookie_companyId);
			String entType = "";
			String enthy = "";
			if(StringUtil.isEmpty(opers)){
				if(info != null){
					entType = info.getCompanyTypeCode();
					if(StringUtil.isNotEmpty(entType)){
						entType = baseCodeService.getNameByCode(ConstantTables.COMM_ENT_TYPE, 
								ConstantTables.ENT_TYPE_COL_CODE, entType, 
								ConstantTables.ENT_TYPE_COL_NAME);
						info.setCompanyTypeCode(entType);
					}
					
					
				}
			}
			if(StringUtil.isNotEmpty(opers) && opers.equals("detailEdit")){
				String codes = "";
				String parentName = "";
				String parentCode = "";
				String officeAreaCode = info.getOfficeArea();
				Map<String,Object> mapVal = new HashMap<String,Object>();
				List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
				if(StringUtil.isNotEmpty(officeAreaCode)){
					if(officeAreaCode.equals("561") 
							|| officeAreaCode.equals("562") 
							|| officeAreaCode.equals("563")){
						int code = Integer.valueOf(officeAreaCode);
						switch (code) {
						case 561:
							parentName = "香港";
							break;
						case 562:
							parentName = "澳门";
							break;
						case 563:
							parentName = "台湾";
							break;
						default:
							parentName = "";
						}
						Map<String,Object> mapSpecie = new HashMap<String,Object>();
						parentCode = officeAreaCode;
						mapSpecie.put("location_code", officeAreaCode);
						mapSpecie.put("location_name", parentName);
						list.add(mapSpecie);
					}else{
						parentCode = baseCodeService.getNameByCode(ConstantTables.COMM_LOCATION, 
								ConstantTables.AREA_COL_CODE, officeAreaCode, ConstantTables.COL_PARENT_CODE);
						Map<String,Object> map = new HashMap<String,Object>();
						map.put("parameters", " parent_code = '"+ parentCode +"' ");
						map.put("tableName", " comm_location ");
					    list = baseCodeService.getCodeList(map);
					}
					
					mapVal.put("pro", parentCode);
					mapVal.put("cityCode", officeAreaCode);
					mapVal.put("cityList", list); 
					String jsonStr = JsonUtil.toJson(mapVal);
					json.setMsg(jsonStr);
				} 
			}
			json.setObj(info);
			return json;
		} catch (Exception e) {
			logger.info(LogUtil.getTrace(e));;
			e.printStackTrace();
			return null;
		}
		
		
	}
	
	/**
	 * 获取最新5条信息简历信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/getResume")
	@ResponseBody
	public Json getResume(HttpServletRequest request, Page page){
		try {
			Json json = new Json();
			page.setRows(5);
			String cookie_companyId = CookieUtil.getCookieInfoByKey(request, Constant.USER_ORG_ID);
			HashMap<String,Object> map = new HashMap<String, Object>();
			map.put("companyId", cookie_companyId);
			map.put("today", TimeUtil.currentTimeMillis());
			map.put("page_start", (page.getPage()-1) * page.getRows());
			map.put("page_size", page.getRows());
			List<Map<String,Object>> list = resumeService.getAll(map);
			List<Map<String,Object>> listCounts = resumeService.getIndexResumeListCounts(map);
			String counts = "0";
			map.clear();
			if(list.size() > 0){
				json.setSuccess(true);
				map.put("list", list);
				json.setObj(map);
				if(listCounts.size() > 0){
					counts = String.valueOf(listCounts.get(0).get(Constant.COUNT));
					json.setSuccess(true);
					map.put("counts", counts);
					json.setObj(map);
				}
			}
			return json;
		} catch (Exception e) {
			logger.info(LogUtil.getTrace(e));;
			e.printStackTrace();
			return null;
		}
		
		
	}
	
	
	/**
	 * 保存个人信息维护  邮箱服务器配置
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/config")  
	@ResponseBody
	public Json config(HttpServletRequest request, 
			CompanyUserInfo newUser, String phone,
			SystemConfig config, String configId, String port, String oper, String entName){
		try {
			Json json = new Json();
			
			String cookie_companyId = CookieUtil.getCookieInfoByKey(request, Constant.USER_ORG_ID);
			String cookie_userId = CookieUtil.getCookieInfoByKey(request, Constant.USER_UID);
			
			if(oper.startsWith("user")){
				CompanyUserInfo info = new CompanyUserInfo();
				UserLocalauth localUser = new UserLocalauth();
				localUser.setId(cookie_userId);
				info.setId(CookieUtil.getCookieInfoByKey(request, Constant.USER_UID));
				if(oper.equals("user-phone")){//修改手机号
					localUser.setPhone(phone);
					localUserService.updateByPrimaryKeySelective(localUser);
				}else if(oper.equals("user-info")){//修改基本信息
					info.setSurname(newUser.getSurname());
					info.setSex(newUser.getSex());
					info.setPosition(newUser.getPosition());
					info.setMissSurname(newUser.getMissSurname());
					json.setMsg(entName);
					CompanyInfo entInfo = new CompanyInfo();
					entInfo.setId(cookie_companyId);
					entInfo.setEntName(entName);
//					request.getSession().setAttribute(Constant.USER, user);
					CompanyInfo entInfoOld = companyInfoService.selectByPrimaryKey(cookie_companyId);
					String oldName = entInfoOld.getEntName();
					if(StringUtil.isNotEmpty(oldName)){
						if(!entName.equals(oldName)){
							entInfo.setVerifyStatus(Byte.valueOf(Constant.VALUE_ZERO));
						}
					}else if(StringUtil.isNotEmpty(entName)){
						entInfo.setVerifyStatus(Byte.valueOf(Constant.VALUE_ZERO));
					}
					
					companyInfoService.updateByPrimaryKeySelective(entInfo);
					int i = companyUserService.updateByPrimaryKeySelective(info);
				}
				
				json.setSuccess(true);
			}else if(oper.equals("mail")){//配置邮箱
				String password = config.getMailPassword();
				password = DESCoderUtil.deCode(password);
				config.setMailPassword(password);
				//判断该企业在表中有无数据
				Map<String,Object> map = new  HashMap<String,Object>();
				map.put("companyId", cookie_companyId);
				List<Map<String,Object>> list = configService.getList(map);
				
				if(list.size() > 0){
					//编辑配置信息
					configService.updateByPrimaryKeySelective(config);
				}else{
					//新增一个配置信息
					config.setId(UuidUtil.getUUID());
					config.setCompanyId(cookie_companyId);
					configService.insertSelective(config);
				}
				//发送邮件测试
				int flag = SendHtmlMailUtil.configTest(config.getMailUsername(),
										DESCoderUtil.enCode(config.getMailPassword()),
										config.getMailUsername(),
										config.getMailHost(),
										String.valueOf(config.getMailPort()));
				
				if(flag == 200) {
					json.setSuccess(true);
				}
			}
			return json;
		} catch (Exception e) {
			logger.info(LogUtil.getTrace(e));;
			e.printStackTrace();
			return null;
		}
	}
	
	
	/**
	 * 获取个人信息维护  邮箱服务器配置
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/getUserInfoAndConfig")  
	@ResponseBody
	public Json getUserInfoAndConfig(HttpServletRequest request, String oper, Model model){
		try {
			Json json = new Json();
			String cookie_companyId = CookieUtil.getCookieInfoByKey(request, Constant.USER_ORG_ID);
			HashMap<String,Object> map = new HashMap<String, Object>();
			map.put("companyId", cookie_companyId);
			if(oper.equals(Constant.VALUE_ZERO)){
				CompanyUserInfo userInfo = companyUserService.selectByPrimaryKey(
						CookieUtil.getCookieInfoByKey(request, Constant.USER_UID)
						);
				CompanyInfo info =companyInfoService.selectByPrimaryKey(cookie_companyId);
				String entName = "";
				EntUser userNew = new EntUser();
				if(StringUtil.isNotEmpty(info.getEntName())){
					entName = info.getEntName();
				}
				UserLocalauth localUser = localUserService.selectByPrimaryKey(CookieUtil.getCookieInfoByKey(request, Constant.USER_UID));
				userNew.setEntName(entName);
				userNew.setEmail(localUser.getEmail());
				userNew.setUserName(userInfo.getMissSurname());
				userNew.setUserSurname(userInfo.getSurname());
				userNew.setSex(userInfo.getSex());
				userNew.setHeadUrl(userInfo.getHeadUrl());
				userNew.setPosition(userInfo.getPosition());
				userNew.setPhone(localUser.getPhone());
				
				
				json.setObj(userNew);
			}else if(oper.equals(Constant.VALUE_ONE)){
				List<Map<String,Object>> listConfig = configService.getList(map);
				if(listConfig.size() > 0){
					Object o = listConfig.get(0).get("mail_password");
					String password = "";
					if(o != null){
						password = String.valueOf(o);
						if(StringUtil.isNotEmpty(password)){
							password = DESCoderUtil.enCode(password);
						}
					}
					listConfig.get(0).put("mail_password", password);
					json.setObj(listConfig.get(0));
				}
			}
			return json;
		} catch (Exception e) {
			logger.info(LogUtil.getTrace(e));;
			e.printStackTrace();
			return null;
		}
	}
	
	
	/**
	 * 密码修改验证
	 * 修改密码
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/modiyPw")  
	@ResponseBody
	public Json modiyPw(HttpServletRequest request, String pw, String oper){
		try {
			Json json = new Json();
			String cookie_companyId = CookieUtil.getCookieInfoByKey(request, Constant.USER_ORG_ID);
			String cookie_userId = CookieUtil.getCookieInfoByKey(request, Constant.USER_UID);
			UserLocalauth localUser = localUserService.selectByPrimaryKey(cookie_userId);
			if(oper.equals(Constant.VALUE_ZERO)){//校验
				if(MD5Util.encrypt(pw).equals(localUser.getPassword())){
					json.setSuccess(true);
				}
			}else if(oper.equals(Constant.VALUE_ONE)){//保存
				UserLocalauth userInfo = new UserLocalauth();
				userInfo.setId(localUser.getId());
				userInfo.setPassword(MD5Util.encrypt(pw));
				userInfo.setEncodePassword(DESCoderUtil.deCode(pw));
				int i = localUserService.updateByPrimaryKeySelective(userInfo);
				localUser.setPassword(MD5Util.encrypt(pw));
				request.getSession().setAttribute(Constant.USER, localUser);
				if(i == 1){
					json.setSuccess(true);
					if (request.getSession() != null) {
						request.getSession().invalidate();
					}
				}
			}
			return json;
		} catch (Exception e) {
			logger.info(LogUtil.getTrace(e));;
			e.printStackTrace();
			return null;
		}
	}
	
	
	/**
	 * 检查资质认证
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/checkCertificate")  
	@ResponseBody
	public Json checkCertificate(HttpServletRequest request){
		try {
			Json json = new Json();
			String cookie_companyId = CookieUtil.getCookieInfoByKey(request, Constant.USER_ORG_ID);
			if(StringUtil.isEmpty(cookie_companyId)){
				return null;
			}
			Map<String,Object> map = new  HashMap<String,Object>();
			map.put("ent_user_id", CookieUtil.getCookieInfoByKey(request, Constant.USER_UID));
			List<Map<String,Object>> list = baseUserService.selectDoc(map);
			if(list.size() > 0){
				Byte verifyStatus = companyInfoService.selectByPrimaryKey(cookie_companyId).getVerifyStatus();
				json.setObj(verifyStatus);
				json.setSuccess(true);
			}
			return json;
		} catch (Exception e) {
			logger.info(LogUtil.getTrace(e));;
			e.printStackTrace();
			return null;
		}
	}
	
	
	/**
	 * 获取公司的网址
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/getWebsite")  
	@ResponseBody
	public Json getWebsite(HttpServletRequest request){
		try {
			Json json = new Json();
			String cookie_companyId = CookieUtil.getCookieInfoByKey(request, Constant.USER_ORG_ID);
			CompanyInfo info = companyInfoService.selectByPrimaryKey(cookie_companyId);
			String website = "";
			String bg = "";
			if(info != null){
				bg = info.getBgUrl();
				website = info.getWebsite();
				if(StringUtil.isNotEmpty(website) && StringUtil.isNotEmpty(bg)){
					json.setObj(website);
					json.setSuccess(true);
				}
			}
			return json;
		} catch (Exception e) {
			logger.info(LogUtil.getTrace(e));;
			e.printStackTrace();
			return null;
		}
	}
	

	/**
	 * 获取头像
	 * @param request
	 * @return
	 */
	@RequestMapping("/getLogo")
	@ResponseBody
	public Json getLogo(HttpServletRequest request){
		Json json = new Json();
		try {
			String cookie_companyId = CookieUtil.getCookieInfoByKey(request, Constant.USER_ORG_ID);
			CompanyInfo info = companyInfoService.selectByPrimaryKey(cookie_companyId);
			String logo = "";
			if(info != null){
				logo = info.getLogo();
				json.setSuccess(true);
			}
			json.setObj(logo);
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			return json;
		}
	}
}
