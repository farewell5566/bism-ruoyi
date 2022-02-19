package com.bism.system.api.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

public class baseEntity {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public String createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public String updateTime;

    private String createBy;

    private String updateBy;

    /**
     * 备注
     */
    private String remake;


    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getRemake() {
        return remake;
    }

    public void setRemake(String remake) {
        this.remake = remake;
    }
}
