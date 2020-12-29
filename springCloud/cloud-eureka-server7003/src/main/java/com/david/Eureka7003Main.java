package com.david;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @Package: com.david
 * @ClassName: Eureka7003Main
 * @Author: zhangweixia
 * @CreateTime: 2020/12/22 下午5:55
 * @Description:
 */
@SpringBootApplication
@EnableEurekaServer
public class Eureka7003Main {

    public static void main(String[] args) {
        SpringApplication.run(Eureka7003Main.class,args);
    }
}
