package com.kaipin.enterprise.presentation.action.basic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kaipin.common.constants.Constant;
import com.kaipin.common.constants.ConstantTables;
import com.kaipin.common.entity.EntUser;
import com.kaipin.common.entity.Json;
import com.kaipin.common.entity.User;
import com.kaipin.common.presentation.action.BaseAction;
import com.kaipin.common.service.common.IBaseCodeService;
import com.kaipin.common.util.CookieUtil;
import com.kaipin.common.util.DESCoderUtil;
import com.kaipin.common.util.LogUtil;
import com.kaipin.common.util.LuceneUtil;
import com.kaipin.common.util.StringUtil;
import com.kaipin.common.util.TimeUtil;
import com.kaipin.enterprise.model.user.CompanyInfo;
import com.kaipin.enterprise.model.user.CompanyUserInfo;
import com.kaipin.enterprise.service.user.ICompanyInfoService;
import com.kaipin.enterprise.service.user.ICompanyUserInfoService;
import com.kaipin.university.model.user.SchoolInfoLink;
import com.kaipin.university.model.user.UserLocalauth;
import com.kaipin.university.service.user.ISchoolInfoLinkService;
import com.kaipin.university.service.user.IUserLocalauthService;

/**
 * 企业基本资料业务处理类
 * @author Mr-H
 *
 */

@RequestMapping("/entbasic")
@Controller
public class EntBasicAciton extends BaseAction{
	
	
	@Autowired
	private IBaseCodeService codeService;
	@Autowired
	private ICompanyInfoService companyInfoService;
	@Autowired
	private ICompanyUserInfoService companyUserService;
	@Autowired
	private IUserLocalauthService localUserService;
	@Autowired
	private ISchoolInfoLinkService schLinkService;

	/**
	 * 判断是否有网址
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping("/getWebsite")
	@ResponseBody
	public Json demo1(HttpServletRequest request, User user){
		Json json = new Json();
		try {
			String cookie_sid = super.getOrgId(request, user);
			CompanyInfo info = companyInfoService.selectByPrimaryKey(cookie_sid);
			if(StringUtil.isNotEmpty(info.getWebsite())){
				json.setObj(info.getWebsite());
				json.setSuccess(true);
			}
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			return json;
		}
	}
	
	/**
	 * 获取详情
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping("/detail")
	@ResponseBody
	public Json detail(HttpServletRequest request, User user){
		Json json = new Json();
		try {
			String cookie_sid = super.getOrgId(request, user);
			String cookie_uid = super.getUid(request, user);
			String u_type = CookieUtil.getCookieInfoByKey(request, Constant.USER_TYPE);
			
			CompanyInfo info = companyInfoService.selectByPrimaryKey(cookie_sid);
			CompanyUserInfo userInfo = companyUserService.selectByPrimaryKey(cookie_uid);
			
			String provinceCode = "";
			String cityCode = "";
			String countyCode = "";
			
			//所属地区
			String locationCode = info.getOfficeArea();
			provinceCode = this.provinceCode(locationCode);//省
			cityCode = this.cityCode(locationCode);//市
			countyCode = locationCode;//县
			if(countyCode.equals(cityCode)){
				countyCode = "";
			}
			
			Map<String, Object> map = new HashMap<>();
			map.put("info", info);
			map.put("userInfo", userInfo);
			map.put("org_id", cookie_sid);
			map.put("u_id", cookie_uid);
			
			map.put("provinceCode", provinceCode);
			map.put("cityCode", cityCode);
			map.put("countyCode", countyCode);
			
			map.put("u_type", u_type);
			
			json.setObj(map);
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			return json;
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
				/*List<Map<String,Object>> listConfig = configService.getList(map);
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
				}*/
			}
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 维护
	 * @return
	 */
	@RequestMapping(value="/edit")  
	@ResponseBody
	public Json edit(HttpServletRequest request, 
			CompanyUserInfo newUser, CompanyInfo entInfo,  String oper, String entName, String logoPath){
		try {
			Json json = new Json();
			
			String cookie_companyId = CookieUtil.getCookieInfoByKey(request, Constant.USER_ORG_ID);
			String cookie_userId = CookieUtil.getCookieInfoByKey(request, Constant.USER_UID);
			
			CompanyUserInfo info = new CompanyUserInfo();
			UserLocalauth localUser = new UserLocalauth();
			localUser.setId(cookie_userId);
			info.setId(CookieUtil.getCookieInfoByKey(request, Constant.USER_UID));
			
			entInfo.setId(cookie_companyId);
			
			if(oper.equals("user-info")){
				info.setSurname(newUser.getSurname());
				info.setSex(newUser.getSex());
				info.setPosition(newUser.getPosition());
				info.setMissSurname(newUser.getMissSurname());
				json.setMsg(entName);
				
				entInfo.setEntName(entName);
//				request.getSession().setAttribute(Constant.USER, user);
				CompanyInfo entInfoOld = companyInfoService.selectByPrimaryKey(cookie_companyId);
				String oldName = entInfoOld.getEntName();
				if(StringUtil.isNotEmpty(oldName)){
					if(!entName.equals(oldName)){
						entInfo.setVerifyStatus(Byte.valueOf(Constant.VALUE_ZERO));
					}
				}else if(StringUtil.isNotEmpty(entName)){
					entInfo.setVerifyStatus(Byte.valueOf(Constant.VALUE_ZERO));
				}
				//更新索引
				LuceneUtil.luceneOpt(info.getId(), Constant.VALUE_ZERO, Constant.VALUE_TWO);
				companyUserService.updateByPrimaryKeySelective(info);
				
			}else if(oper.equals("logo")){
				entInfo.setLogo(logoPath);
				json.setObj(logoPath);
			}else if(oper.equals("basic_logo")){
				//TODO 是否审核名称
			}
			entInfo.setLastUpdatedTime(TimeUtil.currentTimeMillis());
			companyInfoService.updateByPrimaryKeySelective(entInfo);
			json.setSuccess(true);
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
