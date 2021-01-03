package com.david.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Package: com.david.domain
 * @ClassName: Storage
 * @Author: zhangweixia
 * @CreateTime: 2021/1/2 下午7:31
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Storage implements Serializable {

    private Long productId;

    private Integer total;
}
