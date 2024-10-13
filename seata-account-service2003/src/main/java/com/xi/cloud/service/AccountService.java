package com.xi.cloud.service;

import feign.Param;

/**
 * @author ZC_Wu 汐
 * @date 2024/10/13 12:12
 * @description
 */
public interface AccountService {
    /**
     * 扣减账户余额
     * @param userId 用户id
     * @param money 本次消费金额
     */
    void decrease(@Param("userId") Long userId, @Param("money") Long money);
}
