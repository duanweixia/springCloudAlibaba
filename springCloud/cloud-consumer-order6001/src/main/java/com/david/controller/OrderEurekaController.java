package com.david.controller;

import com.david.common.CommonResult;
import com.david.entities.Payment;
import com.david.lb.LoadBalancer;
import com.david.lb.MyLB;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

/**
 * @Package: com.david.controller
 * @ClassName: OrderController
 * @Author: zhangweixia
 * @CreateTime: 2020/12/21 下午6:23
 * @Description:
 */
@RestController
@Slf4j
public class OrderEurekaController {
    //调用在eureka上注册过的服务器提供者名称，服务提供者集群在eureka上有多个集群地址，但是服务提供者名称就一个
    public static final String PAYMENT_URL="http://PROVIDER-PAYMENT";
    @Resource
    private RestTemplate restTemplate;
    @Resource
    private LoadBalancer loadBalancer;
    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping("/order/add")
    public CommonResult add(@RequestBody Payment payment){
        //@RequestBody 接收的是 JSON字符串,所以前端传过来时需要使用JSON.stringify(json对象)
        // 把json对象转化为json字符串,然后使用 contentType : 'application/json'
        // 的contentType向服务端发送数据,使用postman时需要设置contentType : 'application/json'
        //直接使用对象接收设置成默认值即可
        log.info("param is"+payment);
        return restTemplate.postForObject(PAYMENT_URL+"/payment/add",payment,CommonResult.class);
    }
    @GetMapping("/order/get/{id}")
    public CommonResult get(@PathVariable("id") Long id){
        log.info("param is :"+id);
        return restTemplate.getForObject(PAYMENT_URL+"/payment/get/"+id,CommonResult.class);
    }

    //RestTemplate如果返回的是一个json字符串使用 getForObject方法
    //如果返回的是一个对象Bean,则使用 getForEntity
    @GetMapping("/order/getEntity/{id}")
    public CommonResult getEntity(@PathVariable("id") Long id){
        ResponseEntity<CommonResult> resultResponseEntity = restTemplate.getForEntity(PAYMENT_URL+"/payment/get/"+id,CommonResult.class);

        if (resultResponseEntity.getStatusCode().is2xxSuccessful()){
           return resultResponseEntity.getBody();
        }else {
           return   new CommonResult<>(400,"数据错误");
        }

    }
    @GetMapping("/order/lb")
    public String getPaymentLB(){
        List<ServiceInstance> instances = discoveryClient.getInstances("PROVIDER-PAYMENT");
        if (instances == null || instances.size() <0) {
            return "无效的服务，提供者服务未启动";
        }

        ServiceInstance instance = loadBalancer.instances(instances);
        URI uri = instance.getUri();

        return restTemplate.getForObject(uri+"/payment/lb",String.class);
    }

    //sleuth+zipkin链路监控
    @GetMapping("/order/zipkin")
    public String orderZipkin(){
        return restTemplate.getForObject(PAYMENT_URL+"/payment/zipkin/",String.class);
    }
}
