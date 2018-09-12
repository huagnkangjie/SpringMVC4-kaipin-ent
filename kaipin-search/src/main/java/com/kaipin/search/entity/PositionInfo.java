package com.kaipin.search.entity;

import org.apache.ibatis.type.Alias;

@Alias("PositionInfo")
public class PositionInfo {
	
	
	 
	private String id;

	private String positionName;

	private String industryCode;

	private String locationCode;

	private Long createTime;

	private Long lastUpdatedTime;

 

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName == null ? null : positionName.trim();
	}

	public String getIndustryCode() {
		return industryCode;
	}

	public void setIndustryCode(String industryCode) {
		this.industryCode = industryCode == null ? null : industryCode.trim();
	}

	public String getLocationCode() {
		return locationCode;
	}

	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode == null ? null : locationCode.trim();
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Long getLastUpdatedTime() {
		return lastUpdatedTime;
	}

	public void setLastUpdatedTime(Long lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
	}
	
	public static final String  POSITION_TOTAL = "position_total";//
	public class PositionInfoTable {

		public static final String ID = "id";//

		public static final String COMPANY_ID = "company_id"; // 企业id

		public static final String POSITION_NAME = "position_name"; // 职位名称

		public static final String JOB_TYPE_CODE = "job_type_code";// 职位类型
		public static final String EDUCATION_CODE = "education_code";// 学历code

		public static final String INDUSTRY_CODE = "industry_code"; // 行业

		public static final String START_TIME = "start_time";// 开始时间

		public static final String END_TIME = "end_time";// 招聘结束时间
		public static final String LOCATION_CODE = "location_code"; // 工作区域

		public static final String AGE_START = "age_start";// 年龄要求-范围开始

		public static final String AGE_END = "age_end"; // 年龄要求-范围截止

		public static final String SEX_CODE = "sex_code";// 性别要求

		public static final String POSTION_RESPONSIBILITY = "position_responsibility"; // 职位责任

		public static final String POSITION_DETAIL = "position_detail"; // 职位摘要

		public static final String POSITION_REQUIREMENTS = "position_requirements"; // 职位要求
		public static final String OTHER_INFO = "other_info"; // 其他信息
		public static final String COMPANY_INTRODUCTION = "company_introduction"; // 公司
																					// 简介

		public static final String SALARY_TYPE = "salary_type"; // 0-面议,1-月薪,2-年薪

		public static final String SALARY_START = "salary_start";// 薪酬起始
		public static final String SALARY_END = "salary_end";// 薪酬截止

		public static final String WORK_EXPERIENCE_CODE = "work_experience_code";// 工作经验

		public static final String NUMBERS = "numbers"; // 招聘人数

		public static final String SUPERIOR = "superior";// 汇报

		public static final String DEPARTMENT = "department"; // 所属部门
		public static final String DEPARTMENT_NUMBERS = "department_numbers"; // 部门人数
		public static final String SALARY_YEAR = "salary_year"; // 年薪酬福利

		public static final String MAJOR_REQUEST = "major_request"; // 专业要求

		public static final String YEAR_HOLIDAY = "year_holiday"; // 年假福利

		public static final String SALARY_FORMS = "salary_forms"; // 薪资构成
		public static final String SOCIAL_SECURITY = "social_security";// 社保

		public static final String LIVE = "live"; // 居住福利
		public static final String CALL_TRAFFIC = "call_traffic"; // 通讯交通
		public static final String STATUS = "status"; // 状态（0招聘 1暂停 2过期 3删除）
		public static final String CREATE_TIME = "create_time"; // 创建时间
		public static final String CREATE_USER_ID = "create_user_id"; // 创建人
		public static final String LAST_UPDATED_TIME = "last_updated_time"; // 修改时间
		public static final String UPDATED_USER_ID = "updated_user_id"; // 修改人

		public static final String SCALE_MIN = "scale_min"; // 企业规模范围min
		public static final String SCALE_MAX = "scale_max";// 企业规模范围max

	}
 
	public class PostionRelationTable {

		public static final String STATUS = "status";

		public static final String RELATION_ID = "relation_id";
		public static final String CREATE_TIME = "create_time";

		public static final String FACE_CLASS = "face_class";

	}

	public class PostionCollectTable {

		public static final String POSITION_ID = "position_id";

		public static final String CREATE_TIME = "create_time";

		public static final String STU_USER_ID = "stu_user_id";

		public static final String ID = "id";

		public static final String COUNTS = "counts";
		
		
		

		public static final String  IS_DELIVERY= "is_delivery";
		
		public static final String  IS_FAVOURITE= "is_favourite";
		
		
		

	}

	public class PostionRecordTable {
		public static final String POSITION_ID = "position_id";

		public static final String CREATE_TIME = "create_time";

		public static final String USER_ID = "user_id";

		public static final String ID = "id";

		public static final String RESUME_ID = "resume_id";

	}
	
	
 

}