package com.bism.auth.service;

import com.bism.common.core.constant.Constants;
import com.bism.common.core.constant.SecurityConstants;
import com.bism.common.core.constant.UserStatus;
import com.bism.common.core.domain.R;
import com.bism.common.core.exception.ServiceException;
import com.bism.common.core.utils.ServletUtils;
import com.bism.common.core.utils.StringUtils;
import com.bism.common.core.utils.ip.IpUtils;
import com.bism.common.security.utils.SecurityUtils;
import com.bism.system.api.RemoteLogService;
import com.bism.system.api.RemoteUserService;
import com.bism.system.api.domain.SysLoginInfo;
import com.bism.system.api.domain.SysUser;
import com.bism.system.api.model.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 这里主要是为判定登录和记录操作的
 */


@Service
public class SysLoginService {

    @Autowired
    RemoteLogService remoteLogService;

    @Autowired
    RemoteUserService remoteUserService;


    public LoginUser login(String userName, String password){

        if (StringUtils.isAnyBlank(userName,password)){
            recordLoginInfo("username",Constants.LOGIN_FAIL,"userName或password为空");
            throw new ServiceException("账号或密码为空");
        }
        if(userName.length()< 8 || password.length() < 8){
            recordLoginInfo(userName,Constants.LOGIN_FAIL,"账号或密码位数不对");
            throw new ServiceException("账号或密码不再制定范围");
        }

        R<LoginUser> userInfo = remoteUserService.getUserInfo(userName, password);

        if (R.FAIL == userInfo.getCode()){
            throw new ServiceException(userInfo.getMsg());
        }
        LoginUser loginUser  = userInfo.getData();
        SysUser sysUser = loginUser.getSysUser();

        if (loginUser == null){
            recordLoginInfo(userName,Constants.LOGIN_FAIL,"登录用于不存在");
            throw new ServiceException("登录用户：" + userName + "不存在");
        }
        if (sysUser.getDelFlag().equals(UserStatus.DELETED.getMsg())) {
            recordLoginInfo(userName,Constants.LOGIN_FAIL,"用户已经被删除");
            throw new ServiceException("登录用户"+userName + "已经被删除");
        }
        if (sysUser.getStatus().equals(UserStatus.DISABLE.getMsg())){
            recordLoginInfo(userName,Constants.LOGIN_FAIL,"用户已经停用");
            throw new ServiceException("登录用户"+userName + "已经停用");
        }
        if (!SecurityUtils.matchesPassword(sysUser.getPassword(),password)){
            recordLoginInfo(userName,Constants.LOGIN_FAIL,"账号或密码错误");
            throw new ServiceException("登录用户"+userName + "账号或密码错误");
        }
        recordLoginInfo(userName,Constants.LOGIN_SUCCESS,"登录成功");
        return loginUser;
    }

    public void loginOut(String userName){
        recordLoginInfo(userName,Constants.LOGOUT,"退出成功");
    }

    public void register(String username,String password){
        if(StringUtils.isAnyBlank(username,password)){
            throw new ServiceException("用户/密码必须填写");
        }


        SysUser sysUser = new SysUser();
        sysUser.setUserName(username);
        sysUser.setPassword(SecurityUtils.encryptPassword(password));
        R<Boolean> booleanR = remoteUserService.registerUserInfo(sysUser, SecurityConstants.INNER);
        if (R.FAIL == booleanR.getCode()){
            throw new ServiceException(booleanR.getMsg());
        }
        recordLoginInfo(username,Constants.REGISTER,"注册成功");


    }

    public void recordLoginInfo(String username,String status,String msg){
        SysLoginInfo logInfo = new SysLoginInfo();
        logInfo.setMsg(msg);
        logInfo.setUserName(username);
        logInfo.setIpaddr(IpUtils.getIpAddr(ServletUtils.getRequest()));
        //logInfo.createTime()
        if (StringUtils.equalsAny(status, Constants.LOGIN_SUCCESS,Constants.LOGOUT,Constants.REGISTER)){
            logInfo.setStatus(0);
        }
        else if(Constants.LOGIN_FAIL.equals(status)){
            logInfo.setStatus(-1);
        }
        remoteLogService.saveLogInfo(logInfo, SecurityConstants.INNER);
    }
}
