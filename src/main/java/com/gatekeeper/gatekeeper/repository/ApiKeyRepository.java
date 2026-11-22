package com.gatekeeper.gatekeeper.repository;

import com.gatekeeper.gatekeeper.domain.ApiKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApiKeyRepository extends JpaRepository<ApiKey, Long> {
}
