package org.rizki.mufrizal.gateway.service.impl;

import org.rizki.mufrizal.gateway.router.ApiPathRouteLocator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.event.HeartbeatEvent;
import org.springframework.cloud.client.discovery.event.HeartbeatMonitor;
import org.springframework.cloud.context.scope.refresh.RefreshScopeRefreshedEvent;
import org.springframework.cloud.netflix.zuul.RoutesRefreshedEvent;
import org.springframework.cloud.netflix.zuul.web.ZuulHandlerMapping;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

@Service
public class RefreshRouteServiceImpl implements ApplicationListener<ApplicationEvent> {

    @Autowired
    private ZuulHandlerMapping zuulHandlerMapping;

    protected HeartbeatMonitor heartbeatMonitor = new HeartbeatMonitor();

    @Autowired
    ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    private ApiPathRouteLocator apiPathRouteLocator;

    public void refreshRoute() {
        RoutesRefreshedEvent routesRefreshedEvent = new RoutesRefreshedEvent(apiPathRouteLocator);
        applicationEventPublisher.publishEvent(routesRefreshedEvent);
    }

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        if (applicationEvent instanceof ContextRefreshedEvent || applicationEvent instanceof RefreshScopeRefreshedEvent || applicationEvent instanceof RoutesRefreshedEvent) {
            zuulHandlerMapping.setDirty(true);
        } else if (applicationEvent instanceof HeartbeatEvent) {
            HeartbeatEvent heartbeatEvent = (HeartbeatEvent) applicationEvent;
            if (heartbeatMonitor.update(heartbeatEvent.getValue())) {
                zuulHandlerMapping.setDirty(true);
            }
        }
    }
}