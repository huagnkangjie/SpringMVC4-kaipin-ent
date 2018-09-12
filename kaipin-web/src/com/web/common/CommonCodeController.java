package com.web.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.pojo.Json;
import com.service.common.ICommonCodeService;
import com.util.LogUtil;
import com.util.StringUtil;

@Controller
@RequestMapping("/commCode")
public class CommonCodeController {
	Logger log = Logger.getLogger(CommonCodeController.class.getName());

	@Autowired
	private ICommonCodeService commonCodeService;
	
	/**
	 * 获取码表list
	 * @param request
	 * @param parentCode
	 * @param tableName
	 * @return
	 */
	@RequestMapping(value="/getCodeList")
	@ResponseBody
	public Json getCodeList(HttpServletRequest request, String parentCode, String tableName){
		Json json = new Json();
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			if(StringUtil.isEmpty(parentCode) && StringUtil.isNotEmpty(parentCode)){
				map.put("sql", "select * from " + tableName);
			}else if(StringUtil.isNotEmpty(parentCode) && StringUtil.isNotEmpty(parentCode)){
				map.put("sql", "select * from comm_" + tableName + " where parent_code = '"+parentCode+"'");
			}
			List<Map<String,Object>> list = commonCodeService.getCodeList(map);
			if(list.size() > 0){
				json.setSuccess(true);
				json.setObj(list);
			}
			return json;
		} catch (Exception e) {
			log.info(LogUtil.getTrace(e));
			e.printStackTrace();
			return json;
		}
	}
	
	/**
	 * 获取学校list
	 * @param param
	 * @return
	 */
	@RequestMapping(value="/getSchList")
	@ResponseBody
	public Json getSchList(String param){
		Json json = new Json();
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			String sql = "select * from school_info where school_name like '%"+param+"%'";
			map.put("sql", sql);
			List<Map<String,Object>> list = commonCodeService.getCodeList(map);
			if(list.size() > 0){
				json.setSuccess(true);
				json.setObj(list);
			}
			return json;
		} catch (Exception e) {
			log.info(LogUtil.getTrace(e));
			e.printStackTrace();
			return json;
		}
	}
	/**
	 * 获取专业list
	 * @param param
	 * @return
	 */
	@RequestMapping(value="/getMajorList")
	@ResponseBody
	public Json getMajorList(String param, String oper){
		Json json = new Json();
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			String sql = "";
			if(oper.equals("name")){
				sql = "select * from comm_major where major_name like '%"+param+"%'";
			}
			map.put("sql", sql);
			List<Map<String,Object>> list = commonCodeService.getCodeList(map);
			if(list.size() > 0){
				json.setSuccess(true);
				json.setObj(list);
			}
			return json;
		} catch (Exception e) {
			log.info(LogUtil.getTrace(e));
			e.printStackTrace();
			return json;
		}
	}
}
