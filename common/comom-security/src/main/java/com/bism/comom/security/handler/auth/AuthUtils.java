package com.bism.comom.security.handler.auth;


/**
 * 这里边主要看 ，对象的生成bean、spring bean,对象
 */

public class AuthUtils {

    public static AuthImpl authImpl = new AuthImpl();

    public static void logOutByToken(String token){
        authImpl.logOutByToken(token);
    }


}
