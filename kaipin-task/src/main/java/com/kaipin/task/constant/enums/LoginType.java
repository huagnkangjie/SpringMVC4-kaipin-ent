package com.kaipin.task.constant.enums;

/**
 * 登陆类型
 * 
 * @author Tony
 *
 */
public enum LoginType {

	web(0), android(1), ios(2);

	private int value;

	private LoginType(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

}
