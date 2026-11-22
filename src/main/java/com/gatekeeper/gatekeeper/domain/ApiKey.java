package com.gatekeeper.gatekeeper.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "api_keys")
public class ApiKey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @NotBlank
    @Column(name = "key_hash", nullable = false)
    private String keyHash;

    @Column(nullable = false)
    private boolean active;

    protected ApiKey() {
    }

    public ApiKey(Client client, String keyHash, boolean active) {
        this.client = client;
        this.keyHash = keyHash;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public Client getClient() {
        return client;
    }

    public String getKeyHash() {
        return keyHash;
    }

    public boolean isActive() {
        return active;
    }
}
