package com.bism.system.api.domain;

import com.bism.system.api.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

public class SysOperLog extends BaseEntity {

    private Long operId;

    private String method;

    //操作模块
    private String title;

    private String businessType;

    //操作人员名字
    private String operName;

    //请求地址
    private String openUrl;

    //操作地址
    private String operIp;

    //请求参数
    private String operParam;

    //错误消息
    private String errorMsg;

    //操作时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String operTime;

    public Long getOperId() {
        return operId;
    }

    public void setOperId(Long operId) {
        this.operId = operId;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getOperName() {
        return operName;
    }

    public void setOperName(String operName) {
        this.operName = operName;
    }

    public String getOpenUrl() {
        return openUrl;
    }

    public void setOpenUrl(String openUrl) {
        this.openUrl = openUrl;
    }

    public String getOperIp() {
        return operIp;
    }

    public void setOperIp(String operIp) {
        this.operIp = operIp;
    }

    public String getOperParam() {
        return operParam;
    }

    public void setOperParam(String operParam) {
        this.operParam = operParam;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getOperTime() {
        return operTime;
    }

    public void setOperTime(String operTime) {
        this.operTime = operTime;
    }
}
