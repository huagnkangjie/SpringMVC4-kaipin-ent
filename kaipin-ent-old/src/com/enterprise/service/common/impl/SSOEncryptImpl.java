package com.enterprise.service.common.impl;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import com.enterprise.service.common.ISSOEncrypt;
import com.util.Base64Util;

public class SSOEncryptImpl implements ISSOEncrypt {

	private SecretKey secretKey;
	public SSOEncryptImpl( ) {
		
	}
	public SSOEncryptImpl(String str) {
		setKey(str);// generate secret key
	}

	public SecretKey getSecretKey() {
		return secretKey;
	}

	/**
	 * generate KEY
	 */
	public void setKey(String strKey) {
		try {
			byte[] bk =strKey.getBytes(); //MD5.md5Raw(strKey.getBytes(SSOConfig.getSSOEncoding()));

			DESKeySpec desKey = new DESKeySpec(bk);
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			this.secretKey = keyFactory.generateSecret(desKey);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String encryptDES(String str) {
		byte[] encryptBytes = null;
		String encryptStr = null;
		try {
			Cipher cipher = Cipher.getInstance("DES");

			cipher.init(Cipher.ENCRYPT_MODE, getSecretKey());
			encryptBytes = cipher.doFinal(str.getBytes());
			if (encryptBytes != null) {
				encryptStr = Base64Util.encryptBASE64(encryptBytes);
			}
		} catch (Exception e) {
		}
		return encryptStr;

	}

	public String decryptDES(String str) {
		byte[] decryptBytes = null;
		String decryptStr = null;

		try {
			Cipher cipher = Cipher.getInstance("DES");
			// 用密匙初始化Cipher对象
			cipher.init(Cipher.DECRYPT_MODE, getSecretKey());
			// 开始解密操作
			byte[] scrBytes = Base64Util.decryptBASE64(str);
			decryptBytes = cipher.doFinal(scrBytes);
		} catch (Exception e) {
		}

		if (decryptBytes != null) {
			decryptStr = new String(decryptBytes);
		}
		return decryptStr;
	}

	
	@Override
	public String encrypt(String value, String key) throws Exception {
		setKey(key);
		return encryptDES(value);
	}

	@Override
	public String decrypt(String value, String key) throws Exception {
		setKey(key);
		return decryptDES(value);
	}

}

