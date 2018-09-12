package com.kaipin.enterprise.presentation.action.position;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kaipin.common.constants.Constant;
import com.kaipin.common.entity.Json;
import com.kaipin.common.util.CookieUtil;
import com.kaipin.common.util.StringUtil;
import com.kaipin.common.util.TimeUtil;
import com.kaipin.enterprise.service.position.IPositionService;

/**
 * 职位统计
 * @author Mr-H
 *
 */
@Controller
@RequestMapping("/postionCount")
public class EntPostionCountController {
	
	Logger logger = Logger.getLogger(EntPostionCountController.class.getName());
	
	@Autowired
	private IPositionService postionservice;
	
	/**
	 * 统计
	 * 正在招聘
	 * 展厅招聘
	 * 过期招聘
	 * @param request
	 * @param oper
	 * @param ids
	 * @return
	 */
	@RequestMapping(value="/countPostion")
	@ResponseBody
	public Json countPostion (HttpServletRequest request, String oper){
		try {
			Json json = new Json();
			String cookie_companyId = CookieUtil.getCookieInfoByKey(request, Constant.USER_ORG_ID);
			String valueZero = "0",valueTwo = "0",valueOne = "0";
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("companyId", cookie_companyId);
			String arry[] = {"0","0","0"};
			HashMap<String, Object> mapValue = new HashMap<String, Object>();
			List<Map<String,Object>> list = postionservice.getCount(map);
			if(list.size() > 0){
				for (int i = 0; i < list.size(); i++) {
					String value = String.valueOf(list.get(i).get("counts"));
					String status = String.valueOf(list.get(i).get("status"));
					if(StringUtil.isNotEmpty(status)){
						if(status.equals(Constant.VALUE_ZERO)){
							arry[i] = value;
							valueZero = value;
						}
						if(status.equals(Constant.VALUE_ONE)){
							valueOne = value;
							arry[i] = value;
						}
						if(status.equals(Constant.VALUE_TWO)){
							valueTwo = value;
							arry[i] = value;
						}
					}
					
				}
				json.setSuccess(true);
			}
			mapValue.put("zero",valueZero);
			mapValue.put("one",valueOne);
			mapValue.put("two",valueTwo);
			json.setObj(mapValue);
			return json;
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 根据时间统计
	 * @param request
	 * @param oper
	 * @return
	 */
	@RequestMapping(value="/countPostionByEndtime")
	@ResponseBody
	public Json countPostionByEndtime (HttpServletRequest request, String oper){
		try {
			Json json = new Json();
			String cookie_companyId = CookieUtil.getCookieInfoByKey(request, Constant.USER_ORG_ID);
			String valueZero = "0",valueTwo = "0";
			HashMap<String, Object> map = new HashMap<String, Object>();
			
			HashMap<String, Object> mapValue = new HashMap<String, Object>();
			map.put("companyId", cookie_companyId);
			map.put("status", "0");
			map.put("today", TimeUtil.currentTimeMillis());
			List<Map<String,Object>> listZreo = postionservice.countPostionByEndtime(map);
			if(listZreo.size() > 0){
				valueZero = String.valueOf(listZreo.get(0).get(Constant.COUNT));
			}
			map.clear();
			map.put("companyId", cookie_companyId);
			map.put("status", "2");
			map.put("today", TimeUtil.currentTimeMillis());
			List<Map<String,Object>> listTwo = postionservice.countPostionByEndtime(map);
			if(listTwo.size() > 0){
				valueTwo = String.valueOf(listTwo.get(0).get(Constant.COUNT));
			}
			mapValue.put("zero",valueZero);
			mapValue.put("one",Constant.VALUE_ZERO);
			mapValue.put("two",valueTwo);
			json.setObj(mapValue);
			json.setSuccess(true);
			return json;
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
			return null;
		}
	}

}
