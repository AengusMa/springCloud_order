package com.mwl.order.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * @author mawenlong
 * @date 2018/12/22
 */
@Data
@Component
@ConfigurationProperties("person")
@RefreshScope
public class PersonConfig {
    private String name;
    private Integer age;
}
