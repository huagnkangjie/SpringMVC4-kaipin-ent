package com.kaipin.common.entity;

@SuppressWarnings("serial")
public class Page implements java.io.Serializable {

	private int page = 1;// 当前页
	private int rows = 20;// 每页显示记录数

	/**
	 * 是否在Creteria创建完成后，直接抓取总数并设置page的totalCount
	 */
	private boolean readTotalCount = true;
	
	
	public Page() {
	}

	public Page(int page, int rows) {
		this.page = page;
		this.rows = rows;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public boolean isReadTotalCount() {
		return readTotalCount;
	}

	public void setReadTotalCount(boolean readTotalCount) {
		this.readTotalCount = readTotalCount;
	}
	
}
