package com.kaipin.oss.exception.security;

/**
 * 用户被删除
 */
@SuppressWarnings("serial")
public class DeleteException extends AccountStatusException {
	public DeleteException() {
	}

	public DeleteException(String msg) {
		super(msg);
	}

	public DeleteException(String msg, Object extraInformation) {
		super(msg, extraInformation);
	}
}
