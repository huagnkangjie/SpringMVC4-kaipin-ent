package com.kaipin.student.presentation.action.resume;

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

import com.kaipin.common.constants.Constant;
import com.kaipin.common.constants.PhoneMsg;
import com.kaipin.common.entity.Json;
import com.kaipin.common.entity.User;
import com.kaipin.common.presentation.action.BaseAction;
import com.kaipin.common.util.HttpRequestUtil;
import com.kaipin.common.util.LogUtil;
import com.kaipin.common.util.StringUtil;
import com.kaipin.common.util.TimeUtil;
import com.kaipin.common.util.UuidUtil;
import com.kaipin.enterprise.model.msg.MsgUserInterview;
import com.kaipin.enterprise.model.position.PositionDelivery;
import com.kaipin.enterprise.model.position.PositionInfo;
import com.kaipin.enterprise.model.user.CompanyInfo;
import com.kaipin.enterprise.service.msg.IMsgUserInterviewService;
import com.kaipin.enterprise.service.position.IPDeliveryService;
import com.kaipin.enterprise.service.position.IPositionService;
import com.kaipin.enterprise.service.user.ICompanyInfoService;
import com.kaipin.student.model.resume.ResumeInfo;
import com.kaipin.student.service.resume.IResumeInfoService;
import com.kaipin.student.service.resume.IResumeService;
import com.kaipin.university.service.user.IUserLocalauthService;


/**
 * 简历是否通过
 * @author Mr-H
 *
 */
@Controller
@RequestMapping("/resumePassOrNo")
public class ResumePassOrNoController extends BaseAction{

	Logger logger = Logger.getLogger(ResumePassOrNoController.class.getName());
	@Autowired
	private IResumeService iResumeService;
	@Autowired
	private IPDeliveryService pDeliveryService;
	@Autowired
	private IPositionService positionService;
	@Autowired
	private IMsgUserInterviewService userMsgViewService;
	@Autowired
	private ICompanyInfoService companyInfoService;
	@Autowired
	private IUserLocalauthService localUserService;
	@Autowired
	private IResumeInfoService resumeInfoService;
	
	/**
	 * 简历通过
	 * 初始化
	 * @return
	 */
	@RequestMapping(value="/initPass")  
	public String initPass(HttpServletRequest request ,Model model ,User user) {
		
		String orgName = this.getOrgName(request, user);
		this.setSysAttr(model, orgName, null, null);
		
		return "/stu/resume/count/pass";  
	}
	
	/**
	 * 简历不通过
	 * 初始化
	 * @return
	 */
	@RequestMapping(value="/initNOPass")  
	public String initNOPass(HttpServletRequest request ,Model model ,User user) {
		
		String orgName = this.getOrgName(request, user);
		this.setSysAttr(model, orgName, null, null);
		
		return "/stu/resume/count/no_pass";  
	}
	
