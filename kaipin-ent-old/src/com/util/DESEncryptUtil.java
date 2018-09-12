package com.util;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import com.util.Base64Util;

public class DESEncryptUtil  {

	private SecretKey secretKey;
	public DESEncryptUtil( ) {
		
	}
	public DESEncryptUtil(String str) {
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

	public static void main(String[] args) {
		String ss = new DESEncryptUtil("h2wmABdfM7i3K80sso").decryptDES("eTktehAcwtM-TVzlHxL4_cEnTAEt8vI__aqVayHS9pks-eNq4PncN5jSkT7II5_zWm-lxdLmHebuBIMiMiVD6nOETkSgpnwVnL7G2FxpV6fshfRNsCH_Mp1N1nFbEDNQcvyz-WBzgCrFOLO6n1aCNg7QL-62q2aFGNlyXKL-sODc4I0scnIzjnUSuW6oubWkVzORujmvBCbiNY_EKNXU1Y6t8hi10DCZ3k_rVfdkxXNXyo2-H-fJ_67Wn_0Y2kVLHm5Dz4QwdL4Mewrqv0M3sCdGT_FxA3liXxr3CxX5RnvUIQ1AqNmLBn7h3acnjzod_JnSBuRVzUC1UNtFydmXCB-pbf--WlXxVAWX8Ye2-KEtzGSGVNfh_qEN6JaE1fDj");
		System.out.println(ss);
	}
}


