package com.david.controller;

import com.david.common.CommonResult;
import com.david.entities.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @Package: com.david.controller
 * @ClassName: PaymentController
 * @Author: zhangweixia
 * @CreateTime: 2020/12/31 下午6:13
 * @Description:
 */
@RestController
public class PaymentController {
    @Value("${server.port}")
    private String port;

    public static HashMap<Long, Payment> hashMap = new HashMap<>();

    static {
        hashMap.put(1L,new Payment(1L,"aaaaa"));
        hashMap.put(2L,new Payment(2L,"bbbbb"));
        hashMap.put(3L,new Payment(3L,"bbbbb"));
    }
    @GetMapping("/paymentSql/{id}")
    public CommonResult paymentSql(@PathVariable("id") Long id){

        Payment payment = hashMap.get(id);
        CommonResult<Payment> result = new CommonResult<>(200, "from mysql,port=" + port, payment);

        return  result;
    }
}
