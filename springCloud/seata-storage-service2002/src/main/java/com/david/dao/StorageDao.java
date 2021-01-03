package com.david.dao;

import com.david.domain.Storage;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Package: com.david.dao
 * @ClassName: StorageDao
 * @Author: zhangweixia
 * @CreateTime: 2021/1/2 下午7:24
 * @Description:
 */
@Mapper
public interface StorageDao {
    void decrease(Storage storage);
}
