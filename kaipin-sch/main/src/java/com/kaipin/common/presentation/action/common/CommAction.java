package com.kaipin.common.presentation.action.common;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kaipin.common.entity.Json;
import com.kaipin.common.entity.User;
import com.kaipin.common.presentation.action.BaseAction;

@RequestMapping("/comm")
@Controller
public class CommAction extends BaseAction{

	/**
	 * 判读当前阅读者是否登录
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping("/isLogin")
	@ResponseBody
	public Json isLogin(HttpServletRequest request, User user){
		Json json = new Json();
		try {
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			return json;
		}
	}
}
