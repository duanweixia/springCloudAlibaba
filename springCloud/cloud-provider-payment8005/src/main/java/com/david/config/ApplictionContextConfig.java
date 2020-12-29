package com.david.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @Package: com.david.config
 * @ClassName: ApplictionContextConfig
 * @Author: zhangweixia
 * @CreateTime: 2020/12/23 下午6:45
 * @Description:
 */
@Configuration
public class ApplictionContextConfig {

    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate(){

        return new RestTemplate();
    }
}
