package com.github.rep3.cloud.turbine;


import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.turbine.EnableTurbine;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringCloudApplication
@EnableTurbine
@EnableFeignClients
public class Rep3TurbineApplication {
    public static void main(String[] args) {
        SpringApplication.run(Rep3TurbineApplication.class, args);
    }
}
