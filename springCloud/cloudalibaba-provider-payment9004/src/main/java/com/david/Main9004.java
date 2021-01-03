package com.david;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Package: com.david
 * @ClassName: Main9004
 * @Author: zhangweixia
 * @CreateTime: 2020/12/31 下午6:09
 * @Description:
 */
@SpringBootApplication
@EnableDiscoveryClient
public class Main9004 {

    public static void main(String[] args) {
        SpringApplication.run(Main9004.class,args);
    }
}
