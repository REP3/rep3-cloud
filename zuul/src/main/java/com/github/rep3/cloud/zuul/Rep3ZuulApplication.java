package com.github.rep3.cloud.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableZuulProxy
@EnableFeignClients
@SpringCloudApplication
public class Rep3ZuulApplication {
    public static void main(String[] args) {
        SpringApplication.run(Rep3ZuulApplication.class, args);
    }
}
