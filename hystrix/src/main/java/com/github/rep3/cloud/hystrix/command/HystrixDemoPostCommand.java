package com.github.rep3.cloud.hystrix.command;

import com.github.rep3.cloud.hystrix.entity.HystrixDemoEntity;
import com.netflix.hystrix.*;
import org.springframework.web.client.RestTemplate;

public class HystrixDemoPostCommand extends HystrixCommand<HystrixDemoEntity> {

    private static final HystrixCommandGroupKey GROUP_KEY = HystrixCommandGroupKey.Factory.asKey("hystrixDemoGroup");
    private static final HystrixCommandKey COMMAND_KEY = HystrixCommandKey.Factory.asKey("hystrixCommandKey");
    private static final HystrixThreadPoolKey THREAD_POOL_KEY = HystrixThreadPoolKey.Factory.asKey("hystrixThreadPoolKey");
    private RestTemplate restTemplate;
    private HystrixDemoEntity entity;

    public HystrixDemoPostCommand(RestTemplate restTemplate, HystrixDemoEntity entity) {
        super(Setter.withGroupKey(GROUP_KEY).andCommandKey(COMMAND_KEY).andThreadPoolKey(THREAD_POOL_KEY));
        this.restTemplate = restTemplate;
        this.entity = entity;
    }

    @Override
    protected HystrixDemoEntity run() throws Exception {
        // post写入操作
        HystrixDemoEntity result = this.restTemplate.postForObject("http://auth/rhystrixdemos", this.entity, HystrixDemoEntity.class);
        // 刷新缓存
        HystrixDemoGetCommand.flushCache(this.entity.getStr());
        return result;
    }

    protected HystrixDemoEntity getFallback(Throwable e) {
        return new HystrixDemoEntity("POST服务降级了!" + e.getLocalizedMessage());
    }
}
