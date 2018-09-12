package com.common.web.regedit;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.pojo.Json;
import com.enterprise.model.user.CompanyInfo;
import com.enterprise.model.user.CompanyUserInfo;
import com.util.TimeUtil;

/**
 * 学生端--注册
 * @author Mr-H
 *
 */
@Controller
@RequestMapping("stuRegedit")
public class StuRegeditController {

	Logger log = Logger.getLogger(StuRegeditController.class.getName());
	
	
	/**
	 * 注册
	 * @return
	 */
	@RequestMapping({"/register"})
	@ResponseBody
	public Json register(HttpSession session){
		try {
			Json json = new Json();
			return json;
		} catch (Exception e) {
			log.info(e);
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 提交资质认证
	 * @param session
	 * @return
	 */
	@RequestMapping({"/certificateSave"})
	public String certificateSave(HttpSession session){
		try {
			if (session != null) {
				session.invalidate();
			}
			return "/ent/regedit/ent_certificate_check";
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			return null;
		}
	}
}
