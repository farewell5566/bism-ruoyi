package com.bism.common.core.exception;

import org.apache.commons.lang3.StringUtils;

public class NotRoleException extends RuntimeException{
    private static final long serivalVersionUID = 1L;

    public NotRoleException(String msg){
        super(msg);
    }

    public NotRoleException(String[]msgs){
        super(StringUtils.join(msgs));
    }

}
