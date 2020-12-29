package com.david.service;

import org.springframework.stereotype.Component;

/**
 * @Package: com.david.service
 * @ClassName: PaymentHystrixServiceImpl
 * @Author: zhangweixia
 * @CreateTime: 2020/12/25 上午11:32
 * @Description:
 */
@Component //让spring能扫描到这个异常处理实现类
//如果服务提供者异常，这会进入这个实现类里面，来做异常处理
public class PaymentHystrixServiceImpl implements PaymentHystrixService{
    @Override
    public String paymentInfo_OK(Integer id) {
        return "PaymentHystrixService fallback-paymentInfo_OK,服务繁忙，请稍后再试 ";
    }

    @Override
    public String paymentInfo_Timeout(Integer id) {
        return "PaymentHystrixService fallback-paymentInfo_Timeout，服务繁忙，请稍后再试 ";
    }
}
