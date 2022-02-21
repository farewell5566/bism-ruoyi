package com.bism.common.security.service;

import com.bism.common.core.constant.CacheConstants;
import com.bism.common.core.utils.JwtUtils;
import com.bism.common.core.utils.StringUtils;
import com.bism.common.reids.service.RedisService;
import com.bism.system.api.model.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;

public class TokenService {

    private final static String ACCESS_TOKEN = CacheConstants.LOGIN_TOKEN_KEY;







    @Autowired
    RedisService redisService;





    public void delLoginUser(String token) {
        if (StringUtils.isNotEmpty(token)){
            String userKey = JwtUtils.getUserKey(token);
            redisService.deleteObject(userKey);
        }
    }


    public LoginUser getLoginUser(String token) {
        String userKey = JwtUtils.getUserKey(token);
        LoginUser user = redisService.getCacheObject(getTokenKey(userKey));
        return user;
    }

    private String getTokenKey(String token){
        return ACCESS_TOKEN + token;
    }

}
