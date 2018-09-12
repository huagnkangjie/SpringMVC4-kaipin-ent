package com.kaipin.oss.presentation.action.count;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kaipin.oss.common.pojo.Json;
import com.kaipin.oss.constant.Constant;
import com.kaipin.oss.service.count.ICountBaseService;


/**
 * 首页提供的统计
 * @author Mr-H
 *
 */
@RequestMapping("/index/count")
@Controller
public class IndexCountAction {
	
	public static String U_TYPE = "userType";
	
	public static String X_VALUE = "xValue";
	public static String Y_VALUE = "counts";
	
	public static String Y_STU_VALUE = "y_stu_value";
	public static String Y_SCH_VALUE = "y_sch_value";
	public static String Y_ENT_VALUE = "y_ent_value";
	
	@Autowired
	private ICountBaseService countBaseService;

	/**
	 * 周
	 * @param request
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	@RequestMapping("/week")
	@ResponseBody
	public Json week(HttpServletRequest request, String startTime, String endTime){
		Json json = new Json();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put(U_TYPE, Constant.USER_TYPE_ENT);
			List<Map<String, Object>> listEnt = countBaseService.getWeekCount(map);
			String yEntStr = getStrings(listEnt, Y_VALUE);
			
			map.clear();
			map.put(U_TYPE, Constant.USER_TYPE_STU);
			List<Map<String, Object>> listStu = countBaseService.getWeekCount(map);
			String yStuStr = getStrings(listStu, Y_VALUE);
			
			map.clear();
			map.put(U_TYPE, Constant.USER_TYPE_SCH);
			List<Map<String, Object>> listSch = countBaseService.getWeekCount(map);
			String ySchStr = getStrings(listSch, Y_VALUE);
			
			String xValStr = getStrings(listEnt, X_VALUE);
			
			map.clear();
			map.put(X_VALUE, xValStr);
			map.put(Y_STU_VALUE, yStuStr);
			map.put(Y_SCH_VALUE, ySchStr);
			map.put(Y_ENT_VALUE, yEntStr);
			
			json.setObj(map);
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			return json;
		}
	}
	/**
	 * 月
	 * @param request
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	@RequestMapping("/month")
	@ResponseBody
	public Json month(HttpServletRequest request, String startTime, String endTime){
		Json json = new Json();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put(U_TYPE, Constant.USER_TYPE_ENT);
			List<Map<String, Object>> listEnt = countBaseService.getMonthCount(map);
			String yEntStr = getStrings(listEnt, Y_VALUE);
			
			map.clear();
			map.put(U_TYPE, Constant.USER_TYPE_STU);
			List<Map<String, Object>> listStu = countBaseService.getMonthCount(map);
			String yStuStr = getStrings(listStu, Y_VALUE);
			
			map.clear();
			map.put(U_TYPE, Constant.USER_TYPE_SCH);
			List<Map<String, Object>> listSch = countBaseService.getMonthCount(map);
			String ySchStr = getStrings(listSch, Y_VALUE);
			
			String xValStr = getStrings(listEnt, X_VALUE);
			
			map.clear();
			map.put(X_VALUE, xValStr);
			map.put(Y_STU_VALUE, yStuStr);
			map.put(Y_SCH_VALUE, ySchStr);
			map.put(Y_ENT_VALUE, yEntStr);
			
			json.setObj(map);
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			return json;
		}
	}
	/**
	 * 季度
	 * @param request
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	@RequestMapping("/quarter")
	@ResponseBody
	public Json quarter(HttpServletRequest request, String startTime, String endTime){
		Json json = new Json();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put(U_TYPE, Constant.USER_TYPE_ENT);
			List<Map<String, Object>> listEnt = countBaseService.getQuarterCount(map);
			String yEntStr = getStrings(listEnt, Y_VALUE);
			
			map.clear();
			map.put(U_TYPE, Constant.USER_TYPE_STU);
			List<Map<String, Object>> listStu = countBaseService.getQuarterCount(map);
			String yStuStr = getStrings(listStu, Y_VALUE);
			
			map.clear();
			map.put(U_TYPE, Constant.USER_TYPE_SCH);
			List<Map<String, Object>> listSch = countBaseService.getQuarterCount(map);
			String ySchStr = getStrings(listSch, Y_VALUE);
			
			String xValStr = getStrings(listEnt, X_VALUE);
			
			map.clear();
			map.put(X_VALUE, xValStr);
			map.put(Y_STU_VALUE, yStuStr);
			map.put(Y_SCH_VALUE, ySchStr);
			map.put(Y_ENT_VALUE, yEntStr);
			
			json.setObj(map);
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			return json;
		}
	}
	/**
	 * 年
	 * @param request
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	@RequestMapping("/year")
	@ResponseBody
	public Json year(HttpServletRequest request, String startTime, String endTime){
		Json json = new Json();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put(U_TYPE, Constant.USER_TYPE_ENT);
			List<Map<String, Object>> listEnt = countBaseService.getYearCount(map);
			String yEntStr = getStrings(listEnt, Y_VALUE);
			
			map.clear();
			map.put(U_TYPE, Constant.USER_TYPE_STU);
			List<Map<String, Object>> listStu = countBaseService.getYearCount(map);
			String yStuStr = getStrings(listStu, Y_VALUE);
			
			map.clear();
			map.put(U_TYPE, Constant.USER_TYPE_SCH);
			List<Map<String, Object>> listSch = countBaseService.getYearCount(map);
			String ySchStr = getStrings(listSch, Y_VALUE);
			
			String xValStr = getStrings(listEnt, X_VALUE);
			
			map.clear();
			map.put(X_VALUE, xValStr);
			map.put(Y_STU_VALUE, yStuStr);
			map.put(Y_SCH_VALUE, ySchStr);
			map.put(Y_ENT_VALUE, yEntStr);
			
			json.setObj(map);
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			return json;
		}
	}
	/**
	 * 时间段
	 * @param request
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	@RequestMapping("/timeToTime")
	@ResponseBody
	public Json timeToTime(HttpServletRequest request, String startTime, String endTime){
		Json json = new Json();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("startTime", startTime);
			map.put("endTime", endTime);
			map.put(U_TYPE, Constant.USER_TYPE_ENT);
			List<Map<String, Object>> listEnt = countBaseService.getTimeToTimeCount(map);
			String yEntStr = getStrings(listEnt, Y_VALUE);
			
			map.put(U_TYPE, Constant.USER_TYPE_STU);
			List<Map<String, Object>> listStu = countBaseService.getTimeToTimeCount(map);
			String yStuStr = getStrings(listStu, Y_VALUE);
			
			map.put(U_TYPE, Constant.USER_TYPE_SCH);
			List<Map<String, Object>> listSch = countBaseService.getTimeToTimeCount(map);
			String ySchStr = getStrings(listSch, Y_VALUE);
			
			String xValStr = getStrings(listEnt, X_VALUE);
			
			map.clear();
			map.put(X_VALUE, xValStr);
			map.put(Y_STU_VALUE, yStuStr);
			map.put(Y_SCH_VALUE, ySchStr);
			map.put(Y_ENT_VALUE, yEntStr);
			
			json.setObj(map);
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			return json;
		}
	}
	
	/**
	 * 获取前端需要数组
	 * @param list
	 * @return
	 */
	public String getStrings(List<Map<String, Object>> list, String key){
		String s = "";
		for(int i = 0; i < list.size(); i++){
			s = s + "," + list.get(i).get(key);
		}
		return s.substring(1, s.length());
	}
}
