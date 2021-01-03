package com.david.controller;

import com.david.domain.CommonResult;
import com.david.service.StorageService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Package: com.david.controller
 * @ClassName: StorageController
 * @Author: zhangweixia
 * @CreateTime: 2021/1/2 下午7:18
 * @Description:
 */
@RestController
public class StorageController {
    @Resource
    private StorageService storageService;
    @PostMapping("/storage/decrease")
    public CommonResult decrease(@RequestParam("productId") Long productId,
                          @RequestParam("count") Integer count){
        storageService.decrease(productId,count);
        return new CommonResult(200,"扣减库存成功");
    }
}
