package com.bism.common.security.handler;

import com.bism.common.core.constant.HttpStatus;
import com.bism.common.core.exception.NotLoginException;
import com.bism.common.core.exception.NotPermissionException;
import com.bism.common.core.exception.NotRoleException;
import com.bism.common.core.exception.ServiceException;
import com.bism.common.core.utils.StringUtils;
import com.bism.common.core.web.domain.AjaxResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    @ExceptionHandler(ServiceException.class)
    public AjaxResult handlerServviceException(ServiceException e, HttpServletResponse response){
        log.error(e.getMessage(),e.getCode());
        return StringUtils.isNotEmpty(e.getCode().toString())?AjaxResult.error(e.getCode(),e.getMessage()):AjaxResult.error(e.getMessage());
    }

    @ExceptionHandler(NotLoginException.class)
    public AjaxResult handlerNotLoginException(NotLoginException e,HttpServletRequest request){
        log.error(e.getMessage());
        return StringUtils.isNotEmpty(e.getMessage())?AjaxResult.error(e.getMessage()):AjaxResult.error();
    }

    /**
     * 权限异常
     * @param e
     * @param request
     * @return
     */
    @ExceptionHandler(NotPermissionException.class)
    public AjaxResult handlerNotPermissionException(NotPermissionException e, HttpServletRequest request){
        String uri = request.getRequestURI();
        log.error("请求地址:{}，角色权限校验失败{}",e.getMessage(), uri);
        return AjaxResult.error(HttpStatus.FORBIDDEN,"没有访问权限，请联系管理员授权");
        //return StringUtils.isNotEmpty(e.getMessage())? AjaxResult.error(e.getMessage()):AjaxResult.error();
    }

    @ExceptionHandler(NotRoleException.class)
    public AjaxResult handlerNotRoleException(NotRoleException e,HttpServletRequest request){
        String uri = request.getRequestURI();
        log.error("权限校验失败地址{},信息{}",uri,e.getMessage());
        return AjaxResult.error(HttpStatus.FORBIDDEN,"没有访问权限，请联系管理员授权");
    }
















}
