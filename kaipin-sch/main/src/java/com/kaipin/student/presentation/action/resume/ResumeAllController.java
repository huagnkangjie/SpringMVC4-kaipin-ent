package com.kaipin.student.presentation.action.resume;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kaipin.common.constants.Constant;
import com.kaipin.common.entity.User;
import com.kaipin.common.presentation.action.BaseAction;
import com.kaipin.student.service.resume.IResumeService;


/**
 * 全部简历
 * @author Mr-H
 *
 */
@Controller
@RequestMapping("/company/resumeAll")
public class ResumeAllController extends BaseAction{

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
	public String noReadPage(HttpServletRequest request,String postionId, Model model ,User user) {  
		try {
			
			String orgName = this.getOrgName(request, user);
			this.setSysAttr(model, orgName, null, null);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/stu/resume/count/all";
	}
	
	/**
	 * 初始化list页面
	 * @param request
	 * @param postionId
	 * @return
	 */
	@RequestMapping(value="/allPage")  
	public String allList(HttpServletRequest request,String postionId ,Model model ,User user) {  
		try {
			request.setAttribute(Constant.POSTION_ID, postionId);
			
			String orgName = this.getOrgName(request, user);
			this.setSysAttr(model, orgName, null, null);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/stu/resume/list/all_list";
	}
}
