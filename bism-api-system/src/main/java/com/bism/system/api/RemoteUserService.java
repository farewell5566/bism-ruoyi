package com.bism.system.api;

import com.bism.common.core.constant.Constants;
import com.bism.common.core.constant.SecurityConstants;
import com.bism.common.core.domain.R;
import com.bism.system.api.constant.ServiceNameConstants;
import com.bism.system.api.domain.SysUser;
import com.bism.system.api.factory.RemoteUserFallbackFactory;
import com.bism.system.api.model.LoginUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(contextId = "FeignClient",value = ServiceNameConstants.SYSTEM_SERVICE,fallbackFactory = RemoteUserFallbackFactory.class)
public interface RemoteUserService {


    @GetMapping("/user/info/{username}")
    public R<LoginUser>getUserInfo(@RequestHeader String username, @RequestHeader(SecurityConstants.FROM_SOURCE)String souece);

    @PostMapping("/user/register")
    public R<Boolean>registerUserInfo(@RequestParam SysUser sysUser, @RequestHeader(SecurityConstants.FROM_SOURCE)String source);


}
