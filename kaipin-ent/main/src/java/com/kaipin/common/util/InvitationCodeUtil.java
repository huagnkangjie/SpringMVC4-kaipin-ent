package com.kaipin.common.util;

import java.util.Random;

/**
 * 随机邀请码
 * @author Mr-H
 *
 */
public class InvitationCodeUtil {


	/**
	 * 获取6位随机邀请码
	 * @return
	 */
	public static String createRandCode() {
		String allChar = "ABC0DE1FG2HI3JK4LM5NO6PQ7RS8TU9VWXYZ";
		int length = 6;
		StringBuffer sb = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			sb.append(allChar.charAt(random.nextInt(allChar.length())));
		}

		return sb.toString();
	}
	public static void main(String[] args) {
		System.out.println( createRandCode() );
	}
	

	

}
