package com.kaipin.oss.model.stu;

import com.kaipin.oss.model.common.Code;
import com.kaipin.oss.model.common.UserLocalauth;

/**
 * 学生基本属性
 * @author Mr-H
 *
 */
@SuppressWarnings("serial")
public class StuBaseInfo extends Code{
	
	private String uid;
	private String userName;
	private String locationCode;
	private String educationCode;
	private String majorCode;
	private String schoolCode;
	private String createTime;
	private String oneToOneCount;//推荐次数
	private String oneToOnePositionCount;//推荐职位总数
	
	private StuUser stuUser; // 学生基本信息
	
	private UserLocalauth localUser;//本地用户信息
	
	

	public StuUser getStuUser() {
		return stuUser;
	}

	public void setStuUser(StuUser stuUser) {
		this.stuUser = stuUser;
	}

	public UserLocalauth getLocalUser() {
		return localUser;
	}

	public void setLocalUser(UserLocalauth localUser) {
		this.localUser = localUser;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getLocationCode() {
		return locationCode;
	}

	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}

	public String getEducationCode() {
		return educationCode;
	}

	public void setEducationCode(String educationCode) {
		this.educationCode = educationCode;
	}

	public String getMajorCode() {
		return majorCode;
	}

	public void setMajorCode(String majorCode) {
		this.majorCode = majorCode;
	}

	public String getSchoolCode() {
		return schoolCode;
	}

	public void setSchoolCode(String schoolCode) {
		this.schoolCode = schoolCode;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getOneToOneCount() {
		return oneToOneCount;
	}

	public void setOneToOneCount(String oneToOneCount) {
		this.oneToOneCount = oneToOneCount;
	}

	public String getOneToOnePositionCount() {
		return oneToOnePositionCount;
	}

	public void setOneToOnePositionCount(String oneToOnePositionCount) {
		this.oneToOnePositionCount = oneToOnePositionCount;
	}
}
