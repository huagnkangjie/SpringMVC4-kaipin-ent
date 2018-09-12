package com.kaipin.university.presentation.action.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kaipin.common.constants.Constant;
import com.kaipin.common.entity.Json;
import com.kaipin.common.entity.StautsBean;
import com.kaipin.university.service.user.ISchBaseUserService;
import com.kaipin.common.util.HttpRequestUtil;
import com.kaipin.common.util.JsonUtil;
import com.kaipin.common.util.PropUtil;
import com.kaipin.common.util.StringUtil;

/**
 * 手机操作类
 * @author Mr-H
 *
 */
@Controller
@RequestMapping("/phone")
public class PhoneAction {
	
	@Autowired
	private ISchBaseUserService baseUserService;

	
	/**
	 * 手机号唯一性验证
	 * @return
	 */
	@RequestMapping("/validata")
	@ResponseBody
	public Json validata(String phone){
		Json json = new Json();
		try {
			List<Map<String,Object>> list = baseUserService.validataPhone(phone);
			if(list.size() > 0){
				json.setSuccess(false);
			}else {
				json.setSuccess(true);
			}
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			return json;
		}
	}
	
	/**
	 * 发送短信请求
	 * @param phoneCode
	 * @return
	 */
	@RequestMapping(value="/sendPhoneMsg")
	@ResponseBody
	public Json sendPhoneMsg(HttpServletRequest request, String phone, String code){
		try {
			Json json = new Json();
			PropUtil pro = new PropUtil(Constant.PRO_FILE_CONSTANTS);
			String appkey = pro.getValue(Constant.MOB_STU_APPKEY);
			String url = "";
			String param = "appkey="+ appkey +"&phone="+ phone +"&zone=86";
			
			if(StringUtil.isNotEmpty(code)){
				param = param + "&code=" + code;
				url = pro.getValue(Constant.MOB_STU_URL_CHECK);
			}else{
				url = pro.getValue(Constant.MOB_STU_URL_SEND);
			}
			String result = Constant.VALUE_NAGETIVE;
			try {
				result = HttpRequestUtil.sendPost(url, param);
			} catch (Exception e) {
				result = Constant.VALUE_NAGETIVE;
			}
			
			String status = "0";
			if(StringUtil.isNotEmpty(result)&&!result.equals(Constant.VALUE_NAGETIVE)){
				status = JsonUtil.jsonToObj(result, StautsBean.class).getStatus();
			}
			String msg = "";
			if(status.equals("200")){
				msg = "发送成功";
			}else if(status.equals("458")){
				msg = "手机号码超出当天发送短信的限额";
			}else if(status.equals("463")){
				msg = "手机号码超出当天发送短信的限额";
			}else if(status.equals("467")){
				msg = "请求校验验证码频繁（5分钟校验超过3次）";
			}else if(status.equals("468")){
				msg = "验证码错误";
			}else if(status.equals("477")){
				msg = "亲，手机验证次数过多，请明天再试";
			}else if(status.equals("478")){
				msg = "一个手机号一天只能获取5次";
			}else {
				msg = "错误代码： " + status + "， 请联系管理员";
			}
			json.setMsg(msg);
			json.setObj(status);
			json.setSuccess(true);
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
