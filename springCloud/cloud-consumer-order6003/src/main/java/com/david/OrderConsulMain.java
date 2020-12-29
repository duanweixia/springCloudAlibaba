package com.david;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Package: com.david
 * @ClassName: Order6003Main
 * @Author: zhangweixia
 * @CreateTime: 2020/12/23 下午6:59
 * @Description:
 */
@SpringBootApplication
@EnableDiscoveryClient
public class OrderConsulMain {

    public static void main(String[] args) {
        SpringApplication.run(OrderConsulMain.class,args);
    }
}
