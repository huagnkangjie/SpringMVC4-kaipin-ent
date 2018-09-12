package com.kaipin.oss.model;

public class CommonModel {

	private String createTimes;//自己新建的一个属性，用于返回时间字符串
	
	private String isGuoQi;//是否过期

	public String getCreateTimes() {
		return createTimes;
	}

	public void setCreateTimes(String createTimes) {
		this.createTimes = createTimes;
	}

	public String getIsGuoQi() {
		return isGuoQi;
	}

	public void setIsGuoQi(String isGuoQi) {
		this.isGuoQi = isGuoQi;
	}
}
