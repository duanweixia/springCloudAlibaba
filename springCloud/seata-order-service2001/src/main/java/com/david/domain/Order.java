package com.david.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @Package: com.david.domain
 * @ClassName: Order
 * @Author: zhangweixia
 * @CreateTime: 2021/1/2 下午4:29
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    private Long id;

    private Long userId;

    private Long productId;

    private Integer count;

    private BigDecimal money;

    private Integer status; //0创建中，1已创建
}
