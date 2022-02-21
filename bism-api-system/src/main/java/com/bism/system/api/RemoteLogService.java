package com.bism.system.api;

import com.bism.common.core.constant.SecurityConstants;
import com.bism.common.core.domain.R;
import com.bism.system.api.constant.ServiceNameConstants;
import com.bism.system.api.domain.SysLoginInfo;
import com.bism.system.api.domain.SysOperLog;
import com.bism.system.api.factory.RemoteLogFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(contextId = "remoteLogServcie",value = ServiceNameConstants.SYSTEM_SERVICE,fallbackFactory = RemoteLogFallbackFactory.class)
public interface RemoteLogService {


    @PostMapping("/operlog")
    public R<Boolean> saveLog(@RequestBody SysOperLog sysOperLog, @RequestHeader(SecurityConstants.FROM_SOURCE) String source);


    @PostMapping("/logininfo")
    public R<Boolean>saveLogInfo(@RequestBody SysLoginInfo sysLogInfo,@RequestHeader(SecurityConstants.FROM_SOURCE)String source);





}
