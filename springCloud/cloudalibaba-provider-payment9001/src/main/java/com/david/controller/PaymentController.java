package com.david.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Package: com.david.controller
 * @ClassName: PaymentController
 * @Author: zhangweixia
 * @CreateTime: 2020/12/30 下午4:54
 * @Description:
 */
@RestController
public class PaymentController {
    @Value("${server.port}")
    private String port;
    //nacos自带负载均衡
    @GetMapping("/payment/nacos/{id}")
    public String getPort(@PathVariable("id") Integer id){
        return "nacos registry,port :"+port+"\t"+"id"+id;
    }
}
