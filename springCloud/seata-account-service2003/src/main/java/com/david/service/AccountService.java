package com.david.service;

import java.math.BigDecimal;

/**
 * @Package: com.david.service
 * @ClassName: AccountService
 * @Author: zhangweixia
 * @CreateTime: 2021/1/2 下午5:43
 * @Description:
 */
public interface AccountService {


    void derease(Long userId, BigDecimal money);
}
