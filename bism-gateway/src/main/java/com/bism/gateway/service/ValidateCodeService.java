package com.bism.gateway.service;

import com.bism.common.core.exception.CaptchaException;
import com.bism.common.core.web.domain.AjaxResult;
import org.springframework.stereotype.Service;

import java.io.IOException;

//@Service
public  interface ValidateCodeService {
    public AjaxResult createValidateCode() throws IOException, CaptchaException;

    public void checkCaptcha(String key,String value) throws CaptchaException;



}
