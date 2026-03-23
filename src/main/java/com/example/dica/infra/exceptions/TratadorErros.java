package com.example.dica.infra.exceptions;

import com.auth0.jwt.exceptions.SignatureVerificationException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;
import java.security.SignatureException;

@RestControllerAdvice
public class TratadorErros {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity error400(MethodArgumentNotValidException ex){
        var erros = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(erros.stream().map(DadosErroValidacao::new));
    }
    private record DadosErroValidacao(String campo, String mensagem) {
        public DadosErroValidacao(FieldError erro) {
            this(erro.getField(), erro.getDefaultMessage());
        }
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity error401(BadCredentialsException ex){
        return ResponseEntity.status(401).body(new DefaultErrorDto(401, "Unauthorized", "Credenciais inválidas", java.time.LocalDateTime.now()));
    }

    @ExceptionHandler(SignatureVerificationException.class)
    public ResponseEntity errorInvalidToken(SignatureVerificationException ex){
        return ResponseEntity.status(403).body(new DefaultErrorDto(403, "Forbidden", "Token inválido", java.time.LocalDateTime.now()));
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity error403(AccessDeniedException ex){
        return ResponseEntity.status(403).body(new DefaultErrorDto(403, "Forbidden", "Acesso negado", java.time.LocalDateTime.now()));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity errorRuntime(RuntimeException ex){
        return ResponseEntity.status(400).body(new DefaultErrorDto(400, "Bad Request", ex.getMessage(), java.time.LocalDateTime.now()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity errorIllegalArgument(IllegalArgumentException ex){
        return ResponseEntity.status(400).body(new DefaultErrorDto(400, "Bad Request", ex.getMessage(), java.time.LocalDateTime.now()));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity errorEntityNotFound(EntityNotFoundException ex){
        return ResponseEntity.status(404).body(new DefaultErrorDto(404, "Not Found", ex.getMessage(), java.time.LocalDateTime.now()));
    }

}
