package com.enterprise.service.common;


/**
 * 
 * 加密 解密
 * @author Mr-H
 *
 */
public interface ISSOEncrypt {

	/**
	 * 字符串内容加密
	 * <p>
	 * 
	 * @param value
	 *            加密内容
	 * @param key
	 *            密钥
	 * @return
	 * @throws Exception
	 */
	String encrypt(String value, String key) throws Exception;

	/**
	 * 字符串内容解密
	 * <p>
	 * 
	 * @param value
	 *            解密内容
	 * @param key
	 *            密钥
	 * @return
	 * @throws Exception
	 */
	String decrypt(String value, String key) throws Exception;
}

