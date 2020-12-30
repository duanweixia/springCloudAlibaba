package com.david.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Package: com.david.controller
 * @ClassName: ConfigNacosClientController
 * @Author: zhangweixia
 * @CreateTime: 2020/12/30 下午8:55
 * @Description:
 */
@RestController
@RefreshScope // 跟前面springCloud config 作用一样，支持nacos的动态刷新配置文件功能
public class ConfigNacosClientController {
   @Value("${config.info}")
   private String configInfo;

   @GetMapping("/config/info")
   public String getConfigInfo(){

       return configInfo;
   }
}
