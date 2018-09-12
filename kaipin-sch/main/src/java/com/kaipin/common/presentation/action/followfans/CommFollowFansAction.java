package com.kaipin.common.presentation.action.followfans;

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
import com.kaipin.common.util.CookieUtil;
import com.kaipin.common.util.TimeUtil;
import com.kaipin.common.util.UuidUtil;
import com.kaipin.university.service.user.ISchBaseUserService;

/**
 * 公共的粉丝 和  关注操作
 * @author Mr-H
 *
 */
@RequestMapping("/comm/followfans")
@Controller
public class CommFollowFansAction extends BaseAction{
	
	@Autowired
	private ISchBaseUserService schBaseUserInfoService;
	
	/**
	 * 判断该用户是不是自己的关注用户
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping("/isFollowFans")
	@ResponseBody
	public Json isFollowFans(HttpServletRequest request, User user){
		Json json = new Json();
		try {
			String to_uid = super.getOrgId(request, user);
			String from_uid = CookieUtil.getCookieInfoByKey(request, Constant.USER_ORG_ID);
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("from_uid", from_uid);
			map.put("to_uid", to_uid);
			List<Map<String,Object>> list = schBaseUserInfoService.checkIsFollow(map);
			if(list.size() > 0){
				json.setSuccess(true);
			}
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			return json;
		}
	}

}
