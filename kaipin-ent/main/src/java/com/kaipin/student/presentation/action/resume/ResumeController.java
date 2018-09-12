package com.kaipin.student.presentation.action.resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;



import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kaipin.common.constants.Constant;
import com.kaipin.common.constants.ConstantTables;
import com.kaipin.common.constants.PhoneMsg;
import com.kaipin.common.entity.DataGridJson;
import com.kaipin.common.entity.Json;
import com.kaipin.common.entity.Page;
import com.kaipin.common.entity.User;
import com.kaipin.common.presentation.action.BaseAction;
import com.kaipin.common.service.common.IBaseCodeService;
import com.kaipin.common.tencent.XingeMessage;
import com.kaipin.common.tencent.XingePush;
import com.kaipin.common.util.CookieUtil;
import com.kaipin.common.util.HttpRequestUtil;
import com.kaipin.common.util.LogUtil;
import com.kaipin.common.util.StringUtil;
import com.kaipin.common.util.TimeUtil;
import com.kaipin.common.util.UuidUtil;
import com.kaipin.enterprise.model.msg.MsgUserInterview;
import com.kaipin.enterprise.model.position.PositionDelivery;
import com.kaipin.enterprise.model.position.PositionDeliveryInterview;
import com.kaipin.enterprise.model.position.PositionInfo;
import com.kaipin.enterprise.model.position.PositionInterview;
import com.kaipin.enterprise.model.user.CompanyInfo;
import com.kaipin.enterprise.service.msg.IMsgUserInterviewService;
import com.kaipin.enterprise.service.position.IPDeliveryInterviewService;
import com.kaipin.enterprise.service.position.IPDeliveryService;
import com.kaipin.enterprise.service.position.IPInterviewService;
import com.kaipin.enterprise.service.position.IPositionService;
import com.kaipin.enterprise.service.user.ICompanyInfoService;
import com.kaipin.student.model.resume.ResumeInfo;
import com.kaipin.student.model.resume.UserInterview;
import com.kaipin.student.model.resume.UserResume;
import com.kaipin.student.model.resume.UserResumeRelation;
import com.kaipin.student.service.resume.IResumeInfoService;
import com.kaipin.student.service.resume.IResumeService;
import com.kaipin.student.service.resume.IUserResumeRelationService;
import com.kaipin.university.service.user.IUserLocalauthService;



/**
 * 简历管理类
 * @author Mr-H
 *
 */

@Controller
@RequestMapping("/resume")
public class ResumeController extends BaseAction{
	
	Logger logger = Logger.getLogger(ResumeController.class.getName());
	@Autowired
	private IResumeService iResumeService;
	@Autowired
	private IUserResumeRelationService relationService;
	@Autowired
	private IMsgUserInterviewService userMsgViewService;
	@Autowired
	private IPositionService postionService;
	@Autowired
	private IResumeInfoService resumeInfoService;
	@Autowired
	private IPDeliveryService pDeliveryService; //职位投递关系
	@Autowired
	private IPInterviewService viewService;//面试邀请
	@Autowired
	private IPDeliveryInterviewService pdviewService;//简历拓展
	@Autowired
	private IBaseCodeService baseService;
	@Autowired
	private ICompanyInfoService companyInfoService;
	@Autowired
	private IUserLocalauthService localUserService;

