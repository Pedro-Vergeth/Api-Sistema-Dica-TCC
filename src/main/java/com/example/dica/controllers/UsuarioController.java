package com.example.dica.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("gerenciador/usuario")
public class UsuarioController {

    @GetMapping
    public ResponseEntity getUsuario() {
        return ResponseEntity.ok("Acesso permitido para usuário autenticado.");
    }
}
