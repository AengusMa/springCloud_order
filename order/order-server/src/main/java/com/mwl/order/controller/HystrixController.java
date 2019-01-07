package com.mwl.order.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

/**
 * @author mawenlong
 * @date 2019/01/07
 */
@RestController
//默认服务降级方法
@DefaultProperties(defaultFallback = "defaultFallback")
public class HystrixController {

    //超时配置
    // @HystrixCommand(fallbackMethod = "fallback",commandProperties = {
    //         @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000")
    // })
    // @HystrixCommand( commandProperties = {
    //         @HystrixProperty(name = "circuitBreaker.enabled", value = "true"), //设置熔断
    //         @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"), //断路器的最小请求数
    //         @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),//休眠时间窗
    //         @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")//断路器打开错误的百分比条件
    // })
    @HystrixCommand
    @GetMapping("/getProductInfoList")
    public String getProductInfoList(@RequestParam("num") Integer num) {
        if (num % 2 == 0) {
            return "success";
        }
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForObject("http://127.0.0.1:6061/product/listForOrder",
                                          Arrays.asList("157875196366160022"), String.class);
        // throw new RuntimeException("发生异常");也会触发服务降级
    }

    private String fallback() {
        return "太拥挤了，请稍后再试~~";
    }

    private String defaultFallback() {
        return "default:-->太拥挤了，请稍后再试~~";
    }
}
