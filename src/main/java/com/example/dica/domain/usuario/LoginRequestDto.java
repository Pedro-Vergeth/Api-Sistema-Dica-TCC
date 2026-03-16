package com.example.dica.domain.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.antlr.v4.runtime.misc.NotNull;

public record LoginRequestDto(
        @NotBlank(message = "O campo email é obrigatório")
        @Email(message = "O campo email deve ser um endereço de email válido")
        String email,
        @NotBlank(message = "O campo password é obrigatório")
        String password
) {
}
