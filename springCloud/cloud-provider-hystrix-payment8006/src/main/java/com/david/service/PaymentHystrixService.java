package com.david.service;

/**
 * @Package: com.david.service
 * @ClassName: PaymentHystrixService
 * @Author: zhangweixia
 * @CreateTime: 2020/12/24 下午3:56
 * @Description:
 */

public interface PaymentHystrixService {
    //服务降级
     String paymentInfo_OK(Integer id);
    //服务降级
     String paymentInfo_Timeout(Integer id);

     //服务熔断
     public String paymentCircuitBreaker(Integer id);
}
