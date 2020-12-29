package com.david;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * @Package: com.david
 * @ClassName: HystrixDashboardMain6006
 * @Author: zhangweixia
 * @CreateTime: 2020/12/25 下午2:24
 * @Description:
 */
@SpringBootApplication
@EnableHystrixDashboard
public class HystrixDashboardMain6006 {
   //启动后访问localhost:6006/hystrix,进入hystrixDashboard可视化监控页面
    //然后再监控页面中输入要监控的服务 ：http://localhost:8006/hystrix.stream
    public static void main(String[] args) {
        SpringApplication.run(HystrixDashboardMain6006.class,args);
    }
}
