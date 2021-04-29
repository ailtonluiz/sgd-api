package com.ailtonluiz.sgdapi.domain.model;

import lombok.Getter;

public enum MovementSatus {
    ISSUED("Emitido"),
    BUDGET("Presupuesto"),
    CANCELED("Cancelado"),
    BROKEN_GOODS("Mercancías rota");

    @Getter
    private String description;

    MovementSatus(String description) {
        this.description = description;
    }


}