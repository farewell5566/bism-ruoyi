package com.bism.auth.controller;

import com.bism.auth.form.LoginBody;
import com.bism.auth.form.RegisterBody;
import com.bism.auth.service.SysLoginService;
import com.bism.common.core.domain.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class TokenController {

    @Autowired
    SysLoginService sysLoginService;

    @PostMapping("login")
    public R<?> login(@RequestBody LoginBody form){
        return R.ok();
    }

    @PostMapping("loginOut")
    public R<?> logOut(){
        return R.ok();
    }

    @PostMapping("refresh")
    public R<?> refresh(){
        return R.ok();
    }


    @PostMapping("register")
    public R<?>register(@RequestBody RegisterBody registerBody){

    }


}
