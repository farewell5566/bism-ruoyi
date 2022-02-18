package com.bism.common.core.context;

import java.util.Map;

/**
 * 获取当前线程变量中的用户ID、用户名称、Token信息，
 *  注意： 必须在网关通过请求头的方法传入，同时在HeaderInterceptor拦截器设置值。 否则这里无法获取
 *
 */
public class SecurityContextHolder {

    private static final TransmittableThreadLocal <Map<String,Object>> THREAD_LOCAL = new TransmittableThreadLocal<>();





}
