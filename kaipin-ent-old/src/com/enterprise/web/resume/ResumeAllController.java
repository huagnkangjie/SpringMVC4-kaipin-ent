package com.enterprise.web.resume;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.common.Constant;
import com.enterprise.pojo.CountHomeBean;
import com.enterprise.service.resume.IResumeService;
import com.util.LogUtil;

/**
 * 全部简历
 * @author Mr-H
 *
 */
@Controller
@RequestMapping("resumeAllController")
public class ResumeAllController {

	Logger logger = Logger.getLogger(ResumeController.class.getName());
	@Autowired
	private IResumeService iResumeService;
	
	/**
	 * 初始化统计页面
	 * @param request
	 * @param postionId
	 * @return
	 */
	@RequestMapping(value="/init")  
	public String noReadPage(HttpServletRequest request,String postionId) {  
		try {
		} catch (Exception e) {
			logger.info(LogUtil.getTrace(e));;
			e.printStackTrace();
		}
		return "/ent/resume/count/all";
	}
	
	/**
	 * 初始化list页面
	 * @param request
	 * @param postionId
	 * @return
	 */
	@RequestMapping(value="/allPage")  
	public String allList(HttpServletRequest request,String postionId) {  
		try {
			request.setAttribute(Constant.POSTION_ID, postionId);
		} catch (Exception e) {
			logger.info(LogUtil.getTrace(e));;
			e.printStackTrace();
		}
		return "/ent/resume/list/all_list";
	}
}
