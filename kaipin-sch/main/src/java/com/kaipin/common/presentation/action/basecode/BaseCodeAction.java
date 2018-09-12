package com.kaipin.common.presentation.action.basecode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kaipin.common.entity.Json;
import com.kaipin.common.service.common.IBaseCodeService;
import com.kaipin.common.util.StringUtil;

/**
 * 基本码表
 * @author Mr-H
 *
 */
@RequestMapping("/basecode")
@Controller
public class BaseCodeAction {

	@Autowired
	private IBaseCodeService baseCodeService;
	
	/**
	 * 获取城市列表
	 * @return
	 */
	@RequestMapping("/getlist")
	@ResponseBody
	public Json getBaseCodeList(String locationCode, String type){
		Json json = new Json();
		try {
			if(StringUtil.isNotEmpty(locationCode) && StringUtil.isNotEmpty(type)){
				List<Map<String, Object>> list = new ArrayList<>();
				if(type.equals("contain")){
					list = baseCodeService.getLocationListContainOwn(locationCode);
				}else if(type.equals("except")){
					list = baseCodeService.getLocationListExceptOwn(locationCode);
				}
				json.setSuccess(true);
				json.setObj(list);
			}
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			return json;
		}
	}
	
	
	@RequestMapping(value="/getlists")  
	@ResponseBody
	public Json getList(HttpServletRequest request, String tName, String params ) { 
		try {
			Json json = new Json();
			Map<String,Object> map = new HashMap<String,Object>();
			if(StringUtil.isEmpty(params)){
				map.put("parameters", "1=1");
			}else{
				map.put("parameters", params);
			}
			map.put("tableName", tName);
			List<Map<String,Object>> list = baseCodeService.getCodeList(map);
			json.setObj(list);
			if(list.size() > 0) {
				json.setSuccess(true);
			}
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
