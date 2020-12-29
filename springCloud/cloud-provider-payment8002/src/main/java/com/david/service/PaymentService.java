package com.david.service;

import com.david.entities.Payment;

/**
 * @Package: com.david.service
 * @ClassName: PaymentService
 * @Author: zhangweixia
 * @CreateTime: 2020/12/21 下午3:34
 * @Description:
 */
public interface PaymentService {

    public int add(Payment payment);

    public Payment getPaymentById(long id);
}
