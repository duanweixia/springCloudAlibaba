package com.david.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Package: com.david.entities
 * @ClassName: Payment
 * @Author: zhangweixia
 * @CreateTime: 2020/12/21 下午2:29
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment implements Serializable {

    private long id;

    private String serial;

}
