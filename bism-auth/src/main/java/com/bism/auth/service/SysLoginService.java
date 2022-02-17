package com.bism.auth.service;

import com.bism.auth.form.LoginUser;
import com.bism.common.core.utils.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class SysLoginService {


    public LoginUser login(String userName, String password){

        if (StringUtils.isAnyBlank(userName,password)){

        }



    }

    public void loginOut(String loginName){

    }

    public void register(String username,String password){

    }

    public void recordLoginInfo(String username,String status,String msg){

    }


}
