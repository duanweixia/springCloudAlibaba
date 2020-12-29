package com.david.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Package: com.david.service
 * @ClassName: PaymentHystrixServiceImpl
 * @Author: zhangweixia
 * @CreateTime: 2020/12/24 下午3:57
 * @Description:
 */
@Service
public class PaymentHystrixServiceImpl implements PaymentHystrixService {


    @Override
    @HystrixCommand
    public String paymentInfo_OK(Integer id) {
        return "线程池："+Thread.currentThread().getName()+" paymentInfo_OK,id: "+id+"\t"+"哈哈";
    }
    //我们这里利用hystrix做服务降级 官网是 继承HystrixCommand<String>,
    // 我们这里用@HystrxCommand注解来实现，在业务类里实现
    @Override
    //参数里面指定处理该方法的方法名称,以及该方法的超时时间，超过3秒则走异常处理方法，添加了这个注解，
    //主启动类上加上@EnableCircuitBreaker 注解激活。fallbackMethod 表示兜底方法，该方法出异常
    //也会走兜底方法指定的方法，这里服务降级是放在服务提供端，一般我们把服务降级放在服务消费端
   /* @HystrixCommand(fallbackMethod = "paymentInfo_TimeoutHandler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000")
    })*/
    @HystrixCommand
    public String paymentInfo_Timeout(Integer id) {
        /*int time = 2;
        try {
            TimeUnit.SECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        //int i = 10/0;
        return "线程池："+Thread.currentThread().getName()+
                " paymentInfo_Timeout,id: "+id+"\t"+"";
    }



    //上面方法报异常或者是服务超时 这个方法用来兜底，这就是服务降级
    public String paymentInfo_TimeoutHandler(Integer id){
        return "线程池："+Thread.currentThread().getName()+
                " 系统繁忙或运行报错,id: "+id+"\t"+"哈哈";
    }


    //服务熔断方法🐂
    @Override
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {//表示10次里面有6次就开始跳闸
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),//是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),//请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),//时间范围
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"),//失败率达到多少后开始跳闸
    })
    public String paymentCircuitBreaker(Integer id) {
        //连续多次（上面配置的）请求为负数，后面再传正数，断路器已经打开为半开状态，链路断开，提示服务降级方法。
        // 当连续多次输入正数或者到了设置的时间范围（10秒）
        //断路器检测到多次正确或者到了设置到时间范围后，则会恢复，闸刀合并，链路联通
        if (id <0) throw new RuntimeException("id 不能为负数");
        //糊涂工具包
        String serialNumber = IdUtil.simpleUUID();//等价于 UUID.randomUUID().toString()

        return Thread.currentThread().getName()+"\t"+"调用成功，流水号："+serialNumber;
    }

    public String paymentCircuitBreaker_fallback(Integer id){
        return "id 不能是负数，请稍后再试。id="+id;
    }
}
