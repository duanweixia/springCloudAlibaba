package com.david.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @Package: com.david.controller
 * @ClassName: SentinelController
 * @Author: zhangweixia
 * @CreateTime: 2020/12/31 下午2:54
 * @Description:
 */
@RestController
@Slf4j
public class SentinelController {
    //sentinel 页面控制台默认使用的是懒加载，需要请求一次，才能在控制台看到本服务进入控制台被监控
    //在控制台设置 /testA 接口 QPS=1,则表示1秒钟只能请求一次，否则报 ：Blocked by Sentinel (flow limiting)
    //从而达到限流
    //按照 访问的 url 进行限流
    @GetMapping("/testA")
    public String testA(){
       /* try {
            TimeUnit.MILLISECONDS.sleep(800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        return "testA";
    }
    @GetMapping("/testB")
    public String testB(){
        log.info(Thread.currentThread().getName()+"\t"+"testb");
        return "testB";
    }

    @GetMapping("/testD")
    public String testD(){

        /*try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        int a = 10/0;
        log.info(Thread.currentThread().getName()+"\t"+"testD");
        return "testD";
    }
    @GetMapping("/hotkey")
    // 热点key限流
    //这个注解等价于@HystrixCommand
    @SentinelResource(value = "hotkeytest",blockHandler = "deal_hotkey")
    //@SentinelResource主管配置出错，运行出错例如下面的 10/0 ，该走异常走异常，不会走兜底方法
    public String hotkey(@RequestParam(value = "p1",required = false) String p1,
                         @RequestParam(value = "p2",required = false) String p2){
       //int a = 10/0;
        return "success! hotkey";
    }
    //上面方法的兜底方法
    public String deal_hotkey(String p1, String p2, BlockException exception){

        return "deal_hotkey";
    }
}
