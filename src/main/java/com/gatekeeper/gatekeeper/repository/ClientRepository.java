package com.gatekeeper.gatekeeper.repository;

import com.gatekeeper.gatekeeper.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
