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
import com.kaipin.search.service.impl.SchInfoIndexRepairServiceImpl;
import com.kaipin.search.service.impl.StuUserIndexRepairServiceImpl;


/**
 * 学生  索引管理
 * @author Mr-H
 *
 */
@Controller
@RequestMapping("/lucene")
public class SchInfoIndexAction extends LuceneIndexAction {

	/**
	 * 添加
	 * 
	 * @param map
	 * @param request
	 * @param response
	 * @return
	 * @throws ValidateException
	 */
	@RequestMapping(value = "/sch/create", method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	public @ResponseBody ResponseEntity<Object> createLive(@RequestBody Map<String, Object> map,
			HttpServletRequest request, HttpServletResponse response) throws ValidateException {

		checkAttribute(map, SchInfoIndexRepairServiceImpl.ID, SchInfoIndexRepairServiceImpl.SCHOOL_NAME,
				SchInfoIndexRepairServiceImpl.CREATE_TIME);

		
		boolean b = luceneIndexFactory.createSchInfoIndexInner( map);

		if (b) {
			return buildResponseEntity(new OutPacket(), response);
		} else {
			return buildResponseEntity(getStoreException(), response);
		}

	}

	/**
	 * 更新
	 * 
	 * @param map
	 * @param request
	 * @param response
	 * @return
	 * @throws ValidateException
	 */
	@RequestMapping(value = "/sch/update", method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	public @ResponseBody ResponseEntity<Object> updateLive(@RequestBody Map<String, Object> map,
			HttpServletRequest request, HttpServletResponse response) throws ValidateException {

		checkAttribute(map, SchInfoIndexRepairServiceImpl.ID, SchInfoIndexRepairServiceImpl.SCHOOL_NAME,
				SchInfoIndexRepairServiceImpl.CREATE_TIME);

		boolean b = luceneIndexFactory.updateSchInfoIndexInnder ( map);

		if (b) {
			return buildResponseEntity(new OutPacket(), response);
		} else {
			return buildResponseEntity(getStoreException(), response);
		}

	}

	/**
	 * 删除
	 * 
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 * @throws ValidateException
	 */
	@RequestMapping(value = "/sch/delete/{id}", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody ResponseEntity<Object> deleteLive(@PathVariable("id") String id,

	HttpServletRequest request, HttpServletResponse response) throws ValidateException {

		if (id == null || id.equals("")) {

			throw new ValidateException(Status.R_10008 + "", Messages.getString(Status.R_10008 + "", ""));

		}

		boolean b = luceneIndexFactory.deleteSchInfoIndex(  id);

		if (b) {
			return buildResponseEntity(new OutPacket(), response);
		} else {
			return buildResponseEntity(getStoreException(), response);
		}

	}
}

