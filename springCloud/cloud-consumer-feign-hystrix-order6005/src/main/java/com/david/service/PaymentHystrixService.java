package com.david.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Package: com.david.service
 * @ClassName: PaymentHystrixService
 * @Author: zhangweixia
 * @CreateTime: 2020/12/24 下午5:18
 * @Description:
 */
@Component
//value指定调用哪个服务提供者,fallback指定调用服务提供者出异常了走哪个类来处理异常信息
@FeignClient(value = "PROVIDER-HYSTRIX-PAYMENT",fallback = PaymentHystrixServiceImpl.class)
public interface PaymentHystrixService {
    //远程调用服务提供者的接口。如果服务提供者正常就直接调用，服务提供者异常，就走整个大接口的实现类实现
    //异常处理
    @GetMapping("/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id);

    @GetMapping("/payment/hystrix/timeout/{id}")
    public String paymentInfo_Timeout(@PathVariable("id") Integer id);
}
