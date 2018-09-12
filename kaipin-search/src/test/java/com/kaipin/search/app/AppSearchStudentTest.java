package com.kaipin.search.app;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.alibaba.fastjson.JSON;
import com.kaipin.search.Constants;
import com.kaipin.search.JUnitActionBase;
import com.kaipin.search.service.AppSearchService;

public class AppSearchStudentTest extends JUnitActionBase {

    @Test
    public void login() {

        try {

            Map<String, Object> map = new HashMap<>();

            map.put(AppSearchService.KEYWORLD, "李");
            map.put(AppSearchService.LOCATION, "");
            map.put(AppSearchService.INDUSTRY, "");
            map.put(Constants.page, "1");
            map.put(Constants.count, "20");
 
            String json = JSON.toJSONString(map);
            System.out.println(json);

            MvcResult result = mockMvc.perform(
                    post("/search/app/result/student").contentType(MediaType.APPLICATION_JSON_UTF8)
                            .accept(MediaType.APPLICATION_JSON_UTF8_VALUE).content(json)

            ).andDo(MockMvcResultHandlers.print()).andReturn();

            System.out.println(status().isNoContent());

            System.out.println(content().toString());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
