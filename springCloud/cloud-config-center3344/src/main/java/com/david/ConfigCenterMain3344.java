package com.david;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @Package: com.david
 * @ClassName: ConfigCenterMain3344
 * @Author: zhangweixia
 * @CreateTime: 2020/12/29 下午5:17
 * @Description:
 */
@SpringBootApplication
@EnableConfigServer
public class ConfigCenterMain3344 {
    public static void main(String[] args) {
        SpringApplication.run(ConfigCenterMain3344.class,args);
       /* ###读取分支 启动项目访问http://localhost:3344/master/config-dev.yml 即可读取
      #读取到的结果：config:
            #       info: master branch,springcloud-config/config-dev.yml version=7*/
        //通过消息总线bus,当在github上改了配置文件，运维只需要执行：
        // curl -X POST "http://localhost:3344/actuator/bus-refresh"
        //就可以在其他3355和3366服务上获得同步刷新的配置文件
    }
}
