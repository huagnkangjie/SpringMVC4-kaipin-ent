package com.enterprise.web.common;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.Constant;
import com.common.page.Page;
import com.common.pojo.Json;
import com.enterprise.model.common.EntUser;
import com.enterprise.service.position.IPostionService;
import com.enterprise.service.resume.IResumeService;
import com.util.CookieUtil;
import com.util.LogUtil;

/**
 * 全局搜索
 * 
 * @author Mr-H
 *
 */
@Controller
@RequestMapping("globalSearchController")
public class GlobalSearchController {

	Logger log = Logger.getLogger(GlobalSearchController.class.getName());
	
	@Autowired
	private IPostionService postionService;
	@Autowired
	private IResumeService resumeService;
	
	/**
	 * 搜索页面
	 * @return
	 */
	@RequestMapping(value="/init")  
	public String init() {  
		try {
			return "/ent/common/search"; 
		} catch (Exception e) {
			log.info(LogUtil.getTrace(e));;
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 输入实时搜索
	 * @param request
	 * @param datas
	 * @return
	 */
	@RequestMapping(value="/search")  
	public String search(HttpServletRequest request, Page page, String datas, String oper ,Model model) { 
		try {
			String cookie_companyId = CookieUtil.getCookieInfoByKey(request, Constant.USER_ORG_ID);
			datas = URLDecoder.decode(datas, "UTF-8");
			HashMap<String,Object> map = new HashMap<String, Object>();
			map.put("companyId", cookie_companyId);
			map.put("name", datas);
			map.put("page_start", (page.getPage()-1) * page.getRows());
			map.put("page_size", page.getRows());
			List<Map<String,Object>> list = null;
			if(oper.equals(Constant.VALUE_ZERO)){//0搜索职位
				list = postionService.search(map);
			}else if(oper.equals(Constant.VALUE_ONE)){//搜索简历
				list = resumeService.search(map);
			}
			model.addAttribute("searchList", list);
			model.addAttribute("searchDatas", datas);
			model.addAttribute("searchCounts", list.size());
			return "/ent/common/search";
		} catch (Exception e) {
			log.info(LogUtil.getTrace(e));;
			e.printStackTrace();
			return null;
		}
	}
}
