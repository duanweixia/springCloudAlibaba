package com.david.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
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
public class OrderConsulController {

    public static final String consulUrl = "http://provider-payment";
    @Resource
    private RestTemplate restTemplate;
    @Resource
    private DiscoveryClient discoveryClient;
    /*总结：eureka和zookeeper 都是基于java语言，而consul是基于go语言。CAP理论中，eureka是使用的AP理论
    * 而zookeeper和consul都是使用的CP理论,分布式系统一定会保证P,不然还叫分布式么。服务健康检查方面：eureka是可配置支持，zookeeper和consul是
    * 支持。对外暴露接口方面：eureka使用 HTTP，consul使用 HTTP/DNS 二者都有可视化界面的，
    * zookeeper使用的是 linux的 zkCli客户端，题外：redis和mongodb等nosql是CP,mysql是CA.
    * CAP理论关注的粒度是数据，而不是整体系统设计的策略。假如有A和B二个系统：1、当没有出现网络分区时，系统A和
    * 系统B的数据一致，X=1。2、将系统A的值修改为2，X=2。  3、当出现网络分区后，系统A和系统B之间的数据同步
    * 数据失败，系统B的X=1。4、当客户端请求系统B时，为了保证可用性，此时B应返回旧值，X=1
    * AP架构：当网络分区后（即分布式），为了保证可用性，系统B可以返回旧值，保证系统可用性，结论：违背了一致性C要求
    * ，只满足可用性和分区容错性，即AP 比如eureka的自我保护机制，服务挂掉了一个后，短时间该服务还是在上面。
    * CP架构：当网络分区后（即分布式），为了保证一致性，一个服务挂掉后，就必须拒绝请求，否则无法保证一致性，结论：违背
    * 了可用性A要求，只满足一致性和分区容错性，即CP 比如：使用zookeeper和consul时，上面注册的某个服务挂掉后，通过
    * 心跳机制，及时把挂掉服务踢出了注册中心，zookeeper临时节点。
    * 3者都能跟springCloud 很好的集成*/
    @GetMapping("/orderConsul/get")
    public String get(){
       return restTemplate.getForObject(consulUrl+"/payment/consul",String.class);
    }

    //基于consul的服务治理,本质都一样，都是调用的是 DiscoveryClient里面的对象
    @GetMapping("/orderConsul/getDiscoveryClient")
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
