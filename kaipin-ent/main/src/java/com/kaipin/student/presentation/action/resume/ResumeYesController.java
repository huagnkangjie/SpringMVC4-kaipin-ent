package com.kaipin.student.presentation.action.resume;


import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kaipin.common.constants.Constant;
import com.kaipin.common.entity.User;
import com.kaipin.common.presentation.action.BaseAction;
import com.kaipin.common.util.LogUtil;
import com.kaipin.student.service.resume.IResumeService;


@Controller
@RequestMapping("/resumeYes")
public class ResumeYesController extends BaseAction{
	
	Logger logger = Logger.getLogger(ResumeYesController.class.getName());
	@Autowired
	private IResumeService iResumeService;
	
	/**
	 * 初始化简历管理
	 * 已阅读
	 * @return
	 */
	@RequestMapping(value="/init")  
	public String init(HttpServletRequest request ,Model model ,User user) {  
		try {
			
			String orgName = this.getOrgName(request, user);
			this.setSysAttr(model, orgName, null, null);
			
			return "/stu/resume/count/read"; 
		} catch (Exception e) {
			logger.info(LogUtil.getTrace(e));;
			e.printStackTrace();
			return null;
		}
		 
	}
	
	@RequestMapping(value="/yesReadPage")  
	public String yesReadPage(HttpServletRequest request,String postionId ,Model model ,User user) {  
		try {
			request.setAttribute(Constant.POSTION_ID, postionId);
			
			String orgName = this.getOrgName(request, user);
			this.setSysAttr(model, orgName, null, null);
			
			return "/stu/resume/list/read_list"; 
		} catch (Exception e) {
			logger.info(LogUtil.getTrace(e));;
			e.printStackTrace();
			return null;
		}
		
	}

}
