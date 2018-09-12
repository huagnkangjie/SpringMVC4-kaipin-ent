package com.kaipin.university.importdata;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.kaipin.common.constants.Constant;
import com.kaipin.common.constants.FeedStatus;
import com.kaipin.common.service.common.IBaseCodeService;
import com.kaipin.common.util.TimeUtil;
import com.kaipin.common.util.UuidUtil;
import com.kaipin.university.JUnitBase;
import com.kaipin.university.model.feed.Feed;
import com.kaipin.university.model.feed.FeedComment;
import com.kaipin.university.service.feed.IFeedBaseService;
import com.kaipin.university.service.feed.IFeedCommentService;
import com.kaipin.university.service.feed.IFeedService;

public class FeedImportTest extends JUnitBase{
	
	@Autowired
	private IBaseCodeService service;
	@Autowired
	private IFeedBaseService feedBaseService;
	@Autowired
	private IFeedService feedService;
	@Autowired
	private IFeedCommentService feedCommentService;

	@Test
	public void test(){
		
//		importPosition();//导入职位
		
		//修改company_id ==> organization_id 新增 user_type
//		importLiveInfo();//导入视频
		
//		importLiveDigg();//视频数据点赞
		
//		importLiveComment();//导入评论
		
	}
	
	public void importPosition(){
		String sql =" select "+
					"  		a.*,b.id uid "+
					"  	from  "+
					 " 		position_info a, "+
						"		user_localauth b "+
						"where 1=1 "+
						"	and b.organization_id = a.company_id "+
						"	and a.end_time > LEFT( NOW(),10) "+
						"	and a.status != 3 "+
					"order by a.create_time desc";
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sql", sql);
		List<Map<String, Object>> list = service.getBaseList(map);
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < list.size(); i++) {
			Feed feed = new Feed();
			feed.setId(UuidUtil.getUUID());
			feed.setResourceActType(Integer.valueOf(FeedStatus.FEED_ENT_POSITION));
			feed.setUid(list.get(i).get("uid")+"");
			feed.setFeedType(Integer.valueOf(Constant.VALUE_ZERO));
			feed.setCreateUid(list.get(i).get("company_id")+"");
			feed.setResourceTable(FeedStatus.T_FEED_POSITION);
			feed.setResourceId(list.get(i).get("id")+"");
			feed.setCreateTime(Long.valueOf(list.get(i).get("create_time")+""));
			int flag = feedService.insertSelective(feed);
			if(flag == 0){
				break;
			}
		}
		long endTime = System.currentTimeMillis();
		System.out.println(">>>>>>>>>>>>>>> 总耗时 " + (endTime - startTime));
		System.out.println(">>>>>>>>>>>>>>> 总数据 " + list.size() + " 条");
	}
	
	public void importLiveInfo(){
		String sql =" SELECT "+
					"	a.*, b.*,FROM_UNIXTIME(a.create_time,'%Y-%m-%d %H:%i:%S') createTime, "+
					"	(CASE when (SELECT UNIX_TIMESTAMP()*1000) -  (select UNIX_TIMESTAMP(a.strat_time)*1000) > 0 then '已过期' else '预告' end) sfgq "+
					" FROM "+
					"	(select a.*,c.id uid from live_info a, user_localauth c where a.organization_id = c.organization_id) a "+
					" LEFT JOIN live_video_info b ON a.id = b.video_id "+
					" WHERE "+
					"	1 = 1 "+
					" and type != 2"+
					" AND a.enable = 0 "+
					" ORDER BY "+
					" a.create_time DESC";
	
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sql", sql);
		List<Map<String, Object>> list = service.getBaseList(map);
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < list.size(); i++) {
			Feed feed = new Feed();
			
			feed.setId(UuidUtil.getUUID());
			feed.setFeedType(Integer.valueOf(Constant.VALUE_ZERO));
			
			String feedType = list.get(i).get("type")+"";
			if(feedType.equals("3")){
				feedType = FeedStatus.FEED_ENT_XJH_DB;
			}else if(feedType.equals("1")){
				feedType = FeedStatus.FEED_ENT_XJH_YG;
			}
			
			feed.setResourceActType(Integer.valueOf(feedType));
			feed.setUid(list.get(i).get("uid")+"");
			feed.setCreateUid(list.get(i).get("organization_id")+"");
			feed.setResourceTable(FeedStatus.T_FEED_MEETING);
			feed.setResourceId(list.get(i).get("id")+"");
			feed.setCreateTime(Long.valueOf(list.get(i).get("create_time")+""));
			
			
			int flag = feedService.insertSelective(feed);
			if(flag == 0){
				break;
			}
		}
		long endTime = System.currentTimeMillis();
		System.out.println(">>>>>>>>>>>>>>> 总耗时 " + (endTime - startTime));
		System.out.println(">>>>>>>>>>>>>>> 总数据 " + list.size() + " 条");
	}
	
	
	/**
	 * 导入评论
	 */
	public void importLiveDigg(){
		String sql =" SELECT a.*, d.id feedId FROM  `live_praise` a,live_info b,user_localauth c,feed d "+
					" where a.stu_user_id = c.id and a.video_id = b.id and b.id = d.resource_id ";

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sql", sql);
		List<Map<String, Object>> list = service.getBaseList(map);
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < list.size(); i++) {
			map.clear();
			String f_id = list.get(i).get("feedId")+"";
			map.put("uid", list.get(i).get("stu_user_id"));
			map.put("f_id", f_id);
			int count = 0;
			int flag = feedBaseService.checkFeedZan(map);
			Feed feed = feedService.selectByPrimaryKey(f_id);
			feed.setId(f_id);
			if(flag == 0 && feed != null){//点赞
				map.put("id", list.get(i).get("id"));
				map.put("create_time", list.get(i).get("create_time"));
				int j = feedBaseService.insertFeedZan(map);
				if( j == 1){
					count = feed.getDiggCount() + 1;
					feed.setDiggCount(count);
					feedService.updateByPrimaryKeySelective(feed);
				}
			}
		}
		long endTime = System.currentTimeMillis();
		System.out.println(">>>>>>>>>>>>>>> 总耗时 " + (endTime - startTime));
		System.out.println(">>>>>>>>>>>>>>> 总数据 " + list.size() + " 条");
	}
	
	
	public void importLiveComment(){
		String sql =" SELECT a.*, d.id feedId FROM  live_comment a,live_info b,user_localauth c,feed d "+
				" where a.stu_user_id = c.id and a.video_id = b.id and b.id = d.resource_id ";

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sql", sql);
		List<Map<String, Object>> list = service.getBaseList(map);
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < list.size(); i++) {
			
			FeedComment comment = new FeedComment();
			comment.setId(list.get(i).get("id")+"");
			comment.setFeedId(list.get(i).get("feedId")+"");
			comment.setUid(list.get(i).get("stu_user_id")+"");
			comment.setContent(list.get(i).get("content")+"");
			comment.setCreateTime(Long.valueOf(list.get(i).get("create_time")+""));
			
			
			int j = feedCommentService.insertSelective(comment);
			if(j == 1){
				String feedId = comment.getFeedId();
				Feed feed = feedService.selectByPrimaryKey(feedId);
				feed.setId(feedId);
				feed.setCommentCount(feed.getCommentCount() + 1);
				feedService.updateByPrimaryKeySelective(feed);
				
			}
		}
		long endTime = System.currentTimeMillis();
		System.out.println(">>>>>>>>>>>>>>> 总耗时 " + (endTime - startTime));
		System.out.println(">>>>>>>>>>>>>>> 总数据 " + list.size() + " 条");
	}
}
