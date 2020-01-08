package com.github.rep3.cloud.hystrix.command;

import com.github.rep3.cloud.hystrix.entity.HystrixDemoEntity;
import com.netflix.hystrix.*;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategyDefault;
import org.springframework.web.client.RestTemplate;

public class HystrixDemoGetCommand extends HystrixCommand<HystrixDemoEntity> {

    private static final HystrixCommandGroupKey GROUP_KEY = HystrixCommandGroupKey.Factory.asKey("hystrixDemoGroup");
    private static final HystrixCommandKey COMMAND_KEY = HystrixCommandKey.Factory.asKey("hystrixCommandKey");
    private static final HystrixThreadPoolKey THREAD_POOL_KEY = HystrixThreadPoolKey.Factory.asKey("hystrixThreadPoolKey");
    private RestTemplate restTemplate;
    private String str;

    public HystrixDemoGetCommand(RestTemplate restTemplate, String str) {
        super(Setter.withGroupKey(GROUP_KEY).andCommandKey(COMMAND_KEY).andThreadPoolKey(THREAD_POOL_KEY));
        this.restTemplate = restTemplate;
        this.str = str;
    }

    @Override
    protected HystrixDemoEntity run() throws Exception {
        return this.restTemplate.getForObject("http://auth/hystrixdemos/{1}", HystrixDemoEntity.class, str);
    }

    protected HystrixDemoEntity getFallback(Throwable e) {
        return new HystrixDemoEntity("GET服务降级了!"+e.getLocalizedMessage());
    }

    @Override
    protected String getCacheKey() {
        // 根据字符串参数置入缓存
        return str;
    }

    public static void flushCache(String str) {
        HystrixRequestCache.getInstance(COMMAND_KEY, HystrixConcurrencyStrategyDefault.getInstance()).clear(str);
    }
}
