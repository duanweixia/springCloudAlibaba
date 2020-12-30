package com.david.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @Package: com.david.controller
 * @ClassName: NacosOrderController
 * @Author: zhangweixia
 * @CreateTime: 2020/12/30 下午5:20
 * @Description:
 */
@RestController
public class NacosOrderController {
    @Resource
    private RestTemplate restTemplate;
    @Value("${service-url.nacos-user-service}")
    private String serverUrl;
    @GetMapping("/order/nacos/{id}")
    public String getPort(@PathVariable("id") Integer id){

        return restTemplate.getForObject(serverUrl+"/payment/nacos/"+id,String.class);
    }
}
