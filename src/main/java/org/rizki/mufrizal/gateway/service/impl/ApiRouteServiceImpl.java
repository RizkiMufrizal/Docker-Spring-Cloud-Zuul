package org.rizki.mufrizal.gateway.service.impl;

import org.rizki.mufrizal.gateway.domain.ApiRoute;
import org.rizki.mufrizal.gateway.repository.ApiRouteRepository;
import org.rizki.mufrizal.gateway.service.ApiRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApiRouteServiceImpl implements ApiRouteService {

    @Autowired
    private ApiRouteRepository apiRouteRepository;

    @Override
    public List<ApiRoute> findAllByEnabledIsTrue() {
        return apiRouteRepository.findAllByEnabledIsTrue();
    }
}
