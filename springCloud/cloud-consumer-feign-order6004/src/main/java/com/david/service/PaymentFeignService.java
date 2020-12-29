package com.david.service;

import com.david.common.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Package: com.david.service
 * @ClassName: PaymentFeignService
 * @Author: zhangweixia
 * @CreateTime: 2020/12/24 下午1:59
 * @Description: 通过feign实现调用服务提供者接口
 */
@Component
@FeignClient(value = "PROVIDER-PAYMENT") //指定调用哪个服务提供者
public interface PaymentFeignService {

    //调用服务提供者controller的方法
    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id);

    //定义超时方法，测试feign的超时处理
    @GetMapping("/payment/peymentFeignTimeOut")
    public String peymentFeignTimeOut();
}
