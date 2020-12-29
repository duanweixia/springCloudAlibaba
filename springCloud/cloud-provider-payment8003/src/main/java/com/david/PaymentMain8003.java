package com.david;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Package: com.david
 * @ClassName: paymentMain8001
 * @Author: zhangweixia
 * @CreateTime: 2020/12/20 下午4:33
 * @Description: 启动类
 */
@SpringBootApplication
@EnableEurekaClient
public class PaymentMain8003 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentMain8003.class,args);
    }

}
