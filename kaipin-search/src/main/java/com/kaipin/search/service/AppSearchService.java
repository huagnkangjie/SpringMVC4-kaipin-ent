package com.kaipin.search.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.apache.lucene.search.TopFieldCollector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.kaipin.search.Constants;
import com.kaipin.search.constant.ConstantTables;
import com.kaipin.search.constant.Lucene;
import com.kaipin.search.constant.UserType;
import com.kaipin.search.core.dimension.Company;
import com.kaipin.search.core.dimension.Live;
import com.kaipin.search.core.dimension.Position;
import com.kaipin.search.core.dimension.SchInfo;
import com.kaipin.search.core.dimension.StuUser;
import com.kaipin.search.core.index.IndexHolder;
import com.kaipin.search.core.index.SearchHelper;
import com.kaipin.search.core.query.QueryFactory;
import com.kaipin.search.core.query.WebBuildQueryFactory;
import com.kaipin.search.entity.CommEducation.CommEducationTable;
import com.kaipin.search.entity.CommLocation.CommLocationTable;
import com.kaipin.search.entity.CommMajor.CommMajorTable;
import com.kaipin.search.entity.CompanyInfo.CompanyInfoTable;
import com.kaipin.search.entity.LiveInfo.LiveInfoTable;
import com.kaipin.search.entity.LiveInfo.LiveVideoInfoTable;
import com.kaipin.search.entity.PositionInfo;
import com.kaipin.search.entity.SchoolClass;
import com.kaipin.search.entity.SchoolClass.SchoolClassTable;
import com.kaipin.search.entity.PositionInfo.PositionInfoTable;
import com.kaipin.search.entity.SchInfo.SchInfoTable;
import com.kaipin.search.entity.SchoolFeature.SchoolFeatureTable;
import com.kaipin.search.entity.StuUser.StuUserTable;
import com.kaipin.search.model.OutAppLuceneSearch.OutAppSearchLiveList;
import com.kaipin.search.model.OutAppLuceneSearch.OutAppSearchLiveList.AppSearchLiveResult;
import com.kaipin.search.model.OutAppLuceneSearch.OutAppSearchSchoolList;
import com.kaipin.search.model.OutAppLuceneSearch.OutAppSearchSchoolList.AppSearchSchoolResult;
import com.kaipin.search.model.OutAppLuceneSearch.OutAppSearchStudentList;
import com.kaipin.search.model.OutAppLuceneSearch.OutSearchCompanyList;
import com.kaipin.search.model.OutAppLuceneSearch.OutSearchLiveList;
import com.kaipin.search.model.OutAppLuceneSearch.OutSearchLiveList.SearchLiveResult;
import com.kaipin.search.model.OutAppLuceneSearch.OutSearchPositionList;
import com.kaipin.search.model.OutAppLuceneSearch.OutSearchPositionList.SearchPositionResult;
import com.kaipin.search.model.OutAppLuceneSearch.OutSearchSchList;
import com.kaipin.search.model.OutAppLuceneSearch.OutSearchSchList.SearchSchResult;
import com.kaipin.search.model.OutAppLuceneSearch.OutSearchStuList;
import com.kaipin.search.model.OutAppLuceneSearch.OutSearchStuList.SearchStuResult;
import com.kaipin.search.model.OutAppSearchCount;
import com.kaipin.search.model.OutAppSearchCount2;
import com.kaipin.search.model.BaseAppSearchCount.SearchCount;
import com.kaipin.search.model.BaseAppSearchCount.SearchCountRecommend;
import com.kaipin.search.model.OutAppLuceneSearch;
import com.kaipin.search.repository.dao.CommLocationDao;
import com.kaipin.search.repository.dao.IndexQueryDao;
import com.kaipin.search.service.impl.CompanyIndexRepairServiceImpl;
import com.kaipin.search.service.impl.LiveIndexRepairServiceImpl;
import com.kaipin.search.service.impl.PositionIndexRepairServiceImpl;
import com.kaipin.search.util.DateFormatUtils;
import com.kaipin.search.util.StringUtil;

/***
 * *app搜索服务 业务功能
 * 
 * 
 ***/
@Component
public class AppSearchService {

    public final static String KEYWORLD = "keyword";

    public final static String LOCATION = "location";

    public final static String INDUSTRY = "industry";

    @Autowired
    @Qualifier("positionInfoDao")
    private IndexQueryDao positionInfoDao;

