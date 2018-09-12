package com.kaipin.search.demo.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.kaipin.search.core.index.Searchable;

/**
 * 测试索引的对象
 *  
 */
public class Person  implements Searchable {

	private String id;
	private String title;
	private String address;

	public Person(){}
	public Person(String id, String t, String b){
		this.id = id;
		this.title = t;
		this.address = b;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

 
 
 
 
	@Override
	public String id() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public List<String> storeFields() {
		return Arrays.asList("id", "title");
	}

	@Override
	public List<String> indexFields() {
		return Arrays.asList("title","address");
	}

	@Override
	public Map<String, String> extendStoreDatas() {
		return null;
	}

	@Override
	public Map<String, String> extendIndexDatas() {
		return null;
	}

//	@Override
//	public List<? extends Searchable> ListAfter(String id, int count) {
//		return null;
//	}
	@Override
	public float boost() {
		return 1.1f;
	}
	@Override
	public int compareTo(Searchable o) {
		// TODO Auto-generated method stub
		return 0;
	}
	 
}
