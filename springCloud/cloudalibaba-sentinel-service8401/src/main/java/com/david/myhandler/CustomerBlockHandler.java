package com.david.myhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.david.common.CommonResult;

/**
 * @Package: com.david.myhandler
 * @ClassName: CustomerBlockHandler
 * @Author: zhangweixia
 * @CreateTime: 2020/12/31 下午5:42
 * @Description:
 */
public class CustomerBlockHandler {

    public static CommonResult handleException1(BlockException exception){

        return new CommonResult(444,
                "按客户自定义 1号方法");
    }

    public static CommonResult handleException2(BlockException exception){

        return new CommonResult(444,
                "按客户自定义 2号方法");
    }
}
