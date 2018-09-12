package com.enterprise.web;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.comet4j.core.CometConnection;
import org.comet4j.core.CometContext;
import org.comet4j.core.CometEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.Constant;
import com.common.pojo.Json;
import com.enterprise.model.EntBaseinfo;
import com.enterprise.model.EntLiveInfo;
import com.enterprise.model.PositionInfo;
import com.enterprise.model.user.CompanyInfo;
import com.enterprise.model.user.CompanyUserInfo;
import com.enterprise.model.user.EntBaseUser;
import com.enterprise.service.common.IBaseService;
import com.enterprise.service.meeting.IEntLiveInfoService;
import com.enterprise.service.position.IPostionService;
import com.enterprise.service.test.TestService;
import com.enterprise.service.user.ICompanyInfoService;
import com.enterprise.service.user.ICompanyUserInfoService;
import com.enterprise.service.user.IEntBaseUserService;
import com.sun.corba.se.impl.encoding.CodeSetConversion.BTCConverter;
import com.util.StringUtil;
import com.util.TimeUtil;

@Controller
@RequestMapping("test")
public class test {
	
	@Autowired
	private IBaseService baseService;
	@Autowired
	private ICompanyInfoService cinfoService;
	@Autowired
	private ICompanyUserInfoService userService;
	@Autowired
	private IEntBaseUserService baserUserService;
	@Autowired
	private IPostionService positionService;
	@Autowired
	private IEntLiveInfoService liveService;
	@Autowired
	private TestService testService;
	
	/**
	 * 事物回滚
	 * @return
	 */
	@RequestMapping(value="/rollback")
	@ResponseBody
	public Json add(){
		Json json = new Json();
		json.setMsg("test");
		boolean flag = false;
		try {
			testService.insertInfo();
			flag =true;
		} catch (Exception e) {
			flag = false;
		}
		
		json.setSuccess(flag);
		return json;
	}
	
