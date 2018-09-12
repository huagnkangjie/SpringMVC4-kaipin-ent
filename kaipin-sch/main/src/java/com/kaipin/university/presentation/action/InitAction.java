package com.kaipin.university.presentation.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kaipin.common.constants.Constant;
import com.kaipin.common.constants.ConstantTables;
import com.kaipin.common.presentation.action.BaseAction;
import com.kaipin.university.model.user.SchoolInfo;
import com.kaipin.university.model.user.SchoolInfoLink;
import com.kaipin.university.model.user.SchoolUserInfo;
import com.kaipin.university.model.user.UserLocalauth;
import com.kaipin.common.service.common.IBaseCodeService;
import com.kaipin.university.service.user.ISchBaseUserService;
import com.kaipin.university.service.user.ISchoolInfoLinkService;
import com.kaipin.university.service.user.ISchoolInfoService;
import com.kaipin.university.service.user.ISchoolUserInfoService;
import com.kaipin.university.service.user.IUserLocalauthService;
import com.kaipin.common.util.CookieUtil;
import com.kaipin.common.util.StringUtil;

@Controller
@RequestMapping("")
public class InitAction extends BaseAction{
	
	@Autowired
	private IUserLocalauthService localUserService;
	@Autowired
	private ISchoolInfoLinkService schLinkService;
	@Autowired
	private ISchoolInfoService schInfoService;
	@Autowired
	private ISchoolUserInfoService schUserInfoService;
	@Autowired
	private ISchBaseUserService schBaseUserInfoService;
	@Autowired
	private IBaseCodeService codeService;
	
	@RequestMapping("/home")
	public String init(HttpServletRequest request, Model model){
		try {
			String cookie_sid = CookieUtil.getCookieInfoByKey(request, Constant.USER_ORG_ID);
			String cookie_uid = CookieUtil.getCookieInfoByKey(request, Constant.USER_UID);
			//获取基本信息 
			SchoolInfo info = schInfoService.selectByPrimaryKey(cookie_sid);
			SchoolInfoLink linkInfo = schLinkService.selectByPrimaryKey(cookie_sid);
			SchoolUserInfo userInfo = schUserInfoService.selectByPrimaryKey(cookie_uid);
			UserLocalauth baseUserInfo = localUserService.selectByPrimaryKey(cookie_uid);
			
			//判断是否含有头像
			String headUrl = Constant.VALUE_ZERO;
			if(linkInfo != null && StringUtil.isNotEmpty(linkInfo.getSchoolLogo())){
				headUrl = linkInfo.getSchoolLogo();
			}
			//判断是否审核通过
			String checkStatus = Constant.VALUE_ZERO;
			if(linkInfo != null && StringUtil.isNotEmpty(linkInfo.getVerifyStatus()+"")){
				checkStatus = linkInfo.getVerifyStatus()+"";
				if(checkStatus.equals(Constant.VALUE_ZERO)){
					//判断是否有资质认证
					List<Map<String,Object>> list = schBaseUserInfoService.selectDoc(cookie_uid);
					if(list.size() > 0){
						checkStatus = Constant.VALUE_THREE;
					}
				}
			}
			
			//院校特色
			String schoolFeature = Constant.VALUE_ZERO;
			if(linkInfo != null && StringUtil.isNotEmpty(linkInfo.getSchoolFeatureId()+"")){
				schoolFeature = getNameByCode(ConstantTables.SCHOOL_FEATURE, 
						ConstantTables.SCHOOL_FEATURE_CODE, linkInfo.getSchoolFeatureId()+"", 
						ConstantTables.SCHOOL_FEATURE_NAME);
				
			}
			
			//院校人数
			String studentCountName = Constant.VALUE_ZERO;
			String studentCount = linkInfo.getStudentCount();
			String studentTotal = linkInfo.getStudentTotal();
			if(StringUtil.isNotEmpty(studentCount)){
				studentCount = "在校学生" + studentCount + "+";
			}
			
			if(StringUtil.isNotEmpty(studentTotal)){
				studentTotal = "历届学生" + studentTotal + "+";
			}
			if(StringUtil.isNotEmpty(studentCount) && StringUtil.isNotEmpty(studentTotal)){
				studentCountName = studentCount + "/" + studentTotal;
			}else if(StringUtil.isNotEmpty(studentCount) && StringUtil.isEmpty(studentTotal)){
				studentCountName = studentCount;
			}else if(StringUtil.isEmpty(studentCount) && StringUtil.isNotEmpty(studentTotal)){
				studentCountName = studentTotal;
			}else{
				studentCountName = Constant.VALUE_ZERO;
			}
			
			model.addAttribute("studentCountName", studentCountName);
			model.addAttribute("schoolFeature", schoolFeature);
			
			model.addAttribute("checkStatus", checkStatus);
			model.addAttribute("headUrl", headUrl);
			model.addAttribute("info", info);
			model.addAttribute("linkInfo", linkInfo);
			model.addAttribute("baseUserInfo", baseUserInfo);
			model.addAttribute("userInfo", userInfo);
			
			model.addAttribute("orgId", cookie_sid);
			model.addAttribute("uid", cookie_uid);
			
			this.setSysAttr(model, info.getSchoolName(), null, null);
			
			return "index";
		} catch (Exception e) {
			e.printStackTrace();
			return "index";
		}
		
		
	}
	
	/**
	 * 页面加载
	 * @param request
	 * @return
	 */
	@RequestMapping("/loading")
	public String loading(HttpServletRequest request, Model model, String oper){
		
		//获取基本信息
		
		//判断是否含有头像.
		
		//判断是否认证
		
		return "/loading";
	}
	
	/**
	 * 关于我们
	 * @param request
	 * @return
	 */
	@RequestMapping("/v/about")
	public String about(HttpServletRequest request, Model model, String oper){
		String cookie_uid = CookieUtil.getCookieInfoByKey(request, Constant.USER_UID);
		String page = "";
		if(StringUtil.isNotEmpty(cookie_uid)){
			page = "/inc/about";
		}else{
			page = "/inc/about_index";
		}
		return page;
	}
	
	public String getNameByCode(String tableName, String columnName, String cvalue, String backColunm){
		String name = "";
		try {
			if(StringUtil.isNotEmpty(cvalue)){
				name = codeService.getNameByCode(tableName, columnName, cvalue, backColunm);
			}
			return name;
		} catch (Exception e) {
			return name;
		}
	}
	
}
