package com.kaipin.search.model;

import java.util.ArrayList;
import java.util.List;

 
public class BaseAppSearchCount  extends OutPacket { 
    public static class SearchCount {
        public int total = 0;
        public String keyword = "";
        public String type = "";
        public List<SearchCountRecommend> recommend = new ArrayList<>(); // 推荐默认<=3

    }

    public static class SearchCountRecommend {
        public String name;
        public String id;

        public SearchCountRecommend(String id, String name) {
            this.id = id;
            this.name = name;
        }

    }
}
