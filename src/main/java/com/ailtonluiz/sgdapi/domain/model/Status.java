package com.ailtonluiz.sgdapi.domain.model;

import lombok.Getter;

public enum Status {

    ACTIVE("Active"),
    INACTIVE("Inactive");

    @Getter
    private String description;

    Status(String description) {
        this.description = description;
    }
}

