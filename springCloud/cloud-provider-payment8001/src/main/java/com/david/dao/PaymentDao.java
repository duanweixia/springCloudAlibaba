package com.david.dao;

import com.david.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Package: com.david.dao
 * @ClassName: PaymentDao
 * @Author: zhangweixia
 * @CreateTime: 2020/12/21 下午2:45
 * @Description:
 */
@Mapper //此处使用@Mapper注解，是因为该注解是ibatis包下面的，跟mybatis能更好集成，
// 如果使用@Repository注解，集成mybatis时可能会有错误
//@Repository
public interface PaymentDao {

    public int add(Payment payment);

    public Payment getPaymentById(@Param("id") long id);


}
