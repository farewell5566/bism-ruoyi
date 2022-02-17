package com.bism.comomsecurity.handler;

import com.bism.common.core.exception.ServiceException;
import com.bism.common.core.utils.StringUtils;
import com.bism.common.core.web.domain.AjaxResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    @ExceptionHandler(ServiceException.class)
    public AjaxResult handlerServviceException(ServiceException e, HttpServletResponse response){
        log.error(e.getMessage(),e.getCode());
        return StringUtils.isNotEmpty(e.getCode().toString())?AjaxResult.error(e.getCode(),e.getMessage()):AjaxResult.error(e.getMessage());
    }











}
