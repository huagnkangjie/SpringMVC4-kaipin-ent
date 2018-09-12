package com.kaipin.search.entity;

import java.io.Serializable;

public class CompanyInfo implements Serializable {

	public class CompanyInfoTable {
		public static final String ID = "id"; //
		public static final String ENT_NAME = "ent_name"; // 公司/个体全称
		public static final String ENT_SIMPLE_NAME = "ent_simple_name"; // 公司/个体简称

		public static final String INDUSTRY_CODE = "industry_code"; // 所属行业类型
		public static final String POSTAL_CODE = "postal_code"; //
		public static final String EMAIL = "email"; //
		public static final String PHONE = "phone"; // 手机
		public static final String OFFICE_TELPHONE="office_telphone";


		public static final String APPLY_DATE = "apply_date"; // 申请时间
		public static final String APPLY_CODE = "apply_code"; // 企业编号
		public static final String PATH = "path"; // 资质材料
		public static final String TELPHONE = "telphone"; // 手机

		public static final String OFFICE_AREA = "office_area"; // 办公点
		public static final String OFFICE_ADDRESS = "office_address"; // 办公点

		public static final String DETAIL = "detail";// 企业简介

		public static final String WEBSITE = "website";

		public static final String PEOPLE_NUMBER = "people_number";
		public static final String REGEDIT_DATE = "regedit_date";

		public static final String LOGO = "logo";// logo

		public static final String BG_URL = "bg_url";// 背景图

		public static final String STATUS = "status";// 状态 0不通过 1通过
		public static final String CREATE_TIME = "create_time";// 创建时间

		public static final String LAST_UPDATED_TIME = "last_updated_time";// 修改时间

		public static final String LEVEL = "level";// 评级

		public static final String VERIFY_STATUS = "verify_status"; // 审核状态
																	// 0-待审核，1-审核未过,2-已过
		public static final String ENABLE = "enable"; // 状态 0-禁止 1-启用
		public static final String ENT_CODE = "ent_code";// 企业编号
		public static final String BUSINESS_DOMAIN = "business_domain"; // 关注领域
		public static final String COMPANY_TYPE_CODE = "company_type_code";



		public static final String USER_NAME = "user_name";
		public static final String SEX = "sex";

		public static final String POSITION = "position";

		public static final String COMPANY_ID = "company_id";



		// check_phone

		// check_mail 0未验证 1验证 varchar(1) 1 FALSE FALSE FALSE

		public static final String AGE = "age"; // 年龄

		public static final String AREA = "area";// 地区

		public static final String HEAD_URL = "head_url";

	}

	public  static class FllowEntTable {

		public static final String ID = "id"; //

		public static final String USER_ID = "user_id"; // 用户id
		public static final String COMPANY_ID = "company_id";

		public static final String FOLLOW_DATE = "follow_date";

	}

}
