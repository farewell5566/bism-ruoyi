package com.bism.common.core.exception;

public class ServiceException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    private Integer code;

    private String message;

    private String detailMsg;

    public ServiceException(){};

    public ServiceException(String message){
        this.message = message;
    }

    public ServiceException(String message,Integer code){
        this.message = message;
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetailMsg() {
        return detailMsg;
    }

    public void setDetailMsg(String detailMsg) {
        this.detailMsg = detailMsg;
    }
}
