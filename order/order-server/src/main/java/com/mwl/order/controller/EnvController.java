package com.mwl.order.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mawenlong
 * @date 2018/12/19
 */
@RestController
@RequestMapping("env")
@RefreshScope
public class EnvController {

    @Value("${env}")
    public String env;

    @GetMapping("/print")
    public String print(){
        return env;
    }
}
