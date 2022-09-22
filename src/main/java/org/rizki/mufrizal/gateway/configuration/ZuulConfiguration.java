package org.rizki.mufrizal.gateway.configuration;

import org.rizki.mufrizal.gateway.router.ApiPathRouteLocator;
import org.rizki.mufrizal.gateway.service.ApiRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ZuulConfiguration {

    @Autowired
    private ZuulProperties zuulProperties;

    @Autowired
    private ServerProperties serverProperties;

    @Autowired
    private ApiRouteService apiRouteService;

    @Bean
    public ApiPathRouteLocator apiPathRouteLocator() {
        return new ApiPathRouteLocator(serverProperties.getServlet().getContextPath(), zuulProperties, apiRouteService);
    }

}