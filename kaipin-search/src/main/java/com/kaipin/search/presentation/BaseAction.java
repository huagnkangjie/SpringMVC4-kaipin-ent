package com.kaipin.search.presentation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.script.ScriptException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceEditor;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.kaipin.search.Status;
import com.kaipin.search.common.message.Messages;
import com.kaipin.search.exception.AuthorizationException;
import com.kaipin.search.exception.BaseException;
import com.kaipin.search.exception.MethodNotFoundException;
import com.kaipin.search.exception.ValidateException;
import com.kaipin.search.model.OutPacket;

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

	
	protected ResponseEntity<Object> buildResponseEntity(Object obj, HttpServletResponse response) {
 
		return new ResponseEntity<Object>(obj, HttpStatus.OK);
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
				msg.put("code", bex.getCode());

			}
			msg.put("msg", ex.getMessage());

		} else {

			msg.put("msg", ex.toString());
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
	
	
	public OutPacket getStoreException() {
		OutPacket out = new OutPacket("1", "数据存储时发生异常");
		return out;
	}

	protected void checkMap(Map<String, Object> request) {
		if (request == null || request.size() == 0) {

			throw new ValidateException(Status.R_10008 + "", Messages.getString(Status.R_10008 + "", ""));

		}

	}
	
	
	

	public void checkAttributeKey(Map<String, Object> map, String... parms) {

		if (map == null || map.size() == 0) {

			throw new ValidateException(Status.R_10008 + "", Messages.getString(Status.R_10008 + "", ""));

		}
		if (parms == null || parms.length == 0) {

			throw new ValidateException(Status.R_10008 + "", Messages.getString(Status.R_10008 + "", ""));
		}

		String key;

		for (int pos = 0, len = parms.length; pos < len; pos++) {
			key = parms[pos];

			if (!map.containsKey(key)) {

				throw new ValidateException(Status.R_10008 + "", Messages.getString(Status.R_10008 + "", key));

			}

		}

	}

	public void checkAttribute(Map<String, Object> map, String... parms) {

		if (map == null || map.size() == 0) {

			throw new ValidateException(Status.R_10008 + "", Messages.getString(Status.R_10008 + "", ""));

		}

		if (parms == null || parms.length == 0) {

			throw new ValidateException(Status.R_10008 + "", Messages.getString(Status.R_10008 + "", ""));
		}

		String key;

		Object value;

		for (int pos = 0, len = parms.length; pos < len; pos++) {
			key = parms[pos];

			if (!map.containsKey(key)) {

				throw new ValidateException(Status.R_10008 + "", Messages.getString(Status.R_10008 + "", key));

			}

			value = MapUtils.getObject(map, key);

			if (value instanceof List) {

				List val = (List) value;
				if (val == null || val.size() == 0) {
					throw new ValidateException(Status.R_10013 + "", Messages.getString(Status.R_10013 + "", key));

				}

			} else {
				String val = String.valueOf(value);

				if (StringUtils.isEmpty(val) || val.equals("null")) {
					throw new ValidateException(Status.R_10013 + "", Messages.getString(Status.R_10013 + "", key));

				}

			}

		}

	}

}
