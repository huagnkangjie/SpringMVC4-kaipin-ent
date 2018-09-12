package com.enterprise.web.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.ConstantTables;
import com.common.pojo.Json;
import com.enterprise.service.common.IBaseCodeService;
import com.util.LogUtil;
import com.util.StringUtil;

@Controller
@RequestMapping("baseCode")
public class BaseCodeController {

	Logger log = Logger.getLogger(BaseCodeController.class.getName());
	
	
	@Autowired
	private IBaseCodeService baseService;
	
	/**
	 * 获取对应码表的list集合
	 * @param params 参数以 逗号（,）隔开  分别传入
	 * @return
	 */
	@RequestMapping(value="/getList")  
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
			List<Map<String,Object>> list = baseService.getCodeList(map);
			json.setObj(list);
			if(list.size() > 0) {
				json.setSuccess(true);
			}
			return json;
		} catch (Exception e) {
			log.info(LogUtil.getTrace(e));;
			e.printStackTrace();
			return null;
		}
	}
	
	
	/**
	 * 获取对应码表的map集合
	 * @param params 参数以 逗号（,）隔开  分别传入
	 * @return
	 */
	@RequestMapping(value="/getMap")  
	@ResponseBody
	public Json getMap(HttpServletRequest request, String params) { 
		try {
			Json json = new Json();
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("parameters", "company_type_code = '14000006'");
			map.put("tableName", ConstantTables.COMM_ENT_TYPE);
			Map<String,Object> maps = baseService.getCodeMap(map);
			json.setObj(maps);
//			String test = baseService.getNameByCode(ConstantTables.COMM_ENT_TYPE, "company_type_code", "14000006", "company_type_name");
//			json.setObj(test);
			return json;
		} catch (Exception e) {
			log.info(LogUtil.getTrace(e));;
			e.printStackTrace();
			return null;
		}
	}
	
}
