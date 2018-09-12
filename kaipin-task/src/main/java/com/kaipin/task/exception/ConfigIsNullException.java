 

package com.kaipin.task.exception;

/**
 * 
 * 系统配置Key获得的Value为空
 
 * 
 */
public class ConfigIsNullException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ConfigIsNullException(String msg) {
		super(msg);
	}
}
