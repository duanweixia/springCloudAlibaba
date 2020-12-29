package com.david;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Package: com.david
 * @ClassName: GatewayMain9527
 * @Author: zhangweixia
 * @CreateTime: 2020/12/25 下午4:51
 * @Description:  Gateway
 */
@SpringBootApplication
@EnableEurekaClient
public class GatewayMain9527 {

    public static void main(String[] args) {
        SpringApplication.run(GatewayMain9527.class,args);
    }
}
