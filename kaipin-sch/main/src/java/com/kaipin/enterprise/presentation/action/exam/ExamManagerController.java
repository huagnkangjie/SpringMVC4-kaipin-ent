package com.kaipin.enterprise.presentation.action.exam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kaipin.common.constants.Constant;
import com.kaipin.common.constants.PhoneMsg;
import com.kaipin.common.entity.DataGridJson;
import com.kaipin.common.entity.Json;
import com.kaipin.common.entity.Page;
import com.kaipin.common.entity.User;
import com.kaipin.common.presentation.action.BaseAction;
import com.kaipin.common.service.common.IBaseCodeService;
import com.kaipin.common.tencent.XingePush;
import com.kaipin.common.util.CookieUtil;
import com.kaipin.common.util.HttpRequestUtil;
import com.kaipin.common.util.LogUtil;
import com.kaipin.common.util.StringUtil;
import com.kaipin.common.util.TimeUtil;
import com.kaipin.common.util.UuidUtil;
import com.kaipin.enterprise.model.exam.ExamInvite;
import com.kaipin.enterprise.model.exam.ExamListBean;
import com.kaipin.enterprise.model.exam.ExamPaper;
import com.kaipin.enterprise.model.msg.MsgUserExam;
import com.kaipin.enterprise.model.position.PositionDelivery;
import com.kaipin.enterprise.model.position.PositionDeliveryInterview;
import com.kaipin.enterprise.model.position.PositionInfo;
import com.kaipin.enterprise.model.user.CompanyInfo;
import com.kaipin.enterprise.service.exam.IExamInviteService;
import com.kaipin.enterprise.service.exam.IExamPaperService;
import com.kaipin.enterprise.service.exam.IExamQuestionService;
import com.kaipin.enterprise.service.exam.IExamService;
import com.kaipin.enterprise.service.msg.IMsgUserExamService;
import com.kaipin.enterprise.service.position.IPDeliveryInterviewService;
import com.kaipin.enterprise.service.position.IPDeliveryService;
import com.kaipin.enterprise.service.position.IPositionService;
import com.kaipin.enterprise.service.user.ICompanyInfoService;
import com.kaipin.student.model.resume.ResumeInfo;
import com.kaipin.student.service.resume.IResumeInfoService;
import com.kaipin.student.service.resume.IResumeService;
import com.kaipin.university.model.user.UserLocalauth;
import com.kaipin.university.service.user.IUserLocalauthService;


/**
 * 一套题对应下面的详细题
 * 对问卷的管理
 * 新增、编辑
 * 
 * @author Mr-H
 *
 */

@Controller
@RequestMapping("/company/examManager")
public class ExamManagerController extends BaseAction{

	Logger log = Logger.getLogger(ExamManagerController.class.getName());

	@Autowired
	private IExamQuestionService questionService;
	@Autowired
	private IExamService examService;
	@Autowired
	private IExamPaperService paperService;
	@Autowired
	private IPositionService positionService;
	@Autowired
	private IBaseCodeService baseService;
	@Autowired
	private IExamInviteService examInterviewService;
	@Autowired
	private IPDeliveryInterviewService pdViewService;
	@Autowired
	private IPDeliveryService pDservice;
	@Autowired
	private IMsgUserExamService msgExamService;
	@Autowired
	private IResumeService iResumeService;
	@Autowired
	private ICompanyInfoService companyInfoSrevice;
	@Autowired
	private IResumeInfoService resumeInfoService;
	@Autowired
	private IUserLocalauthService localUserService;
	
