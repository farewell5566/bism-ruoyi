package com.bism.common.security.auth;

import com.bism.common.core.utils.SpringUtils;
import com.bism.common.core.utils.StringUtils;
import com.bism.common.security.service.TokenService;
import com.bism.common.security.utils.SecurityUtils;
import com.bism.system.api.model.LoginUser;

import java.util.Set;

/**
 * 用于权限的操作，获取token/role/permission,操作tokenService
 */

public class AuthLogic {


    public TokenService tokenService = SpringUtils.getBean(TokenService.class);

    public void logOutByToken(String token){
        tokenService.delLoginUser(token);
    }

    public void logOut(){
        String token = SecurityUtils.getToken();
        if (token == null){
            return ;
        }
        logOutByToken(token);
    }

    public void checkLogin(){
        getLoginUser();
    }

    public LoginUser getLoginUser(){
        if(StringUtils.isEmpty(SecurityUtils.getToken()){
            throw new NoLoginException("未提供token");
        }



        SecurityUtils.getLoginUser();


    }

    public LoginUser getLoginUser(String token){
        return tokenService.getLoginUser(token);

    }


    public void checkPermi(String permissio){

    }

    public void checkPermiAnd(String ...permission){

    }

    public boolean hasRole(String role){

    }

    public void checkRole(String role){

    }

    public void checkRoleAnd(String ...role){

    }

    public void checkRoleOr(String ...role){

    }

    public Set<String> getRoleList(){

    }

    public Set<String>getPermiList()






}
