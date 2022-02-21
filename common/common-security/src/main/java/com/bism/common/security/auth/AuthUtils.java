package com.bism.common.security.auth;


/**
 * 这里边主要看 ，对象的生成bean、spring bean,对象
 */

public class AuthUtils {

    public static AuthLogic authImpl = new AuthLogic();

    public static void logOutByToken(String token){
        authImpl.logOutByToken(token);
    }


}
