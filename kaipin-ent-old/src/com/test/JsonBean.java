package com.test;

import java.util.List;

public class JsonBean {
	
	public String enName;
	public String code;
	public String name;
	public List<JsonBean> sublist;
	
	
	public String getEnName() {
		return enName;
	}
	public void setEnName(String enName) {
		this.enName = enName;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<JsonBean> getSublist() {
		return sublist;
	}
	public void setSublist(List<JsonBean> sublist) {
		this.sublist = sublist;
	}

}
