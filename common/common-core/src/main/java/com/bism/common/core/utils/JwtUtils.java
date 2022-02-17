package com.bism.common.core.utils;

import com.bism.common.core.constant.SecurityConstants;
import com.bism.common.core.text.Convert;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Map;

public class JwtUtils {

    public static String createToken(Map<String, Object> claims, String secret) {
        String token = Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, secret).compact();
        return token;
    }

    public static Claims parseToken(String token, String secret) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    public static String getUserKey(String token, String secret) {
        Claims claims = parseToken(token, secret);
        return getValue(claims, SecurityConstants.USER_KEY);
    }

    public static String getUsetKey(Claims claims) {
        return getValue(claims, SecurityConstants.USER_KEY);
    }

    public static String getUserId(String token, String secret) {
        Claims claims = parseToken(token, secret);
        return getValue(claims, SecurityConstants.DETAILS_USER_ID);
    }

    public static String getUserId(Claims claims) {
        return getValue(claims, SecurityConstants.DETAILS_USER_ID);
    }

    public static String getUsetName(String token, String secret) {
        Claims claims = parseToken(token, secret);
        return getValue(claims, SecurityConstants.DETAILS_USERNAME);
    }

    public static String getUserName(Claims claims) {
        return getValue(claims, SecurityConstants.DETAILS_USERNAME);
    }

    public static String getValue(Claims claims, String key) {
        return Convert.toStr(claims.get(key), "");
    }

}
