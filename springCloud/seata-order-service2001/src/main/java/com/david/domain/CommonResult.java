package com.david.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Package: com.david.common
 * @ClassName: CommonResult
 * @Author: zhangweixia
 * @CreateTime: 2020/12/21 下午2:30
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {
    //类设置为<T>范性，是为了返回值有我们自定义
    private Integer code;

    private String message;

    private T  data;

    //单独构造一个只有2个基本参赛返回的方法

    public CommonResult(Integer code, String message){

        this(code,message,null);
    }



}
