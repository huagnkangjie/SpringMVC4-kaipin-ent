package com.kaipin.search.entity;

public class LiveInfo {

	public class LiveInfoTable {
		public static final String ID = "id";//
		public static final String COMPANY_ID = "company_id";// 企业id
		public static final String ORG_ID = "organization_id";// 组织id

		public static final String ROOM_ID = "room_id";

		public static final String STATUS = "status"; // 直播状态
														// -1未知,0-未直播,1-直播中,2-直播停止
		public static final String START_TIME = "strat_time"; // 开始时间/拍摄时间
		public static final String END_TIME = "end_time";// 结束时间
		public static final String SUBJECT = "subject"; // 主题
		public static final String TYPE = "type"; // 类型 1-预告片,2-直播,3-点播
													// tinyint(2) 2
													// FALSE FALSE FALSE
		public static final String CHANGE_TYPE = "change_type"; // 改变类型 如:12,123
		public static final String COVER_IMAGE_PATH = "cover_image_path";// 封面图片地址
		public static final String ENABLE = "enable"; // 是否禁用 0-启用,1-禁用
		public static final String IS_DEL = "is_del"; // 是否删除

		public static final String CREATE_TIME = "create_time";// time创建时间
		public static final String MEMO = "memo"; // 备注

	}

	public class LiveShareTable {
		public static final String ID = "id"; //
		public static final String VIDEO_ID = "video_id";//
		public static final String CREATE_TIME = "create_time";

		public static final String STU_USER_ID = "stu_user_id"; // 用户id
		public static final String PLATFORM = "platform";
	}

	public class LivePraiseTable {

		public static final String ID = "id"; //
		public static final String VIDEO_ID = "video_id";//
		public static final String CREATE_TIME = "create_time";

		public static final String STU_USER_ID = "stu_user_id";// 用户id
	}

	public class LiveCommentTable {
		public static final String ID = "id"; //
		public static final String VIDEO_ID = "video_id";//
		public static final String CREATE_TIME = "create_time";

		public static final String STU_USER_ID = "stu_user_id";// 用户id
		public static final String CONTENT = "content";

	}

	public class LiveFollowTable {

		public static final String ID = "id"; //

		public static final String USER_ID = "user_id"; //

		public static final String VIDEO_ID = "video_id"; //
		public static final String FOLLOW_USER_ID = "follow_user_id"; //
		
		public static final String FOLLOW_DATE = "follow_date"; //

	}
	public class  LiveStatTable{
		public static final String WATCH_COUNT  ="watch_count";
		public static final String  COMMENT_COUNT="comment_count";
		public static final String LIKE_COUNT= "like_count";
		public static final String  	SHARE_COUNT="share_count";

	}
	
	
	

	public class LiveVideoInfoTable {

		public static final String VIDEO_ID = "video_id";// 视频id
		public static final String VIDEO_URL = "video_url";// 播放地址
	}

	
	public class LiveWatchUserTable{
		public static final String ID = "id"; //
		public static final String VIDEO_ID = "video_id";// 视频id
		public static final String STU_USER_ID = "stu_user_id";// 用户id
		public static final String CREATE_TIME = "create_time"; //	进入时间	 
		public static final String STATUS="status";//	状态(0-在线,1-退出） 

	}

	
	
	
	
	
	
	
	
	
	
	public class LiveStat {
		public static final String WATCH_COUNT = "watch_count";
		public static final String COMMENT_COUNT = "comment_count";
		public static final String LIKE_COUNT = "like_count";
		public static final String SHARE_COUNT = "share_count";

	}

	public class EntLiveUser {

		public static final String VIDEO_ID = "video_id";// 视频id

		public static final String STATUS = "status";
		public static final String USER_ID = "user_id";

		public static final String CREATE_TIME = "create_time"; // 进入时间

	}

}
