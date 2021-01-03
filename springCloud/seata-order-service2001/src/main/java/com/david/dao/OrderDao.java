package com.david.dao;

import com.david.domain.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Package: com.david.dao
 * @ClassName: OrderDao
 * @Author: zhangweixia
 * @CreateTime: 2021/1/2 下午5:20
 * @Description:
 */
@Mapper
public interface OrderDao {

    void create(Order order);

    void update(@Param("userId") Long userId,@Param("status") Integer status);


}
