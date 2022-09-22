package org.rizki.mufrizal.gateway.repository;

import org.rizki.mufrizal.gateway.domain.ApiRoute;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApiRouteRepository extends JpaRepository<ApiRoute, String> {
    List<ApiRoute> findAllByEnabledIsTrue();
}
