package com.github.rep3.cloud.hystrix.service;

import com.github.rep3.cloud.hystrix.command.HystrixDemoGetCommand;
import com.github.rep3.cloud.hystrix.command.HystrixDemoPostCommand;
import com.github.rep3.cloud.hystrix.entity.HystrixDemoEntity;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheKey;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheRemove;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HystrixDemoService {

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand
    public HystrixDemoEntity getHystrixDemoByStrSync() {
        return new HystrixDemoGetCommand(restTemplate, "abc").execute();
    }

    @HystrixCommand
    public HystrixDemoEntity setHystrixDemoByStrSync() {
        return new HystrixDemoPostCommand(restTemplate, new HystrixDemoEntity("abc")).execute();
    }


    @CacheResult
    @HystrixCommand
    public HystrixDemoEntity getHystrixDemoByStrSyncCached(@CacheKey("str") String str) {
        return restTemplate.getForObject("http://auth/users/{1}", HystrixDemoEntity.class, "str");
    }

    @CacheRemove(commandKey = "getHystrixDemoByStrSyncCached")
    @HystrixCommand
    public HystrixDemoEntity setHystrixDemoByStrSyncRemoveCache(@CacheKey("str") HystrixDemoEntity demo) {
        return restTemplate.postForObject("http://auth/users", demo, HystrixDemoEntity.class);
    }

}
