package com.bism.common.core.exception;

import com.bism.common.core.utils.StringUtils;

import java.util.Set;

public class NotPermissionException extends RuntimeException{

    private static final long serivalVersionUID =1L;

    public NotPermissionException(String msg){
        super(msg);
    }
    public NotPermissionException(String[]msg){
        super(StringUtils.join(msg));
    }

}
