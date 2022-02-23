package com.bism.common.security.auth;

import com.bism.common.core.exception.NotLoginException;
import com.bism.common.core.exception.NotPermissionException;
import com.bism.common.core.exception.NotRoleException;
import com.bism.common.core.utils.SpringUtils;
import com.bism.common.core.utils.StringUtils;
import com.bism.common.security.service.TokenService;
import com.bism.common.security.utils.SecurityUtils;
import com.bism.system.api.model.LoginUser;
import org.springframework.util.PatternMatchUtils;

import java.util.Collection;
import java.util.Set;

/**
 * 用于权限的操作，获取token/role/permission,操作tokenService
 */

public class AuthLogic {


    /**
     * 所有权限标识
     */
    private static final String ALL_PERMISSION = "*:*:*";

    /**
     * 管理员角色权限
     */
    private static final String SUPER_ADMIN = "admin";


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
        if(StringUtils.isEmpty(SecurityUtils.getToken())){
            throw new NotLoginException("未提供token");
        }
        LoginUser loginUser = SecurityUtils.getLoginUser();
        if (loginUser == null){
            throw new NotLoginException("无效的token");
        }
        return loginUser;
    }

    public LoginUser getLoginUser(String token){
        return tokenService.getLoginUser(token);
    }


    public void checkPermi(String permissio){
        if(!hasPermiss(getPermiList(),permissio)){
            throw  new NotPermissionException(permissio);
        }
    }

    public void checkPermiAnd(String ...permissions){
        Set<String> permiList = getPermiList();
        for(String permission : permissions){
            if(!hasPermiss(permiList,permission)){
                throw new NotPermissionException(permission);
            }
        }
    }

    public void checkPermiOr(String ...permissions){
        Set<String> permiList = getPermiList();
        for(String permis:permissions){
            if(hasPermiss(permiList,permis)){
                return ;
            }
        }
        if (permissions.length > 0){
            throw new NotPermissionException(permissions);
        }
    }

    public boolean hasPermiss(Collection<String>auths,String permission){
        return auths.stream().filter(StringUtils::hasText)
                .anyMatch(x -> PatternMatchUtils.simpleMatch(x,permission) || ALL_PERMISSION.contains(x));
    }


    public boolean hasRole(String role){
        return hasRole(getRoleList(),role);
    }

    public boolean hasRole(Collection<String>roles,String role){
        return roles.stream().filter(StringUtils::hasText)
                .anyMatch(x-> PatternMatchUtils.simpleMatch(x,role));
    }

    public void checkRole(String role){
        if(!hasRole(role)){
            throw new NotRoleException(role);
        }
    }

    public void checkRoleAnd(String ...roles){
        Set<String> roleList = getRoleList();
        for(String role : roles){
            if(!hasRole(roleList,role)){
                throw new NotRoleException(role);
            }
        }
    }

    public void checkRoleOr(String ...roles){
        Set<String>roleList = getRoleList();
        for (String role: roles){
            if (hasRole(roleList,role))
                return;
        }
        if (roles.length > 0){
            throw new NotRoleException(roles);
        }
    }

    public Set<String> getRoleList(){
        LoginUser user = getLoginUser();
        return user.getRoles();
    }

    public Set<String>getPermiList(){
        LoginUser  user = getLoginUser();
        Set<String> permissions = user.getPermissions();
        return permissions;
    }






}
