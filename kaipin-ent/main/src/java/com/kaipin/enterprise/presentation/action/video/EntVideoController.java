package com.kaipin.enterprise.presentation.action.video;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kaipin.common.constants.AppSearchConstant;
import com.kaipin.common.constants.Constant;
import com.kaipin.common.constants.FeedStatus;
import com.kaipin.common.entity.Json;
import com.kaipin.common.entity.Page;
import com.kaipin.common.entity.StautsBean;
import com.kaipin.common.entity.User;
import com.kaipin.common.presentation.action.BaseAction;
import com.kaipin.common.util.CookieUtil;
import com.kaipin.common.util.HttpPostUtil;
import com.kaipin.common.util.JsonUtil;
import com.kaipin.common.util.LuceneUtil;
import com.kaipin.common.util.PropUtil;
import com.kaipin.common.util.StringUtil;
import com.kaipin.common.util.TimeUtil;
import com.kaipin.common.util.UuidUtil;
import com.kaipin.enterprise.model.user.CompanyInfo;
import com.kaipin.enterprise.service.user.ICompanyInfoService;
import com.kaipin.university.model.feed.Feed;
import com.kaipin.university.model.user.UserLocalauth;
import com.kaipin.university.model.vedio.LiveInfo;
import com.kaipin.university.service.feed.IFeedBaseService;
import com.kaipin.university.service.feed.IFeedService;
import com.kaipin.university.service.user.ISchoolInfoLinkService;
import com.kaipin.university.service.user.ISchoolInfoService;
import com.kaipin.university.service.user.IUserLocalauthService;
import com.kaipin.university.service.vedio.ILiveInfoService;
/**
 * 企业视频综合管理类
 * @author Mr-H
 *
 */
@RequestMapping("/video")
@Controller
public class EntVideoController extends BaseAction{
	
	@Autowired
	private ICompanyInfoService companyInfoService;
	@Autowired
	private ILiveInfoService liveInfoService;
	@Autowired
	private IFeedService feedService;
	@Autowired
	private IUserLocalauthService localUserService;
	@Autowired
	private ISchoolInfoLinkService schLinkService;
	@Autowired
	private ISchoolInfoService schInfoService;
	@Autowired
	private IFeedBaseService feedBaseService;

	/**
	 * 视频列表页
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("")
	public String videoPage(HttpServletRequest request, Model model, User user){
		try {
			String orgId = this.getOrgId(request, user);
			CompanyInfo info = companyInfoService.selectByPrimaryKey(orgId);
			String orgName = info.getEntName();
			
			model.addAttribute("entName", orgName);
			model.addAttribute("index", "index");
			
			this.setSysAttr(model, orgName, null, null);
			return "/ent/video/vedio_list";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
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
			List<LiveInfo> list = liveInfoService.getList(map);
			List<Map<String,Object>> listCounts = liveInfoService.getListCounts(map);
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
	public Json add(HttpServletRequest request, LiveInfo meeting, String xjhId, 
			String xjhAnnexId, String stratTimeStr, String endTimeStr){
		try {
			Json json = new Json();
			String cookie_companyId = CookieUtil.getCookieInfoByKey(request, Constant.USER_ORG_ID);
			String cookie_uId = CookieUtil.getCookieInfoByKey(request, Constant.USER_UID);
			UserLocalauth localUser = localUserService.selectByPrimaryKey(cookie_uId);
			CompanyInfo info = companyInfoService.selectByPrimaryKey(cookie_companyId);
			HashMap<String,Object> map = new HashMap<String, Object>();
//			SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
			if(StringUtil.isNotEmpty(xjhAnnexId)){
				xjhAnnexId = StringUtil.replaceBlank(xjhAnnexId);
			}
			String feedType = "";
			String  xjhIdNew = "";
			if(StringUtil.isEmpty(xjhId)){
				xjhIdNew = UuidUtil.getUUID();
				meeting.setId(xjhIdNew);
				meeting.setUserType(localUser.getCategoryId());
				if(meeting.getType() == Constant.OPER_ONE){
					meeting.setStratTime(stratTimeStr);
					meeting.setEndTime(stratTimeStr);
					
					//宣讲会预告
					map.put("object_id", CookieUtil.getCookieInfoByKey(request, Constant.USER_UID));
					map.put("create_time", TimeUtil.currentTimeMillis());
					//创建房间
					int roomFlag = liveInfoService.insertRoom(map);
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
					liveInfoService.insertVedio(map);
					feedType = FeedStatus.FEED_ENT_XJH_DB;
					
					//创建索引
					LuceneUtil.luceneOpt(xjhIdNew, Constant.VALUE_TWO, Constant.VALUE_ZERO);
				}
				//宣讲会数据插入
				meeting.setCreateTime(TimeUtil.currentTimeMillis());
				meeting.setOrganizationId(cookie_companyId);
				
				int i = liveInfoService.insertSelective(meeting);
				if(i == Constant.OPER_ONE ){
					json.setSuccess(true);
				}
			}else{
				/**
				 * 编辑
				 */
				xjhIdNew = xjhId;
				meeting.setCreateTime(TimeUtil.currentTimeMillis());
				meeting.setId(xjhId);
				if(StringUtil.isNotEmpty(stratTimeStr)){
					meeting.setStratTime(stratTimeStr);
					meeting.setEndTime(stratTimeStr);
				}
				if(StringUtil.isNotEmpty(endTimeStr)){
					meeting.setEndTime(endTimeStr);
				}
				liveInfoService.updateByPrimaryKeySelective(meeting);
				map.clear();
				map.put("videoUrl", xjhAnnexId);
				map.put("videoId", xjhId);
				liveInfoService.updateVedio(map);
				json.setSuccess(true);
				
