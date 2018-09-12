package com.kaipin;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kaipin.common.entity.Json;
import com.kaipin.common.entity.User;
import com.kaipin.common.presentation.action.BaseAction;

/**
 * 公共的粉丝 和  关注操作
 * @author Mr-H
 *
 */
@RequestMapping("/demo")
@Controller
public class demo extends BaseAction{
	
	@RequestMapping("/demo")
	@ResponseBody
	public Json demo1(HttpServletRequest request, User user){
		Json json = new Json();
		try {
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			return json;
		}
	}
	
	@RequestMapping("/getWebsite")
	public String demo2(HttpServletRequest request, User user, Model model){
		try {
			return "";
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

}
