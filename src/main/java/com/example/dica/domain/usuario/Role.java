package com.example.dica.domain.usuario;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Role {
    ADMIN,
    USER;

    @JsonCreator
    public static Role fromString(String valor) {
        if (valor == null) {
            return null;
        }
        String valorFormatado = valor.trim().toUpperCase();
        for (Role role : Role.values()) {
            if (role.name().equals(valorFormatado)) {
                return role;
            }
        }
        throw new IllegalArgumentException("Permissão inválida");
    }
}
