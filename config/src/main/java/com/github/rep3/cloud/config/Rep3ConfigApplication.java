package com.github.rep3.cloud.config;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringCloudApplication
@EnableConfigServer
public class Rep3ConfigApplication {
    public static void main(String[] args) {
        SpringApplication.run(Rep3ConfigApplication.class, args);
    }
}
