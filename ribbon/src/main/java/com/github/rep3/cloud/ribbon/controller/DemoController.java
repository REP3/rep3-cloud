package com.github.rep3.cloud.ribbon.controller;

import com.github.rep3.cloud.ribbon.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class DemoController {

    @Autowired
    DemoService demoService;

    @GetMapping(value = "/demo")
    String demo() {
        return demoService.toAuth();
    }

}
