package com.kaipin.university.exception;

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
		super(message);
		this.code=code;
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