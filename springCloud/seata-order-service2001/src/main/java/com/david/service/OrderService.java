package com.david.service;

import com.david.domain.Order;
import org.apache.ibatis.annotations.Param;

/**
 * @Package: com.david.service
 * @ClassName: OrderService
 * @Author: zhangweixia
 * @CreateTime: 2021/1/2 下午5:38
 * @Description:
 */
public interface OrderService {

    void create (Order order);

    void update( Long userId, Integer status);
}
