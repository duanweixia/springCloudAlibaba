package com.david;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Package: com.david
 * @ClassName: OrderFeignMain
 * @Author: zhangweixia
 * @CreateTime: 2020/12/24 下午1:55
 * @Description:
 */
@SpringBootApplication
@EnableFeignClients //启动类上激活feign
public class OrderFeignMain {

    public static void main(String[] args) {
        SpringApplication.run(OrderFeignMain.class,args);
    }
}
