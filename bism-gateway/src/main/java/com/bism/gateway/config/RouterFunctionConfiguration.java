package com.bism.gateway.config;

import com.bism.gateway.handler.ValidateCodeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;

@Configuration
public class RouterFunctionConfiguration {

    @Autowired
    ValidateCodeHandler validateCodeHandler;

    @Bean
    public RouterFunction routerFunction(){
        return RouterFunctions.route(RequestPredicates.GET("/code").
                        and(RequestPredicates.accept(MediaType.TEXT_PLAIN)),
                validateCodeHandler);
    }

}
