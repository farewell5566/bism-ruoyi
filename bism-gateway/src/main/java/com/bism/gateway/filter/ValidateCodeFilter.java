package com.bism.gateway.filter;

import com.alibaba.fastjson.JSONObject;
import com.bism.common.core.utils.ServletUtils;
import com.bism.common.core.utils.StringUtils;
import com.bism.gateway.config.properties.CaptchaProperties;
import com.bism.gateway.service.ValidateCodeService;
import com.github.pagehelper.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicReference;

@Component
public class ValidateCodeFilter extends AbstractGatewayFilterFactory<Object> {

    private final static String[] VALIDATE_URL = new String[]{"/auth/login","login/register"};

    @Autowired
    private ValidateCodeService validateCodeService;

    @Autowired
    private CaptchaProperties captchaProperties;

    private static  final String CODE = "code";

    private static final String UUID = "uuid";


    @Override
    public GatewayFilter apply(Object config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();

            if(!StringUtils.containsAnyIgnoreCase(request.getURI().getPath(),VALIDATE_URL) || !captchaProperties.getEnabled()){

                return chain.filter(exchange);
            }
            try{
                String rspStr = resolveBodyFromRequest(request);
                JSONObject obj = JSONObject.parseObject(rspStr);
                validateCodeService.checkCaptcha(obj.getString(CODE),obj.getString(UUID));
            }
            catch (Exception e){
                return ServletUtils.webFluxResponseWriter(exchange.getResponse(),e.getMessage());
            }
            return chain.filter(exchange);
        };
    }

    private String resolveBodyFromRequest(ServerHttpRequest serverHttpRequest){
        Flux<DataBuffer> body = serverHttpRequest.getBody();
        AtomicReference<String> bodyRef = new AtomicReference<>();
        body.subscribe(dataBuffer -> {
            CharBuffer charBuffer = StandardCharsets.UTF_8.decode(dataBuffer.asByteBuffer());
            DataBufferUtils.release(dataBuffer);
            bodyRef.set(charBuffer.toString());
        });
        return bodyRef.get();
    }
}
