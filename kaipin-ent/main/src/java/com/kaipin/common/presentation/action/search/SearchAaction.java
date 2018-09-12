package com.kaipin.common.presentation.action.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kaipin.common.constants.AppSearchConstant;
import com.kaipin.common.constants.Constant;
import com.kaipin.common.entity.Json;
import com.kaipin.common.entity.User;
import com.kaipin.common.presentation.action.BaseAction;
import com.kaipin.common.util.HttpPostUtil;
import com.kaipin.common.util.HttpRequestUtil;
import com.kaipin.common.util.JsonUtil;
import com.kaipin.common.util.PropUtil;
import com.kaipin.common.util.StringUtil;

/**
 * 全文检索类
 * @author Mr-H
 *
 */

@RequestMapping("/search")
@Controller
public class SearchAaction extends BaseAction{

	/**
	 * 查询结果 list页面
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping("/list")
	public String search(HttpServletRequest request, User user, Model model,
			String searchTxt, String searchType, String page, String pageSize){
		
		String result = getSearchResult(searchTxt, searchType, page, pageSize);
		
		result = result.replace("\\r", "");
		result = result.replace("\\n", "");
		
		model.addAttribute("result", result.replace("class=\\\"highlight\\\"", "class=highlight"));//返回结果
		
		model.addAttribute("searchTxt", searchTxt);
		model.addAttribute("searchType", searchType);
		
		this.setSysAttr(model, searchTxt, null, null);
		
		return "/common/search/list";
	}
	
	/**
	 * 接口查询结果
	 * @param searchTxt
	 * @param searchType
	 * @return
	 */
	public String getSearchResult(String searchTxt, String searchType, String page, String pageSize){
		String result = Constant.VALUE_ZERO;
		if(StringUtil.isNotEmpty(searchType) && StringUtil.isNotEmpty(searchTxt)){
			PropUtil pro = new PropUtil(Constant.PRO_FILE_CONSTANTS);
			String url = pro.getValue(AppSearchConstant.APP_SEARCH_URL);
			
			switch (searchType) {
			
			case "stu":
				url += AppSearchConstant.SEARCH_STU;  break;
			case "sch":
				url += AppSearchConstant.SEARCH_SCH;  break;
			case "ent":
				url += AppSearchConstant.SEARCH_COMPANY;  break;
			case "position":
				url += AppSearchConstant.SEARCH_POSITION;  break;
			case "live":
				url += AppSearchConstant.SEARCH_LIVE;  break;
				
			default:
				break;
			}
			
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("keyword", searchTxt);
			map.put("page", page);
			map.put("count", pageSize);
			
			
			result = HttpPostUtil.doPost(url, map);
		}
		return result;
	}
	
	/**
	 * 查询结果
	 * @param request
	 * @return
	 */
	@RequestMapping("/result")
	@ResponseBody
	public Json searchResult(HttpServletRequest request, String searchTxt ,String searchType, String page, String pageSize){
		Json json = new Json();
		try {
			String result = getSearchResult(searchTxt, searchType, page, pageSize);
			if(StringUtil.isNotEmpty(result)){
				json.setObj(result);
				json.setSuccess(true);
			}
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			return json;
		}
	}
	
	/**
	 * 从搜索列表 -- >跳转详细的页面
	 * 
	 * @param type 查询结果的类型
	 * @param rId  资源id
	 * @return
	 */
	@RequestMapping("/detail")
	public String detail(HttpServletRequest request, User user, String type, String rId){
		try {
			if(StringUtil.isNotEmpty(type)){
				switch (type) {
				
				case "person":       break;
				case "position":       break;
				case "ent":       break;
				case "sch":       break;
				case "vedio":       break;

				default:
					break;
				}
			}
			return "/common/search_detail";
		} catch (Exception e) {
			return "/common/search_detail";
		}
		
	}
}
