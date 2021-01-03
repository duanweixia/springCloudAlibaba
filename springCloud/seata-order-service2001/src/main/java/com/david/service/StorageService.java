package com.david.service;

import com.david.domain.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Package: com.david.service
 * @ClassName: StorageService
 * @Author: zhangweixia
 * @CreateTime: 2021/1/2 下午5:49
 * @Description:  库存微服务用feign远程调用
 */
@FeignClient(value = "seata-storage-service")
public interface StorageService {
    //减少库存
    @PostMapping("/storage/decrease")
    CommonResult decrease(@RequestParam("productId") Long productId,
                          @RequestParam("count") Integer count);
}
