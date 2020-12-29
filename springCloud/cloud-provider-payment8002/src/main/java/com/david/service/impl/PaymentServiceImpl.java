package com.david.service.impl;

import com.david.dao.PaymentDao;
import com.david.entities.Payment;
import com.david.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Package: com.david.service.impl
 * @ClassName: PaymentServiceImpl
 * @Author: zhangweixia
 * @CreateTime: 2020/12/21 下午3:35
 * @Description:
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    //@Resource注解是JDK自带的注解，@Autowired注解是spring框架的注解
    @Resource
    private PaymentDao paymentDao;

    @Override
    public int add(Payment payment) {
        return paymentDao.add(payment);
    }

    @Override
    public Payment getPaymentById(long id) {
        return paymentDao.getPaymentById(id);
    }
}
