package com.github.rep3.cloud.hystrix;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringCloudApplication
@EnableHystrixDashboard
@EnableFeignClients
public class Rep3HystrixApplication {
    public static void main(String[] args) {
        SpringApplication.run(Rep3HystrixApplication.class, args);
    }
}
