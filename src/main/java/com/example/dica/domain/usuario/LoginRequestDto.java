package com.example.dica.domain.usuario;

import jakarta.validation.constraints.NotBlank;
import org.antlr.v4.runtime.misc.NotNull;

public record LoginRequestDto(
        @NotBlank
        String email,
        @NotBlank
        String password
) {
}
