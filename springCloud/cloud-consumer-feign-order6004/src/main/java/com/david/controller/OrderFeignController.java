package com.david.controller;

import com.david.common.CommonResult;
import com.david.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Package: com.david.controller
 * @ClassName: OrderFeignController
 * @Author: zhangweixia
 * @CreateTime: 2020/12/24 下午2:06
 * @Description:
 */
@RestController
@Slf4j
public class OrderFeignController {
    @Resource
    private PaymentFeignService paymentFeignService;
    //feign实现远程调用，自带负载均衡（内部其实也是对Ribbon的封装）
    @GetMapping("/order/feign/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){

       return paymentFeignService.getPaymentById(id);
    }

    @GetMapping("/order/feign/timeOut")
    public String getPaymentFeignTimeOut(){
        //默认openFeign客户端等待时间为1秒，这里我们人为设置的程序调用需要3秒，然后报错
        //"message": "Read timed out executing GET http://PROVIDER-PAYMENT/payment/peymentFeignTimeOut"
        //对于有些调用比较久的程序，我们可以在yml配置文件中指定该程序超时控制，该超时控制底层也是使用的是Ribbon
        //的超时控制
        return paymentFeignService.peymentFeignTimeOut();
    }
}
