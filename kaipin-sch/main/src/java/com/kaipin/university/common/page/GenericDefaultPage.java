package com.kaipin.university.common.page;

import java.util.Collections;
import java.util.List;

/**
 * 支持泛型的分页数据对象的默认实现类
 * 
 */
@SuppressWarnings("unchecked")
public class GenericDefaultPage<T> implements IGenericPage<T> {
	public static final int DEF_COUNT = 5;
	/** 当前页数据列表 */
	private List<T> elements;

	/** 页大小（每页数据个数） */
	private int pageSize;

	/** 当前页号 */
	private int pageNo;

	/** 数据总个数 */
	private long  totalCount = 0L;
	
	private int lastPageNo;

	/**
	 * 根据当前页号、页大小（每页数据个数）、当前页数据列表、数据总个数构造分页数据对象的实例。
	 * 
	 * @param pageNo
	 *            当前页号
	 * @param pageSize
	 *            页大小（每页数据个数）
	 * @param elements
	 *            当前页数据列表
	 * @param totalCount
	 *            数据总个数
	 */
	public GenericDefaultPage(int pageNo, int pageSize, List<T> elements, long totalCount) {

		setPageSize(pageSize);

		setPageNo(pageNo);
		
		setTotalCount(totalCount);
		
		this.elements = elements;
		
		adjustLastPageNo();
		
	}

	/**
	 * 定义一空页
	 *
	 * @see #emptyPage()
	 */
	public static final GenericDefaultPage EMPTY_PAGE = new GenericDefaultPage(0, 0, Collections.emptyList(), 0l);

	/**
	 * 获取一空页
	 */
	public static <E> GenericDefaultPage<E> emptyPage() {
		return (GenericDefaultPage<E>) EMPTY_PAGE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 */
	public boolean isFirstPage() {
		return getPageNo() == 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 */
	public boolean isLastPage() {
		return getPageNo() >= getLastPageNo();
	}

	/*
	 * (non-Javadoc)
	 * 
	 */
	public boolean hasNextPage() {
		return elements == null ? false : elements.size() > getPageSize();
	}

	/*
	 * (non-Javadoc)
	 * 
	 */
	public boolean hasPreviousPage() {
		return getPageNo() > 0;
	}

	public void adjustLastPageNo(){
		double totalResults = new Long(getTotalCount()).doubleValue();
		lastPageNo=	  (totalResults % getPageSize() == 0) ? new Double(Math.floor(totalResults / getPageSize())).intValue()
				: (new Double(Math.floor(totalResults / getPageSize())).intValue() + 1);
	}
	/*
	 * (non-Javadoc)
	 * 
	 */
	public int getLastPageNo() {
		
	return lastPageNo;
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 */
	public List<T> getThisPageElements() {
		return hasNextPage() ? elements.subList(0, getPageSize()) : elements;
	}
	
	
public 	int getPageElementCount(){
		
		if(elements!=null){
			
			return elements.size();
		}
		return 0;
		
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 */
	public  long getTotalCount() {
		return totalCount;
	}

	/*
	 * (non-Javadoc)
	 * 
	 */
	public int getThisPageFirstElementNumber() {
		return getPageNo() * getPageSize() + 1;
	}

	/*
	 * (non-Javadoc)
	 * 
	 */
	public int getThisPageLastElementNumber() {
		int fullPage = getThisPageFirstElementNumber() + getPageSize() - 1;
		return (int) (getTotalCount() < fullPage ? getTotalCount() : fullPage);
	}

	/*
	 * (non-Javadoc)
	 * 
	 */
	public int getNextPageNo() {
		return getPageNo() + 1;
	}

	/*
	 * (non-Javadoc)
	 * 
	 */
	public int getPreviousPageNo() {
		return getPageNo() - 1;
	}

	/*
	 * (non-Javadoc)
	 * 
	 */
	public int getPageSize() {
		return pageSize;
	}

	/*
	 * (non-Javadoc)
	 * 
	 */
	public int getPageNo() {
		return pageNo;
	}



	public void setPageSize(int pageSize) {
		if (pageSize < 1) {
			this.pageSize = DEF_COUNT;
		} else {
			this.pageSize = pageSize;
		}
	}

	public void setPageNo(int pageNo) {
		if (pageNo < 1) {
			this.pageNo = 1;
		} else {
			this.pageNo = pageNo;
		}
	}

	public void setTotalCount(Long totalCount) {
		if (totalCount < 0) {
			this.totalCount = 0l;
		} else {
			this.totalCount = totalCount;
		}
	}

	/**
	 * 检查页码 checkPageNo
	 * 
	 * @param pageNo
	 * @return if pageNo==null or pageNo<1 then return 1 else return pageNo
	 */
	public static int cpn(Integer page_no) {
		return (page_no == null || page_no < 1) ? 1 : page_no;
	}
	
	/**
	 * 检查页行 checkPageSzie
	 * @param page_size
	 * @return
	 */
	public static int cps(Integer page_size) {
		return (page_size == null || page_size < 1) ? DEF_COUNT : page_size;
	}

	/**
	 * 根据页大小（每页数据个数）获取给定页号的第一条数据在总数据中的位置（从1开始）
	 * 
	 * @param pageNo
	 *            给定的页号
	 * @param pageSize
	 *            页大小（每页数据个数）
	 * @return 给定页号的第一条数据在总数据中的位置（从1开始）
	 */
	public static int getStartOfPage(int pageNo, int pageSize) {
		int startIndex = (pageNo - 1) * pageSize + 1;
		if (startIndex < 1)
			startIndex = 1;
		return startIndex;
	}

	public List<T> getElements() {
		return elements;
	}

	public void setElements(List<T> elements) {
		this.elements = elements;
	}

 

}
