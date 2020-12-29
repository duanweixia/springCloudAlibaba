package com.david;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @Package: com.david
 * @ClassName: EurekaMain
 * @Author: zhangweixia
 * @CreateTime: 2020/12/21 下午10:40
 * @Description:
 */

/*对于数据访问层，无论是Sql还是NoSql，SpringBoot默认采用整合SpringData的方式进行统一管理，
  添加大量的自动配置，屏蔽了很多设置。启动时会自动注入数据源。而此时在配置文件中并没有配置数据源信息，
  因此会抛出异常。对于不需要引入数据源的模块，我们可以在@SpringBootApplication注解中排除掉*/
@SpringBootApplication
@EnableEurekaServer
public class Eureka7001Main {

    public static void main(String[] args) {
        SpringApplication.run(Eureka7001Main.class,args);
    }
}
