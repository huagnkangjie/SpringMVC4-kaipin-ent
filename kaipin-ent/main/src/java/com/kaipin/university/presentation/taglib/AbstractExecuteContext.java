package com.kaipin.university.presentation.taglib;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;

import javax.servlet.jsp.tagext.SimpleTagSupport;

public abstract class AbstractExecuteContext extends SimpleTagSupport {

	public JspWriter getOut() {
		return getJspContext().getOut();
	}

	public HttpServletRequest getRequest() {

		HttpServletRequest request = (HttpServletRequest) ((PageContext) this.getJspContext()).getRequest();

		return request;
	}

	public HttpServletResponse getResponse() {

		HttpServletResponse response = (HttpServletResponse) ((PageContext) this.getJspContext()).getResponse();

		return response;
	}

	public HttpSession getSession() {

		HttpSession session = (HttpSession) ((PageContext) this.getJspContext()).getSession();

		return session;
	}

}
