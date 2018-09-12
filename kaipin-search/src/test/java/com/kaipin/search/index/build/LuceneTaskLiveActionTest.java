package com.kaipin.search.index.build;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.alibaba.fastjson.JSON;
import com.kaipin.search.JUnitActionBase;
import com.kaipin.search.constant.SearchTask;
import com.kaipin.search.service.AppSearchService;
import com.kaipin.search.service.SearchLuceneTasksService;

public class LuceneTaskLiveActionTest extends JUnitActionBase {


    @Test
    public void test(){
        try {
 
            Map<String, Object> map = new HashMap<>();
 
            map.put(SearchLuceneTasksService.TASK_OBJ_ID, "eb6c12fa-c6cd-49fd-9327-7ec1d582cd36");
            map.put(SearchLuceneTasksService.TASK_OBJ_TYPE, SearchTask.ObjType.live);
            map.put(SearchLuceneTasksService.TASK_OPT, SearchTask.OptType.delete);
            String json = JSON.toJSONString(map);
            System.out.println(json);

            MvcResult result = mockMvc.perform(post("/lucene/task/operation").contentType(MediaType.APPLICATION_JSON_UTF8)
                    .accept(MediaType.APPLICATION_JSON_UTF8_VALUE).content(json)

            ).andDo(MockMvcResultHandlers.print()).andReturn();

            System.out.println(status().isNoContent());

            System.out.println(content().toString());
            
            
        }catch(Exception e){
            
        }
    }
}
