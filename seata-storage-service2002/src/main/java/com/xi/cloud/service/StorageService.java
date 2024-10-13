package com.xi.cloud.service;

/**
 * @author ZC_Wu 汐
 * @date 2024/10/13 11:54
 * @description
 */
public interface StorageService {
    /**
     * 扣减库存
     */
    void decrease(Long productId, Integer count);
}

