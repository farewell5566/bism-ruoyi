package com.bism.system.api.factory;

import com.bism.common.core.domain.R;
import com.bism.system.api.RemoteLogService;
import com.bism.system.api.domain.SysLoginInfo;
import com.bism.system.api.domain.SysOperLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;

public class RemoteLogFallbackFactory implements FallbackFactory<RemoteLogService> {

    private static final Logger log = LoggerFactory.getLogger(RemoteLogFallbackFactory.class);

    @Override
    public RemoteLogService create(Throwable cause) {
        log.error("日志服务调用失败：{}",cause.getMessage());

        return new RemoteLogService() {
            @Override
            public R<Boolean> saveLog(SysOperLog sysOperLog, String source) {
                return null;
            }

            @Override
            public R<Boolean> saveLogInfo(SysLoginInfo sysLogInfo, String source) {
                return null;
            }
        };
    }
}
