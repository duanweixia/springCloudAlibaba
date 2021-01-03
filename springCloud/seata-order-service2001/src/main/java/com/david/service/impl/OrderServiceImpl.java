package com.david.service.impl;

import com.david.dao.OrderDao;
import com.david.domain.Order;
import com.david.service.AccountService;
import com.david.service.OrderService;
import com.david.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Package: com.david.service.impl
 * @ClassName: OrderServiceImpl
 * @Author: zhangweixia
 * @CreateTime: 2021/1/2 下午5:41
 * @Description:
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderDao orderDao;
    @Resource
    private AccountService accountService;
    @Resource
    private StorageService storageService;

    @Override
    //全局异常处理，指定所有异常都会回滚
    @GlobalTransactional(name = "aaa",rollbackFor = Exception.class)
    // 这个就是TM（谁带@GlobalTransactional这个注解就是TM）,
    //一个数据库就是一个RM,我们这次的三个数据库就是RM. seata服务器就是TC
    //服务上线用阿里云 GTS 模式
    //本地测试用的免费版AT模式
    public void create(Order order) {
        // 下订单->减库存->减余额->改状态
      log.info("开始创建订单");
      orderDao.create(order);

      log.info("订单微服务开始调用库存，做扣减库存");
      storageService.decrease(order.getProductId(),order.getCount());

      log.info("订单微服务开始调用账户微服务，减账户余额");
      accountService.decrease(order.getUserId(),order.getMoney());

      //修改订单状态开始
      log.info("修改订单状态开始");
      orderDao.update(order.getUserId(),0);
    }

    @Override
    public void update(Long userId, Integer status) {

    }
}
