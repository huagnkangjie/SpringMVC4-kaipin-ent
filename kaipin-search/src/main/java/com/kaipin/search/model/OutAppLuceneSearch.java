package com.kaipin.search.model;

import java.util.ArrayList;
import java.util.List;

/**
 * App搜索结果
 * 
 */
public class OutAppLuceneSearch {
    /**
     * 职位结果
     */
    public static class OutSearchPositionList extends OutPacket {

        public List<SearchPositionResult> data = new ArrayList<>();

        public Page pagination = new Page();

        public static class SearchPositionResult {
            public String name; // 名称
            public String id;// 职位id
            // public String schema_uri = "";// 跳转uri
            public String time;// 职位更新时间
            public String city;// 工作城市
            public String company_name;// 公司名
            public String company_id; // 公司id
            public String logo;// 公司logo
            public String positionCount;// 当前公司所有招聘职位的总数

        }

    }

    /**
     * 公司结果
     * 
     *
     */
    public static class OutSearchCompanyList extends OutPacket {

        public List<SearchCompanyResult1> data = new ArrayList<>();

        public Page pagination = new Page();



        public static class SearchCompanyResult1 {
            public String name;// 公司名称
            public String id;// 公司Id
            // public String schema_uri = ""; // 跳转uri
            public int position_total; // 职位数量
            public String city; // 公司所属地区
            public String logo;// 公司logo
            public String business_domain;// 专注领域


        }
        public static class SearchCompanyResult extends SearchCompanyResult1 {

            public String positionCount;// 企业在招有效个数

        }

    }

    /**
     * 宣讲会结果
     */
    public static class OutSearchLiveList extends OutPacket {

        public List<SearchLiveResult> data = new ArrayList<>();

        public Page pagination = new Page();

        public static class SearchLiveResult {

            public String name;// 名称
            public String id;
            // public String schema_uri = "";// 跳转uri
            public int type; // 1-预告片,2-直播,3-点播
            public int status;// 状态(-1未知,1-直播中,2-关闭 ) 直播用
            public int room_id;

            public String start_time; // 开始时间
            public String company_name; // 公司名
            public String company_id; // 公司id
            public String logo;// 公司logo
            public int verify_status = 0;// 审核
            public String memo;
            public String vedio_url;// 视频播放地址
         //   public String video_url;
            public String cover_image_path;// 封面地址

        }
    }
    
    
    
    
    
    
    public static class OutAppSearchLiveList extends OutPacket {

        public List<AppSearchLiveResult> data = new ArrayList<>();

        public Page pagination = new Page();

        public static class AppSearchLiveResult {

            public String name;// 名称
            public String id;
            // public String schema_uri = "";// 跳转uri
            public int type; // 1-预告片,2-直播,3-点播
            public int status;// 状态(-1未知,1-直播中,2-关闭 ) 直播用
            public int room_id;

            public String start_time; // 开始时间
            public String company_name; // 公司名
            public String company_id; // 公司id
            public String logo;// 公司logo
            public int verify_status = 0;// 审核
            public String memo;
       //     public String vedio_url;// 视频播放地址
         public String video_url;
            public String cover_image_path;// 封面地址

        }
    }
    
    
    
    
    
    
    
    
    

    /**
     * 学生结果
     */
    public static class OutSearchStuList extends OutPacket {

        public List<SearchStuResult> data = new ArrayList<>();

        public Page pagination = new Page();

        public static class SearchStuResult {

            public String id;
            public String name;// 姓
            public String surname;// 姓
            public String miss_surname;// 名字
            public String nick_name;// 昵称
            public String location_code;// 地区
            public String school_code;// 学校
            public String major_code;// 专业
            public String education_code;// 学历
            public String head_url;// 学历
            public String keyword; // 关键字

        }
    }


    /**
     * 学校结果
     */
    public static class OutSearchSchList extends OutPacket {

        public List<SearchSchResult> data = new ArrayList<>();

        public Page pagination = new Page();

        public static class SearchSchResult {

            public String id;
            public String school_id;// 学校名字
            public String school_short_name;// 简称
            public String school_name;// 简称
            public String location_code;// 地区
            public String school_type_id;// 院校办学类型
            public String school_nature_id;// 院校性质
            public String school_feature_id;// 院校特色
            public String school_class_id;// 院校分类
            public String school_logo;// logo
            public String direction;// 研究方向


        }



    }



    // 学生
    public static class OutAppSearchStudentList extends OutPacket {
        public List<AppSearchStudentResult> data = new ArrayList<>();

        public Page pagination = new Page();

        public static class AppSearchStudentResult {

            public String id;

            public String name; // 姓名

            public String location_name; // 地区
            // schema_uri 跳转uri
            public String school_name; // 学校
            public String major_name; // 专业
            public String   education_name;//学历
            public String logo; // Logo
            public int user_type;
   


        }


    }



    // 学校
    public static class OutAppSearchSchoolList extends OutPacket {
        public List<AppSearchSchoolResult> data = new ArrayList<>();

        public Page pagination = new Page();

        public static class AppSearchSchoolResult {

            public String id;

//            public String school_short_name;// 简称
            public String school_name;// 学校
            public String location_name;// 地区
            public String school_type;// 院校办学类型
            public String school_nature;// 院校性质
            public String school_feature;// 院校特色
            public String school_class;// 院校分类
            public String logo;// logo

            public String direction;// 研究方向
            public int verify_status = 0;// 是否审核
            public int user_type;

        }


    }

}
