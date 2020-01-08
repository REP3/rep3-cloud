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
        List<SwaggerResource> resources = new ArrayList();
        SwaggerResource auth = new SwaggerResource();
        auth.setName("auth");
        auth.setSwaggerVersion("2.0");
        auth.setUrl("/auth/v2/api-docs");
        resources.add(auth);
        SwaggerResource hystrix = new SwaggerResource();
        hystrix.setName("hystrix");
        hystrix.setSwaggerVersion("2.0");
        hystrix.setUrl("/hystrix/v2/api-docs");
        resources.add(hystrix);
        return resources;
    }


    private SwaggerResource swaggerResource(String name, String location, String version) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion(version);
        return swaggerResource;
    }
}
