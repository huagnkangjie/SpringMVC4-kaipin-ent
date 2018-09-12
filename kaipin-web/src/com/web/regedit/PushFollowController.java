package com.web.regedit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.constants.Constant;
import com.common.pojo.Json;
import com.model.company.CompanyInfo;
import com.model.sch.SchoolInfoLink;
import com.model.sch.SchoolUserInfo;
import com.model.stu.StuUser;
import com.model.user.UserLocalauth;
import com.service.common.ICommonCodeService;
import com.service.common.ICommonPushFollowService;
import com.service.company.ICompanyInfoService;
import com.service.company.IEntBaseUserService;
import com.service.sch.ISchoolBaseUserService;
import com.service.sch.ISchoolInfoLinkService;
import com.service.sch.ISchoolUserInfoService;
import com.service.stu.IStuUserService;
import com.service.user.IUserLocalauthService;
import com.util.LogUtil;
import com.util.PropUtil;
import com.util.StringUtil;
import com.util.TimeUtil;
import com.util.UuidUtil;

/**
 * 推荐
 * @author Mr-H
 *
 */
@Controller
@RequestMapping("/rfollow")
public class PushFollowController {
	
	Logger log = Logger.getLogger(PushFollowController.class.getName());
	
	@Autowired
	private ICommonPushFollowService pushService;
	@Autowired
	private IUserLocalauthService localUserService;
	@Autowired
	private ICompanyInfoService companyInfoService;
	@Autowired
	private IEntBaseUserService entBaseService;
	@Autowired
	private IStuUserService stuUserService;
	@Autowired
	private ICommonCodeService commCodeService;
	@Autowired
	private ISchoolBaseUserService schBaseService;
	@Autowired
	private ICommonPushFollowService pushFollowService;
	@Autowired
	private ISchoolUserInfoService schUserInfoService;
	@Autowired
	private ISchoolInfoLinkService schLinkInfoService;
	
	
	/**
	 * 页面初始化
	 * @return
	 */
	@RequestMapping({"/init"})
	public String init(HttpServletRequest request, String oper,
			String userId, String orgId, UserLocalauth user, Model model){
		try {
			if(StringUtil.isEmpty(userId) && user != null){
				userId = user.getId();
			}
			model.addAttribute("userId", userId);//用户id
			model.addAttribute("orgId", orgId);//企业  学习  学生   基本信息id
			model.addAttribute("localUser", user);
			
			return "/regedit/regedit";
		} catch (Exception e) {
			log.info(LogUtil.getTrace(e));
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 推荐页面
	 * @param request
	 * @param userId
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/pushFollowPage")
	public String pushFollowPage(HttpServletRequest request, String userId,String orgId,String oper, Model model){
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			List<Map<String,Object>> listEnt = new ArrayList<Map<String,Object>>();
			List<Map<String,Object>> listStu = new ArrayList<Map<String,Object>>();
			List<Map<String,Object>> listSch = new ArrayList<Map<String,Object>>();
			List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
			String org_id = "";
			if(StringUtil.isNotEmpty(userId)){
				UserLocalauth user = localUserService.selectByPrimaryKey(userId);
				map.put("userId", userId);
				
				
				oper = user.getCategoryId();
				String locationCode = "489";
				int rowEnt = 0,rowStu = 0,rowSch = 0;
				if(oper.equals(Constant.USER_TYPE_ENT)){//企业推荐
					String companyId = String.valueOf(
							entBaseService.getBaseEntUser(map).get(0).get("company_id"));
					CompanyInfo info = companyInfoService.selectByPrimaryKey(companyId);
					locationCode = info.getOfficeArea();
					org_id = info.getId();
					
					rowEnt = 2;
					rowStu = 10;
					rowSch = 3;
					
					orgId = info.getId();
				}else if(oper.equals(Constant.USER_TYPE_STU)){//学生
					StuUser stuUser = stuUserService.selectByPrimaryKey(userId);
					locationCode = stuUser.getLocationCode();
					org_id = stuUser.getId();
					
					rowEnt = 10;
					rowStu = 3;
					rowSch = 2;
					
					orgId = userId;
				}else if(oper.equals(Constant.USER_TYPE_SCH)){//学校
					SchoolUserInfo schUser = schUserInfoService.selectByPrimaryKey(userId);
					SchoolInfoLink link = schLinkInfoService.selectByPrimaryKey(schUser.getSchoolId());
					locationCode = link.getLocationCode();
					org_id = link.getId();
					
					rowEnt = 10;
					rowStu = 3;
					rowSch = 2;
					
					orgId = link.getId();
				}
				
				listEnt = getEntPushList(userId, org_id, locationCode,  rowEnt);
				listSch = getSchPushList(userId, org_id, locationCode, rowSch);
				listStu = getStuPushList(userId, org_id, locationCode, rowStu);
				list = getPushList(listStu, listEnt, listSch);
			}
			model.addAttribute("userId", userId);
			model.addAttribute("orgId", orgId);
			
			model.addAttribute("list", list);
			model.addAttribute("count", list.size());
			return "/regedit/push_follow";
		} catch (Exception e) {
			log.info(LogUtil.getTrace(e));
			e.printStackTrace();
			return null;
		}
	}
	
	//整合推荐list
	public List<Map<String,Object>> getPushList(List<Map<String,Object>> listStu, 
			List<Map<String,Object>> listEnt, List<Map<String,Object>> listSch){
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		try {
			if(listStu.size() > 0){//遍历学生
				String locationCode = String.valueOf(listStu.get(0).get("location_code"));
				String area = commCodeService.getLocationNameByCode(locationCode);
				for (int i = 0; i < listStu.size(); i++) {
					Map<String,Object> stuMap = new HashMap<String,Object>();
					stuMap.put("userId", listStu.get(i).get("id"));
					stuMap.put("userType", "10");
					stuMap.put("name", StringUtil.replaceNull(listStu.get(i).get("surname") +"") 
							+ StringUtil.replaceNull(listStu.get(i).get("miss_surname")+""));
					stuMap.put("row2", listStu.get(i).get("major_name"));
					stuMap.put("area", area);
					String url = "";
					if(listStu.get(i).get("head_url") != null){
						url = String.valueOf(listStu.get(i).get("head_url"));
						if(!url.startsWith("http://")){
							PropUtil pro = new PropUtil(Constant.PRO_FILE_PATH);
							url = pro.getValue("stu.headurl") + url;
						}
					}else{
						url = Constant.VALUE_ZERO;
					}
					stuMap.put("url", url);
					list.add(stuMap);
				}
			}
			if(listEnt.size() > 0){//遍历企业
				String locationCode = String.valueOf(listEnt.get(0).get("office_area"));
				String area = commCodeService.getLocationNameByCode(locationCode);	
				for (int i = 0; i < listEnt.size(); i++) {
					Map<String,Object> entMap = new HashMap<String,Object>();
					String companyId = String.valueOf(listEnt.get(i).get("id"));
					entMap.put("companyId", companyId);
					entMap.put("userType", "11");
					Object userId = entBaseService.getBaseEntUser(entMap).get(0).get("company_user_id");
					entMap.put("userId", String.valueOf(companyId));
					//entMap.put("userId", String.valueOf(userId));
					entMap.put("name", listEnt.get(i).get("ent_name"));
					entMap.put("row2", listEnt.get(i).get("ent_simple_name"));
					entMap.put("area", area);
					Object url = listEnt.get(i).get("logo");
					if(url == null){
						url = Constant.VALUE_ZERO;
					}
					entMap.put("url", url);
					list.add(entMap);
				}
			}
			if(listSch.size() > 0){//遍历学校
				String locationCode = String.valueOf(listSch.get(0).get("location_code"));
				String area = commCodeService.getLocationNameByCode(locationCode);	
				for (int i = 0; i < listSch.size(); i++) {
					Map<String,Object> schMap = new HashMap<String,Object>();
					String schoolId = String.valueOf(listSch.get(i).get("id"));
					schMap.put("schoolId", schoolId);
					schMap.put("userType", "12");
					Object userId = schBaseService.getSchoolUser(schMap).get(0).get("id");
					schMap.put("userId", String.valueOf(schoolId));
//					schMap.put("userId", String.valueOf(userId));
					schMap.put("name", listSch.get(i).get("school_name"));
					schMap.put("row2", listSch.get(i).get("school_short_name"));
					schMap.put("area", area);
					Object url = listSch.get(i).get("school_logo");
					if(url == null){
						url = Constant.VALUE_ZERO;
					}
					schMap.put("url", url);
					list.add(schMap);
				}
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return list;
		}
	}
	
	/**
	 * 获取企业的推荐list
	 * @param userId
	 * @return
	 */
	public List<Map<String,Object>> getEntPushList(String userId, String orgId, String locationCode, int rows){
		try {
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("officeArea", locationCode);
				map.put("userId", userId);
				map.put("orgId", orgId);
				
				map.put("page_start", 0);
				map.put("page_size", rows);
				List<Map<String,Object>> list = pushService.getPushEnt(map);
				return list;
		} catch (Exception e) {
			log.info(LogUtil.getTrace(e));
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 获取学生的推荐list
	 * @param userId
	 * @return
	 */
	public List<Map<String,Object>> getStuPushList(String userId, String orgId, String locationCode, int rows){
		try {
			if(StringUtil.isNotEmpty(locationCode)){
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("locationCode", locationCode);
				map.put("userId", userId);
				map.put("orgId", orgId);
				
				map.put("page_start", 0);
				map.put("page_size", rows);
				List<Map<String,Object>> list = pushService.getPushStu(map);
				return list;
			}
			return null;
		} catch (Exception e) {
			log.info(LogUtil.getTrace(e));
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 获取学校的推荐list
	 * @param userId
	 * @return
	 */
	public List<Map<String,Object>> getSchPushList(String userId, String orgId, String locationCode, int rows){
		try {
			if(StringUtil.isNotEmpty(locationCode)){
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("locationCode", locationCode);
				map.put("userId", userId);
				map.put("orgId", orgId);
				
				map.put("page_start", 0);
				map.put("page_size", rows);
				List<Map<String,Object>> list = pushService.getPushSch(map);
				return list;
			}
			return null;
		} catch (Exception e) {
			log.info(LogUtil.getTrace(e));
			e.printStackTrace();
			return null;
		}
	}
	
	
	/**
	 * 添加关注
	 * @param fromId
	 * @param toId
	 * @return
	 */
	@RequestMapping(value="addPush")
	@ResponseBody
	public Json addPush(String fromId, String toId ,String userId, String fromUserType){
		Json json = new Json();
		try {
			String ids[] = toId.split(",");
			String toUserType[] = fromUserType.split(",");
			for (int i = 0; i < ids.length; i ++) {
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("id", UuidUtil.getUUID());
				map.put("from_uid", fromId);
				map.put("to_uid", ids[i]);
				map.put("remark", "");
				map.put("create_time", TimeUtil.currentTimeMillis());
				map.put("from_user_type", localUserService.selectByPrimaryKey(userId).getCategoryId());
				map.put("to_user_type", toUserType[i]);
				List<Map<String,Object>> list = pushFollowService.checkIsFollow(map);
				if(list.size() == 0){
					pushFollowService.addPushFoloow(map);
					
					//更新统计表
					map.put("org_id", fromId);
					boolean flag = pushFollowService.insertFollowCount(map);
					if(!flag){
						Map<String,Object> maps = new HashMap<String,Object>();
						maps.put("org_id", fromId);
						pushFollowService.updateFollowCount(maps);
					}
				}
			}
			return json;
		} catch (Exception e) {
			log.info(LogUtil.getTrace(e));
			e.printStackTrace();
			return json;
		}
	}
	
	/**
	 * 取消关注
	 * @param fromId
	 * @param toId
	 * @return
	 */
	@RequestMapping(value="delPush")
	@ResponseBody
	public Json delPush(String fromId, String toId, String id, String userId){
		Json json = new Json();
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			if(StringUtil.isNotEmpty(id)){
				map.put("id", id);
			}else if(StringUtil.isNotEmpty(fromId) && StringUtil.isNotEmpty(toId)){
				map.put("fromId", fromId);
				map.put("toId", toId);
			}else{
				map.put("id", UuidUtil.getUUID());
			}
			pushFollowService.delPushFoloow(map);
			
			
			//更新统计表
			map.put("org_id", fromId);
			boolean flag = pushFollowService.insertFollowCount(map);
			if(!flag){
				Map<String,Object> maps = new HashMap<String,Object>();
				maps.put("org_id", fromId);
				pushFollowService.updateFollowCount(maps);
			}
			
			return json;
		} catch (Exception e) {
			log.info(LogUtil.getTrace(e));
			e.printStackTrace();
			return json;
		}
	}
}
