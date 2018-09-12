package com.kaipin.oss.model.sch;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.kaipin.oss.model.Idable;

/**
 * 对应4个表的模型
 * school_info_feedback
 * school_info_feedback_relation
 * school_info_feedback_config
 * school_info_feedback_template
 * @author Mr-H
 *
 */

@SuppressWarnings("serial")
public class SchInfoFeedback implements Idable<String>, Serializable{

	private String id;

    private String schoolId;

    private Long createTime;

    private Long platformUserId;
    
    private String title;

    private String varName;

    private Integer priority;
    
    private String configId;

    private String feedbackId;
    
    private String content;
    
    public List<SchInfoFeedback> templeteList = new ArrayList<SchInfoFeedback>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Long getPlatformUserId() {
		return platformUserId;
	}

	public void setPlatformUserId(Long platformUserId) {
		this.platformUserId = platformUserId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getVarName() {
		return varName;
	}

	public void setVarName(String varName) {
		this.varName = varName;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public String getConfigId() {
		return configId;
	}

	public void setConfigId(String configId) {
		this.configId = configId;
	}

	public String getFeedbackId() {
		return feedbackId;
	}

	public void setFeedbackId(String feedbackId) {
		this.feedbackId = feedbackId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<SchInfoFeedback> getTempleteList() {
		return templeteList;
	}

	public void setTempleteList(List<SchInfoFeedback> templeteList) {
		this.templeteList = templeteList;
	}

	public String getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}

}
