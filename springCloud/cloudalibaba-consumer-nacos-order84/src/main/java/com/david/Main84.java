package com.david;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Package: com.david
 * @ClassName: Main84
 * @Author: zhangweixia
 * @CreateTime: 2020/12/31 下午6:34
 * @Description:
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class Main84 {

    public static void main(String[] args) {
        SpringApplication.run(Main84.class,args);
    }
}
