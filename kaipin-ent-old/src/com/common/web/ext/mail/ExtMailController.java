package com.common.web.ext.mail;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.tomcat.jni.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.Constant;
import com.common.pojo.Json;
import com.common.web.regedit.RegesterController;
import com.enterprise.model.user.UserLocalauth;
import com.enterprise.service.user.IUserLocalauthService;
import com.util.LogUtil;
import com.util.SendHtmlMailUtil;
import com.util.StringUtil;

/**
 * 扩展邮箱接口
 * 
 * 以接口的方式对方开放
 * @author Mr-H
 *
 */
@Controller
@RequestMapping("/extMail")
public class ExtMailController {

	Logger log = Logger.getLogger(ExtMailController.class.getName());
	
	@Autowired
	private IUserLocalauthService localUserService;
	
	/**
	 * 根据uid发送邮件
	 * @param request
	 * @return
	 */
	@RequestMapping("/sendEmailByUid")
	@ResponseBody
	public ResponseEntity<Object> sendEmailByUid(HttpServletRequest request, String uid){
		Json json = new Json();
		try {
			if(StringUtil.isEmpty(uid)){
				json.setObj(Constant.VALUE_NAGETIVE);
			}else{
				UserLocalauth user = 
								localUserService.selectByPrimaryKey(uid);
				if(user == null){
					json.setObj(Constant.VALUE_NAGETIVE);
					return new ResponseEntity<Object>(json, HttpStatus.OK);
				}
				SendHtmlMailUtil.setIndex(Constant.VALUE_ZERO);//获取邮件服务from 的索引			
				while(true){
					int flags = SendHtmlMailUtil.sendCheakMail(uid, user.getEmail());
					if(flags == 550){
						SendHtmlMailUtil.setIndex("add");//获取邮件服务from 的索引+1
					}else if(flags == 200){
						json.setSuccess(true);
						json.setObj(Constant.VALUE_ZERO);
						break;
					}else if(flags != 550 ){
						break;
					}
				}
			}
			return new ResponseEntity<Object>(json, HttpStatus.OK);
		} catch (Exception e) {
			log.info(LogUtil.getTrace(e));
			e.printStackTrace();
			return new ResponseEntity<Object>(json, HttpStatus.OK);
		}
	}
}
