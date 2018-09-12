package com.kaipin.common.constants;


/**
 * <p>定义状态</p>
	发布企业宣讲会预告(100
	发布企业点播(120
	发布企业直播(125
	
	发布企业职位(130
	
	发布院校预告(140
	发布院校点播(150
	发布院校直播(155
	
	学生基本信息(160
	学生联系信息(170
	教育背景(180
	职业背景(190
	求职意向-感兴趣领域(200
	求职意向-意向工作地区(210
	求职意向-意向工作领域(220
	聘用类型(230
	求职信(240
	个人关键词(250
	语言能力(260

 * 
 * @author Mr-H
 *
 */
public class FeedStatus {

	/**
	 * 职位
	 */
	public static String FEED_ENT_POSITION = "130";//职位
	
	public static String FEED_ENT_XJH_YG = "100";//宣预告
	public static String FEED_ENT_XJH_ZB = "125";//直播
	public static String FEED_ENT_XJH_DB = "120";//点播
	
	
	public static String FEED_SCH_XJH_YG = "140";//4宣预告
	public static String FEED_SCH_XJH_ZB = "155";//直播
	public static String FEED_SCH_XJH_DB = "150";//点播
	
	
	public static String FEED_SCH_BASICINFO = "160";//学校基本信息
	public static String FEED_STU_RELATION = "170";//学生联系信息
	public static String FEED_STU_EDU_BACKGROUND = "180";//教育背景
	public static String FEED_STU_JOB_BACKGROUND = "190";//职业背景
	public static String FEED_STU_LIKE_AREA = "200";//感兴趣的领域
	public static String FEED_STU_LIKE_WORK_AREA = "210";//喜欢的工作地区
	public static String FEED_STU_LIKE_JOB_AREA = "220";//喜欢的工作领域
	
	
	public static String FEED_STU_HIRE_TYPE = "230";//聘用类型
	public static String FEED_STU_COVER_LETTER = "240";//求职信
	public static String FEED_STU_KEY_WORDS = "250";//关键字
	public static String FEED_STU_LANGUAGE = "260";//语言能力
	
	
	
	
	
	
	
	public static String T_FEED_POSITION = "position_info";//职位表
	public static String T_FEED_MEETING = "live_info";//宣讲会
	public static String T_FEED_VIDEO = "live_info";//点播
	public static String T_FEED_OFFER = "position_offer";//offer表
	
	
}
