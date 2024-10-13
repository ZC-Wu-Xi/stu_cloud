package com.xi.cloud.apis;

import com.xi.cloud.resp.ResultData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author ZC_Wu 汐
 * @date 2024/10/13 10:44
 * @description  账户Feign接口
 */
@FeignClient(value = "seata-account-service")
public interface AccountFeignApi {
    //扣减账户余额
    @PostMapping("/account/decrease")
    ResultData decrease(@RequestParam("userId") Long userId, @RequestParam("money") Long money);
}
