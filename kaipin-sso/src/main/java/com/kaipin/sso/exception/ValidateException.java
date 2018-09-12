package com.kaipin.sso.exception;

/**
 * key value 验证
 * 
 *
 */

public class ValidateException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * default constructor
	 */
	public ValidateException() {
		super();
	}

	/**
	 * @param message
	 */
	public ValidateException(String message) {
		super(message);
	}
	
	
	
	/**
	 * @param message
	 */
	public ValidateException(String code,String message) {
		super(code,message);
 
	}
	
	
	

	/**
	 * @param message
	 * @param cause
	 */
	public ValidateException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param cause
	 */
	public ValidateException(Throwable cause) {
		super(cause);
	}
	
	

}