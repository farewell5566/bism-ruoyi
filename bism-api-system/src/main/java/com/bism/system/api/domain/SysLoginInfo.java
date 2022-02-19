package com.bism.system.api.domain;

import com.bism.system.api.entity.baseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

public class SysLoginInfo extends baseEntity {

    private int status;

    private String msg;

    private String userName;

    private String ipaddr;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String accessTime;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getIpaddr() {
        return ipaddr;
    }

    public void setIpaddr(String ipaddr) {
        this.ipaddr = ipaddr;
    }

    public String getAccessTime() {
        return accessTime;
    }

    public void setAccessTime(String accessTime) {
        this.accessTime = accessTime;
    }
}
