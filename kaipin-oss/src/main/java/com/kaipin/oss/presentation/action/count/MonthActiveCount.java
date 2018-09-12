package com.kaipin.oss.presentation.action.count;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kaipin.oss.common.pojo.Json;
import com.kaipin.oss.constant.Constant;
import com.kaipin.oss.service.count.ICountBaseService;
import com.kaipin.oss.util.TimeUtil;

/**
 * 月活跃数统计
 * @author Mr-H
 *
 */
@RequestMapping("/monthActive")
@Controller
public class MonthActiveCount {
	
	
	public static String U_TYPE = "userType";
	
	public static String X_VALUE = "xValue";
	public static String Y_VALUE = "counts";
	
	public static String Y_STU_VALUE = "y_stu_value";
	public static String Y_SCH_VALUE = "y_sch_value";
	public static String Y_ENT_VALUE = "y_ent_value";
	
	public static String ACTIVE_MONTH = "/count/active/active_month";

	@Autowired
	private ICountBaseService countBaseService;
	
	/**
	 * 初始化活跃用户统计页面
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/init")
	public String init(HttpServletRequest request, Model model){
		return ACTIVE_MONTH;
	}
	
	
	/**
	 * 月活跃量
	 * @param request
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	@RequestMapping("/monthActive")
	@ResponseBody
	public Json MonthActive(HttpServletRequest request, String startTime, String endTime){
		Json json = new Json();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			//企业
			map.put(U_TYPE, Constant.USER_TYPE_ENT);
			map.put("beforeMonthDay", TimeUtil.getBeforeMounthDay());
			List<Map<String, Object>> listEnt = countBaseService.getMonthActiveCount(map);
			String yEntStr = getStrings(listEnt, Y_VALUE);
			
			//学生
			map.clear();
			map.put(U_TYPE, Constant.USER_TYPE_STU);
			map.put("beforeMonthDay", TimeUtil.getBeforeMounthDay());
			List<Map<String, Object>> listStu = countBaseService.getMonthActiveCount(map);
			String yStuStr = getStrings(listStu, Y_VALUE);
			
			//学校
			map.clear();
			map.put(U_TYPE, Constant.USER_TYPE_SCH);
			map.put("beforeMonthDay", TimeUtil.getBeforeMounthDay());
			List<Map<String, Object>> listSch = countBaseService.getMonthActiveCount(map);
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
