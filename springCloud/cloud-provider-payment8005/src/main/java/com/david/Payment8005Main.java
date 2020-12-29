package com.david;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Package: com.david
 * @ClassName: Payment8005Main
 * @Author: zhangweixia
 * @CreateTime: 2020/12/23 下午6:41
 * @Description:
 */
@SpringBootApplication
@EnableDiscoveryClient
public class Payment8005Main {
    public static void main(String[] args) {
        SpringApplication.run(Payment8005Main.class,args);
    }
}
