package com.david;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Package: com.david
 * @ClassName: SentinelMain8401
 * @Author: zhangweixia
 * @CreateTime: 2020/12/31 下午2:52
 * @Description:
 */
@SpringBootApplication
@EnableDiscoveryClient  //需要监控，引入注解
public class SentinelMain8401 {
    public static void main(String[] args) {
        SpringApplication.run(SentinelMain8401.class,args);
    }
}
