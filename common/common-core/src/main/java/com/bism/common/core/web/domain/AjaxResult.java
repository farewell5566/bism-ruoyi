package com.bism.common.core.web.domain;

import com.bism.common.core.constant.HttpStatus;
import com.bism.common.core.utils.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class AjaxResult extends HashMap<String, Object> {

    private static final long serialVersionUID = 1L;

    public static final String CODE_TAG = "code";

    public static final String MSG_TAG = "msg";

    public static final String DATA_TAG = "data";

    public AjaxResult() {
    }

    public AjaxResult(int code, String msg) {
        super.put(CODE_TAG, code);
        super.put(MSG_TAG, msg);
    }

    public AjaxResult(int code, String msg, Object data) {
        super.put(CODE_TAG, code);
        super.put(MSG_TAG, msg);
        if (StringUtils.isNotNull(data)) {
            super.put(DATA_TAG, data);
        }
    }


    public static AjaxResult success() {
        return AjaxResult.success("success");
    }

    public static AjaxResult success(String msg) {
        return AjaxResult.success(msg, null);
    }

    public static AjaxResult success(String msg, Object object) {
        return new AjaxResult(HttpStatus.SUCCESS, msg, object);
    }

    public static AjaxResult error() {
        return AjaxResult.error("error");
    }

    public static AjaxResult error(String msg) {
        return AjaxResult.error(msg, null);
    }

    public static AjaxResult error(String msg, Object data) {
        return new AjaxResult(HttpStatus.ERROR, msg, data);
    }

    public static AjaxResult error(int code, String msg) {
        return new AjaxResult(code, msg, null);
    }


}
