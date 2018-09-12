package com.kaipin.search.presentation.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kaipin.search.Status;
import com.kaipin.search.common.config.impl.SystemConfig;
import com.kaipin.search.common.message.Messages;

import com.kaipin.search.exception.ValidateException;
import com.kaipin.search.model.OutPacket;
import com.kaipin.search.presentation.BaseAction;
import com.kaipin.search.service.IndexBuilderService;
import com.kaipin.search.service.SearchLuceneTasksService;
import com.kaipin.search.service.impl.ExpireResourceServiceImpl;
import com.kaipin.search.service.impl.IndexBuilderServiceImpl.IndexCount;

/**
 * http://www.cnblogs.com/dazhaxie/archive/2012/11/21/2780742.html
 *  
 *
 */
@Controller
@RequestMapping("/lucene")
public class LuceneRebuilderAction extends BaseAction {
    private final static Logger LOGGER = LoggerFactory.getLogger(ExpireResourceServiceImpl.class);

    @Autowired
    private IndexBuilderService indexBuilderService;



    @Autowired
    private SearchLuceneTasksService searchLuceneTasksService;



    @RequestMapping(value = "/unhandled", method = {RequestMethod.GET, RequestMethod.POST},
            produces = "application/json")
    public @ResponseBody ResponseEntity<Object> unhandle(

    @RequestParam(value = "password", defaultValue = "") String password,
            HttpServletRequest request, HttpServletResponse response)
                    throws ValidateException, Exception {

        if (StringUtils.isEmpty(password)) {

            throw new ValidateException("" + Status.R_10013,
                    Messages.getString("" + Status.R_10013, "password"));

        }

        String pwd = SystemConfig.getInstance().getPassword();

        if (!pwd.equals(password)) {
            throw new ValidateException("" + Status.R_20001,
                    Messages.getString("" + Status.R_20001));

        }
        
        LOGGER.info("进入 /lucene/unhandled");

        searchLuceneTasksService.excuteUnHandleTask();

        return buildResponseEntity(new OutPacket(), response);

        // } else {
        // return buildResponseEntity(getStoreException(), response);
        // }

    }

    /**
     * 重新构建
     * 
     * @param username
     * @param password
     * @param request
     * @param response
     * @return
     * @throws ValidateException
     * @throws Exception
     */

    @RequestMapping(value = "/rebuild", method = {RequestMethod.GET, RequestMethod.POST},
            produces = "application/json")
    public @ResponseBody ResponseEntity<Object> rebuild(

    @RequestParam(value = "password", defaultValue = "") String password,
            HttpServletRequest request, HttpServletResponse response)
                    throws ValidateException, Exception {

        if (StringUtils.isEmpty(password)) {

            throw new ValidateException("" + Status.R_10013,
                    Messages.getString("" + Status.R_10013, "password"));

        }

        String pwd = SystemConfig.getInstance().getPassword();

        if (!pwd.equals(password)) {
            throw new ValidateException("" + Status.R_20001,
                    Messages.getString("" + Status.R_20001));

        }

        IndexCount count = indexBuilderService.reBuildAll();

        return buildResponseEntity(count, response);

        // } else {
        // return buildResponseEntity(getStoreException(), response);
        // }

    }

}
