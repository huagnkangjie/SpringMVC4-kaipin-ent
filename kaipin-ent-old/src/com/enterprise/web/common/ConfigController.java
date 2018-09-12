package com.enterprise.web.common;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.pojo.Json;
import com.util.LogUtil;

/**
 * 个人设置
 * 
 * @author Mr-H
 *
 */

@Controller
@RequestMapping("configController")
public class ConfigController {

	Logger log = Logger.getLogger(ConfigController.class.getName());
	
	
	/**
	 * 个人设置页面
	 * @return
	 */
	@RequestMapping(value="/init")  
	public String init() {  
		try {
			return "/ent/common/config";  
		} catch (Exception e) {
			log.info(LogUtil.getTrace(e));;
			e.printStackTrace();
			return null;
		}
	}
}
