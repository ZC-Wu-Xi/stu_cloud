package com.xi.cloud.service.impl;

import com.xi.cloud.mapper.StorageMapper;
import com.xi.cloud.service.StorageService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author ZC_Wu 汐
 * @date 2024/10/13 11:55
 * @description
 */
@Service
@Slf4j
public class StorageServiceImpl implements StorageService {

    @Resource
    private StorageMapper storageMapper;

    /**
     * 扣减库存
     */
    @Override
    public void decrease(Long productId, Integer count) {
        System.out.println("productId = " + productId);
        System.out.println("count = " + count);
        log.info("------->storage-service中扣减库存开始");
        storageMapper.decrease(productId,count);
        log.info("------->storage-service中扣减库存结束");
    }
}