				//创建索引
				LuceneUtil.luceneOpt(xjhId, Constant.VALUE_TWO, Constant.VALUE_TWO);
				
				//删除消息动态
				LiveInfo liveInfo = liveInfoService.selectByPrimaryKey(xjhIdNew);
				String type = liveInfo.getType() + "";
				if(type.equals(Constant.VALUE_ONE)){
					feedType = FeedStatus.FEED_ENT_XJH_YG;
				}else if(type.equals(Constant.VALUE_TWO)){
					feedType = FeedStatus.FEED_ENT_XJH_ZB;
				}else if(type.equals(Constant.VALUE_THREE)){
					feedType = FeedStatus.FEED_ENT_XJH_DB;
				}
				feedService.delFeedByResourceId(xjhIdNew);
			}
			
			//创建消息流
			Feed feed = new Feed();
			feed.setId(UuidUtil.getUUID());
			feed.setResourceActType(Integer.valueOf(feedType));
			feed.setFeedType(Integer.valueOf(Constant.VALUE_ZERO));
			feed.setUid(cookie_uId);
			feed.setCreateUid(cookie_companyId);
			feed.setResourceTable(FeedStatus.T_FEED_MEETING);
			feed.setResourceId(xjhIdNew);
			feed.setCreateTime(TimeUtil.currentTimeMillis());
			createFeed(feed);
			
			
			return json;
		} catch (Exception e) {
			
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
	public void createIndex(String url, LiveInfo function, CompanyInfo info){
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
//				map.put(AppSearchConstant.OFFICE_AREA, info.getOfficeArea());
//				map.put(AppSearchConstant.INDUSTRY_CODE, info.getIndustryCode());
				map.put(AppSearchConstant.START_TIME, function.getStratTime());
				
				HttpPostUtil.doPost(prop.getValue(AppSearchConstant.APP_SEARCH_URL)
						+ url + luneceTaskId, map);
			}
		} catch (Exception e) {
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
			LiveInfo meeting = liveInfoService.selectByPrimaryKey(meetingId);
			meeting.setIsDel(Byte.valueOf(Constant.VALUE_ONE));
			liveInfoService.updateByPrimaryKeySelective(meeting);
			
			//创建索引
			LuceneUtil.luceneOpt(meetingId, Constant.VALUE_TWO, Constant.VALUE_ONE);
			
			HashMap<String,Object> map = new HashMap<String, Object>();
			map.put("companyId", cookie_companyId);
			List<Map<String,Object>> listCounts = liveInfoService.getListCounts(map);
			String counts = "0";
				if(listCounts.size() > 0){
					counts = String.valueOf(listCounts.get(0).get(Constant.COUNT));
					json.setObj(counts);
					json.setSuccess(true);
				}
				
			// 删除动态
			feedService.updateFeedByResourceId(meetingId);
			
			return json;
		} catch (Exception e) {
			
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
	public Json editPage(HttpServletRequest request, String meetingId){
		try {
			Json json = new Json();
//			EntMeetingNotice meeting = meetingService.selectByPrimaryKey(meetingId);
			HashMap<String,Object> map = new HashMap<String, Object>();
			map.put("meetingId", meetingId);
			Map<String,Object> mapSelect = liveInfoService.getMap(map);
			if(mapSelect == null){
				json.setSuccess(false);
			}else{
				json.setSuccess(true);
				json.setObj(mapSelect);
			}
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
