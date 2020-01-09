package com.github.rep3.cloud.auth.controller;

import com.github.rep3.cloud.common.service.HelloService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Api
@RestController
public class AuthController implements HelloService {
    @GetMapping("/halo/{name}")
    public String halo(@PathVariable("name") String name) {
        return "halo" + name;
    }

    @Override
    public String hello(String name) {
        return "Hello"+name;
    }
}
