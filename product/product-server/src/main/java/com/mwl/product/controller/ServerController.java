package com.mwl.product.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mawenlong
 * @date 2018/12/18
 */
@RestController
public class ServerController {

    @GetMapping
    public String msg() {
        return "this is product msg";
    }
}
