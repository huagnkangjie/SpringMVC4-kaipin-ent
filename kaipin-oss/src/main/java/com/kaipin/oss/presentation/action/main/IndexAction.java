package com.kaipin.oss.presentation.action.main;


import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kaipin.oss.constant.Constant;
import com.kaipin.oss.log.Log;

import com.kaipin.oss.model.platform.PlatformUser;
import com.kaipin.oss.security.SecurityConstants;
import com.kaipin.oss.security.SecurityUtils;
import com.kaipin.oss.security.ShiroUser;
import com.kaipin.oss.service.company.CompanyInfoBaseService;
import com.kaipin.oss.service.count.ICountBaseService;
import com.kaipin.oss.service.platform.PlatformModuleService;
import com.kaipin.oss.util.TimeUtil;

 

@Controller
@RequestMapping("/management")
public class IndexAction {
	private final static Logger LOGGER = LoggerFactory.getLogger(IndexAction.class); 
	
	private static final String INDEX = "index/index";
	
	private static final String MAIN = "index/main";
	
	private static final String COUNT = "count";
	
	@Autowired
	private PlatformModuleService  platformModuleService;
	@Autowired
	private CompanyInfoBaseService companyBaseService;
	@Autowired
	private ICountBaseService countBaseService;
	
/**
 * annotation
  @RequiresUser  验证用户是否被记忆 1（subject.isAuthenticated() 结果为true）  subject.isRemembered()结果为true 

 * 
 * @param request
 * @param map
 * @param model
 * @return
 */

 @RequiresUser 
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public String index(ServletRequest request, 	Map<String, Object> map,ModelMap model) {
		ShiroUser shiroUser = SecurityUtils.getShiroUser();
		
	 
		model.addAttribute(SecurityConstants.LOGIN_USER, shiroUser.getLoginName());

		model.addAttribute(SecurityConstants.MENU_MODULE, platformModuleService.getPermissionMenu());

		 
		return INDEX;
	}
//@RequiresPermissions("Index:main")
//@RequiresPermissions("Index:main")
 //@RequiresUser
	@Log(message="向用户分配了的角色 main")
	@RequestMapping(value="/main", method=RequestMethod.GET)
 
	public String main(ServletRequest request, Map<String, Object> map, ModelMap model) {
		
		String companyCount = "0";
		String studentCount = "0";
		String schoolCount = "0";
		List<Map<String, Object>> list = countBaseService.getUserCount(map);
		String category_id = "";
		String count = "";
		for (int i = 0; i < list.size(); i++) {
			category_id = list.get(i).get("category_id")+"";
			count = list.get(i).get("count")+"";
			if(category_id.equals("10")){
				studentCount = count;
			}else if(category_id.equals("11")){
				companyCount = count;
			}else if(category_id.equals("12")){
				schoolCount = count;
			}
		}
		
//		//企业总注册量
//		Object companyCount = companyBaseService.getRegeditAllCompanyCount().get(0).get(COUNT);
//		//学生总注册量
//		Object studentCount = companyBaseService.getRegeditAllStudentCount().get(0).get(COUNT);
		//职位总注册量
		Object postionCount = companyBaseService.getPositionCount().get(0).get(COUNT);
		//简历总注册量
		Object resumeCount = companyBaseService.getResumeCount().get(0).get(COUNT);
		//被投递的职位数
		Object positionDeliveryCount =companyBaseService.getPositionDeliveryCount().get(0).get(COUNT);
		//投递职位的简历数
		Object resumeDeliveryCount =companyBaseService.getResumeDeliveryCount().get(0).get(COUNT);
		
		
		//获取最近一个月的统计对比图
		List<Map<String,Object>> listEnt = companyBaseService.getRegeditCompanyCount(null);
		List<Map<String,Object>> listStu = companyBaseService.getRegeditStudentCount(null);
		List<Map<String,Object>> listSch = companyBaseService.getRegeditSchCount(null);
		
		
		String xStr = "";
		String xArray[] = TimeUtil.getDaysBetweenDate(TimeUtil.getBeforeMounthDay(), TimeUtil.getDates());
		String yArray = "";
		String yStuArray = "";
		String ySchArray = "";
		String monthsStu = "";
		String monthsEnt = "";
		String monthsSch = "";
		
		for (int i = 0; i < xArray.length; i++) {
			//遍历企业
			String entCount = "0";
			if(listEnt.size() > 0){
				for (int j = 0; j < listEnt.size(); j++) {
					monthsEnt = String.valueOf(listEnt.get(j).get("months"));
					if(xArray[i].equals(monthsEnt)){
						entCount =  String.valueOf(listEnt.get(j).get("count"));
						break;
					}
				}
				yArray = yArray + "," + entCount;
			}
			//遍历学生
			String stuCount = "0";
			if(listStu.size() > 0){
				for (int j = 0; j < listStu.size(); j++) {
					monthsStu = String.valueOf(listStu.get(j).get("months"));
					if(xArray[i].equals(monthsStu)){
						stuCount =  String.valueOf(listStu.get(j).get("count"));
						break;
					}
				}
				yStuArray = yStuArray + "," + stuCount;
			}
			//遍历学校
			String schCount = "0";
			if(listStu.size() > 0){
				for (int j = 0; j < listSch.size(); j++) {
					monthsSch = String.valueOf(listSch.get(j).get("months"));
					if(xArray[i].equals(monthsSch)){
						schCount =  String.valueOf(listSch.get(j).get("count"));
						break;
					}
				}
				ySchArray = ySchArray + "," + schCount;
			}
			
			//生成X轴坐标
			xStr = xStr +","+ xArray[i];
		}
		
		
		
		model.addAttribute("xArray", xStr.substring(1));
		model.addAttribute("yArray", yArray.substring(1));
		model.addAttribute("yStuArray", yStuArray.substring(1));
		model.addAttribute("ySchArray", ySchArray.substring(1));
		
		model.addAttribute("companyCount", companyCount);
		model.addAttribute("studentCount", studentCount);
		model.addAttribute("schoolCount", schoolCount);
		model.addAttribute("postionCount", postionCount);
		model.addAttribute("resumeCount", resumeCount);
		model.addAttribute("positionDeliveryCount", positionDeliveryCount);
		model.addAttribute("resumeDeliveryCount", resumeDeliveryCount);
		LOGGER.error ("访问 main");
		return MAIN;
		
	}
	

	
	
}
