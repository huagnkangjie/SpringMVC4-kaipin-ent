package com.kaipin.search.core.index;

/**
 * 索引更新操作
 *
 *  
 */
public interface IndexTask {

    byte OPT_ADD = 0x01;    //添加索引
    byte OPT_UPDATE = 0x02;    //更新索引
    byte OPT_DELETE = 0x04;    //删除索引

    /**
     * 返回更新操作类型
     *
     * @return 请看上面三个常量
     */
    byte getOpt();

    /**
     * 返回对应的可搜索对象
     *
     * @return
     */
    Searchable object();

    /**
     * 当该索引更新操作完毕后执行此方法
     */
    void afterBuild();

}
