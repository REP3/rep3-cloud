package com.github.rep3.cloud.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class Rep3EurekaApplication {
    public static void main(String[] args) {
        SpringApplication.run(Rep3EurekaApplication.class, args);
    }
}
