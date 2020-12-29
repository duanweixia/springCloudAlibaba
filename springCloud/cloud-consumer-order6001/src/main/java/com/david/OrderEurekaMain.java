package com.david;
import com.myrule.MyselfRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

/**
 * @Package: com.david
 * @ClassName: ConsumerMain
 * @Author: zhangweixia
 * @CreateTime: 2020/12/21 下午6:00
 * @Description:
 */
@SpringBootApplication //这个注解里面包含@ComponentScan注解，会扫描package com.david
// 包下面的所有的类以及组件
@EnableEurekaClient
//自定义Ribbon负载均衡规则为随机,name表示去访问支付微服务，也就是服务提供者的名称
//@RibbonClient(name = "PROVIDER-PAYMENT",configuration = MyselfRule.class)
public class OrderEurekaMain {

    public static void main(String[] args) {
        SpringApplication.run(OrderEurekaMain.class,args);
    }
}
