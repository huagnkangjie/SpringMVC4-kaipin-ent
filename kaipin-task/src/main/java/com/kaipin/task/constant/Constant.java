package com.kaipin.task.constant;
/**
 * 常量类
 * @author Mr-H
 *
 */
public class Constant {
	/**
	 * 常用状态
	 * 
	 * 简历流程 	0未读 		1已读  	2通过  	3不通过    
	 * 			4邀请		5接受		6拒绝		7通过
	 * 			8不通过	9offer
	 * 简历        		0有效		1无效
	 * 性别       		0女                   1 男
	 * 是否毕业 	0否                   1是   		2尚未
	 * 是否结婚		0已婚               1未婚 
	 */
	
	public static String USER_TOKEN = "token";
	
	public static String USER_TYPE_ENT = "11";//企业
	public static String USER_TYPE_STU = "10";//学生
	public static String USER_TYPE_SCH = "12";//学校
	
	public static String USER_UID = "uid";
	public static String USER_TYPE = "u_type";
	public static String USER_ORG_ID = "group_id";
	
	public static String TOKEN_ACCESS_KEY = "h2wmABdfM7i3K80sso";
	
	
	
	
	
	public static String VALUE_NAGETIVE  = "-1";
	public static String VALUE_ZERO = "0";
	public static String VALUE_ONE = "1";
	public static String VALUE_TWO = "2";
	public static String VALUE_THREE = "3";
	public static String VALUE_FOUR = "4";
	public static String VALUE_FIVE = "5";
	public static String VALUE_SIX = "6";
	public static String VALUE_SEVEN = "7";
	public static String VALUE_EIGHT = "8";
	public static String VALUE_NINE = "9";
	public static String VALUE_TEN = "10";
	
	public static String COUNT = "counts";
	public static String CODE = "code";
	
	public static int OPER_MINUS_ZERO = -1;
	public static int OPER_ZERO = 0;
	public static int OPER_ONE = 1;
	public static int OPER_TWO = 2;
	public static int OPER_THTEE = 3;
	
	/**
	 * 常量配置
	 */
	public static String PRO_FILE_CONSTANTS = "constants.properties";
	
	/**
	 * mob 
	 */
	public static String MOB_STU_APPKEY = "mob.stu.appkey";
	public static String MOB_STU_URL_SEND = "mob.stu.url.send";
	public static String MOB_STU_URL_CHECK = "mob.stu.url.check";
	public static String MOB_STU_URL_CUSTOM = "mob.stu.url.custom";
	
	/**
	 * 文件上传配置文件
	 */
	public static String PRO_FILE_PATH = "filePath.properties";
	public static String PRO_FTP_USRNAME = "ftp.username";
	public static String PRO_FTP_PASSWORD = "ftp.password";
	public static String PRO_FTP_IP = "ftp.ip";
	public static String PRO_FTP_PORT = "ftp.port";
	public static String PRO_FTP_FILE_PATH = "ftp.filePath";
	
	/**
	 * 学生头像
	 */
	public static String STU_HEAD_URL = "stu.headurl";
	
	
	
	public static String IMAGES = "images";
	public static String VEDIO = "vedio";
	
	/**
	 * 用于上传文件进度条
	 */
	public static String SESSION_ID = "sessionId";
	public static String PERCENT = "percent";
	public static String HASLOAD = "hasLoad";
	
	/**
	 * 发送offer提示
	 */
	public static String TIP_MAIL = "tip_mail";
	public static String TIP_PUSH = "tip_push";
	
	/**
	 * 消息频道定义
	 */
	public static final String CHANNEL_MSG_INTERVIEW = "msgInterView";

	/**
	 * 登录用户
	 */
	public static String USER = "user";
	public static String USER_ENT = "ent";
	public static String USER_STU = "stu";
	public static String LOGO = "logo";
	public static String COMPANY_ID = "companyId";
	/**
	 * 简历有效
	 */
	public static String RESUME_STATUS_YES = "0";
	/**
	 * 简历无效
	 */
	public static String RESUME_STATUS_NO = "1";
	
	/**
	 * 未阅读
	 */
	public static String NO_READ_COUNT = "NO_READ_COUNT";
	public static String NO_READ = "0";
	
	/**
	 * 已阅读
	 */
	public static String YES_READ_COUNT = "YES_READ_COUNT";
	public static String YES_READ = "1";
	
	/**
	 * 不通过
	 */
	public static String NO_PASS_COUNT = "NO_PASS_COUNT";
	public static String NO_PASS = "3";
	
	/**
	 * 已通过
	 */
	public static String YES_PASS_COUNT = "YES_PASS_COUNT";
	public static String YES_PASS = "2";
	
	/**
	 * 全部
	 */
	public static String ALL_COUNT = "ALL_COUNT";
	public static String ALL = "all";
	
	/**
	 * 职位id
	 */
	public static String POSTION_ID = "POSTION_ID";
	/**
	 * 简历基本信息
	 */
	public static String RESUME_INFO = "RESUME_INFO";
	
	/**
	 * 邮箱索引
	 */
	public static String MAIL_INDEX = "mail_index";
	/**
	 * QQ 邮件服务器
	 */
	public static String MAIL_HOST_QQ = "smtp.qq.com";
	/**
	 * sina 邮件服务器
	 */
	public static String MAIL_HOST_SINA = "smtp.sina.com";
	/**
	 * 邮件服务器
	 */
	public static String MAIL_HOST = "mail_host";
	/**
	 * 邮件服务器端口
	 */
	public static String MAIL_HOST_PORT = "mail_port";
	/**
	 * 邮件from
	 */
	public static String MAIL_FROM = "mail_username";
	/**
	 * 邮件password
	 */
	public static String MAIL_PASSWORD = "mail_password";
	/**
	 * 邮件to
	 */
	public static String MAIL_TO = "mail_to";
	/**
	 * 邮件subject
	 */
	public static String MAIL_SUBJECT = "mail_subject";
	/**
	 * 邮件html
	 */
	public static String MAIL_HTML = "mail_html";
	/**
	 * 邮件nick 昵称
	 */
	public static String MAIL_NICK = "mail_nick";
	/**
	 * 拉米公司
	 * 邮箱服务器,企业注册的时候发送邮箱验证
	 */
	public static String MAIL_SMTP_HOST = "mail.smtp.host";
	public static String MAIL_SMTP_PORT = "mail.smtp.port";
	public static String MAIL_SMTP_FROM = "mail.from";
	public static String MAIL_SMTP_PASSWORD = "mail.password";
	public static String MAIL_WEBSITE = "mail.website";
	
	public static  String TEMP_DIR = "temp";
	public static  String TEMP_ZOOM_DIR = "temp_zoom";
	
	
}
