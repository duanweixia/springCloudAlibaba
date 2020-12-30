package com.david.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @Package: com.david.config
 * @ClassName: SpringContextConfig
 * @Author: zhangweixia
 * @CreateTime: 2020/12/30 下午5:21
 * @Description:
 */
@Configuration
public class SpringContextConfig {
    @Bean
    @LoadBalanced  //nacos里面集成了ribbon,直接使用该注解开启负载均衡
    public RestTemplate getRestTemplate(){

      return   new RestTemplate();
    }
}