	/**
	 * 迁移用户
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/dataUser")  
	@ResponseBody
	public Json data(HttpServletRequest request) { 
		Json json = new Json();
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			List<Map<String,Object>> list = baseService.getList("ent_user", map, "");
			if(list.size() > 0){
				for (int i = 0; i < list.size(); i++) {
					String userId = String.valueOf(list.get(i).get("id"));
					String companyId = String.valueOf(list.get(i).get("company_id"));
					Map<String,Object> mapBasic = new HashMap<String,Object>();
					mapBasic.put("id", companyId);
					List<Map<String,Object>> listBasic 
											= baseService.getList("ent_baseinfo", mapBasic, "");
					if(listBasic.size() > 0){
						//1	插入用户信息
						CompanyUserInfo user = new CompanyUserInfo();
						user.setId(userId);
						if(list.get(i).get("user_name") != null){
							if(StringUtil.isNotEmpty(String.valueOf(list.get(i).get("user_name")))){
//								user.setUserName(String.valueOf(list.get(i).get("user_name")));
							}
						}
						if(list.get(i).get("sex") != null ){
							if(StringUtil.isNotEmpty(String.valueOf(list.get(i).get("sex")))){
								user.setSex(Byte.valueOf(String.valueOf(list.get(i).get("sex"))));
							}
						}
						if(list.get(i).get("position") != null){
							user.setPosition(String.valueOf(list.get(i).get("position")));
						}
						if(list.get(i).get("password") != null){
							user.setPassword(String.valueOf(list.get(i).get("password")));
						}
						if(list.get(i).get("phone") != null){
							user.setPhone(String.valueOf(list.get(i).get("phone")));
						}
						if(list.get(i).get("check_phone") != null){
							user.setIsCheckPhone(Byte.valueOf(String.valueOf(list.get(i).get("check_phone"))));
						}
						if(list.get(i).get("email") != null){
							user.setEmail(String.valueOf(list.get(i).get("email")));
						}
						if(list.get(i).get("check_mail") != null){
							user.setIsCheckMail(Byte.valueOf(String.valueOf(list.get(i).get("check_mail"))));
						}
						
						user.setEnable(Byte.valueOf(Constant.VALUE_ONE));
						userService.insertSelective(user);
						
						//2	插入企业信息
						CompanyInfo info = new CompanyInfo();
						info.setId(companyId);
						info.setEntName(String.valueOf(list.get(i).get("ent_name")));
						if(listBasic.get(0).get("ent_simple_name") != null){
							info.setEntSimpleName(String.valueOf(list.get(i).get("ent_simple_name")));
						}
//						if(listBasic.get(0).get("numbers") != null || !listBasic.get(0).get("numbers").equals("null")){
//							System.out.println(">>>>> "+listBasic.get(0).get("numbers"));
//							info.setPeopleNumber(Integer.valueOf(String.valueOf(list.get(i).get("numbers"))));
//						}
						if(listBasic.get(0).get("office_address") != null){
							info.setOfficeAddress(String.valueOf(list.get(i).get("office_address")));
						}
						if(listBasic.get(0).get("office_address") != null){
							info.setOfficeAddress(String.valueOf(list.get(i).get("office_address")));
						}
						if(listBasic.get(0).get("detail") != null){
							info.setDetail(String.valueOf(list.get(i).get("detail")));
						}
						if(listBasic.get(0).get("follow_area") != null){
							info.setBusinessDomain(String.valueOf(list.get(i).get("follow_area")));
						}
						if(listBasic.get(0).get("website") != null){
							info.setWebsite(String.valueOf(list.get(i).get("website")));
						}
						if(listBasic.get(0).get("regedit_time") != null){
							info.setRegeditDate(String.valueOf(list.get(i).get("regedit_time")));
						}
						if(listBasic.get(0).get("logo") != null){
							info.setLogo(String.valueOf(list.get(i).get("logo")));
						}
						if(listBasic.get(0).get("bg_url") != null){
							info.setBgUrl(String.valueOf(list.get(i).get("bg_url")));
						}
						
						info.setCreateTime(TimeUtil.currentTimeMillisByTime(
								String.valueOf(listBasic.get(0).get("create_time"))));
						cinfoService.insertSelective(info);
						
						//3   插入关系表
						EntBaseUser baseUser = new EntBaseUser();
						baseUser.setCompanyId(companyId);
						baseUser.setCompanyUserId(userId);
						baseUser.setCreateTime(
								TimeUtil.currentTimeMillisByTime(
										String.valueOf(list.get(i).get("create_time"))));
						baseUser.setIsSystem(Byte.valueOf(Constant.VALUE_ONE));
						baserUserService.insertSelective(baseUser);
					}
				}
			}
			return json; 
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		 
	}
	
	
	/**
	 * 迁移职位
	 * 
	 * @return
	 */
	@RequestMapping(value="/dataPosition")  
	@ResponseBody
	public Json dataPosition(HttpServletRequest request) { 
		Json json = new Json();
		try {
			Map<String,String> map = new HashMap<String,String>();
			map.put("sql", "select * from position_info1");
			List<Map<String,String>> list = baseService.getListStr(map);
			if(list.size() > 0){
				for (int i = 0; i < list.size(); i++) {
					PositionInfo info = new PositionInfo();
					info.setId(list.get(i).get("id"));
					info.setCompanyId(list.get(i).get("company_id"));
					info.setPositionName(list.get(i).get("position_name"));
					info.setAgeStart(Byte.valueOf("18"));
					info.setAgeEnd(Byte.valueOf("30"));
					
					if(StringUtil.isNotEmpty(list.get(i).get("company_name"))){
						info.setCompanyName(list.get(i).get("company_name"));
					}
					
					if(StringUtil.isNotEmpty(list.get(i).get("start_time"))){
						info.setStartTime(list.get(i).get("start_time"));
					}
					
					if(StringUtil.isNotEmpty(list.get(i).get("end_time"))){
						info.setEndTime(String.valueOf(TimeUtil.currentTimeMillisByTime(list.get(i).get("end_time") + " 00:00:00")));
					}
					
					if(StringUtil.isNotEmpty(list.get(i).get("position_detail"))){
						info.setPositionDetail(list.get(i).get("position_detail"));
					}
					
					if(StringUtil.isNotEmpty(list.get(i).get("position_introduction"))){
						info.setPositionResponsibility(list.get(i).get("position_introduction"));
					}
					
					if(StringUtil.isNotEmpty(list.get(i).get("position_requirements"))){
						info.setPositionRequirements(list.get(i).get("position_requirements"));
					}
					
					if(StringUtil.isNotEmpty(list.get(i).get("other_info"))){
						info.setOtherInfo(list.get(i).get("other_info"));
					}
					
					if(StringUtil.isNotEmpty(list.get(i).get("company_introduction"))){
						info.setCompanyIntroduction(list.get(i).get("company_introduction"));
					}
					
					if(StringUtil.isNotEmpty(list.get(i).get("reporting"))){
						info.setSuperior(list.get(i).get("reporting"));
					}
					
//					if(StringUtil.isNotEmpty(list.get(i).get("salary_start"))){
//						info.setSalaryStart(Integer.valueOf(list.get(i).get("salary_start")));
//					}
					
//					if(StringUtil.isNotEmpty(list.get(i).get("salary_end"))){
//						info.setSalaryEnd(Integer.valueOf(list.get(i).get("salary_end")));
//					}
					
					if(StringUtil.isNotEmpty(list.get(i).get("sex"))){
						info.setSexCode(list.get(i).get("sex"));
					}
					
					if(StringUtil.isNotEmpty(list.get(i).get("salary_year"))){
						info.setSalaryYear(list.get(i).get("salary_year"));
					}
					
					if(StringUtil.isNotEmpty(list.get(i).get("professional_requirements"))){
						info.setMajorRequest(list.get(i).get("professional_requirements"));
					}
					
//					if(StringUtil.isNotEmpty(list.get(i).get("department_numbers"))){
//						info.setDepartmentNumbers(Integer.valueOf(list.get(i).get("department_numbers")));
//					}
					
//					if(StringUtil.isNotEmpty(list.get(i).get("year_holiday"))){
//						info.setYearHoliday(list.get(i).get("year_holiday"));
//					}
					
					if(StringUtil.isNotEmpty(list.get(i).get("salary_forms"))){
						info.setSalaryForms(list.get(i).get("salary_forms"));
					}
					
					if(StringUtil.isNotEmpty(list.get(i).get("social_security"))){
						info.setSocialSecurity(list.get(i).get("social_security"));
					}
					
					if(StringUtil.isNotEmpty(list.get(i).get("department"))){
						info.setDepartment(list.get(i).get("department"));
					}
					
					if(StringUtil.isNotEmpty(list.get(i).get("live"))){
						info.setLive(list.get(i).get("live"));
					}
					
					if(StringUtil.isNotEmpty(list.get(i).get("call_traffic"))){
						info.setCallTraffic(list.get(i).get("call_traffic"));
					}
					
					if(StringUtil.isNotEmpty(String.valueOf(list.get(i).get("status")))){
//						String s = list.get(i).get("status");
						info.setStatus((Byte.valueOf(String.valueOf(list.get(i).get("status")))));
					}else{
						info.setStatus((Byte.valueOf(String.valueOf("0"))));
					}
					
//					if(StringUtil.isNotEmpty(list.get(i).get("salary_type"))){
						info.setSalaryType((Byte.valueOf(String.valueOf(list.get(i).get("salary_type")))));
//					}
					
					if(StringUtil.isNotEmpty(list.get(i).get("create_time"))){
						info.setCreateTime(
								TimeUtil.currentTimeMillisByTime(
										list.get(i).get("create_time")));
					}
					
					positionService.insertSelective(info);
					System.out.println(">>>>>>>>>>> 插入第   " + i + " 条");
				}
				System.out.println(">>>>>>>>>>>>> game over");
			}
			return json; 
		} catch (Exception e) {
			System.out.println(">>>>>>>>>>>>> time out");
			e.printStackTrace();
			return null;
		}
		 
	}

