package com.kaipin.common.constants;

import java.util.HashMap;
import java.util.Map;

import com.kaipin.common.entity.StautsBean;
import com.kaipin.common.util.HttpRequestUtil;
import com.kaipin.common.util.JsonUtil;
import com.kaipin.common.util.PropUtil;
import com.kaipin.common.util.StringUtil;

/**
 * 短信通知
 * @author Mr-H
 *
 */
public class PhoneMsg {

	public static String Phone_Msg_URl = "https://webapi.sms.mob.com/custom/msg";
	public static String APP_KEY = "e87a7d8f209f";
	public static String DEFAULT_ZONE = "86";
	
	public static String TEMPLATECODE = "templateCode";
	public static String STU = "stu";
	public static String ENT = "ent";
	public static String MST = "mst";//面试时间
	public static String MDT = "mdt";//面试修改时间
	public static String DATE = "d";//面试日期
	public static String TIME = "t";//面试时间
	public static String PN = "pn";
	public static String XM = "xm";//已通过第{xm}次面试，请留意第{rm}次面试邀请或职位入职邮件。
	public static String RM = "rm";
	public static String DDV = "ddv";//直播预告名称
	
	
	/**
	 * 简历投递相关
	 */
	public static String RESUME_STATUS_ZREO = "";//投递
	public static String RESUME_STATUS_ZREO_CODE = "";
	
	public static String RESUME_STATUS_ONE = "";//已查看
	public static String RESUME_STATUS_ONE_CODE = "";
	
	/** *通过筛选  */
	public static String RESUME_STATUS_TWO = "stu,mst,ent,pn";
	public static String RESUME_STATUS_TWO_CODE = "13689983";
	//简历筛选已通过提醒，您申请的{ent}{pn}职位，简历已经通过筛选，请留意面试邀请
	
	/** *未通过筛选  */
	public static String RESUME_STATUS_THREE = "stu,mst,ent,pn";
	public static String RESUME_STATUS_THREE_CODE = "13689978";
	//简历筛选未通过提醒，{stu}，您在{mst}申请的{ent}{pn}职位，简历筛选未能通过。可通过开频关注其它职位
	
	/** *邀请面试 */
	public static String RESUME_STATUS_FOUR = "stu,ent,mst,pn";
	public static String RESUME_STATUS_FOUR_CODE = "4255796";
	//视频面试邀请提醒，{stu}，{ent}想约您进行{pn}职位的视频面试，请登录开频查看
	
	/** *面试提醒-企业  */
	public static String RESUME_STATUS_FOUR_TIP_ENT = "ent,mst,stu,pn";
	public static String RESUME_STATUS_FOUR_TIP_ENT_CODE = "6943291";
	//视频面试时间临近提醒，{ent}，您约定在{mst}与{stu}进行{pn}职位的视频面试，将在60分钟后进行
	
	/** *面试提醒-学生  */
	public static String RESUME_STATUS_FOUR_TIP_STU = "stu,mst,stu,pn";
	public static String RESUME_STATUS_FOUR_TIP_STU_CODE = "6942988";
	//视频面试时间临近提醒，{stu}，您约定在{mst}与{ent}{pn}职位进行视频面试，将在60分钟后进行
	
	/** *接受面试 */
	public static String RESUME_STATUS_FIVE = "ent,stu,mst,pn";
	public static String RESUME_STATUS_FIVE_CODE = "4255794";
	//已接受视频面试邀请提醒，{ent}，{stu}已接受在{mst}与您进行{pn}职位的视频面试，请登录开频应用查看与处理
	
	/** *拒绝面试  */
	public static String RESUME_STATUS_SEX = "ent,stu,pn";
	public static String RESUME_STATUS_SEX_CODE = "4255498";
	//已拒绝视频面试邀请提醒，{ent}，{stu}拒绝了{pn}职位的视频面试邀请。原因请登录开频应用查看
	
	/** *通过面试  */
	public static String RESUME_STATUS_SEVEN = "stu,mst,ent,pn,xm,rm";
	public static String RESUME_STATUS_SEVEN_CODE = "6943290";
	//面试已通过提醒，{stu}，您已通过了在{mst}参与了{ent}的{pn}职位的第{xm}次视频面试，请留意{rm}次面试
	
	/** *未通面试  */
	public static String RESUME_STATUS_EIGHT = "stu,mst,ent,pn";
	public static String RESUME_STATUS_EIGHT_CODE = "6943297";
	//面试未通过提醒，您在参与的{ent}{pn}职位的视频面试未通过，请关注其它职位
	
