package com.kaipin.search.entity;

import org.apache.ibatis.type.Alias;

@Alias("SearchExpireObject")
public class SearchExpireObject {



    private String id;

    private String objId;

    private Byte objType;



    private Long createTime;



    public String getId() {
        return id;
    }



    public void setId(String id) {
        this.id = id;
    }



    public String getObjId() {
        return objId;
    }



    public void setObjId(String objId) {
        this.objId = objId;
    }



    public Byte getObjType() {
        return objType;
    }



    public void setObjType(Byte objType) {
        this.objType = objType;
    }



    public Long getCreateTime() {
        return createTime;
    }



    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public class SearchExpireObjectTable {
        public static final String ID = "id";//

        public static final String OBJ_ID = "obj_id";//
        public static final String OBJ_TYPE = "obj_type";//
        public static final String CREATE_TIME = "create_time";//


    }



}
