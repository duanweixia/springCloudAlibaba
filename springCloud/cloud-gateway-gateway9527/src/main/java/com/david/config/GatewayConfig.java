package com.david.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Package: com.david.config
 * @ClassName: GatewayConfig
 * @Author: zhangweixia
 * @CreateTime: 2020/12/28 下午7:00
 * @Description:  网关配置类（可以配置在yml里面或者写配置文件均可）
 */
@Configuration
public class GatewayConfig {
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder routeLocatorBuilder){
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
        //这里表示访问guonei或者guowai这个地址，就会跳转到对应下面那个地址
        routes.route("path1",r->r.path("/guonei")
        .uri("http://news.baidu.com/guonei")).build();
        routes.route("path2",r->r.path("/guoji")
        .uri("http://news.baidu.com/guonei")).build();
        return routes.build();
    }
}