	/** *发送offer  */
	//已发送offer
	public static String RESUME_STATUS_NINE = "stu,ent,pn";
	public static String RESUME_STATUS_NINE_CODE = "4255811";
	//职位入职提醒，{stu}，恭喜您收到了{ent}{pn}职位的入职邮件，上开频看详情
	
	/** *已邀请笔试  */
	public static String RESUME_STATUS_TEN = "stu,ent,pn";
	public static String RESUME_STATUS_TEN_CODE = "4255790";
	//笔试邀请提醒，{stu}，{ent}邀请您进行{pn}职位的网络笔试，请登录开频APP查看与处理
	
	
	/*** 直播
	 * 
	 * 1-预告片,2-直播,3-点播 
	 */
	
	public static String LIVE_YG= "";//预告
	
	/** *正在直播 */
	public static String LIVE_ZB= "stu,ent,ddv";//直播
	public static String LIVE_ZB_CODE= "6943299";
	//直播已开播提醒，{stu}，您订阅的{ent}的{ddv}直播，已经开播了，请登录开频观看
	
	/** *直播提醒  */
	public static String LIVE_ZB_TIP= "stu,ent,ddv";//直播30分钟前提醒
	public static String LIVE_ZB_TIP_CODE= "6943298";
	//直播临近提醒，{stu}，您订阅的{ent}的{ddv}直播，30分钟后开播，请登录开频观看
	
	
	public static String LIVE_DB= "";//点播
	
	/**
	 * 获取拼接的url,以便于发送自定义短信
	 * @param code
	 * @param phone
	 * @param params
	 * @return
	 */
	public static String getUrlValue(String code, String phone, Map<String, Object> params){
		String url = "";
		try {
			url = "appkey=" + APP_KEY + "&zone=" + DEFAULT_ZONE +"&phone=" + phone + "&templateCode=" + code;
			Object[] keys = params.keySet().toArray();
			if(keys.length > 0){
				for (int i = 0; i < keys.length; i++) {
					url = url + "&" + keys[i] + "=" + params.get(keys[i]);
				}
			}
			return url;
		} catch (Exception e) {
			e.printStackTrace();
			return url;
		}
	}
	
	public static String sendPhoneMsgCustom(String url, String param){
		String status = "";
		try {
			String result = HttpRequestUtil.sendPost(url, param);
			status = "0";
			if(StringUtil.isNotEmpty(result)&&!result.equals(Constant.VALUE_NAGETIVE)){
				status = JsonUtil.jsonToObj(result, StautsBean.class).getStatus();
			}
			return status;
		} catch (Exception e) {
			e.printStackTrace();
			return status;
		}
	}
	
	/**
	 * 获取url
	 * @param phone
	 * @return
	 */
	public static String getUrlValue(String phone){
		String url = "";
		try {
			if(StringUtil.isNotEmpty(phone)){
				PropUtil pro = new PropUtil(Constant.PRO_FILE_CONSTANTS);
				String appkey = pro.getValue(Constant.MOB_STU_APPKEY);
				url = "appkey="+ appkey +"&phone="+ phone +"&zone=86";
			}
			return url;
		} catch (Exception e) {
			e.printStackTrace();
			return url;
		}
	}
	/**
	 * 获取url
	 * @param phone
	 * @return
	 */
	public static String getUrl(){
		String url = "";
		try {
			PropUtil pro = new PropUtil(Constant.PRO_FILE_CONSTANTS);
			url = pro.getValue(Constant.MOB_STU_URL_CUSTOM);
			return url;
		} catch (Exception e) {
			e.printStackTrace();
			return url;
		}
	}
	
	/**
	 * 获取拼接的url
	 * @param map
	 * @return
	 */
	public static String concatParam(Map<String, Object> map){
		String value = "";
		try {
			for (String key : map.keySet()) {
				value = value + "&" + key + "=" + map.get(key);
			}
			return value;
		} catch (Exception e) {
			e.printStackTrace();
			return value;
		}
	}
	
	public static void main(String[] args) {
		String url = "https://webapi.sms.mob.com/custom/msg";
		Map<String, Object> params = new HashMap<String, Object> ();
		params.put("ent", "撒打发第三方91");
		params.put("stu", "黄康杰");
		params.put("pn", "1234567891");
		params.put("mst", "2016-10-0812:12");
		String param = getUrlValue("13689978","15283771727", params);
		System.out.println(param);
		sendPhoneMsgCustom(url, param);
//		sendPhoneMsgCustom(url, "appkey=e87a7d8f209f&zone=86&phone=13637879344&templateCode=13689978&stu=林辉强&mst=2016-10-20&pn=333&ent=企业1企业1企...");
	}

	
}
