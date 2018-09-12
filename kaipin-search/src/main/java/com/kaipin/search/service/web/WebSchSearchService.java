package com.kaipin.search.service.web;

import java.io.File;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.kaipin.search.Constants;
import com.kaipin.search.constant.ConstantTables;
import com.kaipin.search.constant.Lucene;
import com.kaipin.search.core.dimension.Company;
import com.kaipin.search.core.dimension.Live;
import com.kaipin.search.core.dimension.Position;
import com.kaipin.search.core.dimension.SchInfo;
import com.kaipin.search.core.dimension.StuUser;
import com.kaipin.search.core.index.IndexHolder;
import com.kaipin.search.core.index.SearchHelper;
import com.kaipin.search.core.query.QueryFactory;
import com.kaipin.search.core.query.WebBuildQueryFactory;
import com.kaipin.search.entity.PositionInfo;
import com.kaipin.search.entity.CommLocation.CommLocationTable;
import com.kaipin.search.entity.CompanyInfo.CompanyInfoTable;
import com.kaipin.search.entity.LiveInfo.LiveInfoTable;
import com.kaipin.search.entity.LiveInfo.LiveVideoInfoTable;
import com.kaipin.search.entity.PositionInfo.PositionInfoTable;
import com.kaipin.search.entity.SchInfo.SchInfoTable;
import com.kaipin.search.entity.StuUser.StuUserTable;
import com.kaipin.search.model.OutAppSearchCount;
import com.kaipin.search.model.OutAppLuceneSearch.OutSearchCompanyList;
import com.kaipin.search.model.OutAppLuceneSearch.OutSearchLiveList;
import com.kaipin.search.model.OutAppLuceneSearch.OutSearchPositionList;
import com.kaipin.search.model.OutAppLuceneSearch.OutSearchSchList;
import com.kaipin.search.model.OutAppLuceneSearch.OutSearchStuList;
import com.kaipin.search.model.OutAppLuceneSearch.OutSearchLiveList.SearchLiveResult;
import com.kaipin.search.model.OutAppLuceneSearch.OutSearchPositionList.SearchPositionResult;
import com.kaipin.search.model.OutAppLuceneSearch.OutSearchSchList.SearchSchResult;
import com.kaipin.search.model.OutAppLuceneSearch.OutSearchStuList.SearchStuResult;
import com.kaipin.search.model.BaseAppSearchCount.SearchCount;
import com.kaipin.search.model.BaseAppSearchCount.SearchCountRecommend;
import com.kaipin.search.repository.dao.CommLocationDao;
import com.kaipin.search.repository.dao.IndexQueryDao;
import com.kaipin.search.service.ICommonCodeService;
import com.kaipin.search.service.IPositionInfoService;
import com.kaipin.search.service.impl.CompanyIndexRepairServiceImpl;
import com.kaipin.search.service.impl.LiveIndexRepairServiceImpl;
import com.kaipin.search.service.impl.PositionIndexRepairServiceImpl;
import com.kaipin.search.util.DateFormatUtils;
import com.kaipin.search.util.StringUtil;

/**
 * web端搜索功能
 * @author Mr-H
 *
 */
@Component
public class WebSchSearchService {
	public final static String KEYWORLD = "keyword";

	public final static String LOCATION = "location";
	
	public final static String LOCATION_WEB = "location_code";

	public final static String INDUSTRY = "industry";

	@Autowired
	@Qualifier("positionInfoDao")
	protected IndexQueryDao positionInfoDao;

	@Autowired
	@Qualifier("liveInfoDao")
	protected IndexQueryDao liveInfoDao;// 视频

	@Autowired
	@Qualifier("companyInfoDao")
	private IndexQueryDao companyInfoDao; // 公司

	@Autowired
	protected CommLocationDao commLocationDao;
	
	@Autowired
	@Qualifier("stuUserBuildDao")
	protected IndexQueryDao stuUserBuildDao; // 学生用户
	
	
	@Autowired
	@Qualifier("schInfoDao")
	protected IndexQueryDao schInfoDao; // 学校
	
	
	@Autowired
	protected ICommonCodeService commCodeService;//公用码表
	@Autowired
	protected IPositionInfoService positionInfoService;//职位基本信息

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

		OutAppSearchCount out = new OutAppSearchCount();

		out.position = searchPositionCount(keyword, pageNo, pageSize);

		out.live = searchLiveCount(keyword, pageNo, pageSize);

		out.company = searchCompanyCount(keyword, pageNo, pageSize);
		
