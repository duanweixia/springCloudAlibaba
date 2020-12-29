package com.david.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * @Package: com.david.lb
 * @ClassName: LoadBalancer
 * @Author: zhangweixia
 * @CreateTime: 2020/12/24 上午11:33
 * @Description:
 */
public interface LoadBalancer {

    ServiceInstance instances(List<ServiceInstance> serviceInstances);
}
