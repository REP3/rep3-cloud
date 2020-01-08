package com.github.rep3.cloud.hystrix.controller;

import com.github.rep3.cloud.hystrix.entity.HystrixDemoEntity;
import com.github.rep3.cloud.hystrix.service.HystrixDemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HystrixDemoController {

    @Autowired
    HystrixDemoService hystrixDemoService;

    @GetMapping("testSync")
    public String getHystrixDemoByStrSync() {
        return hystrixDemoService.getHystrixDemoByStrSyncCached("aaa").toString();
    }

    @GetMapping("testPostSync")
    public String setHystrixDemoByStrSync() {
        return hystrixDemoService.setHystrixDemoByStrSyncRemoveCache(new HystrixDemoEntity("aaa")).toString();
    }


}
