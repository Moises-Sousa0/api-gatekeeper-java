package com.gatekeeper.gatekeeper.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private boolean active;

    @Positive
    @Column(name = "request_limit", nullable = false)
    private int requestLimit;

    protected Client() {
    }

    public Client(String name, int requestLimit) {
        this.name = name;
        this.requestLimit = requestLimit;
        this.active = true;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isActive() {
        return active;
    }

    public int getRequestLimit() {
        return requestLimit;
    }
}
