package com.kaipin.search.core.dimension;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.kaipin.search.core.index.Searchable;

public class Company implements Searchable {

	private String id;
	private String title;

	private Map<String, String> extendStoreDatas;

	private Map<String, String> extendIndexDatas;

	public Company() {

	}

	public Company(String id, String title, Map<String, String> extendStoreDatas) {

		this(id, title, extendStoreDatas, null);

	}

	public Company(String id, String title, Map<String, String> extendStoreDatas,
			Map<String, String> extendIndexDatas) {
		this.id = id;

		this.title = title;

		this.extendStoreDatas = extendStoreDatas;

		this.extendIndexDatas = extendIndexDatas;

	}

	@Override
	public int compareTo(Searchable o) {

		return 0;
	}

	@Override
	public String id() {

		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;

	}

	public String getTitle() {
		return title;
	}

	@Override
	public List<String> storeFields() {

		return Arrays.asList(ID, TITLE);
	}

	@Override
	public List<String> indexFields() {

		return Arrays.asList(TITLE);
	}

	@Override
	public float boost() {

		return 1.1f;
	}

	@Override
	public Map<String, String> extendStoreDatas() {

		return extendStoreDatas;
	}

	@Override
	public Map<String, String> extendIndexDatas() {

		return null;
	}

}
