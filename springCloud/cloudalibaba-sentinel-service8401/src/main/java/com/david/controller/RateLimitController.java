package com.david.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.david.common.CommonResult;
import com.david.entities.Payment;
import com.david.myhandler.CustomerBlockHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Package: com.david.controller
 * @ClassName: RateLimitController
 * @Author: zhangweixia
 * @CreateTime: 2020/12/31 下午5:21
 * @Description:
 */
@RestController
public class RateLimitController {
    //测试按资源名称限流
    @GetMapping("/byResource")
    @SentinelResource(value = "byResource",blockHandler = "handleException")
    public CommonResult byResource(){

        return new CommonResult(200,"按资源名称限流测试OK",
                new Payment(2020L,"serial001"));
    }

    public CommonResult handleException(BlockException exception){

        return new CommonResult(444,
                exception.getClass().getCanonicalName()+"\t 服务不可用");
    }
   // 按 url 进行限流
    @GetMapping("/byurl")
    @SentinelResource(value = "byurl") //没有写blockHandler = "handleException"
    //则用系统默认的：Blocked by Sentinel (flow limiting)
    public CommonResult byurl(){

        return new CommonResult(200,"按 url 限流测试OK",
                new Payment(2020L,"serial001"));
    }

    //按用户自定义配置兜底方法
    @GetMapping("/customerBlockHandler")
    @SentinelResource(value = "customerBlockHandler",
            blockHandlerClass = CustomerBlockHandler.class,
            blockHandler = "handleException2")
    //这里如果使用url方式限流不会走兜底方法，需要使用按资源名称限流
    //@SentinelResource 不支持 private 方法
    public CommonResult customerBlockHandler(){

        return new CommonResult(200,"按用户自定义",new Payment(2020L,"serial3"));
    }
}
