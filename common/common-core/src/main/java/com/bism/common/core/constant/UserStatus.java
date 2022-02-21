package com.bism.common.core.constant;

public enum UserStatus {

    OK("0","正常"),
    DISABLE("1","停用"),
    DELETED("2","删除");

    private String code;
    private String msg;

    UserStatus(String code,String msg){
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
