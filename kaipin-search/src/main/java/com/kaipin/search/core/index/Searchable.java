package com.kaipin.search.core.index;

import java.util.List;
import java.util.Map;

/**
 * 可搜索对象
 
 */
public interface Searchable  extends Comparable<Searchable> {


	public static final String ID = "id";

	public static final String LOCATION = "location";

	public static final String TITLE = "title";

	public static final String INDUSTRY = "industry";

	public static final String TIME = "time"; // 修改时间
 

    /**
     * 文档的唯一编号
     *
     * @return 文档id
     */
    String id();

    void setId( String  id);
    
 
    /**
     * 要存储的字段
     *
     * @return 返回字段名列表
     */
    List<String> storeFields();

    /**
     * 要进行分词索引的字段
     *
     * @return 返回字段名列表
     */
    List<String> indexFields();

    /**
     * 文档的优先级
     *
     * @return
     */
    float boost();

    /**
     * 扩展的存储数据
     *
     * @return 扩展数据K/V
     */
    Map<String, String> extendStoreDatas();

    /**
     * 扩展的索引数据
     *
     * @return 扩展数据K/V
     */
    Map<String, String> extendIndexDatas();

    /**
     * 列出id值大于指定值得所有对象
     *
     * @param id
     * @param count
     * @return

    List<? extends Searchable> ListAfter(String id, int count);

      */
 

 

 
}
