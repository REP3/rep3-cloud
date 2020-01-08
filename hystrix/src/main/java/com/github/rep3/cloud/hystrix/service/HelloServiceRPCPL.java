package com.github.rep3.cloud.hystrix.service;

import com.github.rep3.cloud.common.service.HelloService;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("auth")
public interface HelloServiceRPCPL extends HelloService {
}
