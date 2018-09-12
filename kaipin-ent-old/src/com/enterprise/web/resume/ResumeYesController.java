package com.enterprise.web.resume;


import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.common.Constant;
import com.enterprise.service.resume.IResumeService;
import com.util.LogUtil;

@Controller
@RequestMapping("resumeYesController")
public class ResumeYesController {
	
	Logger logger = Logger.getLogger(ResumeYesController.class.getName());
	@Autowired
	private IResumeService iResumeService;
	
	/**
	 * 初始化简历管理
	 * 已阅读
	 * @return
	 */
	@RequestMapping(value="/init")  
	public String init(HttpServletRequest request) {  
		try {
			return "/ent/resume/count/read"; 
		} catch (Exception e) {
			logger.info(LogUtil.getTrace(e));;
			e.printStackTrace();
			return null;
		}
		 
	}
	
	@RequestMapping(value="/yesReadPage")  
	public String yesReadPage(HttpServletRequest request,String postionId) {  
		try {
			request.setAttribute(Constant.POSTION_ID, postionId);
			return "/ent/resume/list/read_list"; 
		} catch (Exception e) {
			logger.info(LogUtil.getTrace(e));;
			e.printStackTrace();
			return null;
		}
		
	}

}
