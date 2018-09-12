package com.kaipin.search.presentation.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kaipin.search.Status;
import com.kaipin.search.common.message.Messages;
import com.kaipin.search.exception.ValidateException;
import com.kaipin.search.model.OutPacket;
import com.kaipin.search.service.impl.LiveIndexRepairServiceImpl;
import com.kaipin.search.service.impl.PositionIndexRepairServiceImpl;

@Controller
@RequestMapping("/lucene")
public class LiveIndexAction extends LuceneIndexAction {

	/**
	 * 添加视频
	 * 
	 * @param map
	 * @param request
	 * @param response
	 * @return
	 * @throws ValidateException
	 */
	@RequestMapping(value = "/live/create", method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	public @ResponseBody ResponseEntity<Object> createLive(@RequestBody Map<String, Object> map,
			HttpServletRequest request, HttpServletResponse response) throws ValidateException {

		checkAttribute(map, LiveIndexRepairServiceImpl.ID, LiveIndexRepairServiceImpl.SUBJECT,
							LiveIndexRepairServiceImpl.START_TIME);

		boolean b = luceneIndexFactory.createLiveIndexInner (  map);

		if (b) {
			return buildResponseEntity(new OutPacket(), response);
		} else {
			return buildResponseEntity(getStoreException(), response);
		}

	}

	/**
	 * 更新视频
	 * 
	 * @param map
	 * @param request
	 * @param response
	 * @return
	 * @throws ValidateException
	 */
	@RequestMapping(value = "/live/update", method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	public @ResponseBody ResponseEntity<Object> updateLive(@RequestBody Map<String, Object> map,
			HttpServletRequest request, HttpServletResponse response) throws ValidateException {

		checkAttribute(map, LiveIndexRepairServiceImpl.ID, LiveIndexRepairServiceImpl.SUBJECT,
				LiveIndexRepairServiceImpl.START_TIME);


		boolean b = luceneIndexFactory.updateLiveIndexInnder( map);

		if (b) {
			return buildResponseEntity(new OutPacket(), response);
		} else {
			return buildResponseEntity(getStoreException(), response);
		}

	}

	/**
	 * 删除视频
	 * 
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 * @throws ValidateException
	 */
	@RequestMapping(value = "/live/delete/{id}", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody ResponseEntity<Object> deleteLive(@PathVariable("id") String id,

	HttpServletRequest request, HttpServletResponse response) throws ValidateException {

		if (id == null || id.equals("")) {

			throw new ValidateException(Status.R_10008 + "", Messages.getString(Status.R_10008 + "", ""));

		}

		boolean b = luceneIndexFactory.deleteLiveIndex(  id);

		if (b) {
			return buildResponseEntity(new OutPacket(), response);
		} else {
			return buildResponseEntity(getStoreException(), response);
		}

	}
}
