package com.kaipin.search.presentation.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kaipin.search.constant.SearchTask;
import com.kaipin.search.exception.ValidateException;
import com.kaipin.search.model.OutPacket;
import com.kaipin.search.service.ExpireResourceService;
import com.kaipin.search.service.LuceneIndexFactory;
import com.kaipin.search.service.SearchLuceneTasksService;
import com.kaipin.search.service.impl.LiveIndexRepairServiceImpl;

/**
 * 任务管理
 * 
 * @author Mr-H
 *
 */

@Controller
@RequestMapping("/lucene")
public class LuceneTaskAction extends LuceneIndexAction {

    @Autowired
    private ExpireResourceService expireResourceService;

    /**
     *  索引处理，根据 {@link SearchLuceneTasksService#TASK_OBJ_TYPE} 调用对象类型
     *   根据  {@link SearchLuceneTasksService#TASK_OPT}  处理 create,update,delete
     * <b>如果创建失败,放入任务表里 定时重新建立</b>
     * 
     * @param map 必须带上的参数：
     * 				</br>obj_id 资源id
     * 				</br>obj_type 对象类型(0-公司,1-职位,2-视频,3-学生,4-学校)
     * 				</br>opt 操作类型(0-add,1-delete,2-update
     * @param request
     * @param response
     * @return
     * @throws ValidateException
     */
    @RequestMapping(value = "/task/operation", method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    public @ResponseBody ResponseEntity<Object> createLive(@RequestBody Map<String, Object> map,
            HttpServletRequest request, HttpServletResponse response) throws ValidateException {

        checkAttribute(map, SearchLuceneTasksService.TASK_OBJ_ID,
                SearchLuceneTasksService.TASK_OBJ_TYPE, SearchLuceneTasksService.TASK_OPT);

        
        
        
        boolean b = luceneIndexFactory.insertLuceneTask(map);
         
        if(   MapUtils.getByte(map,  SearchLuceneTasksService.TASK_OBJ_TYPE,(byte) -1)== SearchTask.ObjType.live ){
            
            expireResourceService.handleExpireLiveResource();
            
        }
        

        if (b) {
            return buildResponseEntity(new OutPacket(), response);
        } else {
            return buildResponseEntity(getStoreException(), response);
        }

    }
}
