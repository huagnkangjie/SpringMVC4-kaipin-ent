package com.kaipin.search.demo;


import org.junit.Assert;
import org.junit.Test;

import com.kaipin.search.core.index.SearchHelper;

import java.util.List;

/**
 * 测试 IK 分词器
 
 */
public class IKTester {

    @org.junit.Test
    public void test_highlight() throws Exception {
        String text = "SQL server 是最好的 数据库 应用服务器";
//        Assert.assertEquals("<span class=\"highlight\">SQL</span> <span class=\"highlight\">server</span> 是最好的 数据库 应用服务器", SearchHelper.highlight(text, "sql server"));
        System.out.println(SearchHelper.highlight(text, "最好"));
    }

// @Test
    public void test_split() throws Exception {
        String text = "北京海大";
        long ct = System.currentTimeMillis();
        List<String> stopWords = SearchHelper.splitKeywords(text);
//        Assert.assertEquals("android", stopWords.get(0));
//        Assert.assertEquals("刷机", stopWords.get(1));
System.out.println(stopWords.toString());
        
      //  Assert.assertTrue((System.currentTimeMillis() - ct) < 1200);
    }
    
    public static void main(String[] args) {
		String s = "asdf";
	}


}
