package com.web.annex;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.pojo.Json;

/**
 * 附件上传获取进度
 * @author Mr-H
 *
 */
@Controller
@RequestMapping("annexProcess")
public class AnnexProcess {
	
	Logger log = Logger.getLogger(AnnexProcess.class.getName());

	/**
	 * 获取文件上传进度
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping({"/process"})
	@ResponseBody
	public Json register(HttpServletRequest request, HttpServletResponse response){
		Json json = new Json();
		try {
			Object process = AnnexCache.getCache(request.getSession().getId());
			if(process != null){
				json.setObj(process);
				json.setSuccess(true);
			}
			return json;
		} catch (Exception e) {
			log.info(e);
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 移除当前用户缓存
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping({"/processClear"})
	@ResponseBody
	public Json processClear(HttpServletRequest request, HttpServletResponse response){
		Json json = new Json();
		try {
			AnnexCache.removeCacheByKey(request.getSession().getId());
			return json;
		} catch (Exception e) {
			log.info(e);
			e.printStackTrace();
			return null;
		}
	}
}
