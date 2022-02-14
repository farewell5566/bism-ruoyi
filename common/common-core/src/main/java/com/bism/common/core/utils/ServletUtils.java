package com.bism.common.core.utils;

import com.alibaba.fastjson.JSONObject;
import com.bism.common.core.constant.Constants;
import com.bism.common.core.domain.R;
import com.bism.common.core.text.Convert;
import com.github.pagehelper.util.StringUtil;

import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;
import reactor.core.publisher.Mono;


public class ServletUtils {

    public static ServletRequestAttributes getRequestAttributes(){
        try{
            RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
            return (ServletRequestAttributes) attributes;
        }
        catch (Exception e){
            return null;
        }
    }

    public static HttpServletRequest getRequest(){
        try{
            return getRequestAttributes().getRequest();
        }
        catch(Exception e){
            return null;
        }
    }
    public static HttpServletResponse getResponse(){
        try{
            return getRequestAttributes().getResponse();
        }catch (Exception e){
            return null;
        }
    }

    public static String getParameter(String name){
        return getRequest().getParameter(name);
    }

    public static String getParameter(String name,String defaultValue){
        return Convert.toStr(getRequest().getParameter(name),defaultValue);
    }

    public static Integer getParameterToInt(String name){return Convert.toInt(getRequest().getParameter(name));}

    public static Integer getParameterToInt(String name,Integer defaultValue){
        return Convert.toInt(getRequest().getParameter(name),defaultValue);
    }

    public static Boolean getParameterToBoolean(String name){
        return Convert.toBool(getRequest().getParameter(name));
    }

    public static Boolean getParameterToBoolean(String name,Boolean defaultValue){
        return Convert.toBool(getRequest().getParameter(name),defaultValue);
    }

    /***
     * 获取session
     */

    public static HttpSession getSession(){
        return getRequest().getSession();
    }

    public static String urlDecode(String str){
        try{
            return URLDecoder.decode(str, Constants.UTF8);
        }catch (UnsupportedEncodingException e){
            return StringUtils.EMPTY;
        }
    }

    public static String urlEncode(String str){
        try{
            return URLEncoder.encode(str,Constants.UTF8);
        }
        catch (UnsupportedEncodingException e) {
            return StringUtils.EMPTY;
        }
    }

    public static String getHeader(HttpServletRequest request,String name){
        String value = request.getHeader(name);
        if (StringUtils.isEmpty(value)){
            return StringUtils.EMPTY;
        }
        return urlDecode(value);
    }

    public static Map<String,String>getHeaders(HttpServletRequest request){
        if (request == null){
            return null;
        }
        Map<String,String>map = new LinkedHashMap<>();
        Enumeration<String> headerNames = request.getHeaderNames();
        if (headerNames != null){
            while(headerNames.hasMoreElements()){
                String key = headerNames.nextElement();
                String value = request.getHeader(key);
                map.put(key,value);
            }
        }
        return map;
    }

    /**
     * 将字符串渲染到客户端
     * @return
     */
    public static String renderString(HttpServletResponse response ,String str){
        try{
            response.setStatus(200);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean isAjaxReuest(HttpServletRequest request){
        String accept = request.getHeader("accept");
        if(accept != null && accept.indexOf("application/json" )!= -1 ){
            return true;
        }
        String xRequestedWith = request.getHeader("X-Requested-With");
        if (xRequestedWith !=null&& xRequestedWith.indexOf("XMLHttpRequest") != -1){
            return true;
        }
        String uri = request.getRequestURI();
        if(StringUtils.inStringIgnoreCase(uri,".json",".xml")){
            return true;
        }
        String ajax =  request.getParameter("__ajax");
        if(StringUtils.inStringIgnoreCase(ajax,"json","xml")){
            return true;
        }
        return false;
    }


    public static Mono<Void> webFluxResponseWriter(ServerHttpResponse response, String contentType, HttpStatus status,Object value,int code ){
        response.setStatusCode(status);
        response.getHeaders().add(HttpHeaders.CONTENT_TYPE,contentType);
        R<?> result  = R.fail(code,value.toString());
        DataBuffer dataBuffer = response.bufferFactory().wrap(JSONObject.toJSONString(result).getBytes());
        return response.writeWith(Mono.just(dataBuffer));
    }

    public static Mono<Void> webFluxResponseWriter(ServerHttpResponse response,HttpStatus status,Object value ,int code ){
        return webFluxResponseWriter(response, MediaType.APPLICATION_JSON_VALUE,status,value,code);
    }

    public static Mono<Void> webFluxResponseWriter(ServerHttpResponse response,Object value){
        return webFluxResponseWriter(response,MediaType.APPLICATION_JSON_VALUE,HttpStatus.OK,value,R.FAIL);
    }

    public static Mono<Void> webFluxResponseWriter(ServerHttpResponse response,Object value,int code){
        return webFluxResponseWriter(response,MediaType.APPLICATION_JSON_VALUE,HttpStatus.OK,value,code);
    }









}
