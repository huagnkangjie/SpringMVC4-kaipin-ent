package com.kaipin.oss.presentation.action.count;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kaipin.oss.common.page.GenericDefaultPage;
import com.kaipin.oss.common.page.IGenericPage;
import com.kaipin.oss.common.web.CookieUtils;
import com.kaipin.oss.model.company.CompanyInfo;
import com.kaipin.oss.model.company.CompanyUserInfo;
import com.kaipin.oss.model.company.count.CompanyInfoCount;
import com.kaipin.oss.model.count.CountBase;
import com.kaipin.oss.presentation.action.Constants;
import com.kaipin.oss.service.count.ICountBaseService;
import com.kaipin.oss.util.StringUtil;

/**
 * 职位投递情况
 * 学生投递详情
 * @author Mr-H
 *
 */
@Controller
@RequestMapping("/count/delivery/position/")
public class DeliveryPositionCountAction {
	
	private static final String LIST = "/count/delivery/list";//职位投递详细列表
	
	@Autowired
	private ICountBaseService countBaseService;

	
	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public String list(HttpServletRequest   request, Map<String, Object> map, 
			Integer pageNo , ModelMap model, String entName, String userName,
			String startTime, String endTime) {

		try {
			
			Map<String,Object> param = new HashMap<String,Object>();
			
			if(StringUtil.isNotEmpty(entName)){
				param.put("entName", entName.trim());
			}
			
			if(StringUtil.isNotEmpty(userName)){
				param.put("userName", userName.trim());
			}
			
			if(StringUtil.isEmpty(startTime)){
				startTime = "";
			}
			if(StringUtil.isEmpty(endTime)){
				endTime = "";
			}
			
			param.put("startTime", startTime);
			param.put("endTime", endTime);
			
			int pageSize=	CookieUtils
					.getPageSize(request);
			
			IGenericPage<CountBase> pages = 
					countBaseService.getDeliveryPositionList(param, GenericDefaultPage.cpn(pageNo),
					pageSize);

			model.addAttribute(Constants.PAGE, pages);
			model.addAttribute("entName", entName);
			model.addAttribute("userName", userName);
			model.addAttribute("startTime", startTime);
			model.addAttribute("endTime", endTime);
			return LIST;
		} catch (Exception e) {
			e.printStackTrace();
			return LIST;
		}
		
	}
}
