package com.bism.common.core.domain;

import com.bism.common.core.constant.Constants;
import org.apache.commons.lang3.ObjectUtils;

import java.io.Serializable;

/**
 * 响应信息主体
 */

public class R<T> implements Serializable {
    private static final long serialVersionUID =1L;

    public static final int SUCCESS = Constants.SUCCESS;

    public static final int FAIL = Constants.FAIL;

    private int code ;

    private String msg;

    private T data;

    public static <T> R<T> restResult(T data,int code,String msg){
        R <T> result = new R<>();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    public static <T> R<T> ok(T data,int code,String msg){
        return restResult(data,code,msg);
    }

    public static <T> R<T> ok(T data){
        return restResult(data,SUCCESS,null);
    }
    public static <T>R<T> ok(int code){
        return restResult(null,code,null);
    }
    public static <T> R<T>ok(){
        return restResult(null,SUCCESS,null);
    }

    public static <T> R<T>ok(T data,String msg){
        return restResult(data,SUCCESS,msg);
    }

    public static <T>R<T> fail(){
        return restResult(null,FAIL,null);
    }

    public static <T> R<T> fail(T data){
        return restResult(data,FAIL,null);
    }

    public static <T> R<T>fail(T data,String msg){
        return restResult(data,FAIL,msg);
    }

    public static <T> R<T> fail(String msg) {
        return restResult(null, FAIL, msg);
    }

    public static <T> R<T>fail(int code,String msg){
        return restResult(null,code,msg);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
