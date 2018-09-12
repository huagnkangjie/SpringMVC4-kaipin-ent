package com.kaipin.enterprise.presentation.action.video;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kaipin.common.entity.User;
import com.kaipin.common.presentation.action.BaseAction;
import com.kaipin.enterprise.model.user.CompanyInfo;
import com.kaipin.enterprise.service.user.ICompanyInfoService;
/**
 * 企业视频综合管理类
 * @author Mr-H
 *
 */
@RequestMapping("/company/video")
@Controller
public class EntVideoController extends BaseAction{
	
	@Autowired
	private ICompanyInfoService companyInfoService;

	/**
	 * 视频列表页
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("")
	public String videoPage(HttpServletRequest request, Model model, User user){
		try {
			String orgId = this.getOrgId(request, user);
			CompanyInfo info = companyInfoService.selectByPrimaryKey(orgId);
			String orgName = info.getEntName();
			
			model.addAttribute("entName", orgName);
			model.addAttribute("index", "index");
			
			this.setSysAttr(model, orgName, null, null);
			return "/ent/video/vedio_list";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
