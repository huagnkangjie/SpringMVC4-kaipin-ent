package com.kaipin.task.presentation.web.action.live;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.kaipin.task.constant.Constant;
import com.kaipin.task.constant.PhoneMsg;
import com.kaipin.task.entity.StautsBean;
import com.kaipin.task.service.web.common.ICommonService;
import com.kaipin.task.service.web.live.ILiveTipService;
import com.kaipin.task.util.HttpRequestUtil;
import com.kaipin.task.util.JsonUtil;
import com.kaipin.task.util.PropUtil;
import com.kaipin.task.util.StringUtil;
import com.kaipin.task.util.TimeUtil;
import com.kaipin.task.util.UuidUtil;
import com.sun.org.apache.regexp.internal.REUtil;

/**
 * 直播提醒作业
 * @author Mr-H
 *
 */
public class LiveTipTaskAction {
	
	@Autowired
	private ICommonService commonService;
	@Autowired
	private ILiveTipService liveTipService;
	/**
	 * 执行任务调度
	 */
	public void excute(){
		doTask();
	}
	
	
	public void doTask(){
		System.out.println("查询列表  == ");
		//1、获取需要推送的人员
		final List<Map<String,Object>> list = liveTipService.getLiveTipList();
		
		System.out.println("查询列表  == "+list.size());
		
		//2、发送短信
		new Thread(new sendPhoneMsg(list, getUrl())).start();
		//3、发送邮件
		//new Thread(new sendEmail(list)).start();
		
	}
	
	
	public String getUrlValue(Map<String,Object> map){
		
//		String userPhone = "13637879344";
		String userPhone = map.get("phone")+"";
		String stuUserName = getUserName(map.get("user_name")+"");
		String orgType = map.get("live_org_type")+"";
		String orgName = StringUtil.getSubName(getOrgName(orgType, map.get("live_org_id")+""));
		String subject = StringUtil.getSubName(map.get("subject")+"");
		
		//map.clear();
		map.put(PhoneMsg.STU, stuUserName);
		map.put(PhoneMsg.ENT, orgName);
		map.put(PhoneMsg.DDV, subject);
		
		String param = PhoneMsg.getUrlValue(PhoneMsg.LIVE_ZB_TIP_CODE, userPhone, map);
		
		return param;
	}
	
	public String getOrgName(String orgType, String orgId){
		String orgName = "";
		switch (orgType) {
		case "10": 	break;
		case "11": 
			orgName = StringUtil.getSubName((commonService.getCompanyInfo(orgId).get("ent_name")) + "");	
			break;
		case "12": 	
			orgName = StringUtil.getSubName((commonService.getSchoolInfo(orgId).get("school_name")) + "");	
			break;
		default: break;
		}
		
		return orgName;
	}
	
	
	public String getUrl(){
		PropUtil pro = new PropUtil(Constant.PRO_FILE_CONSTANTS);
		return pro.getValue(Constant.MOB_STU_URL_CUSTOM);
	}
	
	/**
	 * 发送短信
	 * @author Mr-H
	 *
	 */
	class sendPhoneMsg implements Runnable{

		private List<Map<String,Object>> list = new ArrayList<>();
		private String url ;
		
		public sendPhoneMsg(List<Map<String,Object>> list, String url){
			this.list = list;
			this.url = url;
		}
		
		@Override
		public void run() {
			try {
				for (Map<String, Object> map : list) {
					
					System.out.println("map 参数 == " + map );
					
					String result = HttpRequestUtil.sendPost(this.url, getUrlValue(map));
					String status = JsonUtil.jsonToObj(result, StautsBean.class).getStatus();
					
					System.out.println("发送状态 == " + status);
					
					//添加发送记录
					if(status.equals("200")){
						Map<String,Object> param = new HashMap<>();
						param.put("id", UuidUtil.getUUID());
						param.put("phone", map.get("phone"));
						param.put("video_id", map.get("video_id"));
						param.put("stu_user_id", map.get("uid"));
						param.put("organization_id", map.get("live_org_id"));
						param.put("status", status);
						param.put("create_time", TimeUtil.currentTimeMillis());
						
						liveTipService.insertPhoneTip(param);
					}
					
					
				}
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	/**
	 * 发送邮件
	 * @author Mr-H
	 *
	 */
	class sendEmail implements Runnable{

		List<Map<String,Object>> list = new ArrayList<>();
		
		public sendEmail(List<Map<String,Object>> list){
			this.list = list;
		}
		
		@Override
		public void run() {
			for (int i = 0; i < list.size(); i++) {
				System.out.println("执行发送邮件2   " + list.get(i));
				//3、发送成功的记录日志
				//insertTipList(map);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	
	public String getUserName(String userName){
		String name = "亲";
		try {
			if(StringUtil.isNotEmpty(userName)){
				name = userName;
			}
			return name;
		} catch (Exception e) {
			return name;
		}
	}
}