	/**
	 * 简历筛选
	 * 通过 或者 不通过
	 * @param ids
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/passOrNo")
	@ResponseBody
	public Json passOrNo (HttpServletRequest request, 
			String oper, String ids, 
			String companyId, String userId,
			String  resumeId, String postionId, String viewId,
			String times, String face, String status) { 
		try {
			Json json = new Json();
			String idA[] = ids.split(","); 
			
			for (String id : idA) {
				
				
				PositionDelivery delivery = pDeliveryService.selectByPrimaryKey(id);
				PositionInfo position = positionService.selectByPrimaryKey(delivery.getPositionId());
				ResumeInfo resume = resumeInfoService.selectByPrimaryKey(delivery.getResumeId());
				CompanyInfo companyInfo = companyInfoService.selectByPrimaryKey(delivery.getCompanyId());
				
				String stuUserName =getUserName(resume.getSurname() + resume.getMissSurname());//学生名字
				String entName = getEntName(companyInfo.getEntName());//企业名称
				String positionName = getEntName(position.getPositionName());//职位名称
				String userPhone = localUserService.selectByPrimaryKey(delivery.getStuUserId()).getPhone();
				String createTime = TimeUtil.getTimeByMillis(delivery.getCreateTime()).substring(0, 10);
				String url = PhoneMsg.getUrl();
				
				
				Map<String,Object> phoneParam = new HashMap<String, Object>();
				phoneParam.put(PhoneMsg.STU, stuUserName);
				phoneParam.put(PhoneMsg.ENT, entName);
				phoneParam.put(PhoneMsg.MST, createTime);
				phoneParam.put(PhoneMsg.PN, positionName);
				
				String param = "";
				
				PositionDelivery info = new PositionDelivery();
				info.setIsPass(Byte.valueOf(oper));
				info.setIsLook(Byte.valueOf(Constant.VALUE_ONE));
				info.setId(id);
				int i = pDeliveryService.updateByPrimaryKeySelective(info);
				
				String title = "通过职位" + position.getPositionName() + "简历筛选";
				String detail = "通过职位" + position.getPositionName() + "简历筛选";
				
				if(oper.equals(Constant.VALUE_THREE)){
					title = "未" + title;
					detail = "未" + detail;
				}
				
				String msgId = UuidUtil.getUUID();
				MsgUserInterview msg = new MsgUserInterview();
				msg.setId(msgId);
				msg.setCreateTime(TimeUtil.currentTimeMillis());
				msg.setTitle(title);
				msg.setType(Byte.valueOf(oper));
				msg.setStuUserId(delivery.getStuUserId());
				msg.setContent(detail);
				msg.setHint(detail);
				msg.setObjectId("");//通过或者拒绝，此id为空
				
				//往消息表插入数据
				int flag = userMsgViewService.insertSelective(msg);
				
				//写日志
				String content = "通过筛选";
				if(oper.equals(Constant.VALUE_THREE)){
					content = "未" + content;
					param = PhoneMsg.getUrlValue(PhoneMsg.RESUME_STATUS_THREE_CODE , userPhone, phoneParam);
				}else{
					param = PhoneMsg.getUrlValue(PhoneMsg.RESUME_STATUS_TWO_CODE , userPhone, phoneParam);
				}
				
				//发送短信
				HttpRequestUtil.sendPost(url, param);
				
				
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("position_delivery_id", id);
				map.put("create_time", TimeUtil.currentTimeMillis());
				map.put("content", content);
				map.put("status", oper);
				map.put("type", Constant.VALUE_ONE);
				map.put("object_id", "");
				int ii = iResumeService.insertViewLog(map);
				
			}
			json.setSuccess(true);
			json.setMsg("操作成功！");
			return json;
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 初始化页面
	 * 根据一个职位查询该职位下所有
	 * 通过的简历
	 * @return
	 */
	@RequestMapping(value="/passPage")  
	public String passPage(HttpServletRequest request,String postionId ,Model model ,User user) {  
		try {
			request.setAttribute(Constant.POSTION_ID, postionId);
			
			String orgName = this.getOrgName(request, user);
			this.setSysAttr(model, orgName, null, null);
			
		} catch (Exception e) {
			logger.info(LogUtil.getTrace(e));;
			e.printStackTrace();
		}
		return "/stu/resume/list/pass_list";
	}
	
	/**
	 * 初始化页面
	 * 根据一个职位查询该职位下所有
	 * 不通过的简历
	 * @return
	 */
	@RequestMapping(value="/noPassPage")  
	public String noPassPage(HttpServletRequest request,String postionId ,Model model ,User user) {  
		try {
			request.setAttribute(Constant.POSTION_ID, postionId);
			
			String orgName = this.getOrgName(request, user);
			this.setSysAttr(model, orgName, null, null);
			
		} catch (Exception e) {
			logger.info(LogUtil.getTrace(e));;
			e.printStackTrace();
		}
		return "/stu/resume/list/no_pass_list";
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
