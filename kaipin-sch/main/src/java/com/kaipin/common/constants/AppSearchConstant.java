package com.kaipin.common.constants;

/**
 * 全文检索
 * @author Mr-H
 *
 */
public class AppSearchConstant {

	public static String APP_SEARCH_ID = "id";
	public static String APP_SEARCH_URL = "app.search.url";
	public static String SEARCH_URL = "app.search.url";
	public static String SEARCH_TASK_URL = "/lucene/task/add";//添加本地索引前的作业任务
	
	/**
	 * 全文检索
	 */
	public static String SEARCH_POSITION = "/search/web/result/position"; //职位
	public static String SEARCH_COMPANY = "/search/web/result/ent"; //企业
	public static String SEARCH_LIVE = "/search/web/result/live"; //直播
	public static String SEARCH_SCH = "/search/web/result/sch"; //学校
	public static String SEARCH_STU = "/search/web/result/stu"; //学生
	
	
	/**
	 * app全文检索 URL
	 */
	/**
	 * 新增职位
	 */
	public static String APP_SEARCH_POSITION_CREATE = "/lucene/position/create";
	/**
	 * 更新职位
	 */
	public static String APP_SEARCH_POSITION_UPDATE = "/lucene/position/update";
	/**
	 * 删除职位 resetful /lucene/position/delete/{id}
	 */
	
	public static String APP_SEARCH_POSITION_DELETE = "/lucene/position/delete/";
	
	
	/**
	 * 新增企业
	 */
	public static String APP_SEARCH_COMPANY_CREATE = "/lucene/company/create";
	/**
	 * 更新企业
	 */
	public static String APP_SEARCH_COMPANY_UPDATE = "/lucene/company/update";
	/**
	 * 删除企业
	 */
	public static String APP_SEARCH_COMPANY_DELETE = "/lucene/company/delete/";
	
	
	
	/**
	 * 新增宣讲会
	 */
	public static String APP_SEARCH_LIVE_CREATE = "/lucene/live/create?task_id=";
	/**
	 * 编辑宣讲会
	 */
	public static String APP_SEARCH_LIVE_UPDATE = "/lucene/live/update?task_id=";
	/**
	 * 删除宣讲会
	 */
	public static String APP_SEARCH_LIVE_DELETE = "/lucene/live/delete/";
	
	
	public static String POSITION_NAME = "position_name";
	public static String LOCATION_CODE = "location_code";
	public static String OFFICE_AREA = "office_area";
	public static String INDUSTRY_CODE = "industry_code";
	public static String LAST_UPDATED_TIME = "last_updated_time";
	public static String ENT_NAME = "ent_name";
	public static String SUBJECT = "subject";
	public static String START_TIME = "start_time";
	
}
