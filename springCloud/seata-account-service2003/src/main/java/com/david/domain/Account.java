package com.david.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Package: com.david.domain
 * @ClassName: Account
 * @Author: zhangweixia
 * @CreateTime: 2021/1/2 下午7:39
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account implements Serializable {

    private Long userId;

    private BigDecimal money;
}
