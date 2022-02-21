package com.bism.common.security.utils;

import com.bism.common.core.constant.SecurityConstants;
import com.bism.common.core.constant.TokenConstants;
import com.bism.common.core.context.SecurityContextHolder;
import com.bism.common.core.utils.ServletUtils;
import com.bism.common.core.utils.StringUtils;
import com.bism.system.api.model.LoginUser;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.servlet.http.HttpServletRequest;

public class SecurityUtils {

    public static String getUserName(){
        return SecurityContextHolder.getUserName();

    }
    public static String getUserkey(){
        return SecurityContextHolder.getUserKey();
    }

    public static Long getUserId(){
        return SecurityContextHolder.getUserId();
    }

    public static LoginUser getLoginUser(){
        return SecurityContextHolder.get(SecurityConstants.LOGIN_USER,LoginUser.class);
    }

    public static String getToken(HttpServletRequest request){
        String token = request.getHeader(TokenConstants.AUTHENTICATION);
        return replaceTokenPrefix(token);
    }

    public static String getToken(){
        return getToken(ServletUtils.getRequest());
    }

    public static String replaceTokenPrefix(String token){
        if (StringUtils.isNotEmpty(token) && token.startsWith(TokenConstants.PREFIX)){
            token = token.replaceFirst(TokenConstants.PREFIX,"");
        }
        return token;
    }

    public static String encryptPassword(String password){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder.encode(password);
    }


    public static boolean matchesPassword(String rawPassword,String encodePassword){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(rawPassword,encodePassword);
    }

}
