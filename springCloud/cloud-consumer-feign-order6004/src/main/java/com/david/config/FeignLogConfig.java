package com.david.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Package: com.david.config
 * @ClassName: FeignLogConfig
 * @Author: zhangweixia
 * @CreateTime: 2020/12/24 下午2:55
 * @Description:
 */
@Configuration
public class FeignLogConfig {
    @Bean
    Logger.Level feignLoggerLevel(){
        //import feign.Logger;  feign自带的日志，设置日志打印级别为FULL,表示打印所有日志
        return Logger.Level.FULL;
    }
}
