package com.kaipin.search.presentation.action.search;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kaipin.search.exception.ValidateException;
import com.kaipin.search.presentation.BaseAction;
import com.kaipin.search.service.AppSearchService;

/**
 * app搜索服务
 * 
 */
@Controller
@RequestMapping("/search/app")
public class SearchServiceAction extends BaseAction {

    @Autowired
    private AppSearchService appSearchService;

    /**
     * 搜索企业
     * 
     * @param request 请求参数
     * @param response
     * @return 企业json
     * @throws ValidateException
     */
    @RequestMapping(value = "/result/company", method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    public @ResponseBody ResponseEntity<Object> searchCompany(
            @RequestBody Map<String, Object> request, HttpServletResponse response)
                    throws ValidateException {

        Object json = appSearchService.searchCompany(request);

        return buildResponseEntity(json, response);

    }



    /**
     * 搜索视频
     * 
     * @param request
     * @param response
     * @return
     * @throws ValidateException
     */
    @RequestMapping(value = "/result/live", method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    public @ResponseBody ResponseEntity<Object> searchLive(@RequestBody Map<String, Object> request,
            HttpServletResponse response) throws ValidateException {
        Object json = appSearchService.searchLive(request);
        return buildResponseEntity(json, response);
    }


    /**
     * 搜索学生
     * 
     * @param request
     * @param response
     * @return
     * @throws ValidateException
     */
    @RequestMapping(value = "/result/student", method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    public @ResponseBody ResponseEntity<Object> searchStudent(
            @RequestBody Map<String, Object> request, HttpServletResponse response)
                    throws ValidateException {
        Object json = appSearchService.searchStudent(request);
        return buildResponseEntity(json, response);
    }


    /**
     * 搜索学校
     * 
     * @param request
     * @param response
     * @return
     * @throws ValidateException
     */
    @RequestMapping(value = "/result/school", method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    public @ResponseBody ResponseEntity<Object> searchSchool(
            @RequestBody Map<String, Object> request, HttpServletResponse response)
                    throws ValidateException {
        Object json = appSearchService.searchSchool(request);
        return buildResponseEntity(json, response);
    }



    /**
     * 搜索职位
     * 
     * @param request
     * @param response
     * @return
     * @throws ValidateException
     */
    @RequestMapping(value = "/result/position", method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    public @ResponseBody ResponseEntity<Object> searchPosition(
            @RequestBody Map<String, Object> request, HttpServletResponse response)
                    throws ValidateException {
        Object json = appSearchService.searchPosition(request);

        return buildResponseEntity(json, response);
    }



    /**
     * 搜索统计
     * 
     * @param request
     * @param response
     * @return
     * @throws ValidateException
     */
    @RequestMapping(value = "/count", method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    public @ResponseBody ResponseEntity<Object> searchCount(
            @RequestBody Map<String, Object> request, HttpServletResponse response)
                    throws ValidateException {

        Object json = appSearchService.searchCount(request);

        return buildResponseEntity(json, response);

    }

}
