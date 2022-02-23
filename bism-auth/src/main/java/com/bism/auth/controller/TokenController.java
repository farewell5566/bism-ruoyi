package com.bism.auth.controller;

import com.bism.auth.form.LoginBody;
import com.bism.auth.form.RegisterBody;
import com.bism.auth.service.SysLoginService;
import com.bism.auth.service.TokenService;
import com.bism.common.core.domain.R;
import com.bism.common.core.utils.JwtUtils;
import com.bism.common.core.utils.StringUtils;
import com.bism.common.security.auth.AuthUtils;
import com.bism.common.security.service.TokenService;
import com.bism.common.security.utils.SecurityUtils;
import com.bism.system.api.model.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class TokenController {

    @Autowired
    private SysLoginService sysLoginService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("login")
    public R<?> login(@RequestBody LoginBody form){
        LoginUser user = sysLoginService.login(form.getUsername(),form.getPassword());

        return R.ok(tokenService.createToken(user));
    }

    @PostMapping("loginOut")
    public R<?> logOut(HttpServletRequest request){
        String token = SecurityUtils.getToken(request);
        if (StringUtils.isNotEmpty(token)){
            String username = JwtUtils.getUserName(token);
            //String username = SecurityUtils.getUsername(token);
            AuthUtils.logOutByToken(token);
            sysLoginService.loginOut(token);
        }
        return R.ok();


//        String token =  tokenService.getToken(request);
//        LoginUser loginUser = tokenService.getLoginUser(request);
//        sysLoginService.loginOut(loginUser.getUsername());

    }

    @PostMapping("refresh")
    public R<?> refresh(HttpServletRequest request){
        LoginUser user = tokenService.getLoginUser(request);
        if (StringUtils.isNotNull(user)){
            tokenService.refreshToken(user);
            return R.ok();
        }

        //temp
//        String token = request.getHeader(TokenConstants.AUTHENTICATION);
//        token = token.replaceFirst(TokenConstants.PREFIX,"");
//        //Claims claims = parseToken(token);
//        Claims claims1 = Jwts.parser().setSigningKey(TokenConstants.SECRET).parseClaimsJws(token).getBody();
//        String username = claims1.get(SecurityConstants.DETAILS_USERNAME).toString();
//
//
//        tokenService.delLoginUser(token);
//
//
//
//
//        getValue(claims, SecurityConstants.DETAILS_USERNAME);
//        String usernet = JwtUtils.getUserName(token);




        return R.ok();



    }


    @PostMapping("register")
    public R<?>register(@RequestBody RegisterBody registerBody){
        sysLoginService.register(registerBody.getUsername(),registerBody.getPassword());
        return R.ok();
    }


}
