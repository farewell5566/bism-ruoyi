package com.bism.gateway.handler;

import com.bism.common.core.exception.CaptchaException;
import com.bism.common.core.web.domain.AjaxResult;
import com.bism.gateway.service.ValidateCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.io.IOException;

@Component
public class ValidateCodeHandler implements HandlerFunction<ServerResponse> {


    @Autowired
    ValidateCodeService validateCodeService;

    @Override
    public Mono<ServerResponse> handle(ServerRequest serverRequest) {
        AjaxResult ajaxResult ;
        try{
            ajaxResult = validateCodeService.createValidateCode();
        }catch (CaptchaException |IOException e){
            return Mono.error(e);
        }
        return ServerResponse.status(HttpStatus.OK).body(BodyInserters.fromValue(ajaxResult));

    }
}
