package com.kaipin.task;

public interface Constant {
	/** 邮箱正则表达式 */
	public static String emailRegex = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
	/** 电话号码正则表达式 */
	public static String telRegex = "^1[0-9]{10}$";
	
	
	
	public static int  DAY_SECOND=24*60*60;
	
	
	public static int  WEEK_DAY_SECOND=7*DAY_SECOND;
	
    public static final String COLON=":";
    /**
     *web token key 前缀
     */
    public static final String WEB_ACCESS_TOKEN_PREFIX = "web";

    
    public static final String USER_TYPE_S="10";//学生
    public static final String USER_TYPE_C="11";//公司
    public static final String USER_TYPE_U="12";//企业
    
}
