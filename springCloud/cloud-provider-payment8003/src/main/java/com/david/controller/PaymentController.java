package com.david.controller;

import com.david.common.CommonResult;
import com.david.entities.Payment;
import com.david.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @Package: com.david.controller
 * @ClassName: BaseController
 * @Author: zhangweixia
 * @CreateTime: 2020/12/21 下午2:28
 * @Description:
 */
@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;
    @Value("${server.port}")
    private String port;
    @PostMapping(value = "/payment/add")
    public CommonResult add(@RequestBody Payment payment){
        //注意，使用restTemplate远程调用此接口时，被调用的接口用对象接收必须要加上@RequestBody注解
        //不加@RequestBody则参数传递过来为空，不能使用@requestParam注解来传递参数
        log.info("param is :"+payment);
        int result = paymentService.add(payment);
        log.info("插入："+result+"条数据");
        return new CommonResult(200,"新增成功:"+port,result);
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        log.info("param is:" +id);
        Payment payment = paymentService.getPaymentById(id);
        log.info("查询结果"+payment);
        return new CommonResult(200,"查询成功:"+port,payment);
    }

    //此接口用于模拟手写负载均衡轮询算法调用
    @GetMapping("/payment/lb")
    public String getPaymentLB(){

        return port;
    }

    //定义超时方法，测试feign的超时处理
    @GetMapping("/payment/peymentFeignTimeOut")
    public String peymentFeignTimeOut(){
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return port;
    }
}
