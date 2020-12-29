package com.david;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Package: com.david
 * @ClassName: OrderFeignHystriMain6005
 * @Author: zhangweixia
 * @CreateTime: 2020/12/24 下午5:14
 * @Description:
 */
@SpringBootApplication
@EnableFeignClients //启动类上激活feign
@EnableHystrix  //开启服务降级
public class OrderFeignHystrixMain6005 {

    public static void main(String[] args) {
        SpringApplication.run(OrderFeignHystrixMain6005.class,args);
    }
}
