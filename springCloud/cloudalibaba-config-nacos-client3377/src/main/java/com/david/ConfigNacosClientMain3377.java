package com.david;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Package: com.david
 * @ClassName: ConfigNacosClientMain3377
 * @Author: zhangweixia
 * @CreateTime: 2020/12/30 下午8:54
 * @Description:
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ConfigNacosClientMain3377 {

    public static void main(String[] args) {
        SpringApplication.run(ConfigNacosClientMain3377.class,args);
    }
}