    @Autowired
    @Qualifier("liveInfoDao")
    private IndexQueryDao liveInfoDao;// 视频

    @Autowired
    @Qualifier("companyInfoDao")
    private IndexQueryDao companyInfoDao; // 公司

    @Autowired
    private CommLocationDao commLocationDao;

    @Autowired
    @Qualifier("stuUserBuildDao")
    private IndexQueryDao stuUserBuildDao; // 学生用户


    @Autowired
    @Qualifier("schInfoDao")
    private IndexQueryDao schInfoDao; // 学校


    @Autowired
    private ICommonCodeService commCodeService;// 公用码表

    /**
     * 搜索统计
     * 
     * @param param
     * @return
     */
    public Object searchCount(Object param) {
        Map<String, Object> map = (Map) param;

        int pageNo = 1, pageSize = 3;

        String keyword = MapUtils.getString(map, KEYWORLD, "");

        OutAppSearchCount2 out = new OutAppSearchCount2();

        out.position = searchPositionCount(keyword, pageNo, pageSize);

        out.live = searchLiveCount(keyword, pageNo, pageSize);

        out.company = searchCompanyCount(keyword, pageNo, pageSize);

        out.student = searchStudentCount(keyword, pageNo, pageSize);

        out.school = searchSchoolCount(keyword, pageNo, pageSize);

        return out;
    }

    private Map<String, Object> buildSearchCondition(Map<String, Object> map) {

        String keyword = MapUtils.getString(map, KEYWORLD, "");

        String location = MapUtils.getString(map, LOCATION, "");

        String industry = MapUtils.getString(map, INDUSTRY, "");
        List<String> listLocation = new ArrayList<String>();

        if (!StringUtils.isBlank(location)) {

            listLocation = getChildLocations(location);

        }

        Map<String, Object> mapSearch = new HashMap<>();

        mapSearch.put(Position.TITLE, keyword);

        mapSearch.put(Position.LOCATION, listLocation);

        mapSearch.put(Position.INDUSTRY, industry);

        return mapSearch;

    }


    /**
     * 查询职位信息
     * 
     * @param param
     * @return
     */
    public Object searchPosition(Object param) {
        Map<String, Object> map = (Map) param;

        QueryFactory queryFactory = new WebBuildQueryFactory();

        int pageNo = MapUtils.getIntValue(map, Constants.page, 0);

        int pageSize = MapUtils.getIntValue(map, Constants.count, 0);

        if (pageNo == 0) {
            pageNo = Constants.DEF_PAGE;
        }
        if (pageSize == 0) {
            pageSize = Constants.DEF_COUNT;
        }

        // 检索条件
        Map<String, Object> mapSearch = buildSearchCondition(map);

        OutSearchPositionList out = new OutSearchPositionList();
        try {

            IndexHolder indexHolder = IndexHolder.init(Lucene.getLuceneDir());

            String path = Lucene.IndexType.position.getPath();

            Query query = queryFactory.createPositionQuery(mapSearch, 1.f);

            int total = indexHolder.count(path, query, null);

            // Sort sort = new Sort(new SortField(Company.TIME, SortField.Type.LONG, true));


            Sort sort = new Sort(new SortField[] {SortField.FIELD_SCORE,
                    new SortField(Company.TIME, SortField.Type.LONG, true)});


            List<String> list =
                    (List<String>) indexHolder.find(path, query, null, sort, pageNo, pageSize);



            if (list != null && list.size() > 0) {

                List<Map<String, Object>> listResult = positionInfoDao.selectAppSearchResult(list);

                for (Map<String, Object> map2 : listResult) {

                    SearchPositionResult searchPositionResult = new SearchPositionResult();

                    searchPositionResult.id = MapUtils.getString(map2, PositionInfoTable.ID);

                    searchPositionResult.name =
                            MapUtils.getString(map2, PositionInfoTable.POSITION_NAME);

                    if (MapUtils.getString(map2, PositionInfoTable.LAST_UPDATED_TIME) != null
                            || !MapUtils.getString(map2, PositionInfoTable.LAST_UPDATED_TIME)
                                    .equals("")) {

                        searchPositionResult.time = DateFormatUtils.format2Date(Long.parseLong(
                                MapUtils.getString(map2, PositionInfoTable.LAST_UPDATED_TIME)));

                    } else {

                        searchPositionResult.time = DateFormatUtils.format2Date(Long.parseLong(
                                MapUtils.getString(map2, PositionInfoTable.CREATE_TIME)));

                    }



                    searchPositionResult.city =
                            MapUtils.getString(map2, CommLocationTable.LOCATION_NAME);

                    searchPositionResult.company_id =
                            MapUtils.getString(map2, PositionInfoTable.COMPANY_ID);

                    searchPositionResult.company_name =
                            MapUtils.getString(map2, CompanyInfoTable.ENT_NAME);

                    searchPositionResult.logo =
                            StringUtil.getHeadUrl(MapUtils.getString(map2, CompanyInfoTable.LOGO));

                    out.data.add(searchPositionResult);

                }

                out.pagination.total = total;
                out.pagination.page = pageNo;
                out.pagination.count = listResult.size();

            }

        } catch (Exception e) {

        }

        return out;

    }

