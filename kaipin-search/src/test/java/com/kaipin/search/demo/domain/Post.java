package com.kaipin.search.demo.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.kaipin.search.core.index.Searchable;

/**
 * 测试索引的对象
 *
 *  
 */
public class Post   implements Searchable  {

    private String id;
    private String title;
    private String body;

    public Post() {
    }

    public Post(String id, String t, String b) {
        this.id = id;
        this.title = t;
        this.body = b;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

 
  

	@Override
	public String  id() {
 
		return id;
	}

 
	@Override
	public void setId(String  id) {
	 
		this.id=  id;
		
	}

	@Override
    public List<String> storeFields() {
        return Arrays.asList("id", "title");
    }

    @Override
    public List<String> indexFields() {
        return Arrays.asList("title", "body");
    }

    @Override
    public Map<String, String> extendStoreDatas() {
        return null;
    }

    @Override
    public Map<String, String> extendIndexDatas() {
        return null;
    }

//    @Override
//    public List<? extends Searchable> ListAfter(String id, int count) {
//        return null;
//    }

    @Override
    public float boost() {
        return 1.0f;
    }

	@Override
	public int compareTo(Searchable o) {
	 
		return 0;
	}

 

 
 
 
 


}
