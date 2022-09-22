package org.rizki.mufrizal.gateway.router;

import lombok.extern.slf4j.Slf4j;
import org.rizki.mufrizal.gateway.domain.ApiRoute;
import org.rizki.mufrizal.gateway.service.ApiRouteService;
import org.springframework.cloud.netflix.zuul.filters.RefreshableRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.SimpleRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Slf4j
public class ApiPathRouteLocator extends SimpleRouteLocator implements RefreshableRouteLocator {

    protected ApiRouteService apiRouteService;
    protected ZuulProperties zuulProperties;

    public ApiPathRouteLocator(String servletPath, ZuulProperties zuulProperties, ApiRouteService apiRouteService) {
        super(servletPath, zuulProperties);
        this.zuulProperties = zuulProperties;
        this.apiRouteService = apiRouteService;
        log.info("servletPath {}", servletPath);
    }

    @Override
    public void refresh() {
        doRefresh();
    }

    @Override
    protected Map<String, ZuulProperties.ZuulRoute> locateRoutes() {
        LinkedHashMap<String, ZuulProperties.ZuulRoute> routesMap = new LinkedHashMap<>();
        routesMap.putAll(super.locateRoutes());
        routesMap.putAll(getRouteList());
        LinkedHashMap<String, ZuulProperties.ZuulRoute> values = new LinkedHashMap<>();
        for (Map.Entry<String, ZuulProperties.ZuulRoute> entry : routesMap.entrySet()) {
            String path = entry.getKey();
            if (!path.startsWith("/")) {
                path = "/" + path;
            }
            if (StringUtils.hasText(this.zuulProperties.getPrefix())) {
                path = this.zuulProperties.getPrefix() + path;
                if (!path.startsWith("/")) {
                    path = "/" + path;
                }
            }
            values.put(path, entry.getValue());
        }
        return values;
    }

    private LinkedHashMap<String, ZuulProperties.ZuulRoute> getRouteList() {
        LinkedHashMap<String, ZuulProperties.ZuulRoute> zuulRoutes = new LinkedHashMap<>();
        List<ApiRoute> sysApiRoutes = apiRouteService.findAllByEnabledIsTrue();

        for (ApiRoute route : sysApiRoutes) {
            if (route.getPath().isEmpty() && route.getUrl().isEmpty()) {
                continue;
            }

            ZuulProperties.ZuulRoute zuulRoute = new ZuulProperties.ZuulRoute();
            try {
                zuulRoute.setId(route.getId());
                zuulRoute.setPath(route.getPath());
                zuulRoute.setServiceId(route.getId());
                zuulRoute.setRetryable(false);
                zuulRoute.setStripPrefix(route.getStripPrefix());
                zuulRoute.setUrl(route.getUrl());
                Set<String> sensitiveHeaderSet = new HashSet<>();
                zuulRoute.setSensitiveHeaders(sensitiveHeaderSet);
                zuulRoute.setCustomSensitiveHeaders(true);
            } catch (Exception e) {
                log.error("error", e);
            }
            log.info("path：{}，serviceId:{}", zuulRoute.getPath(), zuulRoute.getServiceId());
            zuulRoutes.put(zuulRoute.getPath(), zuulRoute);
        }
        return zuulRoutes;
    }
}
