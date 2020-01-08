package com.github.rep3.cloud.hystrix.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("auth")
public interface FeignService {

    @GetMapping("/halo/{name}")
    String Halo(@PathVariable("name") String name);
}
