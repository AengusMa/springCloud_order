package com.mwl.order.controller;

import com.mwl.order.client.ProductClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author mawenlong
 * @date 2018/12/18
 */
@RestController
@Slf4j
public class ClientController {

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ProductClient productClient;

    @GetMapping("/getProductMsg")
    public String getProductMsg() {
        //1.第一种(直接使用)
        // RestTemplate restTemplate = new RestTemplate();
        // String msg = restTemplate.getForObject("http://localhost:8081/msg", String.class);
        // log.info("response={}", msg);
        //2.第二种（利用loadBalancerClient获取url）
        // ServiceInstance serviceInstance = loadBalancerClient.choose("PRODUCT");
        // serviceInstance.getHost();
        // String url = String.format("http://%s:%s", serviceInstance.getHost(), serviceInstance.getHost());
        // msg = restTemplate.getForObject(url+"/msg", String.class);
        //String msg = restTemplate.getForObject("http://PRODUCT/msg", String.class);
        String msg =productClient.productMsg();
        return msg;
    }
}
