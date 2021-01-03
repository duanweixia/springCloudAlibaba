package com.david.service.impl;

import com.david.dao.StorageDao;
import com.david.domain.Storage;
import com.david.service.StorageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @Package: com.david.service.impl
 * @ClassName: StorageServiceImpl
 * @Author: zhangweixia
 * @CreateTime: 2021/1/2 下午5:45
 * @Description:
 */
@Service
public class StorageServiceImpl implements StorageService {
    @Resource
    private StorageDao storageDao;

    @Override
    public void decrease(Long productId, Integer count) {
        Storage storage = new Storage();
        storage.setProductId(productId);
        storage.setTotal(count);
        storageDao.decrease(storage);
    }
}
