package com.david;

import java.time.ZonedDateTime;

/**
 * @Package: com.david
 * @ClassName: CurentTime
 * @Author: zhangweixia
 * @CreateTime: 2020/12/28 下午7:28
 * @Description:
 */
public class CurentTime {

    public static void main(String[] args) {
        ZonedDateTime now = ZonedDateTime.now(); //默认时区
        System.out.println(now);//2020-12-28T19:29:19.826+08:00[Asia/Shanghai]
    }
}
