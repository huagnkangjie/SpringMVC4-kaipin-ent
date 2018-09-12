package com.kaipin.common.presentation.action.feed;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kaipin.common.constants.AppSearchConstant;
import com.kaipin.common.constants.Constant;
import com.kaipin.common.constants.FeedStatus;
import com.kaipin.common.entity.Json;
import com.kaipin.common.entity.User;
import com.kaipin.common.presentation.action.BaseAction;
import com.kaipin.university.model.feed.Feed;
import com.kaipin.university.model.feed.FeedComment;
import com.kaipin.university.model.user.UserLocalauth;
import com.kaipin.university.model.vedio.LiveInfo;
import com.kaipin.university.service.feed.IFeedBaseService;
import com.kaipin.university.service.feed.IFeedCommentService;
import com.kaipin.university.service.feed.IFeedService;
import com.kaipin.university.service.user.ISchBaseUserService;
import com.kaipin.university.service.user.IUserLocalauthService;
import com.kaipin.university.service.vedio.ILiveInfoService;
import com.kaipin.common.util.CookieUtil;
import com.kaipin.common.util.HttpPostUtil;
import com.kaipin.common.util.LuceneUtil;
import com.kaipin.common.util.PropUtil;
import com.kaipin.common.util.StringUtil;
import com.kaipin.common.util.TimeUtil;
import com.kaipin.common.util.UuidUtil;

/**
 * 信息流
 * @author Mr-H
 *
 */
@Controller
@RequestMapping("/feeds")
public class FeedAction extends BaseAction{
	
	@Autowired
	private IFeedBaseService feedBaseService;
	@Autowired
	private IFeedService feedService;
	@Autowired
	private IFeedCommentService feedCommentService;
	@Autowired
	private IUserLocalauthService localUserService;
	@Autowired
	private ISchBaseUserService schBaseUserService;
	@Autowired
	private ILiveInfoService liveInfoService;
	
	public static String LOGO = "logo";
	public static String NAME = "name";

