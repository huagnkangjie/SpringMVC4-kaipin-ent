package com.kaipin.university.presentation.action.user;

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
import com.kaipin.common.entity.Json;
import com.kaipin.university.model.user.SchoolInfo;
import com.kaipin.university.model.user.SchoolInfoLink;
import com.kaipin.university.model.user.SchoolUserInfo;
import com.kaipin.university.model.user.UserLocalauth;
import com.kaipin.university.service.user.ISchBaseUserService;
import com.kaipin.university.service.user.ISchoolInfoLinkService;
import com.kaipin.university.service.user.ISchoolInfoService;
import com.kaipin.university.service.user.ISchoolUserInfoService;
import com.kaipin.university.service.user.IUserLocalauthService;
import com.kaipin.common.util.CookieUtil;
import com.kaipin.common.util.DESCoderUtil;
import com.kaipin.common.util.MD5Util;

/**
 * 学校用户基本信息
 * @author Mr-H
 *
 */
@Controller
@RequestMapping("/sch")
public class SchBaseUserController {
	
	@Autowired
	private IUserLocalauthService localUserService;
	@Autowired
	private ISchoolInfoLinkService schLinkService;
	@Autowired
	private ISchoolInfoService schInfoService;
	@Autowired
	private ISchoolUserInfoService schUserInfoService;
	@Autowired
	private ISchBaseUserService schBaseUserInfoService;
	
	/**
	 * 获取个人设置中心基本信息
	 * @param request
	 * @param link
	 * @return
	 */
	@RequestMapping("/getUserInfoAndConfig")
	@ResponseBody
	public Json getUserInfoAndConfig(HttpServletRequest request){
		Json json = new Json();
		try {
			String cookie_sid = CookieUtil.getCookieInfoByKey(request, Constant.USER_ORG_ID);
			String cookie_uid = CookieUtil.getCookieInfoByKey(request, Constant.USER_UID);
			SchoolUserInfo user = schUserInfoService.selectByPrimaryKey(cookie_uid);
			SchoolInfoLink link = schLinkService.selectByPrimaryKey(cookie_sid);
			SchoolInfo info = schInfoService.selectByPrimaryKey(cookie_sid);
			UserLocalauth baseUser = localUserService.selectByPrimaryKey(cookie_uid);
			
			if(user!= null && link != null){
				json.setSuccess(true);
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("user", user);
				map.put("link", link);
				map.put("info", info);
				map.put("baseUser", baseUser);
				
				json.setObj(map);
			}
			
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			return json;
		}
	}
	
	/**
	 * 维护基本信息
	 * @param request
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(HttpServletRequest request, SchoolInfoLink link, 
			String oper, SchoolUserInfo userInfo, String entName,UserLocalauth baseUser){
		Json json = new Json();
		try {
			String cookie_sid = CookieUtil.getCookieInfoByKey(request, Constant.USER_ORG_ID);
			String cookie_uid = CookieUtil.getCookieInfoByKey(request, Constant.USER_UID);
			link.setId(cookie_sid);
			userInfo.setId(cookie_uid);
			int j = 0;
			if(oper.equals("user-info")){
				SchoolInfo info = schInfoService.selectByPrimaryKey(cookie_sid);
//				if(!entName.trim().equals(info.getSchoolName())){
//					info.setSchoolName(entName);
					schInfoService.updateByPrimaryKeySelective(info);
					
					//更改审核状态
//					link.setId(cookie_sid);
//					link.setVerifyStatus(Byte.valueOf(Constant.VALUE_ZERO));
//					schLinkService.updateByPrimaryKeySelective(link);
					json.setMsg(Constant.VALUE_ONE);//更改名称必须刷新页面
//				}
				j = schUserInfoService.updateByPrimaryKeySelective(userInfo);
			}else if(oper.equals("user-phone")){
				baseUser.setId(cookie_uid);
				j = localUserService.updateByPrimaryKeySelective(baseUser);
			}else if(oper.equals("logo")){
				j = schLinkService.updateByPrimaryKeySelective(link);
				json.setObj(link.getSchoolLogo());
			}else if(oper.equals("bg")){
				j = schLinkService.updateByPrimaryKeySelective(link);
				json.setObj(link.getSchoolBg());
			}
			
			if(j == 1){
				json.setSuccess(true);
			}
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			return json;
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
			e.printStackTrace();
			return null;
		}
	}

	
}
