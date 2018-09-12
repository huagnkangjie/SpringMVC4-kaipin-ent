package com.enterprise.web.meeting;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.Constant;
import com.common.constants.AppSearchConstant;
import com.common.constants.FeedStatus;
import com.common.page.Page;
import com.common.pojo.Json;
import com.common.pojo.StautsBean;
import com.enterprise.model.EntLiveInfo;
import com.enterprise.model.EntMeetingNotice;
import com.enterprise.model.common.EntUser;
import com.enterprise.model.feed.Feed;
import com.enterprise.model.user.CompanyInfo;
import com.enterprise.service.common.ICommonAnnexService;
import com.enterprise.service.feed.IFeedService;
import com.enterprise.service.meeting.IEntLiveInfoService;
import com.enterprise.service.meeting.IEntMeetingNoticeService;
import com.enterprise.service.user.ICompanyInfoService;
import com.util.CookieUtil;
import com.util.HttpPostUtil;
import com.util.JsonUtil;
import com.util.LogUtil;
import com.util.PropUtil;
import com.util.StringUtil;
import com.util.TimeUtil;
import com.util.UuidUtil;

/**
 * 宣讲会操作类
 * 
 * 对应操作EntLiveInfo模型
 * @author Mr-H
 *
 */
@Controller
@RequestMapping("entMeetingNoticeController")
public class EntMeetingNoticeController {

	Logger log = Logger.getLogger(EntMeetingNoticeController.class.getName());
	
//	@Autowired
//	private IEntMeetingNoticeService meetingService;//宣讲会
	@Autowired
	private ICommonAnnexService annexService;//附件
	@Autowired
	private IEntLiveInfoService liveInfiService;//点播直播
	@Autowired
	private ICompanyInfoService companyInfoService;
	@Autowired
	private IFeedService feedService;
	
