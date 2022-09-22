package org.rizki.mufrizal.gateway.service;

import org.rizki.mufrizal.gateway.domain.ApiRoute;

import java.util.List;

public interface ApiRouteService {
    List<ApiRoute> findAllByEnabledIsTrue();
}
