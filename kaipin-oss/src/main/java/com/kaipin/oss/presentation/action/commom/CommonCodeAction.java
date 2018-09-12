package com.kaipin.oss.presentation.action.commom;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kaipin.oss.common.pojo.Json;
import com.kaipin.oss.model.common.Code;
import com.kaipin.oss.model.common.CommLocation;
import com.kaipin.oss.service.common.CommonCodeService;
/**
 * 公用码表业务类
 * @author Mr-H
 *
 */

@Controller
@RequestMapping("/common")
public class CommonCodeAction {

	@Autowired
	private CommonCodeService service;
	
	/**
	 * 根据码表获取名字
	 * @param request
	 * @param code
	 * @return
	 */
	@RequestMapping(value = "/getLocationName", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Json getLocationName(HttpServletRequest   request, String code) {
		Json json = new Json();
		try {
			String name = "";
			String parentName = "";
			if(code.equals("530") || code.equals("538") || code.equals("551") || code.equals("531") ||
					code.equals("561") || code.equals("562") || code.equals("563")){
				switch(code){
					case "530" : name = "北京"; break;
					case "538" : name = "上海"; break;
					case "551" : name = "重庆"; break;
					case "531" : name = "天津"; break;
					case "561" : name = "香港"; break;
					case "562" : name = "澳门"; break;
					case "563" : name = "台湾"; break;
				}
			}else{
				CommLocation common = service.getLocationByCode(code);
				String parentCode = "";
				if(common != null){
					parentCode = common.getParentCode();
					if(!parentCode.equals("489")){
						CommLocation commonParent = service.getLocationByCode(parentCode);
						parentName = commonParent.getLocationName();
					}
					name = common.getLocationName();
				}
			}
			
			name = parentName + " "+ name ;
			if(name.length() > 6){
				name = name.substring(0, 5) + "...";
			}
			json.setObj(name);
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			return json;
		}
	}
	
}