	/**
	 * 获取信息信息流
	 * @return
	 */
	@RequestMapping("/getFeeds")
	@ResponseBody
	public Json getFeeds(HttpServletRequest request, String page, User user, String oper){
		Json json = new Json();
		try {
			Thread.sleep(1000);
			String cookie_sid = super.getOrgId(request, user);
			String cookie_uid = CookieUtil.getCookieInfoByKey(request, Constant.USER_UID);
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("orgId", cookie_sid);
			map.put("page_start", (Integer.valueOf(page) - 1) * 20);
			map.put("page_size", 20);
			List<Map<String,Object>> list = null;
			
			int count = 0;//总共有多少条动态信息
			if(StringUtil.isNotEmpty(oper) && oper.equals("getFeedByOrgId")){
				list = feedBaseService.getFeedByOrgId(map);
				count = feedBaseService.getFeedByOrgIdCount(map);//总共有多少条动态信息
			}else{
				list = feedBaseService.getFeeds(map);
				count = feedBaseService.getFeedsCount(map);//总共有多少条动态信息
			}
			
			List<Map<String,Object>> resuletList = new ArrayList<Map<String,Object>>();
			Map<String,Object> resultMapVal = new HashMap<String,Object>();
			String feedType ;
			if(list.size() > 0){
				for (Map<String, Object> mapVal : list) {
					Map<String,Object> resultMap = new HashMap<String,Object>();
					Object oFeedType = mapVal.get("resource_act_type");
					feedType = String.valueOf(oFeedType);
					resultMap = getResultMap(request, mapVal, feedType);
					if(resultMap != null){
						resuletList.add(resultMap);
					}
				}
			}
			
			resultMapVal.put("feed", resuletList);
			resultMapVal.put("count", count);
			json.setObj(resultMapVal);
			
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			return json;
		}
	}
	
	
	/**
	 * 获取前台消息流展示封装数据
	 * @return
	 */
	public Map<String,Object> getResultMap(HttpServletRequest request, Map<String, Object> map, String feedType){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			String cookie_uid = CookieUtil.getCookieInfoByKey(request, Constant.USER_UID);
			
			String f_id = String.valueOf(map.get("id"));
			resultMap.put("f_id", f_id);//动态id
			resultMap.put("f_act_type", map.get("resource_act_type"));//动态类型
			resultMap.put("f_org_id", map.get("create_uid"));//动态产生的组织id
			resultMap.put("f_logo", getUserInfo(map.get("uid"),map.get("create_uid"),"logo"));//动态logo
			resultMap.put("org_name", getUserInfo(map.get("uid"),map.get("create_uid"),"name"));//动态产生的组织名称
			resultMap.put("resource_id", map.get("resource_id"));
			
			List<Map<String,Object>> listSource = null;
			List<Map<String,Object>> commentList = new ArrayList<Map<String,Object>>();
			//该动态的第一级回复
			String comment_count = map.get("comment_count")+"";
			//视频动态
			if(feedType.equals(FeedStatus.FEED_ENT_XJH_DB) || //企业点播
					feedType.equals(FeedStatus.FEED_SCH_XJH_DB))//学校点播
			{
				listSource = 
						feedBaseService.getLiveInfo(map.get("resource_id")+"");
				resultMap.put("f_url", listSource.get(0).get("video_url"));//动态url
				resultMap.put("f_title", listSource.get(0).get("subject"));//动态标题
				resultMap.put("f_describe", listSource.get(0).get("memo"));//动态描述
				resultMap.put("f_img", listSource.get(0).get("cover_image_path"));//封面
				
				resultMap.put("is_digg", isDigg(f_id, cookie_uid));//是否点赞 文字显示
				
				//获取评论列表
				if(!comment_count.equals(Constant.VALUE_ZERO)){
					commentList = getCommentList(f_id);
				}
			//预告动态
			}else if(feedType.equals(FeedStatus.FEED_ENT_XJH_YG)){
				listSource = 
						feedBaseService.getLiveInfo(map.get("resource_id")+"");
				resultMap.put("f_url", "");//动态url
				resultMap.put("f_title", listSource.get(0).get("subject"));//动态标题
				resultMap.put("f_describe", listSource.get(0).get("memo"));//动态描述
				resultMap.put("f_img", listSource.get(0).get("cover_image_path"));//封面
				
				resultMap.put("is_digg", isDigg(f_id, cookie_uid));//是否点赞 文字显示
				
				String f_start_time = listSource.get(0).get("strat_time")+"";
				resultMap.put("f_start_time", f_start_time.substring(0, 16));//预告开始时间
				//判断当前动态是否过期
				
				long currentTime = TimeUtil.currentTimeMillis();
				long f_start_timeL = TimeUtil.currentTimeMillisByTime(f_start_time);
				if((currentTime - f_start_timeL) > 0){
					return null;
				}
				
				//获取评论列表
				if(!comment_count.equals(Constant.VALUE_ZERO)){
					commentList = getCommentList(f_id);
				}
			//职位动态	
			}else if(feedType.equals(FeedStatus.FEED_ENT_POSITION)){
				listSource = 
						feedBaseService.getPosition(map.get("resource_id")+"");
				resultMap.put("f_url", "");//动态url
				resultMap.put("f_title", listSource.get(0).get("position_name"));//动态标题
				resultMap.put("f_describe", "");//动态描述
				resultMap.put("f_resource_id", map.get("resource_id"));//职位id
				
			}
			
			
			
			resultMap.put("digg_count", map.get("digg_count"));//点赞数
			resultMap.put("sub_count", map.get("sub_count"));//订阅数
			resultMap.put("comment_count", map.get("comment_count"));//评论数
			
			resultMap.put("create_time", map.get("create_time"));//创建时间
			
			
			resultMap.put("comment_list", commentList);//回复评论
			
			return resultMap;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 获取一级回复
	 * @return
	 */
	public List<Map<String,Object>> getCommentList(String f_id){
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		try {
			//查询该动态所有的一级回复
			List<Map<String,Object>> commenList = feedBaseService.getCommentsLevelOne(f_id);
			if(commenList.size() > 0){
				for (int i = 0; i < commenList.size(); i++) {
					Map<String,Object> commentsOneMap = new HashMap<String,Object>();
					String id = commenList.get(i).get("id")+"";//动态的id
					String uid = commenList.get(i).get("uid")+"";//一级回复的uid
					UserLocalauth localUser = localUserService.selectByPrimaryKey(uid);
					if(localUser == null) continue;
					String orgId = localUserService.selectByPrimaryKey(uid).getOrganizationId();
					//查询二级回复
					
					List<Map<String,Object>> listTwo = feedBaseService.getCommentsLevelTwo(id);
					commentsOneMap.put("comment_list", getCommentLevelTwoList(listTwo));
					
					commentsOneMap.put("commentLevelOne", commenList.get(i));
					commentsOneMap.put("logo", getUserInfo(uid, null, LOGO));
					commentsOneMap.put("name", getUserInfo(uid, null, NAME));
					commentsOneMap.put("orgId", orgId);
					list.add(commentsOneMap);
				}
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return list;
		}
	}
	
	/**
	 * 遍历二级回复
	 * @param list
	 * @return
	 */
	public List<Map<String,Object>> getCommentLevelTwoList(List<Map<String,Object>> list){
		List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
		try {
			if(list.size() > 0){
				for (int i = 0; i < list.size(); i++) {
					Map<String,Object> commentsTwoMap = new HashMap<String,Object>();
					String uid = list.get(i).get("uid")+"";
					String orgId = localUserService.selectByPrimaryKey(uid).getOrganizationId();
					//查询二级回复
					commentsTwoMap.put("commentLevelTwo", list.get(i));
					commentsTwoMap.put("logo", getUserInfo(uid, null, LOGO));
					commentsTwoMap.put("name", getUserInfo(uid, null, NAME));
					commentsTwoMap.put("orgId", orgId);
					resultList.add(commentsTwoMap);
				}
			}
			return resultList;
		} catch (Exception e) {
			e.printStackTrace();
			return resultList;
		}
	}
	
	
	/**
	 * 获取用户信息
	 * @param uid 用户id
	 * @param create_uid 组织id
	 * @return
	 */
	public String getUserInfo(Object uid,Object create_uid, String type){
		try {
			Object resultLogo = "";
			String resultName = "";
			String result = "";
			UserLocalauth user = localUserService.selectByPrimaryKey(String.valueOf(uid));
			String u_type = user.getCategoryId();
			if(u_type.equals(Constant.USER_TYPE_ENT)){
				if(create_uid == null){
					create_uid = schBaseUserService.getCompanyInfoByUserId(
							String.valueOf(uid)).get(0).get("id");
				}
				
				List<Map<String,Object>> list = feedBaseService.getCompanyInfo(String.valueOf(create_uid));
				resultLogo = list.get(0).get("logo");
				resultName = list.get(0).get("ent_simple_name")+"";
				if(StringUtil.isEmpty(resultName)){
					resultName = list.get(0).get("ent_name")+"";
				}
				
			}else if(u_type.equals(Constant.USER_TYPE_SCH)){
				if(create_uid == null){
					create_uid = schBaseUserService.getSchoolInfoByUserId(
							String.valueOf(uid)).get(0).get("id");
				}
				
				List<Map<String,Object>> list = feedBaseService.getSchInfo(String.valueOf(create_uid));
				resultLogo = list.get(0).get("school_logo");
				resultName = list.get(0).get("school_short_name")+"";
				
			}else if(u_type.equals(Constant.USER_TYPE_STU)){
				
				List<Map<String,Object>> list = feedBaseService.getStuInfo(String.valueOf(uid));
				resultLogo = list.get(0).get("head_url");
				resultName = list.get(0).get("surname")+""+list.get(0).get("miss_surname");
				if(resultName.startsWith("null") || resultName.endsWith("null")){
					resultName = (list.get(0).get("nick_name")+"").replace(",", "");
				}
			}
			
			if(type.equals(LOGO)){
				if(resultLogo == null || resultLogo.equals("")){
					result = Constant.VALUE_ZERO;
				}else{
					result = String.valueOf(resultLogo);
					if(!result.startsWith("http")){
						PropUtil pro = new PropUtil(Constant.PRO_FILE_PATH);
						result = pro.getValue(Constant.STU_HEAD_URL) + result;
					}
				}
			}else if(type.equals(NAME)){
				result = resultName;
			}
				
			return result;
		} catch (Exception e) {
			return "0";
		}
	}
	
	/**
	 * 获取点赞文字
	 * @param f_id
	 * @param uid
	 * @return
	 */
	public String isDigg(String f_id, String uid){
		String digg = "赞";
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("uid", uid);
			map.put("f_id", f_id);
			int isDigg = feedBaseService.checkFeedZan(map);
			if(isDigg == 0){
				 digg = "赞";
			}else if(isDigg == 1){
				 digg = "已赞";
			}
			return digg;
		} catch (Exception e) {
			return digg;
		}
	}
	
	/**
	 * 获取点赞总数
	 * @param f_id
	 * @return
	 */
	public int getFeedZanCount(String f_id){
		try {
			return feedBaseService.getFeedZanCount(f_id);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	/**
	 * 点赞
	 * @return
	 */
	@RequestMapping("/addZan")
	@ResponseBody
	public Json addZan(HttpServletRequest request,String f_id){
		Json json = new Json();
		try {
			String status = "add";
			int count = 0;
			String cookie_uid = CookieUtil.getCookieInfoByKey(request, Constant.USER_UID);
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("uid", cookie_uid);
			map.put("f_id", f_id);
			int flag = feedBaseService.checkFeedZan(map);
			Feed feed = feedService.selectByPrimaryKey(f_id);
			feed.setId(f_id);
			if(flag == 0 && feed != null){//点赞
				map.put("id", UuidUtil.getUUID());
				map.put("create_time", TimeUtil.currentTimeMillis());
				int i = feedBaseService.insertFeedZan(map);
				if( i == 1){
					count = feed.getDiggCount() + 1;
					feed.setDiggCount(count);
					feedService.updateByPrimaryKeySelective(feed);
					status = "add";
					json.setSuccess(true);
				}
			}else if(flag == 1){//取消赞
				int i = feedBaseService.delFeedZan(map);
				if( i == 1){
					count = feed.getDiggCount() - 1;
					feed.setDiggCount(count);
					feedService.updateByPrimaryKeySelective(feed);
					status = "del";
					json.setSuccess(true);
				}
			}
			json.setObj(status);
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			return json;
		}
	}
	
	/**
	 * 添加评论
	 * @param request
	 * @return
	 */
	@RequestMapping("/addComment")
	@ResponseBody
	public Json addComment(HttpServletRequest request, FeedComment comment,String oper){
		Json json = new Json();
		try {
			String cookie_uid = CookieUtil.getCookieInfoByKey(request, Constant.USER_UID);
			String cookie_sid = CookieUtil.getCookieInfoByKey(request, Constant.USER_ORG_ID);
			comment.setId(UuidUtil.getUUID());
			comment.setCreateTime(TimeUtil.currentTimeMillis());
			comment.setUid(cookie_uid);
			
			int i = feedCommentService.insertSelective(comment);
			if(i == 1){
				String feedId = comment.getFeedId();
				Feed feed = feedService.selectByPrimaryKey(feedId);
				feed.setId(feedId);
				feed.setCommentCount(feed.getCommentCount() + 1);
				feedService.updateByPrimaryKeySelective(feed);
				
				String name = getUserInfo(comment.getToUid(), null, NAME);
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("name", name);
				map.put("comment", comment);
				json.setSuccess(true);
				json.setObj(map);
				json.setMsg(cookie_sid);//当前回复的组织id
			}
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			return json;
		}
	}
	
	/**
	 * 点击组织名称跳转
	 * @return
	 */
	@RequestMapping("/targetOrg")
	public void targetOrg(HttpServletRequest request, HttpServletResponse response, String orgId){
		String url = request.getContextPath();
		String targetUrl = url + "/home";
		try {
			if(StringUtil.isNotEmpty(orgId)){
				UserLocalauth localUser = localUserService.selectByOrgId(orgId);
				if(localUser == null){
					localUser = localUserService.selectByPrimaryKey(orgId);
					orgId = localUser.getOrganizationId();
				}
				String u_id = localUser.getId();
				if(localUser != null){
					String uType = localUser.getCategoryId();
					switch (uType) {
					case "10":
						targetUrl = url + "/resume/show?org_id="+orgId; break;
					case "11":
						targetUrl = url + "/show?org_id="+orgId+"&u_id="+u_id ; break;
					case "12":
						targetUrl = url + "/basic/show?org_id="+orgId+"&u_id="+u_id ; break;
						
					default: targetUrl = url + "/home";break;
					}
				}
				response.sendRedirect(targetUrl);//跳转到目标地址
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			try {
				response.sendRedirect(targetUrl);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	
	/**
	 * 删除动态
	 * @param request
	 * @param fId
	 * @return
	 */
	@RequestMapping("/delFeed")
	@ResponseBody
	public Json delFeed(HttpServletRequest request, String feedId, String resourceId, String type){
		Json json = new Json();
		try {
			if(StringUtil.isNotEmpty(feedId)){
				Feed feed = feedService.selectByPrimaryKey(feedId);
				feed.setId(feedId);
				feed.setIsDel(Byte.valueOf(Constant.VALUE_ONE));
				
				//更新动态状态
				feedService.updateByPrimaryKeySelective(feed);
				
				//更新该动态的所有评论
				Map<String, Object> map = new HashMap<>();
				map.put("feedId", feedId);
				feedBaseService.deleteFeedComment(map);
				
				//更新索引
				if(StringUtil.isNotEmpty(type) && type.equals("video")){
					
					LuceneUtil.luceneOpt(feed.getResourceId(), Constant.VALUE_TWO, Constant.VALUE_ONE);
					
					
				}else if(StringUtil.isNotEmpty(type) && type.equals("meeting")){
					
				}
				
				LiveInfo live = new LiveInfo();
				live.setId(feed.getResourceId());
				live.setEnable(Byte.valueOf(Constant.VALUE_ONE));
				liveInfoService.updateByPrimaryKeySelective(live);
				
				json.setSuccess(true);
				
			}
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			return json;
		}
	}
	
	/**
	 * 删除动态评论
	 * @param request
	 * @param feedId
	 * @return
	 */
	@RequestMapping("/delFeedComment")
	@ResponseBody
	public Json delFeedComment(HttpServletRequest request, String feedId, String commentId){
		Json json = new Json();
		try {
			if(StringUtil.isNotEmpty(commentId) && StringUtil.isNotEmpty(feedId)){
				//删除当前评论
				feedBaseService.deleteCommentById(commentId);
				
				//更改评论数
				Feed feed = feedService.selectByPrimaryKey(feedId);
				int count = feed.getCommentCount();
				int countLevelTwo = feedBaseService.getCommentLevelTowCount(commentId);
				count = count - (countLevelTwo + 1);
				
				feed.setCommentCount(count);
				feedService.updateByPrimaryKeySelective(feed);
				
				//删除当前评论下的所有二级评论
				feedBaseService.deleteCommentByParentId(commentId);
				
				
				json.setSuccess(true);
				json.setObj(count);
				
			}
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			return json;
		}
	}
}
