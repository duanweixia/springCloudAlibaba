package com.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Package: com.myrule
 * @ClassName: MyselfRule
 * @Author: zhangweixia
 * @CreateTime: 2020/12/24 上午9:11
 * @Description: Ribbon负载均衡规则替换
 */
//自定义Ribbon规则类，不能被@springbootApplication注解下面的@componentScan注解扫描到，所以本类
//不能放在com.david包下面，所以新建了一个com.myrule包，将此类放在此包下面。
/*官方文档明确给出了警告⚠️：
* 这个自定义配置类不能放在@ComponentScan所扫描的当前包以及子包下，否则我们自定义的这个配置类就会被所有的
* Ribbon客户端共享，达不到特殊化定制的目的了*/
@Configuration
public class MyselfRule {

    //Ribbon默认负载均衡是使用的 com.netflix.loadbalancer.RoundRobinRule 轮询，这里我们自定义
    //为随机，并在启动类上添加@RibbonClient(name="PROVIDER-PAYMENT",configuration=MySelfRule.class)
    @Bean
    public IRule myRule(){
        return new RandomRule(); //定义为随机
    }

    //Ribbon负载均衡核心组件:IRule 一共有7种负载均衡策略，默认是轮询，这里我们就自定义其中一种策略 -- 随机策略
    /*⚠️Ribbon核心组件IRule中的轮询策略算法原理：
    负载均衡算法：rest接口第几次请求数 % 服务器集群总数量 = 实际调用服务器位置下标
    每次服务重启后rest接口计数从1开始
    假设现在有3台服务提供者： List = 3 instance
    List<ServiceInstance> instances = discoveryClient.getInstance("PROVIDER-PAYMENT")
    List[0] instances = 127.0.0.1:8001
    List[1] instances = 127.0.0.1:8002
    List[2] instances = 127.0.0.1:8003
    第一次： 1 % 3 = 1 -->  index(下标) = 1 list.get(index);
    第二次： 2 % 3 = 2 -->  index(下标) = 1 list.get(index);
    第三次： 3 % 3 = 0 -->  index(下标) = 1 list.get(index);
    第四次： 4 % 3 = 1 -->  index(下标) = 1 list.get(index);
    第五次： 5 % 3 = 2 -->  index(下标) = 1 list.get(index);
    第六次： 6 % 3 = 0 -->  index(下标) = 1 list.get(index)....
    如果服务重启了，就又从1 开始计算下标*/

    // 同包下面手写了一个负载均衡算法 原理+JUC(CAS+自旋锁)
}
