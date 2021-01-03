package com.david;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Package: com.david
 * @ClassName: Main9003
 * @Author: zhangweixia
 * @CreateTime: 2020/12/31 下午6:06
 * @Description:
 */
@SpringBootApplication
@EnableDiscoveryClient
public class Main9003 {

    public static void main(String[] args) {
        SpringApplication.run(Main9003.class,args);
    }
}
