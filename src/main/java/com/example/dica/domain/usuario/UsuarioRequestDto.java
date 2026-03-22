package com.example.dica.domain.usuario;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumeratedValue;
import jakarta.validation.constraints.*;

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
