package com.github.rep3.cloud.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.context.annotation.Bean;

import javax.servlet.http.HttpServletRequest;

public class AuthFilter extends ZuulFilter {
    private final String FILTER_TYPE = "pre";

    @Override
    public String filterType() {
        return FILTER_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        Object accessToken = request.getParameter("accessToken");
        if (null == accessToken && !request.getRequestURI().contains("docs")) {
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
        }
        return null;
    }
}
