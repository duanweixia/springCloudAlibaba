package com.david.service;

import com.david.domain.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

/**
 * @Package: com.david.service
 * @ClassName: AccountService
 * @Author: zhangweixia
 * @CreateTime: 2021/1/2 下午5:49
 * @Description:  用feign远程调用账户微服务
 */
@FeignClient(value = "seata-account-service")
public interface AccountService {
    @PostMapping("/account/decrease")
    CommonResult decrease(@RequestParam("userId") Long userId,
                          @RequestParam("money") BigDecimal money);
}
