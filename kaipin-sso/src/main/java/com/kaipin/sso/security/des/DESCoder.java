package com.kaipin.sso.security.des;

import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import com.kaipin.sso.common.config.impl.SystemConfig;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * des加解密

 */

public class DESCoder {

	public static String decrypt(byte[] src, String password) throws Exception {
		// DES算法要求有一个可信任的随机数源
		SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
		// 创建一个DESKeySpec对象
		DESKeySpec desKey = new DESKeySpec(password.getBytes());
		// 创建一个密匙工厂
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		// 将DESKeySpec对象转换成SecretKey对象
		SecretKey securekey = keyFactory.generateSecret(desKey);
		// Cipher对象实际完成解密操作
		Cipher cipher = Cipher.getInstance("DES");
		// 用密匙初始化Cipher对象
		cipher.init(Cipher.DECRYPT_MODE, securekey, random);
		// 开始解密操作
		byte[] decryResult = cipher.doFinal(src);

		return new String(decryResult);

	}

	public static String decrypt(String src, String password) throws Exception {

		byte[] keys = new BASE64Decoder().decodeBuffer(src);

		return decrypt(keys, password);

	}

	public static String desCrypto(byte[] datasource, String password) {
		try {
			SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
			DESKeySpec desKey = new DESKeySpec(password.getBytes());
			// 创建一个密匙工厂，然后用它把DESKeySpec转换成
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey securekey = keyFactory.generateSecret(desKey);
			// Cipher对象实际完成加密操作
			Cipher cipher = Cipher.getInstance("DES");
			// 用密匙初始化Cipher对象
			cipher.init(Cipher.ENCRYPT_MODE, securekey, random);
			// 获取数据并加密
			// 执行加密操作

			byte[] key_bs = cipher.doFinal(datasource);

			return (new BASE64Encoder()).encodeBuffer(key_bs);

		} catch (Throwable e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String desCrypto(String datasource, String password) {

		if (datasource == null || datasource.length() == 0) {
			return null;
		}

		return desCrypto(datasource.getBytes(), password);

	}

	public static void main(String[] args) {
		try {
			// 待加密内容

			String str = "web:238a61b0347b43b5b31a93a1e1fd0cf7:3600:10";

			// 密码，长度要是8的倍数
			String password = SystemConfig.getInstance().getTokenPwd();

			String result = desCrypto(str.getBytes(), password);

			System.out.println("加密后内容为：" + result);

			// 直接将如上内容解密

			byte[] keys = new BASE64Decoder().decodeBuffer(result);

			String decryResult = decrypt(keys, password);

			System.out.println("解密后内容为：" + new String(decryResult));
		} catch (Exception e1) {
			e1.printStackTrace();
		}

	}

}
