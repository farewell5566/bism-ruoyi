package com.bism.gateway.service.impl;

import com.bism.common.core.constant.Constants;
import com.bism.common.core.exception.CaptchaException;
import com.bism.common.core.utils.IdUtils;
import com.bism.common.core.utils.StringUtils;
import com.bism.common.core.utils.sign.Base64;
import com.bism.common.core.web.domain.AjaxResult;
import com.bism.common.reids.service.RedisService;
import com.bism.gateway.config.properties.CaptchaProperties;
import com.bism.gateway.service.ValidateCodeService;


import org.springframework.beans.factory.annotation.Autowired;

import com.google.code.kaptcha.Producer;
import org.springframework.stereotype.Service;
import org.springframework.util.FastByteArrayOutputStream;


import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Service
public class ValidateCodeServiceImpl implements ValidateCodeService {

    @Autowired
    private CaptchaProperties captchaProperties;

    @Resource(name ="captchaProducer")
    private Producer captchaProducer;

    @Resource(name = "captchaProducerMath")
    private Producer captchaProducerMath;

    @Autowired
    private RedisService redisService;

    @Override
    public AjaxResult createValidateCode() throws IOException,CaptchaException{

        AjaxResult ajaxResult = new AjaxResult();
        boolean catpchaOnOff = captchaProperties.getEnabled();

        ajaxResult.put("catpchaOnOff",catpchaOnOff);
        if (!catpchaOnOff )
            return ajaxResult;

        String capStr = null,code = null;
        BufferedImage image = null;

        String uuid = IdUtils.simpleUUID();
        String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid;

        String captchaType = captchaProperties.getType();
        if ("math".equals(captchaType)){
            String capText = captchaProducerMath.createText();
            capStr = capText.substring(0,capText.lastIndexOf("@"));
            code = capText.substring(capText.lastIndexOf("@")+1);
            image = captchaProducerMath.createImage(capStr);
        }
        else if ("char".equals(captchaType)){
            capStr = captchaProducer.createText();
            code = capStr;
            image = captchaProducer.createImage(capStr);
        }
        redisService.setCacheObject(verifyKey,code,Constants.CAPTCHA_EXPIRATION, TimeUnit.MINUTES);

        FastByteArrayOutputStream os = new FastByteArrayOutputStream();
        try{
            ImageIO.write(image,"jpg",os);
        }catch (IOException e){
            return AjaxResult.error(e.getMessage());
        }
        ajaxResult.put("img", Base64.encode(os.toByteArray()));
        ajaxResult.put("uuid",uuid);

        return ajaxResult;
    }

    @Override
    public void checkCaptcha(String code, String uuid) throws CaptchaException {
        if (StringUtils.isEmpty(code)){
            throw new CaptchaException("验证码不能为空");
        }
        if(StringUtils.isEmpty(uuid)){
            throw new CaptchaException("验证码已失效");
        }
        String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid;
        String captcha = redisService.getCacheObject(verifyKey);
        redisService.deleteObject(verifyKey);
        if (!code.equalsIgnoreCase(captcha)){
            throw new CaptchaException("验证码错误");
        }

    }
}

