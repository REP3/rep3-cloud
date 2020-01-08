package com.github.rep3.cloud.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringCloudApplication
@EnableFeignClients
public class Rep3AuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(Rep3AuthApplication.class, args);
    }
}
