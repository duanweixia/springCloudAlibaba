package com.david.controller;

import com.david.domain.CommonResult;
import com.david.domain.Order;
import com.david.service.OrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Package: com.david.controller
 * @ClassName: OrderController
 * @Author: zhangweixia
 * @CreateTime: 2021/1/2 下午6:11
 * @Description:
 */
@RestController
public class OrderController {
    @Resource
    private OrderService orderService;
    @PostMapping("/order/create")
    public CommonResult create(Order order){

        try {
            orderService.create(order);
        } catch (Exception e) {
            e.printStackTrace();
            return new CommonResult(400,e.getMessage());
        }

        return new CommonResult(200,"订单创建成功！");
    }
}
