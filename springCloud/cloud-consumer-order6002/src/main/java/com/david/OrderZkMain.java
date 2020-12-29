package com.david;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Package: com.david
 * @ClassName: OrderZkMain
 * @Author: zhangweixia
 * @CreateTime: 2020/12/23 下午4:58
 * @Description:
 */
@SpringBootApplication
@EnableDiscoveryClient
public class OrderZkMain {

    public static void main(String[] args) {
        SpringApplication.run(OrderZkMain.class,args);
    }
}
