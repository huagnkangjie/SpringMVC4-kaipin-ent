package com.kaipin.task.presentation;

import java.util.HashMap;
import java.util.Map;

import javax.script.ScriptException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.kaipin.task.exception.AuthorizationException;
import com.kaipin.task.exception.BaseException;
import com.kaipin.task.exception.MethodNotFoundException;
import com.kaipin.task.exception.ValidateException;
import com.kaipin.task.model.OutPacket;

@ControllerAdvice
public class BaseAction {

	private static final Logger LOG = Logger.getLogger(BaseAction.class);

	@ExceptionHandler({ NoSuchMethodException.class })
	protected ResponseEntity<Object> handleNoSuchMethodException(NoSuchMethodException e) {
		LOG.error("execute script error", e);
		return new ResponseEntity<Object>(buildErrorMsg(e), HttpStatus.OK);

		// return new ResponseEntity<Object>(buildErrorMsg(e),
		// HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@ExceptionHandler({ ScriptException.class })
	protected ResponseEntity<Object> handleScriptException(ScriptException e) {
		LOG.error("execute script error", e);
		return new ResponseEntity<Object>(buildErrorMsg(e), HttpStatus.OK);

		// return new ResponseEntity<Object>(buildErrorMsg(e),
		// HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@ExceptionHandler({ ValidateException.class })
	protected ResponseEntity<Object> handleValidateException(ValidateException e) {
		LOG.error("validate error", e);
		return new ResponseEntity<Object>(buildErrorMsg(e), HttpStatus.OK);

		// return new ResponseEntity<Object>(buildErrorMsg(e),
		// HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler({ MethodNotFoundException.class })
	protected ResponseEntity<Object> handleMethodNotFoundException(MethodNotFoundException e) {
		LOG.error("validate error", e);
		return new ResponseEntity<Object>(buildErrorMsg(e), HttpStatus.OK);

		// return new ResponseEntity<Object>(buildErrorMsg(e),
		// HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler({ Exception.class })
	protected ResponseEntity<Object> handleException(Exception e) {
		LOG.error("server error", e);
		return new ResponseEntity<Object>(buildErrorMsg(e), HttpStatus.OK);

		// return new ResponseEntity<Object>(buildErrorMsg(e),
		// HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@ExceptionHandler({ AuthorizationException.class })
	protected ResponseEntity<Object> handleAuthorizationException(AuthorizationException e) {
		LOG.error("server error", e);
		return new ResponseEntity<Object>(buildErrorMsg(e), HttpStatus.OK);

		// return new ResponseEntity<Object>(buildErrorMsg(e),
		// HttpStatus.UNAUTHORIZED);

	}

	/**
	 * 项目路径
	 * 
	 * @param request
	 * @return
	 */
	protected String getBasePath(HttpServletRequest request) {
		// String path = request.getContextPath();
		return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
	}

	private Map<String, Object> buildErrorMsg(Exception ex) {
		Map<String, Object> msg = new HashMap<String, Object>();

		if (StringUtils.isNotEmpty(ex.getMessage())) {

			if (ex instanceof BaseException) {
				BaseException bex = (BaseException) ex;
				msg.put("code", bex.getErrorCode());

			}
			msg.put("message", ex.getMessage());

		} else {

			msg.put("message", ex.toString());
		}

		return msg;
	}

	protected String redirectTo(String url) {
		StringBuffer rto = new StringBuffer("redirect:");
		rto.append(url);
		return rto.toString();
	}

	/**
	 * 
	 * 返回 JSON 格式对象
	 * 
	 * @param object
	 *            转换对象
	 * @return
	 */
	protected String toJson(Object object) {
		return JSON.toJSONString(object, SerializerFeature.BrowserCompatible);
	}

	/**
	 * 
	 * 返回 JSON 格式对象
	 * 
	 * @param object
	 *            转换对象
	 * @param features
	 *            序列化特点
	 * @return
	 */
	protected String toJson(Object object, String format) {
		if (format == null) {
			return toJson(object);
		}
		return JSON.toJSONStringWithDateFormat(object, format, SerializerFeature.WriteDateUseDateFormat);
	}

}
