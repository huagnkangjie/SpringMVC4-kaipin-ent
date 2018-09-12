package com.kaipin.oss.presentation.action.platform.module;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kaipin.oss.common.page.GenericDefaultPage;
import com.kaipin.oss.common.page.IGenericPage;
import com.kaipin.oss.common.pojo.Json;
import com.kaipin.oss.common.web.CookieUtils;
import com.kaipin.oss.constant.Constant;
import com.kaipin.oss.model.company.CompanyInfo;
import com.kaipin.oss.model.company.CompanyUserInfo;
import com.kaipin.oss.model.company.count.CompanyInfoCount;
import com.kaipin.oss.model.platform.PlatformModule;
import com.kaipin.oss.presentation.action.Constants;
import com.kaipin.oss.service.platform.PlatformModuleService;
import com.kaipin.oss.util.StringUtil;

/**
 * 菜单管理
 * @author Mr-H
 *
 */
@Controller
@RequestMapping("/platform/module")
public class ModuleAction {
	
	public static String LIST = "platform/list";//菜单管理页面
	public static String ADD = "platform/add";//菜单管理页面
	
	public static String icon = "list";//父集标志
	
	@Autowired
	private PlatformModuleService moduleService;
	
	/**
	 * 菜单管理页面
	 * @return
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest   request, Map<String, Object> map, 
			Integer pageNo , ModelMap model, String parentId) {

		try {
			
			Map<String,Object> param = new HashMap<String,Object>();
			if(StringUtil.isEmpty(parentId)){
				parentId = Constant.VALUE_ONE;
			}
				param.put("parentId", parentId);
			
			int pageSize=	CookieUtils
					.getPageSize(request);
			
			IGenericPage<PlatformModule> pages = 
					moduleService.getListByParentId(param, GenericDefaultPage.cpn(pageNo), pageSize);

			model.addAttribute(Constants.PAGE, pages);
			model.addAttribute("parentId", parentId);
			
			return LIST;
		} catch (Exception e) {
			e.printStackTrace();
			return LIST;
		}
	}
	
	/**
	 * 添加页面
	 * @param parentId
	 * @return
	 */
	@RequestMapping("/addpage")
	public String addPage(String parentId, Model model){
		try {
			if(StringUtil.isEmpty(parentId)){
				parentId = Constant.VALUE_ONE;
			}
			model.addAttribute("parentId", parentId);
			return ADD;
		} catch (Exception e) {
			e.printStackTrace();
			return ADD;
		}
	}
	
	
	
	/**
	 * 添加
	 * @param parentId
	 * @return
	 */
	@RequestMapping("/add")
	public void add(HttpServletRequest request, HttpServletResponse response,
			String parentId, PlatformModule function){
		try {
			boolean flag = moduleService.insertModule(function);
			String url = request.getContextPath();
			response.sendRedirect(url + "/platform/module/list?parentId="+parentId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 删除
	 * @param parentId
	 * @return
	 */
	@RequestMapping("/del")
	public Json del(String parentId){
		Json json = new Json();
		try {
			delModule(parentId);
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			return json;
		}
	}
	
	/**
	 * 递归删除
	 * @param parentId
	 */
	public void delModule(String parentId){
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("parentId", parentId);
		IGenericPage<PlatformModule> pages = 
				moduleService.getListByParentId(param, GenericDefaultPage.cpn(0), 100);
		if(pages.getThisPageElements().size() > 0){
			for (PlatformModule module : pages.getThisPageElements()) {
				if(icon.equals(module.getIcon())){
					delModule(String.valueOf(module.getId()));
					moduleService.delModule(String.valueOf(module.getId()));
				}else{
					moduleService.delModule(String.valueOf(module.getId()));
				}
			}
		}
	}
}
