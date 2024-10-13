package com.xi.cloud.controller;

import com.xi.cloud.entities.Order;
import com.xi.cloud.resp.ResultData;
import com.xi.cloud.service.OrderService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZC_Wu 汐
 * @date 2024/10/13 11:47
 * @description
 */
@RestController
public class OrderController {

    @Resource
    private OrderService orderService;

    /**
     * 创建订单
     */
    @GetMapping("/order/create")
    public ResultData create(Order order) {
        orderService.create(order);
        return ResultData.success(order);
    }
}