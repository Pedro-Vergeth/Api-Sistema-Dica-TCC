package com.example.dica.domain.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UsuarioUpdateDto(
        @NotNull(message = "Id é obrigatório")
        Long id,
        String nome,
        @Email(message = "O email deve está no formato de email")
        String email,
        Role role
) {
}