	/**
	 * 宣讲会预告列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/meetingList")  
	@ResponseBody
	public Json meetingList(Page page, HttpServletRequest request){
		try {
			Json json = new Json();
			String cookie_companyId = CookieUtil.getCookieInfoByKey(request, Constant.USER_ORG_ID);
			HashMap<String,Object> map = new HashMap<String, Object>();
			map.put("companyId", cookie_companyId);
			map.put("type", Constant.VALUE_ONE);
			map.put("page_start", (page.getPage()-1) * 3);
			map.put("page_size", 3);
			List<EntLiveInfo> list = liveInfiService.getList(map);
			List<Map<String,Object>> listCounts = liveInfiService.getListCounts(map);
			String counts = "0";
			if(list.size() > 0) {
				map.clear();
				map.put("list",list);
				if(listCounts.size() > 0){
					counts = String.valueOf(listCounts.get(0).get(Constant.COUNT));
				}
				map.put("counts", counts);
				json.setSuccess(true);
				json.setObj(map);
			}else{
				json.setSuccess(false);
			}
			return json;
		} catch (Exception e) {
			log.info(LogUtil.getTrace(e));;
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 宣讲会发布预告
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/add")  
	@ResponseBody
	public Json add(HttpServletRequest request, EntLiveInfo meeting, String xjhId, 
			String xjhAnnexId, String stratTimeStr, String endTimeStr){
		try {
			Json json = new Json();
			String cookie_companyId = CookieUtil.getCookieInfoByKey(request, Constant.USER_ORG_ID);
			String cookie_uId = CookieUtil.getCookieInfoByKey(request, Constant.USER_UID);
			CompanyInfo info = companyInfoService.selectByPrimaryKey(cookie_companyId);
			HashMap<String,Object> map = new HashMap<String, Object>();
//			SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
			if(StringUtil.isNotEmpty(xjhAnnexId)){
				xjhAnnexId = StringUtil.replaceBlank(xjhAnnexId);
			}
			String feedType = "";
			if(StringUtil.isEmpty(xjhId)){
				String  xjhIdNew = UuidUtil.getUUID();
				if(meeting.getType() == Constant.OPER_ONE){
					meeting.setStratTime(stratTimeStr);
					meeting.setEndTime(endTimeStr);
					//宣讲会预告
					map.put("object_id", CookieUtil.getCookieInfoByKey(request, Constant.USER_UID));
					map.put("create_time", TimeUtil.currentTimeMillis());
					//创建房间
					int roomFlag = liveInfiService.insertRoom(map);
					if(roomFlag == Constant.OPER_ONE){
						meeting.setRoomId(Integer.valueOf(map.get("id").toString()));
					}
					feedType = FeedStatus.FEED_ENT_XJH_YG;
				}else if(meeting.getType() == Constant.OPER_THTEE){
					//宣讲会点播
					map.clear();
					map.put("video_id", xjhIdNew);
					map.put("video_url", xjhAnnexId);
					meeting.setStratTime(stratTimeStr);
					liveInfiService.insertVedio(map);
					feedType = FeedStatus.FEED_ENT_XJH_DB;
				}
				//宣讲会数据插入
				meeting.setCreateTime(TimeUtil.currentTimeMillis());
				meeting.setOrganizationId(cookie_companyId);
				meeting.setId(xjhIdNew);
				int i = liveInfiService.insertSelective(meeting);
				if(i == Constant.OPER_ONE ){
					json.setSuccess(true);
					//创建索引
					createIndex(AppSearchConstant.APP_SEARCH_LIVE_CREATE, meeting, info);
					//创建消息流
					Feed feed = new Feed();
					feed.setId(UuidUtil.getUUID());
					feed.setFeedType(Integer.valueOf(Constant.VALUE_ZERO));
					feed.setResourceActType(Integer.valueOf(feedType));
					feed.setUid(cookie_uId);
					feed.setCreateUid(cookie_companyId);
					feed.setResourceTable(FeedStatus.T_FEED_MEETING);
					feed.setResourceId(xjhIdNew);
					feed.setCreateTime(TimeUtil.currentTimeMillis());
					createFeed(feed);
				}
			}else{
				/**
				 * 编辑
				 */
				meeting.setCreateTime(TimeUtil.currentTimeMillis());
				meeting.setId(xjhId);
				if(StringUtil.isNotEmpty(stratTimeStr)){
					meeting.setStratTime(stratTimeStr);
				}
				if(StringUtil.isNotEmpty(endTimeStr)){
					meeting.setEndTime(endTimeStr);
				}
				liveInfiService.updateByPrimaryKeySelective(meeting);
				map.clear();
				map.put("videoUrl", xjhAnnexId);
				map.put("videoId", xjhId);
				liveInfiService.updateVedio(map);
				json.setSuccess(true);
				//创建索引
				createIndex(AppSearchConstant.APP_SEARCH_LIVE_UPDATE, meeting, info);
			}
			return json;
		} catch (Exception e) {
			log.info(LogUtil.getTrace(e));;
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 创建消息流
	 * @param feed
	 */
	public void createFeed(Feed feed){
		try {
			feedService.insertSelective(feed);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 创建索引
	 * @param url
	 * @param function
	 */
	public void createIndex(String url, EntLiveInfo function, CompanyInfo info){
		try {
			
			Map<String, Object> mapLunece = new HashMap<String, Object>();
			String luneceTaskId = UuidUtil.getUUID();
			mapLunece.put("id", luneceTaskId);
			mapLunece.put("obj_id", function.getId());//资源id
			mapLunece.put("obj_type", 2);//对象类型(0-公司,1-职位,2-视频,3-学生,4-学校
			mapLunece.put("opt", 0);//操作类型(0-add,1-delete,2-update
			mapLunece.put("create_time", TimeUtil.currentTimeMillis()+"");//
			mapLunece.put("status", 0);//处理状态（0-未处理,1-已处理
			mapLunece.put("handle_time", TimeUtil.currentTimeMillis()+"");//处理时间
			
			PropUtil prop = new PropUtil(Constant.PRO_FILE_CONSTANTS);
			
			String result = HttpPostUtil.doPost(prop.getValue(AppSearchConstant.APP_SEARCH_URL)
					+ AppSearchConstant.SEARCH_TASK_URL, mapLunece);
			
			String code = JsonUtil.jsonToObj(result, StautsBean.class).getCode();
			
			if(StringUtil.isNotEmpty(code) && code.equals(Constant.VALUE_ZERO)){
				Map<String,Object> map = new HashMap<String,Object>();
				map.put(AppSearchConstant.APP_SEARCH_ID, function.getId());
				map.put(AppSearchConstant.SUBJECT, function.getSubject());
				map.put(AppSearchConstant.OFFICE_AREA, info.getOfficeArea());
				map.put(AppSearchConstant.INDUSTRY_CODE, info.getIndustryCode());
				map.put(AppSearchConstant.START_TIME, function.getStratTime());
				
				String s = HttpPostUtil.doPost(prop.getValue(AppSearchConstant.APP_SEARCH_URL)
						+ url + luneceTaskId, map);
				System.out.println("索引创建信息： " + s);
			}
			
		} catch (Exception e) {
			log.info(LogUtil.getTrace(e));
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 删除
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/del")  
	@ResponseBody
	public Json del(HttpServletRequest request, String meetingId){
		try {
			Json json = new Json();
			PropUtil prop = new PropUtil(Constant.PRO_FILE_CONSTANTS);
			String cookie_companyId = CookieUtil.getCookieInfoByKey(request, Constant.USER_ORG_ID);
			EntLiveInfo meeting = liveInfiService.selectByPrimaryKey(meetingId);
			meeting.setEnable(Byte.valueOf(Constant.VALUE_ONE));
			liveInfiService.updateByPrimaryKeySelective(meeting);
			
			//创建索引
			HttpPostUtil.doPostResetFul(prop.getValue(AppSearchConstant.APP_SEARCH_URL) 
					+ AppSearchConstant.APP_SEARCH_LIVE_DELETE +  meetingId + "?task_id=" + UuidUtil.getUUID());
			
			HashMap<String,Object> map = new HashMap<String, Object>();
			map.put("companyId", cookie_companyId);
			List<Map<String,Object>> listCounts = liveInfiService.getListCounts(map);
			String counts = "0";
				if(listCounts.size() > 0){
					counts = String.valueOf(listCounts.get(0).get(Constant.COUNT));
					json.setObj(counts);
					json.setSuccess(true);
				}
				
			feedService.delFeedByResourceId(meetingId);
			return json;
		} catch (Exception e) {
			log.info(LogUtil.getTrace(e));;
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * edit页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/edit")  
	@ResponseBody
	public Json edit(HttpServletRequest request, String meetingId){
		try {
			Json json = new Json();
//			EntMeetingNotice meeting = meetingService.selectByPrimaryKey(meetingId);
			HashMap<String,Object> map = new HashMap<String, Object>();
			map.put("meetingId", meetingId);
			Map<String,Object> mapSelect = liveInfiService.getMap(map);
			if(mapSelect == null){
				json.setSuccess(false);
			}else{
				json.setSuccess(true);
				json.setObj(mapSelect);
			}
			return json;
		} catch (Exception e) {
			log.info(LogUtil.getTrace(e));;
			e.printStackTrace();
			return null;
		}
	}
}
