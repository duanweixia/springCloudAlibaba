package com.david.controller;

import com.david.common.CommonResult;
import com.david.entities.Payment;
import com.david.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
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
    @Resource
    private DiscoveryClient discoveryClient;
    @Value("${server.port}")
    private String port;
    @PostMapping(value = "/payment/add")
    public CommonResult add(@RequestBody Payment payment){
        //注意，使用restTemplate远程调用此接口时，被调用的接口用对象接收必须要加上@RequestBody注解
        //不加@RequestBody则参数传递过来为空，不能使用@requestParam注解来传递参数
        log.info("param is :"+payment);
        int result = paymentService.add(payment);
        log.info("插入："+result+"条数据");
        return new CommonResult(200,"端口号:"+port+"--新增成功",result);
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        log.info("param is:" +id);
        Payment payment = paymentService.getPaymentById(id);
        log.info("查询结果"+payment);
        return new CommonResult(200,"端口号:"+port+"--查询成功",payment);
    }
    //服务注册与发现接口,盘点服务注册中心上已注册的所以服务信息  主启动类上加上 @EnableDiscoveryClient
    @GetMapping(value = "/payment/discovery")
    public Object discovery(){
        List<String> services = discoveryClient.getServices();
        services.forEach(s->{
            log.info("element---"+s);
        });
        List<ServiceInstance> instances = discoveryClient.getInstances("PROVIDER-PAYMENT");
        instances.forEach(i->{
            log.info(i.getServiceId()+"\t"+i.getHost()+"\t"+i.getPort()+"\t"+i.getUri());
        });

        return this.discoveryClient;
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
