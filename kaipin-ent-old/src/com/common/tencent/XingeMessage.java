package com.common.tencent;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.tencent.xinge.Message;
import com.tencent.xinge.MessageIOS;
import com.tencent.xinge.XingeApp;

public class XingeMessage {

	private long stu_android_accessId = 2100166752; 

	private String stu_android_secretKey = "7b385f7f61969ab405eede20e1bb26fd";
	
	private long stu_ios_accessId = 2200166753l; 

	private String stu_ios_secretKey = "caef7daddb58cc91555ff5b7f6c697e2";
	
	
	

	private long ent_android_accessId = 2100166755;

	private String ent_android_secretKey = "132afcc623424c19c61a9d219951f425";
	
	private long ent_ios_accessId = 2200166756l; 

	private String ent_ios_secretKey = "d6918f2277864b37c07d08b33629388b";
	
	

	private int expireTime = 86400;

	private static XingeMessage instance;

	public static XingeMessage instance() {

		if (instance == null) {

			instance = new XingeMessage();
		}
		return instance;
	}

	/**
	 * 学生端所有设备
	 * 
	 * @return
	 */
	public JSONObject pushStuAllDevice(String title, String content,
			Map<String, Object> custom) {
		XingeApp xinge = new XingeApp(stu_android_accessId, stu_android_secretKey);
		Message message = new Message();
		message.setExpireTime(expireTime);
		message.setType(Message.TYPE_NOTIFICATION);
		message.setTitle(title);
		message.setContent(content);
		if (custom != null && custom.size() > 0) {

			message.setCustom(custom);

		}
		JSONObject ret = xinge.pushAllDevice(0, message);
		return (ret);
	}

	public JSONObject pushStuAllDeviceIos(String title,
			Map<String, Object> custom) {
		XingeApp xinge = new XingeApp(stu_ios_accessId, stu_ios_secretKey);
		MessageIOS message = new MessageIOS();
		message.setExpireTime(expireTime);

		message.setAlert(title);
		message.setSound("beep.wav");

		if (custom != null && custom.size() > 0) {

			message.setCustom(custom);

		}
		JSONObject ret = xinge.pushAllDevice(0, message, XingeApp.IOSENV_DEV);
		return (ret);
	}

	public JSONObject pushStuAccountList(String title, String content,
			Map<String, Object> custom, List<String> accountList) {

		Message message = new Message();
		message.setExpireTime(expireTime);
		message.setType(Message.TYPE_NOTIFICATION);
		message.setTitle(title);
		message.setContent(content);

	
		XingeApp xinge = new XingeApp(stu_android_accessId, stu_android_secretKey);
		if (custom != null && custom.size() > 0) {

			message.setCustom(custom);

		}
		JSONObject ret = xinge.pushAccountList(0, accountList, message);
		return (ret);
	}

	public JSONObject pushStuAccountListIos(String title,
			Map<String, Object> custom, List<String> accountList) {

		MessageIOS message = new MessageIOS();
		message.setExpireTime(expireTime);
		message.setAlert(title);
		message.setBadge(1);
		message.setSound("beep.wav");
		if (custom != null && custom.size() > 0) {
			message.setCustom(custom);

		}
		XingeApp xinge = new XingeApp(stu_ios_accessId, stu_ios_secretKey);

		JSONObject ret = xinge.pushAccountList(0, accountList, message,
				XingeApp.IOSENV_DEV);
		System.out.println(" 推送结果 >>>>>  " + ret.get("ret_code"));
		return (ret);

	}

	/************************* 企业推送 *********************************************/

	public JSONObject pushEntAccountList(String title, String content,
			Map<String, Object> custom, List<String> accountList) {

		Message message = new Message();
		message.setExpireTime(expireTime);
		message.setType(Message.TYPE_NOTIFICATION);
		message.setTitle(title);
		message.setContent(content);

		XingeApp xinge = new XingeApp(ent_android_accessId, ent_android_secretKey);

		if (custom != null && custom.size() > 0) {
			message.setCustom(custom);

		}
		JSONObject ret = xinge.pushAccountList(0, accountList, message);

		return (ret);
	}

	public JSONObject pushEntAccountListIos(String title,
			Map<String, Object> custom, List<String> accountList) {

		MessageIOS message = new MessageIOS();
		message.setExpireTime(expireTime);
		message.setAlert(title);
		message.setBadge(1);
		message.setSound("beep.wav");
		if (custom != null && custom.size() > 0) {
			message.setCustom(custom);

		}
		XingeApp xinge = new XingeApp(ent_ios_accessId, ent_ios_secretKey);

		JSONObject ret = xinge.pushAccountList(0, accountList, message,
				XingeApp.IOSENV_DEV);
		return (ret);

	}

	public JSONObject pushEntAllDevice(String title, String content,
			Map<String, Object> custom) {

		Message message = new Message();
		message.setExpireTime(expireTime);
		message.setType(Message.TYPE_NOTIFICATION);
		message.setTitle(title);
		message.setContent(content);
		XingeApp xinge = new XingeApp(ent_android_accessId, ent_android_secretKey);

		if (custom != null && custom.size() > 0) {

			message.setCustom(custom);

		}
		JSONObject ret = xinge.pushAllDevice(0, message);
		return (ret);
	}

	public JSONObject pushEntAllDeviceIos(String title,
			Map<String, Object> custom) {
		XingeApp xinge = new XingeApp(ent_ios_accessId, ent_ios_secretKey);
		MessageIOS message = new MessageIOS();
		message.setExpireTime(expireTime);

		message.setAlert(title);
		message.setSound("beep.wav");

		if (custom != null && custom.size() > 0) {

			message.setCustom(custom);

		}
		JSONObject ret = xinge.pushAllDevice(0, message, XingeApp.IOSENV_DEV);
		return (ret);
	}

	public static void main(String[] args) {

	}

}
