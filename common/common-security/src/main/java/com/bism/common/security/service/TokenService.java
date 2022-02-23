package com.bism.common.security.service;

import com.bism.common.core.constant.CacheConstants;
import com.bism.common.core.constant.SecurityConstants;
import com.bism.common.core.constant.TokenConstants;
import com.bism.common.core.utils.IdUtils;
import com.bism.common.core.utils.JwtUtils;
import com.bism.common.core.utils.ServletUtils;
import com.bism.common.core.utils.StringUtils;
import com.bism.common.core.utils.ip.IpUtils;
import com.bism.common.reids.service.RedisService;
import com.bism.system.api.model.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Currency;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class TokenService {

    private final static String ACCESS_TOKEN = CacheConstants.LOGIN_TOKEN_KEY;

    private final static long EXPIRATION = CacheConstants.EXPIRATION;

    private final static long REFRESH = CacheConstants.REFRESH_TIME;

    private final static long MILLS_SECONDS = 1000;

    private final static long MILLS_MINUTE = 60 * MILLS_SECONDS;





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

    public Object createToken(LoginUser user) {
        String token = IdUtils.fastUUID();
        Long userId = user.getSysUser().getUserId();
        String userName = user.getSysUser().getUserName();
        user.setIpaddr(IpUtils.getIpAddr(ServletUtils.getRequest()));
        user.setToken(token);
        user.setUsername(userName);
        user.setUserid(userId);
        refreshToken(user);

        //Jwt存储信息
        Map<String,Object> claimMap = new HashMap<>();
        claimMap.put(SecurityConstants.USER_KEY,token);
        claimMap.put(SecurityConstants.DETAILS_USERNAME,userName);
        claimMap.put(SecurityConstants.DETAILS_USER_ID,userId);

        //接口返回消息
        Map<String,Object>rspMap = new HashMap<>();
        rspMap.put("access_token",JwtUtils.createToken(claimMap));
        rspMap.put("expires_in",EXPIRATION);
        return rspMap;


    }

    public void refreshToken(LoginUser user){
        user.setLoginTime(System.currentTimeMillis());
        user.setExpireTime(System.currentTimeMillis() + MILLS_MINUTE * EXPIRATION);
        String userKey = getTokenKey(user.getToken());
        redisService.setCacheObject(userKey,user,EXPIRATION, TimeUnit.MINUTES);
    }



}
