package com.david.controller;

import com.david.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Package: com.david.controller
 * @ClassName: OrderFeignHystrixController
 * @Author: zhangweixia
 * @CreateTime: 2020/12/24 下午5:23
 * @Description:
 */
@RestController
@Slf4j
//配置开启全局服务降级
//@DefaultProperties(defaultFallback = "payment_global_fallbackMethod")
public class OrderFeignHystrixController {
    @Resource
    private PaymentHystrixService paymentHystrixService;
    //服务提供者用jmter压测20000，消费者这边用feign远程调用提供者接口明显变慢，高并发下tomcat里工作
    //线程耗尽，导致其他线程访问缓慢
    @GetMapping("/order/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id){

       String result =  paymentHystrixService.paymentInfo_OK(id);
       return  result;
    }

    @GetMapping("/order/hystrix/timeout/{id}")
    //我们这里利用hystrix做服务降级 官网是 继承HystrixCommand<String>,
    // 我们这里用@HystrxCommand注解来实现，在业务类里实现
    //参数里面指定处理该方法的方法名称,以及该方法的超时时间，超过3秒则走异常处理方法，添加了这个注解，
    //主启动类上加上@EnableCircuitBreaker 注解激活。fallbackMethod 表示兜底方法，该方法出异常
    //也会走兜底方法指定的方法，这里服务降级是放在服务提供端，一般我们把服务降级放在服务消费端
   /* @HystrixCommand(fallbackMethod = "paymentInfo_TimeoutHandler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "1000")
    })*/
    //@HystrixCommand //该注解里面没有指定详细参数的，就走全局降级处理，指定了参数的就走该指定的参数方法
    public String paymentInfo_Timeout(@PathVariable("id") Integer id){
       // int i = 10/0;
        String result = paymentHystrixService.paymentInfo_Timeout(id);

        return result;
    }

    //上面方法报异常或者是服务超时 这个方法用来兜底，这就是服务降级
    public String paymentInfo_TimeoutHandler(Integer id){
        return "线程池："+Thread.currentThread().getName()+
                " 系统繁忙或运行报错,id: "+id+"\t"+"服务消费端6005";
    }

    //⚠️全局 定制fallback,降低代码膨胀，需要细分的服务降级才使用上面的，其他的都走通用的服务降级处理
    //1。每个方法配置一个服务降级方法，技术上可以，实际上傻，代码太膨胀了
    //2。除了个别重要核心业务专属，其他普通的可以通过@DefaultProperties(defaultFallback= "")统一
    //跳转到统一处理结果页面

    //下面是全局服务降级方法
    public String payment_global_fallbackMethod(){

        return " 全局服务降级方法:系统繁忙，请稍后再试";
    }

}
