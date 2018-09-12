package com.kaipin.university.presentation.taglib;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.kaipin.university.common.web.spring.MessageResolver;

 

/**
 * 国际化信息
 * @author tan
 *
 */
public class MessageTag extends AbstractExecuteContext {

	@Override
	public void doTag() throws JspException, IOException {

		if (args == null) {
			this.getOut().write(MessageResolver.getMessage(getRequest(), code));

		} else {

			this.getOut().write(MessageResolver.getMessage(getRequest(), code, args));
		}

	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Object[] getArgs() {
		return args;
	}

	public void setArgs(Object[] args) {
		this.args = args;
	}

	private String code;

	private Object[] args;

}
