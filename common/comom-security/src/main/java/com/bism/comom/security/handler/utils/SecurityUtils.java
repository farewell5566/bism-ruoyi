package com.bism.comom.security.handler.utils;

import javax.servlet.http.HttpServletRequest;

public class SecurityUtils {
    public static String getToken(HttpServletRequest request) {
        return request.getHeader("");
    }




}
