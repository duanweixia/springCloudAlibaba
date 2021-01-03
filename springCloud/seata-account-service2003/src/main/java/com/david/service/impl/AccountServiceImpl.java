package com.david.service.impl;

import com.david.dao.AccountDao;
import com.david.domain.Account;
import com.david.service.AccountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

/**
 * @Package: com.david.service.impl
 * @ClassName: AccountServiceImpl
 * @Author: zhangweixia
 * @CreateTime: 2021/1/2 下午5:44
 * @Description:
 */
@Service
public class AccountServiceImpl implements AccountService {
    @Resource
    private AccountDao accountDao;
    @Override
    public void derease(Long userId, BigDecimal money) {
        //feign默认调用超时为1秒，这里手动模拟超时异常事物的回滚
       /* try {
            TimeUnit.SECONDS.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        Account account = new Account();

        account.setUserId(userId);
        account.setMoney(money);
        accountDao.decrease(account);

    }
}