		out.stu = searchStuCount(keyword, pageNo, pageSize);
		
		out.sch = searchSchCount(keyword, pageNo, pageSize);

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
			
			Sort sort = new Sort(new SortField(Company.TIME, SortField.Type.LONG, true));

			List<String> list = (List<String>) indexHolder.find(path, query, null, sort, pageNo, pageSize);

			if (list != null && list.size() > 0) {
				
				String locations = MapUtils.getString(map, LOCATION_WEB, "");
				String location = "";
				if(StringUtil.isNotEmpty(locations)){
					String locationArray[] = locations.split(",");
					if(locationArray.length == 1){
						location ="'" + locations + "'";
					}else{
						for (String s : locationArray) {
							location = "'" + location + "','" + s; 
						}
						location = location.substring(4, location.length() ) + "'";
					}
					
				}
				
				String ids = "";
				for (int i = 0; i < list.size(); i++) {
					if(i == 0){
						ids = "'" + list.get(0);
					}else{
						ids = ids + "','" + list.get(i);
					}
				}
				ids = ids + "'";
				
				Map<String, Object> mapVal = new HashMap<>();
				mapVal.put("ids", ids);
				mapVal.put("location", location);
				
				List<Map<String, Object>> listResult = positionInfoDao.selectWebSearchResultByMap(mapVal);

				for (Map<String, Object> map2 : listResult) {

					SearchPositionResult searchPositionResult = new SearchPositionResult();
					//id
					searchPositionResult.id = MapUtils.getString(map2, PositionInfoTable.ID);
					//名称
					String positionName = MapUtils.getString(map2, PositionInfoTable.POSITION_NAME);
					searchPositionResult.name = 
							SearchHelper.highlight(positionName, 
									MapUtils.getString(map, KEYWORLD, ""));
					//时间
					if (MapUtils.getString(map2, PositionInfoTable.LAST_UPDATED_TIME) != null
							|| !MapUtils.getString(map2, PositionInfoTable.LAST_UPDATED_TIME).equals("")) {

						searchPositionResult.time = DateFormatUtils.format2Date(
								Long.parseLong(MapUtils.getString(map2, PositionInfoTable.LAST_UPDATED_TIME)));
					} else {
						searchPositionResult.time = DateFormatUtils
								.format2Date(Long.parseLong(MapUtils.getString(map2, PositionInfoTable.CREATE_TIME)));

					}
					//地区
					searchPositionResult.city = MapUtils.getString(map2, CommLocationTable.LOCATION_NAME);
					//企业id
					searchPositionResult.company_id = MapUtils.getString(map2, PositionInfoTable.COMPANY_ID);
					//企业名称
					searchPositionResult.company_name = MapUtils.getString(map2, CompanyInfoTable.ENT_NAME);
					//logo
					searchPositionResult.logo = StringUtil.getHeadUrl(MapUtils.getString(map2, CompanyInfoTable.LOGO));
					//当前公司在找职位总数
					searchPositionResult.positionCount = 
							positionInfoService.getPositionCountByEntId(
									MapUtils.getString(map2, PositionInfoTable.COMPANY_ID));

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

		OutSearchLiveList out = new OutSearchLiveList();

		try {

			IndexHolder indexHolder = IndexHolder.init(Lucene.getLuceneDir());

			String path = Lucene.IndexType.live.getPath();

			Query query = queryFactory.createPositionQuery(mapSearch, 1.f);

			int total = indexHolder.count(path, query, null);

			Sort sort = new Sort(new SortField(Company.TIME, SortField.Type.LONG, true));

			List<String> list = (List<String>) indexHolder.find(path, query, null, sort, pageNo, pageSize);

			if (list != null && list.size() > 0) {

				List<Map<String, Object>> listResult = liveInfoDao.selectWebSearchResult(list);

				for (Map<String, Object> map2 : listResult) {

					SearchLiveResult searchLiveResult = new SearchLiveResult();

					searchLiveResult.id = MapUtils.getString(map2, LiveInfoTable.ID);

					searchLiveResult.start_time = MapUtils.getString(map2, LiveInfoTable.START_TIME);

					String liveName = MapUtils.getString(map2, LiveInfoTable.SUBJECT);
					
					searchLiveResult.name = 
							SearchHelper.highlight(liveName, 
									MapUtils.getString(map, KEYWORLD, ""));

					searchLiveResult.room_id = MapUtils.getIntValue(map2, LiveInfoTable.ROOM_ID);

					searchLiveResult.status = MapUtils.getIntValue(map2, LiveInfoTable.STATUS);

					searchLiveResult.type = MapUtils.getIntValue(map2, LiveInfoTable.TYPE);

					searchLiveResult.company_id = MapUtils.getString(map2, LiveInfoTable.ORG_ID);

					searchLiveResult.logo = StringUtil.getHeadUrl(MapUtils.getString(map2, CompanyInfoTable.LOGO));

					searchLiveResult.verify_status = MapUtils.getIntValue(map2, CompanyInfoTable.VERIFY_STATUS);

					searchLiveResult.company_name = MapUtils.getString(map2, CompanyInfoTable.ENT_SIMPLE_NAME, "");

					if (searchLiveResult.company_name.equals("")) {

						searchLiveResult.company_name = MapUtils.getString(map2, CompanyInfoTable.ENT_NAME, "");

					}
					//封面地址
					searchLiveResult.cover_image_path = MapUtils.getString(map2, LiveInfoTable.COVER_IMAGE_PATH);
					//视频播放地址
					searchLiveResult.vedio_url = MapUtils.getString(map2, LiveVideoInfoTable.VIDEO_URL);;
					//备注
					searchLiveResult.memo = MapUtils.getString(map2, LiveInfoTable.MEMO);
					
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

			Query query = queryFactory.createPositionQuery(mapSearch, 1.f);

			int total = indexHolder.count(path, query, null);

			Sort sort = new Sort(new SortField(Company.TIME, SortField.Type.LONG, true));

			List<String> list = (List<String>) indexHolder.find(path, query, null, sort, pageNo, pageSize);

			if (list != null && list.size() > 0) {

				List<Map<String, Object>> listResult = companyInfoDao.selectWebSearchResult(list);

				for (Map<String, Object> map2 : listResult) {

					OutSearchCompanyList.SearchCompanyResult searchCompanyResult = new OutSearchCompanyList.SearchCompanyResult();

					searchCompanyResult.id = MapUtils.getString(map2, CompanyInfoTable.ID);

					String entName = MapUtils.getString(map2, CompanyInfoTable.ENT_NAME, "");
					
					searchCompanyResult.name = 
							SearchHelper.highlight(entName, MapUtils.getString(map, KEYWORLD, ""));

					searchCompanyResult.city = MapUtils.getString(map2, CommLocationTable.LOCATION_NAME);
					
					//企业在招职位数
					searchCompanyResult.positionCount = positionInfoService.getPositionCountByEntId(MapUtils.getString(map2, CompanyInfoTable.ID));

					searchCompanyResult.logo = StringUtil.getLogo(MapUtils.getString(map2, CompanyInfoTable.LOGO, ""));
					
					//专注领域
					searchCompanyResult.business_domain = MapUtils.getString(map2, CompanyInfoTable.BUSINESS_DOMAIN,"");
					
					out.data.add(searchCompanyResult);

				}

				out.pagination.total = total;
				out.pagination.page = pageNo;
				out.pagination.count = listResult.size();
			}

		} catch (Exception e) {

		}
		return out;

	}

	private List<String> getChildLocations(String location) {
		List<String> listLocation = new ArrayList<String>();

		if (!StringUtils.isBlank(location)) {

			Map<String, Object> mapLocation = commLocationDao.selectGroupLoactionCode(location);

			if (mapLocation != null && mapLocation.size() > 0) {
				String strLocation = MapUtils.getString(mapLocation, CommLocationTable.LOCATION_CODE);

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
	 * 搜索	学校
	 * 
	 * @param param
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Object searchSch(Object param) {
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
		
		OutSearchSchList out = new OutSearchSchList();
		
		try {
			IndexHolder indexHolder = IndexHolder.init(Lucene.getLuceneDir());
			String path = Lucene.IndexType.sch.getPath();
			
			Query query = queryFactory.createSchQuery(mapSearch, 1.f);//查询学生
			
			int total = indexHolder.count(path, query, null);
			Sort sort = new Sort(new SortField(SchInfo.TIME, SortField.Type.LONG, true));
			List<String> list = (List<String>) indexHolder.find(path, query, null, sort, pageNo, pageSize);

			if (list != null && list.size() > 0) {
				List<Map<String, Object>> listResult = schInfoDao.selectWebSearchResult(list);
				for (Map<String, Object> map2 : listResult) {
					SearchSchResult result = new SearchSchResult();
					
					String name =  MapUtils.getString(map2, SchInfoTable.SCHOOL_NAME);
					result.id =  MapUtils.getString(map2, SchInfoTable.ID);
					
					//学校名称
					result.school_name = SearchHelper.highlight(name, MapUtils.getString(map, KEYWORLD, ""));
					
					//学校特色
					result.school_feature_id =  commCodeService.getNameByCode(ConstantTables.SCHOOL_FEATURE, 
							ConstantTables.SCHOOL_FEATURE_CODE, MapUtils.getString(map2, SchInfoTable.SCHOOL_FEATURE_ID), 
							ConstantTables.SCHOOL_FEATURE_NAME);
					//学校类型
					result.school_class_id = commCodeService.getNameByCode(ConstantTables.SCHOOL_CLASS, 
							ConstantTables.SCHOOL_CLASS_CODE, MapUtils.getString(map2, SchInfoTable.SCHOOL_CLASS_ID), 
							ConstantTables.SCHOOL_CLASS_NAME);
					//logo
					result.school_logo =StringUtil.getHeadUrl( MapUtils.getString(map2, SchInfoTable.SCHOOL_LOGO,""));
					
					//研究领域
					result.direction = MapUtils.getString(map2, SchInfoTable.DIRECTION);
					
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
	 * 搜索	学生
	 * 
	 * @param param
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Object searchStu(Object param) {
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
		OutSearchStuList out = new OutSearchStuList();
		try {
			IndexHolder indexHolder = IndexHolder.init(Lucene.getLuceneDir());
			String path = Lucene.IndexType.stu.getPath();
			
			Query query = queryFactory.createStuQuery(mapSearch, 1.f);//查询学生
			
			int total = indexHolder.count(path, query, null);
			Sort sort = new Sort(new SortField(StuUser.TIME, SortField.Type.LONG, true));
			List<String> list = (List<String>) indexHolder.find(path, query, null, sort, pageNo, pageSize);
			
			if (list != null && list.size() > 0) {
				List<Map<String, Object>> listResult = stuUserBuildDao.selectWebSearchResult(list);
				for (Map<String, Object> map2 : listResult) {
					SearchStuResult result = new SearchStuResult();
					
					String surname =  MapUtils.getString(map2, StuUserTable.SURNAME);
					String mis_surname = MapUtils.getString(map2, StuUserTable.MISS_SURNAME);
					result.id =  MapUtils.getString(map2, StuUserTable.ID);
					result.surname = getStrings(surname);
					result.miss_surname = getStrings(mis_surname);
					//姓名
					result.name = SearchHelper.highlight(getStrings(surname) + getStrings(mis_surname), 
							MapUtils.getString(map, KEYWORLD, ""));
					
					//就读学校
					result.school_code = commCodeService.getNameByCode(ConstantTables.COMM_SCHOOL, 
							ConstantTables.SCHOOL_COL_CODE, MapUtils.getString(map2, StuUserTable.SCHOOL_CODE), 
							ConstantTables.SCHOOL_COL_NAME);
										
					//logo
					result.head_url = StringUtil.getHeadUrl(
							getLogo(MapUtils.getString(map2, StuUserTable.HEAD_URL)) );
					//专业
					result.major_code = MapUtils.getString(map2, StuUserTable.MAJOR_NAME);
//					result.major_code = commCodeService.getNameByCode(ConstantTables.COMM_MAJOR, 
//							ConstantTables.MAJOR_COL_CODE, MapUtils.getString(map2, StuUserTable.MAJOR_CODE), 
//							ConstantTables.MAJOR_COL_NAME);
					
					//地区
					result.location_code =	commCodeService.getNameByCode(ConstantTables.COMM_LOCATION, 
							ConstantTables.AREA_COL_CODE, MapUtils.getString(map2, StuUserTable.LOCATION_CODE), 
							ConstantTables.AREA_COL_NAME);
					
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

			Sort sort = new Sort(new SortField(Company.TIME, SortField.Type.LONG, true));

			List<String> list = (List<String>) indexHolder.find(path, query, null, sort, pageNo, pageSize);

			if (list != null && list.size() > 0) {
				List<Map<String, Object>> listResult = companyInfoDao.selectAppSearchRecommend(list);
				// 推荐
				for (Map<String, Object> map2 : listResult) {
					String id = MapUtils.getString(map2, CompanyIndexRepairServiceImpl.ID, "");
					String title = MapUtils.getString(map2, CompanyIndexRepairServiceImpl.ENT_NAME, "");
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

			Sort sort = new Sort(new SortField(Live.TIME, SortField.Type.STRING, true));

			List<String> list = (List<String>) indexHolder.find(path, query, null, sort, pageNo, pageSize);

			if (list != null && list.size() > 0) {
				List<Map<String, Object>> listResult = positionInfoDao.selectAppSearchRecommend(list);
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

			Sort sort = new Sort(new SortField(Position.TIME, SortField.Type.LONG, true));

			List<String> list = (List<String>) indexHolder.find(path, query, null, sort, pageNo, pageSize);

			if (list != null && list.size() > 0) {
				List<Map<String, Object>> listResult = positionInfoDao.selectAppSearchRecommend(list);
				// 推荐
				for (Map<String, Object> map2 : listResult) {
					String id = MapUtils.getString(map2, PositionIndexRepairServiceImpl.ID, "");
					String title = MapUtils.getString(map2, PositionIndexRepairServiceImpl.POSITION_NAME, "");
					position.recommend.add(new SearchCountRecommend(id, title));
				}

			}

		} catch (Exception e) {

		}

		return position;

	}
	
	private SearchCount searchStuCount(String keyword, int pageNo, int pageSize) {
		
		QueryFactory queryFactory = new WebBuildQueryFactory();
		
		Map<String, Object> search = new HashMap<>();
		search.put(StuUser.TITLE, keyword);
		
		SearchCount stu = new SearchCount();
		
		stu.keyword = keyword;
		stu.type = Lucene.IndexType.stu.name();
		try {
			IndexHolder indexHolder = IndexHolder.init(Lucene.getLuceneDir());
			
			String path = Lucene.IndexType.stu.getPath();
			
			Query query = queryFactory.createStuQuery(search, 1.f);
			
			stu.total = indexHolder.count(path, query, null);
			
//			Sort sort = new Sort(new SortField(StuUser.TIME, SortField.Type.LONG, true));
//			
//			List<String> list = (List<String>) indexHolder.find(path, query, null, sort, pageNo, pageSize);
			
//			if (list != null && list.size() > 0) {
//				List<Map<String, Object>> listResult = positionInfoDao.selectAppSearchRecommend(list);
//				// 推荐
//				for (Map<String, Object> map2 : listResult) {
//					String id = MapUtils.getString(map2, PositionIndexRepairServiceImpl.ID, "");
//					String title = MapUtils.getString(map2, PositionIndexRepairServiceImpl.POSITION_NAME, "");
//					position.recommend.add(new SearchCountRecommend(id, title));
//				}
//				
//			}
			
		} catch (Exception e) {
			
		}
		
		return stu;
		
	}
	
	
	private SearchCount searchSchCount(String keyword, int pageNo, int pageSize) {
		
		QueryFactory queryFactory = new WebBuildQueryFactory();
		
		Map<String, Object> search = new HashMap<>();
		search.put(SchInfo.TITLE, keyword);
		
		SearchCount sch = new SearchCount();
		
		sch.keyword = keyword;
		sch.type = Lucene.IndexType.sch.name();
		try {
			IndexHolder indexHolder = IndexHolder.init(Lucene.getLuceneDir());
			
			String path = Lucene.IndexType.sch.getPath();
			
			Query query = queryFactory.createSchQuery(search, 1.f);
			
			sch.total = indexHolder.count(path, query, null);
			
//			Sort sort = new Sort(new SortField(StuUser.TIME, SortField.Type.LONG, true));
//			
//			List<String> list = (List<String>) indexHolder.find(path, query, null, sort, pageNo, pageSize);
			
//			if (list != null && list.size() > 0) {
//				List<Map<String, Object>> listResult = positionInfoDao.selectAppSearchRecommend(list);
//				// 推荐
//				for (Map<String, Object> map2 : listResult) {
//					String id = MapUtils.getString(map2, PositionIndexRepairServiceImpl.ID, "");
//					String title = MapUtils.getString(map2, PositionIndexRepairServiceImpl.POSITION_NAME, "");
//					position.recommend.add(new SearchCountRecommend(id, title));
//				}
//				
//			}
			
		} catch (Exception e) {
			
		}
		
		return sch;
		
	}
	
	public String getLogo(String logo){
		if(StringUtil.isEmpty(logo)){
			logo = "0";
		}else{
			if(!logo.startsWith("http")){
				logo = "http://img2.kaipin.tv/head/" + logo;
			}
		}
		
		return logo;
	}
	
	public String getStrings(String str){
		if(StringUtil.isEmpty(str)){
			str = "";
		}
		return str;
			
	}
}
