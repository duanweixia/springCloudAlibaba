package com.david.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @Package: com.david.config
 * @ClassName: ApplicationContextConfig
 * @Author: zhangweixia
 * @CreateTime: 2020/12/21 下午6:16
 * @Description:
 */
@Configuration
public class ApplicationContextConfig {
    @Bean
    @LoadBalanced  //开启Ribbon的负载均衡
    //注释掉，使用我们自己的写的负载均衡算法
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    //Ribbon+RestTemplate实现远程服务调用
}
