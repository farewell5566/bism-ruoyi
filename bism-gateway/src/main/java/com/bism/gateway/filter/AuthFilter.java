package com.bism.gateway.filter;

import com.bism.common.core.constant.CacheConstants;
import com.bism.common.core.constant.HttpStatus;
import com.bism.common.core.constant.SecurityConstants;
import com.bism.common.core.utils.JwtUtils;
import com.bism.common.core.utils.ServletUtils;
import com.bism.common.core.utils.StringUtils;
import com.bism.common.reids.service.RedisService;
import com.bism.gateway.config.properties.IgnoreWhiteProperties;
import com.bism.gateway.config.properties.TokenProperties;

import com.github.pagehelper.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import io.jsonwebtoken.Claims;

import reactor.core.publisher.Mono;

@Component
public class AuthFilter implements GlobalFilter, Ordered {

    private static final Logger log =  LoggerFactory.getLogger(AuthFilter.class);

    @Autowired
    private IgnoreWhiteProperties ignoreWhiteProperties;

    @Autowired
    private TokenProperties tokenProperties;

    @Autowired
    private RedisService redisService;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //ServerHttpResponse response = exchange.getResponse();
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpRequest.Builder mutate = request.mutate();

        String url = request.getURI().getPath();
        if (StringUtils.matches(url,ignoreWhiteProperties.getWhites())){
            return chain.filter(exchange);
            //filter(exchange,chain);
        }
        String token = getToken(request);
        if(StringUtils.isEmpty(token)){
            unauthorizedResponse(exchange,"token不能为空 ");
            return chain.filter(exchange);
        }
        Claims claims = JwtUtils.parseToken(token,tokenProperties.getSecret());
        if (claims == null){
            return unauthorizedResponse(exchange,"token is expired");
        }

        String userKey = JwtUtils.getUserKey(claims);
        boolean islogin = redisService.hasKey(getTokenKey(userKey));
        if(!islogin){
            return unauthorizedResponse(exchange,"登录状态已过期");
        }

        String userid = JwtUtils.getUserId(claims);
        String username = JwtUtils.getUserName(claims);

        if(StringUtil.isEmpty(userid) || StringUtil.isEmpty(username)){
            return unauthorizedResponse(exchange,"验证失败");
        }
        addHeader(mutate, SecurityConstants.DETAILS_USERNAME,username);
        addHeader(mutate,SecurityConstants.DETAILS_USER_ID,userid);
        addHeader(mutate,SecurityConstants.USER_KEY,userKey);

        removeHeader(mutate,SecurityConstants.FROM_SOURCE);
        return chain.filter(exchange.mutate().request(mutate.build()).build());

    }

    @Override
    public int getOrder() {
        return -200;
    }

    private void addHeader(ServerHttpRequest.Builder mutate,String name,Object value){
        if(value ==null){
            return ;
        }
        String valueStr = value.toString();
        String valueEncode = ServletUtils.urlEncode(valueStr);
        mutate.header(name,valueEncode);
    }

    private void removeHeader(ServerHttpRequest.Builder mutate,String name){
        mutate.headers(httpHeaders -> httpHeaders.remove(name)).build();
    }


    private String getToken(ServerHttpRequest request){
        String token = request.getHeaders().getFirst(tokenProperties.getAuthentication());
        if(StringUtils.isNotEmpty(token)&&token.startsWith(tokenProperties.getPrefix())){
            token = token.replaceFirst(tokenProperties.getPrefix(), StringUtils.EMPTY);
        }
        return token;
    }

    private Mono<Void> unauthorizedResponse(ServerWebExchange exchange,String msg){
        log.error("鉴权异常处理，请求路径：{}",exchange.getRequest().getPath());
        return ServletUtils.webFluxResponseWriter(exchange.getResponse(),msg, HttpStatus.UNAUTHORIZED);
    }

    private String getTokenKey(String token){
        return CacheConstants.LOGIN_TOKEN_KEY + token;
    }



}
