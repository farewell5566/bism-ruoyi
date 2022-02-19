package com.bism.common.core.context;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.bism.common.core.constant.SecurityConstants;
import com.bism.common.core.text.Convert;
import com.bism.common.core.utils.StringUtils;
import com.github.pagehelper.util.StringUtil;
import javafx.beans.binding.ObjectExpression;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 获取当前线程变量中的用户ID、用户名称、Token信息，
 *  注意： 必须在网关通过请求头的方法传入，同时在HeaderInterceptor拦截器设置值。 否则这里无法获取
 *
 */
public class SecurityContextHolder {

    private static final TransmittableThreadLocal <Map<String,Object>> THREAD_LOCAL = new TransmittableThreadLocal<>();

    private static Map<String,Object> getLocalMap(){
        Map<String, Object> map = THREAD_LOCAL.get();
        if (map == null){
            map = new ConcurrentHashMap<>();
        }
        return THREAD_LOCAL.get();
    }

    public static void setLocalMap(Map<String,Object> threadLocal){
        THREAD_LOCAL.set(threadLocal);
    }

    public static String get(String key){
        Map<String, Object> map = getLocalMap();
        return Convert.toStr(map.getOrDefault(key,""));
    }

//    public static <T>T get(String key){
//        Map<String , Object> map = getLocalMap();
//        return StringUtils.cast(map.getOrDefault(key,""));
//    }

    public static <T> T get(String key,Class<T> clazz){
        Map<String,Object>map = getLocalMap();
        return StringUtils.cast(map.getOrDefault(key,""));
    }

    public void set(String key,Object value){
        Map<String,Object>map = getLocalMap();
        map.put(key,value ==null ? StringUtils.EMPTY:value);
    }

    public static String getUserKey(){
        return get(SecurityConstants.USER_KEY);
    }

    public static String getUserName(){
        return get(SecurityConstants.DETAILS_USERNAME);
    }

    public static Long getUserId(){
        return Convert.toLong(get(SecurityConstants.DETAILS_USER_ID));
   }

   public static void setUserKey(String userKey){
        getLocalMap().put(SecurityConstants.USER_KEY,userKey);
   }

   public static void setUserId(Long id){
        getLocalMap().put(SecurityConstants.DETAILS_USER_ID,id);
   }

   public static void setUserName(String name){
        getLocalMap().put(SecurityConstants.DETAILS_USERNAME,name);
   }
























}