	/**
	 * 初始化笔试题维护列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/init")
	public String init(HttpServletRequest request, Model model ,User user) {
		try {
			
			String orgName = this.getOrgName(request, user);
			this.setSysAttr(model, orgName, null, null);
			
			return "/ent/exam/manager/exam_edit_list";
		} catch (Exception e) {
			e.printStackTrace();
			log.info(LogUtil.getTrace(e));;
			return null;
		}
	}


	/**
	 * 查询一套试卷下面的所有答题人列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/datagrid")
	@ResponseBody
	public DataGridJson datagrid(Page page, HttpServletRequest request) {
		try {
			String cookie_companyId = CookieUtil.getCookieInfoByKey(request, Constant.USER_ORG_ID);
			
			DataGridJson json = new DataGridJson();
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("companyId", cookie_companyId);
			map.put("page_start", (page.getPage() - 1) * page.getRows());
			map.put("page_size", page.getRows());
			// List<Map<String,Object>> list =
			// iResumeService.getCountsOfPostionList(map);
			// json.setRows(list);
			// json.setTotal((long)iResumeService.getCountsOfPostionListTotal(map));
			return json;
		} catch (Exception e) {
			log.info(e);
			e.printStackTrace();
			return null;
		}

	}
	
	/**
	 * 判断该企业是否有职位
	 * 
	 * @param request
	 * @param postionStr
	 * @return
	 */
	@RequestMapping("/pulishOnPreview")
	@ResponseBody
	public Json pulishOnPreview(HttpServletRequest request, String postionStr) {
		Json json = new Json();
		try {

			return json;
		} catch (Exception e) {
			log.info(LogUtil.getTrace(e));;
			e.printStackTrace();
			return null;
		}
	}
	
	
	/**
	 * 判断该企业是否有职位
	 * 
	 * @param request
	 * @param postionStr
	 * @return
	 */
	@RequestMapping("/positionList")
	@ResponseBody
	public Json positionList(HttpServletRequest request) {
		Json json = new Json();
		try {
			String cookie_companyId = CookieUtil.getCookieInfoByKey(request, Constant.USER_ORG_ID);
			
			HashMap<String,Object> map = new HashMap<String, Object>();
			map.put("companyId", cookie_companyId);
			map.put("today", TimeUtil.currentTimeMillis());
			List<Map<String,Object>> list = examService.positionList(map);
			json.setObj(list);
			return json;
		} catch (Exception e) {
			log.info(LogUtil.getTrace(e));;
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 添加笔试题
	 * 
	 * @return
	 */
	@RequestMapping(value = "/addPage")
	public String addPage(HttpServletRequest request, String positionId, Model model ,User user) {
		try {
			PositionInfo info = positionService.selectByPrimaryKey(positionId);
			model.addAttribute("positionId", positionId);
			model.addAttribute("positionName", info.getPositionName());
			
			String orgName = this.getOrgName(request, user);
			this.setSysAttr(model, orgName, null, null);
			
			return "/ent/exam/manager/exam_add";
		} catch (Exception e) {
			e.printStackTrace();
			log.info(LogUtil.getTrace(e));;
			return null;
		}
	}

	/**
	 * 保存笔试题
	 * 
	 * 1、创建题库 
	 * 2、创建试卷 
	 * 3、试卷 题目关联 
	 * 4、新增所有的题目 
	 * 5、职位和试卷关联
	 * 
	 * @param request
	 * @param examList
	 *            获取的所有题目集合
	 * @param positionId
	 *            职位id
	 * @return
	 */
	@RequestMapping(value = "/add", method = { RequestMethod.POST })
	public void add(HttpServletRequest request,HttpServletResponse response, ExamListBean examList, String positionId) {
		try {
			String cookie_companyId = CookieUtil.getCookieInfoByKey(request, Constant.USER_ORG_ID);

			if(examList.getExamList().size() > 0){
				questionService.insertExamList(examList, cookie_companyId, positionId);
			}
			String url = request.getContextPath();
			response.sendRedirect(url + "/company/examManager/init");
		} catch (Exception e) {
			e.printStackTrace();
			log.info(LogUtil.getTrace(e));;
			//return null;
		}
	}

	private ServletContext getServletContext() {
		// TODO Auto-generated method stub
		return null;
	}


	/**
	 * 单个学生试卷详情
	 * 
	 * @return
	 */
	@RequestMapping(value = "/detail")
	public String detail(HttpServletRequest request, Model model ,User user) {
		try {
			String orgName = this.getOrgName(request, user);
			this.setSysAttr(model, orgName, null, null);
			
			return "/ent/exam/list/detail";
		} catch (Exception e) {
			log.info(LogUtil.getTrace(e));;
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 启用 或者 停用 试卷
	 * @param request
	 * @return
	 */
	@RequestMapping("/upOrDown")
	@ResponseBody
	public Json upOrDown(HttpServletRequest request, String oper, String paperId) {
		Json json = new Json();
		try {
			ExamPaper paper = paperService.selectByPrimaryKey(paperId);
			paper.setStatus(Byte.valueOf(oper));
			int flag = paperService.updateByPrimaryKeySelective(paper);
			if(flag == Constant.OPER_ONE){
				json.setSuccess(true);
			}
			return json;
		} catch (Exception e) {
			log.info(LogUtil.getTrace(e));;
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 试卷编辑页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/editPage")
	public String editPage(HttpServletRequest request, String paperId, Model model ,User user, String oper) {
		try {
			HashMap<String,Object> map = new HashMap<String, Object>();
			map.put("paperId", paperId);
			List<Map<String,Object>> listOne = new ArrayList<Map<String,Object>>();
			List<Map<String,Object>> list = examService.examQuestionList(map);
			String positionName = "";
			String dbId = "";//题库id
			if(list.size() > 0){
				listOne.add(list.get(0)); 
				positionName = String.valueOf(list.get(0).get("position_name"));
				dbId = String.valueOf(list.get(0).get("dbId"));
				list.remove(0);
			}
			//判断是否可以编辑试卷
			List<Map<String,Object>> listCheck = examService.checkPaperHasUse(map);
			model.addAttribute("editFlag", listCheck.get(0).get(Constant.COUNT));
			
			model.addAttribute("positionName", positionName);
			model.addAttribute("dbId", dbId);//题库id
			model.addAttribute("questionListOne", listOne);
			model.addAttribute("questionList", list);
			model.addAttribute("paperId", paperId);
			model.addAttribute("questionLength", listOne.size() + list.size());
			String url = "";
			if(oper.equals(Constant.VALUE_ZERO)){
				url =  "/ent/exam/manager/exam_edit";
			}else if(oper.equals(Constant.VALUE_ONE)){
				url =  "/ent/exam/manager/exam_detail";
			}
			
			String orgName = this.getOrgName(request, user);
			this.setSysAttr(model, orgName, null, null);
			
			return url;
		} catch (Exception e) {
			e.printStackTrace();
			log.info(LogUtil.getTrace(e));;
			return null;
		}
	}
	
	/**
	 * 编辑 保存
	 * @param request
	 * @param examList
	 * @param paperId
	 * @return
	 */
	@RequestMapping(value = "/edit", method = { RequestMethod.POST })
	public void edit(HttpServletRequest request, HttpServletResponse response, ExamListBean examList, 
			String paperId, String editFlag, String dbId, Model model ,User user) {
		try {
			ExamPaper paper = paperService.selectByPrimaryKey(paperId);
			paper.setStatus(Byte.valueOf(Constant.VALUE_ONE));
			paperService.updateByPrimaryKeySelective(paper);
			questionService.updateExamPaperQuestions(examList,paperId,editFlag,dbId);
			
			String orgName = this.getOrgName(request, user);
			this.setSysAttr(model, orgName, null, null);
			
			
			String url = request.getContextPath();
			response.sendRedirect(url + "/company/examManager/init");
			
			//return "/ent/exam/manager/exam_edit_list";
		} catch (Exception e) {
			e.printStackTrace();
			log.info(LogUtil.getTrace(e));;
		}
	}
	
	/**
	 * 详情
	 * @param request
	 * @param examList
	 * @param paperId
	 * @return
	 *//*
	@RequestMapping(value = "/detail")
	public String detail(HttpServletRequest request, String paperId, String uId, Model model ,User user ) {
		try {
			String orgName = this.getOrgName(request, user);
			this.setSysAttr(model, orgName, null, null);
			
			return "/ent/exam/manager/exam_detail";
		} catch (Exception e) {
			e.printStackTrace();
			log.info(LogUtil.getTrace(e));;
			return null;
		}
	}*/
	
	/**
	 * 判断该试卷是否有试题
	 * @param request
	 * @param positionId
	 * @return
	 */
	@RequestMapping("/checkExamPaper")
	@ResponseBody
	public Json checkExamPaper(HttpServletRequest request, String positionId){
		try {
			Json json = new Json();
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("positionId", positionId);
			List<Map<String,Object>> list = examService.checkPaperByPos(map);
			if(list.size() > 0){
				Object status = list.get(0).get("status");
				json.setObj(status);
				json.setSuccess(false);
			}else{
				json.setSuccess(true);
			}
			
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			log.info(LogUtil.getTrace(e));;
			return null;
		}
	}
	
	
	
	/**
	 * 笔试题邀请
	 * @param positionId   职位id
	 * @param resumePositionId	职位投递id
	 * @return
	 */
	@RequestMapping("/reqExam")
	@ResponseBody
	public Json reqExam(HttpServletRequest request, 
			String positionId, String resumePositionId) {
		try {
			Json json = new Json();
			//获取试卷职位关联的id
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("sql", "select * from exam_paper_position where 1=1 and position_id = '"+positionId+"'");
			List<Map<String,Object>> list = baseService.getBaseList(map);
			
			if(list.size() > 0){
				ExamInvite function = new ExamInvite();
				
				String paperPositionId = String.valueOf(list.get(0).get("id"));
				String reqId = UuidUtil.getUUID();
				function.setId(reqId);
				function.setCreateTime(TimeUtil.currentTimeMillis());
				function.setInviterHandleStatus(Byte.valueOf(Constant.VALUE_FOUR));
				function.setPaperPositionId(paperPositionId);
				function.setObjectId(resumePositionId);//笔试题邀请id
				
				//插入数据
				examInterviewService.insertSelective(function);
				
				//更新拓展表 笔试
				PositionDeliveryInterview pdView = new PositionDeliveryInterview();
				pdView.setPositionDeliveryId(resumePositionId);
				pdView.setExamInviteId(reqId);
				pdViewService.updateByPrimaryKeySelective(pdView);
				
				PositionDelivery pd = pDservice.selectByPrimaryKey(resumePositionId);
				//推送信息
				//插入推送消息
				
				String title = "邀请笔试";
				String content = "邀请笔试";
				
				PositionInfo position = positionService.selectByPrimaryKey(positionId);
				if(position != null){
					title = "邀请参加" + position.getPositionName() + "职位的笔试 ";
				}
				
				PositionDelivery positionDelivery = pDservice.selectByPrimaryKey(resumePositionId);
				String resumeId = positionDelivery.getResumeId();
				String companyId = positionDelivery.getCompanyId();
						
				MsgUserExam msgExam = new MsgUserExam();
				String msgExamId = UuidUtil.getUUID();
				msgExam.setId(msgExamId);
				msgExam.setCreateTime(TimeUtil.currentTimeMillis());
				msgExam.setTitle(title);
				msgExam.setStatus(Byte.valueOf(Constant.VALUE_ZERO));
				msgExam.setStuUserId(pd.getStuUserId());
				msgExam.setContent(content);
				msgExam.setHint(title);
				msgExam.setObjectId(reqId);
				msgExamService.insertSelective(msgExam);
				
				//推送信息
				XingePush push = new  XingePush();
				Map<String,String> mapVal = new  HashMap<String,String>();
				mapVal.put("userId",pd.getStuUserId());
				mapVal.put("title",title);
				mapVal.put("content","");
				mapVal.put("relationId",reqId);
				mapVal.put("msgId",msgExamId);
				mapVal.put("hint",title);
				mapVal.put("type",Constant.VALUE_TEN);
				
				//发送推送
				push.doPushMsg(request, mapVal);
				
				String positionName = position.getPositionName();
				if(positionName.length() > 10){
					positionName = positionName.substring(0, 7) + "...";
				}
				
				String companyName = "";
				CompanyInfo companyInfo = companyInfoSrevice.selectByPrimaryKey(companyId);
				if(companyInfo != null){
					companyName = companyInfo.getEntName();
					if(companyName.length() > 10){
						companyName = companyName.substring(0, 7) + "...";
					}
				}
				
				ResumeInfo resume = resumeInfoService.selectByPrimaryKey(resumeId);
				String userName = "";
				if(resume != null){
					userName = resume.getSurname() + resume.getMissSurname();
					if(userName.length() > 3){
						userName = userName.substring(0, 3) ;
					}
					
				}
				
				//发送短信
				Map<String,Object> phoneParam = new HashMap<String, Object>();
				phoneParam.put(PhoneMsg.STU, userName);
				phoneParam.put(PhoneMsg.ENT, companyName);
				phoneParam.put(PhoneMsg.PN, positionName);
				
				//发送短信
				
				UserLocalauth localUser = localUserService.selectByPrimaryKey(pd.getStuUserId());
				String url = PhoneMsg.getUrl();
				String param = PhoneMsg.getUrlValue(PhoneMsg.RESUME_STATUS_TEN_CODE , 
						localUser.getPhone(), phoneParam);
				
				HttpRequestUtil.sendPost(url, param);
				
				//写日志
				String showLog = "已邀请笔试";
				Map<String,Object> mapVals = new HashMap<String,Object>();
				mapVals.put("position_delivery_id", resumePositionId);
				mapVals.put("create_time", TimeUtil.currentTimeMillis());
				mapVals.put("content", showLog);
				mapVals.put("status", Constant.VALUE_TEN);
				mapVals.put("type", Constant.VALUE_TWO);
				mapVals.put("object_id", "");
				int ii = iResumeService.insertViewLog(mapVals);
				
				json.setSuccess(true);
			}
			
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			log.info(LogUtil.getTrace(e));;
			return null;
		}
	}
	
	public String getUserName(String entName){
		if(StringUtil.isNotEmpty(entName)){
			if(entName.length() > 3){
				entName = entName.substring(0, 3);
			}
		}
		return entName;
	}
	
	public String getEntName(String entName){
		if(StringUtil.isNotEmpty(entName)){
			if(entName.length() > 10){
				entName = entName.substring(0, 7) + "...";
			}
		}
		return entName;
	}
	
	
}
