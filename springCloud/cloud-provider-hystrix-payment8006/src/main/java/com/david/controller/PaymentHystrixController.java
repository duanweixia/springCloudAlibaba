package com.david.controller;

import com.david.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Package: com.david.controller
 * @ClassName: PaymentHystrixController
 * @Author: zhangweixia
 * @CreateTime: 2020/12/24 下午4:09
 * @Description:
 */
@RestController
@Slf4j
public class PaymentHystrixController {
    @Resource
    private PaymentHystrixService paymentHystrixService;
    @GetMapping("/payment/hystrix/ok/{id}")
    @HystrixCommand  //使用豪猪哥页面接口上须加上该注解
    public String paymentInfo_OK(@PathVariable("id") Integer id){

        String result = paymentHystrixService.paymentInfo_OK(id);
        log.info("result:"+result);
        return result;
    }

    @GetMapping("/payment/hystrix/timeout/{id}")
    @HystrixCommand
    public String paymentInfo_Timeout(@PathVariable("id") Integer id){
        //用jmeter20000并发请求该接口，上面的接口响应明显变慢了，因为并发量高了，耗尽了tomcat里面的线程
        //我们这里利用hystrix做服务降级 官网是 继承HystrixCommand<String>,
        // 我们这里用@HystrxCommand注解来实现，在业务类里实现
        String result = paymentHystrixService.paymentInfo_Timeout(id);
        log.info("result:"+result);
        return result;
    }
    @GetMapping("/payment/hystrix/circuit/{id}")
    @HystrixCommand
    public String paymentCircuitBreaker(@PathVariable("id") Integer id){

        String result = paymentHystrixService.paymentCircuitBreaker(id);

        log.info("result"+result);

        return result;
    }
}
