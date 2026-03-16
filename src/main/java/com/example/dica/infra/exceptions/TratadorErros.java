package com.example.dica.infra.exceptions;

import com.auth0.jwt.exceptions.SignatureVerificationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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
        return ResponseEntity.status(401).body("Email ou senha incorretos;");
    }

    @ExceptionHandler(SignatureVerificationException.class)
    public ResponseEntity errorInvalidToken(SignatureVerificationException ex){
        return ResponseEntity.status(403).body("Token inválido ou expirado;");
    }
}
