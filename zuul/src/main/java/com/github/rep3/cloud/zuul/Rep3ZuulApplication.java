package com.github.rep3.cloud.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@EnableEurekaClient
@SpringBootApplication
public class Rep3ZuulApplication {
    public static void main(String[] args) {
        SpringApplication.run(Rep3ZuulApplication.class, args);
    }
}
