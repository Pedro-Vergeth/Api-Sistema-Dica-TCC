package com.example.dica.infra.exceptions;

import com.auth0.jwt.exceptions.SignatureVerificationException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.RestClientException;

import java.nio.file.AccessDeniedException;

@RestControllerAdvice
public class TratadorErros {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> error400(MethodArgumentNotValidException ex){
        var erros = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(erros.stream().map(DadosErroValidacao::new));
    }

    private record DadosErroValidacao(String campo, String mensagem) {
        public DadosErroValidacao(FieldError erro) {
            this(erro.getField(), erro.getDefaultMessage());
        }
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<?> error401(BadCredentialsException ex){
        ex.getMessage();
        return ResponseEntity.status(401).body(new DefaultErrorDto(401, "Unauthorized", "Credenciais inválidas", java.time.LocalDateTime.now()));
    }

    @ExceptionHandler(SignatureVerificationException.class)
    public ResponseEntity<?> errorInvalidToken(SignatureVerificationException ex){
        ex.getMessage();
        return ResponseEntity.status(403).body(new DefaultErrorDto(403, "Forbidden", "Token inválido", java.time.LocalDateTime.now()));
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<?> error403(AccessDeniedException ex){
        ex.getMessage();
        return ResponseEntity.status(403).body(new DefaultErrorDto(403, "Forbidden", "Acesso negado", java.time.LocalDateTime.now()));
    }

    @ExceptionHandler(RestClientException.class)
    public ResponseEntity<?> errorRestClient(RestClientException ex){
        ex.getMessage();
        return ResponseEntity.status(502).body(new DefaultErrorDto(502, "Bad Gateway", "Não foi possível consultar o serviço de IA", java.time.LocalDateTime.now()));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> errorRuntime(RuntimeException ex){
        return ResponseEntity.status(400).body(new DefaultErrorDto(400, "Bad Request", ex.getMessage(), java.time.LocalDateTime.now()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> errorIllegalArgument(IllegalArgumentException ex){
        return ResponseEntity.status(400).body(new DefaultErrorDto(400, "Bad Request", ex.getMessage(), java.time.LocalDateTime.now()));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> errorEntityNotFound(EntityNotFoundException ex){
        return ResponseEntity.status(404).body(new DefaultErrorDto(404, "Not Found", ex.getMessage(), java.time.LocalDateTime.now()));
    }

    @ExceptionHandler(InternalAuthenticationServiceException.class)
    public ResponseEntity<?> erroAuthentication(InternalAuthenticationServiceException ex){
        ex.getMessage();
        return ResponseEntity.status(401).body(new DefaultErrorDto(401, "Unauthorized", "Email ou senha incorretos", java.time.LocalDateTime.now()));
    }
}
