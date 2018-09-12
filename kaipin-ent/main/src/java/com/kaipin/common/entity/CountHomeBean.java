package com.kaipin.common.entity;

public class CountHomeBean {
	/**
	 * 数量
	 */
	private String counts;
	/**
	 * 标签
	 */
	private String tag;
	
	/**
	 * 状态
	 */
	private String status;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getCounts() {
		return counts;
	}
	public void setCounts(String counts) {
		this.counts = counts;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
}
