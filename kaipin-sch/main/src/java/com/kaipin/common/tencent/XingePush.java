package com.kaipin.common.tencent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.kaipin.enterprise.model.msg.MsgUserInterview;
import com.kaipin.enterprise.model.position.PositionDelivery;



/**
 * 信鸽推送y
 * @author Mr-H
 *
 */
public class XingePush {

	
	/**
	 * 推送邀请信息
	 * @param 
	 * @return
	 */
	public void doPush(HttpServletRequest request,
			String type, String title, PositionDelivery relation,MsgUserInterview msg){
		try {
			XingeMessage message = new XingeMessage();
			//用户
			List<String> listAcount = new ArrayList<String>();
			listAcount.add(relation.getStuUserId());
			
			//自定义参数
			HashMap<String,Object> custom = new HashMap<String, Object>();
			HashMap<String,Object> mapData = new HashMap<String, Object>();
			
			mapData.put("r_id", msg.getId());
			mapData.put("msg_id", msg.getId());
//			mapData.put("to", new ArrayList<String>());
			mapData.put("title", title);
			mapData.put("hint", "");
//			mapData.put("time", msg.getCreateTime());
			
			custom.put("type", type);
			custom.put("data", mapData);
			
			//推送ios
			message.pushStuAccountListIos(title, custom, listAcount);
			
			//推送android
			message.pushStuAccountList(title, msg.getContent(), custom, listAcount);
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 推送邀请信息
	 * @param Map
	 * @return
	 */
	public void doPushMsg(HttpServletRequest request,Map<String,String> map){
		try {
			XingeMessage message = new XingeMessage();
			//用户
			List<String> listAcount = new ArrayList<String>();
			listAcount.add(map.get("userId"));
			
			//自定义参数
			HashMap<String,Object> custom = new HashMap<String, Object>();
			HashMap<String,Object> mapData = new HashMap<String, Object>();
			
			String title = map.get("title");
			String content = map.get("content");
			
			mapData.put("r_id", map.get("msgId"));
			mapData.put("msg_id", map.get("msgId"));
//			mapData.put("to", new ArrayList<String>());
			mapData.put("title", title);
			mapData.put("hint", map.get("hint"));
//			mapData.put("time", msg.getCreateTime());
			
			custom.put("type", map.get("type"));
			custom.put("data", mapData);
			
			//推送ios
			message.pushStuAccountListIos(title, custom, listAcount);
			
			//推送android
			message.pushStuAccountList(title, content, custom, listAcount);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
