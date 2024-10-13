package com.xi.cloud.service;

import com.xi.cloud.entities.Order;

/**
 * @author ZC_Wu 汐
 * @date 2024/10/13 11:31
 * @description
 */
public interface OrderService {
    /**
     * 创建订单
     */
    void create(Order order);
}
