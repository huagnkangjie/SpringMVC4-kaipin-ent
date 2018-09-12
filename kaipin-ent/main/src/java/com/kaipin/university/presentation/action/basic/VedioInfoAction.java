package com.kaipin.university.presentation.action.basic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kaipin.common.constants.AppSearchConstant;
import com.kaipin.common.constants.Constant;
import com.kaipin.common.constants.FeedStatus;
import com.kaipin.common.entity.Json;
import com.kaipin.common.entity.Page;
import com.kaipin.common.entity.StautsBean;
import com.kaipin.common.entity.User;
import com.kaipin.common.presentation.action.BaseAction;
import com.kaipin.common.util.CookieUtil;
import com.kaipin.common.util.HttpPostUtil;
import com.kaipin.common.util.JsonUtil;
import com.kaipin.common.util.LuceneUtil;
import com.kaipin.common.util.PropUtil;
import com.kaipin.common.util.StringUtil;
import com.kaipin.common.util.TimeUtil;
import com.kaipin.common.util.UuidUtil;
import com.kaipin.enterprise.model.user.CompanyInfo;
import com.kaipin.enterprise.service.user.ICompanyInfoService;
import com.kaipin.university.model.feed.Feed;
import com.kaipin.university.model.user.SchoolInfo;
import com.kaipin.university.model.user.SchoolInfoLink;
import com.kaipin.university.model.user.UserLocalauth;
import com.kaipin.university.model.vedio.LiveInfo;
import com.kaipin.university.repository.dao.user.ISchoolInfoDao;
import com.kaipin.university.service.feed.IFeedBaseService;
import com.kaipin.university.service.feed.IFeedService;
import com.kaipin.university.service.user.ISchoolInfoLinkService;
import com.kaipin.university.service.user.ISchoolInfoService;
import com.kaipin.university.service.user.IUserLocalauthService;
import com.kaipin.university.service.vedio.ILiveInfoService;

/**
 * 学校发布视频
 * @author Mr-H
 *
 */
@RequestMapping("/vedio")
@Controller
public class VedioInfoAction extends BaseAction{

	@Autowired
	private ILiveInfoService liveInfoService;
	@Autowired
	private IFeedService feedService;
	@Autowired
	private IUserLocalauthService localUserService;
	@Autowired
	private ISchoolInfoLinkService schLinkService;
	@Autowired
	private ISchoolInfoService schInfoService;
	@Autowired
	private IFeedBaseService feedBaseService;
	@Autowired
	private ICompanyInfoService companyInfoService;
	
	
	/**
	 * 视频列表页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value="")
	public String init(HttpServletRequest request, User user, Model model){
		String orgName = "学校名字";
		UserLocalauth localUser = 
				localUserService.selectByPrimaryKey(super.getUid(request, user));
		if(localUser != null){
			SchoolInfo info = schInfoService.selectByPrimaryKey(localUser.getOrganizationId());
			if(info != null){
				orgName = info.getSchoolName();
			}
		}
		model.addAttribute("orgName", orgName);
		
		this.setSysAttr(model, orgName + " - 视频", null, null);
		
		return "/basic/vedio_list";
	}
	
}
