package com.david.controller;

import com.david.domain.CommonResult;
import com.david.service.AccountService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @Package: com.david.controller
 * @ClassName: AccountController
 * @Author: zhangweixia
 * @CreateTime: 2021/1/2 下午7:41
 * @Description:
 */
@RestController
public class AccountController {
    @Resource
    private AccountService accountService;
    @PostMapping("/account/decrease")
    public CommonResult decrease(@RequestParam("userId") Long userId,
                          @RequestParam("money") BigDecimal money){

        accountService.derease(userId,money);
        return new CommonResult(200,"账户扣减成功！");
    }
}
