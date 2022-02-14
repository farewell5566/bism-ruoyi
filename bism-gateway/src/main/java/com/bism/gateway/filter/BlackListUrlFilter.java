package com.bism.gateway.filter;

import com.bism.common.core.utils.ServletUtils;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class BlackListUrlFilter  extends AbstractGatewayFilterFactory<BlackListUrlFilter.Config> {

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            String url = exchange.getRequest().getURI().getPath();
            if (config.matchBlacklist(url)) {
                return ServletUtils.webFluxResponseWriter(exchange.getResponse(), "请求地址不允许访问");
            }
            return chain.filter(exchange);
        };
    }

    public static class Config{
        private List<String> blacklistUrl;

        private List<Pattern>blacklistUrlPattern = new ArrayList<>();

        public boolean matchBlacklist(String url){
            return blacklistUrlPattern.isEmpty()? false:blacklistUrlPattern.stream().filter(
                    p -> p.matcher(url).find()).findAny().isPresent();
        }

        public List<String>getBlacklistUrl(){return this.blacklistUrl;}

        public void setBlacklistUrl(List<String> blacklistUrl){
            this.blacklistUrl = blacklistUrl;
            this.blacklistUrlPattern.clear();
            this.blacklistUrl.forEach(url ->
            {
                this.blacklistUrlPattern.add(Pattern.compile(url.replaceAll("\\*\\*","(.*?)"),Pattern.CASE_INSENSITIVE));
            });
        }
    }
}
