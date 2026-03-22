package com.example.dica.domain.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UsuarioRequestDto(
        @NotBlank(message = "Nome não pode ser em branco")
        String nome,
        @NotBlank(message = "Email não pode estar vazio")
        @Email(message = "Digite um email válido")
        String email,
        @NotBlank(message = "Password não pode ser nulo")
        @Min(value = 8, message = "Password deve conter no mínimo 8 caracteres")
        String password,
        @NotNull(message = "Role não pode ser nulo")
        Role role
) {
}
