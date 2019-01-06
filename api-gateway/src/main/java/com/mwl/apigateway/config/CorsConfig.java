package com.mwl.apigateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

/**
 * @author mawenlong
 * @date 2019/01/06
 * <p>
 * 跨域配置 C-Cross O - Origin R - Resource S - Sharing
 */
@Component
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration configuration = new CorsConfiguration();
        //是否支持cookie跨域
        configuration.setAllowCredentials(true);
        //放那些原始域
        configuration.setAllowedOrigins(Arrays.asList("*"));
        //允许的头
        configuration.setAllowedHeaders(Arrays.asList("*"));
        // 允许的方法
        configuration.setAllowedMethods(Arrays.asList("*"));
        // 缓存时间，在一段时间对于相同的请求不在检查
        configuration.setMaxAge(300l);
        source.registerCorsConfiguration("/**", configuration);
        return new CorsFilter(source);
    }

}
