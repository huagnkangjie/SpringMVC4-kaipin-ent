package com.common.quartz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.common.Constant;
import com.enterprise.model.common.MsgEntMetting;
import com.enterprise.model.common.MsgUserMetting;
import com.enterprise.service.common.IMsgEntMettingService;
import com.enterprise.service.common.IMsgUserMettingService;
import com.enterprise.service.meeting.IEntMeetingNoticeService;
import com.enterprise.service.resume.IUserInterviewService;
import com.util.TimeUtil;
import com.util.UuidUtil;

/**
 * 信鸽及时推送信息
 * 
 * 1、宣讲会开播前30分钟，5分钟分别提醒
 * 2、面试前30分钟，5分钟分别提醒
 * 
 * 3、消息表录入数据
 * 
 * @author Mr-H
 *
 */
public class XingeQuartz {

	Logger log = Logger.getLogger(XingeQuartz.class.getName());
	
	@Autowired
	private IMsgUserMettingService userMeetService;//学生宣讲会消息
	@Autowired
	private IUserInterviewService userViewService;//学生面试通知
	@Autowired
	private IMsgEntMettingService entMeetService;//企业宣讲会

	
	
	/**
	 * 启动作业
	 */
	public void task(){
		System.out.println("开始时间    ==  " + TimeUtil.getDate());
		doXjhTask();//宣讲会
//		push();
	}
	
	
	/**
	 * 宣讲会作业
	 */
	public void doXjhTask(){
		try {
			HashMap<String,Object> map = new HashMap<String, Object>();
			List<Map<String,Object>> list = userMeetService.msgMeetInsVal(map);
			System.out.println("需要广播  宣讲会   的数据           ===   "+list.size() + "   条");
			if(list.size() > 0){
				List<MsgUserMetting> listVal = new ArrayList<MsgUserMetting>();
				List<MsgEntMetting> listEntVal = new ArrayList<MsgEntMetting>();
				for (int i = 0; i < list.size(); i++) {
					//给企业  宣讲会  表数据插入数据
					MsgEntMetting entMeet = new MsgEntMetting();
					entMeet.setId(UuidUtil.getUUID());
					entMeet.setCreateTime(TimeUtil.getDate());
					entMeet.setTitle(list.get(i).get("theme_name").toString());
					entMeet.setType(Constant.VALUE_ONE);
					entMeet.setUserId(null);
					entMeet.setCompanyId(list.get(i).get("company_id").toString());
					entMeet.setStatus(Integer.valueOf(Constant.VALUE_ZERO));
					entMeet.setContent(list.get(i).get("detail").toString());
					entMeet.setVedioId(list.get(i).get("id").toString());
					listEntVal.add(entMeet);
					
					//给学生  面试  表插入数据
					MsgUserMetting meet = new MsgUserMetting();
					meet.setId(UuidUtil.getUUID());
					meet.setCreateTime(TimeUtil.getDate());
					meet.setTitle(list.get(i).get("theme_name").toString());
					meet.setType(Constant.VALUE_ONE);
					meet.setUserId(null);
					meet.setCompanyId(list.get(i).get("company_id").toString());
					meet.setStatus(Integer.valueOf(Constant.VALUE_ZERO));
					meet.setContent(list.get(i).get("detail").toString());
					meet.setVedioId(list.get(i).get("id").toString());
					listVal.add(meet);
					//推送次消息
					//TODO
				}
				//批量插入数据
				insertMsgList(listEntVal, listVal);
			}
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
		}
	}
	
	/**
	 * 往消息表中录入消息
	 * @return
	 */
	public int insertMsgList(List<MsgEntMetting> listEntVal, List<MsgUserMetting> listVal){
		try {
			entMeetService.insertEntMsgList(listEntVal);
			userMeetService.insertMsgList(listVal);
			return 0;
		} catch (Exception e) {
			log.error(e);
			return -1;
		}
	}
	
	
	/**
	 * 推送消息
	 * @return
	 */
	public int push(){
		try {
			pushAllAnroid();
			pushAllIos();
			return 0;
		} catch (Exception e) {
			log.error(e);
			return -1;
		}
	}
	
	
	/**
	 * 推送全部的安卓设备
	 * @return
	 */
	public int pushAllAnroid(){
		try {
			return 0;
		} catch (Exception e) {
			log.error(e);
			return -1;
		}
	}
	
	/**
	 * 推送全部的ios设备
	 * @return
	 */
	public int pushAllIos(){
		try {
			return 0;
		} catch (Exception e) {
			log.error(e);
			return -1;
		}
	}
	
	
}
