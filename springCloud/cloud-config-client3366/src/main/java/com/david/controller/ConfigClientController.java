package com.david.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Package: com.david.controller
 * @ClassName: ConfigClientController
 * @Author: zhangweixia
 * @CreateTime: 2020/12/29 下午8:27
 * @Description:
 */
@RestController
@RefreshScope
public class ConfigClientController {
    @Value("${config.info}")
    private String configInfo;
    //客户端成功获取了配置信息
    @GetMapping("/configInfo")
    public String getConfigInfo(){
        // 返回：master branch,springcloud-config/config-dev.yml version=7
        return configInfo;
        //github 上面把version改成了100，3344服务访问马上改变了，而3355服务访问还是version=7没有变化
        //当重启了3355，version才变成了100，我们不可能每次改了github上面的配置，都重启配置中心客户端噻？
        //1.我们 在3355客户端中首先加上图形监控依赖 spring-boot-starter-actuator
        //2.我们在bootstrap.yml配置文件中加上 暴露监控端点配置
        //3.在业务类controller上加上 刷新的注解 @RefreshScope
        //4.需要运维人员在服务器上执行 curl -X POST "http://localhost:3355/actuator/refresh"
        //5.分布式下运维去手动刷新每个服务要崩溃，这里引入springCloud Bus 消息总线来自动刷新分布式配置
        // Bus 只支持2种消息代理：RabbitMQ和Kafka
    }
}
