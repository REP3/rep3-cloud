package com.github.rep3.cloud.zuul.config;

import com.github.rep3.cloud.zuul.filter.AuthFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ZuulFilterConfig {

    @Bean
    public AuthFilter INIT_AUTH_FILTER() {
        return new AuthFilter();
    }
}
