package com.mwl.order.controller;

import com.mwl.order.config.PersonConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mawenlong
 * @date 2018/12/22
 */
@RestController
public class PersonController {

    @Autowired
    private PersonConfig personConfig;

    @GetMapping("/person/print")
    public String print() {
        return personConfig.toString();
    }
}
