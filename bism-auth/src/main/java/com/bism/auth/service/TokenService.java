package com.bism.auth.service;

import com.bism.auth.form.LoginUser;
import com.bism.common.core.text.UUID;
import com.bism.common.core.utils.ServletUtils;
import com.bism.common.core.utils.ip.IpUtils;
import com.bism.common.reids.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.sql.Time;
import java.util.Map;

@Service
public class TokenService {

    @Autowired
    private RedisService redisService;

    protected static final long MILLIS_SECOND = 1000;




    public Map<String,Object> createToken(LoginUser user){
        String token = UUID.getRandom().toString();
        Long userId = user.getUserid();
        String username = user.getUsername();
        user.setIpaddr(IpUtils.getIpAddr(ServletUtils.getRequest()));
        refreshToken(user);




    }

    public LoginUser getLoginUser(HttpServletRequest request){

    }

    public LoginUser getLoginUser(String token){

    }

    public void setLoginUser(LoginUser loginUser){

    }

    public void delLoginUser(LoginUser loginUser){

    }

    public void refresh(LoginUser){
        Time time = System.currentTimeMillis();
    }







}
