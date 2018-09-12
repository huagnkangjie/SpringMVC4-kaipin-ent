package com.util;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class AuthenticatorUtil extends Authenticator {

	private static final String userName = "1059976050@qq.com";
	private static final String passWord = "mlp940";

	public AuthenticatorUtil() {
		super();
	}

	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(userName, passWord);
	}

}