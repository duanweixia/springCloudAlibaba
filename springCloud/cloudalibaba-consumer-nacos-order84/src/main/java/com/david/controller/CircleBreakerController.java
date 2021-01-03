package com.david.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.david.common.CommonResult;
import com.david.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @Package: com.david.controller
 * @ClassName: CircleBreakerController
 * @Author: zhangweixia
 * @CreateTime: 2020/12/31 下午6:38
 * @Description:  Ribbon+RestTemplate 远程调用，省略 openFeign 方式调用
 */
@RestController
@Slf4j
public class CircleBreakerController {

    public static final String PAYMENT_URL = "http://nacos-payment-provider";
    @Resource
    private RestTemplate restTemplate;
    @GetMapping("/fallback/{id}")
    //@SentinelResource(value = "fallback",fallback = "handlerFallback")//fallback只负责java异常
    //@SentinelResource(value = "fallback",blockHandler = "blockHandler") //blockHandler只负责sentinel控制台配置违规异常
    //如果fallback和blockHandler都配置了，开始出java异常，sentinel异常满足条件也会出来，sentinel控制台异常说了算
    @SentinelResource(value = "fallback",fallback = "handlerFallback",blockHandler = "blockHandler",
                     exceptionsToIgnore = {IllegalArgumentException.class})//exceptionsToIgnore 表示不走这个非法参数异常兜底方法
    public CommonResult fallback(@PathVariable("id") Long id){

        CommonResult result = restTemplate.getForObject(PAYMENT_URL + "/paymentSql/" + id, CommonResult.class);

        if (id == 4){
            throw new IllegalArgumentException("非法参数异常");
        }else if (result.getData() == null){
            throw new NullPointerException("空指针异常");
        }

        return result;
    }
    //当id =4 时，方法返回异常：兜底异常handlerFallback,异常内容：非法参数异常"
    // 当id 不为1，2，3，4 时，方法返回异常：兜底异常handlerFallback,异常内容：空指针异常
    //本列是fallback 方法
    public CommonResult handlerFallback(@PathVariable Long id,Throwable e){
        Payment payment = new Payment(id, "null");
        return new CommonResult(444,"兜底异常handlerFallback,异常内容："+e.getMessage(),payment);
    }
    //本列是blockHandler 方法,当异常达到控制台设置的比如说2次，就会执行下面的方法，2次及以前该报什么异常还是报什么异常
    public CommonResult blockHandler(@PathVariable Long id, BlockException e){

        Payment payment = new Payment(id, "null");
        return new CommonResult(444,"blockHandler-sentinel限流，无此流水：异常内容："+e.getMessage(),payment);
    }


}
