package com.david;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import javax.servlet.Servlet;

/**
 * @Package: com.david
 * @ClassName: PaymentHystrixMain8006
 * @Author: zhangweixia
 * @CreateTime: 2020/12/24 下午3:54
 * @Description:
 */
@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker //该注解作用激活异常处理方法上面的注解
public class PaymentHystrixMain8006 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentHystrixMain8006.class,args);
    }

    /*此配置为了6006服务服务监控而配置，与服务容错本身无关，springCloud升级后的坑
     * ServletRegistrationBean因为springboot的默认路径不是"/hystrix.stream",
     * 只要自己在自己的项目里配置下面的servlet即可*/
    @Bean
    public ServletRegistrationBean getServlet(){
        HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
        ServletRegistrationBean<Servlet> registrationBean = new ServletRegistrationBean<>(streamServlet);
        registrationBean.setLoadOnStartup(1);
        registrationBean.addUrlMappings("/hystrix.stream");
        registrationBean.setName("HystrixMetricsStreamServlet");
        return registrationBean;
    }
}
