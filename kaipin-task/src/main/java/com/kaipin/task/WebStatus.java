package com.kaipin.task;

public interface WebStatus {
	
	/**
	 * 
	 */

	public static final int WEB_R_OK = 0;// 成功

	public static final int WEB_R_NOT_ROLE = 100;// 用户未选择角色
	
	public static final int WEB_R_ENT_INFO_ERRO = 511;// 企业用户信息不完善
	public static final int WEB_R_STU_INFO_ERRO = 510;// 学生用户信息不完善
	public static final int WEB_R_SCH_INFO_ERRO = 512;// 学校用户信息不完善
	
	public static final int WEB_R_EMIAL_NOT_ACTIV = 550;// 邮箱未激活

	public static final int WEB_R_USER_DISABLED = 10; // 用户被禁用

	public static final int WEB_R_TOKEN_NOT_FOUND = 150; // token信息未找到

	public static final int WEB_R_ERROR = -1;// 用户验证失败
	
	public static final int WEB_R_ERROR_PARAM = -2;// 参数错误
	
	public static final int WEB_R_NO_STU = 999;
	
}
