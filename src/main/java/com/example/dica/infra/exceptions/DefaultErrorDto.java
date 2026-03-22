package com.example.dica.infra.exceptions;

import java.time.LocalDateTime;

public record DefaultErrorDto(
        int status,
        String erro,
        String message,
        LocalDateTime timestamp
) {
}
