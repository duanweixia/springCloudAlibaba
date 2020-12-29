package com.david.controller;

import com.david.common.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Package: com.david.controller
 * @ClassName: OrderZkController
 * @Author: zhangweixia
 * @CreateTime: 2020/12/23 下午5:00
 * @Description:
 */
@RestController
@Slf4j
public class OrderZkController {

    public static final String zkUrl = "http://provider-payment";
    @Resource
    private RestTemplate restTemplate;
    @Resource
    private DiscoveryClient discoveryClient;
    @GetMapping("/orderZk/get")
    public String get(){
       return restTemplate.getForObject(zkUrl+"/payment/zk",String.class);
    }

    //基于zookeeper的服务治理
    @GetMapping("/orderZk/getDiscoveryClient")
    public Object getZookerperInfo(){
        List<String> services = discoveryClient.getServices();
        services.forEach(s->{
            log.info("server:"+"\t"+s);
        });
        List<ServiceInstance> instances = discoveryClient.getInstances("consumer-order");
        instances.forEach(i->{
            log.info(i.getServiceId()+"\t"+i.getHost()+"\t"+i.getUri()+"\t"+i.getPort());
        });
        List<ServiceInstance> instances1 = discoveryClient.getInstances("provider-payment");
        instances1.forEach(i1->{
            log.info(i1.getServiceId()+"\t"+i1.getHost()+"\t"+i1.getUri()+"\t"+i1.getPort());
        });
        //返回当前对象的服务信息
        return this.discoveryClient;
    }
}
