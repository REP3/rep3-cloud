package com.github.rep3.cloud.config;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

@SpringCloudApplication
public class Rep3ConfigApplication {
    public static void main(String[] args) {
        SpringApplication.run(Rep3ConfigApplication.class, args);
    }
}