    /**
     * 搜索宣讲会
     * 
     * @param param
     * @return
     */
    public Object searchLive(Object param) {

        Map<String, Object> map = (Map) param;

        QueryFactory queryFactory = new WebBuildQueryFactory();

        int pageNo = MapUtils.getIntValue(map, Constants.page, 0);

        int pageSize = MapUtils.getIntValue(map, Constants.count, 0);

        if (pageNo == 0) {
            pageNo = Constants.DEF_PAGE;
        }
        if (pageSize == 0) {
            pageSize = Constants.DEF_COUNT;
        }

        // 检索条件
        Map<String, Object> mapSearch = buildSearchCondition(map);
        OutAppSearchLiveList out = new OutAppSearchLiveList();

   
        try {

            IndexHolder indexHolder = IndexHolder.init(Lucene.getLuceneDir());

            String path = Lucene.IndexType.live.getPath();

            Query query = queryFactory.createLiveQuery(mapSearch, 1.f);

            int total = indexHolder.count(path, query, null);

            // Sort sort = new Sort(new SortField(Company.TIME, SortField.Type.LONG, true));
            Sort sort = new Sort(new SortField[] {SortField.FIELD_SCORE,
                    new SortField(Company.TIME, SortField.Type.LONG, true)});

            List<String> list =
                    (List<String>) indexHolder.find(path, query, null, sort, pageNo, pageSize);

            if (list != null && list.size() > 0) {

                List<Map<String, Object>> listResult = liveInfoDao.selectAppSearchResult(list);

                for (Map<String, Object> map2 : listResult) {

                    AppSearchLiveResult searchLiveResult = new AppSearchLiveResult();

                    searchLiveResult.id = MapUtils.getString(map2, LiveInfoTable.ID);

                    searchLiveResult.start_time =
                            MapUtils.getString(map2, LiveInfoTable.START_TIME);

                    searchLiveResult.name = MapUtils.getString(map2, LiveInfoTable.SUBJECT);

                    searchLiveResult.room_id = MapUtils.getIntValue(map2, LiveInfoTable.ROOM_ID);

                    searchLiveResult.status = MapUtils.getIntValue(map2, LiveInfoTable.STATUS);

                    searchLiveResult.type = MapUtils.getIntValue(map2, LiveInfoTable.TYPE);

                    searchLiveResult.company_id = MapUtils.getString(map2, LiveInfoTable.ORG_ID);

                    searchLiveResult.logo =
                            StringUtil.getHeadUrl(MapUtils.getString(map2, CompanyInfoTable.LOGO));
                    searchLiveResult.video_url =
                            MapUtils.getString(map2, LiveVideoInfoTable.VIDEO_URL);
  
                    searchLiveResult.verify_status =
                            MapUtils.getIntValue(map2, CompanyInfoTable.VERIFY_STATUS);

                    searchLiveResult.company_name =
                            MapUtils.getString(map2, CompanyInfoTable.ENT_SIMPLE_NAME, "");

                    if (searchLiveResult.company_name.equals("")) {

                        searchLiveResult.company_name =
                                MapUtils.getString(map2, CompanyInfoTable.ENT_NAME, "");

                    }

                    out.data.add(searchLiveResult);

                }

                out.pagination.total = total;
                out.pagination.page = pageNo;
                out.pagination.count = listResult.size();

            }

        } catch (Exception e) {

        }

        return out;
    }

