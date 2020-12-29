package com.david.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @Package: com.david.config
 * @ClassName: ApplicationContextConfig
 * @Author: zhangweixia
 * @CreateTime: 2020/12/23 下午5:00
 * @Description:
 */
@Configuration
public class ApplicationContextConfig {

    @Bean
    @LoadBalanced //Ribbon负载均衡
    public RestTemplate getRestTemplate(){

        return new RestTemplate();
    }
}
