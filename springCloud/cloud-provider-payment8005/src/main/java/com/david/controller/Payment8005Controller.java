package com.david.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @Package: com.david.controller
 * @ClassName: Payment8005Controller
 * @Author: zhangweixia
 * @CreateTime: 2020/12/23 下午6:44
 * @Description:
 */
@RestController
@Slf4j
public class Payment8005Controller {

    @Value("${server.port}")
    private String port;
    @GetMapping("/payment/consul")
    public String paymentZk(){

        return "springCloud with consul: "+port+"\t"+ UUID.randomUUID().toString();
    }
}
