package com.kaipin.university.presentation.action.home;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kaipin.common.constants.Constant;
import com.kaipin.common.entity.Json;
import com.kaipin.common.entity.User;
import com.kaipin.common.presentation.action.BaseAction;
import com.kaipin.university.service.user.ISchBaseUserService;
import com.kaipin.university.service.user.ISchoolInfoLinkService;
import com.kaipin.university.service.user.ISchoolInfoService;
import com.kaipin.university.service.user.ISchoolUserInfoService;
import com.kaipin.university.service.user.IUserLocalauthService;
import com.kaipin.common.util.CookieUtil;
import com.kaipin.common.util.StringUtil;

/**
 * 高校主页
 * @author Mr-H
 *
 */
@Controller
@RequestMapping("/home")
public class HomeAction extends BaseAction{
	
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
	 * 获取粉丝和关注数
	 * @return
	 */
	@RequestMapping("/getFollowAndFans")
	@ResponseBody
	public Json getFollowAndFans(HttpServletRequest request, User user){
		Json json = new Json();
		Object floowCount = "0", fansCount = "0";
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			String cookie_sid = super.getOrgId(request, user);
			String cookie_uid = CookieUtil.getCookieInfoByKey(request, Constant.USER_UID);
			
			if(StringUtil.isNotEmpty(cookie_sid)){
				map.put("from_uid", cookie_sid);
				List<Map<String,Object>> list = schBaseUserInfoService.getFollowAndFans(map);
				if(list.size() > 0){
					floowCount = list.get(0).get(Constant.COUNT);//关注总数
				}
				map.clear();
				map.put("to_uid", cookie_sid);
				List<Map<String,Object>> list2 = schBaseUserInfoService.getFollowAndFans(map);
				if(list2.size() > 0){
					fansCount = list2.get(0).get(Constant.COUNT);//粉丝总数
				}
			}
			
			map.clear();
			map.put("floowCount", floowCount);
			map.put("fansCount", fansCount);
			json.setObj(map);
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			return json;
		}
	}
}
