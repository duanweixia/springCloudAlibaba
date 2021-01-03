package com.david.dao;

import com.david.domain.Account;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Package: com.david.dao
 * @ClassName: AccountDao
 * @Author: zhangweixia
 * @CreateTime: 2021/1/2 下午7:44
 * @Description:
 */
@Mapper
public interface AccountDao {
    void decrease(Account account);
}
