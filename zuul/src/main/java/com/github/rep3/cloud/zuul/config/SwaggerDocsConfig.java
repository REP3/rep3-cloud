package com.github.rep3.cloud.zuul.config;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;

@Component
@Primary
public class SwaggerDocsConfig implements SwaggerResourcesProvider {
    @Override
    public List<SwaggerResource> get() {
        List<SwaggerResource> resources = new ArrayList<SwaggerResource>();

        SwaggerResource auth = new SwaggerResource();
        auth.setName("auth");
        auth.setUrl("/auth/v2/api-docs");
        auth.setSwaggerVersion("2.0");
        resources.add(auth);

        SwaggerResource hystrix = new SwaggerResource();
        hystrix.setName("hystrix");
        hystrix.setUrl("/hystrix/v2/api-docs");
        hystrix.setSwaggerVersion("2.0");
        resources.add(hystrix);

        return resources;
    }

}