	/**
	 * 手机端调用消息接口
	 * 实时的反馈给企业端
	 * 
	 * @return
	 */
	@RequestMapping(value="/liveInfo")  
	@ResponseBody
	public Json liveInfo(HttpServletRequest request) { 
		Json json = new Json();
		try {
			Map<String,String> map = new HashMap<String,String>();
			map.put("sql", "select * from ent_live_info");
			List<Map<String,String>> list = baseService.getListStr(map);
			if(list.size() > 0){
				for (int i = 0; i < list.size(); i++) {
					EntLiveInfo live = new EntLiveInfo();
					
					live.setId(list.get(i).get("id"));
					
					if(StringUtil.isNotEmpty(String.valueOf(list.get(i).get("room_id")))){
						live.setRoomId(Integer.valueOf(String.valueOf(list.get(i).get("room_id"))));
					}
					
					if(StringUtil.isNotEmpty(String.valueOf(list.get(i).get("company_id")))){
						live.setOrganizationId(String.valueOf(list.get(i).get("company_id")));
					}
					
					
					if(StringUtil.isNotEmpty(String.valueOf(list.get(i).get("status")))){
						live.setStatus(Byte.valueOf(String.valueOf(list.get(i).get("status"))));
					}
					
					if(StringUtil.isNotEmpty(String.valueOf(list.get(i).get("strat_time")))){
						String time = String.valueOf(list.get(i).get("strat_time"));
						time = time.substring(0, time.length()-2);
						live.setStratTime(time);
					}
					
					if(StringUtil.isNotEmpty(String.valueOf(list.get(i).get("end_time")))){
						String time = String.valueOf(list.get(i).get("end_time"));
						time = time.substring(0, time.length()-2);
						live.setEndTime(time);
					}
					
					if(StringUtil.isNotEmpty(String.valueOf(list.get(i).get("subject")))){
						live.setSubject(String.valueOf(list.get(i).get("subject")));
					}
					
					if(StringUtil.isNotEmpty(String.valueOf(list.get(i).get("type")))){
						live.setType(Byte.valueOf(String.valueOf(list.get(i).get("type"))));
					}
					
					if(StringUtil.isNotEmpty(String.valueOf(list.get(i).get("change_type")))){
						live.setChangeType(String.valueOf(list.get(i).get("change_type")));
					}
					
					if(StringUtil.isNotEmpty(String.valueOf(list.get(i).get("cover_image_path")))){
						live.setCoverImagePath(String.valueOf(list.get(i).get("cover_image_path")));
					}
					
					if(StringUtil.isNotEmpty(String.valueOf(list.get(i).get("enable")))){
						live.setEnable(Byte.valueOf(String.valueOf(list.get(i).get("enable"))));
					}
					
					if(StringUtil.isNotEmpty(String.valueOf(list.get(i).get("create_time")))){
						live.setCreateTime(TimeUtil.currentTimeMillisByTime(String.valueOf(list.get(i).get("create_time"))));
					}

					if(StringUtil.isNotEmpty(String.valueOf(list.get(i).get("memo")))){
						live.setMemo(String.valueOf(list.get(i).get("memo")));
					}
					
					liveService.insertSelective(live);
				}
			}
			return json; 
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		 
	}
	/**
	 * 手机端调用消息接口
	 * 实时的反馈给企业端
	 * 
	 * @return
	 */
	@RequestMapping(value="/msg")  
	@ResponseBody
	public Json init(HttpServletRequest request) { 
		Json json = new Json();
		try {
			CometEngine engine = CometContext.getInstance().getEngine();
			// 参数的意思：通过什么频道（CHANNEL1）发送什么数据（number1++），前台可用可用频道的值（result1）来获取某频道发送的数据
			// engine.sendToAll(Constant.CHANNEL_MSG_INTERVIEW, "test");
			List<CometConnection> list = engine.getConnections();
			String id = "245fec36-6cb8-4362-a135-53b715b4f5ac";
			if(list.size() > 0){
				for (int i = 0; i < list.size(); i++) {
					if(list.get(i).getId().equals(id)){
						engine.sendTo(Constant.CHANNEL_MSG_INTERVIEW, list.get(i), "testa");
					}
				}
			}
			engine.sendToAll("result1", "result1");
			engine.sendToAll("result2", "result2");
			return json; 
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	/**
	 *重复提交
	 * 
	 * @return
	 */
	@RequestMapping(value="/repeat")  
	@ResponseBody
	public Json repeat(HttpServletRequest request) { 
		Json json = new Json();
		try {
			for (int i = 0; i < 10; i++) {
				System.err.println(">>>>  " + i);
				Thread.sleep(1000);
			}
			return json; 
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	/**
	 * 获取服务器目录
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/getWebPath")  
	@ResponseBody
	public Json getWebPath(HttpServletRequest request) { 
		Json json = new Json();
		try {
			String path = request.getSession().getServletContext().getRealPath("");
			System.out.println(path);
			json.setObj(path);
			return json; 
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	/**
	 * 测试页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/weixin")  
	public String init0(HttpServletRequest request) { 
		try {
			
			String path = request.getSession().getServletContext().getRealPath("/");
			File file = new File(path + "/temp");
			if(!file.exists()){
				file.mkdir();
			}
			
			System.out.println(path);
			return "/test/test"; 
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
}
