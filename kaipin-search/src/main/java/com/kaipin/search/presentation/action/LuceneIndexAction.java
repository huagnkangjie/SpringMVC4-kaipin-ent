package com.kaipin.search.presentation.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.kaipin.search.presentation.BaseAction;
import com.kaipin.search.service.LuceneIndexFactory;
import com.kaipin.search.service.impl.CompanyIndexRepairServiceImpl;
import com.kaipin.search.service.impl.LiveIndexRepairServiceImpl;
import com.kaipin.search.service.impl.PositionIndexRepairServiceImpl;

/**
 * 
 * 全文检索API
 * 
 * @author Tony
 */

public class LuceneIndexAction extends BaseAction {

	@Autowired
	protected  LuceneIndexFactory luceneIndexFactory;

//	protected  String getTaskId(HttpServletRequest request) {
//		String taskId = request.getParameter("task_id");
//		if (taskId == null || taskId.equals("")) {
//			throw new ValidateException(Status.R_10008 + "", Messages.getString(Status.R_10008 + "", "task_id"));
//		}
//		return taskId;
//	}

	




}