    /**
     * 搜索公司
     * 
     * @param param
     * @return
     */
    public Object searchCompany(Object param) {
        Map<String, Object> map = (Map) param;

        QueryFactory queryFactory = new WebBuildQueryFactory();

        int pageNo = MapUtils.getIntValue(map, Constants.page, 0);

        int pageSize = MapUtils.getIntValue(map, Constants.count, 0);

        if (pageNo == 0) {
            pageNo = Constants.DEF_PAGE;
        }
        if (pageSize == 0) {
            pageSize = Constants.DEF_COUNT;
        }

        // 检索条件
        Map<String, Object> mapSearch = buildSearchCondition(map);

        OutSearchCompanyList out = new OutSearchCompanyList();

        try {

            IndexHolder indexHolder = IndexHolder.init(Lucene.getLuceneDir());

            String path = Lucene.IndexType.company.getPath();

            Query query = queryFactory.createCompanyQuery(mapSearch, 1.f);

            int total = indexHolder.count(path, query, null);


            // SortField[] sortFields= new SortField[] { SortField.FIELD_SCORE, SortField.FIELD_DOC
            // } ;

            // Sort sort=new Sort(sortFields);

            // Sort sort = new Sort(new SortField(Company.TIME, SortField.Type.LONG, true));

            Sort sort = new Sort(new SortField[] {SortField.FIELD_SCORE,
                    new SortField(Company.TIME, SortField.Type.LONG, true)});
            // 先按记录的得分排序,然后再按记录的发布时间倒序
            // TopFieldCollector collector = TopFieldCollector.create(sort , 10 , false , true ,
            // false , false);
            //
            //
            // search.search(bq, collector);


            List<String> list =
                    (List<String>) indexHolder.find(path, query, null, sort, pageNo, pageSize);

            if (list != null && list.size() > 0) {

                List<Map<String, Object>> listResult = companyInfoDao.selectAppSearchResult(list);

                for (Map<String, Object> map2 : listResult) {

                    OutSearchCompanyList.SearchCompanyResult1 searchCompanyResult =
                            new OutSearchCompanyList.SearchCompanyResult1();

                    searchCompanyResult.id = MapUtils.getString(map2, CompanyInfoTable.ID);

                    searchCompanyResult.name =
                            MapUtils.getString(map2, CompanyInfoTable.ENT_NAME, "");

                    searchCompanyResult.city =
                            MapUtils.getString(map2, CommLocationTable.LOCATION_NAME);

                    searchCompanyResult.position_total =
                            MapUtils.getIntValue(map2, PositionInfo.POSITION_TOTAL, 0);

                    searchCompanyResult.logo = StringUtil
                            .getHeadUrl(MapUtils.getString(map2, CompanyInfoTable.LOGO, ""));

                    out.data.add(searchCompanyResult);

                }

                out.pagination.total = total;
                out.pagination.page = pageNo;
                out.pagination.count = listResult.size();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return out;

    }

    private List<String> getChildLocations(String location) {
        List<String> listLocation = new ArrayList<String>();

        if (!StringUtils.isBlank(location)) {

            Map<String, Object> mapLocation = commLocationDao.selectGroupLoactionCode(location);

            if (mapLocation != null && mapLocation.size() > 0) {
                String strLocation =
                        MapUtils.getString(mapLocation, CommLocationTable.LOCATION_CODE);

                // 某个地区下的子地区
                if (!StringUtils.isBlank(strLocation)) {

                    strLocation = strLocation + "," + location;// 加上自己

                    String[] strLocations = strLocation.split(",");

                    listLocation = Arrays.asList(strLocations);

                    return listLocation;
                }

            }

        }

        listLocation.add(location);// 默认自己

        return listLocation;
    }


    /**
     * 搜索 学校
     * 
     * @param param
     * @return
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public Object searchSchool(Object param) {
        Map<String, Object> map = (Map) param;
        QueryFactory queryFactory = new WebBuildQueryFactory();
        int pageNo = MapUtils.getIntValue(map, Constants.page, 0);
        int pageSize = MapUtils.getIntValue(map, Constants.count, 0);
        if (pageNo == 0) {
            pageNo = Constants.DEF_PAGE;
        }
        if (pageSize == 0) {
            pageSize = Constants.DEF_COUNT;
        }
        // 检索条件
        Map<String, Object> mapSearch = buildSearchCondition(map);

        OutAppSearchSchoolList out = new OutAppSearchSchoolList();

        try {
            IndexHolder indexHolder = IndexHolder.init(Lucene.getLuceneDir());
            String path = Lucene.IndexType.sch.getPath();

            Query query = queryFactory.createSchQuery(mapSearch, 1.f);// 查询学生

            int total = indexHolder.count(path, query, null);
            Sort sort = new Sort(new SortField[] {SortField.FIELD_SCORE,
                    new SortField(Company.TIME, SortField.Type.LONG, true)});


            // Sort sort = new Sort(new SortField(SchInfo.TIME, SortField.Type.LONG, true));

            List<String> list =
                    (List<String>) indexHolder.find(path, query, null, sort, pageNo, pageSize);

            if (list != null && list.size() > 0) {
                List<Map<String, Object>> listResult = schInfoDao.selectAppSearchResult(list);
                for (Map<String, Object> map2 : listResult) {
                    AppSearchSchoolResult result = new AppSearchSchoolResult();

                    String name = MapUtils.getString(map2, SchInfoTable.SCHOOL_NAME);
                    result.id = MapUtils.getString(map2, SchInfoTable.ID);

                    // 学校名称
                    result.school_name = name;
                    // SearchHelper.highlight(name, MapUtils.getString(map, KEYWORLD, ""));

                    // 学校特色
                    result.school_feature =
                            MapUtils.getString(map2, SchoolFeatureTable.GROUP_NAME, "");

                    // 学校类型
                    result.school_class = MapUtils.getString(map2, SchoolClassTable.CLASS_NAME, "");
                    // logo
                    result.logo = StringUtil
                            .getHeadUrl(MapUtils.getString(map2, SchInfoTable.SCHOOL_LOGO, ""));

                    // 研究领域
                    result.direction = MapUtils.getString(map2, SchInfoTable.DIRECTION, "");

                    result.location_name =
                            MapUtils.getString(map2, CommLocationTable.LOCATION_NAME, "");

                    result.verify_status =
                            MapUtils.getIntValue(map2, SchInfoTable.VERIFY_STATUS, 0);
                    result.user_type = UserType.school.getUserType();
                    out.data.add(result);
                }

                out.pagination.total = total;
                out.pagination.page = pageNo;
                out.pagination.count = listResult.size();

            }

        } catch (Exception e) {

        }

        return out;
    }

    /**
     * 搜索 学生
     * 
     * @param param
     * @return
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public Object searchStudent(Object param) {
        Map<String, Object> map = (Map) param;
        QueryFactory queryFactory = new WebBuildQueryFactory();
        int pageNo = MapUtils.getIntValue(map, Constants.page, 0);
        int pageSize = MapUtils.getIntValue(map, Constants.count, 0);
        if (pageNo == 0) {
            pageNo = Constants.DEF_PAGE;
        }
        if (pageSize == 0) {
            pageSize = Constants.DEF_COUNT;
        }
        // 检索条件
        Map<String, Object> mapSearch = buildSearchCondition(map);

        OutAppLuceneSearch.OutAppSearchStudentList out =
                new OutAppLuceneSearch.OutAppSearchStudentList();
        try {
            IndexHolder indexHolder = IndexHolder.init(Lucene.getLuceneDir());
            String path = Lucene.IndexType.stu.getPath();

            Query query = queryFactory.createStuQuery(mapSearch, 1.f);// 查询学生

            int total = indexHolder.count(path, query, null);


            Sort sort = new Sort(new SortField[] {SortField.FIELD_SCORE,
                    new SortField(Company.TIME, SortField.Type.LONG, true)});


            List<String> list =
                    (List<String>) indexHolder.find(path, query, null, sort, pageNo, pageSize);
            String name;
            if (list != null && list.size() > 0) {
                List<Map<String, Object>> listResult = stuUserBuildDao.selectAppSearchResult(list);
                for (Map<String, Object> map2 : listResult) {
                    OutAppSearchStudentList.AppSearchStudentResult result =
                            new OutAppSearchStudentList.AppSearchStudentResult();

                    name = MapUtils.getString(map2, StuUserTable.SURNAME,"")
                            + MapUtils.getString(map2, StuUserTable.MISS_SURNAME,"");

                    result.id = MapUtils.getString(map2, StuUserTable.ID);

                    result.name = name;
                    // SearchHelper.highlight(surname + mis_surname,
                    // MapUtils.getString(map, KEYWORLD, ""));
                    result.logo = StringUtil
                            .getHeadUrl(MapUtils.getString(map2, StuUserTable.HEAD_URL, ""));
                    result.user_type = UserType.student.getUserType();
                    result.education_name =
                            MapUtils.getString(map2, CommEducationTable.EDUCATION_NAME, "");
                    result.location_name =
                            MapUtils.getString(map2, CommLocationTable.LOCATION_NAME, "");
                    result.major_name = MapUtils.getString(map2, CommMajorTable.MAJOR_NAME, "");
                    result.school_name = MapUtils.getString(map2, SchInfoTable.SCHOOL_NAME, "");
                    out.data.add(result);
                }

                out.pagination.total = total;
                out.pagination.page = pageNo;
                out.pagination.count = listResult.size();

            }

        } catch (Exception e) {

        }

        return out;
    }

    /****************************************************** 统计部分 **************************************************************************/

    private SearchCount searchCompanyCount(String keyword, int pageNo, int pageSize) {
        QueryFactory queryFactory = new WebBuildQueryFactory();

        Map<String, Object> search = new HashMap<>();
        search.put(Position.TITLE, keyword);

        SearchCount company = new SearchCount();

        company.keyword = keyword;

        company.type = Lucene.IndexType.company.name();

        try {
            IndexHolder indexHolder = IndexHolder.init(Lucene.getLuceneDir());

            String path = Lucene.IndexType.company.getPath();

            Query query = queryFactory.createCompanyQuery(search, 1.f);

            company.total = indexHolder.count(path, query, null);

            Sort sort = new Sort(new SortField[] {SortField.FIELD_SCORE,
                    new SortField(Company.TIME, SortField.Type.LONG, true)});

            List<String> list =
                    (List<String>) indexHolder.find(path, query, null, sort, pageNo, pageSize);

            if (list != null && list.size() > 0) {
                List<Map<String, Object>> listResult =
                        companyInfoDao.selectAppSearchRecommend(list);
                // 推荐
                for (Map<String, Object> map2 : listResult) {
                    String id = MapUtils.getString(map2, CompanyIndexRepairServiceImpl.ID, "");
                    String title =
                            MapUtils.getString(map2, CompanyIndexRepairServiceImpl.ENT_NAME, "");
                    company.recommend.add(new SearchCountRecommend(id, title));
                }

            }

        } catch (Exception e) {

        }

        return company;

    }

    private SearchCount searchLiveCount(String keyword, int pageNo, int pageSize) {
        SearchCount live = new SearchCount();
        QueryFactory queryFactory = new WebBuildQueryFactory();

        Map<String, Object> search = new HashMap<>();

        search.put(Live.TITLE, keyword);

        live.keyword = keyword;

        live.type = Lucene.IndexType.live.name();

        try {
            IndexHolder indexHolder = IndexHolder.init(Lucene.getLuceneDir());

            String path = Lucene.IndexType.live.getPath();

            Query query = queryFactory.createLiveQuery(search, 1.f);

            live.total = indexHolder.count(path, query, null);

            Sort sort = new Sort(new SortField[] {SortField.FIELD_SCORE,
                    new SortField(Company.TIME, SortField.Type.LONG, true)});

            List<String> list =
                    (List<String>) indexHolder.find(path, query, null, sort, pageNo, pageSize);

            if (list != null && list.size() > 0) {
                List<Map<String, Object>> listResult = liveInfoDao.selectAppSearchRecommend(list);
                // 推荐
                for (Map<String, Object> map2 : listResult) {
                    String id = MapUtils.getString(map2, LiveIndexRepairServiceImpl.ID, "");
                    String title = MapUtils.getString(map2, LiveIndexRepairServiceImpl.SUBJECT, "");
                    live.recommend.add(new SearchCountRecommend(id, title));
                }

            }

        } catch (Exception e) {

        }

        return live;

    }

    private SearchCount searchPositionCount(String keyword, int pageNo, int pageSize) {

        QueryFactory queryFactory = new WebBuildQueryFactory();

        Map<String, Object> search = new HashMap<>();
        search.put(Position.TITLE, keyword);

        SearchCount position = new SearchCount();

        position.keyword = keyword;
        position.type = Lucene.IndexType.position.name();
        try {
            IndexHolder indexHolder = IndexHolder.init(Lucene.getLuceneDir());

            String path = Lucene.IndexType.position.getPath();

            Query query = queryFactory.createPositionQuery(search, 1.f);

            position.total = indexHolder.count(path, query, null);

            Sort sort = new Sort(new SortField[] {SortField.FIELD_SCORE,
                    new SortField(Company.TIME, SortField.Type.LONG, true)});

            List<String> list =
                    (List<String>) indexHolder.find(path, query, null, sort, pageNo, pageSize);

            if (list != null && list.size() > 0) {
                List<Map<String, Object>> listResult =
                        positionInfoDao.selectAppSearchRecommend(list);
                // 推荐
                for (Map<String, Object> map2 : listResult) {
                    String id = MapUtils.getString(map2, PositionIndexRepairServiceImpl.ID, "");
                    String title = MapUtils.getString(map2,
                            PositionIndexRepairServiceImpl.POSITION_NAME, "");
                    position.recommend.add(new SearchCountRecommend(id, title));
                }

            }

        } catch (Exception e) {

        }

        return position;

    }

    private SearchCount searchStudentCount(String keyword, int pageNo, int pageSize) {

        QueryFactory queryFactory = new WebBuildQueryFactory();

        Map<String, Object> search = new HashMap<>();
        search.put(StuUser.TITLE, keyword);

        SearchCount studentCount = new SearchCount();

        studentCount.keyword = keyword;
        studentCount.type = Lucene.IndexType.student.name();
        try {
            IndexHolder indexHolder = IndexHolder.init(Lucene.getLuceneDir());

            String path = Lucene.IndexType.stu.getPath();

            Query query = queryFactory.createStuQuery(search, 1.f);

            studentCount.total = indexHolder.count(path, query, null);

            // Sort sort = new Sort(new SortField(StuUser.TIME, SortField.Type.LONG, true));
            Sort sort = new Sort(new SortField[] {SortField.FIELD_SCORE,
                    new SortField(Company.TIME, SortField.Type.LONG, true)});

            List<String> list =
                    (List<String>) indexHolder.find(path, query, null, sort, pageNo, pageSize);

            if (list != null && list.size() > 0) {
                List<Map<String, Object>> listResult =
                        stuUserBuildDao.selectAppSearchRecommend(list);
                // 推荐
                for (Map<String, Object> map2 : listResult) {
                    String id = MapUtils.getString(map2, StuUserTable.ID, "");
                    String title = MapUtils.getString(map2, StuUserTable.SURNAME, "")
                            + MapUtils.getString(map2, StuUserTable.MISS_SURNAME, "");
                    studentCount.recommend.add(new SearchCountRecommend(id, title));
                }

            }

        } catch (Exception e) {

        }

        return studentCount;

    }


    private SearchCount searchSchoolCount(String keyword, int pageNo, int pageSize) {

        QueryFactory queryFactory = new WebBuildQueryFactory();

        Map<String, Object> search = new HashMap<>();
        search.put(SchInfo.TITLE, keyword);

        SearchCount schoolCount = new SearchCount();

        schoolCount.keyword = keyword;
        schoolCount.type = Lucene.IndexType.school.name();
        try {
            IndexHolder indexHolder = IndexHolder.init(Lucene.getLuceneDir());

            String path = Lucene.IndexType.sch.getPath();

            Query query = queryFactory.createSchQuery(search, 1.f);

            schoolCount.total = indexHolder.count(path, query, null);

            // Sort sort = new Sort(new SortField(StuUser.TIME, SortField.Type.LONG, true));
            Sort sort = new Sort(new SortField[] {SortField.FIELD_SCORE,
                    new SortField(Company.TIME, SortField.Type.LONG, true)});

            List<String> list =
                    (List<String>) indexHolder.find(path, query, null, sort, pageNo, pageSize);

            if (list != null && list.size() > 0) {
                List<Map<String, Object>> listResult = schInfoDao.selectAppSearchRecommend(list);
                // 推荐
                for (Map<String, Object> map2 : listResult) {
                    String id = MapUtils.getString(map2, SchInfoTable.ID, "");
                    String title = MapUtils.getString(map2, SchInfoTable.SCHOOL_NAME, "");
                    schoolCount.recommend.add(new SearchCountRecommend(id, title));
                }

            }

        } catch (Exception e) {

        }

        return schoolCount;

    }

}
