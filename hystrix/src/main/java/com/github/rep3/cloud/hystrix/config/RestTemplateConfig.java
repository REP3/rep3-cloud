package com.github.rep3.cloud.hystrix.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate InitRestTemplateConfig() {
        return new RestTemplate();
    }
}
