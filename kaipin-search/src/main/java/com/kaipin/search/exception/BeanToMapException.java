package com.kaipin.search.exception;

/**
 * 转换异常

 */
public class BeanToMapException extends BaseException{
	/**
	 * 构造一个DAO数据访问层异常对象.
	 * @param message 信息描述
	 */
	public BeanToMapException(String message) {
		super(message);
	}

	/**
	 * 构造一个DAO数据访问层异常对象.
	 * @param errorCode 错误编码
	 * @param message 信息描述
	 */
	public BeanToMapException(String errorCode,String message) {
		super(errorCode,message,true);
	}
	
	/**
	 * 构造一个DAO数据访问层异常对象.
	 * @param errorCode 错误编码
	 * @param message 信息描述
	 * @param propertiesKey 消息是否为属性文件中的Key
	 */
	public BeanToMapException(String errorCode,String message,boolean propertiesKey) {
		super(errorCode,message,propertiesKey);
	}
	/**
	 * 构造一个DAO数据访问层异常对象.
	 * @param message 信息描述
	 * @param cause 根异常类（可以存入任何异常）
	 */
	public BeanToMapException(String message, Throwable cause) {
		super(message, cause);
	}
}
