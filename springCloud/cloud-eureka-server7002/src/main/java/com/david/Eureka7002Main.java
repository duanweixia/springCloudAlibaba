package com.david;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @Package: com.david
 * @ClassName: eureka7002Main
 * @Author: zhangweixia
 * @CreateTime: 2020/12/22 下午5:51
 * @Description:
 */
@SpringBootApplication
@EnableEurekaServer
public class Eureka7002Main {

    public static void main(String[] args) {
        SpringApplication.run(Eureka7002Main.class,args);
    }
}
