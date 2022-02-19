package com.bism.auth.service;

import com.bism.auth.form.LoginUser;
import com.bism.common.core.utils.StringUtils;
import com.bism.system.api.domain.SysLoginInfo;
import org.springframework.stereotype.Service;

/**
 * 这里主要是为判定登录和记录操作的
 */


@Service
public class SysLoginService {


    public LoginUser login(String userName, String password){

        if (StringUtils.isAnyBlank(userName,password)){

            return null;
        }
        return null;



    }

    public void loginOut(String loginName){

    }

    public void register(String username,String password){

    }

    public void recordLoginInfo(String username,String status,String msg){
        SysLoginInfo logInfo = new SysLoginInfo();
        logInfo.setMsg(msg);
        logInfo.setUserName(username);
        //logInfo.createTime()


    }
}
