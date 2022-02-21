//package com.bism.auth.service;
//
//import com.bism.common.core.constant.CacheConstants;
//import com.bism.common.core.text.UUID;
//import com.bism.common.core.utils.ServletUtils;
//import com.bism.common.core.utils.ip.IpUtils;
//import com.bism.common.reids.service.RedisService;
//import com.bism.system.api.model.LoginUser;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.Map;
//import java.util.concurrent.TimeUnit;
//
//@Service
//public class TokenService {
//
//    @Autowired
//    private RedisService redisService;
//
//    protected static final long MILLIS_SECOND = 1000;
//
//    protected static final long MILLIS_MINUTES = 60 * MILLIS_SECOND;
//
//    protected final static long expireTime = CacheConstants.EXPIRATION;
//
//    private final static String ACCESS_TOKEN = CacheConstants.LOGIN_TOKEN_KEY;
//
//
//
//
//    public Map<String,Object> createToken(LoginUser user){
//        String token = UUID.getRandom().toString();
//        Long userId = user.getUserid();
//        String username = user.getUsername();
//        user.setIpaddr(IpUtils.getIpAddr(ServletUtils.getRequest()));
//        refreshToken(user);
//
//        return null;
//
//
//
//
//    }
//
//    public void refreshToken(){
//
//    }
//
//    public LoginUser getLoginUser(HttpServletRequest request){
//        return null;
//
//    }
//
//    public LoginUser getLoginUser(String token){
//        return null;
//
//    }
//
//    public void setLoginUser(LoginUser loginUser){
//
//    }
//
//    public void delLoginUser(LoginUser loginUser){
//
//    }
//
//    public void refreshToken(LoginUser user){
//        long time = System.currentTimeMillis();
//        user.setLoginTime(System.currentTimeMillis());
//        user.setExpireTime(System.currentTimeMillis() + expireTime  * MILLIS_MINUTES);
//        String userKey = getTokenKey(user.getToken());
//        redisService.setCacheObject(userKey,user,expireTime, TimeUnit.MINUTES);
//    }
//
//    private String getTokenKey(String token){
//        return ACCESS_TOKEN + token;
//    }
//
//
//
//
//
//
//
//
//
//}
