package com.xi.cloud.controller;

import com.xi.cloud.entities.PayDTO;
import com.xi.cloud.resp.ResultData;
import jakarta.annotation.Resource;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author Wu
 * @date 2024/8/9 16:09:09
 * @description
 */
@RestController
public class OrderController {
//    public static final String PaymentSrv_URL = "http://localhost:8001";// 先写死，硬编码
    public static final String PaymentSrv_URL = "http://cloud-payment-service";// 入驻进spring cloud consul时8001微服务的名字


    @Resource
    private RestTemplate restTemplate;

    /**
     * 添加订单
     * @param payDTO
     * @return
     */
    @GetMapping(value = "/consumer/pay/add")
    public ResultData addOrder(PayDTO payDTO) {
        return restTemplate.postForObject(PaymentSrv_URL + "/pay/add", payDTO, ResultData.class);
    }

    @GetMapping(value = "/consumer/pay/get/{id}")
    public ResultData getPayInfo(@PathVariable("id") Integer id) {
        return restTemplate.getForObject(PaymentSrv_URL + "/pay/get/" + id, ResultData.class, id);
    }

    // 删除、修改 家庭作业

    /**
     * 测试负载均衡
     * @return
     */
    @GetMapping(value = "/consumer/pay/get/info")
    private String getInfoByConsul()
    {
        return restTemplate.getForObject(PaymentSrv_URL + "/pay/get/info", String.class);
    }


    // ############################################################################################################
    /*
     * 负载均衡算法：rest接口第几次请求数 % 服务器集群总数量 = 实际调用服务器位置下标  ，每次服务重启动后rest接口计数从1开始。
     * List<ServiceInstance> instances = discoveryClient.getInstances("cloud-payment-service");
     * 如： List [0] instances = 127.0.0.1:8002
     * 　　 List [1] instances = 127.0.0.1:8001
     * 8001+ 8002 组合成为集群，它们共计2台机器，集群总数为2， 按照轮询算法原理：
     * 当总请求数为1时： 1 % 2 =1 对应下标位置为1 ，则获得服务地址为127.0.0.1:8001
     * 当总请求数位2时： 2 % 2 =0 对应下标位置为0 ，则获得服务地址为127.0.0.1:8002
     * 当总请求数位3时： 3 % 2 =1 对应下标位置为1 ，则获得服务地址为127.0.0.1:8001
     * 当总请求数位4时： 4 % 2 =0 对应下标位置为0 ，则获得服务地址为127.0.0.1:8002
     * 如此类推......
     */
    @Resource
    // DiscoveryClient：发现客户端
    private DiscoveryClient discoveryClient;

    /**
     * 模拟loadBalancer查询可用的服务列表，将consul上的微服务撸到本地
     * @return
     */
    @GetMapping("/consumer/discovery")
    public String discovery()
    {
        // 拿到consul服务器上的全部service名字
        List<String> services = discoveryClient.getServices();
        for (String element : services) {
            System.out.println(element);
        }

        System.out.println("===================================");

        // 找名字为这个的微服务
        List<ServiceInstance> instances = discoveryClient.getInstances("cloud-payment-service");
        // 打印该名字下微服务的 serviceId host port uri 等
        for (ServiceInstance element : instances) {
            System.out.println(element.getServiceId()+"\t"+element.getHost()+"\t"+element.getPort()+"\t"+element.getUri());
        }

        // 返回该名字第一个微服务的serviceId和port
        return instances.get(0).getServiceId()+":"+instances.get(0).getPort();
    }
}