	/**
	 * 初始化简历管理
	 * 未阅读
	 * @return
	 */
	@RequestMapping(value="")  
	public String init(HttpServletRequest request,HttpSession session, Model model, User user) {  
		
		try {
			
			String orgId = this.getOrgId(request, user);
			CompanyInfo info = companyInfoService.selectByPrimaryKey(orgId);
			String orgName = info.getEntName();
			
			this.setSysAttr(model, orgName, null, null);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/stu/resume/count/no_read";
	}
	
	/**
	 * 统计已读 未读 通过 未通过 全部
	 * @return
	 */
	@RequestMapping(value="/counts")  
	@ResponseBody
	public Json counts(HttpServletRequest request){
		try {
			Json json = new Json();
			String cookie_companyId = CookieUtil.getCookieInfoByKey(request, Constant.USER_ORG_ID);
			Map<String,Object> map = new HashMap<String,Object>();
			if(StringUtil.isEmpty(cookie_companyId)){
				map.put("zero", 0);
				map.put("one", 0);
				map.put("two", 0);
				map.put("three", 0);
				map.put("total", 0);
				json.setSuccess(true);
				json.setObj(map);
				return json;
			}
			String companyId = cookie_companyId;
			String sqlIsPass = "select count(*) counts,is_pass from position_delivery where 1=1 and company_id = '"+companyId+"' and is_pass != 0 GROUP BY is_pass";
			String sqlIsLook = "select count(*) counts,is_look from position_delivery where 1=1 and company_id = '"+companyId+"' and is_pass = 0 GROUP BY is_look";
			
			int noRead = 0, read = 0, pass = 0, noPass = 0, total = 0;
			
			map.clear();
			map.put("sql", sqlIsPass);
			List<Map<String,Object>> listIsPass = iResumeService.getCounts(map);
			//统计通过与不通过的
			if(listIsPass.size() > 0){
				for (int i = 0; i < listIsPass.size(); i++) {
					String status = String.valueOf(listIsPass.get(i).get("is_pass"));
					String counts = String.valueOf(listIsPass.get(i).get("counts"));
					if(status.equals(Constant.VALUE_TWO)){//2
						total = total + Integer.valueOf(counts);
						pass = pass + Integer.valueOf(counts);
					}else{								  //3
						total = total + Integer.valueOf(counts);
						noPass = Integer.valueOf(counts);
					}
				}
			}
			map.clear();
			map.put("sql", sqlIsLook);
			List<Map<String,Object>> listIsLook = iResumeService.getCounts(map);
			//统计阅读和未阅读的
			if(listIsLook.size() > 0){
				for (int i = 0; i < listIsLook.size(); i++) {
					String status = String.valueOf(listIsLook.get(i).get("is_look"));
					String counts = String.valueOf(listIsLook.get(i).get("counts"));
					if(status.equals(Constant.VALUE_ZERO)){//0
						total = total + Integer.valueOf(counts);
						noRead = noRead + Integer.valueOf(counts);
					}else{								  //1
						read = read + Integer.valueOf(counts);
						total = total + Integer.valueOf(counts);
					}
				}
			}
			
			map.clear();
			map.put("zero", noRead);
			map.put("one", read);
			map.put("two", pass);
			map.put("three", noPass);
			map.put("total", total);
			json.setSuccess(true);
			json.setObj(map);
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	/**
	 * datagridGoutList
	 * 查询列表
	 * 根据职位统计该职位下收到的简历总数
	 * @return
	 */
	@RequestMapping(value="/datagridGoutList")  
	@ResponseBody  
	public DataGridJson datagridGoutList(Page page, HttpServletRequest request,String status,String city, String jobType) { 
		try {
			String cookie_companyId = CookieUtil.getCookieInfoByKey(request, Constant.USER_ORG_ID);
			DataGridJson json = new DataGridJson();
			HashMap<String, Object> map = new HashMap<String, Object>();
			if(status.equals(Constant.VALUE_ZERO)){
				map.put("isLook", Constant.VALUE_ZERO);
			}else if(status.equals(Constant.VALUE_ONE)){
				map.put("isLook", Constant.VALUE_ONE);
			}else if(status.equals(Constant.VALUE_TWO)){
				map.put("isPass", Constant.VALUE_TWO);
			}else if(status.equals(Constant.VALUE_THREE)){
				map.put("isPass", Constant.VALUE_THREE);
			}
			
			map.put("city", city);
			map.put("jobType", jobType);
			map.put("companyId", cookie_companyId);
			map.put("page_start", (page.getPage()-1) * page.getRows());
			map.put("page_size", page.getRows());
			List<Map<String,Object>> list = iResumeService.getCountsOfPostionList(map);
			json.setRows(list);
			json.setTotal((long)iResumeService.getCountsOfPostionListTotal(map));
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	/**
	 * 初始化页面
	 * 根据一个职位查询该职位下所有未阅读的简历
	 * @return
	 */
	@RequestMapping(value="/noReadPage")  
	public String noReadPage(HttpServletRequest request,String postionId ,Model model ,User user) {  
		try {
			request.setAttribute(Constant.POSTION_ID, postionId);
			String orgName = this.getOrgName(request, user);
			this.setSysAttr(model, orgName, null, null);
		} catch (Exception e) {
			logger.info(LogUtil.getTrace(e));;
			e.printStackTrace();
		}
		return "/stu/resume/list/no_read_list";
	}
	
	/**
	 * datagridGoutList
	 * 查询列表
	 * 根据职位统计该职位下收所有简历
	 * @param page
	 * @param request
	 * @param status
	 * @param postionId
	 * @return
	 */
	@RequestMapping(value="/datagridNoReadList")  
	@ResponseBody  
	public DataGridJson datagridNoReadList(Page page, HttpServletRequest request,
			String status, String postionId, UserResume resume, PositionInfo postion) { 
		try {
			String cookie_companyId = CookieUtil.getCookieInfoByKey(request, Constant.USER_ORG_ID);
			DataGridJson json = new DataGridJson();
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("companyId", cookie_companyId);
			map.put("positionId", postionId);
			if(status.equals(Constant.VALUE_ZERO)){//0未读
				map.put("isLook", Constant.VALUE_ZERO);
			}else if(status.equals(Constant.VALUE_ONE)){//1已读
				map.put("isLook", Constant.VALUE_ONE);
				map.put("isPass", Constant.VALUE_ZERO);
			}else if(status.equals(Constant.VALUE_TWO)){//2通过
				map.put("isPass", Constant.VALUE_TWO);
			}else if(status.equals(Constant.VALUE_THREE)){//3不通过
				map.put("isPass", Constant.VALUE_THREE);
			}
			
			//页面条件筛选条件
			map.put("education", postion.getEducationCode());
			map.put("workExperience", postion.getWorkExperienceCode());
			map.put("workArea", postion.getLocationCode());
			
			map.put("resumeStatus", Constant.RESUME_STATUS_YES);
			map.put("page_start", (page.getPage()-1) * page.getRows());
			map.put("page_size", page.getRows());
			List<Map<String,Object>> list = iResumeService.datagridNoReadList(map);
			json.setRows(list);
			json.setTotal((long)iResumeService.datagridNoReadListTotal(map));
			return json;
		} catch (Exception e) {
			logger.info(LogUtil.getTrace(e));;
			e.printStackTrace();
			return null;
		}
		
	}
	/**
	 * 简历流程
	 * @param request
	 * @param id	关系表id
	 * @param companyId 
	 * @param resumeId
	 * @param postionId
	 * @param times
	 * @param face
	 * @param status 关系表状态
	 * @return
	 */
	@RequestMapping(value="/oper")  
	@ResponseBody  
	public Json oper(HttpServletRequest request, 
			String id, String companyId, String userId,
			String  resumeId, String postionId, String viewId,
			String times, String face, String status,UserInterview view, String memo, String faceTime) { 
		try {
			String title = "开频校招";
			String cookie_companyId = CookieUtil.getCookieInfoByKey(request, Constant.USER_ORG_ID);
			//记录日志
//			UserResumeRelation relation = relationService.getuserResumeRelation(id);
			PositionDelivery delivery = pDeliveryService.selectByPrimaryKey(id);
			
			PositionDeliveryInterview deliveryView = pdviewService.selectByPrimaryKey(id);
			PositionInfo position = postionService.selectByPrimaryKey(delivery.getPositionId());
			ResumeInfo resume = resumeInfoService.selectByPrimaryKey(delivery.getResumeId());
			CompanyInfo companyInfo = companyInfoService.selectByPrimaryKey(delivery.getCompanyId());
			
			/**
			 * 用于短信
			 */
			String stuUserName =getUserName(resume.getSurname() + resume.getMissSurname());//学生名字
			String entName = getEntName(companyInfo.getEntName());//企业名称
			String positionName = getEntName(position.getPositionName());//职位名称
			String userPhone = localUserService.selectByPrimaryKey(delivery.getStuUserId()).getPhone();
			String createTime = TimeUtil.getTimeByMillis(delivery.getCreateTime()).substring(0, 10);
			String url = PhoneMsg.getUrl();
			if(status.equals("4") && StringUtil.isNotEmpty(faceTime)){
				createTime = faceTime.replace(" ", "");
			}
			
			Map<String,Object> phoneParam = new HashMap<String, Object>();
			phoneParam.put(PhoneMsg.STU, stuUserName);
			phoneParam.put(PhoneMsg.ENT, entName);
			phoneParam.put(PhoneMsg.MST, createTime);
			phoneParam.put(PhoneMsg.PN, positionName);
			
			String param = "";
			
			String reqId = deliveryView.getInterviewId();
			Byte count_m  = 0; //face 面
			Byte count_n = 0; //times 次
			String parentId = "";
			PositionInterview interView = new PositionInterview();
			PositionInterview interViewNew = new PositionInterview();
			if(StringUtil.isNotEmpty(reqId)){
				interView = viewService.selectByPrimaryKey(reqId);
				count_m = interView.getCountM();
				count_n = interView.getCountN();
			}
			
			String detail = "";
			String showLog = "已经通过筛选";
			String viewIdNew = UuidUtil.getUUID();
			HashMap<String,String> map = new HashMap<String, String>();
			
			if(status.equals("2")){
				detail = "已通过筛选";
				map.put("viewId", viewIdNew);
				
				//发送短信
				param = PhoneMsg.getUrlValue(PhoneMsg.RESUME_STATUS_TWO_CODE , userPhone, phoneParam);
				
			}else if(status.equals("3")){
				detail = "未通过筛选";
				map.put("viewId", viewIdNew);
				
				//发送短信
				param = PhoneMsg.getUrlValue(PhoneMsg.RESUME_STATUS_THREE_CODE , userPhone, phoneParam);
				
			}else if(status.equals("4")){
				
				String reqIds = deliveryView.getInterviewId();
				if(StringUtil.isNotEmpty(reqIds)){
					Object stuHaddle = interView.getStudentHandleStatus();
					if(stuHaddle != null){
						int n = 1;
						int m = 1;
						if(Integer.valueOf(String.valueOf(stuHaddle)) == 5){
							m = interView.getCountM();
							count_m = Byte.valueOf(String.valueOf(m + 1));
						}else{
							n = Integer.valueOf(count_n) + 1;
							count_n = Byte.valueOf(String.valueOf(n));
						}
					}else{
						count_n = Byte.valueOf(String.valueOf(1));
						int m = interView.getCountM();
						count_m = Byte.valueOf(String.valueOf(m + 1));
					}
					
				}else{
					count_n = 1;
					count_m = 1;
				}
				
				title = "邀请面试通知";
				detail = position.getPositionName() + "邀请第"+ count_n + "次" + count_m + "面";
				showLog = "邀请第"+ count_n + "次" + count_m + "面";
				
				//设置需要推送的信息
				String msgId = UuidUtil.getUUID();
				MsgUserInterview msg = new MsgUserInterview();
				msg.setId(msgId);
				msg.setCreateTime(TimeUtil.currentTimeMillis());
				msg.setTitle(title);
				msg.setType(Byte.valueOf(Constant.VALUE_FOUR));
				msg.setStuUserId(delivery.getStuUserId());
				msg.setContent(detail);
				msg.setHint(detail);
				msg.setObjectId(viewIdNew);
				
				
				//往消息表插入数据
				int flag = userMsgViewService.insertSelective(msg);
				if(flag == Constant.OPER_ONE){
					//推送
					//doPush(request,Constant.VALUE_FOUR, relation, msg);
					XingePush push = new  XingePush();
					push.doPush(request, Constant.VALUE_FOUR, title, delivery, msg);
				}
				
				//发送短信
				param = PhoneMsg.getUrlValue(PhoneMsg.RESUME_STATUS_FOUR_CODE , userPhone, phoneParam);
				
			}else if(status.equals("5") || status.equals("6")){
				
//				if(status.equals("6")){
//					int n = Integer.valueOf(count_n) + 1;
//					count_n = Byte.valueOf(String.valueOf(n));
//				}
				
				//要插入的数据
				interViewNew.setParentId(reqId);
				interViewNew.setStudentHandleStatus(Byte.valueOf(status));

				//更新邀请数据的公司反馈状态
				PositionInterview vReq = new PositionInterview();
				vReq.setId(reqId);
				vReq.setStudentHandleStatus(Byte.valueOf(status));
				viewService.updateByPrimaryKeySelective(vReq);
			}else if(status.equals("7") || status.equals("8")){
				//当前邀请对应的数据  用于获取parentId
				PositionInterview v = viewService.selectByPrimaryKey(reqId);
				parentId = v.getParentId();
				
				//要插入的数据
				interViewNew.setParentId(parentId);
				interViewNew.setCompanyHandleStatus(Byte.valueOf(status));
				

				//更新邀请数据的公司反馈状态
				PositionInterview vReq = new PositionInterview();
				vReq.setId(parentId);
				vReq.setCompanyHandleStatus(Byte.valueOf(status));
				viewService.updateByPrimaryKeySelective(vReq);
				
				title = "邀请面试通知";
				if(status.equals("7")){
					detail = "通过第"+ count_n + "次" + count_m + "面";
					
					//发送短信
					phoneParam.put(PhoneMsg.XM, count_n);
					phoneParam.put(PhoneMsg.RM, (count_n + 1));
					param = PhoneMsg.getUrlValue(PhoneMsg.RESUME_STATUS_SEVEN_CODE , userPhone, phoneParam);
					
				}else{
					detail = "未通过第"+ count_n + "次" + count_m + "面";
					
					//发送短信
					param = PhoneMsg.getUrlValue(PhoneMsg.RESUME_STATUS_EIGHT_CODE , userPhone, phoneParam);
					
				}
				showLog = detail;
				detail = position.getPositionName() + detail;
				
				//推送消息设置
				String msgId = UuidUtil.getUUID();
				MsgUserInterview msg = new MsgUserInterview();
				msg.setId(msgId);
				msg.setCreateTime(TimeUtil.currentTimeMillis());
				msg.setTitle(title);
				msg.setType(Byte.valueOf(status));
				msg.setStuUserId(delivery.getStuUserId());
				msg.setContent(detail);
				msg.setHint(detail);
				msg.setObjectId(viewIdNew);
				
				//往消息表插入数据
				int flag = userMsgViewService.insertSelective(msg);
				
			}
			
			//发送短信
			HttpRequestUtil.sendPost(url, param);
			
			//更新拓展表
			deliveryView.setInterviewId(viewIdNew);
			pdviewService.updateByPrimaryKeySelective(deliveryView);
			
			//更新邀请表
			interViewNew.setId(viewIdNew);
			interViewNew.setObjectId(id);
			interViewNew.setInterviewTime(faceTime);
			interViewNew.setCreateTime(TimeUtil.currentTimeMillis());
			interViewNew.setCountN(count_n);
			interViewNew.setCountM(count_m);
			interViewNew.setMemo(memo);
			interViewNew.setCurrentStatus(Byte.valueOf(status));
			int i = viewService.insertSelective(interViewNew);
			
			//写日志
			Map<String,Object> mapVal = new HashMap<String,Object>();
			mapVal.put("position_delivery_id", id);
			mapVal.put("create_time", TimeUtil.currentTimeMillis());
			mapVal.put("content", showLog);
			mapVal.put("status", status);
			mapVal.put("type", Constant.VALUE_ONE);
			mapVal.put("object_id", "");
			int ii = iResumeService.insertViewLog(mapVal);
			
			Json json = new Json();
			return json;
		} catch (Exception e) {
			logger.info(LogUtil.getTrace(e));;
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 插入学生面试提醒消息
	 */
	public void insertUserMsgView(String title, String type, String content){
		MsgUserInterview function = new MsgUserInterview();
		function.setId(UuidUtil.getUUID());
		function.setCreateTime(TimeUtil.currentTimeMillis());
		function.setTitle(title);
		function.setType(Byte.valueOf(type));
//		function.setStatus(Integer.valueOf(Constant.VALUE_ZERO));
		function.setContent(content);
	}
	
	/**
	 * 根据简历id查询该简历的所有日志
	 * @param resumeId
	 * @return
	 */
	@RequestMapping(value="/log")
	@ResponseBody
	public Json log(String relationId, String oper){
		try {
			Json json = new Json();
			HashMap<String,Object> map = new HashMap<String, Object>();
			map.put("position_delivery_id", relationId);
			List<Map<String,Object>> list = iResumeService.getLogList(map);
			map.clear();
			if(oper.equals("show")){
				Object show = null;
				if(list.size() > 0){
					show = list.get(0).get("content");
				}
				map.put("showMsg", show);
				map.put("showStatus", list.get(0).get("status"));
				map.put("showType", list.get(0).get("type"));
				json.setObj(map);
			}else if(oper.equals("log")){
				json.setObj(list);
			}
			
			json.setSuccess(true);
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	/**
	 * 获取当前简历状态
	 * @param relationId
	 * @return
	 */
	@RequestMapping(value="/getStatus")
	@ResponseBody
	public Json getStatus(String relationId, String oper){
		try {
			Json json = new Json();
			HashMap<String,Object> map = new HashMap<String, Object>();
			map.put("relationId", relationId);
			List<Map<String,Object>> list = iResumeService.getCurrentStatus(map);
			String status = "";
			String isPass = "";
			String isLook = "";
			if(list.size() > 0){
				status = list.get(0).get("current_status") + "";
				isPass = list.get(0).get("is_pass") + "";
				isLook = list.get(0).get("is_look") + "";
				
				if(isLook.equals("0") && isPass.equals("0")){
					status = "0";
				}else if(isLook.equals("1") && isPass.equals("0")){
					status = "1";
				}else if(isLook.equals("1") && isPass.equals("2")){
					if(StringUtil.isEmpty(status)){
						status = "2";
					}
				}
			}
			map.clear();
			
			map.put("showStatus", status);
			map.put("showType", 1);
			json.setObj(map);
			
			json.setSuccess(true);
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	/**
	 * 获取该企业的城市列表
	 * @param resumeId
	 * @param postionId
	 * @return
	 */
	@RequestMapping(value="/cityList")
	@ResponseBody
	public Json cityList(HttpServletRequest request){
		try {
			Json json = new Json();
			String cookie_companyId = CookieUtil.getCookieInfoByKey(request, Constant.USER_ORG_ID);
			HashMap<String,Object> map = new HashMap<String, Object>();
			map.put("companyId", cookie_companyId);
			map.put("today", TimeUtil.getDates());
			List<Map<String,Object>> cityList = iResumeService.cityList(map);
			if(cityList.size() > 0){
				json.setSuccess(true);
			}else{
				json.setSuccess(false);
			}
			json.setObj(cityList);
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 获取该企业的城市列表
	 * @param resumeId
	 * @param postionId
	 * @return
	 */
	@RequestMapping(value="/jobTypeList")
	@ResponseBody
	public Json jobTypeList(HttpServletRequest request){
		try {
			Json json = new Json();
			String cookie_companyId = CookieUtil.getCookieInfoByKey(request, Constant.USER_ORG_ID);
			HashMap<String,Object> map = new HashMap<String, Object>();
			map.put("companyId", cookie_companyId);
			map.put("today", TimeUtil.getDates());
			List<Map<String,Object>> cityList = iResumeService.jobTypeList(map);
			if(cityList.size() > 0){
				json.setSuccess(true);
			}else{
				json.setSuccess(false);
			}
			json.setObj(cityList);
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 推送邀请信息
	 * @param id
	 * @return
	 */
	public void doPush(HttpServletRequest request,
			String type, UserResumeRelation relation,MsgUserInterview msg){
		try {
			String cookie_companyId = CookieUtil.getCookieInfoByKey(request, Constant.USER_ORG_ID);
			
//			String title = user.getEntName() + "邀请面试通知";
			String title = "邀请面试通知";
			XingeMessage message = new XingeMessage();
			//用户
			List<String> listAcount = new ArrayList<String>();
			listAcount.add(relation.getUserId());
			
			//自定义参数
			HashMap<String,Object> custom = new HashMap<String, Object>();
			HashMap<String,Object> mapData = new HashMap<String, Object>();
			
//			mapData.put("r_id", relation.getId());
			mapData.put("msg_id", msg.getId());
			mapData.put("to", new ArrayList<String>());
			mapData.put("title", title);
			mapData.put("hint", "");
			mapData.put("time", msg.getCreateTime());
			
			custom.put("type", Constant.VALUE_FOUR);
			custom.put("data", mapData);
			
			//推送ios
			message.pushStuAccountListIos(title, custom, listAcount);
			
			msg.getContent();
			
			//推送android
			message.pushStuAccountList(title, msg.getContent(), custom, listAcount);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getEntName(String entName){
		if(StringUtil.isNotEmpty(entName)){
			if(entName.length() > 10){
				entName = entName.substring(0, 7) + "...";
			}
		}
		return entName;
	}
	public String getUserName(String entName){
		if(StringUtil.isNotEmpty(entName)){
			if(entName.length() > 3){
				entName = entName.substring(0, 3);
			}
		}
		return entName;
	}
}
