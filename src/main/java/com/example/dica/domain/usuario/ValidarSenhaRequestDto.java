package com.example.dica.domain.usuario;

import jakarta.validation.constraints.NotBlank;

public record ValidarSenhaRequestDto(
        @NotBlank(message = "A senha é obrigatória")
        String password
) {
}

