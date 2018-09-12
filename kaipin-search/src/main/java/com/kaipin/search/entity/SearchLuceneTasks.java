package com.kaipin.search.entity;

import org.apache.ibatis.type.Alias;

@Alias("SearchLuceneTasks")
public class SearchLuceneTasks {
    private String id;

    private String objId;

    private Byte objType;

    private Byte opt;

    private Long createTime;

    private Byte status;

    private Long handleTime;

    @Override
    public String toString() {
      StringBuffer str=new StringBuffer();
      str.append("{" );
      str.append("objId="+objId);
      str.append(",objType="+objType);
      str.append(",opt="+opt);
      str.append("}" );
        return str.toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getObjId() {
        return objId;
    }

    public void setObjId(String objId) {
        this.objId = objId == null ? null : objId.trim();
    }

    public Byte getObjType() {
        return objType;
    }

    public void setObjType(Byte objType) {
        this.objType = objType;
    }

    public Byte getOpt() {
        return opt;
    }

    public void setOpt(Byte opt) {
        this.opt = opt;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Long getHandleTime() {
        return handleTime;
    }

    public void setHandleTime(Long handleTime) {
        this.handleTime = handleTime;
    }
}