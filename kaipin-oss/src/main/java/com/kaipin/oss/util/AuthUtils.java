 

package com.kaipin.oss.util;

import org.apache.commons.codec.digest.DigestUtils;

import com.kaipin.oss.constant.SystemConstant;

 

/**
 * 授权相关的工具类
 * 
 *  
 * 
 */
public class AuthUtils {

	/**
	 * 生产密文密码
	 * 
	 * @param password
	 *            明文密码
	 * @param email
	 *            邮箱
	 * @return
	 * @throws AuthException
	 */
	public static String getPassword(String password) {
		return DigestUtils.md5Hex(password).toLowerCase();
	}

	/**
	 * @param str
	 * @return
	 */
	public static String MD5(String str) {
		return DigestUtils.md5Hex(str).toLowerCase();
	}

	/**
	 * @param email
	 * @return
	 */
	public static String getFaceUrl(String email) {
		return SystemConstant.FACE_URL + "/" + AuthUtils.MD5(email) + ".jpg";
	}
}
