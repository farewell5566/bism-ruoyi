package com.bism.common.core.exception;

import java.io.IOException;

public class NotLoginException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public NotLoginException(String msg){
        super(msg);
    }

}
