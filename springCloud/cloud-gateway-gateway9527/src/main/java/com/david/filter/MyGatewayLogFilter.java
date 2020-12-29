package com.david.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Date;

/**
 * @Package: com.david.filter
 * @ClassName: MyGatewayLogFilter
 * @Author: zhangweixia
 * @CreateTime: 2020/12/28 下午9:00
 * @Description: 自定义全局过滤器
 */
@Component //扫描整个包下面的
@Slf4j
public class MyGatewayLogFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("come in MyGatewayLogFilter:  "+new Date());
        String username = exchange.getRequest().getQueryParams().getFirst("username");
        //非法用户，进入下面，不放行
        if (username == null){
            log.info("用户名为null,非法用户");
            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
            return exchange.getResponse().setComplete();
        }

        //http://localhost:9527/payment/lb?username=aaa 访问后面带参数username 才能正常
        //访问，否则报指定状态 406

        //合法用户放行，进入下面过滤链，进一步过滤
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
