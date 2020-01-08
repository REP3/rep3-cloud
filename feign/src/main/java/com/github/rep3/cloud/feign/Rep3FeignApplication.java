package com.github.rep3.cloud.feign;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

@SpringCloudApplication
public class Rep3FeignApplication {
    public static void main(String[] args) {
        SpringApplication.run(Rep3FeignApplication.class, args);
    }
}
